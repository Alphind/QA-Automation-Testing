package org.alphind.alphamcs.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MCOSysUsersPage {

	private WebDriver driver;
	
	private static String userLastName;
	
	private static String userFirstName;
	
	private static final Logger log = LogManager.getLogger(MCOSysUsersPage.class);
	
	@FindBy(xpath = "//span[contains(text(),'Create')]/parent::button")
	private WebElement createButton;
	
	public MCOSysUsersPage(WebDriver driver) {
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
}
