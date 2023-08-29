package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOUB04Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	//private static final Logger log = LogManager.getLogger(MCOUB04Page.class);

	@FindBy(xpath = "//*[contains(text(),'Create')]")
	WebElement createButton;

	// patient Search elements

	////////////////// Implementations

	public MCOUB04Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String createClaim() {

		String claimId = "";
		waitUntilClickable(createButton, 30);
		putStaticWait(2);
		click(createButton, "Create button");

		return claimId;
	}

}
