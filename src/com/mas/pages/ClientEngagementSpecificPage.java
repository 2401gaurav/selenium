package com.mas.pages;

import java.io.File;


import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
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
import org.openqa.selenium.support.ui.Select;

import com.mas.TestCases.TC_MAS_Login;

public class ClientEngagementSpecificPage {
	@FindBy(how = How.XPATH, using = "html/body/div/div/div/div[1]/div/div[1]/div[2]/ul/li[5]/a")
	WebElement clientEngagent;
	@FindBy(how = How.XPATH, using = "//[@ class='container margin-border animated fadeInDown']/div[1]")
	WebElement clientEngagent_header;
	@FindBy(how = How.XPATH, using = "//*[@id='uploaddocument']/div[1]/div")
	WebElement clientEngagent_uploadFile;
	@FindBy(how = How.XPATH, using = "//*[@id='uploaddocument']/div[2]/span[2]/span")
	WebElement sharedClient;
	@FindBy(how = How.XPATH, using = ".//*[@id='uploaddocument']/div[2]/span[4]/span")
	WebElement useProject;
	@FindBy(how = How.XPATH, using = ".//*[@id='assetfocus']/div[1]/div/select")
	WebElement Category_select;
	@FindBy(how = How.XPATH, using = ".//*[@id='assetfocus']/div[2]/div/select")
	WebElement Category_select1;
	@FindBy(how = How.XPATH, using = ".//*[@id='deliveryProcess']/div/div/button")
	WebElement select;
	@FindBy(how = How.XPATH, using = ".//*[@id='deliveryProcess']/div/div/ul/li/a/div/label")
	WebElement label_dlivery;

	@FindBy(how = How.XPATH, using = ".//*[@id='assetName']/label/following-sibling::input")
	WebElement assetNameInput;
	@FindBy(how = How.NAME, using = "urlAccess")
	WebElement urlAccess;
	@FindBy(how = How.NAME, using = "sDescription")
	WebElement sDescription;
	@FindBy(how = How.NAME, using = "lDescription")
	WebElement lDescription;
	@FindBy(how = How.NAME, using = "plannedEffort")
	WebElement plannedEffort;
	@FindBy(how = How.NAME, using = "effortSaved")
	WebElement effortSaved;
	@FindBy(how = How.NAME, using = "costOfEffort")
	WebElement costOfEffort;
	@FindBy(how = How.XPATH, using = "//*[@id='assetfocus']/p/input")
	WebElement Created_Date;
	@FindBy(how = How.XPATH, using = ".//*[@id='deliveryProcess']/div/div/ul/li[@class='ng-scope']/a/div/label/input[@checked='checked']")
	WebElement CheckedValue;
	@FindBy(how = How.XPATH, using = ".//*[@id='collapseZero']/div/div/div[4]/div/button[2]")
	WebElement Save_button;
	@FindBy(how = How.XPATH, using = "html/body/div/div/div/div[1]/div/div[1]/div/h1/a/span[2]")
	@CacheLookup
	WebElement btn_Back;
	@FindBy(how = How.XPATH, using = ".//*[@id='collapse15']/div/div/div/div/div/div/div[1]/div[2]/h3")
	@CacheLookup
	WebElement tileName;
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Category')]/parent::div/span/span/i")
	@CacheLookup
	WebElement refineBy;

	WebDriver driver;
	static String fields;
	static String values;
	static String assertName;
	public List<WebElement>ProductList;
	public String ProductText;

	public ClientEngagementSpecificPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyclientEngagementSpecific_Display() throws Exception {

		if (clientEngagent.isDisplayed()) {
			clientEngagent.click();

			System.out.println("ClientEngagmentSpecific is Present");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the ClientEngagmentSpecific is Present and Navigates to ClientEngagmentSpecific HomePage",
					"ClientEngagmentSpecific is clickable and Navigates ClientEngagmentSpecific HomePage",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the ClientEngagmentSpecific is not Present and not Navigates to ClientEngagmentSpecific HomePage",
					"ClientEngagmentSpecific is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}

	public void verifyClientEngagent_subheader() {

		try {

			if (assetNameInput.isDisplayed() && urlAccess.isDisplayed() && sDescription.isDisplayed()
					&& lDescription.isDisplayed() && plannedEffort.isDisplayed() && effortSaved.isDisplayed()
					&& costOfEffort.isDisplayed() && Created_Date.isDisplayed())

			{
				assetNameInput.sendKeys(TC_MAS_Login.clientEngData.get("Asset Name"));
				urlAccess.sendKeys(TC_MAS_Login.clientEngData.get("URL/Link to access"));
				sDescription.sendKeys(TC_MAS_Login.clientEngData.get("Short Description"));
				lDescription.sendKeys(TC_MAS_Login.clientEngData.get("Long Description"));

				/*
				 * Format formatter = new SimpleDateFormat("yyyy-mm-dd"); String
				 * createdDate =
				 * formatter.format(TC_MAS.clientEngData.get("Created"));
				 * Created_Date.sendKeys(createdDate);
				 */
				Created_Date.sendKeys(TC_MAS_Login.clientEngData.get("Created"));
				String effortSavedValue = String.valueOf(TC_MAS_Login.clientEngData.get("EffortSaved"));
				effortSaved.sendKeys(effortSavedValue);
				String costOfEffortValue = String.valueOf(TC_MAS_Login.clientEngData.get("CostOfAsset"));
				costOfEffort.sendKeys(costOfEffortValue);
				String plannedEffortValue = String.valueOf(TC_MAS_Login.clientEngData.get("PlannedEffort"));
				plannedEffort.sendKeys(plannedEffortValue);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the following fields are displayed and able to take value from Excel Sheet",
				"Required link" + assetNameInput + urlAccess + sDescription + lDescription + Created_Date + effortSaved + costOfEffort + plannedEffort +"is present and is been clicked", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

			} else {
				System.out.println("this is not mand");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the following fields are not displayed and not able to take value from Excel Sheet",
				"Required link" + assetNameInput + urlAccess + sDescription + lDescription + Created_Date + effortSaved + costOfEffort + plannedEffort +"is present and is been clicked", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

			}
		} catch (Exception e) {
			System.out.println("Exception occured - validate this field contains no value " + fields);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the mandatory field",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}
	}

	public void verifySelectCategory(String strCategory, String strAutomation) {
		try {
			Category_select.click();

			Thread.sleep(1000);
			Select objCategory = new Select(Category_select);
			objCategory.selectByVisibleText(strCategory);
			Thread.sleep(4000);
			Category_select1.click();
			Select objAutomation = new Select(Category_select1);
			objAutomation.selectByVisibleText(strAutomation);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the SelectCategory fields are displayed",
					"fields are able to select", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		}

		catch (Exception e) {
			System.out.println("Exception occured - validate this field contains no value " + fields);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify Required option is selected",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}

	}

	public void verifyEnableClient_Project() {
		try {

			String value = sharedClient.getAttribute("class");
            String value1 = useProject.getAttribute("class");
			if ((value.contains("ng-not-empty") && TC_MAS_Login.clientEngData.get("SharedClient").equalsIgnoreCase("No")) ||

					(value1.contains("ng-not-empty")
							&& TC_MAS_Login.clientEngData.get("UseInProject").equalsIgnoreCase("No"))) {
				sharedClient.click();
				useProject.click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the 'sharedClient'  is present",
						"The Header 'Item' is present ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else if ((value.contains("ng-empty") && TC_MAS_Login.clientEngData.get("SharedClient").equalsIgnoreCase("Yes"))
					|| (value1.contains("ng-empty")
							&& TC_MAS_Login.clientEngData.get("UseInProject").equalsIgnoreCase("yes"))) {
				sharedClient.click();
				useProject.click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the 'UseInProject' is present",
						"The 'UseInProject' is present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);

			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the enable button" + "is  clickable",
						"Error occured - Enable button" + " is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);

			}

		} catch (Exception e) {
			System.out.println("Exception occured - validate this field contains no value ");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify Required option is selected",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		}

	}

	public void VerifyApplicableDelivery(String provisionType) {
		try {
			File excelfilename = new File(
					"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
			FileInputStream fis = new FileInputStream(excelfilename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = wb.getSheet(provisionType);
			if (select.isDisplayed()) {
				select.click();

			}
			List<WebElement> CheckBoxList = driver
					.findElements(By.xpath("//*[@id='deliveryProcess']/div/div/ul/li/a/div/label"));
			for (int i = 16; i <= st.getLastRowNum(); i++) {

				XSSFRow excelRow = st.getRow(i);
				XSSFCell excelCell = excelRow.getCell(0);
				excelCell.setCellType(CellType.STRING);
				values = excelCell.getStringCellValue();
				Thread.sleep(1000);
                    for (int j = 0; j < CheckBoxList.size() - 1; j++) {
					System.out.println(CheckBoxList.get(j).getText());
					if (CheckBoxList.get(j).getText().contains(values)) {
						CheckBoxList.get(j).click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the 'checkbox' is present for" + values,
								"The 'checkbox' is able to check  for" + values, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
						break;

					}
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {
			System.out.println("Exception occured - " + values);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the 'checkbox' is present ",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}

	}

	public void verifyUploadDocument() throws Exception {
		try {
			WebElement element = driver.findElement(By.xpath("//*[@id='uploaddocument']/div[1]/div"));
			element.click();
			Thread.sleep(5000);

			Runtime.getRuntime()
					.exec("C:\\Users\\sadhana.kumari\\MyWizard\\AutomationStore\\src\\com\\helper\\FileUpload.exe");
			Thread.sleep(5000);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the UploadDocument Button is clickable and able  to upload file",
					"uploadDocument Button is clickable and able  to upload file", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} catch (Exception e) {
			System.out.println("Exception occured - validate this field contains no value ");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the UploadDocument Button is clickable and able  to upload file",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		}
	}

	public void verifySaveButton() {
		if (Save_button.isEnabled()) {
			Save_button.click();
			System.out.println("Back to the Automation_Store HomePage");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Save Button is clickable and able  to create Tiles in valuewallet",
					"save Button is clickable and .//*[@id='collapse17']/div/div/div/div/div/div/div[1]/div[2]able  to create Tiles in valuewallet",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		} else {
			System.out.println("Not able to go to back button");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Save Button is clickable and able  to create Tiles in valuewallet",
					"Save Button is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}

	}

	public void verifyBackBtnMySelectionPage() throws Exception {

		if (btn_Back.isEnabled()) {
			btn_Back.click();
			System.out.println("Back to the Automation_Store HomePage");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Back Button is clickable and Navigates to back to the Automation Store HomePage",
					"Back Button is clickable and Navigates to back to the Automation Store HomePage", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(15000);
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

	public void verifyTilesCreated(String provisionType) throws Exception {
		try {
			
			
			File excelfilename = new File(
					"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
			FileInputStream fis = new FileInputStream(excelfilename);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = wb.getSheet(provisionType);
			for (int i = 0; i <= 0; i++) {

				XSSFRow excelRow = st.getRow(i);
				XSSFCell excelCell = excelRow.getCell(1);
				excelCell.setCellType(CellType.STRING);
				assertName = excelCell.getStringCellValue();
				Thread.sleep(1000);

				if (tileName.isDisplayed()) {
					ProductList=driver.findElements(By.xpath("//span[contains(.,'Client Engagement Specific')]/following::div/h3"));
					loop:for (int j=0;j<ProductList.size();j++) {
					 ProductText=ProductList.get(j).getText();
						if((ProductText.equalsIgnoreCase(assertName))){
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the created tool is Displayed  to the  Automation Store HomePage",
									"created tool is Displayed  to the  Automation Store HomePage", TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							break loop;
						}
						else if(j==(ProductList.size()-1)){
							boolean value=driver.findElement(By.xpath("//span[contains(.,'Client Engagement Specific')]/following::div/a/i[@class='glyphicon glyphicon-chevron-right']")).isEnabled();
							if((value==true)&&!(ProductText.equalsIgnoreCase(assertName))){
								driver.findElement(By.xpath("//span[contains(.,'Client Engagement Specific')]/following::div/a/i[@class='glyphicon glyphicon-chevron-right']")).click();
								ProductList=driver.findElements(By.xpath("//span[contains(.,'Client Engagement Specific')]/following::div/h3"));
							j=-1;
								continue loop;	
							}
							
						}
					}
						
					
					
					
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {

			System.out.println("Exception occured - validate this field contains no value ");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the created tool is Displayed  to the  Automation Store HomePage",
					"Exception occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		}

	}
	
	
}