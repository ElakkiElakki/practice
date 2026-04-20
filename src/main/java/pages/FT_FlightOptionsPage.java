package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

public class FT_FlightOptionsPage {

	WebDriver driver;
	WebDriverWait wait;

	public FT_FlightOptionsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	}

	// ================= SORT =================

	public void verifyCheapestSorting() {

		// 👉 STEP 1: CHECK IF TAB EXISTS (NOT clickable, just presence)
		boolean isTabPresent = driver.findElements(By.id("TAB-CHEAPEST")).size() > 0;

		if (isTabPresent) {

			System.out.println("👺 Using TAB layout");

			WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(By.id("TAB-CHEAPEST")));

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);

		} else {

			System.out.println("👺 Using DROPDOWN layout");

			By sortBtn = By.xpath("//button[contains(.,'Sort by')]");

			WebElement sort = wait.until(ExpectedConditions.elementToBeClickable(sortBtn));
			sort.click();

			By cheapest = By.xpath("//button[.//div[text()='Cheapest']]");

			WebElement cheap = wait.until(ExpectedConditions.elementToBeClickable(cheapest));
			cheap.click();
		}

		// ✅ WAIT FOR RESULTS
		By pricesLocator = By.xpath("//div[@data-testid='upt_price']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(pricesLocator));

		List<WebElement> prices = driver.findElements(pricesLocator);

		int price1 = Integer.parseInt(prices.get(0).getText().replaceAll("[^0-9]", ""));
		int price2 = Integer.parseInt(prices.get(1).getText().replaceAll("[^0-9]", ""));

		System.out.println("👺 Price 1: " + price1);
		System.out.println("👺 Price 2: " + price2);

		Assert.assertTrue(price1 <= price2, "Sorting FAILED");

		System.out.println("👺 Cheapest sorting PASSED");
	}

	// ================= VIEW DETAILS =================

	public void verifyFlightDetails() {

		// 1. Click "View details"
		By viewDetails = By.xpath("(//button[@data-testid='flight_card_bound_select_flight'])[1]");

		wait.until(ExpectedConditions.elementToBeClickable(viewDetails));

		WebElement btn = driver.findElement(viewDetails);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

		System.out.println("👺 Clicked View Details");

		// 2. Wait for Baggage section
		By baggage = By.xpath("//h2[text()='Baggage']");

		WebElement baggageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(baggage));

		// ✅ 3. ASSERT (clean)
		Assert.assertTrue(baggageElement.isDisplayed(), "Flight details popup FAILED — Baggage section not visible");

		System.out.println("👺 Flight details popup PASSED");

		// 4. Click Close (X)
		By closeBtn = By.xpath("//button[@aria-label='Close']");

		wait.until(ExpectedConditions.elementToBeClickable(closeBtn));

		driver.findElement(closeBtn).click();

		System.out.println("👺 Closed flight details popup");
	}

	// ================= FARE OPTIONS =================
	public void verifyFareOptions() {

		// 1. Click "Fare options"
		By fareBtn = By.xpath("//button[@aria-controls='flights-fare-selector-0']");

		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(fareBtn));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

		System.out.println("👺 Clicked Fare Options");

		// 2. Wait for CAROUSEL (MAIN PROOF)
		By carousel = By.xpath("//div[contains(@class,'Carousel-module__inner')]");

		WebElement carouselElement = wait.until(ExpectedConditions.visibilityOfElementLocated(carousel));

		// 3. Get fare cards (titles)
		List<WebElement> fareCards = driver.findElements(By.xpath("//div[@data-fare-card-row='title']"));

		// 4. ASSERT
		Assert.assertTrue(carouselElement.isDisplayed() && fareCards.size() > 0,
				"Fare options FAILED → carousel not displayed");

		System.out.println("👺 Fare carousel displayed with " + fareCards.size() + " options");

		// 5. Print few fares (for debugging clarity)
		for (int i = 0; i < Math.min(3, fareCards.size()); i++) {
			System.out.println("Fare: " + fareCards.get(i).getText());
		}
	}
	// ================= AIRLINE FILTER =================

	public void validateAirlineFilter() {

		// 1. Get initial flight count (NEW locator)
		By totalFlights = By.xpath("//div[@data-testid='search_filters_summary_results_number']");

		WebElement totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(totalFlights));

		int beforeCount = Integer.parseInt(totalText.getText().replaceAll("[^0-9]", ""));
		System.out.println("👺 Flights BEFORE filter: " + beforeCount);

		// 2. Click Etihad Airways checkbox
		By airline = By.xpath("//div[text()='Etihad Airways']/ancestor::label");

		WebElement airlineCheckbox = wait.until(ExpectedConditions.elementToBeClickable(airline));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", airlineCheckbox);

		System.out.println("👺 Unchecked Etihad Airways");

		// 3. Wait for count to change
		wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElementLocated(totalFlights, String.valueOf(beforeCount))));

		// 4. Get updated count
		int afterCount = Integer.parseInt(driver.findElement(totalFlights).getText().replaceAll("[^0-9]", ""));

		System.out.println("👺 Flights AFTER filter: " + afterCount);

		// 5. Assert
		Assert.assertTrue(afterCount < beforeCount, "Airline filter FAILED");

		System.out.println("👺 Airline filter PASSED");
	}

	// ================= TIME FILTER =================
	public void validateTimeFilter() {

		// 1. Get expected count from filter
		By filterCount = By.xpath("//span[@data-testid='flight_times_filter_v2_flight_time_departure_0_count']");

		WebElement countElement = wait.until(ExpectedConditions.visibilityOfElementLocated(filterCount));
		int expectedCount = Integer.parseInt(countElement.getText());

		System.out.println("👺 Expected flights (filter): " + expectedCount);

		// 2. Click checkbox (NO isSelected)
		By checkbox = By.xpath("//div[text()='12:00 AM–5:59 AM']");

		WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb);

		System.out.println("👺 Time filter applied");

		// 3. Wait for results update (NEW locator)
		By totalFlights = By.xpath("//div[@data-testid='search_filters_summary_results_number']");

		wait.until(ExpectedConditions.textToBePresentInElementLocated(totalFlights, String.valueOf(expectedCount)));

		// 4. Get actual count
		String text = driver.findElement(totalFlights).getText();
		int actualCount = Integer.parseInt(text.replaceAll("[^0-9]", ""));

		System.out.println("👺 Actual flights (result): " + actualCount);

		// 5. Assert
		Assert.assertEquals(actualCount, expectedCount, "Time filter FAILED");

		System.out.println("👺 Time filter PASSED");
	}

}