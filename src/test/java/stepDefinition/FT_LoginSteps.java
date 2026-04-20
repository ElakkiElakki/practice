package stepDefinition;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utilities.DriverFactory;

public class FT_LoginSteps {

	LoginPage loginPage;

	@Given("user opens booking homepage for registration")
	public void user_opens_homepage() {
		loginPage = new LoginPage(DriverFactory.getDriver());

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
		loginPage.waitForVerificationAndSubmit();
	}
}