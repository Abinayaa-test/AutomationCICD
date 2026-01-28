package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/cucumber", 
		glue="frameworktest.stepDefinitions",
		monochrome=true,
		plugin = {"pretty","html:target/cucumber.html"},
		tags = "@Regression"
		)
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
