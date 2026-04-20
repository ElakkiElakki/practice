package stepDefinition;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utilities.DriverFactory;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

    @Given("user opens booking homepage for registration")
    public void user_opens_homepage() {
        loginPage.openSite();
    }

    @When("user dismisses popup if visible")
    public void user_dismisses_popup() {
        loginPage.handlePopupIfPresent();
    }

    @And("user selects register option")
    public void user_selects_register() {
        loginPage.clickRegister();
    }

    @And("user provides email and proceeds")
    public void user_provides_email() {
        loginPage.enterEmail();
        loginPage.clickContinue();
    }

    @Then("user completes CAPTCHA and OTP verification")
    public void user_completes_verification() throws InterruptedException {
        loginPage.waitForOTP();
    }
}