package com.demo.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.demo.init.DataConfig;
import com.demo.utils.Navigations;
import com.demo.utils.angularJSWait;

/**
 * @author gaurav.b.kapoor
 *
 *         This class loads the different browsers
 */
public class initialization {

	public static WebDriver driver;

	String baseurl, browser, username, password = "";
	public static Properties prop;

	public static Properties prop1;
	
	public static DataConfig inputDataConfig;
	public static DataConfig resultDataConfig;
	public static FileOutputStream fileOut;
	public static FileInputStream filein;

	public static XSSFSheet outputWorkSheet;
	public static XSSFSheet testdataWorkSheet;

	public static int rowNumber=0;
	public static XSSFWorkbook workBook;
	public static XSSFWorkbook testdataworkBook;

	public static HashMap<String, String> projectData;
	
	
	public static Navigations objNavigation;

	angularJSWait objJSWaiter;
	
	
	private static XSSFCell testdataCell;

	private static XSSFRow testdataRow;
	

	public void initProperties() throws Exception {

		File envFile = new File

		(".\\environment.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(envFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		File locFile = new File

				(".\\locators.properties");
				FileInputStream fileInput1 = null;
				try {
					fileInput1 = new FileInputStream(locFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				 prop1 = new Properties();
				try {
					prop1.load(fileInput1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				

		browser = prop.getProperty("browser");
		baseurl = prop.getProperty("baseurl");
		
		inputDataConfig = new DataConfig(prop.getProperty("excelpath") + "AITRInputData.xlsx");
		projectData = inputDataConfig.fetchInputData("ClientData");
		
		setTestDataExcelFile();
		
		
		

	}
	
	public static void setTestDataExcelFile() throws Exception {

		try {

			// Open the Excel file

		  filein = new FileInputStream(prop.getProperty("excelpath")+ "TestData.xlsx");

		// Access the required test data sheet

		testdataworkBook = new XSSFWorkbook(filein);

		testdataWorkSheet = testdataworkBook.getSheet("data");

		} catch (Exception e){

			throw (e);

		}

    }
	
	 public String readTestData(int RowNum, int ColNum){
	    	try{

	  			testdataCell = testdataWorkSheet.getRow(RowNum).getCell(ColNum);

	  			String CellData = testdataCell.getStringCellValue();

	  			return CellData;

	  			}catch (Exception e){

					return"";

	  			}
	    
	    
	    }
	    	

	

	public WebDriver launchBrowser(String browser, String baseurl) {

		// select browser based on type
		switch (browser) {

		case "firefox":
			//System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");

			driver = new FirefoxDriver();
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "ie":
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;

		default:
			driver = new FirefoxDriver();
			break;

		}

		driver.get(baseurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		angularJSWait angjs = new angularJSWait(driver);
		
		objNavigation = new Navigations(driver);
		
	
		return driver;
	}
	
	public List<WebElement> creatListLocator(String label) {
		
	
		String labelText = prop1.getProperty(label);

		String labelTextArray[] = labelText.split("~");
		String method = labelTextArray[0];
		String loc = labelTextArray[1];

		List <WebElement> ele = null;

		switch (method) {

		case "id":
			ele = driver.findElements(By.id(loc));
			break;

		case "xpath":
			ele = driver.findElements(By.xpath(loc));
			break;

		case "linktext":
			ele = driver.findElements(By.linkText(loc));
			break;

		case "partiallinktext":
			ele = driver.findElements(By.partialLinkText(loc));
			break;

		case "css":
			ele = driver.findElements(By.cssSelector(loc));
			break;
		case "classname":
			ele = driver.findElements(By.className(loc));
			break;
		case "name":
			ele = driver.findElements(By.name(loc));
			break;
		case "tagname":
			ele = driver.findElements(By.tagName(loc));
			break;
		default:
			System.out.println("Invalid locator type");
			break;

		}
  
		
		
		return ele;

	}

	public WebElement creatLocator(String label) {
		
		
		String labelText = prop1.getProperty(label);

		String labelTextArray[] = labelText.split("~");
		String method = labelTextArray[0];
		String loc = labelTextArray[1];

		WebElement ele = null;

		switch (method) {

		case "id":
			ele = driver.findElement(By.id(loc));
			break;

		case "xpath":
			ele = driver.findElement(By.xpath(loc));
			break;

		case "linktext":
			ele = driver.findElement(By.linkText(loc));
			break;

		case "partiallinktext":
			ele = driver.findElement(By.partialLinkText(loc));
			break;

		case "css":
			ele = driver.findElement(By.cssSelector(loc));
			break;
		case "classname":
			ele = driver.findElement(By.className(loc));
			break;
		case "name":
			ele = driver.findElement(By.name(loc));
			break;
		case "tagname":
			ele = driver.findElement(By.tagName(loc));
			break;
		default:
			System.out.println("Invalid locator type");
			break;

		}
  
		
		return ele;

	}

	public WebDriver start() throws Exception {
		
		
		return launchBrowser(browser, baseurl);
		

	}

	public void stop() {
		driver.quit();
	}
	
	
}
