package frameworktest.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import frameworktest.AbstractComponents.AbstractComponent;

public class OrdersLookup extends AbstractComponent{

	WebDriver driver;
	
	public OrdersLookup(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy (css = ".table.table-hover tbody tr td:nth-of-type(2)")
	List<WebElement> orderTitles;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutBtn;
	
	public boolean verifyOrderDisplay(String productName)
	{
		boolean match = orderTitles.stream()
				.anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}

}
