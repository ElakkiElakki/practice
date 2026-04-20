package stepDefinition;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utilities.DriverFactory;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver3());

    @Given("user opens booking homepage for registration")
    public void user_opens_homepage() {
        loginPage.openSite3();
    }

    @When("user dismisses popup if visible")
    public void user_dismisses_popup() {
        loginPage.handlePopupIfPresent3();
    }

    @And("user selects register option")
    public void user_selects_register() {
        loginPage.clickRegister();
    }

    @And("user provides email and proceeds")
    public void user_provides_email() {
        loginPage.enterEmail3();
        loginPage.clickContinue3();
    }

    @Then("user completes CAPTCHA and OTP verification")
    public void user_completes_verification() throws InterruptedException {
        loginPage.waitForOTP();
    }
}