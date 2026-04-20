package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FH_HotelListingPage {

    WebDriver driver;
    WebDriverWait wait;

    public FH_HotelListingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ First hotel
    private By firstHotelLink = By.xpath("(//a[@data-testid='details'])[1]");

    // ✅ CORRECT locator (based on your HTML)
    private By continueBtn = By.xpath("//button[@data-testid='continue-button']");

    public void selectFirstHotel() {

        System.out.println("⏳ Waiting for hotel list...");

        wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[@data-testid='details']")
        ));

        WebElement hotelLink = wait.until(
            ExpectedConditions.elementToBeClickable(firstHotelLink)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});", hotelLink
        );

        try { Thread.sleep(1000); } catch (Exception ignored) {}

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", hotelLink
        );

        System.out.println("✅ First hotel clicked");

        // ✅ WAIT FOR NEW TAB
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String currentWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void verifyRoomPage() {

        System.out.println("⏳ Waiting for room page...");

        // ✅ WAIT FOR PAGE LOAD (VERY IMPORTANT)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("hotel"),
                ExpectedConditions.presenceOfElementLocated(By.tagName("body"))
        ));

        // ✅ WAIT FOR CONTINUE BUTTON (CORRECT ELEMENT)
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn));

        System.out.println("✅ Room page displayed");
    }
}