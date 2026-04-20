package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    protected static WebDriver driver;

    public static WebDriver getDriver2() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver2() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}