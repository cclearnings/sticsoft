package com.sticsoft.automation.pages;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.Page;
import com.sticsoft.automation.utils.Utils;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;

public class LiveDashBoard extends Page {

	/*
	 * Order ID - Thank you page Restaurant Name - Home Page Order Channel - Home
	 * Page Order received at Customer Name - Checkout Page Mobile Number - Profile
	 * Payment -
	 */

	/*
	 * Address 1 Address 2 City Area Address Type
	 */

	/*
	 * Quantity Item Name Size,Modifier added Removed Unit Price Total Price Order
	 * Amount Tax
	 */

	@Name("Email")
	@Timeout(5)
	@FindBy(id = "Email")
	private WebElement emailAddress;

	@Name("Password")
	@Timeout(5)
	@FindBy(id = "password")
	private WebElement password;

	@Name("Submit")
	@Timeout(5)
	@FindBy(xpath = "//button[text()='Login']")
	private WebElement submit;

	@Name("live dash board")
	@Timeout(5)
	@FindBy(xpath = "//a[@routerlink='/LiveOrderDashboard']")
	private Link liveDashBrd;

	@Name("model content")
	@Timeout(5)
	@FindBy(className = "modal-content")
	private Link viewModel;
		
	
	@Timeout(10)
	@Name("Button Select")
	@FindBy(id = "pills-tab")
	private static WebElement VERIFY_BY;
	
	private static String URL = Config.getURL() + "/checkout";
		
	public LiveDashBoard() {
		WebDriver driver = Browser.getDriver();
		String url = Config.getURL().replaceAll("ui", "admin");
		driver.navigate().to(url);
		login();
	}

	private void login() {
		JSONObject obj = Utils.getUserTestData();
		emailAddress.click();
		emailAddress.sendKeys(obj.get("admin_username").toString());
		password.click();
		password.sendKeys(obj.get("admin_password").toString());
		submit.click();
		waitUntil(liveDashBrd.isDisplayed(), 5);
	}
	
	
	public void viewOrder(String orderNumber)
	{
		String path = "//b[contains(text(),'"+orderNumber+"')]//ancestor::tr";
		try {
			WebElement e = driver.findElement(By.xpath(path));
			e.findElement(By.xpath(".//button")).click();
		}catch (Exception e) {
			// TODO: handle exception
		}
		waitUntil(viewModel.isDisplayed(),3);
	}
	

	public void viewOrderDetails(String orderNumber) throws InterruptedException
	{
		viewOrder(orderNumber);
		Thread.sleep(5000);
			
	}

	private HashMap<String, String> orderDetails()
	{
		HashMap<String, String> od =  new HashMap<String, String>();
		od.put("Order ID", "");
		od.put("Restaurant Name", "");
		od.put("Order Channel", "");
		od.put("Customer Name", "");
		od.put("Mobile No", "");
		od.put("PaymentMode", "");
		return od;
	}
	
	private HashMap<String, String> deliveryAddressDetails()
	{
		return null;
	}
	
	private List<HashMap<String, String>> itemsOrdered()
	{
		return null;
	}
	
	

}

