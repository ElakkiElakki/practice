package stepDefinition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverFactory;

public class Hooks {

	@Before
	public void setUp() {
		DriverFactory.getDriver(); // ✅ ONLY THIS
	}

	@After
	public void tearDown() {
		DriverFactory.quitDriver(); // ✅ CLOSE SAME DRIVER
	}
}