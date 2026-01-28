package frameworktest.pageObjects;

import java.util.List;
import frameworktest.AbstractComponents.AbstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> productList;
	
	@FindBy(css= ".ng-animating")
	WebElement rotatingLogo;
		
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".btn.w-10");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductsList()
	{
		waitForElementAppear(productsBy);
		return productList;
		
	}

	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductsList().stream()
				.filter(s->s.findElement(By.xpath(".//b")).getText()
				.equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementAppear(toastMessage);//Capture the toast message
		waitForElementDisappear(rotatingLogo);//Wait for the rotating wait logo to disappear
		
	}
	
	
}
