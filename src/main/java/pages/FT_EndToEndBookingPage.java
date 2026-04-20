package pages;

import org.openqa.selenium.support.ui.Select;
import utilities.ConfigReader;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class FT_EndToEndBookingPage {

	WebDriver driver;
	WebDriverWait wait;

	public FT_EndToEndBookingPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	}

	// ===== LOCATORS =====

	By flightsTab = By.id("flights");
	By oneWayRadio = By.xpath("//label[@for='search_type_option_ONEWAY']");
	By leavingFromBtn = By.xpath("//button[@data-ui-name='input_location_from_segment_0']");
	By removeDefaultCity = By.xpath("//button[@data-autocomplete-chip-idx='0']");
	By goingToBtn = By.xpath("//button[@data-ui-name='input_location_to_segment_0']");
	By dateBtn = By.xpath("//button[@data-ui-name='button_date_segment_0']");
	By june20 = By.xpath("//span[@data-date='2026-06-20']");
	By travellersBtn = By.xpath("//button[@data-ui-name='button_occupancy']");
	By childPlus = By.xpath("//button[@data-ui-name='button_occupancy_children_plus']");
	By childAgeDropdown = By.xpath("//select[@data-ui-name='select_occupancy_children_age_0']");
	By doneBtn = By.xpath("//button[@data-ui-name='button_occupancy_action_bar_done']");

	// 🔥 UPDATED
	By searchBtn = By.xpath("//button[@data-ui-name='button_search_submit']");
	By inputField = By.xpath("//input[@data-ui-name='input_text_autocomplete']");
	By locationOption = By.xpath("(//li[@data-ui-name='locations_list_item'])[1]");

	By firstFlightCard = By.id("flightcard-0");
	By viewDetailsBtn = By.xpath("//div[@id='flightcard-0']//button[@data-testid='flight_card_bound_select_flight']");

	// 🔥 UPDATED
	By continueBtnDetails = By.xpath("//button[@data-testid='flight_details_continue']");
	By nextBtn = By.xpath("(//button[.//span[text()='Next']])[1]");
	By skipBtn = By.xpath("(//button[.//span[text()='Skip']])[1]");

	By ecoClassicContinue = By.xpath("//button[@data-testid='branded_fare_cta_1']");

	By contactEmail = By.name("booker.email");
	By countryCode = By.name("countryCode");
	By phoneNumber = By.name("number");

	By noFlexOption = By.xpath("//div[@data-testid='title' and text()='No flexibility']");
	By paymentIframe = By.xpath("//iframe[@title='Payment']");

	// ===== METHODS =====

	public void openFlightsTab() {
		waitAndClick(flightsTab);
	}

	public void selectOneWay() {
		wait.until(ExpectedConditions.elementToBeClickable(oneWayRadio)).click();
	}

	public void handleMultipleDeparture() {

		waitAndClick(leavingFromBtn);
		waitAndClick(removeDefaultCity);

		waitAndType(inputField, "Bangalore");
		waitAndClick(locationOption);

		waitAndClick(leavingFromBtn);

		waitAndType(inputField, "Chennai");
		waitAndClick(locationOption);
	}

	public void handleMultipleDestination() {

		waitAndClick(goingToBtn);

		waitAndType(inputField, "Paris");
		waitAndClick(locationOption);
	}

	public void selectTravelDate() {

		waitAndClick(dateBtn);

		By dateLocator = By.xpath("//span[@data-date='2026-06-20']");

		WebElement date = wait.until(ExpectedConditions.presenceOfElementLocated(dateLocator));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", date);

		wait.until(ExpectedConditions.elementToBeClickable(date));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", date);
	}

	public void handleTravellers() {

		waitAndClick(travellersBtn);
		waitAndClick(childPlus);

		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(childAgeDropdown));
		new Select(dropdown).selectByVisibleText("6");

		waitAndClick(doneBtn);
	}

	public void clickSearch() {
		waitAndClick(searchBtn);
	}

	public void openFirstFlightDetails() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstFlightCard));
		wait.until(ExpectedConditions.elementToBeClickable(viewDetailsBtn)).click();
	}

	public void clickContinueFromDetails() {
		waitAndClick(By.xpath("(//button[.//span[text()='Continue']])[1]"));
	}

	public void selectTicketAndContinue() {
		WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(ecoClassicContinue));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
	}

	public void fillTravellerDetails() {

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Enter your details')]")));

		List<WebElement> buttons = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//button[contains(@aria-label,'Add this traveler')]")));

		String[] firstNames = { "Nethra", "Arjun" };
		String[] lastNames = { "Shee", "Kumar" };

		int i = 0;

		for (WebElement btn : buttons) {

			String label = btn.getAttribute("aria-label");

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name,'firstName')]")));

			driver.findElement(By.xpath("//input[contains(@name,'firstName')]")).sendKeys(firstNames[i]);
			driver.findElement(By.xpath("//input[contains(@name,'lastName')]")).sendKeys(lastNames[i]);

			new Select(driver.findElement(By.xpath("//select[contains(@name,'gender')]"))).selectByVisibleText("Male");

			if (label.contains("Child")) {
				new Select(driver.findElement(By.xpath("//select[contains(@name,'month')]")))
						.selectByVisibleText("June");

				driver.findElement(By.xpath("//input[contains(@name,'day')]")).sendKeys("10");
				driver.findElement(By.xpath("//input[contains(@name,'year')]")).sendKeys("2018");
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[text()='Done']]"))).click();

			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[contains(@name,'firstName')]")));

			i++;
		}
	}

	public void fillContactDetails() {

		WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmail));

		emailField.click();
		emailField.sendKeys(Keys.CONTROL + "a");
		emailField.sendKeys(Keys.DELETE);
		emailField.sendKeys(ConfigReader.get("email"));

		Select code = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryCode)));
		code.selectByValue("in");

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber));

		phone.click();
		phone.sendKeys(Keys.CONTROL + "a");
		phone.sendKeys(Keys.DELETE);
		phone.sendKeys("9876543210");

		WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
	}

	public void selectTicketType() {

		WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(noFlexOption));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);

		wait.until(ExpectedConditions.visibilityOf(option));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

		System.out.println("👺 Ticket selected: No flexibility");
	}

	public void clickNextAfterTicket() {

		WebElement next = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[.//span[text()='Next']])[last()]")));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", next);

		wait.until(ExpectedConditions.elementToBeClickable(next));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);

		System.out.println("👺 Clicked Next");
	}

	public void skipSeatSelection() {

		WebElement skip = wait.until(ExpectedConditions.presenceOfElementLocated(skipBtn));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", skip);

		wait.until(ExpectedConditions.elementToBeClickable(skip));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", skip);

		System.out.println("👺 Skipped seats");
	}

	public void verifyPaymentPage() {

		By paymentHeader = By.xpath("//h2[contains(text(),'How do you want to pay')]");

		WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentHeader));

		if (header.isDisplayed()) {
			System.out.println("👺 FINAL SUCCESS: Payment page loaded — E2E COMPLETE");
		} else {
			throw new AssertionError("❌ Payment page not reached");
		}
	}

	public void verifyResults() {

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@data-testid,'flight_card')]")));

		System.out.println("👺 Flight search SUCCESS — results page loaded");
	}

	public void stopAtPaymentStep() {

		By cardField = By.xpath("//input[@name='number']");

		WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(cardField));

		if (card.isDisplayed()) {

			System.out.println("👺 PAYMENT STEP REACHED — STOPPING EXECUTION");

			// 🔥 STOP EXECUTION HERE
			throw new RuntimeException("Test Completed Successfully at Payment Step");
		}
	}

	public void stopAtPaymentUI() {

		By cardField = By.xpath("//input[@name='number']");

		WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(cardField));

		if (card.isDisplayed()) {
			System.out.println("👺 FINAL SUCCESS: Payment UI (card section) reached — STOPPING HERE");

			// STOP execution intentionally
			throw new RuntimeException("Test completed successfully at payment UI");
		}
	}

	public WebElement waitAndClick(By locator) {
		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

		wait.until(ExpectedConditions.elementToBeClickable(el));

		try {
			el.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
		}

		return el;
	}

	public WebElement waitAndType(By locator, String text) {
		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		el.clear();
		el.sendKeys(text);
		return el;
	}
}