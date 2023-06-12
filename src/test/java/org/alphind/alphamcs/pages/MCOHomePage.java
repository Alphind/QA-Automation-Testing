package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOHomePage extends CommonFunctions {

	WebDriver driver;

	private static final Logger log = LogManager.getLogger(MCOHomePage.class);

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;

	@FindBy(xpath = "//*[@role='alertdialog']")
	public static WebElement errorMessage;

	@FindBy(xpath = "//*[@href='#navbarDropdownPatient']")
	WebElement patientLinkNavbar;

	@FindBy(xpath = "//*[text()='Enrollments']")
	WebElement patientEnrollmentsLink;

	@FindBy(xpath = "//*[contains(text(),'Create')]")
	WebElement createButton;

	@FindBy(xpath = "//*[text()='Finance']")
	WebElement financeLink;

	@FindBy(xpath = "//*[text()='CMS 1500']")
	WebElement CMS1500Link;

	@FindBy(xpath = "//*[text()='UB-04']")
	WebElement UB04Link;

	public MCOHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoginSuccessful() {
		if (logout.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public void logout() {
		click(logout, "Logout");
	}

	public void naviagteToPatientEnrollments() {
		click(patientLinkNavbar, "Patient Link");
		click(patientEnrollmentsLink, "Patient enrollments");
		waitUntilClickable(createButton, 30);
	}

	public void naviagteToCMS1500() {
		click(financeLink, "Finance Link");
		click(CMS1500Link, "CMS 1500");
		waitUntilClickable(createButton, 30);
	}

	public void naviagteToUB04() {
		click(financeLink, "Finance Link");
		click(UB04Link, "UB-04");
		waitUntilClickable(createButton, 30);
	}

}
