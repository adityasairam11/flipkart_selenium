package runner;


import java.io.File;

import org.testng.annotations.AfterClass;

//import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(features="features"
,glue= {"stepDefinition"}
,plugin= {"html:target/cucumber-html-report"}
//,plugin = {"com.cucumber.listener.ExtentCucumberFormatter:test-output/Extentreport.html"}
,dryRun = false 
,monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests  {
	
	/*@AfterClass
	public static void teardown() {
	Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
	Reporter.setSystemInfo("user", System.getProperty("user.name"));
	Reporter.setSystemInfo("os", "Mac OSX");
	Reporter.setTestRunnerOutput("Sample test runner output message");
	}*/
	
	
}
