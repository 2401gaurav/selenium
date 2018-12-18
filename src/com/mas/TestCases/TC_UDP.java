package com.mas.TestCases;



import org.openqa.selenium.WebDriver;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.mas.pages.UserDefinedPackage;

public class TC_UDP {
	WebDriver driver;

	UserDefinedPackage objUserDefinedPackage;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {

		this.driver = BrowserType.launchBrowser();
		this.objUserDefinedPackage = PageFactory.initElements(driver, UserDefinedPackage.class);
	}

	@Test(priority = 5)
	
	public void test_UserDefinedPackage() throws Exception{
	Thread.sleep(20000);
	objUserDefinedPackage.verifyPackageDisplay();
	Thread.sleep(5000);
	objUserDefinedPackage.verifyUserdefinedPackageDisplay();
	Thread.sleep(5000);
	objUserDefinedPackage.verifyFiltersType("Sheet2");
	Thread.sleep(50000);
	objUserDefinedPackage.verifySelectedProduct("Sheet2");
	Thread.sleep(5000);
	objUserDefinedPackage.verifyBtnAddToCart();
	Thread.sleep(10000);
	objUserDefinedPackage.verifyBtnGoToCart();
	Thread.sleep(5000);
	objUserDefinedPackage.verifyBtnProceedToCheckout();
	Thread.sleep(5000);
	objUserDefinedPackage.verifyBtnProvisionMyPlatform();

	}
}
