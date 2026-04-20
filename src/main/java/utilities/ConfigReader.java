package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties prop;

	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");

			prop.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("❌ Config file not found. Check path!");
		}
	}

	public static String get2(String key) {
		return prop.getProperty(key);
	}
}