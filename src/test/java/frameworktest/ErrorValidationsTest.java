package frameworktest;
import frameworktest.TestComponents.Retry;

//import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

//import com.sun.net.httpserver.Authenticator.Retry;

import frameworktest.TestComponents.BaseTest;
import frameworktest.pageObjects.CartLookup;
import frameworktest.pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException
	{
				
		landingPage.login("rajini4123@gmail.com", "Rajini123");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		
		
	}

	@Test
	public void productErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";
				
		ProductCatalogue prodcat = landingPage.login("rajini123@gmail.com", "Rajini@123");
		prodcat.addProductToCart(productName);
		CartLookup lookup = prodcat.goToCart();
			
		boolean match;
		match = lookup.verifyProductDisplay("ZARA COAT 32");
		Assert.assertFalse(match);
		}
}
