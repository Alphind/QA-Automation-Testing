package org.alphind.alphamcs.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.alphind.alphamcs.pages.MCOHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

/**
 * The TestBase class is the base class to fetch environment specific
 * configuration parameters from Jenkins/Maven. Based on the parameters, it
 * performs the browser setup and tear-down functions.
 * 
 * @author Abhishek.K
 */

public class CommonFunctions extends TestBase {

	private static final Logger log = LogManager.getLogger(CommonFunctions.class);

	public void tabAndEnter() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	public void clickUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void click(WebElement element, String name) {
		try {
			element.click();
			report(LogStatus.INFO, "Clicked on - " + name);
		} catch (Exception e) {
			report(LogStatus.FAIL, "failed to click on - " + name);
			if (MCOHomePage.errorMessage.isDisplayed()) {
				report(LogStatus.FAIL, MCOHomePage.errorMessage.getText());
			}
			e.printStackTrace();
		}
	}

	public void sendKeys(WebElement element, String name, String value) {
		element.sendKeys(value);
		report(LogStatus.INFO, "Entered '" + value + "' in " + name);
	}

	public void selectDropDown(WebElement element, String name, String value) {
		element.click();
		putStaticWait(1);
		driver.findElement(By.xpath("//*[text()='" + value + "' and @class='mat-option-text']")).click();
		report(LogStatus.INFO, "Selected '" + value.trim() + "' in the dropdown " + name);
	}

	public void clickCheckbox(WebElement element, String name) {
		element.click();
		report(LogStatus.INFO, "Clicked on checkbox- " + name);
	}

	public static String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(99999999);
		return Integer.toString(number);
	}

	public String getRandomLastName() {
		return "TestLastName" + getRandomNumberString();
	}

	public String getRandomFirstName() {
		return "TestFirstName" + getRandomNumberString();
	}

	public void putStaticWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitUntilClickable(WebElement ele, int seconds) {
		new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(ele));
	}

	public String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(new Date());
	}

	public String getFutureDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.YEAR, 1);
		dt = c.getTime();
		return df.format(dt);
	}

}
