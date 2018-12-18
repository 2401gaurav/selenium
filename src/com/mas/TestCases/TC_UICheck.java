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
import com.mas.pages.SolutionInputPage;
import com.mas.pages.UICheck;

public class TC_UICheck {

	

	public static HSSFSheet outputWorkSheet;
	public static int rowNumber;
	public static HSSFWorkbook workBook;
	WebDriver driver;
	
	SolutionInputPage objSolutionInputPage;
	UICheck objUICheck;
	private HSSFSheet worksheet;
	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_UICheck.xls");
		workBook = new HSSFWorkbook();
		outputWorkSheet = workBook.createSheet("UICheck_Page");
		TC_MAS_Login.resultDataConfig = new DataConfig(reportpath + "TC_UICheck.xls");
		TC_MAS_Login.resultDataConfig.WritingToExcelResults1("Product", "Score", "Rank", 0, workBook, outputWorkSheet, true);
		this.driver = BrowserType.launchBrowser();
		this.objSolutionInputPage = PageFactory.initElements(driver, SolutionInputPage.class);
	this.objUICheck=PageFactory.initElements(driver, UICheck.class);
	}

	@Test(priority = 0)
	public void test_UICheck() throws Exception {
		Thread.sleep(5000);
		objUICheck.Validate_ProductRating();
	
	
		
	}
	
	@AfterClass
	public void afterClass() throws Exception {
		workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(5000);
		//driver.close();
	}
}

