package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOCMS1500Page extends CommonFunctions {

	WebDriver driver;
	static String patientLastName;
	static String patientFirstName;

	private static final Logger log = LogManager.getLogger(MCOCMS1500Page.class);

	@FindBy(xpath = "//*[contains(text(),'Create')]")
	WebElement createButton;

	// patient Search elements

	////////////////// Implementations

	public MCOCMS1500Page(WebDriver driver) {
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
