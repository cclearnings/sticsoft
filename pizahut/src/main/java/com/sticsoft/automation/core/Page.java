package com.sticsoft.automation.core;

import static com.sticsoft.automation.core.Browser.log;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class Page {

	public static WebDriver driver = Browser.getDriver();
	
	private static Page currentPage;
	

	public Page() {
		PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(Browser.getDriver())), this);
		currentPage = this;

	}

	public static void waitUntil(BooleanSupplier condition, int seconds) {
		new WebDriverWait(driver, seconds).until((WebDriver driver) -> condition.getAsBoolean());
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
				waitUntil(() -> Browser.getDriver().getCurrentUrl().startsWith(finalUrl));
			} catch (TimeoutException e) {
				log().log(Level.WARNING, e.getMessage());
				throw new RuntimeException("Not on page " + pageClass.getSimpleName() + " (" + url + ")");
			}
		}

		try {
			waitUntil(() -> isPageLoaded() && isAjaxDone());
		} catch (TimeoutException e) {
			log().log(Level.WARNING, e.getMessage());
			throw new RuntimeException(pageClass.getSimpleName() + " (" + url + ") is taking too long to load.");
		}

		By verifyBy = null;
		try {
			verifyBy = (By) pageClass.getDeclaredField("VERIFY_BY").get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			log().log(Level.WARNING, e.getMessage());
		}
		if (verifyBy != null) {
			try {
				new WebDriverWait(Browser.getDriver(), 30).until(WebDriver::getTitle);
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


	public static void waitUntil(BooleanSupplier condition) {
		waitUntil(condition, 30);
	}

	  private static String getPageClassName(String pageClassName) {
		    System.out.println("This is page Name we are getting "+Page.class.getName());
		    System.out.println("This is page "+pageClassName);
	        return Page.class.getName().replace("com.sticsoft.automation.core.Page", "com.sticsoft.automation.core.pages.frontend." + pageClassName);
	    }
	
	public void toggleTrue(String label) {

	}

	public void toggleFalse(String label) {

	}

	public void enterPOSID(String label) {

	}

	public void enterDefaultPrice(String label) {

	}

	public void selectMenu(String label) {

	}

	public void enterText(String label, String textToEnter) {

	}

	public void selectFromList(String label, String itemToSelect) {

	}

	public WebElement findElement(List<String> list) {
		return null;

	}

	public WebElement findElements(List<String> list) {
		return null;

	}

}
