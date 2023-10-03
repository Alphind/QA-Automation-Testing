package org.alphind.alphamcs.pages;

import static org.testng.Assert.assertEquals;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.alphind.alphamcs.base.CommonFunctions;
import org.alphind.alphamcs.exception.CannotCreateClaimException;
import org.alphind.alphamcs.exception.CannotUpdateClaimException;
import org.alphind.alphamcs.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class MCOCMS1500Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(MCOCMS1500Page.class);

	@FindBy(xpath = "//div[contains(text(),\"CMS 1500\")]")
	private WebElement cms1500heading;
	
	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	WebElement createButton;
	

	
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

	//6. PATIENT RELATIONSHIP TO INSURED elements
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Pat_insrd_rel_06']/mat-radio-button[1]")
	private WebElement form6RadioButtonSelf;
	
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Pat_insrd_rel_06']/mat-radio-button[2]")
	private WebElement form6RadioButtonSpouse;
	
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Pat_insrd_rel_06']/mat-radio-button[3]")
	private WebElement form6RadioButtonOters;
	
	
	//other insured's elements
	@FindBy(xpath = "//input[@formcontrolname = 'Other_insrd_ln_09']")
	private WebElement otherInsuredLastname;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_insrd_fn_09']")
	private WebElement otherInsuredFirstname;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_insrd_mn_09']")
	private WebElement otherInsuredMiddlename;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Oi_polcy_num_09a']")
	private WebElement otherInsuredPolicyNumber;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Oi_ins_nm_09d']")
	private WebElement oiName;
	

	// IS PATIENT'S CONDITION RELATED TO elements
	
	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[1]")
	private WebElement employmentStatusYES;
	
	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[2]")
	private WebElement employmentStatusNo;
	
	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement employmentStatusUnknown;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[1]")
	private WebElement autoAccientYes;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[2]")
	private WebElement autoAccientNo;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement autoAccientUnknown;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_aaplace_10b']")
	private WebElement eploymentPlaceState;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[1]")
	private WebElement otherAccientYes;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[2]")
	private WebElement otherAccientNo;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement otherAccientUnknown;
	
	@FindBy(xpath = "//input[@formcontrolname = 'ln']")
	private WebElement claimCodes;
	
	
	//NSURED'S POLICY GROUP OF FECA NUMBER elements
	@FindBy(xpath = "//input[@formcontrolname = 'In_emp_schl_nm_11b']")
	private WebElement otherClaimID;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_plan_nm_11c']")
	private WebElement insuranceName;
	
	@FindBy(xpath = "//mat-label[text()='d. IS THERE ANOTHER BENEFIT PLAN']/following::mat-radio-group/mat-radio-button[1]")
	private WebElement isThereAnotherBenefitPlanYES;
	
	@FindBy(xpath = "//mat-label[text()='d. IS THERE ANOTHER BENEFIT PLAN']/following::mat-radio-group/mat-radio-button[2]")
	private WebElement isThereAnotherBenefitPlanNO;
	
	//12. READ BACK OF FORM COMPLETING AND SIGNING THIS FORM elements
	@FindBy(xpath = "//input[contains(@formcontrolname,'Pat_sign_dt')]")
	private WebElement patientSignDate;
	
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Pat_sign_12']")
	private WebElement signDropDown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Pat_sign_12']/div/div/span/span")
	private WebElement signDropDownValue;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' A')]")
	private WebElement form12SignOptionA;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' I')]")
	private WebElement form12SignOptionI;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' M')]")
	private WebElement form12SignOptionM;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' N')]")
	private WebElement form12SignOptionN;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' O')]")
	private WebElement form12SignOptionO;
	
	@FindBy(xpath = "//mat-option/span[starts-with(text(),' Y')]")
	private WebElement form12SignOptionY;
	
	
	//13. INSURED'S OR AUTHORIZED PERSON'S SIGNATURE elements
	@FindBy(xpath = "//span[text()=' SIGN ']/parent::label/parent::mat-checkbox")
	private WebElement form13Sign;
	
	@FindBy(xpath = "//span[text()=' SIGN ']/preceding-sibling::span/input")
	private WebElement form13SignCheckBox;
	
	
	//14. DATE OF CURRENT ILLNESS, INJURY, or PREGNANCY(LMP) elements
	@FindBy(xpath = "//input[@formcontrolname = 'Dt_of_incident_14']")
	private WebElement form14Date;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Qual_14']")
	private WebElement form14Qual;
	
	
	//15. OTHER DATE elements
	@FindBy(xpath = "//input[@formcontrolname = 'Qual_15']")
	private WebElement form15Qual;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Dt_of_1st_ocur_15']")
	private WebElement form15Date;
	
	
	//16. DATES PATIENT UNABLE TO WORK IN CURRENT OCCUPATION elements
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_abs_frm_16']")
	private WebElement patientAbsentFromDate;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_abs_to_16']")
	private WebElement patientAbsentToDate;
	
	
	//17. NAME OF REFERRING PHYSICIAN OR OTHER SOURCE elements
	@FindBy(xpath = "//input[@formcontrolname = 'Qual_17']")
	private WebElement form17Qual1;
	
	@FindBy(xpath = "//input[@formcontrolname = 'novalue']")
	private WebElement form17Qual2;
	
	@FindBy(xpath = "//mat-label[text()='17.a']/following-sibling::mat-form-field/div/div/div/input")
	private WebElement form17A;
	
	@FindBy(xpath = "//mat-label[text()='17.b']/following-sibling::mat-form-field/div/div/div/input")
	private WebElement form17B;
	
	
	//18. HOSPITALIZATION DATES RELATED TO CURRENT SERVICE elements
	@FindBy(xpath = "//input[@formcontrolname='Hosp_from_18']")
	private WebElement form18HospitalizedFromDate;
	
	@FindBy(xpath = "//input[@formcontrolname='Hosp_to_18']")
	private WebElement form18HospitalizedToDate;
	
	
	//19. ADDITIONAL CLAIM INFORMATION(Designated by NUCC) elements
	@FindBy(xpath = "//input[@formcontrolname='Local_use_19']")
	private WebElement additionalClaimInformation;
	
	
	//20. OUTSIDE LAB elements
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Out_lab_20']/mat-radio-button[1]")
	private WebElement form20RadioButtonYes;
	
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Out_lab_20']/mat-radio-button[2]")
	private WebElement form20RadioButtonNO;
	
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Out_lab_20']/mat-radio-button[3]")
	private WebElement form20RadioButtonUnknown;
	
	@FindBy(xpath = "//input[@formcontrolname='Out_lab_chrg_20']")
	private WebElement form20Charges;
	
	//21. DIAGNOSIS OR NATURE OF ILLNESS OR INJURY (Relate A-L to Service line below (24E).)
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_1_21')]")
	private WebElement diagnosis1;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_2_21')]")
	private WebElement diagnosis2;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_3_21')]")
	private WebElement diagnosis3;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_4_21')]")
	private WebElement diagnosis4;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_5_21')]")
	private WebElement diagnosis5;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_6_21')]")
	private WebElement diagnosis6;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_7_21')]")
	private WebElement diagnosis7;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_8_21')]")
	private WebElement diagnosis8;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_9_21')]")
	private WebElement diagnosis9;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_10_21')]")
	private WebElement diagnosis10;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_11_21')]")
	private WebElement diagnosis11;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_12_21')]")
	private WebElement diagnosis12;
	
	@FindBy(xpath = "//mat-checkbox[contains(@formcontrolname,'Chk_9th_21')]")
	private WebElement icd9CheckBox;
	
	@FindBy(xpath = "//mat-checkbox[contains(@formcontrolname,'Chk_10th_21')]")
	private WebElement icd10CheckBox;
	
	//22. RESUBMISSION CODE elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Medcd_resub_cd_22']")
	private WebElement resubmissionCodeDropDown;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and text()=' 7 - Replacement ']")
	private WebElement replacementOption;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and text()=' 8 - void/cancel ']")
	private WebElement voidOption;
	
	@FindBy(xpath = "//input[@formcontrolname='Medcd_orig_ref_22']")
	private WebElement originalReferenceNumber;
	
	
	//23. PRIOR AUTHORIZATION NUMBER

	@FindBy(xpath = "//input[@formcontrolname='Prior_auth_no_23']")
	private WebElement priorAuthorizationNumber;
	
	

	
	//provider search elements
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button")
	private WebElement providerSearchButton;
	
	@FindBy(xpath = "(//mat-label[text()='Provider ID'])[2]/parent::label/ancestor::span/preceding-sibling::input")
	private WebElement providerId;
	
	@FindBy(xpath = "(//span[contains(text(),'Search')]/parent::button)[3]")
	private WebElement searchProvider;
	
	@FindBy(xpath = "//span[contains(text(),'Select Provider')]/parent::button")
	private WebElement selectProvider;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Site_id']")
	private WebElement selectSiteDropdown;
	
	
	//Add service elements
	@FindBy(xpath = "//span[contains(text(),'Add')]/parent::button")
	private WebElement addService;
	
	@FindBy(xpath = "(//mat-label[text()='From'])[3]/parent::label/parent::span/preceding-sibling::input")
	private WebElement fromDate;
	
	@FindBy(xpath = "(//mat-label[text()='To'])[3]/parent::label/parent::span/preceding-sibling::input")
	private WebElement toDate;
	
	@FindBy(xpath = "//mat-select[contains(@formcontrolname,'Place')]")
	private WebElement selectPOS;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	private WebElement inputPOS;
	
	@FindBy(xpath = "//mat-label[text()='Service Code']/parent::label/parent::span/preceding-sibling::input")
	private WebElement serviceCode;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Rendernpi_type']/div")
	private WebElement renderingNPItypeDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Rendernpi_type']/div/div/span/span")
	private WebElement renderingNPItypeDropdownValue;

	
	@FindBy(xpath = "//span[text()=' Site NPI ']")
	private WebElement optionSiteNPI;
	
	@FindBy(xpath = "//span[text()=' No NPI ']")
	private WebElement optionNoNPI;
	
	@FindBy(xpath = "//span[text()=' Clinician NPI ' ]")
	private WebElement optionClinicianNPI;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Rendernpi']")
	private WebElement renderingNPIDropDown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Rendernpi']/div/div/span/span")
	private WebElement renderingNPIDropDownValue;
	
	@FindBy(xpath = "//input[@formcontrolname='Modfr1']")
	private WebElement modifier1;
	
	@FindBy(xpath = "//input[@formcontrolname='Modfr2']")
	private WebElement modifier2;
	
	@FindBy(xpath = "//input[@formcontrolname='Modfr3']")
	private WebElement modifier3;
	
	@FindBy(xpath = "//input[@formcontrolname='Modfr4']")
	private WebElement modifier4;
	
	@FindBy(xpath = "//input[@formcontrolname='Amt']")
	private WebElement cobbAmount;
	
	@FindBy(xpath = "//input[@formcontrolname='Sel_cob_all_amt']")
	private WebElement cobAllowableAmount;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Cobid']")
	private WebElement cobReason;
	
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='Epdst']")
	private WebElement checkboxEPDST;
	
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='Epdst']/label/span/input")
	private WebElement inputEPDST;
	
	@FindBy(xpath = "//mat-label[text()='Charges']/parent::label/parent::span/preceding-sibling::input")
	private WebElement charges;
	
	@FindBy(xpath = "//mat-label[text()='Days/Units']/parent::label/parent::span/preceding-sibling::input" )
	private WebElement daysPerUnits;
	
	@FindBy(xpath = "//mat-label[text()='Diag. Pointer']/parent::label/parent::span/preceding-sibling::input")
	private WebElement diagpointer;
	
	@FindBy(xpath = "//mat-label[text()='Taxonomy Code']/parent::label/parent::span/preceding-sibling::input")
	private WebElement renderingTaxonomy;
	
	@FindBy(xpath = "(//span[contains(text(),'Save')])[2]/parent::button")
	private WebElement saveService;
	
	@FindBy(xpath = "(//span[contains(text(),'Cancel')])[2]")
	private WebElement cancelService;
	
	
	//26. PATIENT'S ACCOUNT NUMBER
	@FindBy(xpath = "//input[@formcontrolname='Pat_acct_num_26']")
	private WebElement patientAccountNumber;

	
	//27. ACCEPT ASSIGNMENT
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Acept_asnmt_27']/mat-radio-button[1]")
	private WebElement acceptAssignmentYes;
	
	@FindBy(xpath = "//mat-radio-group[@formcontrolname='Acept_asnmt_27']/mat-radio-button[2]")
	private WebElement acceptAssignmentNo;
	
	
	//31. SIGNATURE OF PHYSICIAN/SUPPLIER INCLUDING DEGREES/CREDENTIALS elements
	@FindBy(xpath = "(//mat-label[text()='Date']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement physicianSignDate;
	
	@FindBy(xpath = "//mat-checkbox[contains(@formcontrolname,'Phy_sign_31')]")
	private WebElement physicianSignCheckBox;
	
	@FindBy(xpath = "//input[@formcontrolname='Recd_dt']")
	private WebElement mcoReceivedDate;
	
	
	//32. NAME AND ADDRESS OF FACILITY WHERE SERVICES WERE RENDERED (If other than home or office elements
	@FindBy(xpath = "//input[@formcontrolname='Srvc_ren_addr_32']")
	private WebElement facilityAddress;
	
	@FindBy(xpath = "//input[@formcontrolname='Srvc_ren_state_32']")
	private WebElement facilityState;
	
	@FindBy(xpath = "//input[@formcontrolname='Srvc_ren_city_32']")
	private WebElement facilityCity;
	
	@FindBy(xpath = "//input[@formcontrolname='Srvc_ren_zip_32']")
	private WebElement facilityZip;
	
	@FindBy(xpath = "(//mat-label[text()='Phone']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement sitePhoneNumber;
	
	@FindBy(xpath = "//input[@formcontrolname='Srvc_ren_npi_32']")
	private WebElement facilityNPI;
	
	
	//33. PHYSICIAN'S, SUPPLIER'S BILLING ADDRESS elements
	@FindBy(xpath = "(//mat-label[text()='b. Taxonomy Code']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement billingTaxonomy;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_ln_33']")
	private WebElement physicianLastName;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_fn_33']")
	private WebElement physicianFirstName;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_mi_33']")
	private WebElement physicianMiddleInitial;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_addr_33']")
	private WebElement physicianAddress;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_state_33']")
	private WebElement physicianState;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_city_33']")
	private WebElement physicianCity;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_zip_33']")
	private WebElement physicianZip;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_ph_33']")
	private WebElement physicianPhone;
	
	@FindBy(xpath = "//input[@formcontrolname='Phy_npi_33']")
	private WebElement physicianNPI;
	
	
	//Save,Submit,Print and Cancel button elements
	@FindBy(xpath = "//span[contains(text(),'Submit')]/parent::button")
	private WebElement submitClaim;
	
	@FindBy(xpath="//span[text()=' Save ']/parent::button")
    private WebElement saveCMS;
	
	
	
	//Header elements
	@FindBy(xpath = "(//div[contains(text(),'My MCS Claim #')])[2]")
	private WebElement mymcsclaim;
	
	
	//Unsaved Changes Popup Elements
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement unsavedChagesOK;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement unsavedChagesCancel;
	
	@FindBy(xpath = "//button[text()='×']")
	private WebElement unsavedChagesCloase;
	
	
	
	//FIlter elements
	@FindBy(xpath = "//span[contains(text(),'Filter')]/parent::button")
	private WebElement filterButton;
	
	@FindBy(xpath = "//input[@formcontrolname='clmno']")
	private WebElement filtermyMCSClaimNO;
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	private WebElement filterSearchButton;
	
	
	
	//xpaths
	@FindBy(xpath="//span[text()='Update']/parent::button")
    private WebElement updateCMS;
	
	@FindBy(xpath="(//table/tbody)[1]/tr[1]")
    private WebElement expandFirstCMSRecord;
	
	@FindBy(xpath="(//table/tbody)[3]/tr[1]")
    private WebElement firstServiceLine;
	
	@FindBy(xpath="//span[text()=' Modify ']")
    private WebElement lineModify;
	
	
	@FindBy(xpath = "//span[text()=' Cancel ']/parent::button")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//mat-select[contains(@formcontrolname,'Place')]/div/div/span/span")
	private WebElement modifyPOS;
	
	
	//Filter & View Components created by shaik

    @FindBy (xpath = "//span[contains (text(), 'View')]/parent::button")
    private WebElement viewButton;

    @FindBy (xpath = "//tbody/tr/td/button")
    private WebElement expandButton;

    @FindBy (xpath = "//input[@ng-reflect-name='Pat_ln_02']")
    private WebElement viewLastName;

    @FindBy (xpath = "//input[@ng-reflect-name='Pat_fn_02']")
    private WebElement viewFirstName;

    @FindBy (xpath = "//input[@ng-reflect-name='Bd_prv_taxonomy_33b']")
    private WebElement viewRenderingTaxonomyCode;
	
    @FindBy (xpath = "//input[@ng-reflect-name='Srvc_ren_npi_32']")
    private WebElement renderingNpi;
	
	////////////////// Implementations

	public MCOCMS1500Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Created by Nandhalala
	public boolean isCMS1500PageDisplayed() {
		waitForLoadingToDisappear();
		if(cms1500heading.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	
	public void filterWithMCSNumber(int mymcsclaimnumber) {
		
		click(filterButton, "Filter Button");
		
		waitUntilClickable(filtermyMCSClaimNO, 30);
		
		sendKeys(filtermyMCSClaimNO, "My MCS Claim # ", String.valueOf(mymcsclaimnumber));
		
		click(filterSearchButton, "Filter Search");
		
		waitForLoadingToDisappear();
		
	}
	
	
	//created by Nandhalala
	private void cancelClaim() {
		click(cancelButton, "Cancel");
		putStaticWait(1);
		waitUntilClickable(unsavedChagesOK, 2);
		click(unsavedChagesOK, "OK");
	}
	
	//Created by Nandhalala
	public String createAndSubmitClaim() {
		
		String patientid = dataMap.get("patientid");
		String patientRelationshipToInsured = dataMap.get("PatientRelationshipToInsured");
		String otherInsurdLastName = dataMap.get("otherInsuredLastName");
		String otherInsurdFirstName = dataMap.get("otherInsuredFirstName");
		String otherInsurdMiddleName = dataMap.get("otherInsuredMiddleName");
		String otherInsurdPolicyNumber = dataMap.get("otherInsuredPolicyNumber");
		String otherInsurdProgramName = dataMap.get("othernsuredProgramName");
		String employmentStatus = dataMap.get("employmentStatus");
		String autoAccident = dataMap.get("autoAccident");
		String emplymentPlaceState = dataMap.get("employmentPlaceState");
		String otherAccident = dataMap.get("otherAcciedent");
		String clmCodes = dataMap.get("claimCodes");
		String otherclmId = dataMap.get("otherClaimID");
		String insurancePlanProgramName = dataMap.get("insurancePlanOrProgramName");
		String isThereAnotherbenefitPLN = dataMap.get("isThereAnotherBenefitPlan");
		String sign = dataMap.get("sign");
		String insurdOrAuthorizedPersonsSignature = dataMap.get("Insured'sOrAuthorizedPerson'sSignature");
		String form14dt = dataMap.get("form14Date");
		String form14qual = dataMap.get("form14Qual");
		String form15dt = dataMap.get("form15Date");
		String form15qual = dataMap.get("form15Qual");
		String patabsentfrmdt = dataMap.get("patientAbsentFromDate");
		String patabsenttodt = dataMap.get("patientAbsentToDate");
		String form17qual1 = dataMap.get("form17Qualifier1");
		String form17qual2 = dataMap.get("form17Qualifier2");
		String form17a = dataMap.get("form17a");
		String form17b = dataMap.get("form17b");
		String hospitalizedfrdt = dataMap.get("hospitalizedFromDate");
		String hospitalizedtodt = dataMap.get("hospitalizedToDate");
		String form19ClaimInformation = dataMap.get("additionalClaimInformation");
		String outsideLab = dataMap.get("outsideLabRadiobutton");
		String outsidelabchrgs = dataMap.get("outsideLabCharges");
		String patientSigndate = dataMap.get("patientSigndate");
		String icd = dataMap.get("icdCode");
		String diagnosisCode = dataMap.get("diagnosisCode");
		String diag2 = dataMap.get("diagnosis2");
		String diag3 = dataMap.get("diagnosis3");
		String diag4 = dataMap.get("diagnosis4");
		String diag5 = dataMap.get("diagnosis5");
		String diag6 = dataMap.get("diagnosis6");
		String diag7 = dataMap.get("diagnosis7");
		String diag8 = dataMap.get("diagnosis8");
		String diag9 = dataMap.get("diagnosis9");
		String diag10 = dataMap.get("diagnosis10");
		String diag11 = dataMap.get("diagnosis11");
		String diag12 = dataMap.get("diagnosis12");
		String resubmissionCode = dataMap.get("resubmissionCode");
		String originalClaimHeaderID = dataMap.get("originalReferenceNumber");
		String authNumber = dataMap.get("priorAuthorizationNumber");
		String providerID = dataMap.get("providerID");
		String siteid = dataMap.get("siteID");
		String serviceFromDate = dataMap.get("serviceFromDate");
		String serviceToDate = dataMap.get("serviceToDate"); 
		String POS = dataMap.get("POS");
		String renderingNPIType = dataMap.get("renderingNPItype");
		String renderingNPI = dataMap.get("renderingNPI");
		String procCode = dataMap.get("procCode");
		String mod1 = dataMap.get("modifier1");
		String mod2 = dataMap.get("modifier2");
		String mod3 = dataMap.get("modifier3");
		String mod4 = dataMap.get("modifier4");
		String serviceAmt = dataMap.get("serviceAmt");
		String daysUnits = dataMap.get("dayunits");
		String diagnosispointer = dataMap.get("diagnosispointer");
		String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");
		String patientaccnumber = dataMap.get("patientAccountNumber");
		String acceptassignment = dataMap.get("acceptAssignment");
		String physiciansigndate = dataMap.get("physiciansigndate");
		String receivedDate = dataMap.get("receivedDate");
		String siteAddress = dataMap.get("siteAddress");
		String siteState = dataMap.get("siteState");
		String siteCity = dataMap.get("siteCity");
		String siteZip = dataMap.get("SiteZip");
		String sitephno = dataMap.get("sitephno");
		String sitephNPI = dataMap.get("siteNPI");
		String physicianlastname = dataMap.get("physicianLastName");	
		String physicianfirstname = dataMap.get("physicianFirstName");	
		String physicianmiddleinitial = dataMap.get("physicianMiddleInitial");	
		String physicianaddress = dataMap.get("physicianAddress");	
		String physicianstate = dataMap.get("physicianState");	
		String physiciancity = dataMap.get("physicianCity");	
		String physicianzip = dataMap.get("physicianZip");	
		String physicianphone = dataMap.get("physicianPhone");	
		String physiciannpi = dataMap.get("physicianNPI");
		String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");
		String cobamt = dataMap.get("cobAmount");
		String coballowableamt = dataMap.get("cobAllowableAmount");
		String cobReason = dataMap.get("cobReason");
		String epdst = dataMap.get("epdst");

		String MyMCSNumber = "";
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		waitForLoadingToDisappear();
		waitUntilClickable(patientSearchButton, 30);
		putStaticWait(2);
		click(patientSearchButton, "Patient Search button");
		if(patientID.isDisplayed()) {
			sendKeys(patientID, "Patient ID", patientid);
			String actualpatid = getAttribute(patientID, "ng-reflect-model");
			assertEquals(actualpatid, patientid, "Patient Id from text field is "+actualpatid+ 
					" not "+patientid);
		}
		click(searchPatient, "Search");
		waitForLoadingToDisappear();
		click(driver.findElement(By.xpath("//tbody/tr/td[1]")), 
				"Patient ID : "+patientid);
		
		click(selectPatientButton, "Select Patient");
		waitForLoadingToDisappear();
		putStaticWait(5);
		
		//form 6
		if(Objects.nonNull(patientRelationshipToInsured) && !patientRelationshipToInsured.equals("")) {
			switch (patientRelationshipToInsured) {
			case "SELF":
				click(form6RadioButtonSelf, "Patient Relationship To Insured - SELF");
				break;
			
			case "SPOUSE":
				click(form6RadioButtonSpouse, "Patient Relationship To Insured - SPOUSE");
				break;
				
			case "OTHERS":
				click(form6RadioButtonOters, "Patient Relationship To Insured - OTHERS");
				break;

			default:
				report(LogStatus.WARNING, "Accept Assignment value not valid");
				break;
			}
		}
		
		//form 9
		if(Objects.nonNull(otherInsurdLastName) && !otherInsurdLastName.equals("")) {
			sendKeys(otherInsuredLastname, "Other Insured's Last Name", otherInsurdLastName);
			String actualInsurdLastName = getAttribute(otherInsuredLastname, "value");
			assertEquals(actualInsurdLastName, otherInsurdLastName,"OtherInsured Last Name "
				+ "from text field is "+actualInsurdLastName+" not "+otherInsurdLastName);
			}
		
		if(Objects.nonNull(otherInsurdFirstName)&&!otherInsurdFirstName.equals("")) {
			sendKeys(otherInsuredFirstname, "Other Insured's First Name", otherInsurdFirstName);
			String actualInsurdFirstName = getAttribute(otherInsuredFirstname, "value");
			assertEquals(actualInsurdFirstName, otherInsurdFirstName,"OtherInsured First Name "
				+ "from text field is "+actualInsurdFirstName+" not "+otherInsurdFirstName);
		}
		
		if(Objects.nonNull(otherInsurdMiddleName)&&!otherInsurdMiddleName.equals("")) {
			sendKeys(otherInsuredMiddlename, "Other Insured's Middle Name", otherInsurdMiddleName);
			String actualInsurdMiddleName = getAttribute(otherInsuredMiddlename, "value");
			assertEquals(actualInsurdMiddleName, otherInsurdMiddleName,"OtherInsured Middle Name "
				+ "from text field is "+actualInsurdMiddleName+" not "+otherInsurdMiddleName);
		}
		
		if(Objects.nonNull(otherInsurdPolicyNumber) && !otherInsurdPolicyNumber.equals("")) {
			sendKeys(otherInsuredPolicyNumber, "Other Insured's Policy Number", otherInsurdPolicyNumber);
			String actualInsurdPolicyNumber = getAttribute(otherInsuredPolicyNumber, "value");
			assertEquals(actualInsurdPolicyNumber, otherInsurdPolicyNumber,"OtherInsured Policy number "
				+ "from text field is "+actualInsurdPolicyNumber+" not "+otherInsurdPolicyNumber);
		}
		
		if(Objects.nonNull(otherInsurdProgramName) && !otherInsurdProgramName.equals("")) {
			sendKeys(oiName, "Other Insurance Name", otherInsurdProgramName);
			String actualOIName = getAttribute(oiName, "value");
			assertEquals(actualOIName, otherInsurdProgramName,"Other Insured Policy Name from feild is "+
					actualOIName+" not "+otherInsurdProgramName);
		}
		
		//form 10
		switch (employmentStatus) {
		case "YES":
			click(employmentStatusYES, "Unknown");			
			break;
			
		case "NO":
			click(employmentStatusNo, "Unknown");			
			break;
			
		case "UNKNOWN":
			click(employmentStatusUnknown, "Unknown");	
			break;

		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
		
		switch (autoAccident) {
		case "YES":
			click(autoAccientYes, "Unknown");			
			break;
			
		case "NO":
			click(autoAccientNo, "Unknown");			
			break;
			
		case "UNKNOWN":
			click(autoAccientUnknown, "Unknown");	
			break;

		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
		
		if(Objects.nonNull(emplymentPlaceState) && !emplymentPlaceState.equals("")) {
			sendKeys(eploymentPlaceState, "10.Place State", emplymentPlaceState);
			waitForLoadingToDisappear();
			WebElement state = driver.findElement(By.xpath("//span[@class = 'mat-option-text' and contains(text(),'"
					+emplymentPlaceState+"')]"));
			waitForLoadingToDisappear();
			click(state, "Place State");
			waitForLoadingToDisappear();
		}
		
		switch (otherAccident) {
		case "YES":
			click(otherAccientYes, "Unknown");			
			break;
			
		case "NO":
			click(otherAccientNo, "Unknown");			
			break;
			
		case "UNKNOWN":
			click(otherAccientUnknown, "Unknown");
			break;

		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
		
		
		if(Objects.nonNull(clmCodes) && !clmCodes.equals("")) {
			sendKeys(claimCodes, "Claim Codes", clmCodes);
			String actualClmCodes = getAttribute(claimCodes, "value");
			assertEquals(actualClmCodes, clmCodes,"Claim Codes from field is "+actualClmCodes+" not "+
					clmCodes);
		}
		
		//form 11
		if(Objects.nonNull(otherclmId)&&!otherclmId.equals("")) {
			sendKeys(otherClaimID, "Other Claim ID ", otherclmId);
			String actualOtherClaimId = getAttribute(otherClaimID, "value");
			assertEquals(actualOtherClaimId, otherclmId, "Other Claim ID from field is "
			+actualOtherClaimId+" not "+otherclmId);
		}
		
		if(Objects.nonNull(insurancePlanProgramName) && !insurancePlanProgramName.equals("")) {
			sendKeys(insuranceName, "Insurance Plan or Program Name feild", 
					insurancePlanProgramName);
			String actualinsurancePlanProgramName = getAttribute(insuranceName, "value");
			assertEquals(actualinsurancePlanProgramName, insurancePlanProgramName,
					"Insurance plan from field is "+actualinsurancePlanProgramName+" not "+insurancePlanProgramName);
		}
		
		if(Objects.nonNull(isThereAnotherbenefitPLN) && !isThereAnotherbenefitPLN.equals("")) {
			if(isThereAnotherbenefitPLN.equalsIgnoreCase("YES"))
				click(isThereAnotherBenefitPlanYES, "IS THERE ANOTHER BENEFIT PLAN - YES");
			else if(isThereAnotherbenefitPLN.equalsIgnoreCase("NO"))
				click(isThereAnotherBenefitPlanNO, "IS THERE ANOTHER BENEFIT PLAN - NO");
			else
				report(LogStatus.WARNING,
						"No effective value found for Is There Another Beh=nefit Plan");
		}
		
		//form 12
		String signvalue = getText(signDropDownValue);
		
		//System.out.println(signvalue);
		
		//System.out.println(signvalue.charAt(0));
		
		char signchar = signvalue.charAt(0);
		
		//System.out.println(sign);
		
		if(Objects.nonNull(sign) && !sign.equals("")) {
			if(!(signchar==sign.charAt(0))) {
				click(signDropDown, "Sign Dropdown");
				switch (sign.charAt(0)) {
				case 'A':
					click(form12SignOptionA, " A ");
					break;
				case 'I':
					click(form12SignOptionI, " I ");
					break;
				case 'M':
					click(form12SignOptionM, " M ");
					break;
				case 'N':
					click(form12SignOptionN, " N ");
					break;
				case 'O':
					click(form12SignOptionO, " O ");
					break;
				case 'Y':
					click(form12SignOptionY, " Y ");
					break;
				default:
					report(LogStatus.WARNING, "Accept Assignment value not valid");
					break;
				}
			}
		}
		
		
		
		
		sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
		putStaticWait(2);
		
		String actualPatientSignDate = getAttribute(patientSignDate, "value");
		assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
		+actualPatientSignDate+" not equals "+ patientSigndate);
		
		//form 13
		String form13SignValue = getAttribute(form13SignCheckBox, "aria-checked");
		//System.out.println(form13SignValue);
		
		if(Objects.nonNull(insurdOrAuthorizedPersonsSignature) && 
				!insurdOrAuthorizedPersonsSignature.equals("")) {
			
			if(form13SignValue.equalsIgnoreCase("TRUE") &&
					insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("NO")) {
				click(form13Sign, "Form 13 check box");
			}
			
			if(form13SignValue.equalsIgnoreCase("FALSE") &&
					insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("YES")) {
				click(form13Sign, "Form 13 check box");
			}
			
		}
		
		//form 14
		if (Objects.nonNull(form14qual)&&!form14dt.equals("")) {
			sendKeys(form14Qual, "Form 14 QUAL", form14qual);
			
			String actualForm14QUAL = getAttribute(form14Qual, "value");
			assertEquals(actualForm14QUAL, form14qual, "Date from field is "+actualForm14QUAL+" not "+
			form14qual);
		}
		
		if (Objects.nonNull(form14dt)&&!form14dt.equals("")) {
			sendKeys(form14Date, "Form 14 Date", form14dt);
			
			String actualForm14Date = getAttribute(form14Date, "value");
			assertEquals(actualForm14Date, form14dt, "Date from field is "+actualForm14Date+" not "+
			form14dt);
		}
		
		//form 15
		if (Objects.nonNull(form15dt)&&!form15dt.equals("")) {
			sendKeys(form15Date, "Form 15 Date", form15dt);
			
			String actualForm15Date = getAttribute(form15Date, "value");
			assertEquals(actualForm15Date, form15dt, "Date from field is "+actualForm15Date+" not "+
			form15dt);
		}
		
		if (Objects.nonNull(form15qual)&&!form15dt.equals("")) {
			sendKeys(form15Qual, "Form 15 QUAL", form15qual);
			
			String actualForm15QUAL = getAttribute(form15Qual, "value");
			assertEquals(actualForm15QUAL, form15qual, "Date from field is "+actualForm15QUAL+" not "+
			form15qual);
		}
		
		//form 16
		if(Objects.nonNull(patabsentfrmdt) && !patabsentfrmdt.equals("")) {
			sendKeys(patientAbsentFromDate, "Patient Unable To Work in Current Occupation from date", 
					patabsentfrmdt);
			
			String actualPatAbsFrmDt  = getAttribute(patientAbsentFromDate, "value");
			assertEquals(actualPatAbsFrmDt, patabsentfrmdt,"'Patient absent from date' from field is"
			+actualPatAbsFrmDt+" not "+patabsentfrmdt);
			
		}
		
		if(Objects.nonNull(patabsenttodt) && !patabsenttodt.equals("")) {
			sendKeys(patientAbsentToDate, "Patient Unable To Work in Current Occupation to date", 
					patabsenttodt);
			
			String actualPatAbsToDt  = getAttribute(patientAbsentToDate, "value");
			assertEquals(actualPatAbsToDt, patabsentfrmdt,"'Patient absent to date' from field is"
			+actualPatAbsToDt+" not "+patabsenttodt);
			
		}
		
		//form 17
		if(Objects.nonNull(form17qual1) && !form17qual1.equals("")) {
			sendKeys(form17Qual1, "Form 1 Qualifier 1", form17qual1);
			
			String actualForm17Qual1 = getAttribute(form17Qual1, "value");
			assertEquals(actualForm17Qual1, form17qual1, "Qualifier 1 from form 17 is " +actualForm17Qual1
					+" not "+form17qual1);
			
		}
		
		if(Objects.nonNull(form17qual2) && !form17qual2.equals("")) {
			sendKeys(form17Qual2, "Form 1 Qualifier 2", form17qual2);
			
			String actualForm17Qual2 = getAttribute(form17Qual2, "value");
			assertEquals(actualForm17Qual2, form17qual2, "Qualifier 2 from form 17 is " +actualForm17Qual2
					+" not "+form17qual2);
			
		}
		
		if(Objects.nonNull(form17a) && !form17a.equals("")) {
			
			sendKeys(form17A, "Form 17.a ", form17a);
			
			String actualForm17a = getAttribute(form17A, "value");
			assertEquals(actualForm17a, form17a, "Value from From 17 A is"+actualForm17a+" not "+
			form17a);
			
			String actualForm17b = getAttribute(form17B, "value");
			assertEquals(actualForm17b, form17b, "Value from From 17 B is"+actualForm17b+" not "+
			form17b);
			
		}
		
		//form 18
		if(Objects.nonNull(hospitalizedfrdt) && !hospitalizedfrdt.equals("")) {
			
			sendKeys(form18HospitalizedFromDate, "Form 18 Hospitalized from date", hospitalizedfrdt);
			
			String actualHospitalizedFromDate = getAttribute(form18HospitalizedFromDate, "value");
			assertEquals(actualHospitalizedFromDate, hospitalizedfrdt, "Value from Hospitalized From "
					+ "date field is "+actualHospitalizedFromDate+" not "+hospitalizedfrdt);
			
		}
		
		if(Objects.nonNull(hospitalizedtodt) && !hospitalizedtodt.equals("")) {
			
			sendKeys(form18HospitalizedToDate, "Form 18 Hospitalized to date", hospitalizedtodt);
			
			String actualHospitalizedToDate = getAttribute(form18HospitalizedToDate, "value");
			assertEquals(actualHospitalizedToDate, hospitalizedtodt, "Value from Hospitalized To "
					+ "date field is "+actualHospitalizedToDate+" not "+hospitalizedtodt);
			
		}
		
		//form 19
		if(Objects.nonNull(form19ClaimInformation) && !form19ClaimInformation.equals("")) {
			
			sendKeys(additionalClaimInformation, "Additional Claim Information", form19ClaimInformation);
			
			String actualAdditionalClaimInformation = getAttribute(additionalClaimInformation, "value");
			assertEquals(actualAdditionalClaimInformation, form19ClaimInformation, "Value from "
					+ "Additional Claim Information field is "+actualAdditionalClaimInformation+" not "+form19ClaimInformation);
			
		}
		
		//form 20
		if(Objects.nonNull(outsideLab) && !outsideLab.equals("")) {
			switch (outsideLab) {
			case "YES":
				click(form20RadioButtonYes, "Outside Lab - YES");
				break;
				
			case "NO":
				click(form20RadioButtonNO, "Outside Lab - NO");
				break;
				
			case "UNKNOWN":
				click(form20RadioButtonUnknown, "Outside Lab - UNKNOWN");
				break;

			default:
				report(LogStatus.WARNING, "Accept Assignment value not valid");
				break;
			}
		}
		
		
		if(Objects.nonNull(outsidelabchrgs) && !outsidelabchrgs.equals("")) {
			
			form20Charges.clear();
			
			sendKeys(form20Charges, "Outside Lab Charges", outsidelabchrgs);
			
			String actualOutsideLabCharges = getAttribute(form20Charges, "value");
			assertEquals(actualOutsideLabCharges, outsidelabchrgs, "The value from field "
					+ "outside lab charges is "+actualOutsideLabCharges+" not "+outsidelabchrgs);
			
		}
		
		
		//form 21
		String actualICD10 = getAttribute(icd10CheckBox, "class");
		String actualICD9 = getAttribute(icd9CheckBox, "class");
		
		switch (icd) {
		case "ICD9":
			if( !actualICD9.contains("mat-checkbox-checked"))
			click(icd9CheckBox,"ICD 9");
			break;
		case "ICD10":
			if( !actualICD10.contains("mat-checkbox-checked"))
			click(icd10CheckBox,"ICD 10");
			break;

		default:
			report(LogStatus.WARNING,"Not a valid icd code");
			break;
		}
		
		sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
		waitForLoadingToDisappear();
		
		String actualDiagnosis1 = getAttribute(diagnosis1, "value");
		
		assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "+actualDiagnosis1+
				" not equals "+diagnosisCode);
		
		putStaticWait(1);
		
		if(Objects.nonNull(diag2) && !diag2.equals("")) {
			sendKeys(diagnosis2, "Diagnosis 2", diag2);
			waitForLoadingToDisappear();
			String actualDiagnosis2 = getAttribute(diagnosis2, "value");
			assertEquals(actualDiagnosis2, diag2, "Diagnosis 2 from field is "+actualDiagnosis2+
					" not equals "+diag2);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag3) && !diag3.equals("")) {
			sendKeys(diagnosis3, "Diagnosis 3", diag3);
			waitForLoadingToDisappear();
			String actualDiagnosis3 = getAttribute(diagnosis3, "value");
			assertEquals(actualDiagnosis3, diag3, "Diagnosis 3 from field is "+actualDiagnosis3+
					" not equals "+diag3);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag4) && !diag4.equals("")) {
			sendKeys(diagnosis4, "Diagnosis 4", diag4);
			waitForLoadingToDisappear();
			String actualDiagnosis4 = getAttribute(diagnosis4, "value");
			assertEquals(actualDiagnosis4, diag4, "Diagnosis 4 from field is "+actualDiagnosis4+
					" not equals "+diag4);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag5) && !diag5.equals("")) {
			sendKeys(diagnosis5, "Diagnosis 5", diag5);
			waitForLoadingToDisappear();
			String actualDiagnosis5 = getAttribute(diagnosis5, "value");
			assertEquals(actualDiagnosis5, diag5, "Diagnosis 5 from field is "+actualDiagnosis5+
					" not equals "+diag5);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag6) && !diag6.equals("")) {
			sendKeys(diagnosis6, "Diagnosis 6", diag6);
			waitForLoadingToDisappear();
			String actualDiagnosis6 = getAttribute(diagnosis6, "value");
			assertEquals(actualDiagnosis6, diag6, "Diagnosis 6 from field is "+actualDiagnosis6+
					" not equals "+diag6);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag7) && !diag7.equals("")) {
			sendKeys(diagnosis7, "Diagnosis 7", diag7);
			waitForLoadingToDisappear();
			String actualDiagnosis7 = getAttribute(diagnosis7, "value");
			assertEquals(actualDiagnosis7, diag7, "Diagnosis 7 from field is "+actualDiagnosis7+
					" not equals "+diag7);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag8) && !diag8.equals("")) {
			sendKeys(diagnosis8, "Diagnosis 8", diag8);
			waitForLoadingToDisappear();
			String actualDiagnosis8 = getAttribute(diagnosis8, "value");
			assertEquals(actualDiagnosis8, diag8, "Diagnosis 8 from field is "+actualDiagnosis8+
					" not equals "+diag8);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag9) && !diag9.equals("")) {
			sendKeys(diagnosis9, "Diagnosis 2", diag9);
			waitForLoadingToDisappear();
			String actualDiagnosis9 = getAttribute(diagnosis9, "value");
			assertEquals(actualDiagnosis9, diag9, "Diagnosis 9 from field is "+actualDiagnosis9+
					" not equals "+diag9);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag10) && !diag10.equals("")) {
			sendKeys(diagnosis10, "Diagnosis 10", diag10);
			waitForLoadingToDisappear();
			String actualDiagnosis10 = getAttribute(diagnosis10, "value");
			assertEquals(actualDiagnosis10, diag10, "Diagnosis 10 from field is "+actualDiagnosis10+
					" not equals "+diag10);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag11) && !diag11.equals("")) {
			sendKeys(diagnosis11, "Diagnosis 11", diag11);
			waitForLoadingToDisappear();
			String actualDiagnosis11 = getAttribute(diagnosis11, "value");
			assertEquals(actualDiagnosis11, diag11, "Diagnosis 2 from field is "+actualDiagnosis11+
					" not equals "+diag11);
			putStaticWait(1);
		}
		
		if(Objects.nonNull(diag12) && !diag12.equals("")) {
			sendKeys(diagnosis12, "Diagnosis 12", diag12);
			waitForLoadingToDisappear();
			String actualDiagnosis12 = getAttribute(diagnosis2, "value");
			assertEquals(actualDiagnosis12, diag12, "Diagnosis 12 from field is "+actualDiagnosis12+
					" not equals "+diag12);
			putStaticWait(1);
		}
		
		//form 22 Resubmission
		if(Objects.nonNull(resubmissionCode)&&Objects.nonNull(originalClaimHeaderID)&&
				!resubmissionCode.equals("") && !originalClaimHeaderID.equals("")) {
			
			click(resubmissionCodeDropDown, "Resubmission Code");
			
			if(resubmissionCode.equalsIgnoreCase("Replacement")) {
				click(replacementOption,"Replacement");
			}else if(resubmissionCode.equalsIgnoreCase("Void")) {
				click(voidOption,"Void");
			}else {
				report(LogStatus.WARNING, "Invalid Option");
			}
			
			sendKeys(originalReferenceNumber, "Original Reference Claim Header ID", originalClaimHeaderID);
			
			String actualoriginalClaimHeaderID= getAttribute(originalReferenceNumber, "value");
			assertEquals(actualoriginalClaimHeaderID, originalClaimHeaderID,"The value from Original "
					+ "refernce number feild is "+actualoriginalClaimHeaderID+" not "+originalClaimHeaderID);
			
		}
		
		//form 23 prior authorization
		if(Objects.nonNull(authNumber) && !authNumber.equals("")) {
			
			sendKeys(priorAuthorizationNumber, "Prior Authorization Number", authNumber);
			
			String actualAuthNumber = getAttribute(priorAuthorizationNumber, "value");
			assertEquals(actualAuthNumber, authNumber, "The value from feild is "+actualAuthNumber+
					" not "+authNumber);
			
		}
		
		click(providerSearchButton, "Provider Search");
		waitForLoadingToDisappear();
		putStaticWait(1);
		sendKeys(providerId, "Provider ID", providerID);
		String actualproviderId = getAttribute(providerId, "value");

		assertEquals(actualproviderId,providerID,"Provider Id from field is "

		+actualproviderId+" not equals "+ providerID);
		click(searchProvider, "Search");
		waitForLoadingToDisappear();
		putStaticWait(2);
		driver.findElement(By.xpath("//td[contains(text(),'"+providerID+"')]")).click();
		waitForLoadingToDisappear();
		putStaticWait(2);
		click(selectProvider, "Select Provider");
		waitForLoadingToDisappear();
		putStaticWait(1);
		
		click(selectSiteDropdown, "Site selection");
		waitUntilClickable(unsavedChagesOK, 2);
		click(unsavedChagesOK, "OK");
		click(driver.findElement(By.xpath("//span[contains(text(),'"+siteid+"')]"
				+ "/parent::mat-option")), siteid);
		waitForLoadingToDisappear();
		
		addService(serviceFromDate, serviceToDate, POS, renderingNPIType, renderingNPI,procCode, mod1, mod2,mod3, mod4,serviceAmt, 
				daysUnits, diagnosispointer, renderingtaxonomyCode,cobamt,coballowableamt,cobReason,
				epdst);
		
		click(driver.findElement(By.xpath("//*[@role='alertdialog']")), "Saved successfully");
		
		//form 26 Patient Account Number
		if(Objects.nonNull(patientaccnumber) && !patientaccnumber.equals("")) {
			
			sendKeys(patientAccountNumber, "Patient Account Number", patientaccnumber);
			
			String actualAccountNumber = getAttribute(patientAccountNumber, "value");
			assertEquals(actualAccountNumber, patientaccnumber, "The value from feild is "
			+actualAccountNumber+" not "+patientaccnumber);
			
		}
		
		//form 27 Accept Assignment
		if(Objects.nonNull(acceptassignment) && !acceptassignment.equals("")) {
			switch (acceptassignment) {
			case "YES":
				click(acceptAssignmentYes, "Accept Assignment - YES");
				break;
			case "NO":
				click(acceptAssignmentNo, "Accept Assignment - No");
				break;

			default:
				report(LogStatus.WARNING, "Accept Assignment value not valid");
				break;
			}
		}
		
		
		//form 31 Physician Signature
		click(physicianSignCheckBox, "Physician Sign Check Box");
		
		sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
		
		//Validation Steps- physicianSignDate- Created by keerthana
		String actualphysicianSignDate= getAttribute(physicianSignDate,"value");
		assertEquals(actualphysicianSignDate,physiciansigndate,"physicianSignDate from field is "+actualphysicianSignDate+"not equals"+ physiciansigndate );
		
		if(Objects.nonNull(receivedDate) && !receivedDate.equals("")) {
			sendKeys(mcoReceivedDate, "Date Received by MCO", receivedDate);
			
			String actualReceivedDate = getAttribute(mcoReceivedDate, "value");
			assertEquals(actualReceivedDate, receivedDate,"The value from Date Received by MCO "
					+ "field is "+actualReceivedDate+" not "+receivedDate);
			
		}
		
		
		//Form 32
		if(Objects.nonNull(siteAddress) && !siteAddress.equals("")) {
			
			facilityAddress.clear();
			
			sendKeys(facilityAddress, "Site Address ", siteAddress);
			
			String actualsiteAddress = getAttribute(facilityAddress, "value");
			assertEquals(actualsiteAddress, siteAddress,"The value from Site Address fild is "
					+actualsiteAddress+" not "+siteAddress);
		}
		
		if(Objects.nonNull(siteState) && !siteState.equals("")) {
			
			facilityState.clear();
			
			sendKeys(facilityState, "Site State", siteState);
			
			String actualsitestate = getAttribute(facilityState, "value");
			assertEquals(actualsitestate, siteState,"The value from Site State fild is "
					+actualsitestate+" not "+siteState);
		}
		
		if(Objects.nonNull(siteCity) && !siteCity.equals("")) {
			
			facilityCity.clear();
			
			sendKeys(facilityCity, "Site City", siteCity);
			
			String actualsitecity = getAttribute(facilityCity, "value");
			assertEquals(actualsitecity, siteCity,"The value from Site City fild is "
					+actualsitecity+" not "+siteCity);
		}
		
		if(Objects.nonNull(siteZip) && !siteZip.equals("")) {
			
			facilityZip.clear();
			
			sendKeys(facilityZip, "Site ZIP", siteZip);
			
			String actualsitezip = getAttribute(facilityZip, "value");
			assertEquals(actualsitezip, siteZip,"The value from Site ZIP fild is "
					+actualsitezip+" not "+siteZip);
		}
		
		String actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
		
		if(Objects.nonNull(actualsitePhoneNumber) && !actualsitePhoneNumber.equals("")) {
			sitePhoneNumber.clear();
		}
		
		sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
		
		//Validation steps- sitephonenumber- Created by keerthana
		actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
		assertEquals(actualsitePhoneNumber,sitephno, "sitePhoneNumber from field is" +actualsitePhoneNumber+"not equals"+sitePhoneNumber );
		
		
		if(Objects.nonNull(sitephNPI) && !sitephNPI.equals("")) {
			facilityNPI.clear();
			sendKeys(facilityNPI, "Site NPI", sitephNPI);
			
			String actualsitenpi = getAttribute(facilityNPI, "value");
			assertEquals(actualsitenpi, sitephNPI,"The value from Received date fild is "
					+actualsitenpi+" not "+sitephNPI);
		}
		
		
		if(Objects.nonNull(physicianfirstname) && !physicianfirstname.equals("")) {
			physicianFirstName.clear();
			sendKeys(physicianFirstName, "Physician First Name", physicianfirstname);
			
			String actualphysicianfirstname = getAttribute(physicianFirstName, "value");
			assertEquals(actualphysicianfirstname, physicianfirstname,"The value from Received date fild is "
					+actualphysicianfirstname+" not "+physicianfirstname);
		}
		
		if(Objects.nonNull(physicianlastname) && !physicianlastname.equals("")) {
			physicianLastName.clear();
			sendKeys(physicianLastName, "Physician First Name", physicianlastname);
			
			String actualphysicianlastname = getAttribute(physicianLastName, "value");
			assertEquals(actualphysicianlastname, physicianlastname,"The value from Received date fild is "
					+actualphysicianlastname+" not "+physicianlastname);
		}
		
		if(Objects.nonNull(physicianmiddleinitial) && !physicianmiddleinitial.equals("")) {
			physicianMiddleInitial.clear();
			sendKeys(physicianMiddleInitial, "Physician First Name", physicianmiddleinitial);
			
			String actualphysicianmiddleinitial = getAttribute(physicianMiddleInitial, "value");
			assertEquals(actualphysicianmiddleinitial, physicianmiddleinitial,"The value from Received date fild is "
					+actualphysicianmiddleinitial+" not "+physicianmiddleinitial);
		}
		
		
		if(Objects.nonNull(physicianaddress) && !physicianaddress.equals("")) {
			physicianAddress.clear();
			sendKeys(physicianAddress, "Physician First Name", physicianaddress);
			
			String actualphysicianaddress = getAttribute(physicianAddress, "value");
			assertEquals(actualphysicianaddress, physicianaddress,"The value from Received date fild is "
					+actualphysicianaddress+" not "+physicianaddress);
		}
		
		if(Objects.nonNull(physicianstate) && !physicianstate.equals("")) {
			physicianState.clear();
			sendKeys(physicianState, "Physician First Name", physicianstate);
			
			String actualphysicianstate = getAttribute(physicianAddress, "value");
			assertEquals(actualphysicianstate, physicianstate,"The value from Received date fild is "
					+actualphysicianstate+" not "+physicianstate);
		}
		
		if(Objects.nonNull(physiciancity) && !physiciancity.equals("")) {
			physicianCity.clear();
			sendKeys(physicianCity, "Physician First Name", physiciancity);
			
			String actualphysiciancity = getAttribute(physicianCity, "value");
			assertEquals(actualphysiciancity, physiciancity,"The value from Received date fild is "
					+actualphysiciancity+" not "+physiciancity);
		}
		
		if(Objects.nonNull(physicianzip) && !physicianzip.equals("")) {
			physicianZip.clear();
			sendKeys(physicianZip, "Physician First Name", physicianzip);
			
			String actualphysicianzip = getAttribute(physicianZip, "value");
			assertEquals(actualphysicianzip, physicianzip,"The value from Received date fild is "
					+actualphysicianzip+" not "+physicianzip);
		}
		
		if(Objects.nonNull(physicianphone) && !physicianphone.equals("")) {
			physicianPhone.clear();
			sendKeys(physicianPhone, "Physician First Name", physicianphone);
			
			String actualphysicianphone = getAttribute(physicianPhone, "value");
			assertEquals(actualphysicianphone, physicianphone,"The value from Received date fild is "
					+actualphysicianphone+" not "+physicianphone);
		}
		
		if(Objects.nonNull(physiciannpi) && !physiciannpi.equals("")) {
			physicianNPI.clear();
			sendKeys(physicianNPI, "Physician First Name", physiciannpi);
			
			String actualphysiciannpi = getAttribute(physicianNPI, "value");
			assertEquals(actualphysiciannpi, physiciannpi,"The value from Received date fild is "
					+actualphysiciannpi+" not "+physiciannpi);
		}
		
		sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
		waitForLoadingToDisappear();
		WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
		click(billingtaxonmy, billingTaxonomyCode);
		waitForLoadingToDisappear();
		MyMCSNumber = getText(mymcsclaim);
		click(submitClaim, "Submit button");
		
		putStaticWait(2);
		
		//String alerttext = getText(driver.findElement(By.xpath("//*[@role='alertdialog']")));
		String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
		System.out.println(alerttext);
		
		waitForLoadingToDisappear();
		
		
		if(alerttext.contains("Success")) {
			report(LogStatus.PASS, "Successfully submitted");
			return MyMCSNumber;
		}else {
			report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
			try {
				throw new CannotCreateClaimException("Not able to submit the claim due to error : "
						+alerttext);
			}catch(CannotCreateClaimException e) {
				report(LogStatus.FAIL, e.getMessage());
				e.printStackTrace();
				cancelClaim();
				report(LogStatus.WARNING,"Claim is cancelled");
				return"Not able to create the claim";
			}
		}
		
		//return MyMCSNumber;
	}

	
	/**
	 * 
	 * @param serviceFromDate
	 * @param serviceToDate
	 * @param POS
	 * @param renderingNPItype
	 * @param renderingNPI
	 * @param procCode
	 * @param mod1
	 * @param mod2
	 * @param mod3
	 * @param mod4
	 * @param serviceAmt
	 * @param daysUnits
	 * @param diagnosispointer
	 * @param renderingtaxonomyCode
	 * @param cobamt
	 * @param coballowableamt
	 * @param cobreason
	 * @param epdst
	 * @return whether service added successfully or not
	 */
	private boolean addService(String serviceFromDate, String serviceToDate, String POS,
			String renderingNPItype, String renderingNPI, String procCode, String mod1, 
			String mod2, String mod3, String mod4, String serviceAmt, String daysUnits, 
			String diagnosispointer, String renderingtaxonomyCode, String cobamt,
			String coballowableamt, String cobreason, String epdst) {
		
		boolean flag = false;
		
		click(addService, "Add service");
		waitForLoadingToDisappear();
		putStaticWait(1);
		
		sendKeys(fromDate, "From Date", serviceFromDate);
		
		//Validation Steps-fromDate created by keerthana
		String actualFromDate = getAttribute(fromDate, "value");
		assertEquals(actualFromDate,serviceFromDate,"From Date from field is "+actualFromDate+" not equals "+ serviceFromDate);
		
		sendKeys(toDate, " TO Date", serviceToDate);
		
		//Validation Steps- toDate- Created by keerthana
		String actualToDate= getAttribute(toDate, "value");
		assertEquals(actualToDate,serviceToDate,"To Date from field is" +actualToDate+" not equals "+ serviceToDate);
		
		click(selectPOS, "Place Of Service");
		String poscode = POS.replaceAll("[^0-9]", "");
		String postext = POS.replaceAll("[0-9]", "");
		sendKeys(inputPOS, "Place Of service", poscode);
		putStaticWait(2);
		driver.findElement(By.xpath("//span[contains(text(),'"+postext+"')]/parent::mat-option")).click();
		
		if(Objects.nonNull(mod1) && !mod1.equals("")) {
			
			sendKeys(modifier1, "Modifier 1", mod1);
			
			String actualModifier1 = getAttribute(modifier1, "value");
			assertEquals(actualModifier1, mod1,"The value from Modifier 1 field is "+actualModifier1+
					" not "+mod1);
			
			if(Objects.nonNull(mod2) && !mod2.equals("")) {
				
				sendKeys(modifier2, "Modifier 2", mod2);
			
				String actualModifier2 = getAttribute(modifier2, "value");
				assertEquals(actualModifier2, mod2,"The value from Modifier 2 field is "
				+actualModifier2+" not "+mod2);
				if(Objects.nonNull(mod3) && !mod3.equals("")) {
					
					sendKeys(modifier3, "Modifier 3", mod3);
					
					String actualModifier3 = getAttribute(modifier3, "value");
					assertEquals(actualModifier3, mod3,"The value from Modifier 3 field is "
					+actualModifier3+" not "+mod3);
					if(Objects.nonNull(mod4) && !mod4.equals("")) {
						
						sendKeys(modifier4, "Modifier 4", mod4);
						
						String actualModifier4 = getAttribute(modifier4, "value");
						assertEquals(actualModifier4, mod4,"The value from Modifier 4 field is "
						+actualModifier4+" not "+mod4);
					}
					
				}
				
			}
			
		}
		
		
		sendKeys(serviceCode, "Service Code", procCode);
		
		//Validation Steps-Service Code- Created by keerthana
		String actualServiceCode = getAttribute(serviceCode, "value");
		assertEquals(actualServiceCode,procCode,"Service Code from Field is"+actualServiceCode+"not equals"+ procCode);
		
		waitForLoadingToDisappear();
		
		sendKeys(charges, "Charges", String.valueOf(serviceAmt));
		
		waitForLoadingToDisappear();
		
		String actualrenderingNPIType = getText(renderingNPItypeDropdownValue);
		
		if(!actualrenderingNPIType.equals(renderingNPItype)) {
			switch (renderingNPItype) {
			case "SITE NPI":
				putStaticWait(2);
				click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
				putStaticWait(1);
				click(optionSiteNPI, "SITE NPI");
				waitForLoadingToDisappear();
				break;
			case "NO NPI":
				putStaticWait(2);
				click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
				putStaticWait(1);
				click(optionNoNPI, "NO NPI");
				waitForLoadingToDisappear();
				break;
			case "CLINICIAN NPI":
				putStaticWait(2);
				click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
				putStaticWait(1);
				click(optionClinicianNPI, "CLINICIAN NPI");
				waitForLoadingToDisappear();
				break;

			default:
				break;
			}
		}
		
		actualrenderingNPIType = getText(renderingNPItypeDropdownValue);
		if(!actualrenderingNPIType.equalsIgnoreCase("No NPI")) {
			String actualRenderingNPIValue = getText(renderingNPIDropDownValue);
			actualRenderingNPIValue = actualRenderingNPIValue.replaceAll("[^0-9]", "");
			
			if(!actualRenderingNPIValue.equals(renderingNPI)) {
				click(renderingNPIDropDown,"Rendering NPI");
				click(driver.findElement(By.xpath("//span[contains(text(),'"+renderingNPI+"') "
						+ "and @class = 'mat-option-text']")),renderingNPI);
			}
		}
		
		//Validation Steps-Charges- Created by keerthana
		String actualCharges= getAttribute(charges, "value");
		assertEquals(actualCharges,String.valueOf(serviceAmt),"Charges from field is" +actualCharges+"not equals"+ String.valueOf(serviceAmt) ); 
		waitForLoadingToDisappear();
		
		sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));
		
		//Validation steps-daysPerUnits- Created by keerthana
		String actualdaysPerUnits= getAttribute(daysPerUnits, "value");
		assertEquals(actualdaysPerUnits, String.valueOf(daysUnits),"daysPerUnits from field is" +actualdaysPerUnits+"not equals"+String.valueOf(daysUnits));
		
		sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);
		
		//Validation steps- diagpointer-Created by keerthana
		String actualdiagpointer= getAttribute(diagpointer, "value");
		assertEquals(actualdiagpointer, diagnosispointer,"diagpointer from field is " +actualdiagpointer+"not equals"+ diagnosispointer);
		
		if(!actualrenderingNPIType.equalsIgnoreCase("No NPI")) {
			sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
			waitForLoadingToDisappear();
			putStaticWait(2);
			WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
			click(renderingtaxonomy, renderingtaxonomyCode);
			putStaticWait(2);
		}
		
		if(Objects.nonNull(cobamt) && !cobamt.equals("")) {
			
			cobbAmount.clear();
			
			sendKeys(cobbAmount, "COB Amount", cobamt);
			
			String actualcobamt = getAttribute(cobbAmount, "value");
			assertEquals(actualcobamt, cobamt, "The Value from feild is "+actualcobamt+" not "+
			cobamt);
			
		}
		
		if(Objects.nonNull(coballowableamt) && !coballowableamt.equals("")) {
			
			cobAllowableAmount.clear();
			
			sendKeys(cobAllowableAmount, "COB Amount", coballowableamt);
			
			String actualcobamt = getAttribute(cobAllowableAmount, "value");
			assertEquals(actualcobamt, coballowableamt, "The Value from feild is "+actualcobamt+" not "+
					coballowableamt);
			
		}
		
		if(Objects.nonNull(cobreason) && !cobreason.equals("")) {
			
			click(cobReason,"COB reason dropdown");
			
			driver.findElement(By.xpath("//span[contains(text(),"
					+ "'"+cobreason+"')]/parent::mat-option")).click();
			
		}
		
		if(Objects.nonNull(epdst) && !epdst.equals("")) {
			String actualepdst = getAttribute(inputEPDST, "aria-checked");
			if(actualepdst.equals("true") && epdst.equalsIgnoreCase("NO")) {
				click(checkboxEPDST,"EPDST");
				actualepdst = getAttribute(inputEPDST, "aria-checked");
				String asserttext = actualepdst.equals("false")?"EPDST checkbox unchecked"
						:"EPDST checkbox not unchecked";
				System.out.println(asserttext);
			}
			if(actualepdst.equals("false") && epdst.equalsIgnoreCase("YES")) {
				click(checkboxEPDST,"EPDST");
				actualepdst = getAttribute(inputEPDST, "aria-checked");
				String asserttext = actualepdst.equals("true")?"EPDST checkbox checked"
						:"EPDST checkbox not checked";
				System.out.println(asserttext);
			}
		}
		
		click(saveService, "Save Service");
		putStaticWait(2);
		String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
		System.out.println(alerttext);
		waitForLoadingToDisappear();
		if(alerttext.contains("Saved successfully")) {
			report(LogStatus.PASS, "Service added Successfully");
			flag = true;
		}else {
			report(LogStatus.FAIL,alerttext);
			try {
				throw new Exception("Cannot add service");
			}catch(Exception e) {
				report(LogStatus.FAIL, e.getMessage());
				e.printStackTrace();
				click(cancelService, "Cancel");
				putStaticWait(1);
				waitUntilClickable(unsavedChagesOK, 2);
				click(unsavedChagesOK, "OK");
				
			}
		}
		return flag;
		
	}
	/**
	 * 
	 * @param serviceFromDate
	 * @param serviceToDate
	 * @param POS
	 * @param renderingNPItype
	 * @param renderingNPI
	 * @param procCode
	 * @param mod1
	 * @param mod2
	 * @param mod3
	 * @param mod4
	 * @param serviceAmt
	 * @param daysUnits
	 * @param diagnosispointer
	 * @param renderingtaxonomyCode
	 * @param cobamt
	 * @param coballowableamt
	 * @param cobreason
	 * @param epdst
	 * @return
	 */
	private boolean modifyService(String serviceFromDate, String serviceToDate, String POS,
			String renderingNPItype, String renderingNPI, String procCode, String mod1, 
			String mod2, String mod3, String mod4, String serviceAmt, String daysUnits, 
			String diagnosispointer, String renderingtaxonomyCode, String cobamt,
			String coballowableamt, String cobreason, String epdst) {
		
		boolean flag = false;
		
		click(lineModify, "Modify service");
		waitForLoadingToDisappear();
		putStaticWait(1);
		
		if(Objects.nonNull(serviceFromDate) && !serviceToDate.equals("")) {
			String actualFromDate = getAttribute(fromDate, "value");
			if(!actualFromDate.equals(serviceFromDate)) {
				fromDate.clear();
				sendKeys(fromDate, "From Date", serviceFromDate);
				actualFromDate = getAttribute(fromDate, "value");
			}
			//Validation Steps-fromDate created by keerthana
			assertEquals(actualFromDate,serviceFromDate,"From Date from field is "+actualFromDate
					+" not equals "+ serviceFromDate);
		}
		
		if(Objects.nonNull(serviceToDate) && !serviceToDate.equals("")) {
			String actualToDate= getAttribute(toDate, "value");
			if(!serviceToDate.equals(actualToDate)) {
				toDate.clear();
				sendKeys(toDate, " TO Date", serviceToDate);
				actualToDate= getAttribute(toDate, "value");
			}
			//Validation Steps- toDate- Created by keerthana
			assertEquals(actualToDate,serviceToDate,"To Date from field is" +actualToDate
					+" not equals "+ serviceToDate);
		}

		if(Objects.nonNull(POS) && !POS.equals("") ) {
			click(selectPOS, "Place Of Service");
			String poscode = POS.replaceAll("[^0-9]", "");
			String postext = POS.replaceAll("[0-9]", "");
			sendKeys(inputPOS, "Place Of service", poscode);
			putStaticWait(2);
			driver.findElement(By.xpath("//span[contains(text(),'"+postext+"')]/parent::mat-option"))
				.click();
		}
		
		
		if(Objects.nonNull(mod1) && !mod1.equals("")) {
			String actualModifier1 = getAttribute(modifier1, "value");
			if(!actualModifier1.equals(mod1)) {
				modifier1.clear();
				sendKeys(modifier1, "Modifier 1", mod1);
				actualModifier1 = getAttribute(modifier1, "value");
			}
			assertEquals(actualModifier1, mod1,"The value from Modifier 1 field is "+actualModifier1+
					" not "+mod1);
			
			if(Objects.nonNull(mod2) && !mod2.equals("")) {
				String actualModifier2 = getAttribute(modifier2, "value");
				if(!actualModifier2.equals(mod2)) {
					modifier2.clear();
					sendKeys(modifier2, "Modifier 2", mod2);
					actualModifier2 = getAttribute(modifier2, "value");
				}
				assertEquals(actualModifier2, mod2,"The value from Modifier 2 field is "
						+actualModifier2+" not "+mod2);
				if(Objects.nonNull(mod3) && !mod3.equals("")) {
					String actualModifier3 = getAttribute(modifier3, "value");
					if(!actualModifier3.equals(mod3)) {
						modifier3.clear();
						sendKeys(modifier3, "Modifier 3", mod3);
						actualModifier3 = getAttribute(modifier3, "value");
					}
					assertEquals(actualModifier3, mod3,"The value from Modifier 3 field is "
					+actualModifier3+" not "+mod3);
					if(Objects.nonNull(mod4) && !mod4.equals("")) {
						String actualModifier4 = getAttribute(modifier4, "value");
						if(!actualModifier4.equals(mod4)) {
							modifier4.clear();
							sendKeys(modifier4, "Modifier 4", mod4);
							actualModifier4 = getAttribute(modifier4, "value");
						}
						assertEquals(actualModifier4, mod4,"The value from Modifier 4 field is "
						+actualModifier4+" not "+mod4);
					}
					
				}
				
			}
			
		}
		
		if(Objects.nonNull(procCode) && !procCode.equals("")) {
			String actualServiceCode = getAttribute(serviceCode, "value");
			if(!actualServiceCode.equals(procCode)) {
				serviceCode.clear();
				sendKeys(serviceCode, "Service Code", procCode);
				actualServiceCode = getAttribute(serviceCode, "value");
			}	
			//Validation Steps-Service Code- Created by keerthana
			assertEquals(actualServiceCode,procCode,"Service Code from Field is"
					+actualServiceCode+"not equals"+ procCode);
			
		}
	
		if(Objects.nonNull(String.valueOf(serviceAmt)) && !String.valueOf(serviceAmt).equals("")) {
			String actualCharges= getAttribute(charges, "value");
			if(actualCharges.equals(String.valueOf(serviceAmt))) {
				charges.clear();
				sendKeys(charges, "Charges", String.valueOf(serviceAmt));
				actualCharges= getAttribute(charges, "value");
			}
			//Validation Steps-Charges- Created by keerthana
			assertEquals(actualCharges,String.valueOf(serviceAmt),"Charges from field is" 
					+actualCharges+"not equals"+ String.valueOf(serviceAmt) ); 
		}
		
		waitForLoadingToDisappear();
		
			String actualrenderingNPIType = getText(renderingNPItypeDropdownValue);
			
			if(!actualrenderingNPIType.equals(renderingNPItype)) {
				switch (renderingNPItype) {
				case "SITE NPI":
					putStaticWait(2);
					click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
					putStaticWait(1);
					click(optionSiteNPI, "SITE NPI");
					break;
				case "NO NPI":
					putStaticWait(2);
					click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
					putStaticWait(1);
					click(optionNoNPI, "NO NPI");
					break;
				case "CLINICIAN NPI":
					putStaticWait(2);
					click(renderingNPItypeDropdownValue, "Rendering NPI Type Dropdown");
					putStaticWait(1);
					click(optionClinicianNPI, "CLINICIAN NPI");
					break;

				default:
					break;
				}
			}
			
			actualrenderingNPIType = getText(renderingNPItypeDropdownValue);
			if(!actualrenderingNPIType.equalsIgnoreCase("No NPI")) {
				String actualRenderingNPIValue = getText(renderingNPIDropDownValue);
				actualRenderingNPIValue = actualRenderingNPIValue.replaceAll("[^0-9]", "");
				
				if(!actualRenderingNPIValue.equals(renderingNPI)) {
					click(renderingNPIDropDown,"Rendering NPI");
					click(driver.findElement(By.xpath("//span[contains(text(),'"+renderingNPI+"') "
							+ "and @class = 'mat-option-text']")),renderingNPI);
				}
			}
		
		if(Objects.nonNull(String.valueOf(daysUnits)) && 
				String.valueOf(daysUnits).equals("")) {
			String actualdaysPerUnits= getAttribute(daysPerUnits, "value");
			if(actualdaysPerUnits.equals(daysPerUnits)) {
				daysPerUnits.clear();
				sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));
				actualdaysPerUnits= getAttribute(daysPerUnits, "value");
			}
			//Validation steps-daysPerUnits- Created by keerthana
			assertEquals(actualdaysPerUnits, String.valueOf(daysUnits),"daysPerUnits from field is" 
					+actualdaysPerUnits+"not equals"+String.valueOf(daysUnits));
		}
		
		if(Objects.nonNull(diagnosispointer) && diagnosispointer.equals("")) {
			String actualdiagpointer= getAttribute(diagpointer, "value");
			if(!actualdiagpointer.equals(diagnosispointer)) {
				diagpointer.clear();
				sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);
				actualdiagpointer= getAttribute(diagpointer, "value");
			}
			assertEquals(actualdiagpointer, diagnosispointer,"diagpointer from field is " 
					+actualdiagpointer+"not equals"+ diagnosispointer);
		}
		
		if(!actualrenderingNPIType.equalsIgnoreCase("No NPI")) {
			renderingTaxonomy.clear();
			sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
			waitForLoadingToDisappear();
			putStaticWait(2);
			WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
			click(renderingtaxonomy, renderingtaxonomyCode);
			putStaticWait(2);
		}
		
		if(Objects.nonNull(cobamt) && !cobamt.equals("")) {
			
			cobbAmount.clear();
			
			sendKeys(cobbAmount, "COB Amount", cobamt);
			
			String actualcobamt = getAttribute(cobbAmount, "value");
			assertEquals(actualcobamt, cobamt, "The Value from feild is "+actualcobamt+" not "+
			cobamt);
			
		}
		
		if(Objects.nonNull(coballowableamt) && !coballowableamt.equals("")) {
			
			cobAllowableAmount.clear();
			
			sendKeys(cobAllowableAmount, "COB Amount", coballowableamt);
			
			String actualcobamt = getAttribute(cobAllowableAmount, "value");
			assertEquals(actualcobamt, coballowableamt, "The Value from feild is "+actualcobamt+" not "+
					coballowableamt);
			
		}
		
		if(Objects.nonNull(cobreason) && !cobreason.equals("")) {
			
			click(cobReason,"COB reason dropdown");
			
			driver.findElement(By.xpath("//span[contains(text(),"
					+ "'"+cobreason+"')]/parent::mat-option")).click();
			
		}
		
		if(Objects.nonNull(epdst) && !epdst.equals("")) {
			String actualepdst = getAttribute(inputEPDST, "aria-checked");
			if(actualepdst.equals("true") && epdst.equalsIgnoreCase("NO")) {
				click(checkboxEPDST,"EPDST");
				actualepdst = getAttribute(inputEPDST, "aria-checked");
				String asserttext = actualepdst.equals("false")?"EPDST checkbox unchecked"
						:"EPDST checkbox not unchecked";
				System.out.println(asserttext);
			}
			if(actualepdst.equals("false") && epdst.equalsIgnoreCase("YES")) {
				click(checkboxEPDST,"EPDST");
				actualepdst = getAttribute(inputEPDST, "aria-checked");
				String asserttext = actualepdst.equals("true")?"EPDST checkbox checked"
						:"EPDST checkbox not checked";
				System.out.println(asserttext);
			}
		}
		
		click(saveService, "Save Service");
		putStaticWait(2);
		String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
		System.out.println(alerttext);
		waitForLoadingToDisappear();
		if(alerttext.contains("Saved successfully")) {
			report(LogStatus.PASS, "Service added Successfully");
			flag = true;
		}else {
			report(LogStatus.FAIL,alerttext);
			try {
				throw new Exception("Cannot add service");
			}catch(Exception e) {
				report(LogStatus.FAIL, e.getMessage());
				e.printStackTrace();
				click(cancelService, "Cancel");
				putStaticWait(1);
				waitUntilClickable(unsavedChagesOK, 2);
				click(unsavedChagesOK, "OK");
				
			}
		}
		return flag;
		
	}
	
	//Created by Nandhalala
	public String createAndSaveClaim() {
			
			String patientid = dataMap.get("patientid");
			String patientRelationshipToInsured = dataMap.get("PatientRelationshipToInsured");
			String otherInsurdLastName = dataMap.get("otherInsuredLastName");
			String otherInsurdFirstName = dataMap.get("otherInsuredFirstName");
			String otherInsurdMiddleName = dataMap.get("otherInsuredMiddleName");
			String otherInsurdPolicyNumber = dataMap.get("otherInsuredPolicyNumber");
			String otherInsurdProgramName = dataMap.get("othernsuredProgramName");
			String emplymentPlaceState = dataMap.get("employmentPlaceState");
			String clmCodes = dataMap.get("claimCodes");
			String otherclmId = dataMap.get("otherClaimID");
			String insurancePlanProgramName = dataMap.get("insurancePlanOrProgramName");
			String isThereAnotherbenefitPLN = dataMap.get("isThereAnotherBenefitPlan");
			String sign = dataMap.get("sign");
			String insurdOrAuthorizedPersonsSignature = dataMap.get("Insured'sOrAuthorizedPerson'sSignature");
			String form14dt = dataMap.get("form14Date");
			String form14qual = dataMap.get("form14Qual");
			String form15dt = dataMap.get("form15Date");
			String form15qual = dataMap.get("form15Qual");
			String patabsentfrmdt = dataMap.get("patientAbsentFromDate");
			String patabsenttodt = dataMap.get("patientAbsentToDate");
			String form17qual1 = dataMap.get("form17Qualifier1");
			String form17qual2 = dataMap.get("form17Qualifier2");
			String form17a = dataMap.get("form17a");
			String form17b = dataMap.get("form17b");
			String hospitalizedfrdt = dataMap.get("hospitalizedFromDate");
			String hospitalizedtodt = dataMap.get("hospitalizedToDate");
			String form19ClaimInformation = dataMap.get("additionalClaimInformation");
			String outsideLab = dataMap.get("outsideLabRadiobutton");
			String outsidelabchrgs = dataMap.get("outsideLabCharges");
			String patientSigndate = dataMap.get("patientSigndate");
			String diagnosisCode = dataMap.get("diagnosisCode");
			String resubmissionCode = dataMap.get("resubmissionCode");
			String originalClaimHeaderID = dataMap.get("originalReferenceNumber");
			String authNumber = dataMap.get("priorAuthorizationNumber");
			String providerID = dataMap.get("providerID");
			String siteid = dataMap.get("siteID");
			String serviceFromDate = dataMap.get("serviceFromDate");
			String serviceToDate = dataMap.get("serviceToDate"); 
			String POS = dataMap.get("POS");
			String renderingNPIType = dataMap.get("renderingNPItype");
			String renderingNPI = dataMap.get("renderingNPI");
			String procCode = dataMap.get("procCode");
			String mod1 = dataMap.get("modifier1");
			String mod2 = dataMap.get("modifier2");
			String mod3 = dataMap.get("modifier3");
			String mod4 = dataMap.get("modifier4");
			String serviceAmt = dataMap.get("serviceAmt");
			String daysUnits = dataMap.get("dayunits");
			String diagnosispointer = dataMap.get("diagnosispointer");
			String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");
			String patientaccnumber = dataMap.get("patientAccountNumber");
			String acceptassignment = dataMap.get("acceptAssignment");
			String physiciansigndate = dataMap.get("physiciansigndate");
			String receivedDate = dataMap.get("receivedDate");
			String siteAddress = dataMap.get("siteAddress");
			String siteState = dataMap.get("siteState");
			String siteCity = dataMap.get("siteCity");
			String siteZip = dataMap.get("SiteZip");
			String sitephno = dataMap.get("sitephno");
			String sitephNPI = dataMap.get("siteNPI");
			String physicianlastname = dataMap.get("physicianLastName");	
			String physicianfirstname = dataMap.get("physicianFirstName");	
			String physicianmiddleinitial = dataMap.get("physicianMiddleInitial");	
			String physicianaddress = dataMap.get("physicianAddress");	
			String physicianstate = dataMap.get("physicianState");	
			String physiciancity = dataMap.get("physicianCity");	
			String physicianzip = dataMap.get("physicianZip");	
			String physicianphone = dataMap.get("physicianPhone");	
			String physiciannpi = dataMap.get("physicianNPI");
			String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");
			String cobamt = dataMap.get("cobAmount");
			String coballowableamt = dataMap.get("cobAllowableAmount");
			String cobReason = dataMap.get("cobReason");
			String epdst = dataMap.get("epdst");

			String MyMCSNumber = "";
			waitUntilClickable(createButton, 30);
			putStaticWait(2);
			click(createButton, "Create button");
			waitForLoadingToDisappear();
			waitUntilClickable(patientSearchButton, 30);
			putStaticWait(2);
			click(patientSearchButton, "Patient Search button");
			if(patientID.isDisplayed()) {
				sendKeys(patientID, "Patient ID", patientid);
				String actualpatid = getAttribute(patientID, "ng-reflect-model");
				assertEquals(actualpatid, patientid, "Patient Id from text field is "+actualpatid+ 
						" not "+patientid);
			}
			click(searchPatient, "Search");
			waitForLoadingToDisappear();
			click(driver.findElement(By.xpath("//tbody/tr/td[1]")), 
					"Patient ID : "+patientid);
			
			click(selectPatientButton, "Select Patient");
			waitForLoadingToDisappear();
			putStaticWait(5);
			
			if(Objects.nonNull(patientRelationshipToInsured) && !patientRelationshipToInsured.equals("")) {
				switch (patientRelationshipToInsured) {
				case "SELF":
					click(form6RadioButtonSelf, "Patient Relationship To Insured - SELF");
					break;
				
				case "SPOUSE":
					click(form6RadioButtonSpouse, "Patient Relationship To Insured - SPOUSE");
					break;
					
				case "OTHERS":
					click(form6RadioButtonOters, "Patient Relationship To Insured - OTHERS");
					break;

				default:
					break;
				}
			}
			
			if(Objects.nonNull(otherInsurdLastName) && !otherInsurdLastName.equals("")) {
				sendKeys(otherInsuredLastname, "Other Insured's Last Name", otherInsurdLastName);
				String actualInsurdLastName = getAttribute(otherInsuredLastname, "value");
				assertEquals(actualInsurdLastName, otherInsurdLastName,"OtherInsured Last Name "
					+ "from text field is "+actualInsurdLastName+" not "+otherInsurdLastName);
				}
			
			if(Objects.nonNull(otherInsurdFirstName)&&!otherInsurdFirstName.equals("")) {
				sendKeys(otherInsuredFirstname, "Other Insured's First Name", otherInsurdFirstName);
				String actualInsurdFirstName = getAttribute(otherInsuredFirstname, "value");
				assertEquals(actualInsurdFirstName, otherInsurdFirstName,"OtherInsured First Name "
					+ "from text field is "+actualInsurdFirstName+" not "+otherInsurdFirstName);
			}
			
			if(Objects.nonNull(otherInsurdMiddleName)&&!otherInsurdMiddleName.equals("")) {
				sendKeys(otherInsuredMiddlename, "Other Insured's Middle Name", otherInsurdMiddleName);
				String actualInsurdMiddleName = getAttribute(otherInsuredMiddlename, "value");
				assertEquals(actualInsurdMiddleName, otherInsurdMiddleName,"OtherInsured Middle Name "
					+ "from text field is "+actualInsurdMiddleName+" not "+otherInsurdMiddleName);
			}
			
			if(Objects.nonNull(otherInsurdPolicyNumber) && !otherInsurdPolicyNumber.equals("")) {
				sendKeys(otherInsuredPolicyNumber, "Other Insured's Policy Number", otherInsurdPolicyNumber);
				String actualInsurdPolicyNumber = getAttribute(otherInsuredPolicyNumber, "value");
				assertEquals(actualInsurdPolicyNumber, otherInsurdPolicyNumber,"OtherInsured Policy number "
					+ "from text field is "+actualInsurdPolicyNumber+" not "+otherInsurdPolicyNumber);
			}
			
			if(Objects.nonNull(otherInsurdProgramName) && !otherInsurdProgramName.equals("")) {
				sendKeys(oiName, "Other Insurance Name", otherInsurdProgramName);
				String actualOIName = getAttribute(oiName, "value");
				assertEquals(actualOIName, otherInsurdProgramName,"Other Insured Policy Name from feild is "+
						actualOIName+" not "+otherInsurdProgramName);
			}
			
			if(Objects.nonNull(emplymentPlaceState) && !emplymentPlaceState.equals("")) {
				sendKeys(eploymentPlaceState, "10.Place State", emplymentPlaceState);
				waitForLoadingToDisappear();
				WebElement state = driver.findElement(By.xpath("//span[@class = 'mat-option-text' and contains(text(),'"
						+emplymentPlaceState+"')]"));
				waitForLoadingToDisappear();
				click(state, "Place State");
				waitForLoadingToDisappear();
			}
			
			click(employmentStatusUnknown, "Unknown");
			
			click(autoAccientUnknown, "Unknown");
			
			click(otherAccientUnknown, "Unknown");
			
			if(Objects.nonNull(clmCodes) && !clmCodes.equals("")) {
				sendKeys(claimCodes, "Claim Codes", clmCodes);
				String actualClmCodes = getAttribute(claimCodes, "value");
				assertEquals(actualClmCodes, clmCodes,"Claim Codes from field is "+actualClmCodes+" not "+
						clmCodes);
			}
			
			if(Objects.nonNull(otherclmId)&&!otherclmId.equals("")) {
				sendKeys(otherClaimID, "Other Claim ID ", otherclmId);
				String actualOtherClaimId = getAttribute(otherClaimID, "value");
				assertEquals(actualOtherClaimId, otherclmId, "Other Claim ID from field is "
				+actualOtherClaimId+" not "+otherclmId);
			}
			
			if(Objects.nonNull(insurancePlanProgramName) && !insurancePlanProgramName.equals("")) {
				sendKeys(insuranceName, "Insurance Plan or Program Name feild", 
						insurancePlanProgramName);
				String actualinsurancePlanProgramName = getAttribute(insuranceName, "value");
				assertEquals(actualinsurancePlanProgramName, insurancePlanProgramName,
						"Insurance plan from field is "+actualinsurancePlanProgramName+" not "+insurancePlanProgramName);
			}
			
			if(Objects.nonNull(isThereAnotherbenefitPLN) && !isThereAnotherbenefitPLN.equals("")) {
				if(isThereAnotherbenefitPLN.equalsIgnoreCase("YES"))
					click(isThereAnotherBenefitPlanYES, "IS THERE ANOTHER BENEFIT PLAN - YES");
				else if(isThereAnotherbenefitPLN.equalsIgnoreCase("NO"))
					click(isThereAnotherBenefitPlanNO, "IS THERE ANOTHER BENEFIT PLAN - NO");
				else
					report(LogStatus.WARNING,
							"No effective value found for Is There Another Beh=nefit Plan");
			}
			
			String signvalue = getText(signDropDownValue);
			
			//System.out.println(signvalue);
			
			//System.out.println(signvalue.charAt(0));
			
			char signchar = signvalue.charAt(0);
			
			//System.out.println(sign);
			
			if(Objects.nonNull(sign) && !sign.equals("")) {
				if(!(signchar==sign.charAt(0))) {
					click(signDropDown, "Sign Dropdown");
					switch (sign.charAt(0)) {
					case 'A':
						click(form12SignOptionA, " A ");
						break;
					case 'I':
						click(form12SignOptionI, " I ");
						break;
					case 'M':
						click(form12SignOptionM, " M ");
						break;
					case 'N':
						click(form12SignOptionN, " N ");
						break;
					case 'O':
						click(form12SignOptionO, " O ");
						break;
					case 'Y':
						click(form12SignOptionY, " Y ");
						break;
					default:
						break;
					}
				}
			}
			
			
			
			
			sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
			putStaticWait(2);
			
			String actualPatientSignDate = getAttribute(patientSignDate, "value");
			assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
			+actualPatientSignDate+" not equals "+ patientSigndate);
			
			String form13SignValue = getAttribute(form13SignCheckBox, "aria-checked");
			//System.out.println(form13SignValue);
			
			if(Objects.nonNull(insurdOrAuthorizedPersonsSignature) && 
					!insurdOrAuthorizedPersonsSignature.equals("")) {
				
				if(form13SignValue.equalsIgnoreCase("TRUE") &&
						insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("NO")) {
					click(form13Sign, "Form 13 check box");
				}
				
				if(form13SignValue.equalsIgnoreCase("FALSE") &&
						insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("YES")) {
					click(form13Sign, "Form 13 check box");
				}
				
			}
			
			if (Objects.nonNull(form14dt)&&!form14dt.equals("")) {
				sendKeys(form14Date, "Form 14 Date", form14dt);
				
				String actualForm14Date = getAttribute(form14Date, "value");
				assertEquals(actualForm14Date, form14dt, "Date from field is "+actualForm14Date+" not "+
				form14dt);
			}
			
			if (Objects.nonNull(form14qual)&&!form14dt.equals("")) {
				sendKeys(form14Qual, "Form 14 QUAL", form14qual);
				
				String actualForm14QUAL = getAttribute(form14Qual, "value");
				assertEquals(actualForm14QUAL, form14qual, "Date from field is "+actualForm14QUAL+" not "+
				form14qual);
			}
			
			if (Objects.nonNull(form15dt)&&!form15dt.equals("")) {
				sendKeys(form15Date, "Form 15 Date", form15dt);
				
				String actualForm15Date = getAttribute(form15Date, "value");
				assertEquals(actualForm15Date, form15dt, "Date from field is "+actualForm15Date+" not "+
				form15dt);
			}
			
			if (Objects.nonNull(form15qual)&&!form15dt.equals("")) {
				sendKeys(form15Qual, "Form 15 QUAL", form15qual);
				
				String actualForm15QUAL = getAttribute(form15Qual, "value");
				assertEquals(actualForm15QUAL, form15qual, "Date from field is "+actualForm15QUAL+" not "+
				form15qual);
			}
			
			if(Objects.nonNull(patabsentfrmdt) && !patabsentfrmdt.equals("")) {
				sendKeys(patientAbsentFromDate, "Patient Unable To Work in Current Occupation from date", 
						patabsentfrmdt);
				
				String actualPatAbsFrmDt  = getAttribute(patientAbsentFromDate, "value");
				assertEquals(actualPatAbsFrmDt, patabsentfrmdt,"'Patient absent from date' from field is"
				+actualPatAbsFrmDt+" not "+patabsentfrmdt);
				
			}
			
			if(Objects.nonNull(patabsenttodt) && !patabsenttodt.equals("")) {
				sendKeys(patientAbsentToDate, "Patient Unable To Work in Current Occupation to date", 
						patabsenttodt);
				
				String actualPatAbsToDt  = getAttribute(patientAbsentToDate, "value");
				assertEquals(actualPatAbsToDt, patabsentfrmdt,"'Patient absent to date' from field is"
				+actualPatAbsToDt+" not "+patabsenttodt);
				
			}
			
			if(Objects.nonNull(form17qual1) && !form17qual1.equals("")) {
				sendKeys(form17Qual1, "Form 1 Qualifier 1", form17qual1);
				
				String actualForm17Qual1 = getAttribute(form17Qual1, "value");
				assertEquals(actualForm17Qual1, form17qual1, "Qualifier 1 from form 17 is " +actualForm17Qual1
						+" not "+form17qual1);
				
			}
			
			if(Objects.nonNull(form17qual2) && !form17qual2.equals("")) {
				sendKeys(form17Qual2, "Form 1 Qualifier 2", form17qual2);
				
				String actualForm17Qual2 = getAttribute(form17Qual2, "value");
				assertEquals(actualForm17Qual2, form17qual2, "Qualifier 2 from form 17 is " +actualForm17Qual2
						+" not "+form17qual2);
				
			}
			
			if(Objects.nonNull(form17a) && !form17a.equals("")) {
				
				sendKeys(form17A, "Form 17.a ", form17a);
				
				String actualForm17a = getAttribute(form17A, "value");
				assertEquals(actualForm17a, form17a, "Value from From 17 A is"+actualForm17a+" not "+
				form17a);
				
				String actualForm17b = getAttribute(form17B, "value");
				assertEquals(actualForm17b, form17b, "Value from From 17 B is"+actualForm17b+" not "+
				form17b);
				
			}
			
			if(Objects.nonNull(hospitalizedfrdt) && !hospitalizedfrdt.equals("")) {
				
				sendKeys(form18HospitalizedFromDate, "Form 18 Hospitalized from date", hospitalizedfrdt);
				
				String actualHospitalizedFromDate = getAttribute(form18HospitalizedFromDate, "value");
				assertEquals(actualHospitalizedFromDate, hospitalizedfrdt, "Value from Hospitalized From "
						+ "date field is "+actualHospitalizedFromDate+" not "+hospitalizedfrdt);
				
			}
			
			if(Objects.nonNull(hospitalizedtodt) && !hospitalizedtodt.equals("")) {
				
				sendKeys(form18HospitalizedToDate, "Form 18 Hospitalized to date", hospitalizedtodt);
				
				String actualHospitalizedToDate = getAttribute(form18HospitalizedToDate, "value");
				assertEquals(actualHospitalizedToDate, hospitalizedtodt, "Value from Hospitalized To "
						+ "date field is "+actualHospitalizedToDate+" not "+hospitalizedtodt);
				
			}
			
			if(Objects.nonNull(form19ClaimInformation) && !form19ClaimInformation.equals("")) {
				
				sendKeys(additionalClaimInformation, "Additional Claim Information", form19ClaimInformation);
				
				String actualAdditionalClaimInformation = getAttribute(additionalClaimInformation, "value");
				assertEquals(actualAdditionalClaimInformation, form19ClaimInformation, "Value from "
						+ "Additional Claim Information field is "+actualAdditionalClaimInformation+" not "+form19ClaimInformation);
				
			}
			
			if(Objects.nonNull(outsideLab) && !outsideLab.equals("")) {
				switch (outsideLab) {
				case "YES":
					click(form20RadioButtonYes, "Outside Lab - YES");
					break;
					
				case "NO":
					click(form20RadioButtonNO, "Outside Lab - NO");
					break;
					
				case "UNKNOWN":
					click(form20RadioButtonUnknown, "Outside Lab - UNKNOWN");
					break;

				default:
					break;
				}
			}
			
			
			if(Objects.nonNull(outsidelabchrgs) && !outsidelabchrgs.equals("")) {
				
				form20Charges.clear();
				
				sendKeys(form20Charges, "Outside Lab Charges", outsidelabchrgs);
				
				String actualOutsideLabCharges = getAttribute(form20Charges, "value");
				assertEquals(actualOutsideLabCharges, outsidelabchrgs, "The value from field "
						+ "outside lab charges is "+actualOutsideLabCharges+" not "+outsidelabchrgs);
				
			}
			
			
			sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
			waitForLoadingToDisappear();
			
			String actualDiagnosis1 = getAttribute(diagnosis1, "value");
			
			assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "+actualDiagnosis1+
					" not equals "+diagnosisCode);
			
			putStaticWait(1);
			
			//form 22 Resubmission
			if(Objects.nonNull(resubmissionCode)&&Objects.nonNull(originalClaimHeaderID)&&
					!resubmissionCode.equals("") && !originalClaimHeaderID.equals("")) {
				
				click(resubmissionCodeDropDown, "Resubmission Code");
				
				if(resubmissionCode.equalsIgnoreCase("Replacement")) {
					click(replacementOption,"Replacement");
				}else if(resubmissionCode.equalsIgnoreCase("Void")) {
					click(voidOption,"Void");
				}else {
					report(LogStatus.WARNING, "Invalid Option");
				}
				
				sendKeys(originalReferenceNumber, "Original Reference Claim Header ID", originalClaimHeaderID);
				
				String actualoriginalClaimHeaderID= getAttribute(originalReferenceNumber, "value");
				assertEquals(actualoriginalClaimHeaderID, originalClaimHeaderID,"The value from Original "
						+ "refernce number feild is "+actualoriginalClaimHeaderID+" not "+originalClaimHeaderID);
				
			}
			
			//form 23 prior authorization
			if(Objects.nonNull(authNumber) && !authNumber.equals("")) {
				
				sendKeys(priorAuthorizationNumber, "Prior Authorization Number", authNumber);
				
				String actualAuthNumber = getAttribute(priorAuthorizationNumber, "value");
				assertEquals(actualAuthNumber, authNumber, "The value from feild is "+actualAuthNumber+
						" not "+authNumber);
				
			}
			
			click(providerSearchButton, "Provider Search");
			waitForLoadingToDisappear();
			putStaticWait(1);
			sendKeys(providerId, "Provider ID", providerID);
			String actualproviderId = getAttribute(providerId, "value");

			assertEquals(actualproviderId,providerID,"Provider Id from field is "

			+actualproviderId+" not equals "+ providerID);
			click(searchProvider, "Search");
			waitForLoadingToDisappear();
			putStaticWait(2);
			driver.findElement(By.xpath("//td[contains(text(),'"+providerID+"')]")).click();
			waitForLoadingToDisappear();
			putStaticWait(2);
			click(selectProvider, "Select Provider");
			waitForLoadingToDisappear();
			putStaticWait(1);
			
			click(selectSiteDropdown, "Site selection");
			waitUntilClickable(unsavedChagesOK, 2);
			click(unsavedChagesOK, "OK");
			click(driver.findElement(By.xpath("//span[contains(text(),'"+siteid+"')]"
					+ "/parent::mat-option")), siteid);
			waitForLoadingToDisappear();
			
			addService(serviceFromDate, serviceToDate, POS, renderingNPIType, renderingNPI,procCode, mod1, mod2,mod3, mod4,serviceAmt, 
					daysUnits, diagnosispointer, renderingtaxonomyCode,cobamt,coballowableamt,cobReason,
					epdst);
			
			
//			click(addService, "Add service");
//			waitForLoadingToDisappear();
//			putStaticWait(1);
//			
//			sendKeys(fromDate, "From Date", serviceFromDate);
//			
//			//Validation Steps-fromDate created by keerthana
//			String actualFromDate = getAttribute(fromDate, "value");
//			assertEquals(actualFromDate,serviceFromDate,"From Date from field is "+actualFromDate+" not equals "+ serviceFromDate);
//			
//			sendKeys(toDate, " TO Date", serviceToDate);
//			
//			//Validation Steps- toDate- Created by keerthana
//			String actualToDate= getAttribute(toDate, "value");
//			assertEquals(actualToDate,serviceToDate,"To Date from field is" +actualToDate+" not equals "+ serviceToDate);
//			
//			click(selectPOS, "Place Of Service");
//			sendKeys(inputPOS, "Place Of service", POS);
//			putStaticWait(2);
//			driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();
//			sendKeys(serviceCode, "Service Code", procCode);
//			
//			//Validation Steps-Service Code- Created by keerthana
//			String actualServiceCode = getAttribute(serviceCode, "value");
//			assertEquals(actualServiceCode,procCode,"Service Code from Field is"+actualServiceCode+"not equals"+ procCode);
//			
//			sendKeys(charges, "Charges", String.valueOf(serviceAmt));
//			
//			//Validation Steps-Charges- Created by keerthana
//			String actualCharges= getAttribute(charges, "value");
//			assertEquals(actualCharges,String.valueOf(serviceAmt),"Charges from field is" +actualCharges+"not equals"+ String.valueOf(serviceAmt) ); 
//			waitForLoadingToDisappear();
//			
//			sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));
//			
//			//Validation steps-daysPerUnits- Created by keerthana
//			String actualdaysPerUnits= getAttribute(daysPerUnits, "value");
//			assertEquals(actualdaysPerUnits, String.valueOf(daysUnits),"daysPerUnits from field is" +actualdaysPerUnits+"not equals"+String.valueOf(daysUnits));
//			
//			sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);
//			
//			//Validation steps- diagpointer-Created by keerthana
//			String actualdiagpointer= getAttribute(diagpointer, "value");
//			assertEquals(actualdiagpointer, diagnosispointer,"diagpointer from field is " +actualdiagpointer+"not equals"+ diagnosispointer);
//			
//			sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
//			waitForLoadingToDisappear();
//			putStaticWait(2);
//			WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
//			click(renderingtaxonomy, renderingtaxonomyCode);
//			putStaticWait(2);
//			click(saveService, "Save Service");
//			putStaticWait(2);
			
			click(driver.findElement(By.xpath("//*[@role='alertdialog']")), "Saved successfully");
			
			//form 26 Patient Account Number
			if(Objects.nonNull(patientaccnumber) && !patientaccnumber.equals("")) {
				
				sendKeys(patientAccountNumber, "Patient Account Number", patientaccnumber);
				
				String actualAccountNumber = getAttribute(patientAccountNumber, "value");
				assertEquals(actualAccountNumber, patientaccnumber, "The value from feild is "
				+actualAccountNumber+" not "+patientaccnumber);
				
			}
			
			//form 27 Accept Assignment
			if(Objects.nonNull(acceptassignment) && !acceptassignment.equals("")) {
				switch (acceptassignment) {
				case "YES":
					click(acceptAssignmentYes, "Accept Assignment - YES");
					break;
				case "NO":
					click(acceptAssignmentNo, "Accept Assignment - No");
					break;

				default:
					report(LogStatus.WARNING, "Accept Assignment value not valid");
					break;
				}
			}
			
			
			//form 31 Physician Signature
			click(physicianSignCheckBox, "Physician Sign Check Box");
			
			sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
			
			//Validation Steps- physicianSignDate- Created by keerthana
			String actualphysicianSignDate= getAttribute(physicianSignDate,"value");
			assertEquals(actualphysicianSignDate,physiciansigndate,"physicianSignDate from field is "+actualphysicianSignDate+"not equals"+ physiciansigndate );
			
			if(Objects.nonNull(receivedDate) && !receivedDate.equals("")) {
				sendKeys(mcoReceivedDate, "Date Received by MCO", receivedDate);
				
				String actualReceivedDate = getAttribute(mcoReceivedDate, "value");
				assertEquals(actualReceivedDate, receivedDate,"The value from Date Received by MCO "
						+ "field is "+actualReceivedDate+" not "+receivedDate);
				
			}
			
			
			//Form 32
			if(Objects.nonNull(siteAddress) && !siteAddress.equals("")) {
				
				facilityAddress.clear();
				
				sendKeys(facilityAddress, "Site Address ", siteAddress);
				
				String actualsiteAddress = getAttribute(facilityAddress, "value");
				assertEquals(actualsiteAddress, siteAddress,"The value from Site Address fild is "
						+actualsiteAddress+" not "+siteAddress);
			}
			
			if(Objects.nonNull(siteState) && !siteState.equals("")) {
				
				facilityState.clear();
				
				sendKeys(facilityState, "Site State", siteState);
				
				String actualsitestate = getAttribute(facilityState, "value");
				assertEquals(actualsitestate, siteState,"The value from Site State fild is "
						+actualsitestate+" not "+siteState);
			}
			
			if(Objects.nonNull(siteCity) && !siteCity.equals("")) {
				
				facilityCity.clear();
				
				sendKeys(facilityCity, "Site City", siteCity);
				
				String actualsitecity = getAttribute(facilityCity, "value");
				assertEquals(actualsitecity, siteCity,"The value from Site City fild is "
						+actualsitecity+" not "+siteCity);
			}
			
			if(Objects.nonNull(siteZip) && !siteZip.equals("")) {
				
				facilityZip.clear();
				
				sendKeys(facilityZip, "Site ZIP", siteZip);
				
				String actualsitezip = getAttribute(facilityZip, "value");
				assertEquals(actualsitezip, siteZip,"The value from Site ZIP fild is "
						+actualsitezip+" not "+siteZip);
			}
			
			String actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
			
			if(Objects.nonNull(actualsitePhoneNumber) && !actualsitePhoneNumber.equals("")) {
				sitePhoneNumber.clear();
			}
			
			sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
			
			//Validation steps- sitephonenumber- Created by keerthana
			actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
			assertEquals(actualsitePhoneNumber,sitephno, "sitePhoneNumber from field is" +actualsitePhoneNumber+"not equals"+sitePhoneNumber );
			
			
			if(Objects.nonNull(sitephNPI) && !sitephNPI.equals("")) {
				facilityNPI.clear();
				sendKeys(facilityNPI, "Site NPI", sitephNPI);
				
				String actualsitenpi = getAttribute(facilityNPI, "value");
				assertEquals(actualsitenpi, sitephNPI,"The value from Received date fild is "
						+actualsitenpi+" not "+sitephNPI);
			}
			
			
			if(Objects.nonNull(physicianfirstname) && !physicianfirstname.equals("")) {
				physicianFirstName.clear();
				sendKeys(physicianFirstName, "Physician First Name", physicianfirstname);
				
				String actualphysicianfirstname = getAttribute(physicianFirstName, "value");
				assertEquals(actualphysicianfirstname, physicianfirstname,"The value from Received date fild is "
						+actualphysicianfirstname+" not "+physicianfirstname);
			}
			
			if(Objects.nonNull(physicianlastname) && !physicianlastname.equals("")) {
				physicianLastName.clear();
				sendKeys(physicianLastName, "Physician First Name", physicianlastname);
				
				String actualphysicianlastname = getAttribute(physicianLastName, "value");
				assertEquals(actualphysicianlastname, physicianlastname,"The value from Received date fild is "
						+actualphysicianlastname+" not "+physicianlastname);
			}
			
			if(Objects.nonNull(physicianmiddleinitial) && !physicianmiddleinitial.equals("")) {
				physicianMiddleInitial.clear();
				sendKeys(physicianMiddleInitial, "Physician First Name", physicianmiddleinitial);
				
				String actualphysicianmiddleinitial = getAttribute(physicianMiddleInitial, "value");
				assertEquals(actualphysicianmiddleinitial, physicianmiddleinitial,"The value from Received date fild is "
						+actualphysicianmiddleinitial+" not "+physicianmiddleinitial);
			}
			
			
			if(Objects.nonNull(physicianaddress) && !physicianaddress.equals("")) {
				physicianAddress.clear();
				sendKeys(physicianAddress, "Physician First Name", physicianaddress);
				
				String actualphysicianaddress = getAttribute(physicianAddress, "value");
				assertEquals(actualphysicianaddress, physicianaddress,"The value from Received date fild is "
						+actualphysicianaddress+" not "+physicianaddress);
			}
			
			if(Objects.nonNull(physicianstate) && !physicianstate.equals("")) {
				physicianState.clear();
				sendKeys(physicianState, "Physician First Name", physicianstate);
				
				String actualphysicianstate = getAttribute(physicianAddress, "value");
				assertEquals(actualphysicianstate, physicianstate,"The value from Received date fild is "
						+actualphysicianstate+" not "+physicianstate);
			}
			
			if(Objects.nonNull(physiciancity) && !physiciancity.equals("")) {
				physicianCity.clear();
				sendKeys(physicianCity, "Physician First Name", physiciancity);
				
				String actualphysiciancity = getAttribute(physicianCity, "value");
				assertEquals(actualphysiciancity, physiciancity,"The value from Received date fild is "
						+actualphysiciancity+" not "+physiciancity);
			}
			
			if(Objects.nonNull(physicianzip) && !physicianzip.equals("")) {
				physicianZip.clear();
				sendKeys(physicianZip, "Physician First Name", physicianzip);
				
				String actualphysicianzip = getAttribute(physicianZip, "value");
				assertEquals(actualphysicianzip, physicianzip,"The value from Received date fild is "
						+actualphysicianzip+" not "+physicianzip);
			}
			
			if(Objects.nonNull(physicianphone) && !physicianphone.equals("")) {
				physicianPhone.clear();
				sendKeys(physicianPhone, "Physician First Name", physicianphone);
				
				String actualphysicianphone = getAttribute(physicianPhone, "value");
				assertEquals(actualphysicianphone, physicianphone,"The value from Received date fild is "
						+actualphysicianphone+" not "+physicianphone);
			}
			
			if(Objects.nonNull(physiciannpi) && !physiciannpi.equals("")) {
				physicianNPI.clear();
				sendKeys(physicianNPI, "Physician First Name", physiciannpi);
				
				String actualphysiciannpi = getAttribute(physicianNPI, "value");
				assertEquals(actualphysiciannpi, physiciannpi,"The value from Received date fild is "
						+actualphysiciannpi+" not "+physiciannpi);
			}
			
			sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
			waitForLoadingToDisappear();
			WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
			click(billingtaxonmy, billingTaxonomyCode);
			waitForLoadingToDisappear();
			MyMCSNumber = getText(mymcsclaim);
			click(saveCMS, "Submit button");
			
			putStaticWait(2);
			
			//String alerttext = getText(driver.findElement(By.xpath("//*[@role='alertdialog']")));
			String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
			System.out.println(alerttext);
			
			waitForLoadingToDisappear();
			
			
			if(alerttext.contains("Success")) {
				report(LogStatus.PASS, "Saved submitted");
				return MyMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
				try {
					throw new CannotCreateClaimException("Not able to submit the claim due to error"
				+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
			
			//return MyMCSNumber;
		}
	
	
	//created by Mugundhan
	public String updateAndSaveCMS1500Claim () {

		String myMCSNumber = dataMap.get("myMCSNumber");
		String patientSigndate = dataMap.get("patientSigndate");
		String diagnosisCode = dataMap.get("diagnosisCode");
		//String providerID = dataMap.get("providerID");
		String serviceFromDate = dataMap.get("serviceFromDate");
		String serviceToDate = dataMap.get("serviceToDate"); 
		String POS = dataMap.get("POS");
		String procCode = dataMap.get("procCode");
		String serviceAmt = dataMap.get("serviceAmt");
		String daysUnits = dataMap.get("dayunits");
		String diagnosispointer = dataMap.get("diagnosispointer");
		String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");
		String physiciansigndate = dataMap.get("physiciansigndate");
		String sitephno = dataMap.get("sitephno");
		String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");

	        click(filterButton, "Filter Button");

	        waitUntilClickable(filtermyMCSClaimNO, 30);

	        sendKeys(filtermyMCSClaimNO, "My MCS Claim # ", myMCSNumber);

	        putStaticWait(2);

	        click(filterSearchButton, "Filter Search");

	        putStaticWait(2);

	        click(expandFirstCMSRecord,"EXPAND CMS");

	        putStaticWait(2);

	        if(!updateCMS.isEnabled()) {
	        	report(LogStatus.FAIL, "Update button is disabled");
	        }
	        
	        click(updateCMS,"Update CMS");

	        waitForLoadingToDisappear();

	        String updatepatsigndate=getAttribute(physicianSignDate, "value");

	        System.out.println(updatepatsigndate);

	        System.out.println(dataMap);

	        System.out.println(patientSigndate);

	        if(!updatepatsigndate.equals(patientSigndate))	{
	        	
	        	patientSignDate.clear();

	            sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);    

	        }

	        String updatediagnosisCode=getAttribute(diagnosis1, "value");

	        System.out.println(updatediagnosisCode);

	        if(!diagnosisCode.contains(updatediagnosisCode)) {
	        	
	        	diagnosis1.clear();

	            sendKeys(diagnosis1, "diagnosis code", diagnosisCode);    

	        }
	        
	        waitUntilClickable(firstServiceLine, 120);

	        click(firstServiceLine,"First service line");

	        putStaticWait(2);

	        click(lineModify,"service line Modify");

	        putStaticWait(2);

	        String updatefromdate=getAttribute(fromDate,"value");

	        System.out.println(updatefromdate);

	        if(!updatefromdate.equals(serviceFromDate))	{
	        	
	        	fromDate.clear();

	            sendKeys(fromDate, "From Date", serviceFromDate);    

	        }

	        String updatetodate=getAttribute(toDate,"value");

	        if(!updatetodate.equals(serviceToDate))	{
	        	
	        	toDate.clear();

	            sendKeys(toDate, "To Date", serviceToDate);    

	        }

	        String updatePOS=getText(modifyPOS);
	        
	        System.out.println(updatePOS);

	        System.out.println(POS);
	        
	        if(!updatePOS.contains(POS))	{
	        	
	        	inputPOS.clear();
	        	
	        	waitUntilClickable(selectPOS, 120);

	            click(selectPOS, "Place Of Service");

	            sendKeys(inputPOS, "Place Of service", POS);
	            
	            putStaticWait(30);
	            
	            driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();

	        }

	        String updateProc=getAttribute(serviceCode,"value");

	        if(!updateProc.equals(procCode)){
	        	
	        	serviceCode.clear();

	            sendKeys(serviceCode, "Service Code", procCode);

	        }

	        String updatecharges=getAttribute(charges,"value");

	        if(!updatecharges.equals(serviceAmt))	{

	        	charges.clear();
	        	
	            sendKeys(charges, "Charges", String.valueOf(serviceAmt));    

	        }

	        String updateunit=getAttribute(daysPerUnits,"value");

	        if(!updateunit.equals(daysUnits))	{

	        	daysPerUnits.clear();
	        	
	            sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));

	        }

	        String updatediagPointer=getAttribute(diagpointer,"value");

	        if(!updatediagPointer.equals(diagnosispointer))	{
	        	
	        	diagpointer.clear();

	        	sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);

	        }

	        String updatetaxonomy=getAttribute(renderingTaxonomy,"value");

	        if(!updatetaxonomy.contains(renderingtaxonomyCode))	{
	        	
	        	renderingTaxonomy.clear();

	        	sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);

	        	waitForLoadingToDisappear();

	        	putStaticWait(2);

	        	WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));

	        	click(renderingtaxonomy, renderingtaxonomyCode);

	        }

	        putStaticWait(2);

	        click(saveService, "Save Service");

	        String updatephySign = getAttribute(physicianSignDate,"value");

	        if(!updatephySign.equals(physiciansigndate))	{
	        	
	        	physicianSignDate.clear();

	        	sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);

	        }


	        String updatephoneno =getAttribute(sitePhoneNumber,"value");

	        if(!updatephoneno.equals(sitephno))	{
	        	
	        	sitePhoneNumber.clear();

	        	sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);

	        }

	        String updatebilltax =getAttribute(billingTaxonomy,"value");

	        if(!updatebilltax.contains(billingTaxonomyCode))  {

	        	billingTaxonomy.clear();
	        
	        	sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);

	        }

	        click(saveCMS,"Save Claim");

	        return myMCSNumber;

	    }
	    
	    
	//created by Mugundhan
	public String updateAndSubmitCMS1500Claim () {


		String myMCSNumber = dataMap.get("myMCSNumber");
		String patientid = dataMap.get("patientid");
		String patientRelationshipToInsured = dataMap.get("PatientRelationshipToInsured");
		String otherInsurdLastName = dataMap.get("otherInsuredLastName");
		String otherInsurdFirstName = dataMap.get("otherInsuredFirstName");
		String otherInsurdMiddleName = dataMap.get("otherInsuredMiddleName");
		String otherInsurdPolicyNumber = dataMap.get("otherInsuredPolicyNumber");
		String otherInsurdProgramName = dataMap.get("othernsuredProgramName");
		String employmentStatus = dataMap.get("employmentStatus");
		String autoAccident = dataMap.get("autoAccident");
		String emplymentPlaceState = dataMap.get("employmentPlaceState");
		String otherAccident = dataMap.get("otherAcciedent");
		String clmCodes = dataMap.get("claimCodes");
		String otherclmId = dataMap.get("otherClaimID");
		String insurancePlanProgramName = dataMap.get("insurancePlanOrProgramName");
		String isThereAnotherbenefitPLN = dataMap.get("isThereAnotherBenefitPlan");
		String sign = dataMap.get("sign");
		String insurdOrAuthorizedPersonsSignature = dataMap.get("Insured'sOrAuthorizedPerson'sSignature");
		String form14dt = dataMap.get("form14Date");
		String form14qual = dataMap.get("form14Qual");
		String form15dt = dataMap.get("form15Date");
		String form15qual = dataMap.get("form15Qual");
		String patabsentfrmdt = dataMap.get("patientAbsentFromDate");
		String patabsenttodt = dataMap.get("patientAbsentToDate");
		String form17qual1 = dataMap.get("form17Qualifier1");
		String form17qual2 = dataMap.get("form17Qualifier2");
		String form17a = dataMap.get("form17a");
		String form17b = dataMap.get("form17b");
		String hospitalizedfrdt = dataMap.get("hospitalizedFromDate");
		String hospitalizedtodt = dataMap.get("hospitalizedToDate");
		String form19ClaimInformation = dataMap.get("additionalClaimInformation");
		String outsideLab = dataMap.get("outsideLabRadiobutton");
		String outsidelabchrgs = dataMap.get("outsideLabCharges");
		String patientSigndate = dataMap.get("patientSigndate");
		String icd = dataMap.get("icdCode");
		String diagnosisCode = dataMap.get("diagnosisCode");
		String diag2 = dataMap.get("diagnosis2");
		String diag3 = dataMap.get("diagnosis3");
		String diag4 = dataMap.get("diagnosis4");
		String diag5 = dataMap.get("diagnosis5");
		String diag6 = dataMap.get("diagnosis6");
		String diag7 = dataMap.get("diagnosis7");
		String diag8 = dataMap.get("diagnosis8");
		String diag9 = dataMap.get("diagnosis9");
		String diag10 = dataMap.get("diagnosis10");
		String diag11 = dataMap.get("diagnosis11");
		String diag12 = dataMap.get("diagnosis12");
		String resubmissionCode = dataMap.get("resubmissionCode");
		String originalClaimHeaderID = dataMap.get("originalReferenceNumber");
		String authNumber = dataMap.get("priorAuthorizationNumber");
		String providerID = dataMap.get("providerID");
		String siteid = dataMap.get("siteID");
		String serviceFromDate = dataMap.get("serviceFromDate");
		String serviceToDate = dataMap.get("serviceToDate"); 
		String POS = dataMap.get("POS");
		String renderingNPIType = dataMap.get("renderingNPItype");
		String renderingNPI = dataMap.get("renderingNPI");
		String procCode = dataMap.get("procCode");
		String mod1 = dataMap.get("modifier1");
		String mod2 = dataMap.get("modifier2");
		String mod3 = dataMap.get("modifier3");
		String mod4 = dataMap.get("modifier4");
		String serviceAmt = dataMap.get("serviceAmt");
		String daysUnits = dataMap.get("dayunits");
		String diagnosispointer = dataMap.get("diagnosispointer");
		String renderingtaxonomyCode = dataMap.get("renderingtaxonomyCode");
		String patientaccnumber = dataMap.get("patientAccountNumber");
		String acceptassignment = dataMap.get("acceptAssignment");
		String physiciansigndate = dataMap.get("physiciansigndate");
		String receivedDate = dataMap.get("receivedDate");
		String siteAddress = dataMap.get("siteAddress");
		String siteState = dataMap.get("siteState");
		String siteCity = dataMap.get("siteCity");
		String siteZip = dataMap.get("SiteZip");
		String sitephno = dataMap.get("sitephno");
		String sitephNPI = dataMap.get("siteNPI");
		String physicianlastname = dataMap.get("physicianLastName");	
		String physicianfirstname = dataMap.get("physicianFirstName");	
		String physicianmiddleinitial = dataMap.get("physicianMiddleInitial");	
		String physicianaddress = dataMap.get("physicianAddress");	
		String physicianstate = dataMap.get("physicianState");	
		String physiciancity = dataMap.get("physicianCity");	
		String physicianzip = dataMap.get("physicianZip");	
		String physicianphone = dataMap.get("physicianPhone");	
		String physiciannpi = dataMap.get("physicianNPI");
		String billingTaxonomyCode = dataMap.get("billingTaxonomyCode");
		String cobamt = dataMap.get("cobAmount");
		String coballowableamt = dataMap.get("cobAllowableAmount");
		String cobReason = dataMap.get("cobReason");
		String epdst = dataMap.get("epdst");
	       
	        //click filter button and search claim with MY MCS #
	        click(filterButton, "Filter Button");

	        waitUntilClickable(filtermyMCSClaimNO, 30);

	        sendKeys(filtermyMCSClaimNO, "My MCS Claim # ", myMCSNumber);

	        putStaticWait(1);

	        click(filterSearchButton, "Filter Search");

	        putStaticWait(1);

	        //expand first record
	        click(expandFirstCMSRecord,"EXPAND CMS");

	        putStaticWait(2);

	        //Verify if update button is enabled, if enabled update the data else throw an exception
	        if(!updateCMS.isEnabled()) {
	        	report(LogStatus.FAIL, "Update button is disabled. Not able to update the calim");
	        	try {
	        		throw new Exception("Update button disabled. Cannot update record.");
	        	}catch(Exception e) {
	        		e.printStackTrace();
	        	}
	        	return "Not able to update the calim";
	        	
	        }else {
	        
	        click(updateCMS,"Update CMS");
	        
	        waitForLoadingToDisappear();
	        
	    //form 6
		if(Objects.nonNull(patientRelationshipToInsured) && !patientRelationshipToInsured.equals("")) {
			switch (patientRelationshipToInsured) {
			case "SELF":
				click(form6RadioButtonSelf, "Patient Relationship To Insured - SELF");
				break;
				
			case "SPOUSE":
				click(form6RadioButtonSpouse, "Patient Relationship To Insured - SPOUSE");
				break;
					
			case "OTHERS":
				click(form6RadioButtonOters, "Patient Relationship To Insured - OTHERS");
				break;

			default:
				report(LogStatus.WARNING, "Accept Assignment value not valid");
				break;
				}
			}
		
		//form 9
		if(Objects.nonNull(otherInsurdLastName) && !otherInsurdLastName.equals("")) {
			
			String actualInsurdLastName = getAttribute(otherInsuredLastname, "value");
			
			if(!actualInsurdLastName.equals(otherInsurdLastName)) {
				otherInsuredLastname.clear();
				sendKeys(otherInsuredLastname, "Other Insured's Last Name", otherInsurdLastName);
				actualInsurdLastName = getAttribute(otherInsuredLastname, "value");
			}
			
			assertEquals(actualInsurdLastName, otherInsurdLastName,"OtherInsured Last Name "
				+ "from text field is "+actualInsurdLastName+" not "+otherInsurdLastName);
			}
				
		if(Objects.nonNull(otherInsurdFirstName)&&!otherInsurdFirstName.equals("")) {
			
			String actualInsurdFirstName = getAttribute(otherInsuredFirstname, "value");
			
			if(!actualInsurdFirstName.equals(otherInsurdFirstName)){
				otherInsuredFirstname.clear();
				sendKeys(otherInsuredFirstname, "Other Insured's First Name", otherInsurdFirstName);
				actualInsurdFirstName = getAttribute(otherInsuredFirstname, "value");
			}
			
			assertEquals(actualInsurdFirstName, otherInsurdFirstName,"OtherInsured First Name "
				+ "from text field is "+actualInsurdFirstName+" not "+otherInsurdFirstName);
		}
				
		if(Objects.nonNull(otherInsurdMiddleName)&&!otherInsurdMiddleName.equals("")) {
			
			String actualInsurdMiddleName = getAttribute(otherInsuredMiddlename, "value");
			
			if(!actualInsurdMiddleName.equals(otherInsurdMiddleName)) {
				otherInsuredMiddlename.clear();
				sendKeys(otherInsuredMiddlename, "Other Insured's Middle Name", otherInsurdMiddleName);
				actualInsurdMiddleName = getAttribute(otherInsuredMiddlename, "value");
			}
			
			assertEquals(actualInsurdMiddleName, otherInsurdMiddleName,"OtherInsured Middle Name "
				+ "from text field is "+actualInsurdMiddleName+" not "+otherInsurdMiddleName);
		}
				
		if(Objects.nonNull(otherInsurdPolicyNumber) && !otherInsurdPolicyNumber.equals("")) {
			
			String actualInsurdPolicyNumber = getAttribute(otherInsuredPolicyNumber, "value");
			
			if(!actualInsurdPolicyNumber.equals(otherInsurdPolicyNumber)) {
				otherInsuredPolicyNumber.clear();
				sendKeys(otherInsuredPolicyNumber, "Other Insured's Policy Number", 
						otherInsurdPolicyNumber);
				actualInsurdPolicyNumber = getAttribute(otherInsuredPolicyNumber, "value");	
			}
					
			assertEquals(actualInsurdPolicyNumber, otherInsurdPolicyNumber,"OtherInsured Policy number "
				+ "from text field is "+actualInsurdPolicyNumber+" not "+otherInsurdPolicyNumber);
		}
				
		if(Objects.nonNull(otherInsurdProgramName) && !otherInsurdProgramName.equals("")) {
			
			String actualOIName = getAttribute(oiName, "value");
			
			if(!actualOIName.equals(otherInsurdProgramName)) {
				oiName.clear();
				sendKeys(oiName, "Other Insurance Name", otherInsurdProgramName);
				actualOIName = getAttribute(oiName, "value");
			}
			
			assertEquals(actualOIName, otherInsurdProgramName,"Other Insured Policy Name from feild is "+
					actualOIName+" not "+otherInsurdProgramName);
		}
		
		//form 10
		switch (employmentStatus) {
		case "YES":
			click(employmentStatusYES, "Unknown");			
			break;
		case "NO":
			click(employmentStatusNo, "Unknown");			
			break;		
		case "UNKNOWN":
			click(employmentStatusUnknown, "Unknown");	
			break;
		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
				
		switch (autoAccident) {
		case "YES":
			click(autoAccientYes, "Unknown");			
			break;		
		case "NO":
			click(autoAccientNo, "Unknown");			
			break;
		case "UNKNOWN":
			click(autoAccientUnknown, "Unknown");	
			break;
		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
		
		if(Objects.nonNull(emplymentPlaceState) && !emplymentPlaceState.equals("")) {
			eploymentPlaceState.clear();
			waitForLoadingToDisappear();
			sendKeys(eploymentPlaceState, "10.Place State", emplymentPlaceState);
			waitForLoadingToDisappear();
			WebElement state = driver.findElement(By.xpath("//span[@class = 'mat-option-text' and contains(text(),'"
					+emplymentPlaceState+"')]"));
			waitForLoadingToDisappear();
			click(state, "Place State");
			waitForLoadingToDisappear();
		}
		
		switch (otherAccident) {
		case "YES":
			click(otherAccientYes, "Unknown");			
			break;	
		case "NO":
			click(otherAccientNo, "Unknown");			
			break;
		case "UNKNOWN":
			click(otherAccientUnknown, "Unknown");
			break;
		default:
			report(LogStatus.WARNING, "Accept Assignment value not valid");
			break;
		}
			
		if(Objects.nonNull(clmCodes) && !clmCodes.equals("")) {
			
			String actualClmCodes = getAttribute(claimCodes, "value");
			
			if(!actualClmCodes.equals(clmCodes)) {
				claimCodes.clear();
				sendKeys(claimCodes, "Claim Codes", clmCodes);
				actualClmCodes = getAttribute(claimCodes, "value");
			}

			assertEquals(actualClmCodes, clmCodes,"Claim Codes from field is "+actualClmCodes+" not "+
					clmCodes);
		}
		
		//form 11
		if(Objects.nonNull(otherclmId)&&!otherclmId.equals("")) {
			
			String actualOtherClaimId = getAttribute(otherClaimID, "value");
			
			if(!actualOtherClaimId.equals(otherclmId)) {
				otherClaimID.clear();
				sendKeys(otherClaimID, "Other Claim ID ", otherclmId);
				actualOtherClaimId = getAttribute(otherClaimID, "value");
			}
			
			assertEquals(actualOtherClaimId, otherclmId, "Other Claim ID from field is "
					+actualOtherClaimId+" not "+otherclmId);
		}
		
		if(Objects.nonNull(insurancePlanProgramName) && !insurancePlanProgramName.equals("")) {
			
			String actualinsurancePlanProgramName = getAttribute(insuranceName, "value");
			
			if(!actualinsurancePlanProgramName.equals(insurancePlanProgramName)) {
				insuranceName.clear();
				sendKeys(insuranceName, "Insurance Plan or Program Name feild", 
						insurancePlanProgramName);
				actualinsurancePlanProgramName = getAttribute(insuranceName, "value");
			}
			
			assertEquals(actualinsurancePlanProgramName, insurancePlanProgramName,
					"Insurance plan from field is "+actualinsurancePlanProgramName+" not "+
							insurancePlanProgramName);
		}
		
		if(Objects.nonNull(isThereAnotherbenefitPLN) && !isThereAnotherbenefitPLN.equals("")) {
			if(isThereAnotherbenefitPLN.equalsIgnoreCase("YES"))
				click(isThereAnotherBenefitPlanYES, "IS THERE ANOTHER BENEFIT PLAN - YES");
			else if(isThereAnotherbenefitPLN.equalsIgnoreCase("NO"))
				click(isThereAnotherBenefitPlanNO, "IS THERE ANOTHER BENEFIT PLAN - NO");
			else
				report(LogStatus.WARNING,
						"No effective value found for Is There Another Beh=nefit Plan");
		}

		//form 12
		String signvalue = getText(signDropDownValue);
				
		char signchar = signvalue.charAt(0);
				
		if(Objects.nonNull(sign) && !sign.equals("")) {
			if(!(signchar==sign.charAt(0))) {
				click(signDropDown, "Sign Dropdown");
				switch (sign.charAt(0)) {
				case 'A':
					click(form12SignOptionA, " A ");
					break;
				case 'I':
					click(form12SignOptionI, " I ");
					break;
				case 'M':
					click(form12SignOptionM, " M ");
					break;
				case 'N':
					click(form12SignOptionN, " N ");
					break;
				case 'O':
					click(form12SignOptionO, " O ");
					break;
				case 'Y':
					click(form12SignOptionY, " Y ");
					break;
				default:
					report(LogStatus.WARNING, "Accept Assignment value not valid");
					break;
				}
			}
		}
		
		String actualPatientSignDate = getAttribute(patientSignDate, "value");
		if(actualPatientSignDate.equals(patientSigndate)) {
			patientSignDate.clear();
			sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
			actualPatientSignDate = getAttribute(patientSignDate, "value");
		}
		
		assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
				+actualPatientSignDate+" not equals "+ patientSigndate);

		//form 13
		String form13SignValue = getAttribute(form13SignCheckBox, "aria-checked");
		if(Objects.nonNull(insurdOrAuthorizedPersonsSignature) && 
				!insurdOrAuthorizedPersonsSignature.equals("")) {
			if(form13SignValue.equalsIgnoreCase("TRUE") &&
					insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("NO")) {
				click(form13Sign, "Form 13 check box");
			}
			if(form13SignValue.equalsIgnoreCase("FALSE") &&
					insurdOrAuthorizedPersonsSignature.equalsIgnoreCase("YES")) {
				click(form13Sign, "Form 13 check box");
			}
		}

		//form 14
		if (Objects.nonNull(form14qual)&&!form14dt.equals("")) {
			String actualForm14QUAL = getAttribute(form14Qual, "value");
			if(!actualForm14QUAL.equals(form14qual)) {
				form14Qual.clear();
				sendKeys(form14Qual, "Form 14 QUAL", form14qual);
				actualForm14QUAL = getAttribute(form14Qual, "value");
			}
			assertEquals(actualForm14QUAL, form14qual, "Date from field is "+actualForm14QUAL+" not "+
			form14qual);
		}
		if (Objects.nonNull(form14dt)&&!form14dt.equals("")) {
			String actualForm14Date = getAttribute(form14Date, "value");
			if(!actualForm14Date.equals(form14dt)) {
				form14Date.clear();
				sendKeys(form14Date, "Form 14 Date", form14dt);
				actualForm14Date = getAttribute(form14Date, "value");
			}
			assertEquals(actualForm14Date, form14dt, "Date from field is "+actualForm14Date+" not "+
			form14dt);
		}
		
		//form 15		
		if (Objects.nonNull(form15qual)&&!form15dt.equals("")) {
			String actualForm15QUAL = getAttribute(form15Qual, "value");
			if(!actualForm15QUAL.equals(form15qual)){
				form15Qual.clear();
				sendKeys(form15Qual, "Form 15 QUAL", form15qual);
				actualForm15QUAL = getAttribute(form15Qual, "value");
			}
			assertEquals(actualForm15QUAL, form15qual, "Date from field is "+actualForm15QUAL+" not "+
			form15qual);
		}
		
		if (Objects.nonNull(form15dt)&&!form15dt.equals("")) {
			String actualForm15Date = getAttribute(form15Date, "value");
			if(!actualForm15Date.equals(form15dt)) {
				form15Date.clear();
				sendKeys(form15Date, "Form 15 Date", form15dt);
				actualForm15Date = getAttribute(form15Date, "value");
			}
			assertEquals(actualForm15Date, form15dt, "Date from field is "+actualForm15Date+" not "+
			form15dt);
		}
		
		//form 16
		if(Objects.nonNull(patabsentfrmdt) && !patabsentfrmdt.equals("")) {	
			String actualPatAbsFrmDt  = getAttribute(patientAbsentFromDate, "value");
			if(!actualPatAbsFrmDt.equals(patabsentfrmdt)) {
				patientAbsentFromDate.clear();
				sendKeys(patientAbsentFromDate, "Patient Unable To Work in Current Occupation from "
						+ "date", patabsentfrmdt);
				actualPatAbsFrmDt  = getAttribute(patientAbsentFromDate, "value");
			}
			assertEquals(actualPatAbsFrmDt, patabsentfrmdt,"'Patient absent from date' from field is"
			+actualPatAbsFrmDt+" not "+patabsentfrmdt);
			
		}
		if(Objects.nonNull(patabsenttodt) && !patabsenttodt.equals("")) {
			String actualPatAbsToDt  = getAttribute(patientAbsentToDate, "value");
			if(!actualPatAbsToDt.equals(patabsenttodt)) {
				patientAbsentToDate.clear();
				sendKeys(patientAbsentToDate, "Patient Unable To Work in Current Occupation to date", 
						patabsenttodt);
				actualPatAbsToDt  = getAttribute(patientAbsentToDate, "value");
			}
			assertEquals(actualPatAbsToDt, patabsentfrmdt,"'Patient absent to date' from field is"
			+actualPatAbsToDt+" not "+patabsenttodt);	
		}
		
		//form 17
		if(Objects.nonNull(form17qual1) && !form17qual1.equals("")) {
			String actualForm17Qual1 = getAttribute(form17Qual1, "value");
			if(!actualForm17Qual1.equals(form17qual1)) {
				form17Qual1.clear();
				sendKeys(form17Qual1, "Form 17 Qualifier 1", form17qual1);
				actualForm17Qual1 = getAttribute(form17Qual1, "value");
			}
			assertEquals(actualForm17Qual1, form17qual1, "Qualifier 1 from form 17 is " +actualForm17Qual1
					+" not "+form17qual1);
			
		}
		
		if(Objects.nonNull(form17qual2) && !form17qual2.equals("")) {
			String actualForm17Qual2 = getAttribute(form17Qual2, "value");
			if(!actualForm17Qual2.equals(form17qual2)) {
				form17Qual2.clear();
				sendKeys(form17Qual2, "Form 17 Qualifier 2", form17qual2);
				actualForm17Qual2 = getAttribute(form17Qual1, "value");
			}
			assertEquals(actualForm17Qual2, form17qual2, "Qualifier 2 from form 17 is " 
			+actualForm17Qual2+" not "+form17qual2);
			
		}
		
		if(Objects.nonNull(form17a) && !form17a.equals("")) {
			String actualForm17a = getAttribute(form17A, "value");
			String actualForm17b = getAttribute(form17B, "value");
			if(actualForm17a.equals(actualForm17a)){
				form17A.clear();
				sendKeys(form17A, "Form 17.a ", form17a);
				actualForm17a = getAttribute(form17A, "value");
				actualForm17b = getAttribute(form17B, "value");
			}
			
			assertEquals(actualForm17a, form17a, "Value from From 17 A is"+actualForm17a+" not "+
					form17a);
			assertEquals(actualForm17b, form17b, "Value from From 17 B is"+actualForm17b+" not "+
			form17b);
		}
		
		//form 18
		if(Objects.nonNull(hospitalizedfrdt) && !hospitalizedfrdt.equals("")) {
			String actualHospitalizedFromDate = getAttribute(form18HospitalizedFromDate, "value");
			if(!actualHospitalizedFromDate.equals(hospitalizedfrdt)) {
				form18HospitalizedFromDate.clear();
				sendKeys(form18HospitalizedFromDate, "Form 18 Hospitalized from date", 
						hospitalizedfrdt);
				actualHospitalizedFromDate = getAttribute(form18HospitalizedFromDate, "value");
			}
			assertEquals(actualHospitalizedFromDate, hospitalizedfrdt, "Value from Hospitalized From "
					+ "date field is "+actualHospitalizedFromDate+" not "+hospitalizedfrdt);
			
		}
		
		if(Objects.nonNull(hospitalizedtodt) && !hospitalizedtodt.equals("")) {

			String actualHospitalizedToDate = getAttribute(form18HospitalizedToDate, "value");
			if(!actualHospitalizedToDate.equals(hospitalizedtodt)) {
				form18HospitalizedToDate.clear();
				sendKeys(form18HospitalizedToDate, "Form 18 Hospitalized to date", 
						hospitalizedtodt);
				actualHospitalizedToDate = getAttribute(form18HospitalizedToDate, "value");
			}
			assertEquals(actualHospitalizedToDate, hospitalizedtodt, "Value from Hospitalized To "
					+ "date field is "+actualHospitalizedToDate+" not "+hospitalizedtodt);	
		}
		
		//form 19
		if(Objects.nonNull(form19ClaimInformation) && !form19ClaimInformation.equals("")) {
			String actualAdditionalClaimInformation = getAttribute(additionalClaimInformation, 
					"value");
			if(!actualAdditionalClaimInformation.equals(form19ClaimInformation)) {
				additionalClaimInformation.clear();
				sendKeys(additionalClaimInformation, "Additional Claim Information", 
						form19ClaimInformation);
				 actualAdditionalClaimInformation = getAttribute(additionalClaimInformation, "value");
			}
			assertEquals(actualAdditionalClaimInformation, form19ClaimInformation, "Value from "
					+ "Additional Claim Information field is "+actualAdditionalClaimInformation
					+" not "+form19ClaimInformation);
			
		}
		
		//form 20
		if(Objects.nonNull(outsideLab) && !outsideLab.equals("")) {
			switch (outsideLab) {
			case "YES":
				click(form20RadioButtonYes, "Outside Lab - YES");
				break;
				
			case "NO":
				click(form20RadioButtonNO, "Outside Lab - NO");
				break;
				
			case "UNKNOWN":
				click(form20RadioButtonUnknown, "Outside Lab - UNKNOWN");
				break;

			default:
				report(LogStatus.WARNING, "Accept Assignment value not valid");
				break;
			}
		}
		
		
		if(Objects.nonNull(outsidelabchrgs) && !outsidelabchrgs.equals("")) {
			String actualOutsideLabCharges = getAttribute(form20Charges, "value");
			if(!actualOutsideLabCharges.equals(outsidelabchrgs)) {
				form20Charges.clear();
				sendKeys(form20Charges, "Outside Lab Charges", outsidelabchrgs);
				actualOutsideLabCharges = getAttribute(form20Charges, "value");
			}
			assertEquals(actualOutsideLabCharges, outsidelabchrgs, "The value from field "
					+ "outside lab charges is "+actualOutsideLabCharges+" not "+outsidelabchrgs);
			
		}
		
		//form 21
		String actualICD10 = getAttribute(icd10CheckBox, "class");
		String actualICD9 = getAttribute(icd9CheckBox, "class");
		
		switch (icd) {
		case "ICD9":
			if( !actualICD9.contains("mat-checkbox-checked"))
			click(icd9CheckBox,"ICD 9");
			break;
		case "ICD10":
			if( !actualICD10.contains("mat-checkbox-checked"))
			click(icd10CheckBox,"ICD 10");
			break;

		default:
			report(LogStatus.WARNING,"Not a valid icd code");
			break;
		}
				if(Objects.nonNull(diagnosisCode) && !diagnosisCode.equals("")) {
					diagnosis1.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
					waitForLoadingToDisappear();
					String actualDiagnosis1 = getAttribute(diagnosis1, "value");
					assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "
							+actualDiagnosis1+" not equals "+diagnosisCode);
					putStaticWait(1);
				}
				
				
				if(Objects.nonNull(diag2) && !diag2.equals("")) {
					diagnosis2.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis2, "Diagnosis 2", diag2);
					waitForLoadingToDisappear();
					String actualDiagnosis2 = getAttribute(diagnosis2, "value");
					assertEquals(actualDiagnosis2, diag2, "Diagnosis 2 from field is "
							+actualDiagnosis2+" not equals "+diag2);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag3) && !diag3.equals("")) {
					diagnosis3.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis3, "Diagnosis 3", diag3);
					waitForLoadingToDisappear();
					String actualDiagnosis3 = getAttribute(diagnosis3, "value");
					assertEquals(actualDiagnosis3, diag3, "Diagnosis 3 from field is "
							+actualDiagnosis3+" not equals "+diag3);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag4) && !diag4.equals("")) {
					diagnosis4.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis4, "Diagnosis 4", diag4);
					waitForLoadingToDisappear();
					String actualDiagnosis4 = getAttribute(diagnosis4, "value");
					assertEquals(actualDiagnosis4, diag4, "Diagnosis 4 from field is "
							+actualDiagnosis4+" not equals "+diag4);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag5) && !diag5.equals("")) {
					diagnosis5.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis5, "Diagnosis 5", diag5);
					waitForLoadingToDisappear();
					String actualDiagnosis5 = getAttribute(diagnosis5, "value");
					assertEquals(actualDiagnosis5, diag5, "Diagnosis 5 from field is "
							+actualDiagnosis5+" not equals "+diag5);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag6) && !diag6.equals("")) {
					diagnosis6.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis6, "Diagnosis 6", diag6);
					waitForLoadingToDisappear();
					String actualDiagnosis6 = getAttribute(diagnosis6, "value");
					assertEquals(actualDiagnosis6, diag6, "Diagnosis 6 from field is "
							+actualDiagnosis6+" not equals "+diag6);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag7) && !diag7.equals("")) {
					diagnosis7.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis7, "Diagnosis 7", diag7);
					waitForLoadingToDisappear();
					String actualDiagnosis7 = getAttribute(diagnosis7, "value");
					assertEquals(actualDiagnosis7, diag7, "Diagnosis 7 from field is "
							+actualDiagnosis7+" not equals "+diag7);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag8) && !diag8.equals("")) {
					diagnosis8.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis8, "Diagnosis 8", diag8);
					waitForLoadingToDisappear();
					String actualDiagnosis8 = getAttribute(diagnosis8, "value");
					assertEquals(actualDiagnosis8, diag8, "Diagnosis 8 from field is "
							+actualDiagnosis8+" not equals "+diag8);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag9) && !diag9.equals("")) {
					diagnosis9.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis9, "Diagnosis 2", diag9);
					waitForLoadingToDisappear();
					String actualDiagnosis9 = getAttribute(diagnosis9, "value");
					assertEquals(actualDiagnosis9, diag9, "Diagnosis 9 from field is "
							+actualDiagnosis9+" not equals "+diag9);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag10) && !diag10.equals("")) {
					diagnosis10.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis10, "Diagnosis 10", diag10);
					waitForLoadingToDisappear();
					String actualDiagnosis10 = getAttribute(diagnosis10, "value");
					assertEquals(actualDiagnosis10, diag10, "Diagnosis 10 from field is "
							+actualDiagnosis10+" not equals "+diag10);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag11) && !diag11.equals("")) {
					diagnosis11.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis11, "Diagnosis 11", diag11);
					waitForLoadingToDisappear();
					String actualDiagnosis11 = getAttribute(diagnosis11, "value");
					assertEquals(actualDiagnosis11, diag11, "Diagnosis 2 from field is "
							+actualDiagnosis11+" not equals "+diag11);
					putStaticWait(1);
				}
				
				if(Objects.nonNull(diag12) && !diag12.equals("")) {
					diagnosis12.clear();
					waitForLoadingToDisappear();
					sendKeys(diagnosis12, "Diagnosis 12", diag12);
					waitForLoadingToDisappear();
					String actualDiagnosis12 = getAttribute(diagnosis2, "value");
					assertEquals(actualDiagnosis12, diag12, "Diagnosis 12 from field is "
							+actualDiagnosis12+" not equals "+diag12);
					putStaticWait(1);
				}
	        
				
				//form 22 Resubmission
				if(Objects.nonNull(resubmissionCode)&&Objects.nonNull(originalClaimHeaderID)&&
						!resubmissionCode.equals("") && !originalClaimHeaderID.equals("")) {
					
					click(resubmissionCodeDropDown, "Resubmission Code");
					
					if(resubmissionCode.equalsIgnoreCase("Replacement")) {
						click(replacementOption,"Replacement");
					}else if(resubmissionCode.equalsIgnoreCase("Void")) {
						click(voidOption,"Void");
					}else {
						report(LogStatus.WARNING, "Invalid Option");
					}
					
					
					String actualoriginalClaimHeaderID= getAttribute(originalReferenceNumber, 
							"value");
					if(!actualoriginalClaimHeaderID.equals(originalClaimHeaderID)) {
						originalReferenceNumber.clear();
						sendKeys(originalReferenceNumber, "Original Reference Claim Header ID", 
								originalClaimHeaderID);	
						actualoriginalClaimHeaderID= getAttribute(originalReferenceNumber, "value");
					}
					assertEquals(actualoriginalClaimHeaderID, originalClaimHeaderID,
							"The value from Original refernce number feild is "
									+actualoriginalClaimHeaderID+" not "+originalClaimHeaderID);
				}
				
				//form 23 prior authorization
				if(Objects.nonNull(authNumber) && !authNumber.equals("")) {
					String actualAuthNumber = getAttribute(priorAuthorizationNumber, "value");
					if(!actualAuthNumber.equals(authNumber)) {
						priorAuthorizationNumber.clear();
						sendKeys(priorAuthorizationNumber, "Prior Authorization Number", authNumber);
						actualAuthNumber = getAttribute(priorAuthorizationNumber, "value");
					}
					assertEquals(actualAuthNumber, authNumber, "The value from feild is "
					+actualAuthNumber+" not "+authNumber);
				}
				
				WebElement sitevalue = driver.findElement(By.
						xpath("//mat-select[@formcontrolname='Site_id']/div/div/span/span"));
				String siteno = getText(sitevalue);
				siteno = siteno.replaceAll("[^0-9]", "");
				
				if(Objects.nonNull(siteid) && !siteid.equals("")) {
					if(!siteid.equals(siteno)) {
						click(selectSiteDropdown, "Site selection");
						waitUntilClickable(unsavedChagesOK, 2);
						click(unsavedChagesOK, "OK");
						try {
							click(driver.findElement(By.xpath("//span[contains(text(),'"+siteid+"')]"
									+ "/parent::mat-option")), siteid);
							waitForLoadingToDisappear();
						}catch(NoSuchElementException e) {
							e.printStackTrace();
						}
						
					}
				}
				
	        waitUntilClickable(firstServiceLine, 120);

	        click(firstServiceLine,"First service line");

	        putStaticWait(2);
	        
	        modifyService(serviceFromDate, serviceToDate, POS, renderingNPIType, renderingNPI, 
	        		procCode, mod1, mod2,  mod3, mod4, serviceAmt, daysUnits, diagnosispointer, 
	        		renderingtaxonomyCode, cobamt, coballowableamt, cobReason, epdst);

//	        click(lineModify,"service line Modify");
//
//	        putStaticWait(2);
//
//	        String updatefromdate=getAttribute(fromDate,"value");
//
//	        System.out.println(updatefromdate);
//
//	        if(!updatefromdate.equals(serviceFromDate))	{
//	        	
//	        	fromDate.clear();
//
//	            sendKeys(fromDate, "From Date", serviceFromDate);    
//
//	        }
//
//	        String updatetodate=getAttribute(toDate,"value");
//
//	        if(!updatetodate.equals(serviceToDate))	{
//	        	
//	        	toDate.clear();
//
//	            sendKeys(toDate, "To Date", serviceToDate);    
//
//	        }
//
//	        String updatePOS=getText(modifyPOS);
//	        
//	        System.out.println(updatePOS);
//
//	        System.out.println(POS);
//	        
//	        if(!updatePOS.contains(POS))	{
//	        	
//	        	inputPOS.clear();
//	        	
//	        	waitUntilClickable(selectPOS, 120);
//
//	            click(selectPOS, "Place Of Service");
//
//	            sendKeys(inputPOS, "Place Of service", POS);
//	            
//	            putStaticWait(30);
//	            
//	            driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();
//
//	        }
//
//	        String updateProc=getAttribute(serviceCode,"value");
//
//	        if(!updateProc.equals(procCode)){
//	        	
//	        	serviceCode.clear();
//
//	            sendKeys(serviceCode, "Service Code", procCode);
//
//	        }
//
//	        String updatecharges=getAttribute(charges,"value");
//
//	        if(!updatecharges.equals(serviceAmt))	{
//
//	        	charges.clear();
//	        	
//	            sendKeys(charges, "Charges", String.valueOf(serviceAmt));    
//
//	        }
//
//	        String updateunit=getAttribute(daysPerUnits,"value");
//
//	        if(!updateunit.equals(daysUnits))	{
//	        	
//	        	daysPerUnits.clear();
//
//	            sendKeys(daysPerUnits, "Days/Units", String.valueOf(daysUnits));
//
//	        }
//
//	        String updatediagPointer=getAttribute(diagpointer,"value");
//
//	        if(!updatediagPointer.equals(diagnosispointer))	{
//	        	
//	        	diagpointer.clear();
//
//	        	sendKeys(diagpointer, "Diagnosis Pointer", diagnosispointer);
//
//	        }
//
//	        String updatetaxonomy=getAttribute(renderingTaxonomy,"value");
//
//	        if(!updatetaxonomy.contains(renderingtaxonomyCode))	{
//	        	
//	        	renderingTaxonomy.clear();
//
//	        
//	        	sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
//
//	        
//	        	waitForLoadingToDisappear();
//
//	        
//	        	putStaticWait(2);
//
//	        
//	        	WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
//
//	        
//	        	click(renderingtaxonomy, renderingtaxonomyCode);
//
//	        }
//
//	        putStaticWait(2);
//
//	        click(saveService, "Save Service");

	      //form 26 Patient Account Number
			if(Objects.nonNull(patientaccnumber) && !patientaccnumber.equals("")) {
				
				String actualAccountNumber = getAttribute(patientAccountNumber, "value");
				if(Objects.nonNull(patientaccnumber) && !patientaccnumber.equals("")) {
					patientAccountNumber.clear();
					sendKeys(patientAccountNumber, "Patient Account Number", patientaccnumber);
					actualAccountNumber = getAttribute(patientAccountNumber, "value");
				}
				assertEquals(actualAccountNumber, patientaccnumber, "The value from feild is "
				+actualAccountNumber+" not "+patientaccnumber);
				
			}
			
			//form 27 Accept Assignment
			if(Objects.nonNull(acceptassignment) && !acceptassignment.equals("")) {
				switch (acceptassignment) {
				case "YES":
					click(acceptAssignmentYes, "Accept Assignment - YES");
					break;
				case "NO":
					click(acceptAssignmentNo, "Accept Assignment - No");
					break;

				default:
					report(LogStatus.WARNING, "Accept Assignment value not valid");
					break;
				}
			}
			
			
			//form 31 Physician Signature
			String physicianCheckboxValue = getAttribute(physicianSignCheckBox, "class");
			if(!physicianCheckboxValue.contains("mat-checkbox-checked")) {
				click(physicianSignCheckBox, "Physician Sign Check Box");
			}
			
			
			
			if(Objects.nonNull(receivedDate) && !receivedDate.equals("")) {
				String actualphysicianSignDate= getAttribute(physicianSignDate,"value");
				if(!actualphysicianSignDate.equals(receivedDate)) {
					physicianSignDate.clear();
					sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
					actualphysicianSignDate= getAttribute(physicianSignDate,"value");
				}
				
				assertEquals(actualphysicianSignDate,physiciansigndate,"physicianSignDate from field is "
						+actualphysicianSignDate+"not equals"+ physiciansigndate );
			}
			
			//Validation Steps- physicianSignDate- Created by keerthana
			
			
			
			if(Objects.nonNull(receivedDate) && !receivedDate.equals("")) {
				String actualReceivedDate = getAttribute(mcoReceivedDate, "value");
				if(!actualReceivedDate.equals(receivedDate)) {
					mcoReceivedDate.clear();
					sendKeys(mcoReceivedDate, "Date Received by MCO", receivedDate);
					actualReceivedDate = getAttribute(mcoReceivedDate, "value");
				}
				assertEquals(actualReceivedDate, receivedDate,"The value from Date Received by MCO "
						+ "field is "+actualReceivedDate+" not "+receivedDate);
				
			}
			
			//Form 32
			if(Objects.nonNull(siteAddress) && !siteAddress.equals("")) {
				facilityAddress.clear();
				
				sendKeys(facilityAddress, "Site Address ", siteAddress);
				
				String actualsiteAddress = getAttribute(facilityAddress, "value");
				assertEquals(actualsiteAddress, siteAddress,"The value from Site Address fild is "
						+actualsiteAddress+" not "+siteAddress);
			}
			
			if(Objects.nonNull(siteState) && !siteState.equals("")) {
				String actualsitestate = getAttribute(facilityState, "value");
				if(!actualsitestate.equals(siteState)) {
					facilityState.clear();
					sendKeys(facilityState, "Site State", siteState);
					actualsitestate = getAttribute(facilityState, "value");
				}
				assertEquals(actualsitestate, siteState,"The value from Site State fild is "
						+actualsitestate+" not "+siteState);
			}
			
			if(Objects.nonNull(siteCity) && !siteCity.equals("")) {
				String actualsitecity = getAttribute(facilityCity, "value");
				if(!actualsitecity.equals(siteState)) {
					facilityCity.clear();
					sendKeys(facilityCity, "Site City", siteCity);
					actualsitecity = getAttribute(facilityCity, "value");
				}
				assertEquals(actualsitecity, siteCity,"The value from Site City fild is "
						+actualsitecity+" not "+siteCity);
			}
			
			if(Objects.nonNull(siteZip) && !siteZip.equals("")) {
				String actualsitezip = getAttribute(facilityZip, "value");
				if(!actualsitezip.equals(siteState)) {
					facilityZip.clear();
					sendKeys(facilityZip, "Site ZIP", siteZip);
					actualsitezip = getAttribute(facilityZip, "value");
				}
				assertEquals(actualsitezip, siteZip,"The value from Site ZIP fild is "
						+actualsitezip+" not "+siteZip);
			}
			
			if(Objects.nonNull(sitephno) && !sitephno.equals("")) {
				String actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
				if(Objects.nonNull(actualsitePhoneNumber) && !actualsitePhoneNumber.equals("")) {
					sitePhoneNumber.clear();
					sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
					//Validation steps- sitephonenumber- Created by keerthana
					actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");	
				}
				assertEquals(actualsitePhoneNumber,sitephno, "sitePhoneNumber from field is" 
						+actualsitePhoneNumber+"not equals"+sitePhoneNumber );
			}
						
			if(Objects.nonNull(sitephNPI) && !sitephNPI.equals("")) {
				String actualsitenpi = getAttribute(facilityNPI, "value");
				if(actualsitenpi.equals(sitephNPI)) {
					facilityNPI.clear();
					sendKeys(facilityNPI, "Site NPI", sitephNPI);
					actualsitenpi = getAttribute(facilityNPI, "value");
				}		
				assertEquals(actualsitenpi, sitephNPI,"The value from Received date fild is "
						+actualsitenpi+" not "+sitephNPI);
			}
			
			
			if(Objects.nonNull(physicianfirstname) && !physicianfirstname.equals("")) {
				String actualphysicianfirstname = getAttribute(physicianFirstName, "value");
				if(!actualphysicianfirstname.equals(physicianfirstname)) {
					physicianFirstName.clear();
					sendKeys(physicianFirstName, "Physician First Name", physicianfirstname);
					actualphysicianfirstname = getAttribute(physicianFirstName, "value");
				}
				assertEquals(actualphysicianfirstname, physicianfirstname,"The value from Received date fild is "
						+actualphysicianfirstname+" not "+physicianfirstname);
			}
			
			if(Objects.nonNull(physicianlastname) && !physicianlastname.equals("")) {
				String actualphysicianlastname = getAttribute(physicianLastName, "value");
				if(!actualphysicianlastname.equals(physicianlastname)) {
					physicianLastName.clear();
					sendKeys(physicianLastName, "Physician First Name", physicianlastname);
					actualphysicianlastname = getAttribute(physicianLastName, "value");
				}
				assertEquals(actualphysicianlastname, physicianlastname,"The value from Received date fild is "
						+actualphysicianlastname+" not "+physicianlastname);
			}
			
			if(Objects.nonNull(physicianmiddleinitial) && !physicianmiddleinitial.equals("")) {
				String actualphysicianmiddleinitial = getAttribute(physicianMiddleInitial, "value");
				if(!actualphysicianmiddleinitial.equals(physicianmiddleinitial)) {
					physicianMiddleInitial.clear();
					sendKeys(physicianMiddleInitial, "Physician First Name", physicianmiddleinitial);
					actualphysicianmiddleinitial = getAttribute(physicianMiddleInitial, "value");
				}				
				assertEquals(actualphysicianmiddleinitial, physicianmiddleinitial,"The value from Received date fild is "
						+actualphysicianmiddleinitial+" not "+physicianmiddleinitial);
			}
			
			
			if(Objects.nonNull(physicianaddress) && !physicianaddress.equals("")) {
				String actualphysicianaddress = getAttribute(physicianAddress, "value");
				if(!actualphysicianaddress.equals(physicianaddress)) {
					physicianAddress.clear();
					sendKeys(physicianAddress, "Physician First Name", physicianaddress);
					actualphysicianaddress = getAttribute(physicianAddress, "value");
				}
				assertEquals(actualphysicianaddress, physicianaddress,"The value from Received date fild is "
						+actualphysicianaddress+" not "+physicianaddress);
			}
			
			if(Objects.nonNull(physicianstate) && !physicianstate.equals("")) {
				String actualphysicianstate = getAttribute(physicianAddress, "value");
				if(!actualphysicianstate.equals(physicianstate)) {
					physicianState.clear();
					sendKeys(physicianState, "Physician First Name", physicianstate);
					actualphysicianstate = getAttribute(physicianAddress, "value");
				}
				assertEquals(actualphysicianstate, physicianstate,"The value from Received date fild is "
						+actualphysicianstate+" not "+physicianstate);
			}
			
			if(Objects.nonNull(physiciancity) && !physiciancity.equals("")) {
				String actualphysiciancity = getAttribute(physicianCity, "value");
				if(!actualphysiciancity.equals(physiciancity)) {
					physicianCity.clear();
					sendKeys(physicianCity, "Physician First Name", physiciancity);
					actualphysiciancity = getAttribute(physicianCity, "value");
				}
				assertEquals(actualphysiciancity, physiciancity,"The value from Received date fild is "
						+actualphysiciancity+" not "+physiciancity);
			}
			
			if(Objects.nonNull(physicianzip) && !physicianzip.equals("")) {
				String actualphysicianzip = getAttribute(physicianZip, "value");
				if(!actualphysicianzip.equals(physicianzip)) {
					physicianZip.clear();
					sendKeys(physicianZip, "Physician First Name", physicianzip);
					actualphysicianzip = getAttribute(physicianZip, "value");
				}
				assertEquals(actualphysicianzip, physicianzip,"The value from Received date fild is "
						+actualphysicianzip+" not "+physicianzip);
			}
			
			if(Objects.nonNull(physicianphone) && !physicianphone.equals("")) {
				String actualphysicianphone = getAttribute(physicianPhone, "value");
				if(!actualphysicianphone.equals(physicianphone)) {
					physicianPhone.clear();
					sendKeys(physicianPhone, "Physician First Name", physicianphone);
					actualphysicianphone = getAttribute(physicianPhone, "value");
				}
				assertEquals(actualphysicianphone, physicianphone,"The value from Received date fild is "
						+actualphysicianphone+" not "+physicianphone);
			}
			
			if(Objects.nonNull(physiciannpi) && !physiciannpi.equals("")) {
				String actualphysiciannpi = getAttribute(physicianNPI, "value");
				if(!actualphysiciannpi.equals(physiciannpi)) {
					physicianNPI.clear();
					sendKeys(physicianNPI, "Physician First Name", physiciannpi);
					actualphysiciannpi = getAttribute(physicianNPI, "value");
				}
				assertEquals(actualphysiciannpi, physiciannpi,"The value from Received date fild is "
						+actualphysiciannpi+" not "+physiciannpi);
			}
			
			billingTaxonomy.clear();
			sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
			waitForLoadingToDisappear();
			WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
			click(billingtaxonmy, billingTaxonomyCode);
			waitForLoadingToDisappear();
			myMCSNumber = getText(mymcsclaim);
			click(submitClaim, "Submit button");
			
			putStaticWait(2);
			
			//String alerttext = getText(driver.findElement(By.xpath("//*[@role='alertdialog']")));
			String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
			System.out.println(alerttext);
			
			waitForLoadingToDisappear();
			if(alerttext.contains("Success")) {
				report(LogStatus.PASS, "Successfully submitted");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
				try {
					throw new CannotUpdateClaimException("Not able to submit the claim due to "
							+ "error : "+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
	        //return myMCSNumber;
	        }
	    }
	    

	public void viewCMS1500(int mcsNumber) {

	        String conStr = envConfig.getProperty("devDBConnectionString");

	        DBUtil dbutil = new DBUtil();

	        //System.out.println(db.executeQuery(conStr, "select * from tb_cms1500s where clm_num="+mcsNumber));

	        Map<String, String> map = dbutil.
	        		executeQuery(conStr, "select * from tb_cms1500s where clm_num="+mcsNumber);

	     
	        String patlastName=map.get("pat_ln_02");
	        String RendNPI=map.get("srvc_ren_npi_32");
	        String patFirstName=map.get("pat_fn_02");
	        String patSignDate=map.get("pat_sign_dt_12");
	        String diagCode=map.get("diag_1_21");
	        String phySignDate=map.get("phy_sign_dt_31");
	        String phoneNo=map.get("srvc_ren_ph_32");
	        //String billPrvTaxon=map.get("Bd_prv_taxonomy_id_33b");
	        
	        Map<String, String> billtax = dbutil.
	        		executeQuery(conStr, "select Taxonomy_cd from tb_taxonomy_codes where Taxonomy_id = "
	        				+map.get("Bd_prv_taxonomy_id_33b"));
	        
	        String billPrvTaxon=billtax.get("Taxonomy_cd").trim();
	        
	        billPrvTaxon = billPrvTaxon.replaceAll("[^0-9]", "");
	        
	        filterWithMCSNumber(mcsNumber);

	        waitForLoadingToDisappear();
	        
	        click(expandButton, "Expand Button");

	        waitForLoadingToDisappear();    
	        click(viewButton, "View Button");

	        waitForLoadingToDisappear();

	        String truePatientLastName = getAttribute(viewLastName, "value");
	        assertEquals(truePatientLastName,patlastName,"Patient Last Name from field is "
	        		+truePatientLastName+" not equals "+ patlastName);
	        
	        String truePatientFirstName = getAttribute(viewFirstName, "value");
	        assertEquals(truePatientFirstName,patFirstName,"Patient First Name from field is "
	        		+truePatientFirstName+" not equals "+ patFirstName);
	        String truePatientSignDate = getAttribute(patientSignDate, "value");
	        patSignDate = changeDBDateToApplicationDateFormat(patSignDate);
	        assertEquals(truePatientSignDate,patSignDate,"Patient Sign Date from field is "
	        	+truePatientSignDate+" not equals "+ patSignDate);
	        
	        String trueDiagnosisCode = getAttribute(diagnosis1, "value");
	        assertEquals(trueDiagnosisCode.trim(), diagCode.trim() ,"Diagnosis 1 from field is "
	        		+trueDiagnosisCode+" not equals "+ diagCode);
	        
	        String trueProviderSignDate = getAttribute(physicianSignDate, "value");
	        phySignDate = changeDBDateToApplicationDateFormat(phySignDate);
	        assertEquals(trueProviderSignDate, phySignDate,"Provider Sign Date from field is "
	        		+trueProviderSignDate+" not equals "+ phySignDate);	     
	        
	        String trueSitePhoneNumber = getAttribute(sitePhoneNumber, "value");
	        assertEquals(trueSitePhoneNumber, phoneNo,"Site Phone Number from field is "
	        		+trueSitePhoneNumber+" not equals "+ phoneNo);
	        
	        String trueRenderingNpi = getAttribute(renderingNpi, "value");
	        assertEquals(trueRenderingNpi, RendNPI,"Rendering Npi from field is "
	        		+trueRenderingNpi+" not equals "+ RendNPI);
	        
	        String trueBillingTaxonomyCode = getAttribute(billingTaxonomy, "value");
	        trueBillingTaxonomyCode = trueBillingTaxonomyCode.replaceAll("[^0-9]", "");
	        assertEquals(trueBillingTaxonomyCode, billPrvTaxon,"Billing Taxonomy Code from field is "
	        		+trueBillingTaxonomyCode+" not equals "+ billPrvTaxon);
	        }  
	    
}

