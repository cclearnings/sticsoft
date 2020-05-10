package com.sticsoft.automation.pages;

import java.util.HashMap;

import org.openqa.selenium.support.FindBy;

import com.sticsoft.automation.core.Page;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;

public class Checkout extends Page {
	
	
	String URL = "";
	
	@Name("Top Navigation")
	@Timeout(20)
	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	private Link placeOrder;
	
	
	
	
	public HashMap<String, String> getOrderSummary()
	{
		return null;
	}
	
	
	public void selectPlaceOrder()
	{
		placeOrder.click();
	}
	
	
	

}
