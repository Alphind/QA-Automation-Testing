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

	@Test
	public void incomingFile837() {

		report(LogStatus.INFO, "Verify end to end processing for incoming837p File");

		fileUtil = new FileUtil();
		String incoming837pFilePath = "testData\\EDI_Files\\SingleClaimIncoming837p.jci";
		String conStr = envConfig.getProperty("devDBConnectionString");

		String testSFTPFilePath = "\\\\10.0.82.211\\sftp\\Test_Folders\\MCS_Folders\\1006\\in\\test";
		String testSFTPArchiveFolderPath = "\\\\10.0.82.211\\sftp\\Test_Folders\\MCS_Folders\\1006\\in\\test\\archive";
		String sandhillsAcceptFolderPath = "\\\\10.0.82.211\\sftp\\Test_Folders\\SANDHILLS_ACCEPT_TEST";
		String encDevSandhillsAcceptFolderPath = "\\\\192.168.10.93\\d$\\sftp\\sandhills_accept";

		fileUtil.copyFile(incoming837pFilePath, testSFTPFilePath + "\\SingleClaimIncoming837p.jci");
		report(LogStatus.PASS, "Incoming 837 file placed at folder -" + testSFTPFilePath);

		if (fileUtil.isFileProcessed("SingleClaimIncoming837p", testSFTPFilePath, 20)) {
			report(LogStatus.PASS, "File processed from folder -" + testSFTPFilePath);

			if (!(fileUtil.findFile("SingleClaimIncoming837p", testSFTPArchiveFolderPath).equals("File Not Found"))) {
				report(LogStatus.PASS, "File found in folder -" + testSFTPArchiveFolderPath);

				String acceptedXMLFileName = fileUtil.findFile("SingleClaimIncoming837p", sandhillsAcceptFolderPath);

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
