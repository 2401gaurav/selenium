package com.mas.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.mas.pages.ConfigPendingPage;
import com.mas.pages.ConfigureAutomationPage;
import com.mas.pages.MySelectionPage;
import com.mas.pages.ProductCartPage;
/**
 * @author aswin.r.j
 *
 */
public class TC_MAS_Provisioning {


	WebDriver driver;
	ConfigureAutomationPage objConfigureAutomationPage;
	ProductCartPage objProductCartPage;
	MySelectionPage objMySelectionPage;
	ConfigPendingPage objConfigPendingPage;

	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
		Thread.sleep(2000);
		//driver.close();
	}

	@BeforeClass
	public void initialize() throws Exception {
		this.driver = BrowserType.launchBrowser();
		this.objConfigureAutomationPage = PageFactory.initElements(driver, ConfigureAutomationPage.class);
		this.objProductCartPage = PageFactory.initElements(driver, ProductCartPage.class);
		this.objMySelectionPage = PageFactory.initElements(driver, MySelectionPage.class);
		this.objConfigPendingPage = PageFactory.initElements(driver, ConfigPendingPage.class);
	}


	@Test(priority = 5)
	public void test_ddfTools() throws Exception {
		
	//	objConfigureAutomationPage.addToolToCartDFF("Metal_Extract");
		objConfigureAutomationPage.addToolToCartDFF("Chemical_Extract");
	//	objConfigureAutomationPage.addToolToCartDFF("IBM_Platform");
	}
	
	/*@Parameters({ "UserID" })
	@Test(priority = 6)
	public void test_emailProvTools(String UserID) throws Exception {
		
		objConfigureAutomationPage.addToolToCartEmailAccMgmt("Email_AccMgmt", UserID );
		objConfigureAutomationPage.addToolToCartEmailClientMgmt("Email_TFS", UserID);
		objConfigureAutomationPage.addToolToCartEmailClientMgmt("Email_ServiceNow", UserID);
		objConfigureAutomationPage.addToolToCartEmailClientMgmt("Email_RTC", UserID);
		objConfigureAutomationPage.addToolToCartEmailClientMgmt("Email_RQM", UserID);
		objConfigureAutomationPage.addToolToCartEmailClientMgmt("Email_RDNG", UserID);
	}*/
	
}
