package frameworktest.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import frameworktest.AbstractComponents.AbstractComponent;

public class CartLookup extends AbstractComponent{

	WebDriver driver;
	
	public CartLookup(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy (css = ".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutBtn;
	
	public boolean verifyProductDisplay(String productName)
	{
		boolean match = productTitles.stream()
				.anyMatch(s->s.getText().equalsIgnoreCase(productName));
			//Assert.assertTrue(match);
		return match;
	}
	
	public CheckoutPage clickCheckout()
	{
		checkoutBtn.click();
		return new CheckoutPage(driver);
		
	}
	
}
