package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.mas.TestCases.TC_MAS_Login;

public class FeedbackPage {

	static int pageCount;
	static int ratedProductCount;
	static int unRatedProductCount;
	static String pageNextButtonClass;
	static List<WebElement> ratedProduct;
	static List<WebElement> unRatedProduct;

	@FindBy(how = How.XPATH, using = ".//button[text()='Ok']")
	WebElement btn_popupOk;

	@FindBy(how = How.XPATH, using = ".//button[@ng-click='saveProductRatings()']")
	WebElement btn_saveFeedback;

	WebDriver driver;

	@FindBy(how = How.XPATH, using = ".//div[@class='filter-rating']/span[contains(text(),'Filter by')]/following::span[contains(@ng-class,'selectedFilter')]/a[text()='All']")
	WebElement lnk_AllFilter;

	@FindBy(how = How.XPATH, using = "//div[@class='row page-title']/div[2]/ul/li/a[text() = 'Feedback']")
	WebElement lnk_feedback;

	@FindBy(how = How.XPATH, using = "//span[text()='Back']/parent::a")
	WebElement lnk_feedbackNavigateBackBtn;

	@FindBy(how = How.XPATH, using = "//a[@aria-controls='Feedback']")
	WebElement lnk_feedbackRatingTab;

	@FindBy(how = How.XPATH, using = ".//div[@class='filter-rating']/span[contains(text(),'Filter by')]/following::span[contains(@ng-class,'selectedFilter')]/a[text()='Rated']")
	WebElement lnk_ratedFilter;

	@FindBy(how = How.XPATH, using = ".//div[@class='filter-rating']/span[contains(text(),'Filter by')]/following::span[contains(@ng-class,'selectedFilter')]/a[text()='Unrated']")
	WebElement lnk_unRatedFilter;

	@FindBy(how = How.XPATH, using = ".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '«']")
	WebElement prevBoundaryLink;

	/*
	 * @FindBy(how = How.XPATH, using =
	 * ".//*[@id='page-content-wrapper']/div/div[1]/div[2]/div/div/div/input")
	 * WebElement txt_searchBox;
	 */

	@FindBy(how = How.XPATH, using = ".//input[@ng-model='SearchValue']")
	WebElement txt_searchBox;

	@FindBy(how = How.XPATH, using = ".//span[span[@class='review-user ng-binding'] and span[@class='ng-binding']]/preceding::rate-yo[1]/div/div[@class='jq-ry-rated-group jq-ry-group']")
	List<WebElement> svg_starRating;

	@FindBy(how = How.XPATH, using = ".//rate-yo[@ng-model='product.Rating']/div/div[@class='jq-ry-rated-group jq-ry-group']")
	WebElement productRating;

	@FindBy(how = How.XPATH, using = ".//div[@class = 'row feedback-summary']/div/div[@class='row star-value']/div/span/span")
	WebElement avgRatingValue;

	@FindBy(how = How.XPATH, using = "//div[text()[normalize-space() = 'Rating']]/following-sibling::div/rate-yo/div/div[@class='jq-ry-normal-group jq-ry-group']/*[name()='svg']/*[name()='polygon']")
	List<WebElement> toolDetailsRatingInput;
	
	@FindBy(how = How.XPATH, using = "//div[text()[normalize-space() = 'Feedback']]/following-sibling::div/textarea")
	WebElement toolDetailsFeedBackInput;
	
	@FindBy(how = How.XPATH, using = "//div[@id='feedback']/div/div/div/div/button[text()[normalize-space() = 'Save']]")
	WebElement toolDetailsSaveFeedBack;
	
	@FindBy(how = How.XPATH, using = "//button[text()[normalize-space() = 'Ok']]")
	WebElement toolDetailsConfirmSaveFeedback;

	public FeedbackPage(WebDriver driver) {
		this.driver = driver;

	}

	/*
	 * public void enterRatingAndFeedback2(String provisionType) throws
	 * InterruptedException, IOException { // lnk_feedback.click(); File
	 * excelfilename = new File(
	 * "C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx"
	 * ); FileInputStream fis = new FileInputStream(excelfilename); XSSFWorkbook
	 * wb = new XSSFWorkbook(fis); XSSFSheet st = wb.getSheet(provisionType);
	 * for (int i = 1; i <= st.getLastRowNum(); i++) { XSSFRow excelRow =
	 * st.getRow(i); XSSFCell excelCell1 = excelRow.getCell(0); XSSFCell
	 * excelCell2 = excelRow.getCell(1); XSSFCell excelCell3 =
	 * excelRow.getCell(2); excelCell1.setCellType(CellType.STRING); String
	 * product = excelCell1.getStringCellValue();
	 * excelCell2.setCellType(CellType.STRING); int rating =
	 * Integer.parseInt(excelCell2.getStringCellValue());
	 * excelCell3.setCellType(CellType.STRING); String feedbackTxt =
	 * excelCell3.getStringCellValue(); // Thread.sleep(15000); // // // int
	 * pageCount = driver .findElements(By
	 * .xpath(".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"
	 * )) .size(); loop: for (int j = 0; j <= pageCount - 1; j++) {
	 * List<WebElement> pageListNumber = driver.findElements(By.xpath(
	 * ".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"
	 * )); pageListNumber.get(j).click(); List<WebElement> toolNameList =
	 * driver.findElements(By.xpath(
	 * ".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"
	 * )); for (int k = 0; k <= toolNameList.size() - 1; k++) { String toolName
	 * = toolNameList.get(k).getText(); if (toolName.contains(product)) {
	 * List<WebElement> starRating = toolNameList.get(k).findElements(By.xpath(
	 * "./following::div[1]/rate-yo/div/div[@class='jq-ry-normal-group jq-ry-group']/[name()='svg']/[name()='polygon']"
	 * )); Thread.sleep(5000); for (int l = 0; l <= rating - 1; l++) { Actions
	 * action = new Actions(driver);
	 * action.click(starRating.get(l)).build().perform(); } WebElement
	 * feedBackText = toolNameList.get(k)
	 * .findElement(By.xpath("./following::div[5]/div/textarea"));
	 * feedBackText.sendKeys(feedbackTxt); break loop; } else if ((k ==
	 * toolNameList.size() - 1) && (!toolName.contains(product)) && (j <
	 * pageCount - 1)) { continue loop; } else if ((k == toolNameList.size() -
	 * 1) && (!toolName.contains(product)) && (j == pageCount - 1)) {
	 * System.out.println(product + " not Found"); break loop; } } } }
	 * wb.close(); fis.close(); btn_saveFeedback.click(); Thread.sleep(5000);
	 * btn_popupOk.click(); }
	 */

	public void enterRatingAndFeedback(String provisionType) throws InterruptedException, IOException {
		lnk_feedback.click();
		Thread.sleep(10000);
		lnk_AllFilter.click();
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		for (int i = 1; i <= st.getLastRowNum(); i++) {
			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell1 = excelRow.getCell(0);
			XSSFCell excelCell2 = excelRow.getCell(1);
			XSSFCell excelCell3 = excelRow.getCell(2);
			excelCell1.setCellType(CellType.STRING);
			String product = excelCell1.getStringCellValue();
			excelCell2.setCellType(CellType.STRING);
			int rating = Integer.parseInt(excelCell2.getStringCellValue());
			excelCell3.setCellType(CellType.STRING);
			String feedbackTxt = excelCell3.getStringCellValue();
			// Thread.sleep(15000);
			if ((rating >= 1) && (rating <= 5)) {
				loop: do {
					WebElement pageNextButton = driver.findElement(By.xpath(
							".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
					pageNextButtonClass = driver
							.findElement(By
									.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
							.getAttribute("class");
					List<WebElement> toolNameList = driver.findElements(By.xpath(
							".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
					for (int j = 0; j <= toolNameList.size() - 1; j++) {
						String toolName = toolNameList.get(j).getText();
						if (toolName.contains(product)) {
							List<WebElement> starRating = toolNameList.get(j).findElements(By.xpath(
									"./following::div[1]/rate-yo/div/div[@class='jq-ry-normal-group jq-ry-group']/*[name()='svg']/*[name()='polygon']"));
							Thread.sleep(5000);
							for (int l = 0; l <= rating - 1; l++) {
								Actions action = new Actions(driver);
								action.click(starRating.get(l)).build().perform();
							}
							WebElement feedBackText = toolNameList.get(j)
									.findElement(By.xpath("./following::div[5]/div/textarea"));
							feedBackText.clear();
							feedBackText.sendKeys(feedbackTxt);
							if (i < st.getLastRowNum()) {
								prevBoundaryLink.click();
							}
							break loop;
						} else if ((j == toolNameList.size() - 1) && (!toolName.contains(product))
								&& (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
							Thread.sleep(2000);
							pageNextButton.click();
							continue loop;
						} else if ((j == toolNameList.size() - 1) && (!toolName.contains(product))
								&& (!pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
							System.out.println(product + " not Found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate while product"+product+ " is found",
									"Error occured - product"+product+ " not found", TC_MAS_Login.rowNumber,
									TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							Thread.sleep(2000);
							break loop;
						}
					}
				} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));
				wb.close();
				fis.close();
				btn_saveFeedback.click();
				Thread.sleep(5000);
				try {
					btn_popupOk.click();
				} catch (Exception e) {

				}
			} else {
				System.out.println("Feedback Comments not Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate while user gives the input greater than 5 or less than 1",
						"Error occured - user gave the input greater than 5 or less than 1", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				break;
			}

		}

	}

	/*
	 * public void verifyRatingAndFeedback2(String provisionType) throws
	 * InterruptedException, IOException {
	 * 
	 * // lnk_feedback.click(); File excelfilename = new File(
	 * "C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx"
	 * ); FileInputStream fis = new FileInputStream(excelfilename); XSSFWorkbook
	 * wb = new XSSFWorkbook(fis); XSSFSheet st = wb.getSheet(provisionType);
	 * for (int i = 1; i <= st.getLastRowNum(); i++) { XSSFRow excelRow =
	 * st.getRow(i); XSSFCell excelCell1 = excelRow.getCell(0); XSSFCell
	 * excelCell2 = excelRow.getCell(1); XSSFCell excelCell3 =
	 * excelRow.getCell(2); excelCell1.setCellType(CellType.STRING); String
	 * product = excelCell1.getStringCellValue();
	 * excelCell2.setCellType(CellType.STRING); int rating =
	 * Integer.parseInt(excelCell2.getStringCellValue());
	 * excelCell3.setCellType(CellType.STRING); String feedbackTxt =
	 * excelCell3.getStringCellValue(); Thread.sleep(5000); int pageCount =
	 * driver .findElements(By
	 * .xpath(".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"
	 * )) .size(); loop: for (int j = 0; j <= pageCount - 1; j++) {
	 * List<WebElement> pageListNumber = driver.findElements(By.xpath(
	 * ".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"
	 * )); pageListNumber.get(j).click(); List<WebElement> toolNameList =
	 * driver.findElements(By.xpath(
	 * ".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"
	 * )); for (int k = 0; k <= toolNameList.size() - 1; k++) { String toolName
	 * = toolNameList.get(k).getText(); if (toolName.contains(product)) {
	 * WebElement savedStarRating = toolNameList.get(k).findElement(By
	 * .xpath("./following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group']"
	 * )); Thread.sleep(5000); String stringRatingSaved =
	 * StringUtils.substring(savedStarRating.getAttribute("style"), 8, 10); int
	 * savedRating = Integer.parseInt(stringRatingSaved); int inputrating =
	 * (rating / 5) * 100; if (savedRating == inputrating) {
	 * System.out.println("Rating Saved Successfully"); } else {
	 * System.out.println("Fail - Rating Mismatch"); } WebElement feedBackText =
	 * toolNameList.get(k)
	 * .findElement(By.xpath("./following::div[5]/div/textarea")); if
	 * (feedbackTxt.equalsIgnoreCase(feedBackText.getAttribute("value"))) {
	 * System.out.println("Feedback Saved Successfully"); } else {
	 * System.out.println("Feedback Not Saved Successfully"); } break loop; }
	 * else if ((k == toolNameList.size() - 1) && (!toolName.contains(product))
	 * && (j < pageCount - 1)) { continue loop; } else if ((k ==
	 * toolNameList.size() - 1) && (!toolName.contains(product)) && (j ==
	 * pageCount - 1)) { System.out.println(product + " not Found"); break loop;
	 * } } } } wb.close(); fis.close(); btn_saveFeedback.click();
	 * Thread.sleep(5000); btn_popupOk.click(); }
	 */

	public void enterRatingAndFeedbackAllTools() throws InterruptedException, IOException {
		lnk_feedbackNavigateBackBtn.click();
		Thread.sleep(15000);
		lnk_feedback.click();
		Thread.sleep(10000);
		lnk_AllFilter.click();
		// Thread.sleep(15000);
		do {
			WebElement pageNextButton = driver.findElement(By.xpath(
					".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
			pageNextButtonClass = driver
					.findElement(By
							.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
					.getAttribute("class");
			List<WebElement> toolNameList = driver.findElements(By.xpath(
					".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
			for (int j = 0; j <= toolNameList.size() - 1; j++) {
				List<WebElement> starRating = toolNameList.get(j).findElements(By.xpath(
						"./following::div[1]/rate-yo/div/div[@class='jq-ry-normal-group jq-ry-group']/*[name()='svg']/*[name()='polygon']"));
				Thread.sleep(5000);
				for (int l = 0; l <= starRating.size() - 1; l++) {
					Actions action = new Actions(driver);
					action.click(starRating.get(l)).build().perform();
				}
				WebElement feedBackText = toolNameList.get(j).findElement(By.xpath("./following::div[5]/div/textarea"));
				feedBackText.clear();
				feedBackText.sendKeys("TestFeedBack");
			}
			pageNextButton.click();
		} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));
		btn_saveFeedback.click();
		Thread.sleep(5000);
		try {
			btn_popupOk.click();
		} catch (Exception e) {

		}
	}

	public void validateNoDataRatedFilter() throws InterruptedException {
		lnk_feedback.click();
		Thread.sleep(10000);
		lnk_AllFilter.click();
		List<WebElement> allPageListNumber = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"));
		int allPageCount = allPageListNumber.size();
		allPageListNumber.get(allPageCount - 1).click();
		Thread.sleep(5000);
		List<WebElement> allToolNameList = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
		int allFilter_totalToolCount = ((allPageCount - 1) * 6) + allToolNameList.size();

		lnk_unRatedFilter.click();
		List<WebElement> unRatedPageListNumber = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"));
		int unRatedPageCount = unRatedPageListNumber.size();
		unRatedPageListNumber.get(unRatedPageCount - 1).click();
		List<WebElement> unRatedToolNameList = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
		int unRatedFilter_totalToolCount = ((unRatedPageCount - 1) * 6) + unRatedToolNameList.size();

		if (allFilter_totalToolCount == unRatedFilter_totalToolCount) {

			lnk_ratedFilter.click();

			WebElement noDataAvailableText = driver.findElement(
					By.xpath(".//*[@id='collapseZero']/div/div[@ng-show='productRatingList.length == 0']/div"));

			if ((noDataAvailableText.getText().equalsIgnoreCase("No data available"))
					&& (noDataAvailableText.findElement(By.xpath("./parent::div")).getAttribute("class"))
							.equalsIgnoreCase("row")) {
				System.out.println("No Data displayed in Rated Filter when tool is not rated");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify 'No Data displayed' text is displayed in Rated Filter screen when none of tools are not rated",
						"'No Data displayed' text is displayed in Rated Filter screen when none of tools are not rated",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

			} else {
				System.out.println("Fail - Data displayed in Rated Filter when tool is not rated");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify 'No Data displayed' text is displayed in Rated Filter screen when none of tools are not rated",
						"Error occured - 'No Data displayed' text is displayed in Rated Filter screen when none of tool are not rated",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}

		}

	}

	public void validateNoDataUnRatedFilter() throws InterruptedException {
		Thread.sleep(10000);
		lnk_AllFilter.click();
		List<WebElement> allPageListNumber = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"));
		int allPageCount = allPageListNumber.size();
		allPageListNumber.get(allPageCount - 1).click();
		Thread.sleep(5000);
		List<WebElement> allToolNameList = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
		int allFilter_totalToolCount = ((allPageCount - 1) * 6) + allToolNameList.size();

		lnk_ratedFilter.click();
		List<WebElement> ratedPageListNumber = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"));
		int ratedPageCount = ratedPageListNumber.size();
		ratedPageListNumber.get(ratedPageCount - 1).click();
		List<WebElement> ratedToolNameList = driver.findElements(By.xpath(
				".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
		int ratedFilter_totalToolCount = ((ratedPageCount - 1) * 6) + ratedToolNameList.size();
		if (allFilter_totalToolCount == ratedFilter_totalToolCount) {
			lnk_unRatedFilter.click();
			WebElement noDataAvailableText = driver.findElement(
					By.xpath(".//*[@id='collapseZero']/div/div[@ng-show='productRatingList.length == 0']/div"));

			if ((noDataAvailableText.getText().equalsIgnoreCase("No data available"))
					&& (noDataAvailableText.findElement(By.xpath("./parent::div")).getAttribute("class"))
							.equalsIgnoreCase("row")) {
				System.out.println("No Data displayed in unRated Filter when all tools are rated");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify 'No Data displayed' text is displayed in Unrated Filter screen when all the tools are rated",
						"'No Data displayed' text is displayed in Unrated Filter screen when all the tools are rated",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				System.out.println("Fail - Data displayed in unrated Filter when all the tools are rated");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Verify 'No Data displayed' text is displayed in Unrated Filter screen when all the tools are rated",
						"Error occured - 'No Data displayed' text is displayed in Unrated Filter screen when all the tools are rated",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
		}

	}

	public void verifyFeedbackRatingFilter() throws InterruptedException {

		List<WebElement> filterList = driver.findElements(By.xpath(
				".//div[@class='filter-rating']/span[contains(text(),'Filter by')]/following::span[contains(@ng-class,'selectedFilter')]/a"));
		for (WebElement filter : filterList) {
			filter.click();
			Thread.sleep(5000);
			String filterName = filter.getText();
			switch (filterName) {
			case "All":

				pageCount = 0;
				ratedProductCount = 0;
				unRatedProductCount = 0;

				pageCount = driver
						.findElements(By
								.xpath(".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"))
						.size();

				if (pageCount > 0) {
					do {
						WebElement pageNextButton = driver.findElement(By.xpath(
								".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
						pageNextButtonClass = driver
								.findElement(By
										.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
								.getAttribute("class");
						if (driver
								.findElements(
										By.xpath(".//div[@id='collapseZero']/div/div[@class='row feedback-list']"))
								.size() > 0) {
							ratedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and ((contains(@style,'100%')) or (contains(@style,'80%')) or (contains(@style,'60%')) or (contains(@style,'40%')) or (contains(@style,'20%')))]"));

							unRatedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and contains(@style,'width: 0%')]"));

							ratedProductCount += ratedProduct.size();
							unRatedProductCount += unRatedProduct.size();

							if ((pageCount > 1) && (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
								pageNextButton.click();
							}
						}
					} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));

					if ((ratedProductCount > 0) || (unRatedProductCount > 0)) {
						System.out.println("'All' Filter Option is working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'All' Filter Option should display both rated and unrated tools in page",
								"'All' Filter Option displays both rated and unrated tools in page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} else {
						System.out.println("'All' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'All' Filter Option should display both rated and unrated tools in page",
								"Error occured - 'All' Filter Option is not displaying both rated and unrated tools in page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else if (pageCount == 0) {

					try {
						WebElement noDataAvailableText = driver.findElement(By.xpath(
								".//*[@id='collapseZero']/div/div[@ng-show='productRatingList.length == 0']/div"));
						if ((noDataAvailableText.getText().equalsIgnoreCase("No data available"))
								&& (noDataAvailableText.findElement(By.xpath("./parent::div")).getAttribute("class"))
										.equalsIgnoreCase("row")) {
							System.out.println("'All' Filter Option is not working as expected");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the 'Unrated' Filter Option should display only unrated tools in page",
									"Error occured - 'All' Filter Option is working as expected",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					} catch (Exception e) {

						System.out.println("'All' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Unrated' Filter Option should display only unrated tools in page",
								"Error occured - " + e.getMessage(), TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
								TC_MAS_Login.outputWorkSheet, false);
					}
				}
				break;

			case "Unrated":

				pageCount = 0;
				ratedProductCount = 0;
				unRatedProductCount = 0;

				pageCount = driver
						.findElements(By
								.xpath(".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"))
						.size();
				if (pageCount > 0) {

					do {
						WebElement pageNextButton = driver.findElement(By.xpath(
								".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
						pageNextButtonClass = driver
								.findElement(By
										.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
								.getAttribute("class");
						if (driver
								.findElements(
										By.xpath(".//div[@id='collapseZero']/div/div[@class='row feedback-list']"))
								.size() > 0) {
							ratedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and ((contains(@style,'100%')) or (contains(@style,'80%')) or (contains(@style,'60%')) or (contains(@style,'40%')) or (contains(@style,'20%')))]"));

							unRatedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and contains(@style,'width: 0%')]"));

							ratedProductCount += ratedProduct.size();
							unRatedProductCount += unRatedProduct.size();

							if ((pageCount > 1) && (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
								pageNextButton.click();
							}
						}
					} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));

					if ((ratedProductCount == 0) && (unRatedProductCount > 0)) {
						System.out.println("'Unrated' Filter Option is working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Unrated' Filter Option should display only unrated tools in page",
								"'Unrated' Filter Option displays only unrated tools in page", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} else {
						System.out.println("'Unrated' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Unrated' Filter Option should display only unrated tools in page",
								"Error occured - 'Unrated' Filter Option is not displaying only unrated tools in page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
					break;
				} else if (pageCount == 0) {

					try {
						WebElement noDataAvailableText = driver.findElement(By.xpath(
								".//*[@id='collapseZero']/div/div[@ng-show='productRatingList.length == 0']/div"));
						if ((noDataAvailableText.getText().equalsIgnoreCase("No data available"))
								&& (noDataAvailableText.findElement(By.xpath("./parent::div")).getAttribute("class"))
										.equalsIgnoreCase("row")) {
							System.out.println("No Data displayed in UnRated Filter when all tool are rated");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify 'No Data displayed' text is displayed in UnRated Filter screen when all of tools is rated",
									"'No Data displayed' text is displayed in UnRated Filter screen when all of tools is rated",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

						} else {
							System.out.println("Fail - Data displayed in UnRated Filter when all tools are rated");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify 'No Data displayed' text is displayed in UnRated Filter screen when all of tools is rated",
									"Error occured - 'No Data displayed' text is displayed in UnRated Filter screen when all of tools is rated",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					} catch (Exception e) {

						System.out.println("'Unrated' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Unrated' Filter Option should display only unrated tools in page",
								"Error occured - 'Unrated' Filter Option is working as expected",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

					}

				}
				break;

			case "Rated":

				pageCount = 0;
				ratedProductCount = 0;
				unRatedProductCount = 0;

				pageCount = driver
						.findElements(By
								.xpath(".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li[contains(@class,'pagination-page ng-scope')]/a"))
						.size();
				if (pageCount > 0) {
					do {
						WebElement pageNextButton = driver.findElement(By.xpath(
								".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
						pageNextButtonClass = driver
								.findElement(By
										.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
								.getAttribute("class");
						if (driver
								.findElements(
										By.xpath(".//div[@id='collapseZero']/div/div[@class='row feedback-list']"))
								.size() > 0) {
							ratedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and ((contains(@style,'100%')) or (contains(@style,'80%')) or (contains(@style,'60%')) or (contains(@style,'40%')) or (contains(@style,'20%')))]"));

							unRatedProduct = driver.findElements(By.xpath(
									".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']/following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group' and contains(@style,'width: 0%')]"));

							ratedProductCount += ratedProduct.size();
							unRatedProductCount += unRatedProduct.size();

							if ((pageCount > 1) && (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
								pageNextButton.click();
							}
						}
					} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));

					if ((ratedProductCount > 0) && (unRatedProductCount == 0)) {
						System.out.println("'Rated' Filter Option is working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Rated' Filter Option should display only Rated tools in page",
								"'Unrated' Filter Option displays only Rated tools in page", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					} else {
						System.out.println("'Rated' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Unrated' Filter Option should display only unrated tools in page",
								"Error occured - 'Unrated' Filter Option is not displaying only Rated tools in page",
								TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
					}
				} else if (pageCount == 0) {

					try {
						WebElement noDataAvailableText = driver.findElement(By.xpath(
								".//*[@id='collapseZero']/div/div[@ng-show='productRatingList.length == 0']/div"));
						if ((noDataAvailableText.getText().equalsIgnoreCase("No data available"))
								&& (noDataAvailableText.findElement(By.xpath("./parent::div")).getAttribute("class"))
										.equalsIgnoreCase("row")) {
							System.out.println("No Data displayed in Rated Filter when all of tools is not rated");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify 'No Data displayed' text is displayed in Rated Filter screen when all of tools is not rated",
									"'No Data displayed' text is displayed in Rated Filter screen when all of tools is not rated",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

						} else {
							System.out
									.println("Fail - No Data displayed in Rated Filter when all of tools is not rated");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify 'No Data displayed' text is displayed in Rated Filter screen when all of tools is not rated",
									"Error occured - 'No Data displayed' text is displayed in Rated Filter screen when  all of tools is not rated",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					} catch (Exception e) {

						System.out.println("'Rated' Filter Option is not working as expected");
						TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
						TC_MAS_Login.resultDataConfig.WritingToExcelResults(
								"Validate the 'Rated' Filter Option should display only rated tools in page",
								"Error occured - 'Rated' Filter Option is working as expected", TC_MAS_Login.rowNumber,
								TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

					}
				}
				break;
			}
		}
	}

	public void verifyRatingAndFeedback(String provisionType) throws InterruptedException, IOException {

		Thread.sleep(10000);
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		for (int i = 1; i <= st.getLastRowNum(); i++) {
			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell1 = excelRow.getCell(0);
			XSSFCell excelCell2 = excelRow.getCell(1);
			XSSFCell excelCell3 = excelRow.getCell(2);
			excelCell1.setCellType(CellType.STRING);
			String product = excelCell1.getStringCellValue();
			excelCell2.setCellType(CellType.STRING);
			int rating = Integer.parseInt(excelCell2.getStringCellValue());
			excelCell3.setCellType(CellType.STRING);
			String feedbackTxt = excelCell3.getStringCellValue();
			// lnk_feedback.click();
			// lnk_AllFilter.click();
			Thread.sleep(5000);
			loop: do {
				WebElement pageNextButton = driver.findElement(By.xpath(
						".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']"));
				pageNextButtonClass = driver
						.findElement(By
								.xpath("//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row']/div/ul/li/a[text() = '›']/parent::li"))
						.getAttribute("class");
				List<WebElement> toolNameList = driver.findElements(By.xpath(
						".//div[@id='productRating']/div/div[@id='collapseZero']/div/div[@class='row feedback-list']/div/div/span[@ng-bind='productRating.Name']"));
				for (int j = 0; j <= toolNameList.size() - 1; j++) {
					String toolName = toolNameList.get(j).getText();
					if (toolName.contains(product)) {
						WebElement savedStarRating = toolNameList.get(j).findElement(By
								.xpath("./following::div[1]/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group']"));
						Thread.sleep(5000);
						String stringRatingSaved = StringUtils.substringBetween(savedStarRating.getAttribute("style"),
								": ", "%");
						int savedRating = Integer.parseInt(stringRatingSaved);
						float tempValue = ((float) rating / 5) * 100;
						int inputrating = (int) tempValue;
						if (savedRating == inputrating) {
							System.out.println(product + " - Rating Saved Successfully");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the Feedback Rating for " + product + " is saved in Feedback Page",
									"Feedback Rating for " + product + " is saved in Feedback Page",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						} else {
							System.out.println(product + "- Fail - Rating Mismatch");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the Feedback Rating for " + product + " is saved in Feedback Page",
									"Error occured - Feedback Rating for " + product + " is not saved in Feedback Page",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
						WebElement feedBackText = toolNameList.get(j)
								.findElement(By.xpath("./following::div[5]/div/textarea"));
						if (feedbackTxt.equalsIgnoreCase(feedBackText.getAttribute("value"))) {
							System.out.println(product + " - Feedback Saved Successfully");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the Feedback comments for " + product + " is saved in Feedback Page",
									"Feedback Comments for " + product + " is saved in Feedback Page",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						} else {
							System.out.println(product + " - Feedback Not Saved Successfully");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Validate the Feedback Comments for " + product + " is saved in Feedback Page",
									"Error occured - Feedback Comments for " + product
											+ " is not saved in Feedback Page",
									TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
						if (i < st.getLastRowNum()) {
							prevBoundaryLink.click();
						}
						break loop;
					} else if ((j == toolNameList.size() - 1) && (!toolName.contains(product))
							&& (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
						Thread.sleep(2000);
						pageNextButton.click();
						continue loop;
					} else if ((j == toolNameList.size() - 1) && (!toolName.contains(product))
							&& (!pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"))) {
						System.out.println(product + " not Found");
						break loop;
					}
				}
			} while (pageNextButtonClass.equalsIgnoreCase("pagination-next ng-scope"));
		}
		wb.close();
		fis.close();
	}

	public void verifyToolFeedbackAndRating(String provisionType, String UserID)
			throws IOException, InterruptedException {
		Format formatter = new SimpleDateFormat("dd MMMM yyyy");
		String currentDate = formatter.format(new Date());
		System.out.println(currentDate);
		File excelfilename = new File(
				"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\AutomationStoreInputData.xlsx");
		FileInputStream fis = new FileInputStream(excelfilename);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet st = wb.getSheet(provisionType);
		for (int i = 1; i <= st.getLastRowNum(); i++) {
			XSSFRow excelRow = st.getRow(i);
			XSSFCell excelCell1 = excelRow.getCell(0);
			XSSFCell excelCell2 = excelRow.getCell(1);
			XSSFCell excelCell3 = excelRow.getCell(2);
			excelCell1.setCellType(CellType.STRING);
			String product = excelCell1.getStringCellValue();
			excelCell2.setCellType(CellType.STRING);
			int rating = Integer.parseInt(excelCell2.getStringCellValue());
			excelCell3.setCellType(CellType.STRING);
			String feedbackTxt = excelCell3.getStringCellValue();
			lnk_feedbackNavigateBackBtn.click();
			Thread.sleep(20000);
			txt_searchBox.clear();
			txt_searchBox.sendKeys(product);
			System.out.println(product);
			txt_searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(20000);
			WebElement productTile = driver.findElement(By
					.xpath(".//div[@class='panel-collapse collapse in']/div/div/div/div/div/div/div/div[@class='name-height']/h3[normalize-space() = '"
							+ product + "']"));
			productTile.click();
			Thread.sleep(30000);
			lnk_feedbackRatingTab.click();
			Thread.sleep(5000);
			WebElement userRating = driver
					.findElement(By.xpath(".//span[span[@class='review-user ng-binding' and text()='" + UserID + "'] "
							+ "and span[@class='ng-binding' and text()='" + currentDate
							+ "']]/preceding::rate-yo[1]/div/div[@class='jq-ry-rated-group jq-ry-group']"));
			String userRatingString = StringUtils.substringBetween(userRating.getAttribute("style"), ": ", "%");
			int userRatingValue = Integer.parseInt(userRatingString);
			float tempValue = ((float) rating / 5) * 100;
			int inputrating = (int) tempValue;
			if (userRatingValue == inputrating) {
				System.out.println("Feedback Rating Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Rating for " + product
								+ " in tool details matches with feedback rating saved in Feedback Page",
						"Feedback Rating for " + product
								+ " in tool details matches with feeback rating saved in Feedback Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				System.out.println("Feedback Rating not Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Rating for " + product
								+ " in tool details matches with feeback rating saved in Feedback Page",
						"Error occured - Feedback Rating for " + product
								+ " in tool details not matches with feedback rating not saved in Feedback Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			String userfeedBackTxt = driver
					.findElement(By.xpath(".//span[span[@class='review-user ng-binding' and text()='" + UserID
							+ "'] and span[@class='ng-binding' and text()='" + currentDate
							+ "']]/following::div[1]/span"))
					.getText();
			if (userfeedBackTxt.equalsIgnoreCase(feedbackTxt)) {
				System.out.println("Feedback Comments Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback comments for " + product
								+ " in tool details matches with feedback saved in Feedback Page",
						"Feedback Comments for " + product
								+ " in tool details matches with feeback comments saved in Feedback Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				System.out.println("Feedback Comments not Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Comments for " + product
								+ " in tool details matches with feeback comments saved in Feedback Page",
						"Error occured - Feedback Comments for " + product
								+ " in tool details not matches with feedback comments not saved in Feedback Page",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			this.verifyAvgRating(product);
			this.verifyToolDetailFeedBackInput(product, rating, UserID, currentDate);
		}
		wb.close();
		fis.close();
	}

	public void verifyToolDetailFeedBackInput(String product, int rating, String UserID, String currentDate) throws InterruptedException {
		
		Actions action = new Actions(driver);
		int tooldetailrating = rating + 1;
		for (int i = 0; i <= tooldetailrating-1; i++) {
			
			if ((tooldetailrating >= 1) && (tooldetailrating <= 5)) {
				action.click(toolDetailsRatingInput.get(i)).build().perform();
			} else {
				System.out.println("Fail - Error occured - user gave the input greater than 5");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate while user gives the input greater than 5",
						"Error occured - user gave the input greater than 5", TC_MAS_Login.rowNumber,
						TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
				break;
			}
		}
			
			toolDetailsFeedBackInput.clear();
			toolDetailsFeedBackInput.sendKeys("Testing Feeback Input in Tool Details");
			toolDetailsSaveFeedBack.click();
			Thread.sleep(5000);
			toolDetailsConfirmSaveFeedback.click();
			Thread.sleep(5000);
			lnk_feedbackRatingTab.click();
			Thread.sleep(2000);
			WebElement toolDetailuserRating = driver
					.findElement(By.xpath("//div[text()[normalize-space() = 'Rating']]/following-sibling::div/rate-yo/div/div[@class='jq-ry-rated-group jq-ry-group']"));
			String toolDetailuserRatingString = StringUtils.substringBetween(toolDetailuserRating.getAttribute("style"), ": ", "%");
			String toolDetailuserFeedBackInput = toolDetailsFeedBackInput.getAttribute("value");
			int toolDetailuserRatingValue = Integer.parseInt(toolDetailuserRatingString);
			WebElement savedUserRating = driver
					.findElement(By.xpath(".//span[span[@class='review-user ng-binding' and text()='" + UserID + "'] "
							+ "and span[@class='ng-binding' and text()='" + currentDate
							+ "']]/preceding::rate-yo[1]/div/div[@class='jq-ry-rated-group jq-ry-group']"));
			String savedUserRatingString = StringUtils.substringBetween(savedUserRating.getAttribute("style"), ": ", "%");
			int savedUserRatingValue = Integer.parseInt(savedUserRatingString);
			String savedUserfeedBackTxt = driver
					.findElement(By.xpath(".//span[span[@class='review-user ng-binding' and text()='" + UserID
							+ "'] and span[@class='ng-binding' and text()='" + currentDate
							+ "']]/following::div[1]/span"))
					.getText();
			if(savedUserRatingValue==toolDetailuserRatingValue){
				System.out.println("Feedback Rating Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Rating for " + product
								+ " entered in tool details matches with feedback rating saved in Feedback Page, below",
						"Feedback Rating for " + product
								+ "entered in tool details matches with feedback rating saved in Feedback Page, below",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}else{
				System.out.println("Feedback Rating not Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Rating for " + product
								+ " entered in tool details matches with feedback rating saved in Feedback Page, below",
						"Error occured - Feedback Rating for " + product
								+ " entered in tool details not matches with feedback rating saved in Feedback Page, below",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
			if (savedUserfeedBackTxt.equalsIgnoreCase(toolDetailuserFeedBackInput)) {
				System.out.println("Feedback Comments Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback comments for " + product
								+ " entered in tool details matches with feedback comments saved in Feedback Page, below",
						"Feedback Comments for " + product
								+ " entered in tool details matches with feedback comments saved in Feedback Page, below",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			} else {
				System.out.println("Feedback Comments not Matches");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.resultDataConfig.WritingToExcelResults(
						"Validate the Feedback Comments for " + product
								+ " entered in tool details matches with feedback comments saved in Feedback Page, below",
						"Error occured - Feedback Comments for " + product
								+ " entered in tool details not matches with feedback comments saved in Feedback Page, below",
						TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
			}
	}

	public void verifyAvgRating(String product) throws IOException {

		double sum = 0;

		for (WebElement svgitem : svg_starRating) {
			int tempRatingValue = Integer
					.parseInt(StringUtils.substringBetween(svgitem.getAttribute("style"), ": ", "%"));
			double ratingValue = (double) tempRatingValue * 0.05;
			sum += ratingValue;
		}
		System.out.println(sum);
		double avgRating = (Math.round(sum / (double) svg_starRating.size() * 10.0)) / 10.0;
		// double tempValue = sum/(double)svg_starRating.size();
		// double avgRating = Math.round(tempValue*10.0)/10.0;
		double productRatingValue = Double
				.parseDouble(StringUtils.substringBetween(productRating.getAttribute("style"), ": ", "%")) * 0.05;
		System.out.println("Avg Rating from the feedback rating - " + avgRating);
		System.out.println("Product Rating for the - " + product + " - " + productRatingValue);
		if ((productRatingValue == avgRating) && (avgRatingValue.getText().equals(String.valueOf(avgRating)))) {
			System.out.println("The Product Rating for " + product
					+ " in tool details matches with average feedbacks ratings saved");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Product Rating for " + product
							+ " in tool details matches with average feedbacks ratings saved",
					"The Product Rating for " + product
							+ " in tool details matches with average feedbacks ratings saved",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

		} else if (productRatingValue != avgRating) {
			System.out.println("The Product Rating for " + product
					+ " in tool details not matches with average feedbacks ratings saved");
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.resultDataConfig.WritingToExcelResults(
					"Validate the Product Rating for " + product
							+ " in tool details matches with average feedbacks ratings saved",
					"Error occured - The Product Rating for " + product
							+ " in tool details not matches with average feedbacks ratings saved",
					TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
		}
	}
}
