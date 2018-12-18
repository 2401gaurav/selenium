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
import com.mas.pages.refineBy;

import reusablefunction.reusefunction;

/**
 * @author gaurav.b.kapoor
 *
 */
public class TC_RefineBy {
	public static DataConfig resultDataConfig;
	public static DataConfig df;
	public static DataConfig df1;
	public static FileOutputStream fileOut;
	public static HSSFSheet outputWorkSheet;
	public static int rowNumber;
	
	String AppURL;
	public String browserType;
	WebDriver driver;

	String landingURL;
	
	Navigations objNavigation;
	
	refineBy objrefineBy;
	String pageName;
	HashMap<String, String> projectData;

	

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath)
			throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");

		TC_MAS_Login.resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");
		this.driver = BrowserType.launchBrowser();
		this.objrefineBy = PageFactory.initElements(driver, refineBy.class);
		}
	@Test(priority = 6)
	public void test_RefineBy() throws Exception {
		Thread.sleep(10000);
		objrefineBy.validateRefineBy();
		Thread.sleep(15000);
	}
		@AfterClass
		public void afterClass() throws Exception {
			TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
			Thread.sleep(10000);
			//driver.close();
		}}