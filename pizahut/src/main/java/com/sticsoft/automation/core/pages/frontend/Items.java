package com.sticsoft.automation.core.pages.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Page;


import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;

public class Items extends Page {
	
	@Name("Top Navigation")
	@Timeout(20)
	@FindBy(id = "pills-tab")
	private Link categories;
	
	@Name("Your Cart")
	@Timeout(20)
	@FindBy(className = "cart-items-container")
	private Link cartItems;
	
	

	@Name("select Menu Item")
	@Timeout(20)
	@FindBy(xpath = "//div[contains(@class,'menu-itm_card')]")
	private Link menuItemsAvailable;
	
	
	@Name("Navigate to Checkout Page")
	@Timeout(20)
	@FindBy(xpath = "//button[contains(text(),'Add To Cart')]")
	public Link checkOut;


	
   public void addItemToCart(String category, String itemName,  List<HashMap> modifiers)
   {
      	      
	   
   }
	
	
	private void selectCategory(String category)
	{
		//System.out.println(categories.getText());
		isPageLoaded();
		isAjaxDone();
		if(category == null || category == "") {
			throw new InvalidArgumentException("Please provide valid inpu to select");
		}
		String locator = "//a[@id='REPLACE']";
		locator = locator.replaceAll("REPLACE", category);
		System.out.println(locator);
	    try {
//		 categories.findElement(By.xpath(locator)).click();
	     driver.findElement(By.xpath(locator)).click();	
		 System.out.println("Selected category");
	    }catch (Exception e) {
	    	System.out.println("Failed to select "+ category );
		}
	    
	}
	
	
	public void selectMenuItem(String category, String menuItem, HashMap<String, String> custom) throws InterruptedException
	{
		selectCategory(category);
//		WebElement menuItemToSelect = driver.findElements(By.xpath("//div[contains(@class,'menu-itm_card')]")).stream().filter(x -> x.findElement(By.xpath("//h3[contains(text(),'CHICKEN SUPREME')]"));
		String menuItemPath = "//h3[contains(text(),'REPLACE')]//parent::div/parent::div";
		menuItemPath = menuItemPath.replace("REPLACE", menuItem);
		System.out.println(menuItemPath);
		WebElement item = driver.findElement(By.xpath(menuItemPath));
		// Customization
		doCustomization(item);
		WebElement el = driver.findElement(By.xpath("//button[contains(text(),'Add To Cart')]"));
		System.out.println(el.getText());
		String price = el.getText().split("OMR")[1].toString();
		el.click();
	
	}
	
	
    public void doCustomization(WebElement element) throws InterruptedException
	{
		HashMap<String, String> customization = new HashMap<String, String>();
		customization.put("Size", "large");
		customization.put("Crust", "Thin N Cripsy");
		customization.put("Toppings", "Beef");
		driver.findElement(By.xpath("//h3[contains(text(),'VERY VEGGIE')]//parent::div/parent::div//p[1]")).click();
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//span[@class='item-size-card' and text()=' Large']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[contains(text(),'Thin N Cripsy')]")).click();
			Thread.sleep(1000);
			WebElement e = driver.findElement(By.xpath("//label[contains(text(),'Pepperoni')]"));
			e.click();
		}catch (Exception e) {
			Boolean flag = driver.findElement(By.xpath("//label[contains(text(),'Pepperoni')]//parent::div/parent::div")).isSelected();
			System.out.println(flag);
		}
			
	}
	
	
    // Will move to Cart object later
    
    public HashMap<String, String> getItemsInCart()
    {
    	HashMap<String, String> cartItems =  new HashMap<String, String>();
    	WebElement cartItemsContainer = driver.findElement(By.className("cart-items-container"));
    	List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 'row border-bottom')]"));
    	for(WebElement e : items)
    	{
    		
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
    
    public void navigateToCartPage()
    {
    	driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();;
    }
	
	
	
	
//	private void availableMenuItems(String category)
//	{
//		List<String> itemsAvailable = new ArrayList<>();
//		HashMap<String, String> itemDetails = new HashMap<String, String>();
//		List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class,'menu-itm_card')]"));
//		int i = 0;
//		for(WebElement element : elements)
//		{
//				itemDetails.put("Title", (element.findElement(By.xpath("//h3"))).getText());
//				itemDetails.put("defaultPrice", (element.findElement(By.xpath("//span[contains(text(),'Add To Cart')]/following-sibling::span"))).getText());
//				itemDetails.put("sizeSelected", (element.findElement(By.id("size_"+i+)
//				itemDetails.put("sizeSelected", (element.findElement(By.id("crust_"+i+"))).getText());	
//				i +=1;
//		}
//		
//		
//		
//	}
//	
	
	
	private WebElement getMenuItemSelected() {
		
		
		
		return null;
	}
	
	
	
	
	
	
		
	
	
	
	
	
	
	

}
