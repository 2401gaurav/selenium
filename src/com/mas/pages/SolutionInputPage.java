package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.beust.jcommander.converters.IntegerConverter;
import com.helper.DataConfig;
import com.mas.TestCases.TC_MAS_Login;

public class SolutionInputPage {

	@FindBy(how = How.XPATH, using = "//span/strong[contains(.,'Solution Description')]//following::div/textarea")
	@CacheLookup
	WebElement btn_SolutionDescription;
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Save')]")
	@CacheLookup
	WebElement btn_Save;
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Ok')]")
	@CacheLookup
	WebElement btn_Ok;
	@FindBy(how = How.XPATH, using = "//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]")
	@CacheLookup
	WebElement btn_Back;
	@FindBy(how = How.XPATH, using = ".//*[@id='wbse']/input")
	@CacheLookup
	WebElement btn_wbse;
	@FindBy(how = How.XPATH, using = "//p[@class='ng-binding']")
	@CacheLookup
	WebElement btn_Oktext;
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Solution Inputs')]")
	WebElement btn_SolutionInput;

	// p[@class='ng-binding']

	DataConfig df;

	HashMap<String, String> projectData11;

	HashMap<String, String> projectData;

	WebDriver driver;
	SolutionInputPage objSolutionInputPage;

	public SolutionInputPage(WebDriver driver) {

		this.driver = driver;

	}

	public void ValidateSolutionInputsPage() throws Exception

	{
		this.objSolutionInputPage = PageFactory.initElements(driver, SolutionInputPage.class);

		Thread.sleep(5000);

		List<WebElement> listheading = driver.findElements(By.xpath(".//span/strong"));
		List<WebElement> listbusinessvalue = driver
				.findElements(By
						.xpath("//div[@class='col-md-4 solution-checkbox ng-scope']/span[contains(@class, 'padding5-l ng-binding')]"));

		df = new DataConfig("C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\"
				+ "AutomationStoreInputData.xlsx");

		projectData = df.fetchInputData("SolutionInput");
		String text_solutioninput = projectData.get("Solution Description");
		String check_BusinessFunction = projectData.get("Business Function");
		/*String[] arr = check_BusinessFunction.split(",");
		int length=arr.length;*/
		String check_BusinessFunctiontext = check_BusinessFunction.replaceAll("\\s", "");
		String check_DeliveryFunction = projectData.get("Delivery Function");
		int ch = check_DeliveryFunction.length();
		String text_Other = projectData.get("Others");

		Thread.sleep(5000);

		for (WebElement values : listheading)

		{
			Thread.sleep(5000);
			String text = values.getText();
			Thread.sleep(5000);
			switch (text) {
			case "Solution Description": {
				Thread.sleep(5000);

				if (text_solutioninput.length() > 0) {
					try {
						btn_SolutionDescription.clear();
						Thread.sleep(8000);

						btn_SolutionDescription.sendKeys(text_solutioninput);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Vefrify that  the text as -" + text_solutioninput
								+ "  is Entered into the text box",
								"The text has been entered into the text box Successfully", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} catch (Exception e) {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Vefrify that  the text as -" + text_solutioninput
								+ "  is Entered into the text box", "Error occured" + e.getMessage(), TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}

				} else {

					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that SOLUTION DESCRIPTION Text-" + text_solutioninput
							+ "The text is blank", ".Skip the SOLUTION DESCRIPTION", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				}
				break;
			}
			case "Business Function": {
				if (check_BusinessFunction.length() > 0) {
					Thread.sleep(8000);
					objSolutionInputPage.checkbutton();
					for (WebElement businessfunction : listbusinessvalue) {
						String businessvalue = businessfunction.getText();
						System.out.println(businessvalue);
						Thread.sleep(5000);
						if (businessvalue.equals(check_BusinessFunction)) {
							try {
								WebElement businessElement = driver
										.findElement(By
												.xpath("//span[contains(.,'"
														+ check_BusinessFunction
														+ "')]/ancestor::div[@class='col-md-4 solution-checkbox ng-scope']/label/span"));
								Thread.sleep(10000);
								businessElement.click();

								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Business Function tool" + businessvalue
										+ "Is selected", "Required Business Function tool " + businessvalue
										+ "Is selected and has been checked", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
										TC_MAS_Login.outputWorkSheet, false);
							} catch (Exception e) {
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Business Function tool" + businessvalue
										+ "Is selected", "Error occured" + e.getMessage(), TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

							}
						} else {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Business Function tool-->" + businessvalue
									+ " Is not checked", "Required Business Function tool-->" + businessvalue
									+ " Is not matching with the excel input", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
									TC_MAS_Login.outputWorkSheet, false);
						}
					}
				}
				break;
			}
			case "Delivery Function": {
				Thread.sleep(5000);
				List<WebElement> listDeliveryvalue = driver
						.findElements(By
								.xpath("//span[contains(@class,'ng-not-empty checked')]//following::span[@class='padding5-l ng-binding' and contains(.,'"
										+ check_DeliveryFunction
										+ "')]//following::span[contains(@class,'ng-not-empty checked')]/ancestor::div/span"));
				Thread.sleep(5000);
				for (WebElement deliveryfunction : listDeliveryvalue) {
					Thread.sleep(2000);

					String deliveryfunctiontext = deliveryfunction.getText().trim();
					if (objSolutionInputPage.validatebyexcel(check_BusinessFunctiontext, deliveryfunctiontext) == true) {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify the tools which are automatically gets checked by selecting "
										+ check_BusinessFunction, deliveryfunctiontext
										+ " has been checked successfully", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
						System.out.println(deliveryfunctiontext + " is Present and Marked");

					} else {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify the tools which are automatically gets checked by selecting "
										+ check_BusinessFunction, deliveryfunctiontext + " has not been checked ",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						System.out.println(deliveryfunctiontext + " is not Present and Marked");
					}

				}

				break;
			}
			case "Others": {

				if (text_Other.length() > 0) {
					Thread.sleep(10000);
					btn_wbse.clear();
					Thread.sleep(5000);
					btn_wbse.sendKeys(text_Other);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that WBSE " + text_Other
							+ " should be entered into the input box", "Required WBSE " + text_Other
							+ " has been entered into the input box", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					break;
				}
			}
			}
		}

		Thread.sleep(5000);
		this.VerifySaveButton();
		Thread.sleep(20000);
		String popuptext = btn_Oktext.getText();
		if (popuptext.equalsIgnoreCase("Business Function cannot be empty")) {
			
			driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
			Thread.sleep(15000);
			driver.findElement(By.xpath("//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Business fuction is empty ",
					" Business fuction is empty and page is navigated to mass homepage ", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		} else {
			
			driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
			Thread.sleep(15000);
			try{
			driver.findElement(By.xpath("//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Solution Inputs page saved successfully ",
					"Solution Inputs page is saved successfully navigated to mass homepage ", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}catch(Exception e){
				WebElement Back=driver.findElement(By.xpath("//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]"));
				new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(Back));
				Back.click();
			}
		}
	}

	public boolean validatebyexcel(String getLink, String deliveryfunctiontext) throws Exception {
		boolean status = false;
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\"+ "solution.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(getLink);
		int row = st.getLastRowNum();
		for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(0).setCellType(CellType.STRING);

			XSSFCell category = st.getRow(i).getCell(0);
			String attributetext = category.getStringCellValue();

			if (deliveryfunctiontext.contains(attributetext)) {

				System.out.println(attributetext + " is Present");
				status = true;
				break;

			}
		}
		wb.close();
		fis.close();

		return status;
	}

	public void checkbutton() {
		try {
			if (driver
					.findElements(
							By.xpath("//div[@class='row margin-0']/div/label/span[@class='switch ng-valid ng-not-empty checked']"))
					.size() > 0) {
				List<WebElement> checked = driver.findElements(By
						.xpath("//div[@class='row margin-0']/div/label/span[@class='switch ng-valid ng-not-empty checked']/parent::label/following-sibling::span"));
				for (WebElement webElement : checked) {
					Thread.sleep(5000);
					String text=webElement.getText();
					System.out.println(text);
					driver.findElement(By.xpath("//div[@class='row margin-0']/div/label/span[@class='switch ng-valid ng-not-empty checked']/parent::label/following-sibling::span[contains(.,'"+text+"')]/parent::div/label/span")).click();
					
				}

			}
		} catch (Exception e) {

		}
	}

	public void VerifySaveButton() throws Exception {
		WebElement Characters = driver.findElement(By
				.xpath("html/body/div[1]/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div[2]/span"));
		String CharacterPresent = Characters.getText();
		String NoOfCharacterPresent = CharacterPresent.substring(0, CharacterPresent.indexOf(" "));
		int number = Integer.parseInt(NoOfCharacterPresent);
		System.out.println(number);
		if (number < 10000) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Solution Description Link Contains the text ",
					"The text is present under Solution Description Link ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(10000);
			btn_Save.click();
			Thread.sleep(30000);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Save Button is cllickable ",
					"Save button is clicked successfully ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			WebElement savetext = driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/p"));
			String savedsuccessfullyPopUpText = savetext.getText();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Save Button is cllickable ",
					"Save button is clicked successfully with the following text as " + savedsuccessfullyPopUpText,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(5000);
			

		} else {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Solution Description Does not Contains the text ",
					"Error Occured-The textbox  under Solution Description Link is empty ", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(10000);
			btn_Save.click();
			Thread.sleep(20000);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Save Button is cllickable ",
					"Save button is clicked successfully ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			WebElement savetext = driver.findElement(By.xpath("html/body/div[1]/div/div/div[2]/p"));
			String savePopUpText = savetext.getText();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Save Button is cllickable ",
					"Save button is clicked successfully with the following text as " + savePopUpText,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(5000);
			

		}

	}

	public void navigateSolutionPage() throws Exception {
		Thread.sleep(10000);
		if (driver.getCurrentUrl().contains("https://mywizardautostore.accenture.com/project")) {
			Thread.sleep(5000);
			btn_SolutionInput.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Navigate to the solution input page",
					"Navigated to the solution input page ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		} else if (driver.getCurrentUrl().contains("https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs")) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Navigate to the solution input page",
					"Navigated to the solution input page ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}
	}
}
