package com.sticsoft.automation.tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.sticsoft.automation.core.Browser;
import com.sticsoft.automation.core.Config;

public class Admin {

	WebDriver browser = Browser.getDriver();
	
	
	@Test
	public void login() {
		browser.findElement(By.id("Email")).sendKeys("chandra@pizza.com");
		browser.findElement(By.id("password")).sendKeys("Stic@123$");
		browser.findElement(By.xpath("//button[text()='Login']")).click();
	}

	@Test
	public void loged() {
		System.out.println("Logged In");
	}

	@Test(groups = { "Sanity" })
	public void logisdn() {

		System.out.println("Login done");
		System.out.println("Smoke Scenario passed");
	}

	@Test(groups = { "Regression" })
	public void register() {
		System.out.println("Registration done");
	}

}
