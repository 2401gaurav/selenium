package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class ConfigPendingPage {

	static WebDriver driver;

	static WebElement inputDetails;
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")

	WebElement CartIcon;
	@FindBy(how = How.XPATH, using = "//button[@ng-click='navigateToCheckout()']")
	static
	WebElement btn_GoToCart;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	static
	WebElement btn_Submit;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	WebElement btn_Ok;

	@FindBy(how = How.XPATH, using = "//label[text() ='Instance Type']/following::select[1]")
	static WebElement drpdown_instanceType;

	@FindBy(how = How.XPATH, using = "//label[text() ='Admin User']/following::input[1]")
	static WebElement txt_adminUser;

	@FindBy(how = How.XPATH, using = "//label[text() ='Users']/following::input[1]")
	static WebElement txt_user;

	@FindBy(how = How.XPATH, using = "//button[text()[normalize-space() = 'Save']]")
	static WebElement btn_saveConfig;

	@FindBy(how = How.XPATH, using = "//button[text()[normalize-space() = 'Go to cart']]")
	static WebElement btn_goToCart;

	@FindBy(how = How.XPATH, using = ".//button[text()='Ok']")
	static WebElement btn_popupOk;
	
	@FindBy(how = How.XPATH, using = "//label[text() ='Engagement Name']/following::input[1]")
	static WebElement txt_configPageEngagement;
	
	@FindBy(how = How.XPATH, using = "//label[text() ='Project Name']/following::input[1]")
	static WebElement txt_configPageProject;

	@SuppressWarnings("static-access")
	public ConfigPendingPage(WebDriver driver) {
		this.driver = driver;
	}

	public static void configPageClientMgmtFields(String sheetname) throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(sheetname);
		int row = st.getLastRowNum();
		for (int i = 3; i <= row; i++) {

			st.getRow(i).getCell(0).setCellType(CellType.STRING);
			st.getRow(i).getCell(1).setCellType(CellType.STRING);
			st.getRow(i).getCell(2).setCellType(CellType.STRING);

			XSSFCell fields = st.getRow(i).getCell(0);
			String labelText = fields.getStringCellValue();
			XSSFCell fieldType = st.getRow(i).getCell(1);
			String elementType = fieldType.getStringCellValue();
			XSSFCell fieldValue = st.getRow(i).getCell(2);
			String cellData = fieldValue.getStringCellValue();

			System.out.println(cellData);
			System.out.println(elementType);

			if (((elementType.equalsIgnoreCase("text") || (elementType.equalsIgnoreCase("email"))))) {
				inputDetails = driver
						.findElement(By.xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'"
								+ labelText + "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/input"));
				inputDetails.clear();
				inputDetails.sendKeys(cellData);
				try {
					if (driver
							.findElement(By.xpath(
									".//ul[@class='suggestions-list']/li[text()[contains(.,'" + cellData + "')]]"))
							.isDisplayed()) {
						driver.findElement(
								By.xpath(".//ul[@class='suggestions-list']/li[text()[contains(.,'" + cellData + "')]]"))
								.click();
					}
				} catch (NoSuchElementException e) {
					wb.close();
					fis.close();
				}

			} else if (((elementType.equalsIgnoreCase("radio")))) {
				inputDetails = driver.findElement(
						By.xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'" + labelText
								+ "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/span[contains(@ng-repeat,'radioButtonItem')]/label[text()[contains(.,'"
								+ cellData + "')]]/input"));
				inputDetails.click();

			}

			else if (((elementType.equalsIgnoreCase("checkbox")))) {
				inputDetails = driver.findElement(By.xpath("//a[contains(text(),'" + labelText
						+ "')]/ancestor::*[position()=1]/following::div[@class='col-sm-4']/span[contains(@ng-repeat,'checkboxItem')]/label/input"));

				inputDetails.click();

			}

			else if (((elementType.equalsIgnoreCase("dropdown")))) {
				inputDetails = driver
						.findElement(By.xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'"
								+ labelText + "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/select"));

				Select objInputDetails = new Select(inputDetails);
				objInputDetails.selectByVisibleText(cellData);

			}

			else {
				System.out.println("Nothing is present");
			}
		}
		wb.close();
		fis.close();
	}

	public static void configPageAccnMgmtDetails(String UserID) throws InterruptedException {

		Actions build = new Actions(driver);
/*		try {
			Select objInstanceType = new Select(drpdown_instanceType);
			objInstanceType.selectByVisibleText("Accenture Managed");
			Thread.sleep(5000);

		} catch (Exception e) {

		}*/
		
		selectInstanceType("Accenture Managed");

		try {
			txt_adminUser.click();
			txt_adminUser.clear();
			if(UserID.contains("abhishek.suresh"))
			{
			build.sendKeys("abhishek.suresh").build().perform();
			Thread.sleep(20000);
			try{
			 WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = 'abhishek.suresh']]"));build.click(suggestedUser).build().perform();
			}catch(Exception e){
				
				txt_user.clear();
				build.sendKeys("abhishek.suresh").build().perform();
				Thread.sleep(20000);
				WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = 'abhishek.suresh']]"));build.click(suggestedUser).build().perform();
			}
			}
			else
			{build.sendKeys(UserID).build().perform();
			Thread.sleep(20000);
			
			WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = "+UserID+"]]"));build.click(suggestedUser).build().perform();
}
		} catch (Exception e) {

		} 
			 

		

		try {

			txt_user.click();
			txt_user.clear();
			
			if(UserID.contains("abhishek.suresh"))
			{
			build.sendKeys("abhishek.suresh").build().perform();
			Thread.sleep(20000);
			try{
			 WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = 'abhishek.suresh']]"));build.click(suggestedUser).build().perform();
			}catch(Exception e){
				
				txt_user.clear();
				build.sendKeys("abhishek.suresh").build().perform();
				Thread.sleep(20000);
				WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = 'abhishek.suresh']]"));build.click(suggestedUser).build().perform();
			}
			}
			else
			{build.sendKeys(UserID).build().perform();
			Thread.sleep(20000);
			
			WebElement suggestedUser = driver.findElement(By.xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = "+UserID+"]]"));build.click(suggestedUser).build().perform();
}
		} catch (Exception e) {

		} 

		btn_saveConfig.click();
		Thread.sleep(10000);

		try {
			if (btn_popupOk.isDisplayed() == true) {
				btn_popupOk.click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {

		}

		btn_goToCart.click();
	}

	public static void selectInstanceType(String instanceType) {

		try {
			Select objInstanceType = new Select(drpdown_instanceType);
			objInstanceType.selectByVisibleText(instanceType);
			Thread.sleep(5000);

		} catch (Exception e) {

		}
	}

	public static void configPageClientMgmtDetails_TFS(String engagement) throws Exception {

		selectInstanceType("Client Managed");
		txt_configPageEngagement.clear();
		txt_configPageEngagement.sendKeys(engagement);
		configPageClientMgmtFields("Email_TFS");		
		btn_saveConfig.click();
		Thread.sleep(10000);
		try {
			if (btn_popupOk.isDisplayed() == true) {
				btn_popupOk.click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {

		}
		btn_goToCart.click();
		
	}
	
	public static void configPageClientMgmtDetails_ServiceNow(String project, String UserID) throws Exception {

		Actions build = new Actions(driver);
		selectInstanceType("Client Managed");
		configPageClientMgmtFields("Email_ServiceNow");
		txt_configPageProject.sendKeys(project);
		txt_user.click();
		txt_user.clear();
		build.sendKeys(UserID + ",").build().perform();
		Thread.sleep(20000);
		btn_saveConfig.click();
		Thread.sleep(10000);
		try {
			if (btn_popupOk.isDisplayed() == true) {
				btn_popupOk.click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {

		}
		btn_goToCart.click();	
	}
	
	public static void configPageClientMgmtDetails_RTC_RQM_RDNG(String product,String project) throws Exception {

		selectInstanceType("Client Managed");
		txt_configPageProject.sendKeys(project);
		if(product.contains("Rational CLM - RTC")){
			configPageClientMgmtFields("Email_RTC");
		}else if(product.contains("Rational CLM - RQM")){
			configPageClientMgmtFields("Email_RQM");
		}else if(product.contains("Rational CLM - RQM")){
			configPageClientMgmtFields("Email_RDNG");
		}		
		btn_saveConfig.click();
		Thread.sleep(10000);
		try {
			if (btn_popupOk.isDisplayed() == true) {
				btn_popupOk.click();
				Thread.sleep(5000);
			}
		} catch (Exception e) {

		}
		btn_goToCart.click();	
	}
	
	
}
