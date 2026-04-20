package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverFactory;

public class Hooks {

	@Before
	public void setUp2() {
		DriverFactory.getDriver2(); // ✅ ONLY THIS
	}

	@After
	public void tearDown2() {
		DriverFactory.quitDriver2(); // ✅ CLOSE SAME DRIVER
	}
    @Before
    public void setup3() {
        DriverFactory.getDriver3(); // driver init only
    }

    @After
    public void tearDown3() {
        DriverFactory.quitDriver3();
    }
}