package com.sticsoft.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.Page;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;

public class Checkout extends Page {

	private static String URL = Config.getURL() + "/checkout";

	@Name("Place Order Button")
	@Timeout(20)
	@FindBy(id = "placeorderbtn")
	private Button placeOrder;

	@Name("View Orders")
	@Timeout(20)
	@FindBy(xpath = "//button[contains(text(),'View Items')]")
	private Button viewOrers;

	@Name("Cart Items")
	@Timeout(20)
	@FindBy(xpath = "//div[starts-with(@class,'row border-bottom')]")
	private List<WebElement> cartItems;

	@Name("Change Address")
	@Timeout(10)
	@FindBy(xpath = "//button[text()='Change']")
	private WebElement changeAddress;

	@Name("Change Address")
	@Timeout(10)
	@FindBy(id = "payment00")
	private WebElement cash;
	
	@Name("Delivery Address")
	@Timeout(10)
	@FindBy(xpath = "//button[text()='Deliver here']")
	private List<WebElement> deliverhere;
	
	@Name("Verify Page")
	@Timeout(10)
	@FindBy(xpath = "//button[contains(text(),'View Items')]")
	private static WebElement VERIFY_BY;


//	public Checkout() {
//		List<HashMap> cartDetailsOnItemsPage = Items.cartDetailsOnItemsPage;
//		HashMap<String, String> orderTotalInItemsPage = Items.orderTotalInItemsPage;
//	}

	public HashMap<String, String> getOrderSummary() {

		return null;
	}

	public HashMap<String, String> getItemsList() {
		viewOrers.click();
		for (WebElement e : cartItems) {
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("Item Title",
					e.findElement(By.xpath(".//div[contains(@class,'item-cont-name')]/p")).getText().strip());
			item.put("Quantity",
					e.findElement(By.xpath(".//span[contains(@class,'text-center')][2]")).getText().strip());
			item.put("Price", e.findElement(By.xpath(".//p[contains(text(),'OMR')]")).getText().strip());
			String path = ".//div[contains(@class,'item-cont-name')]/small";
			if (e.findElements(By.xpath(path)).size() == 2) {
				List<WebElement> items = e.findElements(By.xpath(path));
				item.put("Size", items.get(0).getText().strip());
				item.put("Crust", items.get(1).getText().strip());
			} else if (e.findElements(By.xpath(path)).size() == 1) {
				item.put("Size", e.findElement(By.xpath(path)).getText().strip());
				item.put("Crust", "");
			} else {
				item.put("Size", "");
				item.put("Crust", "");
			}
			String path1 = "//p[contains(@class,'modifier-text')]";
			if (e.findElements(By.xpath(path1)).size() > 0) {
				String value = "";

				for (WebElement ele : e.findElements(By.xpath(path1))) {
					String[] strs = ele.getText().strip().split(":");
					item.put(strs[0], strs[1]);
				}

			}
			item.put("dealItems", "");

		}
		return null;
	}

	public void selectPaymentOption(String type) {
		if (type.equalsIgnoreCase("cash")) {
			if(cash.isSelected()) {
				System.out.println("Selected Cash By default");
			}else
			{
				cash.click();
			}
		}
	}

	public void selectAddress() {
		if(deliverhere.size()>0)
		{
			deliverhere.get(0).click();
		}else
		{
			changeAddress.click();
		}
	}

	public void selectPlaceOrder() throws InterruptedException {
		placeOrder.click();
		waitUntil(ExpectedConditions.invisibilityOf(placeOrder), 5);
	}

}
