package stepDefinition;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.FT_FlightOptionsPage;
import utilities.*;

public class FT_FlightOptionsSteps {

	WebDriver driver = DriverFactory.getDriver();
	FT_FlightOptionsPage page = new FT_FlightOptionsPage(driver);

	@Then("User validates cheapest sorting")
	public void validate_sorting() throws InterruptedException {
		page.verifyCheapestSorting();
	}

	@Then("User validates flight details")
	public void validate_flight_details() {
		page.verifyFlightDetails();
	}

	@Then("User validates fare options")
	public void validate_fare_options() {
		page.verifyFareOptions();
	}

	@Then("User validates airline filter")
	public void validate_airline_filter() {
		page.validateAirlineFilter();
	}

	@Then("User validates time filter")
	public void validate_time_filter() {
		page.validateTimeFilter();
	}
}