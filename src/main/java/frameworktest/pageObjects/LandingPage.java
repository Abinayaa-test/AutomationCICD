package frameworktest.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworktest.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id = "userEmail")
	private WebElement username;
	
	@FindBy(id = "userPassword")
	private WebElement password;
	
	@FindBy(xpath = "//input[@id='login']")
	private WebElement loginBtn;
	
	@FindBy(css="[class*=flyInOut]")
	private WebElement loginErrorMessage;
	
	public ProductCatalogue login(String name, String passwd)
	{
		username.sendKeys(name);
		password.sendKeys(passwd);
		loginBtn.click();
		ProductCatalogue prodcat = new ProductCatalogue(driver);
		return prodcat;
	}

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementAppear(loginErrorMessage);
		return loginErrorMessage.getText();
	}
}
