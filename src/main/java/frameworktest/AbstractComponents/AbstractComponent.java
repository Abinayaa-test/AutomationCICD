package frameworktest.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworktest.pageObjects.CartLookup;
import frameworktest.pageObjects.OrdersLookup;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
	}

	@FindBy(css= "[routerlink*='cart']")
	private WebElement cartHeader;
	
	@FindBy(css= "[routerlink*='myorders']")
	private WebElement ordersHeader;
	
	@FindBy(css= ".ng-animating")
	WebElement rotatingLogo;
	
	
	public void waitForElementAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitForElementDisappear(WebElement findElem)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findElem));
	}
	
	public WebElement waitForElementToBeClickable(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void scrollIntoView(WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public CartLookup goToCart()
	{
		waitForWebElementAppear(cartHeader); 
		waitForElementDisappear(rotatingLogo); 
		cartHeader.click();//Click on cart button to see the items added
		CartLookup lookup = new CartLookup(driver);
		return lookup;
	}
	
	public OrdersLookup goToOrders()
	{
		ordersHeader.click();
		OrdersLookup ordlookup = new OrdersLookup(driver);
		return ordlookup;
	}
}
