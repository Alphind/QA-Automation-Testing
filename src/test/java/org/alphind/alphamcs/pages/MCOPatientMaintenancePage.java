package org.alphind.alphamcs.pages;

import java.util.List;
import java.util.Objects;

import org.alphind.alphamcs.base.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

public class MCOPatientMaintenancePage extends CommonFunctions {

	private WebDriver driver;
	
	private final String dropdownOptions = "//span[@class = 'mat-option-text' "
			+ "and contains(text(),'XX')]";
	
	@FindBy(xpath = "//div[text()='Patient Maintenance']")
	private WebElement patientMaintenanceHeading;
	
	@FindBy(xpath = "//tbody/tr")
	private List<WebElement> grid;
	
	@FindBy(xpath = "//tbody/tr[1]")
	private WebElement landingPageFirstRecord;
	
	@FindBy(xpath = "//span[contains(text(),'View')]/parent::button")
	private WebElement viewButton;
	
	@FindBy(xpath = "//span[contains(text(),'View Patient')]")
	private WebElement viewPatientPopup;
	
	@FindBy(xpath = "//span[contains(text(),'Close')]/parent::button")
	private WebElement viewPopupClose;
	
	@FindBy(xpath = "//span[contains(text(),'Filter')]/parent::button")
	private WebElement filterButton;
	
	@FindBy(xpath = "//span[contains(text(),'Patient Filter')]")
	private WebElement filterHeading;
	
	@FindBy(xpath = "//input[@formcontrolname='Pat_ln']")
	private WebElement filterLastName;
	
	@FindBy(xpath = "//input[@formcontrolname='Pat_fn']")
	private WebElement filterFirstName;
	
	@FindBy(xpath = "//input[@formcontrolname='Pat_dob']")
	private WebElement filterDOB;
	
	@FindBy(xpath = "//input[@formcontrolname='Mrn_no']")
	private WebElement filterMRNNumber;
	
	@FindBy(xpath = "//input[@formcontrolname = 'Pat_ins']")
	private WebElement filterInsuranceNumber;
	
	@FindBy(xpath = "//input[@formcontrolname='Pat_ssn']")
	private WebElement filterPatientSSN;
	
	@FindBy(xpath = "//input[@formcontrolname='Pat_id']")
	private WebElement filterPatientID;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Actives']")
	private WebElement filterActiveStatus;
	
	@FindBy(xpath = "//mat-select[@formcontrolname='Actives']/div/div/span/span")
	private WebElement filterActiveStatusText;
	
	@FindBy(xpath = "//span[contains(text(),'Clear')]/parent::button")
	private WebElement filterClearButton;
	
	@FindBy(xpath = "//span[contains(text(),'Cancel')]/parent::button")
	private WebElement filterCancelButton;
	
	@FindBy(xpath = "//span[contains(text(),'Search')]/parent::button")
	private WebElement filterSearchButton;
	
	@FindBy(xpath = "//h4/mat-toolbar/button")
	private WebElement filterClose;
	
	public MCOPatientMaintenancePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPatientMaintenancePageDisplayed() {
		if(patientMaintenanceHeading.isDisplayed()) {
			report(LogStatus.PASS,"Patient Maintenance is Displayed.");
			return true;
		}else {
			report(LogStatus.FAIL,"Patient Maintenance is not Displayed.");
			return false;
		}
	}
	
	public boolean clickFilter() {
		waitUntilClickable(filterButton, 10);
		click(filterButton, "Filter");
		if(filterHeading.isDisplayed()) {
			report(LogStatus.PASS,"Filter Popup is Displayed.");
			return true;
		}else {
			report(LogStatus.FAIL,"Filter Popup is not Displayed.");
			return false;
		}
	}
	
	public MCOPatientMaintenancePage setFilterLastName(String lastName) {
		if(!getAttribute(filterLastName, "value").equals("") || 
				Objects.nonNull(getAttribute(filterLastName, "value")))
			filterLastName.clear();
		sendKeys(filterLastName, "Last Name", lastName);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterFirstName(String firstName) {
		if(!getAttribute(filterFirstName, "value").equals("") || 
				Objects.nonNull(getAttribute(filterFirstName, "value")))
			filterFirstName.clear();
		sendKeys(filterFirstName, "First Name", firstName);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterDOB(String dob) {
		if(!getAttribute(filterMRNNumber, "value").equals("") || 
				Objects.nonNull(getAttribute(filterMRNNumber, "value")))
			filterMRNNumber.clear();
		sendKeys(filterDOB, "Date Of Birth", dob);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterMRNNumber(String mrnNumber) {
		if(!getAttribute(filterMRNNumber, "value").equals("") || 
				Objects.nonNull(getAttribute(filterMRNNumber, "value")))
			filterMRNNumber.clear();
		sendKeys(filterMRNNumber, "MRN Number", mrnNumber);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterInsuranceNumber(String insuranceNumber) {
		if(!getAttribute(filterInsuranceNumber, "value").equals("") || 
				Objects.nonNull(getAttribute(filterInsuranceNumber, "value")))
			filterInsuranceNumber.clear();
		sendKeys(filterInsuranceNumber, "Insurance Number", insuranceNumber);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterSSnNumber(String ssnNumber) {
		if(!getAttribute(filterPatientSSN, "value").equals("") || 
				Objects.nonNull(getAttribute(filterPatientSSN, "value")))
			filterPatientSSN.clear();
		sendKeys(filterPatientSSN, "SSN Number", ssnNumber);
		return this;
	}

	public MCOPatientMaintenancePage setFilterPatientID(String patientID) {
		if(!getAttribute(filterPatientID, "value").equals("") || 
				Objects.nonNull(getAttribute(filterPatientID, "value")))
			filterPatientID.clear();
		sendKeys(filterPatientID, "Patient ID", patientID);
		return this;
	}
	
	public MCOPatientMaintenancePage setFilterActiveStatus(String activeStatus) {
		click(filterActiveStatus, "Status");
		if(activeStatus.equalsIgnoreCase("Active")) {
			String status =dropdownOptions.replace("XX", "Active");
			WebElement statusDropdown = driver.findElement(By.xpath(status));
			statusDropdown.click();
			getText(filterActiveStatusText);
			if(getText(filterActiveStatusText).equalsIgnoreCase(activeStatus))
				report(LogStatus.INFO,"Selected Active Status.");
			else
				report(LogStatus.WARNING,"Active not Status.");
		}else if(activeStatus.equalsIgnoreCase("Inactive")) {
			String status =dropdownOptions.replace("XX", "Inactive");
			WebElement statusDropdown = driver.findElement(By.xpath(status));
			statusDropdown.click();
			getText(filterActiveStatusText);
			if(getText(filterActiveStatusText).equalsIgnoreCase(activeStatus))
				report(LogStatus.INFO,"Selected Inactive Status.");
			else
				report(LogStatus.WARNING,"Inactive not Status.");
		}else {
			report(LogStatus.WARNING, "Invalid Active status");
		}
		return this;
	}
	
	public void clickFilterSearch() {
		click(filterSearchButton, "Search");
		waitForLoadingToDisappear();
		grid.forEach(element -> {
			System.out.println(getText(element));
		});
	}
	
	public void clickFilterCancel() {
		click(filterCancelButton, "Cancel");
	}

	public void clickFilterClear() {
		click(filterClearButton, "Clear");
	}
	
	public void clickFilterClose() {
		click(filterClose, "Close");
	}
	
	public boolean selectFirstRow() {
		click(landingPageFirstRecord, "First Row");
		waitForLoadingToDisappear();
		String firstRowClass = getAttribute(landingPageFirstRecord, "class");
		if(firstRowClass.contains("example-expanded-row")) {
			report(LogStatus.PASS, "Selected First Record");
			return true;
		}else {
			report(LogStatus.PASS, "Not Selected First Record");
			return false;
		}
	}
	
	public void viewPatient() {
		String patientID = dataMap.get("patientId");
		
		clickFilter();
		
		setFilterPatientID(patientID).clickFilterSearch();
		
		selectFirstRow();
		
		click(viewButton, "View");
		
		waitForLoadingToDisappear();
	}
	
	public boolean isViewPageDisplayed() {
		if(viewPatientPopup.isDisplayed()) {
			report(LogStatus.PASS,"View Popup is displayed.");
			return true;
		}else {
			report(LogStatus.FAIL,"View Popup is not displayed.");
			return false;
		}
	}
	
}
