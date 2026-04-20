package utilities;

import org.openqa.selenium.WebDriver;

public class BaseClass {

    protected WebDriver driver;

    public BaseClass() {
        this.driver = DriverFactory.getDriver();
    }
}