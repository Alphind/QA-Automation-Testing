package org.alphind.alphamcs.test;

import org.alphind.alphamcs.base.TestBase;
import org.alphind.alphamcs.pages.MCOCMS1500Page;
import org.alphind.alphamcs.pages.MCOClaimMaintenancePage;
import org.alphind.alphamcs.pages.MCOHomePage;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.FileUtil;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class ClaimTest extends TestBase {

	DBUtil dbUtil;
	MCOLoginPage loginPage;
	MCOHomePage homePage;
	MCOClaimMaintenancePage claimMaintenancepage;
	MCOCMS1500Page cms1500page;
	FileUtil fileUtil;

	String userName;
	String passWord;

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

		// claimMaintenancepage.searchWithMCSNumberAndViewClaim("250600");

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

		if (cms1500page.isCMS1500PageDisplayed()) {
			report(LogStatus.PASS, "CMS 1500 page is displayed");
		} else {
			report(LogStatus.FAIL, "CMS 1500 page is not displayed");
		}

		String claimnumber = cms1500page.createClaim();

		String mcsnumber = claimnumber.replaceAll("[^0-9]", "");

		report(LogStatus.INFO, "My MCS Claim # for new created CMS 1500 claim is : " + mcsnumber);

		String conStr = envConfig.getProperty("devDBConnectionString");

		dbUtil = new DBUtil();

		String wrapper = "asp_portal_claims_processing_wrapper";

		dbUtil.executeSP(conStr, wrapper);

		claimMaintenancepage = homePage.navigateToClaimMaintenance();

		claimMaintenancepage.isClaimMaintenancePageDisplayed();

		claimMaintenancepage.clickFilter();

		claimMaintenancepage.searchWithMCSNumberAndViewClaim(mcsnumber);

	}

	@Test
	public void incomingFile837P() {

		userName = envConfig.getProperty("userName");
		passWord = envConfig.getProperty("password");

		report(LogStatus.INFO, "Verify end to end processing for incoming837p File");

		fileUtil = new FileUtil();
		dbUtil = new DBUtil();
		String incoming837pFilePath = "testData\\EDI_Files\\" + dataMap.get("EDIfileName");
		String conStr = envConfig.getProperty("devDBConnectionString");

		String testSFTPFilePath = envConfig.getProperty("testSFTPFilePath");
		String testSFTPArchiveFolderPath = envConfig.getProperty("testSFTPArchiveFolderPath");
		String sandhillsAcceptFolderPath = envConfig.getProperty("sandhillsAcceptFolderPath");
		String encDevSandhillsAcceptFolderPath = envConfig.getProperty("encDevSandhillsAcceptFolderPath");

		fileUtil.copyFile(incoming837pFilePath, testSFTPFilePath + "\\SingleClaimIncoming837p.jci");
		report(LogStatus.PASS, "Incoming 837 file placed at folder -" + testSFTPFilePath);

		if (fileUtil.isFileProcessed("SingleClaimIncoming837p", testSFTPFilePath, 20)) {
			report(LogStatus.PASS, "File processed from folder -" + testSFTPFilePath);

			if (!(fileUtil.findFile("SingleClaimIncoming837p", testSFTPArchiveFolderPath).equals("File Not Found"))) {
				report(LogStatus.PASS, "File found in folder -" + testSFTPArchiveFolderPath);

				String acceptedXMLFileName = fileUtil.findFile("SingleClaimIncoming837p", sandhillsAcceptFolderPath, 5);

				if (!(acceptedXMLFileName.equals("File Not Found"))) {
					report(LogStatus.PASS, "Accepted xml file  -" + acceptedXMLFileName);

					String acceptedXMLFilePath = sandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;
					String acceptedXMLFilePathDest = encDevSandhillsAcceptFolderPath + "\\" + acceptedXMLFileName;

					fileUtil.copyFile(acceptedXMLFilePath, acceptedXMLFilePathDest);
					report(LogStatus.PASS, "Incoming 837 file placed at folder -" + encDevSandhillsAcceptFolderPath);

					dbUtil.executeSP(conStr, "asp_837_process_wrapper");
					report(LogStatus.PASS, "Executed wrapper - asp_837_process_wrapper");
					if (fileUtil.isFileProcessed(acceptedXMLFileName, encDevSandhillsAcceptFolderPath, 30)) {
						report(LogStatus.PASS, "File processed from folder -" + encDevSandhillsAcceptFolderPath);

						String batNum = (dbUtil.executeQuery(conStr,
								"select top 1 bat_num from tb_ic_837P_processing_det h order by h.bat_num desc"))
								.get("bat_num");
						String claimHeaderId = (dbUtil.executeQuery(conStr,
								"select clm_hdr_id from tb_ic_837P_processing_det h where bat_num = " + batNum))
								.get("clm_hdr_id");

						if (claimHeaderId != null) {
							report(LogStatus.PASS, "Claim header ID = " + claimHeaderId);

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

							claimMaintenancepage.searchWithHeaderAndViewClaim(claimHeaderId);
						} else {
							report(LogStatus.FAIL, "Unable to get Claim header ID from database");
						}

					} else {
						report(LogStatus.FAIL, "File processed failed from folder -" + encDevSandhillsAcceptFolderPath);
					}

				} else {
					report(LogStatus.FAIL, "Accepted xml file not found in folder -" + sandhillsAcceptFolderPath);
				}

			} else {
				report(LogStatus.FAIL, "File not found in folder -" + testSFTPArchiveFolderPath);
			}
		} else {
			report(LogStatus.FAIL, "File process failed from folder -" + testSFTPFilePath);
		}

	}

}
