package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;



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


import com.mas.TestCases.TC_MAS;

public class UserDefinedPackage {
	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[1]/li[5]/a")
	@CacheLookup
	WebElement packageBtn;
	@FindBy(how = How.XPATH, using = ".//*[@id='collapsePackage']/div/div/div/div[2]/div")
	@CacheLookup
	WebElement UserDefinedAaesPackag;
	@FindBy(how = How.XPATH, using = "//div/div[1]/div/ul/li/ul/li[contains(.,'FiltersType')]/div/div/Parent::div/span/input")
	@CacheLookup
	WebElement  industryFiltersTypes;
	@FindBy(how = How.XPATH, using = "//div/div[1]/div/ul/li/ul/li/ul/li[contains(.,'Finance Transformation')]/div/div/Parent::div/span/input")
	@CacheLookup
	WebElement  industryFiltersSuTypes;
	@FindBy(how = How.XPATH, using = "//div/div[contains(.,'AP216_Cost center.docx')]/a/ancestor::div/div/span/input")
	@CacheLookup
	WebElement  selectedProduct;
	@FindBy(how = How.XPATH, using = "//div[2]/button[text()[contains(.,'Add to Cart')]]")
	@CacheLookup
	WebElement  btnAddToCart;
	@FindBy(how = How.XPATH, using = "//div[2]/button[text()[contains(.,'Go to cart')]]")
	@CacheLookup
	WebElement  btnGoToCart;
	@FindBy(how = How.XPATH, using = "//div/button[text()[contains(.,'Proceed To Checkout')]]")
	@CacheLookup
	WebElement  btnProceedToCheckout;
	@FindBy(how = How.XPATH, using = "//div/button[text()[contains(.,'Provision My Platform')]]")
	@CacheLookup
	WebElement  btnProvisionMyPlatform;
	
	static String FiltersType;
	static String FiltersSubType;
	static String ProductName;
	
	WebDriver driver;
	public UserDefinedPackage(WebDriver driver){
	this.driver=driver;
	
}
	
	public void verifyPackageDisplay() throws Exception{
		if (packageBtn.isDisplayed()) {
			packageBtn.click();
			System.out.println("PackageDisplay is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the packageBtn is clickable and Navigates to package HomePage",
					"packageBtn is clickable and Navigates package HomePage", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the packageBtn is clickable and Navigates to package HomePage",
					"Error occured - packageBtn is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
}
	public void verifyUserdefinedPackageDisplay() throws Exception{
		if (UserDefinedAaesPackag.isDisplayed()) {
			UserDefinedAaesPackag.click();
			System.out.println("PackageDisplay is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the UserDefinedAAESPackag is clickable and Navigates to UserDefinedAAESPackag HomePage",
					"UserDefinedAAESPackag is clickable and Navigates UserDefinedAAESPackag HomePage", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the UserDefinedAAESPackag is clickable and Navigates to UserDefinedAAESPackag HomePage",
					"Error occured - UserDefinedAAESPackag is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}
	public void verifyFiltersType(String provisionType) throws Exception{
		File excelfilename = new File(
				"C:\\Users\\sadhana.kumari\\MyWizard\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
	
			XSSFRow excelRow = st.getRow(1);
			XSSFCell excelCell = excelRow.getCell(0);
		    excelCell.setCellType(CellType.STRING);
			FiltersType = excelCell.getStringCellValue();
			Thread.sleep(1000);
		
		if(driver.findElement(By.xpath("//div/div[1]/div/ul/li/ul/li[contains(.,normalize-space('"+FiltersType+"'))]/div/div/preceding-sibling::span/input")).isDisplayed())
				
		{
			driver.findElement(By.xpath("//div/div[1]/div/ul/li/ul/li[contains(.,normalize-space('"+FiltersType+"'))]/div/div/preceding-sibling::span/input")).click();
			System.out.println(FiltersType);
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the SelectedProduct for " + FiltersType
							+ " in tool details matches with Selected Product",
					"Selected Product for " + FiltersType
							+ " in tool details matches with Selected Product",
					TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
		} else {
			System.out.println("Selected Product not Matches");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the Selected Product for " + FiltersType
							+ " in tool details matches with Selected Product",
					"Error occured - Selected Product for " + FiltersType
							+ " in tool details not matches with Selected Product",
					TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
		
		}wb.close();
        fis.close();
	

}
	public void verifySelectedProduct(String provisionType) throws Exception{
		
		File excelfilename = new File(
				"C:\\Users\\sadhana.kumari\\MyWizard\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		for (int i = 1; i <=st.getLastRowNum()-1; i++) {

			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell2 = excelRow.getCell(2);
			excelCell2.setCellType(CellType.STRING);
			ProductName = excelCell2.getStringCellValue();
			Thread.sleep(1000);
		
	       if (driver.findElement(By.xpath("//div/div[contains(.,normalize-space('"+ProductName+"'))]/a/ancestor::div/div/span/input")).isDisplayed()) 
                {
				
				driver.findElement(By.xpath("//div/div[contains(.,normalize-space('"+ProductName+"'))]/a/ancestor::div/div/span/input")).click();
				System.out.println(ProductName);
				TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
				TC_MAS.resultDataConfig.WritingToExcelResults(
						"Validate the SelectedProduct for " + ProductName
								+ " in tool details matches with Selected Product",
						"Selected Product for " + ProductName
								+ " in tool details matches with Selected Product",
						TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			} else {
				System.out.println("Selected Product not Matches");
				TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
				TC_MAS.resultDataConfig.WritingToExcelResults(
						"Validate the Selected Product for " + ProductName
								+ " in tool details matches with Selected Product",
						"Error occured - Selected Product for " + ProductName
								+ " in tool details not matches with Selected Product",
						TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			
			}}wb.close();
			fis.close();
		
}
	public void verifyBtnAddToCart() throws InterruptedException {
		if (btnAddToCart.isDisplayed()) {
			btnAddToCart.click();
			System.out.println("btnAddToCart is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the AddToCartBtn is clickable and Product added to cart",
					"AddToCartBtn is clickable and Product added to cart", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the AddToCartBtn is clickable and Product added to cart",
					"Error occured -AddToCartBtn is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}
	
	public void verifyBtnGoToCart() throws InterruptedException {
		if (btnGoToCart.isDisplayed()) {
			btnGoToCart.click();
			System.out.println("btnAddToCart is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the GoToCartBtn is clickable and Product added to cart",
					"GoToCartBtn is clickable and Product added to cart", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the GoToCartBtn is clickable and Product added to cart",
					"Error occured - GoToCartBtn is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}
	public void verifyBtnProceedToCheckout() throws InterruptedException {
		if (btnProceedToCheckout.isDisplayed()) {
			btnProceedToCheckout.click();
			System.out.println("btnProceedToCheckout is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the ProceedToCheckoutBtn is clickable",
					"ProceedToCheckoutBtn is clickable", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the ProceedToCheckoutBtn is clickable",
					"Error occured -ProceedToCheckoutBtn is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}
	public void verifyBtnProvisionMyPlatform() throws InterruptedException {
		if ( btnProvisionMyPlatform.isDisplayed()) {
			 btnProvisionMyPlatform.click();
			System.out.println("btnProceedToCheckout is Present");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the  ProvisionMyPlatform Btn is clickable and Navigates to continueShoping HomePage",
					"ProvisionMyPlatform Btn is clickable", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);
			Thread.sleep(1500);
		} else {
			System.out.println("Not able to go to back button");
			TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.resultDataConfig.WritingToExcelResults(
					"Validate the ProvisionMyPlatformBtn is clickable and Navigates to continueShoping HomePage",
					"Error occured -ProvisionMyPlatformBtn is not clickable", TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
			driver.quit();
			System.exit(1);
		}
	}
	
}