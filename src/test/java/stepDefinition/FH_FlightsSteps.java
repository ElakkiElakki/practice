package stepDefinition;

import io.cucumber.java.en.*;
import pages.FH_FlightsPage;
import utilities.DriverFactory;

public class FH_FlightsSteps {

	FH_FlightsPage flightsPage =
	        new FH_FlightsPage(DriverFactory.getDriver3());

    // ✅ Initialize safely
    @Given("initialize flight page")
    public void init_flight_page() {
        flightsPage = new FH_FlightsPage(DriverFactory.getDriver3());
    }

    @Then("user should see flight selection page")
    public void user_should_see_flight_selection_page() {
        flightsPage.verifyFlightPage();
    }

    @When("user selects flight and clicks continue")
    public void user_selects_flight_and_clicks_continue() {
        flightsPage.selectFlightAndContinue();
    }

    @Then("traveller details page should be displayed")
    public void traveller_details_page_should_be_displayed() {
        flightsPage.verifyTravellerPage();
    }
}