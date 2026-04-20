package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverFactory;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.getDriver(); // driver init only
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}