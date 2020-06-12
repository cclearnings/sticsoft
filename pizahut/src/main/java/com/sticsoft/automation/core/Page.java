package com.sticsoft.automation.core;

import static com.sticsoft.automation.core.Browser.log;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class Page {

	public static WebDriver driver = Browser.getDriver();

	private static Page currentPage;
	private static String city;
	private static String area;
	private static String orderID;
	private static String customerName;
	private static String orderChannel;
	private static String mobileNumber;
	private static String paymentMode;
	private static HashMap<String, String> address;
	private static List<HashMap<String, String>> itemsOrdered;

	public Page() {
		PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(Browser.getDriver())), this);
		currentPage = this;
	}

	public static Page getCurrentPage() {
		return currentPage;
	}

	public static void waitUntil(ExpectedCondition<Boolean> expectedCondition, int seconds) {
		new WebDriverWait(driver, seconds);
	}

	public static void waitUntil(Boolean condition, int seconds) {
		new WebDriverWait(driver, seconds);
	}

	public static boolean isPageLoaded() {
		String state = (String) Browser.execJavascript("return document.readyState;");
		return state.matches("complete|loaded|interactive");
	}

	public static boolean isAjaxDone() {
		Object jsResponse = Browser.execJavascript("return jQuery.active;");
		if (jsResponse instanceof Long) {
			return ((Long) jsResponse) == 0;
		} else if (jsResponse instanceof String) {
			String response = (String) jsResponse;
			return (response.startsWith("{\"hCode\"") || response.isEmpty());
		} else {
			return true;
		}
	}

	public static void waitUntil(Boolean condition) {
		waitUntil(condition, 10);
	}

	public static void waitAndClick(By by, int sec) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
			try {
			element.click();
			}catch (ElementClickInterceptedException en) {
				driver.findElement(by).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCity() {
		return Page.city;
	}

	public static void setCity(String city) {
		Page.city = city;
	}

	public static String getArea() {
		return Page.area;
	}

	public static void setArea(String area) {
		Page.area = area;
	}

	public static String getCustomerName() {
		return Page.customerName;
	}

	public static void setCustomerName(String customerName) {
		Page.customerName = customerName;
	}

	public static String getOrderChannel() {
		return Page.orderChannel;
	}

	public static void setOrderChannel(String channel) {
		Page.orderChannel = channel;
	}

	public static String getMobileNumber() {
		return Page.mobileNumber;
	}

	public static void setMobileNumber(String mobile) {
		Page.mobileNumber = mobile;
	}

	public static String getOrderID() {
		return Page.orderID;
	}

	public static void setOrderID(String orderid) {
		Page.orderID = orderid;
	}

	public static String getPaymentMode() {
		return Page.paymentMode;
	}

	public static void setPaymentMode(String payment) {
		Page.paymentMode = payment;
	}

	public static HashMap<String, String> getAddress() {
		return Page.address;
	}

	public static void setAddress(HashMap<String, String> map) {
		Page.address = map;
	}

	public static List<HashMap<String, String>> getItemsOrdered() {
		return Page.itemsOrdered;
	}

	public static void setItemsOrdered(List<HashMap<String, String>> listMap) {
		Page.itemsOrdered = listMap;
	}

	public static void verifyPage(Class pageClass) {
		String url = null;
		try {
			url = (String) pageClass.getDeclaredField("URL").get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			log().log(Level.WARNING, e.getMessage());
		}
		if (url != null) {
			String finalUrl = url;
			try {
				waitUntil(Browser.getDriver().getCurrentUrl().startsWith(finalUrl),5);
//				Assert.assertTrue(Browser.getDriver().getCurrentUrl().startsWith(finalUrl));
			} catch (TimeoutException e) {
				throw new RuntimeException("Not on page " + pageClass.getSimpleName() + " (" + url + ")");
			}
			
		}
		
		try {
			waitUntil(isPageLoaded() && isAjaxDone());
		} catch (TimeoutException e) {
			log().log(Level.WARNING, e.getMessage());
			throw new RuntimeException(pageClass.getSimpleName() + " (" + url + ") is taking too long to load.");
		}

		WebElement verifyBy = null;
		try {
			verifyBy = (WebElement) pageClass.getDeclaredField("VERIFY_BY").get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			log().log(Level.WARNING, e.getMessage());
		}
		if (verifyBy != null) {
			try {
				new WebDriverWait(Browser.getDriver(), 10).until(ExpectedConditions.visibilityOf(verifyBy));
			} catch (TimeoutException e) {
				log().log(Level.WARNING, e.getMessage());
				throw new RuntimeException(
						pageClass.getSimpleName() + " VERIFY_BY (" + verifyBy.toString() + ") is not displayed.");
			}
		}
		log().info("On Page: " + pageClass.getSimpleName());
	}

	public static Page onPage(String pageClassName) {
		pageClassName = getPageClassName(pageClassName);
		try {
			return onPage(Class.forName(pageClassName));
		} catch (ClassNotFoundException e) {
			log().log(Level.WARNING, e.getMessage());
			throw new RuntimeException(pageClassName + " not found!");
		}
	}

	public static Page onPage(Class pageClass) {
		verifyPage(pageClass);
		try {
			pageClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log().log(Level.WARNING, e.getMessage());
			throw new RuntimeException("Instantiation failed for " + pageClass.getName());
		}
		return currentPage;
	}

	private static String getPageClassName(String pageClassName) {
		return Page.class.getName().replace("com.sticsoft.automation.Page",
				"com.sticsoft.automation.pages." + pageClassName);
	}

//	
//	
//	public static void verifyPage(Class pageClass) {
//		String url = null;
//		try {
//			url = (String) pageClass.getDeclaredField("URL").get(null);
//		} catch (NoSuchFieldException | IllegalAccessException e) {
//			log().log(Level.WARNING, e.getMessage());
//		}
//		if (url != null) {
//			String finalUrl = url;
//			try {
//				waitUntil(Browser.getDriver().getCurrentUrl().startsWith(finalUrl));
//			} catch (TimeoutException e) {
//				log().log(Level.WARNING, e.getMessage());
//				throw new RuntimeException("Not on page " + pageClass.getSimpleName() + " (" + url + ")");
//			}
//		}
//
//		try {
//			waitUntil(isPageLoaded() && isAjaxDone());
//		} catch (TimeoutException e) {
//			log().log(Level.WARNING, e.getMessage());
//			throw new RuntimeException(pageClass.getSimpleName() + " (" + url + ") is taking too long to load.");
//		}
//
//		By verifyBy = null;
//		try {
//			verifyBy = (By) pageClass.getDeclaredField("VERIFY_BY").get(null);
//		} catch (NoSuchFieldException | IllegalAccessException e) {
//			log().log(Level.WARNING, e.getMessage());
//		}
//		if (verifyBy != null) {
//			try {
//				new WebDriverWait(Browser.getDriver(), 30).until(WebDriver::getTitle);
//			} catch (TimeoutException e) {
//				log().log(Level.WARNING, e.getMessage());
//				throw new RuntimeException(
//						pageClass.getSimpleName() + " VERIFY_BY (" + verifyBy.toString() + ") is not displayed.");
//			}
//		}
//		log().info("On Page: " + pageClass.getSimpleName());
//	}
//
//	public static Page onPage(String pageClassName) {
//		pageClassName = getPageClassName(pageClassName);
//		try {
//			return onPage(Class.forName(pageClassName));
//		} catch (ClassNotFoundException e) {
//			log().log(Level.WARNING, e.getMessage());
//			throw new RuntimeException(pageClassName + " not found!");
//		}
//	}
//
//	public static Page onPage(Class pageClass) {
//		verifyPage(pageClass);
//		try {
//			pageClass.newInstance();
//		} catch (InstantiationException | IllegalAccessException e) {
//			log().log(Level.WARNING, e.getMessage());
//			throw new RuntimeException("Instantiation failed for " + pageClass.getName());
//		}
//		return currentPage;
//	}

}
