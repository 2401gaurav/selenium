package com.mas.TestCases;

import java.io.FileOutputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
//import com.mas.pages.ClientEngagementSpecificPage;
import com.mas.pages.NewProductPage;
import com.mas.pages.ToolsDetailsPage;

public class TC_NewProduct {
	WebDriver driver;

	NewProductPage objNewProduct;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");
		this.driver = BrowserType.launchBrowser();
		this.objNewProduct = PageFactory.initElements(driver, NewProductPage.class);
	}

	@Test(priority = 5)
	private void test_NewProductPage() throws Exception{
		Thread.sleep(50000);
		objNewProduct.verifyNewDisplay();
		Thread.sleep(5000);
		objNewProduct.validateNewProduct();
		

	}
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(10000);
		//driver.close();
	}}
