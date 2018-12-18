package reusablefunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;


import com.mas.TestCases.TC_MAS_Login;

public class reusefunction {
	WebDriver driver;
	public static int total;
	public static int number;
	public static boolean value;
	static WebElement toggle;
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Yes, Keep Working')]")
	@CacheLookup
	WebElement YesKeepWorking;


	public reusefunction(WebDriver driver) {
		this.driver = driver;
	}

	public boolean business_Function(String subcategories, String link, String category) throws Exception {
		boolean status=false;

		try {				 value=driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
					+ "'))]/ancestor::div/span/input")).isDisplayed();
			
			Thread.sleep(10000);
			if (value==true)
			{
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
					+ "'))]/ancestor::div/span/input")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is displayed",
					"Required checkbox under " + subcategories + " is present and is been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			 status=true;
			}
		else{TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
				"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=false;
		}
		}
		catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
					"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		}

		try {
			Thread.sleep(10000);
			 value=driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isDisplayed();
			if(value==true){
				Thread.sleep(10000);
				driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
						"Required link " + link + " is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				status=true;
			}
			else{
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
						"Required link " + link + " is not been found ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				status=false;
			}}catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
					"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			status=false;	}
		return status;}	
	public boolean delivery_Function(String subcategories, String link, String category) throws Exception {
		boolean status=false;
		try { value=driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"
				+ category
				+ "')]/parent::div/following-sibling::ul/li/div/div/following::ul/li/div/div[contains(.,normalize-space('"
				+ subcategories + "'))]/ancestor::div/span/input")).isDisplayed();
		
			Thread.sleep(10000);
			if (value==true){
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"
					+ category
					+ "')]/parent::div/following-sibling::ul/li/div/div/following::ul/li/div/div[contains(.,normalize-space('"
					+ subcategories + "'))]/ancestor::div/span/input")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is displayed",
					"Required checkbox under " + subcategories + " is present and is been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=true;
		}else{
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
					"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=false;
		}
			}
			
			catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
					"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=false;
		}

		try {
			Thread.sleep(10000);
			 value=driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isDisplayed();
			if(value==true){
				Thread.sleep(10000);
				driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
						"Required link " + link + " is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
status=true;
			}
			else{
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + "is not displayed",
						"Required link " + link + "is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				status=false;
			}}catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
					"Required link " + link + " is not been found ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			status=false;
		}
	return status;}

	public boolean delivery_Type(String subcategories, String link, String category) throws Exception {
		boolean status=false;
		try { value=driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
				+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
				+ "'))]/ancestor::div/span/input")).isDisplayed();
			Thread.sleep(10000);
			if (value==true){
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
					+ "'))]/ancestor::div/span/input")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is displayed",
					"Required checkbox under " + subcategories + " is present and is been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=true;}
			else{
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
						"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				status=false;
			}
				} 
			
			catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
					"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=false;
		}
		try {
			Thread.sleep(10000);
			boolean value=driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isDisplayed();
			if(value==true){
				Thread.sleep(10000);
				driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
						"Required link " + link + " is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				status=true;
			}
			else{
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
						"Required link " + link + "is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				status=false;
				
			}}catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
					"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			status=false;}
	return status;}

		public boolean industry_Assets(String subcategories, String link, String category) throws Exception {
			boolean status=false;
			try {
				Thread.sleep(15000);
				 value=driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
						+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
						+ "'))]/ancestor::div/span/input"))

						.isDisplayed();
				if (value == true)
					
				{
					driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
							+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('"
							+ subcategories + "'))]/ancestor::div/span/input")).click();
					Thread.sleep(10000);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is displayed",
							"Required checkbox under " + subcategories + " is present and is been clicked", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					status=true;
				}
				else{
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + "is not displayed",
							"Required checkbox under" + subcategories + "is not been clicked", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
			} catch (NoSuchElementException e)

			{
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
						"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}

			try {
				Thread.sleep(10000);
				 value=driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isDisplayed();
				if(value==true){
					Thread.sleep(10000);
					driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
							"Required link " + link + " is present and is been clicked", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					status=true;
				}
				else{
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
							"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					
				}}catch (NoSuchElementException e) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
						"Required link " + link + " is not been found ", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				
			}
		return status;}

		public boolean ratings(String subcategories, String link, String category) throws Exception {
			boolean status=false;
			try {
				Thread.sleep(10000);
				if (driver.findElement(By.xpath(".//div/span/img[contains(@ng-src,'" + subcategories + "')]"))
						.isDisplayed())
					;
				Thread.sleep(10000);
				driver.findElement(By.xpath(".//div/span/img[contains(@ng-src,'" + subcategories + "')]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The Link under Ratings -" + subcategories + "is displayed",
						"Required Ratings " + subcategories + "is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} catch (NoSuchElementException e) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The Link under Ratings -" + subcategories + " is not displayed",
						"Required Ratings " + subcategories + " is not present and is not been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			try {
				Thread.sleep(10000);
				boolean value=driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isDisplayed();
				if(value==true){
					Thread.sleep(10000);
					driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
							"Required link" + link + "is present and is been clicked", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

				}
				else{
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
							"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					
				}}catch (NoSuchElementException e) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
						"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
			}
		return status;}
	public void delivery_Funtionsub(String subcategories, String link, String category, String subcategory)
			throws Exception {
		try {
			Thread.sleep(10000);
			if (driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
					+ "'))]/following::ul/li/div/div[contains(.,'" + subcategory + "')]/ancestor::div/span/input"))

					.isDisplayed())
				;
			driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + category
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + subcategories
					+ "'))]/following::ul/li/div/div[contains(.,'" + subcategory + "')]/ancestor::div/span/input"))
					.click();
			Thread.sleep(10000);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is displayed",
					"Required checkbox under " + subcategories + " is present and is been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		} catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The checkbox for the -" + subcategories + " is not displayed",
					"Required checkbox under " + subcategories + " is not been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
		try {
			if (driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).isEnabled()) {
				driver.findElement(By.xpath("//h4/span/span[contains(.,'" + link + "')]")).click();

				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is displayed",
						"Required link " + link + " is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			
			
		} catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + " is not displayed",
					"Required link " + link + " is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public int CalculateProduct() throws Exception {
		// btnAll.click();
		total = 0;
		// Thread.sleep(15000);
		List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span"));
		for (WebElement test : table) {
			String text = test.getText();
			System.out.println(text);
			String str = text.substring(text.indexOf("(") + 1, text.indexOf(")"));
			int number = Integer.parseInt(str);

			total = total + number;
		}
		return total;
	}
	

	public void javascripttoggleclick(String category, String subcategory, String subscategory) throws Exception {
		try {
			Thread.sleep(10000);
			if (subscategory.contains("null")) {
				toggle = driver
						.findElement(By.xpath("*//div[contains(text(),'" + category + "')]/parent::div/span/span/i"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
				Thread.sleep(10000);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"The toggle button for the category- " + category + " is visible and clickable",
						"Required Toggle under " + category + " is present and is been clicked", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}

			else {
				Thread.sleep(10000);
				toggle = driver
						.findElement(By.xpath("*//div[contains(text(),'" + category + "')]/parent::div/span/span/i"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
				toggle = driver.findElement(By.xpath(
						"//div[contains(text(),'" + category + "')]/parent::div/following::ul/li/div/div[contains(.,'"
								+ subcategory + "')]/preceding-sibling::span/span/i"));
				Thread.sleep(10000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"The toggle button for " + subscategory + " under the the category-" + category
								+ " is visible and clickable",
						"Required Toggle under the subcategory " + subscategory + " is present and is been clicked",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
		} catch (NoSuchElementException e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The toggle button for-" + category + " is not clickable",
					"Required Toggle under " + category + " is not been clicked and opened", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void javascripttoggle(String category, String subcategory, String subscategory) throws Exception {
		Thread.sleep(10000);
		toggle = driver.findElement(By.xpath("*//div[contains(text(),'" + category + "')]/parent::div/span/span/i"));
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
		TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults(
				"The toggle button for the category-" + category + " is visible and clickable  ",
				"Required Toggle under " + category + " is present and is been clicked again", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
	}

	public void javascripttoggle1(String category) throws Exception {
		Thread.sleep(10000);
		toggle = driver.findElement(By.xpath("*//div[contains(text(),'" + category + "')]/parent::div/span/span/i"));
		Thread.sleep(10000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
		TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults(
				"The toggle button for the category-" + category + "is visible and clickable  ",
				"Required Toggle under " + category + " is present and is been clicked ", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
	}
	public void javascripttoggle2(String getLink) throws Exception {
		Thread.sleep(10000);
		List<WebElement> toggle = driver.findElements(By
				.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"+getLink+"')]/parent::div/following-sibling::ul/li/div/span/span/i"));
		Thread.sleep(10000);
		for (WebElement webElement : toggle) {
			Thread.sleep(1000);

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
		}
	}
	public boolean validatebyexcel(String getLink, String text, String categorytext, String textLink) throws Exception {
		boolean status=false;
		File excelfilename = new File(
				"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\data.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("Product-Attribute");
		int row = st.getLastRowNum();
		for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(1).setCellType(CellType.STRING);

			st.getRow(i).getCell(3).setCellType(CellType.STRING);
			st.getRow(i).getCell(4).setCellType(CellType.STRING);
			st.getRow(i).getCell(5).setCellType(CellType.STRING);

			XSSFCell category = st.getRow(i).getCell(3);
			String attributetext = category.getStringCellValue();
			XSSFCell subcategory = st.getRow(i).getCell(4);
			String AttributeValue = subcategory.getStringCellValue();
			XSSFCell link = st.getRow(i).getCell(5);
			String Category = link.getStringCellValue();
			XSSFCell tool = st.getRow(i).getCell(1);
			String AssetName = tool.getStringCellValue();

			//System.out.println(attributetext + " " + AttributeValue + " " + Category + " " + AssetName);
			if (getLink.contains(attributetext) && text.contains(AttributeValue) && categorytext.contains(AssetName)
					&& Category.contains(textLink)) {
				System.out.println("pass");
				System.out.println(attributetext + " " + AttributeValue + " " + Category + " " + AssetName);
				status = true;
				break;

			}
		}
		wb.close();
		fis.close();

		
		return status;
		}
public void javascripttoggle2() throws Exception {
	Thread.sleep(10000);
	List<WebElement>toggle = driver.findElements(By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li[4]/ul/li/div/span/span/i"));
	Thread.sleep(10000);
	for (WebElement webElement : toggle) {
		
	
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
}
}
public void checkYesKeepWorking()
{
	try
	{
	if(YesKeepWorking.isDisplayed())
{
	YesKeepWorking.click();
	
}
}
	catch(Exception e)
	{
	
}
	
}



public void list() throws Exception {
	try {
		if (driver.findElement(By.xpath(".//*[@id='accordion']/div/div[2]/div[1]/div[2]/div/select")).isEnabled()) {
			driver.findElement(By.xpath(".//*[@id='accordion']/div/div[2]/div[1]/div[2]/div/select")).click();
			Select objInputDetails = new Select(driver.findElement(By
					.xpath(".//*[@id='accordion']/div/div[2]/div[1]/div[2]/div/select")));
			Thread.sleep(5000);
			objInputDetails.selectByVisibleText("100");
		}
	} catch (Exception e) {
	}
}

public void popup() {
	try {

		driver.findElement(By.xpath("//button[contains(.,'Yes')]")).click();

	} catch (Exception e) {

	}
}

public void refreshpage(String getLink, String text, String tabletext, WebElement btnAll) throws Exception {
	if(getLink.contains("Delivery Type")||getLink.contains("Industry Segment")||getLink.contains("Business Function"))
	{
		driver.navigate().refresh();
	Thread.sleep(45000);
	driver.findElement(By.xpath("//a[contains(.,'All')]")).click();
	Thread.sleep(40000);
	this.javascripttoggle2(getLink);
	Thread.sleep(20000);
	driver.findElement(
			By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + getLink
					+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + text
					+ "'))]/ancestor::div/span/input")) // checkbox click
			.click();
	Thread.sleep(30000);
	driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();

	Thread.sleep(10000);
	this.list();
	}
	else
	{
		driver.navigate().refresh();
	Thread.sleep(45000);
	driver.findElement(By.xpath("//a[contains(.,'All')]")).click();
	Thread.sleep(40000);
	this.javascripttoggle1(getLink);
	this.javascripttoggle2(getLink);
	Thread.sleep(20000);
	driver.findElement(
			By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"
					+ getLink
					+ "')]/parent::div/following-sibling::ul/li/div/div/following::ul/li/div/div[contains(.,normalize-space('"
					+ text + "'))]/ancestor::div/span/input")) // checkbox
																// click
			.click();
	Thread.sleep(30000);
	driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();

	Thread.sleep(10000);
	this.list();
		}
	}



public void refresh(String getLink, String text, int i) throws Exception {
	// TODO Auto-generated method stub
	if (i > 0) {
		driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/div/a/strong")).click();
		Thread.sleep(40000);
		this.javascripttoggle1(getLink);
		Thread.sleep(20000);
		
		driver.findElement(
				By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + getLink
						+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + text
						+ "'))]/ancestor::div/span/input")) // checkbox click
				.click();
	} else {
		driver.findElement(
				By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'" + getLink
						+ "')]/parent::div/following-sibling::ul/li/div/div[contains(.,normalize-space('" + text
						+ "'))]/ancestor::div/span/input")) // checkbox click
				.click();
	}
}

public void refreshForCertainCategory(String getLink, String text, int i) throws Exception {
	// TODO Auto-generated method stub
	if (i > 0) {
		driver.findElement(By.xpath(".//*[@id='wrapper']/div[1]/div/div/div/a/strong")).click();
		Thread.sleep(20000);
		this.javascripttoggle1(getLink);
		this.javascripttoggle2(getLink);
		Thread.sleep(20000);
		driver.findElement(
				By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"
						+ getLink
						+ "')]/parent::div/following-sibling::ul/li/div/div/following::ul/li/div/div[contains(.,normalize-space('"
						+ text + "'))]/ancestor::div/span/input")) // checkbox
																	// click
				.click();
	} else {
		driver.findElement(
				By.xpath(".//*[@id='wrapper']/div[1]/div/div/ul/li/div/div[contains(.,'"
						+ getLink
						+ "')]/parent::div/following-sibling::ul/li/div/div/following::ul/li/div/div[contains(.,normalize-space('"
						+ text + "'))]/ancestor::div/span/input")) // checkbox
																	// click
				.click();
	}

}
public boolean validatebyexcel1(String categorytext, String textLink) throws Exception {
	boolean status=false;
	File excelfilename = new File(
			"C:\\Users\\gaurav.b.kapoor\\Desktop\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet("Sheet3");
	int row = st.getLastRowNum();
	for (int i = 0; i <= row; i++) {
		
		st.getRow(i).getCell(0).setCellType(CellType.STRING);
		st.getRow(i).getCell(1).setCellType(CellType.STRING);
		XSSFCell tool = st.getRow(i).getCell(1);
		String AssetName = tool.getStringCellValue();
		XSSFCell link = st.getRow(i).getCell(0);
		String Category = link.getStringCellValue();

		//System.out.println(attributetext + " " + AttributeValue + " " + Category + " " + AssetName);
		if(categorytext.contains(AssetName) && Category.contains(textLink)) {
			System.out.println("pass");
			System.out.println(AssetName + " " );
			status = true;
			break;

		}
	}
	wb.close();
	fis.close();

	
	return status;
	}
public boolean selectNewProductLink( String link) throws Exception {
	boolean status=false;
	
	try {
		Thread.sleep(10000);
		 value=driver.findElement(By.xpath("//h4/span/span[contains(.,'"+link+"')]")).isDisplayed();
		if(value==true){
			Thread.sleep(10000);
			driver.findElement(By.xpath("//h4/span/span[contains(.,'"+link+"')]")).click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + "is displayed",
					"Required link" + link + "is present and is been clicked", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			status=true;
		}
		else{
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + "is not displayed",
					"Required link " + link + "is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			
		}}catch (NoSuchElementException e) {
		TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults("The following link -" + link + "is not displayed",
				"Required link " + link + "is not been found", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
				TC_MAS_Login.outputWorkSheet, false);
	}
return status;}
public void validatebyexcelsheet(String categorytext) throws Exception {
	int Score=0;
	File excelfilename = new File(
			"C:\\Users\\gaurav.b.kapoor\\Desktop\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet("Sheet1");
	int row = st.getLastRowNum();
	for (int i = 0; i <= row; i++) {
		
		st.getRow(i).getCell(0).setCellType(CellType.STRING);
		st.getRow(i).getCell(1).setCellType(CellType.STRING);
		XSSFCell tool = st.getRow(i).getCell(1);
		String AssetName = tool.getStringCellValue();
		XSSFCell link = st.getRow(i).getCell(0);
		String Category = link.getStringCellValue();

		//System.out.println(attributetext + " " + AttributeValue + " " + Category + " " + AssetName);
		if(categorytext.contains(AssetName) || Category.contains("Cross Industry")) {
			System.out.println("pass");
			System.out.println(AssetName + " " );
		Score=1;

		}
	}
	wb.close();
	fis.close();

	
	
	}}
