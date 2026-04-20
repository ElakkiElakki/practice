package stepDefinition;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import pages.FH_TravellerPage;
import utilities.DriverFactory;

import java.util.List;
import java.util.Map;

public class FH_TravellerSteps {

    FH_TravellerPage travellerPage =
            new FH_TravellerPage(DriverFactory.getDriver3());

    @Given("initialize traveller page")
    public void init_traveller_page() {
        travellerPage = new FH_TravellerPage(DriverFactory.getDriver3());
    }

    @When("user enters complete traveller details")
    public void user_enters_traveller_details(DataTable table) throws InterruptedException {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {

            String type = row.get("type");

            if (type.equalsIgnoreCase("contact")) {

                travellerPage.fillContactDetails(
                        row.get("firstName"),
                        row.get("lastName"),
                        row.get("email"),
                        row.get("phone"),
                        row.get("address"),
                        row.get("houseNo"),
                        row.get("pincode"),
                        row.get("city")
                );
            }

            else if (type.equalsIgnoreCase("traveller1")) {

                travellerPage.fillTraveller(
                        1,
                        row.get("gender"),
                        row.get("firstName"),
                        row.get("lastName"),
                        row.get("day"),
                        row.get("month"),
                        row.get("year"),
                        row.get("nationality"),
                        row.get("docNumber"),
                        row.get("issueCountry"),
                        row.get("issueDay"),
                        row.get("issueMonth"),
                        row.get("issueYear"),
                        row.get("expDay"),
                        row.get("expMonth"),
                        row.get("expYear")
                );
            }

            else if (type.equalsIgnoreCase("traveller2")) {

                travellerPage.fillTraveller(
                        2,
                        row.get("gender"),
                        row.get("firstName"),
                        row.get("lastName"),
                        row.get("day"),
                        row.get("month"),
                        row.get("year"),
                        row.get("nationality"),
                        row.get("docNumber"),
                        row.get("issueCountry"),
                        row.get("issueDay"),
                        row.get("issueMonth"),
                        row.get("issueYear"),
                        row.get("expDay"),
                        row.get("expMonth"),
                        row.get("expYear")
                );
            }
        }
    }

    @And("user accepts policy and clicks next")
    public void user_accepts_policy_and_clicks_next() {
        travellerPage.acceptPolicyAndProceed();
    }

    @Then("payment page should be displayed")
    public void payment_page_should_be_displayed() {
        travellerPage.verifyPaymentSection();
    }
}