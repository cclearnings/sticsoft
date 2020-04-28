package com.sticsoft.automation.tests.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;

public class PlaceOrder {
	
	WebDriver browser = Browser.start();
	
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
	
	@Test
	public void place()
	{
		System.out.println("In place order");
	}
	
	   
    @Test(groups={"Sanity"})
    public void login(){
        
        System.out.println("Login done");
        System.out.println("Smoke Scenario2 passed");
    }
    
    @Test(groups={"Regression"})
    public void register(){
        System.out.println("Registration2 done");
    }

}
