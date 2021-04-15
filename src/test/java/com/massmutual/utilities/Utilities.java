package com.massmutual.utilities;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Utilities {
	
	public static ResourceBundle rb = ResourceBundle.getBundle("application");
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		String browser = rb.getString("browser");
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", rb.getString("webdriver.chrome.driver"));
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", rb.getString("webdriver.gecko.driver"));
				driver = new FirefoxDriver();
			}
		} catch(Exception e) {
			Assert.fail("Invalid Browser: "+browser);
		}
		return driver;
	}
	
	public static void waitForElements(List<WebElement> element) {
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(element));
			Reporter.log(element.toString()+ " is visible");
		} catch(Exception e) {
			Assert.fail(element.toString()+" is not visible");
		}
	}
	
	public static String getDecimalFormat(Float value, String format) {
		DecimalFormat decimalFormat = new DecimalFormat(format);
		String val = decimalFormat.format(value);
		return val;
	}
	
	
}
