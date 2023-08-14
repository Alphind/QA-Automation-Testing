package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOCMS1500Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(MCOCMS1500Page.class);

	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	WebElement createButton;

	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement employmentStatusUnknown;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement autoAccientUnknown;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement otherAccientUnknown;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Pat_sign_dt')]")
	private WebElement patientSignDate;
	
	//patient search elements
	@FindBy(xpath = "(//span[contains(text(),'Search ')]/parent::button)[1]")
	private WebElement patientSearchButton;
	
	@FindBy(xpath = "//mat-label[text()='Patient ID']/parent::label/parent::span/preceding-sibling::input")
	private WebElement patientID;
	
	@FindBy(xpath = "//span[text() = 'Patient Search']")
	private WebElement headingPatientSearch;
	
	@FindBy(xpath = "//span[text() = 'Search']/parent::button")
	private WebElement searchPatient;
	
	@FindBy(xpath = "//span[text()='Select Patient']/parent::button")
	private WebElement selectPatientButton;

	////////////////// Implementations

	public MCOCMS1500Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String createClaim(String patientid, String patientSigndate) {

		String claimId = "";
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		waitForLoadingToDisappear();
		waitUntilClickable(patientSearchButton, 30);
		putStaticWait(2);
		click(patientSearchButton, "Patient Search button");
		if(patientID.isDisplayed()) {
			sendKeys(patientID, "Patient ID", patientid);
		}
		click(searchPatient, "Search");
		waitForLoadingToDisappear();
		click(driver.findElement(By.xpath("//tbody/tr/td[1]")), 
				"Patient ID : "+patientID);
		click(selectPatientButton, "Select Patient");
		waitForLoadingToDisappear();
		putStaticWait(5);
		//scrollToElement(employmentStatusUnknown,driver);
		click(employmentStatusUnknown, "Unknown");
		//scrollToElement(autoAccientUnknown,driver);
		click(autoAccientUnknown, "Unknown");
		//scrollToElement(otherAccientUnknown,driver);
		click(otherAccientUnknown, "Unknown");
		//scrollToElement(patientSignDate,driver);
		sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
		putStaticWait(2);
		
		return claimId;
	}

}
