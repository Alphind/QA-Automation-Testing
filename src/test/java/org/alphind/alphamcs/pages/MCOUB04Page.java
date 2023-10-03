package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Abhishek.K.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class MCOUB04Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	//private static final Logger log = LogManager.getLogger(MCOUB04Page.class);

	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	WebElement createButton;
	
	@FindBy(xpath = "//span[contains(text(),'Filter')]/parent::button")
	private WebElement filterBtton;
	
	@FindBy(xpath = "//div[contains(text(),'UB-04')]")
	private WebElement pageHeading;
	
	//Filter Elements
	@FindBy(xpath = "//span[contains(text(),'UB-04 Filter')]")
	private WebElement filterHeading;
	
	@FindBy(xpath = "//input[@formcontrolname='ln']")
	private WebElement filterLastName;
	
	@FindBy(xpath = "//input[@formcontrolname='fn']")
	private WebElement filterFirstName;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='statid']")
	private WebElement filterStatusDropdpwn;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'All')]")
	private WebElement filterStatusOptionAll;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'Saved')]")
	private WebElement filterStatusOptionSaved;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'Submitted')]")
	private WebElement filterStatusOptionSubmitted;
	
	@FindBy(xpath = "//input[@formcontrolname='usrid']")
	private WebElement filterUserID;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='dttype']")
	private WebElement filterDateTypeDropdpwn;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'Date of Service')]")
	private WebElement filterDateTypeOptionDateOfService;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and contains(text(),'Submitted Date')]")
	private WebElement filterDateTypeOptionSubmittedDate;
	
	@FindBy(xpath = "//input[@formcontrolname='stdt']")
	private WebElement filterStartDate;
	
	@FindBy(xpath = "//input[@formcontrolname='enddt']")
	private WebElement filterEndDate;
	
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='check_billon']")
	private WebElement filterPaperBillCheckBox;
	
	@FindBy(xpath = "//input[@formcontrolname='clmno']")
	private WebElement filterMyMCSClaimNumber;
	
	@FindBy(xpath = "//input[@formcontrolname='adj_id']")
	private WebElement filterAdjudicationID;
	
	@FindBy(xpath = "//span[contains(text(),'Clear')]/parent::button")
	private WebElement filterClearButon;
	
	@FindBy(xpath = "//span[contains(text(),'Cancel')]/parent::button")
	private WebElement filterCancelButon;
	
	@FindBy(xpath = "//span[contains(text(),'Clear')]/parent::button")
	private WebElement filterSearchButon;
	
	
	//Create, Update, Copy Elements
	@FindBy(xpath = "//mat-label[contains(text(),'My MCS Claim #')]/parent::div")
	private WebElement myMCSClaimnumber;
	
	@FindBy(xpath = "//span[contains(text(),'Create UB-04')]")
	private WebElement createUB04Heading;
	
	//Form 1
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button")
	private WebElement providerSearchButton;
	
	//provider Search elements
	@FindBy(xpath = "//mat-label[text()='Provider ID']/parent::label/"
			+ "parent::span/preceding-sibling::input")
	private WebElement providerSeachProviderID;
	
	@FindBy(xpath = "//mat-label[text()='Provider Name']/parent::label/"
			+ "parent::span/preceding-sibling::input")
	private WebElement providerSeachProviderName;
	
	@FindBy(xpath = "(//mat-select)[12]")
	private WebElement providerSearchStatusDropdown;
	
	private String dropdownOptions = "//span[@class = 'mat-option-text' "
			+ "and contains(text(),'XX')]";
	
	@FindBy(xpath = "(//mat-select)[13]")
	private WebElement providerSearchSpecialityDropdown;
	
	@FindBy(xpath = "(//mat-select)[14]")
	private WebElement providerSearchOrgTypeDropdown;
	
	@FindBy(xpath = "//mat-label[text()='Zip']/parent::label/"
			+ "parent::span/preceding-sibling::input")
	private WebElement providerSearchZIP;
	
	@FindBy(xpath = "//mat-label[text()='Active']/parent::label/"
			+ "parent::span/preceding-sibling::mat-select")
	private WebElement providerSearchActiveStatusDropdown;
	
	@FindBy(xpath = "//span[text()='Clear']/parent::button")
	private WebElement providerSearchClearButton;
	
	@FindBy(xpath = "//span[text()='Search']/parent::button")
	private WebElement providerSearchSearcButton;
	
	@FindBy(xpath = "//span[text()='Cancel']/parent::button")
	private WebElement providerSearchCancelButton;
	
	@FindBy(xpath = "//span[text()='Select Provider']/parent::button")
	private WebElement providerSearchSelectProviderButton;
	
	private String providerSearchGridRow = "(//tbody)[9]/tr[XX]";
	
	@FindBy(xpath = "//span[text()='Provider Search']")
	private WebElement providerSearchPopupHeading;
	
	@FindBy(xpath = "//span[text()='Provider Search']/following-sibling::button")
	private WebElement providerSearchCloseButton;
	
	
	
	

	// patient Search elements

	////////////////// Implementations

	/**
	 * POM contructor to initialize the page objects.
	 * @param driver
	 */
	public MCOUB04Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Created by Nandhalala
	 * @return whether page is displayed or not
	 */
	public boolean isUB04PageDisplayed() {
		waitForLoadingToDisappear();
		if(pageHeading.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	

	public String createUB04AndSubmitClaim() {

		String myMCSNumber = "";
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		waitForLoadingToDisappear();
		
		if(createUB04Heading.isDisplayed()) {
			report(LogStatus.PASS, "Create UB-04 page is Displayed");
		}else {
			report(LogStatus.FAIL,"Create UB-04 page is Not Displayed");
		}
		
		myMCSNumber = getText(myMCSClaimnumber);

		return myMCSNumber;
	}

}
