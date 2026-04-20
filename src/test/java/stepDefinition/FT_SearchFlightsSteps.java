package stepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.FT_SearchFlightsPage;
import utilities.DriverFactory;

public class FT_SearchFlightsSteps {

	WebDriver driver = DriverFactory.getDriver();
	FT_SearchFlightsPage page = new FT_SearchFlightsPage(driver);

	@Given("user is on booking homepage")
	public void homepage() {
	}

	@When("user runs complete flight validation flow")
	public void fullFlow() throws InterruptedException {

		page.openFlights();
		page.selectOneWay();

		// ❌ Missing departure
		page.clearDeparture();
		page.enterDestination("Paris");
		page.clickSearch();
		page.verifyMissingDeparture();

		Thread.sleep(2000);

		// ❌ Missing destination
		page.enterDeparture("Bangalore");
		page.clearDestination();
		page.clickSearch();
		page.verifyMissingDestination();

		Thread.sleep(2000);

		// 🟡 ADD THIS NEW TEST
		page.validateTravellers();

		Thread.sleep(2000);
		// ✅ Correct flow
		page.enterDestination("Paris");
		page.clickSearch();
		page.verifyResults();
	}
}