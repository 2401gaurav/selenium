package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mas.TestCases.TC_MAS_Login;
import com.mas.pages.ProductCartPage;

public class ConfigureAutomationPage {
	static String currentUrl;

	static List<WebElement> masLinks;

	static String product;

	static String client;

	static String engagement;

	static String project;

	static WebElement btn_rightArrow;

	static WebElement btn_leftArrow;

	static XSSFSheet assetListSheet;

	static int i, j, k, l;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	WebElement btn_cartIcon;

	@FindBy(how = How.XPATH, using = "//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[last()]/img")
	WebElement btn_listViewAll;

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[2]/img")
	WebElement btn_gridView;

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div/ul/li/img[contains(@src, 'tile-view4IA.png')]")
	WebElement btn_GridViewRec;

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[3]/img")
	WebElement btn_listView;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'scope-selector-toggle')]/div/span[@class = 'glyphicon glyphicon-triangle-bottom']")
	WebElement btn_masScopeSelector;

	@FindBy(how = How.XPATH, using = "//label[text()='Client']/following-sibling::select")
	WebElement drpdown_masClient;

	@FindBy(how = How.XPATH, using = "//label[text()='Engagement']/following-sibling::select")
	WebElement drpdown_masEngagement;

	@FindBy(how = How.XPATH, using = "//label[text()='Project']/following-sibling::select")
	WebElement drpdown_masProject;

	@FindBy(how = How.XPATH, using = ".//button[@class='btn btn-primary' and text() = 'Apply']")
	WebElement btn_scopeSelectorApply;

	WebDriver driver;

	@FindBy(how = How.XPATH, using = "html/body/div/footer[contains(text(),'Accenture. All Rights Reserved. Accenture Confidential. For Internal Use Only.')]")
	WebElement lbl_footer;

	HashMap<String, String> projectData;

	@FindBy(how = How.XPATH, using = "//span[@id='submitButton']")
	WebElement SignIn;

	@FindBy(how = How.XPATH, using = ".//*[@id='collapse12']/div/div/a[2]/i")
	@CacheLookup
	WebElement Tech_rightclick;

	@FindBy(how = How.XPATH, using = ".//*[@id='collapse10']/div/div/a[2]/i")
	WebElement Tools_rightclick;

	@FindBy(how = How.XPATH, using = ".//input[@ng-model='SearchValue']")
	WebElement txt_searchBox;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='isTileView']")
	List<WebElement> gridView;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='!isTileView']")
	List<WebElement> listView;

	@FindBy(how = How.XPATH, using = "//a[text()='New']")
	WebElement NewTab;

	@FindBy(how = How.XPATH, using = "//a[text()='Packages']")
	WebElement PackagesTab;

	@FindBy(how = How.XPATH, using = "//a[text()='All']")
	WebElement AllTab;

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[1]/li[1]/a")
	WebElement recommendedLnk;

	public ConfigureAutomationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void addToolToCartDFF(String sheetName) throws Exception {
		int counter = 25;
		i = 3;
		l = 1;
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		assetListSheet = wb.getSheet(sheetName);
		loop: for (; l <= assetListSheet.getLastRowNum(); l++) {
			assetListSheet = wb.getSheet(sheetName);
			XSSFRow excelRow0 = assetListSheet.getRow(l);
			XSSFCell excelCell0 = excelRow0.getCell(0);
			XSSFCell excelCell1 = excelRow0.getCell(1);
			XSSFCell excelCell2 = excelRow0.getCell(2);
			excelCell0.setCellType(CellType.STRING);
			excelCell1.setCellType(CellType.STRING);
			excelCell2.setCellType(CellType.STRING);
			client = excelCell0.getStringCellValue();
			engagement = excelCell1.getStringCellValue();
			project = excelCell2.getStringCellValue();
			System.out.println(client + " " + engagement + " " + project);
			this.selectMASProject(client, engagement, project);
			Thread.sleep(10000);
			innerloop: for (; i <= assetListSheet.getLastRowNum(); i++) {
				XSSFRow excelRow1 = assetListSheet.getRow(i);
				XSSFCell excelCell3 = excelRow1.getCell(0);
				excelCell3.setCellType(CellType.STRING);
				product = excelCell3.getStringCellValue().trim();
				txt_searchBox.clear();
				txt_searchBox.sendKeys(product);
				txt_searchBox.sendKeys(Keys.ENTER);
				Thread.sleep(15000);
				try {
					WebElement productTileCartIcon = driver.findElement(By
							.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
									+ product
									+ "']]/following::div[@class='cart-height']/span/span[@class != 'ng-hide']/span"));
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate on " + product + "the icon  is displayed",
							"Product icon is displayed for " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					productTileCartIcon.click();
				} catch (Exception e) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate on " + product + "the icon  is displayed",
							"Error occurred - Product icon is not displayed for " + product, TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					continue innerloop;
				}
				Thread.sleep(10000);
				this.verifyProductTileCartIcon(product);
				txt_searchBox.clear();
				if ((i == counter + 2) || (i == assetListSheet.getLastRowNum())) {
					System.out.println("navigated product cart");
					this.verifyNavigatetoProductCart();
					Thread.sleep(15000);
					List<WebElement> display_tools = driver.findElements(By.xpath(
							".//div[@class='panel-body']/div/div/div/a[@ng-click='navigateToProductDetails(product.ProductId)']"));
					if (display_tools.size() > 1) {
						for (j = i - 24; j <= assetListSheet.getLastRowNum(); j++) {
							excelRow1 = assetListSheet.getRow(j);
							excelCell3 = excelRow1.getCell(0);
							excelCell3.setCellType(CellType.STRING);
							product = excelCell3.getStringCellValue().trim();
							for (k = 0; k <= display_tools.size() - 1; k++) {
								String visible_tools = display_tools.get(k).getText();
								if (product.contains(visible_tools)) {
									System.out.println(product + "Is Found in Cart");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Validate the Added product - " + product + " is found in Cart",
											"Added product - " + product + " is found in Cart", TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									break;
								}
								if ((k == display_tools.size()) && (!product.contains(visible_tools))) {
									System.out.println(product + "Not Found in Cart");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Validate the Added product - " + product + " is found in Cart",
											"Error occured - Added product - " + product + " is not found in Cart",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
											false);
								}
							}

							if (j == counter + 2) {
								break;
							}
						}
					} else {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Added products is found in Cart",
								"Error occured - Products not added to cart", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
					ProductCartPage.validateProvisionMyPlatform();
					Thread.sleep(15000);
					MySelectionPage.verifyOrderFullFilledStatus(assetListSheet, i);
					Thread.sleep(15000);
					l = i + 1;
					i = i + 4;
					counter = counter + 28;
					continue loop;
				}

			}
		}

		wb.close();
		fis.close();

	}

	public void addToolToCartEmailAccMgmt(String sheetName, String UserID) throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		assetListSheet = wb.getSheet(sheetName);
		loop: for (i = 1; i <= assetListSheet.getLastRowNum(); i++) {
			assetListSheet = wb.getSheet(sheetName);
			XSSFRow excelRow0 = assetListSheet.getRow(i);
			XSSFCell excelCell0 = excelRow0.getCell(0);
			XSSFCell excelCell1 = excelRow0.getCell(1);
			XSSFCell excelCell2 = excelRow0.getCell(2);
			XSSFCell excelCell3 = excelRow0.getCell(3);
			excelCell0.setCellType(CellType.STRING);
			excelCell1.setCellType(CellType.STRING);
			excelCell2.setCellType(CellType.STRING);
			excelCell3.setCellType(CellType.STRING);
			client = excelCell0.getStringCellValue();
			engagement = excelCell1.getStringCellValue();
			project = excelCell2.getStringCellValue();
			product = excelCell3.getStringCellValue().trim();
			System.out.println(client + " " + engagement + " " + project);
			Thread.sleep(20000);
			this.selectMASProject(client, engagement, project);
			Thread.sleep(20000);
			txt_searchBox.clear();
			txt_searchBox.sendKeys(product);
			txt_searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(15000);
			try {
				WebElement productTileCartIcon = driver.findElement(By
						.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
								+ product
								+ "']]/following::div[@class='cart-height']/span/span[@class != 'ng-hide']/span"));
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + product + "the icon  is displayed",
						"Product Icon is displayed for " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				productTileCartIcon.click();
			} catch (Exception e) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + product + "the icon  is displayed",
						"Error occurred - Product icon is not displayed for " + product, TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				continue loop;
			}
			Thread.sleep(10000);
			this.verifyProductTileCartIcon(product);
			this.verifyNavigatetoProductCart();
			ProductCartPage.validateConfigPending(product);
			Thread.sleep(10000);
			ConfigPendingPage.configPageAccnMgmtDetails(UserID);
			Thread.sleep(10000);
			ProductCartPage.validateProvisionMyPlatform();
			Thread.sleep(15000);
			MySelectionPage.verifyEmailOrderStatus(product);
			Thread.sleep(20000);
		}
		wb.close();
		fis.close();
	}

	public void addToolToCartEmailClientMgmt(String sheetName, String userID) throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		assetListSheet = wb.getSheet(sheetName);
		XSSFRow excelRow0 = assetListSheet.getRow(1);
		XSSFCell excelCell0 = excelRow0.getCell(0);
		XSSFCell excelCell1 = excelRow0.getCell(1);
		XSSFCell excelCell2 = excelRow0.getCell(2);
		XSSFCell excelCell3 = excelRow0.getCell(3);
		excelCell0.setCellType(CellType.STRING);
		excelCell1.setCellType(CellType.STRING);
		excelCell2.setCellType(CellType.STRING);
		excelCell3.setCellType(CellType.STRING);
		client = excelCell0.getStringCellValue();
		engagement = excelCell1.getStringCellValue();
		project = excelCell2.getStringCellValue();
		product = excelCell3.getStringCellValue().trim();
		System.out.println(client + " " + engagement + " " + project);
		Thread.sleep(20000);
		this.selectMASProject(client, engagement, project);
		Thread.sleep(20000);
		txt_searchBox.clear();
		txt_searchBox.sendKeys(product);
		txt_searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(15000);
		try {
			WebElement productTileCartIcon = driver.findElement(By
					.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
							+ product
							+ "']]/following::div[@class='cart-height']/span/span[@class != 'ng-hide']/span"));
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + product + "the icon  is displayed",
					"Product Icon is displayed for " + product, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			productTileCartIcon.click();
		} catch (Exception e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + product + "the icon  is displayed",
					"Error occurred - Product icon is not displayed for " + product, TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
		Thread.sleep(10000);
		this.verifyProductTileCartIcon(product);
		this.verifyNavigatetoProductCart();
		ProductCartPage.validateConfigPending(product);
		Thread.sleep(10000);
		configClientMgmtTool(engagement, project, product, userID);
		Thread.sleep(10000);
		ProductCartPage.validateProvisionMyPlatform();
		Thread.sleep(15000);
		MySelectionPage.verifyEmailOrderStatus(product);
		wb.close();
		fis.close();
		Thread.sleep(20000);
	}

	public static void configClientMgmtTool(String engagement, String project, String product, String userID)
			throws Exception {

		switch (product) {

		case "Team Foundation Server (TFS)":
			ConfigPendingPage.configPageClientMgmtDetails_TFS(engagement);
			break;

		case "ServiceNow":
			ConfigPendingPage.configPageClientMgmtDetails_ServiceNow(project, userID);
			break;

		case "Rational CLM - RTC":
			ConfigPendingPage.configPageClientMgmtDetails_RTC_RQM_RDNG(product, userID);
			break;

		case "Rational CLM - RQM":
			ConfigPendingPage.configPageClientMgmtDetails_RTC_RQM_RDNG(product, userID);
			break;

		case "Rational CLM - RDNG":
			ConfigPendingPage.configPageClientMgmtDetails_RTC_RQM_RDNG(product, userID);
			break;

		}

	}

	public void selectMASProject(String client, String engagement, String project) throws InterruptedException {

		btn_masScopeSelector.click();
		Thread.sleep(5000);
		Select masClient = new Select(drpdown_masClient);
		Select masEngagement = new Select(drpdown_masEngagement);
		Select masProject = new Select(drpdown_masProject);
		masClient.selectByVisibleText(client);
		Thread.sleep(2000);
		masEngagement.selectByVisibleText(engagement);
		Thread.sleep(5000);
		masProject.selectByVisibleText(project);
		Thread.sleep(2000);
		btn_scopeSelectorApply.click();
	}

	public void navigateAllTab() throws InterruptedException {
		AllTab.click();
		Thread.sleep(100);

	}

	public void listViewAllTab(String Page) {
		try {
			boolean value = btn_listView.isDisplayed();
			if (value == true) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate on " + Page + "the list button  is displayed ", "Listview is clickable",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate on " + Page + "the list button  is clickable",
						"Error occurred - List view is not disaplyed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
			}
		} catch (Exception e) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + Page + "the list button  is clickable",
					"Error occured - List view is not disaplyed - " + e.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void listViewContentsALL(String Page) throws Exception {
		Boolean status = false;
		Actions build = new Actions(driver);
		new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(btn_listViewAll));
		build.click(btn_listViewAll).build().perform();
		if (gridView.size() == 0 && listView.size() > 0) {
			status = true;
		}

		if (status == true) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate on " + Page + " clicking on ListView button, the Tools are appearing in List View.",
					"The Tools are appearing in List View as required.", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("ListView is disable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate " + Page + " clicking on ListView button, the Tools are appearing in List View.",
					"Error occured - The Tools are not  appearing in List View which is incorrect.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void verifyProductTileCartIcon(String product) {

		try {
			WebElement productTileCartIcon = driver.findElement(By
					.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
							+ product
							+ "']]/following::div[@class='cart-height']/span/span[@class != 'ng-hide']/span"));

			@SuppressWarnings("rawtypes")
			ArrayList parentAttributes = (ArrayList) ((JavascriptExecutor) driver).executeScript(
					"var s = []; var attrs = arguments[0].attributes; for (var l = 0; l < attrs.length;++l) { var a = attrs[l]; s.push(a.name); } ; return s;",
					productTileCartIcon);
			int noofattribute = parentAttributes.size();
			for (int i = 0; i < noofattribute; i++) {
				String attributename = parentAttributes.get(i).toString();
				if (attributename.equals("disabled")) {
					System.out.println(productTileCartIcon.getAttribute("disabled"));
					if (productTileCartIcon.getAttribute("disabled").contains("true")) {
						System.out.println("The cart icons for the " + product + " is not visible after added to cart");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the cart icon for the " + product
										+ " - product is not visible after added to cart",
								"The cart icon for the " + product + " is not visible after added to cart",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						break;
					}
				} else if ((i == noofattribute) && (!attributename.equals("disabled"))) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the cart icon for the " + product
									+ " - product is not visible after added to cart",
							"Error occured - The cart icon for the " + product + " is visible after added to cart",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
			}
		} catch (Exception e) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the cart icon for the " + product + " - product is not visible after added to cart",
					"Error occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
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
				Thread.sleep(10000);
				if (driver
						.findElement(By
								.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div[text()[contains(., '"
										+ product
										+ "')]]/preceding-sibling::div[@class='blank-img']/img[contains(@src,'.png')]"))
						.isDisplayed()) {
					System.out.println("Product Icon is displayed for the " + product);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the product icon is displayed for the product",
							"Product Icon is displayed for the " + product, TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
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
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the product icon is displayed for the product", "Exception occured - " + e.getMessage(),
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void verfiyMySelectionPageFooterSection() {
		try {
			if (lbl_footer.isDisplayed()) {
				System.out.println("Footer is present");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Footer Section is displayed in My Selections Page",
						"Footer Section is displayed in My Selections Page", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

			}
		} catch (Exception e) {
			System.out.println("footer is not present");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Footer Section is displayed in My Selections Page",
					"Error - occured - Footer Section is not displayed in My Selections Page", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void listViewContents(String Page) throws Exception {
		Boolean status = false;
		Actions build = new Actions(driver);
		new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(btn_listView));

		build.click(btn_listView).build().perform();
		if (gridView.size() == 0 && listView.size() > 0) {
			status = true;
		}

		if (status == true) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate on " + Page + " clicking on ListView button, the Tools are appearing in List View.",
					"The Tools are appearing in List View as required.", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("ListView is disable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate " + Page + " clicking on ListView button, the Tools are appearing in List View.",
					"Error - The Tools are not  appearing in List View which is incorrect.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void gridView(String Page) throws Exception {
		Actions build = new Actions(driver);
		if (btn_gridView.getAttribute("ng-click") != null) {
			build.click(btn_gridView).build().perform();
			// btn_gridView.click();
			System.out.println("GridView is clickable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + Page + " the GridView is clickable",
					"GridView is clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("GridView is disable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + Page + " the GridView is clickable",
					"Error - GridView is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void navigateNewTab() throws InterruptedException {
		NewTab.click();
		Thread.sleep(100);

	}

	public void navigatePackageTab() throws InterruptedException {
		PackagesTab.click();
		Thread.sleep(100);

	}

	public void gridViewContents(String Page) throws Exception {
		Boolean status = false;
		Actions build = new Actions(driver);

		build.click(btn_gridView).build().perform();
		System.out.println("GridView is clickable");
		if (gridView.size() > 0 && listView.size() == 0) {
			status = true;
		}
		if (status == true) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate on " + Page + " clicking on GridView button, the Tools are appearing in Grid View.",
					"The Tools are appearing in Grid View as required.", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("GridView is disable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate " + Page + " clicking on GridView button, the Tools are appearing in Grid View.",
					"Error - The Tools are not appearing in Grid View which is incorrect.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void listView(String Page) throws Exception {
		Actions build = new Actions(driver);
		// new WebDriverWait(driver,
		// 180).until(ExpectedConditions.elementToBeClickable(btn_listView));
		if (btn_listView.getAttribute("ng-click") != null) {
			build.click(btn_listView).build().perform();
			// btn_listView.click();
			System.out.println("ListView is clickable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + Page + "the listview is clickable",
					"Listview is clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		} else {
			System.out.println("ListView is disable");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate on " + Page + "the listview is clickable",
					"Error - Listview is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void masSectionLinks() throws InterruptedException {
		int masLinksCount = driver.findElements(By.xpath("//div[@class='row page-title']/div[2]/ul/li/a")).size();
		String masHomePageURL = driver.getCurrentUrl();
		for (int i = 0; i <= masLinksCount - 1; i++) {
			masLinks = driver.findElements(By.xpath("//div[@class='row page-title']/div[2]/ul/li/a"));
			System.out.println(masLinks.get(i).getText());
			String linkText = masLinks.get(i).getText();
			switch (linkText) {
			case "Solution Inputs":
				if (linkText.contains("Solution Inputs")) {
					System.out.println("Solution Inputs Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Solution Inputs Link is displayed", "Solution Inputs Link is displayed",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					masLinks.get(i).click();
					Thread.sleep(15000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs")) {
						System.out.println("The Page Navigated to Solution Inputs");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Solution Inputs Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs",
								"Solution Inputs Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						driver.findElement(
								By.xpath("//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]"))
								.click();
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to Solution Inputs");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Solution Inputs Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs",
								"Error occured - Solution Inputs Link not navigates to https://hpdpvaluewallet.ciostage.accenture.com/solutioninputs",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Solution Inputs Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Solution Inputs Link is displayed",
							"Error occured - Solution Inputs not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;

			case "Configure Automation":
				if (linkText.contains("Configure Automation")) {
					System.out.println("Configure Automation Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Configure Automation is displayed", "Configure Automation Link is displayed",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(15000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/project")) {
						System.out.println("The Page Navigated to Configure Automation Link Found");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Configure Automation Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/project",
								"Configure Automation Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/project",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} else {
						System.out.println("The Page is not navigated to Configure Automation Link Found");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Configure Automation Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/project",
								"Error occured - Configure Automation Link not navigates to https://hpdpvaluewallet.ciostage.accenture.com/project",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Configure Automation Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Configure Automation Link is displayed",
							"Error occured - Configure Automation not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;

			case "My Selections":
				if (linkText.contains("My Selections")) {
					System.out.println("My Selections Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the My Selections Link is displayed",
							"My Selections Link is displayed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(15000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/myorders")) {
						System.out.println("The Page Navigated to My Selections");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the My Selections Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/myorders",
								"My Selections Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/myorders",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						this.verfiyMySelectionPageFooterSection();
						driver.findElement(
								By.xpath("//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]"))
								.click();
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to My Selections");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the My Selections Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/myorders",
								"Error occured - My Selections Link not navigates to https://hpdpvaluewallet.ciostage.accenture.com/myorders",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("My Selections Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the My Selections Link is displayed",
							"Error occured - My Selections not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;

			case "Feedback":
				if (linkText.contains("Feedback")) {
					System.out.println("Feedback Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Feedback Link is displayed",
							"Feedback Link is displayed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(15000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/productrating")) {
						System.out.println("The Page Navigated to Feedback");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Feedback Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/productrating",
								"Feedback Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/productrating",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						driver.findElement(
								By.xpath("//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]"))
								.click();
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to Feedback");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Feedback Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/productrating",
								"Error occured - Feedback Link not navigates to https://hpdpvaluewallet.ciostage.accenture.com/productrating",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Feedback Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Feedback Link is displayed",
							"Error occured - Feedback not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;

			case "Client Eng Specific":
				if (linkText.contains("Client Eng Specific")) {
					System.out.println("Client Eng Specific Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Client Eng Specific Link is displayed",
							"Client Eng Specific Link is displayed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(20000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/clientspecificasset")) {
						System.out.println("The Page Navigated to Client Eng Specific");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Client Eng Specific Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/clientspecificasset",
								"Client Eng Specific Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/clientspecificasset",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						driver.findElement(
								By.xpath("//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]"))
								.click();
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to Client Eng Specific");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Client Eng Specific Link is navigates to https://hpdpautomationarcade.ciostage.accenture.com/clientspecificasset",
								"Error occured - Client Eng Specific Link not navigates to https://hpdpautomationarcade.ciostage.accenture.com/clientspecificasset",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Client Eng Specific Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Client Eng Specific Link is displayed",
							"Error occured - Client Eng Specific not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;

			case "Arcade In-Flight":
				if (linkText.contains("Arcade In-Flight")) {
					System.out.println("Arcade In-Flight Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Arcade In-Flight Link is displayed", "Arcade In-Flight Link is displayed",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(15000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpautomationarcade.ciostage.accenture.com/assestList")) {
						System.out.println("The Page Navigated to Arcade In-Flight");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Arcade In-Flight Link is navigates to https://hpdpautomationarcade.ciostage.accenture.com/assestList",
								"Arcade In-Flight Link is navigates to https://hpdpautomationarcade.ciostage.accenture.com/assestList",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						driver.navigate().to(masHomePageURL);
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to Arcade In-Flight");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Arcade In-Flight Link is navigates to https://hpdpautomationarcade.ciostage.accenture.com/assestList",
								"Error occured - Arcade In-Flight Link not navigates to https://hpdpautomationarcade.ciostage.accenture.com/assestList",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Arcade In-Flight Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Arcade In-Flight Link is displayed",
							"Error occured - Arcade In-Flight not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;
			case "Admin":
				if (linkText.contains("Admin")) {
					System.out.println("Admin Link Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Admin Link is displayed",
							"Admin Link is displayed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					Thread.sleep(10000);
					masLinks.get(i).click();
					Thread.sleep(20000);
					currentUrl = driver.getCurrentUrl();
					if (currentUrl.contains("https://hpdpvaluewallet.ciostage.accenture.com/admindashboard")) {
						System.out.println("The Page Navigated to Admin");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Admin Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/admindashboard",
								"Admin Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/admindashboard",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						driver.findElement(
								By.xpath("//a[@class='tile-back text-decor-none']/span[text()[contains(., 'Back')]]"))
								.click();
						Thread.sleep(20000);
					} else {
						System.out.println("The Page is not navigated to Admin");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Admin Link is navigates to https://hpdpvaluewallet.ciostage.accenture.com/admindashboard",
								"Error occured - Admin Link not navigates to https://hpdpvaluewallet.ciostage.accenture.com/admindashboard",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else {
					System.out.println("Admin Link not Displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Admin Link is displayed",
							"Error occured - Admin not Link is displayed", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				}
				break;
			}
		}
	}

	public void verifyCategoryRightLeftButton() throws Exception {

		recommendedLnk.click();
		Thread.sleep(20000);
		btn_GridViewRec.click();
		int categoryCount = driver.findElements(By.xpath(".//div[@id='accordion']/div/div[1]/h4/a/span")).size();
		for (int i = 0; i <= categoryCount - 1; i++) {
			List<WebElement> lst_categoryLinks = driver
					.findElements(By.xpath(".//div[@id='accordion']/div/div[1]/h4/a/span"));
			String categoryLinkText = StringUtils.substringBefore(lst_categoryLinks.get(i).getText(), " (");
			System.out.println(categoryLinkText);
			switch (categoryLinkText) {
			case "Tools":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Tools')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Tools')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Tools - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Tools')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Tools')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Tools - Left Click is visible and Clickable");
				}
				break;
			case "Automation Services":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Automation Services')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Automation Services')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Automation Services - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Automation Services')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Automation Services')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Automation Services - Left Click is visible and Clickable");
				}
				break;
			case "Technology Assets":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Technology Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Technology Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Technology Assets - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Technology Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Technology Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Technology Assets - Left Click is visible and Clickable");
				}
				break;

			case "Industry Assets":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Industry Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Industry Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Industry Assets - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Industry Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Industry Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Industry Assets - Left Click is visible and Clickable");
				}
				break;

			case "Architecture Assets":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Architecture Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Architecture Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Architecture Assets - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Architecture Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Architecture Assets')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Architecture Assets - Left Click is visible and Clickable");
				}
				break;

			case "Methods & Estimators":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Methods')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Methods')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Methods - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Methods')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Methods')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Methods - Left Click is visible and Clickable");
				}
				break;

			case "Governance":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Governance')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Governance')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Governance - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Governance')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Governance')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Governance - Left Click is visible and Clickable");
				}
				break;

			case "Client Engagement Specific":
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Client Engagement Specific')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Client Engagement Specific')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='right carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Client Engagement Specific - Right Click is visible and Clickable");
				}
				Thread.sleep(5000);
				while (driver
						.findElements(By
								.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Client Engagement Specific')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
						.size() > 0) {
					driver.findElement(By
							.xpath(".//div[@ng-if='isTileView && category.ProductsCount !=0']/h4/a/span[@class='ng-binding' and contains(text(),'Client Engagement Specific')]/ancestor::*[position()=4]/div[@class='panel-collapse collapse in']/div/div/a[@class='left carousel-control']/i"))
							.click();
					Thread.sleep(1000);
					System.out.println("Client Engagement Specific - Left Click is visible and Clickable");
				}
				break;
			}

		}
	}

	public void verifyNavigatetoProductCart() throws InterruptedException {
		try {

			if (btn_cartIcon.isDisplayed()) {
				System.out.println("Cart Icon is displayed");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Cart Icon is displayed",
						"Cart Icon is displayed", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				btn_cartIcon.click();
				Thread.sleep(15000);
				String currentURL = driver.getCurrentUrl();
				if (currentURL.contains("https://hpdpvaluewallet.ciostage.accenture.com/checkout")) {
					System.out.println("Cart Icon is clickable and navigates to Cart Page");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate the Cart Icon is clickable and navigates to Cart Page",
							"Cart Icon is clickable and navigates to Cart Page", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				} else {
					System.out.println("Cart Icon is not clickable");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Cart Icon is clickable",
							"Error occured - Cart Icon is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				}
			}

		} catch (Exception e) {

			System.out.println("Cart Icon is not displayed");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Validate the Cart Icon is Present",
					"Error occured - Cart Icon is Present", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void verifyToolInCart(String provisionType) throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		List<WebElement> display_tools = driver.findElements(By.xpath(
				".//div[@class='panel-body']/div/div/div/a[@ng-click='navigateToProductDetails(product.ProductId)']"));
		if (display_tools.size() > 1) {
			for (int i = 1; i <= st.getLastRowNum(); i++) {
				XSSFRow excelRow = st.getRow(i);
				XSSFCell excelCell = excelRow.getCell(0);
				excelCell.setCellType(CellType.STRING);
				String product = excelCell.getStringCellValue().trim();
				for (int j = 0; j <= display_tools.size(); j++) {
					String visible_tools = display_tools.get(j).getText();
					if (product.contains(visible_tools)) {
						System.out.println(product + "Is Found in Cart");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Added product - " + product + " is found in Cart",
								"Added product - " + product + " is found in Cart", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						break;
					}
					if ((j == display_tools.size()) && (!product.contains(visible_tools))) {
						System.out.println(product + "Not Found in Cart");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the Added product - " + product + " is found in Cart",
								"Added product - " + product + " is not found in Cart", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}

				}
			}
		}
		wb.close();
		fis.close();
	}
}
