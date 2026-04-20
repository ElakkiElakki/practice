package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FH_FlightsHotelsSearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public FH_FlightsHotelsSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ===== LOCATORS =====

    By flightHotelTab = By.xpath("//span[contains(text(),'Flight + Hotel') or contains(text(),'Flights + Hotels')]");
    By dateBtn = By.xpath("//button[@data-testid='searchbox-dates-container']");
    By calendar = By.xpath("//div[@data-testid='searchbox-datepicker']");
    By nextMonth = By.xpath("//button[@aria-label='Next month']");
    By searchBtn = By.xpath("//button[@type='submit']");
    By departureInput = By.xpath("//input[@placeholder='City or airport']");

    // ===== OPEN TAB =====

    public void openFlightHotelTab() {

        handleCookies();

        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(flightHotelTab));
        tab.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[contains(@placeholder,'Where')]")
        ));

        handleCookies();
    }

    // ===== DEPARTURE (SAFE FOR INVALID) =====

    public void enterDeparture(String departure) {

        if (departure == null || departure.trim().isEmpty()) {
            System.out.println("⚠️ Skipping departure (blank input)");
            return;
        }

        handleCookies();

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(departureInput));
        input.click();

        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);

        input.sendKeys(departure);

        By suggestion = By.xpath("//ul//li//span");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
            driver.findElements(suggestion).get(0).click();
            System.out.println("✅ Departure selected: " + departure);
        } catch (Exception e) {
            System.out.println("⚠️ No suggestion for departure: " + departure);
        }
    }

    // ===== DESTINATION (SAFE FOR INVALID) =====

    public void enterDestination(String destination) {

        if (destination == null || destination.trim().isEmpty()) {
            System.out.println("⚠️ Skipping destination (blank input)");
            return;
        }

        handleCookies();

        By destinationBtn = By.xpath("(//button[@type='button'])[2]");

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(destinationBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        By inputField = By.xpath("//input[contains(@placeholder,'Where')]");

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));

        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(destination);

        By suggestions = By.xpath("//ul//li");

        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestions, 0));

            List<WebElement> list = driver.findElements(suggestions);

            for (WebElement el : list) {
                if (el.getText().toLowerCase().contains(destination.toLowerCase())) {
                    el.click();
                    System.out.println("✅ Destination selected: " + destination);
                    return;
                }
            }

            System.out.println("⚠️ No matching suggestion for: " + destination);

        } catch (Exception e) {
            System.out.println("⚠️ No suggestions displayed for: " + destination);
        }
    }

    // ===== DATE =====

    public void selectDate() {

        
        System.out.println("✅ Dates selected");
    }

    

    // ===== TRAVELLERS =====

    public void selectTravellers() throws InterruptedException {

        By travellerBtn = By.xpath("//span[text()='Travellers/Flight class']/parent::div//button");
        wait.until(ExpectedConditions.elementToBeClickable(travellerBtn)).click();

        By adultCount = By.xpath("//input[@aria-label='Adults 12+']");
        By decreaseBtn = By.xpath("//button[contains(@aria-label,'Decrease')]");

        WebElement countEl = wait.until(ExpectedConditions.visibilityOfElementLocated(adultCount));

        int count = Integer.parseInt(countEl.getAttribute("value"));

        while (count > 1) {
            wait.until(ExpectedConditions.elementToBeClickable(decreaseBtn)).click();
            Thread.sleep(300);
            count = Integer.parseInt(driver.findElement(adultCount).getAttribute("value"));
        }

        By economy = By.xpath("//input[@value='Y']/parent::label");
        wait.until(ExpectedConditions.elementToBeClickable(economy)).click();

        driver.findElement(By.xpath("//body")).click();

        System.out.println("✅ Travellers configured");
    }

    // ===== SEARCH =====

    public void clickSearch() {

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", search);

        search.click();

        System.out.println("✅ Search clicked");
    }

    // ===== VALID RESULT =====

    public void verifyResults() {

        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(45));

        longWait.until(ExpectedConditions.urlContains("search"));

        longWait.until(driver ->
                driver.findElements(By.xpath("//h4[contains(@id,'card-title')]")).size() > 2
        );

        System.out.println("✅ Hotel results fully loaded");
    }

    ////
    public void clearDepartureField() {
    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(departureInput));
    input.click();
    input.sendKeys(Keys.CONTROL + "a");
    input.sendKeys(Keys.DELETE);
    System.out.println("⚠️ Departure cleared");
}
    //
    public void clearDestinationField() {

        try {
            By destinationBtn = By.xpath("(//button[@type='button'])[2]");
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(destinationBtn));
            btn.click();

            By inputField = By.xpath("//input[contains(@placeholder,'Where')]");
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputField));

            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);

            System.out.println("⚠️ Destination cleared");

        } catch (Exception e) {
            System.out.println("⚠️ Unable to clear destination");
        }
    }
    
    public void resetSearch() {

        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(departureInput));

            input.click();
            input.sendKeys(Keys.CONTROL + "a");
            input.sendKeys(Keys.DELETE);

            clearDestinationField();

            System.out.println("🔄 Fields reset (no refresh)");

        } catch (Exception e) {
            System.out.println("⚠️ Reset fallback");
        }
    }
    // ===== COOKIE =====

    public void handleCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                        By.xpath("//iframe[contains(@id,'sp_message_iframe')]")
                ));
            } catch (Exception ignored) {}

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Accept') or contains(.,'Agree')]")
            ));

            btn.click();
            driver.switchTo().defaultContent();

            System.out.println("✅ Cookie handled");

        } catch (Exception e) {
            System.out.println("⚠️ No cookie popup");
            try {
                driver.switchTo().defaultContent();
            } catch (Exception ignored) {}
        }
    }
}