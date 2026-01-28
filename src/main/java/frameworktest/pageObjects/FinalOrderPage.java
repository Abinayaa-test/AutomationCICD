package frameworktest.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworktest.AbstractComponents.AbstractComponent;

public class FinalOrderPage extends AbstractComponent{

WebDriver driver;
	
	public FinalOrderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tbody//tr[@class='ng-star-inserted']//td")
	List<WebElement> orderlist;
	
	@FindBy(css = ".hero-primary")
	WebElement confirmElement;
	
	public void fetchOrderid()
	{
		orderlist.stream()
		.map(order->order.getText())
		.forEach(order->System.out.println("Order no:"+order));	
	}
	
	public String getConfirmation()
	{
		fetchOrderid();
		waitForWebElementAppear(confirmElement);
		String confirmMessage = confirmElement.getText();
		return confirmMessage;
	} 
}
