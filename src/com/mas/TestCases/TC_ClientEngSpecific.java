package com.mas.TestCases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.mas.pages.ClientEngagementSpecificPage;


public class TC_ClientEngSpecific {
	WebDriver driver;
	public static HashMap<String, String> clientEngData; 

	ClientEngagementSpecificPage objClientEngagementSpecific;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {

		this.driver = BrowserType.launchBrowser();
		this.objClientEngagementSpecific = PageFactory.initElements(driver, ClientEngagementSpecificPage.class);
	}

	@Test(priority = 4)
	private void test_ClientEngagmentSpecificPage() throws Exception{
		objClientEngagementSpecific.verifyclientEngagementSpecific_Display();
		Thread.sleep(5000);
		objClientEngagementSpecific.verifyClientEngagent_subheader();
		Thread.sleep(5000);
		objClientEngagementSpecific.VerifyApplicableDelivery("ClientEngSpecific");
		Thread.sleep(5000);
		objClientEngagementSpecific.verifySelectCategory("strCategory", "strAutomation");
		Thread.sleep(5000);
		objClientEngagementSpecific.verifyEnableClient_Project();
		Thread.sleep(5000);
		//objClientEngagmentSpecificPage.verifyUploadDocument();
		Thread.sleep(5000);
		objClientEngagementSpecific.verifySaveButton();
		Thread.sleep(5000);
		objClientEngagementSpecific.verifyBackBtnMySelectionPage();
		Thread.sleep(10000);
		objClientEngagementSpecific.verifyTilesCreated("ClientEngSpecific");

	}
	

}