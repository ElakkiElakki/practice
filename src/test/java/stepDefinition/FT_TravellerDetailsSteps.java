package stepDefinition;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.FT_TravellerDetailsPage;
import utilities.DriverFactory;

public class FT_TravellerDetailsSteps {

    WebDriver driver = DriverFactory.getDriver();
    FT_TravellerDetailsPage page = new FT_TravellerDetailsPage(driver);

    @When("user enters invalid adult details")
    public void invalid_adult() {
        page.enterInvalidAdult();
    }

    @When("user corrects adult details")
    public void correct_adult() {
        page.enterValidAdult();
    }

    @When("user enters invalid child details")
    public void invalid_child() {
        page.enterInvalidChild();
    }

    @When("user corrects child details")
    public void correct_child() {
        page.enterValidChild();
    }

    @When("user clicks done")
    public void click_done() {
        page.clickDone();
    }

    @Then("user should see traveller validation error")
    public void traveller_error() {
    	page.verifyTravellerError();
    }

    @When("user enters invalid email")
    public void invalid_email() {
        page.enterInvalidEmail();
    }

    @Then("user should see email error")
    public void email_error() {
        page.verifyEmailError();
    }

    @When("user corrects email and proceeds")
    public void correct_email() {
        page.enterValidContact();
        page.clickNext();
    }

    @When("user clicks next")
    public void click_next() {
        page.clickNext();
    }

   
}