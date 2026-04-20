package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class FH_TravellerPage {

    WebDriver driver;
    WebDriverWait wait;

    public FH_TravellerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    // ================= COMMON =================
    public void handleAutoFillPopup() {
        try {
            new Actions(driver).sendKeys(Keys.ESCAPE).perform();
            js().executeScript("document.body.click();");
        } catch (Exception e) {}
    }

    // ================= INPUT =================
    public void enterReactText(By locator, String value) {

        if (value == null || value.trim().isEmpty()) return;

        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        js().executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        js().executeScript("arguments[0].click();", el);

        // Clear + Type
        js().executeScript("arguments[0].value='';", el);
        el.sendKeys(value);

        // React triggers
        js().executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles:true}));", el);
        js().executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", el);
        js().executeScript("arguments[0].dispatchEvent(new Event('blur', {bubbles:true}));", el);
    }
    public boolean isPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // ================= DROPDOWN (FINAL FIX) =================
    public void selectDropdown(By buttonLocator, String value) {

        if (value == null || value.trim().isEmpty()) return;

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));

        js().executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        js().executeScript("arguments[0].click();", btn);

        // ✅ Wait for ANY dropdown to appear (React portal)
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[normalize-space()='" + value + "']")
        ));

        // ✅ GLOBAL search (NOT inside role)
        By option = By.xpath("//span[text()='" + value + "']");

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(option));

        js().executeScript("arguments[0].click();", el);

        System.out.println("✅ Selected: " + value);
    }
    // ================= CONTACT =================
    private By firstName = By.name("name");
    private By lastName  = By.name("surname");
    private By email     = By.name("email");
    private By phone     = By.name("phone");
    private By address   = By.name("address");
    private By houseNo   = By.name("houseNumber");
    private By pincode   = By.name("postCode");
    private By city      = By.name("city");

    public void fillContactDetails(String fn, String ln, String mail,
                                   String ph, String addr, String house,
                                   String pin, String cityName) {

        enterReactText(firstName, fn);
        enterReactText(lastName, ln);
        enterReactText(email, mail);
        enterReactText(phone, ph);
        enterReactText(address, addr);
        enterReactText(houseNo, house);
        enterReactText(pincode, pin);
        enterReactText(city, cityName);

        System.out.println("✅ Contact details filled");
    }

    // ================= TRAVELLER =================
    public void fillTraveller(int i, String gender, String first,
            String last, String day,
            String month, String year,
            String nationality,
            String docNumber,
            String issueCountry,
            String issueDay,
            String issueMonth,
            String issueYear,
            String expDay,
            String expMonth,
            String expYear) {

wait.until(ExpectedConditions.visibilityOfElementLocated(
By.xpath("//span[contains(text(),'Traveller " + i + "')]")
));

handleAutoFillPopup();

// ✅ Gender
String value = gender.equalsIgnoreCase("Mr") ? "MALE" : "FEMALE";

By genderBtn = By.xpath("//input[@name='groups.1.travellers." + i + ".title' and @value='" + value + "']");
js().executeScript("arguments[0].click();",
wait.until(ExpectedConditions.presenceOfElementLocated(genderBtn)));

// ✅ Name
enterReactText(By.name("groups.1.travellers." + i + ".name"), first);
enterReactText(By.name("groups.1.travellers." + i + ".surname"), last);

// ✅ DOB
enterReactText(By.xpath("//div[@data-testid='groups.1.travellers." + i + ".dateOfBirth_day']//input"), day);

selectDropdown(
By.xpath("//button[@data-testid='groups.1.travellers." + i + ".dateOfBirth_month']"),
month
);

enterReactText(By.xpath("//div[@data-testid='groups.1.travellers." + i + ".dateOfBirth_year']//input"), year);

// ================= OPTIONAL FIELDS =================

// ✅ Nationality
By nationalityField = By.xpath("//button[@data-testid='groups.1.travellers." + i + ".nationality']");
if (isPresent(nationalityField)) {
selectDropdown(nationalityField, nationality);
}

// ✅ Document Number
By docField = By.name("groups.1.travellers." + i + ".documentNumber");
if (isPresent(docField)) {
enterReactText(docField, docNumber);
}

// ✅ Country of Issue
By issueCountryField = By.xpath("//button[@data-testid='groups.1.travellers." + i + ".documentIssueCountry']");
if (isPresent(issueCountryField)) {
selectDropdown(issueCountryField, issueCountry);
}

// ✅ Issue Date
By issueDayField = By.xpath("//div[@data-testid='groups.1.travellers." + i + ".documentIssueDate_day']//input");
if (isPresent(issueDayField)) {

enterReactText(issueDayField, issueDay);

selectDropdown(
   By.xpath("//button[@data-testid='groups.1.travellers." + i + ".documentIssueDate_month']"),
   issueMonth
);

enterReactText(
   By.xpath("//div[@data-testid='groups.1.travellers." + i + ".documentIssueDate_year']//input"),
   issueYear
);
}

// ✅ Expiry Date
By expDayField = By.xpath("//div[@data-testid='groups.1.travellers." + i + ".documentExpiryDate_day']//input");
if (isPresent(expDayField)) {

enterReactText(expDayField, expDay);

selectDropdown(
   By.xpath("//button[@data-testid='groups.1.travellers." + i + ".documentExpiryDate_month']"),
   expMonth
);

enterReactText(
   By.xpath("//div[@data-testid='groups.1.travellers." + i + ".documentExpiryDate_year']//input"),
   expYear
);
}

System.out.println("✅ Traveller " + i + " filled");
}
    // ================= FINAL =================
    private By checkbox = By.xpath("//span[@data-test='privacyPolicy-check']");
    private By nextBtn  = By.xpath("//button[@data-test='lead-generation-submit-btn']");

    public void acceptPolicyAndProceed() {

        js().executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(checkbox)));

        js().executeScript("arguments[0].click();",
                wait.until(ExpectedConditions.elementToBeClickable(nextBtn)));

        System.out.println("✅ Clicked Next");
    }

    public void verifyPaymentSection() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'How would you like to pay')]")
        ));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Card number']")
        ));

        System.out.println("✅ Payment section loaded");
    }
}