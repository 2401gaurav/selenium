package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
//import com.Automationstore.TestCases.TC_MAS;
//import com.Automationstore.TestCases.TC_SolutionInput;

import com.mas.TestCases.TC_MAS_Login;
import com.mas.TestCases.TC_UICheck;

import reusablefunction.reusefunction;

public class UICheck {
	String pagenextbutton;
	WebDriver driver;
	List<WebElement> category;
	reusefunction objreusable;
	List<WebElement> Table;
	
	@FindBy(how = How.XPATH, using = "//div[@class='row margin-15-t']/div/ul/li/a[text()='>']")
	@CacheLookup
	WebElement pagenextbuttonclass;
	ConfigRecommendedPage objConfigPage;
	int i;

	public UICheck(WebDriver driver) {
		this.driver = driver;

	}

	public void Validate_ProductRating() throws Exception {
		this.objConfigPage = PageFactory.initElements(driver, ConfigRecommendedPage.class);
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);
		objConfigPage.listview();
		Table = driver.findElements(By.xpath("//h4/span/span"));
	loop:	for (int j = 0; j < Table.size() ; j++) {
			Thread.sleep(1000);
			String Tabletext = Table.get(j).getText().trim();
			driver.findElement(By.xpath("//h4/span/span[contains(.,'" + Tabletext + "')]")).click();
			int RankList=0;
		int	Position=0;
		/*	TC_MAS.rowNumber = TC_MAS.rowNumber + 1;
			TC_MAS.df.WritingToExcelResults("Verify that the " + Tabletext + " Is Visible and Clickable ",
					"Required Table--- " + Tabletext + " ----is clicked", TC_MAS.rowNumber, TC_MAS.workBook,
					TC_MAS.outputWorkSheet, false);*/
			objreusable.list();
			Thread.sleep(5000);
			do {
				category = driver.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
				pagenextbutton = driver
						.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
						.getAttribute("class");

				for (i = 0; i <= category.size() - 1; i++) {
					Thread.sleep(5000);

					String categorytext = category.get(i).getText();
					categorytext.trim();
					System.out.println(categorytext);
					String Rank = driver
							.findElement(By.xpath(
									"//span[contains(.,'" + categorytext + "')]/following-sibling::div/span[2]/span"))
							.getAttribute("style");

					String Ranknumber = Rank.substring(Rank.indexOf(":") + 1, Rank.lastIndexOf("%"));
					Ranknumber.trim();
					System.out.println(Ranknumber);
					 Position=++RankList;
					String pos= Integer.toString(Position);
					TC_UICheck.rowNumber = TC_UICheck.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults1(categorytext, Ranknumber, pos, TC_UICheck.rowNumber,
							TC_UICheck.workBook, TC_UICheck.outputWorkSheet, false);
					
/*if(this.ValidateProductRank(categorytext, Ranknumber, RankList)==true){
	System.out.println("Present");
}*/
				}
			
				if (i == category.size() && !(pagenextbutton.contains("disabled"))) {
					TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
					TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clickable -",
							"Navigated to the Next Page Continue the search again", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
							TC_MAS_Login.outputWorkSheet, false);
					driver.findElement(By.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']")).click();

				}
				Table = driver.findElements(By.xpath("//h4/span/span"));
			} while (!(pagenextbutton.contains("disabled")));
		}
	}

	public  Boolean ValidateProductRank(String ProductExcel,String ScoreExcel,int RankExcel) throws Exception {
Boolean Status=false;
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\refine.xlsx");

		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet("RecommendationExtract-84051");
		int row = st.getLastRowNum();
		for (int i = 1; i <= row; i++) {

			st.getRow(i).getCell(1).setCellType(CellType.STRING);
			st.getRow(i).getCell(0).setCellType(CellType.STRING);
			st.getRow(i).getCell(3).setCellType(CellType.STRING);

			XSSFCell Product = st.getRow(i).getCell(0);
			String Producttext = Product.getStringCellValue();
			XSSFCell PercentageScore = st.getRow(i).getCell(3);
			String PercentageScoretext = PercentageScore.getStringCellValue();
			XSSFCell Rank = st.getRow(i).getCell(1);
			String Ranktext = Rank.getStringCellValue();
			System.out.println(Producttext);
			System.out.println(PercentageScoretext);
			System.out.println(Ranktext);
			Thread.sleep(15000);
			String ranklist=Integer.toString(RankExcel);
if(ProductExcel.equalsIgnoreCase(Producttext)&&ScoreExcel.equalsIgnoreCase(PercentageScoretext)&&ranklist.equalsIgnoreCase(Ranktext)){
	Status=true;
}
		}
		wb.close();
		fis.close();
return Status;
	}
}
