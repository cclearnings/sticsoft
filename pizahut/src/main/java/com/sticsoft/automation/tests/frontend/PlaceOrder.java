package com.sticsoft.automation.tests.frontend;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Page;
import com.sticsoft.automation.core.pages.frontend.Checkout;
import com.sticsoft.automation.core.pages.frontend.HomePage;
import com.sticsoft.automation.core.pages.frontend.Items;



public class PlaceOrder {
	
	WebDriver browser = Browser.getDriver();
//	
//			
//	@BeforeSuite
//	public void collectTests() {
//		System.out.println("Before Suite started");
//		Browser.start("frontend");
//	}

	@BeforeGroups(groups={"Sanity"})
	public void startSession() {
		System.out.println("Before Groups  started");
		Browser.start("frontend");
	}
	
		
//	@BeforeMethod
//	public void openBrowser() {
//	   System.out.println("Before Method Started");
//	}
//
//	@AfterMethod
//	public void exitBrowser() {
//		browser.close();
//		browser.quit();
//		System.out.println("After Method Called");
//	}
	
	@AfterGroups(groups={"Sanity"})
	public void afterSession() {
		browser.close();
		browser.quit();
		System.out.println("After Groups Called");
	}
	
	
	@Test(groups={"Sanity"})
	public void placeOrder() throws InterruptedException
	{
		HomePage homepage = new HomePage();
		homepage.login();
		homepage.selectCityAndArea("MUSCAT","AIRPORT");
		Items items = new Items();
		HashMap<String, String> customization = new HashMap<String, String>();
		customization.put("Size", "large");
		customization.put("Crust", "Thin N Cripsy");
		customization.put("Toppings", "Pepperoni");
		items.selectMenuItem("Pizza", "VERY VEGGIE", customization);
		items.getItemsInCart();
		items.navigateToCartPage();
		Checkout checkout = new Checkout();
		checkout.selectPlaceOrder();
		Thread.sleep(5000);
	}

		
	
	
   }
