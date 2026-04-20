package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class FH_FlightsPage {

    WebDriver driver;
    WebDriverWait wait;

    public FH_FlightsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ===== LOCATORS =====
    private By firstContinueBtn = By.cssSelector("button[data-testid='select-flight-button']");
    private By modalContainer = By.xpath("//div[@role='dialog']");
    private By modalContinueBtn = By.xpath("//div[@role='dialog']//button[@data-testid='lmn-ds-btn']");

    // ===== VERIFY FLIGHT PAGE =====
    public void verifyFlightPage() {
        System.out.println("⏳ Waiting for flight page...");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("transportDetail"),
                ExpectedConditions.presenceOfElementLocated(firstContinueBtn)
        ));

        System.out.println("✅ Flight page displayed");
    }

    // ===== MAIN FLOW =====
    public void selectFlightAndContinue() {

        System.out.println("⏳ Waiting for flight results...");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement firstBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(firstContinueBtn)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstBtn);

        wait.until(ExpectedConditions.elementToBeClickable(firstBtn));

        try {
            firstBtn.click();
        } catch (Exception e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(firstBtn).pause(Duration.ofMillis(300)).click().perform();
        }

        System.out.println("✅ Flight first Continue clicked");

        // ===== MODAL =====
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContainer));

        System.out.println("✅ Flight modal opened");

        WebElement modalBtn = wait.until(
                ExpectedConditions.elementToBeClickable(modalContinueBtn)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", modalBtn);

        try {
            modalBtn.click();
        } catch (Exception e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(modalBtn).pause(Duration.ofMillis(300)).click().perform();
        }

        System.out.println("✅ Flight modal Continue clicked");

        // ===== FINAL NAVIGATION =====
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("review"),
                ExpectedConditions.urlContains("checkout")
        ));

        if (driver.getCurrentUrl().contains("review")) {
            System.out.println("⏳ Waiting for redirect to checkout...");
            wait.until(ExpectedConditions.urlContains("checkout"));
        }

        System.out.println("✅ Final traveller page reached");
    }

    // ✅ SEPARATE METHOD (CORRECT)
    public void verifyTravellerPage() {

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout"),
                ExpectedConditions.urlContains("review")
        ));

        System.out.println("✅ Traveller details page displayed");
    }
}