package com.sticsoft.automation.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;

public class Utils {
	
	
	public static void main(String[] args)
	{
		System.out.println("Hello");
		getLocator("", "");
	}
	
	
	
	public static JSONObject getJson(String path)
	{
		JSONObject object = null;
		JSONParser parser = new JSONParser();
		try {
		 Object obj = parser.parse(new FileReader(path));
		 object = (JSONObject) obj;
		}catch (FileNotFoundException e) {
			System.out.print("File request is not available in "+ path);
		}catch (Exception e) {
			System.out.print("Failed in exception "+ path);
			e.printStackTrace();
		}
		System.out.println(object.get("admin_username"));
		return object;
	}
    	
	
	public static List getLocator(String fileName, String locatorName)
	{
		
		JSONObject object = getJson("resources/locators/login.json");
		List<String> elementData = null;
		String selector = "";
		String locator = "";
		String value = "";
		
			
		return elementData;
	}
	
	
}
