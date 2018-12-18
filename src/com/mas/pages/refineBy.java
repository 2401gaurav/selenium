package com.mas.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import com.mas.TestCases.TC_MAS_Login;

import reusablefunction.reusefunction;

public class refineBy {
	String pagenextbutton;
	public int attempts = 0;
	String textLink;
	String  text;

	WebDriver driver;
	reusefunction objreusable;
	@FindBy(how = How.XPATH, using = "//div[@class='row margin-15-t']/div/ul/li/a[text()='>']")
	@CacheLookup
	WebElement pagenextbuttonclass;
	@FindBy(how = How.XPATH, using = ".//*[@id='page-content-wrapper']/div/div[1]/ul[1]/li[3]/a")
	@CacheLookup
	WebElement btnAll;
	List<WebElement> category;

	public refineBy(WebDriver driver) {
		this.driver = driver;
	}

	public void validateRefineByLinks_BusinessFunction() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);

		btnAll.click();
		Thread.sleep(20000);
		int refineByLinkssize = driver.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"))
				.size();

		for (int m = 0; m < refineByLinkssize; m++) {
			List<WebElement> refineByLinks = driver
					.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

			String getLink = StringUtils.trim(refineByLinks.get(m).getText());

			switch (getLink) {

			case "Business Function": {
				objreusable.javascripttoggle1(getLink);
				int linkValue = driver
						.findElements(By
								.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
										+ getLink
										+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
						.size();

				for (int k = 0; k < linkValue; k++)

				{
					List<WebElement> linksValue = driver.findElements(By.xpath(
							"//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('" + getLink
									+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

					String getLink2 = linksValue.get(k).getText();
					String text = getLink2;
					text = text.substring(1, text.indexOf("(") - 1);
					text = StringUtils.trim(text);
					System.out.println(text);
					objreusable.refresh(getLink, text, k);
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

						driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
						Thread.sleep(15000);

						objreusable.list();
						Thread.sleep(10000);
						category = driver
								.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));

						int counter = 1;
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
								if (objreusable.validatebyexcel(getLink, text, categorytext, tabletext) == true) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + categorytext + "is present under -" + text,
											"Required Product " + categorytext
													+ " is  Present and  matching whit the Input Data under -" + text
													+ " and under -" + getLink + " Category",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									continue loop;

								} else {
									// Thread.sleep(20000);
									if (i <= category.size()) {

										Thread.sleep(2000);
										TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
										TC_MAS_Login.resultDataConfig.WritingToExcelResults(
												"Verify that the Product - " + categorytext + "is present under -"
														+ text,
												"Error Occured-Required Product " + categorytext
														+ " is  not Present under -" + text,
												TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										System.out.println("tool not found-->" + categorytext);

										continue loop;

									}
								}
								Thread.sleep(20000);
							} else if ((!pagenextbutton.contains("disabled"))) {
								counter = counter + 1;
								objreusable.refreshpage(getLink, text, tabletext, btnAll);
								Thread.sleep(20000);
								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
										.click();
								Thread.sleep(20000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
										"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
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
			}

			}
		}
	}

	public void validateRefineByLinks_IndustrySegment() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);

		btnAll.click();
		Thread.sleep(20000);
		int refineByLinkssize = driver.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"))
				.size();

		for (int m = 0; m < refineByLinkssize; m++) {
			List<WebElement> refineByLinks = driver
					.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

			String getLink = StringUtils.trim(refineByLinks.get(m).getText());

			switch (getLink) {

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
					List<WebElement> linksValue = driver.findElements(By.xpath(
							"//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('" + getLink
									+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

					String getLink2 = linksValue.get(k).getText();
					String text = getLink2;
					text = text.substring(1, text.indexOf("(") - 1);
					text = StringUtils.trim(text);
					System.out.println(text);
					objreusable.refresh(getLink, text, k);
					Thread.sleep(10000);
					int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
					for (int j = 0; j < tablesize; j++) {

						List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
						String textLink = table.get(j).getText();
						String tabletext = textLink;
						tabletext = tabletext.substring(0, tabletext.indexOf("("));
						tabletext = StringUtils.trim(tabletext);
						System.out.println(tabletext);
						driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
						Thread.sleep(1000);
						objreusable.list();
						Thread.sleep(10000);
						category = driver
								.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
						int counter = 1;
						loop: for (int i = 0; i <= category.size() + 1; i++) {
							if (i <= category.size() - 1) {
								pagenextbutton = driver
										.findElement(By
												.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
										.getAttribute("class");
								String categorytext = category.get(i).getText();
								System.out.println(categorytext);
								if (objreusable.validatebyexcel(getLink, text, categorytext, tabletext) == true) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + categorytext + "is present under -" + text,
											"Required Product " + categorytext
													+ " is  Present and  matching whit the Input Data under -" + text
													+ " and under -" + getLink + " Category",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								} else {
									// Thread.sleep(20000);
									if (i <= category.size()) {

										Thread.sleep(2000);
										TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
										TC_MAS_Login.resultDataConfig.WritingToExcelResults(
												"Verify that the Product - " + categorytext + "is present under -"
														+ text,
												"Error Occured-Required Product " + categorytext
														+ " is  not Present under -" + text,
												TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										System.out.println("tool not found" + categorytext);

										continue loop;

									}
								}
							} else if ((!pagenextbutton.contains("disabled"))) {
								counter = counter + 1;
								objreusable.refreshpage(getLink, text, tabletext, btnAll);
								Thread.sleep(20000);
								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
										.click();
								Thread.sleep(20000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
										"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
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

			}
		}
	}

	public void validateRefineByLinks_DeliveryFunction() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);

		btnAll.click();
		Thread.sleep(20000);
		int refineByLinkssize = driver.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"))
				.size();

		for (int m = 0; m < refineByLinkssize; m++) {
			List<WebElement> refineByLinks = driver
					.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

			String getLink = StringUtils.trim(refineByLinks.get(m).getText());

			switch (getLink) {

			case "Delivery Function": {
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
					List<WebElement> linksValue = driver.findElements(By.xpath(
							"//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('" + getLink
									+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

					String getLink2 = linksValue.get(k).getText();
					String text = getLink2;
					text = text.substring(1, text.indexOf("(") - 1);
					text = StringUtils.trim(text);
					System.out.println(text);
					objreusable.refreshForCertainCategory(getLink, text, k);
					Thread.sleep(10000);
					int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
					for (int j = 0; j < tablesize; j++) {

						List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
						String textLink = table.get(j).getText();
						String tabletext = textLink;
						tabletext = tabletext.substring(0, tabletext.indexOf("("));
						tabletext = StringUtils.trim(tabletext);
						System.out.println(tabletext);
						driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
						Thread.sleep(1000);
						objreusable.list();
						category = driver
								.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));

						Thread.sleep(10000);
						int counter = 1;
						loop: for (int i = 0; i <= category.size() + 1; i++) {
							if (i <= category.size() - 1) {
								pagenextbutton = driver
										.findElement(By
												.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
										.getAttribute("class");
								String categorytext = category.get(i).getText();
								System.out.println(categorytext);
								if (objreusable.validatebyexcel(getLink, text, categorytext, tabletext) == true) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + categorytext + "is present under -" + text,
											"Required Product " + categorytext
													+ " is  Present and  matching whit the Input Data under -" + text
													+ " and under -" + getLink + " Category",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								} else {
									Thread.sleep(200);
									if (i <= category.size()) {

										Thread.sleep(2000);
										TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
										TC_MAS_Login.resultDataConfig.WritingToExcelResults(
												"Verify that the Product - " + categorytext + "is present under -"
														+ text,
												"Error Occured-Required Product " + categorytext
														+ " is  not Present under -" + text,
												TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										System.out.println("tool not found" + categorytext);

										continue loop;

									}
								}
							} else if ((!pagenextbutton.contains("disabled"))) {
								counter = counter + 1;
								objreusable.refreshpage(getLink, text, tabletext, btnAll);

								Thread.sleep(20000);
								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
										.click();
								Thread.sleep(20000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
										"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
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

			}
		}
	}

	public void validateRefineByLinks_DeliveryType() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);

		btnAll.click();
		Thread.sleep(20000);
		int refineByLinkssize = driver.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"))
				.size();

		for (int m = 0; m < refineByLinkssize; m++) {
			List<WebElement> refineByLinks = driver
					.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

			String getLink = StringUtils.trim(refineByLinks.get(m).getText());

			switch (getLink) {

			case "Delivery Type": {

				objreusable.javascripttoggle1(getLink);
				int linkValue = driver
						.findElements(By
								.xpath("//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('"
										+ getLink
										+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"))
						.size();

				for (int k = 0; k < linkValue; k++) {
					List<WebElement> linksValue = driver.findElements(By.xpath(
							"//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('" + getLink
									+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

					String getLink2 = linksValue.get(k).getText();
					String text = getLink2;
					text = text.substring(1, text.indexOf("(") - 1);
					text = StringUtils.trim(text);
					System.out.println(text);
					objreusable.refresh(getLink, text, k);

					Thread.sleep(20000);
					int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
					for (int j = 0; j < tablesize; j++) {

						List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
						String textLink = table.get(j).getText();
						String tabletext = textLink;
						tabletext = tabletext.substring(0, tabletext.indexOf("("));
						tabletext = StringUtils.trim(tabletext);
						System.out.println(tabletext);
						driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
						Thread.sleep(1000);

						objreusable.list();
						category = driver
								.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));

						Thread.sleep(10000);
						int counter = 1;
						loop: for (int i = 0; i <= category.size() + 1; i++) {
							if (i <= category.size() - 1) {
								pagenextbutton = driver
										.findElement(By
												.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
										.getAttribute("class");
								String categorytext = category.get(i).getText();
								System.out.println(categorytext);
								if (objreusable.validatebyexcel(getLink, text, categorytext, tabletext) == true) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + categorytext + "is present under -" + text,
											"Required Product " + categorytext
													+ " is  Present and  matching whit the Input Data under -" + text
													+ " and under -" + getLink + " Category",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								} else {
									// Thread.sleep(20000);
									if (i <= category.size()) {

										Thread.sleep(2000);
										TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
										TC_MAS_Login.resultDataConfig.WritingToExcelResults(
												"Verify that the Product - " + categorytext + "is present under -"
														+ text,
												"Error Occured-Required Product " + categorytext
														+ " is  not Present under -" + text,
												TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										System.out.println("tool not found" + categorytext);

										continue loop;

									}
								}
							} else if ((!pagenextbutton.contains("disabled"))) {

								counter = counter + 1;
								objreusable.refreshpage(getLink, text, tabletext, btnAll);
								Thread.sleep(20000);
								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
										.click();
								Thread.sleep(20000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
										"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
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
			}

			}
		}
	}

	public void validateRefineByLinks_IndustryFilters() throws Exception

	{
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);

		btnAll.click();
		Thread.sleep(20000);
		int refineByLinkssize = driver.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"))
				.size();

		for (int m = 0; m < refineByLinkssize; m++) {
			List<WebElement> refineByLinks = driver
					.findElements(By.xpath(".//li/div/div[@class='col-md-8 filter-name ng-binding']"));

			String getLink = StringUtils.trim(refineByLinks.get(m).getText());

			switch (getLink) {

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
					List<WebElement> linksValue = driver.findElements(By.xpath(
							"//div[@class='col-md-8 filter-name ng-binding' and contains(.,normalize-space('" + getLink
									+ "'))]//following::ul[@class='tree remove-bullet ng-scope']/li[@class='padding12-l']/div/div"));

					String getLink2 = linksValue.get(k).getText();
					String text = getLink2;
					text = text.substring(1, text.indexOf("(") - 1);
					text = StringUtils.trim(text);
					System.out.println(text);

					objreusable.refreshForCertainCategory(getLink, text, k);
					Thread.sleep(10000);
					int tablesize = driver.findElements(By.xpath("//div[1]/h4/span/span")).size();
					for (int j = 0; j < tablesize; j++) {

						List<WebElement> table = driver.findElements(By.xpath("//div[1]/h4/span/span"));
						String textLink = table.get(j).getText();
						String tabletext = textLink;
						tabletext = tabletext.substring(0, tabletext.indexOf("("));
						tabletext = StringUtils.trim(tabletext);
						System.out.println(tabletext);
						driver.findElement(By.xpath("//div[1]/h4/span/span[contains(.,'" + tabletext + "')]")).click();
						Thread.sleep(1000);
						objreusable.list();
						Thread.sleep(10000);
						category = driver
								.findElements(By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
						int counter = 1;
						loop: for (int i = 0; i <= category.size() + 1; i++) {
							if (i <= category.size() - 1) {
								pagenextbutton = driver
										.findElement(By
												.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
										.getAttribute("class");
								String categorytext = category.get(i).getText();
								System.out.println(categorytext);
								if (objreusable.validatebyexcel(getLink, text, categorytext, tabletext) == true) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + categorytext + "is present under -" + text,
											"Required Product " + categorytext
													+ " is  Present and  matching whit the Input Data under -" + text
													+ " and under -" + getLink + " Category",
											TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									continue loop;
								} else {

									if (i <= category.size()) {

										Thread.sleep(2000);
										TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
										TC_MAS_Login.resultDataConfig.WritingToExcelResults(
												"Verify that the Product - " + categorytext + "is present under -"
														+ text,
												"Error Occured-Required Product " + categorytext
														+ " is  not Present under -" + text,
												TC_MAS_Login.rowNumber, TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
										System.out.println("tool not found" + categorytext);

										continue loop;

									}
								}
							} else if ((!pagenextbutton.contains("disabled"))) {
								counter = counter + 1;
								objreusable.refreshpage(getLink, text, tabletext, btnAll);
								Thread.sleep(20000);

								driver.findElement(By
										.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='" + counter + "']"))
										.click();
								Thread.sleep(20000);
								TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
								TC_MAS_Login.resultDataConfig.WritingToExcelResults("Verify that the Next Page is present and clicable -",
										"Navigated to the Page " + counter + " Continue the search", TC_MAS_Login.rowNumber,
										TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);

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
			}
		}
	}
	public void validateRefineBy() throws Exception {
		this.objreusable = PageFactory.initElements(driver, reusefunction.class);
	
			

				File excelfilename = new File(
						"C:\\workspace\\AutomationStore\\src\\com\\InputFiles\\refine.xlsx");

				FileInputStream fis = new FileInputStream(excelfilename);
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				XSSFSheet st = wb.getSheet("Sheet1");
				int row = st.getLastRowNum();
				loop2:for (int i = 1; i <= row; i++) {

					st.getRow(i).getCell(1).setCellType(CellType.STRING);
					st.getRow(i).getCell(4).setCellType(CellType.STRING);
					st.getRow(i).getCell(5).setCellType(CellType.STRING);
					st.getRow(i).getCell(3).setCellType(CellType.STRING);

					XSSFCell category = st.getRow(i).getCell(3);
					String categorytext = category.getStringCellValue();
					XSSFCell subcategory = st.getRow(i).getCell(4);
					String subcategorytext = subcategory.getStringCellValue();
					XSSFCell link = st.getRow(i).getCell(5);
					String linkData = link.getStringCellValue();
					XSSFCell tool = st.getRow(i).getCell(1);
					String tooltext = tool.getStringCellValue();
					
					System.out.println(categorytext);
					System.out.println(subcategorytext);
					System.out.println(tooltext);
					System.out.println(linkData);
					driver.findElement(By.xpath(".//*[@id='page-content-wrapper']/div/div[1]/ul[1]/li[3]/a")).click();
					Thread.sleep(15000);
					switch (categorytext) {
					case "Industry Segment": {
						objreusable.javascripttoggle1(categorytext);

						if((objreusable.industry_Assets(subcategorytext, linkData, categorytext))==true)
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
									System.out.println(tooltext + " is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + " is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under - " + linkData + " Link",TC_MAS_Login.rowNumber,
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
										"Verify that the Product - " + tooltext + "is present under -" + categorytext,
										"Error Occurred-Required Product " + tooltext
												+ " is not   matching with the Input Data under -" + categorytext
												+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
												TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
							}
						driver.navigate().refresh();
						Thread.sleep(20000);
						break;
					}
					case "Business Function": {
						
						objreusable.javascripttoggle1(categorytext);

						if(objreusable.business_Function(subcategorytext, linkData, categorytext)==true){
						
						
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
									System.out.println(tooltext + " is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + "is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
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
						}else{
							break;
						}
						if((pagenextbutton.contains("disabled"))&&!(text.contains(tooltext))){
							System.out.println(tooltext+" not found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the Product - " + tooltext + " is present under -" + categorytext,
									"Error Occurred-Required Product " + tooltext
											+ " is not   matching with the Input Data under -" + categorytext
											+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					driver.navigate().refresh();
					Thread.sleep(20000);
						
						break;
					}
					case "Delivery Function": {
						objreusable.javascripttoggle1(categorytext);
						objreusable.javascripttoggle2(categorytext);
						
						
						if(objreusable.delivery_Function(subcategorytext, linkData, categorytext)==true){
							
						
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
									System.out.println(tooltext + " is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + " is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
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
									"Verify that the Product - " + tooltext + " is present under -" + categorytext,
									"Error Occurred-Required Product " + tooltext
											+ " is not   matching with the Input Data under -" + categorytext
											+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					driver.navigate().refresh();
					Thread.sleep(20000);
						
						break;}
					case "Industry Filters": {
						objreusable.javascripttoggle1(categorytext);
						objreusable.javascripttoggle2(categorytext);
					
					
						if(objreusable.delivery_Function(subcategorytext, linkData, categorytext)==true){
						
						
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
									System.out.println(tooltext + " is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + " is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
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
						}
						while (!(pagenextbutton.contains("disabled")));
						}
						else{
							break;
							
						}
						if((pagenextbutton.contains("disabled"))&&!(text.contains(tooltext))){
							System.out.println(tooltext+" not found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the Product - " + tooltext + " is present under -" + categorytext,
									"Error Occurred-Required Product " + tooltext
											+ " is not   matching with the Input Data under -" + categorytext
											+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					driver.navigate().refresh();
					Thread.sleep(20000);
					break;
					}
					
					
					case "Delivery Type": {
						
				
						objreusable.javascripttoggle1(categorytext);

						if(objreusable.delivery_Type(subcategorytext, linkData, categorytext)==true){
						
						
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
									System.out.println(tooltext + " is present");
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + " is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
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
						}else{
							break;
							
						}
						if((pagenextbutton.contains("disabled"))&&!(text.contains(tooltext))){
							System.out.println(tooltext+" not found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the Product - " + tooltext + " is present under -" + categorytext,
									"Error Occurred-Required Product " + tooltext
											+ " is not   matching with the Input Data under -" + categorytext
											+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					driver.navigate().refresh();
					Thread.sleep(20000);
					break;
					}
					case "Ratings": {
						objreusable.ratings(subcategorytext, linkData, categorytext);
						loop1: do {
							pagenextbutton = driver
									.findElement(By
											.xpath("//div[@class='row margin-15-t']/div/ul/li/a[text()='>']/parent::li"))
									.getAttribute("class");
							List<WebElement> listview = driver.findElements(
									By.xpath(".//*[@id='accordion']/div/div[2]/div[3]/div/div[1]/span[1]"));
							loop: for (WebElement list : listview) {
									text = list.getText();
								System.out.println(text);
								if (text.contains(tooltext)) {
									TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
									TC_MAS_Login.resultDataConfig.WritingToExcelResults(
											"Verify that the Product - " + tooltext + " is present under -" + categorytext,
											"Required Product " + tooltext
													+ " is  Present and  matching with the Input Data under -" + categorytext
													+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
													TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
									System.out.println(tooltext + " is present");
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
						if((pagenextbutton.contains("disabled"))&&!(text.contains(tooltext))){
							System.out.println(tooltext+" not found");
							TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
							TC_MAS_Login.resultDataConfig.WritingToExcelResults(
									"Verify that the Product - " + tooltext + "is present under -" + categorytext,
									"Error Occurred-Required Product " + tooltext
											+ " is not   matching with the Input Data under -" + categorytext
											+ " and under -" + linkData + " Link",TC_MAS_Login.rowNumber,
											TC_MAS_Login.workBook, TC_MAS_Login.outputWorkSheet, false);
						}
					driver.navigate().refresh();
					Thread.sleep(20000);
						break;
					}
					}
				}
			

	wb.close();
	fis.close();
	Thread.sleep(10000);}
	

}
