package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.helper.DataConfig;
import com.mas.TestCases.TC_MAS_Login;

import reusablefunction.reusefunction;

public class ConfigRecommendedPage {
	DataConfig df;
	public static int total;
	public static int number;
	public String text;
	
	HashMap<String, String> projectData;
	reusefunction objreusable;
	CartPage objCart;
	WebDriver driver;
	String pagenextbutton;
	public int attempts = 0;
	String textLink;
	public static String  TagNametext;
	  List<String> list = new ArrayList<>();

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[2]/img")
	@CacheLookup
	WebElement GridView;

	@FindBy(how = How.XPATH, using = "//div[@class='row margin-15-t']/div/ul/li/a[text()='>']")
	@CacheLookup
	WebElement pagenextbuttonclass;

	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[3]/img")
	@CacheLookup
	WebElement ListView;

	@FindBy(how = How.XPATH, using = "//span[@id='submitButton']")
	@CacheLookup
	WebElement SignIn;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	WebElement CartIcon;

	@FindBy(how = How.XPATH, using = "//span[@class='text-decor-line margin-l--2' and contains(text(),'Back')]")
	@CacheLookup
	WebElement Back;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue Shopping')]")
	@CacheLookup
	WebElement btn_continueshopping;
	

	@CacheLookup
	WebElement btnAll;

	@FindBy(how = How.XPATH, using = "//div/input[@placeholder='Search here']")
	WebElement txt_searchBox;
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Ok')]")
	WebElement btn_Ok;

	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	WebElement btn_cartIcon;
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Solution Inputs')]")
	WebElement btn_SolutionInput;

	List<WebElement> category;

	public ConfigRecommendedPage(WebDriver driver) {
		this.driver = driver;
	}

	public void listview() throws Exception {
		new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(ListView));
		if (ListView.getAttribute("ng-click") != null) {
			ListView.click();
			System.out.println("ListView is clickable whereas Gridview is disable");

		} else {
			System.out.println("ListView is disable Select GridView");
		}

	}

	public void gridview() throws Exception {
		if (GridView.getAttribute("ng-click") != null) {
			GridView.click();
			System.out.println("GridView is clickable whereas Listview is disable");
		} else {
			System.out.println("GridView is disable Select ListView");
		}
	}

	public void carticon() {
		if (CartIcon.isDisplayed()) {
			System.out.println("Cart Icon is Present");
			CartIcon.click();
			System.out.println("Cart Icon is clicable Navigated to Cart Page");
		} else {
			System.out.println("Cart Icon is disable not Navigated to Cart Page");
		}
	}

	public void backbtn() throws Exception {

		if (Back.isEnabled()) {
			Back.click();
			System.out.println("Back to the Automation_Store HomePage");

			Thread.sleep(1000);

		} else {
			System.out.println("Not able to go to back button");
		}
	}

	public void addToolToCart(String provisionType) throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		for (int i = 1; i <= st.getLastRowNum(); i++) {

			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell = excelRow.getCell(0);
			excelCell.setCellType(CellType.STRING);
			String product = excelCell.getStringCellValue();
			txt_searchBox.sendKeys(product);
			System.out.println(product);
			txt_searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(10000);
			WebElement productTileCartIcon = driver.findElement(By
					.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[normalize-space() = '"
							+ product
							+ "']/following::div[@class='cart-height']/span/span[@ng-show='product.DeliveryModeEnabled != 1']/span"));
			productTileCartIcon.click();

			Thread.sleep(10000);
			txt_searchBox.clear();
			Thread.sleep(10000);
		}
		wb.close();
		fis.close();

	}

	public void addDependentToolToCart(String provisionType) throws Exception {

		this.objCart = PageFactory.initElements(driver, CartPage.class);
		File excelfilename = new File(
				"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\Product Relationship V2.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		int row = st.getLastRowNum();
		loop: for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(0).setCellType(CellType.STRING);
			st.getRow(i).getCell(1).setCellType(CellType.STRING);
			st.getRow(i).getCell(2).setCellType(CellType.STRING);

			XSSFCell category = st.getRow(i).getCell(0);
			String product = category.getStringCellValue();
			XSSFCell Related = st.getRow(i).getCell(1);
			String Relationship = Related.getStringCellValue();
			XSSFCell dependentProduct = st.getRow(i).getCell(2);
			String dependentProducttext = dependentProduct.getStringCellValue();
			System.out.println(Relationship);
			if (Relationship.contains("Dependent")) {

				txt_searchBox.sendKeys(product);
				System.out.println(product);
				txt_searchBox.sendKeys(Keys.ENTER);
				Thread.sleep(15000);
				try {
					WebElement productTileCartIcon = driver.findElement(By
							.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[normalize-space() = '"
									+ product
									+ "']/following::div[@class='cart-height']/span/span[@ng-show='product.DeliveryModeEnabled != 1']/span"));
					Thread.sleep(10000);
					productTileCartIcon.click();
					Thread.sleep(6000);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Verify that the Product - " + product + "is present and the cart button is Present" ,
							"Required Product " + product
									+ " is  Present  while searching and the cart button is clicked successfully",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
				}
				catch(NoSuchElementException e){
					if(driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()){
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the Product - " + product + "is present "
										,
								"Error Occured-Required Product " + product
										+ " is  not Present " ,
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}
				}
				catch(Exception e){
					if(!driver.findElements(By.xpath("//span/b")).isEmpty()){
						String getStatus=driver.findElement(By.xpath("//span/b")).getText();
						System.out.println(getStatus);
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the Product - " + product + "is present "
										,
								"Error Occured-Required Product " + product
										+ " is  Present with the status as "+getStatus ,
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}
					else{
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the Product - " + product + "is present "
										,
								"Error Occured-Required Product " + product
										+ " is  not Present " ,
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}
				
				}
					try{WebElement Popuptext = driver.findElement(By.xpath("//div/strong"));
					String PopUpText = Popuptext.getText();

					if (PopUpText.contains("The product(s) listed below are pre-requisites ")) {
						WebElement PopUpProduct = driver
								.findElement(By.xpath("//div/div[@ng-repeat='product in products']"));
						String PopUpProductText = PopUpProduct.getText();
						System.out.println(PopUpProductText + "" + dependentProducttext);
						if (PopUpProductText.contains(dependentProducttext)) {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the  Dependent Product - " + dependentProducttext + "is present in the Application" ,
									"Required Dependent Product " + dependentProducttext
											+ " is  Present  in the application",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
							if(!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty())
							{
								driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the  Pop Up is Displayed for the Product - " + product  ,
									"Required Pop Up is Displayed for the Product - " + product,
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							
								Thread.sleep(10000);
								txt_searchBox.clear();
								Thread.sleep(10000);
								this.carticon();}
							else{
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults(
										"Verify that the  Pop Up is Displayed for the Product - " + product
												,
										"Error Occured-Required Pop Up is not Displayed for the Product - " + product+
												" While clicking on the cart icon." ,
										TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								continue loop;
							}
						} else {

						}
					} else {
						Thread.sleep(10000);
						WebElement RadioCheckProduct = driver.findElement(By.xpath("//div/span[contains(.,'"
								+ dependentProducttext + "')]/ancestor::div/div[@class='row']/div/input"));
						RadioCheckProduct.click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the  Dependent Product - " + dependentProducttext + "is present in the Application" ,
								"Required Dependent Product " + dependentProducttext
										+ " is  Present  in the application radio button is clicked",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
						if(!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty())
						{
							driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that the  Pop Up is Displayed for the Product - " + product  ,
								"Required Pop Up is Displayed for the Product - " + product,
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						
							Thread.sleep(10000);
							txt_searchBox.clear();
							Thread.sleep(10000);
							this.carticon();}
						else{
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the  Pop Up is Displayed for the Product - " + product
											,
									"Error Occured-Required Pop Up is not Displayed for the Product - " + product+
											" While clicking on the cart icon." ,
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							continue loop;
						}
						Thread.sleep(10000);
						txt_searchBox.clear();
						Thread.sleep(10000);
						this.carticon();

					}}catch(Exception e){
						continue loop;
					}

					objCart.validateDependendProduct(product, dependentProducttext);

			 
			}
			wb.close();
			fis.close();

		}
	}

	public void verifyNavigatetoProductCart() throws InterruptedException {
		if (btn_cartIcon.isDisplayed()) {
			System.out.println("Cart Icon is displayed");
			Thread.sleep(10000);
			btn_cartIcon.click();
			Thread.sleep(15000);
			String currentURL = driver.getCurrentUrl();
			if (currentURL.contains("https://hpdpvaluewallet.ciostage.accenture.com/checkout")) {
				System.out.println("Cart Icon is clickable and navigates to Cart Page");

			} else {
				System.out.println("Cart Icon is not clickable");

			}
		} else {
			System.out.println("Cart Icon is not displayed");

		}
	}

	public void verifyToolInCart(String provisionType) throws Exception {
		File excelfilename = new File(
				"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
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
				String product = excelCell.getStringCellValue();
				Thread.sleep(10000);
				for (int j = 0; j <= display_tools.size(); j++) {
					String visible_tools = display_tools.get(j).getText();
					Thread.sleep(10000);
					if (product.contains(visible_tools)) {
						System.out.println(product + "Is Found in Cart");

						break;
					}
					Thread.sleep(10000);
					if ((j == display_tools.size()) && (!product.contains(visible_tools))) {
						System.out.println(product + "Not Found in Cart");

					}

				}
			}
		}
		wb.close();
		fis.close();
	}

	public void validateRefineByLinks() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);
	
				btnAll.click();
				Thread.sleep(20000);
				int refineByLinkssize = driver
						.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']")).size();


				for (int m = 0; m < refineByLinkssize; m++) {
					List<WebElement> refineByLinks = driver
							.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

					String getLink = StringUtils.trim(refineByLinks.get(m).getText());

					switch (getLink) {

	/*	case "Business Function": {
						objreusable.javascripttoggle1(getLink);
						int linkValue = driver
								.findElements(By
										.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
												+ getLink
												+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
								.size();

						for (int k = 0; k < linkValue; k++)

						{
							List<WebElement> linksValue = driver.findElements(By
									.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
											+ getLink
											+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

							String getLink2 = linksValue.get(k).getText();
							String text = getLink2;
							text = text.substring(1, text.indexOf("(") - 1);
							text = StringUtils.trim(text);
							System.out.println(text);
								objreusable.refresh(getLink, text,k);
							Thread.sleep(20000);
							int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
							for (int j = 0; j < tablesize; j++) {
								
								List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
								String textLink = table.get(j).getText();
								String tabletext = textLink;
								tabletext = tabletext.substring(0, tabletext.indexOf("("));
								tabletext = StringUtils.trim(tabletext);
								

								System.out.println(tabletext);
								Thread.sleep(1000);
								
								
								driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]"))
										.click();
								Thread.sleep(15000);
								
								objreusable.list();
								Thread.sleep(10000);
								category = driver.findElements(
										By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
								
								int counter=1;
								loop: for (int i = 0; i <= category.size() + 1; i++) {
									Thread.sleep(2000);
									
									if (i <= category.size() - 1) {
										pagenextbutton = driver
												.findElement(By
														.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
												.getAttribute("class");
										String categorytext = category.get(i).getText();
										System.out.println(categorytext);
										// Thread.sleep(10000);
										if (objreusable.validatebyexcel(getLink, text, categorytext,
												tabletext) == true) {
											TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Product - " +categorytext+ "is present under -" + text,
													"Required Product " +categorytext+ " is  Present and  matching whit the Input Data under -" + text+" and under -"+getLink+" Category" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
											continue loop;

										} else {
											// Thread.sleep(20000);
											if (i <= category.size()) {

												Thread.sleep(2000);
												TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
												TC_MAS.df.WritingToExcelResults(
														"Verify that the Product - " +categorytext+ "is present under -" + text,
														"Error Occured-Required Product " +categorytext+ " is  not Present under -" + text ,
														TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
												System.out.println("tool not found-->" + categorytext);

												continue loop;

											}
										} Thread.sleep(20000);
									} else if ((!pagenextbutton.contains("disabled"))) {
										counter=counter+1;
										objreusable.refreshpage(getLink,text,tabletext,btnAll);
										 Thread.sleep(20000);
										driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='"+counter+"']")).click();
										 Thread.sleep(20000);
										 TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Next Page is present and clicable -",
													"Navigated to the Page "+counter +" Continue the search" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
										category = driver.findElements(
												By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
										i = -1;
										System.out.println(i);

									}

								}
							}

							

						}
						objreusable.javascripttoggle1(getLink);
						break;
					}*/
/*
					case "Delivery Type": {

						objreusable.javascripttoggle1(getLink);
						int linkValue = driver
								.findElements(By
										.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
												+ getLink
												+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
								.size();

						for (int k = 0; k < linkValue; k++) {
							List<WebElement> linksValue = driver.findElements(By
									.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
											+ getLink
											+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

						
							String getLink2 = linksValue.get(k).getText();
							String text = getLink2;
							text = text.substring(1, text.indexOf("(") - 1);
							text = StringUtils.trim(text);
							System.out.println(text);
								objreusable.refresh(getLink, text,k);
					
							Thread.sleep(20000);
							int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
							for (int j = 0; j < tablesize; j++) {

								List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
								String textLink = table.get(j).getText();
								String tabletext = textLink;
								tabletext = tabletext.substring(0, tabletext.indexOf("("));
								tabletext = StringUtils.trim(tabletext);
								System.out.println(tabletext);
								driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'"+tabletext+"')]")).click();
								Thread.sleep(1000);
								
								objreusable.list();
								category = driver.findElements(
										By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
							
								Thread.sleep(10000);
								int counter=1;
								loop: for (int i = 0; i <= category.size() + 1; i++) {
									if (i <= category.size() - 1) {
										pagenextbutton = driver
												.findElement(By
														.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
												.getAttribute("class");
										String categorytext = category.get(i).getText();
										System.out.println(categorytext);
										if (objreusable.validatebyexcel(getLink, text, categorytext,
												tabletext) == true) {
											TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Product - " +categorytext+ "is present under -" + text,
													"Required Product " +categorytext+ " is  Present and  matching whit the Input Data under -" + text+" and under -"+getLink+" Category" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
											continue loop;
										} else {
											// Thread.sleep(20000);
											if (i <= category.size()) {

												Thread.sleep(2000);
												TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
												TC_MAS.df.WritingToExcelResults(
														"Verify that the Product - " +categorytext+ "is present under -" + text,
														"Error Occured-Required Product " +categorytext+ " is  not Present under -" + text ,
														TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
												System.out.println("tool not found" + categorytext);

												continue loop;

											}
										}
									} else if ((!pagenextbutton.contains("disabled"))) {

										counter=counter+1;
										objreusable.refreshpage(getLink,text,tabletext,btnAll);
										 Thread.sleep(20000);
										driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='"+counter+"']")).click();
										 Thread.sleep(20000);
										 TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Next Page is present and clicable -",
													"Navigated to the Page "+counter +" Continue the search" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
										category = driver.findElements(
												By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
										i = 0;
										System.out.println(i);
									
									}
								}
							}
							
							Thread.sleep(2000);
						}
						objreusable.javascripttoggle1(getLink);
						break;
					}*/

					case "Industry Segment": {

						objreusable.javascripttoggle1(getLink);
						int linkValue = driver
								.findElements(By
										.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
												+ getLink
												+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
								.size();

						for (int k = 0; k < linkValue; k++)

						{
							List<WebElement> linksValue = driver.findElements(By
									.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
											+ getLink
											+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

							String getLink2 = linksValue.get(k).getText();
							String text = getLink2;
							text = text.substring(1, text.indexOf("(") - 1);
							text = StringUtils.trim(text);
							System.out.println(text);
							objreusable.refresh(getLink, text,k);
							Thread.sleep(10000);
							int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
							for (int j = 0; j < tablesize; j++) {

								List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
								String textLink = table.get(j).getText();
								String tabletext = textLink;
								tabletext = tabletext.substring(0, tabletext.indexOf("("));
								tabletext = StringUtils.trim(tabletext);
								System.out.println(tabletext);
								driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'"+tabletext+"')]")).click();
								Thread.sleep(1000);
								objreusable.list();
								Thread.sleep(10000);
								category = driver.findElements(
										By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
								int counter=1;
								loop: for (int i = 0; i <= category.size() + 1; i++) {
									if (i <= category.size() - 1) {
										pagenextbutton = driver
												.findElement(By
														.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
												.getAttribute("class");
										String categorytext = category.get(i).getText();
										System.out.println(categorytext);
										if (objreusable.validatebyexcel(getLink, text, categorytext,
												tabletext) == true) {
											TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
											TC_MAS_Login.resultDataConfig.WritingToExcelResults(
													"Verify that the Product - " +categorytext+ "is present under -" + text,
													"Required Product " +categorytext+ " is  Present and  matching whit the Input Data under -" + text+" and under -"+getLink+" Category" ,
													TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
											continue loop;
										} else {
											// Thread.sleep(20000);
											if (i <= category.size()) {

												
												 Thread.sleep(2000);
												 TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
													TC_MAS_Login.resultDataConfig.WritingToExcelResults(
															"Verify that the Product - " +categorytext+ "is present under -" + text,
															"Error Occured-Required Product " +categorytext+ " is  not Present under -" + text ,
															TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
												System.out.println("tool not found" + categorytext);

												continue loop;

											}
										}
									} else if ((!pagenextbutton.contains("disabled"))) {
										counter=counter+1;
										objreusable.refreshpage(getLink,text,tabletext,btnAll);
										 Thread.sleep(20000);
										driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='"+counter+"']")).click();
										 Thread.sleep(20000);
										 TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
											TC_MAS_Login.resultDataConfig.WritingToExcelResults(
													"Verify that the Next Page is present and clicable -",
													"Navigated to the Page "+counter +" Continue the search" ,
													TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										category = driver.findElements(
												By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
										i = -1;
										System.out.println(i);


										continue loop;
									}

								}
							}

							
							Thread.sleep(10000);
						}
						objreusable.javascripttoggle1(getLink);
						break;
					}

					/*case "Delivery Function": {
						objreusable.javascripttoggle1(getLink);
						objreusable.javascripttoggle2(getLink);
						int linkValue = driver
								.findElements(By
										.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
												+ getLink
												+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
								.size();

						for (int k = 0; k < linkValue; k++)

						{
							List<WebElement> linksValue = driver.findElements(By
									.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
											+ getLink
											+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

							String getLink2 = linksValue.get(k).getText();
							String text = getLink2;
							text = text.substring(1, text.indexOf("(") - 1);
							text = StringUtils.trim(text);
							System.out.println(text);
							objreusable.refreshForCertainCategory(getLink,text,k);
							Thread.sleep(10000);
							int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
							for (int j = 0; j < tablesize; j++) {

								List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
								String textLink = table.get(j).getText();
								String tabletext = textLink;
								tabletext = tabletext.substring(0, tabletext.indexOf("("));
								tabletext = StringUtils.trim(tabletext);
								System.out.println(tabletext);
								driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'"+tabletext+"')]")).click();
								Thread.sleep(1000);
								objreusable.list();
								category = driver.findElements(
										By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
								
								Thread.sleep(10000);
								int counter=1;
								loop: for (int i = 0; i <= category.size() + 1; i++) {
									if (i <= category.size() - 1) {
										pagenextbutton = driver
												.findElement(By
														.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
												.getAttribute("class");
										String categorytext = category.get(i).getText();
										System.out.println(categorytext);
										if (objreusable.validatebyexcel(getLink, text, categorytext,
												tabletext) == true) {
											TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Product - " +categorytext+ "is present under -" + text,
													"Required Product " +categorytext+ " is  Present and  matching whit the Input Data under -" + text+" and under -"+getLink+" Category" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
											continue loop;
										} else {
											Thread.sleep(200);
											if (i <= category.size()) {

												Thread.sleep(2000);
												TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
												TC_MAS.df.WritingToExcelResults(
														"Verify that the Product - " +categorytext+ "is present under -" + text,
														"Error Occured-Required Product " +categorytext+ " is  not Present under -" + text ,
														TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
												System.out.println("tool not found" + categorytext);

												continue loop;

											}
										}
									} else if ((!pagenextbutton.contains("disabled"))) {
										counter=counter+1;
										objreusable.refreshpage(getLink, text, tabletext, btnAll);
										
										 Thread.sleep(20000);
										driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='"+counter+"']")).click();
										 Thread.sleep(20000);
										 TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Next Page is present and clicable -",
													"Navigated to the Page "+counter +" Continue the search" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
										category = driver.findElements(
												By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
										i = -1;
										System.out.println(i);

									}

								}
							}
						
						}
						objreusable.javascripttoggle2(getLink);
						objreusable.javascripttoggle1(getLink);
					
						break;
					}
				
					case "Industry Filters": {
						objreusable.javascripttoggle1(getLink);
						objreusable.javascripttoggle2(getLink);
						int linkValue = driver
								.findElements(By
										.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
												+ getLink
												+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
								.size();

						for (int k = 0; k < linkValue; k++)

						{
							List<WebElement> linksValue = driver.findElements(By
									.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
											+ getLink
											+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

							String getLink2 = linksValue.get(k).getText();
							String text = getLink2;
							text = text.substring(1, text.indexOf("(") - 1);
							text = StringUtils.trim(text);
							System.out.println(text);

							objreusable.refreshForCertainCategory(getLink,text,k);
							Thread.sleep(10000);
							int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
							for (int j = 0; j < tablesize; j++) {

								List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
								String textLink = table.get(j).getText();
								String tabletext = textLink;
								tabletext = tabletext.substring(0, tabletext.indexOf("("));
								tabletext = StringUtils.trim(tabletext);
								System.out.println(tabletext);
								driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'"+tabletext+"')]")).click();
								Thread.sleep(1000);
								objreusable.list();
								Thread.sleep(10000);
								category = driver.findElements(
										By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
								int counter=1;
								loop: for (int i = 0; i <= category.size() + 1; i++) {
									if (i <= category.size() - 1) {
										pagenextbutton = driver
												.findElement(By
														.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
												.getAttribute("class");
										String categorytext = category.get(i).getText();
										System.out.println(categorytext);
										if (objreusable.validatebyexcel(getLink, text, categorytext,
												tabletext) == true) {
											TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Product - " +categorytext+ "is present under -" + text,
													"Required Product " +categorytext+ " is  Present and  matching whit the Input Data under -" + text+" and under -"+getLink+" Category" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
											continue loop;
										} else {
											
											if (i <= category.size()) {

												Thread.sleep(2000);
												TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
												TC_MAS.df.WritingToExcelResults(
														"Verify that the Product - " +categorytext+ "is present under -" + text,
														"Error Occured-Required Product " +categorytext+ " is  not Present under -" + text ,
														TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
												System.out.println("tool not found" + categorytext);

												continue loop;

											}
										}
									}
									else if ((!pagenextbutton.contains("disabled"))) {
										counter=counter+1;
										objreusable.refreshpage(getLink, text, tabletext, btnAll);
										 Thread.sleep(20000);
										 
										driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='"+counter+"']")).click();
										 Thread.sleep(20000);
										 TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
											TC_MAS.df.WritingToExcelResults(
													"Verify that the Next Page is present and clicable -",
													"Navigated to the Page "+counter +" Continue the search" ,
													TC_MAS.rowNumber, TC_MAS.workBook, TC_MAS.outputWorkSheet, false);
										
										category = driver.findElements(
												By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
										i = -1;
										System.out.println(i);
										}
								}

							}
							
						}
						objreusable.javascripttoggle2(getLink);
						objreusable.javascripttoggle1(getLink);
					
						break;

					}*/
					}
					
					
				}
			}
public void verifytoolsdetails() throws Exception{
	
	File excelfilename = new File(
			"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\ProductList.xlsx");

	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet("BasicInfo");
	int row = st.getLastRowNum();
	for (int i = 1; i <= row; i++) {

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
		Thread.sleep(10000);
		WebElement productTile = driver.findElement(By
				.xpath("//div/h3[contains(.,'"+product+"')]"));
		Thread.sleep(10000);
		
			productTile.click();
			Thread.sleep(10000);
		String CurrentURL=	driver.getCurrentUrl();
		if(CurrentURL.contains("https://hpdpvaluewallet.ciostage.accenture.com/product/")){
		
			WebElement ProductDisplay = driver.findElement(By.xpath("//h4/big"));
		String ProductDisplayedText=ProductDisplay.getText();
		
		if(ProductDisplayedText.contains(product)){
			this.tag(product,list);
			List<WebElement> ProductTags=driver.findElements(By.xpath("//p/span/a"));
			int TagsSize=ProductTags.size();
			loop:for (int j=0;j<TagsSize;j++) {
				if(j<TagsSize-1){
				String getProductTags =ProductTags.get(j).getText();	
				getProductTags=getProductTags.substring(0, getProductTags.indexOf("|")-1);
			
				for (String webElement2 : list) {
					if(getProductTags.contains(webElement2)){
						System.out.println(getProductTags+ " Found");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Verify that Tags Present for the Product -  " ,
								"Required Tag "+getProductTags+" for the Product " + product
										+ " is Present",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
						continue loop;
					}
				}
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify that Tags Present for the Product -  " ,
						"Error Occured-Required Tag "+getProductTags+" for the Product " + product
								+ " is not Present and Matching from the Excel sheet",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
				}
				else{
					String getProductTags =ProductTags.get(j).getText();
					getProductTags=getProductTags.trim();
					for (String webElement2 : list) {
						if(getProductTags.contains(webElement2)){
							System.out.println(getProductTags+ " Found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that Tags Present for the Product -  " ,
									"Required Tag "+getProductTags+" for the Product " + product
											+ " is Present",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
							continue loop;
						}
					}
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Verify that Tags Present for the Product -  " ,
							"Error Occured-Required Tag "+getProductTags+" for the Product " + product
									+ " is not Present and Matching from the Excel sheet",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);	
					
				}
			
			}
			WebElement ProductShortDescription=driver.findElement(By.xpath("//div/h4/following-sibling::p[1]"));
			String getShotDescription=ProductShortDescription.getText();
			WebElement ProductLongDescription=driver.findElement(By.xpath("//span[@ng-bind-html='product.DetailedDescription']"));
			String getProductLongDescription=ProductLongDescription.getText();
			if(DetailedDescriptiontext.contains(getProductLongDescription)){
				System.out.println(getProductLongDescription);
				
			}else{
				System.out.println(getProductLongDescription+" Not Matching");
			}
			WebElement ProductVendorDetails=driver.findElement(By.xpath("//div/span[@ng-show='product.VendorName != null']"));
			String getProductVendorText=ProductVendorDetails.getText();
			getProductVendorText=getProductVendorText.substring(getProductVendorText.indexOf(":")+2);
			System.out.println(getProductVendorText);
			if(vendordetailstext.contains(getProductVendorText)){
				System.out.println(getProductVendorText);
				
			}
			else{
				System.out.println(getProductVendorText+" Not Matching");
			}
			if(ShortDescriptiontext.contains(getShotDescription)){
				System.out.println(getShotDescription);
				
			
			
			}else{
				System.out.println(getShotDescription+" Not Matching");
			}
			Thread.sleep(10000);
			try{driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();
			
			}catch(Exception e){
				btn_continueshopping.click();
			}
			Thread.sleep(20000);
		}
		else{
			System.out.println("Not Matching");
		}
		
		}
		
		
	
}
	fis.close();
wb.close();
}
public List<String> tag(String product, List<String> list2) throws Exception
{	list.removeAll(list2);
	File excelfilename = new File(
			"C:\\Users\\gaurav.b.kapoor\\workspace\\AutomationStore\\src\\com\\InputFiles\\ProductList.xlsx");

	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet("Tags");
	int row = st.getLastRowNum();
	loop:for (int i = 1; i <= row; i++) {

		
		st.getRow(i).getCell(1).setCellType(CellType.STRING);
		st.getRow(i).getCell(2).setCellType(CellType.STRING);

	
		XSSFCell product1 = st.getRow(i).getCell(1);
		String productname = product1.getStringCellValue();
		XSSFCell TagName = st.getRow(i).getCell(2);
		 TagNametext = TagName.getStringCellValue();
	if(product.contains(productname)){
		
	    list.add(TagNametext);
	   
	 
continue loop;
	    	}
	
	}
	fis.close();
	wb.close();
	return list2;


}
	
	}
	

