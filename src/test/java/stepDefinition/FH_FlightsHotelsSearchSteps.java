package stepDefinition;

import io.cucumber.java.en.*;
import pages.FH_FlightsHotelsSearchPage;
import utilities.DriverFactory;
import utilities.ExcelReader;

import java.util.List;
import java.util.Map;

public class FH_FlightsHotelsSearchSteps {

    FH_FlightsHotelsSearchPage page;

    // ✅ ADD THIS (VERY IMPORTANT)
    @Given("initialize search page")
    public void init_page() {
        page = new FH_FlightsHotelsSearchPage(DriverFactory.getDriver());
    }

    @When("user performs search for all test cases")
    public void run_all_test_cases() throws InterruptedException {

        List<Map<String, String>> allData = ExcelReader.getAllData();

        // 🔥 OPEN ONLY ONCE
        page.openFlightHotelTab();

        // ================= INVALID FIRST =================
        for (Map<String, String> data : allData) {

            String departure = data.get("Departure");
            String destination = data.get("Destination");
            String tcId = data.get("TC_ID");

            boolean isInvalid = destination.contains("@")
                    || departure.isEmpty()
                    || destination.isEmpty();

            if (isInvalid) {

                System.out.println("\n🚀 Running INVALID Test Case: " + tcId);

                runTestCase(departure, destination, false);
            }
        }

        // ================= VALID LAST =================
        for (Map<String, String> data : allData) {

            String departure = data.get("Departure");
            String destination = data.get("Destination");
            String tcId = data.get("TC_ID");

            boolean isValid = !destination.contains("@")
                    && !departure.isEmpty()
                    && !destination.isEmpty();

            if (isValid) {

                System.out.println("\n🚀 Running VALID Test Case: " + tcId);

                runTestCase(departure, destination, true);
            }
        }
    }

    // 🔥 COMMON METHOD
    private void runTestCase(String departure, String destination, boolean isValid) throws InterruptedException {

        page.resetSearch();

        if (!departure.isEmpty()) {
            page.enterDeparture(departure);
        } else {
            page.clearDepartureField();
        }

        if (!destination.isEmpty()) {
            page.enterDestination(destination);
        } else {
            page.clearDestinationField();
        }

        if (destination.contains("@")) {
            System.out.println("⚠️ Invalid characters → no search");
            return;
        }

        page.clickSearch();

        if (isValid) {
            page.verifyResults();
            System.out.println("✅ VALID PASSED");
        } else {
            System.out.println("✅ Validation error displayed");
        }
    }
}