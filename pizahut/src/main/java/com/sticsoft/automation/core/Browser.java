package com.sticsoft.automation.core;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sticsoft.automation.core.Config.*;

public class Browser {

	private static WebDriver driver;
	private final static Logger logger = Logger.getLogger("Browser");

	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static Logger log() {
		return logger;
	}

	public static void start(String site) {
		String url = null;
		if (site == "admin") {
			url = Config.defaultAdminUrl;
		} else if (site == "frontend") {
			url = Config.defaultFrontEndUrl;
		}
		getDriver().get(url);
	}

	public void exit() {
		driver.quit();
	}

	public void closeCurrentWindow() {
		driver.close();
	}

	public static synchronized Object execJavascript(String script, Object... args) {
		try {
			JavascriptExecutor scriptExe = ((JavascriptExecutor) getDriver());
			return scriptExe.executeScript(script, args);
		} catch (Exception e) {
			log().log(Level.WARNING, e.getMessage());
			return "";
		}
	}

}
