package com.mas.pages;

import java.util.List;

//import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageWorkTabPage {

	WebDriver driver;

	//NSSubmissionPage objNSSubmissionPage;

	@FindBy(how = How.XPATH, using = "//*[@id='mainTile']/div[2]/mywizard-tile/ng-include")
	WebElement label_header;

	@FindBy(how = How.XPATH, using = ".//*[@id='deliveryType']")
	WebElement deliveryType;

	@FindBy(how = How.XPATH, using = "//*[@id='mainTile']/div[2]/mywizard-tile[2]/ng-include/div/div/h2/a") // .//*[@id='mainTile']/div[2]/div[2]/div/h2/a")
	WebElement AutomationStore;

	@FindBy(how = How.XPATH, using = ".//*[@id='reportingFreequency']")
	WebElement reportingFreequency;

	@FindBy(how = How.XPATH, using = ".//*[@id='navbar-menu']/ul/li[2]/a")
	WebElement manageWorkTab;

	public ManageWorkTabPage(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToManageWorkTab() {
		try {
			manageWorkTab.click();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void navigateToAutomationStore() {
		try {
			AutomationStore.click();
			Thread.sleep(20000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void tileNavigation(String tileHeader) {
		try {
			List<WebElement> elements = label_header.findElements(By.xpath("//div/div/h2"));
			for (WebElement webElement : elements) {
				if (webElement.getText().equalsIgnoreCase(tileHeader)) {
					System.out.println("Tile header " + tileHeader);
					webElement.click();
					break;
				}
			}
			Thread.sleep(20000);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
