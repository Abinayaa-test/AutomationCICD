package frameworktest.TestComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import frameworktest.pageObjects.LandingPage;
import frameworktest.utils.Property;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException
	{
		//properties class
		//Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream("/Users/kathirvel/Projects/SeleniumFrameworkDesign/src/main/java/frameworktest/resources/GlobalData.properties");
		//prop.load(fis);
		//String browserName = prop.getProperty("browser");
		
		
		//String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : Property.get("browser");
		
		String browserName = Property.get("browser", "chrome");
		boolean headless = Boolean.parseBoolean(
			    System.getProperty("headless", "false")
			);
		
		if (browserName == null) {
	        throw new RuntimeException("Browser value is not provided");
	    }
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			/*WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			*/
			ChromeOptions chromeOptions = new ChromeOptions();

	        if (headless) {
	            chromeOptions.addArguments(
	                "--headless=new",
	                "--window-size=1920,1080",
	                "--disable-gpu",
	                "--no-sandbox"
	            );
	        }

	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver(chromeOptions);
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("safari"))
		{
			//WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		 else 
		{
		        throw new RuntimeException("Unsupported browser: " + browserName);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir")+"/reports/" + testCaseName + ".png"); 
		FileUtils.copyFile(sourceFile, destinationFile); 
		return System.getProperty("user.dir")+"/reports/" + testCaseName + ".png";
	}
}
