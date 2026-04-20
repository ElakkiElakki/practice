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
}