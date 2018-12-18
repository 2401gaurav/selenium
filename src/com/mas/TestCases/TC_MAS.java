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
//import com.mas.pages.AdminPackagePage;
import com.mas.pages.ClientEngagementSpecificPage;
import com.mas.pages.ConfigureAutomationPage;
import com.mas.pages.FeedbackPage;
import com.mas.pages.LoginPage;
import com.mas.pages.ManageWorkTabPage;
import com.mas.pages.MySelectionPage;
import com.mas.pages.NewProductPage;
import com.mas.pages.ProductCartPage;
import com.mas.pages.ProjectSelection;
import com.mas.pages.UserDefinedPackage;

/**
 * @author gaurav.b.kapoor
 *
 */
public class TC_MAS {

	public static DataConfig inputDataConfig;
	public static DataConfig resultDataConfig;
	public static FileOutputStream fileOut;
	public static HSSFSheet outputWorkSheet;
	public static int rowNumber;
	public static HSSFWorkbook workBook;
	String AppURL = "https://hpdpvaluewallet.ciostage.accenture.com/project/84037";
	WebDriver driver;
	String landingURL = "https://hpdpvaluewallet.ciostage.accenture.com";
	ProductCartPage objProductCartPage;
	LoginPage objLogin;
	ManageWorkTabPage objManageWorkTabPage;
	Navigations objNavigation;
	ProjectSelection objProjectSelection;
	ConfigureAutomationPage objConfigureAutomationPage;
	MySelectionPage objMySelectionPage;
	ClientEngagementSpecificPage objClientEngagementSpecificPage;
	//AdminPackagePage objAdminPackagePage;
	NewProductPage objNewProductPage;
	FeedbackPage objFeedbackPage;
	UserDefinedPackage objUserDefinedPackage;
	String pageName;
	HashMap<String, String> projectData;
	public static HashMap<String, String> clientEngData;
	public static Object df;
	
	@AfterClass
	public void afterClass() throws Exception {
		workBook.write(fileOut);
		Thread.sleep(50000);
		driver.close();
	}

	@Parameters({ "browserType", "reportpath", "excelPath" })
	@BeforeClass
	public void initialize(String browserType, String reportpath, String excelPath) throws Exception {
		fileOut = new FileOutputStream(
				excelPath+"TC_AutomationStoreResults.xls");
		workBook = new HSSFWorkbook();
		outputWorkSheet = workBook.createSheet("AutomationStoreResult");
		objNavigation = new Navigations(driver);
		inputDataConfig = new DataConfig(excelPath + "AutomationStoreInputData.xlsx");
		resultDataConfig = new DataConfig(reportpath + "TC_AutomationStoreResults.xls");
		resultDataConfig.WritingToExcelResults("TestStep", "Expected Result", 0, workBook, outputWorkSheet, true);
		//projectData = inputDataConfig.fetchInputData("ClientData");
		clientEngData = inputDataConfig.fetchInputData("ClientEngSpecific");
		this.driver = BrowserType.launchBrowser(browserType, AppURL);
		this.objLogin = PageFactory.initElements(driver, LoginPage.class);
		this.objProjectSelection = PageFactory.initElements(driver, ProjectSelection.class);
		this.objManageWorkTabPage = PageFactory.initElements(driver, ManageWorkTabPage.class);
		this.objNavigation = PageFactory.initElements(driver, Navigations.class);
		this.objConfigureAutomationPage = PageFactory.initElements(driver, ConfigureAutomationPage.class);
		this.objProductCartPage = PageFactory.initElements(driver, ProductCartPage.class);
		this.objFeedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
		this.objMySelectionPage = PageFactory.initElements(driver, MySelectionPage.class);
		this.objClientEngagementSpecificPage = PageFactory.initElements(driver, ClientEngagementSpecificPage.class);
		//this.objAdminPackagePage = PageFactory.initElements(driver, AdminPackagePage.class);
        this.objNewProductPage = PageFactory.initElements(driver,NewProductPage.class);
		this.objUserDefinedPackage = PageFactory.initElements(driver, UserDefinedPackage.class);
	}

	@Parameters({ "UserID", "Password" })
	@Test(priority = 0)
	public void LoginToHomePage(String UserID, String Password) throws Exception {
		objLogin.login(UserID, Password);
		//objLogin.validate();
		objProjectSelection.handleSecureIDTokenWindow();
/*		if (objProjectSelection.verifyAnnouncementPopUpExistence() == true) {
			objProjectSelection.closeAnnouncementPopUp();
			Thread.sleep(5000);
		}
		Thread.sleep(25000);
		objProjectSelection.navigateTomyWizard();
		Thread.sleep(15000);
		if (objProjectSelection.verifyCloseManageWorkTourExistence() == true) {
			objProjectSelection.closeManageWorkTour();
			Thread.sleep(5000);
		}
		objManageWorkTabPage.navigateToManageWorkTab();
		Thread.sleep(10000);
		objProjectSelection.selectProject(projectData.get("Client"), projectData.get("Engagement"),
				projectData.get("Project"));
		if (objProjectSelection.verifyCloseManageWorkTourExistence() == true) {
			objProjectSelection.closeManageWorkTour();
			Thread.sleep(10000);
		}
		objManageWorkTabPage.tileNavigation("Automation Store");
		Thread.sleep(6000);*/
		//objProjectSelection.handleSecureIDTokenWindow();
/*		objNavigation.verifyNavigationToSpecificURL(landingURL, pageName, "Automation Store");
		Thread.sleep(60000);*/
	}

	/*@Test(priority = 1)
	public void test_configureAutomationTabPage() throws Exception {

		objConfigureAutomationPage.listView();
		Thread.sleep(15000);
		objConfigureAutomationPage.gridView();
		Thread.sleep(10000);
		objConfigureAutomationPage.verifyCategoryRightLeftButton();
		Thread.sleep(15000);
		objConfigureAutomationPage.masSectionLinks();

	}

	@Test(priority = 2)
	public void test_productCartPage() throws Exception {
		Thread.sleep(10000);
		objConfigureAutomationPage.verifyNavigatetoProductCart();
		Thread.sleep(15000);
		objProductCartPage.verifyProductCartPageColumns();
		Thread.sleep(20000);
		objProductCartPage.verifyLoginInfo();
		Thread.sleep(20000);
		objProductCartPage.verifyContinueShoppingButton();
		Thread.sleep(20000);
		objConfigureAutomationPage.verifyNavigatetoProductCart();
		Thread.sleep(20000);
		objProductCartPage.verifyBackBtn();
	}

	@Test(priority = 3)
	public void test_ddfTools() throws Exception {

		objConfigureAutomationPage.addToolToCart("DirectProvisioning");
		Thread.sleep(20000);
		objConfigureAutomationPage.verifyNavigatetoProductCart();
		Thread.sleep(20000);
		objConfigureAutomationPage.verifyToolInCart("DirectProvisioning");
		objProductCartPage.validateProvisionMyPlatform();
	}

	@Test(priority = 4)
	public void test_emailProvisionTools() throws Exception {

		objConfigureAutomationPage.addToolToCart("EmailProvisioning");
		Thread.sleep(20000);
		objConfigureAutomationPage.verifyNavigatetoProductCart();
		Thread.sleep(20000);
		objConfigureAutomationPage.verifyToolInCart("EmailProvisioning");
	}*/

/*	@Test(priority = 5)
	public void test_mySelectionPage() throws Exception {

		objMySelectionPage.mySelectionDisplay();
		Thread.sleep(5000);
		objMySelectionPage.verifyOrderHeaders();
		objMySelectionPage.verifyProductIcon("DirectProvisioning");
		objMySelectionPage.verifyOrderSubheaders();
		objMySelectionPage.verfiyMySelectionPageFooterSection();
		Thread.sleep(5000);
		objMySelectionPage.verifyBackBtnMySelectionPage();
	}*/
	
	/*@Parameters({ "UserID" })
	@Test(priority = 6)
	public void test_feedBackPage_DDFTools(String UserID) throws InterruptedException, IOException {

		//objFeedbackPage.validateNoDataRatedFilter();
		objFeedbackPage.enterRatingAndFeedback("DirectProvisioning");
		objFeedbackPage.verifyRatingAndFeedback("DirectProvisioning");
		objFeedbackPage.verifyFeedbackRatingFilter();
		objFeedbackPage.verifyToolFeedbackAndRating("DirectProvisioning", UserID);
		objFeedbackPage.enterRatingAndFeedbackAllTools();
		objFeedbackPage.validateNoDataUnRatedFilter();
	}*/
	@Test(priority = 7)
	public void test_clientEngagementSpecificPage() throws Exception{
		objClientEngagementSpecificPage.verifyclientEngagementSpecific_Display();
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyClientEngagent_subheader();
		Thread.sleep(100);
		objClientEngagementSpecificPage.verifySelectCategory(clientEngData.get("Category"),clientEngData.get("Automation type"));
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyEnableClient_Project();
		Thread.sleep(1000);
		objClientEngagementSpecificPage.VerifyApplicableDelivery("ClientEngSpecific");
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyUploadDocument();
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyEnableClient_Project();
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifySaveButton();
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyBackBtnMySelectionPage();
		Thread.sleep(5000);
		objClientEngagementSpecificPage.verifyTilesCreated("ClientEngSpecific");

		

	}
	/*@Test(priority =8)
	public void test_NewProductPage() throws Exception{
		objNewProductPage.verifyNewDisplay();
		Thread.sleep(5000);
	
		objNewProductPage.validateNewProduct();
		
		
	}
*/	
	@Test(priority =9)
	public void test_UserDefinedPackage() throws Exception{
		objUserDefinedPackage.verifyPackageDisplay();
		Thread.sleep(5000);
		objUserDefinedPackage.verifyUserdefinedPackageDisplay();
		Thread.sleep(5000);
		objUserDefinedPackage.verifyFiltersType("Sheet2");
		Thread.sleep(20000);
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
	 @Test(priority = 13)
		public void closeBrowser() throws Exception {
			workBook.write(fileOut);
			//driver.quit();
		}
}
