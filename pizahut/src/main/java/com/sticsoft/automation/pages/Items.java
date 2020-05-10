package com.sticsoft.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Page;
import com.sticsoft.automation.utils.Utils;
import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.MenuItemNotAvailable;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class Items extends Page {

	private WebElement element;

	@Name("Top Navigation")
	@Timeout(20)
	@FindBy(xpath = "//a[contains(@class,'nav-link')]")
	private List<HtmlElement> topCategories;

	@Name("Top Navigation")
	@Timeout(20)
	@FindBy(id = "pills-tab")
	private HtmlElement mainMenu;

	@Name("Your Cart")
	@Timeout(20)
	@FindBy(className = "cart-items-container")
	private Link cartItems;

	@Name("Add to bag on Custom Page")
	@Timeout(20)
	@FindBy(xpath = "//div[@class='model-item-des-footer']//button")
	public Button addToBagCustomize;

	@Timeout(10)
	@Name("availableMenuItems")
	@FindBy(xpath = "//div[contains(@class,'menu-itm_card')]")
	private List<HtmlElement> menuItems;

	@Timeout(10)
	@Name("Checkout Page")
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	private Button cartCheckout;

	@Timeout(10)
	@Name("Item Size on Popup")
	@FindBy(xpath = "//span[@class='item-size-card' and contains(text(),'REPLACE')]")
	private Button selectSizeOnPopup;

	@Timeout(10)
	@Name("Item Crust on Popup")
	@FindBy(xpath = "//div[contains(@class,'crust-card-item-description')]/h4/span[contains(text(),'REPLACE')]")
	private Button selectCrustOnPopup;

	public static final String URL = Config.getURL() + "/Order/Deals";
	public static final By VERIFY_BY = By.id("pills-tab");

	private List<HashMap> availableMenuItems = new ArrayList<>();
	private List<HashMap> cartDetailsOnItemsPage = new ArrayList<>();

	private WebElement getItemToSelect(String category, String itemName) throws MenuItemNotAvailable {
		try {
			getAvailableMenuItems(category);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index = getIndex(itemName, availableMenuItems);
		if (index == -1) {
			throw new MenuItemNotAvailable("Menu Item is not available");
		}

		return menuItems.get(index);
	}

	private static Integer getIndex(String name, List<HashMap> det) {
		for (HashMap<String, String> map : det) {
			if (map.get("Title").equalsIgnoreCase(name)) {
				return det.indexOf(map);
			}
		}
		return -1;
	}

	/*
	 * This method will return all available items listed under category
	 * 
	 * @param: Category Return: List of Items available under category
	 * 
	 */

	private List<HashMap> getAvailableMenuItems(String category) throws InterruptedException {
		selectCategory(category);
		for (WebElement element : menuItems) {
			HashMap<String, String> itemDetails = new HashMap<String, String>();
			itemDetails.put("Title", element.findElement(By.xpath(".//h3")).getText().strip());
			availableMenuItems.add(itemDetails);
		}
		return availableMenuItems;
	}

	/*
	 * This method will Select Category from Top Navigation
	 * 
	 * @param: category
	 * 
	 */

	private void selectCategory(String category) {
		waitUntil(ExpectedConditions.attributeContains(By.id("Deals"), "aria-selected", "true"), 5);
//		Optional<HtmlElement> mainMenu = topCategories.stream().filter(m -> m.getText().trim().equalsIgnoreCase(category)).findFirst();
//		Assert.assertTrue(category + " is not present", mainMenu.isPresent());
//		mainMenu.get().click();
		mainMenu.findElement(By.xpath(".//a[@id='" + category + "']")).click();

	}

	/*
	 * This method will Select checkout button on cart Page
	 * 
	 */

	public void navigateToCartPage() {
		cartCheckout.click();
	}

	/*
	 * This method will Select Menu Item
	 * 
	 * @param: category
	 * 
	 * @param: menuItem
	 * 
	 * @param: custom
	 * 
	 */

	public void addItemToCart(JSONObject menuItemObject) throws InterruptedException  {
		WebElement item = null;
		try {
			item = getItemToSelect(menuItemObject.get("Category").toString(), menuItemObject.get("Title").toString());
		} catch (MenuItemNotAvailable e) {
			e.printStackTrace();
		}
		String size = (String) menuItemObject.get("Size");
		String crust = (String) menuItemObject.get("Crust");
		if (menuItemObject.get("Toppings") == null) {
			if (size != null) {
				selectSize(item, size);
			}
			if (crust != null) {
				selectCrust(item, crust);
			}
			System.out.println("Continue to add to cart");
		} else {
			String yourTopping = Utils.getToppings(menuItemObject, "Your Toppings");
			String extraTopping = Utils.getToppings(menuItemObject, "Extra Toppings");
			String sauce = Utils.getToppings(menuItemObject, "Sauce");
			customize(item);
		    selectSize(size);
		    selectCrust(crust);
			Thread.sleep(2000);
			updateYourToppings(yourTopping);
			updateExtraToppings(extraTopping);
		}
		addToCart(item);

	}
	
	private static void selectSize(String s)
	{
	    try { 
			driver.findElement(By.xpath("//span[@class='item-size-card' and contains(text(),'" + s + "')]")).click();
		}catch (NoSuchElementException e) {
		    System.out.println("Unable to select "+ s  + " in Customized window");		
		}
	
	}
	
	private static void selectCrust(String c)
	{
	    try { 
			driver.findElement(By.xpath("//span[@class='item-size-card' and contains(text(),'" + c + "')]")).click();
		}catch (NoSuchElementException e) {
		    System.out.println("Unable to select "+ c  + " in Customized window");		
		}
	
	}

	
		
	private void addToCart(WebElement add) {
		try {
			if (addToBagCustomize.isEnabled()) {
				addToBagCustomize.click();
			}
		} catch (NoSuchElementException e) {
			add.findElement(By.xpath(".//button[contains(@class,'btn-green')]")).click();
		}
	}

	
	private void customize(WebElement item) {
		try {
			item.findElement(By.xpath(".//p[@class='btn-customize']")).click();
		} catch (NoSuchElementException e) {
			System.out.println("Failed to Select Customize button");
		}
	}
	
	
	
	private static void updateYourToppings(String yourToppings) {
		String[] yt = yourToppings.split(",");
		try {
			for (String word : yt) {
				driver.findElement(By.xpath("//label[contains(text(),'" + word + "')]")).click();
			}

		} catch (Exception e) {
			System.out.println("Failed to Select your toppings");
		}

	}

	private void updateExtraToppings(String extraToppings) {
		String[] et = extraToppings.split(",");
		try {
			for (String word : et) {
				driver.findElement(
						By.xpath("//label[contains(text(),'" + word + "')]/parent::div/following-sibling::span/i")).click();
			}

		} catch (Exception e) {
			System.out.println("Failed to Select extra toppings");
		}

	}

	private void selectSize(WebElement elem, String size) {
		Select sizeElement = null;
		try {
			sizeElement = new Select(elem.findElement(By.xpath(".//select[contains(@id,'size')]")));
			sizeElement.selectByVisibleText(size);
		} catch (Exception e) {
			System.out.println("Available options to select " + sizeElement.getOptions().stream().map(str -> str.getText()));
		}
	}

	private void selectCrust(WebElement elem, String crust) {
		Select crustElement = null;
		try {
			crustElement = new Select(elem.findElement(By.xpath(".//select[contains(@id,'crust')]")));
			crustElement.selectByVisibleText(crust);
		} catch (Exception e) {
			System.out.println(
					"Available options to select " + crustElement.getOptions().stream().map(str -> str.getText()));
		}
	}

	private String getPrice(WebElement elem) {
		WebElement price = null;
		String ItemPrice = null;
		try {
			price = elem.findElement(By.xpath(".//button[contains(@class,'btn-green')]//span[2]"));
		} catch (Exception e) {
			System.out.println("Failed to get Item price");
		}
		return ItemPrice;
	}

	// Will move to Cart object later

	public HashMap<String, String> getItemsInCart() {
		HashMap<String, String> cartItems = new HashMap<String, String>();
		WebElement cartItemsContainer = driver.findElement(By.className("cart-items-container"));
		List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 'row border-bottom')]"));
		for (WebElement e : items) {

			String itemDetail = e.findElement(By.xpath("//div[contains(@class, 'col-6')]")).getText();
			cartItems.put("Item Title", itemDetail.split("\n")[0]);
			cartItems.put("Size", itemDetail.split("\n")[1].split(",")[0]);
			cartItems.put("Crust", itemDetail.split("\n")[1].split(",")[1]);
			cartItems.put("Price", e.findElement(By.xpath("//p[contains(text(), 'OMR')]")).getText());
		}
		String cartTotal = driver.findElement(By.xpath("//button[contains(text(),'Checkout ')]//span")).getText();
		cartItems.put("cartTotal", cartTotal);
		System.out.println(cartItems);
		return cartItems;
	}

}
