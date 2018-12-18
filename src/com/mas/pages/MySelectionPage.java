package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.helper.CaptureScreenshot;
import com.mas.TestCases.TC_MAS_Login;

public class MySelectionPage {
	static String product;
	static String Order_ID;
	static String accessLink;
	static WebDriver driver;
	
	@FindBy(how = How.XPATH, using = ".//a[@ng-click='navigateToMyOrders()']")
	WebElement lnk_mySelection;
	
	@FindBy(how = How.XPATH, using = "html/body/div/div/div/div[1]/div/div[1]/div/h1/a/span[2]")
	static
	WebElement btn_Back;
	
	@FindBy(how = How.XPATH, using = "html/body/div/footer[contains(text(),'Accenture. All Rights Reserved. Accenture Confidential. For Internal Use Only.')]")
	WebElement lbl_footer;

	public MySelectionPage(WebDriver driver) {
		MySelectionPage.driver = driver;
	}

	public void mySelectionDisplay() throws Exception {
		if (lnk_mySelection.isDisplayed()) {
			lnk_mySelection.click();
			System.out.println("MySelectionDisplay is Present");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the MySelection is clickable and Navigates to MySelection HomePage",
					"MySelection is clickable and Navigates MySelection HomePage", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the MySelection is clickable and Navigates to MySelection HomePage",
					"MySelection is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}

	public void verfiyMySelectionPageFooterSection() {
		if (lbl_footer.isDisplayed()) {
			System.out.println("Footer is present");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Footer Section is displayed in My Selections Page",
					"Footer Section is displayed in My Selections Page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);

		} else {
			System.out.println("footer is not present");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Footer Section is displayed in My Selections Page",
					"Error - occured - Footer Section is not displayed in My Selections Page", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void verifyBackBtnMySelectionPage() throws Exception {

		if (btn_Back.isEnabled()) {
			btn_Back.click();
			Thread.sleep(10000);
			String currentURL = driver.getCurrentUrl();
			if(currentURL.contains("https://hpdpvaluewallet.ciostage.accenture.com/project")){
				System.out.println("Back to the Automation_Store HomePage");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
						"Back Button is clickable and Navigates to back to the Automation Store HomePage", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				Thread.sleep(15000);
			}else{
				System.out.println("Not navigated to the Automation_Store HomePage");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
						"Back Button is clickable and but not Navigates to back to the Automation Store HomePage", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
					"Back Button is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}

	public void verifyOrderHeaders() throws InterruptedException {
		List<WebElement> title_Header = driver
				.findElements(By.xpath(".//div[@id='accordion']/div/div/div[@class='panel-heading1 bg-head']"));
		for (int i = 0; i< title_Header.size()-1;i++) {
			WebElement orderTogglebutton = title_Header.get(i).findElement(By.xpath(
					"./div/div/strong[text()='Order']/preceding::div/a/i[contains(@class,'glyphicon glyphicon-triangle')]"));
				if (orderTogglebutton.isDisplayed()) {
					WebElement eleOrderID = title_Header.get(i).findElement(By.xpath("./div/div/strong[text()='Order']/parent::div"));
					String Order_ID = StringUtils.substringBetween(eleOrderID.getAttribute("innerHTML"), "#", " <br>");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the 'Togglebutton' is present for Order ID - " + Order_ID,
							"The 'Togglebutton' is present for Order ID - " + Order_ID, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);

				}
				else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the 'Togglebutton' is present for Order ID - " + Order_ID,
							"Error occured - The 'Togglebutton' is present for Order ID - " + Order_ID, TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

				}
			List<WebElement> itemHeader = title_Header.get(i).findElements(
					By.xpath("./div[@class='pull-left text-center margin-10-t']/following-sibling::div/strong"));
			for (int j = 0; j <= itemHeader.size() - 1; j++) {

				if (itemHeader.get(j).getText().equalsIgnoreCase("Order")) {

					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Header 'Order' is present",
							"The Header 'Order' is present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
							false);
				} else if (itemHeader.get(j).getText().equalsIgnoreCase("Order Placed")) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Header'Order Placed' is present",
							"The Header 'Status' is present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
							false);

				} else if (itemHeader.get(j).getText().equalsIgnoreCase("Summary")) {

					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Header'Summary' is present",
							"The Header 'Provision Description' is present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);

				} else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Header" + itemHeader.get(j).getText() + "is displayed",
							"Error occured - Product Header" + itemHeader.get(j).getText() + " is not displayed ",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}

			}
		}
	}

	public void verifyOrderSubheaders() throws InterruptedException {
		List<WebElement> title_Header = driver.findElements(By.xpath(
				".//div[@id='accordion']/div/div/div[@class='panel-heading1 bg-head']/div/following::div[@ class='panel-collapse collapse in']"));
		for (int i=0;i<title_Header.size();i++) {
			CaptureScreenshot.highlightElement(title_Header.get(i));
			List<WebElement> sub_Header = title_Header.get(i)
					.findElements(By.xpath("./div/div[contains(@class,'txt-fw-Orders')]"));
			/*WebElement eleOrderID = driver.findElements(By.xpath(
					".//div[@id='accordion']/div/div/div[@class='panel-heading1 bg-head']/div/following::div[@ class='panel-collapse collapse in']")).get(i).findElement(By.xpath("./div/div[contains(@class,'txt-fw-Orders')]/preceding::div[@class='panel-heading1 bg-head']/div/div/strong[text()='Order']/parent::div"));*/
			List<WebElement> eleOrderID = driver.findElements(By.xpath(
					".//div[@id='accordion']/div/div/div[@class='panel-heading1 bg-head']/div/following::div[@ class='panel-collapse collapse in']/div/div[contains(@class,'txt-fw-Orders')]/preceding::div[@class='panel-heading1 bg-head']/div/div/strong[text()='Order']/parent::div"));
			CaptureScreenshot.highlightElement(eleOrderID.get(i));
			String Order_ID = StringUtils.substringBetween(eleOrderID.get(i).getAttribute("innerHTML"), "#", " <br>");
			for (int j = 0; j <= sub_Header.size() - 1; j++) {

				if (sub_Header.get(j).getText().contains("Item")) {
					CaptureScreenshot.highlightElement((sub_Header.get(j)));
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Header 'Item' is present for "+Order_ID,
							"The Header 'Item' is present for "+Order_ID, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
							false);
				} else if (sub_Header.get(j).getText().contains("Status")) {
					CaptureScreenshot.highlightElement((sub_Header.get(j)));
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Header'Status' is present for "+Order_ID,
							"The Header 'Status' is present for "+Order_ID, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
							false);

				} else if (sub_Header.get(j).getText().contains("Provision Description")) {
					CaptureScreenshot.highlightElement((sub_Header.get(j)));
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Header'Provision Description' is present for "+Order_ID,
							"The Header 'Provision Description' is present for "+Order_ID, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);

				} else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Header" + sub_Header.get(i).getText() + "is displayed for the product for "+Order_ID,
							"Error occured - Product Header" + sub_Header.get(i).getText() + " is not displayed for "+Order_ID,
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
			}
		}

	}

	public void verifyProductIcon(String provisionType) throws Exception {
		try {
			File excelfilename = new File(
					"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
			FileInputStream fis = new FileInputStream(excelfilename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = wb.getSheet(provisionType);
			for (int i = 1; i <= st.getLastRowNum(); i++) {

				XSSFRow excelRow = st.getRow(i);
				XSSFCell excelCell = excelRow.getCell(0);
				excelCell.setCellType(CellType.STRING);
				product = excelCell.getStringCellValue();
				if (driver
						.findElement(By
								.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(.,'"
										+ product
										+ "')]]/preceding-sibling::div[@class='blank-img']/img[contains(@src,'.png')] "))
						.isDisplayed()) {
					System.out.println("Product Icon is displayed for the " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the product icon is displayed for the product",
							"Product Icon is displayed for the " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				} else {
					System.out.println("Product Icon is not displayed for the " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the product icon is displayed for the product",
							"Error occured - Product Icon is not displayed for the " + product, TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {
			System.out.println("Exception occured - Product Icon is not displayed for the " + product);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the product icon is displayed for the product",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}

	}

	
	public static void verifyOrderFullFilledStatus(XSSFSheet assetListSheet, int counter) throws Exception {	
		try {
			WebElement lnk_mySelection = driver.findElement(By.xpath(".//a[@ng-click='navigateToMyOrders()']"));
			lnk_mySelection.click();
			Thread.sleep(15000);
			int  i = counter-24;
			for (; i <= assetListSheet.getLastRowNum(); i++) {
				XSSFRow excelRow = assetListSheet.getRow(i);
				XSSFCell assertNameCell = excelRow.getCell(0);
				XSSFCell provisionDescCell = excelRow.getCell(1);
				assertNameCell.setCellType(CellType.STRING);
				provisionDescCell.setCellType(CellType.STRING);
				product = assertNameCell.getStringCellValue();
//				accessLink = provisionDescCell.getStringCellValue();	
				WebElement orderStatus = driver.findElement(By.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(.,'"+product+"')]]/following::div[1]"));
//				String accessLinkURL = driver.findElement(By.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(.,'"+product+"')]]/following::div[2]/table/tbody/tr/td[@class='urlDescriptionShrink']/span/a")).getText();
				if (orderStatus.getText().trim().contains("Fulfilled")) {
					System.out.println("Fullfilled is displayed for the - " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Fullfilled status is displayed for the product",
							"Fullfilled status is displayed for the " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					/*if(accessLinkURL.contains(accessLink)){
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the AccessURL is displayed for the product",
								"AccessURL is displayed for the " + product +" - " + accessLinkURL, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
					}else{
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the AccessURL is displayed for the product",
								"Error occured - AccessURL is displayed not for the " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
					}*/
				} else {
					System.out.println("Fullfilled is not displayed for the " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Fullfilled status is displayed for the product",
							"Error occured - Fullfilled status is not displayed for the " + product, TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				
				if((i==counter)||(i==assetListSheet.getLastRowNum())){
					break;		
				}
			}
			
			btn_Back.click();
			
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Fullfilled status is displayed for the products",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			Thread.sleep(10000);
			btn_Back.click();
		}
	}
	
	public static void verifyEmailOrderStatus(String product) throws Exception {	
		try {
			WebElement lnk_mySelection = driver.findElement(By.xpath(".//a[@ng-click='navigateToMyOrders()']"));
			lnk_mySelection.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Myselection button is enable",
					"Myselection button is enable and clicked successfully navigated to myselection page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
				WebElement orderStatus = driver.findElement(By.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(.,'"+product+"')]]/following::div[1]"));
				System.out.println(orderStatus.getText());
				/*String accessLinkURL = driver.findElement(By.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(.,'"+product+"')]]/following-sibling::div/table/tbody/tr/td[contains(text(),'ADTLink')]/following-sibling::td/a[text()[normalize-space() = 'Go to Get ADT']]")).getAttribute("href");*/
				if (orderStatus.getText().trim().contains("Ordered")) {
					System.out.println("Ordered is displayed for the - " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Ordered status is displayed for the product",
							"Ordered status is displayed for the " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				}	else{
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the status is displayed for the product",
							"the status is displayed for the product " + product+" is"+orderStatus.getText(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					}
			
			btn_Back.click();
			
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Fullfilled status is displayed for the products",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			btn_Back.click();
		}
	}
	}