package com.mas.TestCases;

import java.io.FileOutputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.helper.DataConfig;
import com.mas.pages.ToolsDetailsPage;


/**
 * @author gaurav.b.kapoor
 *
 */
public class TC_ToolsDetails {
	public static FileOutputStream fileOut;
	WebDriver driver;

	ToolsDetailsPage objtoolsDetails;

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		TC_MAS_Login.fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");

		TC_MAS_Login.resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");
		
		this.driver = BrowserType.launchBrowser();
		this.objtoolsDetails = PageFactory.initElements(driver, ToolsDetailsPage.class);
	}

	@Test(priority = 1)
	public void test_ToolDetails() throws Exception {
		Thread.sleep(10000);
		objtoolsDetails.verifytoolsdetails();
		Thread.sleep(15000);

	}
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(10000);
		//driver.close();
	}}