package com.mas.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
		WebDriver driver;
		
		@FindBy (how=How.XPATH,using="//a[@ui-sref='MyWizard.Manage']")
		WebElement mywizard;
		
		@FindBy(how=How.TAG_NAME,using="h1")
		WebElement header;
		
		@FindBy(how=How.XPATH,using="//span[@id='scopePreference']")
		WebElement navigator;
		
		@FindBy(how=How.ID,using="selectedClientID")
		WebElement Client;
		
		@FindBy(how=How.ID,using="selectedEngagementID")
		WebElement Engagement;
		
		@FindBy(how=How.ID,using="selectedProjectID")
		WebElement Project;
		
		@FindBy(how=How.XPATH,using="//*[@id='ApplyProject']")
		WebElement applybutton;
		
	
		
		public HomePage(WebDriver driver)
		{
			this.driver=driver;
			
		}
				
		public boolean validateHomePage()
		{
			
			if (header.getText().contains("Use the services on this page to initiate, manage, monitor and track status of an engagement.Included in this view are Command Centers tailored to the type of work being delivered. These are used to drill down into the detailed aspects of the delivery"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		
		public void selectProject(String strClient, String strEng, String strProj)
		{
			try
			{
				navigator.click();

				Thread.sleep(4000);
				
				Select objClient = new Select(Client);
				objClient.selectByVisibleText(strClient);
				
				Thread.sleep(4000);
				
				Select objEng = new Select(Engagement);
				objEng.selectByVisibleText(strEng);
				
				Thread.sleep(4000);
				
				Select objPro = new Select(Project);
				objPro.selectByVisibleText(strProj);	
				
				Thread.sleep(4000);
				
				applybutton.click();
				
				Thread.sleep(4000);
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}

		public void endtour() {
			// TODO Auto-generated method stub
			String handle= driver.getWindowHandle();
			 
	        System.out.println(handle);
 
	        // Click on the Button "New Message Window"
 
	        driver.findElement(By.xpath("/html/body/div[5]/div[3]/button")).click();
 
	        // Store and Print the name of all the windows open	              
 
	        Set<String> handles = driver.getWindowHandles();
 
	        System.out.println(handles);
 
	        // Pass a window handle to the other window
 
	        for (String handle1 : driver.getWindowHandles()) {
 
	        	System.out.println(handle1);
 
	        	driver.switchTo().window(handle1);
	        	
	        	mywizard.click();
		}
		
}}
