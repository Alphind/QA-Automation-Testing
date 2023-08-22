package org.alphind.alphamcs.test;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.util.DBUtil;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class ClaimTest extends TestBase {

	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOClaimMaintenancePage claimMaintenancepage;
	MCOCMS1500Page cms1500page;
	
	String userName = "shc/shctest";
	String passWord = "Letmein@12345";
	
	
	@Test
	public void viewClaim() {
		
		report(LogStatus.INFO, "Verify whether able to view claim status.");
		
		loginPage = new MCOLoginPage(driver);
		
		loginPage.selectMCOLogin();
		
		homePage = loginPage.login(userName, passWord);
		
		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		claimMaintenancepage = homePage.navigateToClaimMaintenance();
		
		claimMaintenancepage.isClaimMaintenancePageDisplayed();
		
		claimMaintenancepage.clickFilter();
		
		claimMaintenancepage.searchWithHeaderAndViewClaim("6557017");
		
		//claimMaintenancepage.searchWithMCSNumberAndViewClaim("250600");
		
	}
	
	@Test
	public void createCMS1500Claim() {
		
		report(LogStatus.INFO, "Verify whether able to create a new CMS 1500 claim.");
		
		loginPage = new MCOLoginPage(driver);
		
		loginPage.selectMCOLogin();
		
		homePage = loginPage.login(userName, passWord);
		
		if (homePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		cms1500page = homePage.navigateToCMS1500Page();
		
		if(cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		}else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}	
		
		String claimnumber=cms1500page.createClaim();
		
		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");
		
		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : "+mcsnumber);
		
		String conStr = envConfig.getProperty("devDBConnectionString");
		
		dbUtil = new DBUtil();
		
		String wrapper = "asp_portal_claims_processing_wrapper";

		dbUtil.executeSP(conStr, wrapper);
		
		claimMaintenancepage = homePage.navigateToClaimMaintenance();
		
		claimMaintenancepage.isClaimMaintenancePageDisplayed();
		
		claimMaintenancepage.clickFilter();
		
		claimMaintenancepage.searchWithMCSNumberAndViewClaim(mcsnumber);
		
	}
	
}
