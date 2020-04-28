package com.sticsoft.automation.core;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		if(driver == null) {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");	 	
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}
	    return driver;
	}
	
	
	public static WebDriver start()
	{
		getDriver().get(Config.defaultAdminUrl);
		return driver;
	}
		

	public void exit()
	{
		driver.quit();
	}
	
  
	public void closeCurrentWindow()
	{
		driver.close();
	}
	
	
	
	public static WebDriverWait waitUntill(int milliseconds)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliseconds));
		return wait;
			
	}
	
	
	public static void toggleTrue(String label)
	{
		
	}
	
	
	public static void toggleFalse(String label)
	{
		
	}
	
	
	public static void enterPOSID(String label)
	{
		
	}
	
	
	public static void enterDefaultPrice(String label)
	{
		
	}
	
	public static void selectMenu(String label)
	{
		
	}
	
	
	public WebElement findElement(List<String> list)
	{
		return null;
		
	}
	
	public WebElement findElements(List<String> list)
	{
		return null;
		
	}
	
	
	
	
}
