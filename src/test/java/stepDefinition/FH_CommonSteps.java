package stepDefinition;

import io.cucumber.java.en.*;
import pages.FH_FlightsHotelsSearchPage;
import utilities.DriverFactory;
import utilities.ExcelReader;

public class FH_CommonSteps {

    FH_FlightsHotelsSearchPage searchPage;

    @Given("user is on booking homepage after login")
    public void user_on_homepage_after_login() {
        searchPage = new FH_FlightsHotelsSearchPage(DriverFactory.getDriver3());
    }

    @When("user opens flight + hotel tab")
    public void open_tab() {
        searchPage.openFlightHotelTab();
    }

    @When("user enters departure location from excel row {int}")
    public void enter_departure(int row) {
        String dep = ExcelReader.getAllData3().get(row - 1).get("Departure");
        searchPage.enterDeparture(dep);
    }

    @And("user enters destination location from excel row {int}")
    public void enter_destination(int row) {
        String dest = ExcelReader.getAllData3().get(row - 1).get("Destination");
        searchPage.enterDestination(dest);
    }

    @And("user clicks search button")
    public void click_search() {
        searchPage.clickSearch();
    }

    @Then("user should see hotel results")
    public void verify_results() {
        searchPage.verifyResults();
    }
}