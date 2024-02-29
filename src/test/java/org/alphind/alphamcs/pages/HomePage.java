package org.alphind.alphamcs.pages;

import org.alphind.alphamcs.base.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class HomePage extends CommonFunctions {

	WebDriver driver;

	private static final Logger log = LogManager.getLogger(HomePage.class);

	@FindBy(xpath = "//*[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "(//a[text()='Click here'])[2]")
	WebElement portalLoginLink;

	@FindBy(xpath = "(//a[text()='Click here'])[1]")
	WebElement mcoLoginLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void selectPortalLogin() {
		click(portalLoginLink, "Portal Login Link");
	}

	public MCOLoginPage selectMCOLogin() {
		click(mcoLoginLink, "MCO Login Link");
		return new MCOLoginPage(driver);
	}

}
