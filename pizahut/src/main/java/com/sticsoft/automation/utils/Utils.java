package com.sticsoft.automation.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;

import com.sticsoft.automation.core.Config;

public class Utils {
	
	
    /* This method will return Json Object which can be used where it gets called. 
     * Param: Path of the file where Json file located
     * Return: Json Object
     * 
     */	
	
	public static JSONObject getJson(String path)
	{
		String completePath = "src/main/resources/testdata/" + path +".json";
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();
		try {
		 jsonObject = (JSONObject)parser.parse(new FileReader(completePath));
		}catch (FileNotFoundException e) {
			System.out.print("File request is not available in "+ completePath);
		}catch (Exception e) {
			System.out.print("Failed in exception "+ completePath);
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static JSONArray getOrderTestData(String path, String key)
	{
		path = null;
		path = path == null? "place_order" : path;
		return (JSONArray) getJson(path).get(key);
	}
	
	public static JSONObject getUserTestData()
	{
		JSONObject dataJson = getJson("user");;
		JSONObject obj = (JSONObject) dataJson.get(Config.getEnv());
		return obj;
	}
	
	
	   	
	public static String getToppings(JSONObject obj , String key)
	{
		JSONObject item = (JSONObject) obj.get("Toppings");
		return ((String) item.get(key));
	}
	
    
		
//	public static List getLocator(String fileName, String locatorName)
//	{
//		
//		JSONObject object = getJson("resources/locators/login.json");
//		List<String> elementData = null;
//		String selector = "";
//		String locator = "";
//		String value = "";		
//		return elementData;
//	}
//	
	
}
