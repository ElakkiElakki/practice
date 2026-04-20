package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\featureFile\\FT_TravellerDetails.feature", glue = {
		"stepDefinition" }, plugin = { "pretty",
				"html:target/cucumber-reports.html" }, monochrome = true, dryRun = false)
public class TestRunnerIO extends AbstractTestNGCucumberTests {
}