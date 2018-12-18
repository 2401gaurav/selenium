package com.mas.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.mas.TestCases.TC_MAS_Login;

public class LoginPage {

WebDriver driver;

	@FindBy(how=How.XPATH,using="//input[@id='userNameInput']")
	@CacheLookup
	WebElement username;
	
	@FindBy(how=How.XPATH,using="//input[@id='passwordInput']")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.XPATH,using="//span[@id='submitButton']")
	@CacheLookup
	WebElement SignIn;
	
	@FindBy(how=How.XPATH,using=".//*[@id='errorText']")
	@CacheLookup
	WebElement errorMsg;

	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void login(String uname,String pwd) throws Exception
	{
			username.sendKeys(uname);
			password.sendKeys(pwd);
			SignIn.click();
			TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
			TC_MAS_Login.inputDataConfig.WritingToExcelResults("Validate the login is valid", "Login Successful", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
					TC_MAS_Login.outputWorkSheet, false);
			Thread.sleep(5000);
			if(errorMsg.isDisplayed())
			{
				Assert.fail("Invalid UserName or Password");
				TC_MAS_Login.rowNumber = TC_MAS_Login.rowNumber + 1;
				TC_MAS_Login.inputDataConfig.WritingToExcelResults("Validate the login is valid", "Error - Login Not Successful", TC_MAS_Login.rowNumber, TC_MAS_Login.workBook,
						TC_MAS_Login.outputWorkSheet, false);
			}

		
	}
				
	public String getSelectOptionValue(List<WebElement> options, String value){
		for (WebElement option : options) {
			System.out.println(option.getText());
			if (option.getText().replace("\n", "").replace(" ", "")
					.equals(value.replace(" ", ""))) {
				return option.getAttribute("value");
			}
		}
		Assert.fail("Entered VALUE [" + value + "] is not found");
		return "";
	} 
	}
