package com.sticsoft.automation.pages;

import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.Page;
import com.sticsoft.automation.utils.Utils;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.*;


public class HomePage extends Page {
		
	@Name("Search by City and Area")
	@Timeout(5)
	@FindBy(xpath = "//span[contains(text(),'Search by City')]")
	private Link tapToSelectCityArea;

	@Name("Select City")
	@Timeout(5)
	@FindBy(xpath = "//select[@id='inputGroupSelect01' and @formcontrolname='Province']")
	private Select city;

	
	@Name("Select Area")
	@Timeout(5)
	@FindBy(xpath = "//select[@id='inputGroupSelect01' and @formcontrolname='Suburb']")
	private Select area;
	
	
	@Name("Start My Order")
	@Timeout(5)
	@FindBy(xpath = "//button[contains(text(),'Start My')]")
	private Button startMyOrder;
	

	@Name("sign in link")
	@Timeout(5)
	@FindBy(xpath = "//span[text()='SIGN IN']")
	private Button signIn;
		
	
	@Name("username")
	@Timeout(5)
	@FindBy(id = "email-id")
	private Button userName;
	
	@Name("password")
	@Timeout(5)
	@FindBy(id = "pwrd")
	private Button passwordKey;
	
	@Name("submit")
	@Timeout(5)
	@FindBy(xpath = "//button[text()='Login']")
	private Button sumitDetail;
	
	@Name("Take Away")
	@Timeout(5)
	@FindBy(xpath = "//a[contains(text(),'Take Away')]")
	private Button takeAway;
	
	@Name("verify page")
	@Timeout(5)
	@FindBy(xpath = "//h2[text()='Our Most Popular Deals']")
	private static Button VERIFY_BY;
		
	@Name("change")
	@Timeout(5)
	@FindBy( xpath =  "//button[text()='Change']")
	private List<WebElement> change;

	
	
	public static String URL = Config.getURL()+"/Home";
	
	
	public HomePage()
	{
		driver.get(URL);
	}

	public void selectChannelAndCity()
	{
	    	
	   	if(change.size()>0)
	   	{
	   		change.get(0).click();
	   	}
//		tapToSelectCityArea.click();
//		System.out.println("On Test Place Orderw");
		if(Page.getOrderChannel().equalsIgnoreCase("take away"))
		{
			takeAway.click();
		}
		city.click();
		waitUntil(ExpectedConditions.elementToBeSelected(city), 5);
		city.selectByVisibleText(Page.getCity());
		area.click();
		waitUntil(ExpectedConditions.elementToBeSelected(area), 5);
	    area.selectByVisibleText(Page.getArea());
	    tapToSelectCityArea.click();
	    try {
	    	startMyOrder.click();	
	    }catch (Exception e) {
	    	startMyOrder.sendKeys(Keys.ENTER);
		}
	    waitUntil(ExpectedConditions.invisibilityOf(takeAway), 5);
		   
	}
	
	public void login()
	{
		JSONObject json = Utils.getJson("place_order");
		Page.setCity(json.get("city").toString());
		Page.setArea(json.get("area").toString());
		signIn.click();
		userName.clear();
		JSONObject obj = Utils.getUserTestData();
	 	userName.sendKeys(obj.get("username").toString());
		passwordKey.clear();
		passwordKey.sendKeys(obj.get("password").toString());
		sumitDetail.click();
		Page.setOrderChannel("");
	}
	
	

}

