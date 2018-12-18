package com.mas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.mas.TestCases.TC_MAS_Login;

public class ProductCartPage {

	static WebDriver driver;

	static int rowNumber;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	WebElement btn_cartIcon;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[1]/strong")
	WebElement lbl_items;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[2]/strong")
	WebElement lbl_deliverTypes;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[3]/strong")
	WebElement lbl_deliveryFunctions;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/header/div/div[2]/div/div[1]/div/span")
	WebElement lbl_loginInfo;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Continue Shopping')]")
	static WebElement btn_continueShopping;

	@FindBy(how = How.XPATH, using = "//div/button[@class='btn btn-success btn-infocartcolor pull-right']")
	static WebElement btn_provision;

	@FindBy(how = How.XPATH, using = "//div[@ng-show='vendor.ddldeliverymode']/select")
	static WebElement drpdown_assertDeliveryMode;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='vendor.ddldeliverymode.AssetDeliveryModeId==1']")
	static WebElement obj_assertDeliveryMode;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger fade in ng-binding']")
	static WebElement txt_noItemsInTheCart;

	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[1]/div/div[2]/div[1]")
	static WebElement lbl_successMsg;

	@FindBy(how = How.XPATH, using = "//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]")
	WebElement btn_Back;
	
	@FindBy(how = How.XPATH, using = ".//button[text()='Ok']")
	public static WebElement btn_popupOk;

	@SuppressWarnings("static-access")
	public ProductCartPage(WebDriver driver) {

		this.driver = driver;
	}

	public void verifyContinueShoppingButton() throws Exception {
		try {

			if (btn_continueShopping.isDisplayed()) {
				System.out.println("Continue Shopping button is displayed");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Continue Shopping button is displayed", "Continue Shopping button is displayed",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				btn_continueShopping.click();
				Thread.sleep(15000);
				String currentURL = driver.getCurrentUrl();
				if (currentURL.contains("https://hpdpvaluewallet.ciostage.accenture.com/project")) {
					System.out.println("Continue Shopping is clickable and Navigated to Configuration Automation Page");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Continue Shopping is clickable and Navigated to Configuration Automation Page",
							"Continue Shopping is clickable and navigates to Configuration Automation Page",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				} else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Continue Shopping is clickable and Navigated to Configuration Automation Page",
							"Error occured - Continue Shopping button not navigates to Configuration Automation Page",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}

			} else {
				System.out.println("Continue Shopping is disable not Navigated to Configuration Page");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Continue Shopping is clickable and Navigated to Configuration Automation Page",
						"Error occured - Continue Shopping is disable not navigates to Configuration Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
			this.verifyBackBtn();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Continue Shopping is clickable and Navigated to Configuration Automation Page",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void verifyBackBtn() throws Exception {

		if (btn_Back.isEnabled()) {
			btn_Back.click();
			System.out.println("Back to the Automation_Store HomePage");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
					"Back Button is clickable and Navigates to back to the Automation Store HomePage",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(15000);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
					"Back Button is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}

	public void verifyProductCartPageColumns() throws Exception {

		if (lbl_items.isDisplayed() && lbl_deliverTypes.isDisplayed() && lbl_deliveryFunctions.isDisplayed()) {
			System.out.println("Columns are displayed in the Cart Page");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the columns are displayed in Cart Page - Items, Delivery Type and Delivery Functions",
					"The columns are displayed in Cart Page - " + lbl_items.getText() + "," + lbl_deliverTypes.getText()
							+ "," + lbl_deliveryFunctions.getText(),
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("Below Columns are not displayed in the Cart Page");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the columns are displayed in Cart Page - Items, Delivery Type and Delivery Functions",
					"Error occured - The columns are not displayed in Cart Page  - Items, Delivery Type and Delivery Functions",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void verifyLoginInfo() {
		if (lbl_loginInfo.isDisplayed()) {
			String logininfo = lbl_loginInfo.getText();
			System.out.println("Logged in user is" + logininfo);
		}
	}

	public static void validateProvisionMyPlatform() throws Exception {
		if (driver.findElement(By.xpath("//div/button[@class='btn btn-success btn-infocartcolor pull-right']")).isEnabled()) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate Provision Button is enabled and clickable",
					"Provision Button is enable and clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			driver.findElement(By.xpath("//div/button[@class='btn btn-success btn-infocartcolor pull-right']")).click();
			Thread.sleep(10000);
			try{
				if (obj_assertDeliveryMode.isDisplayed()==true) {
					Thread.sleep(10000);
					Select objddAssertDeliveryMode = new Select(drpdown_assertDeliveryMode);
					objddAssertDeliveryMode.selectByVisibleText("Email");
					btn_provision.click();
				}
			}catch(Exception e){
				btn_provision.click();
			}
			//Thread.sleep(90000);
			try{			
				if (txt_noItemsInTheCart.isDisplayed()==true && lbl_successMsg.isDisplayed()==true) {
					String displayNoItems = txt_noItemsInTheCart.getText();
					String displaySuccess = lbl_successMsg.getText();
					System.out.println(displayNoItems + "\n" + displaySuccess);
					if ((displayNoItems.contains("No items in the Cart"))
							&& (displaySuccess.contains("Success! Order has been placed"))) {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate 'No Items in the Cart'  and  'Success! Order has been placed' message is displayed after provisioning is displayed",
								"'No Items in the Cart'  and  'Success! Order has been placed' message is displayed after provisioning is displayed",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} else {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate 'No Items in the Cart' and  'Success! Order has been placed' message is displayed after provisioning is displayed",
								"Error Occured - Incorrect message is displayed after provisioning is displayed instead of 'No Items in the Cart' and 'Success! Order has been placed' message",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				}
				
			}catch (Exception e){
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate 'No Items in the Cart' and  'Success! Order has been placed' message is displayed after provisioning is displayed",
						"Error Occured - 'No Items in the Cart' and 'Success! Order has been placed' message is not displayed after provisioning is displayed",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
		} else {
			System.out.println("Provision Button is disable and not clickable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate Provision Button is enabled and clickable",
					"Error occured - Provision Button is disable and not clickable", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
		Thread.sleep(5000);
		try{
			
			btn_popupOk.click();
			Thread.sleep(3000);
			btn_continueShopping.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate 'continueShopping'  button is enabled",
					"'continueShopping'   is displayed and clicked successfully navigated to MAS homepage",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}catch (Exception e){
			
			btn_continueShopping.click();
		}	
		Thread.sleep(5000);
		}

	public static void validateConfigPending(String product) throws Exception {

		try{
			WebElement lnk_configPending = driver
					.findElement(By.xpath("//div/a[@class='black cursor ng-binding' and contains(text(),'" + product
							+ "')]//following::div[@id='isConfig']"));
			if (lnk_configPending.isDisplayed()==true) {	
				System.out.println("Configuration pending icon is displayed for the "+product);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate Configuration pending icon is displayed for the "+product,
						"Configuration pending icon is displayed for the present tool",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				System.out.println("Configuration pending icon is not present for the "+product);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate Configuration pending icon is displayed for the "+product,
						"Error occured - Configuration pending icon is displayed for the not present tool", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			lnk_configPending.click();
			
		}catch (Exception e){
			System.out.println(e.getMessage());
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate Configuration pending icon is displayed for the "+product,
					"Error occured - "+e.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}
}
