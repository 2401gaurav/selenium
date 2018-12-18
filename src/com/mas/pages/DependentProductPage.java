package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.mas.TestCases.TC_MAS_Login;


public class DependentProductPage {
	List<String> list = new ArrayList<>();
	public static String TagNametext;
	public static String RelatedProductText;
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div/div[1]/div/div[1]/div[3]/div/span[2]/a/img")
	WebElement CartIcon;

	@FindBy(how = How.XPATH, using = "//div/input[@placeholder='Search here']")
	WebElement txt_searchBox;
	
	@FindBy(how = How.XPATH, using = "//button[text()[normalize-space() = 'Save']]")
	static WebElement btn_saveConfig;

	@FindBy(how = How.XPATH, using = "//button[text()[normalize-space() = 'Go to cart']]")
	static WebElement btn_goToCart;

	@FindBy(how = How.XPATH, using = ".//button[text()='Ok']")
	static WebElement btn_popupOk;
	DependentProductPage objDependentProduct;
	public int counter;
	public static List<WebElement> RelatedProduct;
	static WebDriver driver;
	static WebElement inputDetails;
	ProductCartPage objProductCartPage;
	MySelectionPage objMySelectionPage;

	public DependentProductPage(WebDriver driver) {
		DependentProductPage.driver = driver;
		this.objProductCartPage = PageFactory.initElements(driver, ProductCartPage.class);
		this.objMySelectionPage = PageFactory.initElements(driver, MySelectionPage.class);
	}


	public void addDependentToolToCart(String provisionType) throws Exception {

		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\Product Relationship V2.xlsx");
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
				txt_searchBox.clear();
				txt_searchBox.sendKeys(product);
				System.out.println(product);
				txt_searchBox.sendKeys(Keys.ENTER);
				Thread.sleep(25000);
				try {
					WebElement productTileCartIcon = driver
							.findElement(By
									.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[normalize-space() = '"
											+ product
											+ "']/following::div[@class='cart-height']/span/span[@ng-show='product.DeliveryModeEnabled != 1']/span"));
					Thread.sleep(7000);
					productTileCartIcon.click();
					Thread.sleep(6000);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product
							+ "is present and the cart button is Present", "Required Product " + product
							+ " is  Present  while searching and the cart button is clicked successfully",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				} catch (NoSuchElementException e) {
					if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}
				} catch (Exception e) {
					if (!driver.findElements(By.xpath("//span/b")).isEmpty()) {
						String getStatus = driver.findElement(By.xpath("//span/b")).getText();
						System.out.println(getStatus);
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  Present with the status as "
										+ getStatus, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					} else {
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}

				}
				try {
					WebElement Popuptext = driver.findElement(By.xpath("//div/strong"));
					String PopUpText = Popuptext.getText();

					if (PopUpText.contains("The product(s) listed below are pre-requisites ")) {
						WebElement PopUpProduct = driver.findElement(By
								.xpath("//div/div[@ng-repeat='product in products']"));
						String PopUpProductText = PopUpProduct.getText();
						System.out.println(PopUpProductText + "" + dependentProducttext);
						if (PopUpProductText.contains(dependentProducttext)) {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - "
									+ dependentProducttext + "is present in the Application",
									"Required Dependent Product " + dependentProducttext
											+ " is  Present  in the application", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
									TC_MAS_Login.outputWorkSheet, false);
							if (!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty()) {
								driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults(
										"Verify that the  Pop Up is Displayed for the Product - " + product,
										"Required Pop Up is Displayed for the Product - " + product, TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

								Thread.sleep(8000);
								txt_searchBox.clear();
								Thread.sleep(8000);
								this.carticon();
							} else {
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults(
										"Verify that the  Pop Up is Displayed for the Product - " + product,
										"Error Occured-Required Pop Up is not Displayed for the Product - " + product
												+ " While clicking on the cart icon.", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								continue loop;
							}
						} else {

						}
					} else {
						Thread.sleep(5000);
						WebElement RadioCheckProduct = driver.findElement(By.xpath("//div/span[contains(.,'"
								+ dependentProducttext + "')]/ancestor::div/div[@class='row']/div/input"));
						RadioCheckProduct.click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - " + dependentProducttext
								+ "is present in the Application", "Required Dependent Product " + dependentProducttext
								+ " is  Present  in the application radio button is clicked", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						if (!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty()) {
							driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Pop Up is Displayed for the Product - "
									+ product, "Required Pop Up is Displayed for the Product - " + product,
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

							Thread.sleep(4000);
							txt_searchBox.clear();
							Thread.sleep(10000);
							this.carticon();
						} else {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Pop Up is Displayed for the Product - "
									+ product, "Error Occured-Required Pop Up is not Displayed for the Product - "
									+ product + " While clicking on the cart icon.", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
									TC_MAS_Login.outputWorkSheet, false);
							continue loop;
						}
						

					}
				} catch (Exception e) {
					continue loop;
				}
				Thread.sleep(5000);
				this.validateDependendProduct(product, dependentProducttext);

			}
			wb.close();
			fis.close();

		}
	}

	public void validateDependendProduct(String product, String DependentProduct) throws Exception {

		Thread.sleep(10000);

		if (driver
				.findElement(
						By.xpath("//div/a[contains(.,normalize-space('" + DependentProduct
								+ "'))]/ancestor::div/div/button")).getAttribute("disabled").equalsIgnoreCase("true")) {

			String getattribute = driver.findElement(
					By.xpath("//div/a[contains(.,normalize-space('" + DependentProduct
							+ "'))]/ancestor::div/div/button")).getAttribute("disabled");

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - " + DependentProduct
					+ "is present in the Cart Page ", "Required Dependent Product " + DependentProduct
					+ " is  Present  in the Cart Page containing Remove Button as Disable", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			System.out.println("Dependent Product is automatically added into the list " + DependentProduct + " "
					+ getattribute);

			if (driver.findElement(By.xpath("//div/a[contains(.,normalize-space('" + product + "'))]")).isDisplayed()) {
				System.out.println(product + " is displayed");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Product - " + product
						+ "is present in the Cart Page ", "Required  Product " + product
						+ " is  Present  in the Cart Page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);

				driver.findElement(
						By.xpath("//div/a[contains(.,'" + product + "')]/following::div/button[contains(.,'Remove')]"))
						.click();
				WebElement btn_Removepopup = driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]"));
				btn_Removepopup.click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Product - " + product
						+ "is removed from the cart Page ", "Required  Product " + product
						+ " is  Successfully removed from the Cart Page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				Thread.sleep(20000);

				driver.findElement(
						By.xpath("//div/a[contains(.,'" + DependentProduct
								+ "')]/following::div/button[contains(.,'Remove')]")).click();
				driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Dependent Product - " + DependentProduct
						+ "is removed from the cart Page ", "Required Dependent Product " + DependentProduct
						+ " is  Successfully removed from the Cart Page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
				Thread.sleep(15000);
				driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify that the   Clicking on Continue Shopping Button Navigate to the MAS HomePage  ",
						" Successfully Navigated to the MAS HomePage from the Cart Page", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				Thread.sleep(20000);

			}
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
	public void VerifyIncompatibleProduct(String provisionType) throws Exception {
		this.objDependentProduct = PageFactory.initElements(driver, DependentProductPage.class);
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\Product Relationship V2.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		int row = st.getLastRowNum();
		loop: for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(0).setCellType(CellType.STRING);
			st.getRow(i).getCell(1).setCellType(CellType.STRING);
			

			XSSFCell product = st.getRow(i).getCell(0);
			String producttext = product.getStringCellValue();
			XSSFCell Incompatible = st.getRow(i).getCell(1);
			String Incompatibletext = Incompatible.getStringCellValue();
			
			System.out.println(Incompatibletext);
			

				txt_searchBox.sendKeys(producttext);
				System.out.println(producttext);
				txt_searchBox.sendKeys(Keys.ENTER);
				Thread.sleep(12000);
				try {
					WebElement productTileCartIcon = driver
							.findElement(By
									.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"+producttext+"']]/parent::div/parent::div/div[@class='cart-height']/span/span[2]/span"));
					Thread.sleep(8000);
					productTileCartIcon.click();
					Thread.sleep(5000);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product
							+ "is present and the cart button is Present", "Required Product " + product
							+ " is  Present  while searching and the cart button is clicked successfully",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				} catch (NoSuchElementException e) {
					if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}
				} catch (Exception e) {
					if (!driver.findElements(By.xpath("//span/b")).isEmpty()) {
						String getStatus = driver.findElement(By.xpath("//span/b")).getText();
						System.out.println(getStatus);
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  Present with the status as "
										+ getStatus, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					} else {
						txt_searchBox.clear();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
								"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}

				}
				txt_searchBox.clear();
				Thread.sleep(2000);
				txt_searchBox.sendKeys(Incompatibletext);
				System.out.println(Incompatibletext);
				txt_searchBox.sendKeys(Keys.ENTER);
				Thread.sleep(12000);
				try{driver
						.findElement(By
								.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"+Incompatibletext+"']]/parent::div/parent::div/div[@class='cart-height']/span/span[2]/span")).click();
				
				Thread.sleep(4000);
				WebElement Popuptext = driver.findElement(By.xpath("//div[@class='modal-body ng-scope']/p"));
					String PopUpText1 = Popuptext.getText();
				
				
					if(PopUpText1.contains(producttext)){
driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Product - " + Incompatibletext
		+ "is incompatible ", "Required  Product " + Incompatibletext
		+ " is  incompatible as "+producttext+" is already present in the cart page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
		false);
Thread.sleep(5000);
						
					}

}catch(Exception e){
	if (!driver.findElements(By.xpath("//span/b")).isEmpty()) {
		String getStatus = driver.findElement(By.xpath("//span/b")).getText();
		System.out.println(getStatus);
		txt_searchBox.clear();
		TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
				"Error Occured-Required Product " + product + " is  Present with the status as "
						+ getStatus, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
	}
	
				}
				objDependentProduct.carticon();
				Thread.sleep(15000); 
				if (driver.findElement(By.xpath("//div/a[contains(.,normalize-space('" + product + "'))]")).isDisplayed()) {
					System.out.println(product + " is displayed");
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Product - " + product
							+ "is present in the Cart Page ", "Required  Product " + product
							+ " is  Present  in the Cart Page", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
							false);

					driver.findElement(
							By.xpath("//div/a[contains(.,'" + product + "')]/following::div/button[contains(.,'Remove')]"))
							.click();
					 driver.findElement(By.xpath("html/body/div[1]/div/div/div[3]/button[1]")).click();
					}
				Thread.sleep(60000);
				driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();
				Thread.sleep(20000);

			wb.close();
			fis.close();

		}
	}

	public void verifyRelatedProducts(String sheetname) throws Exception {

		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\Product Relationship V2.xlsx");
		
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(sheetname);
		int row = st.getLastRowNum();
	loop2:	for (int i = 1; i <= row; i++) {
			int  counter=0;
			st.getRow(i).getCell(0).setCellType(CellType.STRING);

			XSSFCell category = st.getRow(i).getCell(0);
			String product = category.getStringCellValue();

			txt_searchBox.sendKeys(product);
			System.out.println(product);
			txt_searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(10000);
			try {
				driver.findElement(By.xpath("//div/h3[contains(.,'" + product + "')]")).click();
				Thread.sleep(10000);
			} catch (Exception e) {
				if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
					txt_searchBox.clear();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
							"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					txt_searchBox.clear();
					continue loop2;
				}
			}
			
			String CurrentURL = driver.getCurrentUrl();
			if (CurrentURL.contains("https://mywizardautostore.accenture.com/product/")) {

				WebElement ProductDisplay = driver.findElement(By.xpath("//h4/big"));
				String ProductDisplayedText = ProductDisplay.getText();
				this.tag(product, list);
				if (ProductDisplayedText.contains(product)) {
					RelatedProduct = driver.findElements(By.xpath("//h4/strong"));
					loop: for (int j=0;j<RelatedProduct.size();) {
						this.counter=++counter;
						if(counter<=4){
							
						RelatedProductText = RelatedProduct.get(j).getText();
						j++;
						try{driver.findElement(By.xpath("//h4/strong[contains(.,'"+RelatedProductText+"')]/parent::h4/following-sibling::button")).click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that View Detail button for the Product - "+RelatedProductText+" is Clickable " ,
								"Required View Detail Button for the Product "+RelatedProductText+" is Present And Clicked Successfully",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}catch(Exception e){
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that View Detail button for the Product - "+RelatedProductText+" is Clickable " ,
									"Error Occured-Required View Detail Button for the Product "+RelatedProductText+" is not Clickable",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							System.out.println(RelatedProductText);
							continue loop;
						}
						Thread.sleep(10000);
						int size=0;
						for (String webElement2 : list) {
							size=size+1; 
							String RelatedProductViewDetailsText=driver.findElement(By.xpath("//h4/big")).getText();
							if (RelatedProductViewDetailsText.contains(webElement2)) {
								System.out.println(RelatedProductViewDetailsText + " Found");
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Related Assets Present for the Product - "+product ,
										"Required Related Assets " + RelatedProductViewDetailsText + " for the Product " + product
												+ " is Present",
										TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								driver.navigate().back();
								Thread.sleep(10000);
								size=0;
								RelatedProduct = driver.findElements(By.xpath("//h4/strong"));
								continue loop;}
							else if(list.size()==size){
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that Tags Present for the Product -  "+product,
										"Error Occured-Required Related Assets " + RelatedProductViewDetailsText + " for the Product " + product
												+ " is not Present and Matching from the Excel sheet", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								size=0;
								driver.navigate().back();
								Thread.sleep(10000);
								RelatedProduct = driver.findElements(By.xpath("//h4/strong")); 
								continue loop;
							}
							}}
						else if(driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-chevron-right']"))
								.isDisplayed() == true){
							driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-chevron-right']"))
							.click();
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Right Click Arrow is Present " ,
									"Right Click Arrow is Present and Clicked Successfully ",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							RelatedProduct = driver.findElements(By.xpath("//h4/strong"));
					counter=0;
					
					continue loop;
						}
								
							
						

					}
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("//button[contains(.,'Continue Shopping')]")).click();
Thread.sleep(15000);
			}
			wb.close();
			fis.close();
		}
	}

	public List<String> tag(String product, List<String> list2) throws Exception {
		list.removeAll(list2);
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\Product Relationship V2.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("RelatedProduct");
		int row = st.getLastRowNum();
		loop: for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(0).setCellType(CellType.STRING);
			st.getRow(i).getCell(1).setCellType(CellType.STRING);

			XSSFCell product1 = st.getRow(i).getCell(0);
			String productname = product1.getStringCellValue();
			XSSFCell TagName = st.getRow(i).getCell(1);
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
	
	public void addnondependendproduct(String product,String sheet) throws Exception{
		
		
		System.out.println(product);
		txt_searchBox.sendKeys(product);
		System.out.println(product);
		txt_searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(15000);
		try {
			WebElement productTileCartIcon = driver
					.findElement(By
							.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
									+ product
									+ "']]/parent::div/parent::div/div[@class='cart-height']/span/span[2]/span"));

			productTileCartIcon.click();
			Thread.sleep(6000);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product
					+ "is present and the cart button is Present", "Required Product " + product+ " is  Present  while searching and the cart button is clicked successfully",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		} catch (NoSuchElementException e) {
			if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
				txt_searchBox.clear();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
						"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			
			}
		} catch (Exception e) {
			if (!driver.findElements(By.xpath("//span/b")).isEmpty()) {
				String getStatus = driver.findElement(By.xpath("//span/b")).getText();
				System.out.println(getStatus);
				txt_searchBox.clear();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
						"Error Occured-Required Product " + product + " is  Present with the status as "
								+ getStatus, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				
			} else {
				txt_searchBox.clear();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
						"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			
			}

		}
		
	
	this.carticon();
	this.verifyconfigpendingicon(product,sheet);


}
public void addDependentToolToCart1(String provisionType) throws Exception {

	File excelfilename = new File("C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet(provisionType);
	int row = st.getLastRowNum();
	loop: for (int i = 1; i <= 1; i++) {

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
				WebElement productTileCartIcon = driver
						.findElement(By
								.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[text()[normalize-space() = '"
										+ product
										+ "']]/parent::div/parent::div/div[@class='cart-height']/span/span[2]/span"));
				Thread.sleep(10000);
				productTileCartIcon.click();
				Thread.sleep(8000);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product
						+ "is present and the cart button is Present", "Required Product " + product
						+ " is  Present  while searching and the cart button is clicked successfully",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} catch (NoSuchElementException e) {
				if (driver.findElement(By.xpath("//div[@class='alert alert-danger fade in']")).isDisplayed()) {
					txt_searchBox.clear();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
							"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					continue loop;
				}
			} catch (Exception e) {
				if (!driver.findElements(By.xpath("//span/b")).isEmpty()) {
					String getStatus = driver.findElement(By.xpath("//span/b")).getText();
					System.out.println(getStatus);
					txt_searchBox.clear();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
							"Error Occured-Required Product " + product + " is  Present with the status as "
									+ getStatus, TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					continue loop;
				} else {
					txt_searchBox.clear();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Product - " + product + "is present ",
							"Error Occured-Required Product " + product + " is  not Present ", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					continue loop;
				}

			}
			try {
				WebElement Popuptext = driver.findElement(By.xpath("//div/strong"));
				String PopUpText = Popuptext.getText();

				if (PopUpText.contains("The product(s) listed below are pre-requisites ")) {
					WebElement PopUpProduct = driver.findElement(By
							.xpath("//div/div[@ng-repeat='product in products']"));
					String PopUpProductText = PopUpProduct.getText();
					System.out.println(PopUpProductText + "" + dependentProducttext);
					if (PopUpProductText.contains(dependentProducttext)) {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - "
								+ dependentProducttext + "is present in the Application",
								"Required Dependent Product " + dependentProducttext
										+ " is  Present  in the application", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
						if (!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty()) {
							driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the  Pop Up is Displayed for the Product - " + product,
									"Required Pop Up is Displayed for the Product - " + product, TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

						//	Thread.sleep(10000);
						//	txt_searchBox.clear();
							Thread.sleep(10000);
							this.carticon();
						} else {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the  Pop Up is Displayed for the Product - " + product,
									"Error Occured-Required Pop Up is not Displayed for the Product - " + product
											+ " While clicking on the cart icon.", TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							continue loop;
						}
					} else {

					}
				} else {
					Thread.sleep(5000);
					WebElement RadioCheckProduct = driver.findElement(By.xpath("//div/span[contains(.,'"
							+ dependentProducttext + "')]/ancestor::div/div[@class='row']/div/input"));
					RadioCheckProduct.click();
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - " + dependentProducttext
							+ "is present in the Application", "Required Dependent Product " + dependentProducttext
							+ " is  Present  in the application radio button is clicked", TC_MAS_Login.rowNumber,
							TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					if (!driver.findElements(By.xpath("//button[contains(.,'Ok')]")).isEmpty()) {
						driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Pop Up is Displayed for the Product - "
								+ product, "Required Pop Up is Displayed for the Product - " + product,
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

				
						txt_searchBox.clear();
						Thread.sleep(5000);
						this.carticon();
					} else {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Pop Up is Displayed for the Product - "
								+ product, "Error Occured-Required Pop Up is not Displayed for the Product - "
								+ product + " While clicking on the cart icon.", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
						continue loop;
					}

				}
			} catch (Exception e) {
				continue loop;
			}
			Thread.sleep(5000);
			this.validateDependendProduct1(product, dependentProducttext);

		}
		wb.close();
		fis.close();

	}
}
public void verifyconfigpendingicon(String DependentProduct,String Excelsheet) throws Exception{
	Thread.sleep(8000);
	driver.findElement(
			By.xpath("//div/a[contains(.,'" + DependentProduct
					+ "')]/following::div/div[contains(.,'Configuration Pending')]")).click();
	TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Configuration Pending icon is present for - " + DependentProduct
			, " Configuration Pending icon is present for " + DependentProduct
			+ " is  Present  in the Cart Page ", TC_MAS_Login.rowNumber,
			TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
	configPageClientMgmtFields(Excelsheet);
	Thread.sleep(5000);
	driver.findElement(By.xpath(".//input[@ng-change='checkboxOnChange()']")).click();

	Thread.sleep(5000);
ProductCartPage.validateProvisionMyPlatform();
Thread.sleep(10000);
MySelectionPage.verifyEmailOrderStatus(DependentProduct);
}
	public void validateDependendProduct1(String product, String DependentProduct) throws Exception {

		Thread.sleep(10000);

		if (driver.findElement(By.xpath("//div/a[contains(.,normalize-space('" + product + "'))]")).isDisplayed()) {
			System.out.println(product + " is displayed");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the   Product - " + product + "is present in the Cart Page ",
					"Required  Product " + product + " is  Present  in the Cart Page", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		try{	driver.findElement(
					By.xpath("//div/a[contains(.,'" + product
							+ "')]/following::div/div[contains(.,'Configuration Pending')]")).click();
		TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Configuration Pending icon is present for - " + product
				, " Configuration Pending icon is present for " + product
				+ " is  Present  in the Cart Page ", TC_MAS_Login.rowNumber,
				TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		configPageClientMgmtFields("ConfigPendingDetails");
		Thread.sleep(5000);}
		catch(Exception E){
			
		}
			
			try{if (driver
					.findElement(
							By.xpath("//div/a[contains(.,normalize-space('" + DependentProduct
									+ "'))]/ancestor::div/div/button")).getAttribute("disabled")
					.equalsIgnoreCase("true")) {

				String getattribute = driver.findElement(
						By.xpath("//div/a[contains(.,normalize-space('" + DependentProduct
								+ "'))]/ancestor::div/div/button")).getAttribute("disabled");

				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Dependent Product - " + DependentProduct
						+ "is present in the Cart Page ", "Required Dependent Product " + DependentProduct
						+ " is  Present  in the Cart Page containing Remove Button as Disable", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				System.out.println("Dependent Product is automatically added into the list " + DependentProduct + " "
						+ getattribute);
				driver.findElement(
						By.xpath("//div/a[contains(.,'" + DependentProduct
								+ "')]/following::div/div[contains(.,'Configuration Pending')]")).click();
				TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Configuration Pending icon is present for - " + DependentProduct
						, " Configuration Pending icon is present for " + DependentProduct
						+ " is  Present  in the Cart Page ", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				configPageClientMgmtFields("AAAM");
			Thread.sleep(5000);
			driver.findElement(By.xpath(".//input[@ng-change='checkboxOnChange()']")).click();
			}
			}catch(Exception E){
				driver.findElement(By.xpath(".//input[@ng-change='checkboxOnChange()']")).click();
			}
		}
		ProductCartPage.validateProvisionMyPlatform();
		Thread.sleep(5000);
		MySelectionPage.verifyEmailOrderStatus(product);
		Thread.sleep(5000);
		MySelectionPage.verifyEmailOrderStatus(DependentProduct);
		
	}

	public static void configPageClientMgmtFields(String sheetname) throws Exception {
		//Thread.sleep(10000);
		File excelfilename = new File("C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet st = wb.getSheet(sheetname);
        int row = st.getLastRowNum();
        loop:for (int i = 1; i <= row; i++) {

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
                    inputDetails = driver.findElement(By
                                .xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'" + labelText
                                            + "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/input"));
                    inputDetails.clear();
                    inputDetails.sendKeys(cellData,Keys.ENTER);
                    TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
                    TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  input text been entered for "+labelText
                                , " Required Text "+cellData+ " has been entered successfully", TC_MAS_Login.rowNumber,
                                TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
                    try {
                          Thread.sleep(10000);
                          if (driver.findElement(
                                      By.xpath(".//ul[@class='suggestions-list']/li"))
                                      .isDisplayed()) {
                                inputDetails.clear();
                                Actions build = new Actions(driver);
                                int idLength = StringUtils.length(cellData);
                                String lastchar = StringUtils.substring(cellData, idLength - 1, idLength);
                                build.sendKeys(cellData).build().perform();
                                Thread.sleep(20000);
                                build.sendKeys(Keys.BACK_SPACE).build().perform();
                                Thread.sleep(5000);
                                build.sendKeys(lastchar).build().perform();
                                Thread.sleep(5000);
                                WebElement suggestedUser = driver.findElement(By
                                            .xpath(".//ul[@class='suggestions-list']/li[text()[normalize-space() = '" + cellData
                                                        + "']]"));
                                build.click(suggestedUser).build().perform();
                                TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  input text been entered for "+labelText
                                            , " Required People Picker Text "+cellData+ " has been selected successfully", TC_MAS_Login.rowNumber,
                                            TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);}
                    } catch (NoSuchElementException e) {
                          wb.close();
                          fis.close();
                    }
                    catch (Exception e) {
                          continue loop;
                    }
                    

              } else if (((elementType.equalsIgnoreCase("radio")))) {
              
                    inputDetails = driver
                                .findElement(By
                                            .xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'"
                                                        + labelText
                                                        + "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/span[contains(@ng-repeat,'radioButtonItem')]/label[text()[contains(.,'"
                                                        + cellData + "')]]/input"));
                    inputDetails.click();
                    TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
                    TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  Radio Button been selected for "+labelText
                                , " Required Radio Button  has been selected successfully", TC_MAS_Login.rowNumber,
                                TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
              }

              else if (((elementType.equalsIgnoreCase("checkbox")))) {
                    try{
                    inputDetails = driver
                                .findElement(By
                                            .xpath("//div[contains(.,'"+labelText+"')]/parent::ng-include/div/div[@class='col-sm-4']/span[contains(@ng-repeat,'checkboxItem')]/label[contains(.,'"+cellData+"')]/input"));

                    inputDetails.click();
                    }catch(Exception E){
                          inputDetails = driver
                                      .findElement(By
                                                  .xpath("//div[contains(.,'"+labelText+"')]/parent::ng-include/div/div[@class='col-sm-4']/span[contains(@ng-repeat,'checkboxItem')]/label/input"));

                          inputDetails.click();
                    }
                    TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
                    TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  checkbox Button been selected for "+labelText
                                , " Required checkbox Button  has been selected successfully for "+labelText, TC_MAS_Login.rowNumber,
                                TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);}

             /* else if (((elementType.equalsIgnoreCase("dropdown")))) {
                    inputDetails = driver.findElement(By
                                .xpath("//label[@class='col-sm-3 control-label ng-binding' and contains(text(),'" + labelText
                                            + "')]/ancestor::*[position()=1]/div[@class='col-sm-4']/select"));

                    Select objInputDetails = new Select(inputDetails);
                    objInputDetails.selectByVisibleText(cellData);
                    TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
                    TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the  dropdown Menu been selected for "+labelText
                                , " Required checkbox Button  has been selected successfully for "+labelText, TC_MAS_Login.rowNumber,
                                TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
              }*/

              else {
                    System.out.println("Nothing is there");
              }
        }
        wb.close();
        fis.close();
  try{  btn_saveConfig.click();
        Thread.sleep(15000);
  }catch(Exception E){
        driver.findElement(By.xpath("//button[text()[normalize-space() = 'Update']]")).click();
  Thread.sleep(15000);
  }
        try {
              if (btn_popupOk.isDisplayed() == true) {
                    btn_popupOk.click();
                    Thread.sleep(5000);
              }
        } catch (Exception e) {
driver.findElement(By.xpath("//button[text()[normalize-space() = 'Save']]")).click();
        }
        btn_goToCart.click();
  Thread.sleep(5000);
  }


	}

