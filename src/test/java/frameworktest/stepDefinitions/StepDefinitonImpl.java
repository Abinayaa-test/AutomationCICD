package frameworktest.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import frameworktest.TestComponents.BaseTest;
import frameworktest.pageObjects.CartLookup;
import frameworktest.pageObjects.CheckoutPage;
import frameworktest.pageObjects.FinalOrderPage;
import frameworktest.pageObjects.LandingPage;
import frameworktest.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitonImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue prodcat;
	public CartLookup lookup;
	public CheckoutPage checkpage;
	public FinalOrderPage orderpage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		prodcat = landingPage.login(username,password);
	}
	
	@When("I add product {string} to cart")
	public void adding_products_to_cart(String productName)
	{
		prodcat.addProductToCart(productName);
	}
	
	@And("I checkout {string} and submit the order")
	public void checkout_and_submit_order(String productName)
	{
		lookup = prodcat.goToCart();
		
		boolean match;
		match = lookup.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkpage = lookup.clickCheckout();
		orderpage = checkpage.placeOrder("india");
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void confirmation_message_display(String expectedMessage)
	{
		String confirmMessage = orderpage.getConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(expectedMessage));
		driver.close();
	}
			
	@Then ("{string} error message is displayed")
	public void error_message_is_displayed(String errorMessage)
	{
		Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
		driver.close();
	}
}
