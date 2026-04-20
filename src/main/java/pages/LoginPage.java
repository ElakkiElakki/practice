package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import utilities.ConfigReader;

public class LoginPage {

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

    public void openSite() {
    	
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
    }

    public void handlePopupIfPresent() {
        try {
            if (!driver.findElements(popup).isEmpty()) {
                driver.findElement(popup).click();
            }
        } catch (Exception ignored) {}
    }

    public void clickRegister() {

        handlePopupIfPresent();

        WebElement signIn = wait.until(
                ExpectedConditions.elementToBeClickable(signInBtn)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signIn);

        System.out.println("✅ Clicked Sign In");
    }

    public void enterEmail() {
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        );
        email.clear();
        email.sendKeys(ConfigReader.get("email"));
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void waitForOTP() throws InterruptedException {

        System.out.println("👉 Please complete CAPTCHA and enter OTP manually...");

        Thread.sleep(25000); // wait for user

        wait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
    }
}