package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ConfigReader;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// 🔹 Locators
	By popupCloseBtn = By.xpath("//button[@aria-label='Dismiss sign-in info.']");
	By registerBtn = By.xpath("//a[@data-testid='header-sign-up-button']");
	By emailField = By.id("username");
	By continueBtn = By.xpath("//button[@type='submit']");
	By verifyBtn = By.xpath("//button[.//span[text()='Verify email']]");

	// 🔹 Actions

	public void handlePopupIfPresent() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(popupCloseBtn)).click();
		} catch (Exception e) {
			System.out.println("Popup not present");
		}
	}

	public void clickRegister() {
		wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
	}

	public void enterEmail() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(ConfigReader.get("email"));
	}

	public void clickContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	}

	public void waitForVerificationAndSubmit() throws InterruptedException {

		System.out.println("👉 Please complete CAPTCHA and enter OTP manually...");

		Thread.sleep(25000); // wait for user
		By verifyBtn = By.xpath("//button[.//span[text()='Verify email']]");

		wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();

		// driver.quit(); // as you asked, no separate method
	}
}