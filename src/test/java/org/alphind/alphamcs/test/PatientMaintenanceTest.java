package org.alphind.alphamcs.test;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.HomePage;
import org.alphind.alphamcs.pages.MCOPatientMaintenancePage;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.FileUtil;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class PatientMaintenanceTest extends TestBase {

	DBUtil dbUtil;
	HomePage homePage;
	MCOLoginPage mcoLoginPage;
	MCOHomePage mcoHomePage;
	MCOPatientMaintenancePage mcopatientMaintenancePage;
	FileUtil fileUtil;

	String userName;
	String passWord;
	
	String networkUserName;
	String networkPassWord;
	
	
	@Test
	public void viewPatient() {
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
//		report(LogStatus.INFO, "");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcopatientMaintenancePage = mcoHomePage.navigateToPatientMaintenance();
		
		mcopatientMaintenancePage.isPatientMaintenancePageDisplayed();
		
		dataMapList.forEach(list -> {
			
			dataMap = list;
			
			mcopatientMaintenancePage.viewPatient();
			
			mcopatientMaintenancePage.isViewPageDisplayed();
			
		});
		
	}

}
