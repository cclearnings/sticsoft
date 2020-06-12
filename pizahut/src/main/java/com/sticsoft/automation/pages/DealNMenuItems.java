package com.sticsoft.automation.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Page;
import com.sticsoft.automation.utils.Utils;
import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Config;
import com.sticsoft.automation.core.MenuItemNotAvailable;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class DealNMenuItems extends Page {

	private WebElement element;

	@Name("Top Navigation")
	@Timeout(20)
	@FindBy(xpath = "//a[contains(@class,'nav-link')]")
	private List<HtmlElement> topCategories;

	@Name("Top Navigation")
	@Timeout(10)
	@FindBy(id = "pills-tab")
	private HtmlElement mainMenu;

	@Name("Your Cart")
	@Timeout(10)
	@FindBy(className = "cart-items-container")
	private Link cartItems;

	@Name("Add to bag on Custom Page")
	@Timeout(10)
	@FindBy(xpath = "//div[@class='model-item-des-footer']//button")
	public Button addToBagCustomize;

	@Name("Add to bag on Page")
	@Timeout(10)
	@FindBy(xpath = ".//button[contains(@class,'btn-green')]")
	public Button addToBag;

	@Timeout(10)
	@Name("available Menu Items")
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

	@Timeout(10)
	@Name("cart Item titles")
	@FindBy(xpath = "//div[@class='selected-item-left']/p")
	private List<HtmlElement> cartItemsTitles;

	@Timeout(10)
	@Name("cart Item quantity")
	@FindBy(xpath = "//div[@class='selected-item-middle']//span[2]/a")
	private List<HtmlElement> cartItemsQuantity;

	@Timeout(10)
	@Name("Price of items in Cart")
	@FindBy(xpath = "//div[@class='selected-item-right']/p")
	private List<HtmlElement> cartPriceOfItems;

	@Timeout(10)
	@Name("Cart Container")
	@FindBy(id = "cartsectionresp")
	private HtmlElement cartContainer;

	@Timeout(10)
	@Name("Cart Items")
	@FindBy(xpath = ".//div[contains(@class,'border-bottom')]")
	private List<HtmlElement> addedItems;

	@Timeout(10)
	@Name("Order Totals")
	@FindBy(xpath = "//div[@class='tax-info-container']")
	private List<HtmlElement> orderTotals;

	@Timeout(10)
	@Name("Included Toppings")
	@FindBy(xpath = "//span[contains(text(),'Included Toppings')]")
	private HtmlElement IncludeToppingsToggle;

	@Timeout(10)
	@Name("Sauce Toggle")
	@FindBy(xpath = "//span[contains(text(),'Sauce')]")
	private HtmlElement sauceToggle;
	
	
	@Timeout(10)
	@Name("BUtton Select")
	@FindBy(xpath = "//button[contains(text(),'Add To Cart')]")
	private HtmlElement addToCart;
	
	
	@Timeout(10)
	@Name("Button Select")
	@FindBy(id = "pills-tab")
	private static WebElement VERIFY_BY;
	
	private static String URL = Config.getURL() + "/Order/Deals";

	public List<HashMap> availableMenuItems = new ArrayList<>();
	public static List<HashMap> cartDetailsOnItemsPage = new ArrayList<>();
	public static HashMap<String, String> orderTotalInItemsPage = new HashMap<String, String>();

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
		availableMenuItems = new ArrayList<>();
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
			itemDetails.put("Title",
					element.findElement(By.xpath(".//h3[contains(@class,'card-title')]")).getText().strip());
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

	public void navigateToCartPage() throws InterruptedException {
//		getItemsInCart();
//		getOrderTotals();
		cartCheckout.click();
		waitUntil(ExpectedConditions.invisibilityOf(cartCheckout), 5);
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

	public void addItemToCart(JSONObject menuItemObject) throws InterruptedException {
		WebElement item = null;
		try {
			item = getItemToSelect(menuItemObject.get("Category").toString(), menuItemObject.get("Title").toString());
		} catch (MenuItemNotAvailable e) {
			System.out.println();
			e.printStackTrace();

		}
		if (!menuItemObject.get("Category").toString().equalsIgnoreCase("deals")) {
			String size = (String) menuItemObject.get("Size");
			String crust = (String) menuItemObject.get("Crust");
			if (menuItemObject.get("Toppings") == null) {
				if (size != null) {
					selectSize(item, size);
				}
				if (crust != null) {
					selectCrust(item, crust);
				}
				addToBag.click();

			} else {
				String yourTopping = Utils.getToppings(menuItemObject, "Your Toppings");
				String extraTopping = Utils.getToppings(menuItemObject, "Extra Toppings");
				String sauce = Utils.getToppings(menuItemObject, "Sauce");
				customize(item);
				selectSize(size);
				selectCrust(crust);
				if (yourTopping != null) {
					updateYourToppings(yourTopping);
				}
				if (extraTopping != null) {
					updateExtraToppings(extraTopping);
				}
				if (sauce != null) {
					selectSauce(sauce);
				}
				addToBagCustomize.click();
			}
		} else {
			item.click();
			addDealItem(item);
		}
	}

	
	public void addDealItem(WebElement dealContainer) throws InterruptedException {
		String path = "//div[starts-with(@id,'addButton')]";
		do {
			Thread.sleep(500);
			driver.findElements(By.xpath(path)).get(0).click();
			Thread.sleep(500);
			addToBagRandomly();
			Thread.sleep(500);
		}while(driver.findElements(By.xpath(path)).size()>0);
		Thread.sleep(200);
		driver.findElement(By.name("button")).click();
				
	}

	public void addToBagRandomly() throws InterruptedException {
		List<WebElement> dealItems = driver.findElements(By.xpath("//div[contains(@class,'menu-itm_card')]"));
		Random rand = new Random(); 
		int index = rand.nextInt(dealItems.size());
		index = index == 0? 1: index;
        dealItems.get(index).findElement(By.xpath(".//span[contains(text(),'Add To Deal')]")).click();
        Thread.sleep(500);
	}
	
	
	private void selectSauce(String sauce) {
		sauceToggle.click();
		String path = "//label[contains(text(),'" + sauce + "')]";
		try {
			driver.findElement(By.xpath(path)).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		sauceToggle.click();
	}

	private static void selectSize(String s) throws InterruptedException {
		try {
			waitAndClick(By.xpath("//span[@class='item-size-card' and contains(text(),'" + s + "')]"), 5);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to select " + s + " in Customized window");
		}
		Thread.sleep(500);

	}

	private static void selectCrust(String c) throws InterruptedException {
		try {
			waitAndClick(By.xpath("//span[contains(text(),'" + c.trim() + "')]"), 5);
		} catch (NoSuchElementException e) {
			System.out.println("Unable to select " + c + " in Customized window");
		}
		Thread.sleep(500);
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

	private void updateYourToppings(String yourToppings) {
		IncludeToppingsToggle.click();
		String[] yt = yourToppings.split(",");
		for (String word : yt) {
			try {
				waitAndClick(By.xpath("//label[contains(text(),'" + word + "')]"), 3);
			} catch (Exception e) {
				Browser.execJavascript("arguments[0].click();",
						driver.findElement(By.xpath("//label[contains(text(),'" + word.trim() + "')]")));
				System.out.println("Added "+ word + " Using Javascript");
			}

		}
		IncludeToppingsToggle.click();
	}

	private void updateExtraToppings(String extraToppings) {
		String[] et = extraToppings.split(",");
		for (String word : et) {
			System.out.println(word);
			try {
				waitAndClick(By.xpath(
						"//label[contains(text(),'" + word.trim() + "')]/parent::div/following-sibling::span/i"), 3);
				Thread.sleep(1000);
			} catch (Exception e) {
				Browser.execJavascript("arguments[0].click();", driver.findElement(By.xpath(
						"//label[contains(text(),'" + word.trim() + "')]/parent::div/following-sibling::span/i")));
				System.out.println("Updated "+ word + " Using Javascript");
			}

		}
	}

	private void selectSize(WebElement elem, String size) {
		Select sizeElement = null;
		try {
			sizeElement = new Select(elem.findElement(By.xpath(".//select[contains(@id,'size')]")));
			sizeElement.selectByVisibleText(size);
		} catch (Exception e) {
			System.out.println(
					"Available options to select " + sizeElement.getOptions().stream().map(str -> str.getText()));
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

	// Will move to Cart object later

	public void getItemsInCart() {
		List<WebElement> itemsInCart = cartContainer.findElements(By.xpath(".//div[contains(@class,'border-bottom')]"));
		for (WebElement e : itemsInCart) {
			HashMap<String, String> cartItems = new HashMap<String, String>();
			cartItems.put("Item Title",
					e.findElement(By.xpath(".//div[@class='selected-item-left']/p")).getText().strip());
			String sizePath = ".//div[@class='selected-item-left']/p/following-sibling::small[1]";
			String crustPath = ".//div[@class='selected-item-left']/p/following-sibling::small[2]";
			if (e.findElements(By.xpath(sizePath)).size() > 0) {
				cartItems.put("Size", e.findElement(By.xpath(sizePath)).getText().strip().replace(",", ""));
			}
			if (e.findElements(By.xpath(crustPath)).size() > 0) {
				cartItems.put("Crust", e.findElement(By.xpath(crustPath)).getText().strip());
			}
			cartItems.put("Quantity", e.findElement(By.xpath(".//div[@class='selected-item-middle']//span[2]/a"))
					.getText().toString().strip());
			cartItems.put("Price", e.findElement(By.xpath(".//div[@class='selected-item-right']/p")).getText().strip());
			String path = ".//p[@dir='ltr']";
			if (e.findElements(By.xpath(path)).size() > 0) {
				for (WebElement el : e.findElements(By.xpath(path))) {
					String[] str = el.getText().split(":");
					cartItems.put(str[0], str[1]);
				}
			}
			if(e.findElements(By.xpath(".//div[@class='menu-itmText']")).size()>0)
			{
				String value = "";
		
				for(WebElement ele : e.findElements(By.xpath(".//div[@class='menu-itmText']/span")))
					{
					 value += ele.getText().strip()+",";
					}
				 
				cartItems.put("dealItems", value);
			}
			cartDetailsOnItemsPage.add(cartItems);
		}
	}

	public void getOrderTotals() {
		for (WebElement e : orderTotals) {
			String key = e.findElement(By.xpath(".//h2[1]")).getText().strip();
			String value = e.findElement(By.xpath(".//h2[2]")).getText().strip();
			orderTotalInItemsPage.put(key, value);
		}
	}

	}
