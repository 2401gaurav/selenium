package com.mas.TestCases;

import java.io.FileOutputStream;
import java.util.HashMap;

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
import com.helper.Navigations;

import com.mas.pages.SolutionInputPage;


public class TC_SolutionInput {

	public static HSSFSheet outputWorkSheet;
	public static int rowNumber;
	public static HSSFWorkbook workBook;
	WebDriver driver;

	SolutionInputPage objSolutionInputPage;

	private HSSFSheet worksheet;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");

		TC_MAS_Login.resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");

		this.driver = BrowserType.launchBrowser();
		this.objSolutionInputPage = PageFactory.initElements(driver, SolutionInputPage.class);

	}

	@Test(priority = 1)
	public void test_SolutionInput() throws Exception {
		Thread.sleep(15000);
		objSolutionInputPage.navigateSolutionPage();

		Thread.sleep(15000);
		objSolutionInputPage.ValidateSolutionInputsPage();
		Thread.sleep(15000);

	}

	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(10000);
		// driver.close();
	}
}
