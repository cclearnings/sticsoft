package com.sticsoft.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.Page;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;

public class ThankYou extends Page {

	@Name("Order ID")
	@Timeout(5)
	@FindBy(xpath = "//p[contains(text(),'Your order is placed with orderId')]/b")
	private WebElement orderID;

	@Name("Line Items")
	@Timeout(5)
	@FindBy(xpath = "//div[starts-with(@class,'row border-bottom')]")
	private List<WebElement> itemsplaced;
	
		
	@Timeout(10)
	@Name("Button Select")
	@FindBy(id = "pills-tab")
	private static WebElement VERIFY_BY;
	
	private static String URL = Config.getURL() + "/Thankyou";
	
	public ThankYou()
	{
		Page.setOrderID(orderID.getText().strip());
	}

	public void getItems() {
		List<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();
		for (WebElement e : itemsplaced) {
			System.out.println("itemsplaced size is "+ itemsplaced.size());
            try {
            	driver.findElement(By.className("slim-scroll")).click();
            	e.sendKeys(Keys.ARROW_DOWN);
            }catch (Exception ex) {
				//System
			}
			Browser.moveToElement();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Qunatity", e.findElement(By.xpath(".//a")).getText().strip());
			map.put("Item Name",
					e.findElement(By.xpath(".//div[contains(@class,'item-cont-name')]/p")).getText().strip());
			String path = ".//div[contains(@class,'item-cont-name')]//small";
			if (e.findElements(By.xpath(path)).size() == 2) {
				List<WebElement> sc = e.findElements(By.xpath(path));
				map.put("Size", sc.get(0).getText().strip());
				map.put("Crust", sc.get(1).getText().strip());
			} else if (e.findElements(By.xpath(path)).size() == 1) {
				map.put("Size", e.findElement(By.xpath(path)).getText().strip());
				map.put("Crust", "");
			} else {
				map.put("Size", "");
				map.put("Crust", "");
			}
			String path1 = ".//p[contains(@class,'modifier-text')]";
			if (e.findElements(By.xpath(path1)).size() > 0) {
				String value = "";
				
				for (WebElement ele : e.findElements(By.xpath(path1))) {
					System.out.println(ele.getText());
					String[] strs = ele.getText().strip().split(":");
					map.put(strs[0], strs[1]);
				}
			}
			map.put("Price", e.findElement(By.xpath(".//p[contains(text(),'OMR')]")).getText().strip());
			String path3 = ".//div[contains(@class,'menu-itmText')]";
			if (e.findElements(By.xpath(path3)).size() > 0) {
				String value = "";

				for (WebElement ele : e.findElements(By.xpath(path3))) {
					value += ele.getText().strip() + ",";
				}

				map.put("dealItems", value);
			}
           items.add(map);
		}

	   Page.setItemsOrdered(items);
	}

}
