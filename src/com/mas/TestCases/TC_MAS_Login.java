package com.mas.TestCases;

import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.helper.DataConfig;
import com.helper.Navigations;
import com.mas.pages.LoginPage;
import com.mas.pages.ManageWorkTabPage;
import com.mas.pages.ProductCartPage;
import com.mas.pages.ProjectSelection;

/**
 * @author aswin.r.j
 *
 */
public class TC_MAS_Login {

	public static DataConfig inputDataConfig;
	public static DataConfig resultDataConfig;
	public static FileOutputStream fileOut;
	public static HSSFSheet outputWorkSheet;
	public static int rowNumber;
	public static HSSFWorkbook workBook;
	String AppURL = "https://mywizardautostore.accenture.com/project/54812";
	WebDriver driver;
	String landingURL = "https://hpdpvaluewallet.ciostage.accenture.com";
	ProductCartPage objProductCartPage;
	LoginPage objLogin;
	ManageWorkTabPage objManageWorkTabPage;
	Navigations objNavigation;
	ProjectSelection objProjectSelection;
	String pageName;
	HashMap<String, String> projectData;
	HashMap<String, String> directfullfillmentData;
	public static HashMap<String, String> clientEngData;
	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		fileOut = new FileOutputStream(
				"C:\\workspace\\AutomationStore\\src\\com\\TestResults\\TC_AutomationStoreResults.xls");
		workBook = new HSSFWorkbook();
		outputWorkSheet = workBook.createSheet("AutomationStoreResult");
		objNavigation = new Navigations(driver);
		inputDataConfig = new DataConfig(excelPath + "AutomationStoreInputData.xlsx");
		resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");
		resultDataConfig.WritingToExcelResults("TestStep", "Expected Result", 0, workBook, outputWorkSheet, true);
		projectData = inputDataConfig.fetchInputData("ClientData");
		//clientEngData = resultDataConfig.fetchInputData("ClientEngSpecific"); 
		this.driver = BrowserType.launchBrowser(browserType, AppURL);
		this.objLogin = PageFactory.initElements(driver, LoginPage.class);
		this.objProjectSelection = PageFactory.initElements(driver, ProjectSelection.class);
		this.objManageWorkTabPage = PageFactory.initElements(driver, ManageWorkTabPage.class);
		this.objNavigation = PageFactory.initElements(driver, Navigations.class);
	}

	@Parameters({ "UserID", "Password" })
	@Test(priority = 0)
	public void LoginToHomePage(String UserID, String Password) throws Exception {
		objLogin.login(UserID, Password);
		if(driver.getCurrentUrl().contains("https://federation-sts.accenture.com")){
			objProjectSelection.handleSecureIDTokenWindow();
		}
		Thread.sleep(20000);
/*		if (objProjectSelection.verifyAnnouncementPopUpExistence() == true) {
			objProjectSelection.closeAnnouncementPopUp();
			Thread.sleep(5000);
		}
		objProjectSelection.navigateTomyWizard();
		Thread.sleep(15000);
		if (objProjectSelection.verifyCloseManageWorkTourExistence() == true) {
			objProjectSelection.closeManageWorkTour();		
		}
		Thread.sleep(20000);
		objManageWorkTabPage.navigateToManageWorkTab();
		Thread.sleep(10000);
		objProjectSelection.selectProject(projectData.get("Client"), projectData.get("Engagement"),
				projectData.get("Project"));
		Thread.sleep(10000);
		if (objProjectSelection.verifyCloseManageWorkTourExistence() == true) {
			objProjectSelection.closeManageWorkTour();
		}
		Thread.sleep(10000);
		objManageWorkTabPage.navigateToManageWorkTab();
		Thread.sleep(2000);
		objManageWorkTabPage.tileNavigation("Automation Store");
		if(driver.getCurrentUrl().contains("https://federation-sts.accenture.com")){
			objProjectSelection.handleSecureIDTokenWindow();
		}
		Thread.sleep(25000);
		objNavigation.verifyNavigationToSpecificURL(landingURL, pageName, "Automation Store");
		Thread.sleep(30000);*/
	}
	
}
