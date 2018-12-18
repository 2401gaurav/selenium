package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.XPath;
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

//import com.Automationstore.TestCases.TC_MAS;
import com.google.common.base.Verify;
import com.mas.TestCases.TC_MAS_Login;



public class DeliveryPatternPage {

	static String currentUrl;
	static WebElement inputDetails;
	WebDriver driver;
	String pagenextbutton;
	public String PatternName;

	@FindBy(how = How.XPATH, using = "//a[@class='icon icon11 cursorhand']")
	WebElement adminIcon;

	@FindBy(how = How.XPATH, using = "//div/div[2]/div/h2/a[text()='Delivery Profile']")
	WebElement DPPTileheader;

	@FindBy(how = How.XPATH, using = "//a[text()='Delivery Profile']/parent::h2/parent::div")
	WebElement DPPTile;

	@FindBy(how = How.XPATH, using = "//div/div/h1/span[contains(text(),'Admin | Delivery Profile')]")
	WebElement DPPheader;

	//// div[3]/div[1]/div/div[@ng-repeat]/div/div[@class='col-md-2']/button
	@FindBy(how = How.XPATH, using = "//div[3]/div[1]/div/div[@ng-repeat]/div/div[@class='col-md-2']/button")
	List<WebElement> editProfileBtn;

	//// div[1]/div/div[@class='panel-body ng-scope']
	@FindBy(how = How.XPATH, using = "//div[1]/div/div[@class='panel-body ng-scope']")
	List<WebElement> allrows;

	@FindBy(how = How.XPATH, using = "//button[.='Create a Profile']")
	WebElement Profilebutton;

	//// form/div[2]/select[@name='Status']
	@FindBy(how = How.XPATH, using = "//form/div[2]/select[@name='Status']")
	WebElement drpStatus;

	// .//*[@ng-click='clickOpen(1);']
	@FindBy(how = How.XPATH, using = "//*[@ng-click='clickOpen(1);']")
	WebElement deliveryType;

	// old
	// xpath//*[@id='multiselect'][contains(@ng-click,'open1')]/following-sibling::div/div..dev
	// changed xpath
	@FindBy(how = How.XPATH, using = "//*[@id='multiselect'][contains(@ng-click,'clickOpen(1)')]/following-sibling::div/div")
	List<WebElement> delTypeDropdown;

	@FindBy(how = How.XPATH, using = "//*[@ng-click='clickOpen(2);']")
	WebElement industry;

	// *[@id='multiselect'][contains(@ng-click,'open2')]/following-sibling::div/div
	@FindBy(how = How.XPATH, using = "//*[@id='multiselect'][contains(@ng-click,'clickOpen(2)')]/following-sibling::div/div")
	List<WebElement> indDropdown;

	@FindBy(how = How.XPATH, using = "//*[@ng-click='clickOpen(3);']")
	WebElement subIndustry;
	
	@FindBy(how = How.XPATH, using = "//*[@ng-click='clickOpen(4);']")
	WebElement technology;

	@FindBy(how = How.XPATH, using = "//*[@id='multiselect'][contains(@ng-click,'clickOpen(4)')]/following-sibling::div/div")
	List<WebElement> techDropdown;

	//// form/div[11]/div[2]/div/div[2]/select
	@FindBy(how = How.XPATH, using = "//form/div[11]/div[2]/div/div[2]/select")
	WebElement MadOptionalDrpdwn;

	//// select[2][@ng-model="limit"]
	@FindBy(how = How.XPATH, using = "//select[2][@ng-model='limit']")
	WebElement limitDrpdwn;

	@FindBy(how = How.XPATH, using = "//h1/a/span[2][text()='Back']")
	WebElement backBtn;

	public DeliveryPatternPage(WebDriver driver) {
		this.driver = driver;
	}

	public void CheckAdminIcon() {
		try {
			boolean value = adminIcon.isDisplayed();
			if (value == true) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  Admin icon is displayed on the Value wallet page.",
						"Admin icon is displayed as required.", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  Admin icon is displayed on the Value wallet page.",
						"Error - Admin icon is not displayed which is incorrect.", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}

		} catch (Exception ex) {
			ex.getMessage();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  Admin icon is displayed on the Value wallet page.",
					"Error - Admin icon is not displayed which is incorrect." + ex.getMessage(),
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		}

	}

	public void navigateDeliveryPattern() {
		try {
			adminIcon.click();
			boolean value = DPPTile.isDisplayed();

			if (value == true) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate Delivery Pattern Profile Tile  is displayed on  Admin Page.",
						"Delivery Pattern Profile Tile is displayed on  Admin Page.", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate Delviery Pattern Profile Tile  is displayed on Admin Page.",
						"Error - Delivery Pattern Profile Tile is not displayed on Admin Page.",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}

		} catch (Exception ex) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate Delviery Pattern Profile  is displayed in Admin Page.",
					"Error - Delivery Pattern Profile is not displayed in Admin Page." + ex.getMessage(),
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);

		}
	}

	public void CheckingDDPTile() throws InterruptedException {

		DPPTileheader.click();
		Thread.sleep(5000);
		String header = DPPheader.getText();
		if (header.equals("Admin | Delivery Pattern Profile")) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate Delivery Pattern Profile page displays required header.",
					"Required header is displayed on Delivery Pattern profile.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		} else {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate Delivery Pattern Profile page displays required header.",
					"Error - Required header is not  displayed on Delivery Pattern profile.",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
		}

	}

	public void CreateProfileBtn() {
		boolean profilebtn = Profilebutton.isEnabled();
		boolean profilebtndis = Profilebutton.isDisplayed();
		if (profilebtn) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page has Create a profile button enabled and displaying.",
					"Required two dropdowns are displayed under the Status dropdown.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		Profilebutton.click();
		} else {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile Page has Create a profile button enabled and displaying.",
					"Required two dropdowns are displayed under the Status dropdown.", TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void LimitDrp() {

		boolean found = false;
		String Failedfields = "";
		String[] data = { "Show 10", "Show 20", "Show 40", "Show All" };

		try {

			backBtn.click();
			Thread.sleep(1000);

			Select limitdrpdpwn = new Select(limitDrpdwn);
			List<WebElement> limitdropdw = limitdrpdpwn.getOptions();

			System.out.println(limitdropdw.size());
			System.out.println(data.length);

			for (int i = 0; i < limitdropdw.size(); i++) {
				System.out.println(limitdropdw.get(i).getText());
				System.out.println(i);

				for (int j = 0; i < data.length; i++) {
					System.out.println(data[j]);
					if (limitdropdw.get(i).getText().equals(data[j])) {

						found = true;
						break;
					}
				}

				if (found == false) {
					Failedfields += limitdropdw.get(i).getText() + " ";
				}
			}

			if (found) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile  Page, Limit dropdown  of Delivery Pattern Package(s) has required three dropdown options.",
						"Required three dropdowns are displayed under the  Limit dropdown  of Delivery Pattern page."
								+ "Expected :" + Arrays.toString(data) + "Actual Results" + limitdropdw,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile  Page, Limit dropdown  of Delivery Pattern Package(s) has required three dropdown options.",
						"error occured - Required three dropdowns are displayed under the  Limit dropdown  of Delivery Pattern page."
								+ "Expected dropdown values:" + data + "Actual dropdown values:" + limitdropdw,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}

			System.out.println(Failedfields);
		} catch (Exception ex) {

			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile  Page, Limit dropdown  of Delivery Pattern Package(s) has required three dropdown options.",
					"error occured" + ex.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);

		}

	}

	public void Editprofilebtn() {

		int countsdisp = 0;
		int countEnabled = 0;
		try {

			Select limitdrpdpwn = new Select(limitDrpdwn);
			limitdrpdpwn.selectByValue("Show All");
			Thread.sleep(100);

			for (WebElement element : editProfileBtn) {
				if (element.isDisplayed()) {
					countsdisp += 1;
					if (element.isEnabled()) {
						countEnabled += 1;
					}
				}
			}

			int Rows = allrows.size();
			if (allrows.size() == countsdisp) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate on  Delivery Pattern Profile page, the number of Edit button is displayed for every Record rows.",
						"On Delivery Pattern Profile page, the number of Edit button are  equal to number of record rows,",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);

				if (countsdisp == countEnabled) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate on  Delivery Pattern Profile page, all the Edit buttons are enabled.",
							"On  Delivery Pattern Profile page, all the Edit buttons are enabled, as required.",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);

				} else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate on  Delivery Pattern Profile page, all the Edit buttons are enabled.",
							"Error - On  Delivery Pattern Profile page, all the Edit buttons are  not enabled, which is incorrect.",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				}

			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate on  Delivery Pattern Profile page, the number of Edit button is displayed for every Record rows.",
						"Error - On Delivery Pattern Profile page, the number of Edit button are not  equal to number of record rows, which is incorrect.",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);

			}
		} catch (Exception ex) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate on  Delivery Pattern Profile page, the number of Edit button is displayed for every Record rows.",
					"Error -" + ex.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void Statusbutton() {

		boolean found = false;
		try {
			if (Profilebutton.isDisplayed()) {

				Profilebutton.click();
				String[] reqStatusDrp = { "Active", "Inactive" };
				Select Statusdrp = new Select(drpStatus);

				List<WebElement> alldropdwoptions = Statusdrp.getOptions();
				System.out.println(alldropdwoptions.get(0).getText());
				System.out.println(alldropdwoptions.get(1).getText());

				int sizedd = alldropdwoptions.size();
				for (int i = 0; i < sizedd; i++) {
					if (alldropdwoptions.get(i).getText().equals(reqStatusDrp[i])) {
						found = true;
						break;

					}

				}

				if (found) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate  on Delivery Pattern Profile page Status field has required two dropdown option.",
							"Required two dropdowns are displayed under the Status dropdown." + "Expected :"
									+ Arrays.toString(reqStatusDrp) + "Actual Results" + alldropdwoptions,
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				} else {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults(
							"Validate  on Delivery Pattern Profile page Status field has required two dropdown option.",
							"Error - Required two dropdowns are not displayed under the Status dropdown.",
							TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
				}

			}
		} catch (Exception ex) {
			ex.getMessage();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page Status field has required two dropdown option.",
					"Error - Exception occured ." + ex.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		}

	}

	public void CheckDelType() {
		String value = "";
		String value1 = "";
		String[] ExpDelTypedrpdwn = { "Application Development", "Application Maintenance", "Infrastructure",
				"IT Service Management", "Other", "Testing" };
		try {

			deliveryType.click();
			// driver.findElement(By.xpath("//*[@ng-click='clickOpen(1);']")).click();

			System.out.println(delTypeDropdown.size());
			// System.out.println(delTypeDropdown2.get(1));
			System.out.println(delTypeDropdown.get(1).getText());
			System.out.println(delTypeDropdown.get(2).getText());

			// String data =
			// driver.findElement(By.xpath("//*[@id='multiselect'][contains(@ng-click,'open1')]/following-sibling::div/div/text()")).getText();

			for (int i = 0; i < delTypeDropdown.size(); i++) {
				if (delTypeDropdown.get(i).getText().equals(ExpDelTypedrpdwn[i]) == false) {
					value += delTypeDropdown.get(i).getText();
					break;
				}

			}
			deliveryType.click();
			if (value.equals("")) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Delivery Type  has all the required dropdown options.",
						"All required dropdown options are displayed for the Delivery Type.",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Delivery Type  has all the required dropdown options.",
						"Error -All required dropdown options are not  displayed for the Delivery Type. "
								+ " Failed fields are:" + value,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}
		} catch (Exception ex) {
			deliveryType.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page, Delivery Type  has all the required dropdown options.",
					"Error -Exception ocurred . " + ex.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void CheckIndustry() {
		String value = "";
		String[] ExpDelTypedrpdwn = { "CMT C&M", "CMT E&HT", "CMT SW&P", "Cross Industry", "FS Banking",
				"FS Capital Markets", "FS Insurance", "H&PS Border Mgt & PS", "H&PS CITIZEN SERVICE",
				"H&PS Defns/PubSafety", "H&PS Health", "H&PS Other", "H&PS Postal", "H&PS PS Ops & Mngt",
				"H&PS Public Transpo", "PD Air F&L Trvl Svc", "PD Auto", "PD Con Goods & Srvcs", "PD Ind Equip",
				"PD Infr & Transp Svc", "PD Life Sciences", "PD Retail", "RS Chem", "RS Energy", "RS Nat Res",
				"RS Other", "RS Util" };
		try {

			industry.click();

			for (int i = 0; i < indDropdown.size(); i++) {
				System.out.println(indDropdown.get(i).getText());
				if (indDropdown.get(i).getText().equals(ExpDelTypedrpdwn[i]) == false) {

					value += indDropdown.get(i).getText();

				}
			}
			System.out.println(value);
			industry.click();
			if (value.equals("")) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Industry Type  has all the required dropdown options.",
						"All required dropdown options are displayed for the Industry Type.",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Industry Type  has all the required dropdown options.",
						"Error -All required dropdown options are not  displayed for the Industry Type. "
								+ " Failed fields are:" + value,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}
		} catch (Exception ex) {
			industry.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page, Industry Type  has all the required dropdown options.",
					"Error- Exception ocurred " + ex.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
	}

	public void SubIndustry(HashMap<String, String>DelPat) {
		String[] CMTCM = { "Cross Communications" };
		String[] CMTEHT = { "Aerospace and Defense", "Communication Technology", "Consumer Technology",
				"Enterprise Technology", "ICT(Hybrid of Hardware & Software)", "Medical Equipment Technologies",
				"Semiconductor" };
		String[] CMTSWP = { "Broadcasting & Entertainment", "Education", "Internet & Social", "Printing & Publishing" };
		String[] CrossIndustry = { "Not Applicable" };
		String[] FSBanking = { "Accenture Credit Services", "Accenture Distribution & Marketing",
				"Accenture Payments - Card Services", "Core Banking Services", "Core Payment Services" };
		String[] FSCapitalMarkets = { "Accenture Trading Services", "Accenture Wealth and Asset Management Services",
				"Basel III & BCBS239", "Crime & Compliance & Liquidity Risk Management", "IFRS9 & MIFID II" };
		String[] FSInsurance = { "Accenture Life Insurance Services",
				"Accenture Property and Casualty Insurance Services" };
		String[] HPSBorderMgtPS = { "Not Applicable" };
		String[] HPSCITIZENSERVICE = { "Administration", "Employment", "Postal", "Revenue", "Social Services" };
		String[] HPSDefnsPubSafety = { "Borders", "Defense", "Public Safety" };
		String[] HPSHealth = { "Cross Health", "Health Analytics", "Health Back Office Management", "Payer",
				"Provider" };
		String[] HPSOther = { "Cross H&PS" };
		String[] HPSHHPSPostalealth = { "Not Applicable" };
		String[] HPSPSOpsMngt = { "Not Applicable" };
		String[] HPSPublicTranspo = { "Not Applicable" };
		String[] PDAirFLTrvlSvc = { "Not Applicable" };
		String[] PDAuto = { "Not Applicable" };
		String[] PDConGoodsSrvcs = { "Agribusiness", "Alcoholic Beverages", "Apparel", "Consumer Healthcare",
				"Food and Non Alcoholic Beverages", "Home and personal Care", "Tobacco" };
		String[] PDIndEquip = { "Auto & Auto Spares", "Consumer Durables", "Freight & Logistics", "Heavy Equipment",
				"Industrial & Electrical Equipment", "Industrial Equipment", "Industrial Equipment Suppliers",
				"Infrastructure & Construction",
				"Original Equipment Manufacturers (Truck & Commercial Vehicles Manufacturing)", "Rail & Transit" };

		String[] PDInfrTranspSvc = { "Airlines", "Airports", "Aviation", "Cruise", "Gaming", "Hospitality", "Hotels",
				"Not Applicable", "Online Travel Services", "Tour Operators", "Travel Services" };

		String[] PDLifeSciences = { "Biotechnology", "CRO (Clinical Research Organization)",
				"Distributors and Wholesalers", "Medical Technology", "Pharmaceuticals", "Regulatory Organizations" };
		String[] PDRetail = { "Apparel", "Consumer Electronics", "Department Stores",
				"Discount Stores / Warehouse Clubs", "Drug / Convenience Stores", "Grocery Stores / Supermarkets",
				"Home Improvement / Do-it-Yourself Stores", "Mass Merchants / Hypermarkets", "Professional Services",
				"Quick Service Restaurants", "Specialty Retailers", "Wholesale / Distribution" };
		String[] RSChem = { "Chemicals" };
		String[] RSEnergy = { "Downstream – Marketing", "LNG", "Upstream - Production Operations",
				"Upstream - Subsurface Wells", "", "" };
		String[] RSNatRes = { "Forest Products & Building Materials", "Metals", "Mining" };
		String[] RSOther = { "Not Applicable" };
		String[] RSUtil = { "Corporate", "Power Generation", "Retail", "Transmission & Distribution" };
		
		
		System.out.println(DelPat.get("Industry"));
		String data = DelPat.get("Industry");
		String data1 =data.replace(" ","").replace("&","").replace("/","");
		
		industry.click();
		
		//PD Infr & Transp Svc
		
		subIndustry.clear();
		
		

	}

	public void CheckTechnology() {
		String value = "";
		String[] data = { ".Net", "Accela", "Actuate", "Adobe CQ5", "Amazon Web Services", "Amdocs", "Android",
				"Angular JS", "Apache Wicket", "Appcelerator", "ARIBA", "ATG Ecommerce", "AWS (Amazon Web Services)",
				"BEA/ALSB", "BigData", "Biztalk", "BlackBerry10", "C/C++/Assembly", "C/C++/Unix", "Callidus", "CAS",
				"Centura", "Cerner", "Click", "Cobol", "Cognos", "Content Mgmt-Documentum", "Content Mgmt-Vignette",
				"Cordova", "Coremedia CMS", "Cramer", "Crystal Reports", "Datastage", "DBA", "Drupal", "DW/BI",
				"Dynamics CRM", "Embedded Linux", "Epic", "EPIServer", "ExactTarget", "Excentive", "GE SmallWorld/GIS",
				"Hadoop", "HTML", "Hybris", "IBM Curam", "IBM FileNet", "IBM Worklight", "IBM-WebSphere-Commerce",
				"Informatica", "iOS", "Java/J2EE", "JCAPS", "JD Edwards", "Jquery", "Kalido", "Kenan", "Kony",
				"LAMP (Linux, Apache, MySQL and PHP) stack to the technology", "Liferay", "Linux", "Lodestar",
				"Mainframe", "Microsoft", "Microsoft Dynamics AX (Axapta)", "Microsoft SQL BI",
				"Microsoft Virtualization/Hyper V", "Microstrategy", "Middleware", "Midrange", "Mobility", "MOSS",
				"Netezza (DBA appliance)", "NetSuite", "NFC", "Node JS", "Opentext DAM", "Oracle (Utilities)",
				"Oracle ADF", "Oracle Apps", "Oracle BRM", "Oracle Fusion Apps", "Oracle Fusion Middleware",
				"Oracle identity Access Management", "Oracle RAC", "Other", "Pantaho", "Pega", "PeopleSoft", "PL/SQL",
				"PLM", "Qlikview (Reporting)", "Salesforce.com", "SAP", "SAP BI", "SAP BO", "SAP ByD", "SAS",
				"SAS Reporting", "SDL Tridion", "Sencha", "SFDC", "SFSF (Success Factors Success Factors)", "Siebel",
				"Silverlight", "SingleView/Perl", "SiteCore", "SOA", "Solaris", "SQL Server", "Success Factors",
				"Sybase", "Symbian", "Tableau", "Teradata", "Testing", "Tibco Spotfire", "Touch/React JS", "VBA",
				"Versa", "Vmware", "Web Methods", "Web sphere", "Windows CE .Net", "Windows Mobile", "Workday",
				"Xamarin", "ExactTarget" };

		try {
			technology.click();

			for (int i = 0; i < techDropdown.size(); i++) {
				System.out.println(techDropdown.get(i).getText());
				if (techDropdown.get(i).getText().equals(data[i]) == false) {
					value += techDropdown.get(i).getText() + " ";
				}
			}

			if (value.equals("")) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Technology  Type  has all the required dropdown options.",
						"All required dropdown options are displayed for the Industry Type.",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page, Technology Type  has all the required dropdown options.",
						"Error - All required dropdown options are not  displayed for the Industry Type. "
								+ " Failed fields are:" + value,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}
		} catch (Exception ex) {
			technology.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page, Technology Type  has all the required dropdown options.",
					"Error - Exception occured " + ex.getMessage(), TC_MAS_Login.rowNumber,
					TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}

	}

	public void MadOptionalDrp() {

		boolean found = false;
		try {
			String[] MadOptionalDropdown = { "Mandate", "Optional" };
			Select MadOptionalDd = new Select(MadOptionalDrpdwn);

			List<WebElement> DropdownValues = MadOptionalDd.getOptions();

			for (int i = 0; i < DropdownValues.size(); i++) {

				if (DropdownValues.get(i).getText().equals(MadOptionalDropdown[i])) {
					found = true;

				}
			}

			if (found) {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page Mandate/Optional dropdown  of Delivery Pattern Package(s) has required two dropdown option.",
						"Required two dropdowns are displayed under the  Mandate/Optional dropdown  of Delivery Pattern Package(s)."
								+ "Expected :" + Arrays.toString(MadOptionalDropdown) + "Actual Results"
								+ DropdownValues,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			} else {
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page Mandate/Optional  field  of Delivery Pattern Package(s) has required two dropdown option.",
						"error occured - Required two dropdowns are not displayed under the  Mandate/Optional dropdown  of Delivery Pattern Package(s).",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}

		} catch (Exception ex) {
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page Mandate/Optional  field  of Delivery Pattern Package(s) has required two dropdown option.",
					"error occured" + ex.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
		}

	}

public void CreateDeliverypatternProfile() throws Exception{
	
	File excelfilename = new File(
			"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

	FileInputStream fis = new FileInputStream(excelfilename);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet st = wb.getSheet("DeliveryPattern");
	int row = st.getLastRowNum();
	for (int i = 1; i <= row; i++) {

		st.getRow(i).getCell(0).setCellType(CellType.STRING);
		st.getRow(i).getCell(1).setCellType(CellType.STRING);
		st.getRow(i).getCell(2).setCellType(CellType.STRING);
		st.getRow(i).getCell(3).setCellType(CellType.STRING);

		XSSFCell category = st.getRow(i).getCell(0);
		String categorytext = category.getStringCellValue();
		XSSFCell fieldtype = st.getRow(i).getCell(1);
		String fieldtypetext = fieldtype.getStringCellValue();
		XSSFCell input = st.getRow(i).getCell(2);
		String inputtext = input.getStringCellValue();
		XSSFCell moreinput = st.getRow(i).getCell(3);
		String moreinputtext = moreinput.getStringCellValue();
		
		
		System.out.println(categorytext);
		System.out.println(fieldtypetext);
		
		System.out.println(inputtext);
		if ((fieldtypetext.equalsIgnoreCase("text"))) {
			inputDetails = driver
					.findElement(By.xpath("//div[@class='col-md-3 padding85-l required' and contains(.,'"+categorytext+"')]/following-sibling::div/input"));
			inputDetails.clear();
			Thread.sleep(10000);
			inputDetails.sendKeys(inputtext);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page "+categorytext+" has been selected.",
					"Required input "+inputtext+" are displayed and Entered under "+categorytext+""
							,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			Thread.sleep(10000);
	
		}
		else if (((fieldtypetext.equalsIgnoreCase("DropDown"))&&(categorytext.equalsIgnoreCase("Status")))) {
			Thread.sleep(10000);
			inputDetails = driver.findElement(By
					.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::select"));
			Thread.sleep(10000);
			Select objInputDetails= new Select(inputDetails);
			Thread.sleep(10000);
			objInputDetails.selectByVisibleText(inputtext);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page dropdown  of "+categorytext+" has been selected.",
					"Required  dropdown "+inputtext+" are displayed and selected under "+categorytext+"."
							,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			
												
		}
		else if (((fieldtypetext.equalsIgnoreCase("DropDown"))&&((categorytext.equalsIgnoreCase("Delivery Type"))||(categorytext.equalsIgnoreCase("Industry"))||(categorytext.equalsIgnoreCase("Sub Industry"))||(categorytext.equalsIgnoreCase("Technology"))))) {
			Thread.sleep(15000);
			driver.findElement(By.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::div/label/span")).click();
			inputDetails = driver.findElement(By
					.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::div/div/div[contains(.,'"+inputtext+"')]/input"));
			inputDetails.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page "+categorytext+" has been Checked.",
					"Required field "+inputtext+" under "+categorytext+" Has Been Checked Successfully"
							,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,false);
			Thread.sleep(15000);
			driver.findElement(By.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::div/label/span")).click();
			if(!(moreinputtext.contains("Null"))){
				driver.findElement(By.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::span/small")).click();
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page "+categorytext+" has been Marked.",
						"Required Checkbox under "+categorytext+" Has Been Marked Successfully"
								,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,false);
			}
		}
		else if (((fieldtypetext.equalsIgnoreCase("textarea"))&&(categorytext.equalsIgnoreCase("Description and Keyword")))) {
			Thread.sleep(10000);
			inputDetails = driver
					.findElement(By.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::textarea"));
			inputDetails.clear();
			Thread.sleep(10000);
			inputDetails.sendKeys(inputtext);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page "+categorytext+" has been selected.",
					"Required input "+inputtext+" are displayed and Entered under "+categorytext+""
							,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			Thread.sleep(10000);
												
		}
		else if (((fieldtypetext.equalsIgnoreCase("DropDown"))&&(categorytext.equalsIgnoreCase("Delivery Package(s)")))) {
			Thread.sleep(10000);
			inputDetails = driver.findElement(By
					.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::div/div/div[1]/select"));
			Thread.sleep(10000);
			Select objInputDetails= new Select(inputDetails);
			Thread.sleep(10000);
			objInputDetails.selectByVisibleText(inputtext);
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate  on Delivery Pattern Profile page Packages Name dropdown  of Delivery Pattern Package(s) has been selected.",
					"Required  dropdown "+moreinputtext+" are displayed and selected under Delivery Pattern Package(s)."
							,
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
					false);
			if(!(moreinputtext.contains("Null"))){
				inputDetails = driver.findElement(By
						.xpath("//div[@class='col-md-3 padding85-l' and contains(.,'"+categorytext+"')]/following-sibling::div/div/div[2]/select"));
				Thread.sleep(10000);
				Select objInputDetails1= new Select(inputDetails);
				Thread.sleep(10000);
				objInputDetails1.selectByVisibleText(moreinputtext);
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate  on Delivery Pattern Profile page Mandate/Optional dropdown  of Delivery Pattern Package(s) has been selected.",
						"Required  dropdown "+moreinputtext+" are displayed and selected under Delivery Pattern Package(s)."
								,
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
						false);
			}
			}
												
		
		
	

		else {
			System.out.println("Nothing is present");
		}
	
	
wb.close();
fis.close();}
	boolean value=driver.findElement(By.xpath("//button[contains(.,'Save')]	")).isEnabled();
	if(value==true){
		driver.findElement(By.xpath("//button[contains(.,'Save')]	")).click();
		TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
		TC_MAS_Login.resultDataConfig.WritingToExcelResults(
				"Validate  on Delivery Pattern Profile page Save Button is Enable",
				"Required  Save Button is Enable and clicked successfully."
						,
				TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet,
				false);
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]	")).click();
		Thread.sleep(15000);
		driver.findElement(By.xpath("//h1/a/span[2][text()='Back']")).click();
	}
}

	public void ValidateDeliveryPatternName() throws Exception {
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("DeliveryPattern");

		loop: for (int i = 1; i <= 1; i++) {

			st.getRow(i).getCell(2).setCellType(CellType.STRING);

			XSSFCell input = st.getRow(i).getCell(2);
			String inputtext = input.getStringCellValue();

			loop1: do {
				pagenextbutton = driver.findElement(By.xpath("//li/a[text()='›']/parent::li")).getAttribute("class");
				List<WebElement> PatterName = driver
						.findElements(By.xpath("//div[@class='panel-body ng-scope']/div/div[1]"));
				loop2: for (WebElement webElement : PatterName) {
					PatternName = webElement.getText();
					if (PatternName.equalsIgnoreCase(inputtext)) {
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate  on Delivery Pattern Page Profile is Created",
								"Required  Profile has Been Successfully Created With the Name. " + inputtext + "",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						break loop2;
					}
				}
				if (!pagenextbutton.contains("disabled") && !(PatternName.equalsIgnoreCase(inputtext))) {
					Thread.sleep(15000);
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clickable -",
							"Navigated to the Next Page Continue the search again", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					driver.findElement(By.xpath("//li/a[text()='›']")).click();

				} else if (PatternName.contains(inputtext)) {
					driver.findElement(By.xpath("//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]")).click();
					Thread.sleep(15000);
					driver.findElement(By.xpath("//a[@class='tile-back text-decor-none']/span[contains(.,'Back')]")).click();
					break loop1;
				}

			} while (!(pagenextbutton.contains("disabled")));// li/a[text()='›']
			// li/a[text()='›']/parent::li
			wb.close();
			fis.close();
			
		}
	}
}
