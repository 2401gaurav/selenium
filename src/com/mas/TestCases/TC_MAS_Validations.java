package com.mas.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.mas.pages.ConfigureAutomationPage;
import com.mas.pages.MySelectionPage;
import com.mas.pages.ProductCartPage;
import com.mas.pages.ProjectSelection;

/**
 * @author aswin.r.j
 *
 */
public class TC_MAS_Validations {

	WebDriver driver;
	ProductCartPage objProductCartPage;
	ProjectSelection objProjectSelection;
	ConfigureAutomationPage objConfigureAutomationPage;
	MySelectionPage objMySelectionPage;
	
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
	}

	@BeforeClass
	public void initialize() throws Exception {
		this.driver = BrowserType.launchBrowser();
		this.objConfigureAutomationPage = PageFactory.initElements(driver, ConfigureAutomationPage.class);
		this.objProductCartPage = PageFactory.initElements(driver, ProductCartPage.class);
		this.objMySelectionPage = PageFactory.initElements(driver, MySelectionPage.class);
	}


	@Test(priority = 1)
	public void test_configureAutomationTabPage() throws Exception {

		objConfigureAutomationPage.listView("Recommendation Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.gridView("Recommendation Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.listViewContents("Recommendation Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.gridViewContents("Recommendation Page");
		Thread.sleep(1000);

		// navigate to New and check grid and list view
		objConfigureAutomationPage.navigateNewTab();
		Thread.sleep(1000);
		objConfigureAutomationPage.listView("New Page");
		objConfigureAutomationPage.gridView("New Page");
		objConfigureAutomationPage.listViewContents("New Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.gridViewContents("New Page");

		// navigate to Package and check grid and list view
		objConfigureAutomationPage.navigatePackageTab();
		Thread.sleep(1000);
		objConfigureAutomationPage.listView("Packages Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.gridView("Packages Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.listViewContents("Packages Page");
		Thread.sleep(1000);
		objConfigureAutomationPage.gridViewContents("Packages Page");
		Thread.sleep(1000);

		// navigate to All section and check only list view is enabled and
		// contents are in list view
		objConfigureAutomationPage.navigateAllTab();
		objConfigureAutomationPage.listViewAllTab("All Page");
		objConfigureAutomationPage.listViewContentsALL("All Page");
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
	public void test_mySelectionPage() throws Exception {

		objMySelectionPage.mySelectionDisplay();
		Thread.sleep(5000);
		objMySelectionPage.verifyOrderHeaders();
		objMySelectionPage.verifyProductIcon("FeedbackRating");
		objMySelectionPage.verifyOrderSubheaders();
		objMySelectionPage.verfiyMySelectionPageFooterSection();
		Thread.sleep(5000);
		objMySelectionPage.verifyBackBtnMySelectionPage();
	}

	
}
