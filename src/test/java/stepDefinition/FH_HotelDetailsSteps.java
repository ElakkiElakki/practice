package stepDefinition;

import io.cucumber.java.en.*;
import pages.FH_HotelDetailsPage;
import utilities.DriverFactory;

public class FH_HotelDetailsSteps {
	FH_HotelDetailsPage detailsPage =
	        new FH_HotelDetailsPage(DriverFactory.getDriver());

    // ✅ Initialize safely
    @Given("initialize hotel details page")
    public void init_details_page() {
        detailsPage = new FH_HotelDetailsPage(DriverFactory.getDriver());
    }

    @When("user selects room and clicks continue")
    public void user_selects_room_and_continue() {
        detailsPage.selectRoomAndContinue();
    }

    @Then("flight selection page should be displayed")
    public void flight_selection_page_should_be_displayed() {
        detailsPage.verifyFlightPage();
    }
}