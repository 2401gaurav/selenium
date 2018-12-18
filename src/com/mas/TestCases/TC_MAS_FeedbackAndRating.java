package com.mas.TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.helper.BrowserType;
import com.mas.pages.FeedbackPage;
/**
 * @author aswin.r.j
 *
 */
public class TC_MAS_FeedbackAndRating {

	WebDriver driver;
	FeedbackPage objFeedbackPage;
	
	@AfterClass
	public void afterClass() throws Exception {
		TC_MAS_Login.workBook.write(TC_MAS_Login.fileOut);
	}

	@BeforeClass
	public void initialize() throws Exception {
		this.driver = BrowserType.launchBrowser();
		this.objFeedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
	}


	@Parameters({ "UserID" })
	@Test(priority = 4)
	public void test_feedBackPage_DFFTools(String UserID) throws InterruptedException, IOException {

		// objFeedbackPage.validateNoDataRatedFilter();
		objFeedbackPage.enterRatingAndFeedback("FeedbackRating");
		objFeedbackPage.verifyRatingAndFeedback("FeedbackRating");
		objFeedbackPage.verifyFeedbackRatingFilter();
		objFeedbackPage.verifyToolFeedbackAndRating("FeedbackRating", UserID);
		objFeedbackPage.enterRatingAndFeedbackAllTools();
		objFeedbackPage.validateNoDataUnRatedFilter();

	}
	
}
