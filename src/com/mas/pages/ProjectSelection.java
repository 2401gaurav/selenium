package com.mas.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;
import com.helper.CaptureScreenshot;
import com.mas.TestCases.TC_MAS_Login;

public class ProjectSelection {

	static int rowNumber;
	
	@FindBy(how = How.ID, using = "ApplyProject")
	WebElement btn_apply;
	
	@FindBy(how = How.XPATH, using = "html/body/pageslide/div/div/div/div[1]/button/span") 
	WebElement btn_closeAnnouncementPopup;

	@FindBy(how = How.XPATH, using = "//*[@id='ApplyProject']")
	WebElement btn_ddPageApplyButton;

	@FindBy(how = How.XPATH, using = "//*[@id= 'step-0']/div[3]/button")
	WebElement btn_ddPageEndTour;

	@FindBy(how = How.XPATH, using = ".//*[@id='step-0']/div[3]/button")
	WebElement btn_endGuidedTour;

	@FindBy(how = How.XPATH, using = "html/body/div[5]/div[3]/button")
	WebElement btn_endTour;

	@FindBy(how = How.XPATH, using = ".//a[text()='Get Started']")
	WebElement btn_getStartedButton;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-menu']/ul/li[3]/a")
	WebElement btn_taskTab;
	
	WebDriver driver;
	
	@FindBy(how = How.ID, using = "selectedClientID")
	WebElement drpdown_client;

	@FindBy(how = How.ID, using = "selectedEngagementID")
	WebElement drpdown_engagement;

	@FindBy(how = How.ID, using = "selectedProjectID")
	WebElement drpdown_project;
	
	@FindBy(how = How.ID, using = "//*[text()[normalize-space(.)='Responsible Entity']]//following::select[@id='locationselectedProjectID']")																																// //For
	WebElement drpDownResponsibleEntity;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-menu']/div/div/div[1]")
	WebElement label_clientEnggPrjName;
	
	@FindBy(how = How.XPATH, using = "//span[@class='glyphicon glyphicon-triangle-bottom']")
	WebElement btn_preference;
	
	CaptureScreenshot objCaptureScreenshots;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-menu']/ul/li[2]/a")
	WebElement tabBtn_ManageWorkTab;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-menu']/ul/li[1]/a")
	WebElement tabBtn_Portfolio;

	public ProjectSelection(WebDriver driver) {
		this.driver = driver;
		objCaptureScreenshots = PageFactory.initElements(driver, CaptureScreenshot.class);
	}

	public void closeAnnouncementPopUp() {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		btn_closeAnnouncementPopup.click();
	}

	public void closeEndTourInDDPage() throws InterruptedException {
		try {

			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			btn_ddPageEndTour.click();
		} catch (Exception e) {

		}
	}

	public void closeManageWorkTour() {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		btn_endTour.click();
	}
	// new announcement pop up
	

	public void GetStartedInmyWizard() throws Exception {
		// Getting started button
		Thread.sleep(30000);
		System.out.println("Click on Get started");
		driver.findElement(By.xpath(".//a[text()='Get Started']")).click();
		Thread.sleep(3000);
	}

	public void handleFirefoxAuthenticateWindow() throws Exception {

		Runtime.getRuntime().exec(
				"C:\\workspace\\AutomationStore\\src\\com\\helper\\AuthenticateWindow-Firefox.exe");

		Thread.sleep(15000);
	}

	public void handleSecureIDTokenWindow() throws Exception 
	{
		Thread.sleep(15000);
		
		Runtime.getRuntime().exec("C:\\workspace\\AutomationStore\\src\\com\\helper\\vipAccess_Script.exe");

		Thread.sleep(60000);
	}

	public void navigateTomyWizard() throws InterruptedException {
		btn_getStartedButton.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public String selectLocationProject(String LocationID, String projOrEng) {
		String output = "";
		try {
			Thread.sleep(30000);

			WebDriverWait wait = new WebDriverWait(driver, 60);// wait for 40
																// sec.
			wait.until(ExpectedConditions.elementToBeClickable(btn_preference));
			// Thread.sleep(20000);

			// click on the user preferences Dropdown
			btn_preference.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

			if (projOrEng.contains("Proj")) {
				Verify.verify(drpDownResponsibleEntity.isDisplayed() && drpDownResponsibleEntity.isEnabled(),
						"No provision for Selecting a Location Project");
				if (drpDownResponsibleEntity.isDisplayed() && drpDownResponsibleEntity.isEnabled()) {
					new Select(drpDownResponsibleEntity).selectByVisibleText(LocationID);// data.get(4));//("48735");
																					// //
																					// Project
																					// ID
					btn_ddPageApplyButton.click();
					Thread.sleep(20000);
					output = output
							+ "User has a provision for Selecting a Location Project in Project Delivery Dashboard ";
				} else {
					output = output
							+ "Error Occurred : No provision for Selecting a Location Project in Project Delivery Dashboard ";
				}

			} else if (projOrEng.contains("Eng")) {
				Verify.verify(drpDownResponsibleEntity.isDisplayed() == false,
						"Engagement Delivery Dashboard should not contain the provision for selecting a project");
				if (drpDownResponsibleEntity.isDisplayed()) {
					output = output
							+ "Error Occurred : Engagement Delivery Dashboard should not contain the provision for selecting a project";
				} else {
					output = output + "No Provision for selecting a project in Engagement Delivery Dashboard";
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			output = output + "Error Occured :" + ex.getMessage();
		}

		return output;

	}

	
	public void selectProject(String strClient, String strEng, String strProj)
			throws InterruptedException, IOException {
		try {
			btn_preference.click();
			Thread.sleep(5000);
			Select objClient = new Select(drpdown_client);
			objClient.selectByVisibleText(strClient);
			Thread.sleep(5000);
			Select objEng = new Select(drpdown_engagement);
			objEng.selectByVisibleText(strEng);
			Thread.sleep(5000);
			Select objPro = new Select(drpdown_project);
			objPro.selectByVisibleText(strProj);
			Thread.sleep(5000);
			btn_apply.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.inputDataConfig.WritingToExcelResults("Verify Required Project is selected",
					"Required Project is selected - " + strProj, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} catch (Exception e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.inputDataConfig.WritingToExcelResults("Verify Required Project is selected",
					"Error Occurred - Required Project is not selected - " + strProj + " - " + e.getMessage(),
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
			driver.quit();
			System.exit(1);

		}
	}


	public void validateManageWorkTabUI() {
		Verify.verify(tabBtn_Portfolio.isDisplayed(), "portfolioTab not getting displayed");
		Verify.verify(tabBtn_ManageWorkTab.isDisplayed(), "ManageWorkTab not getting displayed");
		Verify.verify(btn_taskTab.isDisplayed(), "TaskTab not getting displayed");
		Verify.verify(label_clientEnggPrjName.isDisplayed(), "clientEnggProjectNames not getting displayed");
	}

	public String ValidateProject_ApplyButton(String strClient, String strEng) {

		String output = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);// wait for 40
																// sec.

			wait.until(ExpectedConditions.elementToBeClickable(btn_preference));

			btn_preference.click();
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
			Thread.sleep(5000);

			new Select(drpdown_client).selectByVisibleText(strClient);

			Thread.sleep(3000);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

			Select drpDataSource = new Select(drpdown_engagement);

			drpDataSource.selectByVisibleText(strEng);

			boolean projectstatus = drpdown_project.isDisplayed();
			boolean applystatus = btn_apply.isEnabled();

			Thread.sleep(15000);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

			rowNumber = rowNumber + 1;
			objCaptureScreenshots.getscreenshot(driver,
					System.getProperty("user.dir") + "\\src\\com\\Screenshots\\ProjectSelector" + rowNumber + ".jpeg");

			btn_apply.click();
			Thread.sleep(10000);

			if (projectstatus == false && applystatus == true) {

				output = "E2E Delivery selection/Service Delivery Contract, is not displayed and Apply button is enabled for standalone engagement, as required.";
			} else if (projectstatus == true && applystatus == true) {

				output = "E2E Delivery selection/Service Delivery Contract is displayed AND Apply button is enabled for standalone engagement, it shoudnt(wrong).";
			} else if (projectstatus == false && applystatus == false) {

				output = "E2E Delivery selection/Service Delivery Contract isn't  displayed AND Apply button is disabled for standalone engagement, it shoudnt(wrong).";

			} else if (projectstatus == true && applystatus == false) {
				output = "E2E Delivery selection/Service Delivery Contract is displayed AND Apply button is disabled for standalone engagement, it shoudnt(wrong).";
			}

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);

		} catch (Exception e) {
			System.out.println(e.getMessage());

			output = "A standalone engagement is  not selected in MyWizard.";
		}
		return output;

	}

	public boolean verifyAnnouncementPopUpExistence() {
		try {
			Verify.verify(btn_closeAnnouncementPopup.isDisplayed(), "AnnouncementPopup is not displayed");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifyCloseEndTourExistenceInDDpage() {
		try {
			Verify.verify(btn_ddPageEndTour.isDisplayed(), "Delivery Dashboard Guided tour pop up is not displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyCloseManageWorkTourExistence() {
		try {
			Verify.verify(btn_endTour.isDisplayed(), "Guided tour pop up is not displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}