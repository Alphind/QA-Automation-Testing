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
		
		cms1500page.createClaim("37", "07/13/2023");
		
	}
	
}
