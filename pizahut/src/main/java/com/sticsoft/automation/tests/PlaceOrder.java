package com.sticsoft.automation.tests;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Page;

import static com.sticsoft.automation.core.Page.*;


import com.sticsoft.automation.pages.Checkout;
import com.sticsoft.automation.pages.HomePage;
import com.sticsoft.automation.pages.DealNMenuItems;
import com.sticsoft.automation.pages.LiveDashBoard;
import com.sticsoft.automation.pages.ThankYou;
import com.sticsoft.automation.utils.Utils;



public class PlaceOrder {
	
	WebDriver browser = Browser.getDriver();
		
	
	@BeforeSuite(groups={"Sanity"})
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
	public static void placeOrder() throws InterruptedException
	{		 
		for(int i = 1; i < 5;  i ++)
		{
		JSONArray dataJson = Utils.getOrderTestData("","order_0"+i);
		onPage(HomePage.class);
		HomePage homePage = (HomePage) getCurrentPage();
		homePage.login();
		homePage.selectChannelAndCity();
		onPage(DealNMenuItems.class);
		DealNMenuItems items = (DealNMenuItems) getCurrentPage();
		for(Object obj : dataJson)
        {
           items.addItemToCart((JSONObject) obj);
        }
		items.navigateToCartPage();
		onPage(Checkout.class);
		Checkout checkout = (Checkout) getCurrentPage();
        checkout.selectAddress();
        checkout.selectPaymentOption("cash");
        checkout.selectPlaceOrder();
        onPage(ThankYou.class);
        ThankYou thankyou = (ThankYou) getCurrentPage();
        Thread.sleep(1000);
        thankyou.getItems();
        System.out.println(Page.getItemsOrdered());
	    onPage(LiveDashBoard.class);
        LiveDashBoard liveDashBoard = (LiveDashBoard) getCurrentPage();
        liveDashBoard.viewOrderDetails(Page.getOrderID());
        // TODO
        
        
	  }
	}

			
	
	
   }
