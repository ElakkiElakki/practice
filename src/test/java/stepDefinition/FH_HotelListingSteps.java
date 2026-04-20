package stepDefinition;

import io.cucumber.java.en.*;
import pages.FH_HotelListingPage;
import utilities.DriverFactory;

public class FH_HotelListingSteps {

    FH_HotelListingPage hotelPage;

    // ✅ ONLY INITIALIZE PAGE
    @Given("user is on hotel listing page")
    public void user_on_hotel_listing_page() {
        hotelPage = new FH_HotelListingPage(DriverFactory.getDriver());
    }

    // ✅ ONLY LISTING ACTION
    @When("user selects first hotel")
    public void user_selects_first_hotel() {
        hotelPage.selectFirstHotel();
    }

    // ✅ MOVE THIS HERE (keep only one place in project)
    @Then("user should see room details page")
    public void verify_room_page() {
        hotelPage.verifyRoomPage();
    }
}