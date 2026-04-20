package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src\\test\\java\\featureFile",
        glue = {"stepDefinition"},
        plugin = {"pretty"
        		
        		, "html:target/cucumber-report.html"},
        monochrome = true,
        dryRun = false
)
public class TestRunnerIO extends AbstractTestNGCucumberTests {
}