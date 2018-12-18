package com.mas.TestCases;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.helper.DataConfig;
import com.mas.pages.DependentProductPage;

public class TC_DependentProduct {

	
	WebDriver driver;

	DependentProductPage objDependentProduct;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");

		TC_MAS_Login.resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");
		this.driver = BrowserType.launchBrowser();
		this.objDependentProduct = PageFactory.initElements(driver, DependentProductPage.class);
	}

	/*@Test(priority = 2)
	public void test_RelatedProduct() throws Exception {
		Thread.sleep(20000);
        System.out.println("Related products");
		objDependentProduct.verifyRelatedProducts("RelatedProduct");
		Thread.sleep(15000);

	}*/
	/*@Test(priority = 3)
	public void test_IncompatibleProduct() throws Exception {
		Thread.sleep(20000);
		System.out.println("Incompatible products");
		objDependentProduct.VerifyIncompatibleProduct("IncompatibleProduct");
		Thread.sleep(15000);

	}*/
	/*@Test(priority = 4)
	public void test_DependentProduct() throws Exception {
		Thread.sleep(20000);
		System.out.println("Dependent products");
		objDependentProduct.addDependentToolToCart("DependentProduct");
		Thread.sleep(15000);
	}*/
	@Test(priority = 5)
	public void test_DedicatedProduct() throws Exception {
		Thread.sleep(20000);
		System.out.println("AAAM Dependent products");
		objDependentProduct.addDependentToolToCart1("Sheet1");
		Thread.sleep(15000);
	}
	/*@Test(priority = 6)
	public void test_nonDedicatedProduct() throws Exception {
		Thread.sleep(20000);
		System.out.println("AAAM Dependent products");
		objDependentProduct.addnondependendproduct("AAAM Web Monitoring app","AAAM Web Monitoring app");
		Thread.sleep(15000);
	}*/
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(10000);
		//driver.close();
	}}
