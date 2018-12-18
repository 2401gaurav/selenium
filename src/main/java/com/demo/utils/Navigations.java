package com.demo.utils;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demo.init.initialization;



public class Navigations extends initialization{
	
	
	public static String parenthandler; 
	public static String childhandler;

	public Navigations(WebDriver driver) {
		this.driver = driver;
	}

	public boolean clickToNavigate(WebElement navigationTile) throws Exception {
		boolean navigateTile = false;
		if (navigationTile.isDisplayed()) {
			navigationTile.click();
			navigateTile = true;
		} else {

		}
		return navigateTile;
	}

	public void switchToParentWindow(String parentHandle) {
		driver.switchTo().window(parentHandle);
	}

	public String ValidatemyHPDPInternalNavigations(String landingURL, String pageName, String tileName)
			throws Exception {
		String output = "";
		try {

			// code to do perform actions on new window

			// Example to verify the cuurentURL on ChildWindow
			String currentURL = driver.getCurrentUrl();

			if (currentURL.contains(landingURL)) {
				output = output + "Redirection successful. User is getting navigated to: " + pageName + " on click of :"
						+ tileName;

				System.out.println("Redirection successful. User is getting navigated to: " + pageName
						+ " on click of :" + tileName);

			} else {
				output = output + "Error Occured : Redirection Failed. User is getting navigated to: " + currentURL
						+ " on click of :" + tileName;
			}

			Thread.sleep(15000);

		} catch (Exception ex) {

			output = output + "Error Occured : " + ex.getMessage();
		}
		return output;

	}

	public String ValidatemyHPDPInternalNavigations(String landingURL, String pageName, String tileName,
			WebElement backButton) throws Exception {
		String output = "";
		try {

			// code to do perform actions on new window

			// Example to verify the cuurentURL on ChildWindow
			String currentURL = driver.getCurrentUrl();

			if (currentURL.contains(landingURL)) {
				output = output + "Redirection successful. User is getting navigated to: " + pageName + " on click of :"
						+ tileName;

				System.out.println("Redirection successful. User is getting navigated to: " + pageName
						+ " on click of :" + tileName);

			} else {
				output = output + "Error Occured : Redirection Failed. User is getting navigated to: " + currentURL
						+ " on click of :" + tileName;
			}

			Thread.sleep(20000);
			backButton.click();
			Thread.sleep(10000);
		} catch (Exception ex) {

			output = output + "Error Occured : " + ex.getMessage();
		}
		return output;

	}

	public String verifyNavigationToSpecificURL(String landingURL, String pageName, String tileName) throws Exception {
		String outPut = "";
		parenthandler=driver.getWindowHandle();
		try {
			int numberofWindows = 0;
			for (String winHandle : driver.getWindowHandles()) {
				numberofWindows = numberofWindows + 1;
				driver.switchTo().window(winHandle); 
				// switch focus of
				// WebDriver to the next
				// found window handle
				// (that's your newly
				// opened window)
			}

			// Example to verify the cuurentURL on ChildWindow
			String currentURL = driver.getCurrentUrl();
			//driver.manage().window().maximize();
			childhandler=driver.getWindowHandle();
			if (currentURL.contains(landingURL)) {
				outPut = outPut + " Redirection successful. User is getting navigated to: " + pageName
						+ " on click of :" + tileName;
				outPut = outPut + "\n" + "Expected: " + landingURL + " Actual :" + currentURL;
				
				
			
			} else {
				outPut = outPut + "Error Occured : User is getting navigated to: " + currentURL + " on click of :"
						+ tileName;
				outPut = outPut + "\n" + "Expected: " + landingURL + " Actual :" + currentURL;
				System.out.println("Error Occurred - Not Navigated to "+tileName);
				
				

				
			}
			System.out.println(currentURL);
			angularJSWait.waitJQueryAngular();
			/*
			 * if (numberofWindows > 1) { driver.close(); // close newly opened
			 * window when done with it }
			 **/

		} catch (NoSuchElementException ex) {
			outPut = outPut + "Error Occured during navigation to cdart from metrics grid " + ex.getMessage();
			System.out.println(tileName+" not Found");
			System.out.println("Exception Occurred - "+tileName+" not Found");
			
		

			
		}
		return outPut;
	}
}
