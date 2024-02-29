package org.alphind.alphamcs.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.HomePage;
import org.alphind.alphamcs.pages.MCOUB04Page;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.FileUtil;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class UB04ClaimTest extends TestBase {

	DBUtil dbUtil;
	HomePage homePage;
	MCOLoginPage mcoLoginPage;
	MCOHomePage mcoHomePage;
	MCOClaimMaintenancePage mcoClaimMaintenancePage;
	MCOUB04Page mcoUB04Page;
	FileUtil fileUtil;

	String userName;
	String passWord;
	
	String networkUserName;
	String networkPassWord;
	
	@Test
	public void createAndSubmitUB04Claim() {
		  
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create and submit a "
				+ "new UB-04 claim.");
		
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		int previousClaimID = 0;
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		String className = this.getClass().getSimpleName();
		
		for (int i = 0; i < dataMapList.size(); i++) {
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(mcoUB04Page.createUB04AndSubmitClaim(className,claimID));
				
				mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
				
				if(!mcsnumber.get(i).equals("")) {
					
					report(LogStatus.INFO, "My MCS Claim # for new created UB-04 claim is : " 
					+ mcsnumber.get(i));
					
				}else {
					report(LogStatus.WARNING,"Not able to create claim");
				}
				
				if(previousClaimID < Integer.parseInt(claimID)) {
					previousClaimID++;
				}
				
				System.out.println(claimnumber.get(i));
			}

		}
		
//		dataMapList.forEach(list -> {
//			dataMap = list;
//			
//			claimnumber.add(mcoUB04Page.createUB04AndSubmitClaim());
//			
//			int i = claimnumber.size()-1;
//			
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//				
//				report(LogStatus.INFO, "My MCS Claim # for new created UB-04 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.WARNING,"Not able to create claim");
//			}
//			
//			System.out.println(claimnumber.get(i));
//		});
		
		String conStr = envConfig.getProperty("devDBConnectionString");

		dbUtil = new DBUtil();

		if(!mcsnumber.isEmpty()) {
			String wrapper = "asp_portal_claims_processing_wrapper";
			
			dbUtil.executeSP(conStr, wrapper);
		}

		mcoClaimMaintenancePage = mcoHomePage.navigateToClaimMaintenance();

		mcoClaimMaintenancePage.isClaimMaintenancePageDisplayed();
	
		mcsnumber.stream().forEach(x->{
			if(Objects.nonNull(x) && !x.isEmpty()) {
				
				int i = 0;
				
				dataMap = dataMapList.get(i);
				
				String expectedReason = dataMap.get("expectedReasonCode");
				
				System.out.println("Expected reason : "+expectedReason);

				mcoClaimMaintenancePage.clickFilter();
				
				mcoClaimMaintenancePage.searchWithMCSNumberAndViewClaim(x,expectedReason);
				
				i++;
				}
		});
		
	}
	
	@Test
	public void createAndSaveUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create and save a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		int previousClaimID = 0;

		String className = this.getClass().getSimpleName();

		for (int i = 0; i < dataMapList.size(); i++) {
					dataMap = dataMapList.get(i);
					
					String claimID = dataMap.get("claimID");
					
					if(previousClaimID != Integer.parseInt(claimID)) {
						claimnumber.add(mcoUB04Page.createUB04AndSubmitClaim(className,claimID));
						
						mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
						
						if(!mcsnumber.get(i).equals("")) {
							
							report(LogStatus.INFO, "My MCS Claim # for new created UB-04 claim is : " 
							+ mcsnumber.get(i));
							
						}else {
							report(LogStatus.WARNING,"Not able to create claim");
						}
						
						if(previousClaimID < Integer.parseInt(claimID)) {
							previousClaimID++;
						}
						
						System.out.println(claimnumber.get(i));
					}

				}
		
//		dataMapList.forEach(list -> {
//			dataMap = list;
//			
//			claimnumber.add(mcoUB04Page.createUB04AndSaveClaim());
//			
//			int i = claimnumber.size()-1;
//			
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//				
//				report(LogStatus.INFO, "My MCS Claim # for new created UB-04 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.WARNING,"Not able to create claim");
//			}
//			
//			System.out.println(claimnumber.get(i));
//		});
		
	}
	
	@Test
	public void viewUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create and submit a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		dataMapList.forEach(list -> {
			
			dataMap = list;
			
			String myMCSNumber = dataMap.get("myMCSClaimNumber");
			
			mcoUB04Page.viewUB04Claim(myMCSNumber);
			
		});
		
	}

	@Test
	public void updateAndSubmitUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to Update and submit a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
//		List<String> claimnumber = new ArrayList<String>();
//		
//		List<String> mcsnumber = new ArrayList<String>();
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		String className = this.getClass().getSimpleName();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		int previousClaimID = 0;
		
		for (int i = 0; i < dataMapList.size(); i++) {
			
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(mcoUB04Page.updateAndSubmitUB04Claim(className,claimID));
			
			
			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
			
			if(!mcsnumber.get(i).equals("")) {
				
				report(LogStatus.INFO, "My MCS Claim # for updated UB-04 claim is : " 
				+ mcsnumber.get(i));
				
			}else {
				report(LogStatus.WARNING,"Not able to create claim");
			}
			
			if(previousClaimID < Integer.parseInt(claimID)) {
				previousClaimID++;
			}
			
			System.out.println(claimnumber.get(i));
			}
		}
		
//		dataMapList.forEach(list -> {
//			dataMap = list;
//			
//			claimnumber.add(mcoUB04Page.updateAndSubmitUB04Claim());
//			
//			int i = claimnumber.size()-1;
//			
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//				
//				report(LogStatus.INFO, "My MCS Claim # for updated UB-04 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.WARNING,"Not able to create claim");
//			}
//			
//			System.out.println(claimnumber.get(i));
//		});
		
//		String conStr = envConfig.getProperty("devDBConnectionString");
//
//		dbUtil = new DBUtil();
//
//		if(!mcsnumber.isEmpty()) {
//			String wrapper = "asp_portal_claims_processing_wrapper";
//			
//			dbUtil.executeSP(conStr, wrapper);
//		}

//		claimMaintenancePage = homePage.navigateToClaimMaintenance();
//
//		claimMaintenancePage.isClaimMaintenancePageDisplayed();
//	
//		mcsnumber.stream().forEach(x->{
//			if(Objects.nonNull(x) && !x.isEmpty()) {
//				
//				int i = 0;
//				
//				dataMap = dataMapList.get(i);
//				
//				String expectedReason = dataMap.get("expectedReasonCode");
//				
//				System.out.println("Expected reason : "+expectedReason);
//
//				claimMaintenancePage.clickFilter();
//				
//				claimMaintenancePage.searchWithMCSNumberAndViewClaim(x,expectedReason);
//				
//				i++;
//				}
//		});
		
	}
	
	@Test
	public void updateAndSaveUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to Update and submit a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		dataMapList.forEach(list -> {
			dataMap = list;
			
			claimnumber.add(mcoUB04Page.updateAndSaveUB04Claim());
			
			int i = claimnumber.size()-1;
			
			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
			
			if(!mcsnumber.get(i).equals("")) {
				
				report(LogStatus.INFO, "My MCS Claim # for updated UB-04 claim is : " 
				+ mcsnumber.get(i));
				
			}else {
				report(LogStatus.WARNING,"Not able to create claim");
			}
			
			System.out.println(claimnumber.get(i));
		});
		
	}
	
	@Test
	public void copyAndSubmitUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to Update and submit a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		dataMapList.forEach(list -> {
			dataMap = list;
			
			claimnumber.add(mcoUB04Page.copyAndSubmitUB04Claim());
			
			int i = claimnumber.size()-1;
			
			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
			
			if(!mcsnumber.get(i).equals("")) {
				
				report(LogStatus.INFO, "My MCS Claim # for updated UB-04 claim is : " 
				+ mcsnumber.get(i));
				
			}else {
				report(LogStatus.WARNING,"Not able to create claim");
			}
			
			System.out.println(claimnumber.get(i));
		});
		
//		String conStr = envConfig.getProperty("devDBConnectionString");
//
//		dbUtil = new DBUtil();
//
//		if(!mcsnumber.isEmpty()) {
//			String wrapper = "asp_portal_claims_processing_wrapper";
//			
//			dbUtil.executeSP(conStr, wrapper);
//		}

//		claimMaintenancePage = homePage.navigateToClaimMaintenance();
//
//		claimMaintenancePage.isClaimMaintenancePageDisplayed();
//	
//		mcsnumber.stream().forEach(x->{
//			if(Objects.nonNull(x) && !x.isEmpty()) {
//				
//				int i = 0;
//				
//				dataMap = dataMapList.get(i);
//				
//				String expectedReason = dataMap.get("expectedReasonCode");
//				
//				System.out.println("Expected reason : "+expectedReason);
//
//				claimMaintenancePage.clickFilter();
//				
//				claimMaintenancePage.searchWithMCSNumberAndViewClaim(x,expectedReason);
//				
//				i++;
//				}
//		});
		
	}
	
	@Test
	public void copyAndSaveUB04Claim() {
		
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to Update and submit a "
				+ "new UB-04 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}
		
		mcoUB04Page = mcoHomePage.navigateToUB04Page();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		if (mcoUB04Page.isUB04PageDisplayed()) {
			report(LogStatus.PASS, "UB04 page is displayed");
		} else {
			report(LogStatus.FAIL, "UB04 page is not displayed");
		}
		
		dataMapList.forEach(list -> {
			dataMap = list;
			
			claimnumber.add(mcoUB04Page.copyAndSaveUB04Claim());
			
			int i = claimnumber.size()-1;
			
			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
			
			if(!mcsnumber.get(i).equals("")) {
				
				report(LogStatus.INFO, "My MCS Claim # for updated UB-04 claim is : " 
				+ mcsnumber.get(i));
				
			}else {
				report(LogStatus.WARNING,"Not able to create claim");
			}
			
			System.out.println(claimnumber.get(i));
		});
		
	}
	
}
