package org.alphind.alphamcs.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.alphind.alphamcs.pages.MCOLoginPage;
import org.alphind.alphamcs.util.ConfigurationReader;
import org.alphind.alphamcs.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 * created by  Abhishek.K.
 * 
 * You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 * is prohibited without prior and proper license agreement
 * 
 * The TestBase class is the base class to fetch environment specific
 * configuration parameters from Jenkins/Maven. Based on the parameters, it
 * performs the browser setup and tear-down functions.
 * 
 * 
 */

public class TestBase {

	protected static WebDriver driver;
	public static Properties envConfig;
	public static String[][] testData;
	public static Map<String, String> dataMap;
	
	public static List<Map<String, String>> dataMapList;
	
	WebDriverWait wait;

	public static ExtentReports report;
	public static ExtentTest test;
	String reportPath = new File("").getAbsolutePath().toString().trim() + "/reports/";

	private static final Logger log = LogManager.getLogger(MCOLoginPage.class);

	// Environment value fetched from POM with 'staging' and 'production' being the
	// valid values
	//public static final String ENV = System.getProperty("env");// , "Production");
	public static final String ENV = ConfigurationReader.getEnvironment();// , "Production");

	// BROWSER value fetched from POM with Chrome being the default value
	//private static final String BROWSER = System.getProperty("browser");// , "Chrome");
	private static final String BROWSER = ConfigurationReader.getBrowser();

	// Automation suite setup method to configure and instantiate a particular
	// browser
	@BeforeSuite
	public void suiteSetup() throws Exception {

		// Browser configuration - can add more browsers and remote driver here
		System.out.println(BROWSER);
		if (BROWSER.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup(); // can also use set property method for browser executables
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-geolocation");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if (BROWSER.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			throw new RuntimeException("Browser type unsupported");
		}

		// Setting implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		// Setting WebDriverWait with max timeout value of 20 seconds
		wait = new WebDriverWait(driver, 20);

		//System.out.println(ENV);
		// Environment specific properties file loading
		InputStream configFile = new FileInputStream(
				System.getProperty("user.dir") + "\\config\\" + ENV + ".properties");
		envConfig = new Properties();
		envConfig.load(configFile);
		// readTestData();

	}

	@BeforeMethod()
	public void loadBaseUrl(Method method) throws Exception {
		report = new ExtentReports(reportPath + this.getClass().getSimpleName() + ".html", false);
		test = report.startTest(method.getName());

		String baseURL = envConfig.getProperty("baseUrl");
		driver.get(baseURL);
		report(LogStatus.INFO, "Opened the url for - " + ENV + " environment - " + baseURL);
		report(LogStatus.INFO, "Running Test " + method.getName());
		readTestDataInMap(method.getName());

	}

	@AfterMethod
	public void screenshotAndDeleteCookies(ITestResult testResult) throws IOException {
		// Taking screenshot in case of failure
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
					+ Arrays.toString(testResult.getParameters()) + ".jpg"));
		}

		// Deleting cookies
		driver.manage().deleteAllCookies();
		report.endTest(test);
		report.flush();
		report.close();
	}

	@AfterSuite
	public void suiteTearDown() {
		driver.quit();
	}

	public Object[][] readTestData() throws Exception {

		testData = ExcelUtil.getExcelDataIn2DArray("testData//AlphaPlusTestData.xlsx", "testDataSheet");
		return testData;
	}

	public void readTestDataInMap(String methodName) throws Exception {

		dataMap = ExcelUtil.getTestCaseDataInMap("testData//AlphaPlusTestData.xlsx", "testDataSheet", methodName);
		dataMapList = ExcelUtil.getTestCasesDataInMap("testData//AlphaPlusTestData.xlsx", "testDataSheet", methodName);
		// return dataMap;
	}

	public void report(com.relevantcodes.extentreports.LogStatus logStatus, String message) {
		
		switch (logStatus) {
		case ERROR:
			break;
		case FAIL:{
			try {
				File scrFile = ((TakesScreenshot) driver).
						getScreenshotAs(OutputType.FILE);
				
				File destination = new File(System.getenv("user.dir")+
						"\\errorScreenshots\\ Failed" + ".png");
				
				FileUtils.copyFile(scrFile, destination);
				
				FileInputStream fis = new FileInputStream(destination);
				
				fis.read();
				
				byte[] bytes = Files.readAllBytes(destination.toPath());
				
				String encodedBase64 = new String(Base64.encodeBase64String(bytes));
				
				fis.close();
				
				test.log(logStatus, message);
				
				log.info(message);
				
				test.log(logStatus, 
						"<img src = \"data:image/png;base64,"+encodedBase64.toString()+
						"\" width=\"1200\" height=\"900\"/>");
				
				log.info("<img src = \"data:image/png;base64,"+encodedBase64+"\" "
						+ "width=\"800\" height=\"400\"/>");
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		}
			break;
		case FATAL:
			break;
		case INFO:{
			test.log(logStatus, message);
			log.info(message);
		}
			break;
		case PASS:{
			test.log(logStatus, message);
			log.info(message);
		}
			break;
		case SKIP:
			break;
		case UNKNOWN:
			break;
		case WARNING:
			break;
		default:
			break;
		
		}
		
//		if(logStatus.equals(LogStatus.FAIL) || logStatus == LogStatus.FAIL) {
//			try {
//				String datetime = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss z")
//						.format(new Date());
//				
//				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//				File destination = new File("errorScreenshots\\ Failed "+datetime + "- .jpg");
//				FileUtils.copyFile(scrFile, 
//						destination);
//				FileInputStream fis = new FileInputStream(destination);
//				
//				byte [] filebytes = new byte[(int)destination.length()];
//				
//				fis.read();
//				
//				String encodedBase64 = new String(Base64.encodeBase64String(filebytes));
//				
//				fis.close();
//				
//				test.log(logStatus, message);
//				log.info(message);
//				
//				test.log(logStatus, encodedBase64);
//				log.info( encodedBase64);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else {
//			test.log(logStatus, message);
//			log.info(message);
//		}
		
	}

}
