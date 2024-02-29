package org.alphind.alphamcs.test;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.pages.HomePage;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.FileUtil;


import com.relevantcodes.extentreports.LogStatus;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class ClaimTest extends TestBase {

	DBUtil dbUtil;
	HomePage homePage;
	MCOLoginPage mcoLoginPage;
	MCOHomePage mcoHomePage;
	MCOClaimMaintenancePage claimMaintenancepage;
	MCOCMS1500Page cms1500page;
	FileUtil fileUtil;

	String userName;
	String passWord;
	
	String networkUserName;
	String networkPassWord;

//	@Test
//	public void viewClaim() {
//
//		report(LogStatus.INFO, "Verify whether able to view claim status.");
//
//		loginPage = new MCOLoginPage(driver);
//
//		loginPage.selectMCOLogin();
//
//		homePage = loginPage.login(userName, passWord);
//
//		if (homePage.isLoginSuccessful()) {
//			report(LogStatus.PASS, "Login successful with user - " + userName);
//		} else {
//			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
//		}
//
//		claimMaintenancepage = homePage.navigateToClaimMaintenance();
//
//		claimMaintenancepage.isClaimMaintenancePageDisplayed();
//
//		claimMaintenancepage.clickFilter();
//
//		claimMaintenancepage.searchWithHeaderAndViewClaim("6557017");
//
//		// claimMaintenancepage.searchWithMCSNumberAndViewClaim("250600");
//
//	}

	@Test
	public void createCMS1500Claim() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create and submit a new CMS 1500 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);
		
		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = mcoHomePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		int previousClaimID = 0;
//		dataMapList.forEach(list -> {
//			String claimID = dataMap.get("claimID");
//			if(previousClaimID < Integer.parseInt(claimID)) {
//				previousClaimID++;
//			}
//			this.getClass().getEnclosingMethod().getName();
//			dataMap = list;
//			if(previousClaimID != Integer.parseInt(claimID)) {
//				
//			}
//			claimnumber.add(cms1500page.createAndSubmitClaim(claimID));
//			
//			int i = claimnumber.size()-1;
//		
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//			
//				report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.WARNING,"Not able to create claim");
//			}
//			
//		});
		String className = this.getClass().getSimpleName();
//		System.out.println(className);
		for (int i = 0; i < dataMapList.size(); i++) {
			
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");		
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(cms1500page.createAndSubmitClaim(className,claimID));
				
				mcsnumber.add(claimnumber.get(previousClaimID).replaceAll("[^0-9]", ""));
				
				if(!mcsnumber.get(previousClaimID).equals("")) {
				
					report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
					+ mcsnumber.get(previousClaimID));
					
				}else {
					report(LogStatus.FAIL,"Not able to create claim");
				}
				
				if(previousClaimID < Integer.parseInt(claimID)) {
					previousClaimID++;
				}
			}
		
		}

		String conStr = envConfig.getProperty("devDBConnectionString");

		dbUtil = new DBUtil();

		String wrapper = "asp_portal_claims_processing_wrapper";

		dbUtil.executeSP(conStr, wrapper);

		claimMaintenancepage = mcoHomePage.navigateToClaimMaintenance();

		claimMaintenancepage.isClaimMaintenancePageDisplayed();
	
		mcsnumber.stream().forEach(x->{
			if(Objects.nonNull(x) && !x.isEmpty()) {
				
				int i = 0;
				
				dataMap = dataMapList.get(i);
				
				String expectedReason = dataMap.get("expectedReasonCode");
				
//				System.out.println("Expected reason : "+expectedReason);

				claimMaintenancepage.clickFilter();
				
				claimMaintenancepage.searchWithMCSNumberAndViewClaim(x,expectedReason);
				
				i++;
				}
		});

	}
	
	@Test
	public void createSaveCMS1500Claim() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to create and save a new CMS 1500 claim.");

		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);

		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = mcoHomePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}

		String className = this.getClass().getSimpleName();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		int previousClaimID = 0;
		
		for (int i = 0; i < dataMapList.size(); i++) {
			
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");		
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(cms1500page.createAndSaveClaim(className,claimID));
				
				mcsnumber.add(claimnumber.get(previousClaimID).replaceAll("[^0-9]", ""));
				
				if(!mcsnumber.get(previousClaimID).equals("")) {
				
					report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
					+ mcsnumber.get(previousClaimID));
					
				}else {
					report(LogStatus.FAIL,"Not able to create claim");
				}
				
				if(previousClaimID < Integer.parseInt(claimID)) {
					previousClaimID++;
				}
			}
		
		}
		
//		String claimnumber = cms1500page.createAndSaveClaim();
//
//		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");

		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " + mcsnumber);

	}
	
	@Test
	public void updateSaveCMS1500Claim() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to update and save a CMS 1500 claim.");

		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);

		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = mcoHomePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}
		
		String className = this.getClass().getSimpleName();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		int previousClaimID = 0;
		
		for (int i = 0; i < dataMapList.size(); i++) {
			
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");		
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(cms1500page.updateAndSaveCMS1500Claim(className,claimID));
				
				mcsnumber.add(claimnumber.get(previousClaimID).replaceAll("[^0-9]", ""));
				
				if(!mcsnumber.get(previousClaimID).equals("")) {
				
					report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
					+ mcsnumber.get(previousClaimID));
					
				}else {
					report(LogStatus.FAIL,"Not able to create claim");
				}
				
				if(previousClaimID < Integer.parseInt(claimID)) {
					previousClaimID++;
				}
			}
		
		}
		
//		String claimnumber = cms1500page.updateAndSaveCMS1500Claim();
//		
//		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");
		
		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " + mcsnumber);

	}
	
	@Test
	public void updateSubmitCMS1500Claim() throws Exception {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		report(LogStatus.INFO, "Verify whether able to update and submit a CMS 1500 claim.");

		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);

		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = mcoHomePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}

		String className = this.getClass().getSimpleName();
		
		List<String> claimnumber = new ArrayList<String>();
		
		List<String> mcsnumber = new ArrayList<String>();
		
		int previousClaimID = 0;
		
		for (int i = 0; i < dataMapList.size(); i++) {
			
			dataMap = dataMapList.get(i);
			
			String claimID = dataMap.get("claimID");		
			
			if(previousClaimID != Integer.parseInt(claimID)) {
				claimnumber.add(cms1500page.updateAndSubmitCMS1500Claim(className,claimID));
				
				mcsnumber.add(claimnumber.get(previousClaimID).replaceAll("[^0-9]", ""));
				
				if(!mcsnumber.get(previousClaimID).equals("")) {
				
					report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
					+ mcsnumber.get(previousClaimID));
					
				}else {
					report(LogStatus.FAIL,"Not able to create claim");
				}
				
				if(previousClaimID < Integer.parseInt(claimID)) {
					previousClaimID++;
				}
			}
		
		}
		
//		String claimnumber = cms1500page.updateAndSubmitCMS1500Claim();
//		
//		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");
		
		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " + mcsnumber);

		String conStr = envConfig.getProperty("devDBConnectionString");

		dbUtil = new DBUtil();

		String wrapper = "asp_portal_claims_processing_wrapper";

		dbUtil.executeSP(conStr, wrapper);
		
		claimMaintenancepage = mcoHomePage.navigateToClaimMaintenance();

		claimMaintenancepage.isClaimMaintenancePageDisplayed();
	
		mcsnumber.stream().forEach(x->{
			if(Objects.nonNull(x) && !x.isEmpty()) {
				
				int i = 0;
				
				dataMap = dataMapList.get(i);
				
				String expectedReason = dataMap.get("expectedReasonCode");
				
//				System.out.println("Expected reason : "+expectedReason);

				claimMaintenancepage.clickFilter();
				
				claimMaintenancepage.searchWithMCSNumberAndViewClaim(x,expectedReason);
				
				i++;
				}
		});

//		claimMaintenancepage = mcoHomePage.navigateToClaimMaintenance();
//
//		claimMaintenancepage.isClaimMaintenancePageDisplayed();
//
//		claimMaintenancepage.clickFilter();
//
//		claimMaintenancepage.searchWithMCSNumberAndViewClaim(mcsnumber);

	}
	
	@Test
	public void viewCMS1500Claim() {
		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");
		
		String clmnum = dataMap.get("myMCSNumber");
		
		report(LogStatus.INFO, "Verify whether able to view a CMS 1500 claim.");
		
		homePage = new HomePage(driver);
		
		mcoLoginPage = homePage.selectMCOLogin();
		
		mcoHomePage = mcoLoginPage.login(userName, passWord);

		if (mcoHomePage.isLoginSuccessful()) {
			report(LogStatus.PASS, "Login successful with user - " + userName);
		} else {
			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
		}

		cms1500page = mcoHomePage.navigateToCMS1500Page();

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}

		cms1500page.viewCMS1500(Integer.parseInt(clmnum));
		
	}
	
//
//	@Test
//	public void EDIincomingFile837PTest() {
//
//		userName = envConfig.getProperty("userName");
//		passWord = envConfig.getProperty("password");
//		
//		networkUserName = envConfig.getProperty("networkUserName");
//		networkPassWord = envConfig.getProperty("networkPassWord");
//
//		report(LogStatus.INFO, "Verify end to end processing for incoming837p File");
//
//		fileUtil = new FileUtil();
//		dbUtil = new DBUtil();
//		String incoming837pFilePath = "testData\\EDI_Files\\" + dataMap.get("EDIfileName");
//		
//		System.out.println(incoming837pFilePath);
//		
//		String conStr = envConfig.getProperty("devDBConnectionString");
//
//		String testSFTPFilePath = envConfig.getProperty("testSFTPFilePath");
//		String testSFTPArchiveFolderPath = envConfig.getProperty("testSFTPArchiveFolderPath");
//		String sandhillsAcceptFolderPath = envConfig.getProperty("sandhillsAcceptFolderPath");
//		String encDevSandhillsAcceptFolderPath = envConfig.getProperty("encDevSandhillsAcceptFolderPath");
//
//		//Copy file from test data to \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
//		fileUtil.copyFile(incoming837pFilePath, testSFTPFilePath + "\\"+dataMap.get("EDIfileName"));
//		report(LogStatus.PASS, "Incoming 837 file placed at folder -" + testSFTPFilePath);
//
//		//Verify file processed from \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test folder
//		//if (fileUtil.isFileProcessed("SingleClaimIncoming837p", testSFTPFilePath, 20)) 
//			if (fileUtil.isFileProcessed(dataMap.get("EDIfileName"), testSFTPFilePath, 20)) {
//			report(LogStatus.PASS, "File processed from folder -" + testSFTPFilePath);
//
//			//Verify processed file present \\10.0.82.211->sftp->Test_Folders->MCS_Folders->1006->in->test->1006->in->test->archive folders
//			if (!(fileUtil.findFile(dataMap.get("EDIfileName"), testSFTPArchiveFolderPath).equals("File Not Found"))) {
//				report(LogStatus.PASS, "File found in folder -" + testSFTPArchiveFolderPath);
//
//				String acceptedXMLFileName = fileUtil.findFile(dataMap.get("EDIfileName"), sandhillsAcceptFolderPath, 5);
//
//				//Verify xml file generated in \\10.0.82.211->sftp->Test_Folders->MCS_Folders->SANDHILLS_ACCEPT_TEST folder
//				if (!(acceptedXMLFileName.equals("File Not Found"))) {
//					report(LogStatus.PASS, "Accepted xml file  -" + acceptedXMLFileName);
//
//					String acceptedXMLFilePath = sandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
//					String acceptedXMLFilePathDest = encDevSandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
//
//					//Copy xml file to \\192.168.10.93->d$->sftp->sandhills_accept
//					fileUtil.copyFile(acceptedXMLFilePath, acceptedXMLFilePathDest);
//					fileUtil.findFile(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 1);
//					report(LogStatus.PASS, "Incoming 837 file placed at folder -" + encDevSandhillsAcceptFolderPath);
//
//					dbUtil.executeSP(conStr, "asp_837_process_wrapper");
//					report(LogStatus.PASS, "Executed wrapper - asp_837_process_wrapper");
//					
//					//Verify file processed from \\192.168.10.93->d$->sftp->sandhills_accept
//					if (fileUtil.isFileProcessed(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 30)) {
//						report(LogStatus.PASS, "File processed from folder -" + encDevSandhillsAcceptFolderPath);
//
//						String batNum = (dbUtil.executeQuery(conStr,
//								"select top 1 bat_num from tb_ic_837P_processing_det h order by h.bat_num desc"))
//								.get("bat_num");
//						String claimHeaderId = (dbUtil.executeQuery(conStr,
//								"select clm_hdr_id from tb_ic_837P_processing_det h where bat_num = " + batNum))
//								.get("clm_hdr_id");
//
//						if (claimHeaderId != null) {
//							report(LogStatus.PASS, "Claim header ID = " + claimHeaderId);
//
//							loginPage = new MCOLoginPage(driver);
//
//							loginPage.selectMCOLogin();
//
//							homePage = loginPage.login(userName, passWord);
//
//							if (homePage.isLoginSuccessful()) {
//								report(LogStatus.PASS, "Login successful with user - " + userName);
//							} else {
//								report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
//							}
//
//							claimMaintenancepage = homePage.navigateToClaimMaintenance();
//
//							claimMaintenancepage.isClaimMaintenancePageDisplayed();
//
//							claimMaintenancepage.clickFilter();
//
//							claimMaintenancepage.searchWithHeaderAndViewClaim(claimHeaderId);
//						} else {
//							report(LogStatus.FAIL, "Unable to get Claim header ID from database");
//						}
//
//					} else {
//						report(LogStatus.FAIL, "File processed failed from folder -" + encDevSandhillsAcceptFolderPath);
//					}
//
//				} else {
//					report(LogStatus.FAIL, "Accepted xml file not found in folder -" + sandhillsAcceptFolderPath);
//				}
//
//			} else {
//				report(LogStatus.FAIL, "File not found in folder -" + testSFTPArchiveFolderPath);
//			}
//		} else {
//			report(LogStatus.FAIL, "File process failed from folder -" + testSFTPFilePath);
//		}
//
//	}

	
//	@Test
//	public void updateAndSubmitCMS1500Claim() {
//
//		userName = envConfig.getProperty("userName");
//		passWord = envConfig.getProperty("password");
//		
//		report(LogStatus.INFO, "Verify whether able to create and submit a new CMS 1500 claim.");
//		report(LogStatus.FAIL,"");
//
//		homePage = new HomePage(driver);
//		
//		mcoLoginPage = homePage.selectMCOLogin();
//		
//		mcoHomePage = mcoLoginPage.login(userName, passWord);
//		
//		if (mcoHomePage.isLoginSuccessful()) {
//			report(LogStatus.PASS, "Login successful with user - " + userName);
//		} else {
//			report(LogStatus.FAIL, "Login unsuccessful with user - " + userName);
//		}
//
//		cms1500page = mcoHomePage.navigateToCMS1500Page();
//
//		if (cms1500page.isCMS1500PageDisplayed()) {
//			report(LogStatus.PASS, "CMS 1500 page is displayed");
//		} else {
//			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
//		}
//		
//		List<String> claimnumber = new ArrayList<String>();
//		
//		List<String> mcsnumber = new ArrayList<String>();
//		
//		dataMapList.forEach(list -> {
//			
//			dataMap = list;
//			
//			claimnumber.add(cms1500page.updateAndSubmitCMS1500Claim());
//			
//			int i = claimnumber.size()-1;
//		
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//			
//				report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.FAIL,"Not able to create claim");
//			}
//			
//		});
//		
//		
//		for (int i = 0; i < dataMapList.size(); i++) {
//			
//			dataMap = dataMapList.get(i);
//		
//			claimnumber.add(cms1500page.createAndSubmitClaim());
//		
//			mcsnumber.add(claimnumber.get(i).replaceAll("[^0-9]", ""));
//			
//			if(!mcsnumber.get(i).equals("")) {
//			
//				report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " 
//				+ mcsnumber.get(i));
//				
//			}else {
//				report(LogStatus.FAIL,"Not able to create claim");
//			}
//
//		
//		}
//
//		String conStr = envConfig.getProperty("devDBConnectionString");
//
//		dbUtil = new DBUtil();
//
//		String wrapper = "asp_portal_claims_processing_wrapper";
//
//		dbUtil.executeSP(conStr, wrapper);
//
//		claimMaintenancepage = mcoHomePage.navigateToClaimMaintenance();
//
//		claimMaintenancepage.isClaimMaintenancePageDisplayed();
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
//				claimMaintenancepage.clickFilter();
//				
//				claimMaintenancepage.searchWithMCSNumberAndViewClaim(x,expectedReason);
//				
//				i++;
//				}
//		});
//
//	}
	
}
