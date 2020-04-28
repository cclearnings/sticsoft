package com.sticsoft.automation.core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

public class Runner {
	
	WebDriver browser = Browser.getDriver();
	
	@BeforeGroups(groups = { "Sanity","Regression"})
	public void openBrowser() {
		System.out.println("Before Group");
	}

	@AfterGroups(groups =  { "Sanity","Regression"})
	public void exitBrowser() {
		System.out.println("After Group");
		browser.close();
		browser.quit();
	}
	
	

}
