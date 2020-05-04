package com.sticsoft.automation.core.pages.frontend;

import org.openqa.selenium.support.FindBy;


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
	
	
	
	public void selectCityAndArea(String cityName, String areaName) throws InterruptedException
	{
		
		tapToSelectCityArea.click();
		System.out.println("On Test Place Orderw");
		city.click();
		Thread.sleep(1000);
		city.selectByVisibleText(cityName);
		area.click();
		Thread.sleep(1000);
	    area.selectByVisibleText(areaName);
	   	Thread.sleep(1000);
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

