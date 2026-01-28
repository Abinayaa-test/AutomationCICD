package frameworktest.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworktest.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div[class='user__address'] input")
	WebElement enterCountry;
	
	@FindBy(css = ".ta-item")
	List<WebElement> retrievedCountryList;
	
	@FindBy(css= ".btnn.action__submit")
	WebElement placeOrderBtn;
	
	By countryList = By.cssSelector(".ta-item");
		
	public void getCountryList(String countryName)
	{
	enterCountry.sendKeys("ind");
	waitForElementAppear(countryList);
	//String country = "india";
	retrievedCountryList.stream()
						.filter(option->option.getText().equalsIgnoreCase(countryName ))
						.findFirst()
						.ifPresent(option->option.click());
	}
	
	public FinalOrderPage placeOrder(String country)
	{
		getCountryList(country);
		waitForElementToBeClickable(placeOrderBtn);
		scrollIntoView(placeOrderBtn);
		placeOrderBtn.click();
		//WebElement el = waitForElementToBeClickable(placeOrderBtn);
		//((JavascriptExecutor) driver).executeScript(
		//    "arguments[0].scrollIntoView({block:'center', inline:'center'});", el
		//);
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
		FinalOrderPage ordersucess = new FinalOrderPage(driver);
		return ordersucess;
	}
}
