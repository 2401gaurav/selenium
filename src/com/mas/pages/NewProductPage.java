package com.mas.pages;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import reusablefunction.reusefunction;
//import com.Automationstore.TestCases.TC_MAS;
import com.mas.TestCases.TC_MAS_Login;
 
public class NewProductPage {
       @FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[1]/li[4]/a")
       @CacheLookup
       WebElement newProduct;
       @FindBy(how= How.XPATH,using =".//*[@id='page-content-wrapper']/div/div[1]/ul[2]/li[3]/img")
       WebElement listView;
       @FindBy(how= How.XPATH,using ="//h4/span[contains(@class,'ng-scope')]/span")
       WebElement productHeader;
       @FindBy(how = How.XPATH, using = "//div[@class='row margin-15-t']/div/ul/li/a[text()='>']")
      	@CacheLookup
      	WebElement pagenextbuttonclass;
   	public  String  text;
   
       WebDriver driver;
       static String toolHeader;
       static String toolSubHeader;
       static String toolDesc;
       String pagenextbutton;
       public int attempts = 0;
       String textLink;
       List<WebElement> category;
       reusefunction objreusable;
   	
       public NewProductPage(WebDriver driver){
             
              this.driver=driver;
       }
      
       public void verifyNewDisplay() throws Exception{
             
              try {
              if(newProduct.isDisplayed()){
              newProduct.click();
              Thread.sleep(20000);
              TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
              TC_MAS_Login.resultDataConfig.WritingToExcelResults(
              "Validate the New Button is Present and Navigates to New HomePage",
              "New button is clickable and Navigates New HomePage", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
              TC_MAS_Login.outputWorkSheet, false);
              }
              } catch (Exception e) {
              TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
              TC_MAS_Login.resultDataConfig.WritingToExcelResults(
              "Validate the New Button is present and Navigates to Admin HomePage",
              "Error occured - New Button is not clickable  for newProduct", TC_MAS_Login.rowNumber,
              TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
              driver.quit();
              System.exit(1);
              }
              try {
              if( listView.isDisplayed()){
              listView.click();
              Thread.sleep(5000);
              TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
              TC_MAS_Login.resultDataConfig.WritingToExcelResults(
              "Validate the listView is Present and Navigates to listView HomePage",
              "listView is clickable and Navigates New HomePage", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
              TC_MAS_Login.outputWorkSheet, false);
              }} catch (Exception e2) {
              TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
              TC_MAS_Login.resultDataConfig.WritingToExcelResults(
              "Validate the listView is present and Navigates to listView HomePage",
              "listView is not clickable", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
              driver.quit();
              System.exit(1);
              }
             
              }
 
 
  public void verifyNewProductCategory() throws Exception{
              
        	 this.objreusable = PageFactory.initElements(driver, reusefunction.class);
     		//Thread.sleep(20000);
         
        	 int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
				for (int j = 0; j < tablesize; j++) {

					List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
					String textLink = table.get(j).getText();
					String tabletext = textLink;
					tabletext = tabletext.substring(0, tabletext.indexOf("("));
					tabletext = StringUtils.trim(tabletext);

					System.out.println(tabletext);
					Thread.sleep(1000);

					driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
					//Thread.sleep(500);

					objreusable.list();
					Thread.sleep(1000);
        	 category = driver
						.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));

				int counter = 1;
				loop: for (int i = 0; i <= category.size() + 1; i++) {
					//Thread.sleep(200);

					if (i <= category.size() - 1) {
						pagenextbutton = driver
								.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
								.getAttribute("class");
						String categorytext = category.get(i).getText();
						System.out.println(categorytext);
						// Thread.sleep(1000);
						if (objreusable.validatebyexcel1(categorytext,tabletext) == true) {
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the Product - " + categorytext + "is present under -" + tabletext,
									"Required Product " + categorytext
											+ " is  Present and  matching whit the Input Data under -" + tabletext
											+ " and under -" + tabletext + " Category",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							continue loop;

						} else {
							// Thread.sleep(2000);
							if (i <= category.size()) {

							//Thread.sleep(2000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults(
										"Verify that the Product - " + categorytext + "is present under -"
												+ tabletext,
										"Error Occured-Required Product " + categorytext
												+ " is  not Present under -" + tabletext,
										TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								System.out.println("tool not found-->" + categorytext);

								continue loop;

							}
						}
						//Thread.sleep(2000);
					} else if ((!pagenextbutton.contains("disabled"))) {
						counter = counter + 1;
						Thread.sleep(2000);
						driver.findElement(By
								.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
								.click();
						Thread.sleep(2000);
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
								"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						category = driver.findElements(
								By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
						i = -1;
						System.out.println(i);
					
					}}}
                    
  } 
	public void validateNewProduct() throws Exception {
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);
	
			

				File excelfilename = new File(
						"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\refine.xlsx");

				FileInputStream fis = new FileInputStream(excelfilename);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet st = wb.getSheet("New_Products");
				int row = st.getLastRowNum();
				for (int i = 1; i <= row; i++) {

					st.getRow(i).getCell(0).setCellType(CellType.STRING);
					
					st.getRow(i).getCell(1).setCellType(CellType.STRING);
				
					
					
					XSSFCell link = st.getRow(i).getCell(1);
					String linkData = link.getStringCellValue();
					XSSFCell tool = st.getRow(i).getCell(0);
					String tooltext = tool.getStringCellValue();
					
				
					System.out.println(tooltext);
					System.out.println(linkData);
					
					Thread.sleep(15000);
					

						if((objreusable.selectNewProductLink(linkData))==true)
						{
							
					
						
						loop1: do {
							pagenextbutton = driver
									.findElement(By
											.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
									.getAttribute("class");
							objreusable.list();
							List<WebElement> listview = driver.findElements(
									By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
						
							loop: for (WebElement list : listview) {
									text = list.getText();
								System.out.println(text);
								if (text.contains(tooltext)) {
									System.out.println(tooltext + "is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + "is present under -" + linkData,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data  and present  under - " + linkData + " Link",TC_MAS_Login.rowNumber,
													TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									break loop;

								}
							}
							if (!pagenextbutton.contains("disabled") && !(text.contains(tooltext))) {
								Thread.sleep(15000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clickable -",
										"Navigated to the Next Page Continue the search again", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']"))
										.click();

							} else if (text.contains(tooltext)) {
								break loop1;
							}
								
							
						} while (!(pagenextbutton.contains("disabled")));
						}
						else{
							break;
						}
							if((pagenextbutton.contains("disabled"))&&!(text.contains(tooltext))){
								System.out.println(tooltext+" not found");
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults(
										"Verify that the Product - " + tooltext + "is present under -" + linkData,
										"Error Occurred-Required Product " + tooltext
												+ " is not   matching with the Input Data under - and is not present under -" + linkData + " Link",TC_MAS_Login.rowNumber,
												TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							}
						
					}
		
					wb.close();
					fis.close();}
}
