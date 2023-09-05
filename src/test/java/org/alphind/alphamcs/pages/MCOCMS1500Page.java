package org.alphind.alphamcs.pages;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.alphind.alphamcs.base.CommonFunctions;
import org.alphind.alphamcs.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

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

	@FindBy(xpath = "//mat-label[text()='a. EMPLOYMENT (current or previous)']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement employmentStatusUnknown;
	
	@FindBy(xpath = "//mat-label[text()='b. AUTO ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement autoAccientUnknown;
	
	@FindBy(xpath = "//mat-label[text()='c. OTHER ACCIDENT']/parent::div/div/mat-radio-group/mat-radio-button[3]")
	private WebElement otherAccientUnknown;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Pat_sign_dt')]")
	private WebElement patientSignDate;
	
	@FindBy(xpath = "//input[contains(@formcontrolname,'Diag_1_')]")
	private WebElement diagnosis1;
	
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

	
	//provider search components
	@FindBy(xpath = "//span[contains(text(),'Provider Search')]/parent::button")
	private WebElement providerSearchButton;
	
	@FindBy(xpath = "(//mat-label[text()='Provider ID'])[2]/parent::label/ancestor::span/preceding-sibling::input")
	private WebElement providerId;
	
	@FindBy(xpath = "(//span[contains(text(),'Search')]/parent::button)[3]")
	private WebElement searchProvider;
	
	@FindBy(xpath = "//span[contains(text(),'Select Provider')]/parent::button")
	private WebElement selectProvider;
	
	
	//Add service components
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
	
	
	
	@FindBy(xpath = "(//mat-label[text()='Date']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement physicianSignDate;
	
	@FindBy(xpath = "//mat-checkbox[contains(@formcontrolname,'Phy_sign_31')]")
	private WebElement physicianSignCheckBox;
	
	@FindBy(xpath = "(//mat-label[text()='Phone']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement sitePhoneNumber;
	
	@FindBy(xpath = "(//mat-label[text()='b. Taxonomy Code']/following::mat-form-field/div/div/div/input)[1]")
	private WebElement billingTaxonomy;
	
	@FindBy(xpath = "//span[contains(text(),'Submit')]/parent::button")
	private WebElement submitClaim;
	
	@FindBy(xpath = "(//div[contains(text(),'My MCS Claim #')])[2]")
	private WebElement mymcsclaim;
	
	
	//FIlter Components
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
	
	@FindBy(xpath="(//table/tbody/tr)[3]/td")
    private WebElement firstServiceLine;
	
	@FindBy(xpath="//span[text()=' Modify ']")
    private WebElement lineModify;
	
	@FindBy(xpath="//span[text()=' Save ']/parent::button")
    private WebElement saveCMS;
	
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
	
	
	//Created by Nandhalala
	public String createAndSubmitClaim() {
		
		String patientid = dataMap.get("patientid"); 
		String patientSigndate = dataMap.get("patientSigndate");
		String diagnosisCode = dataMap.get("diagnosisCode");
		String providerID = dataMap.get("providerID");
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
		
		click(employmentStatusUnknown, "Unknown");
		
		click(autoAccientUnknown, "Unknown");
		
		click(otherAccientUnknown, "Unknown");
		
		sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
		putStaticWait(2);
		
		String actualPatientSignDate = getAttribute(patientSignDate, "value");
		assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
		+actualPatientSignDate+" not equals "+ patientSigndate);
		
		sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
		waitForLoadingToDisappear();
		
		String actualDiagnosis1 = getAttribute(diagnosis1, "value");
		
		assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "+actualDiagnosis1+
				" not equals "+diagnosisCode);
		
		putStaticWait(2);
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
		sendKeys(inputPOS, "Place Of service", POS);
		putStaticWait(2);
		driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();
		sendKeys(serviceCode, "Service Code", procCode);
		
		//Validation Steps-Service Code- Created by keerthana
		String actualServiceCode = getAttribute(serviceCode, "value");
		assertEquals(actualServiceCode,procCode,"Service Code from Field is"+actualServiceCode+"not equals"+ procCode);
		
		sendKeys(charges, "Charges", String.valueOf(serviceAmt));
		
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
		
		sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
		waitForLoadingToDisappear();
		putStaticWait(2);
		WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
		click(renderingtaxonomy, renderingtaxonomyCode);
		putStaticWait(2);
		click(saveService, "Save Service");
		putStaticWait(2);
		click(physicianSignCheckBox, "Physician Sign Check Box");
		
		sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
		
		//Validation Steps- physicianSignDate- Created by keerthana
		String actualphysicianSignDate= getAttribute(physicianSignDate,"value");
		assertEquals(actualphysicianSignDate,physiciansigndate,"physicianSignDate from field is "+actualphysicianSignDate+"not equals"+ physiciansigndate );
		
		sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
		
		//Validation steps- sitephonenumber- Created by keerthana
		String actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
		assertEquals(actualsitePhoneNumber,sitephno, "sitePhoneNumber from field is" +actualsitePhoneNumber+"not equals"+sitePhoneNumber );
		
		sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
		waitForLoadingToDisappear();
		WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
		click(billingtaxonmy, billingTaxonomyCode);
		waitForLoadingToDisappear();
		MyMCSNumber = getText(mymcsclaim);
		click(submitClaim, "Submit button");
		
		waitForLoadingToDisappear();
		
		return MyMCSNumber;
	}

	
	//Created by Nandhalala
		public String createAndSaveClaim() {
			
			String patientid = dataMap.get("patientid"); 
			String patientSigndate = dataMap.get("patientSigndate");
			String diagnosisCode = dataMap.get("diagnosisCode");
			String providerID = dataMap.get("providerID");
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
			
			click(employmentStatusUnknown, "Unknown");
			
			click(autoAccientUnknown, "Unknown");
			
			click(otherAccientUnknown, "Unknown");
			
			sendKeys(patientSignDate, "Patient Sign Date", patientSigndate);
			putStaticWait(2);
			
			String actualPatientSignDate = getAttribute(patientSignDate, "value");
			assertEquals(actualPatientSignDate, patientSigndate,"Patient Sign Date from field is "
			+actualPatientSignDate+" not equals "+ patientSigndate);
			
			sendKeys(diagnosis1, "Diagnosis 1", diagnosisCode);
			waitForLoadingToDisappear();
			
			String actualDiagnosis1 = getAttribute(diagnosis1, "value");
			
			assertEquals(actualDiagnosis1, diagnosisCode, "Diagnosis 1 from field is "+actualDiagnosis1+
					" not equals "+diagnosisCode);
			
			putStaticWait(2);
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
			sendKeys(inputPOS, "Place Of service", POS);
			putStaticWait(2);
			driver.findElement(By.xpath("//span[contains(text(),'"+POS+"')]")).click();
			sendKeys(serviceCode, "Service Code", procCode);
			
			//Validation Steps-Service Code- Created by keerthana
			String actualServiceCode = getAttribute(serviceCode, "value");
			assertEquals(actualServiceCode,procCode,"Service Code from Field is"+actualServiceCode+"not equals"+ procCode);
			
			sendKeys(charges, "Charges", String.valueOf(serviceAmt));
			
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
			
			sendKeys(renderingTaxonomy, "Taxonomy code", renderingtaxonomyCode);
			waitForLoadingToDisappear();
			putStaticWait(2);
			WebElement renderingtaxonomy = driver.findElement(By.xpath("//span[contains(text(),'"+renderingtaxonomyCode+"')]"));
			click(renderingtaxonomy, renderingtaxonomyCode);
			putStaticWait(2);
			click(saveService, "Save Service");
			putStaticWait(2);
			click(physicianSignCheckBox, "Physician Sign Check Box");
			
			sendKeys(physicianSignDate, "Physician Sign Date", physiciansigndate);
			
			//Validation Steps- physicianSignDate- Created by keerthana
			String actualphysicianSignDate= getAttribute(physicianSignDate,"value");
			assertEquals(actualphysicianSignDate,physiciansigndate,"physicianSignDate from field is "+actualphysicianSignDate+"not equals"+ physiciansigndate );
			
			sendKeys(sitePhoneNumber, "Site Phone Number", sitephno);
			
			//Validation steps- sitephonenumber- Created by keerthana
			String actualsitePhoneNumber= getAttribute(sitePhoneNumber,"value");
			assertEquals(actualsitePhoneNumber,sitephno, "sitePhoneNumber from field is" +actualsitePhoneNumber+"not equals"+sitePhoneNumber );
			
			sendKeys(billingTaxonomy, "Taxonomy code", billingTaxonomyCode);
			waitForLoadingToDisappear();
			WebElement billingtaxonmy = driver.findElement(By.xpath("//span[contains(text(),'"+billingTaxonomyCode+"')]"));
			click(billingtaxonmy, billingTaxonomyCode);
			waitForLoadingToDisappear();
			MyMCSNumber = getText(mymcsclaim);
			click(saveCMS, "Submit button");
			
			return MyMCSNumber;
		}
	
	
		//created by Mugundhan
	    public String updateAndSaveCMS1500Claim (String myMCSNumber) {


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
	    public String updateAndSubmitCMS1500Claim (String myMCSNumber) {


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

	        click(submitClaim,"Save Claim");

	        return myMCSNumber;

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

