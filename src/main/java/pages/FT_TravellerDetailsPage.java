package pages;

import java.time.Duration;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import utilities.ConfigReader;
import utilities.ExcelReader;

public class FT_TravellerDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    public FT_TravellerDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // 🔹 LOCATORS
    By adultAddBtn = By.xpath("(//button[contains(@aria-label,'Add this traveler')])[1]");

    // 🔥 FIXED (handles both Add + Edit)
    By childBtn = By.xpath("(//button[contains(@aria-label,'Add this traveler') or contains(@aria-label,'Edit this traveler')])[2]");

    By correctAdultBtn = By.xpath("(//button[contains(@aria-label,'Edit this traveler')])[1]");
    By correctChildBtn = By.xpath("(//button[contains(@aria-label,'Edit this traveler')])[2]");

    By firstName = By.xpath("//input[contains(@name,'firstName')]");
    By lastName = By.xpath("//input[contains(@name,'lastName')]");
    By gender = By.xpath("//select[contains(@name,'gender')]");
    By doneBtn = By.xpath("//button[.//span[text()='Done']]");

    By email = By.name("booker.email");
    By phone = By.name("number");
    By nextBtn = By.xpath("//button[.//span[text()='Next']]");

    By errorMsg = By.xpath("//*[contains(text(),'Enter') or contains(text(),'valid') or contains(@class,'error')]");

    String path = "src/test/resources/TravellerData.xlsx";

    // ================= ADULT =================

    public void enterInvalidAdult() {
        try {
            ExcelReader.setExcelFile2(path, "Adult");

            click(adultAddBtn);

            clear(firstName);
            clear(lastName);

            type(firstName, ExcelReader.getCellData2(1, 0)); // invalid
            type(lastName, ""); // 🔥 MUST be empty

            selectGender(ExcelReader.getCellData2(1, 2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterValidAdult() {
        try {
            ExcelReader.setExcelFile2(path, "Adult");

            click(correctAdultBtn);

            clear(firstName);
            clear(lastName);

            type(firstName, ExcelReader.getCellData2(3, 0));
            type(lastName, ExcelReader.getCellData2(3, 1));

            selectGender(ExcelReader.getCellData2(3, 2));

            click(doneBtn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= CHILD =================

    public void enterInvalidChild() {
        try {
            ExcelReader.setExcelFile2(path, "Child");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(doneBtn));

            click(childBtn); // 🔥 FIXED dynamic button

            By childFirst = By.name("passengers.1.firstName");
            By childLast = By.name("passengers.1.lastName");
            By childGender = By.name("passengers.1.gender");

            clear(childFirst);
            clear(childLast);

            String fname = ExcelReader.getCellData2(1, 0);

            type(childFirst, fname + "11"); // invalid
            type(childLast, ""); // invalid

            new Select(wait.until(ExpectedConditions.elementToBeClickable(childGender)))
                    .selectByVisibleText(ExcelReader.getCellData2(1, 2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterValidChild() {
        try {
            ExcelReader.setExcelFile2(path, "Child");

            click(correctChildBtn);

            By childFirst = By.name("passengers.1.firstName");
            By childLast = By.name("passengers.1.lastName");
            By childGender = By.name("passengers.1.gender");

            By childMonth = By.name("passengers.1.birthDate__month");
            By childDay = By.name("passengers.1.birthDate__day");
            By childYear = By.name("passengers.1.birthDate__year");

            clear(childFirst);
            clear(childLast);

            type(childFirst, ExcelReader.getCellData2(1, 0));
            type(childLast, ExcelReader.getCellData2(1, 1));

            new Select(wait.until(ExpectedConditions.elementToBeClickable(childGender)))
                    .selectByVisibleText(ExcelReader.getCellData2(1, 2));

            new Select(driver.findElement(childMonth))
                    .selectByVisibleText(ExcelReader.getCellData2(1, 3));

            type(childDay, ExcelReader.getCellData2(1, 4));
            type(childYear, ExcelReader.getCellData2(1, 5));

            click(doneBtn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= EMAIL =================

    public void enterInvalidEmail() {
        clear(email);
        type(email, "abc@.com");
    }

    public void verifyEmailError() {
        Assert.assertTrue(driver.findElements(errorMsg).size() > 0, "No email error shown");
    }

    public void enterValidContact() {
        clear(email);
        type(email, ConfigReader.get2("email"));

        clear(phone);
        type(phone, "9876543210");
    }

    public void clickNext() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }


    // ================= COMMON =================

    public void clickDone() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(doneBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void verifyTravellerError() {
        Assert.assertTrue(driver.findElements(errorMsg).size() > 0, "No traveller error shown");
    }

    // ================= HELPERS =================

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value);
    }

    public void clear(By locator) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
    }

    public void selectGender(String value) {
        new Select(wait.until(ExpectedConditions.elementToBeClickable(gender)))
                .selectByVisibleText(value);
    }
}