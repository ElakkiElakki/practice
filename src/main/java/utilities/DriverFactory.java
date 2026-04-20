package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver2() {

        if (driver == null) {

            String browser = ConfigReader.get2("browser");

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            }

            driver.manage().window().maximize();

            String url = ConfigReader.get2("url");
            driver.get(url);
        }

        return driver;
    }

    public static void quitDriver2() {
    public static WebDriver getDriver3() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver3() {
        if (driver != null) {
            //driver.quit();
            driver = null;
        }
    }
    }               
}