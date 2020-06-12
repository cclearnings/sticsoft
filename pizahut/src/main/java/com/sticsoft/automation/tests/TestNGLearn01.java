package com.sticsoft.automation.tests;

import org.junit.AfterClass;
import org.testng.annotations.*;

public class TestNGLearn01 {

	@BeforeMethod
	public void openBrowser() {
	   System.out.println("Before method in 01 class");
	}

	@AfterMethod
	public void exitBrowser() {
		System.out.println("After method in 01 class");  
	}
	

	@BeforeClass
	public void openBrow() {
	   System.out.println("Before class in 01 class");
	}

	@AfterClass
	public void exitBrow() {
		System.out.println("After class in 01 class");  
	}


	@BeforeTest
	public void opent() {
	   System.out.println("Before test in 01 class");
	}

	@AfterTest
	public void exitt() {
		System.out.println("After test in 01 class");  
	}

	@BeforeSuite(groups={"Sanity"})
	public void opwent() {
	   System.out.println("Before Suite in 01 class");
	}

	@AfterSuite(groups={"Sanity"})
	public void exiwtt() {
		System.out.println("After Suite in 01 class");  
	}


	@BeforeGroups
	public void opewent() {
	   System.out.println("Before groups in 01 class");
	}

	@AfterGroups
	public void exiewtt() {
		System.out.println("After groups in 01 class");  
	}

    @Test(groups={"Sanity"})
    public void itstest()
    {
    	System.out.println("We are in Test 01 Class, group Sanity");
    }
	
    @Test(groups={"Place Order"})
    public void itstest2()
    {
    	System.out.println("We are in Test 01-01 Class group Place Order");
    }
	
    @Test(groups={"Regression"})
    public void itstest22()
    {
    	System.out.println("We are in Test 01-02 Class group Regression");
    }
	
	
	
	
	
}
