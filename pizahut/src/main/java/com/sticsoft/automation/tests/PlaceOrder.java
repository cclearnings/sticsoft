package com.sticsoft.automation.tests;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;

import com.sticsoft.automation.pages.HomePage;
import com.sticsoft.automation.pages.Items;
import com.sticsoft.automation.utils.Utils;



public class PlaceOrder {
	
	WebDriver browser = Browser.getDriver();
	
	
	
	@BeforeSuite
	public void collectTests() {
		System.out.println("Before Suite started");
		Browser.start("frontend");
		
	}

//	@BeforeGroups(groups={"Sanity"})
//	public void startSession() {
//		System.out.println("Before Groups  started");
//		Browser.start("frontend");
//	}
//	
		
	@BeforeMethod
	public void openBrowser() {
	   System.out.println("Before Method Started");
	}

	@AfterMethod
	public void exitBrowser() {
		browser.close();
		browser.quit();
		System.out.println("After Method Called");
	}
	
//	@AfterGroups(groups={"Sanity"})
//	public void afterSession() {
//		browser.close();
//		browser.quit();
//		System.out.println("After Groups Called");
//	}
	
//	
//	 @DataProvider(name = "place_orders")
//	   public static JSONArray placeOrders() {
//		 JSONArray dataJson = Utils.getOrderTestData("","order_01");
//		 return dataJson;
//	   }
	
	
	
	@Test(groups={"Sanity"})
	public void placeOrder() throws InterruptedException
	{		
		JSONArray dataJson = Utils.getOrderTestData("","order_01");
		HomePage homepage = new HomePage();
		homepage.login();
		homepage.selectCityAndArea("MUSCAT","AIRPORT");
		Items items = new Items();
		for(Object obj : dataJson)
        {
           items.addItemToCart((JSONObject) obj);
        }
	
		
//		dataJson
//		items.selectMenuItem("Pizza", "CHICKEN FAJITA", null);
	}

		
	
	
   }
