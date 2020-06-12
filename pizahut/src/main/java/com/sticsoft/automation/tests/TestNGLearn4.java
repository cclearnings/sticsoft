package com.sticsoft.automation.tests;

import org.junit.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGLearn4 {
	
	
	@BeforeMethod
	public void openBrowser() {
	   System.out.println("Before method in 04 class");
	}

	@AfterMethod
	public void exitBrowser() {
		System.out.println("After method in 04 class");  
	}
	

	@BeforeClass
	public void openBrow() {
	   System.out.println("Before class in 04 class");
	}

	@AfterClass
	public void exitBrow() {
		System.out.println("After class in 04 class");  
	}


	@BeforeTest
	public void opent() {
	   System.out.println("Before test in 04 class");
	}

	@AfterTest
	public void exitt() {
		System.out.println("After test in 04 class");  
	}

	@BeforeSuite
	public void opwent() {
	   System.out.println("Before Suite in 04 class");
	}

	@AfterSuite
	public void exiwtt() {
		System.out.println("After Suite in 04 class");  
	}


	@BeforeGroups
	public void opewent() {
	   System.out.println("Before groups in 04 class");
	}

	@AfterGroups
	public void exiewtt() {
		System.out.println("After groups in 04 class");  
	}

	@Test(groups = { "Sanity" })
	public void itstest() {
		System.out.println("We are in Test 04 Class, group Sanity");
	}

	@Test(groups = { "Place Order" })
	public void itstest2() {
		System.out.println("We are in Test 04-01 Class group Place Order");
	}

	@Test(groups = { "Regression" })
	public void itstest22() {
		System.out.println("We are in Test 04-02 Class group Regression");
	}


}
