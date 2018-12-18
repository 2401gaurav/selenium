package com.mas.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
//import com.mas.pages.ClientEngagementSpecificPage;
import com.mas.pages.DeliveryPatternPage;

public class TC_DeliveryPattern {
	WebDriver driver;

	DeliveryPatternPage objDelivery;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {

		this.driver = BrowserType.launchBrowser();
		this.objDelivery = PageFactory.initElements(driver, DeliveryPatternPage.class);
	}

	
	
	
	
	@Test(priority = 3)
	public void test_DeliveryPatternPage() throws Exception {
		Thread.sleep(20000);
		objDelivery.CheckAdminIcon();
		Thread.sleep(20000);
		objDelivery.navigateDeliveryPattern();
		Thread.sleep(20000);
		objDelivery.CheckingDDPTile();
		Thread.sleep(20000);
		objDelivery.CreateProfileBtn();
		Thread.sleep(20000);
		objDelivery.CreateDeliverypatternProfile();
		Thread.sleep(20000);
		objDelivery.ValidateDeliveryPatternName();
		

		
	}
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(10000);
		// driver.close();
	}}
