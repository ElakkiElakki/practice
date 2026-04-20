package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(ConfigReader.get2("email"));
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
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By signInBtn = By.xpath("//a[contains(@data-testid,'header-sign-in-button') or contains(text(),'Sign in')]");
    By emailField = By.id("username");
    By continueBtn = By.xpath("//button[@type='submit']");
    By verifyBtn = By.xpath("//button[.//span[text()='Verify email']]");
    By popup = By.xpath("//button[@aria-label='Dismiss sign-in info.']");

    public void openSite3() {
    	
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
    }

    public void handlePopupIfPresent3() {
        try {
            if (!driver.findElements(popup).isEmpty()) {
                driver.findElement(popup).click();
            }
        } catch (Exception ignored) {}
    }

    public void clickRegister() {

        handlePopupIfPresent3();

        WebElement signIn = wait.until(
                ExpectedConditions.elementToBeClickable(signInBtn)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signIn);

        System.out.println("✅ Clicked Sign In");
    }

    public void enterEmail3() {
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        );
        email.clear();
        email.sendKeys(ConfigReader.get3("email"));
    }

    public void clickContinue3() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void waitForOTP() throws InterruptedException {

        System.out.println("👉 Please complete CAPTCHA and enter OTP manually...");

        Thread.sleep(25000); // wait for user

        wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
    }
}