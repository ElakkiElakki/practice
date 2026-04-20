package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FH_HotelDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    public FH_HotelDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ===== LOCATORS =====
    private By firstContinueBtn = By.cssSelector("button[data-testid='continue-button']");
    private By modalContainer = By.xpath("//div[@role='dialog']");
    private By modalContinueBtn = By.xpath(
            "//div[@role='dialog']//div[contains(@class,'BottomContinuePanel')]//button"
    );

    // ===== VERIFY PAGE =====
    public void verifyRoomPage() {
        System.out.println("⏳ Waiting for hotel details page...");
        wait.until(ExpectedConditions.urlContains("hotel"));
        System.out.println("✅ Hotel details page displayed");
    }
    public void verifyFlightPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("transportDetail&destination"));

        System.out.println("✅ Flight selection page displayed");
    }

    // ===== STEP 1: CLICK FIRST CONTINUE =====
    public void selectRoomAndContinue() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println("⏳ Clicking first Continue...");

        WebElement firstBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstContinueBtn)
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstBtn);
        js.executeScript("arguments[0].click();", firstBtn);

        System.out.println("✅ First Continue clicked");

        // 🔥 WAIT MODAL FULLY
        waitForRoomModal();

        // 🔥 CLICK SECOND BUTTON
        clickFinalContinue();
    }

    // ===== WAIT MODAL =====
    public void waitForRoomModal() {

        System.out.println("⏳ Waiting for modal...");

        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContainer));

        // 🔥 IMPORTANT: allow animation to complete
        try { Thread.sleep(1500); } catch (Exception ignored) {}

        System.out.println("✅ Modal fully loaded");
    }

    // ===== STEP 2: FINAL CONTINUE =====
    public void clickFinalContinue() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println("⏳ Waiting for modal Continue button...");

        WebElement btn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(modalContinueBtn)
        );

        wait.until(ExpectedConditions.elementToBeClickable(btn));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", btn);
        }

        System.out.println("✅ REAL modal Continue clicked");

        // 🔥 STRONG VALIDATION
        wait.until(ExpectedConditions.and(
        	    ExpectedConditions.urlContains("transportDetail"),
        	    ExpectedConditions.visibilityOfElementLocated(
        	        By.cssSelector("button[data-testid='select-flight-button']")
        	    )
        	));

        System.out.println("✅ Navigation confirmed");
    }
}