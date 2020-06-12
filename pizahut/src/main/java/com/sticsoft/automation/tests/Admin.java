package com.sticsoft.automation.tests;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.utils.Utils;


public class Admin {
	
	
	public static void main(String[] args)
	{
		
		
		String str = "hel*lo";
		String s = str.replaceAll("^[0-9][a-z][A-Z]","");
		if(s.length() == str.length())
		{
			System.out.println(str);
		}else {
			char ch[] = str.toCharArray();
			for(char c : ch)
			{
				
			}
		}
		
		
//	   HashMap<String, String> map = new HashMap<String, String>();
//	   map.put("Title", "HUTS SUPER SUPREME");
//	   map.put("Price", "OMR 4.5000"); 
//	   HashMap<String, String> map1 = new HashMap<String, String>();
//	   map1.put("Title", "DYNAMITE SHRIMP");
//	   map1.put("Price", "OMR 5.3000");
//	   HashMap<String, String> map2 = new HashMap<String, String>();
//	   map2.put("Title", "VERY VEGGIE");
//	   map2.put("Price", "OMR 6.8000");
//	   List<HashMap> detail = new ArrayList<>();
//       detail.add(map);detail.add(map1);detail.add(map2);
//       System.out.println(detail);
//       System.out.println(getIndex("VERY EGGIE", detail));
//       for(int i = 1 ; i <= 10 ; i++)
//       {
//    	   System.out.println(85*i);
//       }
////    
//		JSONArray items = Utils.getOrderTestData("","order_01");
//        System.out.println(items);
//        for(Object obj : items)
//        {
//        	JSONObject json = (JSONObject) obj;
//        	System.out.println(json.get("Crust"));
//        	System.out.println(json.get("Toppings"));
//        	String str = Utils.getToppings(json, "Sauce");
//        	String[] s = str.split(",");
//    		System.out.println(str.split(","));
//    		
//        }
		
			
//		
//		
//		
		
		//dataJson.keySet().forEach(keyStr ->
//	    {
//	        Object keyvalue = (JSONObject) dataJson.get(keyStr);
//	        System.out.println("key: "+ keyStr + " Items: " + keyvalue);
//	        JSONArray jsonArray = new JSONArray();
//	        jsonArray.add(keyvalue);
//	    });   
////	   
//		
//		Utils util = new Utils();
//		util.getUserTestData("username");
//		
		
	}


		
	public static Integer getIndex(String name, List<HashMap> det)
	{
		for(HashMap<String, String> map : det)
		{
			if(map.get("Title").equalsIgnoreCase(name))
			{
				return det.indexOf(map);
			}
		}
		return -1;
	}
	
	
//	

//	WebDriver browser = Browser.getDriver();
//	
//	
//	@Test
//	public void login() {
//		browser.findElement(By.id("Email")).sendKeys("chandra@pizza.com");
//		browser.findElement(By.id("password")).sendKeys("Stic@123$");
//		browser.findElement(By.xpath("//button[text()='Login']")).click();
//	}
//
//	@Test
//	public void loged() {
//		System.out.println("Logged In");
//	}
//
//	@Test(groups = { "Sanity" })
//	public void logisdn() {
//
//		System.out.println("Login done");
//		System.out.println("Smoke Scenario passed");
//	}
//
//	@Test(groups = { "Regression" })
//	public void register() {
//		System.out.println("Registration done");
//	}

}
