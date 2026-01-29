package frameworktest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworktest.TestComponents.BaseTest;
import frameworktest.pageObjects.CartLookup;
import frameworktest.pageObjects.CheckoutPage;
import frameworktest.pageObjects.FinalOrderPage;
import frameworktest.pageObjects.OrdersLookup;
import frameworktest.pageObjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "ADIDAS ORIGINAL";
	//new comments to test CICD
	@Test(dataProvider = "MyLoginData")
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		
		String productName1 = "iphone 13 pro";
				
		ProductCatalogue prodcat = landingPage.login(input.get("username"),input.get("pass"));
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		        wait.until(ExpectedConditions.alertIsPresent());
		        driver.switchTo().alert().accept();
		        System.out.println("Alert accepted.");
		    } catch (Exception e) {
		        //System.out.println("No alert to accept.");
		    }
		prodcat.addProductToCart(productName);
		//prodcat.addProductToCart(productName1);
		CartLookup lookup = prodcat.goToCart();
			
		boolean match;
		match = lookup.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		//match = lookup.verifyProductDisplay(productName1);
		//Assert.assertTrue(match);
		CheckoutPage checkpage = lookup.clickCheckout();
		FinalOrderPage orderpage = checkpage.placeOrder("india");
		
		String confirmMessage = orderpage.getConfirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue prodcat = landingPage.login("rajini123@gmail.com", "Rajini@123");
		OrdersLookup ordlkup = prodcat.goToOrders();
		boolean orderMatch = ordlkup.verifyOrderDisplay(productName);
		Assert.assertTrue(orderMatch);
	}
	/*
	@DataProvider(name ="MyLoginData")
	public Object[][] getData() throws IOException
	{
		HashMap<Object,Object> map = new HashMap();
		map.put("username", "rajini123@gmail.com");
		map.put("pass", "Rajini@123");
		HashMap<Object,Object> map1 = new HashMap();
		map1.put("username", "anshika@gmail.com");
		map1.put("pass", "Iamking@000");
		//return null;
		
		/*List<Map<String, String>> data = DataReader.getJsonDataToMap("data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};*--/
		
	} */
	
@DataProvider(name ="MyLoginData")
	public Object[][]  getData() throws IOException
	{
		HashMap<Object,Object> map = new HashMap();
		map.put("username", "rajini123@gmail.com");
		map.put("pass", "Rajini@123");
		HashMap<Object,Object> map1 = new HashMap();
		map1.put("username", "kamal234@gmail.com");
		map1.put("pass", "Kamal@123");
		return new Object[][] {{map},{map1}};
		
	}
}
