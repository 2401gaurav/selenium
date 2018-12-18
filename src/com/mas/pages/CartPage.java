package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import com.helper.CaptureScreenshot;
import com.helper.DataConfig;
import com.mas.TestCases.TC_MAS_Login;

public class CartPage {
	static List<WebElement> btn_remove;
	WebDriver driver;
	DataConfig df;
	HashMap<String, String> projectData;
	CaptureScreenshot objCaptureScreenshots;
	static int rowNumber;
	//configPendingPage objConfigPendingPage;
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	@CacheLookup
	WebElement CartIcon;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[1]/strong")
	@CacheLookup
	WebElement Items;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[2]/strong")
	@CacheLookup
	WebElement DeliverTypes;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[3]/div[3]/strong")
	@CacheLookup
	WebElement DeliveryFunctions;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/header/div/div[2]/div/div[1]/div/span")
	@CacheLookup

	WebElement LoginInfo;
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/header/div/div[2]/div/div[1]/ul/li/a")
	@CacheLookup
	WebElement LogOutInfo;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[2]/div/div/div[2]/div[5]/div/button[1]")
	@CacheLookup
	WebElement ContinueShopping;

	@FindBy(how = How.XPATH, using = "//div/button[@class='btn btn-success btn-infocartcolor pull-right']")
	@CacheLookup
	WebElement btn_Provision;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[3]/button[1]")
	@CacheLookup
	WebElement alertCancel;

	@FindBy(how = How.XPATH, using = "//div[@class='panel panel-primary']/div[2]/div[@class='alert alert-success fade in']")
	@CacheLookup
	WebElement text_Success;

	@FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger fade in ng-binding']")
	@CacheLookup
	WebElement text_NoItemsInTheCart;
	
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void ContinueShoppingDisplay() {
		if (ContinueShopping.isDisplayed()) {
			System.out.println("Continue Shopping button is Present");
			ContinueShopping.click();
			System.out.println("Continue Shopping is clicable Navigated to Configuration Page");
		} else {
			System.out.println("Continue Shopping is disable not Navigated to Configuration Page");
		}
	}

	public void columnsDisplay() throws Exception {

		if (Items.isDisplayed() && DeliverTypes.isDisplayed() && DeliveryFunctions.isDisplayed()) {
			System.out.println("Below Columns are displayed in the Cart Page");
			// objCaptureScreenshots.getscreenshot(driver,
			// "C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\Screenshots\\columns"
			// +rowNumber+ ".jpeg");

			// System.out.println("Below Columns are displayed in the Cart
			// Page");

		} else {
			System.out.println("Below Columns are not displayed in the Cart Page");
		}
	}

	public void loginifo() {
		if (LoginInfo.isDisplayed()) {
			String logininfo = LoginInfo.getText();
			System.out.println("Logged in user is" + logininfo);
		}
	}

	public void RemoveNotNeededTools() throws Exception {
		int removeBtnCount = driver.findElements(By.xpath("//button[@class='btn btn-info btn-infocartcolor']")).size();

		// String provision_btn=Remove_Btn.getText();

		for (int i = 0; i <= removeBtnCount; i++) {
			Thread.sleep(15000);

			btn_remove = driver.findElements(By.xpath("//button[@class='btn btn-info btn-infocartcolor']"));
			btn_remove.get(i).click();
			Thread.sleep(15000);
			WebElement btn_Removepopup = driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]"));
			btn_Removepopup.click();
			// System.out.println("provison status is" + provision_btn);
		}

		System.out.println("No");
	}

	public void validateConfigPending(String sheetname) throws Exception {
		//this.objConfigPendingPage = PageFactory.initElements(driver, configPendingPage.class);
		File excelfilename = new File(
				"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(sheetname);
		for (int i = 0; i <= st.getLastRowNum(); i++) {
			Thread.sleep(10000);
			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell = excelRow.getCell(0);
			// excelCell.setCellType(CellType.STRING);
			String product = excelCell.getStringCellValue();
			WebElement icon_ConfigPending = driver
					.findElement(By.xpath("//div/a[@class='black cursor ng-binding' and contains(text(),'" + product
							+ "')]//following::div[@id='isConfig']"));
			Thread.sleep(10000);
			if (icon_ConfigPending.isDisplayed()) {
				icon_ConfigPending.click();
				System.out.println("Configuration pending icon is displayed for the present tool");
				Thread.sleep(15000);
				//objConfigPendingPage.configPageDetails("ConfigPendingDetails");
				wb.close();
				fis.close();
			} else {
				System.out.println("Configuration pending icon is not present for the present tool");
			}
		}
	}

	public void validateDependendProduct(String product,String DependentProduct) throws Exception {
	
		Thread.sleep(10000);
		
			if (driver.findElement(By.xpath("//div/a[contains(.,normalize-space('"+DependentProduct+"'))]/ancestor::div/div/button")).getAttribute("disabled").equalsIgnoreCase("true")) {
				
				String getattribute=driver.findElement(By.xpath("//div/a[contains(.,normalize-space('"+DependentProduct+"'))]/ancestor::div/div/button")).getAttribute("disabled");
				
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify that the  Dependent Product - " + DependentProduct + "is present in the Cart Page " ,
						"Required Dependent Product " + DependentProduct
								+ " is  Present  in the Cart Page containing Remove Button as Disable",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
				System.out.println("Dependent Product is automatically added into the list " + DependentProduct+" "+getattribute);
			
				if(driver.findElement(By.xpath("//div/a[contains(.,normalize-space('"+product+"'))]")).isDisplayed()){
				System.out.println(product+" is displayed");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify that the   Product - " + product + "is present in the Cart Page " ,
						"Required  Product " + product
								+ " is  Present  in the Cart Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
				
				driver.findElement(By.xpath("//div/a[contains(.,'"+product+"')]/following::div/button[contains(.,'Remove')]")).click();
				WebElement btn_Removepopup = driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]"));
				btn_Removepopup.click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify that the   Product - " + product + "is removed from the cart Page " ,
						"Required  Product " + product
								+ " is  Successfully removed from the Cart Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
				Thread.sleep(10000);
				
					driver.findElement(By.xpath("//div/a[contains(.,'"+DependentProduct+"')]/following::div/button[contains(.,'Remove')]")).click();
					 driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]")).click();
					 TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the   Dependent Product - " + DependentProduct + "is removed from the cart Page " ,
								"Required Dependent Product " + DependentProduct
										+ " is  Successfully removed from the Cart Page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
					 Thread.sleep(20000);
					driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();
					 TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the   Clicking on Continue Shopping Button Navigate to the MAS HomePage  " ,
								" Successfully Navigated to the MAS HomePage from the Cart Page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(20000);
				
			}
		}
	}

	public void validateProvisionMyPlatform() throws Exception {
		if (btn_Provision.isEnabled()) {
			btn_Provision.click();
			Thread.sleep(20000);
			if (text_NoItemsInTheCart.isDisplayed() && text_Success.isDisplayed()) {
				String displayNoItems = text_NoItemsInTheCart.getText();
				String displaySuccess = text_Success.getText();
				System.out.println(displayNoItems + "\n" + displaySuccess);
			}
		} else {
			System.out.println("Provision Button is disable and not clickable");
		}
	}
}
