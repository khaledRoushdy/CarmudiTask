package com.carmudi.AbstractSelenium;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.carmudi.Utilities.ResourceLoader;

public class AbstractSelenium {

	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	public static WebDriver getBrowser(String browserName) {
		WebDriver driver = null;

		switch (browserName) {
		case "Firefox":
			driver = drivers.get("Firefox");

			if (driver == null)

			{
				driver = new FirefoxDriver();
				drivers.put("Firefox", driver);
			}
			break;

		case "Chrome":
			// driver = drivers.get("Chrome");
			if (driver == null)

			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
				driver = new ChromeDriver();
				drivers.put("Chrome", driver);
			}
			break;
		}
		return driver;
	}

	public static void closeAllDriver() {
		for (String key : drivers.keySet()) {
			drivers.get(key).close();
			drivers.get(key).quit();
		}

	}
}
