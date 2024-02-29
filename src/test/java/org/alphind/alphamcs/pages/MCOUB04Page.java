package org.alphind.alphamcs.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.alphind.alphamcs.base.CommonFunctions;
import org.alphind.alphamcs.exception.CannotCreateClaimException;
import org.alphind.alphamcs.util.DBUtil;
import org.alphind.alphamcs.util.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
	private final String dropdownOptions = "//span[@class = 'mat-option-text' "
			+ "and contains(text(),'XX')]";
	
	//Table Elements
	@FindBy(xpath = "(//table)[5]/tbody/tr[1]")
	private WebElement firstRecord;
	
	//Grid Elements
	@FindBy(xpath = "//span[text()=' View ']/parent::button")
	private WebElement viewButton;
	
	@FindBy(xpath = "//span[text()=' Update ']/parent::button")
	private WebElement updateButton;
	
	@FindBy(xpath = "//span[text()=' Copy ']/parent::button")
	private WebElement CopyButton;
	
	@FindBy(xpath = "//span[text()=' Details ']/parent::button")
	private WebElement detailsButton;
	
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
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	private WebElement filterSearchButon;
		
	//View UB-04 elements
	@FindBy(xpath = "//span[text()='View UB-04']")
	private WebElement viewUB04Heading;
	
	//Create, Update, Copy Elements
	@FindBy(xpath = "//mat-label[contains(text(),'My MCS Claim #')]/parent::div")
	private WebElement myMCSClaimnumber;
	
	@FindBy(xpath = "//span[contains(text(),'Create UB-04')]")
	private WebElement createUB04Heading;
	
	@FindBy(xpath = "//span[contains(text(),'Update UB-04')]")
	private WebElement updateUB04Heading;
	
	@FindBy(xpath = "//span[contains(text(),'Create UB-04')]")
	private WebElement copyUB04Heading;
	
	//Form 1 elements
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button")
	private WebElement providerSearchButton;
	
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button/"
			+ "parent::div/following-sibling::div/mat-form-field/div/div/div/input")
	private WebElement providerIDText;
	
	//provider Search elements
	@FindBy(xpath = "//mat-label[text()='Provider ID']/parent::label/"
			+ "parent::span/preceding-sibling::input")
	private WebElement providerSeachProviderID;
	
	@FindBy(xpath = "//mat-label[text()='Provider Name']/parent::label/"
			+ "parent::span/preceding-sibling::input")
	private WebElement providerSeachProviderName;
	
	@FindBy(xpath = "(//mat-select)[12]")
	private WebElement providerSearchStatusDropdown;
	
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
	
	//Form 2 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Site_nm_01']")
	private WebElement siteSelectionDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Site_nm_01']/div/div/span/span")
	private WebElement siteIDText;
	
	@FindBy(xpath = "//input[@formcontrolname='Site_id']")
	private WebElement siteIDText2;
	
	//Form 3 elements
	@FindBy(xpath = "//mat-label[text()='3a. Pat. CNTL #']/following-sibling::"
			+ "mat-form-field/div/div/div/input")
	private WebElement patientControlNumber;
	
	//Form 4
	@FindBy(xpath = "//input[@formcontrolname = 'Bill_type_04']")
	private WebElement billType;
	
	//Form 6 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Stmt_prd_frm_06']")
	private WebElement statementFromPeriod;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Stmt_prd_to_06']")
	private WebElement statementToPeriod;

	//Form 8 elements
	// patient Search elements
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_id']")
	private WebElement patientIDText;
	
	@FindBy(xpath = "(//span[contains(text(),'Search')])[2]/parent::button")
	private WebElement patientSearchButton;
	
	@FindBy(xpath = "//span[contains(text(),'Clear')]/parent::button")
	private WebElement patientClear;
	
	@FindBy(xpath = "//span[text()='Patient Search']")
	private WebElement patientSearchHeading;
	
	@FindBy(xpath = "//span[text()='Patient Search']/following-sibling::button")
	private WebElement patientSearchClose;
	
	@FindBy(xpath = "//mat-label[text()='Patient ID']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchPatientID;
	
	@FindBy(xpath = "//mat-label[text()='Last Name']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchLastName;
	
	@FindBy(xpath = "//mat-label[text()='First Name']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchFirstName;
	
	@FindBy(xpath = "//mat-label[text()='Insurance #']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchInsuranceNumber;
	
	@FindBy(xpath = "//mat-label[text()='SSN']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchSSN;
	
	@FindBy(xpath = "//mat-label[text()='MRN #']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchMRNNumber;
	
	@FindBy(xpath = "//mat-label[text()='Birth Date']/parent::label/parent::span/"
			+ "preceding-sibling::input")
	private WebElement patientSearchBirthDate;
	
	@FindBy(xpath = "(//mat-select)[12]")
	private WebElement patientSearchActiveStatus;
	
	@FindBy(xpath = "//span[text()='Search']/parent::button")
	private WebElement patientSearchSearchButton;
	
	@FindBy(xpath = "//span[text()='Select Patient']/parent::button")
	private WebElement patientSearchSelectPatientButton;
	
	@FindBy(xpath = "//span[text()='Cancel']/parent::button")
	private WebElement patientSearchCancelButton;
	
	private String patientSearchGridRow = "(//tbody)[9]/tr[XX]";
	
	//Form 12 elements
	@FindBy(xpath = "//input[@formcontrolname='Admsn_dt_12']")
	private WebElement admissionDate;
	
	//Form 13 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Admsn_hr_13']")
	private WebElement admissionHourDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Admsn_hr_13']/div/div/span/span")
	private WebElement admissionHourText;
	
	//Form 14 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Priority_of_visit_type_14']")
	private WebElement visitTypeDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Priority_of_visit_type_14']/div/div/span/span")
	private WebElement visitTypeText;
	
	//Form 15 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Ref_src_15']")
	private WebElement referenceSourceDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Ref_src_15']/div/div/span/span")
	private WebElement referenceSourceText;
	
	//Form 16 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Disch_hr_16']")
	private WebElement dischargeHour;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Disch_hr_16']/div/div/span/span")
	private WebElement dischargeHourText;
	
	//Form 17 elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Disch_stat_17']")
	private WebElement dischargeStatus;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Disch_stat_17']/div/div/span/span")
	private WebElement dischargeStatusText;
	
	//Form 18 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_18']")
	private WebElement form18;
	
	//Form 19 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_19']")
	private WebElement form19;
	
	//Form 20 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_20']")
	private WebElement form20;
	
	//Form 21 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_21']")
	private WebElement form21;
	
	//Form 22 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_22']")
	private WebElement form22;
		
	//Form 23 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_23']")
	private WebElement form23;
		
	//Form 24 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_24']")
	private WebElement form24;
		
	//Form 25 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_25']")
	private WebElement form25;
		
	//Form 26 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_26']")
	private WebElement form26;
		
	//Form 27 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_27']")
	private WebElement form27;
		
	//Form 28 elements
	@FindBy(xpath = "//input[@formcontrolname='Condn_cd_28']")
	private WebElement form28;
	
	//Form 29 elements
	@FindBy(xpath = "//input[@formcontrolname='Accident_state_29']")
	private WebElement accidentState;
	
	//Form 31 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_31a']")
	private WebElement form31OccuranceCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_31a']")
	private WebElement form31OccuranceDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_31b']")
	private WebElement form31OccuranceCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_31b']")
	private WebElement form31OccuranceDateB;
	
	//Form 32 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_32a']")
	private WebElement form32OccuranceCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_32a']")
	private WebElement form32OccuranceDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_32b']")
	private WebElement form32OccuranceCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_32b']")
	private WebElement form32OccuranceDateB;
		
	//Form 33 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_33a']")
	private WebElement form33OccuranceCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_33a']")
	private WebElement form33OccuranceDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_33b']")
	private WebElement form33OccuranceCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_33b']")
	private WebElement form33OccuranceDateB;
		
	//Form 34 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_34a']")
	private WebElement form34OccuranceCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_34a']")
	private WebElement form34OccuranceDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_cd_34b']")
	private WebElement form34OccuranceCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_dt_34b']")
	private WebElement form34OccuranceDateB;
	
	//Form 35 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_cd_35a']")
	private WebElement form35OccuranceSpanCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_st_35a']")
	private WebElement form35OccuranceSpanCodeFromDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_end_35a']")
	private WebElement form35OccuranceSpanCodeThroughDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_cd_35b']")
	private WebElement form35OccuranceSpanCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_st_35b']")
	private WebElement form35OccuranceSpanCodeFromDateB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_end_35b']")
	private WebElement form35OccuranceSpanCodeThroughDateB;
	
	//Form 36 elements
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_cd_36a']")
	private WebElement form36OccuranceSpanCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_st_36a']")
	private WebElement form36OccuranceSpanCodeFromDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_end_36a']")
	private WebElement form36OccuranceSpanCodeThroughDateA;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_cd_36b']")
	private WebElement form36OccuranceSpanCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_st_36b']")
	private WebElement form36OccuranceSpanCodeFromDateB;
	
	@FindBy(xpath = "//input[@formcontrolname='Occur_span_end_36b']")
	private WebElement form36OccuranceSpanCodeThroughDateB;	
	
	//Form 39 elements
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_39a']")
	private WebElement form39ValueCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_39a']")
	private WebElement form39ValueCodeAmountA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_39b']")
	private WebElement form39ValueCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_39b']")
	private WebElement form39ValueCodeAmountB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_39c']")
	private WebElement form39ValueCodeC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_39c']")
	private WebElement form39ValueCodeAmountC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_39d']")
	private WebElement form39ValueCodeD;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_39d']")
	private WebElement form39ValueCodeAmountD;
	
	//Form 40 elements
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_40a']")
	private WebElement form40ValueCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_40a']")
	private WebElement form40ValueCodeAmountA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_40b']")
	private WebElement form40ValueCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_40b']")
	private WebElement form40ValueCodeAmountB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_40c']")
	private WebElement form40ValueCodeC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_40c']")
	private WebElement form40ValueCodeAmountC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_40d']")
	private WebElement form40ValueCodeD;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_40d']")
	private WebElement form40ValueCodeAmountD;
	
	//Form 41 elements
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_41a']")
	private WebElement form41ValueCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_41a']")
	private WebElement form41ValueCodeAmountA;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_41b']")
	private WebElement form41ValueCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_41b']")
	private WebElement form41ValueCodeAmountB;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_41c']")
	private WebElement form41ValueCodeC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_41c']")
	private WebElement form41ValueCodeAmountC;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_41d']")
	private WebElement form41ValueCodeD;
	
	@FindBy(xpath = "//input[@formcontrolname='Val_cd_amt_41d']")
	private WebElement form41ValueCodeAmountD;
	
	//Service
	@FindBy(xpath = "//span[text()=' Add ']/parent::button")
	private WebElement addService;
	
	@FindBy(xpath = "//span[text()=' Modify ']/parent::button")
	private WebElement modifyService;
	
	@FindBy(xpath = "//span[text()=' Remove ']/parent::button")
	private WebElement removeService;
	
	private String serviceLine = "(//tbody)[4]/tr[XX]";
	
	private String serviceLineRevCode = "(//tbody)[4]/tr[XX]/td[1]";
	
	private String serviceLineDescription = "(//tbody)[4]/tr[XX]/td[2]";
	
	private String serviceLineHIPPSCode = "(//tbody)[4]/tr[XX]/td[3]";
	
	private String serviceLineSericeDate = "(//tbody)[4]/tr[XX]/td[4]";
	
	private String serviceLineSericeUnits = "(//tbody)[4]/tr[XX]/td[5]";
	
	private String serviceLineTotalCharges = "(//tbody)[4]/tr[XX]/td[6]";
	
	private String serviceLineNonCoveredCharges = "(//tbody)[4]/tr[XX]/td[7]";
	
	@FindBy(xpath = "//input[@formcontrolname='Rev_cd']")
	private WebElement revenueCode;
	
	@FindBy(xpath = "//input[@formcontrolname='Proc_cd']")
	private WebElement serviceCode;
	
	@FindBy(xpath = "//input[@formcontrolname='Srvdt']")
	private WebElement serviceDate;
	
	@FindBy(xpath = "//input[@formcontrolname='Units']")
	private WebElement units;
	
	@FindBy(xpath = "//input[@formcontrolname='Charge']")
	private WebElement totalCharges;
	
	@FindBy(xpath = "//input[@formcontrolname='Noncoveredcharge']")
	private WebElement nonCoverageCharges;
	
	@FindBy(xpath = "(//span[text()=' Cancel ']/parent::button)[2]")
	private WebElement cancelService;
	
	@FindBy(xpath = "(//span[text()=' Save ']/parent::button)[2]")
	private WebElement saveService;
	
	@FindBy(xpath = "//span[text()='Service']/following-sibling::button")
	private WebElement closeService;
	
	//Add previous payer
	@FindBy(xpath = "//span[contains(text(),'Add Previous Payer')]/parent::button")
	private WebElement addPreviousPayerButton;
	
	//Form 50 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Payer_50a']")
	private WebElement payerA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Payer_50b']")
	private WebElement payerB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Payer_50c']")
	private WebElement payerC;
	
	//Form 51 elements
	@FindBy(xpath = "//input[@formcontrolname='Plan_id_51a']")
	private WebElement healthPlanIDA;
	
	@FindBy(xpath = "//input[@formcontrolname='Plan_id_51b']")
	private WebElement healthPlanIDB;
	
	@FindBy(xpath = "//input[@formcontrolname='Plan_id_51c']")
	private WebElement healthPlanIDC;
	
	//Form 52 elements
	@FindBy(xpath  = "//mat-checkbox[@formcontrolname='Rel_info_52a']")
	private WebElement relInfoCheckBoxA;
	
	@FindBy(xpath  = "//mat-checkbox[@formcontrolname='Rel_info_52b']")
	private WebElement relInfoCheckBoxB;
	
	@FindBy(xpath  = "//mat-checkbox[@formcontrolname='Rel_info_52c']")
	private WebElement relInfoCheckBoxC;
	
	//Form 53 elements
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='Benefit_asign_53a']")
	private WebElement beneftAssignmentCheckboxA;
	
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='Benefit_asign_53b']")
	private WebElement beneftAssignmentCheckboxB;
	
	@FindBy(xpath = "//mat-checkbox[@formcontrolname='Benefit_asign_53c']")
	private WebElement beneftAssignmentCheckboxC;
	
	//Form 54 elements
	@FindBy(xpath = "//input[@formcontrolname='Prior_pmt_54a']")
	private WebElement priorPaymentAmountA;
	
	@FindBy(xpath = "//input[@formcontrolname='Prior_pmt_54b']")
	private WebElement priorPaymentAmountB;
	
	@FindBy(xpath = "//input[@formcontrolname='Prior_pmt_54c']")
	private WebElement priorPaymentAmountC;
	
	//Form 55 elements
	@FindBy(xpath = "//input[@formcontrolname='Est_amt_due_55a']")
	private WebElement estAmountDueA;
	
	@FindBy(xpath = "//input[@formcontrolname='Est_amt_due_55b']")
	private WebElement estAmountDueB;
	
	@FindBy(xpath = "//input[@formcontrolname='Est_amt_due_55c']")
	private WebElement estAmountDueC;
	
	//Payer type elements
	@FindBy(xpath = "//mat-select[@formcontrolname='Payer_type_a']")
	private WebElement payerTypeDrodownA;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Payer_type_b']")
	private WebElement payerTypeDrodownB;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Payer_type_c']")
	private WebElement payerTypeDrodownC;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and text()=' Medicare ']")
	private WebElement payerTypeMedicareOption;
	
	@FindBy(xpath = "//span[@class='mat-option-text' and text()=' Non-Medicare ']")
	private WebElement payerTypeNonMedicareOption;
	
	//Form 56 elements
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Bill_prv_npi_56']")
	private WebElement billingProviderNPIDropdown;
	
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Bill_prv_npi_56']/div/div/span/span")
	private WebElement billingNPIText;
	
	//Form 57 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Other_prv_id_57']")
	private WebElement otherProviderID;
	
	//Form 58 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_nm_58a']")
	private WebElement insuredNameA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_nm_58b']")
	private WebElement insuredNameB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_nm_58c']")
	private WebElement insuredNameC;
	
	//Form 59 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_relto_ins_59a']")
	private WebElement patientRelatedToInsuranceA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_relto_ins_59b']")
	private WebElement patientRelatedToInsuranceB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_relto_ins_59c']")
	private WebElement patientRelatedToInsuranceC;
	
	//Form 60 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_id_60a']")
	private WebElement insuredsUniqueIDA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_id_60b']")
	private WebElement insuredsUniqueIDB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_id_60c']")
	private WebElement insuredsUniqueIDC;
	
	//Form 61 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_nm_61a']")
	private WebElement insuredGroupNameA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_nm_61b']")
	private WebElement insuredGroupNameB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_nm_61c']")
	private WebElement insuredGroupNameC;
	
	//Form 62 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_no_62a']")
	private WebElement insuredGroupNumberA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_no_62b']")
	private WebElement insuredGroupNumberB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Insrd_grp_no_62c']")
	private WebElement insuredGroupNumberC;
	
	//Form 63 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Auth_cd_63a']")
	private WebElement treatmentAuthCodesA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Auth_cd_63b']")
	private WebElement treatmentAuthCodesB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Auth_cd_63c']")
	private WebElement treatmentAuthCodesC;
	
	//Form 64 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Resub_ref_dcn_64a']")
	private WebElement resubmissionClaimNumberA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Resub_ref_dcn_64b']")
	private WebElement resubmissionClaimNumberB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Resub_ref_dcn_64c']")
	private WebElement resubmissionClaimNumberC;
	
	//Form 65 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Emplyr_nm_65a']")
	private WebElement employerNameA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Emplyr_nm_65b']")
	private WebElement employerNameB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Emplyr_nm_65c']")
	private WebElement employerNameC;
	
	//Form 66 elements
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Dx_version_66']")
	private WebElement diagnosisVersion;
	
	@FindBy(xpath = "//mat-select[@formcontrolname = 'Dx_version_66']/div/div/span/span")
	private WebElement diagnosisVersionText;
	
	//Form 67 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Prin_diag_cd_67']")
	private WebElement principalDIagnosis;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67a']")
	private WebElement otherDiagnosisA;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67b']")
	private WebElement otherDiagnosisB;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67c']")
	private WebElement otherDiagnosisC;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67d']")
	private WebElement otherDiagnosisD;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67e']")
	private WebElement otherDiagnosisE;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67f']")
	private WebElement otherDiagnosisF;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67g']")
	private WebElement otherDiagnosisG;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67h']")
	private WebElement otherDiagnosisH;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67i']")
	private WebElement otherDiagnosisI;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67j']")
	private WebElement otherDiagnosisJ;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67k']")
	private WebElement otherDiagnosisK;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67l']")
	private WebElement otherDiagnosisL;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67m']")
	private WebElement otherDiagnosisM;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67n']")
	private WebElement otherDiagnosisN;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67o']")
	private WebElement otherDiagnosisO;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67p']")
	private WebElement otherDiagnosisP;
	
	@FindBy (xpath = "//input[@formcontrolname = 'Oth_diag_cd_67q']")
	private WebElement otherDiagnosisQ;
	
	//Form 69 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Adm_diag_cd_69']")
	private WebElement admissionDiagnosis;
	
	//Form 70 elements 
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_rsn_diag_cd_70a']")
	private WebElement patientReasonDiagnosisA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_rsn_diag_cd_70b']")
	private WebElement patientReasonDiagnosisB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_rsn_diag_cd_70c']")
	private WebElement patientReasonDiagnosisC;
	
	//Form 71 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Pps_cd_71']")
	private WebElement ppsCode;
	
	//Form 72 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Eci_diag_cd_72a']")
	private WebElement eciDiagnosisCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Eci_diag_cd_72b']")
	private WebElement eciDiagnosisCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Eci_diag_cd_72c']")
	private WebElement eciDiagnosisCodeC;
	
	//Form 74 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Prin_proccd_74']")
	private WebElement principleProcedureCode;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Prin_pc_dt_74']")
	private WebElement principlePCDate;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_proccd_74a']")
	private WebElement otherProcedureCodeA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_pc_dt_74a']")
	private WebElement otherPCDateA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_proccd_74b']")
	private WebElement otherProcedureCodeB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_pc_dt_74b']")
	private WebElement otherPCDateB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_proccd_74c']")
	private WebElement otherProcedureCodeC;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_pc_dt_74c']")
	private WebElement otherPCDateC;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_proccd_74d']")
	private WebElement otherProcedureCodeD;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_pc_dt_74d']")
	private WebElement otherPCDateD;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_proccd_74e']")
	private WebElement otherProcedureCodeE;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_pc_dt_74e']")
	private WebElement otherPCDateE;
	
	//Form 76 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Attnd_phy_npi_76']")
	private WebElement attendingPhysicianNPI;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Atnd_phy_id_type_76']")
	private WebElement attendingPhysicianQual1;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Atnd_phy_id_76']")
	private WebElement attendingPhysicianQual2;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Atnd_phy_ln_76']")
	private WebElement attendingPhysicianLastName;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Atnd_phy_fn_76']")
	private WebElement attendingPhysicianFirstName;
	
	//Form 77 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Optg_phy_npi_77']")
	private WebElement operatingPhysicianNPI;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Optg_phy_id_type_77']")
	private WebElement operatingPhysicianQual1;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Optg_phy_id_77']")
	private WebElement operatingPhysicianQual2;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Optg_phy_ln_77']")
	private WebElement operatingPhysicianLastName;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Optg_phy_fn_77']")
	private WebElement operatingPhysicianFirstName;
	
	//Form 78 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_npi_78']")
	private WebElement otherNPIA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_id_type_78']")
	private WebElement otherNPIQual1A;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_id_78']")
	private WebElement otherNPIQual2A;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_ln_78']")
	private WebElement otherNPILastNameA;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_fn_78']")
	private WebElement otherNPIFirstNameA;
	
	//Form 79 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_npi_79']")
	private WebElement otherNPIB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_id_type_79']")
	private WebElement otherNPIQual1B;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_id_79']")
	private WebElement otherNPIQual2B;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_ln_79']")
	private WebElement otherNPILastNameB;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Other_phy_fn_79']")
	private WebElement otherNPIFirstNameB;
	
	//Form 80 elements
	@FindBy(xpath = "//textarea[@formcontrolname = 'Remarks_80']")
	private WebElement remarks;
	
	//Form 81 elements
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_qualfr_81a']")
	private WebElement form81AQualifier;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_81a']")
	private WebElement form81ATaxanomy;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Value_81a']")
	private WebElement form81AValue;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_qualfr_81b']")
	private WebElement form81BQualifier;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_81b']")
	private WebElement form81BTaxanomy;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Value_81b']")
	private WebElement form81BValue;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_qualfr_81c']")
	private WebElement form81CQualifier;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_81c']")
	private WebElement form81CTaxanomy;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Value_81c']")
	private WebElement form81CValue;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_qualfr_81d']")
	private WebElement form81DQualifier;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Cc_81d']")
	private WebElement form81DTaxanomy;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Value_81d']")
	private WebElement form81DValue;	

	//Received Date elements
	@FindBy(xpath = "//input[@formcontrolname = 'Recd_dt']")
	private WebElement receivedDate;

	//Print, Save, Cancel, Submit buttons
	@FindBy(xpath = "//span[text()=' Print ']/parent::button")
	private WebElement printButton;
	
	@FindBy(xpath = "//span[text()=' Cancel ']/parent::button")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//span[text()=' Save ']/parent::button")
	private WebElement saveButton;
	
	@FindBy(xpath = "//span[text()=' Submit ']/parent::button")
	private WebElement submitButton;
	
	
	//Unsaved Changes Popup Elements
	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement unsavedChagesOK;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement unsavedChagesCancel;
	
	@FindBy(xpath = "//button[text()='×']")
	private WebElement unsavedChagesClose;
	
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
	
	//created by Nandhalala
		private void cancelClaim() {
			click(cancelButton, "Cancel");
			putStaticWait(1);
			waitUntilClickable(unsavedChagesOK, 2);
			click(unsavedChagesOK, "OK");
		}

	public String createUB04AndSubmitClaim(String className, String claimID) {

		String myMCSNumber = "";
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
//		String revcd = dataMap.get("revenueCode");
//		String pccd = dataMap.get("serviceCode");
//		String srvcdt = dataMap.get("serviceDate");
//		String noofunits =dataMap.get("units");
//		String charges = dataMap.get("totalCharges");
//		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		if(clickCreateButton()) {
			report(LogStatus.PASS, "Create UB-04 page is Displayed");
			myMCSNumber = getText(myMCSClaimnumber);
			waitUntilClickable(providerSearchButton, 20);
			click(providerSearchButton, "Provider Search");
			
			//Provider selection
			if(providerSearchPopupHeading.isDisplayed()) {
				report(LogStatus.PASS,"Provider Search popup is displayed.");
			}else {
				report(LogStatus.FAIL,"Provider Search popup is not displayed.");
			}
			if(!prvid.equals("") && Objects.nonNull(prvid)) {
				sendKeys(providerSeachProviderID, "Provider ID", prvid);
				click(providerSearchSearcButton, "Search");
				waitForLoadingToDisappear();
				String rownumber = providerSearchGridRow.replace("XX", "1");
				WebElement providerrow = driver.findElement(By.xpath(rownumber));
				click(providerrow, "First provider record");
				click(providerSearchSelectProviderButton, "Select Provider");
				waitForLoadingToDisappear();
				String actualProviderID = getAttribute(providerIDText, "value");
				if(Objects.nonNull(actualProviderID) && 
						!actualProviderID.equals("")) {
					assertEquals(actualProviderID, prvid, "The provider id from field "
							+ "is : "+actualProviderID+" not : "+prvid);
				}else {
					try {
						throw new Exception("Provider ID from feild is null or empty");
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
			}else {
				try {
					throw new Exception("Provider ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Site selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				click(siteSelectionDropdown, "Site dropdown");
				String siteElement = dropdownOptions.replace("XX", steid);
				WebElement site_ele = driver.findElement(By.xpath(siteElement));
				click(site_ele, "Site");
				waitForLoadingToDisappear();
				String actualSite = getText(siteIDText);
				String[] actualSiteID = actualSite.split(" - ");
				assertEquals(actualSiteID[0], steid,"The site id from field "
							+ "is : "+actualSiteID[0]+" not : "+steid);
			}else {
				try {
					throw new Exception("Site ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Patient Search
			waitUntilClickable(patientSearchButton, 20);
			click(patientSearchButton, "Patient Search");
			if(patientSearchHeading.isDisplayed()) {
				report(LogStatus.PASS,"Patient Search popup is displayed.");
			}else {
				report(LogStatus.FAIL,"Patient Search popup is not displayed.");
			}
			if(Objects.nonNull(patid) && !patid.equals("")) {
				sendKeys(patientSearchPatientID, "Patient ID", patid);
				String actualPatientID = getAttribute(patientSearchPatientID, "value");
				assertEquals(actualPatientID, patid, "The Patient Control Number from "
						+ "field is : "+actualPatientID+" not : "+patid);
				click(patientSearchSearchButton, "Search");
				waitForLoadingToDisappear();
				String rownumber = patientSearchGridRow.replace("XX", "1");
				WebElement patientrow = driver.findElement(By.xpath(rownumber));
				click(patientrow, "First patient record");
				click(patientSearchSelectPatientButton, "Select Patient");
				waitForLoadingToDisappear();
			}else {
				try {
					throw new Exception("Patient ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Patient Control Number edit
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				patientControlNumber.clear();
				sendKeys(patientControlNumber, "Patient Control Number", pcn);
				String actualPCN = getAttribute(patientControlNumber, "value");
				assertEquals(actualPCN, pcn, "The Patient Control Number from field "
						+ "is : "+actualPCN+" not : "+pcn);
			}else {
				report(LogStatus.WARNING, "Patient Control Number is empty or null.");
			}
			
			//Bill Type edit
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				sendKeys(billType, "Bill Type", billtype);
				String actualBillType = getAttribute(billType, "value");
				assertEquals(actualBillType, billtype, "The Patient Control Number "
						+ "from field is : "+actualBillType+" not : "+billtype);
			}else {
				try {
					throw new Exception("Bill Type is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Statement from and to date edit
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				sendKeys(statementFromPeriod, "From Date", fromperiod);
				sendKeys(statementToPeriod, "To Date", toperiod);
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
						+ "from field is : "+actualFromDate+" not : "+fromperiod);
				assertEquals(actualToDate, toperiod, "The Statement To Period  "
						+ "from field is : "+actualToDate+" not : "+toperiod);
			}else {
				try {
					throw new Exception("From Date or To Date is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 12 Admission Date edit
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				sendKeys(admissionDate, "Admission Date", admsndt);
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
						+" is : "+actualAdmissionDate+" not : "+admsndt);
			}else {
				try {
					throw new Exception("Admission Date is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 13 Admission Hour Edit
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				click(admissionHourDropdown,"Admission Hour");
				String admsnHour = dropdownOptions.replace("XX", admsnhr);
				WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
				waitUntilClickable(admisionHour, 10);
				click(admisionHour,"Admission Hour");
				String actualAdmissionHour = getText(admissionHourText);
				assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
						+ "is : "+actualAdmissionHour+" not "+admsnhr);
				
			}else {
				report(LogStatus.WARNING, "Admission Hour is empty or null.");
			}
			
			//Form 14 Reference Type edits
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				click(visitTypeDropdown, "Visit Type");
				String refType = dropdownOptions.replace("XX", reftype);
				WebElement referenceType = driver.findElement(By.xpath(refType));
				waitUntilClickable(referenceType, 10);
				click(referenceType, reftype);
				String actualReferenceType = getText(visitTypeText);
				assertEquals(actualReferenceType, reftype,"The Reference Type from field "
						+ "is : "+actualReferenceType+" not "+reftype);
			}else {
				try {
					throw new Exception("Reference Type is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 15 Reference Source edits
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				click(referenceSourceDropdown, "Visit Type");
				String refSrc = dropdownOptions.replace("XX", refsrc);
				WebElement referenceSource = driver.findElement(By.xpath(refSrc));
				waitUntilClickable(referenceSource, 10);
				click(referenceSource, refsrc);
				String actualReferenceSource = getText(referenceSourceText);
				assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
						+ "is : "+actualReferenceSource+" not "+refsrc);
			}else {
				try {
					throw new Exception("Reference Source is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 16 Discharge Hour edits
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				click(dischargeHour, "Visit Type");
				String dischrgHr = dropdownOptions.replace("XX", dschrghr);
				WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
				waitUntilClickable(dischargeHour, 10);
				click(dischargeHour, dschrghr);
				String actualDischargeHour = getText(dischargeHourText);
				assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
						+ "is : "+actualDischargeHour+" not "+dschrghr);
			}else {
				report(LogStatus.WARNING, "Discharge Hour is empty or null.");
			}
			
			//Form 17 Discharge Status edits
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				click(dischargeStatus, "Visit Type");
				String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
				WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
				waitUntilClickable(dischargeStatus, 10);
				click(dischargeStatus, dschrgsts);
				String actualDischargeStatus = getText(dischargeStatusText);
				assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
						+ "is : "+actualDischargeStatus+" not "+dschrgsts);
			}else {
				report(LogStatus.WARNING, "Discharge Status is empty or null.");
			}
			
			//Form 18 Edits
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				sendKeys(form18, "Form 18", form_18);
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
						actualForm18+" not : "+actform_18);
			}else {
				report(LogStatus.WARNING, "Form 18 value is empty or null.");
			}
			
			//Form 19 Edits
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				sendKeys(form19, "Form 19", form_19);
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
						actualForm19+" not : "+actform_19);
			}else {
				report(LogStatus.WARNING, "Form 19 value is empty or null.");
			}
			
			//Form 20 Edits
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				sendKeys(form20, "Form 20", form_20);
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
						actualForm20+" not : "+actform_20);
			}else {
				report(LogStatus.WARNING, "Form 20 value is empty or null.");
			}
			
			//Form 21 Edits
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				sendKeys(form21, "Form 21", form_21);
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
						actualForm21+" not : "+actform_21);
			}else {
				report(LogStatus.WARNING, "Form 21 value is empty or null.");
			}
			
			//Form 22 Edits
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				sendKeys(form22, "Form 22", form_22);
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
						actualForm22+" not : "+actform_22);
			}else {
				report(LogStatus.WARNING, "Form 22 value is empty or null.");
			}
			
			//Form 23 Edits
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				sendKeys(form23, "Form 23", form_23);
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
						actualForm23+" not : "+actform_23);
			}else {
				report(LogStatus.WARNING, "Form 23 value is empty or null.");
			}
			
			//Form 24 Edits
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				sendKeys(form24, "Form 24", form_24);
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
						actualForm24+" not : "+actform_24);
			}else {
				report(LogStatus.WARNING, "Form 24 value is empty or null.");
			}
			
			//Form 25 Edits
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				sendKeys(form25, "Form 25", form_25);
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
						actualForm25+" not : "+actform_25);
			}else {
				report(LogStatus.WARNING, "Form 25 value is empty or null.");
			}
			
			//Form 26 Edits
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				sendKeys(form26, "Form 26", form_26);
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
						actualForm26+" not : "+actform_26);
			}else {
				report(LogStatus.WARNING, "Form 26 value is empty or null.");
			}
			
			//Form 27 Edits
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				sendKeys(form27, "Form 27", form_27);
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
						actualForm27+" not : "+actform_27);
			}else {
				report(LogStatus.WARNING, "Form 27 value is empty or null.");
			}
			
			//Form 28 Edits
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				sendKeys(form28, "Form 28", form_28);
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
						actualForm28+" not : "+actform_28);
			}else {
				report(LogStatus.WARNING, "Form 28 value is empty or null.");
			}
			
			//Form 29 Accident State Edits
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				sendKeys(accidentState, "Accident State", accdtstate);
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
						actualAccidentState+" not : "+actaccdtstate);
			}else {
				report(LogStatus.WARNING, "Accident State value is empty or null.");
			}
			
			//Form 31-A Edits
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
						+ "code from Form 31 A is : "+actualform31aoccurancecode+
						" not : "+actform31occcd_a);
				
				sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				assertEquals(actualform31adate, form31occdt_a, "The Occurance "
						+ "code from Form 31 A is : "+actualform31adate+
						" not : "+form31occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 31-A is empty or null.");
			}
			
			//Form 31-B Edits
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
						+ "code from Form 31 B is : "+actualform31boccurancecode+
						" not : "+actform31occcd_b);
				
				sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
						+ "code from Form 31 B is : "+actualform31bdate+
						" not : "+form31occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 31-B is empty or null.");
			}
			
			//Form 32-A Edits
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
						+ "code from Form 32 A is : "+actualform32aoccurancecode+
						" not : "+actform32occcd_a);
				
				sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				assertEquals(actualform32adate, form32occdt_a, "The Occurance "
						+ "code from Form 32 A is : "+actualform32adate+
						" not : "+form32occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 32-A is empty or null.");
			}
			
			//Form 32-B Edits
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
						+ "code from Form 32 B is : "+actualform32boccurancecode+
						" not : "+actform32occcd_b);
				
				sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
						+ "code from Form 32 B is : "+actualform32bdate+
						" not : "+form32occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 32-B is empty or null.");
			}
		
			//Form 33-A Edits
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form32occcd_a;
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
						+ "code from Form 33 A is : "+actualform33aoccurancecode+
						" not : "+actform33occcd_a);
				
				sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				assertEquals(actualform33adate, form33occdt_a, "The Occurance "
						+ "code from Form 33 A is : "+actualform33adate+
						" not : "+form33occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 33-A is empty or null.");
			}
			
			//Form 33-B Edits
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				assertEquals(actualform33boccurancecode, form33occcd_b, "The Occurance "
						+ "code from Form 33 B is : "+actualform33boccurancecode+
						" not : "+form33occcd_b);
				
				sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
						+ "code from Form 33 B is : "+actualform33bdate+
						" not : "+form33occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 33-B is empty or null.");
			}
			
			//Form 34-A Edits
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
						+ "code from Form 34 A is : "+actualform34aoccurancecode+
						" not : "+actform34occcd_a);
				
				sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				assertEquals(actualform34adate, form34occdt_a, "The Occurance "
						+ "code from Form 34 A is : "+actualform34adate+
						" not : "+form34occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 34-A is empty or null.");
			}
			
			//Form 34-B Edits
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
						+ "code from Form 34 B is : "+actualform34boccurancecode+
						" not : "+actform34occcd_b);
				
				sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
						+ "code from Form 34 B is : "+actualform34bdate+
						" not : "+form34occdt_b);
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 34-B is empty or null.");
			}
				
			//Form 35-A Edits
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35aoccurancecode+
						" not : "+actform35occcd_a);
				
				sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35afromdate+
						" not : "+form35occfrdt_a);
				
				sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
						form35occtodt_a);
				String actualform35atodate = 
					getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35atodate+
						" not : "+form35occtodt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 35-A is empty or null.");
			}
				
			//Form 35-B Edits
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35boccurancecode+
						" not : "+actform35occcd_b);
				
				sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35bfromdate+
						" not : "+form35occfrdt_b);
				
				sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
						form35occtodt_b);
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35btodate+
						" not : "+form35occtodt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 35-B is empty or null.");
			}
			
			//Form 36-A Edits
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36aoccurancecode+
						" not : "+actform36occcd_a);
				
				sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36afromdate+
						" not : "+form36occfrdt_a);
				
				sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
						form36occtodt_a);
				String actualform36atodate = 
					getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36atodate+
						" not : "+form36occtodt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 36-A is empty or null.");
			}
				
			//Form 36-B Edits
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36boccurancecode+
						" not : "+actform36occcd_b);
				
				sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36bfromdate+
						" not : "+form36occfrdt_b);
				
				sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
						form36occtodt_b);
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36btodate+
						" not : "+form36occtodt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 36-B is empty or null.");
			}
			
			//Form 39-A Edits
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				assertEquals(actualform39avalcd, actform39avalcd, "The Value "
						+ "code from Form 39 A is : "+actualform39avalcd+
						" not : "+actform39avalcd);
				
				form39ValueCodeAmountA.clear();
				sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
						+ "code from Form 39 A is : "+actualform39avalcdamt+
						" not : "+form39valcdamt_a);
				
			}
			
			//Form 39-B Edits
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
						+ "code from Form 39 B is : "+actualform39bvalcd+
						" not : "+actform39bvalcd);
				
				form39ValueCodeAmountB.clear();
				sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
						+ "code from Form 39 B is : "+actualform39bvalcdamt+
						" not : "+form39valcdamt_b);
				
			}
			
			//Form 39-C Edits
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_c;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
						+ "code from Form 39 C is : "+actualform39cvalcd+
						" not : "+actform39cvalcd);
				
				form39ValueCodeAmountC.clear();
				sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
						+ "code from Form 39 D is : "+actualform39cvalcdamt+
						" not : "+form39valcdamt_c);
				
			}
			
			//Form 39-D Edits
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
						+ "code from Form 39 D is : "+actualform39dvalcd+
						" not : "+actform39dvalcd);
				
				form39ValueCodeAmountD.clear();
				sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
						+ "code from Form 39 D is : "+actualform39dvalcdamt+
						" not : "+form39valcdamt_d);
				
			}
			
			//Form 40-A Edits
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				assertEquals(actualform40avalcd, actform40avalcd, "The Value "
						+ "code from Form 40 A is : "+actualform40avalcd+
						" not : "+actform40avalcd);
				
				form40ValueCodeAmountA.clear();
				sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
						+ "code from Form 40 A is : "+actualform40avalcdamt+
						" not : "+form40valcdamt_a);
				
			}
			
			//Form 40-B Edits
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
						+ "code from Form 40 B is : "+actualform40bvalcd+
						" not : "+actform40bvalcd);
				
				form40ValueCodeAmountB.clear();
				sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
						+ "code from Form 40 B is : "+actualform40bvalcdamt+
						" not : "+form40valcdamt_b);
				
			}
			
			//Form 40-C Edits
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_c;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
						+ "code from Form 40 C is : "+actualform40cvalcd+
						" not : "+actform40cvalcd);
				
				form40ValueCodeAmountC.clear();
				sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
						+ "code from Form 40 D is : "+actualform40cvalcdamt+
						" not : "+form40valcdamt_c);
				
			}
			
			//Form 40-D Edits
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
						+ "code from Form 40 D is : "+actualform40dvalcd+
						" not : "+actform40dvalcd);
				
				form40ValueCodeAmountD.clear();
				sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
						+ "code from Form 40 D is : "+actualform40dvalcdamt+
						" not : "+form40valcdamt_d);
				
			}
			
			//Form 41-A Edits
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				assertEquals(actualform41avalcd, actform41avalcd, "The Value "
						+ "code from Form 41 A is : "+actualform41avalcd+
						" not : "+actform41avalcd);
				
				form41ValueCodeAmountA.clear();
				sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
						+ "code from Form 41 A is : "+actualform41avalcdamt+
						" not : "+form41valcdamt_a);
				
			}
			
			//Form 41-B Edits
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
						+ "code from Form 41 B is : "+actualform41bvalcd+
						" not : "+actform41bvalcd);
				
				form41ValueCodeAmountB.clear();
				sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
						+ "code from Form 41 B is : "+actualform41bvalcdamt+
						" not : "+form41valcdamt_b);
				
			}
			
			//Form 41-C Edits
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_c;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
						+ "code from Form 41 C is : "+actualform41cvalcd+
						" not : "+actform41cvalcd);
				
				form41ValueCodeAmountC.clear();
				sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
						+ "code from Form 41 D is : "+actualform41cvalcdamt+
						" not : "+form41valcdamt_c);
				
			}
			
			//Form 41-D Edits
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && 
					!form41valcdamt_d.equals("")) {
				sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
						+ "code from Form 41 D is : "+actualform41dvalcd+
						" not : "+actform41dvalcd);
				
				form41ValueCodeAmountD.clear();
				sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
						+ "code from Form 41 D is : "+actualform41dvalcdamt+
						" not : "+form41valcdamt_d);
			}
			
			//Add service
			List<Map<String, String>> serviceDataMAP = ExcelUtil.getTestCasesDataInMap
					("testData//AlphaPlusTestData.xlsx", className, "claimID", claimID);
			 Map< String, String> service ;
			 for(int i = 0 ; i < serviceDataMAP.size() ; i++) {
				 	service = serviceDataMAP.get(i);String revcd = dataMap.get("revenueCode");
					String pccd = service.get("serviceCode");
					String srvcdt = service.get("serviceDate");
					String noofunits =service.get("units");
					String charges = service.get("totalCharges");
					String noncoverdcharges = service.get("nonCoveredCharges");
					if(addService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service added successfully.");
					}else {
						report(LogStatus.FAIL,"Service not added.");
					}
			 }
			 
//				//Add service
//				if(addService(revcd, pccd, srvcdt, noofunits, charges, 
//						noncoverdcharges)) {
//					report(LogStatus.PASS,"Service added successfully.");
//				}else {
//					report(LogStatus.FAIL,"Service not added.");
//				}
				
				//Previous Payer
				
					
					//Health Plan ID A
					if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
						sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
						String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
						assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
								+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);						
					}

					
					//REL INFO A
					if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
						String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
						String[] relInfoAData = relInfoAClass.split(" ");
						String actualRelInfoA = "";
						for(String s : relInfoAData) {
							if(s.equals("mat-checkbox-checked")) {
								actualRelInfoA = s;
							}
						}
						if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
							click(relInfoCheckBoxA, "Rel Info Checkbox A");
							actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
							if(actualRelInfoA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Actual Rel Info check box checked");
							}else {
								report(LogStatus.FAIL, "Actual Rel Info check box not checked");
							}
							
						}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
								relinfo_a.equals("NO")) {
							click(relInfoCheckBoxA, "Rel Info Checkbox A");
							actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
							if(!actualRelInfoA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Actual Rel Info check box unchecked");
							}else {
								report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
							}
							
						}
					}
					
					//ASN BEN A
					if(Objects.nonNull(benfitassignment_a) && 
							!benfitassignment_a.equals("")) {
						String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
						String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
						String actualBenfitAssignmentA = "";
						for(String s : benfitAssignmentAData) {
							if(s.equals("mat-checkbox-checked")) {
								actualBenfitAssignmentA = s;
							}
						}
						if(actualBenfitAssignmentA.equals("") && 
								benfitassignment_a.equals("YES")) {
							click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
							actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
							if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Benefit Assignment check box checked");
							}else {
								report(LogStatus.FAIL, "Benefit Assignment check box not checked");
							}
							
						}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
								benfitassignment_a.equals("NO")) {
							click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
							actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
							if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Benefit Assignment check box unchecked");
							}else {
								report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
							}
							
						}
					}
					
					//Prior payment amount
					if(Objects.nonNull(priorpaymentamt_a) && 
							!priorpaymentamt_a.equals("")) {
						priorPaymentAmountA.clear();
						sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
						String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
						assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
								+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
								" not : "+priorpaymentamt_a);
					}
					
					//EST Due Amount
					if(Objects.nonNull(estamountdue_a) && 
							!estamountdue_a.equals("")) {
						estAmountDueA.clear();
						sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
						String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
						assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
								+"from prior payment amount field A is : "+actualESTAmountDueA+
								" not : "+estamountdue_a);
					}
					
					//primary payer
					if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
						click(payerTypeDrodownA, "Payer Type");
						switch(payertype_a) {
						case "MEDICARE":{
							click(payerTypeMedicareOption, "MEDICARE");
							break;
						}
						case "NON MEDICARE":{
							click(payerTypeNonMedicareOption, "Non - MEDICARE");
							break;
						}
						default:
							report(LogStatus.WARNING, "Payer Type is not valid");
							
						}
					}
					
					if(Objects.nonNull(noOfPreviousPayer) && 
							!noOfPreviousPayer.equals("")) {
						if(Integer.parseInt(noOfPreviousPayer) > 1) {
							System.out.println(Integer.parseInt(noOfPreviousPayer));
							for(int i = 1; i<Integer.parseInt(noOfPreviousPayer); i++) {
								click(addPreviousPayerButton, "Add previouss Payer");
							}
							
							if(noOfPreviousPayer.equals("2")) {
								addPrimaryPayeB(payer_b,healthplanid_b, relinfo_b, 
										benfitassignment_b,	priorpaymentamt_b, 
										estamountdue_b, payertype_b);
							}else if(noOfPreviousPayer.equals("3")) {
								addPrimaryPayeBAndC(payer_b,healthplanid_b, relinfo_b, 
										benfitassignment_b,	priorpaymentamt_b, 
										estamountdue_b, payertype_b, payer_c, 
										healthplanid_c, relinfo_c, benfitassignment_c, 
										priorpaymentamt_c, estamountdue_c, payertype_c);
							}
						
					}
					}
					
					
				
			
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				try {
					throw new Exception("Billing Provider NPI is null or empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				sendKeys(otherProviderID, "Other Provider ID", othrprvid);
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				assertEquals(actualOtherProviderID, othrprvid, "The value from other"
						+ " Provider ID field is : "+actualOtherProviderID+" not "
								+ othrprvid);		
				}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				sendKeys(insuredNameA, "Insured Name A", insrdname_a);
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				assertEquals(actualInsuredNameA, insrdname_a, "The value from "
						+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				sendKeys(insuredNameB, "Insured Name B", insrdname_b);
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				assertEquals(actualInsuredNameB, insrdname_b, "The value from "
						+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				sendKeys(insuredNameC, "Insured Name C", insrdname_c);
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				assertEquals(actualInsuredNameC, insrdname_c, "The value from "
						+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
						patreltoinsure_a);
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
						+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
						patreltoinsure_a);
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
						patreltoinsure_b);
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
						+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
						patreltoinsure_b);
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
						patreltoinsure_c);
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
						+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
						patreltoinsure_c);
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
						insuredunqid_a);
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
						+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
						insuredunqid_a);
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
						insuredunqid_b);
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
						+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
						insuredunqid_b);
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
						insuredunqid_c);
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
						+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
						insuredunqid_c);
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				sendKeys(insuredGroupNameA, "Insured Unique ID A", 
						insrdgrpnm_a);
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
						insrdgrpnm_a);
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				sendKeys(insuredGroupNameB, "Insured Unique ID B", 
						insrdgrpnm_b);
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
						+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
						insrdgrpnm_b);
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				sendKeys(insuredGroupNameC, "Insured Unique ID C", 
						insrdgrpnm_c);
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
						+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
						insrdgrpnm_c);
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
						insrdgrpno_a);
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNumberA+" not "+
						insrdgrpno_a);
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
						insrdgrpno_b);
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
						+ "Insured Name B is : "+actualInsuredGroupNumberB+" not "+
						insrdgrpno_b);
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
						insrdgrpno_c);
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNumberC+" not "+
						insrdgrpno_c);
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				sendKeys(employerNameA, "Employer Name A", empnm_a);
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
						+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				sendKeys(employerNameB, "Employer Name B", empnm_b);
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
						+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				sendKeys(employerNameC, "Employer Name C", empnm_c);
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
						+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
				waitForLoadingToDisappear();
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
						+ "code from field is : "+actualPrincipalDiagnosis + " not : "
						+principaldiag);
			}else {
				try {
					throw new Exception("The principal diagnosis is empty or null");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisA + " not : "
						+othrdiag_a);
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisB + " not : "
						+othrdiag_b);
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisC + " not : "
						+othrdiag_c);
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisD + " not : "
						+othrdiag_d);
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisE + " not : "
						+othrdiag_e);
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisF + " not : "
						+othrdiag_f);
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisG + " not : "
						+othrdiag_g);
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisH + " not : "
						+othrdiag_h);
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisI + " not : "
						+othrdiag_i);
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisJ,
						"value");
				assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisJ + " not : "
						+othrdiag_j);
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisK + " not : "
						+othrdiag_k);
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisL + " not : "
						+othrdiag_l);
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisM + " not : "
						+othrdiag_m);
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisN + " not : "
						+othrdiag_n);
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisO + " not : "
						+othrdiag_o);
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisP + " not : "
						+othrdiag_p);
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisQ + " not : "
						+othrdiag_q);
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
				waitForLoadingToDisappear();
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
						+ "code from field is : "+actualAdmissionDiagnosis + " not : "
						+admsndiag);
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
						patrsndiag_a);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
						+patrsndiag_a);
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
						patrsndiag_b);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
						+patrsndiag_b);
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
						patrsndiag_c);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
						+patrsndiag_c);
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				sendKeys(ppsCode, "PPS Code", ppscd);
				String actualPPSCode = getAttribute(ppsCode, "value");
				assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
				+actualPPSCode+" not : "+ppscd);
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
						ecidiagcd_a);
				waitForLoadingToDisappear();
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisA + " not : "
						+ecidiagcd_a);
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
						ecidiagcd_b);
				waitForLoadingToDisappear();
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisB + " not : "
						+ecidiagcd_b);
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
						ecidiagcd_c);
				waitForLoadingToDisappear();
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisC + " not : "
						+ecidiagcd_c);
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				sendKeys(principleProcedureCode, "Principle Proc Code", principlepccd);
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, "value");
				assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
						+ "code from field is : "+actualPrincipleProcCode + " not : "
						+principlepccd);
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, "value");
				assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
						"The Principle Proc code from field is : "
						+actualPrincipleProcCodeDate + " not : "+principlepcdt);
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeA + " not : "
						+othrpccd_a);
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeB + " not : "
						+othrpccd_b);
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeC + " not : "
						+othrpccd_c);
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeD + " not : "
						+othrpccd_d);
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeE + " not : "
						+othrpccd_e);
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
				+actualAttendingNPI+" not : "+attndphynpi);
			}else {
				try {
					throw new Exception("The Attending NPI is null or empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
						attndphyqual1);
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
				+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
			}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
						attndphyqual2);
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
				+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
			}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
						attndphyfn);
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
				+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
			}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
						attndphyln);
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualAttendingPhysicianLastName+" not : "+attndphyln);
			}
			
			//Operating Physician NPI
			if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}
			
			//Operating Physician Qualifier1
			if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}
			
			//Operating Physician Qualifier2
			if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}
			
			//Operating Physician FirstName
			if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
				+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}
			
			//Operating Physician LasttName
			if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}
			
			//Other Physician NPI A
			if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				String actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}
			
			//Other Physician Qualifier1 A
			if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}
			
			//Other Physician Qualifier2 A
			if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}
			
			//Other Physician First Name A
			if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}
			
			//Other Physician LastName A
			if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}
			
			//Other Physician NPI B
			if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				String actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}
			
			//Other Physician Qualifier1 B
			if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}
			
			//Other Physician Qualifier2 B
			if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}
			
			//Other Physician First Name B
			if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}
			
			//Other Physician LastName B
			if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}
			
			//Remarks
			if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
				sendKeys(remarks, "Remarks", reMarks);
				String actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}
			
			//Taxonomy
			if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
				sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
				String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
				waitForLoadingToDisappear();
				WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
				click(taxonomy, form81taxonomy_a);
			}else {
				try {
					throw new Exception("Taxonmy is empty or null");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 81 A value
			if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				String actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}
			
			//Form 81 B Qualifier
			if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}
			
			//Form 81 B Taxonomy
			if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}
			
			//Form 81 B Value
			if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				String actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}
			
			//Form 81 C Qualifier
			if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}
			
			//Form 81 C Taxonomy
			if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}
			
			//Form 81 C Value
			if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				String actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}
			
			//Form 81 D Qualifier
			if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}
			
			//Form 81 D Taxonomy
			if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}
			
			//Form 81 D Value
			if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				String actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}
			
			//Received Date
			if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
				sendKeys(receivedDate, "Received Date", receveddate);
				String actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}
			
			click(submitButton, "Submit Claim");
			
			waitForLoadingToDisappear();
			
			String alertXpath = "//*[@role='alertdialog']";
			WebElement alertEle = driver.findElement(By.xpath(alertXpath));
			putStaticWait(2);
			waitUntilElementVisible(By.xpath(alertXpath), 20);
			String alerttext = getAttribute(alertEle,"aria-label");
			String alerttext1 = getAttribute(alertEle,"innerHTML");
			System.out.println(alerttext);
			System.out.println(alerttext1);
			alertEle.click();
			if(Objects.nonNull(alerttext)) {

				if(alerttext.contains("success") || alerttext.contains("Success") ) {
					report(LogStatus.PASS, "Successfully submitted");
					return myMCSNumber;
				}else {
					report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
					try {
						throw new CannotCreateClaimException("Not able to submit the "
								+ "claim due to error : "+alerttext);
					}catch(CannotCreateClaimException e) {
						report(LogStatus.FAIL, e.getMessage());
						e.printStackTrace();
						cancelClaim();
						report(LogStatus.WARNING,"Claim is cancelled");
						return "Not able to create claim";
					}
				}
			}else if(Objects.nonNull(alerttext1)) {
				if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
					report(LogStatus.PASS, "Successfully submitted");
					return myMCSNumber;
				}else {
					report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext1);
					try {
						throw new CannotCreateClaimException("Not able to submit the "
								+ "claim due to error : "+alerttext1);
					}catch(CannotCreateClaimException e) {
						report(LogStatus.FAIL, e.getMessage());
						e.printStackTrace();
						cancelClaim();
						report(LogStatus.WARNING,"Claim is cancelled");
						return "Not able to create claim";
					}
				}
			}
			
			
			
			waitForLoadingToDisappear();
			
			
		}else {
			report(LogStatus.FAIL,"Create UB-04 page is Not Displayed");
			try {
				throw new Exception("Create UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		return myMCSNumber;
	}

	public String updateAndSubmitUB04Claim(String className, String claimID) {
		
		boolean flag =  false;
		String myMCSNumber = dataMap.get("myMCSClaimNumber");
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
		String serviceLineNumber = dataMap.get("serviceLineNumber");
		String revcd = dataMap.get("revenueCode");
		String pccd = dataMap.get("serviceCode");
		String srvcdt = dataMap.get("serviceDate");
		String noofunits =dataMap.get("units");
		String charges = dataMap.get("totalCharges");
		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		filterWithMyMCSNumber(myMCSNumber);
		click(firstRecord, "");
		waitForLoadingToDisappear();
		click(updateButton, "Update");
		if(updateUB04Heading.isDisplayed()) {
			waitForLoadingToDisappear();
			report(LogStatus.PASS, "Update Popup is displayed.");
			if(Objects.nonNull(prvid) && !prvid.equals("")) {
				String actualProviderID = getAttribute(providerIDText, "value");
				if(!actualProviderID.equals(prvid)) {
					waitUntilClickable(providerSearchButton, 10);
					click(providerSearchButton, "Provider Search");
					
					//Provider selection
					if(providerSearchPopupHeading.isDisplayed()) {
						report(LogStatus.PASS,"Provider Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Provider Search popup is not displayed.");
					}
					
					sendKeys(providerSeachProviderID, "Provider ID", prvid);
					click(providerSearchSearcButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = providerSearchGridRow.replace("XX", "1");
					WebElement providerrow = driver.findElement(By.xpath(rownumber));
					click(providerrow, "First provider record");
					click(providerSearchSelectProviderButton, "Select Provider");
					waitForLoadingToDisappear();
					actualProviderID = getAttribute(providerIDText, "value");
					assertEquals(actualProviderID, prvid, "The provider id from field "
								+ "is : "+actualProviderID+" not : "+prvid);
					report(LogStatus.PASS, "Provider Id updated successfully.");
					
				}else {
					report(LogStatus.INFO, "Provider Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Provider id is null or empty.");
			}
			
			//Site Selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				String actualSiteID = getAttribute(siteIDText2, "value");
				if(!actualSiteID.equals(steid)) {
					waitForLoadingToDisappear();
					click(siteSelectionDropdown, "Site dropdown");
					String siteElement = dropdownOptions.replace("XX", steid);
					WebElement site_ele = driver.findElement(By.xpath(siteElement));
					click(site_ele, "Site");
					waitForLoadingToDisappear();
					actualSiteID = getAttribute(siteIDText2, "value");
					assertEquals(actualSiteID, steid,"The site id from field "
								+ "is : "+actualSiteID+" not : "+steid);
					report(LogStatus.PASS, "Site Id updated successfully.");
				}else {
					report(LogStatus.INFO, "Site Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Site id is null or empty.");
			}
			
			//Patient selection
			if(Objects.nonNull(patid) && !patid.equals("")) {
				String actualPatientID = getAttribute(patientIDText, "value");
				if(!actualPatientID.equals(patid)) {
					waitUntilClickable(patientSearchButton, 20);
					click(patientSearchButton, "Patient Search");
					if(patientSearchHeading.isDisplayed()) {
						report(LogStatus.PASS,"Patient Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Patient Search popup is not displayed.");
					}
					sendKeys(patientSearchPatientID, "Patient ID", patid);
					actualPatientID = getAttribute(patientSearchPatientID, "value");
					assertEquals(actualPatientID, patid, "The Patient Control Number from "
							+ "field is : "+actualPatientID+" not : "+patid);
					click(patientSearchSearchButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = patientSearchGridRow.replace("XX", "1");
					WebElement patientrow = driver.findElement(By.xpath(rownumber));
					click(patientrow, "First patient record");
					click(patientSearchSelectPatientButton, "Select Patient");
					waitForLoadingToDisappear();
				}else {
					report(LogStatus.INFO, "Patient Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient id is null or empty.");
			}
			
			//Patient Control Number
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				String actualPCN = getAttribute(patientControlNumber, "value");
				if(!actualPCN.equals(pcn)) {
					patientControlNumber.clear();
					sendKeys(patientControlNumber, "Patient Control Number", pcn);
					actualPCN = getAttribute(patientControlNumber, "value");
					assertEquals(actualPCN, pcn, "The Patient Control Number from field "
							+ "is : "+actualPCN+" not : "+pcn);
				}else {
					report(LogStatus.INFO, "Patient Control Number is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient control Number is null or empty.");
			}
			
			//Bill Type Speciality
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				String actualBillType = getAttribute(billType, "value");
				System.out.println(actualBillType+" : "+billtype);
				if(!actualBillType.equals(billtype)) {
					billType.clear();
					sendKeys(billType, "Bill Type", billtype);
					actualBillType = getAttribute(billType, "value");
					assertEquals(actualBillType, billtype, "The Patient Control Number "
							+ "from field is : "+actualBillType+" not : "+billtype);
				}else {
					report(LogStatus.INFO, "Billtype is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Bill Type is null or empty.");
			}
			
			//From and To period
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				if(!actualFromDate.equals(fromperiod) && !actualToDate.equals(toperiod)) {
					for(int i = 0 ; i < 10 ; i++)
						statementFromPeriod.sendKeys(Keys.BACK_SPACE);
					for(int i = 0 ; i < 10 ; i++)
						statementToPeriod.sendKeys(Keys.BACK_SPACE);
					sendKeys(statementFromPeriod, "From Date", fromperiod);
					sendKeys(statementToPeriod, "To Date", toperiod);
					actualFromDate = getAttribute(statementFromPeriod, "value");
					actualToDate = getAttribute(statementToPeriod, "value");
					assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
							+ "from field is : "+actualFromDate+" not : "+fromperiod);
					assertEquals(actualToDate, toperiod, "The Statement To Period  "
							+ "from field is : "+actualToDate+" not : "+toperiod);
				}else {
					report(LogStatus.INFO, "From Date or To Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"From period or To period is null or empty.");
			}
			
			//Admission Date
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				if(!actualAdmissionDate.equals(admsndt)) {
					for(int i = 0 ; i < 10 ; i++)
						admissionDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(admissionDate, "Admission Date", admsndt);
					actualAdmissionDate = getAttribute(admissionDate, "value");
					assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
							+" is : "+actualAdmissionDate+" not : "+admsndt);
				}else {
					report(LogStatus.INFO, "Admission Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission date is null or empty.");
			}
			
			//Admission Hour
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				String actualAdmissionHour = getText(admissionHourText);
				if(!actualAdmissionHour.equals(admsnhr)) {
					click(admissionHourDropdown,"Admission Hour");
					String admsnHour = dropdownOptions.replace("XX", admsnhr);
					WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
					waitUntilClickable(admisionHour, 10);
					click(admisionHour,"Admission Hour");
					actualAdmissionHour = getText(admissionHourText);
					assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
							+ "is : "+actualAdmissionHour+" not "+admsnhr);
				}else {
					report(LogStatus.INFO, "Admission Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission Hour is null or empty.");
			}
			
			//Reference Type
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				String actualReferenceType = getText(visitTypeText);
				if(!actualReferenceType.equals(reftype)) {
					click(visitTypeDropdown, "Visit Type");
					String refType = dropdownOptions.replace("XX", reftype);
					WebElement referenceType = driver.findElement(By.xpath(refType));
					waitUntilClickable(referenceType, 10);
					click(referenceType, reftype);
					actualReferenceType = getText(visitTypeText);
					assertEquals(actualReferenceType, reftype,"The Reference Type from field "
							+ "is : "+actualReferenceType+" not "+reftype);
				}else {
					report(LogStatus.INFO, "Reference type is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Type is empty or null.");
			}
			
			//Reference Source
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				String actualReferenceSource = getText(referenceSourceText);
				if(!actualReferenceSource.equals(refsrc)) {
					click(referenceSourceDropdown, "Visit Type");
					String refSrc = dropdownOptions.replace("XX", refsrc);
					WebElement referenceSource = driver.findElement(By.xpath(refSrc));
					waitUntilClickable(referenceSource, 10);
					click(referenceSource, refsrc);
					actualReferenceSource = getText(referenceSourceText);
					assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
							+ "is : "+actualReferenceSource+" not "+refsrc);
				}else {
					report(LogStatus.INFO, "Reference Source is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Source is empty or null.");
			}
			
			//Discharge Hour
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				String actualDischargeHour = getText(dischargeHourText);
				if(actualDischargeHour.equals(dschrghr)) {
					click(dischargeHour, "Visit Type");
					String dischrgHr = dropdownOptions.replace("XX", dschrghr);
					WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
					waitUntilClickable(dischargeHour, 10);
					click(dischargeHour, dschrghr);
					actualDischargeHour = getText(dischargeHourText);
					assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
							+ "is : "+actualDischargeHour+" not "+dschrghr);
				}else {
					report(LogStatus.INFO, "Discharge Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Hour is empty or null.");
			}
			
			//Discharge Status
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				String actualDischargeStatus = getText(dischargeStatusText);
				if(!actualDischargeStatus.equals(dschrgsts)) {
					click(dischargeStatus, "Visit Type");
					String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
					WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
					waitUntilClickable(dischargeStatus, 10);
					click(dischargeStatus, dschrgsts);
					actualDischargeStatus = getText(dischargeStatusText);
					assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
							+ "is : "+actualDischargeStatus+" not "+dschrgsts);
				}else {
					report(LogStatus.INFO, "Discharge Status is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Status is empty or null.");
			}
			
			//Form 18
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				if(!actualForm18.equals(actform_18)) {
					form18.clear();
					sendKeys(form18, "Form 18", form_18);
					actualForm18 = getAttribute(form18, "value");
					assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
							actualForm18+" not : "+actform_18);
				}else {
					report(LogStatus.INFO, "Form 18 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 18 is empty or null.");
			}
			
			//Form 19
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				if(!actualForm19.equals(actform_19)) {
					form19.clear();
					sendKeys(form19, "Form 19", form_19);
					actualForm19 = getAttribute(form19, "value");
					assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
							actualForm19+" not : "+actform_19);
				}else {
					report(LogStatus.INFO, "Form 19 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 19 is empty or null.");
			}
			
			//Form 20
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				if(!actualForm20.equals(actform_20)) {
					form20.clear();
					sendKeys(form20, "Form 20", form_20);
					actualForm20 = getAttribute(form20, "value");
					assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
							actualForm20+" not : "+actform_20);
				}else {
					report(LogStatus.INFO, "Form 20 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 20 is empty or null.");
			}
			
			//Form 21
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				if(!actualForm21.equals(actform_21)) {
					form21.clear();
					sendKeys(form21, "Form 21", form_21);
					actualForm21 = getAttribute(form21, "value");
					assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
							actualForm21+" not : "+actform_21);
				}else {
					report(LogStatus.INFO, "Form 21 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 21 is empty or null.");
			}
			
			//Form 22
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				if(!actualForm22.equals(actform_22)) {
					form22.clear();
					sendKeys(form22, "Form 22", form_22);
					actualForm22 = getAttribute(form22, "value");
					assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
							actualForm22+" not : "+actform_22);
				}else {
					report(LogStatus.INFO, "Form 22 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 22 is empty or null.");
			}
			
			//Form 23
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				if(!actualForm23.equals(actform_23)) {
					form23.clear();
					sendKeys(form23, "Form 23", form_23);
					actualForm23 = getAttribute(form23, "value");
					assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
							actualForm23+" not : "+actform_23);
				}else {
					report(LogStatus.INFO, "Form 23 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 23 is empty or null.");
			}
			
			//Form 24
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				if(!actualForm24.equals(actform_24)) {
					form24.clear();
					sendKeys(form24, "Form 24", form_24);
					actualForm24 = getAttribute(form24, "value");
					assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
							actualForm24+" not : "+actform_24);
				}else {
					report(LogStatus.INFO, "Form 24 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 24 is empty or null.");
			}
			
			//Form 25
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				if(!actualForm25.equals(actform_25)) {
					form25.clear();
					sendKeys(form25, "Form 25", form_25);
					actualForm25 = getAttribute(form25, "value");
					assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
							actualForm25+" not : "+actform_25);
				}else {
					report(LogStatus.INFO, "Form 25 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 25 is empty or null.");
			}
			
			//Form 26
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				if(!actualForm26.equals(actform_26)) {
					form26.clear();
					sendKeys(form26, "Form 26", form_26);
					actualForm26 = getAttribute(form26, "value");
					assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
							actualForm26+" not : "+actform_26);
				}else {
					report(LogStatus.INFO, "Form 26 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 26 is empty or null.");
			}
			
			//Form 27
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				if(!actualForm27.equals(actform_27)) {
					form27.clear();
					sendKeys(form27, "Form 27", form_27);
					actualForm27 = getAttribute(form27, "value");
					assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
							actualForm27+" not : "+actform_27);
				}else {
					report(LogStatus.INFO, "Form 27 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 27 is empty or null.");
			}
			
			//Form 28
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				if(!actualForm28.equals(actform_28)) {
					form28.clear();
					sendKeys(form28, "Form 28", form_28);
					actualForm28 = getAttribute(form28, "value");
					assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
							actualForm28+" not : "+actform_28);
				}else {
					report(LogStatus.INFO, "Form 28 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 28 is empty or null.");
			}
			
			//Accident State
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				if(!actualAccidentState.equals(accdtstate)) {
					accidentState.clear();
					sendKeys(accidentState, "Accident State", accdtstate);
					actualAccidentState = getAttribute(accidentState, "value");
					assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
							actualAccidentState+" not : "+actaccdtstate);
				}else {
					report(LogStatus.INFO, "Accident State is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Accident State is empty or null.");
			}
			
			//Form 31 A
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				if(!actualform31aoccurancecode.equals(actform31occcd_a) && 
						!actualform31adate.equals(form31occdt_a)) {
					form31OccuranceCodeA.clear();
					sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
					actualform31aoccurancecode = 
							getAttribute(form31OccuranceCodeA, "value");
					assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31aoccurancecode+
							" not : "+actform31occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
					actualform31adate = 
							getAttribute(form31OccuranceDateA, "value");
					assertEquals(actualform31adate, form31occdt_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31adate+
							" not : "+form31occdt_a);
				}else {
					report(LogStatus.INFO, "Form 31 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 A code or date is empty or null.");
			}
			
			//Form 31 B
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				if(!actualform31boccurancecode.equals(actform31occcd_b) && 
						!actualform31bdate.equals(form31occdt_b)) {
					form31OccuranceCodeB.clear();
					sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
					actualform31boccurancecode = 
							getAttribute(form31OccuranceCodeB, "value");
					assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31boccurancecode+
							" not : "+actform31occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
					actualform31bdate = 
							getAttribute(form31OccuranceDateB, "value");
					assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31bdate+
							" not : "+form31occdt_b);
				}else {
					report(LogStatus.INFO, "Form 31 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 B code or date is empty or null.");
			}
			
			//Form 32 A
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				if(!actualform32aoccurancecode.equals(actform32occcd_a) && 
						!actualform32adate.equals(form32occdt_a)) {
					form32OccuranceCodeA.clear();
					sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
					actualform32aoccurancecode = 
							getAttribute(form32OccuranceCodeA, "value");
					assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32aoccurancecode+
							" not : "+actform32occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
					actualform32adate = 
							getAttribute(form32OccuranceDateA, "value");
					assertEquals(actualform32adate, form32occdt_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32adate+
							" not : "+form32occdt_a);
				}else {
					report(LogStatus.INFO, "Form 32 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 A code or date is empty or null.");
			}
			
			//Form 32 B
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				if(!actualform32boccurancecode.equals(actform32occcd_b) && 
						!actualform32bdate.equals(form32occdt_b)) {
					form32OccuranceCodeB.clear();
					sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
					actualform32boccurancecode = 
							getAttribute(form32OccuranceCodeB, "value");
					assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32boccurancecode+
							" not : "+actform32occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
					actualform32bdate = 
							getAttribute(form32OccuranceDateB, "value");
					assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32bdate+
							" not : "+form32occdt_b);
				}else {
					report(LogStatus.INFO, "Form 32 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 B code or date is empty or null.");
			}
			
			//Form 33 A
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form33occcd_a;
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				if(!actualform33aoccurancecode.equals(actform33occcd_a) && 
						!actualform33adate.equals(form33occdt_a)) {
					form33OccuranceCodeA.clear();
					sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
					actualform33aoccurancecode = 
							getAttribute(form33OccuranceCodeA, "value");
					assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33aoccurancecode+
							" not : "+actform33occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
					actualform33adate = 
							getAttribute(form33OccuranceDateA, "value");
					assertEquals(actualform33adate, form33occdt_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33adate+
							" not : "+form33occdt_a);
				}else {
					report(LogStatus.INFO, "Form 33 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 A code or date is empty or null.");
			}
			
			//Form 33 B
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				if(!actualform33boccurancecode.equals(actform33occcd_b) && 
						!actualform33bdate.equals(form33occdt_b)) {
					form33OccuranceCodeB.clear();
					sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
					actualform33boccurancecode = 
							getAttribute(form33OccuranceCodeB, "value");
					assertEquals(actualform33boccurancecode, actform33occcd_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33boccurancecode+
							" not : "+actform33occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
					actualform33bdate = 
							getAttribute(form33OccuranceDateB, "value");
					assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33bdate+
							" not : "+form33occdt_b);
				}else {
					report(LogStatus.INFO, "Form 33 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 B code or date is empty or null.");
			}
			
			//Form 34 A
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				if(!actualform34aoccurancecode.equals(actform34occcd_a) && 
						!actualform34adate.equals(form34occdt_a)) {
					form34OccuranceCodeA.clear();
					sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
					actualform34aoccurancecode = 
							getAttribute(form34OccuranceCodeA, "value");
					assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34aoccurancecode+
							" not : "+actform34occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
					actualform34adate = 
							getAttribute(form34OccuranceDateA, "value");
					assertEquals(actualform34adate, form34occdt_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34adate+
							" not : "+form34occdt_a);
				}else {
					report(LogStatus.INFO, "Form 34 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 A code or date is empty or null.");
			}
			
			//Form 34 B
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				if(!actualform34boccurancecode.equals(actform34occcd_b) && 
						!actualform34bdate.equals(form34occdt_b)) {
					form34OccuranceCodeB.clear();
					sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
					actualform34boccurancecode = 
							getAttribute(form34OccuranceCodeB, "value");
					assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34boccurancecode+
							" not : "+actform34occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
					actualform34bdate = 
							getAttribute(form34OccuranceDateB, "value");
					assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34bdate+
							" not : "+form34occdt_b);
				}else {
					report(LogStatus.INFO, "Form 34 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 B code or date is empty or null.");
			}
			
			//Form 35 A
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				String actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				if(!actualform35aoccurancecode.equals(actform35occcd_a) && 
						!actualform35afromdate.equals(form35occfrdt_a) && 
						!actualform35atodate.equals(form35occtodt_a)) {
					form35OccuranceSpanCodeA.clear();
					sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
					actualform35aoccurancecode = 
							getAttribute(form35OccuranceSpanCodeA, "value");
					assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35aoccurancecode+
							" not : "+actform35occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
					actualform35afromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35afromdate+
							" not : "+form35occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
							form35occtodt_a);
					actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35atodate+
							" not : "+form35occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 35 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 A code or date is empty or null.");
			}
			
			//Form 35 B
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				if(!actualform35boccurancecode.equals(actform35occcd_b) && 
						!actualform35bfromdate.equals(form35occfrdt_b) && 
						!actualform35btodate.equals(form35occtodt_b)) {
					form35OccuranceSpanCodeB.clear();
					sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
					actualform35boccurancecode = 
							getAttribute(form35OccuranceSpanCodeB, "value");
					assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35boccurancecode+
							" not : "+actform35occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
					actualform35bfromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35bfromdate+
							" not : "+form35occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
							form35occtodt_b);
					actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35btodate+
							" not : "+form35occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 35 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 B code or date is empty or null.");
			}
			
			//Form 36 A
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				String actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				if(!actualform36aoccurancecode.equals(actform36occcd_a) && 
						!actualform36afromdate.equals(form36occfrdt_a) && 
						!actualform36atodate.equals(form36occtodt_a)) {
					form36OccuranceSpanCodeA.clear();
					sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
					actualform36aoccurancecode = 
							getAttribute(form36OccuranceSpanCodeA, "value");
					assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36aoccurancecode+
							" not : "+actform36occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
					actualform36afromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36afromdate+
							" not : "+form36occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
							form36occtodt_a);
					actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36atodate+
							" not : "+form36occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 36 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 A code or date is empty or null.");
			}
			
			//Form 36 B
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				if(!actualform36boccurancecode.equals(actform36occcd_b) && 
						!actualform36bfromdate.equals(form36occfrdt_b) && 
						!actualform36btodate.equals(form36occtodt_b)) {
					form36OccuranceSpanCodeB.clear();
					sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
					actualform36boccurancecode = 
							getAttribute(form36OccuranceSpanCodeB, "value");
					assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36boccurancecode+
							" not : "+actform36occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
					actualform36bfromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36bfromdate+
							" not : "+form36occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
							form36occtodt_b);
					actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36btodate+
							" not : "+form36occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 36 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 B code or date is empty or null.");
			}
			
			//Form 39 A
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				if(!actualform39avalcd.equals(actform39avalcd) && 
						!actualform39avalcdamt.equals(form39valcdamt_a)) {
							form39ValueCodeA.clear();
							sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
							actualform39avalcd = 
									getAttribute(form39ValueCodeA, "value");
							assertEquals(actualform39avalcd, actform39avalcd, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcd+
									" not : "+actform39avalcd);
							
							form39ValueCodeAmountA.clear();
							sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
							actualform39avalcdamt = 
									getAttribute(form39ValueCodeAmountA, "value");
							assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcdamt+
									" not : "+form39valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 39 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 A Code or Amount is empty or null");
			}
			
			//Form 39 B
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				if(!actualform39bvalcd.equals(actform39bvalcd) && 
						!actualform39bvalcdamt.equals(form39valcdamt_b)) {
							form39ValueCodeB.clear();
							sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
							actualform39bvalcd = 
									getAttribute(form39ValueCodeB, "value");
							assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcd+
									" not : "+actform39bvalcd);
							
							form39ValueCodeAmountB.clear();
							sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
							actualform39bvalcdamt = 
									getAttribute(form39ValueCodeAmountB, "value");
							assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcdamt+
									" not : "+form39valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 39 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 B Code or Amount is empty or null");
			}
			
			//Form 39 C
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_a;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				if(!actualform39cvalcd.equals(actform39cvalcd) && 
						!actualform39cvalcdamt.equals(form39valcdamt_c)) {
							form39ValueCodeC.clear();
							sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
							actualform39cvalcd = 
									getAttribute(form39ValueCodeC, "value");
							assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcd+
									" not : "+actform39cvalcd);
							
							form39ValueCodeAmountC.clear();
							sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
							actualform39cvalcdamt = 
									getAttribute(form39ValueCodeAmountC, "value");
							assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcdamt+
									" not : "+form39valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 39 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 C Code or Amount is empty or null");
			}
			
			//Form 39 D
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				if(!actualform39dvalcd.equals(actform39dvalcd) && 
						!actualform39dvalcdamt.equals(form39valcdamt_d)) {
							form39ValueCodeD.clear();
							sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
							actualform39dvalcd = 
									getAttribute(form39ValueCodeD, "value");
							assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcd+
									" not : "+actform39dvalcd);
							
							form39ValueCodeAmountD.clear();
							sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
							actualform39dvalcdamt = 
									getAttribute(form39ValueCodeAmountD, "value");
							assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcdamt+
									" not : "+form39valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 39 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 D Code or Amount is empty or null");
			}
			
			//Form 40 A
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				if(!actualform40avalcd.equals(actform40avalcd) && 
						!actualform40avalcdamt.equals(form40valcdamt_a)) {
							form40ValueCodeA.clear();
							sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
							actualform40avalcd = 
									getAttribute(form40ValueCodeA, "value");
							assertEquals(actualform40avalcd, actform40avalcd, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcd+
									" not : "+actform40avalcd);
							
							form40ValueCodeAmountA.clear();
							sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
							actualform40avalcdamt = 
									getAttribute(form40ValueCodeAmountA, "value");
							assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcdamt+
									" not : "+form40valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 40 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 A Code or Amount is empty or null");
			}
			
			//Form 40 B
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				if(!actualform40bvalcd.equals(actform40bvalcd) && 
						!actualform40bvalcdamt.equals(form40valcdamt_b)) {
							form40ValueCodeB.clear();
							sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
							actualform40bvalcd = 
									getAttribute(form40ValueCodeB, "value");
							assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcd+
									" not : "+actform40bvalcd);
							
							form40ValueCodeAmountB.clear();
							sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
							actualform40bvalcdamt = 
									getAttribute(form40ValueCodeAmountB, "value");
							assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcdamt+
									" not : "+form40valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 40 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 B Code or Amount is empty or null");
			}
			
			//Form 40 C
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_a;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				if(!actualform40cvalcd.equals(actform40cvalcd) && 
						!actualform40cvalcdamt.equals(form40valcdamt_c)) {
							form40ValueCodeC.clear();
							sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
							actualform40cvalcd = 
									getAttribute(form40ValueCodeC, "value");
							assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcd+
									" not : "+actform40cvalcd);
							
							form40ValueCodeAmountC.clear();
							sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
							actualform40cvalcdamt = 
									getAttribute(form40ValueCodeAmountC, "value");
							assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcdamt+
									" not : "+form40valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 40 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 C Code or Amount is empty or null");
			}
			
			//Form 40 D
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				if(!actualform40dvalcd.equals(actform40dvalcd) && 
						!actualform40dvalcdamt.equals(form40valcdamt_d)) {
							form40ValueCodeD.clear();
							sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
							actualform40dvalcd = 
									getAttribute(form40ValueCodeD, "value");
							assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcd+
									" not : "+actform40dvalcd);
							
							form40ValueCodeAmountD.clear();
							sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
							actualform40dvalcdamt = 
									getAttribute(form40ValueCodeAmountD, "value");
							assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcdamt+
									" not : "+form40valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 40 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 D Code or Amount is empty or null");
			}
			
			//Form 41 A
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				if(!actualform41avalcd.equals(actform41avalcd) && 
						!actualform41avalcdamt.equals(form41valcdamt_a)) {
							form41ValueCodeA.clear();
							sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
							actualform41avalcd = 
									getAttribute(form41ValueCodeA, "value");
							assertEquals(actualform41avalcd, actform41avalcd, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcd+
									" not : "+actform41avalcd);
							
							form41ValueCodeAmountA.clear();
							sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
							actualform41avalcdamt = 
									getAttribute(form41ValueCodeAmountA, "value");
							assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcdamt+
									" not : "+form41valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 41 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 A Code or Amount is empty or null");
			}
			
			//Form 41 B
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				if(!actualform41bvalcd.equals(actform41bvalcd) && 
						!actualform41bvalcdamt.equals(form41valcdamt_b)) {
							form41ValueCodeB.clear();
							sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
							actualform41bvalcd = 
									getAttribute(form41ValueCodeB, "value");
							assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcd+
									" not : "+actform41bvalcd);
							
							form41ValueCodeAmountB.clear();
							sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
							actualform41bvalcdamt = 
									getAttribute(form41ValueCodeAmountB, "value");
							assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcdamt+
									" not : "+form41valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 41 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 B Code or Amount is empty or null");
			}
			
			//Form 41 C
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_a;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				if(!actualform41cvalcd.equals(actform41cvalcd) && 
						!actualform41cvalcdamt.equals(form41valcdamt_c)) {
							form41ValueCodeC.clear();
							sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
							actualform41cvalcd = 
									getAttribute(form41ValueCodeC, "value");
							assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcd+
									" not : "+actform41cvalcd);
							
							form41ValueCodeAmountC.clear();
							sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
							actualform41cvalcdamt = 
									getAttribute(form41ValueCodeAmountC, "value");
							assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcdamt+
									" not : "+form41valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 41 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 C Code or Amount is empty or null");
			}
			
			//Form 41 D
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && !form41valcdamt_d.equals("")) {
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				if(!actualform41dvalcd.equals(actform41dvalcd) && 
						!actualform41dvalcdamt.equals(form41valcdamt_d)) {
							form41ValueCodeD.clear();
							sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
							actualform41dvalcd = 
									getAttribute(form41ValueCodeD, "value");
							assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcd+
									" not : "+actform41dvalcd);
							
							form41ValueCodeAmountD.clear();
							sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
							actualform41dvalcdamt = 
									getAttribute(form41ValueCodeAmountD, "value");
							assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcdamt+
									" not : "+form41valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 41 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 D Code or Amount is empty or null");
			}
			
			List<Map<String, String>> serviceDataMAP = ExcelUtil.getTestCasesDataInMap
			("testData//AlphaPlusTestData.xlsx", className, "claimID", claimID);
			Map< String, String> service ;
			
			if(Objects.nonNull(serviceLineNumber) && !serviceLineNumber.equals("")) {
				String numberOfLines = serviceLine.replace("[XX]", "");
				int lineCount= driver.findElements(By.xpath(numberOfLines)).size();
				if(Integer.parseInt(serviceLineNumber) <= lineCount) {
					String lineNumber = serviceLine.replace("XX", serviceLineNumber);
					WebElement serviceLine = driver.findElement(By.xpath(lineNumber));
					serviceLine.click();
					if(modifyService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service modified successfully.");
					}else {
						report(LogStatus.FAIL,"Service not modified.");
					}
				}else {
					report(LogStatus.INFO,"The Service line numner is not valid");
				}
				
			}else {
				report(LogStatus.WARNING, "Service Line Number is empty or null");
			}
			
			//Previous Payer
				
			//Health Plan ID A
			if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
				String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
				if(!actualHealthPlanIDA.equals(healthplanid_a)) {
					healthPlanIDA.clear();
					sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
					actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
					assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
							+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);
				}else {
					report(LogStatus.INFO,"Health Plan ID A numner is not valid");
				}						
			}else {
				report(LogStatus.WARNING, "Health Plan ID A is empty or null");
			}

			
			//REL INFO A
			if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
				String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
				String[] relInfoAData = relInfoAClass.split(" ");
				String actualRelInfoA = "";
				for(String s : relInfoAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualRelInfoA = s;
					}
				}
				if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box checked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not checked");
					}
					
				}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
						relinfo_a.equals("NO")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(!actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box unchecked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
					}
					
				}
			}
			
			//ASN BEN A
			if(Objects.nonNull(benfitassignment_a) && 
					!benfitassignment_a.equals("")) {
				String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
				String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
				String actualBenfitAssignmentA = "";
				for(String s : benfitAssignmentAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualBenfitAssignmentA = s;
					}
				}
				if(actualBenfitAssignmentA.equals("") && 
						benfitassignment_a.equals("YES")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box checked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not checked");
					}
					
				}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
						benfitassignment_a.equals("NO")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box unchecked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
					}
					
				}
			}
			
			//Prior payment amount
			if(Objects.nonNull(priorpaymentamt_a) && 
					!priorpaymentamt_a.equals("")) {
				priorPaymentAmountA.clear();
				sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
				String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
				assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
						+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
						" not : "+priorpaymentamt_a);
			}
			
			//EST Due Amount
			if(Objects.nonNull(estamountdue_a) && 
					!estamountdue_a.equals("")) {
				estAmountDueA.clear();
				sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
				String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
				assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
						+"from prior payment amount field A is : "+actualESTAmountDueA+
						" not : "+estamountdue_a);
			}
			
			//primary payer
			if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
				click(payerTypeDrodownA, "Payer Type");
				switch(payertype_a) {
				case "MEDICARE":{
					click(payerTypeMedicareOption, "MEDICARE");
					break;
				}
				case "NON MEDICARE":{
					click(payerTypeNonMedicareOption, "Non - MEDICARE");
					break;
				}
				default:
					report(LogStatus.WARNING, "Payer Type is not valid");
					
				}
			}
			
			//Additional Previous payer
			if(Objects.nonNull(noOfPreviousPayer) && 
					!noOfPreviousPayer.equals("")) {
				if(Integer.parseInt(noOfPreviousPayer) > 1) {
					for(int i = 1; i<=2; i++) {
						if(addPreviousPayerButton.isEnabled())
							click(addPreviousPayerButton, "Add previouss Payer");
					}
					if(Integer.parseInt(noOfPreviousPayer) == 2) {
						driver.findElement(By.xpath("(//span[contains"
								+ "(text(),'Remove')]/parent::button)[2]")).click();
						waitUntilClickable(unsavedChagesOK, 10);
						click(unsavedChagesOK, "OK");
						modifyPrimaryPayeB(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b);
					}else if(Integer.parseInt(noOfPreviousPayer) == 3) {
						modifyPrimaryPayeBAndC(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b, payer_c, healthplanid_c, relinfo_c, 
								benfitassignment_c, priorpaymentamt_c, estamountdue_c, 
								payertype_c);
						
					}
				}else {
					for(int i = 1 ; i <= 2 ; i++) {
						try {
							driver.findElement(By.xpath("(//span[contains"
									+ "(text(),'Remove')]/parent::button)[2]")).click();
							waitUntilClickable(unsavedChagesOK, 10);
							click(unsavedChagesOK, "OK");
						}catch (NoSuchElementException e) {
							break;
						}
					}
				}
			}else {
				report(LogStatus.INFO,"No Of previous payer is empty or null");
			}
			
			//Billing Provider NPI
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				report(LogStatus.WARNING, "Billing Provider NPI is empty or null");
			}
			
			//Other Provider ID
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				if(!actualOtherProviderID.equals(othrprvid)) {
					otherProviderID.clear();
					sendKeys(otherProviderID, "Other Provider ID", othrprvid);
					actualOtherProviderID = getAttribute(otherProviderID, "value");
					assertEquals(actualOtherProviderID, othrprvid, "The value from other"
							+ " Provider ID field is : "+actualOtherProviderID+" not "
									+ othrprvid);
				}else {
					report(LogStatus.INFO,"Other Provider ID is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Other Provider ID is empty or null");
			}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				if(!actualInsuredNameA.equals(insrdname_a)) {
					insuredNameA.clear();
					sendKeys(insuredNameA, "Insured Name A", insrdname_a);
					actualInsuredNameA = getAttribute(insuredNameA, "value");
					assertEquals(actualInsuredNameA, insrdname_a, "The value from "
							+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
				}else {
					report(LogStatus.INFO, "Insured Name A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name A is empty or null");
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				if(!actualInsuredNameB.equals(insrdname_b)) {
					insuredNameB.clear();
					sendKeys(insuredNameB, "Insured Name B", insrdname_b);
					actualInsuredNameB = getAttribute(insuredNameB, "value");
					assertEquals(actualInsuredNameB, insrdname_b, "The value from "
							+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
				}else {
					report(LogStatus.INFO, "Insured Name B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name B is empty or null");
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				if(!actualInsuredNameC.equals(insrdname_c)) {
					insuredNameC.clear();
					sendKeys(insuredNameC, "Insured Name C", insrdname_c);
					actualInsuredNameC = getAttribute(insuredNameC, "value");
					assertEquals(actualInsuredNameC, insrdname_c, "The value from "
							+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
				}else {
					report(LogStatus.INFO, "Insured Name C is same and not changed");
				}
			
			}else {
				report(LogStatus.WARNING, "Insured Name C is empty or null");
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				if(!actualPatRelInsuredA.equals(patreltoinsure_a)) {
					patientRelatedToInsuranceA.clear();
					sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
							patreltoinsure_a);
					actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
							"value");
					if(patreltoinsure_a.length()>2)
						assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
							+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
							patreltoinsure_a);
					else
						assertEquals(actualPatRelInsuredA, patreltoinsure_a, "The value from "
								+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
								patreltoinsure_a);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure A is empty or null");
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				if(!actualPatRelInsuredB.equals(patreltoinsure_b)) {
					patientRelatedToInsuranceB.clear();
					sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
							patreltoinsure_b);
					actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
							"value");
					if(patreltoinsure_b.length()>2)
						assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
							patreltoinsure_b);
					else
						assertEquals(actualPatRelInsuredB, patreltoinsure_b, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
								patreltoinsure_b);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure B is empty or null");
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				if(!actualPatRelInsuredC.equals(patreltoinsure_c)) {
					patientRelatedToInsuranceC.clear();
					sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
							patreltoinsure_c);
					actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
							"value");
					if(patreltoinsure_c.length()>2)
						assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
							patreltoinsure_c);
					else
						assertEquals(actualPatRelInsuredC, patreltoinsure_c, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
								patreltoinsure_c);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure C is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure C is empty or null");
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				if(!actualInsuredUniqueIDA.equals(insuredunqid_a)) {
					insuredsUniqueIDA.clear();
					sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
							insuredunqid_a);
					actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
							"value");
					assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
							+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
							insuredunqid_a);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID A is empty or null.");
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				if(!actualInsuredUniqueIDB.equals(insuredunqid_b)) {
					insuredsUniqueIDB.clear();
					sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
							insuredunqid_b);
					actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
							"value");
					assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
							insuredunqid_b);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID B is empty or null.");
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				if(!actualInsuredUniqueIDC.equals(insuredunqid_c)) {
					insuredsUniqueIDC.clear();
					sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
							insuredunqid_c);
					actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
							"value");
					assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
							insuredunqid_c);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID C is empty or null.");
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				if(!actualInsuredGroupNameA.equals(insrdgrpnm_a)) {
					insuredGroupNameA.clear();
					sendKeys(insuredGroupNameA, "Insured Unique ID A", 
							insrdgrpnm_a);
					actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
							"value");
					assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
							+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
							insrdgrpnm_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Name A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name A is empty or null.");
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				if(!actualInsuredGroupNameB.equals(insrdgrpnm_b)) {
					insuredGroupNameB.clear();
					sendKeys(insuredGroupNameB, "Insured Unique ID B", 
							insrdgrpnm_b);
					actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
							"value");
					assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
							+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
							insrdgrpnm_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Name B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name B is empty or null.");
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				if(!actualInsuredGroupNameC.equals(insrdgrpnm_c)) {
					insuredGroupNameC.clear();
					sendKeys(insuredGroupNameC, "Insured Unique ID C", 
							insrdgrpnm_c);
					actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
							"value");
					assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
							+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
							insrdgrpnm_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Name C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name C is empty or null.");
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				if(!actualInsuredGroupNumberA.equals(insrdgrpno_a)) {
					insuredGroupNumberA.clear();
					sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
							insrdgrpno_a);
					actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
							"value");
					assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
							+ "Insured Number A is : "+actualInsuredGroupNumberA+" not "+
							insrdgrpno_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number A is empty or null.");
			}
			
			//Insured Group Number B
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				if(!actualInsuredGroupNumberB.equals(insrdgrpno_a)) {
					insuredGroupNumberB.clear();
					sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
							insrdgrpno_b);
					actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
							"value");
					assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
							+ "Insured Number B is : "+actualInsuredGroupNumberB+" not "+
							insrdgrpno_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number B is empty or null.");
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				if(!actualInsuredGroupNumberC.equals(insrdgrpno_c)) {
					insuredGroupNumberC.clear();
					sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
							insrdgrpno_c);
					actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
							"value");
					assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
							+ "Insured Number C is : "+actualInsuredGroupNumberC+" not "+
							insrdgrpno_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number C is empty or null.");
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				if(!actualTreatmentAuthCodeA.equals(txauthcd_a)) {
					treatmentAuthCodesA.clear();
					sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
					actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
							"value");
					assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code A is empty or null.");
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				if(!actualTreatmentAuthCodeB.equals(txauthcd_b)) {
					treatmentAuthCodesB.clear();
					sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
					actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
							"value");
					assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code B is empty or null.");
			}
			
			//Treatment Authorization code C
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				if(!actualTreatmentAuthCodeC.equals(txauthcd_c)) {
					treatmentAuthCodesC.clear();
					sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
					actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
							"value");
					assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code C is empty or null.");
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				if(!actualResubmissionClaimA.equals(refclm_a)) {
					resubmissionClaimNumberA.clear();
					sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
					actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
							"value");
					assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
				}else {
					report(LogStatus.INFO, "The Document Control Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number A is empty or null.");
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				if(!actualResubmissionClaimB.equals(refclm_a)) {
					resubmissionClaimNumberB.clear();
					sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
					actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
							"value");
					assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
				}else {
					report(LogStatus.INFO, "The Document Control Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number B is empty or null.");
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				if(!actualResubmissionClaimC.equals(refclm_c)) {
					resubmissionClaimNumberC.clear();
					sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
					actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
							"value");
					assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
				}else {
					report(LogStatus.INFO, "The Document Control Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number C is empty or null.");
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				if(!actualEmployerNameA.equals(empnm_a)) {
					employerNameA.clear();
					sendKeys(employerNameA, "Employer Name A", empnm_a);
					actualEmployerNameA = getAttribute(employerNameA, 
							"value");
					assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
							+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
				}else {
					report(LogStatus.INFO, "The Employer A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer A is empty or null.");
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				if(!actualEmployerNameB.equals(empnm_b)) {
					employerNameB.clear();
					sendKeys(employerNameB, "Employer Name B", empnm_b);
					actualEmployerNameB = getAttribute(employerNameB, 
							"value");
					assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
							+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
				}else {
					report(LogStatus.INFO, "The Employer B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer B is empty or null.");
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				if(!actualEmployerNameC.equals(empnm_c)) {
					employerNameC.clear();
					sendKeys(employerNameC, "Employer Name C", empnm_c);
					actualEmployerNameC = getAttribute(employerNameC, 
							"value");
					assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
							+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
				}else {
					report(LogStatus.INFO, "The Employer C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer C is empty or null.");
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}else {
				report(LogStatus.WARNING, "Diagnosis Version is empty or null.");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				if(!actualPrincipalDiagnosis.equals(principaldiag)) {
					principalDIagnosis.clear();
					sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
					waitForLoadingToDisappear();
					actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
							"value");
					assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
							+ "code from field is : "+actualPrincipalDiagnosis + " not : "
							+principaldiag);
				}else {
					report(LogStatus.INFO, "The Principal Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The principal diagnosis is empty or null");
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				if(!actualOtherDiagnosisA.equals(othrdiag_a)) {
					otherDiagnosisA.clear();
					sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
					waitForLoadingToDisappear();
					actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
							"value");
					assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisA + " not : "
							+othrdiag_a);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis A is empty or null");
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_b) && !othrdiag_b.equals("")) {
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				if(!actualOtherDiagnosisB.equals(othrdiag_b)) {
					otherDiagnosisB.clear();
					sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
					waitForLoadingToDisappear();
					actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisB + " not : "
							+othrdiag_b);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis B is empty or null");
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				if(!actualOtherDiagnosisC.equals(othrdiag_c)) {
					otherDiagnosisC.clear();
					sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
					waitForLoadingToDisappear();
					actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
							"value");
					assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisC + " not : "
							+othrdiag_c);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis C is empty or null");
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				if(!actualOtherDiagnosisD.equals(othrdiag_d)) {
					otherDiagnosisD.clear();
					sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
					waitForLoadingToDisappear();
					actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
							"value");
					assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisD + " not : "
							+othrdiag_d);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis D is empty or null");
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				if(!actualOtherDiagnosisE.equals(othrdiag_e)) {
					otherDiagnosisE.clear();
					sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
					waitForLoadingToDisappear();
					actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
							"value");
					assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisE + " not : "
							+othrdiag_e);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis E is empty or null");
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				if(!actualOtherDiagnosisF.equals(othrdiag_f)) {
					otherDiagnosisF.clear();
					sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
					waitForLoadingToDisappear();
					actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
							"value");
					assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisF + " not : "
							+othrdiag_f);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis F is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis F is empty or null");
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				if(!actualOtherDiagnosisG.equals(othrdiag_g)) {
					otherDiagnosisG.clear();
					sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
					waitForLoadingToDisappear();
					actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
							"value");
					assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisG + " not : "
							+othrdiag_g);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis G is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis G is empty or null");
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				if(!actualOtherDiagnosisH.equals(othrdiag_h)) {
					otherDiagnosisH.clear();
					sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
					waitForLoadingToDisappear();
					actualOtherDiagnosisH = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisH + " not : "
							+othrdiag_h);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis H is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis H is empty or null");
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				if(!actualOtherDiagnosisI.equals(othrdiag_i)) {
					otherDiagnosisI.clear();
					sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
					waitForLoadingToDisappear();
					actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
							"value");
					assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisI + " not : "
							+othrdiag_i);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis I is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis I is empty or null");
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisJ,
						"value");
				if(!actualOtherDiagnosisJ.equals(othrdiag_j)) {
					otherDiagnosisJ.clear();
					sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
					waitForLoadingToDisappear();
					actualOtherDiagnosisJ = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisJ + " not : "
							+othrdiag_j);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis J is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis J is empty or null");
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				if(!actualOtherDiagnosisK.equals(othrdiag_k)) {
					otherDiagnosisK.clear();
					sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
					waitForLoadingToDisappear();
					actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
							"value");
					assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisK + " not : "
							+othrdiag_k);
				}
				else {
					report(LogStatus.INFO, "The Other Diagnosis K is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis K is empty or null");
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				if(!actualOtherDiagnosisL.equals(othrdiag_l)) {
					otherDiagnosisL.clear();
					sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
					waitForLoadingToDisappear();
					actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
							"value");
					assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisL + " not : "
							+othrdiag_l);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis L is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis L is empty or null");
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				if(!actualOtherDiagnosisM.equals(othrdiag_m)) {
					otherDiagnosisM.clear();
					sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
					waitForLoadingToDisappear();
					actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
							"value");
					assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisM + " not : "
							+othrdiag_m);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis M is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis M is empty or null");
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				if(!actualOtherDiagnosisN.equals(othrdiag_n)) {
					otherDiagnosisN.clear();
					sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
					waitForLoadingToDisappear();
					actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
							"value");
					assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisN + " not : "
							+othrdiag_n);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis N is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis N is empty or null");
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				if(!actualOtherDiagnosisO.equals(othrdiag_o)) {
					otherDiagnosisO.clear();
					sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
					waitForLoadingToDisappear();
					actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
							"value");
					assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisO + " not : "
							+othrdiag_o);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis O is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis O is empty or null");
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				if(!actualOtherDiagnosisP.equals(othrdiag_p)) {
					otherDiagnosisP.clear();
					sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
					waitForLoadingToDisappear();
					actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
							"value");
					assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisP + " not : "
							+othrdiag_p);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis P is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis P is empty or null");
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				if(!actualOtherDiagnosisQ.equals(othrdiag_q)) {
					otherDiagnosisQ.clear();
					sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
					waitForLoadingToDisappear();
					actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
							"value");
					assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisQ + " not : "
							+othrdiag_q);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis Q is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis Q is empty or null");
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				if(!actualAdmissionDiagnosis.equals(admsndiag)) {
					admissionDiagnosis.clear();
					sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
					waitForLoadingToDisappear();
					actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
							"value");
					assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
							+ "code from field is : "+actualAdmissionDiagnosis + " not : "
							+admsndiag);
				}else {
					report(LogStatus.INFO, "The Admission Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Admission Diagnosis is empty or null");
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				if(!actualPatientReasonDiagnosisA.equals(patrsndiag_a)) {
					patientReasonDiagnosisA.clear();
					sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
							patrsndiag_a);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
							"value");
					assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
							+patrsndiag_a);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis A is empty or null");
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				if(!actualPatientReasonDiagnosisB.equals(patrsndiag_b)) {
					patientReasonDiagnosisB.clear();
					sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
							patrsndiag_b);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
							"value");
					assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
							+patrsndiag_b);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis B is empty or null");
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				if(!actualPatientReasonDiagnosisC.equals(patrsndiag_b)) {
					patientReasonDiagnosisC.clear();
					sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
							patrsndiag_c);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
							"value");
					assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
							+patrsndiag_c);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis C is empty or null");
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				String actualPPSCode = getAttribute(ppsCode, "value");
				if(!actualPPSCode.equals(ppscd)) {
					ppsCode.clear();
					sendKeys(ppsCode, "PPS Code", ppscd);
					actualPPSCode = getAttribute(ppsCode, "value");
					assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
					+actualPPSCode+" not : "+ppscd);
				}else {
					report(LogStatus.INFO, "The PPS Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The PPS Code is empty or null");
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				if(!actualECIDiagnosisA.equals(ecidiagcd_a)) {
					eciDiagnosisCodeA.clear();
					sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
							ecidiagcd_a);
					waitForLoadingToDisappear();
					actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
							"value");
					assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisA + " not : "
							+ecidiagcd_a);
				}else {
					report(LogStatus.INFO, "The ECI A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI A is empty or null");
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				if(!actualECIDiagnosisB.equals(ecidiagcd_b)) {
					eciDiagnosisCodeB.clear();
					sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
							ecidiagcd_b);
					waitForLoadingToDisappear();
					actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
							"value");
					assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisB + " not : "
							+ecidiagcd_b);
				}else {
					report(LogStatus.INFO, "The ECI B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI B is empty or null");
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				if(!actualECIDiagnosisC.equals(ecidiagcd_c)) {
					eciDiagnosisCodeC.clear();
					sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
							ecidiagcd_c);
					waitForLoadingToDisappear();
					 actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
							"value");
					assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisC + " not : "
							+ecidiagcd_c);
				}else {
					report(LogStatus.INFO, "The ECI C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI C is empty or null");
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, 
						"value");
				if(!actualPrincipleProcCode.equals(principlepccd)) {
					principleProcedureCode.clear();
					sendKeys(principleProcedureCode, "Principle Proc Code", 
							principlepccd);
					actualPrincipleProcCode = getAttribute(principleProcedureCode, 
							"value");
					if(principlepccd.length()>7)
						assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
							+ "code from field is : "+actualPrincipleProcCode + " not : "
							+principlepccd);
					else
						assertEquals(actualPrincipleProcCode, principlepccd, "The Principle Proc "
								+ "code from field is : "+actualPrincipleProcCode + " not : "
								+principlepccd);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
						"value");
				if(!actualPrincipleProcCodeDate.equals(principlepcdt)) {
					for(int i = 0 ; i < 10 ; i++)
						principlePCDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
					actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
							"value");
					assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
							"The Principle Proc code from field is : "
							+actualPrincipleProcCodeDate + " not : "+principlepcdt);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				if(!actualOtherProcCodeA.equals(othrpccd_a)) {
					otherProcedureCodeA.clear();
					sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
					actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
							"value");
					if(othrpccd_a.length() > 7)
						assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeA + " not : "
							+othrpccd_a);
					else
						assertEquals(actualOtherProcCodeA, othrpccd_a, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeA + " not : "
								+othrpccd_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code A is same and not changed.");
				}	
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code A is empty or null");
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				if(!actualOtherProcCodeDateA.equals(othrpcdt_a)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
					actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
					assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date A is empty or null");
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				if(!actualOtherProcCodeB.equals(othrpccd_b)) {
					otherProcedureCodeB.clear();
					sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
					actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
							"value");
					if(othrpccd_b.length() > 7)
						assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeB + " not : "
							+othrpccd_b);
					else
						assertEquals(actualOtherProcCodeB, othrpccd_b, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeB + " not : "
								+othrpccd_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code B is empty or null");
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				if(!actualOtherProcCodeDateB.equals(othrpcdt_b)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
					actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
					assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date B is empty or null");
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				if(!actualOtherProcCodeC.equals(othrpccd_c)) {
					otherProcedureCodeC.clear();
					sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
					actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
							"value");
					if(othrpccd_c.length() > 7)
						assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeC + " not : "
							+othrpccd_c);
					else
						assertEquals(actualOtherProcCodeC, othrpccd_c, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeC + " not : "
								+othrpccd_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code C is empty or null");
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				if(!actualOtherProcCodeDateC.equals(othrpcdt_c)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateC.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
					actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
					assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date C is empty or null");
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				if(!actualOtherProcCodeD.equals(othrpccd_d)) {
					otherProcedureCodeD.clear();
					sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
					actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
							"value");
					if(othrpccd_d.length() > 7)
						assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeD + " not : "
							+othrpccd_d);
					else
						assertEquals(actualOtherProcCodeD, othrpccd_d, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeD + " not : "
								+othrpccd_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code D is empty or null");
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				if(!actualOtherProcCodeDateD.equals(othrpcdt_d)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateD.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
					actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
					assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date D is empty or null");
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				if(!actualOtherProcCodeE.equals(othrpccd_e)) {
					otherProcedureCodeE.clear();
					sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
					actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
							"value");
					if(othrpccd_e.length() > 7)
						assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeE + " not : "
							+othrpccd_e);
					else
						assertEquals(actualOtherProcCodeE, othrpccd_e, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeE + " not : "
								+othrpccd_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code E is empty or null");
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				if(!actualOtherProcCodeDateE.equals(othrpcdt_e)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateE.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
					actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
					assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date E is empty or null");
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				if(!actualAttendingNPI.equals(attndphynpi)) {
					attendingPhysicianNPI.clear();
					sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
					actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
					assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
					+actualAttendingNPI+" not : "+attndphynpi);
				}else {
					report(LogStatus.INFO, "The Attending Physician NPI is same and not changed.");
				}
			}else {
					report(LogStatus.WARNING,"The Attending Physician NPI is null or empty");
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				if(!actualAttendingPhysicianQual1.equals(attndphyqual1)) {
					attendingPhysicianQual1.clear();
					sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
							attndphyqual1);
					actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
							"value");
					assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
					+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier1 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier1 is null or empty");
		}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				if(!actualAttendingPhysicianQual2.equals(attndphyqual2)) {
					attendingPhysicianQual2.clear();
					sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
							attndphyqual2);
					actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
							"value");
					assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
					+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier2 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier2 is null or empty");
		}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				if(!actualAttendingPhysicianFirstName.equals(attndphyfn)) {
					attendingPhysicianFirstName.clear();
					sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
							attndphyfn);
					actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
							"value");
					assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
					+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
				}else {
					report(LogStatus.INFO, "The Attending Physician FirstName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician FirstName is null or empty");
		}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				if(!actualAttendingPhysicianLastName.equals(attndphyln)) {
					attendingPhysicianLastName.clear();
					sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
							attndphyln);
					actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
							"value");
					assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
					+actualAttendingPhysicianLastName+" not : "+attndphyln);
				}else {
					report(LogStatus.INFO, "The Attending Physician LastName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician LastName is null or empty");
		}
			
		//Operating Physician NPI
		if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
			String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
					"value");
			if(!actualOperatingNPI.equals(oprtphynpi)) {
				operatingPhysicianNPI.clear();
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}else {
				report(LogStatus.INFO, "The Operating Physician NPI is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician NPI is null or empty");
		}
			
		//Operating Physician Qualifier1
		if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
			String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
					"value");
			if(!actualOperatingPhysicianQual1.equals(oprtphyqual1)) {
				operatingPhysicianQual1.clear();
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier1 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier1 is null or empty");
		}
		
		//Operating Physician Qualifier2
		if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
			String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
					"value");
			if(!actualOperatingPhysicianQual2.equals(oprtphyqual2)) {
				operatingPhysicianQual2.clear();
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier2 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier2 is null or empty");
		}
		
		//Operating Physician FirstName
		if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
			String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
					"value");
			if(!actualOperatingPhysicianFirstName.equals(oprtphyfn)) {
				operatingPhysicianFirstName.clear();
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
						+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}else {
				report(LogStatus.INFO, "The Operating Physician FirstName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician FirstName is null or empty");
		}
		
		//Operating Physician LasttName
		if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
			String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
					"value");
			if(!actualOperatingPhysicianLastName.equals(oprtphyln)) {
				operatingPhysicianLastName.clear();
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}else {
				report(LogStatus.INFO, "The Operating Physician LastName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician LastName is null or empty");
		}
		
		//Other Physician NPI A
		if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
			String actualOtherNPIA = getAttribute(otherNPIA, "value");
			if(!actualOtherNPIA.equals(othrnpi_a)) {
				otherNPIA.clear();
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI Ais same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI A is null or empty");
		}
		
		//Other Physician Qualifier1 A
		if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
			String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
					"value");
			if(!actualOtherNPIQual1A.equals(othrnpiqual1_a)) {
				otherNPIQual1A.clear();
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 A is null or empty");
		}
		
		//Other Physician Qualifier2 A
		if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
			String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
					"value");
			if(!actualOtherNPIQual2A.equals(othrnpiqual2_a)) {
				otherNPIQual2A.clear();
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 A is null or empty");
		}
		
		//Other Physician First Name A
		if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
			String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
					"value");
			if(!actualOtherPhysicianFirstNameA.equals(othrnpifn_a)) {
				otherNPIFirstNameA.clear();
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName A is null or empty");
		}
		
		//Other Physician LastName A
		if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
			String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
					"value");
			if(!actualOtherPhysicianLastNameA.equals(othrnpiln_a)) {
				otherNPILastNameA.clear();
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName A is null or empty");
		}
		
		//Other Physician NPI B
		if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
			String actualOtherNPIB = getAttribute(otherNPIB, "value");
			if(!actualOtherNPIB.equals(othrnpi_b)) {
				otherNPIB.clear();
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI B is null or empty");
		}
		
		//Other Physician Qualifier1 B
		if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
			String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
					"value");
			if(!actualOtherNPIQual1B.equals(othrnpiqual1_b)) {
				otherNPIQual1B.clear();
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 B is null or empty");
		}
		
		//Other Physician Qualifier2 B
		if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
			String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
					"value");
			if(!actualOtherNPIQual2B.equals(othrnpiqual2_b)) {
				otherNPIQual2B.clear();
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 B is null or empty");
		}
		
		//Other Physician First Name B
		if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
			String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
					"value");
			if(!actualOtherPhysicianFirstNameB.equals(othrnpifn_b)) {
				otherNPIFirstNameB.clear();
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName B is null or empty");
		}
		
		//Other Physician LastName B
		if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
			String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
					"value");
			if(!actualOtherPhysicianLastNameB.equals(othrnpiln_b)) {
				otherNPILastNameB.clear();
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName B is null or empty");
		}
		
		//Remarks
		if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
			String actualRemarks = getAttribute(remarks, "value");
			if(!actualRemarks.equals(reMarks)) {
				remarks.clear();
				sendKeys(remarks, "Remarks", reMarks);
				actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}else {
				report(LogStatus.INFO, "The Remarks is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Remarks is null or empty");
		}
		
		//Taxonomy
		if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
			form81ATaxanomy.clear();
			sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
			String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
			waitForLoadingToDisappear();
			WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
			click(taxonomy, form81taxonomy_a);
		}else {
			report(LogStatus.WARNING,"The Taxonomy is null or empty");
		}
		
		//Form 81 A value
		if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
			String actualForm81AValue = getAttribute(form81AValue, "value");
			if(!actualForm81AValue.equals(form81value_a)) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}else {
				report(LogStatus.INFO,"The Form 81 A value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 A value is null or empty");
		}
		
		//Form 81 B Qualifier
		if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
			String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
			if(!actualForm81BQualifier.equals(form81qualifier_b)) {
				form81BQualifier.clear();
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Qualifier value is null or empty");
		}
		
		//Form 81 B Taxonomy
		if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
			String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
			if(!actualForm81BTaxonomy.equals(form81taxonomy_b)) {
				form81BTaxanomy.clear();
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Taxonomy value is null or empty");
		}
		
		//Form 81 B Value
		if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
			String actualForm81BValue = getAttribute(form81BValue, "value");
			if(!actualForm81BValue.equals(form81value_b)) {
				form81BValue.clear();
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Value is null or empty");
		}
		
		//Form 81 C Qualifier
		if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
			String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
			if(!actualForm81CQualifier.equals(form81qualifier_c)) {
				form81CQualifier.clear();
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Qualifier is null or empty");
		}
		
		//Form 81 C Taxonomy
		if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
			String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
			if(!actualForm81CTaxonomy.equals(form81taxonomy_c)) {
				form81CTaxanomy.clear();
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Taxonomy is null or empty");
		}
		
		//Form 81 C Value
		if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
			String actualForm81CValue = getAttribute(form81CValue, "value");
			if(!actualForm81CValue.equals(actualForm81CValue)) {
				form81CValue.clear();
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Value is null or empty");
		}
		
		//Form 81 D Qualifier
		if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
			String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
			if(!actualForm81DQualifier.equals(form81qualifier_d)) {
				form81DQualifier.clear();
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Qualifier is null or empty");
		}
		
		//Form 81 D Taxonomy
		if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
			String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
			if(!actualForm81DTaxonomy.equals(form81taxonomy_d)) {
				form81DTaxanomy.clear();
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}else {
				report(LogStatus.INFO,"The //Form 81 D Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Taxonomy is null or empty");
		}
		
		//Form 81 D Value
		if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
			String actualForm81DValue = getAttribute(form81DValue, "value");
			if(!actualForm81DValue.equals(form81value_d)) {
				form81DValue.clear();
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Value is null or empty");
		}
		
		//Received Date
		if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
			String actualReceivedDate = getAttribute(receivedDate, "value");
			if(!actualReceivedDate.equals(receveddate)) {
				for(int i = 0 ; i < 10 ; i++)
					receivedDate.sendKeys(Keys.BACK_SPACE);
				sendKeys(receivedDate, "Received Date", receveddate);
				actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}else {
				report(LogStatus.INFO,"The Received Date is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Received Date is null or empty");
		}
		
		click(submitButton, "Submit Claim");
		
		waitForLoadingToDisappear();
		
		String alertXpath = "//*[@role='alertdialog']";
		WebElement alertEle = driver.findElement(By.xpath(alertXpath));
		putStaticWait(2);
		waitUntilElementVisible(By.xpath(alertXpath), 20);
		String alerttext = getAttribute(alertEle,"aria-label");
		String alerttext1 = getAttribute(alertEle,"innerHTML");
		System.out.println(alerttext);
		System.out.println(alerttext1);
		alertEle.click();
		if(Objects.nonNull(alerttext)) {

			if(alerttext.contains("success") || alerttext.contains("Success") ) {
				report(LogStatus.PASS, "Successfully submitted");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
				try {
					throw new CannotCreateClaimException("Not able to submit the "
							+ "claim due to error : \n"+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}else if(Objects.nonNull(alerttext1)) {
			if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
				report(LogStatus.PASS, "Successfully submitted");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext1);
				try {
					throw new CannotCreateClaimException("Not able to submit the "
							+ "claim due to error : \n"+alerttext1);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}
		waitForLoadingToDisappear();	
		}else {
			report(LogStatus.FAIL, "Copy UB-04 screen is not displayed.");
			try {
				throw new Exception("Copy UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		waitForLoadingToDisappear();
		
		return myMCSNumber;
		
	}
	
	public String copyAndSubmitUB04Claim() {
		
		boolean flag =  false;
		String myMCSNumber = dataMap.get("myMCSClaimNumber");
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
		String serviceLineNumber = dataMap.get("serviceLineNumber");
		String revcd = dataMap.get("revenueCode");
		String pccd = dataMap.get("serviceCode");
		String srvcdt = dataMap.get("serviceDate");
		String noofunits =dataMap.get("units");
		String charges = dataMap.get("totalCharges");
		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		filterWithMyMCSNumber(myMCSNumber);
		click(firstRecord, "");
		waitForLoadingToDisappear();
		click(CopyButton, "Copy");
		if(copyUB04Heading.isDisplayed()) {
			waitForLoadingToDisappear();
			report(LogStatus.PASS, "Update Popup is displayed.");
			if(Objects.nonNull(prvid) && !prvid.equals("")) {
				String actualProviderID = getAttribute(providerIDText, "value");
				if(!actualProviderID.equals(prvid)) {
					waitUntilClickable(providerSearchButton, 10);
					click(providerSearchButton, "Provider Search");
					
					//Provider selection
					if(providerSearchPopupHeading.isDisplayed()) {
						report(LogStatus.PASS,"Provider Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Provider Search popup is not displayed.");
					}
					
					sendKeys(providerSeachProviderID, "Provider ID", prvid);
					click(providerSearchSearcButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = providerSearchGridRow.replace("XX", "1");
					WebElement providerrow = driver.findElement(By.xpath(rownumber));
					click(providerrow, "First provider record");
					click(providerSearchSelectProviderButton, "Select Provider");
					waitForLoadingToDisappear();
					actualProviderID = getAttribute(providerIDText, "value");
					assertEquals(actualProviderID, prvid, "The provider id from field "
								+ "is : "+actualProviderID+" not : "+prvid);
					report(LogStatus.PASS, "Provider Id updated successfully.");
					
				}else {
					report(LogStatus.INFO, "Provider Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Provider id is null or empty.");
			}
			
			//Site Selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				String actualSiteID = getAttribute(siteIDText2, "value");
				if(!actualSiteID.equals(steid)) {
					waitForLoadingToDisappear();
					click(siteSelectionDropdown, "Site dropdown");
					String siteElement = dropdownOptions.replace("XX", steid);
					WebElement site_ele = driver.findElement(By.xpath(siteElement));
					click(site_ele, "Site");
					waitForLoadingToDisappear();
					actualSiteID = getAttribute(siteIDText2, "value");
					assertEquals(actualSiteID, steid,"The site id from field "
								+ "is : "+actualSiteID+" not : "+steid);
					report(LogStatus.PASS, "Site Id updated successfully.");
				}else {
					report(LogStatus.INFO, "Site Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Site id is null or empty.");
			}
			
			//Patient selection
			if(Objects.nonNull(patid) && !patid.equals("")) {
				String actualPatientID = getAttribute(patientIDText, "value");
				if(!actualPatientID.equals(patid)) {
					waitUntilClickable(patientSearchButton, 20);
					click(patientSearchButton, "Patient Search");
					if(patientSearchHeading.isDisplayed()) {
						report(LogStatus.PASS,"Patient Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Patient Search popup is not displayed.");
					}
					sendKeys(patientSearchPatientID, "Patient ID", patid);
					actualPatientID = getAttribute(patientSearchPatientID, "value");
					assertEquals(actualPatientID, patid, "The Patient Control Number from "
							+ "field is : "+actualPatientID+" not : "+patid);
					click(patientSearchSearchButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = patientSearchGridRow.replace("XX", "1");
					WebElement patientrow = driver.findElement(By.xpath(rownumber));
					click(patientrow, "First patient record");
					click(patientSearchSelectPatientButton, "Select Patient");
					waitForLoadingToDisappear();
				}else {
					report(LogStatus.INFO, "Patient Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient id is null or empty.");
			}
			
			//Patient Control Number
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				String actualPCN = getAttribute(patientControlNumber, "value");
				if(!actualPCN.equals(pcn)) {
					patientControlNumber.clear();
					sendKeys(patientControlNumber, "Patient Control Number", pcn);
					actualPCN = getAttribute(patientControlNumber, "value");
					assertEquals(actualPCN, pcn, "The Patient Control Number from field "
							+ "is : "+actualPCN+" not : "+pcn);
				}else {
					report(LogStatus.INFO, "Patient Control Number is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient control Number is null or empty.");
			}
			
			//Bill Type Speciality
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				String actualBillType = getAttribute(billType, "value");
				System.out.println(actualBillType+" : "+billtype);
				if(!actualBillType.equals(billtype)) {
					billType.clear();
					sendKeys(billType, "Bill Type", billtype);
					actualBillType = getAttribute(billType, "value");
					assertEquals(actualBillType, billtype, "The Patient Control Number "
							+ "from field is : "+actualBillType+" not : "+billtype);
				}else {
					report(LogStatus.INFO, "Billtype is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Bill Type is null or empty.");
			}
			
			//From and To period
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				if(!actualFromDate.equals(fromperiod) && !actualToDate.equals(toperiod)) {
					for(int i = 0 ; i < 10 ; i++)
						statementFromPeriod.sendKeys(Keys.BACK_SPACE);
					for(int i = 0 ; i < 10 ; i++)
						statementToPeriod.sendKeys(Keys.BACK_SPACE);
					sendKeys(statementFromPeriod, "From Date", fromperiod);
					sendKeys(statementToPeriod, "To Date", toperiod);
					actualFromDate = getAttribute(statementFromPeriod, "value");
					actualToDate = getAttribute(statementToPeriod, "value");
					assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
							+ "from field is : "+actualFromDate+" not : "+fromperiod);
					assertEquals(actualToDate, toperiod, "The Statement To Period  "
							+ "from field is : "+actualToDate+" not : "+toperiod);
				}else {
					report(LogStatus.INFO, "From Date or To Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"From period or To period is null or empty.");
			}
			
			//Admission Date
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				if(!actualAdmissionDate.equals(admsndt)) {
					for(int i = 0 ; i < 10 ; i++)
						admissionDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(admissionDate, "Admission Date", admsndt);
					actualAdmissionDate = getAttribute(admissionDate, "value");
					assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
							+" is : "+actualAdmissionDate+" not : "+admsndt);
				}else {
					report(LogStatus.INFO, "Admission Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission date is null or empty.");
			}
			
			//Admission Hour
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				String actualAdmissionHour = getText(admissionHourText);
				if(!actualAdmissionHour.equals(admsnhr)) {
					click(admissionHourDropdown,"Admission Hour");
					String admsnHour = dropdownOptions.replace("XX", admsnhr);
					WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
					waitUntilClickable(admisionHour, 10);
					click(admisionHour,"Admission Hour");
					actualAdmissionHour = getText(admissionHourText);
					assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
							+ "is : "+actualAdmissionHour+" not "+admsnhr);
				}else {
					report(LogStatus.INFO, "Admission Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission Hour is null or empty.");
			}
			
			//Reference Type
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				String actualReferenceType = getText(visitTypeText);
				if(!actualReferenceType.equals(reftype)) {
					click(visitTypeDropdown, "Visit Type");
					String refType = dropdownOptions.replace("XX", reftype);
					WebElement referenceType = driver.findElement(By.xpath(refType));
					waitUntilClickable(referenceType, 10);
					click(referenceType, reftype);
					actualReferenceType = getText(visitTypeText);
					assertEquals(actualReferenceType, reftype,"The Reference Type from field "
							+ "is : "+actualReferenceType+" not "+reftype);
				}else {
					report(LogStatus.INFO, "Reference type is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Type is empty or null.");
			}
			
			//Reference Source
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				String actualReferenceSource = getText(referenceSourceText);
				if(!actualReferenceSource.equals(refsrc)) {
					click(referenceSourceDropdown, "Visit Type");
					String refSrc = dropdownOptions.replace("XX", refsrc);
					WebElement referenceSource = driver.findElement(By.xpath(refSrc));
					waitUntilClickable(referenceSource, 10);
					click(referenceSource, refsrc);
					actualReferenceSource = getText(referenceSourceText);
					assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
							+ "is : "+actualReferenceSource+" not "+refsrc);
				}else {
					report(LogStatus.INFO, "Reference Source is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Source is empty or null.");
			}
			
			//Discharge Hour
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				String actualDischargeHour = getText(dischargeHourText);
				if(actualDischargeHour.equals(dschrghr)) {
					click(dischargeHour, "Visit Type");
					String dischrgHr = dropdownOptions.replace("XX", dschrghr);
					WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
					waitUntilClickable(dischargeHour, 10);
					click(dischargeHour, dschrghr);
					actualDischargeHour = getText(dischargeHourText);
					assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
							+ "is : "+actualDischargeHour+" not "+dschrghr);
				}else {
					report(LogStatus.INFO, "Discharge Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Hour is empty or null.");
			}
			
			//Discharge Status
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				String actualDischargeStatus = getText(dischargeStatusText);
				if(!actualDischargeStatus.equals(dschrgsts)) {
					click(dischargeStatus, "Visit Type");
					String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
					WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
					waitUntilClickable(dischargeStatus, 10);
					click(dischargeStatus, dschrgsts);
					actualDischargeStatus = getText(dischargeStatusText);
					assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
							+ "is : "+actualDischargeStatus+" not "+dschrgsts);
				}else {
					report(LogStatus.INFO, "Discharge Status is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Status is empty or null.");
			}
			
			//Form 18
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				if(!actualForm18.equals(actform_18)) {
					form18.clear();
					sendKeys(form18, "Form 18", form_18);
					actualForm18 = getAttribute(form18, "value");
					assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
							actualForm18+" not : "+actform_18);
				}else {
					report(LogStatus.INFO, "Form 18 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 18 is empty or null.");
			}
			
			//Form 19
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				if(!actualForm19.equals(actform_19)) {
					form19.clear();
					sendKeys(form19, "Form 19", form_19);
					actualForm19 = getAttribute(form19, "value");
					assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
							actualForm19+" not : "+actform_19);
				}else {
					report(LogStatus.INFO, "Form 19 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 19 is empty or null.");
			}
			
			//Form 20
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				if(!actualForm20.equals(actform_20)) {
					form20.clear();
					sendKeys(form20, "Form 20", form_20);
					actualForm20 = getAttribute(form20, "value");
					assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
							actualForm20+" not : "+actform_20);
				}else {
					report(LogStatus.INFO, "Form 20 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 20 is empty or null.");
			}
			
			//Form 21
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				if(!actualForm21.equals(actform_21)) {
					form21.clear();
					sendKeys(form21, "Form 21", form_21);
					actualForm21 = getAttribute(form21, "value");
					assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
							actualForm21+" not : "+actform_21);
				}else {
					report(LogStatus.INFO, "Form 21 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 21 is empty or null.");
			}
			
			//Form 22
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				if(!actualForm22.equals(actform_22)) {
					form22.clear();
					sendKeys(form22, "Form 22", form_22);
					actualForm22 = getAttribute(form22, "value");
					assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
							actualForm22+" not : "+actform_22);
				}else {
					report(LogStatus.INFO, "Form 22 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 22 is empty or null.");
			}
			
			//Form 23
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				if(!actualForm23.equals(actform_23)) {
					form23.clear();
					sendKeys(form23, "Form 23", form_23);
					actualForm23 = getAttribute(form23, "value");
					assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
							actualForm23+" not : "+actform_23);
				}else {
					report(LogStatus.INFO, "Form 23 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 23 is empty or null.");
			}
			
			//Form 24
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				if(!actualForm24.equals(actform_24)) {
					form24.clear();
					sendKeys(form24, "Form 24", form_24);
					actualForm24 = getAttribute(form24, "value");
					assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
							actualForm24+" not : "+actform_24);
				}else {
					report(LogStatus.INFO, "Form 24 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 24 is empty or null.");
			}
			
			//Form 25
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				if(!actualForm25.equals(actform_25)) {
					form25.clear();
					sendKeys(form25, "Form 25", form_25);
					actualForm25 = getAttribute(form25, "value");
					assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
							actualForm25+" not : "+actform_25);
				}else {
					report(LogStatus.INFO, "Form 25 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 25 is empty or null.");
			}
			
			//Form 26
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				if(!actualForm26.equals(actform_26)) {
					form26.clear();
					sendKeys(form26, "Form 26", form_26);
					actualForm26 = getAttribute(form26, "value");
					assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
							actualForm26+" not : "+actform_26);
				}else {
					report(LogStatus.INFO, "Form 26 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 26 is empty or null.");
			}
			
			//Form 27
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				if(!actualForm27.equals(actform_27)) {
					form27.clear();
					sendKeys(form27, "Form 27", form_27);
					actualForm27 = getAttribute(form27, "value");
					assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
							actualForm27+" not : "+actform_27);
				}else {
					report(LogStatus.INFO, "Form 27 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 27 is empty or null.");
			}
			
			//Form 28
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				if(!actualForm28.equals(actform_28)) {
					form28.clear();
					sendKeys(form28, "Form 28", form_28);
					actualForm28 = getAttribute(form28, "value");
					assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
							actualForm28+" not : "+actform_28);
				}else {
					report(LogStatus.INFO, "Form 28 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 28 is empty or null.");
			}
			
			//Accident State
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				if(!actualAccidentState.equals(accdtstate)) {
					accidentState.clear();
					sendKeys(accidentState, "Accident State", accdtstate);
					actualAccidentState = getAttribute(accidentState, "value");
					assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
							actualAccidentState+" not : "+actaccdtstate);
				}else {
					report(LogStatus.INFO, "Accident State is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Accident State is empty or null.");
			}
			
			//Form 31 A
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				if(!actualform31aoccurancecode.equals(actform31occcd_a) && 
						!actualform31adate.equals(form31occdt_a)) {
					form31OccuranceCodeA.clear();
					sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
					actualform31aoccurancecode = 
							getAttribute(form31OccuranceCodeA, "value");
					assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31aoccurancecode+
							" not : "+actform31occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
					actualform31adate = 
							getAttribute(form31OccuranceDateA, "value");
					assertEquals(actualform31adate, form31occdt_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31adate+
							" not : "+form31occdt_a);
				}else {
					report(LogStatus.INFO, "Form 31 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 A code or date is empty or null.");
			}
			
			//Form 31 B
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				if(!actualform31boccurancecode.equals(actform31occcd_b) && 
						!actualform31bdate.equals(form31occdt_b)) {
					form31OccuranceCodeB.clear();
					sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
					actualform31boccurancecode = 
							getAttribute(form31OccuranceCodeB, "value");
					assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31boccurancecode+
							" not : "+actform31occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
					actualform31bdate = 
							getAttribute(form31OccuranceDateB, "value");
					assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31bdate+
							" not : "+form31occdt_b);
				}else {
					report(LogStatus.INFO, "Form 31 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 B code or date is empty or null.");
			}
			
			//Form 32 A
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				if(!actualform32aoccurancecode.equals(actform32occcd_a) && 
						!actualform32adate.equals(form32occdt_a)) {
					form32OccuranceCodeA.clear();
					sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
					actualform32aoccurancecode = 
							getAttribute(form32OccuranceCodeA, "value");
					assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32aoccurancecode+
							" not : "+actform32occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
					actualform32adate = 
							getAttribute(form32OccuranceDateA, "value");
					assertEquals(actualform32adate, form32occdt_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32adate+
							" not : "+form32occdt_a);
				}else {
					report(LogStatus.INFO, "Form 32 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 A code or date is empty or null.");
			}
			
			//Form 32 B
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				if(!actualform32boccurancecode.equals(actform32occcd_b) && 
						!actualform32bdate.equals(form32occdt_b)) {
					form32OccuranceCodeB.clear();
					sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
					actualform32boccurancecode = 
							getAttribute(form32OccuranceCodeB, "value");
					assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32boccurancecode+
							" not : "+actform32occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
					actualform32bdate = 
							getAttribute(form32OccuranceDateB, "value");
					assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32bdate+
							" not : "+form32occdt_b);
				}else {
					report(LogStatus.INFO, "Form 32 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 B code or date is empty or null.");
			}
			
			//Form 33 A
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form33occcd_a;
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				if(!actualform33aoccurancecode.equals(actform33occcd_a) && 
						!actualform33adate.equals(form33occdt_a)) {
					form33OccuranceCodeA.clear();
					sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
					actualform33aoccurancecode = 
							getAttribute(form33OccuranceCodeA, "value");
					assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33aoccurancecode+
							" not : "+actform33occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
					actualform33adate = 
							getAttribute(form33OccuranceDateA, "value");
					assertEquals(actualform33adate, form33occdt_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33adate+
							" not : "+form33occdt_a);
				}else {
					report(LogStatus.INFO, "Form 33 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 A code or date is empty or null.");
			}
			
			//Form 33 B
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				if(!actualform33boccurancecode.equals(actform33occcd_b) && 
						!actualform33bdate.equals(form33occdt_b)) {
					form33OccuranceCodeB.clear();
					sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
					actualform33boccurancecode = 
							getAttribute(form33OccuranceCodeB, "value");
					assertEquals(actualform33boccurancecode, actform33occcd_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33boccurancecode+
							" not : "+actform33occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
					actualform33bdate = 
							getAttribute(form33OccuranceDateB, "value");
					assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33bdate+
							" not : "+form33occdt_b);
				}else {
					report(LogStatus.INFO, "Form 33 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 B code or date is empty or null.");
			}
			
			//Form 34 A
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				if(!actualform34aoccurancecode.equals(actform34occcd_a) && 
						!actualform34adate.equals(form34occdt_a)) {
					form34OccuranceCodeA.clear();
					sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
					actualform34aoccurancecode = 
							getAttribute(form34OccuranceCodeA, "value");
					assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34aoccurancecode+
							" not : "+actform34occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
					actualform34adate = 
							getAttribute(form34OccuranceDateA, "value");
					assertEquals(actualform34adate, form34occdt_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34adate+
							" not : "+form34occdt_a);
				}else {
					report(LogStatus.INFO, "Form 34 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 A code or date is empty or null.");
			}
			
			//Form 34 B
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				if(!actualform34boccurancecode.equals(actform34occcd_b) && 
						!actualform34bdate.equals(form34occdt_b)) {
					form34OccuranceCodeB.clear();
					sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
					actualform34boccurancecode = 
							getAttribute(form34OccuranceCodeB, "value");
					assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34boccurancecode+
							" not : "+actform34occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
					actualform34bdate = 
							getAttribute(form34OccuranceDateB, "value");
					assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34bdate+
							" not : "+form34occdt_b);
				}else {
					report(LogStatus.INFO, "Form 34 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 B code or date is empty or null.");
			}
			
			//Form 35 A
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				String actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				if(!actualform35aoccurancecode.equals(actform35occcd_a) && 
						!actualform35afromdate.equals(form35occfrdt_a) && 
						!actualform35atodate.equals(form35occtodt_a)) {
					form35OccuranceSpanCodeA.clear();
					sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
					actualform35aoccurancecode = 
							getAttribute(form35OccuranceSpanCodeA, "value");
					assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35aoccurancecode+
							" not : "+actform35occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
					actualform35afromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35afromdate+
							" not : "+form35occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
							form35occtodt_a);
					actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35atodate+
							" not : "+form35occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 35 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 A code or date is empty or null.");
			}
			
			//Form 35 B
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				if(!actualform35boccurancecode.equals(actform35occcd_b) && 
						!actualform35bfromdate.equals(form35occfrdt_b) && 
						!actualform35btodate.equals(form35occtodt_b)) {
					form35OccuranceSpanCodeB.clear();
					sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
					actualform35boccurancecode = 
							getAttribute(form35OccuranceSpanCodeB, "value");
					assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35boccurancecode+
							" not : "+actform35occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
					actualform35bfromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35bfromdate+
							" not : "+form35occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
							form35occtodt_b);
					actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35btodate+
							" not : "+form35occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 35 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 B code or date is empty or null.");
			}
			
			//Form 36 A
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				String actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				if(!actualform36aoccurancecode.equals(actform36occcd_a) && 
						!actualform36afromdate.equals(form36occfrdt_a) && 
						!actualform36atodate.equals(form36occtodt_a)) {
					form36OccuranceSpanCodeA.clear();
					sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
					actualform36aoccurancecode = 
							getAttribute(form36OccuranceSpanCodeA, "value");
					assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36aoccurancecode+
							" not : "+actform36occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
					actualform36afromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36afromdate+
							" not : "+form36occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
							form36occtodt_a);
					actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36atodate+
							" not : "+form36occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 36 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 A code or date is empty or null.");
			}
			
			//Form 36 B
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				if(!actualform36boccurancecode.equals(actform36occcd_b) && 
						!actualform36bfromdate.equals(form36occfrdt_b) && 
						!actualform36btodate.equals(form36occtodt_b)) {
					form36OccuranceSpanCodeB.clear();
					sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
					actualform36boccurancecode = 
							getAttribute(form36OccuranceSpanCodeB, "value");
					assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36boccurancecode+
							" not : "+actform36occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
					actualform36bfromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36bfromdate+
							" not : "+form36occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
							form36occtodt_b);
					actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36btodate+
							" not : "+form36occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 36 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 B code or date is empty or null.");
			}
			
			//Form 39 A
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				if(!actualform39avalcd.equals(actform39avalcd) && 
						!actualform39avalcdamt.equals(form39valcdamt_a)) {
							form39ValueCodeA.clear();
							sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
							actualform39avalcd = 
									getAttribute(form39ValueCodeA, "value");
							assertEquals(actualform39avalcd, actform39avalcd, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcd+
									" not : "+actform39avalcd);
							
							form39ValueCodeAmountA.clear();
							sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
							actualform39avalcdamt = 
									getAttribute(form39ValueCodeAmountA, "value");
							assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcdamt+
									" not : "+form39valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 39 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 A Code or Amount is empty or null");
			}
			
			//Form 39 B
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				if(!actualform39bvalcd.equals(actform39bvalcd) && 
						!actualform39bvalcdamt.equals(form39valcdamt_b)) {
							form39ValueCodeB.clear();
							sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
							actualform39bvalcd = 
									getAttribute(form39ValueCodeB, "value");
							assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcd+
									" not : "+actform39bvalcd);
							
							form39ValueCodeAmountB.clear();
							sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
							actualform39bvalcdamt = 
									getAttribute(form39ValueCodeAmountB, "value");
							assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcdamt+
									" not : "+form39valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 39 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 B Code or Amount is empty or null");
			}
			
			//Form 39 C
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_a;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				if(!actualform39cvalcd.equals(actform39cvalcd) && 
						!actualform39cvalcdamt.equals(form39valcdamt_c)) {
							form39ValueCodeC.clear();
							sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
							actualform39cvalcd = 
									getAttribute(form39ValueCodeC, "value");
							assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcd+
									" not : "+actform39cvalcd);
							
							form39ValueCodeAmountC.clear();
							sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
							actualform39cvalcdamt = 
									getAttribute(form39ValueCodeAmountC, "value");
							assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcdamt+
									" not : "+form39valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 39 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 C Code or Amount is empty or null");
			}
			
			//Form 39 D
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				if(!actualform39dvalcd.equals(actform39dvalcd) && 
						!actualform39dvalcdamt.equals(form39valcdamt_d)) {
							form39ValueCodeD.clear();
							sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
							actualform39dvalcd = 
									getAttribute(form39ValueCodeD, "value");
							assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcd+
									" not : "+actform39dvalcd);
							
							form39ValueCodeAmountD.clear();
							sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
							actualform39dvalcdamt = 
									getAttribute(form39ValueCodeAmountD, "value");
							assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcdamt+
									" not : "+form39valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 39 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 D Code or Amount is empty or null");
			}
			
			//Form 40 A
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				if(!actualform40avalcd.equals(actform40avalcd) && 
						!actualform40avalcdamt.equals(form40valcdamt_a)) {
							form40ValueCodeA.clear();
							sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
							actualform40avalcd = 
									getAttribute(form40ValueCodeA, "value");
							assertEquals(actualform40avalcd, actform40avalcd, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcd+
									" not : "+actform40avalcd);
							
							form40ValueCodeAmountA.clear();
							sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
							actualform40avalcdamt = 
									getAttribute(form40ValueCodeAmountA, "value");
							assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcdamt+
									" not : "+form40valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 40 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 A Code or Amount is empty or null");
			}
			
			//Form 40 B
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				if(!actualform40bvalcd.equals(actform40bvalcd) && 
						!actualform40bvalcdamt.equals(form40valcdamt_b)) {
							form40ValueCodeB.clear();
							sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
							actualform40bvalcd = 
									getAttribute(form40ValueCodeB, "value");
							assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcd+
									" not : "+actform40bvalcd);
							
							form40ValueCodeAmountB.clear();
							sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
							actualform40bvalcdamt = 
									getAttribute(form40ValueCodeAmountB, "value");
							assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcdamt+
									" not : "+form40valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 40 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 B Code or Amount is empty or null");
			}
			
			//Form 40 C
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_a;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				if(!actualform40cvalcd.equals(actform40cvalcd) && 
						!actualform40cvalcdamt.equals(form40valcdamt_c)) {
							form40ValueCodeC.clear();
							sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
							actualform40cvalcd = 
									getAttribute(form40ValueCodeC, "value");
							assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcd+
									" not : "+actform40cvalcd);
							
							form40ValueCodeAmountC.clear();
							sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
							actualform40cvalcdamt = 
									getAttribute(form40ValueCodeAmountC, "value");
							assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcdamt+
									" not : "+form40valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 40 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 C Code or Amount is empty or null");
			}
			
			//Form 40 D
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				if(!actualform40dvalcd.equals(actform40dvalcd) && 
						!actualform40dvalcdamt.equals(form40valcdamt_d)) {
							form40ValueCodeD.clear();
							sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
							actualform40dvalcd = 
									getAttribute(form40ValueCodeD, "value");
							assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcd+
									" not : "+actform40dvalcd);
							
							form40ValueCodeAmountD.clear();
							sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
							actualform40dvalcdamt = 
									getAttribute(form40ValueCodeAmountD, "value");
							assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcdamt+
									" not : "+form40valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 40 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 D Code or Amount is empty or null");
			}
			
			//Form 41 A
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				if(!actualform41avalcd.equals(actform41avalcd) && 
						!actualform41avalcdamt.equals(form41valcdamt_a)) {
							form41ValueCodeA.clear();
							sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
							actualform41avalcd = 
									getAttribute(form41ValueCodeA, "value");
							assertEquals(actualform41avalcd, actform41avalcd, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcd+
									" not : "+actform41avalcd);
							
							form41ValueCodeAmountA.clear();
							sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
							actualform41avalcdamt = 
									getAttribute(form41ValueCodeAmountA, "value");
							assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcdamt+
									" not : "+form41valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 41 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 A Code or Amount is empty or null");
			}
			
			//Form 41 B
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				if(!actualform41bvalcd.equals(actform41bvalcd) && 
						!actualform41bvalcdamt.equals(form41valcdamt_b)) {
							form41ValueCodeB.clear();
							sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
							actualform41bvalcd = 
									getAttribute(form41ValueCodeB, "value");
							assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcd+
									" not : "+actform41bvalcd);
							
							form41ValueCodeAmountB.clear();
							sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
							actualform41bvalcdamt = 
									getAttribute(form41ValueCodeAmountB, "value");
							assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcdamt+
									" not : "+form41valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 41 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 B Code or Amount is empty or null");
			}
			
			//Form 41 C
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_a;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				if(!actualform41cvalcd.equals(actform41cvalcd) && 
						!actualform41cvalcdamt.equals(form41valcdamt_c)) {
							form41ValueCodeC.clear();
							sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
							actualform41cvalcd = 
									getAttribute(form41ValueCodeC, "value");
							assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcd+
									" not : "+actform41cvalcd);
							
							form41ValueCodeAmountC.clear();
							sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
							actualform41cvalcdamt = 
									getAttribute(form41ValueCodeAmountC, "value");
							assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcdamt+
									" not : "+form41valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 41 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 C Code or Amount is empty or null");
			}
			
			//Form 41 D
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && !form41valcdamt_d.equals("")) {
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				if(!actualform41dvalcd.equals(actform41dvalcd) && 
						!actualform41dvalcdamt.equals(form41valcdamt_d)) {
							form41ValueCodeD.clear();
							sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
							actualform41dvalcd = 
									getAttribute(form41ValueCodeD, "value");
							assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcd+
									" not : "+actform41dvalcd);
							
							form41ValueCodeAmountD.clear();
							sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
							actualform41dvalcdamt = 
									getAttribute(form41ValueCodeAmountD, "value");
							assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcdamt+
									" not : "+form41valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 41 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 D Code or Amount is empty or null");
			}
			
			if(Objects.nonNull(serviceLineNumber) && !serviceLineNumber.equals("")) {
				String numberOfLines = serviceLine.replace("[XX]", "");
				int lineCount= driver.findElements(By.xpath(numberOfLines)).size();
				if(Integer.parseInt(serviceLineNumber) <= lineCount) {
					String lineNumber = serviceLine.replace("XX", serviceLineNumber);
					WebElement serviceLine = driver.findElement(By.xpath(lineNumber));
					serviceLine.click();
					if(modifyService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service modified successfully.");
					}else {
						report(LogStatus.FAIL,"Service not modified.");
					}
				}else {
					report(LogStatus.INFO,"The Service line numner is not valid");
				}
				
			}else {
				report(LogStatus.WARNING, "Service Line Number is empty or null");
			}
			
			//Previous Payer
				
			//Health Plan ID A
			if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
				String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
				if(!actualHealthPlanIDA.equals(healthplanid_a)) {
					healthPlanIDA.clear();
					sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
					actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
					assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
							+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);
				}else {
					report(LogStatus.INFO,"Health Plan ID A numner is not valid");
				}						
			}else {
				report(LogStatus.WARNING, "Health Plan ID A is empty or null");
			}

			
			//REL INFO A
			if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
				String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
				String[] relInfoAData = relInfoAClass.split(" ");
				String actualRelInfoA = "";
				for(String s : relInfoAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualRelInfoA = s;
					}
				}
				if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box checked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not checked");
					}
					
				}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
						relinfo_a.equals("NO")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(!actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box unchecked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
					}
					
				}
			}
			
			//ASN BEN A
			if(Objects.nonNull(benfitassignment_a) && 
					!benfitassignment_a.equals("")) {
				String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
				String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
				String actualBenfitAssignmentA = "";
				for(String s : benfitAssignmentAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualBenfitAssignmentA = s;
					}
				}
				if(actualBenfitAssignmentA.equals("") && 
						benfitassignment_a.equals("YES")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box checked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not checked");
					}
					
				}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
						benfitassignment_a.equals("NO")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box unchecked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
					}
					
				}
			}
			
			//Prior payment amount
			if(Objects.nonNull(priorpaymentamt_a) && 
					!priorpaymentamt_a.equals("")) {
				priorPaymentAmountA.clear();
				sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
				String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
				assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
						+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
						" not : "+priorpaymentamt_a);
			}
			
			//EST Due Amount
			if(Objects.nonNull(estamountdue_a) && 
					!estamountdue_a.equals("")) {
				estAmountDueA.clear();
				sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
				String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
				assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
						+"from prior payment amount field A is : "+actualESTAmountDueA+
						" not : "+estamountdue_a);
			}
			
			//primary payer
			if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
				click(payerTypeDrodownA, "Payer Type");
				switch(payertype_a) {
				case "MEDICARE":{
					click(payerTypeMedicareOption, "MEDICARE");
					break;
				}
				case "NON MEDICARE":{
					click(payerTypeNonMedicareOption, "Non - MEDICARE");
					break;
				}
				default:
					report(LogStatus.WARNING, "Payer Type is not valid");
					
				}
			}
			
			//Additional Previous payer
			if(Objects.nonNull(noOfPreviousPayer) && 
					!noOfPreviousPayer.equals("")) {
				if(Integer.parseInt(noOfPreviousPayer) > 1) {
					for(int i = 1; i<=2; i++) {
						if(addPreviousPayerButton.isEnabled())
							click(addPreviousPayerButton, "Add previouss Payer");
					}
					if(Integer.parseInt(noOfPreviousPayer) == 2) {
						driver.findElement(By.xpath("(//span[contains"
								+ "(text(),'Remove')]/parent::button)[2]")).click();
						waitUntilClickable(unsavedChagesOK, 10);
						click(unsavedChagesOK, "OK");
						modifyPrimaryPayeB(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b);
					}else if(Integer.parseInt(noOfPreviousPayer) == 3) {
						modifyPrimaryPayeBAndC(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b, payer_c, healthplanid_c, relinfo_c, 
								benfitassignment_c, priorpaymentamt_c, estamountdue_c, 
								payertype_c);
						
					}
				}else {
					for(int i = 1 ; i <= 2 ; i++) {
						try {
							driver.findElement(By.xpath("(//span[contains"
									+ "(text(),'Remove')]/parent::button)[2]")).click();
							waitUntilClickable(unsavedChagesOK, 10);
							click(unsavedChagesOK, "OK");
						}catch (NoSuchElementException e) {
							break;
						}
					}
				}
			}else {
				report(LogStatus.INFO,"No Of previous payer is empty or null");
			}
			
			//Billing Provider NPI
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				report(LogStatus.WARNING, "Billing Provider NPI is empty or null");
			}
			
			//Other Provider ID
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				if(!actualOtherProviderID.equals(othrprvid)) {
					otherProviderID.clear();
					sendKeys(otherProviderID, "Other Provider ID", othrprvid);
					actualOtherProviderID = getAttribute(otherProviderID, "value");
					assertEquals(actualOtherProviderID, othrprvid, "The value from other"
							+ " Provider ID field is : "+actualOtherProviderID+" not "
									+ othrprvid);
				}else {
					report(LogStatus.INFO,"Other Provider ID is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Other Provider ID is empty or null");
			}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				if(!actualInsuredNameA.equals(insrdname_a)) {
					insuredNameA.clear();
					sendKeys(insuredNameA, "Insured Name A", insrdname_a);
					actualInsuredNameA = getAttribute(insuredNameA, "value");
					assertEquals(actualInsuredNameA, insrdname_a, "The value from "
							+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
				}else {
					report(LogStatus.INFO, "Insured Name A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name A is empty or null");
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				if(!actualInsuredNameB.equals(insrdname_b)) {
					insuredNameB.clear();
					sendKeys(insuredNameB, "Insured Name B", insrdname_b);
					actualInsuredNameB = getAttribute(insuredNameB, "value");
					assertEquals(actualInsuredNameB, insrdname_b, "The value from "
							+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
				}else {
					report(LogStatus.INFO, "Insured Name B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name B is empty or null");
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				if(!actualInsuredNameC.equals(insrdname_c)) {
					insuredNameC.clear();
					sendKeys(insuredNameC, "Insured Name C", insrdname_c);
					actualInsuredNameC = getAttribute(insuredNameC, "value");
					assertEquals(actualInsuredNameC, insrdname_c, "The value from "
							+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
				}else {
					report(LogStatus.INFO, "Insured Name C is same and not changed");
				}
			
			}else {
				report(LogStatus.WARNING, "Insured Name C is empty or null");
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				if(!actualPatRelInsuredA.equals(patreltoinsure_a)) {
					patientRelatedToInsuranceA.clear();
					sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
							patreltoinsure_a);
					actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
							"value");
					if(patreltoinsure_a.length()>2)
						assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
							+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
							patreltoinsure_a);
					else
						assertEquals(actualPatRelInsuredA, patreltoinsure_a, "The value from "
								+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
								patreltoinsure_a);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure A is empty or null");
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				if(!actualPatRelInsuredB.equals(patreltoinsure_b)) {
					patientRelatedToInsuranceB.clear();
					sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
							patreltoinsure_b);
					actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
							"value");
					if(patreltoinsure_b.length()>2)
						assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
							patreltoinsure_b);
					else
						assertEquals(actualPatRelInsuredB, patreltoinsure_b, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
								patreltoinsure_b);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure B is empty or null");
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				if(!actualPatRelInsuredC.equals(patreltoinsure_c)) {
					patientRelatedToInsuranceC.clear();
					sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
							patreltoinsure_c);
					actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
							"value");
					if(patreltoinsure_c.length()>2)
						assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
							patreltoinsure_c);
					else
						assertEquals(actualPatRelInsuredC, patreltoinsure_c, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
								patreltoinsure_c);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure C is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure C is empty or null");
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				if(!actualInsuredUniqueIDA.equals(insuredunqid_a)) {
					insuredsUniqueIDA.clear();
					sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
							insuredunqid_a);
					actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
							"value");
					assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
							+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
							insuredunqid_a);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID A is empty or null.");
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				if(!actualInsuredUniqueIDB.equals(insuredunqid_b)) {
					insuredsUniqueIDB.clear();
					sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
							insuredunqid_b);
					actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
							"value");
					assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
							insuredunqid_b);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID B is empty or null.");
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				if(!actualInsuredUniqueIDC.equals(insuredunqid_c)) {
					insuredsUniqueIDC.clear();
					sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
							insuredunqid_c);
					actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
							"value");
					assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
							insuredunqid_c);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID C is empty or null.");
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				if(!actualInsuredGroupNameA.equals(insrdgrpnm_a)) {
					insuredGroupNameA.clear();
					sendKeys(insuredGroupNameA, "Insured Unique ID A", 
							insrdgrpnm_a);
					actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
							"value");
					assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
							+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
							insrdgrpnm_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Name A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name A is empty or null.");
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				if(!actualInsuredGroupNameB.equals(insrdgrpnm_b)) {
					insuredGroupNameB.clear();
					sendKeys(insuredGroupNameB, "Insured Unique ID B", 
							insrdgrpnm_b);
					actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
							"value");
					assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
							+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
							insrdgrpnm_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Name B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name B is empty or null.");
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				if(!actualInsuredGroupNameC.equals(insrdgrpnm_c)) {
					insuredGroupNameC.clear();
					sendKeys(insuredGroupNameC, "Insured Unique ID C", 
							insrdgrpnm_c);
					actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
							"value");
					assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
							+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
							insrdgrpnm_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Name C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name C is empty or null.");
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				if(!actualInsuredGroupNumberA.equals(insrdgrpno_a)) {
					insuredGroupNumberA.clear();
					sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
							insrdgrpno_a);
					actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
							"value");
					assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
							+ "Insured Number A is : "+actualInsuredGroupNumberA+" not "+
							insrdgrpno_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number A is empty or null.");
			}
			
			//Insured Group Number B
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				if(!actualInsuredGroupNumberB.equals(insrdgrpno_a)) {
					insuredGroupNumberB.clear();
					sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
							insrdgrpno_b);
					actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
							"value");
					assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
							+ "Insured Number B is : "+actualInsuredGroupNumberB+" not "+
							insrdgrpno_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number B is empty or null.");
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				if(!actualInsuredGroupNumberC.equals(insrdgrpno_c)) {
					insuredGroupNumberC.clear();
					sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
							insrdgrpno_c);
					actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
							"value");
					assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
							+ "Insured Number C is : "+actualInsuredGroupNumberC+" not "+
							insrdgrpno_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number C is empty or null.");
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				if(!actualTreatmentAuthCodeA.equals(txauthcd_a)) {
					treatmentAuthCodesA.clear();
					sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
					actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
							"value");
					assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code A is empty or null.");
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				if(!actualTreatmentAuthCodeB.equals(txauthcd_b)) {
					treatmentAuthCodesB.clear();
					sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
					actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
							"value");
					assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code B is empty or null.");
			}
			
			//Treatment Authorization code C
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				if(!actualTreatmentAuthCodeC.equals(txauthcd_c)) {
					treatmentAuthCodesC.clear();
					sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
					actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
							"value");
					assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code C is empty or null.");
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				if(!actualResubmissionClaimA.equals(refclm_a)) {
					resubmissionClaimNumberA.clear();
					sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
					actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
							"value");
					assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
				}else {
					report(LogStatus.INFO, "The Document Control Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number A is empty or null.");
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				if(!actualResubmissionClaimB.equals(refclm_a)) {
					resubmissionClaimNumberB.clear();
					sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
					actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
							"value");
					assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
				}else {
					report(LogStatus.INFO, "The Document Control Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number B is empty or null.");
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				if(!actualResubmissionClaimC.equals(refclm_c)) {
					resubmissionClaimNumberC.clear();
					sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
					actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
							"value");
					assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
				}else {
					report(LogStatus.INFO, "The Document Control Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number C is empty or null.");
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				if(!actualEmployerNameA.equals(empnm_a)) {
					employerNameA.clear();
					sendKeys(employerNameA, "Employer Name A", empnm_a);
					actualEmployerNameA = getAttribute(employerNameA, 
							"value");
					assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
							+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
				}else {
					report(LogStatus.INFO, "The Employer A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer A is empty or null.");
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				if(!actualEmployerNameB.equals(empnm_b)) {
					employerNameB.clear();
					sendKeys(employerNameB, "Employer Name B", empnm_b);
					actualEmployerNameB = getAttribute(employerNameB, 
							"value");
					assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
							+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
				}else {
					report(LogStatus.INFO, "The Employer B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer B is empty or null.");
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				if(!actualEmployerNameC.equals(empnm_c)) {
					employerNameC.clear();
					sendKeys(employerNameC, "Employer Name C", empnm_c);
					actualEmployerNameC = getAttribute(employerNameC, 
							"value");
					assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
							+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
				}else {
					report(LogStatus.INFO, "The Employer C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer C is empty or null.");
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}else {
				report(LogStatus.WARNING, "Diagnosis Version is empty or null.");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				if(!actualPrincipalDiagnosis.equals(principaldiag)) {
					principalDIagnosis.clear();
					sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
					waitForLoadingToDisappear();
					actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
							"value");
					assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
							+ "code from field is : "+actualPrincipalDiagnosis + " not : "
							+principaldiag);
				}else {
					report(LogStatus.INFO, "The Principal Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The principal diagnosis is empty or null");
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				if(!actualOtherDiagnosisA.equals(othrdiag_a)) {
					otherDiagnosisA.clear();
					sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
					waitForLoadingToDisappear();
					actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
							"value");
					assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisA + " not : "
							+othrdiag_a);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis A is empty or null");
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_b) && !othrdiag_b.equals("")) {
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				if(!actualOtherDiagnosisB.equals(othrdiag_b)) {
					otherDiagnosisB.clear();
					sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
					waitForLoadingToDisappear();
					actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisB + " not : "
							+othrdiag_b);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis B is empty or null");
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				if(!actualOtherDiagnosisC.equals(othrdiag_c)) {
					otherDiagnosisC.clear();
					sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
					waitForLoadingToDisappear();
					actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
							"value");
					assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisC + " not : "
							+othrdiag_c);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis C is empty or null");
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				if(!actualOtherDiagnosisD.equals(othrdiag_d)) {
					otherDiagnosisD.clear();
					sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
					waitForLoadingToDisappear();
					actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
							"value");
					assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisD + " not : "
							+othrdiag_d);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis D is empty or null");
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				if(!actualOtherDiagnosisE.equals(othrdiag_e)) {
					otherDiagnosisE.clear();
					sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
					waitForLoadingToDisappear();
					actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
							"value");
					assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisE + " not : "
							+othrdiag_e);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis E is empty or null");
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				if(!actualOtherDiagnosisF.equals(othrdiag_f)) {
					otherDiagnosisF.clear();
					sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
					waitForLoadingToDisappear();
					actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
							"value");
					assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisF + " not : "
							+othrdiag_f);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis F is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis F is empty or null");
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				if(!actualOtherDiagnosisG.equals(othrdiag_g)) {
					otherDiagnosisG.clear();
					sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
					waitForLoadingToDisappear();
					actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
							"value");
					assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisG + " not : "
							+othrdiag_g);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis G is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis G is empty or null");
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				if(!actualOtherDiagnosisH.equals(othrdiag_h)) {
					otherDiagnosisH.clear();
					sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
					waitForLoadingToDisappear();
					actualOtherDiagnosisH = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisH + " not : "
							+othrdiag_h);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis H is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis H is empty or null");
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				if(!actualOtherDiagnosisI.equals(othrdiag_i)) {
					otherDiagnosisI.clear();
					sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
					waitForLoadingToDisappear();
					actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
							"value");
					assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisI + " not : "
							+othrdiag_i);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis I is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis I is empty or null");
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisJ,
						"value");
				if(!actualOtherDiagnosisJ.equals(othrdiag_j)) {
					otherDiagnosisJ.clear();
					sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
					waitForLoadingToDisappear();
					actualOtherDiagnosisJ = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisJ + " not : "
							+othrdiag_j);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis J is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis J is empty or null");
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				if(!actualOtherDiagnosisK.equals(othrdiag_k)) {
					otherDiagnosisK.clear();
					sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
					waitForLoadingToDisappear();
					actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
							"value");
					assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisK + " not : "
							+othrdiag_k);
				}
				else {
					report(LogStatus.INFO, "The Other Diagnosis K is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis K is empty or null");
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				if(!actualOtherDiagnosisL.equals(othrdiag_l)) {
					otherDiagnosisL.clear();
					sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
					waitForLoadingToDisappear();
					actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
							"value");
					assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisL + " not : "
							+othrdiag_l);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis L is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis L is empty or null");
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				if(!actualOtherDiagnosisM.equals(othrdiag_m)) {
					otherDiagnosisM.clear();
					sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
					waitForLoadingToDisappear();
					actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
							"value");
					assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisM + " not : "
							+othrdiag_m);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis M is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis M is empty or null");
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				if(!actualOtherDiagnosisN.equals(othrdiag_n)) {
					otherDiagnosisN.clear();
					sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
					waitForLoadingToDisappear();
					actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
							"value");
					assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisN + " not : "
							+othrdiag_n);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis N is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis N is empty or null");
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				if(!actualOtherDiagnosisO.equals(othrdiag_o)) {
					otherDiagnosisO.clear();
					sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
					waitForLoadingToDisappear();
					actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
							"value");
					assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisO + " not : "
							+othrdiag_o);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis O is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis O is empty or null");
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				if(!actualOtherDiagnosisP.equals(othrdiag_p)) {
					otherDiagnosisP.clear();
					sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
					waitForLoadingToDisappear();
					actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
							"value");
					assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisP + " not : "
							+othrdiag_p);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis P is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis P is empty or null");
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				if(!actualOtherDiagnosisQ.equals(othrdiag_q)) {
					otherDiagnosisQ.clear();
					sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
					waitForLoadingToDisappear();
					actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
							"value");
					assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisQ + " not : "
							+othrdiag_q);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis Q is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis Q is empty or null");
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				if(!actualAdmissionDiagnosis.equals(admsndiag)) {
					admissionDiagnosis.clear();
					sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
					waitForLoadingToDisappear();
					actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
							"value");
					assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
							+ "code from field is : "+actualAdmissionDiagnosis + " not : "
							+admsndiag);
				}else {
					report(LogStatus.INFO, "The Admission Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Admission Diagnosis is empty or null");
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				if(!actualPatientReasonDiagnosisA.equals(patrsndiag_a)) {
					patientReasonDiagnosisA.clear();
					sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
							patrsndiag_a);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
							"value");
					assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
							+patrsndiag_a);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis A is empty or null");
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				if(!actualPatientReasonDiagnosisB.equals(patrsndiag_b)) {
					patientReasonDiagnosisB.clear();
					sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
							patrsndiag_b);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
							"value");
					assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
							+patrsndiag_b);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis B is empty or null");
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				if(!actualPatientReasonDiagnosisC.equals(patrsndiag_b)) {
					patientReasonDiagnosisC.clear();
					sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
							patrsndiag_c);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
							"value");
					assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
							+patrsndiag_c);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis C is empty or null");
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				String actualPPSCode = getAttribute(ppsCode, "value");
				if(!actualPPSCode.equals(ppscd)) {
					ppsCode.clear();
					sendKeys(ppsCode, "PPS Code", ppscd);
					actualPPSCode = getAttribute(ppsCode, "value");
					assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
					+actualPPSCode+" not : "+ppscd);
				}else {
					report(LogStatus.INFO, "The PPS Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The PPS Code is empty or null");
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				if(!actualECIDiagnosisA.equals(ecidiagcd_a)) {
					eciDiagnosisCodeA.clear();
					sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
							ecidiagcd_a);
					waitForLoadingToDisappear();
					actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
							"value");
					assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisA + " not : "
							+ecidiagcd_a);
				}else {
					report(LogStatus.INFO, "The ECI A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI A is empty or null");
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				if(!actualECIDiagnosisB.equals(ecidiagcd_b)) {
					eciDiagnosisCodeB.clear();
					sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
							ecidiagcd_b);
					waitForLoadingToDisappear();
					actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
							"value");
					assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisB + " not : "
							+ecidiagcd_b);
				}else {
					report(LogStatus.INFO, "The ECI B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI B is empty or null");
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				if(!actualECIDiagnosisC.equals(ecidiagcd_c)) {
					eciDiagnosisCodeC.clear();
					sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
							ecidiagcd_c);
					waitForLoadingToDisappear();
					 actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
							"value");
					assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisC + " not : "
							+ecidiagcd_c);
				}else {
					report(LogStatus.INFO, "The ECI C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI C is empty or null");
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, 
						"value");
				if(!actualPrincipleProcCode.equals(principlepccd)) {
					principleProcedureCode.clear();
					sendKeys(principleProcedureCode, "Principle Proc Code", 
							principlepccd);
					actualPrincipleProcCode = getAttribute(principleProcedureCode, 
							"value");
					if(principlepccd.length()>7)
						assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
							+ "code from field is : "+actualPrincipleProcCode + " not : "
							+principlepccd);
					else
						assertEquals(actualPrincipleProcCode, principlepccd, "The Principle Proc "
								+ "code from field is : "+actualPrincipleProcCode + " not : "
								+principlepccd);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
						"value");
				if(!actualPrincipleProcCodeDate.equals(principlepcdt)) {
					for(int i = 0 ; i < 10 ; i++)
						principlePCDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
					actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
							"value");
					assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
							"The Principle Proc code from field is : "
							+actualPrincipleProcCodeDate + " not : "+principlepcdt);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				if(!actualOtherProcCodeA.equals(othrpccd_a)) {
					otherProcedureCodeA.clear();
					sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
					actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
							"value");
					if(othrpccd_a.length() > 7)
						assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeA + " not : "
							+othrpccd_a);
					else
						assertEquals(actualOtherProcCodeA, othrpccd_a, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeA + " not : "
								+othrpccd_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code A is same and not changed.");
				}	
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code A is empty or null");
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				if(!actualOtherProcCodeDateA.equals(othrpcdt_a)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
					actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
					assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date A is empty or null");
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				if(!actualOtherProcCodeB.equals(othrpccd_b)) {
					otherProcedureCodeB.clear();
					sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
					actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
							"value");
					if(othrpccd_b.length() > 7)
						assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeB + " not : "
							+othrpccd_b);
					else
						assertEquals(actualOtherProcCodeB, othrpccd_b, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeB + " not : "
								+othrpccd_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code B is empty or null");
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				if(!actualOtherProcCodeDateB.equals(othrpcdt_b)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
					actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
					assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date B is empty or null");
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				if(!actualOtherProcCodeC.equals(othrpccd_c)) {
					otherProcedureCodeC.clear();
					sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
					actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
							"value");
					if(othrpccd_c.length() > 7)
						assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeC + " not : "
							+othrpccd_c);
					else
						assertEquals(actualOtherProcCodeC, othrpccd_c, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeC + " not : "
								+othrpccd_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code C is empty or null");
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				if(!actualOtherProcCodeDateC.equals(othrpcdt_c)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateC.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
					actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
					assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date C is empty or null");
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				if(!actualOtherProcCodeD.equals(othrpccd_d)) {
					otherProcedureCodeD.clear();
					sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
					actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
							"value");
					if(othrpccd_d.length() > 7)
						assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeD + " not : "
							+othrpccd_d);
					else
						assertEquals(actualOtherProcCodeD, othrpccd_d, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeD + " not : "
								+othrpccd_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code D is empty or null");
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				if(!actualOtherProcCodeDateD.equals(othrpcdt_d)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateD.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
					actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
					assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date D is empty or null");
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				if(!actualOtherProcCodeE.equals(othrpccd_e)) {
					otherProcedureCodeE.clear();
					sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
					actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
							"value");
					if(othrpccd_e.length() > 7)
						assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeE + " not : "
							+othrpccd_e);
					else
						assertEquals(actualOtherProcCodeE, othrpccd_e, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeE + " not : "
								+othrpccd_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code E is empty or null");
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				if(!actualOtherProcCodeDateE.equals(othrpcdt_e)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateE.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
					actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
					assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date E is empty or null");
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				if(!actualAttendingNPI.equals(attndphynpi)) {
					attendingPhysicianNPI.clear();
					sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
					actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
					assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
					+actualAttendingNPI+" not : "+attndphynpi);
				}else {
					report(LogStatus.INFO, "The Attending Physician NPI is same and not changed.");
				}
			}else {
					report(LogStatus.WARNING,"The Attending Physician NPI is null or empty");
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				if(!actualAttendingPhysicianQual1.equals(attndphyqual1)) {
					attendingPhysicianQual1.clear();
					sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
							attndphyqual1);
					actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
							"value");
					assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
					+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier1 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier1 is null or empty");
		}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				if(!actualAttendingPhysicianQual2.equals(attndphyqual2)) {
					attendingPhysicianQual2.clear();
					sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
							attndphyqual2);
					actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
							"value");
					assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
					+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier2 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier2 is null or empty");
		}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				if(!actualAttendingPhysicianFirstName.equals(attndphyfn)) {
					attendingPhysicianFirstName.clear();
					sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
							attndphyfn);
					actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
							"value");
					assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
					+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
				}else {
					report(LogStatus.INFO, "The Attending Physician FirstName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician FirstName is null or empty");
		}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				if(!actualAttendingPhysicianLastName.equals(attndphyln)) {
					attendingPhysicianLastName.clear();
					sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
							attndphyln);
					actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
							"value");
					assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
					+actualAttendingPhysicianLastName+" not : "+attndphyln);
				}else {
					report(LogStatus.INFO, "The Attending Physician LastName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician LastName is null or empty");
		}
			
		//Operating Physician NPI
		if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
			String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
					"value");
			if(!actualOperatingNPI.equals(oprtphynpi)) {
				operatingPhysicianNPI.clear();
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}else {
				report(LogStatus.INFO, "The Operating Physician NPI is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician NPI is null or empty");
		}
			
		//Operating Physician Qualifier1
		if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
			String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
					"value");
			if(!actualOperatingPhysicianQual1.equals(oprtphyqual1)) {
				operatingPhysicianQual1.clear();
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier1 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier1 is null or empty");
		}
		
		//Operating Physician Qualifier2
		if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
			String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
					"value");
			if(!actualOperatingPhysicianQual2.equals(oprtphyqual2)) {
				operatingPhysicianQual2.clear();
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier2 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier2 is null or empty");
		}
		
		//Operating Physician FirstName
		if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
			String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
					"value");
			if(!actualOperatingPhysicianFirstName.equals(oprtphyfn)) {
				operatingPhysicianFirstName.clear();
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
						+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}else {
				report(LogStatus.INFO, "The Operating Physician FirstName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician FirstName is null or empty");
		}
		
		//Operating Physician LasttName
		if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
			String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
					"value");
			if(!actualOperatingPhysicianLastName.equals(oprtphyln)) {
				operatingPhysicianLastName.clear();
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}else {
				report(LogStatus.INFO, "The Operating Physician LastName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician LastName is null or empty");
		}
		
		//Other Physician NPI A
		if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
			String actualOtherNPIA = getAttribute(otherNPIA, "value");
			if(!actualOtherNPIA.equals(othrnpi_a)) {
				otherNPIA.clear();
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI Ais same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI A is null or empty");
		}
		
		//Other Physician Qualifier1 A
		if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
			String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
					"value");
			if(!actualOtherNPIQual1A.equals(othrnpiqual1_a)) {
				otherNPIQual1A.clear();
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 A is null or empty");
		}
		
		//Other Physician Qualifier2 A
		if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
			String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
					"value");
			if(!actualOtherNPIQual2A.equals(othrnpiqual2_a)) {
				otherNPIQual2A.clear();
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 A is null or empty");
		}
		
		//Other Physician First Name A
		if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
			String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
					"value");
			if(!actualOtherPhysicianFirstNameA.equals(othrnpifn_a)) {
				otherNPIFirstNameA.clear();
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName A is null or empty");
		}
		
		//Other Physician LastName A
		if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
			String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
					"value");
			if(!actualOtherPhysicianLastNameA.equals(othrnpiln_a)) {
				otherNPILastNameA.clear();
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName A is null or empty");
		}
		
		//Other Physician NPI B
		if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
			String actualOtherNPIB = getAttribute(otherNPIB, "value");
			if(!actualOtherNPIB.equals(othrnpi_b)) {
				otherNPIB.clear();
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI B is null or empty");
		}
		
		//Other Physician Qualifier1 B
		if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
			String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
					"value");
			if(!actualOtherNPIQual1B.equals(othrnpiqual1_b)) {
				otherNPIQual1B.clear();
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 B is null or empty");
		}
		
		//Other Physician Qualifier2 B
		if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
			String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
					"value");
			if(!actualOtherNPIQual2B.equals(othrnpiqual2_b)) {
				otherNPIQual2B.clear();
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 B is null or empty");
		}
		
		//Other Physician First Name B
		if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
			String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
					"value");
			if(!actualOtherPhysicianFirstNameB.equals(othrnpifn_b)) {
				otherNPIFirstNameB.clear();
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName B is null or empty");
		}
		
		//Other Physician LastName B
		if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
			String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
					"value");
			if(!actualOtherPhysicianLastNameB.equals(othrnpiln_b)) {
				otherNPILastNameB.clear();
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName B is null or empty");
		}
		
		//Remarks
		if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
			String actualRemarks = getAttribute(remarks, "value");
			if(!actualRemarks.equals(reMarks)) {
				remarks.clear();
				sendKeys(remarks, "Remarks", reMarks);
				actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}else {
				report(LogStatus.INFO, "The Remarks is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Remarks is null or empty");
		}
		
		//Taxonomy
		if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
			form81ATaxanomy.clear();
			sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
			String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
			waitForLoadingToDisappear();
			WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
			click(taxonomy, form81taxonomy_a);
		}else {
			report(LogStatus.WARNING,"The Taxonomy is null or empty");
		}
		
		//Form 81 A value
		if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
			String actualForm81AValue = getAttribute(form81AValue, "value");
			if(!actualForm81AValue.equals(form81value_a)) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}else {
				report(LogStatus.INFO,"The Form 81 A value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 A value is null or empty");
		}
		
		//Form 81 B Qualifier
		if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
			String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
			if(!actualForm81BQualifier.equals(form81qualifier_b)) {
				form81BQualifier.clear();
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Qualifier value is null or empty");
		}
		
		//Form 81 B Taxonomy
		if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
			String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
			if(!actualForm81BTaxonomy.equals(form81taxonomy_b)) {
				form81BTaxanomy.clear();
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Taxonomy value is null or empty");
		}
		
		//Form 81 B Value
		if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
			String actualForm81BValue = getAttribute(form81BValue, "value");
			if(!actualForm81BValue.equals(form81value_b)) {
				form81BValue.clear();
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Value is null or empty");
		}
		
		//Form 81 C Qualifier
		if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
			String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
			if(!actualForm81CQualifier.equals(form81qualifier_c)) {
				form81CQualifier.clear();
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Qualifier is null or empty");
		}
		
		//Form 81 C Taxonomy
		if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
			String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
			if(!actualForm81CTaxonomy.equals(form81taxonomy_c)) {
				form81CTaxanomy.clear();
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Taxonomy is null or empty");
		}
		
		//Form 81 C Value
		if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
			String actualForm81CValue = getAttribute(form81CValue, "value");
			if(!actualForm81CValue.equals(actualForm81CValue)) {
				form81CValue.clear();
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Value is null or empty");
		}
		
		//Form 81 D Qualifier
		if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
			String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
			if(!actualForm81DQualifier.equals(form81qualifier_d)) {
				form81DQualifier.clear();
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Qualifier is null or empty");
		}
		
		//Form 81 D Taxonomy
		if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
			String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
			if(!actualForm81DTaxonomy.equals(form81taxonomy_d)) {
				form81DTaxanomy.clear();
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}else {
				report(LogStatus.INFO,"The //Form 81 D Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Taxonomy is null or empty");
		}
		
		//Form 81 D Value
		if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
			String actualForm81DValue = getAttribute(form81DValue, "value");
			if(!actualForm81DValue.equals(form81value_d)) {
				form81DValue.clear();
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Value is null or empty");
		}
		
		//Received Date
		if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
			String actualReceivedDate = getAttribute(receivedDate, "value");
			if(!actualReceivedDate.equals(receveddate)) {
				for(int i = 0 ; i < 10 ; i++)
					receivedDate.sendKeys(Keys.BACK_SPACE);
				sendKeys(receivedDate, "Received Date", receveddate);
				actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}else {
				report(LogStatus.INFO,"The Received Date is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Received Date is null or empty");
		}
		
		click(submitButton, "Submit Claim");
		
		waitForLoadingToDisappear();
		
		String alertXpath = "//*[@role='alertdialog']";
		WebElement alertEle = driver.findElement(By.xpath(alertXpath));
		putStaticWait(2);
		waitUntilElementVisible(By.xpath(alertXpath), 20);
		String alerttext = getAttribute(alertEle,"aria-label");
		String alerttext1 = getAttribute(alertEle,"innerHTML");
		System.out.println(alerttext);
		System.out.println(alerttext1);
		alertEle.click();
		if(Objects.nonNull(alerttext)) {

			if(alerttext.contains("success") || alerttext.contains("Success") ) {
				report(LogStatus.PASS, "Successfully submitted");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
				try {
					throw new CannotCreateClaimException("Not able to submit the "
							+ "claim due to error : \n"+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}else if(Objects.nonNull(alerttext1)) {
			if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
				report(LogStatus.PASS, "Successfully submitted");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext1);
				try {
					throw new CannotCreateClaimException("Not able to submit the "
							+ "claim due to error : \n"+alerttext1);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}
		waitForLoadingToDisappear();	
		}else {
			report(LogStatus.FAIL, "Update UB-04 screen is not displayed.");
			try {
				throw new Exception("Update UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		waitForLoadingToDisappear();
		
		return myMCSNumber;
		
	}
	
	public String createUB04AndSaveClaim(String className ,String claimID ) {

		String myMCSNumber = "";
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
//		String revcd = dataMap.get("revenueCode");
//		String pccd = dataMap.get("serviceCode");
//		String srvcdt = dataMap.get("serviceDate");
//		String noofunits =dataMap.get("units");
//		String charges = dataMap.get("totalCharges");
//		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		if(clickCreateButton()) {
			report(LogStatus.PASS, "Create UB-04 page is Displayed");
			myMCSNumber = getText(myMCSClaimnumber);
			waitUntilClickable(providerSearchButton, 20);
			click(providerSearchButton, "Provider Search");
			
			//Provider selection
			if(providerSearchPopupHeading.isDisplayed()) {
				report(LogStatus.PASS,"Provider Search popup is displayed.");
			}else {
				report(LogStatus.FAIL,"Provider Search popup is not displayed.");
			}
			if(!prvid.equals("") && Objects.nonNull(prvid)) {
				sendKeys(providerSeachProviderID, "Provider ID", prvid);
				click(providerSearchSearcButton, "Search");
				waitForLoadingToDisappear();
				String rownumber = providerSearchGridRow.replace("XX", "1");
				WebElement providerrow = driver.findElement(By.xpath(rownumber));
				click(providerrow, "First provider record");
				click(providerSearchSelectProviderButton, "Select Provider");
				waitForLoadingToDisappear();
				String actualProviderID = getAttribute(providerIDText, "value");
				if(Objects.nonNull(actualProviderID) && 
						!actualProviderID.equals("")) {
					assertEquals(actualProviderID, prvid, "The provider id from field "
							+ "is : "+actualProviderID+" not : "+prvid);
				}else {
					try {
						throw new Exception("Provider ID from feild is null or empty");
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}
			}else {
				try {
					throw new Exception("Provider ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Site selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				click(siteSelectionDropdown, "Site dropdown");
				String siteElement = dropdownOptions.replace("XX", steid);
				WebElement site_ele = driver.findElement(By.xpath(siteElement));
				click(site_ele, "Site");
				waitForLoadingToDisappear();
				String actualSite = getText(siteIDText);
				String[] actualSiteID = actualSite.split(" - ");
				assertEquals(actualSiteID[0], steid,"The site id from field "
							+ "is : "+actualSiteID[0]+" not : "+steid);
			}else {
				try {
					throw new Exception("Site ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Patient Search
			waitUntilClickable(patientSearchButton, 20);
			click(patientSearchButton, "Patient Search");
			if(patientSearchHeading.isDisplayed()) {
				report(LogStatus.PASS,"Patient Search popup is displayed.");
			}else {
				report(LogStatus.FAIL,"Patient Search popup is not displayed.");
			}
			if(Objects.nonNull(patid) && !patid.equals("")) {
				sendKeys(patientSearchPatientID, "Patient ID", patid);
				String actualPatientID = getAttribute(patientSearchPatientID, "value");
				assertEquals(actualPatientID, patid, "The Patient Control Number from "
						+ "field is : "+actualPatientID+" not : "+patid);
				click(patientSearchSearchButton, "Search");
				waitForLoadingToDisappear();
				String rownumber = patientSearchGridRow.replace("XX", "1");
				WebElement patientrow = driver.findElement(By.xpath(rownumber));
				click(patientrow, "First patient record");
				click(patientSearchSelectPatientButton, "Select Patient");
				waitForLoadingToDisappear();
			}else {
				try {
					throw new Exception("Patient ID is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Patient Control Number edit
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				patientControlNumber.clear();
				sendKeys(patientControlNumber, "Patient Control Number", pcn);
				String actualPCN = getAttribute(patientControlNumber, "value");
				assertEquals(actualPCN, pcn, "The Patient Control Number from field "
						+ "is : "+actualPCN+" not : "+pcn);
			}else {
				report(LogStatus.WARNING, "Patient Control Number is empty or null.");
			}
			
			//Bill Type edit
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				sendKeys(billType, "Bill Type", billtype);
				String actualBillType = getAttribute(billType, "value");
				assertEquals(actualBillType, billtype, "The Patient Control Number "
						+ "from field is : "+actualBillType+" not : "+billtype);
			}else {
				try {
					throw new Exception("Bill Type is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Statement from and to date edit
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				sendKeys(statementFromPeriod, "From Date", fromperiod);
				sendKeys(statementToPeriod, "To Date", toperiod);
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
						+ "from field is : "+actualFromDate+" not : "+fromperiod);
				assertEquals(actualToDate, toperiod, "The Statement To Period  "
						+ "from field is : "+actualToDate+" not : "+toperiod);
			}else {
				try {
					throw new Exception("From Date or To Date is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 12 Admission Date edit
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				sendKeys(admissionDate, "Admission Date", admsndt);
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
						+" is : "+actualAdmissionDate+" not : "+admsndt);
			}else {
				try {
					throw new Exception("Admission Date is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 13 Admission Hour Edit
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				click(admissionHourDropdown,"Admission Hour");
				String admsnHour = dropdownOptions.replace("XX", admsnhr);
				WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
				waitUntilClickable(admisionHour, 10);
				click(admisionHour,"Admission Hour");
				String actualAdmissionHour = getText(admissionHourText);
				assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
						+ "is : "+actualAdmissionHour+" not "+admsnhr);
				
			}else {
				report(LogStatus.WARNING, "Admission Hour is empty or null.");
			}
			
			//Form 14 Reference Type edits
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				click(visitTypeDropdown, "Visit Type");
				String refType = dropdownOptions.replace("XX", reftype);
				WebElement referenceType = driver.findElement(By.xpath(refType));
				waitUntilClickable(referenceType, 10);
				click(referenceType, reftype);
				String actualReferenceType = getText(visitTypeText);
				assertEquals(actualReferenceType, reftype,"The Reference Type from field "
						+ "is : "+actualReferenceType+" not "+reftype);
			}else {
				try {
					throw new Exception("Reference Type is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 15 Reference Source edits
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				click(referenceSourceDropdown, "Visit Type");
				String refSrc = dropdownOptions.replace("XX", refsrc);
				WebElement referenceSource = driver.findElement(By.xpath(refSrc));
				waitUntilClickable(referenceSource, 10);
				click(referenceSource, refsrc);
				String actualReferenceSource = getText(referenceSourceText);
				assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
						+ "is : "+actualReferenceSource+" not "+refsrc);
			}else {
				try {
					throw new Exception("Reference Source is empty or null.");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 16 Discharge Hour edits
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				click(dischargeHour, "Visit Type");
				String dischrgHr = dropdownOptions.replace("XX", dschrghr);
				WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
				waitUntilClickable(dischargeHour, 10);
				click(dischargeHour, dschrghr);
				String actualDischargeHour = getText(dischargeHourText);
				assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
						+ "is : "+actualDischargeHour+" not "+dschrghr);
			}else {
				report(LogStatus.WARNING, "Discharge Hour is empty or null.");
			}
			
			//Form 17 Discharge Status edits
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				click(dischargeStatus, "Visit Type");
				String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
				WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
				waitUntilClickable(dischargeStatus, 10);
				click(dischargeStatus, dschrgsts);
				String actualDischargeStatus = getText(dischargeStatusText);
				assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
						+ "is : "+actualDischargeStatus+" not "+dschrgsts);
			}else {
				report(LogStatus.WARNING, "Discharge Status is empty or null.");
			}
			
			//Form 18 Edits
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				sendKeys(form18, "Form 18", form_18);
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
						actualForm18+" not : "+actform_18);
			}else {
				report(LogStatus.WARNING, "Form 18 value is empty or null.");
			}
			
			//Form 19 Edits
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				sendKeys(form19, "Form 19", form_19);
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
						actualForm19+" not : "+actform_19);
			}else {
				report(LogStatus.WARNING, "Form 19 value is empty or null.");
			}
			
			//Form 20 Edits
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				sendKeys(form20, "Form 20", form_20);
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
						actualForm20+" not : "+actform_20);
			}else {
				report(LogStatus.WARNING, "Form 20 value is empty or null.");
			}
			
			//Form 21 Edits
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				sendKeys(form21, "Form 21", form_21);
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
						actualForm21+" not : "+actform_21);
			}else {
				report(LogStatus.WARNING, "Form 21 value is empty or null.");
			}
			
			//Form 22 Edits
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				sendKeys(form22, "Form 22", form_22);
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
						actualForm22+" not : "+actform_22);
			}else {
				report(LogStatus.WARNING, "Form 22 value is empty or null.");
			}
			
			//Form 23 Edits
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				sendKeys(form23, "Form 23", form_23);
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
						actualForm23+" not : "+actform_23);
			}else {
				report(LogStatus.WARNING, "Form 23 value is empty or null.");
			}
			
			//Form 24 Edits
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				sendKeys(form24, "Form 24", form_24);
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
						actualForm24+" not : "+actform_24);
			}else {
				report(LogStatus.WARNING, "Form 24 value is empty or null.");
			}
			
			//Form 25 Edits
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				sendKeys(form25, "Form 25", form_25);
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
						actualForm25+" not : "+actform_25);
			}else {
				report(LogStatus.WARNING, "Form 25 value is empty or null.");
			}
			
			//Form 26 Edits
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				sendKeys(form26, "Form 26", form_26);
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
						actualForm26+" not : "+actform_26);
			}else {
				report(LogStatus.WARNING, "Form 26 value is empty or null.");
			}
			
			//Form 27 Edits
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				sendKeys(form27, "Form 27", form_27);
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
						actualForm27+" not : "+actform_27);
			}else {
				report(LogStatus.WARNING, "Form 27 value is empty or null.");
			}
			
			//Form 28 Edits
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				sendKeys(form28, "Form 28", form_28);
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
						actualForm28+" not : "+actform_28);
			}else {
				report(LogStatus.WARNING, "Form 28 value is empty or null.");
			}
			
			//Form 29 Accident State Edits
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				sendKeys(accidentState, "Accident State", accdtstate);
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
						actualAccidentState+" not : "+actaccdtstate);
			}else {
				report(LogStatus.WARNING, "Accident State value is empty or null.");
			}
			
			//Form 31-A Edits
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
						+ "code from Form 31 A is : "+actualform31aoccurancecode+
						" not : "+actform31occcd_a);
				
				sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				assertEquals(actualform31adate, form31occdt_a, "The Occurance "
						+ "code from Form 31 A is : "+actualform31adate+
						" not : "+form31occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 31-A is empty or null.");
			}
			
			//Form 31-B Edits
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
						+ "code from Form 31 B is : "+actualform31boccurancecode+
						" not : "+actform31occcd_b);
				
				sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
						+ "code from Form 31 B is : "+actualform31bdate+
						" not : "+form31occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 31-B is empty or null.");
			}
			
			//Form 32-A Edits
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
						+ "code from Form 32 A is : "+actualform32aoccurancecode+
						" not : "+actform32occcd_a);
				
				sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				assertEquals(actualform32adate, form32occdt_a, "The Occurance "
						+ "code from Form 32 A is : "+actualform32adate+
						" not : "+form32occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 32-A is empty or null.");
			}
			
			//Form 32-B Edits
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
						+ "code from Form 32 B is : "+actualform32boccurancecode+
						" not : "+actform32occcd_b);
				
				sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
						+ "code from Form 32 B is : "+actualform32bdate+
						" not : "+form32occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 32-B is empty or null.");
			}
		
			//Form 33-A Edits
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form32occcd_a;
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
						+ "code from Form 33 A is : "+actualform33aoccurancecode+
						" not : "+actform33occcd_a);
				
				sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				assertEquals(actualform33adate, form33occdt_a, "The Occurance "
						+ "code from Form 33 A is : "+actualform33adate+
						" not : "+form33occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 33-A is empty or null.");
			}
			
			//Form 33-B Edits
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				assertEquals(actualform33boccurancecode, form33occcd_b, "The Occurance "
						+ "code from Form 33 B is : "+actualform33boccurancecode+
						" not : "+form33occcd_b);
				
				sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
						+ "code from Form 33 B is : "+actualform33bdate+
						" not : "+form33occdt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 33-B is empty or null.");
			}
			
			//Form 34-A Edits
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
						+ "code from Form 34 A is : "+actualform34aoccurancecode+
						" not : "+actform34occcd_a);
				
				sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				assertEquals(actualform34adate, form34occdt_a, "The Occurance "
						+ "code from Form 34 A is : "+actualform34adate+
						" not : "+form34occdt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 34-A is empty or null.");
			}
			
			//Form 34-B Edits
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
						+ "code from Form 34 B is : "+actualform34boccurancecode+
						" not : "+actform34occcd_b);
				
				sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
						+ "code from Form 34 B is : "+actualform34bdate+
						" not : "+form34occdt_b);
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 34-B is empty or null.");
			}
				
			//Form 35-A Edits
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35aoccurancecode+
						" not : "+actform35occcd_a);
				
				sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35afromdate+
						" not : "+form35occfrdt_a);
				
				sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
						form35occtodt_a);
				String actualform35atodate = 
					getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
						+ "code from Form 35 A is : "+actualform35atodate+
						" not : "+form35occtodt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 35-A is empty or null.");
			}
				
			//Form 35-B Edits
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35boccurancecode+
						" not : "+actform35occcd_b);
				
				sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35bfromdate+
						" not : "+form35occfrdt_b);
				
				sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
						form35occtodt_b);
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
						+ "code from Form 35 B is : "+actualform35btodate+
						" not : "+form35occtodt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 35-B is empty or null.");
			}
			
			//Form 36-A Edits
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36aoccurancecode+
						" not : "+actform36occcd_a);
				
				sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36afromdate+
						" not : "+form36occfrdt_a);
				
				sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
						form36occtodt_a);
				String actualform36atodate = 
					getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
						+ "code from Form 36 A is : "+actualform36atodate+
						" not : "+form36occtodt_a);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 36-A is empty or null.");
			}
				
			//Form 36-B Edits
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36boccurancecode+
						" not : "+actform36occcd_b);
				
				sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36bfromdate+
						" not : "+form36occfrdt_b);
				
				sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
						form36occtodt_b);
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
						+ "code from Form 36 B is : "+actualform36btodate+
						" not : "+form36occtodt_b);
				
			}else {
				report(LogStatus.WARNING, "Occurance Code and Date 36-B is empty or null.");
			}
			
			//Form 39-A Edits
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				assertEquals(actualform39avalcd, actform39avalcd, "The Value "
						+ "code from Form 39 A is : "+actualform39avalcd+
						" not : "+actform39avalcd);
				
				form39ValueCodeAmountA.clear();
				sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
						+ "code from Form 39 A is : "+actualform39avalcdamt+
						" not : "+form39valcdamt_a);
				
			}
			
			//Form 39-B Edits
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
						+ "code from Form 39 B is : "+actualform39bvalcd+
						" not : "+actform39bvalcd);
				
				form39ValueCodeAmountB.clear();
				sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
						+ "code from Form 39 B is : "+actualform39bvalcdamt+
						" not : "+form39valcdamt_b);
				
			}
			
			//Form 39-C Edits
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_c;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
						+ "code from Form 39 C is : "+actualform39cvalcd+
						" not : "+actform39cvalcd);
				
				form39ValueCodeAmountC.clear();
				sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
						+ "code from Form 39 D is : "+actualform39cvalcdamt+
						" not : "+form39valcdamt_c);
				
			}
			
			//Form 39-D Edits
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
						+ "code from Form 39 D is : "+actualform39dvalcd+
						" not : "+actform39dvalcd);
				
				form39ValueCodeAmountD.clear();
				sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
						+ "code from Form 39 D is : "+actualform39dvalcdamt+
						" not : "+form39valcdamt_d);
				
			}
			
			//Form 40-A Edits
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				assertEquals(actualform40avalcd, actform40avalcd, "The Value "
						+ "code from Form 40 A is : "+actualform40avalcd+
						" not : "+actform40avalcd);
				
				form40ValueCodeAmountA.clear();
				sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
						+ "code from Form 40 A is : "+actualform40avalcdamt+
						" not : "+form40valcdamt_a);
				
			}
			
			//Form 40-B Edits
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
						+ "code from Form 40 B is : "+actualform40bvalcd+
						" not : "+actform40bvalcd);
				
				form40ValueCodeAmountB.clear();
				sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
						+ "code from Form 40 B is : "+actualform40bvalcdamt+
						" not : "+form40valcdamt_b);
				
			}
			
			//Form 40-C Edits
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_c;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
						+ "code from Form 40 C is : "+actualform40cvalcd+
						" not : "+actform40cvalcd);
				
				form40ValueCodeAmountC.clear();
				sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
						+ "code from Form 40 D is : "+actualform40cvalcdamt+
						" not : "+form40valcdamt_c);
				
			}
			
			//Form 40-D Edits
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
						+ "code from Form 40 D is : "+actualform40dvalcd+
						" not : "+actform40dvalcd);
				
				form40ValueCodeAmountD.clear();
				sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
						+ "code from Form 40 D is : "+actualform40dvalcdamt+
						" not : "+form40valcdamt_d);
				
			}
			
			//Form 41-A Edits
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				assertEquals(actualform41avalcd, actform41avalcd, "The Value "
						+ "code from Form 41 A is : "+actualform41avalcd+
						" not : "+actform41avalcd);
				
				form41ValueCodeAmountA.clear();
				sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
						+ "code from Form 41 A is : "+actualform41avalcdamt+
						" not : "+form41valcdamt_a);
				
			}
			
			//Form 41-B Edits
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
						+ "code from Form 41 B is : "+actualform41bvalcd+
						" not : "+actform41bvalcd);
				
				form41ValueCodeAmountB.clear();
				sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
						+ "code from Form 41 B is : "+actualform41bvalcdamt+
						" not : "+form41valcdamt_b);
				
			}
			
			//Form 41-C Edits
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_c;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
						+ "code from Form 41 C is : "+actualform41cvalcd+
						" not : "+actform41cvalcd);
				
				form41ValueCodeAmountC.clear();
				sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
						+ "code from Form 41 D is : "+actualform41cvalcdamt+
						" not : "+form41valcdamt_c);
				
			}
			
			//Form 41-D Edits
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && 
					!form41valcdamt_d.equals("")) {
				sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
						+ "code from Form 41 D is : "+actualform41dvalcd+
						" not : "+actform41dvalcd);
				
				form41ValueCodeAmountD.clear();
				sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
						+ "code from Form 41 D is : "+actualform41dvalcdamt+
						" not : "+form41valcdamt_d);
			}
			
			//Add service
			List<Map<String, String>> serviceDataMAP = ExcelUtil.getTestCasesDataInMap
					("testData//AlphaPlusTestData.xlsx", className, "claimID", claimID);
			 Map< String, String> service ;
			 for(int i = 0 ; i < serviceDataMAP.size() ; i++) {
				 	service = serviceDataMAP.get(i);String revcd = dataMap.get("revenueCode");
					String pccd = service.get("serviceCode");
					String srvcdt = service.get("serviceDate");
					String noofunits =service.get("units");
					String charges = service.get("totalCharges");
					String noncoverdcharges = service.get("nonCoveredCharges");
					if(addService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service added successfully.");
					}else {
						report(LogStatus.FAIL,"Service not added.");
					}
			 }
			
//				//Add service
//				if(addService(revcd, pccd, srvcdt, noofunits, charges, 
//						noncoverdcharges)) {
//					report(LogStatus.PASS,"Service added successfully.");
//				}else {
//					report(LogStatus.FAIL,"Service not added.");
//				}
				
				//Previous Payer
				
					
					//Health Plan ID A
					if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
						sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
						String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
						assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
								+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);						
					}

					
					//REL INFO A
					if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
						String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
						String[] relInfoAData = relInfoAClass.split(" ");
						String actualRelInfoA = "";
						for(String s : relInfoAData) {
							if(s.equals("mat-checkbox-checked")) {
								actualRelInfoA = s;
							}
						}
						if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
							click(relInfoCheckBoxA, "Rel Info Checkbox A");
							actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
							if(actualRelInfoA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Actual Rel Info check box checked");
							}else {
								report(LogStatus.FAIL, "Actual Rel Info check box not checked");
							}
							
						}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
								relinfo_a.equals("NO")) {
							click(relInfoCheckBoxA, "Rel Info Checkbox A");
							actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
							if(!actualRelInfoA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Actual Rel Info check box unchecked");
							}else {
								report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
							}
							
						}
					}
					
					//ASN BEN A
					if(Objects.nonNull(benfitassignment_a) && 
							!benfitassignment_a.equals("")) {
						String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
						String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
						String actualBenfitAssignmentA = "";
						for(String s : benfitAssignmentAData) {
							if(s.equals("mat-checkbox-checked")) {
								actualBenfitAssignmentA = s;
							}
						}
						if(actualBenfitAssignmentA.equals("") && 
								benfitassignment_a.equals("YES")) {
							click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
							actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
							if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Benefit Assignment check box checked");
							}else {
								report(LogStatus.FAIL, "Benefit Assignment check box not checked");
							}
							
						}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
								benfitassignment_a.equals("NO")) {
							click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
							actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
							if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
								report(LogStatus.PASS, "Benefit Assignment check box unchecked");
							}else {
								report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
							}
							
						}
					}
					
					//Prior payment amount
					if(Objects.nonNull(priorpaymentamt_a) && 
							!priorpaymentamt_a.equals("")) {
						priorPaymentAmountA.clear();
						sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
						String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
						assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
								+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
								" not : "+priorpaymentamt_a);
					}
					
					//EST Due Amount
					if(Objects.nonNull(estamountdue_a) && 
							!estamountdue_a.equals("")) {
						estAmountDueA.clear();
						sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
						String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
						assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
								+"from prior payment amount field A is : "+actualESTAmountDueA+
								" not : "+estamountdue_a);
					}
					
					//primary payer
					if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
						click(payerTypeDrodownA, "Payer Type");
						switch(payertype_a) {
						case "MEDICARE":{
							click(payerTypeMedicareOption, "MEDICARE");
							break;
						}
						case "NON MEDICARE":{
							click(payerTypeNonMedicareOption, "Non - MEDICARE");
							break;
						}
						default:
							report(LogStatus.WARNING, "Payer Type is not valid");
							
						}
					}
					
					if(Objects.nonNull(noOfPreviousPayer) && 
							!noOfPreviousPayer.equals("")) {
						if(Integer.parseInt(noOfPreviousPayer) > 1) {
							System.out.println(Integer.parseInt(noOfPreviousPayer));
							for(int i = 1; i<Integer.parseInt(noOfPreviousPayer); i++) {
								click(addPreviousPayerButton, "Add previouss Payer");
							}
							
							if(noOfPreviousPayer.equals("2")) {
								addPrimaryPayeB(payer_b,healthplanid_b, relinfo_b, 
										benfitassignment_b,	priorpaymentamt_b, 
										estamountdue_b, payertype_b);
							}else if(noOfPreviousPayer.equals("3")) {
								addPrimaryPayeBAndC(payer_b,healthplanid_b, relinfo_b, 
										benfitassignment_b,	priorpaymentamt_b, 
										estamountdue_b, payertype_b, payer_c, 
										healthplanid_c, relinfo_c, benfitassignment_c, 
										priorpaymentamt_c, estamountdue_c, payertype_c);
							}
						
					}
					}
					
					
				
			
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				try {
					throw new Exception("Billing Provider NPI is null or empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				sendKeys(otherProviderID, "Other Provider ID", othrprvid);
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				assertEquals(actualOtherProviderID, othrprvid, "The value from other"
						+ " Provider ID field is : "+actualOtherProviderID+" not "
								+ othrprvid);		
				}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				sendKeys(insuredNameA, "Insured Name A", insrdname_a);
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				assertEquals(actualInsuredNameA, insrdname_a, "The value from "
						+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				sendKeys(insuredNameB, "Insured Name B", insrdname_b);
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				assertEquals(actualInsuredNameB, insrdname_b, "The value from "
						+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				sendKeys(insuredNameC, "Insured Name C", insrdname_c);
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				assertEquals(actualInsuredNameC, insrdname_c, "The value from "
						+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
						patreltoinsure_a);
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
						+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
						patreltoinsure_a);
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
						patreltoinsure_b);
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
						+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
						patreltoinsure_b);
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
						patreltoinsure_c);
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
						+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
						patreltoinsure_c);
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
						insuredunqid_a);
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
						+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
						insuredunqid_a);
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
						insuredunqid_b);
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
						+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
						insuredunqid_b);
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
						insuredunqid_c);
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
						+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
						insuredunqid_c);
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				sendKeys(insuredGroupNameA, "Insured Unique ID A", 
						insrdgrpnm_a);
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
						insrdgrpnm_a);
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				sendKeys(insuredGroupNameB, "Insured Unique ID B", 
						insrdgrpnm_b);
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
						+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
						insrdgrpnm_b);
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				sendKeys(insuredGroupNameC, "Insured Unique ID C", 
						insrdgrpnm_c);
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
						+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
						insrdgrpnm_c);
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
						insrdgrpno_a);
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNumberA+" not "+
						insrdgrpno_a);
			}
			
			//Insured Group Number B
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
						insrdgrpno_b);
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
						+ "Insured Name B is : "+actualInsuredGroupNumberB+" not "+
						insrdgrpno_b);
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
						insrdgrpno_c);
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
						+ "Insured Name A is : "+actualInsuredGroupNumberC+" not "+
						insrdgrpno_c);
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
			}
			
			//Treatment Authorization code C
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
						+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
						+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				sendKeys(employerNameA, "Employer Name A", empnm_a);
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
						+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				sendKeys(employerNameB, "Employer Name B", empnm_b);
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
						+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				sendKeys(employerNameC, "Employer Name C", empnm_c);
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
						+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
				waitForLoadingToDisappear();
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
						+ "code from field is : "+actualPrincipalDiagnosis + " not : "
						+principaldiag);
			}else {
				try {
					throw new Exception("The principal diagnosis is empty or null");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisA + " not : "
						+othrdiag_a);
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_b) && !othrdiag_b.equals("")) {
				sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisB + " not : "
						+othrdiag_b);
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisC + " not : "
						+othrdiag_c);
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisD + " not : "
						+othrdiag_d);
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisE + " not : "
						+othrdiag_e);
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisF + " not : "
						+othrdiag_f);
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisG + " not : "
						+othrdiag_g);
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisH + " not : "
						+othrdiag_h);
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisI + " not : "
						+othrdiag_i);
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisH,
						"value");
				assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisJ + " not : "
						+othrdiag_j);
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisK + " not : "
						+othrdiag_k);
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisL + " not : "
						+othrdiag_l);
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisM + " not : "
						+othrdiag_m);
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisN + " not : "
						+othrdiag_n);
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisO + " not : "
						+othrdiag_o);
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisP + " not : "
						+othrdiag_p);
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
				waitForLoadingToDisappear();
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
						+ "code from field is : "+actualOtherDiagnosisQ + " not : "
						+othrdiag_q);
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
				waitForLoadingToDisappear();
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
						+ "code from field is : "+actualAdmissionDiagnosis + " not : "
						+admsndiag);
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
						patrsndiag_a);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
						+patrsndiag_a);
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
						patrsndiag_b);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
						+patrsndiag_b);
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
						patrsndiag_c);
				waitForLoadingToDisappear();
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
						+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
						+patrsndiag_c);
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				sendKeys(ppsCode, "PPS Code", ppscd);
				String actualPPSCode = getAttribute(ppsCode, "value");
				assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
				+actualPPSCode+" not : "+ppscd);
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
						ecidiagcd_a);
				waitForLoadingToDisappear();
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisA + " not : "
						+ecidiagcd_a);
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
						ecidiagcd_b);
				waitForLoadingToDisappear();
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisB + " not : "
						+ecidiagcd_b);
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
						ecidiagcd_c);
				waitForLoadingToDisappear();
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
						+ "code from field is : "+actualECIDiagnosisC + " not : "
						+ecidiagcd_c);
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				sendKeys(principleProcedureCode, "Principle Proc Code", principlepccd);
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, "value");
				assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
						+ "code from field is : "+actualPrincipleProcCode + " not : "
						+principlepccd);
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, "value");
				assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
						"The Principle Proc code from field is : "
						+actualPrincipleProcCodeDate + " not : "+principlepcdt);
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeA + " not : "
						+othrpccd_a);
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeB + " not : "
						+othrpccd_b);
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeC + " not : "
						+othrpccd_c);
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeD + " not : "
						+othrpccd_d);
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
						+ "code from field is : "+actualOtherProcCodeE + " not : "
						+othrpccd_e);
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
						"The Principle Proc code from field is : "
						+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
				+actualAttendingNPI+" not : "+attndphynpi);
			}else {
				try {
					throw new Exception("The Attending NPI is null or empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
						attndphyqual1);
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
				+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
			}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
						attndphyqual2);
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
				+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
			}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
						attndphyfn);
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
				+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
			}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
						attndphyln);
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualAttendingPhysicianLastName+" not : "+attndphyln);
			}
			
			//Operating Physician NPI
			if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}
			
			//Operating Physician Qualifier1
			if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}
			
			//Operating Physician Qualifier2
			if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}
			
			//Operating Physician FirstName
			if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
				+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}
			
			//Operating Physician LasttName
			if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}
			
			//Other Physician NPI A
			if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				String actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}
			
			//Other Physician Qualifier1 A
			if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}
			
			//Other Physician Qualifier2 A
			if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}
			
			//Other Physician First Name A
			if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}
			
			//Other Physician LastName A
			if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}
			
			//Other Physician NPI B
			if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				String actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}
			
			//Other Physician Qualifier1 B
			if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}
			
			//Other Physician Qualifier2 B
			if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}
			
			//Other Physician First Name B
			if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}
			
			//Other Physician LastName B
			if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}
			
			//Remarks
			if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
				sendKeys(remarks, "Remarks", reMarks);
				String actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}
			
			//Taxonomy
			if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
				sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
				String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
				waitForLoadingToDisappear();
				WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
				click(taxonomy, form81taxonomy_a);
			}else {
				try {
					throw new Exception("Taxonmy is empty or null");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			//Form 81 A value
			if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				String actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}
			
			//Form 81 B Qualifier
			if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}
			
			//Form 81 B Taxonomy
			if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}
			
			//Form 81 B Value
			if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				String actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}
			
			//Form 81 C Qualifier
			if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}
			
			//Form 81 C Taxonomy
			if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}
			
			//Form 81 C Value
			if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				String actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}
			
			//Form 81 D Qualifier
			if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}
			
			//Form 81 D Taxonomy
			if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}
			
			//Form 81 D Value
			if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				String actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}
			
			//Received Date
			if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
				sendKeys(receivedDate, "Received Date", receveddate);
				String actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}
			
			click(saveButton, "Save Claim");
			
			waitForLoadingToDisappear();
			
			String alertXpath = "//*[@role='alertdialog']";
			WebElement alertEle = driver.findElement(By.xpath(alertXpath));
			putStaticWait(2);
			waitUntilElementVisible(By.xpath(alertXpath), 20);
			String alerttext = getAttribute(alertEle,"aria-label");
			String alerttext1 = getAttribute(alertEle,"innerHTML");
			System.out.println(alerttext);
			System.out.println(alerttext1);
			alertEle.click();
			if(Objects.nonNull(alerttext)) {

				if(alerttext.contains("saved") || alerttext.contains("Saved") ) {
					report(LogStatus.PASS, "Successfully submitted");
					return myMCSNumber;
				}else {
					report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext);
					try {
						throw new CannotCreateClaimException("Not able to submit the "
								+ "claim due to error : "+alerttext);
					}catch(CannotCreateClaimException e) {
						report(LogStatus.FAIL, e.getMessage());
						e.printStackTrace();
						cancelClaim();
						report(LogStatus.WARNING,"Claim is cancelled");
						return "Not able to create claim";
					}
				}
			}else if(Objects.nonNull(alerttext1)) {
				if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
					report(LogStatus.PASS, "Successfully submitted");
					return myMCSNumber;
				}else {
					report(LogStatus.FAIL,"Not submitted successfully\n"+alerttext1);
					try {
						throw new CannotCreateClaimException("Not able to submit the "
								+ "claim due to error : "+alerttext1);
					}catch(CannotCreateClaimException e) {
						report(LogStatus.FAIL, e.getMessage());
						e.printStackTrace();
						cancelClaim();
						report(LogStatus.WARNING,"Claim is cancelled");
						return "Not able to create claim";
					}
				}
			}
			
			
			
			waitForLoadingToDisappear();
			
			
		}else {
			report(LogStatus.FAIL,"Create UB-04 page is Not Displayed");
			try {
				throw new Exception("Create UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

		return myMCSNumber;
	}

	public String updateAndSaveUB04Claim() {
		
		boolean flag =  false;
		String myMCSNumber = dataMap.get("myMCSClaimNumber");
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
		String serviceLineNumber = dataMap.get("serviceLineNumber");
		String revcd = dataMap.get("revenueCode");
		String pccd = dataMap.get("serviceCode");
		String srvcdt = dataMap.get("serviceDate");
		String noofunits =dataMap.get("units");
		String charges = dataMap.get("totalCharges");
		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		filterWithMyMCSNumber(myMCSNumber);
		click(firstRecord, "");
		waitForLoadingToDisappear();
		click(updateButton, "Update");
		if(updateUB04Heading.isDisplayed()) {
			waitForLoadingToDisappear();
			report(LogStatus.PASS, "Update Popup is displayed.");
			if(Objects.nonNull(prvid) && !prvid.equals("")) {
				String actualProviderID = getAttribute(providerIDText, "value");
				if(!actualProviderID.equals(prvid)) {
					waitUntilClickable(providerSearchButton, 10);
					click(providerSearchButton, "Provider Search");
					
					//Provider selection
					if(providerSearchPopupHeading.isDisplayed()) {
						report(LogStatus.PASS,"Provider Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Provider Search popup is not displayed.");
					}
					
					sendKeys(providerSeachProviderID, "Provider ID", prvid);
					click(providerSearchSearcButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = providerSearchGridRow.replace("XX", "1");
					WebElement providerrow = driver.findElement(By.xpath(rownumber));
					click(providerrow, "First provider record");
					click(providerSearchSelectProviderButton, "Select Provider");
					waitForLoadingToDisappear();
					actualProviderID = getAttribute(providerIDText, "value");
					assertEquals(actualProviderID, prvid, "The provider id from field "
								+ "is : "+actualProviderID+" not : "+prvid);
					report(LogStatus.PASS, "Provider Id updated successfully.");
					
				}else {
					report(LogStatus.INFO, "Provider Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Provider id is null or empty.");
			}
			
			//Site Selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				String actualSiteID = getAttribute(siteIDText2, "value");
				if(!actualSiteID.equals(steid)) {
					waitForLoadingToDisappear();
					click(siteSelectionDropdown, "Site dropdown");
					String siteElement = dropdownOptions.replace("XX", steid);
					WebElement site_ele = driver.findElement(By.xpath(siteElement));
					click(site_ele, "Site");
					waitForLoadingToDisappear();
					actualSiteID = getAttribute(siteIDText2, "value");
					assertEquals(actualSiteID, steid,"The site id from field "
								+ "is : "+actualSiteID+" not : "+steid);
					report(LogStatus.PASS, "Site Id updated successfully.");
				}else {
					report(LogStatus.INFO, "Site Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Site id is null or empty.");
			}
			
			//Patient selection
			if(Objects.nonNull(patid) && !patid.equals("")) {
				String actualPatientID = getAttribute(patientIDText, "value");
				if(!actualPatientID.equals(patid)) {
					waitUntilClickable(patientSearchButton, 20);
					click(patientSearchButton, "Patient Search");
					if(patientSearchHeading.isDisplayed()) {
						report(LogStatus.PASS,"Patient Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Patient Search popup is not displayed.");
					}
					sendKeys(patientSearchPatientID, "Patient ID", patid);
					actualPatientID = getAttribute(patientSearchPatientID, "value");
					assertEquals(actualPatientID, patid, "The Patient Control Number from "
							+ "field is : "+actualPatientID+" not : "+patid);
					click(patientSearchSearchButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = patientSearchGridRow.replace("XX", "1");
					WebElement patientrow = driver.findElement(By.xpath(rownumber));
					click(patientrow, "First patient record");
					click(patientSearchSelectPatientButton, "Select Patient");
					waitForLoadingToDisappear();
				}else {
					report(LogStatus.INFO, "Patient Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient id is null or empty.");
			}
			
			//Patient Control Number
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				String actualPCN = getAttribute(patientControlNumber, "value");
				if(!actualPCN.equals(pcn)) {
					patientControlNumber.clear();
					sendKeys(patientControlNumber, "Patient Control Number", pcn);
					actualPCN = getAttribute(patientControlNumber, "value");
					assertEquals(actualPCN, pcn, "The Patient Control Number from field "
							+ "is : "+actualPCN+" not : "+pcn);
				}else {
					report(LogStatus.INFO, "Patient Control Number is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient control Number is null or empty.");
			}
			
			//Bill Type Speciality
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				String actualBillType = getAttribute(billType, "value");
				System.out.println(actualBillType+" : "+billtype);
				if(!actualBillType.equals(billtype)) {
					billType.clear();
					sendKeys(billType, "Bill Type", billtype);
					actualBillType = getAttribute(billType, "value");
					assertEquals(actualBillType, billtype, "The Patient Control Number "
							+ "from field is : "+actualBillType+" not : "+billtype);
				}else {
					report(LogStatus.INFO, "Billtype is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Bill Type is null or empty.");
			}
			
			//From and To period
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				if(!actualFromDate.equals(fromperiod) && !actualToDate.equals(toperiod)) {
					for(int i = 0 ; i < 10 ; i++)
						statementFromPeriod.sendKeys(Keys.BACK_SPACE);
					for(int i = 0 ; i < 10 ; i++)
						statementToPeriod.sendKeys(Keys.BACK_SPACE);
					sendKeys(statementFromPeriod, "From Date", fromperiod);
					sendKeys(statementToPeriod, "To Date", toperiod);
					actualFromDate = getAttribute(statementFromPeriod, "value");
					actualToDate = getAttribute(statementToPeriod, "value");
					assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
							+ "from field is : "+actualFromDate+" not : "+fromperiod);
					assertEquals(actualToDate, toperiod, "The Statement To Period  "
							+ "from field is : "+actualToDate+" not : "+toperiod);
				}else {
					report(LogStatus.INFO, "From Date or To Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"From period or To period is null or empty.");
			}
			
			//Admission Date
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				if(!actualAdmissionDate.equals(admsndt)) {
					for(int i = 0 ; i < 10 ; i++)
						admissionDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(admissionDate, "Admission Date", admsndt);
					actualAdmissionDate = getAttribute(admissionDate, "value");
					assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
							+" is : "+actualAdmissionDate+" not : "+admsndt);
				}else {
					report(LogStatus.INFO, "Admission Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission date is null or empty.");
			}
			
			//Admission Hour
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				String actualAdmissionHour = getText(admissionHourText);
				if(!actualAdmissionHour.equals(admsnhr)) {
					click(admissionHourDropdown,"Admission Hour");
					String admsnHour = dropdownOptions.replace("XX", admsnhr);
					WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
					waitUntilClickable(admisionHour, 10);
					click(admisionHour,"Admission Hour");
					actualAdmissionHour = getText(admissionHourText);
					assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
							+ "is : "+actualAdmissionHour+" not "+admsnhr);
				}else {
					report(LogStatus.INFO, "Admission Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission Hour is null or empty.");
			}
			
			//Reference Type
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				String actualReferenceType = getText(visitTypeText);
				if(!actualReferenceType.equals(reftype)) {
					click(visitTypeDropdown, "Visit Type");
					String refType = dropdownOptions.replace("XX", reftype);
					WebElement referenceType = driver.findElement(By.xpath(refType));
					waitUntilClickable(referenceType, 10);
					click(referenceType, reftype);
					actualReferenceType = getText(visitTypeText);
					assertEquals(actualReferenceType, reftype,"The Reference Type from field "
							+ "is : "+actualReferenceType+" not "+reftype);
				}else {
					report(LogStatus.INFO, "Reference type is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Type is empty or null.");
			}
			
			//Reference Source
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				String actualReferenceSource = getText(referenceSourceText);
				if(!actualReferenceSource.equals(refsrc)) {
					click(referenceSourceDropdown, "Visit Type");
					String refSrc = dropdownOptions.replace("XX", refsrc);
					WebElement referenceSource = driver.findElement(By.xpath(refSrc));
					waitUntilClickable(referenceSource, 10);
					click(referenceSource, refsrc);
					actualReferenceSource = getText(referenceSourceText);
					assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
							+ "is : "+actualReferenceSource+" not "+refsrc);
				}else {
					report(LogStatus.INFO, "Reference Source is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Source is empty or null.");
			}
			
			//Discharge Hour
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				String actualDischargeHour = getText(dischargeHourText);
				if(actualDischargeHour.equals(dschrghr)) {
					click(dischargeHour, "Visit Type");
					String dischrgHr = dropdownOptions.replace("XX", dschrghr);
					WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
					waitUntilClickable(dischargeHour, 10);
					click(dischargeHour, dschrghr);
					actualDischargeHour = getText(dischargeHourText);
					assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
							+ "is : "+actualDischargeHour+" not "+dschrghr);
				}else {
					report(LogStatus.INFO, "Discharge Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Hour is empty or null.");
			}
			
			//Discharge Status
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				String actualDischargeStatus = getText(dischargeStatusText);
				if(!actualDischargeStatus.equals(dschrgsts)) {
					click(dischargeStatus, "Visit Type");
					String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
					WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
					waitUntilClickable(dischargeStatus, 10);
					click(dischargeStatus, dschrgsts);
					actualDischargeStatus = getText(dischargeStatusText);
					assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
							+ "is : "+actualDischargeStatus+" not "+dschrgsts);
				}else {
					report(LogStatus.INFO, "Discharge Status is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Status is empty or null.");
			}
			
			//Form 18
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				if(!actualForm18.equals(actform_18)) {
					form18.clear();
					sendKeys(form18, "Form 18", form_18);
					actualForm18 = getAttribute(form18, "value");
					assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
							actualForm18+" not : "+actform_18);
				}else {
					report(LogStatus.INFO, "Form 18 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 18 is empty or null.");
			}
			
			//Form 19
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				if(!actualForm19.equals(actform_19)) {
					form19.clear();
					sendKeys(form19, "Form 19", form_19);
					actualForm19 = getAttribute(form19, "value");
					assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
							actualForm19+" not : "+actform_19);
				}else {
					report(LogStatus.INFO, "Form 19 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 19 is empty or null.");
			}
			
			//Form 20
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				if(!actualForm20.equals(actform_20)) {
					form20.clear();
					sendKeys(form20, "Form 20", form_20);
					actualForm20 = getAttribute(form20, "value");
					assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
							actualForm20+" not : "+actform_20);
				}else {
					report(LogStatus.INFO, "Form 20 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 20 is empty or null.");
			}
			
			//Form 21
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				if(!actualForm21.equals(actform_21)) {
					form21.clear();
					sendKeys(form21, "Form 21", form_21);
					actualForm21 = getAttribute(form21, "value");
					assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
							actualForm21+" not : "+actform_21);
				}else {
					report(LogStatus.INFO, "Form 21 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 21 is empty or null.");
			}
			
			//Form 22
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				if(!actualForm22.equals(actform_22)) {
					form22.clear();
					sendKeys(form22, "Form 22", form_22);
					actualForm22 = getAttribute(form22, "value");
					assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
							actualForm22+" not : "+actform_22);
				}else {
					report(LogStatus.INFO, "Form 22 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 22 is empty or null.");
			}
			
			//Form 23
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				if(!actualForm23.equals(actform_23)) {
					form23.clear();
					sendKeys(form23, "Form 23", form_23);
					actualForm23 = getAttribute(form23, "value");
					assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
							actualForm23+" not : "+actform_23);
				}else {
					report(LogStatus.INFO, "Form 23 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 23 is empty or null.");
			}
			
			//Form 24
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				if(!actualForm24.equals(actform_24)) {
					form24.clear();
					sendKeys(form24, "Form 24", form_24);
					actualForm24 = getAttribute(form24, "value");
					assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
							actualForm24+" not : "+actform_24);
				}else {
					report(LogStatus.INFO, "Form 24 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 24 is empty or null.");
			}
			
			//Form 25
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				if(!actualForm25.equals(actform_25)) {
					form25.clear();
					sendKeys(form25, "Form 25", form_25);
					actualForm25 = getAttribute(form25, "value");
					assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
							actualForm25+" not : "+actform_25);
				}else {
					report(LogStatus.INFO, "Form 25 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 25 is empty or null.");
			}
			
			//Form 26
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				if(!actualForm26.equals(actform_26)) {
					form26.clear();
					sendKeys(form26, "Form 26", form_26);
					actualForm26 = getAttribute(form26, "value");
					assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
							actualForm26+" not : "+actform_26);
				}else {
					report(LogStatus.INFO, "Form 26 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 26 is empty or null.");
			}
			
			//Form 27
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				if(!actualForm27.equals(actform_27)) {
					form27.clear();
					sendKeys(form27, "Form 27", form_27);
					actualForm27 = getAttribute(form27, "value");
					assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
							actualForm27+" not : "+actform_27);
				}else {
					report(LogStatus.INFO, "Form 27 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 27 is empty or null.");
			}
			
			//Form 28
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				if(!actualForm28.equals(actform_28)) {
					form28.clear();
					sendKeys(form28, "Form 28", form_28);
					actualForm28 = getAttribute(form28, "value");
					assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
							actualForm28+" not : "+actform_28);
				}else {
					report(LogStatus.INFO, "Form 28 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 28 is empty or null.");
			}
			
			//Accident State
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				if(!actualAccidentState.equals(accdtstate)) {
					accidentState.clear();
					sendKeys(accidentState, "Accident State", accdtstate);
					actualAccidentState = getAttribute(accidentState, "value");
					assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
							actualAccidentState+" not : "+actaccdtstate);
				}else {
					report(LogStatus.INFO, "Accident State is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Accident State is empty or null.");
			}
			
			//Form 31 A
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				if(!actualform31aoccurancecode.equals(actform31occcd_a) && 
						!actualform31adate.equals(form31occdt_a)) {
					form31OccuranceCodeA.clear();
					sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
					actualform31aoccurancecode = 
							getAttribute(form31OccuranceCodeA, "value");
					assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31aoccurancecode+
							" not : "+actform31occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
					actualform31adate = 
							getAttribute(form31OccuranceDateA, "value");
					assertEquals(actualform31adate, form31occdt_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31adate+
							" not : "+form31occdt_a);
				}else {
					report(LogStatus.INFO, "Form 31 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 A code or date is empty or null.");
			}
			
			//Form 31 B
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				if(!actualform31boccurancecode.equals(actform31occcd_b) && 
						!actualform31bdate.equals(form31occdt_b)) {
					form31OccuranceCodeB.clear();
					sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
					actualform31boccurancecode = 
							getAttribute(form31OccuranceCodeB, "value");
					assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31boccurancecode+
							" not : "+actform31occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
					actualform31bdate = 
							getAttribute(form31OccuranceDateB, "value");
					assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31bdate+
							" not : "+form31occdt_b);
				}else {
					report(LogStatus.INFO, "Form 31 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 B code or date is empty or null.");
			}
			
			//Form 32 A
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				if(!actualform32aoccurancecode.equals(actform32occcd_a) && 
						!actualform32adate.equals(form32occdt_a)) {
					form32OccuranceCodeA.clear();
					sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
					actualform32aoccurancecode = 
							getAttribute(form32OccuranceCodeA, "value");
					assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32aoccurancecode+
							" not : "+actform32occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
					actualform32adate = 
							getAttribute(form32OccuranceDateA, "value");
					assertEquals(actualform32adate, form32occdt_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32adate+
							" not : "+form32occdt_a);
				}else {
					report(LogStatus.INFO, "Form 32 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 A code or date is empty or null.");
			}
			
			//Form 32 B
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				if(!actualform32boccurancecode.equals(actform32occcd_b) && 
						!actualform32bdate.equals(form32occdt_b)) {
					form32OccuranceCodeB.clear();
					sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
					actualform32boccurancecode = 
							getAttribute(form32OccuranceCodeB, "value");
					assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32boccurancecode+
							" not : "+actform32occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
					actualform32bdate = 
							getAttribute(form32OccuranceDateB, "value");
					assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32bdate+
							" not : "+form32occdt_b);
				}else {
					report(LogStatus.INFO, "Form 32 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 B code or date is empty or null.");
			}
			
			//Form 33 A
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form33occcd_a;
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				if(!actualform33aoccurancecode.equals(actform33occcd_a) && 
						!actualform33adate.equals(form33occdt_a)) {
					form33OccuranceCodeA.clear();
					sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
					actualform33aoccurancecode = 
							getAttribute(form33OccuranceCodeA, "value");
					assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33aoccurancecode+
							" not : "+actform33occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
					actualform33adate = 
							getAttribute(form33OccuranceDateA, "value");
					assertEquals(actualform33adate, form33occdt_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33adate+
							" not : "+form33occdt_a);
				}else {
					report(LogStatus.INFO, "Form 33 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 A code or date is empty or null.");
			}
			
			//Form 33 B
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				if(!actualform33boccurancecode.equals(actform33occcd_b) && 
						!actualform33bdate.equals(form33occdt_b)) {
					form33OccuranceCodeB.clear();
					sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
					actualform33boccurancecode = 
							getAttribute(form33OccuranceCodeB, "value");
					assertEquals(actualform33boccurancecode, actform33occcd_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33boccurancecode+
							" not : "+actform33occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
					actualform33bdate = 
							getAttribute(form33OccuranceDateB, "value");
					assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33bdate+
							" not : "+form33occdt_b);
				}else {
					report(LogStatus.INFO, "Form 33 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 B code or date is empty or null.");
			}
			
			//Form 34 A
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				if(!actualform34aoccurancecode.equals(actform34occcd_a) && 
						!actualform34adate.equals(form34occdt_a)) {
					form34OccuranceCodeA.clear();
					sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
					actualform34aoccurancecode = 
							getAttribute(form34OccuranceCodeA, "value");
					assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34aoccurancecode+
							" not : "+actform34occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
					actualform34adate = 
							getAttribute(form34OccuranceDateA, "value");
					assertEquals(actualform34adate, form34occdt_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34adate+
							" not : "+form34occdt_a);
				}else {
					report(LogStatus.INFO, "Form 34 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 A code or date is empty or null.");
			}
			
			//Form 34 B
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				if(!actualform34boccurancecode.equals(actform34occcd_b) && 
						!actualform34bdate.equals(form34occdt_b)) {
					form34OccuranceCodeB.clear();
					sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
					actualform34boccurancecode = 
							getAttribute(form34OccuranceCodeB, "value");
					assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34boccurancecode+
							" not : "+actform34occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
					actualform34bdate = 
							getAttribute(form34OccuranceDateB, "value");
					assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34bdate+
							" not : "+form34occdt_b);
				}else {
					report(LogStatus.INFO, "Form 34 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 B code or date is empty or null.");
			}
			
			//Form 35 A
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				String actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				if(!actualform35aoccurancecode.equals(actform35occcd_a) && 
						!actualform35afromdate.equals(form35occfrdt_a) && 
						!actualform35atodate.equals(form35occtodt_a)) {
					form35OccuranceSpanCodeA.clear();
					sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
					actualform35aoccurancecode = 
							getAttribute(form35OccuranceSpanCodeA, "value");
					assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35aoccurancecode+
							" not : "+actform35occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
					actualform35afromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35afromdate+
							" not : "+form35occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
							form35occtodt_a);
					actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35atodate+
							" not : "+form35occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 35 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 A code or date is empty or null.");
			}
			
			//Form 35 B
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				if(!actualform35boccurancecode.equals(actform35occcd_b) && 
						!actualform35bfromdate.equals(form35occfrdt_b) && 
						!actualform35btodate.equals(form35occtodt_b)) {
					form35OccuranceSpanCodeB.clear();
					sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
					actualform35boccurancecode = 
							getAttribute(form35OccuranceSpanCodeB, "value");
					assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35boccurancecode+
							" not : "+actform35occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
					actualform35bfromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35bfromdate+
							" not : "+form35occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
							form35occtodt_b);
					actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35btodate+
							" not : "+form35occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 35 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 B code or date is empty or null.");
			}
			
			//Form 36 A
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				String actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				if(!actualform36aoccurancecode.equals(actform36occcd_a) && 
						!actualform36afromdate.equals(form36occfrdt_a) && 
						!actualform36atodate.equals(form36occtodt_a)) {
					form36OccuranceSpanCodeA.clear();
					sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
					actualform36aoccurancecode = 
							getAttribute(form36OccuranceSpanCodeA, "value");
					assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36aoccurancecode+
							" not : "+actform36occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
					actualform36afromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36afromdate+
							" not : "+form36occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
							form36occtodt_a);
					actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36atodate+
							" not : "+form36occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 36 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 A code or date is empty or null.");
			}
			
			//Form 36 B
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				if(!actualform36boccurancecode.equals(actform36occcd_b) && 
						!actualform36bfromdate.equals(form36occfrdt_b) && 
						!actualform36btodate.equals(form36occtodt_b)) {
					form36OccuranceSpanCodeB.clear();
					sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
					actualform36boccurancecode = 
							getAttribute(form36OccuranceSpanCodeB, "value");
					assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36boccurancecode+
							" not : "+actform36occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
					actualform36bfromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36bfromdate+
							" not : "+form36occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
							form36occtodt_b);
					actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36btodate+
							" not : "+form36occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 36 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 B code or date is empty or null.");
			}
			
			//Form 39 A
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				if(!actualform39avalcd.equals(actform39avalcd) && 
						!actualform39avalcdamt.equals(form39valcdamt_a)) {
							form39ValueCodeA.clear();
							sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
							actualform39avalcd = 
									getAttribute(form39ValueCodeA, "value");
							assertEquals(actualform39avalcd, actform39avalcd, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcd+
									" not : "+actform39avalcd);
							
							form39ValueCodeAmountA.clear();
							sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
							actualform39avalcdamt = 
									getAttribute(form39ValueCodeAmountA, "value");
							assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcdamt+
									" not : "+form39valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 39 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 A Code or Amount is empty or null");
			}
			
			//Form 39 B
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				if(!actualform39bvalcd.equals(actform39bvalcd) && 
						!actualform39bvalcdamt.equals(form39valcdamt_b)) {
							form39ValueCodeB.clear();
							sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
							actualform39bvalcd = 
									getAttribute(form39ValueCodeB, "value");
							assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcd+
									" not : "+actform39bvalcd);
							
							form39ValueCodeAmountB.clear();
							sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
							actualform39bvalcdamt = 
									getAttribute(form39ValueCodeAmountB, "value");
							assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcdamt+
									" not : "+form39valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 39 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 B Code or Amount is empty or null");
			}
			
			//Form 39 C
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_a;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				if(!actualform39cvalcd.equals(actform39cvalcd) && 
						!actualform39cvalcdamt.equals(form39valcdamt_c)) {
							form39ValueCodeC.clear();
							sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
							actualform39cvalcd = 
									getAttribute(form39ValueCodeC, "value");
							assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcd+
									" not : "+actform39cvalcd);
							
							form39ValueCodeAmountC.clear();
							sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
							actualform39cvalcdamt = 
									getAttribute(form39ValueCodeAmountC, "value");
							assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcdamt+
									" not : "+form39valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 39 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 C Code or Amount is empty or null");
			}
			
			//Form 39 D
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				if(!actualform39dvalcd.equals(actform39dvalcd) && 
						!actualform39dvalcdamt.equals(form39valcdamt_d)) {
							form39ValueCodeD.clear();
							sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
							actualform39dvalcd = 
									getAttribute(form39ValueCodeD, "value");
							assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcd+
									" not : "+actform39dvalcd);
							
							form39ValueCodeAmountD.clear();
							sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
							actualform39dvalcdamt = 
									getAttribute(form39ValueCodeAmountD, "value");
							assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcdamt+
									" not : "+form39valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 39 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 D Code or Amount is empty or null");
			}
			
			//Form 40 A
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				if(!actualform40avalcd.equals(actform40avalcd) && 
						!actualform40avalcdamt.equals(form40valcdamt_a)) {
							form40ValueCodeA.clear();
							sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
							actualform40avalcd = 
									getAttribute(form40ValueCodeA, "value");
							assertEquals(actualform40avalcd, actform40avalcd, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcd+
									" not : "+actform40avalcd);
							
							form40ValueCodeAmountA.clear();
							sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
							actualform40avalcdamt = 
									getAttribute(form40ValueCodeAmountA, "value");
							assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcdamt+
									" not : "+form40valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 40 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 A Code or Amount is empty or null");
			}
			
			//Form 40 B
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				if(!actualform40bvalcd.equals(actform40bvalcd) && 
						!actualform40bvalcdamt.equals(form40valcdamt_b)) {
							form40ValueCodeB.clear();
							sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
							actualform40bvalcd = 
									getAttribute(form40ValueCodeB, "value");
							assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcd+
									" not : "+actform40bvalcd);
							
							form40ValueCodeAmountB.clear();
							sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
							actualform40bvalcdamt = 
									getAttribute(form40ValueCodeAmountB, "value");
							assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcdamt+
									" not : "+form40valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 40 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 B Code or Amount is empty or null");
			}
			
			//Form 40 C
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_a;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				if(!actualform40cvalcd.equals(actform40cvalcd) && 
						!actualform40cvalcdamt.equals(form40valcdamt_c)) {
							form40ValueCodeC.clear();
							sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
							actualform40cvalcd = 
									getAttribute(form40ValueCodeC, "value");
							assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcd+
									" not : "+actform40cvalcd);
							
							form40ValueCodeAmountC.clear();
							sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
							actualform40cvalcdamt = 
									getAttribute(form40ValueCodeAmountC, "value");
							assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcdamt+
									" not : "+form40valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 40 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 C Code or Amount is empty or null");
			}
			
			//Form 40 D
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				if(!actualform40dvalcd.equals(actform40dvalcd) && 
						!actualform40dvalcdamt.equals(form40valcdamt_d)) {
							form40ValueCodeD.clear();
							sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
							actualform40dvalcd = 
									getAttribute(form40ValueCodeD, "value");
							assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcd+
									" not : "+actform40dvalcd);
							
							form40ValueCodeAmountD.clear();
							sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
							actualform40dvalcdamt = 
									getAttribute(form40ValueCodeAmountD, "value");
							assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcdamt+
									" not : "+form40valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 40 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 D Code or Amount is empty or null");
			}
			
			//Form 41 A
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				if(!actualform41avalcd.equals(actform41avalcd) && 
						!actualform41avalcdamt.equals(form41valcdamt_a)) {
							form41ValueCodeA.clear();
							sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
							actualform41avalcd = 
									getAttribute(form41ValueCodeA, "value");
							assertEquals(actualform41avalcd, actform41avalcd, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcd+
									" not : "+actform41avalcd);
							
							form41ValueCodeAmountA.clear();
							sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
							actualform41avalcdamt = 
									getAttribute(form41ValueCodeAmountA, "value");
							assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcdamt+
									" not : "+form41valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 41 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 A Code or Amount is empty or null");
			}
			
			//Form 41 B
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				if(!actualform41bvalcd.equals(actform41bvalcd) && 
						!actualform41bvalcdamt.equals(form41valcdamt_b)) {
							form41ValueCodeB.clear();
							sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
							actualform41bvalcd = 
									getAttribute(form41ValueCodeB, "value");
							assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcd+
									" not : "+actform41bvalcd);
							
							form41ValueCodeAmountB.clear();
							sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
							actualform41bvalcdamt = 
									getAttribute(form41ValueCodeAmountB, "value");
							assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcdamt+
									" not : "+form41valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 41 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 B Code or Amount is empty or null");
			}
			
			//Form 41 C
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_a;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				if(!actualform41cvalcd.equals(actform41cvalcd) && 
						!actualform41cvalcdamt.equals(form41valcdamt_c)) {
							form41ValueCodeC.clear();
							sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
							actualform41cvalcd = 
									getAttribute(form41ValueCodeC, "value");
							assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcd+
									" not : "+actform41cvalcd);
							
							form41ValueCodeAmountC.clear();
							sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
							actualform41cvalcdamt = 
									getAttribute(form41ValueCodeAmountC, "value");
							assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcdamt+
									" not : "+form41valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 41 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 C Code or Amount is empty or null");
			}
			
			//Form 41 D
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && !form41valcdamt_d.equals("")) {
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				if(!actualform41dvalcd.equals(actform41dvalcd) && 
						!actualform41dvalcdamt.equals(form41valcdamt_d)) {
							form41ValueCodeD.clear();
							sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
							actualform41dvalcd = 
									getAttribute(form41ValueCodeD, "value");
							assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcd+
									" not : "+actform41dvalcd);
							
							form41ValueCodeAmountD.clear();
							sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
							actualform41dvalcdamt = 
									getAttribute(form41ValueCodeAmountD, "value");
							assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcdamt+
									" not : "+form41valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 41 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 D Code or Amount is empty or null");
			}
			
			if(Objects.nonNull(serviceLineNumber) && !serviceLineNumber.equals("")) {
				String numberOfLines = serviceLine.replace("[XX]", "");
				int lineCount= driver.findElements(By.xpath(numberOfLines)).size();
				if(Integer.parseInt(serviceLineNumber) <= lineCount) {
					String lineNumber = serviceLine.replace("XX", serviceLineNumber);
					WebElement serviceLine = driver.findElement(By.xpath(lineNumber));
					serviceLine.click();
					if(modifyService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service modified successfully.");
					}else {
						report(LogStatus.FAIL,"Service not modified.");
					}
				}else {
					report(LogStatus.INFO,"The Service line numner is not valid");
				}
				
			}else {
				report(LogStatus.WARNING, "Service Line Number is empty or null");
			}
			
			//Previous Payer
			
			//Health Plan ID A
			if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
				String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
				if(!actualHealthPlanIDA.equals(healthplanid_a)) {
					healthPlanIDA.clear();
					sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
					actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
					assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
							+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);
				}else {
					report(LogStatus.INFO,"Health Plan ID A numner is not valid");
				}						
			}else {
				report(LogStatus.WARNING, "Health Plan ID A is empty or null");
			}

			
			//REL INFO A
			if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
				String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
				String[] relInfoAData = relInfoAClass.split(" ");
				String actualRelInfoA = "";
				for(String s : relInfoAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualRelInfoA = s;
					}
				}
				if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box checked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not checked");
					}
					
				}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
						relinfo_a.equals("NO")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(!actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box unchecked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
					}
					
				}
			}
			
			//ASN BEN A
			if(Objects.nonNull(benfitassignment_a) && 
					!benfitassignment_a.equals("")) {
				String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
				String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
				String actualBenfitAssignmentA = "";
				for(String s : benfitAssignmentAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualBenfitAssignmentA = s;
					}
				}
				if(actualBenfitAssignmentA.equals("") && 
						benfitassignment_a.equals("YES")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box checked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not checked");
					}
					
				}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
						benfitassignment_a.equals("NO")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box unchecked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
					}
					
				}
			}
			
			//Prior payment amount
			if(Objects.nonNull(priorpaymentamt_a) && 
					!priorpaymentamt_a.equals("")) {
				priorPaymentAmountA.clear();
				sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
				String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
				assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
						+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
						" not : "+priorpaymentamt_a);
			}
			
			//EST Due Amount
			if(Objects.nonNull(estamountdue_a) && 
					!estamountdue_a.equals("")) {
				estAmountDueA.clear();
				sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
				String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
				assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
						+"from prior payment amount field A is : "+actualESTAmountDueA+
						" not : "+estamountdue_a);
			}
			
			//primary payer
			if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
				click(payerTypeDrodownA, "Payer Type");
				switch(payertype_a) {
				case "MEDICARE":{
					click(payerTypeMedicareOption, "MEDICARE");
					break;
				}
				case "NON MEDICARE":{
					click(payerTypeNonMedicareOption, "Non - MEDICARE");
					break;
				}
				default:
					report(LogStatus.WARNING, "Payer Type is not valid");
					
				}
			}
			
			//Additional Previous payer
			if(Objects.nonNull(noOfPreviousPayer) && 
					!noOfPreviousPayer.equals("")) {
				if(Integer.parseInt(noOfPreviousPayer) > 1) {
					for(int i = 1; i<=2; i++) {
						if(addPreviousPayerButton.isEnabled())
							click(addPreviousPayerButton, "Add previouss Payer");
					}
					if(Integer.parseInt(noOfPreviousPayer) == 2) {
						driver.findElement(By.xpath("(//span[contains"
								+ "(text(),'Remove')]/parent::button)[2]")).click();
						waitUntilClickable(unsavedChagesOK, 10);
						click(unsavedChagesOK, "OK");
						modifyPrimaryPayeB(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b);
					}else if(Integer.parseInt(noOfPreviousPayer) == 3) {
						modifyPrimaryPayeBAndC(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b, payer_c, healthplanid_c, relinfo_c, 
								benfitassignment_c, priorpaymentamt_c, estamountdue_c, 
								payertype_c);
						
					}
				}else {
					for(int i = 1 ; i <= 2 ; i++) {
						try {
							driver.findElement(By.xpath("(//span[contains"
									+ "(text(),'Remove')]/parent::button)[2]")).click();
							waitUntilClickable(unsavedChagesOK, 10);
							click(unsavedChagesOK, "OK");
						}catch (NoSuchElementException e) {
							break;
						}
					}
				}
			}else {
				report(LogStatus.INFO,"No Of previous payer is empty or null");
			}
			
			//Billing Provider NPI
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				report(LogStatus.WARNING, "Billing Provider NPI is empty or null");
			}
			
			//Other Provider ID
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				if(!actualOtherProviderID.equals(othrprvid)) {
					otherProviderID.clear();
					sendKeys(otherProviderID, "Other Provider ID", othrprvid);
					actualOtherProviderID = getAttribute(otherProviderID, "value");
					assertEquals(actualOtherProviderID, othrprvid, "The value from other"
							+ " Provider ID field is : "+actualOtherProviderID+" not "
									+ othrprvid);
				}else {
					report(LogStatus.INFO,"Other Provider ID is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Other Provider ID is empty or null");
			}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				if(!actualInsuredNameA.equals(insrdname_a)) {
					insuredNameA.clear();
					sendKeys(insuredNameA, "Insured Name A", insrdname_a);
					actualInsuredNameA = getAttribute(insuredNameA, "value");
					assertEquals(actualInsuredNameA, insrdname_a, "The value from "
							+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
				}else {
					report(LogStatus.INFO, "Insured Name A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name A is empty or null");
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				if(!actualInsuredNameB.equals(insrdname_b)) {
					insuredNameB.clear();
					sendKeys(insuredNameB, "Insured Name B", insrdname_b);
					actualInsuredNameB = getAttribute(insuredNameB, "value");
					assertEquals(actualInsuredNameB, insrdname_b, "The value from "
							+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
				}else {
					report(LogStatus.INFO, "Insured Name B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name B is empty or null");
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				if(!actualInsuredNameC.equals(insrdname_c)) {
					insuredNameC.clear();
					sendKeys(insuredNameC, "Insured Name C", insrdname_c);
					actualInsuredNameC = getAttribute(insuredNameC, "value");
					assertEquals(actualInsuredNameC, insrdname_c, "The value from "
							+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
				}else {
					report(LogStatus.INFO, "Insured Name C is same and not changed");
				}
			
			}else {
				report(LogStatus.WARNING, "Insured Name C is empty or null");
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				if(!actualPatRelInsuredA.equals(patreltoinsure_a)) {
					patientRelatedToInsuranceA.clear();
					sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
							patreltoinsure_a);
					actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
							"value");
					if(patreltoinsure_a.length()>2)
						assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
							+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
							patreltoinsure_a);
					else
						assertEquals(actualPatRelInsuredA, patreltoinsure_a, "The value from "
								+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
								patreltoinsure_a);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure A is empty or null");
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				if(!actualPatRelInsuredB.equals(patreltoinsure_b)) {
					patientRelatedToInsuranceB.clear();
					sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
							patreltoinsure_b);
					actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
							"value");
					if(patreltoinsure_b.length()>2)
						assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
							patreltoinsure_b);
					else
						assertEquals(actualPatRelInsuredB, patreltoinsure_b, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
								patreltoinsure_b);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure B is empty or null");
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				if(!actualPatRelInsuredC.equals(patreltoinsure_c)) {
					patientRelatedToInsuranceC.clear();
					sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
							patreltoinsure_c);
					actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
							"value");
					if(patreltoinsure_c.length()>2)
						assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
							patreltoinsure_c);
					else
						assertEquals(actualPatRelInsuredC, patreltoinsure_c, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
								patreltoinsure_c);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure C is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure C is empty or null");
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				if(!actualInsuredUniqueIDA.equals(insuredunqid_a)) {
					insuredsUniqueIDA.clear();
					sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
							insuredunqid_a);
					actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
							"value");
					assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
							+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
							insuredunqid_a);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID A is empty or null.");
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				if(!actualInsuredUniqueIDB.equals(insuredunqid_b)) {
					insuredsUniqueIDB.clear();
					sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
							insuredunqid_b);
					actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
							"value");
					assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
							insuredunqid_b);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID B is empty or null.");
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				if(!actualInsuredUniqueIDC.equals(insuredunqid_c)) {
					insuredsUniqueIDC.clear();
					sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
							insuredunqid_c);
					actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
							"value");
					assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
							insuredunqid_c);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID C is empty or null.");
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				if(!actualInsuredGroupNameA.equals(insrdgrpnm_a)) {
					insuredGroupNameA.clear();
					sendKeys(insuredGroupNameA, "Insured Unique ID A", 
							insrdgrpnm_a);
					actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
							"value");
					assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
							+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
							insrdgrpnm_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Name A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name A is empty or null.");
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				if(!actualInsuredGroupNameB.equals(insrdgrpnm_b)) {
					insuredGroupNameB.clear();
					sendKeys(insuredGroupNameB, "Insured Unique ID B", 
							insrdgrpnm_b);
					actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
							"value");
					assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
							+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
							insrdgrpnm_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Name B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name B is empty or null.");
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				if(!actualInsuredGroupNameC.equals(insrdgrpnm_c)) {
					insuredGroupNameC.clear();
					sendKeys(insuredGroupNameC, "Insured Unique ID C", 
							insrdgrpnm_c);
					actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
							"value");
					assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
							+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
							insrdgrpnm_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Name C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name C is empty or null.");
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				if(!actualInsuredGroupNumberA.equals(insrdgrpno_a)) {
					insuredGroupNumberA.clear();
					sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
							insrdgrpno_a);
					actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
							"value");
					assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
							+ "Insured Number A is : "+actualInsuredGroupNumberA+" not "+
							insrdgrpno_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number A is empty or null.");
			}
			
			//Insured Group Number B
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				if(!actualInsuredGroupNumberB.equals(insrdgrpno_a)) {
					insuredGroupNumberB.clear();
					sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
							insrdgrpno_b);
					actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
							"value");
					assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
							+ "Insured Number B is : "+actualInsuredGroupNumberB+" not "+
							insrdgrpno_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number B is empty or null.");
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				if(!actualInsuredGroupNumberC.equals(insrdgrpno_c)) {
					insuredGroupNumberC.clear();
					sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
							insrdgrpno_c);
					actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
							"value");
					assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
							+ "Insured Number C is : "+actualInsuredGroupNumberC+" not "+
							insrdgrpno_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number C is empty or null.");
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				if(!actualTreatmentAuthCodeA.equals(txauthcd_a)) {
					treatmentAuthCodesA.clear();
					sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
					actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
							"value");
					assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code A is empty or null.");
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				if(!actualTreatmentAuthCodeB.equals(txauthcd_b)) {
					treatmentAuthCodesB.clear();
					sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
					actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
							"value");
					assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code B is empty or null.");
			}
			
			//Treatment Authorization code C
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				if(!actualTreatmentAuthCodeC.equals(txauthcd_c)) {
					treatmentAuthCodesC.clear();
					sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
					actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
							"value");
					assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code C is empty or null.");
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				if(!actualResubmissionClaimA.equals(refclm_a)) {
					resubmissionClaimNumberA.clear();
					sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
					actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
							"value");
					assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
				}else {
					report(LogStatus.INFO, "The Document Control Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number A is empty or null.");
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				if(!actualResubmissionClaimB.equals(refclm_a)) {
					resubmissionClaimNumberB.clear();
					sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
					actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
							"value");
					assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
				}else {
					report(LogStatus.INFO, "The Document Control Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number B is empty or null.");
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				if(!actualResubmissionClaimC.equals(refclm_c)) {
					resubmissionClaimNumberC.clear();
					sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
					actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
							"value");
					assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
				}else {
					report(LogStatus.INFO, "The Document Control Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number C is empty or null.");
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				if(!actualEmployerNameA.equals(empnm_a)) {
					employerNameA.clear();
					sendKeys(employerNameA, "Employer Name A", empnm_a);
					actualEmployerNameA = getAttribute(employerNameA, 
							"value");
					assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
							+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
				}else {
					report(LogStatus.INFO, "The Employer A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer A is empty or null.");
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				if(!actualEmployerNameB.equals(empnm_b)) {
					employerNameB.clear();
					sendKeys(employerNameB, "Employer Name B", empnm_b);
					actualEmployerNameB = getAttribute(employerNameB, 
							"value");
					assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
							+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
				}else {
					report(LogStatus.INFO, "The Employer B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer B is empty or null.");
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				if(!actualEmployerNameC.equals(empnm_c)) {
					employerNameC.clear();
					sendKeys(employerNameC, "Employer Name C", empnm_c);
					actualEmployerNameC = getAttribute(employerNameC, 
							"value");
					assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
							+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
				}else {
					report(LogStatus.INFO, "The Employer C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer C is empty or null.");
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}else {
				report(LogStatus.WARNING, "Diagnosis Version is empty or null.");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				if(!actualPrincipalDiagnosis.equals(principaldiag)) {
					principalDIagnosis.clear();
					sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
					waitForLoadingToDisappear();
					actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
							"value");
					assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
							+ "code from field is : "+actualPrincipalDiagnosis + " not : "
							+principaldiag);
				}else {
					report(LogStatus.INFO, "The Principal Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The principal diagnosis is empty or null");
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				if(!actualOtherDiagnosisA.equals(othrdiag_a)) {
					otherDiagnosisA.clear();
					sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
					waitForLoadingToDisappear();
					actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
							"value");
					assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisA + " not : "
							+othrdiag_a);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis A is empty or null");
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_b) && !othrdiag_b.equals("")) {
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				if(!actualOtherDiagnosisB.equals(othrdiag_b)) {
					otherDiagnosisB.clear();
					sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
					waitForLoadingToDisappear();
					actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisB + " not : "
							+othrdiag_b);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis B is empty or null");
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				if(!actualOtherDiagnosisC.equals(othrdiag_c)) {
					otherDiagnosisC.clear();
					sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
					waitForLoadingToDisappear();
					actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
							"value");
					assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisC + " not : "
							+othrdiag_c);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis C is empty or null");
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				if(!actualOtherDiagnosisD.equals(othrdiag_d)) {
					otherDiagnosisD.clear();
					sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
					waitForLoadingToDisappear();
					actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
							"value");
					assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisD + " not : "
							+othrdiag_d);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis D is empty or null");
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				if(!actualOtherDiagnosisE.equals(othrdiag_e)) {
					otherDiagnosisE.clear();
					sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
					waitForLoadingToDisappear();
					actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
							"value");
					assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisE + " not : "
							+othrdiag_e);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis E is empty or null");
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				if(!actualOtherDiagnosisF.equals(othrdiag_f)) {
					otherDiagnosisF.clear();
					sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
					waitForLoadingToDisappear();
					actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
							"value");
					assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisF + " not : "
							+othrdiag_f);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis F is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis F is empty or null");
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				if(!actualOtherDiagnosisG.equals(othrdiag_g)) {
					otherDiagnosisG.clear();
					sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
					waitForLoadingToDisappear();
					actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
							"value");
					assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisG + " not : "
							+othrdiag_g);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis G is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis G is empty or null");
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				if(!actualOtherDiagnosisH.equals(othrdiag_h)) {
					otherDiagnosisH.clear();
					sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
					waitForLoadingToDisappear();
					actualOtherDiagnosisH = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisH + " not : "
							+othrdiag_h);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis H is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis H is empty or null");
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				if(!actualOtherDiagnosisI.equals(othrdiag_i)) {
					otherDiagnosisI.clear();
					sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
					waitForLoadingToDisappear();
					actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
							"value");
					assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisI + " not : "
							+othrdiag_i);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis I is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis I is empty or null");
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisJ,
						"value");
				if(!actualOtherDiagnosisJ.equals(othrdiag_j)) {
					otherDiagnosisJ.clear();
					sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
					waitForLoadingToDisappear();
					actualOtherDiagnosisJ = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisJ + " not : "
							+othrdiag_j);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis J is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis J is empty or null");
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				if(!actualOtherDiagnosisK.equals(othrdiag_k)) {
					otherDiagnosisK.clear();
					sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
					waitForLoadingToDisappear();
					actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
							"value");
					assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisK + " not : "
							+othrdiag_k);
				}
				else {
					report(LogStatus.INFO, "The Other Diagnosis K is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis K is empty or null");
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				if(!actualOtherDiagnosisL.equals(othrdiag_l)) {
					otherDiagnosisL.clear();
					sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
					waitForLoadingToDisappear();
					actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
							"value");
					assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisL + " not : "
							+othrdiag_l);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis L is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis L is empty or null");
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				if(!actualOtherDiagnosisM.equals(othrdiag_m)) {
					otherDiagnosisM.clear();
					sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
					waitForLoadingToDisappear();
					actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
							"value");
					assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisM + " not : "
							+othrdiag_m);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis M is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis M is empty or null");
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				if(!actualOtherDiagnosisN.equals(othrdiag_n)) {
					otherDiagnosisN.clear();
					sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
					waitForLoadingToDisappear();
					actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
							"value");
					assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisN + " not : "
							+othrdiag_n);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis N is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis N is empty or null");
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				if(!actualOtherDiagnosisO.equals(othrdiag_o)) {
					otherDiagnosisO.clear();
					sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
					waitForLoadingToDisappear();
					actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
							"value");
					assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisO + " not : "
							+othrdiag_o);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis O is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis O is empty or null");
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				if(!actualOtherDiagnosisP.equals(othrdiag_p)) {
					otherDiagnosisP.clear();
					sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
					waitForLoadingToDisappear();
					actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
							"value");
					assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisP + " not : "
							+othrdiag_p);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis P is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis P is empty or null");
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				if(!actualOtherDiagnosisQ.equals(othrdiag_q)) {
					otherDiagnosisQ.clear();
					sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
					waitForLoadingToDisappear();
					actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
							"value");
					assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisQ + " not : "
							+othrdiag_q);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis Q is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis Q is empty or null");
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				if(!actualAdmissionDiagnosis.equals(admsndiag)) {
					admissionDiagnosis.clear();
					sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
					waitForLoadingToDisappear();
					actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
							"value");
					assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
							+ "code from field is : "+actualAdmissionDiagnosis + " not : "
							+admsndiag);
				}else {
					report(LogStatus.INFO, "The Admission Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Admission Diagnosis is empty or null");
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				if(!actualPatientReasonDiagnosisA.equals(patrsndiag_a)) {
					patientReasonDiagnosisA.clear();
					sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
							patrsndiag_a);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
							"value");
					assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
							+patrsndiag_a);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis A is empty or null");
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				if(!actualPatientReasonDiagnosisB.equals(patrsndiag_b)) {
					patientReasonDiagnosisB.clear();
					sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
							patrsndiag_b);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
							"value");
					assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
							+patrsndiag_b);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis B is empty or null");
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				if(!actualPatientReasonDiagnosisC.equals(patrsndiag_b)) {
					patientReasonDiagnosisC.clear();
					sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
							patrsndiag_c);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
							"value");
					assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
							+patrsndiag_c);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis C is empty or null");
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				String actualPPSCode = getAttribute(ppsCode, "value");
				if(!actualPPSCode.equals(ppscd)) {
					ppsCode.clear();
					sendKeys(ppsCode, "PPS Code", ppscd);
					actualPPSCode = getAttribute(ppsCode, "value");
					assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
					+actualPPSCode+" not : "+ppscd);
				}else {
					report(LogStatus.INFO, "The PPS Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The PPS Code is empty or null");
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				if(!actualECIDiagnosisA.equals(ecidiagcd_a)) {
					eciDiagnosisCodeA.clear();
					sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
							ecidiagcd_a);
					waitForLoadingToDisappear();
					actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
							"value");
					assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisA + " not : "
							+ecidiagcd_a);
				}else {
					report(LogStatus.INFO, "The ECI A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI A is empty or null");
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				if(!actualECIDiagnosisB.equals(ecidiagcd_b)) {
					eciDiagnosisCodeB.clear();
					sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
							ecidiagcd_b);
					waitForLoadingToDisappear();
					actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
							"value");
					assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisB + " not : "
							+ecidiagcd_b);
				}else {
					report(LogStatus.INFO, "The ECI B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI B is empty or null");
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				if(!actualECIDiagnosisC.equals(ecidiagcd_c)) {
					eciDiagnosisCodeC.clear();
					sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
							ecidiagcd_c);
					waitForLoadingToDisappear();
					 actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
							"value");
					assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisC + " not : "
							+ecidiagcd_c);
				}else {
					report(LogStatus.INFO, "The ECI C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI C is empty or null");
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, 
						"value");
				if(!actualPrincipleProcCode.equals(principlepccd)) {
					principleProcedureCode.clear();
					sendKeys(principleProcedureCode, "Principle Proc Code", 
							principlepccd);
					actualPrincipleProcCode = getAttribute(principleProcedureCode, 
							"value");
					if(principlepccd.length()>7)
						assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
							+ "code from field is : "+actualPrincipleProcCode + " not : "
							+principlepccd);
					else
						assertEquals(actualPrincipleProcCode, principlepccd, "The Principle Proc "
								+ "code from field is : "+actualPrincipleProcCode + " not : "
								+principlepccd);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
						"value");
				if(!actualPrincipleProcCodeDate.equals(principlepcdt)) {
					for(int i = 0 ; i < 10 ; i++)
						principlePCDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
					actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
							"value");
					assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
							"The Principle Proc code from field is : "
							+actualPrincipleProcCodeDate + " not : "+principlepcdt);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				if(!actualOtherProcCodeA.equals(othrpccd_a)) {
					otherProcedureCodeA.clear();
					sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
					actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
							"value");
					if(othrpccd_a.length() > 7)
						assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeA + " not : "
							+othrpccd_a);
					else
						assertEquals(actualOtherProcCodeA, othrpccd_a, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeA + " not : "
								+othrpccd_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code A is same and not changed.");
				}	
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code A is empty or null");
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				if(!actualOtherProcCodeDateA.equals(othrpcdt_a)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
					actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
					assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date A is empty or null");
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				if(!actualOtherProcCodeB.equals(othrpccd_b)) {
					otherProcedureCodeB.clear();
					sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
					actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
							"value");
					if(othrpccd_b.length() > 7)
						assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeB + " not : "
							+othrpccd_b);
					else
						assertEquals(actualOtherProcCodeB, othrpccd_b, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeB + " not : "
								+othrpccd_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code B is empty or null");
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				if(!actualOtherProcCodeDateB.equals(othrpcdt_b)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
					actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
					assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date B is empty or null");
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				if(!actualOtherProcCodeC.equals(othrpccd_c)) {
					otherProcedureCodeC.clear();
					sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
					actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
							"value");
					if(othrpccd_c.length() > 7)
						assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeC + " not : "
							+othrpccd_c);
					else
						assertEquals(actualOtherProcCodeC, othrpccd_c, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeC + " not : "
								+othrpccd_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code C is empty or null");
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				if(!actualOtherProcCodeDateC.equals(othrpcdt_c)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateC.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
					actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
					assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date C is empty or null");
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				if(!actualOtherProcCodeD.equals(othrpccd_d)) {
					otherProcedureCodeD.clear();
					sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
					actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
							"value");
					if(othrpccd_d.length() > 7)
						assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeD + " not : "
							+othrpccd_d);
					else
						assertEquals(actualOtherProcCodeD, othrpccd_d, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeD + " not : "
								+othrpccd_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code D is empty or null");
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				if(!actualOtherProcCodeDateD.equals(othrpcdt_d)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateD.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
					actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
					assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date D is empty or null");
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				if(!actualOtherProcCodeE.equals(othrpccd_e)) {
					otherProcedureCodeE.clear();
					sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
					actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
							"value");
					if(othrpccd_e.length() > 7)
						assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeE + " not : "
							+othrpccd_e);
					else
						assertEquals(actualOtherProcCodeE, othrpccd_e, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeE + " not : "
								+othrpccd_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code E is empty or null");
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				if(!actualOtherProcCodeDateE.equals(othrpcdt_e)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateE.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
					actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
					assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date E is empty or null");
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				if(!actualAttendingNPI.equals(attndphynpi)) {
					attendingPhysicianNPI.clear();
					sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
					actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
					assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
					+actualAttendingNPI+" not : "+attndphynpi);
				}else {
					report(LogStatus.INFO, "The Attending Physician NPI is same and not changed.");
				}
			}else {
					report(LogStatus.WARNING,"The Attending Physician NPI is null or empty");
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				if(!actualAttendingPhysicianQual1.equals(attndphyqual1)) {
					attendingPhysicianQual1.clear();
					sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
							attndphyqual1);
					actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
							"value");
					assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
					+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier1 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier1 is null or empty");
		}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				if(!actualAttendingPhysicianQual2.equals(attndphyqual2)) {
					attendingPhysicianQual2.clear();
					sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
							attndphyqual2);
					actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
							"value");
					assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
					+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier2 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier2 is null or empty");
		}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				if(!actualAttendingPhysicianFirstName.equals(attndphyfn)) {
					attendingPhysicianFirstName.clear();
					sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
							attndphyfn);
					actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
							"value");
					assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
					+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
				}else {
					report(LogStatus.INFO, "The Attending Physician FirstName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician FirstName is null or empty");
		}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				if(!actualAttendingPhysicianLastName.equals(attndphyln)) {
					attendingPhysicianLastName.clear();
					sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
							attndphyln);
					actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
							"value");
					assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
					+actualAttendingPhysicianLastName+" not : "+attndphyln);
				}else {
					report(LogStatus.INFO, "The Attending Physician LastName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician LastName is null or empty");
		}
			
		//Operating Physician NPI
		if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
			String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
					"value");
			if(!actualOperatingNPI.equals(oprtphynpi)) {
				operatingPhysicianNPI.clear();
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}else {
				report(LogStatus.INFO, "The Operating Physician NPI is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician NPI is null or empty");
		}
			
		//Operating Physician Qualifier1
		if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
			String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
					"value");
			if(!actualOperatingPhysicianQual1.equals(oprtphyqual1)) {
				operatingPhysicianQual1.clear();
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier1 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier1 is null or empty");
		}
		
		//Operating Physician Qualifier2
		if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
			String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
					"value");
			if(!actualOperatingPhysicianQual2.equals(oprtphyqual2)) {
				operatingPhysicianQual2.clear();
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier2 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier2 is null or empty");
		}
		
		//Operating Physician FirstName
		if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
			String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
					"value");
			if(!actualOperatingPhysicianFirstName.equals(oprtphyfn)) {
				operatingPhysicianFirstName.clear();
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
						+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}else {
				report(LogStatus.INFO, "The Operating Physician FirstName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician FirstName is null or empty");
		}
		
		//Operating Physician LasttName
		if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
			String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
					"value");
			if(!actualOperatingPhysicianLastName.equals(oprtphyln)) {
				operatingPhysicianLastName.clear();
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}else {
				report(LogStatus.INFO, "The Operating Physician LastName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician LastName is null or empty");
		}
		
		//Other Physician NPI A
		if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
			String actualOtherNPIA = getAttribute(otherNPIA, "value");
			if(!actualOtherNPIA.equals(othrnpi_a)) {
				otherNPIA.clear();
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI Ais same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI A is null or empty");
		}
		
		//Other Physician Qualifier1 A
		if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
			String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
					"value");
			if(!actualOtherNPIQual1A.equals(othrnpiqual1_a)) {
				otherNPIQual1A.clear();
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 A is null or empty");
		}
		
		//Other Physician Qualifier2 A
		if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
			String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
					"value");
			if(!actualOtherNPIQual2A.equals(othrnpiqual2_a)) {
				otherNPIQual2A.clear();
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 A is null or empty");
		}
		
		//Other Physician First Name A
		if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
			String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
					"value");
			if(!actualOtherPhysicianFirstNameA.equals(othrnpifn_a)) {
				otherNPIFirstNameA.clear();
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName A is null or empty");
		}
		
		//Other Physician LastName A
		if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
			String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
					"value");
			if(!actualOtherPhysicianLastNameA.equals(othrnpiln_a)) {
				otherNPILastNameA.clear();
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName A is null or empty");
		}
		
		//Other Physician NPI B
		if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
			String actualOtherNPIB = getAttribute(otherNPIB, "value");
			if(!actualOtherNPIB.equals(othrnpi_b)) {
				otherNPIB.clear();
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI B is null or empty");
		}
		
		//Other Physician Qualifier1 B
		if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
			String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
					"value");
			if(!actualOtherNPIQual1B.equals(othrnpiqual1_b)) {
				otherNPIQual1B.clear();
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 B is null or empty");
		}
		
		//Other Physician Qualifier2 B
		if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
			String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
					"value");
			if(!actualOtherNPIQual2B.equals(othrnpiqual2_b)) {
				otherNPIQual2B.clear();
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 B is null or empty");
		}
		
		//Other Physician First Name B
		if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
			String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
					"value");
			if(!actualOtherPhysicianFirstNameB.equals(othrnpifn_b)) {
				otherNPIFirstNameB.clear();
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName B is null or empty");
		}
		
		//Other Physician LastName B
		if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
			String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
					"value");
			if(!actualOtherPhysicianLastNameB.equals(othrnpiln_b)) {
				otherNPILastNameB.clear();
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName B is null or empty");
		}
		
		//Remarks
		if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
			String actualRemarks = getAttribute(remarks, "value");
			if(!actualRemarks.equals(reMarks)) {
				remarks.clear();
				sendKeys(remarks, "Remarks", reMarks);
				actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}else {
				report(LogStatus.INFO, "The Remarks is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Remarks is null or empty");
		}
		
		//Taxonomy
		if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
			form81ATaxanomy.clear();
			sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
			String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
			waitForLoadingToDisappear();
			WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
			click(taxonomy, form81taxonomy_a);
		}else {
			report(LogStatus.WARNING,"The Taxonomy is null or empty");
		}
		
		//Form 81 A value
		if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
			String actualForm81AValue = getAttribute(form81AValue, "value");
			if(!actualForm81AValue.equals(form81value_a)) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}else {
				report(LogStatus.INFO,"The Form 81 A value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 A value is null or empty");
		}
		
		//Form 81 B Qualifier
		if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
			String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
			if(!actualForm81BQualifier.equals(form81qualifier_b)) {
				form81BQualifier.clear();
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Qualifier value is null or empty");
		}
		
		//Form 81 B Taxonomy
		if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
			String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
			if(!actualForm81BTaxonomy.equals(form81taxonomy_b)) {
				form81BTaxanomy.clear();
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Taxonomy value is null or empty");
		}
		
		//Form 81 B Value
		if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
			String actualForm81BValue = getAttribute(form81BValue, "value");
			if(!actualForm81BValue.equals(form81value_b)) {
				form81BValue.clear();
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Value is null or empty");
		}
		
		//Form 81 C Qualifier
		if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
			String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
			if(!actualForm81CQualifier.equals(form81qualifier_c)) {
				form81CQualifier.clear();
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Qualifier is null or empty");
		}
		
		//Form 81 C Taxonomy
		if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
			String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
			if(!actualForm81CTaxonomy.equals(form81taxonomy_c)) {
				form81CTaxanomy.clear();
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Taxonomy is null or empty");
		}
		
		//Form 81 C Value
		if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
			String actualForm81CValue = getAttribute(form81CValue, "value");
			if(!actualForm81CValue.equals(actualForm81CValue)) {
				form81CValue.clear();
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Value is null or empty");
		}
		
		//Form 81 D Qualifier
		if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
			String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
			if(!actualForm81DQualifier.equals(form81qualifier_d)) {
				form81DQualifier.clear();
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Qualifier is null or empty");
		}
		
		//Form 81 D Taxonomy
		if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
			String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
			if(!actualForm81DTaxonomy.equals(form81taxonomy_d)) {
				form81DTaxanomy.clear();
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}else {
				report(LogStatus.INFO,"The //Form 81 D Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Taxonomy is null or empty");
		}
		
		//Form 81 D Value
		if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
			String actualForm81DValue = getAttribute(form81DValue, "value");
			if(!actualForm81DValue.equals(form81value_d)) {
				form81DValue.clear();
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Value is null or empty");
		}
		
		//Received Date
		if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
			String actualReceivedDate = getAttribute(receivedDate, "value");
			if(!actualReceivedDate.equals(receveddate)) {
				for(int i = 0 ; i < 10 ; i++)
					receivedDate.sendKeys(Keys.BACK_SPACE);
				sendKeys(receivedDate, "Received Date", receveddate);
				actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}else {
				report(LogStatus.INFO,"The Received Date is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Received Date is null or empty");
		}
		
		click(saveButton, "Submit Claim");
		
		waitForLoadingToDisappear();
		
		String alertXpath = "//*[@role='alertdialog']";
		WebElement alertEle = driver.findElement(By.xpath(alertXpath));
		putStaticWait(2);
		waitUntilElementVisible(By.xpath(alertXpath), 20);
		String alerttext = getAttribute(alertEle,"aria-label");
		String alerttext1 = getAttribute(alertEle,"innerHTML");
		System.out.println(alerttext);
		System.out.println(alerttext1);
		alertEle.click();
		if(Objects.nonNull(alerttext)) {

			if(alerttext.contains("success") || alerttext.contains("Success") ) {
				report(LogStatus.PASS, "Successfully saved");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not saved successfully\n"+alerttext);
				try {
					throw new CannotCreateClaimException("Not able to save the "
							+ "claim due to error : \n"+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}else if(Objects.nonNull(alerttext1)) {
			if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
				report(LogStatus.PASS, "Successfully saved");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not savved successfully\n"+alerttext1);
				try {
					throw new CannotCreateClaimException("Not able to save the "
							+ "claim due to error : \n"+alerttext1);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}
		waitForLoadingToDisappear();	
		}else {
			report(LogStatus.FAIL, "Update UB-04 screen is not displayed.");
			try {
				throw new Exception("Update UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		waitForLoadingToDisappear();
		
		return myMCSNumber;
		
	}
	
	public String copyAndSaveUB04Claim() {
		
		boolean flag =  false;
		String myMCSNumber = dataMap.get("myMCSClaimNumber");
		
		String prvid = dataMap.get("providerID");
		String steid = dataMap.get("siteID");
		String pcn = dataMap.get("patientControlNumber");
		String billtype = dataMap.get("billType");
		String fromperiod = dataMap.get("statemanFromPeriod");
		String toperiod = dataMap.get("statementToPeriod");
		String patid = dataMap.get("patientID");
		String admsndt = dataMap.get("admissionDate");
		String admsnhr = dataMap.get("admissionHour");
		String reftype = dataMap.get("referenceType");
		String refsrc = dataMap.get("referenceSource");
		String dschrghr = dataMap.get("dischargeHour");
		String dschrgsts = dataMap.get("dischargeStatus");
		String form_18 = dataMap.get("form18");
		String form_19 = dataMap.get("form19");
		String form_20 = dataMap.get("form20");
		String form_21 = dataMap.get("form21");
		String form_22 = dataMap.get("form22");
		String form_23 = dataMap.get("form23");
		String form_24 = dataMap.get("form24");
		String form_25 = dataMap.get("form25");
		String form_26 = dataMap.get("form26");
		String form_27 = dataMap.get("form27");
		String form_28 = dataMap.get("form28");
		String accdtstate = dataMap.get("accidentState");
		String form31occcd_a = dataMap.get("form31OccuranceCodeA");
		String form31occdt_a = dataMap.get("form31OccuranceDateA");
		String form31occcd_b = dataMap.get("form31OccuranceCodeB");
		String form31occdt_b = dataMap.get("form31OccuranceDateB");
		String form32occcd_a = dataMap.get("form32OccuranceCodeA");
		String form32occdt_a = dataMap.get("form32OccuranceDateA");
		String form32occcd_b = dataMap.get("form32OccuranceCodeB");
		String form32occdt_b = dataMap.get("form32OccuranceDateB");
		String form33occcd_a = dataMap.get("form33OccuranceCodeA");
		String form33occdt_a = dataMap.get("form33OccuranceDateA");
		String form33occcd_b = dataMap.get("form33OccuranceCodeB");
		String form33occdt_b = dataMap.get("form33OccuranceDateB");
		String form34occcd_a = dataMap.get("form34OccuranceCodeA");
		String form34occdt_a = dataMap.get("form34OccuranceDateA");
		String form34occcd_b = dataMap.get("form34OccuranceCodeB");
		String form34occdt_b = dataMap.get("form34OccuranceDateB");
		String form35occcd_a = dataMap.get("form35OccuranceSpanCodeA");
		String form35occfrdt_a = dataMap.get("form35OccuranceSpanCodeFromDateA");
		String form35occtodt_a = dataMap.get("form35OccuranceSpanCodeThroughDateA");
		String form35occcd_b = dataMap.get("form35OccuranceSpanCodeB");
		String form35occfrdt_b = dataMap.get("form35OccuranceSpanCodeFromDateB");
		String form35occtodt_b = dataMap.get("form35OccuranceSpanCodeThroughDateB");
		String form36occcd_a = dataMap.get("form36OccuranceSpanCodeA");
		String form36occfrdt_a = dataMap.get("form36OccuranceSpanCodeFromDateA");
		String form36occtodt_a = dataMap.get("form36OccuranceSpanCodeThroughDateA");
		String form36occcd_b = dataMap.get("form36OccuranceSpanCodeB");
		String form36occfrdt_b = dataMap.get("form36OccuranceSpanCodeFromDateB");
		String form36occtodt_b = dataMap.get("form36OccuranceSpanCodeThroughDateB");
		String form39valcd_a = dataMap.get("form39ValueCodeA");
		String form39valcdamt_a = dataMap.get("form39ValueCodeAmountA");
		String form39valcd_b = dataMap.get("form39ValueCodeB");
		String form39valcdamt_b = dataMap.get("form39ValueCodeAmountB");
		String form39valcd_c = dataMap.get("form39ValueCodeC");
		String form39valcdamt_c = dataMap.get("form39ValueCodeAmountC");
		String form39valcd_d = dataMap.get("form39ValueCodeD");
		String form39valcdamt_d = dataMap.get("form39ValueCodeAmountD");
		String form40valcd_a = dataMap.get("form40ValueCodeA");
		String form40valcdamt_a = dataMap.get("form40ValueCodeAmountA");
		String form40valcd_b = dataMap.get("form40ValueCodeB");
		String form40valcdamt_b = dataMap.get("form40ValueCodeAmountB");
		String form40valcd_c = dataMap.get("form40ValueCodeC");
		String form40valcdamt_c = dataMap.get("form40ValueCodeAmountC");
		String form40valcd_d = dataMap.get("form40ValueCodeD");
		String form40valcdamt_d = dataMap.get("form40ValueCodeAmountD");
		String form41valcd_a = dataMap.get("form41ValueCodeA");
		String form41valcdamt_a = dataMap.get("form41ValueCodeAmountA");
		String form41valcd_b = dataMap.get("form41ValueCodeB");
		String form41valcdamt_b = dataMap.get("form41ValueCodeAmountB");
		String form41valcd_c = dataMap.get("form41ValueCodeC");
		String form41valcdamt_c = dataMap.get("form41ValueCodeAmountC");
		String form41valcd_d = dataMap.get("form41ValueCodeD");
		String form41valcdamt_d = dataMap.get("form41ValueCodeAmountD");
		String serviceLineNumber = dataMap.get("serviceLineNumber");
		String revcd = dataMap.get("revenueCode");
		String pccd = dataMap.get("serviceCode");
		String srvcdt = dataMap.get("serviceDate");
		String noofunits =dataMap.get("units");
		String charges = dataMap.get("totalCharges");
		String noncoverdcharges = dataMap.get("nonCoveredCharges");
		String noOfPreviousPayer = dataMap.get("noOFPreviousPayer");
		String healthplanid_a = dataMap.get("healthPlanIDA");
		String relinfo_a = dataMap.get("relInfoCheckBoxA");
		String benfitassignment_a = dataMap.get("beneftAssignmentCheckboxA");
		String priorpaymentamt_a = dataMap.get("priorPaymentAmountA");
		String estamountdue_a = dataMap.get("estAmountDueA");
		String payertype_a = dataMap.get("payerTypeDrodownA");
		String payer_b = dataMap.get("payerB");
		String healthplanid_b = dataMap.get("healthPlanIDB");
		String relinfo_b = dataMap.get("relInfoCheckBoxB");
		String benfitassignment_b = dataMap.get("beneftAssignmentCheckboxB");
		String priorpaymentamt_b = dataMap.get("priorPaymentAmountB");
		String estamountdue_b = dataMap.get("estAmountDueB");
		String payertype_b = dataMap.get("payerTypeDrodownB");
		String payer_c = dataMap.get("payerC");
		String healthplanid_c = dataMap.get("healthPlanIDC");
		String relinfo_c = dataMap.get("relInfoCheckBoxC");
		String benfitassignment_c = dataMap.get("beneftAssignmentCheckboxC");
		String priorpaymentamt_c = dataMap.get("priorPaymentAmountC");
		String estamountdue_c = dataMap.get("estAmountDueC");
		String payertype_c = dataMap.get("payerTypeDrodownC");
		String billingprvid = dataMap.get("billingProviderNPI");
		String othrprvid = dataMap.get("otherProviderID");
		String insrdname_a = dataMap.get("insuredNameA");
		String insrdname_b = dataMap.get("insuredNameB");
		String insrdname_c = dataMap.get("insuredNameC");
		String patreltoinsure_a = dataMap.get("patientRelatedToInsuranceA");
		String patreltoinsure_b = dataMap.get("patientRelatedToInsuranceB");
		String patreltoinsure_c = dataMap.get("patientRelatedToInsuranceC");
		String insuredunqid_a = dataMap.get("insuredsUniqueIDA");
		String insuredunqid_b = dataMap.get("insuredsUniqueIDB");
		String insuredunqid_c = dataMap.get("insuredsUniqueIDC");
		String insrdgrpnm_a = dataMap.get("insuredGroupNameA");
		String insrdgrpnm_b = dataMap.get("insuredGroupNameB");
		String insrdgrpnm_c = dataMap.get("insuredGroupNameC");
		String insrdgrpno_a = dataMap.get("insuredGroupNumberA");
		String insrdgrpno_b = dataMap.get("insuredGroupNumberB");
		String insrdgrpno_c = dataMap.get("insuredGroupNumberC");
		String txauthcd_a = dataMap.get("treatmentAuthCodesA");
		String txauthcd_b = dataMap.get("treatmentAuthCodesB");
		String txauthcd_c = dataMap.get("treatmentAuthCodesC");
		String refclm_a = dataMap.get("resubmissionClaimNumberA");
		String refclm_b = dataMap.get("resubmissionClaimNumberB");
		String refclm_c = dataMap.get("resubmissionClaimNumberC");
		String empnm_a = dataMap.get("employerNameA");
		String empnm_b = dataMap.get("employerNameB");
		String empnm_c = dataMap.get("employerNameC");
		String diagversion = dataMap.get("diagnosisVersion");
		String principaldiag = dataMap.get("principalDIagnosis");
		String othrdiag_a = dataMap.get("otherDiagnosisA");
		String othrdiag_b = dataMap.get("otherDiagnosisB");
		String othrdiag_c = dataMap.get("otherDiagnosisC");
		String othrdiag_d = dataMap.get("otherDiagnosisD");
		String othrdiag_e = dataMap.get("otherDiagnosisE");
		String othrdiag_f = dataMap.get("otherDiagnosisF");
		String othrdiag_g = dataMap.get("otherDiagnosisG");
		String othrdiag_h = dataMap.get("otherDiagnosisH");
		String othrdiag_i = dataMap.get("otherDiagnosisI");
		String othrdiag_j = dataMap.get("otherDiagnosisJ");
		String othrdiag_k = dataMap.get("otherDiagnosisK");
		String othrdiag_l = dataMap.get("otherDiagnosisL");
		String othrdiag_m = dataMap.get("otherDiagnosisM");
		String othrdiag_n = dataMap.get("otherDiagnosisN");
		String othrdiag_o = dataMap.get("otherDiagnosisO");
		String othrdiag_p = dataMap.get("otherDiagnosisP");
		String othrdiag_q = dataMap.get("otherDiagnosisQ");
		String admsndiag = dataMap.get("admissionDiagnosis");
		String patrsndiag_a = dataMap.get("patientReasonDiagnosisA");
		String patrsndiag_b = dataMap.get("patientReasonDiagnosisB");
		String patrsndiag_c = dataMap.get("patientReasonDiagnosisC");
		String ppscd = dataMap.get("ppsCode");
		String ecidiagcd_a = dataMap.get("eciDiagnosisCodeA");
		String ecidiagcd_b = dataMap.get("eciDiagnosisCodeB");
		String ecidiagcd_c = dataMap.get("eciDiagnosisCodeC");
		String principlepccd = dataMap.get("principleProcedureCode");
		String principlepcdt = dataMap.get("principlePCDate");
		String othrpccd_a = dataMap.get("otherProcedureCodeA");
		String othrpcdt_a = dataMap.get("otherPCDateA");
		String othrpccd_b = dataMap.get("otherProcedureCodeB");
		String othrpcdt_b = dataMap.get("otherPCDateB");
		String othrpccd_c = dataMap.get("otherProcedureCodeC");
		String othrpcdt_c = dataMap.get("otherPCDateC");
		String othrpccd_d = dataMap.get("otherProcedureCodeD");
		String othrpcdt_d = dataMap.get("otherPCDateD");
		String othrpccd_e = dataMap.get("otherProcedureCodeE");
		String othrpcdt_e = dataMap.get("otherPCDateE");
		String attndphynpi = dataMap.get("attendingPhysicianNPI");
		String attndphyqual1 = dataMap.get("attendingPhysicianQual1");
		String attndphyqual2 = dataMap.get("attendingPhysicianQual2");
		String attndphyln = dataMap.get("attendingPhysicianLastName");
		String attndphyfn = dataMap.get("attendingPhysicianFirstName");
		String oprtphynpi = dataMap.get("operatingPhysicianNPI");
		String oprtphyqual1 = dataMap.get("operatingPhysicianQual1");
		String oprtphyqual2 = dataMap.get("operatingPhysicianQual2");
		String oprtphyln = dataMap.get("operatingPhysicianLastName");
		String oprtphyfn = dataMap.get("operatingPhysicianFirstName");
		String othrnpi_a = dataMap.get("otherNPIA");
		String othrnpiqual1_a = dataMap.get("otherNPIQual1A");
		String othrnpiqual2_a = dataMap.get("otherNPIQual2A");
		String othrnpiln_a = dataMap.get("otherNPILastNameA");
		String othrnpifn_a = dataMap.get("otherNPIFirstNameA");
		String othrnpi_b = dataMap.get("otherNPIB");
		String othrnpiqual1_b = dataMap.get("otherNPIQual1B");
		String othrnpiqual2_b = dataMap.get("otherNPIQual2B");
		String othrnpiln_b = dataMap.get("otherNPILastNameB");
		String othrnpifn_b = dataMap.get("otherNPIFirstNameB");
		String reMarks = dataMap.get("remarks");
//		String form81qualifier_a = dataMap.get("form81AQualifier");
		String form81taxonomy_a = dataMap.get("form81ATaxanomy");
		String form81value_a = dataMap.get("form81AValue");
		String form81qualifier_b = dataMap.get("form81BQualifier");
		String form81taxonomy_b = dataMap.get("form81BTaxanomy");
		String form81value_b = dataMap.get("form81BValue");
		String form81qualifier_c = dataMap.get("form81CQualifier");
		String form81taxonomy_c = dataMap.get("form81CTaxanomy");
		String form81value_c = dataMap.get("form81CValue");
		String form81qualifier_d = dataMap.get("form81DQualifier");
		String form81taxonomy_d = dataMap.get("form81DTaxanomy");
		String form81value_d = dataMap.get("form81DValue");
		String receveddate = dataMap.get("receivedDate");
		
		filterWithMyMCSNumber(myMCSNumber);
		click(firstRecord, "");
		waitForLoadingToDisappear();
		click(CopyButton, "Copy");
		if(copyUB04Heading.isDisplayed()) {
			waitForLoadingToDisappear();
			report(LogStatus.PASS, "Copy Popup is displayed.");
			if(Objects.nonNull(prvid) && !prvid.equals("")) {
				String actualProviderID = getAttribute(providerIDText, "value");
				if(!actualProviderID.equals(prvid)) {
					waitUntilClickable(providerSearchButton, 10);
					click(providerSearchButton, "Provider Search");
					
					//Provider selection
					if(providerSearchPopupHeading.isDisplayed()) {
						report(LogStatus.PASS,"Provider Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Provider Search popup is not displayed.");
					}
					
					sendKeys(providerSeachProviderID, "Provider ID", prvid);
					click(providerSearchSearcButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = providerSearchGridRow.replace("XX", "1");
					WebElement providerrow = driver.findElement(By.xpath(rownumber));
					click(providerrow, "First provider record");
					click(providerSearchSelectProviderButton, "Select Provider");
					waitForLoadingToDisappear();
					actualProviderID = getAttribute(providerIDText, "value");
					assertEquals(actualProviderID, prvid, "The provider id from field "
								+ "is : "+actualProviderID+" not : "+prvid);
					report(LogStatus.PASS, "Provider Id updated successfully.");
					
				}else {
					report(LogStatus.INFO, "Provider Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Provider id is null or empty.");
			}
			
			//Site Selection
			if(Objects.nonNull(steid) && !steid.equals("")) {
				String actualSiteID = getAttribute(siteIDText2, "value");
				if(!actualSiteID.equals(steid)) {
					waitForLoadingToDisappear();
					click(siteSelectionDropdown, "Site dropdown");
					String siteElement = dropdownOptions.replace("XX", steid);
					WebElement site_ele = driver.findElement(By.xpath(siteElement));
					click(site_ele, "Site");
					waitForLoadingToDisappear();
					actualSiteID = getAttribute(siteIDText2, "value");
					assertEquals(actualSiteID, steid,"The site id from field "
								+ "is : "+actualSiteID+" not : "+steid);
					report(LogStatus.PASS, "Site Id updated successfully.");
				}else {
					report(LogStatus.INFO, "Site Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Site id is null or empty.");
			}
			
			//Patient selection
			if(Objects.nonNull(patid) && !patid.equals("")) {
				String actualPatientID = getAttribute(patientIDText, "value");
				if(!actualPatientID.equals(patid)) {
					waitUntilClickable(patientSearchButton, 20);
					click(patientSearchButton, "Patient Search");
					if(patientSearchHeading.isDisplayed()) {
						report(LogStatus.PASS,"Patient Search popup is displayed.");
					}else {
						report(LogStatus.FAIL,"Patient Search popup is not displayed.");
					}
					sendKeys(patientSearchPatientID, "Patient ID", patid);
					actualPatientID = getAttribute(patientSearchPatientID, "value");
					assertEquals(actualPatientID, patid, "The Patient Control Number from "
							+ "field is : "+actualPatientID+" not : "+patid);
					click(patientSearchSearchButton, "Search");
					waitForLoadingToDisappear();
					String rownumber = patientSearchGridRow.replace("XX", "1");
					WebElement patientrow = driver.findElement(By.xpath(rownumber));
					click(patientrow, "First patient record");
					click(patientSearchSelectPatientButton, "Select Patient");
					waitForLoadingToDisappear();
				}else {
					report(LogStatus.INFO, "Patient Id is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient id is null or empty.");
			}
			
			//Patient Control Number
			if(Objects.nonNull(pcn) && !pcn.equals("")) {
				String actualPCN = getAttribute(patientControlNumber, "value");
				if(!actualPCN.equals(pcn)) {
					patientControlNumber.clear();
					sendKeys(patientControlNumber, "Patient Control Number", pcn);
					actualPCN = getAttribute(patientControlNumber, "value");
					assertEquals(actualPCN, pcn, "The Patient Control Number from field "
							+ "is : "+actualPCN+" not : "+pcn);
				}else {
					report(LogStatus.INFO, "Patient Control Number is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Patient control Number is null or empty.");
			}
			
			//Bill Type Speciality
			if(Objects.nonNull(billtype) && !billtype.equals("")) {
				String actualBillType = getAttribute(billType, "value");
				System.out.println(actualBillType+" : "+billtype);
				if(!actualBillType.equals(billtype)) {
					billType.clear();
					sendKeys(billType, "Bill Type", billtype);
					actualBillType = getAttribute(billType, "value");
					assertEquals(actualBillType, billtype, "The Patient Control Number "
							+ "from field is : "+actualBillType+" not : "+billtype);
				}else {
					report(LogStatus.INFO, "Billtype is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Bill Type is null or empty.");
			}
			
			//From and To period
			if(Objects.nonNull(fromperiod) && Objects.nonNull(toperiod) && 
					!fromperiod.equals("") && !toperiod.equals("")) {
				String actualFromDate = getAttribute(statementFromPeriod, "value");
				String actualToDate = getAttribute(statementToPeriod, "value");
				if(!actualFromDate.equals(fromperiod) && !actualToDate.equals(toperiod)) {
					for(int i = 0 ; i < 10 ; i++)
						statementFromPeriod.sendKeys(Keys.BACK_SPACE);
					for(int i = 0 ; i < 10 ; i++)
						statementToPeriod.sendKeys(Keys.BACK_SPACE);
					sendKeys(statementFromPeriod, "From Date", fromperiod);
					sendKeys(statementToPeriod, "To Date", toperiod);
					actualFromDate = getAttribute(statementFromPeriod, "value");
					actualToDate = getAttribute(statementToPeriod, "value");
					assertEquals(actualFromDate, fromperiod, "The Statement From Period  "
							+ "from field is : "+actualFromDate+" not : "+fromperiod);
					assertEquals(actualToDate, toperiod, "The Statement To Period  "
							+ "from field is : "+actualToDate+" not : "+toperiod);
				}else {
					report(LogStatus.INFO, "From Date or To Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"From period or To period is null or empty.");
			}
			
			//Admission Date
			if(Objects.nonNull(admsndt) && !admsndt.equals("")) {
				String actualAdmissionDate = getAttribute(admissionDate, "value");
				if(!actualAdmissionDate.equals(admsndt)) {
					for(int i = 0 ; i < 10 ; i++)
						admissionDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(admissionDate, "Admission Date", admsndt);
					actualAdmissionDate = getAttribute(admissionDate, "value");
					assertEquals(actualAdmissionDate, admsndt, "The Admission Date from field"
							+" is : "+actualAdmissionDate+" not : "+admsndt);
				}else {
					report(LogStatus.INFO, "Admission Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission date is null or empty.");
			}
			
			//Admission Hour
			if(Objects.nonNull(admsnhr) && !admsnhr.equals("")) {
				String actualAdmissionHour = getText(admissionHourText);
				if(!actualAdmissionHour.equals(admsnhr)) {
					click(admissionHourDropdown,"Admission Hour");
					String admsnHour = dropdownOptions.replace("XX", admsnhr);
					WebElement admisionHour = driver.findElement(By.xpath(admsnHour));
					waitUntilClickable(admisionHour, 10);
					click(admisionHour,"Admission Hour");
					actualAdmissionHour = getText(admissionHourText);
					assertEquals(actualAdmissionHour, admsnhr,"The Admission Hour from field "
							+ "is : "+actualAdmissionHour+" not "+admsnhr);
				}else {
					report(LogStatus.INFO, "Admission Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Admission Hour is null or empty.");
			}
			
			//Reference Type
			if(Objects.nonNull(reftype) && !reftype.equals("")) {
				String actualReferenceType = getText(visitTypeText);
				if(!actualReferenceType.equals(reftype)) {
					click(visitTypeDropdown, "Visit Type");
					String refType = dropdownOptions.replace("XX", reftype);
					WebElement referenceType = driver.findElement(By.xpath(refType));
					waitUntilClickable(referenceType, 10);
					click(referenceType, reftype);
					actualReferenceType = getText(visitTypeText);
					assertEquals(actualReferenceType, reftype,"The Reference Type from field "
							+ "is : "+actualReferenceType+" not "+reftype);
				}else {
					report(LogStatus.INFO, "Reference type is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Type is empty or null.");
			}
			
			//Reference Source
			if(Objects.nonNull(refsrc) && !refsrc.equals("")) {
				String actualReferenceSource = getText(referenceSourceText);
				if(!actualReferenceSource.equals(refsrc)) {
					click(referenceSourceDropdown, "Visit Type");
					String refSrc = dropdownOptions.replace("XX", refsrc);
					WebElement referenceSource = driver.findElement(By.xpath(refSrc));
					waitUntilClickable(referenceSource, 10);
					click(referenceSource, refsrc);
					actualReferenceSource = getText(referenceSourceText);
					assertEquals(actualReferenceSource, refsrc,"The Reference Source from field "
							+ "is : "+actualReferenceSource+" not "+refsrc);
				}else {
					report(LogStatus.INFO, "Reference Source is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Reference Source is empty or null.");
			}
			
			//Discharge Hour
			if(Objects.nonNull(dschrghr) && !dschrghr.equals("")) {
				String actualDischargeHour = getText(dischargeHourText);
				if(actualDischargeHour.equals(dschrghr)) {
					click(dischargeHour, "Visit Type");
					String dischrgHr = dropdownOptions.replace("XX", dschrghr);
					WebElement dischargeHour = driver.findElement(By.xpath(dischrgHr));
					waitUntilClickable(dischargeHour, 10);
					click(dischargeHour, dschrghr);
					actualDischargeHour = getText(dischargeHourText);
					assertEquals(actualDischargeHour, dschrghr,"The Reference Source from field "
							+ "is : "+actualDischargeHour+" not "+dschrghr);
				}else {
					report(LogStatus.INFO, "Discharge Hour is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Hour is empty or null.");
			}
			
			//Discharge Status
			if(Objects.nonNull(dschrgsts) && !dschrgsts.equals("")) {
				String actualDischargeStatus = getText(dischargeStatusText);
				if(!actualDischargeStatus.equals(dschrgsts)) {
					click(dischargeStatus, "Visit Type");
					String dischrgSts = dropdownOptions.replace("XX", dschrgsts);
					WebElement dischargeStatus = driver.findElement(By.xpath(dischrgSts));
					waitUntilClickable(dischargeStatus, 10);
					click(dischargeStatus, dschrgsts);
					actualDischargeStatus = getText(dischargeStatusText);
					assertEquals(actualDischargeStatus, dschrgsts,"The Reference Source from field "
							+ "is : "+actualDischargeStatus+" not "+dschrgsts);
				}else {
					report(LogStatus.INFO, "Discharge Status is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Discharge Status is empty or null.");
			}
			
			//Form 18
			if(Objects.nonNull(form_18) && !form_18.equals("")) {
				String actualForm18 = getAttribute(form18, "value");
				String actform_18 = form_18;
				if(form_18.length()>2)
					actform_18 = form_18.substring(0, 2);
				if(!actualForm18.equals(actform_18)) {
					form18.clear();
					sendKeys(form18, "Form 18", form_18);
					actualForm18 = getAttribute(form18, "value");
					assertEquals(actualForm18, actform_18, "The Value from Form 18 is : "+
							actualForm18+" not : "+actform_18);
				}else {
					report(LogStatus.INFO, "Form 18 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 18 is empty or null.");
			}
			
			//Form 19
			if(Objects.nonNull(form_19) && !form_19.equals("")) {
				String actualForm19 = getAttribute(form19, "value");
				String actform_19 = form_19;
				if(form_19.length()>2)
					actform_19 = form_19.substring(0, 2);
				if(!actualForm19.equals(actform_19)) {
					form19.clear();
					sendKeys(form19, "Form 19", form_19);
					actualForm19 = getAttribute(form19, "value");
					assertEquals(actualForm19, actform_19, "The Value from Form 19 is : "+
							actualForm19+" not : "+actform_19);
				}else {
					report(LogStatus.INFO, "Form 19 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 19 is empty or null.");
			}
			
			//Form 20
			if(Objects.nonNull(form_20) && !form_20.equals("")) {
				String actualForm20 = getAttribute(form20, "value");
				String actform_20 = form_20;
				if(form_20.length()>2)
					actform_20 = form_20.substring(0, 2);
				if(!actualForm20.equals(actform_20)) {
					form20.clear();
					sendKeys(form20, "Form 20", form_20);
					actualForm20 = getAttribute(form20, "value");
					assertEquals(actualForm20, actform_20, "The Value from Form 20 is : "+
							actualForm20+" not : "+actform_20);
				}else {
					report(LogStatus.INFO, "Form 20 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 20 is empty or null.");
			}
			
			//Form 21
			if(Objects.nonNull(form_21) && !form_21.equals("")) {
				String actualForm21 = getAttribute(form21, "value");
				String actform_21 = form_21;
				if(form_21.length()>2)
					actform_21 = form_21.substring(0, 2);
				if(!actualForm21.equals(actform_21)) {
					form21.clear();
					sendKeys(form21, "Form 21", form_21);
					actualForm21 = getAttribute(form21, "value");
					assertEquals(actualForm21, actform_21, "The Value from Form 21 is : "+
							actualForm21+" not : "+actform_21);
				}else {
					report(LogStatus.INFO, "Form 21 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 21 is empty or null.");
			}
			
			//Form 22
			if(Objects.nonNull(form_22) && !form_22.equals("")) {
				String actualForm22 = getAttribute(form22, "value");
				String actform_22 = form_22;
				if(form_22.length()>2)
					actform_22 = form_22.substring(0, 2);
				if(!actualForm22.equals(actform_22)) {
					form22.clear();
					sendKeys(form22, "Form 22", form_22);
					actualForm22 = getAttribute(form22, "value");
					assertEquals(actualForm22, actform_22, "The Value from Form 22 is : "+
							actualForm22+" not : "+actform_22);
				}else {
					report(LogStatus.INFO, "Form 22 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 22 is empty or null.");
			}
			
			//Form 23
			if(Objects.nonNull(form_23) && !form_23.equals("")) {
				String actualForm23 = getAttribute(form23, "value");
				String actform_23 = form_23;
				if(form_23.length()>2)
					actform_23 = form_23.substring(0, 2);
				if(!actualForm23.equals(actform_23)) {
					form23.clear();
					sendKeys(form23, "Form 23", form_23);
					actualForm23 = getAttribute(form23, "value");
					assertEquals(actualForm23, actform_23, "The Value from Form 23 is : "+
							actualForm23+" not : "+actform_23);
				}else {
					report(LogStatus.INFO, "Form 23 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 23 is empty or null.");
			}
			
			//Form 24
			if(Objects.nonNull(form_24) && !form_24.equals("")) {
				String actualForm24 = getAttribute(form24, "value");
				String actform_24 = form_24;
				if(form_24.length()>2)
					actform_24 = form_24.substring(0, 2);
				if(!actualForm24.equals(actform_24)) {
					form24.clear();
					sendKeys(form24, "Form 24", form_24);
					actualForm24 = getAttribute(form24, "value");
					assertEquals(actualForm24, actform_24, "The Value from Form 24 is : "+
							actualForm24+" not : "+actform_24);
				}else {
					report(LogStatus.INFO, "Form 24 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 24 is empty or null.");
			}
			
			//Form 25
			if(Objects.nonNull(form_25) && !form_25.equals("")) {
				String actualForm25 = getAttribute(form25, "value");
				String actform_25 = form_25;
				if(form_25.length()>2)
					actform_25 = form_25.substring(0, 2);
				if(!actualForm25.equals(actform_25)) {
					form25.clear();
					sendKeys(form25, "Form 25", form_25);
					actualForm25 = getAttribute(form25, "value");
					assertEquals(actualForm25, actform_25, "The Value from Form 25 is : "+
							actualForm25+" not : "+actform_25);
				}else {
					report(LogStatus.INFO, "Form 25 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 25 is empty or null.");
			}
			
			//Form 26
			if(Objects.nonNull(form_26) && !form_26.equals("")) {
				String actualForm26 = getAttribute(form26, "value");
				String actform_26 = form_26;
				if(form_26.length()>2)
					actform_26 = form_26.substring(0, 2);
				if(!actualForm26.equals(actform_26)) {
					form26.clear();
					sendKeys(form26, "Form 26", form_26);
					actualForm26 = getAttribute(form26, "value");
					assertEquals(actualForm26, actform_26, "The Value from Form 26 is : "+
							actualForm26+" not : "+actform_26);
				}else {
					report(LogStatus.INFO, "Form 26 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 26 is empty or null.");
			}
			
			//Form 27
			if(Objects.nonNull(form_27) && !form_27.equals("")) {
				String actualForm27 = getAttribute(form27, "value");
				String actform_27 = form_27;
				if(form_27.length()>2)
					actform_27 = form_27.substring(0, 2);
				if(!actualForm27.equals(actform_27)) {
					form27.clear();
					sendKeys(form27, "Form 27", form_27);
					actualForm27 = getAttribute(form27, "value");
					assertEquals(actualForm27, actform_27, "The Value from Form 27 is : "+
							actualForm27+" not : "+actform_27);
				}else {
					report(LogStatus.INFO, "Form 27 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 27 is empty or null.");
			}
			
			//Form 28
			if(Objects.nonNull(form_28) && !form_28.equals("")) {
				String actualForm28 = getAttribute(form28, "value");
				String actform_28 = form_28;
				if(form_28.length()>2)
					actform_28 = form_28.substring(0, 2);
				if(!actualForm28.equals(actform_28)) {
					form28.clear();
					sendKeys(form28, "Form 28", form_28);
					actualForm28 = getAttribute(form28, "value");
					assertEquals(actualForm28, actform_28, "The Value from Form 28 is : "+
							actualForm28+" not : "+actform_28);
				}else {
					report(LogStatus.INFO, "Form 28 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Form 28 is empty or null.");
			}
			
			//Accident State
			if(Objects.nonNull(accdtstate) && !accdtstate.equals("")) {
				String actualAccidentState = getAttribute(accidentState, "value");
				String actaccdtstate = accdtstate;
				if(accdtstate.length()>2)
					actaccdtstate = accdtstate.substring(0, 2);
				if(!actualAccidentState.equals(accdtstate)) {
					accidentState.clear();
					sendKeys(accidentState, "Accident State", accdtstate);
					actualAccidentState = getAttribute(accidentState, "value");
					assertEquals(actualAccidentState, actaccdtstate, "The Value from Form 28 is : "+
							actualAccidentState+" not : "+actaccdtstate);
				}else {
					report(LogStatus.INFO, "Accident State is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"Accident State is empty or null.");
			}
			
			//Form 31 A
			if(Objects.nonNull(form31occcd_a) && !form31occcd_a.equals("") && 
					Objects.nonNull(form31occdt_a) && !form31occdt_a.equals("")) {
				String actualform31aoccurancecode = 
						getAttribute(form31OccuranceCodeA, "value");
				String actform31occcd_a = form31occcd_a;
				String actualform31adate = 
						getAttribute(form31OccuranceDateA, "value");
				if(actform31occcd_a.length()>2)
					actform31occcd_a = form31occcd_a.substring(0, 2);
				if(!actualform31aoccurancecode.equals(actform31occcd_a) && 
						!actualform31adate.equals(form31occdt_a)) {
					form31OccuranceCodeA.clear();
					sendKeys(form31OccuranceCodeA, "31 A Occurance Code", form31occcd_a);
					actualform31aoccurancecode = 
							getAttribute(form31OccuranceCodeA, "value");
					assertEquals(actualform31aoccurancecode, actform31occcd_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31aoccurancecode+
							" not : "+actform31occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateA, "31 A Date", form31occdt_a);
					actualform31adate = 
							getAttribute(form31OccuranceDateA, "value");
					assertEquals(actualform31adate, form31occdt_a, "The Occurance "
							+ "code from Form 31 A is : "+actualform31adate+
							" not : "+form31occdt_a);
				}else {
					report(LogStatus.INFO, "Form 31 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 A code or date is empty or null.");
			}
			
			//Form 31 B
			if(Objects.nonNull(form31occcd_b) && !form31occcd_b.equals("") && 
					Objects.nonNull(form31occdt_b) && !form31occdt_b.equals("")) {
				String actualform31boccurancecode = 
						getAttribute(form31OccuranceCodeB, "value");
				String actform31occcd_b = form31occcd_b;
				String actualform31bdate = 
						getAttribute(form31OccuranceDateB, "value");
				if(actform31occcd_b.length()>2)
					actform31occcd_b = form31occcd_b.substring(0, 2);
				if(!actualform31boccurancecode.equals(actform31occcd_b) && 
						!actualform31bdate.equals(form31occdt_b)) {
					form31OccuranceCodeB.clear();
					sendKeys(form31OccuranceCodeB, "31 B Occurance Code", form31occcd_b);
					actualform31boccurancecode = 
							getAttribute(form31OccuranceCodeB, "value");
					assertEquals(actualform31boccurancecode, actform31occcd_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31boccurancecode+
							" not : "+actform31occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form31OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form31OccuranceDateB, "31 B Date", form31occdt_b);
					actualform31bdate = 
							getAttribute(form31OccuranceDateB, "value");
					assertEquals(actualform31bdate, form31occdt_b, "The Occurance "
							+ "code from Form 31 B is : "+actualform31bdate+
							" not : "+form31occdt_b);
				}else {
					report(LogStatus.INFO, "Form 31 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 31 B code or date is empty or null.");
			}
			
			//Form 32 A
			if(Objects.nonNull(form32occcd_a) && !form32occcd_a.equals("") && 
					Objects.nonNull(form32occdt_a) && !form32occdt_a.equals("")) {
				String actualform32aoccurancecode = 
						getAttribute(form32OccuranceCodeA, "value");
				String actform32occcd_a = form32occcd_a;
				String actualform32adate = 
						getAttribute(form32OccuranceDateA, "value");
				if(actform32occcd_a.length()>2)
					actform32occcd_a = form32occcd_a.substring(0, 2);
				if(!actualform32aoccurancecode.equals(actform32occcd_a) && 
						!actualform32adate.equals(form32occdt_a)) {
					form32OccuranceCodeA.clear();
					sendKeys(form32OccuranceCodeA, "32 A Occurance Code", form32occcd_a);
					actualform32aoccurancecode = 
							getAttribute(form32OccuranceCodeA, "value");
					assertEquals(actualform32aoccurancecode, actform32occcd_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32aoccurancecode+
							" not : "+actform32occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateA, "32 A Date", form32occdt_a);
					actualform32adate = 
							getAttribute(form32OccuranceDateA, "value");
					assertEquals(actualform32adate, form32occdt_a, "The Occurance "
							+ "code from Form 32 A is : "+actualform32adate+
							" not : "+form32occdt_a);
				}else {
					report(LogStatus.INFO, "Form 32 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 A code or date is empty or null.");
			}
			
			//Form 32 B
			if(Objects.nonNull(form32occcd_b) && !form32occcd_b.equals("") && 
					Objects.nonNull(form32occdt_b) && !form32occdt_b.equals("")) {
				String actualform32boccurancecode = 
						getAttribute(form32OccuranceCodeB, "value");
				String actform32occcd_b = form32occcd_b;
				String actualform32bdate = 
						getAttribute(form32OccuranceDateB, "value");
				if(actform32occcd_b.length()>2)
					actform32occcd_b = form32occcd_b.substring(0, 2);
				if(!actualform32boccurancecode.equals(actform32occcd_b) && 
						!actualform32bdate.equals(form32occdt_b)) {
					form32OccuranceCodeB.clear();
					sendKeys(form32OccuranceCodeB, "32 B Occurance Code", form32occcd_b);
					actualform32boccurancecode = 
							getAttribute(form32OccuranceCodeB, "value");
					assertEquals(actualform32boccurancecode, actform32occcd_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32boccurancecode+
							" not : "+actform32occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form32OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form32OccuranceDateB, "32 B Date", form32occdt_b);
					actualform32bdate = 
							getAttribute(form32OccuranceDateB, "value");
					assertEquals(actualform32bdate, form32occdt_b, "The Occurance "
							+ "code from Form 32 B is : "+actualform32bdate+
							" not : "+form32occdt_b);
				}else {
					report(LogStatus.INFO, "Form 32 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 32 B code or date is empty or null.");
			}
			
			//Form 33 A
			if(Objects.nonNull(form33occcd_a) && !form33occcd_a.equals("") && 
					Objects.nonNull(form33occdt_a) && !form33occdt_a.equals("")) {
				String actualform33aoccurancecode = 
						getAttribute(form33OccuranceCodeA, "value");
				String actform33occcd_a = form33occcd_a;
				String actualform33adate = 
						getAttribute(form33OccuranceDateA, "value");
				if(actform33occcd_a.length()>2)
					actform33occcd_a = form33occcd_a.substring(0, 2);
				if(!actualform33aoccurancecode.equals(actform33occcd_a) && 
						!actualform33adate.equals(form33occdt_a)) {
					form33OccuranceCodeA.clear();
					sendKeys(form33OccuranceCodeA, "33 A Occurance Code", form33occcd_a);
					actualform33aoccurancecode = 
							getAttribute(form33OccuranceCodeA, "value");
					assertEquals(actualform33aoccurancecode, actform33occcd_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33aoccurancecode+
							" not : "+actform33occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateA, "33 A Date", form33occdt_a);
					actualform33adate = 
							getAttribute(form33OccuranceDateA, "value");
					assertEquals(actualform33adate, form33occdt_a, "The Occurance "
							+ "code from Form 33 A is : "+actualform33adate+
							" not : "+form33occdt_a);
				}else {
					report(LogStatus.INFO, "Form 33 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 A code or date is empty or null.");
			}
			
			//Form 33 B
			if(Objects.nonNull(form33occcd_b) && !form33occcd_b.equals("") && 
					Objects.nonNull(form33occdt_b) && !form33occdt_b.equals("")) {
				String actualform33boccurancecode = 
						getAttribute(form33OccuranceCodeB, "value");
				String actform33occcd_b = form33occcd_b;
				String actualform33bdate = 
						getAttribute(form33OccuranceDateB, "value");
				if(actform33occcd_b.length()>2)
					actform33occcd_b = form33occcd_b.substring(0, 2);
				if(!actualform33boccurancecode.equals(actform33occcd_b) && 
						!actualform33bdate.equals(form33occdt_b)) {
					form33OccuranceCodeB.clear();
					sendKeys(form33OccuranceCodeB, "33 B Occurance Code", form33occcd_b);
					actualform33boccurancecode = 
							getAttribute(form33OccuranceCodeB, "value");
					assertEquals(actualform33boccurancecode, actform33occcd_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33boccurancecode+
							" not : "+actform33occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form33OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form33OccuranceDateB, "33 B Date", form33occdt_b);
					actualform33bdate = 
							getAttribute(form33OccuranceDateB, "value");
					assertEquals(actualform33bdate, form33occdt_b, "The Occurance "
							+ "code from Form 33 B is : "+actualform33bdate+
							" not : "+form33occdt_b);
				}else {
					report(LogStatus.INFO, "Form 33 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 33 B code or date is empty or null.");
			}
			
			//Form 34 A
			if(Objects.nonNull(form34occcd_a) && !form34occcd_a.equals("") && 
					Objects.nonNull(form34occdt_a) && !form34occdt_a.equals("")) {
				String actualform34aoccurancecode = 
						getAttribute(form34OccuranceCodeA, "value");
				String actform34occcd_a = form34occcd_a;
				String actualform34adate = 
						getAttribute(form34OccuranceDateA, "value");
				if(actform34occcd_a.length()>2)
					actform34occcd_a = form34occcd_a.substring(0, 2);
				if(!actualform34aoccurancecode.equals(actform34occcd_a) && 
						!actualform34adate.equals(form34occdt_a)) {
					form34OccuranceCodeA.clear();
					sendKeys(form34OccuranceCodeA, "34 A Occurance Code", form34occcd_a);
					actualform34aoccurancecode = 
							getAttribute(form34OccuranceCodeA, "value");
					assertEquals(actualform34aoccurancecode, actform34occcd_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34aoccurancecode+
							" not : "+actform34occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateA, "34 A Date", form34occdt_a);
					actualform34adate = 
							getAttribute(form34OccuranceDateA, "value");
					assertEquals(actualform34adate, form34occdt_a, "The Occurance "
							+ "code from Form 34 A is : "+actualform34adate+
							" not : "+form34occdt_a);
				}else {
					report(LogStatus.INFO, "Form 34 A code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 A code or date is empty or null.");
			}
			
			//Form 34 B
			if(Objects.nonNull(form34occcd_b) && !form34occcd_b.equals("") && 
					Objects.nonNull(form34occdt_b) && !form34occdt_b.equals("")) {
				String actualform34boccurancecode = 
						getAttribute(form34OccuranceCodeB, "value");
				String actform34occcd_b = form34occcd_b;
				String actualform34bdate = 
						getAttribute(form34OccuranceDateB, "value");
				if(actform34occcd_b.length()>2)
					actform34occcd_b = form34occcd_b.substring(0, 2);
				if(!actualform34boccurancecode.equals(actform34occcd_b) && 
						!actualform34bdate.equals(form34occdt_b)) {
					form34OccuranceCodeB.clear();
					sendKeys(form34OccuranceCodeB, "34 B Occurance Code", form34occcd_b);
					actualform34boccurancecode = 
							getAttribute(form34OccuranceCodeB, "value");
					assertEquals(actualform34boccurancecode, actform34occcd_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34boccurancecode+
							" not : "+actform34occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form34OccuranceDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form34OccuranceDateB, "34 B Date", form34occdt_b);
					actualform34bdate = 
							getAttribute(form34OccuranceDateB, "value");
					assertEquals(actualform34bdate, form34occdt_b, "The Occurance "
							+ "code from Form 34 B is : "+actualform34bdate+
							" not : "+form34occdt_b);
				}else {
					report(LogStatus.INFO, "Form 34 B code or date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "Form 34 B code or date is empty or null.");
			}
			
			//Form 35 A
			if(Objects.nonNull(form35occcd_a) && !form35occcd_a.equals("") && 
					Objects.nonNull(form35occfrdt_a) && !form35occfrdt_a.equals("")
					&& Objects.nonNull(form35occtodt_a) && !form35occtodt_a.equals("")) {
				String actualform35aoccurancecode = 
						getAttribute(form35OccuranceSpanCodeA, "value");
				String actform35occcd_a = form35occcd_a;
				if(actform35occcd_a.length()>2)
					actform35occcd_a = form35occcd_a.substring(0, 2);
				String actualform35afromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateA, "value");
				String actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
				if(!actualform35aoccurancecode.equals(actform35occcd_a) && 
						!actualform35afromdate.equals(form35occfrdt_a) && 
						!actualform35atodate.equals(form35occtodt_a)) {
					form35OccuranceSpanCodeA.clear();
					sendKeys(form35OccuranceSpanCodeA, "35 A Occurance Code", form35occcd_a);
					actualform35aoccurancecode = 
							getAttribute(form35OccuranceSpanCodeA, "value");
					assertEquals(actualform35aoccurancecode, actform35occcd_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35aoccurancecode+
							" not : "+actform35occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateA, "35 A Date", form35occfrdt_a);
					actualform35afromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform35afromdate, form35occfrdt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35afromdate+
							" not : "+form35occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateA, "35 A Date", 
							form35occtodt_a);
					actualform35atodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform35atodate, form35occtodt_a, "The Occurance "
							+ "code from Form 35 A is : "+actualform35atodate+
							" not : "+form35occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 35 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 A code or date is empty or null.");
			}
			
			//Form 35 B
			if(Objects.nonNull(form35occcd_b) && !form35occcd_b.equals("") && 
					Objects.nonNull(form35occfrdt_b) && !form35occfrdt_b.equals("")
					&& Objects.nonNull(form35occtodt_b) && !form35occtodt_b.equals("")) {
				String actualform35boccurancecode = 
						getAttribute(form35OccuranceSpanCodeB, "value");
				String actform35occcd_b = form35occcd_b;
				if(actform35occcd_b.length()>2)
					actform35occcd_b = form35occcd_b.substring(0, 2);
				String actualform35bfromdate = 
						getAttribute(form35OccuranceSpanCodeFromDateB, "value");
				String actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
				if(!actualform35boccurancecode.equals(actform35occcd_b) && 
						!actualform35bfromdate.equals(form35occfrdt_b) && 
						!actualform35btodate.equals(form35occtodt_b)) {
					form35OccuranceSpanCodeB.clear();
					sendKeys(form35OccuranceSpanCodeB, "35 B Occurance Code", form35occcd_b);
					actualform35boccurancecode = 
							getAttribute(form35OccuranceSpanCodeB, "value");
					assertEquals(actualform35boccurancecode, actform35occcd_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35boccurancecode+
							" not : "+actform35occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeFromDateB, "35 B Date", form35occfrdt_b);
					actualform35bfromdate = 
							getAttribute(form35OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform35bfromdate, form35occfrdt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35bfromdate+
							" not : "+form35occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form35OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form35OccuranceSpanCodeThroughDateB, "35 B Date", 
							form35occtodt_b);
					actualform35btodate = 
						getAttribute(form35OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform35btodate, form35occtodt_b, "The Occurance "
							+ "code from Form 35 B is : "+actualform35btodate+
							" not : "+form35occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 35 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 35 B code or date is empty or null.");
			}
			
			//Form 36 A
			if(Objects.nonNull(form36occcd_a) && !form36occcd_a.equals("") && 
					Objects.nonNull(form36occfrdt_a) && !form36occfrdt_a.equals("")
					&& Objects.nonNull(form36occtodt_a) && !form36occtodt_a.equals("")) {
				String actualform36aoccurancecode = 
						getAttribute(form36OccuranceSpanCodeA, "value");
				String actform36occcd_a = form36occcd_a;
				if(actform36occcd_a.length()>2)
					actform36occcd_a = form36occcd_a.substring(0, 2);
				String actualform36afromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateA, "value");
				String actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
				if(!actualform36aoccurancecode.equals(actform36occcd_a) && 
						!actualform36afromdate.equals(form36occfrdt_a) && 
						!actualform36atodate.equals(form36occtodt_a)) {
					form36OccuranceSpanCodeA.clear();
					sendKeys(form36OccuranceSpanCodeA, "36 A Occurance Code", form36occcd_a);
					actualform36aoccurancecode = 
							getAttribute(form36OccuranceSpanCodeA, "value");
					assertEquals(actualform36aoccurancecode, actform36occcd_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36aoccurancecode+
							" not : "+actform36occcd_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateA, "36 A Date", form36occfrdt_a);
					actualform36afromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateA, "value");
					assertEquals(actualform36afromdate, form36occfrdt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36afromdate+
							" not : "+form36occfrdt_a);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateA, "36 A Date", 
							form36occtodt_a);
					actualform36atodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateA, "value");
					assertEquals(actualform36atodate, form36occtodt_a, "The Occurance "
							+ "code from Form 36 A is : "+actualform36atodate+
							" not : "+form36occtodt_a);
				}else {
					report(LogStatus.INFO, "Form 36 A code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 A code or date is empty or null.");
			}
			
			//Form 36 B
			if(Objects.nonNull(form36occcd_b) && !form36occcd_b.equals("") && 
					Objects.nonNull(form36occfrdt_b) && !form36occfrdt_b.equals("")
					&& Objects.nonNull(form36occtodt_b) && !form36occtodt_b.equals("")) {
				String actualform36boccurancecode = 
						getAttribute(form36OccuranceSpanCodeB, "value");
				String actform36occcd_b = form36occcd_b;
				if(actform36occcd_b.length()>2)
					actform36occcd_b = form36occcd_b.substring(0, 2);
				String actualform36bfromdate = 
						getAttribute(form36OccuranceSpanCodeFromDateB, "value");
				String actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
				if(!actualform36boccurancecode.equals(actform36occcd_b) && 
						!actualform36bfromdate.equals(form36occfrdt_b) && 
						!actualform36btodate.equals(form36occtodt_b)) {
					form36OccuranceSpanCodeB.clear();
					sendKeys(form36OccuranceSpanCodeB, "36 B Occurance Code", form36occcd_b);
					actualform36boccurancecode = 
							getAttribute(form36OccuranceSpanCodeB, "value");
					assertEquals(actualform36boccurancecode, actform36occcd_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36boccurancecode+
							" not : "+actform36occcd_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeFromDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeFromDateB, "36 B Date", form36occfrdt_b);
					actualform36bfromdate = 
							getAttribute(form36OccuranceSpanCodeFromDateB, "value");
					assertEquals(actualform36bfromdate, form36occfrdt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36bfromdate+
							" not : "+form36occfrdt_b);
					
					for(int i = 0 ; i < 10 ; i++)
						form36OccuranceSpanCodeThroughDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(form36OccuranceSpanCodeThroughDateB, "36 B Date", 
							form36occtodt_b);
					actualform36btodate = 
						getAttribute(form36OccuranceSpanCodeThroughDateB, "value");
					assertEquals(actualform36btodate, form36occtodt_b, "The Occurance "
							+ "code from Form 36 B is : "+actualform36btodate+
							" not : "+form36occtodt_b);
				}else {
					report(LogStatus.INFO, "Form 36 B code or date is same and not changed.");
				}
				
			}else {
				report(LogStatus.WARNING, "Form 36 B code or date is empty or null.");
			}
			
			//Form 39 A
			if(Objects.nonNull(form39valcd_a) && !form39valcd_a.equals("") && 
					Objects.nonNull(form39valcdamt_a) && !form39valcdamt_a.equals("")) {
				String actualform39avalcdamt = 
						getAttribute(form39ValueCodeAmountA, "value");
				String actualform39avalcd = 
						getAttribute(form39ValueCodeA, "value");
				String actform39avalcd = form39valcd_a;
				if(actform39avalcd.length()>2)
					actform39avalcd = form39valcd_a.substring(0, 2);
				if(!actualform39avalcd.equals(actform39avalcd) && 
						!actualform39avalcdamt.equals(form39valcdamt_a)) {
							form39ValueCodeA.clear();
							sendKeys(form39ValueCodeA, "Form 39 Value Code A", form39valcd_a);
							actualform39avalcd = 
									getAttribute(form39ValueCodeA, "value");
							assertEquals(actualform39avalcd, actform39avalcd, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcd+
									" not : "+actform39avalcd);
							
							form39ValueCodeAmountA.clear();
							sendKeys(form39ValueCodeAmountA, "Form 39 A Value code amount", form39valcdamt_a);
							actualform39avalcdamt = 
									getAttribute(form39ValueCodeAmountA, "value");
							assertEquals(actualform39avalcdamt, form39valcdamt_a, "The Value "
									+ "code from Form 39 A is : "+actualform39avalcdamt+
									" not : "+form39valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 39 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 A Code or Amount is empty or null");
			}
			
			//Form 39 B
			if(Objects.nonNull(form39valcd_b) && !form39valcd_b.equals("") && 
					Objects.nonNull(form39valcdamt_b) && !form39valcdamt_b.equals("")) {
				String actualform39bvalcdamt = 
						getAttribute(form39ValueCodeAmountB, "value");
				String actualform39bvalcd = 
						getAttribute(form39ValueCodeB, "value");
				String actform39bvalcd = form39valcd_b;
				if(actform39bvalcd.length()>2)
					actform39bvalcd = form39valcd_b.substring(0, 2);
				if(!actualform39bvalcd.equals(actform39bvalcd) && 
						!actualform39bvalcdamt.equals(form39valcdamt_b)) {
							form39ValueCodeB.clear();
							sendKeys(form39ValueCodeB, "Form 39 Value Code B", form39valcd_b);
							actualform39bvalcd = 
									getAttribute(form39ValueCodeB, "value");
							assertEquals(actualform39bvalcd, actform39bvalcd, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcd+
									" not : "+actform39bvalcd);
							
							form39ValueCodeAmountB.clear();
							sendKeys(form39ValueCodeAmountB, "Form 39 B Value code amount", form39valcdamt_b);
							actualform39bvalcdamt = 
									getAttribute(form39ValueCodeAmountB, "value");
							assertEquals(actualform39bvalcdamt, form39valcdamt_b, "The Value "
									+ "code from Form 39 B is : "+actualform39bvalcdamt+
									" not : "+form39valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 39 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 B Code or Amount is empty or null");
			}
			
			//Form 39 C
			if(Objects.nonNull(form39valcd_c) && !form39valcd_c.equals("") && 
					Objects.nonNull(form39valcdamt_c) && !form39valcdamt_c.equals("")) {
				String actualform39cvalcdamt = 
						getAttribute(form39ValueCodeAmountC, "value");
				String actualform39cvalcd = 
						getAttribute(form39ValueCodeC, "value");
				String actform39cvalcd = form39valcd_a;
				if(actform39cvalcd.length()>2)
					actform39cvalcd = form39valcd_c.substring(0, 2);
				if(!actualform39cvalcd.equals(actform39cvalcd) && 
						!actualform39cvalcdamt.equals(form39valcdamt_c)) {
							form39ValueCodeC.clear();
							sendKeys(form39ValueCodeC, "Form 39 Value Code C", form39valcd_c);
							actualform39cvalcd = 
									getAttribute(form39ValueCodeC, "value");
							assertEquals(actualform39cvalcd, actform39cvalcd, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcd+
									" not : "+actform39cvalcd);
							
							form39ValueCodeAmountC.clear();
							sendKeys(form39ValueCodeAmountC, "Form 39 C Value code amount", form39valcdamt_c);
							actualform39cvalcdamt = 
									getAttribute(form39ValueCodeAmountC, "value");
							assertEquals(actualform39cvalcdamt, form39valcdamt_c, "The Value "
									+ "code from Form 39 C is : "+actualform39cvalcdamt+
									" not : "+form39valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 39 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 C Code or Amount is empty or null");
			}
			
			//Form 39 D
			if(Objects.nonNull(form39valcd_d) && !form39valcd_d.equals("") && 
					Objects.nonNull(form39valcdamt_d) && !form39valcdamt_d.equals("")) {
				String actualform39dvalcdamt = 
						getAttribute(form39ValueCodeAmountD, "value");
				String actualform39dvalcd = 
						getAttribute(form39ValueCodeD, "value");
				String actform39dvalcd = form39valcd_d;
				if(actform39dvalcd.length()>2)
					actform39dvalcd = form39valcd_d.substring(0, 2);
				if(!actualform39dvalcd.equals(actform39dvalcd) && 
						!actualform39dvalcdamt.equals(form39valcdamt_d)) {
							form39ValueCodeD.clear();
							sendKeys(form39ValueCodeD, "Form 39 Value Code D", form39valcd_d);
							actualform39dvalcd = 
									getAttribute(form39ValueCodeD, "value");
							assertEquals(actualform39dvalcd, actform39dvalcd, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcd+
									" not : "+actform39dvalcd);
							
							form39ValueCodeAmountD.clear();
							sendKeys(form39ValueCodeAmountD, "Form 39 D Value code amount", form39valcdamt_d);
							actualform39dvalcdamt = 
									getAttribute(form39ValueCodeAmountD, "value");
							assertEquals(actualform39dvalcdamt, form39valcdamt_d, "The Value "
									+ "code from Form 39 D is : "+actualform39dvalcdamt+
									" not : "+form39valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 39 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 39 D Code or Amount is empty or null");
			}
			
			//Form 40 A
			if(Objects.nonNull(form40valcd_a) && !form40valcd_a.equals("") && 
					Objects.nonNull(form40valcdamt_a) && !form40valcdamt_a.equals("")) {
				String actualform40avalcdamt = 
						getAttribute(form40ValueCodeAmountA, "value");
				String actualform40avalcd = 
						getAttribute(form40ValueCodeA, "value");
				String actform40avalcd = form40valcd_a;
				if(actform40avalcd.length()>2)
					actform40avalcd = form40valcd_a.substring(0, 2);
				if(!actualform40avalcd.equals(actform40avalcd) && 
						!actualform40avalcdamt.equals(form40valcdamt_a)) {
							form40ValueCodeA.clear();
							sendKeys(form40ValueCodeA, "Form 40 Value Code A", form40valcd_a);
							actualform40avalcd = 
									getAttribute(form40ValueCodeA, "value");
							assertEquals(actualform40avalcd, actform40avalcd, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcd+
									" not : "+actform40avalcd);
							
							form40ValueCodeAmountA.clear();
							sendKeys(form40ValueCodeAmountA, "Form 40 A Value code amount", form40valcdamt_a);
							actualform40avalcdamt = 
									getAttribute(form40ValueCodeAmountA, "value");
							assertEquals(actualform40avalcdamt, form40valcdamt_a, "The Value "
									+ "code from Form 40 A is : "+actualform40avalcdamt+
									" not : "+form40valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 40 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 A Code or Amount is empty or null");
			}
			
			//Form 40 B
			if(Objects.nonNull(form40valcd_b) && !form40valcd_b.equals("") && 
					Objects.nonNull(form40valcdamt_b) && !form40valcdamt_b.equals("")) {
				String actualform40bvalcdamt = 
						getAttribute(form40ValueCodeAmountB, "value");
				String actualform40bvalcd = 
						getAttribute(form40ValueCodeB, "value");
				String actform40bvalcd = form40valcd_b;
				if(actform40bvalcd.length()>2)
					actform40bvalcd = form40valcd_b.substring(0, 2);
				if(!actualform40bvalcd.equals(actform40bvalcd) && 
						!actualform40bvalcdamt.equals(form40valcdamt_b)) {
							form40ValueCodeB.clear();
							sendKeys(form40ValueCodeB, "Form 40 Value Code B", form40valcd_b);
							actualform40bvalcd = 
									getAttribute(form40ValueCodeB, "value");
							assertEquals(actualform40bvalcd, actform40bvalcd, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcd+
									" not : "+actform40bvalcd);
							
							form40ValueCodeAmountB.clear();
							sendKeys(form40ValueCodeAmountB, "Form 40 B Value code amount", form40valcdamt_b);
							actualform40bvalcdamt = 
									getAttribute(form40ValueCodeAmountB, "value");
							assertEquals(actualform40bvalcdamt, form40valcdamt_b, "The Value "
									+ "code from Form 40 B is : "+actualform40bvalcdamt+
									" not : "+form40valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 40 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 B Code or Amount is empty or null");
			}
			
			//Form 40 C
			if(Objects.nonNull(form40valcd_c) && !form40valcd_c.equals("") && 
					Objects.nonNull(form40valcdamt_c) && !form40valcdamt_c.equals("")) {
				String actualform40cvalcdamt = 
						getAttribute(form40ValueCodeAmountC, "value");
				String actualform40cvalcd = 
						getAttribute(form40ValueCodeC, "value");
				String actform40cvalcd = form40valcd_a;
				if(actform40cvalcd.length()>2)
					actform40cvalcd = form40valcd_c.substring(0, 2);
				if(!actualform40cvalcd.equals(actform40cvalcd) && 
						!actualform40cvalcdamt.equals(form40valcdamt_c)) {
							form40ValueCodeC.clear();
							sendKeys(form40ValueCodeC, "Form 40 Value Code C", form40valcd_c);
							actualform40cvalcd = 
									getAttribute(form40ValueCodeC, "value");
							assertEquals(actualform40cvalcd, actform40cvalcd, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcd+
									" not : "+actform40cvalcd);
							
							form40ValueCodeAmountC.clear();
							sendKeys(form40ValueCodeAmountC, "Form 40 C Value code amount", form40valcdamt_c);
							actualform40cvalcdamt = 
									getAttribute(form40ValueCodeAmountC, "value");
							assertEquals(actualform40cvalcdamt, form40valcdamt_c, "The Value "
									+ "code from Form 40 C is : "+actualform40cvalcdamt+
									" not : "+form40valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 40 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 C Code or Amount is empty or null");
			}
			
			//Form 40 D
			if(Objects.nonNull(form40valcd_d) && !form40valcd_d.equals("") && 
					Objects.nonNull(form40valcdamt_d) && !form40valcdamt_d.equals("")) {
				String actualform40dvalcdamt = 
						getAttribute(form40ValueCodeAmountD, "value");
				String actualform40dvalcd = 
						getAttribute(form40ValueCodeD, "value");
				String actform40dvalcd = form40valcd_d;
				if(actform40dvalcd.length()>2)
					actform40dvalcd = form40valcd_d.substring(0, 2);
				if(!actualform40dvalcd.equals(actform40dvalcd) && 
						!actualform40dvalcdamt.equals(form40valcdamt_d)) {
							form40ValueCodeD.clear();
							sendKeys(form40ValueCodeD, "Form 40 Value Code D", form40valcd_d);
							actualform40dvalcd = 
									getAttribute(form40ValueCodeD, "value");
							assertEquals(actualform40dvalcd, actform40dvalcd, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcd+
									" not : "+actform40dvalcd);
							
							form40ValueCodeAmountD.clear();
							sendKeys(form40ValueCodeAmountD, "Form 40 D Value code amount", form40valcdamt_d);
							actualform40dvalcdamt = 
									getAttribute(form40ValueCodeAmountD, "value");
							assertEquals(actualform40dvalcdamt, form40valcdamt_d, "The Value "
									+ "code from Form 40 D is : "+actualform40dvalcdamt+
									" not : "+form40valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 40 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 40 D Code or Amount is empty or null");
			}
			
			//Form 41 A
			if(Objects.nonNull(form41valcd_a) && !form41valcd_a.equals("") && 
					Objects.nonNull(form41valcdamt_a) && !form41valcdamt_a.equals("")) {
				String actualform41avalcdamt = 
						getAttribute(form41ValueCodeAmountA, "value");
				String actualform41avalcd = 
						getAttribute(form41ValueCodeA, "value");
				String actform41avalcd = form41valcd_a;
				if(actform41avalcd.length()>2)
					actform41avalcd = form41valcd_a.substring(0, 2);
				if(!actualform41avalcd.equals(actform41avalcd) && 
						!actualform41avalcdamt.equals(form41valcdamt_a)) {
							form41ValueCodeA.clear();
							sendKeys(form41ValueCodeA, "Form 41 Value Code A", form41valcd_a);
							actualform41avalcd = 
									getAttribute(form41ValueCodeA, "value");
							assertEquals(actualform41avalcd, actform41avalcd, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcd+
									" not : "+actform41avalcd);
							
							form41ValueCodeAmountA.clear();
							sendKeys(form41ValueCodeAmountA, "Form 41 A Value code amount", form41valcdamt_a);
							actualform41avalcdamt = 
									getAttribute(form41ValueCodeAmountA, "value");
							assertEquals(actualform41avalcdamt, form41valcdamt_a, "The Value "
									+ "code from Form 41 A is : "+actualform41avalcdamt+
									" not : "+form41valcdamt_a);
				}else {
					report(LogStatus.INFO, "Form 41 A Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 A Code or Amount is empty or null");
			}
			
			//Form 41 B
			if(Objects.nonNull(form41valcd_b) && !form41valcd_b.equals("") && 
					Objects.nonNull(form41valcdamt_b) && !form41valcdamt_b.equals("")) {
				String actualform41bvalcdamt = 
						getAttribute(form41ValueCodeAmountB, "value");
				String actualform41bvalcd = 
						getAttribute(form41ValueCodeB, "value");
				String actform41bvalcd = form41valcd_b;
				if(actform41bvalcd.length()>2)
					actform41bvalcd = form41valcd_b.substring(0, 2);
				if(!actualform41bvalcd.equals(actform41bvalcd) && 
						!actualform41bvalcdamt.equals(form41valcdamt_b)) {
							form41ValueCodeB.clear();
							sendKeys(form41ValueCodeB, "Form 41 Value Code B", form41valcd_b);
							actualform41bvalcd = 
									getAttribute(form41ValueCodeB, "value");
							assertEquals(actualform41bvalcd, actform41bvalcd, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcd+
									" not : "+actform41bvalcd);
							
							form41ValueCodeAmountB.clear();
							sendKeys(form41ValueCodeAmountB, "Form 41 B Value code amount", form41valcdamt_b);
							actualform41bvalcdamt = 
									getAttribute(form41ValueCodeAmountB, "value");
							assertEquals(actualform41bvalcdamt, form41valcdamt_b, "The Value "
									+ "code from Form 41 B is : "+actualform41bvalcdamt+
									" not : "+form41valcdamt_b);
				}else {
					report(LogStatus.INFO, "Form 41 B Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 B Code or Amount is empty or null");
			}
			
			//Form 41 C
			if(Objects.nonNull(form41valcd_c) && !form41valcd_c.equals("") && 
					Objects.nonNull(form41valcdamt_c) && !form41valcdamt_c.equals("")) {
				String actualform41cvalcdamt = 
						getAttribute(form41ValueCodeAmountC, "value");
				String actualform41cvalcd = 
						getAttribute(form41ValueCodeC, "value");
				String actform41cvalcd = form41valcd_a;
				if(actform41cvalcd.length()>2)
					actform41cvalcd = form41valcd_c.substring(0, 2);
				if(!actualform41cvalcd.equals(actform41cvalcd) && 
						!actualform41cvalcdamt.equals(form41valcdamt_c)) {
							form41ValueCodeC.clear();
							sendKeys(form41ValueCodeC, "Form 41 Value Code C", form41valcd_c);
							actualform41cvalcd = 
									getAttribute(form41ValueCodeC, "value");
							assertEquals(actualform41cvalcd, actform41cvalcd, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcd+
									" not : "+actform41cvalcd);
							
							form41ValueCodeAmountC.clear();
							sendKeys(form41ValueCodeAmountC, "Form 41 C Value code amount", form41valcdamt_c);
							actualform41cvalcdamt = 
									getAttribute(form41ValueCodeAmountC, "value");
							assertEquals(actualform41cvalcdamt, form41valcdamt_c, "The Value "
									+ "code from Form 41 C is : "+actualform41cvalcdamt+
									" not : "+form41valcdamt_c);
				}else {
					report(LogStatus.INFO, "Form 41 C Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 C Code or Amount is empty or null");
			}
			
			//Form 41 D
			if(Objects.nonNull(form41valcd_d) && !form41valcd_d.equals("") && 
					Objects.nonNull(form41valcdamt_d) && !form41valcdamt_d.equals("")) {
				String actualform41dvalcdamt = 
						getAttribute(form41ValueCodeAmountD, "value");
				String actualform41dvalcd = 
						getAttribute(form41ValueCodeD, "value");
				String actform41dvalcd = form41valcd_d;
				if(actform41dvalcd.length()>2)
					actform41dvalcd = form41valcd_d.substring(0, 2);
				if(!actualform41dvalcd.equals(actform41dvalcd) && 
						!actualform41dvalcdamt.equals(form41valcdamt_d)) {
							form41ValueCodeD.clear();
							sendKeys(form41ValueCodeD, "Form 41 Value Code D", form41valcd_d);
							actualform41dvalcd = 
									getAttribute(form41ValueCodeD, "value");
							assertEquals(actualform41dvalcd, actform41dvalcd, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcd+
									" not : "+actform41dvalcd);
							
							form41ValueCodeAmountD.clear();
							sendKeys(form41ValueCodeAmountD, "Form 41 D Value code amount", form41valcdamt_d);
							actualform41dvalcdamt = 
									getAttribute(form41ValueCodeAmountD, "value");
							assertEquals(actualform41dvalcdamt, form41valcdamt_d, "The Value "
									+ "code from Form 41 D is : "+actualform41dvalcdamt+
									" not : "+form41valcdamt_d);
				}else {
					report(LogStatus.INFO, "Form 41 D Code or Amount is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Form 41 D Code or Amount is empty or null");
			}
			
			if(Objects.nonNull(serviceLineNumber) && !serviceLineNumber.equals("")) {
				String numberOfLines = serviceLine.replace("[XX]", "");
				int lineCount= driver.findElements(By.xpath(numberOfLines)).size();
				if(Integer.parseInt(serviceLineNumber) <= lineCount) {
					String lineNumber = serviceLine.replace("XX", serviceLineNumber);
					WebElement serviceLine = driver.findElement(By.xpath(lineNumber));
					serviceLine.click();
					if(modifyService(revcd, pccd, srvcdt, noofunits, charges, 
							noncoverdcharges)) {
						report(LogStatus.PASS,"Service modified successfully.");
					}else {
						report(LogStatus.FAIL,"Service not modified.");
					}
				}else {
					report(LogStatus.INFO,"The Service line numner is not valid");
				}
				
			}else {
				report(LogStatus.WARNING, "Service Line Number is empty or null");
			}
			
			//Previous Payer
			
			//Health Plan ID A
			if(Objects.nonNull(healthplanid_a) && !healthplanid_a.equals("")) {
				String actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
				if(!actualHealthPlanIDA.equals(healthplanid_a)) {
					healthPlanIDA.clear();
					sendKeys(healthPlanIDA, "Health Plan ID A", healthplanid_a);
					actualHealthPlanIDA = getAttribute(healthPlanIDA, "value");
					assertEquals(actualHealthPlanIDA, healthplanid_a, "The Health Plan ID A from"
							+ " field is : "+actualHealthPlanIDA+" not : "+healthplanid_a);
				}else {
					report(LogStatus.INFO,"Health Plan ID A numner is not valid");
				}						
			}else {
				report(LogStatus.WARNING, "Health Plan ID A is empty or null");
			}

			
			//REL INFO A
			if(Objects.nonNull(relinfo_a) && !relinfo_a.equals("")) {
				String relInfoAClass = getAttribute(relInfoCheckBoxA, "class");
				String[] relInfoAData = relInfoAClass.split(" ");
				String actualRelInfoA = "";
				for(String s : relInfoAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualRelInfoA = s;
					}
				}
				if(actualRelInfoA.equals("") && relinfo_a.equals("YES")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box checked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not checked");
					}
					
				}else if(actualRelInfoA.equals("mat-checkbox-checked") && 
						relinfo_a.equals("NO")) {
					click(relInfoCheckBoxA, "Rel Info Checkbox A");
					actualRelInfoA = getAttribute(relInfoCheckBoxA, "class");
					if(!actualRelInfoA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Actual Rel Info check box unchecked");
					}else {
						report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
					}
					
				}
			}
			
			//ASN BEN A
			if(Objects.nonNull(benfitassignment_a) && 
					!benfitassignment_a.equals("")) {
				String benfitAssignmentAClass = getAttribute(beneftAssignmentCheckboxA, "class");
				String[] benfitAssignmentAData = benfitAssignmentAClass.split(" ");
				String actualBenfitAssignmentA = "";
				for(String s : benfitAssignmentAData) {
					if(s.equals("mat-checkbox-checked")) {
						actualBenfitAssignmentA = s;
					}
				}
				if(actualBenfitAssignmentA.equals("") && 
						benfitassignment_a.equals("YES")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box checked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not checked");
					}
					
				}else if(actualBenfitAssignmentA.equals("mat-checkbox-checked") && 
						benfitassignment_a.equals("NO")) {
					click(beneftAssignmentCheckboxA, "Benefit Assignment Checkbox A");
					actualBenfitAssignmentA = getAttribute(beneftAssignmentCheckboxA, "class");
					if(!actualBenfitAssignmentA.contains("mat-checkbox-checked")) {
						report(LogStatus.PASS, "Benefit Assignment check box unchecked");
					}else {
						report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
					}
					
				}
			}
			
			//Prior payment amount
			if(Objects.nonNull(priorpaymentamt_a) && 
					!priorpaymentamt_a.equals("")) {
				priorPaymentAmountA.clear();
				sendKeys(priorPaymentAmountA, "Prior Payment Amount A", priorpaymentamt_a);
				String actualPriorPaymentAmountA = getAttribute(priorPaymentAmountA, "value");
				assertEquals(actualPriorPaymentAmountA, priorpaymentamt_a, "The amount "
						+"from prior payment amount field A is : "+actualPriorPaymentAmountA+
						" not : "+priorpaymentamt_a);
			}
			
			//EST Due Amount
			if(Objects.nonNull(estamountdue_a) && 
					!estamountdue_a.equals("")) {
				estAmountDueA.clear();
				sendKeys(estAmountDueA, "EST Due Amount A", estamountdue_a);
				String actualESTAmountDueA= getAttribute(estAmountDueA, "value");
				assertEquals(actualESTAmountDueA, estamountdue_a, "The amount "
						+"from prior payment amount field A is : "+actualESTAmountDueA+
						" not : "+estamountdue_a);
			}
			
			//primary payer
			if(Objects.nonNull(payertype_a) && !payertype_a.equals("")) {
				click(payerTypeDrodownA, "Payer Type");
				switch(payertype_a) {
				case "MEDICARE":{
					click(payerTypeMedicareOption, "MEDICARE");
					break;
				}
				case "NON MEDICARE":{
					click(payerTypeNonMedicareOption, "Non - MEDICARE");
					break;
				}
				default:
					report(LogStatus.WARNING, "Payer Type is not valid");
					
				}
			}
			
			//Additional Previous payer
			if(Objects.nonNull(noOfPreviousPayer) && 
					!noOfPreviousPayer.equals("")) {
				if(Integer.parseInt(noOfPreviousPayer) > 1) {
					for(int i = 1; i<=2; i++) {
						if(addPreviousPayerButton.isEnabled())
							click(addPreviousPayerButton, "Add previouss Payer");
					}
					if(Integer.parseInt(noOfPreviousPayer) == 2) {
						driver.findElement(By.xpath("(//span[contains"
								+ "(text(),'Remove')]/parent::button)[2]")).click();
						waitUntilClickable(unsavedChagesOK, 10);
						click(unsavedChagesOK, "OK");
						modifyPrimaryPayeB(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b);
					}else if(Integer.parseInt(noOfPreviousPayer) == 3) {
						modifyPrimaryPayeBAndC(payer_b, healthplanid_b, relinfo_b, 
								benfitassignment_b, priorpaymentamt_b, estamountdue_b, 
								payertype_b, payer_c, healthplanid_c, relinfo_c, 
								benfitassignment_c, priorpaymentamt_c, estamountdue_c, 
								payertype_c);
						
					}
				}else {
					for(int i = 1 ; i <= 2 ; i++) {
						try {
							driver.findElement(By.xpath("(//span[contains"
									+ "(text(),'Remove')]/parent::button)[2]")).click();
							waitUntilClickable(unsavedChagesOK, 10);
							click(unsavedChagesOK, "OK");
						}catch (NoSuchElementException e) {
							break;
						}
					}
				}
			}else {
				report(LogStatus.INFO,"No Of previous payer is empty or null");
			}
			
			//Billing Provider NPI
			if(Objects.nonNull(billingprvid) && !billingprvid.equals("")) {
				click(billingProviderNPIDropdown, "Billing Provider NPI");
				String billingnpi = dropdownOptions.replace("XX", billingprvid);
				WebElement billingNPIElement = driver.findElement(By.xpath(billingnpi));
				click(billingNPIElement, billingprvid);
				String actualBillingNPI = getText(billingNPIText);
				assertEquals(actualBillingNPI, billingprvid, "The NPI from field is:  "
						+ actualBillingNPI+ " not : "+billingprvid);
			}else {
				report(LogStatus.WARNING, "Billing Provider NPI is empty or null");
			}
			
			//Other Provider ID
			if(Objects.nonNull(othrprvid) && !othrprvid.equals("")) {
				String actualOtherProviderID = getAttribute(otherProviderID, "value");
				if(!actualOtherProviderID.equals(othrprvid)) {
					otherProviderID.clear();
					sendKeys(otherProviderID, "Other Provider ID", othrprvid);
					actualOtherProviderID = getAttribute(otherProviderID, "value");
					assertEquals(actualOtherProviderID, othrprvid, "The value from other"
							+ " Provider ID field is : "+actualOtherProviderID+" not "
									+ othrprvid);
				}else {
					report(LogStatus.INFO,"Other Provider ID is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Other Provider ID is empty or null");
			}
			
			//Insured Name A
			if(Objects.nonNull(insrdname_a) && !insrdname_a.equals("")) {
				String actualInsuredNameA = getAttribute(insuredNameA, "value");
				if(!actualInsuredNameA.equals(insrdname_a)) {
					insuredNameA.clear();
					sendKeys(insuredNameA, "Insured Name A", insrdname_a);
					actualInsuredNameA = getAttribute(insuredNameA, "value");
					assertEquals(actualInsuredNameA, insrdname_a, "The value from "
							+ "Insured Name A is : "+actualInsuredNameA+" not "+insrdname_a);
				}else {
					report(LogStatus.INFO, "Insured Name A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name A is empty or null");
			}
			
			//Insured Name B
			if(Objects.nonNull(insrdname_b) && !insrdname_b.equals("")) {
				String actualInsuredNameB = getAttribute(insuredNameB, "value");
				if(!actualInsuredNameB.equals(insrdname_b)) {
					insuredNameB.clear();
					sendKeys(insuredNameB, "Insured Name B", insrdname_b);
					actualInsuredNameB = getAttribute(insuredNameB, "value");
					assertEquals(actualInsuredNameB, insrdname_b, "The value from "
							+ "Insured Name B is : "+actualInsuredNameB+" not "+insrdname_b);
				}else {
					report(LogStatus.INFO, "Insured Name B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Insured Name B is empty or null");
			}
			
			//Insured Name C
			if(Objects.nonNull(insrdname_c) && !insrdname_c.equals("")) {
				String actualInsuredNameC = getAttribute(insuredNameC, "value");
				if(!actualInsuredNameC.equals(insrdname_c)) {
					insuredNameC.clear();
					sendKeys(insuredNameC, "Insured Name C", insrdname_c);
					actualInsuredNameC = getAttribute(insuredNameC, "value");
					assertEquals(actualInsuredNameC, insrdname_c, "The value from "
							+ "Insured Name C is : "+actualInsuredNameC+" not "+insrdname_c);
				}else {
					report(LogStatus.INFO, "Insured Name C is same and not changed");
				}
			
			}else {
				report(LogStatus.WARNING, "Insured Name C is empty or null");
			}
			
			//Patient related to Insured A
			if(Objects.nonNull(patreltoinsure_a) && !patreltoinsure_a.equals("")) {
				String actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
						"value");
				if(!actualPatRelInsuredA.equals(patreltoinsure_a)) {
					patientRelatedToInsuranceA.clear();
					sendKeys(patientRelatedToInsuranceA, "Patient related to Insured A", 
							patreltoinsure_a);
					actualPatRelInsuredA = getAttribute(patientRelatedToInsuranceA, 
							"value");
					if(patreltoinsure_a.length()>2)
						assertEquals(actualPatRelInsuredA, patreltoinsure_a.substring(0, 2), "The value from "
							+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
							patreltoinsure_a);
					else
						assertEquals(actualPatRelInsuredA, patreltoinsure_a, "The value from "
								+ "Insured Name A is : "+actualPatRelInsuredA+" not "+
								patreltoinsure_a);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure A is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure A is empty or null");
			}
			
			//Patient related to Insured B
			if(Objects.nonNull(patreltoinsure_b) && !patreltoinsure_b.equals("")) {
				String actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
						"value");
				if(!actualPatRelInsuredB.equals(patreltoinsure_b)) {
					patientRelatedToInsuranceB.clear();
					sendKeys(patientRelatedToInsuranceB, "Patient related to Insured B",
							patreltoinsure_b);
					actualPatRelInsuredB = getAttribute(patientRelatedToInsuranceB,
							"value");
					if(patreltoinsure_b.length()>2)
						assertEquals(actualPatRelInsuredB, patreltoinsure_b.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
							patreltoinsure_b);
					else
						assertEquals(actualPatRelInsuredB, patreltoinsure_b, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredB+" not "+
								patreltoinsure_b);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure B is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure B is empty or null");
			}
			
			//Patient related to Insured C
			if(Objects.nonNull(patreltoinsure_c) && !patreltoinsure_c.equals("")) {
				String actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
						"value");
				if(!actualPatRelInsuredC.equals(patreltoinsure_c)) {
					patientRelatedToInsuranceC.clear();
					sendKeys(patientRelatedToInsuranceC, "Patient related to Insured C",
							patreltoinsure_c);
					actualPatRelInsuredC = getAttribute(patientRelatedToInsuranceC,
							"value");
					if(patreltoinsure_c.length()>2)
						assertEquals(actualPatRelInsuredC, patreltoinsure_c.substring(0, 2), "The value from "
							+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
							patreltoinsure_c);
					else
						assertEquals(actualPatRelInsuredC, patreltoinsure_c, "The value from "
								+ "Insured Name B is : "+actualPatRelInsuredC+" not "+
								patreltoinsure_c);
				}else {
					report(LogStatus.INFO, "Patient Related To Insure C is same and not changed");
				}
			}else {
				report(LogStatus.WARNING, "Patient Related To Insure C is empty or null");
			}
			
			//Insured Unique ID A
			if(Objects.nonNull(insuredunqid_a) && !insuredunqid_a.equals("")) {
				String actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
						"value");
				if(!actualInsuredUniqueIDA.equals(insuredunqid_a)) {
					insuredsUniqueIDA.clear();
					sendKeys(insuredsUniqueIDA, "Insured Unique ID A", 
							insuredunqid_a);
					actualInsuredUniqueIDA = getAttribute(insuredsUniqueIDA, 
							"value");
					assertEquals(actualInsuredUniqueIDA, insuredunqid_a, "The value from "
							+ "Insured Name A is : "+actualInsuredUniqueIDA+" not "+
							insuredunqid_a);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID A is empty or null.");
			}
			
			//Insured Unique ID B
			if(Objects.nonNull(insuredunqid_b) && !insuredunqid_b.equals("")) {
				String actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
						"value");
				if(!actualInsuredUniqueIDB.equals(insuredunqid_b)) {
					insuredsUniqueIDB.clear();
					sendKeys(insuredsUniqueIDB, "Insured Unique ID B", 
							insuredunqid_b);
					actualInsuredUniqueIDB = getAttribute(insuredsUniqueIDB, 
							"value");
					assertEquals(actualInsuredUniqueIDB, insuredunqid_b, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDB+" not "+
							insuredunqid_b);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID B is empty or null.");
			}
			
			//Insured Unique ID C
			if(Objects.nonNull(insuredunqid_c) && !insuredunqid_c.equals("")) {
				String actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
						"value");
				if(!actualInsuredUniqueIDC.equals(insuredunqid_c)) {
					insuredsUniqueIDC.clear();
					sendKeys(insuredsUniqueIDC, "Insured Unique ID C", 
							insuredunqid_c);
					actualInsuredUniqueIDC = getAttribute(insuredsUniqueIDC, 
							"value");
					assertEquals(actualInsuredUniqueIDC, insuredunqid_c, "The value from "
							+ "Insured Name B is : "+actualInsuredUniqueIDC+" not "+
							insuredunqid_c);
				}else {
					report(LogStatus.INFO, "The Insured Unique ID C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Unique ID C is empty or null.");
			}
			
			//Insured Group Name A
			if(Objects.nonNull(insrdgrpnm_a) && !insrdgrpnm_a.equals("")) {
				String actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
						"value");
				if(!actualInsuredGroupNameA.equals(insrdgrpnm_a)) {
					insuredGroupNameA.clear();
					sendKeys(insuredGroupNameA, "Insured Unique ID A", 
							insrdgrpnm_a);
					actualInsuredGroupNameA = getAttribute(insuredGroupNameA, 
							"value");
					assertEquals(actualInsuredGroupNameA, insrdgrpnm_a, "The value from "
							+ "Insured Name A is : "+actualInsuredGroupNameA+" not "+
							insrdgrpnm_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Name A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name A is empty or null.");
			}
			
			//Insured Group Name B
			if(Objects.nonNull(insrdgrpnm_b) && !insrdgrpnm_b.equals("")) {
				String actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
						"value");
				if(!actualInsuredGroupNameB.equals(insrdgrpnm_b)) {
					insuredGroupNameB.clear();
					sendKeys(insuredGroupNameB, "Insured Unique ID B", 
							insrdgrpnm_b);
					actualInsuredGroupNameB = getAttribute(insuredGroupNameB, 
							"value");
					assertEquals(actualInsuredGroupNameB, insrdgrpnm_b, "The value from "
							+ "Insured Name B is : "+actualInsuredGroupNameB+" not "+
							insrdgrpnm_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Name B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name B is empty or null.");
			}
			
			//Insured Group Name C
			if(Objects.nonNull(insrdgrpnm_c) && !insrdgrpnm_c.equals("")) {
				String actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
						"value");
				if(!actualInsuredGroupNameC.equals(insrdgrpnm_c)) {
					insuredGroupNameC.clear();
					sendKeys(insuredGroupNameC, "Insured Unique ID C", 
							insrdgrpnm_c);
					actualInsuredGroupNameC = getAttribute(insuredGroupNameC, 
							"value");
					assertEquals(actualInsuredGroupNameC, insrdgrpnm_c, "The value from "
							+ "Insured Name C is : "+actualInsuredGroupNameC+" not "+
							insrdgrpnm_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Name C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Name C is empty or null.");
			}
			
			//Insured Group Number A
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
						"value");
				if(!actualInsuredGroupNumberA.equals(insrdgrpno_a)) {
					insuredGroupNumberA.clear();
					sendKeys(insuredGroupNumberA, "Insured Unique ID A", 
							insrdgrpno_a);
					actualInsuredGroupNumberA = getAttribute(insuredGroupNumberA, 
							"value");
					assertEquals(actualInsuredGroupNumberA, insrdgrpno_a, "The value from "
							+ "Insured Number A is : "+actualInsuredGroupNumberA+" not "+
							insrdgrpno_a);
				}else {
					report(LogStatus.INFO, "The Insured Group Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number A is empty or null.");
			}
			
			//Insured Group Number B
			if(Objects.nonNull(insrdgrpno_a) && !insrdgrpno_a.equals("")) {
				String actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
						"value");
				if(!actualInsuredGroupNumberB.equals(insrdgrpno_a)) {
					insuredGroupNumberB.clear();
					sendKeys(insuredGroupNumberB, "Insured Unique ID B", 
							insrdgrpno_b);
					actualInsuredGroupNumberB = getAttribute(insuredGroupNumberB, 
							"value");
					assertEquals(actualInsuredGroupNumberB, insrdgrpno_b, "The value from "
							+ "Insured Number B is : "+actualInsuredGroupNumberB+" not "+
							insrdgrpno_b);
				}else {
					report(LogStatus.INFO, "The Insured Group Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number B is empty or null.");
			}
			
			//Insured Group Number C
			if(Objects.nonNull(insrdgrpno_c) && !insrdgrpno_c.equals("")) {
				String actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
						"value");
				if(!actualInsuredGroupNumberC.equals(insrdgrpno_c)) {
					insuredGroupNumberC.clear();
					sendKeys(insuredGroupNumberC, "Insured Unique ID C", 
							insrdgrpno_c);
					actualInsuredGroupNumberC = getAttribute(insuredGroupNumberC, 
							"value");
					assertEquals(actualInsuredGroupNumberC, insrdgrpno_c, "The value from "
							+ "Insured Number C is : "+actualInsuredGroupNumberC+" not "+
							insrdgrpno_c);
				}else {
					report(LogStatus.INFO, "The Insured Group Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Insured Group Number C is empty or null.");
			}
			
			//Treatment Authorization code A
			if(Objects.nonNull(txauthcd_a) && !txauthcd_a.equals("")) {
				String actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
						"value");
				if(!actualTreatmentAuthCodeA.equals(txauthcd_a)) {
					treatmentAuthCodesA.clear();
					sendKeys(treatmentAuthCodesA, "Auth Code A", txauthcd_a);
					actualTreatmentAuthCodeA = getAttribute(treatmentAuthCodesA, 
							"value");
					assertEquals(actualTreatmentAuthCodeA, txauthcd_a, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeA+" not : "+txauthcd_a);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code A is empty or null.");
			}
			
			//Treatment Authorization code B
			if(Objects.nonNull(txauthcd_b) && !txauthcd_b.equals("")) {
				String actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
						"value");
				if(!actualTreatmentAuthCodeB.equals(txauthcd_b)) {
					treatmentAuthCodesB.clear();
					sendKeys(treatmentAuthCodesB, "Auth Code B", txauthcd_b);
					actualTreatmentAuthCodeB = getAttribute(treatmentAuthCodesB, 
							"value");
					assertEquals(actualTreatmentAuthCodeB, txauthcd_b, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeB+" not : "+txauthcd_b);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code B is empty or null.");
			}
			
			//Treatment Authorization code C
			if(Objects.nonNull(txauthcd_c) && !txauthcd_c.equals("")) {
				String actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
						"value");
				if(!actualTreatmentAuthCodeC.equals(txauthcd_c)) {
					treatmentAuthCodesC.clear();
					sendKeys(treatmentAuthCodesC, "Auth Code C", txauthcd_c);
					actualTreatmentAuthCodeC = getAttribute(treatmentAuthCodesC, 
							"value");
					assertEquals(actualTreatmentAuthCodeC, txauthcd_c, "The Auth code from "
							+ "field is : "+actualTreatmentAuthCodeC+" not : "+txauthcd_c);
				}else {
					report(LogStatus.INFO, "The Treatment Authorization code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Treatment Authorization code C is empty or null.");
			}
			
			//Document Control Number A
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
						"value");
				if(!actualResubmissionClaimA.equals(refclm_a)) {
					resubmissionClaimNumberA.clear();
					sendKeys(resubmissionClaimNumberA, "Document Control Number A", refclm_a);
					actualResubmissionClaimA = getAttribute(resubmissionClaimNumberA, 
							"value");
					assertEquals(actualResubmissionClaimA, refclm_a, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimA+" not : "+refclm_a);
				}else {
					report(LogStatus.INFO, "The Document Control Number A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number A is empty or null.");
			}
			
			//Document Control Number B
			if(Objects.nonNull(refclm_a) && !refclm_a.equals("")) {
				String actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
						"value");
				if(!actualResubmissionClaimB.equals(refclm_a)) {
					resubmissionClaimNumberB.clear();
					sendKeys(resubmissionClaimNumberB, "Document Control Number A", refclm_b);
					actualResubmissionClaimB = getAttribute(resubmissionClaimNumberB, 
							"value");
					assertEquals(actualResubmissionClaimB, refclm_b, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimB+" not : "+refclm_b);
				}else {
					report(LogStatus.INFO, "The Document Control Number B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number B is empty or null.");
			}
			
			//Document Control Number C
			if(Objects.nonNull(refclm_c) && !refclm_c.equals("")) {
				String actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
						"value");
				if(!actualResubmissionClaimC.equals(refclm_c)) {
					resubmissionClaimNumberC.clear();
					sendKeys(resubmissionClaimNumberC, "Document Control Number C", refclm_c);
					actualResubmissionClaimC = getAttribute(resubmissionClaimNumberC, 
							"value");
					assertEquals(actualResubmissionClaimC, refclm_c, "The Document Control Number from "
							+ "field is : "+actualResubmissionClaimC+" not : "+refclm_c);
				}else {
					report(LogStatus.INFO, "The Document Control Number C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Document Control Number C is empty or null.");
			}
			
			//Employer A
			if(Objects.nonNull(empnm_a) && !empnm_a.equals("")) {
				String actualEmployerNameA = getAttribute(employerNameA, 
						"value");
				if(!actualEmployerNameA.equals(empnm_a)) {
					employerNameA.clear();
					sendKeys(employerNameA, "Employer Name A", empnm_a);
					actualEmployerNameA = getAttribute(employerNameA, 
							"value");
					assertEquals(actualEmployerNameA, empnm_a, "The Eployer Name from "
							+ "field is : "+actualEmployerNameA+" not : "+empnm_a);
				}else {
					report(LogStatus.INFO, "The Employer A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer A is empty or null.");
			}
			
			//Employer B
			if(Objects.nonNull(empnm_b) && !empnm_b.equals("")) {
				String actualEmployerNameB = getAttribute(employerNameB, 
						"value");
				if(!actualEmployerNameB.equals(empnm_b)) {
					employerNameB.clear();
					sendKeys(employerNameB, "Employer Name B", empnm_b);
					actualEmployerNameB = getAttribute(employerNameB, 
							"value");
					assertEquals(actualEmployerNameB, empnm_b, "The Eployer Name from "
							+ "field is : "+actualEmployerNameB+" not : "+empnm_b);
				}else {
					report(LogStatus.INFO, "The Employer B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer B is empty or null.");
			}
			
			//Employer C
			if(Objects.nonNull(empnm_c) && !empnm_c.equals("")) {
				String actualEmployerNameC = getAttribute(employerNameC, 
						"value");
				if(!actualEmployerNameC.equals(empnm_c)) {
					employerNameC.clear();
					sendKeys(employerNameC, "Employer Name C", empnm_c);
					actualEmployerNameC = getAttribute(employerNameC, 
							"value");
					assertEquals(actualEmployerNameC, empnm_c, "The Eployer Name from "
							+ "field is : "+actualEmployerNameC+" not : "+empnm_c);
				}else {
					report(LogStatus.INFO, "The Employer C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING, "The Employer C is empty or null.");
			}
			
			//Diagnosis Version
			if(Objects.nonNull(diagversion) && !diagversion.equals("")) {
				click(diagnosisVersion, "Diagnosis Version");
				String diagvrsn = dropdownOptions.replace("XX", diagversion);
				WebElement diagnosisVersionElement = driver.findElement(By.xpath(diagvrsn));
				click(diagnosisVersionElement, "Diagnosis version");
			}else {
				report(LogStatus.WARNING, "Diagnosis Version is empty or null.");
			}
			
			//Principal Diagnosis
			if(Objects.nonNull(principaldiag) && !principaldiag.equals("")) {
				String actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
						"value");
				if(!actualPrincipalDiagnosis.equals(principaldiag)) {
					principalDIagnosis.clear();
					sendKeys(principalDIagnosis, "Principal Diagnosis", principaldiag);
					waitForLoadingToDisappear();
					actualPrincipalDiagnosis = getAttribute(principalDIagnosis,
							"value");
					assertEquals(actualPrincipalDiagnosis, principaldiag, "The Diagnosis "
							+ "code from field is : "+actualPrincipalDiagnosis + " not : "
							+principaldiag);
				}else {
					report(LogStatus.INFO, "The Principal Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The principal diagnosis is empty or null");
			}
			
			//Other Diagnosis A
			if(Objects.nonNull(othrdiag_a) && !othrdiag_a.equals("")) {
				String actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
						"value");
				if(!actualOtherDiagnosisA.equals(othrdiag_a)) {
					otherDiagnosisA.clear();
					sendKeys(otherDiagnosisA, "Other Diagnosis A", othrdiag_a);
					waitForLoadingToDisappear();
					actualOtherDiagnosisA = getAttribute(otherDiagnosisA,
							"value");
					assertEquals(actualOtherDiagnosisA, othrdiag_a, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisA + " not : "
							+othrdiag_a);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis A is empty or null");
			}
			
			//Other Diagnosis B
			if(Objects.nonNull(othrdiag_b) && !othrdiag_b.equals("")) {
				String actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
						"value");
				if(!actualOtherDiagnosisB.equals(othrdiag_b)) {
					otherDiagnosisB.clear();
					sendKeys(otherDiagnosisB, "Other Diagnosis B", othrdiag_b);
					waitForLoadingToDisappear();
					actualOtherDiagnosisB = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisB, othrdiag_b, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisB + " not : "
							+othrdiag_b);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis B is empty or null");
			}
			
			//Other Diagnosis C
			if(Objects.nonNull(othrdiag_c) && !othrdiag_c.equals("")) {
				String actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
						"value");
				if(!actualOtherDiagnosisC.equals(othrdiag_c)) {
					otherDiagnosisC.clear();
					sendKeys(otherDiagnosisC, "Other Diagnosis C", othrdiag_c);
					waitForLoadingToDisappear();
					actualOtherDiagnosisC = getAttribute(otherDiagnosisC,
							"value");
					assertEquals(actualOtherDiagnosisC, othrdiag_c, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisC + " not : "
							+othrdiag_c);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis C is empty or null");
			}
			
			//Other Diagnosis D
			if(Objects.nonNull(othrdiag_d) && !othrdiag_d.equals("")) {
				String actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
						"value");
				if(!actualOtherDiagnosisD.equals(othrdiag_d)) {
					otherDiagnosisD.clear();
					sendKeys(otherDiagnosisD, "Other Diagnosis D", othrdiag_d);
					waitForLoadingToDisappear();
					actualOtherDiagnosisD = getAttribute(otherDiagnosisD,
							"value");
					assertEquals(actualOtherDiagnosisD, othrdiag_d, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisD + " not : "
							+othrdiag_d);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis D is empty or null");
			}
			
			//Other Diagnosis E
			if(Objects.nonNull(othrdiag_e) && !othrdiag_e.equals("")) {
				String actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
						"value");
				if(!actualOtherDiagnosisE.equals(othrdiag_e)) {
					otherDiagnosisE.clear();
					sendKeys(otherDiagnosisE, "Other Diagnosis E", othrdiag_e);
					waitForLoadingToDisappear();
					actualOtherDiagnosisE = getAttribute(otherDiagnosisE,
							"value");
					assertEquals(actualOtherDiagnosisE, othrdiag_e, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisE + " not : "
							+othrdiag_e);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis E is empty or null");
			}
			
			//Other Diagnosis F
			if(Objects.nonNull(othrdiag_f)&& !othrdiag_f.equals("")) {
				String actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
						"value");
				if(!actualOtherDiagnosisF.equals(othrdiag_f)) {
					otherDiagnosisF.clear();
					sendKeys(otherDiagnosisF, "Other Diagnosis F", othrdiag_f);
					waitForLoadingToDisappear();
					actualOtherDiagnosisF = getAttribute(otherDiagnosisF,
							"value");
					assertEquals(actualOtherDiagnosisF, othrdiag_f, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisF + " not : "
							+othrdiag_f);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis F is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis F is empty or null");
			}
			
			//Other Diagnosis G
			if(Objects.nonNull(othrdiag_g) && !othrdiag_g.equals("")) {
				String actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
						"value");
				if(!actualOtherDiagnosisG.equals(othrdiag_g)) {
					otherDiagnosisG.clear();
					sendKeys(otherDiagnosisG, "Other Diagnosis G", othrdiag_g);
					waitForLoadingToDisappear();
					actualOtherDiagnosisG = getAttribute(otherDiagnosisG,
							"value");
					assertEquals(actualOtherDiagnosisG, othrdiag_g, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisG + " not : "
							+othrdiag_g);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis G is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis G is empty or null");
			}
			
			//Other Diagnosis H
			if(Objects.nonNull(othrdiag_h) && !othrdiag_h.equals("")) {
				String actualOtherDiagnosisH = getAttribute(otherDiagnosisH,
						"value");
				if(!actualOtherDiagnosisH.equals(othrdiag_h)) {
					otherDiagnosisH.clear();
					sendKeys(otherDiagnosisH, "Other Diagnosis H", othrdiag_h);
					waitForLoadingToDisappear();
					actualOtherDiagnosisH = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisH, othrdiag_h, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisH + " not : "
							+othrdiag_h);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis H is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis H is empty or null");
			}
			
			//Other Diagnosis I
			if(Objects.nonNull(othrdiag_i) && !othrdiag_i.equals("")) {
				String actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
						"value");
				if(!actualOtherDiagnosisI.equals(othrdiag_i)) {
					otherDiagnosisI.clear();
					sendKeys(otherDiagnosisI, "Other Diagnosis I", othrdiag_i);
					waitForLoadingToDisappear();
					actualOtherDiagnosisI = getAttribute(otherDiagnosisI,
							"value");
					assertEquals(actualOtherDiagnosisI, othrdiag_i, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisI + " not : "
							+othrdiag_i);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis I is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis I is empty or null");
			}
			
			//Other Diagnosis J
			if(Objects.nonNull(othrdiag_j) && !othrdiag_j.equals("")) {
				String actualOtherDiagnosisJ = getAttribute(otherDiagnosisJ,
						"value");
				if(!actualOtherDiagnosisJ.equals(othrdiag_j)) {
					otherDiagnosisJ.clear();
					sendKeys(otherDiagnosisJ, "Other Diagnosis J", othrdiag_j);
					waitForLoadingToDisappear();
					actualOtherDiagnosisJ = getAttribute(otherDiagnosisB,
							"value");
					assertEquals(actualOtherDiagnosisJ, othrdiag_j, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisJ + " not : "
							+othrdiag_j);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis J is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis J is empty or null");
			}
			
			//Other Diagnosis K
			if(Objects.nonNull(othrdiag_k) && !othrdiag_k.equals("")) {
				String actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
						"value");
				if(!actualOtherDiagnosisK.equals(othrdiag_k)) {
					otherDiagnosisK.clear();
					sendKeys(otherDiagnosisK, "Other Diagnosis K", othrdiag_k);
					waitForLoadingToDisappear();
					actualOtherDiagnosisK = getAttribute(otherDiagnosisK,
							"value");
					assertEquals(actualOtherDiagnosisK, othrdiag_k, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisK + " not : "
							+othrdiag_k);
				}
				else {
					report(LogStatus.INFO, "The Other Diagnosis K is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis K is empty or null");
			}
			
			//Other Diagnosis L
			if(Objects.nonNull(othrdiag_l) && !othrdiag_l.equals("")) {
				String actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
						"value");
				if(!actualOtherDiagnosisL.equals(othrdiag_l)) {
					otherDiagnosisL.clear();
					sendKeys(otherDiagnosisL, "Other Diagnosis L", othrdiag_l);
					waitForLoadingToDisappear();
					actualOtherDiagnosisL = getAttribute(otherDiagnosisL,
							"value");
					assertEquals(actualOtherDiagnosisL, othrdiag_l, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisL + " not : "
							+othrdiag_l);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis L is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis L is empty or null");
			}
			
			//Other Diagnosis M
			if(Objects.nonNull(othrdiag_m) && !othrdiag_m.equals("")) {
				String actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
						"value");
				if(!actualOtherDiagnosisM.equals(othrdiag_m)) {
					otherDiagnosisM.clear();
					sendKeys(otherDiagnosisM, "Other Diagnosis M", othrdiag_m);
					waitForLoadingToDisappear();
					actualOtherDiagnosisM = getAttribute(otherDiagnosisM,
							"value");
					assertEquals(actualOtherDiagnosisM, othrdiag_m, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisM + " not : "
							+othrdiag_m);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis M is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis M is empty or null");
			}
			
			//Other Diagnosis N
			if(Objects.nonNull(othrdiag_n) && !othrdiag_n.equals("")) {
				String actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
						"value");
				if(!actualOtherDiagnosisN.equals(othrdiag_n)) {
					otherDiagnosisN.clear();
					sendKeys(otherDiagnosisN, "Other Diagnosis N", othrdiag_n);
					waitForLoadingToDisappear();
					actualOtherDiagnosisN = getAttribute(otherDiagnosisN,
							"value");
					assertEquals(actualOtherDiagnosisN, othrdiag_n, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisN + " not : "
							+othrdiag_n);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis N is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis N is empty or null");
			}
			
			//Other Diagnosis O
			if(Objects.nonNull(othrdiag_o) && !othrdiag_o.equals("")) {
				String actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
						"value");
				if(!actualOtherDiagnosisO.equals(othrdiag_o)) {
					otherDiagnosisO.clear();
					sendKeys(otherDiagnosisO, "Other Diagnosis O", othrdiag_o);
					waitForLoadingToDisappear();
					actualOtherDiagnosisO = getAttribute(otherDiagnosisO,
							"value");
					assertEquals(actualOtherDiagnosisO, othrdiag_o, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisO + " not : "
							+othrdiag_o);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis O is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis O is empty or null");
			}
			
			//Other Diagnosis P
			if(Objects.nonNull(othrdiag_p) && !othrdiag_p.equals("")) {
				String actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
						"value");
				if(!actualOtherDiagnosisP.equals(othrdiag_p)) {
					otherDiagnosisP.clear();
					sendKeys(otherDiagnosisP, "Other Diagnosis P", othrdiag_p);
					waitForLoadingToDisappear();
					actualOtherDiagnosisP = getAttribute(otherDiagnosisP,
							"value");
					assertEquals(actualOtherDiagnosisP, othrdiag_p, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisP + " not : "
							+othrdiag_p);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis P is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis P is empty or null");
			}
			
			//Other Diagnosis Q
			if(Objects.nonNull(othrdiag_q) && !othrdiag_q.equals("")) {
				String actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
						"value");
				if(!actualOtherDiagnosisQ.equals(othrdiag_q)) {
					otherDiagnosisQ.clear();
					sendKeys(otherDiagnosisQ, "Other Diagnosis Q", othrdiag_q);
					waitForLoadingToDisappear();
					actualOtherDiagnosisQ = getAttribute(otherDiagnosisQ,
							"value");
					assertEquals(actualOtherDiagnosisQ, othrdiag_q, "The Diagnosis "
							+ "code from field is : "+actualOtherDiagnosisQ + " not : "
							+othrdiag_q);
				}else {
					report(LogStatus.INFO, "The Other Diagnosis Q is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Diagnosis Q is empty or null");
			}
			
			//Admission Diagnosis
			if(Objects.nonNull(admsndiag) && !admsndiag.equals("")) {
				String actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
						"value");
				if(!actualAdmissionDiagnosis.equals(admsndiag)) {
					admissionDiagnosis.clear();
					sendKeys(admissionDiagnosis, "Admission Diagnosis", admsndiag);
					waitForLoadingToDisappear();
					actualAdmissionDiagnosis = getAttribute(admissionDiagnosis,
							"value");
					assertEquals(actualAdmissionDiagnosis, admsndiag, "The Diagnosis "
							+ "code from field is : "+actualAdmissionDiagnosis + " not : "
							+admsndiag);
				}else {
					report(LogStatus.INFO, "The Admission Diagnosis is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Admission Diagnosis is empty or null");
			}
			
			//Patient Reason Diagnosis A
			if(Objects.nonNull(patrsndiag_a) && !patrsndiag_a.equals("")) {
				String actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
						"value");
				if(!actualPatientReasonDiagnosisA.equals(patrsndiag_a)) {
					patientReasonDiagnosisA.clear();
					sendKeys(patientReasonDiagnosisA, "Patient Reason Diagnosis A", 
							patrsndiag_a);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisA = getAttribute(patientReasonDiagnosisA,
							"value");
					assertEquals(actualPatientReasonDiagnosisA, patrsndiag_a, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisA + " not : "
							+patrsndiag_a);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis A is empty or null");
			}
			
			//Patient Reason Diagnosis B
			if(Objects.nonNull(patrsndiag_b) && !patrsndiag_b.equals("")) {
				String actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
						"value");
				if(!actualPatientReasonDiagnosisB.equals(patrsndiag_b)) {
					patientReasonDiagnosisB.clear();
					sendKeys(patientReasonDiagnosisB, "Patient Reason Diagnosis B", 
							patrsndiag_b);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisB = getAttribute(patientReasonDiagnosisB,
							"value");
					assertEquals(actualPatientReasonDiagnosisB, patrsndiag_b, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisB + " not : "
							+patrsndiag_b);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis B is empty or null");
			}
			
			//Patient Reason Diagnosis C
			if(Objects.nonNull(patrsndiag_c) && !patrsndiag_c.equals("")) {
				String actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
						"value");
				if(!actualPatientReasonDiagnosisC.equals(patrsndiag_b)) {
					patientReasonDiagnosisC.clear();
					sendKeys(patientReasonDiagnosisC, "Patient Reason Diagnosis C", 
							patrsndiag_c);
					waitForLoadingToDisappear();
					actualPatientReasonDiagnosisC = getAttribute(patientReasonDiagnosisC,
							"value");
					assertEquals(actualPatientReasonDiagnosisC, patrsndiag_c, "The Diagnosis "
							+ "code from field is : "+actualPatientReasonDiagnosisC + " not : "
							+patrsndiag_c);
				}else {
					report(LogStatus.INFO, "The Patient Reason Diagnosis C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Patient Reason Diagnosis C is empty or null");
			}
			
			//PPS Code
			if(Objects.nonNull(ppscd) && !ppscd.equals("")) {
				String actualPPSCode = getAttribute(ppsCode, "value");
				if(!actualPPSCode.equals(ppscd)) {
					ppsCode.clear();
					sendKeys(ppsCode, "PPS Code", ppscd);
					actualPPSCode = getAttribute(ppsCode, "value");
					assertEquals(actualPPSCode, ppscd,"The Value from PPS Code field is "
					+actualPPSCode+" not : "+ppscd);
				}else {
					report(LogStatus.INFO, "The PPS Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The PPS Code is empty or null");
			}
			
			//ECI A
			if(Objects.nonNull(ecidiagcd_a) && !ecidiagcd_a.equals("")) {
				String actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
						"value");
				if(!actualECIDiagnosisA.equals(ecidiagcd_a)) {
					eciDiagnosisCodeA.clear();
					sendKeys(eciDiagnosisCodeA, "ECI Diagnosis A", 
							ecidiagcd_a);
					waitForLoadingToDisappear();
					actualECIDiagnosisA = getAttribute(eciDiagnosisCodeA,
							"value");
					assertEquals(actualECIDiagnosisA, ecidiagcd_a, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisA + " not : "
							+ecidiagcd_a);
				}else {
					report(LogStatus.INFO, "The ECI A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI A is empty or null");
			}
			
			//ECI B
			if(Objects.nonNull(ecidiagcd_b) && !ecidiagcd_b.equals("")) {
				String actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
						"value");
				if(!actualECIDiagnosisB.equals(ecidiagcd_b)) {
					eciDiagnosisCodeB.clear();
					sendKeys(eciDiagnosisCodeB, "ECI Diagnosis B", 
							ecidiagcd_b);
					waitForLoadingToDisappear();
					actualECIDiagnosisB = getAttribute(eciDiagnosisCodeB,
							"value");
					assertEquals(actualECIDiagnosisB, ecidiagcd_b, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisB + " not : "
							+ecidiagcd_b);
				}else {
					report(LogStatus.INFO, "The ECI B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI B is empty or null");
			}
			
			//ECI C
			if(Objects.nonNull(ecidiagcd_c) && !ecidiagcd_c.equals("")) {
				String actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
						"value");
				if(!actualECIDiagnosisC.equals(ecidiagcd_c)) {
					eciDiagnosisCodeC.clear();
					sendKeys(eciDiagnosisCodeC, "ECI Diagnosis C", 
							ecidiagcd_c);
					waitForLoadingToDisappear();
					 actualECIDiagnosisC = getAttribute(eciDiagnosisCodeC,
							"value");
					assertEquals(actualECIDiagnosisC, ecidiagcd_c, "The Diagnosis "
							+ "code from field is : "+actualECIDiagnosisC + " not : "
							+ecidiagcd_c);
				}else {
					report(LogStatus.INFO, "The ECI C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The ECI C is empty or null");
			}
			
			//Principle Procedure Code
			if(Objects.nonNull(principlepccd) && !principlepccd.equals("")) {
				String actualPrincipleProcCode = getAttribute(principleProcedureCode, 
						"value");
				if(!actualPrincipleProcCode.equals(principlepccd)) {
					principleProcedureCode.clear();
					sendKeys(principleProcedureCode, "Principle Proc Code", 
							principlepccd);
					actualPrincipleProcCode = getAttribute(principleProcedureCode, 
							"value");
					if(principlepccd.length()>7)
						assertEquals(actualPrincipleProcCode, principlepccd.substring(0, 7), "The Principle Proc "
							+ "code from field is : "+actualPrincipleProcCode + " not : "
							+principlepccd);
					else
						assertEquals(actualPrincipleProcCode, principlepccd, "The Principle Proc "
								+ "code from field is : "+actualPrincipleProcCode + " not : "
								+principlepccd);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			//Principle Procedure Code Date
			if(Objects.nonNull(principlepcdt) && !principlepcdt.equals("")) {
				String actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
						"value");
				if(!actualPrincipleProcCodeDate.equals(principlepcdt)) {
					for(int i = 0 ; i < 10 ; i++)
						principlePCDate.sendKeys(Keys.BACK_SPACE);
					sendKeys(principlePCDate, "Principle Proc Code Date", principlepcdt);
					actualPrincipleProcCodeDate = getAttribute(principlePCDate, 
							"value");
					assertEquals(actualPrincipleProcCodeDate, principlepcdt, 
							"The Principle Proc code from field is : "
							+actualPrincipleProcCodeDate + " not : "+principlepcdt);
				}else {
					report(LogStatus.INFO, "The Principle Procedure Code Date is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Principle Procedure Code is empty or null");
			}
			
			
			//Other Procedure Code A
			if(Objects.nonNull(othrpccd_a) && !othrpccd_a.equals("")) {
				String actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
						"value");
				if(!actualOtherProcCodeA.equals(othrpccd_a)) {
					otherProcedureCodeA.clear();
					sendKeys(otherProcedureCodeA, "Other Proc Code A", othrpccd_a);
					actualOtherProcCodeA = getAttribute(otherProcedureCodeA, 
							"value");
					if(othrpccd_a.length() > 7)
						assertEquals(actualOtherProcCodeA, othrpccd_a.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeA + " not : "
							+othrpccd_a);
					else
						assertEquals(actualOtherProcCodeA, othrpccd_a, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeA + " not : "
								+othrpccd_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code A is same and not changed.");
				}	
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code A is empty or null");
			}
			
			//Other Procedure Code Date A
			if(Objects.nonNull(othrpcdt_a) && !othrpcdt_a.equals("")) {
				String actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
				if(!actualOtherProcCodeDateA.equals(othrpcdt_a)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateA.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateA, "Other Proc Code Date A", othrpcdt_a);
					actualOtherProcCodeDateA = getAttribute(otherPCDateA, "value");
					assertEquals(actualOtherProcCodeDateA, othrpcdt_a, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateA + " not : "+othrpcdt_a);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date A is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date A is empty or null");
			}
			
			
			//Other Procedure Code B
			if(Objects.nonNull(othrpccd_b) && !othrpccd_b.equals("")) {
				String actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
						"value");
				if(!actualOtherProcCodeB.equals(othrpccd_b)) {
					otherProcedureCodeB.clear();
					sendKeys(otherProcedureCodeB, "Other Proc Code B", othrpccd_b);
					actualOtherProcCodeB = getAttribute(otherProcedureCodeB, 
							"value");
					if(othrpccd_b.length() > 7)
						assertEquals(actualOtherProcCodeB, othrpccd_b.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeB + " not : "
							+othrpccd_b);
					else
						assertEquals(actualOtherProcCodeB, othrpccd_b, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeB + " not : "
								+othrpccd_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code B is empty or null");
			}
			
			//Other Procedure Code Date B
			if(Objects.nonNull(othrpcdt_b) && !othrpcdt_b.equals("")) {
				String actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
				if(!actualOtherProcCodeDateB.equals(othrpcdt_b)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateB.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateB, "Other Proc Code Date B", othrpcdt_b);
					actualOtherProcCodeDateB = getAttribute(otherPCDateB, "value");
					assertEquals(actualOtherProcCodeDateB, othrpcdt_b, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateB + " not : "+othrpcdt_b);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date B is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date B is empty or null");
			}
			
			//Other Procedure Code C
			if(Objects.nonNull(othrpccd_c) && !othrpccd_c.equals("")) {
				String actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
						"value");
				if(!actualOtherProcCodeC.equals(othrpccd_c)) {
					otherProcedureCodeC.clear();
					sendKeys(otherProcedureCodeC, "Other Proc Code C", othrpccd_c);
					actualOtherProcCodeC = getAttribute(otherProcedureCodeC, 
							"value");
					if(othrpccd_c.length() > 7)
						assertEquals(actualOtherProcCodeC, othrpccd_c.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeC + " not : "
							+othrpccd_c);
					else
						assertEquals(actualOtherProcCodeC, othrpccd_c, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeC + " not : "
								+othrpccd_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code C is empty or null");
			}
			
			//Other Procedure Code Date C
			if(Objects.nonNull(othrpcdt_c) && !othrpcdt_c.equals("")) {
				String actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
				if(!actualOtherProcCodeDateC.equals(othrpcdt_c)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateC.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateC, "Other Proc Code Date C", othrpcdt_c);
					actualOtherProcCodeDateC = getAttribute(otherPCDateC, "value");
					assertEquals(actualOtherProcCodeDateC, othrpcdt_c, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateC + " not : "+othrpcdt_c);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date C is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date C is empty or null");
			}
			
			//Other Procedure Code D
			if(Objects.nonNull(othrpccd_d) && !othrpccd_d.equals("")) {
				String actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
						"value");
				if(!actualOtherProcCodeD.equals(othrpccd_d)) {
					otherProcedureCodeD.clear();
					sendKeys(otherProcedureCodeD, "Other Proc Code D", othrpccd_d);
					actualOtherProcCodeD = getAttribute(otherProcedureCodeD, 
							"value");
					if(othrpccd_d.length() > 7)
						assertEquals(actualOtherProcCodeD, othrpccd_d.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeD + " not : "
							+othrpccd_d);
					else
						assertEquals(actualOtherProcCodeD, othrpccd_d, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeD + " not : "
								+othrpccd_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code D is empty or null");
			}
			
			//Other Procedure Code Date D
			if(Objects.nonNull(othrpcdt_d) && !othrpcdt_d.equals("")) {
				String actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
				if(!actualOtherProcCodeDateD.equals(othrpcdt_d)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateD.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateD, "Other Proc Code Date D", othrpcdt_d);
					actualOtherProcCodeDateD = getAttribute(otherPCDateD, "value");
					assertEquals(actualOtherProcCodeDateD, othrpcdt_d, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateD + " not : "+othrpcdt_d);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date D is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date D is empty or null");
			}
			
			//Other Procedure Code E
			if(Objects.nonNull(othrpccd_e) && !othrpccd_e.equals("")) {
				String actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
						"value");
				if(!actualOtherProcCodeE.equals(othrpccd_e)) {
					otherProcedureCodeE.clear();
					sendKeys(otherProcedureCodeE, "Other Proc Code E", othrpccd_e);
					actualOtherProcCodeE = getAttribute(otherProcedureCodeE, 
							"value");
					if(othrpccd_e.length() > 7)
						assertEquals(actualOtherProcCodeE, othrpccd_e.substring(0, 7), "The Other Proc "
							+ "code from field is : "+actualOtherProcCodeE + " not : "
							+othrpccd_e);
					else
						assertEquals(actualOtherProcCodeE, othrpccd_e, "The Other Proc "
								+ "code from field is : "+actualOtherProcCodeE + " not : "
								+othrpccd_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code E is empty or null");
			}
			
			//Other Procedure Code Date E
			if(Objects.nonNull(othrpcdt_e) && !othrpcdt_e.equals("")) {
				String actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
				if(!actualOtherProcCodeDateE.equals(othrpcdt_e)) {
					for(int i = 0 ; i < 10 ; i++)
						otherPCDateE.sendKeys(Keys.BACK_SPACE);
					sendKeys(otherPCDateE, "Other Proc Code Date E", othrpcdt_e);
					actualOtherProcCodeDateE = getAttribute(otherPCDateE, "value");
					assertEquals(actualOtherProcCodeDateE, othrpcdt_e, 
							"The Principle Proc code from field is : "
							+actualOtherProcCodeDateE + " not : "+othrpcdt_e);
				}else {
					report(LogStatus.INFO, "The Other Procedure Code Date E is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Other Procedure Code Date E is empty or null");
			}
			
			//Attending Physician NPI
			if(Objects.nonNull(attndphynpi) && !attndphynpi.equals("")) {
				String actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
				if(!actualAttendingNPI.equals(attndphynpi)) {
					attendingPhysicianNPI.clear();
					sendKeys(attendingPhysicianNPI, "Attending NPI", attndphynpi);
					actualAttendingNPI = getAttribute(attendingPhysicianNPI, "value");
					assertEquals(actualAttendingNPI, attndphynpi.substring(0, 10),"The NPI from field is"
					+actualAttendingNPI+" not : "+attndphynpi);
				}else {
					report(LogStatus.INFO, "The Attending Physician NPI is same and not changed.");
				}
			}else {
					report(LogStatus.WARNING,"The Attending Physician NPI is null or empty");
			}
			
			//Attending Physician Qualifier1
			if(Objects.nonNull(attndphyqual1) && !attndphyqual1.equals("")) {
				String actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
						"value");
				if(!actualAttendingPhysicianQual1.equals(attndphyqual1)) {
					attendingPhysicianQual1.clear();
					sendKeys(attendingPhysicianQual1, "Attending Physician Qualifier 1", 
							attndphyqual1);
					actualAttendingPhysicianQual1 = getAttribute(attendingPhysicianQual1, 
							"value");
					assertEquals(actualAttendingPhysicianQual1, attndphyqual1,"The Qual from field is"
					+actualAttendingPhysicianQual1+" not : "+attndphyqual1);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier1 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier1 is null or empty");
		}
			
			//Attending Physician Qualifier2
			if(Objects.nonNull(attndphyqual2) && !attndphyqual2.equals("")) {
				String actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
						"value");
				if(!actualAttendingPhysicianQual2.equals(attndphyqual2)) {
					attendingPhysicianQual2.clear();
					sendKeys(attendingPhysicianQual2, "Attending Physician Qualifier 2", 
							attndphyqual2);
					actualAttendingPhysicianQual2 = getAttribute(attendingPhysicianQual2, 
							"value");
					assertEquals(actualAttendingPhysicianQual2, attndphyqual2,"The Qual from field is"
					+actualAttendingPhysicianQual2+" not : "+attndphyqual2);
				}else {
					report(LogStatus.INFO, "The Attending Physician Qualifier2 is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician Qualifier2 is null or empty");
		}
			
			//Attending Physician FirstName
			if(Objects.nonNull(attndphyfn) && !attndphyfn.equals("")) {
				String actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
						"value");
				if(!actualAttendingPhysicianFirstName.equals(attndphyfn)) {
					attendingPhysicianFirstName.clear();
					sendKeys(attendingPhysicianFirstName, "Attending Physician FirstName", 
							attndphyfn);
					actualAttendingPhysicianFirstName = getAttribute(attendingPhysicianFirstName, 
							"value");
					assertEquals(actualAttendingPhysicianFirstName, attndphyfn,"The FirstName from field is"
					+actualAttendingPhysicianFirstName+" not : "+attndphyfn);
				}else {
					report(LogStatus.INFO, "The Attending Physician FirstName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician FirstName is null or empty");
		}
			
			//Attending Physician LastName
			if(Objects.nonNull(attndphyln) && !attndphyln.equals("")) {
				String actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
						"value");
				if(!actualAttendingPhysicianLastName.equals(attndphyln)) {
					attendingPhysicianLastName.clear();
					sendKeys(attendingPhysicianLastName, "Attending Physician LastName", 
							attndphyln);
					actualAttendingPhysicianLastName = getAttribute(attendingPhysicianLastName, 
							"value");
					assertEquals(actualAttendingPhysicianLastName, attndphyln,"The LastName from field is"
					+actualAttendingPhysicianLastName+" not : "+attndphyln);
				}else {
					report(LogStatus.INFO, "The Attending Physician LastName is same and not changed.");
				}
			}else {
				report(LogStatus.WARNING,"The Attending Physician LastName is null or empty");
		}
			
		//Operating Physician NPI
		if(Objects.nonNull(oprtphynpi) && !oprtphynpi.equals("")) {
			String actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
					"value");
			if(!actualOperatingNPI.equals(oprtphynpi)) {
				operatingPhysicianNPI.clear();
				sendKeys(operatingPhysicianNPI, "Operating NPI", oprtphynpi);
				actualOperatingNPI = getAttribute(operatingPhysicianNPI, 
						"value");
				assertEquals(actualOperatingNPI, oprtphynpi.substring(0, 10),"The NPI from field is"
				+actualOperatingNPI+" not : "+oprtphynpi);
			}else {
				report(LogStatus.INFO, "The Operating Physician NPI is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician NPI is null or empty");
		}
			
		//Operating Physician Qualifier1
		if(Objects.nonNull(oprtphyqual1) && !oprtphyqual1.equals("")) {
			String actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
					"value");
			if(!actualOperatingPhysicianQual1.equals(oprtphyqual1)) {
				operatingPhysicianQual1.clear();
				sendKeys(operatingPhysicianQual1, "Operating Physician Qualifier 1", 
						oprtphyqual1);
				actualOperatingPhysicianQual1 = getAttribute(operatingPhysicianQual1, 
						"value");
				assertEquals(actualOperatingPhysicianQual1, oprtphyqual1,"The Qual from field is"
				+actualOperatingPhysicianQual1+" not : "+oprtphyqual1);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier1 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier1 is null or empty");
		}
		
		//Operating Physician Qualifier2
		if(Objects.nonNull(oprtphyqual2) && !oprtphyqual2.equals("")) {
			String actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
					"value");
			if(!actualOperatingPhysicianQual2.equals(oprtphyqual2)) {
				operatingPhysicianQual2.clear();
				sendKeys(operatingPhysicianQual2, "Operating Physician Qualifier 2", 
						oprtphyqual2);
				actualOperatingPhysicianQual2 = getAttribute(operatingPhysicianQual2, 
						"value");
				assertEquals(actualOperatingPhysicianQual2, oprtphyqual2,"The Qual from field is"
				+actualOperatingPhysicianQual2+" not : "+oprtphyqual2);
			}else {
				report(LogStatus.INFO, "The Operating Physician Qualifier2 is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician Qualifier2 is null or empty");
		}
		
		//Operating Physician FirstName
		if(Objects.nonNull(oprtphyfn) && !oprtphyfn.equals("")) {
			String actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
					"value");
			if(!actualOperatingPhysicianFirstName.equals(oprtphyfn)) {
				operatingPhysicianFirstName.clear();
				sendKeys(operatingPhysicianFirstName, "Operating Physician FirstName", 
						oprtphyfn);
				actualOperatingPhysicianFirstName = getAttribute(operatingPhysicianFirstName, 
						"value");
				assertEquals(actualOperatingPhysicianFirstName, oprtphyfn,"The FirstName from field is"
						+actualOperatingPhysicianFirstName+" not : "+oprtphyfn);
			}else {
				report(LogStatus.INFO, "The Operating Physician FirstName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician FirstName is null or empty");
		}
		
		//Operating Physician LasttName
		if(Objects.nonNull(oprtphyln) && !oprtphyln.equals("")) {
			String actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
					"value");
			if(!actualOperatingPhysicianLastName.equals(oprtphyln)) {
				operatingPhysicianLastName.clear();
				sendKeys(operatingPhysicianLastName, "Operating Physician LastName", 
						attndphyln);
				actualOperatingPhysicianLastName = getAttribute(operatingPhysicianLastName, 
						"value");
				assertEquals(actualOperatingPhysicianLastName, attndphyln,"The LastName from field is"
				+actualOperatingPhysicianLastName+" not : "+attndphyln);
			}else {
				report(LogStatus.INFO, "The Operating Physician LastName is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Operating Physician LastName is null or empty");
		}
		
		//Other Physician NPI A
		if(Objects.nonNull(othrnpi_a) && !othrnpi_a.equals("")) {
			String actualOtherNPIA = getAttribute(otherNPIA, "value");
			if(!actualOtherNPIA.equals(othrnpi_a)) {
				otherNPIA.clear();
				sendKeys(otherNPIA, "Other NPI", othrnpi_a);
				actualOtherNPIA = getAttribute(otherNPIA, "value");
				assertEquals(actualOtherNPIA, othrnpi_a.substring(0, 10),"The NPI from field is"
				+actualOtherNPIA+" not : "+othrnpi_a);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI Ais same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI A is null or empty");
		}
		
		//Other Physician Qualifier1 A
		if(Objects.nonNull(othrnpiqual1_a) && !othrnpiqual1_a.equals("")) {
			String actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
					"value");
			if(!actualOtherNPIQual1A.equals(othrnpiqual1_a)) {
				otherNPIQual1A.clear();
				sendKeys(otherNPIQual1A, "Other NPI Qualifier 1", othrnpiqual1_a);
				actualOtherNPIQual1A = getAttribute(otherNPIQual1A, 
						"value");
				assertEquals(actualOtherNPIQual1A, othrnpiqual1_a,"The Qual from field is"
				+actualOtherNPIQual1A+" not : "+othrnpiqual1_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 A is null or empty");
		}
		
		//Other Physician Qualifier2 A
		if(Objects.nonNull(othrnpiqual2_a) && !othrnpiqual2_a.equals("")) {
			String actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
					"value");
			if(!actualOtherNPIQual2A.equals(othrnpiqual2_a)) {
				otherNPIQual2A.clear();
				sendKeys(otherNPIQual2A, "Other NPI Qualifier 2", othrnpiqual2_a);
				actualOtherNPIQual2A = getAttribute(otherNPIQual2A, 
						"value");
				assertEquals(actualOtherNPIQual2A, othrnpiqual2_a,"The Qual from field is"
				+actualOtherNPIQual2A+" not : "+othrnpiqual2_a);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 A is null or empty");
		}
		
		//Other Physician First Name A
		if(Objects.nonNull(othrnpifn_a) && !othrnpifn_a.equals("")) {
			String actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
					"value");
			if(!actualOtherPhysicianFirstNameA.equals(othrnpifn_a)) {
				otherNPIFirstNameA.clear();
				sendKeys(otherNPIFirstNameA, "Other Physician FirstName A", 
						othrnpifn_a);
				actualOtherPhysicianFirstNameA = getAttribute(otherNPIFirstNameA, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameA, othrnpifn_a,"The FirstName from field is"
				+actualOtherPhysicianFirstNameA+" not : "+othrnpifn_a);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName A is null or empty");
		}
		
		//Other Physician LastName A
		if(Objects.nonNull(othrnpiln_a) && !othrnpiln_a.equals("")) {
			String actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
					"value");
			if(!actualOtherPhysicianLastNameA.equals(othrnpiln_a)) {
				otherNPILastNameA.clear();
				sendKeys(otherNPILastNameA, "Operating Physician LastName", 
						othrnpiln_a);
				actualOtherPhysicianLastNameA = getAttribute(otherNPILastNameA, 
						"value");
				assertEquals(actualOtherPhysicianLastNameA, othrnpiln_a,"The LastName from field is"
				+actualOtherPhysicianLastNameA+" not : "+othrnpiln_a);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName A is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName A is null or empty");
		}
		
		//Other Physician NPI B
		if(Objects.nonNull(othrnpi_b) && !othrnpi_b.equals("")) {
			String actualOtherNPIB = getAttribute(otherNPIB, "value");
			if(!actualOtherNPIB.equals(othrnpi_b)) {
				otherNPIB.clear();
				sendKeys(otherNPIB, "Other NPI B", othrnpi_b);
				actualOtherNPIB = getAttribute(otherNPIB, "value");
				assertEquals(actualOtherNPIB, othrnpi_b.substring(0, 10),"The NPI from field is"
				+actualOtherNPIB+" not : "+othrnpi_b);
			}else {
				report(LogStatus.INFO, "The Other Physician NPI B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician NPI B is null or empty");
		}
		
		//Other Physician Qualifier1 B
		if(Objects.nonNull(othrnpiqual1_b) && !othrnpiqual1_b.equals("")) {
			String actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
					"value");
			if(!actualOtherNPIQual1B.equals(othrnpiqual1_b)) {
				otherNPIQual1B.clear();
				sendKeys(otherNPIQual1B, "Other NPI Qualifier 1", othrnpiqual1_b);
				actualOtherNPIQual1B = getAttribute(otherNPIQual1B, 
						"value");
				assertEquals(actualOtherNPIQual1B, othrnpiqual1_b,"The Qual from field is"
				+actualOtherNPIQual1B+" not : "+othrnpiqual1_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier1 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier1 B is null or empty");
		}
		
		//Other Physician Qualifier2 B
		if(Objects.nonNull(othrnpiqual2_b) && !othrnpiqual2_b.equals("")) {
			String actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
					"value");
			if(!actualOtherNPIQual2B.equals(othrnpiqual2_b)) {
				otherNPIQual2B.clear();
				sendKeys(otherNPIQual2B, "Other NPI Qualifier 2", othrnpiqual2_b);
				actualOtherNPIQual2B = getAttribute(otherNPIQual2B, 
						"value");
				assertEquals(actualOtherNPIQual2B, othrnpiqual2_b,"The Qual from field is"
				+actualOtherNPIQual2B+" not : "+othrnpiqual2_b);
			}else {
				report(LogStatus.INFO, "The Other Physician Qualifier2 B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician Qualifier2 B is null or empty");
		}
		
		//Other Physician First Name B
		if(Objects.nonNull(othrnpifn_b) && !othrnpifn_b.equals("")) {
			String actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
					"value");
			if(!actualOtherPhysicianFirstNameB.equals(othrnpifn_b)) {
				otherNPIFirstNameB.clear();
				sendKeys(otherNPIFirstNameB, "Other Physician FirstName A", 
						othrnpifn_b);
				actualOtherPhysicianFirstNameB = getAttribute(otherNPIFirstNameB, 
						"value");
				assertEquals(actualOtherPhysicianFirstNameB, othrnpifn_b,"The FirstName from field is"
				+actualOtherPhysicianFirstNameB+" not : "+othrnpifn_b);
			}else {
				report(LogStatus.INFO, "The Other Physician FirstName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician FirstName B is null or empty");
		}
		
		//Other Physician LastName B
		if(Objects.nonNull(othrnpiln_b) && !othrnpiln_b.equals("")) {
			String actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
					"value");
			if(!actualOtherPhysicianLastNameB.equals(othrnpiln_b)) {
				otherNPILastNameB.clear();
				sendKeys(otherNPILastNameB, "Operating Physician LastName", 
						othrnpiln_b);
				actualOtherPhysicianLastNameB = getAttribute(otherNPILastNameB, 
						"value");
				assertEquals(actualOtherPhysicianLastNameB, othrnpiln_b,"The LastName from field is"
				+actualOtherPhysicianLastNameB+" not : "+othrnpiln_b);
			}else {
				report(LogStatus.INFO, "The Other Physician LastName B is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Other Physician LastName B is null or empty");
		}
		
		//Remarks
		if(Objects.nonNull(reMarks) && !reMarks.equals("")) {
			String actualRemarks = getAttribute(remarks, "value");
			if(!actualRemarks.equals(reMarks)) {
				remarks.clear();
				sendKeys(remarks, "Remarks", reMarks);
				actualRemarks = getAttribute(remarks, "value");
				assertEquals(actualRemarks, reMarks,"The remarks from field is : "
				+actualRemarks+" not : "+reMarks);
			}else {
				report(LogStatus.INFO, "The Remarks is same and not changed.");
			}
		}else {
			report(LogStatus.WARNING,"The Remarks is null or empty");
		}
		
		//Taxonomy
		if(Objects.nonNull(form81taxonomy_a) && !form81taxonomy_a.equals("")) {
			form81ATaxanomy.clear();
			sendKeys(form81ATaxanomy, "Taxanomy", form81taxonomy_a);
			String taxonomyOption = dropdownOptions.replace("XX", form81taxonomy_a);
			waitForLoadingToDisappear();
			WebElement taxonomy = driver.findElement(By.xpath(taxonomyOption));
			click(taxonomy, form81taxonomy_a);
		}else {
			report(LogStatus.WARNING,"The Taxonomy is null or empty");
		}
		
		//Form 81 A value
		if(Objects.nonNull(form81value_a) && !form81taxonomy_a.equals("")) {
			String actualForm81AValue = getAttribute(form81AValue, "value");
			if(!actualForm81AValue.equals(form81value_a)) {
				sendKeys(form81AValue, "Form 81 A Value", form81value_a);
				actualForm81AValue = getAttribute(form81AValue, "value");
				assertEquals(actualForm81AValue, form81value_a, "The Value from field "
						+ "is : "+actualForm81AValue+" not : "+form81value_a);
			}else {
				report(LogStatus.INFO,"The Form 81 A value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 A value is null or empty");
		}
		
		//Form 81 B Qualifier
		if(Objects.nonNull(form81qualifier_b) && !form81qualifier_b.equals("")) {
			String actualForm81BQualifier = getAttribute(form81BQualifier, "value");
			if(!actualForm81BQualifier.equals(form81qualifier_b)) {
				form81BQualifier.clear();
				sendKeys(form81BQualifier, "Form 81 A Qualifier", form81qualifier_b);
				actualForm81BQualifier = getAttribute(form81BQualifier, "value");
				assertEquals(actualForm81BQualifier, form81qualifier_b, "The Value from field "
						+ "is : "+actualForm81BQualifier+" not : "+form81qualifier_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Qualifier value is null or empty");
		}
		
		//Form 81 B Taxonomy
		if(Objects.nonNull(form81taxonomy_b) && !form81taxonomy_b.equals("")) {
			String actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
			if(!actualForm81BTaxonomy.equals(form81taxonomy_b)) {
				form81BTaxanomy.clear();
				sendKeys(form81BTaxanomy, "Form 81 A Taxonomy", form81taxonomy_b);
				actualForm81BTaxonomy = getAttribute(form81BTaxanomy, "value");
				assertEquals(actualForm81BTaxonomy, form81taxonomy_b, "The Value from field "
						+ "is : "+actualForm81BTaxonomy+" not : "+form81taxonomy_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Taxonomy value is null or empty");
		}
		
		//Form 81 B Value
		if(Objects.nonNull(form81value_b) && !form81value_b.equals("")) {
			String actualForm81BValue = getAttribute(form81BValue, "value");
			if(!actualForm81BValue.equals(form81value_b)) {
				form81BValue.clear();
				sendKeys(form81BValue, "Form 81 A Value", form81value_b);
				actualForm81BValue = getAttribute(form81BValue, "value");
				assertEquals(actualForm81BValue, form81value_b, "The Value from field "
						+ "is : "+actualForm81BValue+" not : "+form81value_b);
			}else {
				report(LogStatus.INFO,"The Form 81 B Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 B Value is null or empty");
		}
		
		//Form 81 C Qualifier
		if(Objects.nonNull(form81qualifier_c) && !form81qualifier_c.equals("")) {
			String actualForm81CQualifier = getAttribute(form81CQualifier, "value");
			if(!actualForm81CQualifier.equals(form81qualifier_c)) {
				form81CQualifier.clear();
				sendKeys(form81CQualifier, "Form 81 C Qualifier", form81qualifier_c);
				actualForm81CQualifier = getAttribute(form81CQualifier, "value");
				assertEquals(actualForm81CQualifier, form81qualifier_c, "The Value from field "
						+ "is : "+actualForm81CQualifier+" not : "+form81qualifier_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Qualifier is null or empty");
		}
		
		//Form 81 C Taxonomy
		if(Objects.nonNull(form81taxonomy_c) && !form81taxonomy_c.equals("")) {
			String actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
			if(!actualForm81CTaxonomy.equals(form81taxonomy_c)) {
				form81CTaxanomy.clear();
				sendKeys(form81CTaxanomy, "Form 81 C Taxonomy", form81taxonomy_c);
				actualForm81CTaxonomy = getAttribute(form81CTaxanomy, "value");
				assertEquals(actualForm81CTaxonomy, form81taxonomy_c, "The Value from field "
						+ "is : "+actualForm81CTaxonomy+" not : "+form81taxonomy_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Taxonomy is null or empty");
		}
		
		//Form 81 C Value
		if(Objects.nonNull(form81value_c) && !form81value_c.equals("")) {
			String actualForm81CValue = getAttribute(form81CValue, "value");
			if(!actualForm81CValue.equals(actualForm81CValue)) {
				form81CValue.clear();
				sendKeys(form81CValue, "Form 81 C Value", form81value_c);
				actualForm81CValue = getAttribute(form81CValue, "value");
				assertEquals(actualForm81CValue, form81value_c, "The Value from field "
						+ "is : "+actualForm81CValue+" not : "+form81value_c);
			}else {
				report(LogStatus.INFO,"The Form 81 C Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 C Value is null or empty");
		}
		
		//Form 81 D Qualifier
		if(Objects.nonNull(form81qualifier_d) && !form81qualifier_d.equals("")) {
			String actualForm81DQualifier = getAttribute(form81DQualifier, "value");
			if(!actualForm81DQualifier.equals(form81qualifier_d)) {
				form81DQualifier.clear();
				sendKeys(form81DQualifier, "Form 81 D Qualifier", form81qualifier_d);
				actualForm81DQualifier = getAttribute(form81DQualifier, "value");
				assertEquals(actualForm81DQualifier, form81qualifier_d, "The Value from field "
						+ "is : "+actualForm81DQualifier+" not : "+form81qualifier_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Qualifier is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Qualifier is null or empty");
		}
		
		//Form 81 D Taxonomy
		if(Objects.nonNull(form81taxonomy_d) && !form81taxonomy_d.equals("")) {
			String actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
			if(!actualForm81DTaxonomy.equals(form81taxonomy_d)) {
				form81DTaxanomy.clear();
				sendKeys(form81DTaxanomy, "Form 81 D Taxonomy", form81taxonomy_d);
				actualForm81DTaxonomy = getAttribute(form81DTaxanomy, "value");
				assertEquals(actualForm81DTaxonomy, form81taxonomy_d, "The Value from field "
						+ "is : "+actualForm81DTaxonomy+" not : "+form81taxonomy_d);
			}else {
				report(LogStatus.INFO,"The //Form 81 D Taxonomy is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Taxonomy is null or empty");
		}
		
		//Form 81 D Value
		if(Objects.nonNull(form81value_d) && !form81value_d.equals("")) {
			String actualForm81DValue = getAttribute(form81DValue, "value");
			if(!actualForm81DValue.equals(form81value_d)) {
				form81DValue.clear();
				sendKeys(form81DValue, "Form 81 D Value", form81value_d);
				actualForm81DValue = getAttribute(form81DValue, "value");
				assertEquals(actualForm81DValue, form81value_d, "The Value from field "
						+ "is : "+actualForm81DValue+" not : "+form81value_d);
			}else {
				report(LogStatus.INFO,"The Form 81 D Value is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Form 81 D Value is null or empty");
		}
		
		//Received Date
		if(Objects.nonNull(receveddate) && !receveddate.equals("")) {
			String actualReceivedDate = getAttribute(receivedDate, "value");
			if(!actualReceivedDate.equals(receveddate)) {
				for(int i = 0 ; i < 10 ; i++)
					receivedDate.sendKeys(Keys.BACK_SPACE);
				sendKeys(receivedDate, "Received Date", receveddate);
				actualReceivedDate = getAttribute(receivedDate, "value");
				assertEquals(actualReceivedDate, receveddate, "The Received Date "
						+ "from field is : "+actualReceivedDate+" not : "+receveddate);
			}else {
				report(LogStatus.INFO,"The Received Date is same and not changed");
			}
		}else {
			report(LogStatus.WARNING,"The Received Date is null or empty");
		}
		
		click(saveButton, "Submit Claim");
		
		waitForLoadingToDisappear();
		
		String alertXpath = "//*[@role='alertdialog']";
		WebElement alertEle = driver.findElement(By.xpath(alertXpath));
		putStaticWait(2);
		waitUntilElementVisible(By.xpath(alertXpath), 20);
		String alerttext = getAttribute(alertEle,"aria-label");
		String alerttext1 = getAttribute(alertEle,"innerHTML");
		System.out.println(alerttext);
		System.out.println(alerttext1);
		alertEle.click();
		if(Objects.nonNull(alerttext)) {

			if(alerttext.contains("success") || alerttext.contains("Success") ) {
				report(LogStatus.PASS, "Successfully saved");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not saved successfully\n"+alerttext);
				try {
					throw new CannotCreateClaimException("Not able to save the "
							+ "claim due to error : \n"+alerttext);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}else if(Objects.nonNull(alerttext1)) {
			if(alerttext1.contains("success") || alerttext1.contains("Success") ) {
				report(LogStatus.PASS, "Successfully saved");
				return myMCSNumber;
			}else {
				report(LogStatus.FAIL,"Not savved successfully\n"+alerttext1);
				try {
					throw new CannotCreateClaimException("Not able to save the "
							+ "claim due to error : \n"+alerttext1);
				}catch(CannotCreateClaimException e) {
					report(LogStatus.FAIL, e.getMessage());
					e.printStackTrace();
					cancelClaim();
					report(LogStatus.WARNING,"Claim is cancelled");
					return "Not able to create claim";
				}
			}
		}
		waitForLoadingToDisappear();	
		}else {
			report(LogStatus.FAIL, "Copy UB-04 screen is not displayed.");
			try {
				throw new Exception("Copy UB-04 screen is not displayed");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		waitForLoadingToDisappear();
		
		return myMCSNumber;
		
	}
	
	public boolean viewUB04Claim(String myMCSClaimNumber) {
		boolean flag = false;
		
		filterWithMyMCSNumber(myMCSClaimNumber);
		
		click(firstRecord, "");
		
		waitForLoadingToDisappear();
		
		click(viewButton, "View");
		
		waitForLoadingToDisappear();
		
		if(viewUB04Heading.isDisplayed()) {
			report(LogStatus.PASS,"View UB04 Popup is displayed");
		}else {
			report(LogStatus.FAIL,"View UB04 Popup is not displayed");
		}
		
		String conStr = envConfig.getProperty("devDBConnectionString");

		DBUtil dbUtil = new DBUtil();
		
		Map<String, String> claimData = dbUtil.executeQuery(conStr, 
				"select * from tb_ub04s where clm_num = '"+myMCSClaimNumber+"' ;");
		
		Map<String, String> claimServiceData = dbUtil.executeQuery(conStr, 
				"Select * from tb_ub04_srvcs where clm_num = '"+myMCSClaimNumber+"' ;");
		
		return flag;
	}
	
	private void filterWithMyMCSNumber(String myMCSNumber) {
		
		click(filterBtton, "Filter");
		
		if(filterHeading.isDisplayed()) {
			report(LogStatus.PASS, "Filter Popup is displayed.");
			if(filterHeading.isDisplayed()) {
				report(LogStatus.PASS,"The Filter Popup is displayed");
			}else {
				report(LogStatus.FAIL,"The Filter Popup is not displayed");
			}
			String actualMyMCSNumber = getAttribute(filterMyMCSClaimNumber, "value");
			if(actualMyMCSNumber.equals("") || Objects.isNull(actualMyMCSNumber))
				sendKeys(filterMyMCSClaimNumber, "My MCS Claim Number", myMCSNumber);
			else {
				filterMyMCSClaimNumber.clear();
				sendKeys(filterMyMCSClaimNumber, "My MCS Claim Number", myMCSNumber);
			}
			actualMyMCSNumber = getAttribute(filterMyMCSClaimNumber, "value");
			assertEquals(actualMyMCSNumber, myMCSNumber,"The Value from field is : "
			+actualMyMCSNumber+" not : "+myMCSNumber);
			click(filterSearchButon, "Search Button");
			waitForLoadingToDisappear();
		}else {
			report(LogStatus.FAIL,"Filter popup is not displayed.");
		}
		
	}

	private void addPrimaryPayeB(String payer_b,String healthplanid_b, String relinfo_b, 
			String benfitassignment_b, String priorpaymentamt_b, String estamountdue_b, 
			String payertype_b){
		
		//Payer Name
		if(Objects.nonNull(payer_b) && !payer_b.equals("")) {
			sendKeys(payerB, "Payer B", payer_b);
			String actualPayerB = getAttribute(payerB, "value");
			assertEquals(actualPayerB, payer_b, "The Value from Payer field is : "+
					actualPayerB+" not : "+payer_b);
		}
		
		//Health Plan ID B
		if(Objects.nonNull(healthplanid_b) && !healthplanid_b.equals("")) {
			sendKeys(healthPlanIDB, "Health Plan ID B", healthplanid_b);
			String actualHealthPlanIDB = getAttribute(healthPlanIDB, "value");
			assertEquals(actualHealthPlanIDB, healthplanid_b, "The Health Plan ID B from"
					+ " field is : "+actualHealthPlanIDB+" not : "+healthplanid_b);						
		}

		
		//REL INFO B
		if(Objects.nonNull(relinfo_b) && !relinfo_b.equals("")) {
			String relInfoBClass = getAttribute(relInfoCheckBoxB, "class");
			String[] relInfoBData = relInfoBClass.split(" ");
			String actualRelInfoB = "";
			for(String s : relInfoBData) {
				if(s.equals("mat-checkbox-checked")) {
					actualRelInfoB = s;
				}
			}
			if(actualRelInfoB.equals("") && relinfo_b.equals("YES")) {
				click(relInfoCheckBoxB, "Rel Info Checkbox B");
				actualRelInfoB = getAttribute(relInfoCheckBoxB, "class");
				if(actualRelInfoB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box checked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not checked");
				}
				
			}else if(actualRelInfoB.equals("mat-checkbox-checked") && 
					relinfo_b.equals("NO")) {
				click(relInfoCheckBoxB, "Rel Info Checkbox B");
				actualRelInfoB = getAttribute(relInfoCheckBoxB, "class");
				if(!actualRelInfoB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box unchecked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
				}
				
			}
		}
		
		//ASN BEN B
		if(Objects.nonNull(benfitassignment_b) && 
				!benfitassignment_b.equals("")) {
			String benfitAssignmentBClass = getAttribute(beneftAssignmentCheckboxB, "class");
			String[] benfitAssignmentBData = benfitAssignmentBClass.split(" ");
			String actualBenfitAssignmentB = "";
			for(String s : benfitAssignmentBData) {
				if(s.equals("mat-checkbox-checked")) {
					actualBenfitAssignmentB = s;
				}
			}
			if(actualBenfitAssignmentB.equals("") && 
					benfitassignment_b.equals("YES")) {
				click(beneftAssignmentCheckboxB, "Benefit Assignment Checkbox B");
				actualBenfitAssignmentB = getAttribute(beneftAssignmentCheckboxB, "class");
				if(actualBenfitAssignmentB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box checked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not checked");
				}
				
			}else if(actualBenfitAssignmentB.equals("mat-checkbox-checked") && 
					benfitassignment_b.equals("NO")) {
				click(beneftAssignmentCheckboxB, "Benefit Assignment Checkbox B");
				actualBenfitAssignmentB = getAttribute(beneftAssignmentCheckboxB, "class");
				if(!actualBenfitAssignmentB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box unchecked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
				}
				
			}
		}
		
		//Prior payment amount
		if(Objects.nonNull(priorpaymentamt_b) && 
				!priorpaymentamt_b.equals("")) {
			priorPaymentAmountB.clear();
			sendKeys(priorPaymentAmountB, "Prior Payment Amount B", priorpaymentamt_b);
			String actualPriorPaymentAmountB = getAttribute(priorPaymentAmountB, "value");
			assertEquals(actualPriorPaymentAmountB, priorpaymentamt_b, "The amount "
					+"from prior payment amount field B is : "+actualPriorPaymentAmountB+
					" not : "+priorpaymentamt_b);
		}
		
		//EST Due Amount
		if(Objects.nonNull(estamountdue_b) && 
				!estamountdue_b.equals("")) {
			estAmountDueB.clear();
			sendKeys(estAmountDueB, "EST Due Amount B", estamountdue_b);
			String actualESTAmountDueB= getAttribute(estAmountDueB, "value");
			assertEquals(actualESTAmountDueB, estamountdue_b, "The amount "
					+"from prior payment amount field B is : "+actualESTAmountDueB+
					" not : "+estamountdue_b);
		}
		
		//primary payer
		if(Objects.nonNull(payertype_b) && !payertype_b.equals("")) {
			click(payerTypeDrodownB, "Payer Type");
			switch(payertype_b) {
			case "MEDICARE":{
				click(payerTypeMedicareOption, "MEDICARE");
				break;
			}
			case "NON MEDICARE":{
				click(payerTypeNonMedicareOption, "Non - MEDICARE");
				break;
			}
			default:
				report(LogStatus.WARNING, "Payer Type is not valid");
				break;
				
			}
		}
	}
	
	private void modifyPrimaryPayeB(String payer_b,String healthplanid_b, String relinfo_b, 
			String benfitassignment_b, String priorpaymentamt_b, String estamountdue_b, 
			String payertype_b){
		
		//Payer Name
		if(Objects.nonNull(payer_b) && !payer_b.equals("")) {
			String actualPayerB = getAttribute(payerB, "value");
			if(!actualPayerB.endsWith(payer_b)) {
				payerB.clear();
				sendKeys(payerB, "Payer B", payer_b);
				actualPayerB = getAttribute(payerB, "value");
				assertEquals(actualPayerB, payer_b, "The Value from Payer field is : "+
						actualPayerB+" not : "+payer_b);
			}else {
				report(LogStatus.INFO, "Payer Name B is same and not changed");
			}
		}else {
			report(LogStatus.WARNING, "Payer Name B is empty or null");
		}
		
		//Health Plan ID B
		if(Objects.nonNull(healthplanid_b) && !healthplanid_b.equals("")) {
			String actualHealthPlanIDB = getAttribute(healthPlanIDB, "value");
			if(!actualHealthPlanIDB.equals(healthplanid_b)) {
				healthPlanIDB.clear();
				sendKeys(healthPlanIDB, "Health Plan ID B", healthplanid_b);
				actualHealthPlanIDB = getAttribute(healthPlanIDB, "value");
				assertEquals(actualHealthPlanIDB, healthplanid_b, "The Health Plan ID B from"
						+ " field is : "+actualHealthPlanIDB+" not : "+healthplanid_b);	
			}else {
				report(LogStatus.INFO, "Health Plan ID B is same and not changed");
			}		
		}else {
			report(LogStatus.WARNING, "Health Plan ID B is empty or null");
		}

		
		//REL INFO B
		if(Objects.nonNull(relinfo_b) && !relinfo_b.equals("")) {
			String relInfoBClass = getAttribute(relInfoCheckBoxB, "class");
			String[] relInfoBData = relInfoBClass.split(" ");
			String actualRelInfoB = "";
			for(String s : relInfoBData) {
				if(s.equals("mat-checkbox-checked")) {
					actualRelInfoB = s;
				}
			}
			if(actualRelInfoB.equals("") && relinfo_b.equals("YES")) {
				click(relInfoCheckBoxB, "Rel Info Checkbox B");
				actualRelInfoB = getAttribute(relInfoCheckBoxB, "class");
				if(actualRelInfoB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box checked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not checked");
				}
				
			}else if(actualRelInfoB.equals("mat-checkbox-checked") && 
					relinfo_b.equals("NO")) {
				click(relInfoCheckBoxB, "Rel Info Checkbox B");
				actualRelInfoB = getAttribute(relInfoCheckBoxB, "class");
				if(!actualRelInfoB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box unchecked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
				}
				
			}
		}
		
		//ASN BEN B
		if(Objects.nonNull(benfitassignment_b) && 
				!benfitassignment_b.equals("")) {
			String benfitAssignmentBClass = getAttribute(beneftAssignmentCheckboxB, "class");
			String[] benfitAssignmentBData = benfitAssignmentBClass.split(" ");
			String actualBenfitAssignmentB = "";
			for(String s : benfitAssignmentBData) {
				if(s.equals("mat-checkbox-checked")) {
					actualBenfitAssignmentB = s;
				}
			}
			if(actualBenfitAssignmentB.equals("") && 
					benfitassignment_b.equals("YES")) {
				click(beneftAssignmentCheckboxB, "Benefit Assignment Checkbox B");
				actualBenfitAssignmentB = getAttribute(beneftAssignmentCheckboxB, "class");
				if(actualBenfitAssignmentB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box checked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not checked");
				}
				
			}else if(actualBenfitAssignmentB.equals("mat-checkbox-checked") && 
					benfitassignment_b.equals("NO")) {
				click(beneftAssignmentCheckboxB, "Benefit Assignment Checkbox B");
				actualBenfitAssignmentB = getAttribute(beneftAssignmentCheckboxB, "class");
				if(!actualBenfitAssignmentB.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box unchecked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
				}
				
			}
		}
		
		//Prior payment amount
		if(Objects.nonNull(priorpaymentamt_b) && 
				!priorpaymentamt_b.equals("")) {
			priorPaymentAmountB.clear();
			sendKeys(priorPaymentAmountB, "Prior Payment Amount B", priorpaymentamt_b);
			String actualPriorPaymentAmountB = getAttribute(priorPaymentAmountB, "value");
			assertEquals(actualPriorPaymentAmountB, priorpaymentamt_b, "The amount "
					+"from prior payment amount field B is : "+actualPriorPaymentAmountB+
					" not : "+priorpaymentamt_b);
		}
		
		//EST Due Amount
		if(Objects.nonNull(estamountdue_b) && 
				!estamountdue_b.equals("")) {
			estAmountDueB.clear();
			sendKeys(estAmountDueB, "EST Due Amount B", estamountdue_b);
			String actualESTAmountDueB= getAttribute(estAmountDueB, "value");
			assertEquals(actualESTAmountDueB, estamountdue_b, "The amount "
					+"from prior payment amount field B is : "+actualESTAmountDueB+
					" not : "+estamountdue_b);
		}
		
		//primary payer
		if(Objects.nonNull(payertype_b) && !payertype_b.equals("")) {
			click(payerTypeDrodownB, "Payer Type");
			switch(payertype_b) {
			case "MEDICARE":{
				click(payerTypeMedicareOption, "MEDICARE");
				break;
			}
			case "NON MEDICARE":{
				click(payerTypeNonMedicareOption, "Non - MEDICARE");
				break;
			}
			default:
				report(LogStatus.WARNING, "Payer Type is not valid");
				break;
				
			}
		}
	}
	
	private void addPrimaryPayeBAndC(String payer_b,String healthplanid_b, 
			String relinfo_b, String benfitassignment_b, String priorpaymentamt_b, 
			String estamountdue_b, String payertype_b, String payer_c, 
			String healthplanid_c, String relinfo_c, String benfitassignment_c, 
			String priorpaymentamt_c, String estamountdue_c, 
			String payertype_c){
		
		addPrimaryPayeB(payer_b,healthplanid_b, relinfo_b, benfitassignment_b, priorpaymentamt_b, estamountdue_b, payertype_b);
				
		//Payer Name
		if(Objects.nonNull(payer_c) && !payer_c.equals("")) {
			sendKeys(payerC, "Payer C", payer_c);
			String actualPayerC = getAttribute(payerC, "value");
			assertEquals(actualPayerC, payer_c, "The Value from Payer field is : "+
					actualPayerC+" not : "+payer_c);
		}
		
		//Health Plan ID C
		if(Objects.nonNull(healthplanid_c) && !healthplanid_c.equals("")) {
			sendKeys(healthPlanIDC, "Health Plan ID C", healthplanid_c);
			String actualHealthPlanIDC = getAttribute(healthPlanIDC, "value");
			assertEquals(actualHealthPlanIDC, healthplanid_c, "The Health Plan ID C from"
					+ " field is : "+actualHealthPlanIDC+" not : "+healthplanid_c);						
		}
			
		//REL INFO C
		if(Objects.nonNull(relinfo_c) && !relinfo_c.equals("")) {
			String relInfoCClass = getAttribute(relInfoCheckBoxC, "class");
			String[] relInfoCData = relInfoCClass.split(" ");
		  	String actualRelInfoC = "";
			 for(String s : relInfoCData) {
				if(s.equals("mat-checkbox-checked")) {
					actualRelInfoC = s;
				}
			}
			if(actualRelInfoC.equals("") && relinfo_c.equals("YES")) {
				click(relInfoCheckBoxC, "Rel Info Checkbox C");
				actualRelInfoC = getAttribute(relInfoCheckBoxC, "class");
				if(actualRelInfoC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box checked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not checked");
				}
				
			}else if(actualRelInfoC.equals("mat-checkbox-checked") && 
				relinfo_c.equals("NO")) {
				click(relInfoCheckBoxC, "Rel Info Checkbox C");
				actualRelInfoC = getAttribute(relInfoCheckBoxC, "class");
				if(!actualRelInfoC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box unchecked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
				}
				
			}
		}
	
		//ASN BEN C
		if(Objects.nonNull(benfitassignment_c) && 
			!benfitassignment_c.equals("")) {
			String benfitAssignmentCClass = getAttribute(beneftAssignmentCheckboxC, "class");
			String[] benfitAssignmentCData = benfitAssignmentCClass.split(" ");
			String actualBenfitAssignmentC = "";
			for(String s : benfitAssignmentCData) {
				if(s.equals("mat-checkbox-checked")) {
					actualBenfitAssignmentC = s;
				}
			}
			if(actualBenfitAssignmentC.equals("") && 
					benfitassignment_c.equals("YES")) {
				click(beneftAssignmentCheckboxC, "Benefit Assignment Checkbox C");
				actualBenfitAssignmentC = getAttribute(beneftAssignmentCheckboxC, "class");
				if(actualBenfitAssignmentC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box checked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not checked");
				}
				
			}else if(actualBenfitAssignmentC.equals("mat-checkbox-checked") && 
					benfitassignment_c.equals("NO")) {
				click(beneftAssignmentCheckboxC, "Benefit Assignment Checkbox C");
				actualBenfitAssignmentC = getAttribute(beneftAssignmentCheckboxC, "class");
				if(!actualBenfitAssignmentC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box unchecked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
				}
				
			}
		}
		
		//Prior payment amount
		if(Objects.nonNull(priorpaymentamt_c) && 
				!priorpaymentamt_c.equals("")) {
			priorPaymentAmountC.clear();
			sendKeys(priorPaymentAmountC, "Prior Payment Amount C", priorpaymentamt_c);
			String actualPriorPaymentAmountC = getAttribute(priorPaymentAmountC, "value");
			assertEquals(actualPriorPaymentAmountC, priorpaymentamt_c, "The amount "
					+"from prior payment amount field C is : "+actualPriorPaymentAmountC+
					" not : "+priorpaymentamt_c);
		}
		
		//EST Due Amount
		if(Objects.nonNull(estamountdue_c) && 
				!estamountdue_c.equals("")) {
			estAmountDueC.clear();
			sendKeys(estAmountDueC, "EST Due Amount C", estamountdue_c);
			String actualESTAmountDueC= getAttribute(estAmountDueC, "value");
			assertEquals(actualESTAmountDueC, estamountdue_c, "The amount "
					+"from prior payment amount field B is : "+actualESTAmountDueC+
					" not : "+estamountdue_c);
		}
		
		//primary payer
		if(Objects.nonNull(payertype_c) && !payertype_c.equals("")) {
			click(payerTypeDrodownC, "Payer Type");
			switch(payertype_c) {
			case "MEDICARE":{
				click(payerTypeMedicareOption, "MEDICARE");
				break;
			}
			case "NON MEDICARE":{
				click(payerTypeNonMedicareOption, "Non - MEDICARE");
				break;
			}
			default:
				report(LogStatus.WARNING, "Payer Type is not valid");
				break;
			}
		}

	}

	private void modifyPrimaryPayeBAndC(String payer_b,String healthplanid_b, 
			String relinfo_b, String benfitassignment_b, String priorpaymentamt_b, 
			String estamountdue_b, String payertype_b, String payer_c, 
			String healthplanid_c, String relinfo_c, String benfitassignment_c, 
			String priorpaymentamt_c, String estamountdue_c, 
			String payertype_c){
		
		addPrimaryPayeB(payer_b,healthplanid_b, relinfo_b, benfitassignment_b, priorpaymentamt_b, estamountdue_b, payertype_b);
				
		//Payer Name
		if(Objects.nonNull(payer_c) && !payer_c.equals("")) {
			String actualPayerC = getAttribute(payerC, "value");
			if(!actualPayerC.equals(payer_c)) {
				payerC.clear();
				sendKeys(payerC, "Payer C", payer_c);
				actualPayerC = getAttribute(payerC, "value");
				assertEquals(actualPayerC, payer_c, "The Value from Payer field is : "+
						actualPayerC+" not : "+payer_c);
			}else {
				report(LogStatus.INFO, "Payer Name C is Same and not changed");
			}
		}else {
			report(LogStatus.WARNING, "Payer Name C is empty or null");
		}
		
		//Health Plan ID C
		if(Objects.nonNull(healthplanid_c) && !healthplanid_c.equals("")) {
			String actualHealthPlanIDC = getAttribute(healthPlanIDC, "value");
			if(!actualHealthPlanIDC.equals(healthplanid_c)) {
				healthPlanIDC.clear();
				sendKeys(healthPlanIDC, "Health Plan ID C", healthplanid_c);
				actualHealthPlanIDC = getAttribute(healthPlanIDC, "value");
				assertEquals(actualHealthPlanIDC, healthplanid_c, "The Health Plan ID C from"
						+ " field is : "+actualHealthPlanIDC+" not : "+healthplanid_c);	
			}else {
				report(LogStatus.INFO, "Health Plan ID C is Same and not changed");
			}	
		}else {
			report(LogStatus.WARNING, "Health Plan ID C is empty or null");
		}
			
		//REL INFO C
		if(Objects.nonNull(relinfo_c) && !relinfo_c.equals("")) {
			String relInfoCClass = getAttribute(relInfoCheckBoxC, "class");
			String[] relInfoCData = relInfoCClass.split(" ");
		  	String actualRelInfoC = "";
			 for(String s : relInfoCData) {
				if(s.equals("mat-checkbox-checked")) {
					actualRelInfoC = s;
				}
			}
			if(actualRelInfoC.equals("") && relinfo_c.equals("YES")) {
				click(relInfoCheckBoxC, "Rel Info Checkbox C");
				actualRelInfoC = getAttribute(relInfoCheckBoxC, "class");
				if(actualRelInfoC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box checked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not checked");
				}
				
			}else if(actualRelInfoC.equals("mat-checkbox-checked") && 
				relinfo_c.equals("NO")) {
				click(relInfoCheckBoxC, "Rel Info Checkbox C");
				actualRelInfoC = getAttribute(relInfoCheckBoxC, "class");
				if(!actualRelInfoC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Actual Rel Info check box unchecked");
				}else {
					report(LogStatus.FAIL, "Actual Rel Info check box not unchecked");
				}
				
			}
		}
	
		//ASN BEN C
		if(Objects.nonNull(benfitassignment_c) && 
			!benfitassignment_c.equals("")) {
			String benfitAssignmentCClass = getAttribute(beneftAssignmentCheckboxC, "class");
			String[] benfitAssignmentCData = benfitAssignmentCClass.split(" ");
			String actualBenfitAssignmentC = "";
			for(String s : benfitAssignmentCData) {
				if(s.equals("mat-checkbox-checked")) {
					actualBenfitAssignmentC = s;
				}
			}
			if(actualBenfitAssignmentC.equals("") && 
					benfitassignment_c.equals("YES")) {
				click(beneftAssignmentCheckboxC, "Benefit Assignment Checkbox C");
				actualBenfitAssignmentC = getAttribute(beneftAssignmentCheckboxC, "class");
				if(actualBenfitAssignmentC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box checked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not checked");
				}
				
			}else if(actualBenfitAssignmentC.equals("mat-checkbox-checked") && 
					benfitassignment_c.equals("NO")) {
				click(beneftAssignmentCheckboxC, "Benefit Assignment Checkbox C");
				actualBenfitAssignmentC = getAttribute(beneftAssignmentCheckboxC, "class");
				if(!actualBenfitAssignmentC.contains("mat-checkbox-checked")) {
					report(LogStatus.PASS, "Benefit Assignment check box unchecked");
				}else {
					report(LogStatus.FAIL, "Benefit Assignment check box not unchecked");
				}
				
			}
		}
		
		//Prior payment amount
		if(Objects.nonNull(priorpaymentamt_c) && 
				!priorpaymentamt_c.equals("")) {
			priorPaymentAmountC.clear();
			sendKeys(priorPaymentAmountC, "Prior Payment Amount C", priorpaymentamt_c);
			String actualPriorPaymentAmountC = getAttribute(priorPaymentAmountC, "value");
			assertEquals(actualPriorPaymentAmountC, priorpaymentamt_c, "The amount "
					+"from prior payment amount field C is : "+actualPriorPaymentAmountC+
					" not : "+priorpaymentamt_c);
		}
		
		//EST Due Amount
		if(Objects.nonNull(estamountdue_c) && 
				!estamountdue_c.equals("")) {
			estAmountDueC.clear();
			sendKeys(estAmountDueC, "EST Due Amount C", estamountdue_c);
			String actualESTAmountDueC= getAttribute(estAmountDueC, "value");
			assertEquals(actualESTAmountDueC, estamountdue_c, "The amount "
					+"from prior payment amount field B is : "+actualESTAmountDueC+
					" not : "+estamountdue_c);
		}
		
		//primary payer
		if(Objects.nonNull(payertype_c) && !payertype_c.equals("")) {
			click(payerTypeDrodownC, "Payer Type");
			switch(payertype_c) {
			case "MEDICARE":{
				click(payerTypeMedicareOption, "MEDICARE");
				break;
			}
			case "NON MEDICARE":{
				click(payerTypeNonMedicareOption, "Non - MEDICARE");
				break;
			}
			default:
				report(LogStatus.WARNING, "Payer Type is not valid");
				break;
			}
		}

	}
	
	/**
	 * Click create button
	 * @return whether create buttn is clicked or not
	 */

	
	private boolean clickCreateButton() {
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");
		waitForLoadingToDisappear();
		if(createUB04Heading.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Add service in UB04
	 * @param revCode
	 * @param servicecode
	 * @param servicedate
	 * @param noofunits
	 * @param totalcharges
	 * @param nonCoveredCharges
	 * @return whether service is added or not
	 */
	private boolean addService(String revCode, String servicecode, String servicedate
			, String noofunits, String totalcharges, 
			String nonCoveredCharges) {
		
		boolean flag = false;
		
		waitUntilClickable(addService, 10);
		click(addService, "Add Service");
		
		waitUntilClickable(revenueCode, 10);
		sendKeys(revenueCode, "Revenue Code", revCode);
		waitForLoadingToDisappear();
		String revcode = dropdownOptions.replace("XX", revCode);
		WebElement revenueCodeElement = driver.findElement(By.xpath(revcode));
		click(revenueCodeElement, revCode);
		waitForLoadingToDisappear();
		
		if(Objects.nonNull(servicecode) && !servicecode.equals("")) {
			sendKeys(serviceCode, "HIPPS Code", servicecode);
			waitForLoadingToDisappear();
			String proccode = dropdownOptions.replace("XX", servicecode);
			WebElement serviceCodeElement = driver.findElement(By.xpath(proccode));
			click(serviceCodeElement, servicecode);
			waitForLoadingToDisappear();
		}
		
		if(Objects.nonNull(servicedate) && !servicedate.equals("")) {
			sendKeys(serviceDate, "Service Date", servicedate);
			String actualServiceDate = getAttribute(serviceDate, "value");
			assertEquals(actualServiceDate, servicedate, "The date from Servicce Date"
					+" field is : "+actualServiceDate+" not : "+servicedate);
		}
		
		sendKeys(units, "No Of Units", noofunits);
		String actualUnits = getAttribute(units, "value");
		assertEquals(actualUnits, noofunits, "The units from "
				+" field is : "+actualUnits+" not : "+noofunits);
		
		sendKeys(totalCharges, "Total Charges", totalcharges);
		String actualCharges = getAttribute(totalCharges, "value");
		assertEquals(actualCharges, totalcharges, "The units from "
				+" field is : "+actualCharges+" not : "+totalcharges);
		
		sendKeys(nonCoverageCharges, "Non Covered Charges", nonCoveredCharges);
		String actualnonCoveredCharges = getAttribute(nonCoverageCharges, "value");
		assertEquals(actualnonCoveredCharges, nonCoveredCharges, "The units from "
				+" field is : "+actualnonCoveredCharges+" not : "+nonCoveredCharges);
		
		
		click(saveService, "Save Service");
		putStaticWait(2);
		String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
		System.out.println(alerttext);
		waitForLoadingToDisappear();
		if(alerttext.contains("Saved Successfully")) {
			report(LogStatus.PASS, "Service added Successfully");
			flag = true;
		}else {
			report(LogStatus.FAIL,alerttext);
			try {
				throw new Exception("Cannot add service");
			}catch(Exception e) {
				report(LogStatus.FAIL, e.getMessage());
				e.printStackTrace();
				driver.findElement(By.xpath("//*[@role='alertdialog']")).click();
				waitUntilClickable(cancelService, 10);
				click(cancelService, "Cancel");
				putStaticWait(1);
				waitUntilClickable(unsavedChagesOK, 2);
				click(unsavedChagesOK, "OK");
			}
		}
		
		return flag;
		
		
	}
	
	private boolean modifyService(String revCode, String servicecode, String servicedate
			, String noofunits, String totalcharges, 
			String nonCoveredCharges) {
		
		boolean flag = false;
		
		waitUntilClickable(modifyService, 10);
		click(modifyService, "Modify Service");
		
		revenueCode.clear();
		waitUntilClickable(revenueCode, 10);
		sendKeys(revenueCode, "Revenue Code", revCode);
		waitForLoadingToDisappear();
		String revcode = dropdownOptions.replace("XX", revCode);
		WebElement revenueCodeElement = driver.findElement(By.xpath(revcode));
		click(revenueCodeElement, revCode);
		waitForLoadingToDisappear();
		
		if(Objects.nonNull(servicecode) && !servicecode.equals("")) {
			serviceCode.clear();
			sendKeys(serviceCode, "HIPPS Code", servicecode);
			waitForLoadingToDisappear();
			String proccode = dropdownOptions.replace("XX", servicecode);
			WebElement serviceCodeElement = driver.findElement(By.xpath(proccode));
			click(serviceCodeElement, servicecode);
			waitForLoadingToDisappear();
		}
		
		if(Objects.nonNull(servicedate) && !servicedate.equals("")) {
			serviceDate.clear();
			sendKeys(serviceDate, "Service Date", servicedate);
			String actualServiceDate = getAttribute(serviceDate, "value");
			assertEquals(actualServiceDate, servicedate, "The date from Servicce Date"
					+" field is : "+actualServiceDate+" not : "+servicedate);
		}
		
		units.clear();
		sendKeys(units, "No Of Units", noofunits);
		String actualUnits = getAttribute(units, "value");
		assertEquals(actualUnits, noofunits, "The units from "
				+" field is : "+actualUnits+" not : "+noofunits);
		
		totalCharges.clear();
		sendKeys(totalCharges, "Total Charges", totalcharges);
		String actualCharges = getAttribute(totalCharges, "value");
		assertEquals(actualCharges, totalcharges, "The units from "
				+" field is : "+actualCharges+" not : "+totalcharges);
		
		nonCoverageCharges.clear();
		sendKeys(nonCoverageCharges, "Non Covered Charges", nonCoveredCharges);
		String actualnonCoveredCharges = getAttribute(nonCoverageCharges, "value");
		assertEquals(actualnonCoveredCharges, nonCoveredCharges, "The units from "
				+" field is : "+actualnonCoveredCharges+" not : "+nonCoveredCharges);
		
		
		click(saveService, "Save Service");
		putStaticWait(2);
		String alerttext = getAttribute(driver.findElement(By.xpath("//*[@role='alertdialog']")),"aria-label");
		System.out.println(alerttext);
		waitForLoadingToDisappear();
		if(alerttext.contains("Saved Successfully")) {
			report(LogStatus.PASS, "Service added Successfully");
			flag = true;
		}else {
			report(LogStatus.FAIL,alerttext);
			try {
				throw new Exception("Cannot add service");
			}catch(Exception e) {
				report(LogStatus.FAIL, e.getMessage());
				e.printStackTrace();
				driver.findElement(By.xpath("//*[@role='alertdialog']")).click();
				waitUntilClickable(cancelService, 10);
				click(cancelService, "Cancel");
				putStaticWait(1);
				waitUntilClickable(unsavedChagesOK, 2);
				click(unsavedChagesOK, "OK");
			}
		}
		
		return flag;
		
		
	}

}
