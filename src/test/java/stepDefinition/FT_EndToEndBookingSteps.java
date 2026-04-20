package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import pages.FT_EndToEndBookingPage;
import utilities.DriverFactory;

public class FT_EndToEndBookingSteps {

	WebDriver driver = DriverFactory.getDriver();
	FT_EndToEndBookingPage flightPage = new FT_EndToEndBookingPage(driver);

	@When("user chooses one way trip option")
	public void user_chooses_one_way() {
		flightPage.selectOneWay();
	}

	@When("user selects multiple departure locations")
	public void user_selects_multiple_departure() {
		flightPage.handleMultipleDeparture();
	}

	@When("user selects multiple destination locations")
	public void user_selects_multiple_destination() {
		flightPage.handleMultipleDestination();
	}

	@When("user picks travel date")
	public void user_picks_travel_date() {
		flightPage.selectTravelDate();
	}

	@When("user configures travellers and children ages")
	public void user_configures_travellers() {
		flightPage.handleTravellers();
	}

	@When("user triggers flight search")
	public void user_triggers_flight_search() {
		flightPage.clickSearch();
	}

	@Then("user views available flight results")
	public void user_views_available_flights() {
		flightPage.verifyResults();
	}

	@Then("user selects first flight and proceeds")
	public void user_selects_first_flight() {
		flightPage.openFirstFlightDetails();
		flightPage.clickContinueFromDetails();
		flightPage.selectTicketAndContinue();
	}

	@Then("user lands on traveller details section")
	public void user_lands_on_traveller_section() {
		System.out.println("Reached traveller details page");
	}

	@When("user fills traveller personal information")
	public void user_fills_traveller_details() {
		flightPage.fillTravellerDetails();
	}

	@When("user fills contact information and proceeds")
	public void user_fills_contact_and_proceeds() {
		flightPage.fillContactDetails();
	}

	@When("user selects ticket type and proceeds")
	public void user_selects_ticket_type_and_proceeds() {
		flightPage.selectTicketType();
		flightPage.clickNextAfterTicket();
	}

	@When("user skips seat selection")
	public void user_skips_seat_selection() {
		flightPage.skipSeatSelection();
	}

	@Then("user reaches payment section")
	public void user_reaches_payment() {
		flightPage.verifyPaymentPage();
	}

	@Then("user reaches payment section and stops")
	public void stopAtPayment() {
	    flightPage.stopAtPaymentUI();
	}
}