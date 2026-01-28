package frameworktest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
	
		String productName = "ZARA COAT 3";
		String productName1 = "iphone 13 pro";
		
		
		String country = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Login to page
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("rajini123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rajini@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				
		//Add item to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = productList.stream()
			.filter(s->s.findElement(By.xpath(".//b")).getText()
			.equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".btn.w-10")).click();
		
		List<WebElement> productList1 = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod1 = productList1.stream()
			.filter(s->s.findElement(By.xpath(".//b")).getText()
			.equalsIgnoreCase(productName1)).findFirst().orElse(null);
		prod1.findElement(By.cssSelector(".btn.w-10")).click();
		
		//Capture the toast message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//Wait for the rotating wait logo to disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on cart button to see the items added
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//Check items in the cart matches the item we added in the 'Add Item' section
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartItems.stream()
			.anyMatch(s->s.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//Click Check-out button
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		//Type 'ind' in Country textbox
		driver.findElement(By.cssSelector("div[class='user__address'] input")).sendKeys("ind");
		
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item")));
		
		options.stream()
				.filter(option->option.getText().equalsIgnoreCase(country))
				.findFirst()
				.ifPresent(option->option.click());
		
		//Click on 'Place Order' button
		//WebElement placeOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btnn.action__submit")));
		//placeOrderLink.click();
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		
		//Get order id
		List<WebElement> orders = driver.findElements(By.xpath("//tbody//tr[@class='ng-star-inserted']//td"));
		
		/*orders.stream()
				.map(order->order.getText())
				.forEach(order->System.out.println("Order no:"+order));		*/
		
		// In practical testing printing in console is not recommended, instead Assert
		AtomicInteger index = new AtomicInteger(1);
		orders.stream()
	      .map(WebElement::getText)
	      .flatMap(text -> Arrays.stream(text.split("\\|")))
	      .map(String::trim)
	      .filter(str -> !str.isEmpty())
	      .forEach(id -> 
	          System.out.println("Order No " + index.getAndIncrement() + ": " + id)
	      );
		
		//Assertion
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
