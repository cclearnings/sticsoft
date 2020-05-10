package com.sticsoft.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.Page;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.*;


public class HomePage extends Page {
	
	
	
	@Name("Search by City and Area")
	@Timeout(20)
	@FindBy(xpath = "//span[contains(text(),'Search by City')]")
	private Link tapToSelectCityArea;

	@Name("Select City")
	@Timeout(20)
	@FindBy(xpath = "//select[@id='inputGroupSelect01' and @formcontrolname='Province']")
	private Select city;

	
	@Name("Select Area")
	@Timeout(20)
	@FindBy(xpath = "//select[@id='inputGroupSelect01' and @formcontrolname='Suburb']")
	private Select area;
	
	
	@Name("Start My Order")
	@Timeout(20)
	@FindBy(xpath = "//button[contains(text(),'Start My order')]")
	private Button startMyOrder;
	

	@Name("sign in link")
	@Timeout(20)
	@FindBy(xpath = "//span[text()='SIGN IN']")
	private Button signIn;
		
	
	@Name("username")
	@Timeout(20)
	@FindBy(id = "email-id")
	private Button userName;
	
	@Name("password")
	@Timeout(20)
	@FindBy(id = "pwrd")
	private Button passwordKey;
	
	@Name("submit")
	@Timeout(20)
	@FindBy(xpath = "//button[text()='Login']")
	private Button sumitDetail;
	

	public static final String URL = Config.getURL()+"/Home";
	public static final By VERIFY_BY = By.xpath("//h2[text()='Our Most Popular Deals']");

	
	public void selectCityAndArea(String cityName, String areaName)
	{
		
//		tapToSelectCityArea.click();
//		System.out.println("On Test Place Orderw");
    	city.click();
		waitUntil(ExpectedConditions.elementToBeSelected(city), 5);
		city.selectByVisibleText(cityName);
		area.click();
		waitUntil(ExpectedConditions.elementToBeSelected(area), 5);
	    area.selectByVisibleText(areaName);
	    waitUntil(ExpectedConditions.elementToBeSelected(startMyOrder), 5);
		startMyOrder.click();
			
	}
	
	
	public void login()
	{
		signIn.click();
		userName.clear();
		userName.sendKeys("cc.stic@gmail.com");
		passwordKey.clear();
		passwordKey.sendKeys("Stic@2018");
		sumitDetail.click();
	}
	
	
	
	
	

}

