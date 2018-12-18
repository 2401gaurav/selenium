package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import com.mas.TestCases.TC_MAS_Login;

public class ToolsDetailsPage {
	public static String TagNametext;
	List<String> list = new ArrayList<>();

	@FindBy(how = How.XPATH, using = "//div/input[@placeholder='Search here']")
	WebElement txt_searchBox;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue Shopping')]")
	@CacheLookup
	WebElement btn_continueshopping;

	WebDriver driver;

	public ToolsDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifytoolsdetails() throws Exception {

		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\ToolDetails.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("BasicInfo");
		int row = st.getLastRowNum();
		loop1:for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(2).setCellType(CellType.STRING);
			st.getRow(i).getCell(4).setCellType(CellType.STRING);
			st.getRow(i).getCell(3).setCellType(CellType.STRING);
			st.getRow(i).getCell(7).setCellType(CellType.STRING);

			XSSFCell category = st.getRow(i).getCell(2);
			String product = category.getStringCellValue();
			XSSFCell DetailedDescription = st.getRow(i).getCell(4);
			String DetailedDescriptiontext = DetailedDescription.getStringCellValue();
			XSSFCell ShortDescription = st.getRow(i).getCell(3);
			String ShortDescriptiontext = ShortDescription.getStringCellValue();
			XSSFCell vendordetails = st.getRow(i).getCell(7);
			String vendordetailstext = vendordetails.getStringCellValue();
			
			txt_searchBox.sendKeys(product);
			System.out.println(product);
			txt_searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(18000);
			try{WebElement productTile = driver.findElement(By.xpath("//div/h3[contains(.,'" + product + "')]"));
			Thread.sleep(10000);
			
			productTile.click();
			}catch(Exception ex){
				if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
					txt_searchBox.clear();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
							"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					continue loop1;
			}}
			Thread.sleep(10000);
			String CurrentURL = driver.getCurrentUrl();
			if (CurrentURL.contains("https://mywizardautostore.accenture.com/product/")) {

				WebElement ProductDisplay = driver.findElement(By.xpath("//h4/big"));
				String ProductDisplayedText = ProductDisplay.getText();

				if (ProductDisplayedText.contains(product)) {
					this.tag(product, list);
					List<WebElement> ProductTags = driver.findElements(By.xpath("//p/span/a"));
					int TagsSize = ProductTags.size();
					loop: for (int j = 0; j < TagsSize; j++) {
						if (j < TagsSize - 1) {
							String getProductTags = ProductTags.get(j).getText();
							getProductTags = getProductTags.substring(0, getProductTags.indexOf("|") - 1);

							for (String webElement2 : list) {
								if (getProductTags.contains(webElement2)) {
									System.out.println(getProductTags + " Found");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Tags Present for the Product -  ",
											"Required Tag " + getProductTags + " for the Product " + product
													+ " is Present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
											TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								}
							}
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Tags Present for the Product -  ",
									"Error Occured-Required Tag " + getProductTags + " for the Product " + product
											+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						} else {
							String getProductTags = ProductTags.get(j).getText();
							getProductTags = getProductTags.trim();
							for (String webElement2 : list) {
								if (getProductTags.contains(webElement2)) {
									System.out.println(getProductTags + " Found");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Tags Present for the Product -  ",
											"Required Tag " + getProductTags + " for the Product " + product
													+ " is Present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
											TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								}
							}
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Tags Present for the Product -  ",
									"Error Occured-Required Tag " + getProductTags + " for the Product " + product
											+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

						}

					}
					WebElement ProductShortDescription = driver.findElement(By
							.xpath("//div/h4/following-sibling::p[1]"));
					String getShotDescription = ProductShortDescription.getText();
					WebElement ProductLongDescription = driver.findElement(By
							.xpath("//span[@ng-bind-html='product.DetailedDescription']"));
					String getProductLongDescription = ProductLongDescription.getText();
					getProductLongDescription.trim();
					String getProduct=getProductLongDescription.replaceAll("\\s+","");
					System.out.println(getProduct);
					String getProductlong=DetailedDescriptiontext.replaceAll("\\s+","");
					System.out.println(getProductlong);
					if (getProductlong.contains(getProduct)) {
						System.out.println(getProductLongDescription);
						System.out.println(DetailedDescriptiontext);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Long Description is Matching for the Product -  ",
								"Required Long Description " + getProductLongDescription + " for the Product " + product
										+ " is Present and Matching with the excel ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);

					} else {
						System.out.println(getProductLongDescription + " Not Matching");
						System.out.println(DetailedDescriptiontext);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Long Description is Matching for the Product -   ",
								"Error Occured-Required Long Description " + getProductLongDescription + " for the Product " + product
										+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
					WebElement ProductVendorDetails = driver.findElement(By
							.xpath("//div/span[@ng-show='product.VendorName != null']"));
					String getProductVendorText = ProductVendorDetails.getText();
					getProductVendorText = getProductVendorText.substring(getProductVendorText.indexOf(":") + 2);
					System.out.println(getProductVendorText);
					if (vendordetailstext.contains(getProductVendorText)) {
						System.out.println(getProductVendorText);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Vendor Detail is Matching for the Product -  ",
								"Required Vendor Detail " + getProductVendorText + " for the Product " + product
										+ " is Present and Matching with the excel ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);

					} else {
						System.out.println(getProductVendorText + " Not Matching");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Long Description is Matching for the Product -   ",
								"Error Occured-Required Long Description " + getProductVendorText + " for the Product " + product
										+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
					if (ShortDescriptiontext.contains(getShotDescription)) {
						System.out.println(getShotDescription);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Short Description is Matching for the Product -  ",
								"Required Short Description " + getShotDescription + " for the Product " + product
										+ " is Present and Matching with the excel ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
					} else {
						System.out.println(getShotDescription + " Not Matching");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Long Description is Matching for the Product -   ",
								"Error Occured-Required Long Description " + getShotDescription + " for the Product " + product
										+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
					Thread.sleep(10000);
					try {
						driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();

					} catch (Exception e) {
						btn_continueshopping.click();
					}
					Thread.sleep(20000);
				} /*else {
					System.out.println("Not Matching");
					TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
					TC_MAS.df.WritingToExcelResults("Verify that Tags Present for the Product -  ",
							"Error Occured-Required Tag " + getProductTags + " for the Product " + product
									+ " is not Present and Matching from the Excel sheet", TC_MAS.rowNumber,
							TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
				}*/

			}

		}
		fis.close();
		wb.close();
	}

	public List<String> tag(String product, List<String> list2) throws Exception {
		list.removeAll(list2);
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\ToolDetails.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("Tags");
		int row = st.getLastRowNum();
		loop: for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(1).setCellType(CellType.STRING);
			st.getRow(i).getCell(2).setCellType(CellType.STRING);

			XSSFCell product1 = st.getRow(i).getCell(1);
			String productname = product1.getStringCellValue();
			XSSFCell TagName = st.getRow(i).getCell(2);
			TagNametext = TagName.getStringCellValue();
			if (product.contains(productname)) {

				list.add(TagNametext);

				continue loop;
			}

		}
		fis.close();
		wb.close();
		return list2;

	}
}
