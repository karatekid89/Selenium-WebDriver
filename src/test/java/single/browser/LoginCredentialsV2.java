package single.browser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import page.elements.LoginPage;
import utils.ExcelUtils;

public class LoginCredentialsV2 {

	WebDriver driver;

	// This is the location where the extent report is saved.
	String extentReportFileLocation = "D:/Documents/Projects/Selenium/SeleniumWebdriver/test-output/evidences";

	// This is the location where the screenshots are saved.
	String screenshotFileLocation = "D:/Documents/Projects/Selenium/SeleniumWebdriver/test-output/evidences";

	SimpleDateFormat fileDateFormatter;
	SimpleDateFormat fileTimeFormatter;
	Date date;
	String dateNow;
	String timeNow;
	String className;
	String screenshotFileName;
	ExtentSparkReporter spark;
	ExtentReports extent;
	ExtentTest test;
	File file;
	SoftAssert sa;

	String actualScreenTitle;
	String expectedScreenTitle;

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest() {

		className = this.getClass().getName();

		fileDateFormatter = new SimpleDateFormat("MM-dd-yyyy");
		fileTimeFormatter = new SimpleDateFormat("HHmmss");
		date = new Date();

		dateNow = fileDateFormatter.format(date);
		timeNow = fileTimeFormatter.format(date);

		spark = new ExtentSparkReporter(
				extentReportFileLocation + "/" + className + "/" + dateNow + "_" + timeNow + "/" + className + ".html");

		extent = new ExtentReports();
		extent.attachReporter(spark);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1280x1024");
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("useAutomationExtension", false);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "UserCredentials")
	public void navigateToLoginPage(String url, String username, String password)
			throws IOException, InterruptedException {

		test = extent.createTest("Admin login.",
				"This is to test if the admin can login to the system using their credentials.");

		LoginPage lp = new LoginPage(driver);

		sa = new SoftAssert();

		test.log(Status.INFO, "Starting the test.");

		// Going to the specified url.
		driver.get(url);

		// Getting the screen title for validation.
		try {

			actualScreenTitle = driver
					.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/h5[1]")).getText();
			Thread.sleep(3500);

		} catch (Exception e) {

			test.fail("EXEPTION ERROR! Failed to navigate to login page."
					.concat("<br/> Error Message : [ " + e.getMessage() + " ]")
					.concat("<br/> Error Cause : [ " + e.getCause() + " ]"));

			screenshotFileName = "01.(Failed)_exeptionError";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			System.out.println("Error Message : " + e.getMessage());
			System.out.println("Error Cause : " + e.getCause());
			e.printStackTrace();

		}

		expectedScreenTitle = "Login";

		// Validating the screen title.
		if (actualScreenTitle.equals(actualScreenTitle)) {

			test.pass("Successfully navigated to login page.");

			// Take a screenshot if pass.
			screenshotFileName = "01.(Passed)_loginPage";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} else {

			test.fail("Failed to navigate to login page."
					.concat("<br/> Actual Screen Title : [ " + actualScreenTitle + " ]")
					.concat("<br/> Expected Screen Title : [ " + expectedScreenTitle + " ]"));

			// Take a screenshot if failed.
			screenshotFileName = "01.(Failed)_loginPage";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			sa.fail("Actual Screen Title : [" + actualScreenTitle + "] Expected Screen Title : [" + expectedScreenTitle
					+ "]");

		}

		// Entering the username.
		try {

			test.pass("Successfully entered the username.");

			lp.EnterUsername(username);
			Thread.sleep(2000);

			screenshotFileName = "02.(Passed)_enterUsername";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} catch (Exception e) {

			test.fail("EXEPTION ERROR! Failed to enter enter the username."
					.concat("<br/> Error Message : [ " + e.getMessage() + " ]")
					.concat("<br/> Error Cause : [ " + e.getCause() + " ]"));

			screenshotFileName = "02.(Failed)_enterUsername";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			System.out.println("Error Message : " + e.getMessage());
			System.out.println("Error Cause : " + e.getCause());
			e.printStackTrace();

		}

		// Entering the password.
		try {

			test.pass("Successfully entered the password.");

			lp.EnterPassword(password);
			Thread.sleep(2000);

			screenshotFileName = "03.(Passed)_enterPassword";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} catch (Exception e) {

			test.fail("EXEPTION ERROR! Failed to enter enter the password."
					.concat("<br/> Error Message : [ " + e.getMessage() + " ]")
					.concat("<br/> Error Cause : [ " + e.getCause() + " ]"));

			screenshotFileName = "03.(Failed)_enterPassword";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			System.out.println("Error Message : " + e.getMessage());
			System.out.println("Error Cause : " + e.getCause());
			e.printStackTrace();

		}

		// Clicking the login button.
		try {

			test.pass("Successfully clicked the login button.");

			lp.ClickLoginButton();
			Thread.sleep(3500);

			screenshotFileName = "04.(Passed)_clickLoginButton";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} catch (Exception e) {

			test.fail("EXEPTION ERROR! Failed to click the login button."
					.concat("<br/> Error Message : [ " + e.getMessage() + " ]")
					.concat("<br/> Error Cause : [ " + e.getCause() + " ]"));

			screenshotFileName = "04.(Failed)_clickLoginButton";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			System.out.println("Error Message : " + e.getMessage());
			System.out.println("Error Cause : " + e.getCause());
			e.printStackTrace();

		}

		// Getting the screen title for validation.
		try {

			actualScreenTitle = driver.findElement(By.xpath("//header/div[1]/div[1]/span[1]/h6[1]")).getText();

		} catch (Exception e) {

			test.fail("EXEPTION ERROR! Failed to login the account."
					.concat("<br/> Error Message : [ " + e.getMessage() + " ]")
					.concat("<br/> Error Cause : [ " + e.getCause() + " ]"));

			screenshotFileName = "05.(Failed)_exeptionError";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			System.out.println("Error Message : " + e.getMessage());
			System.out.println("Error Cause : " + e.getCause());
			e.printStackTrace();

		}

		expectedScreenTitle = "Dashboard";

		// Validating the screen title.
		if (actualScreenTitle.equals(actualScreenTitle)) {

			test.pass("Successfully login the account.");

			// Take a screenshot if pass.
			screenshotFileName = "05.(Passed)_loginPage";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} else {

			test.fail("Failed to login the account.".concat("<br/> Actual Screen Title : [ " + actualScreenTitle + " ]")
					.concat("<br/> Expected Screen Title : [ " + expectedScreenTitle + " ]"));

			// Take a screenshot if failed.
			screenshotFileName = "05.(Failed)_loginPage";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

			sa.fail("Actual Screen Title : [" + actualScreenTitle + "] Expected Screen Title : [" + expectedScreenTitle
					+ "]");

		}

		test.info("Test completed");
		sa.assertAll();

	}

	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
		extent.flush();

	}

	@DataProvider(name = "UserCredentials")
	public Object[][] getData() {
		String excelPath = "D:/Documents/Projects/Selenium/SeleniumWebdriver/test-data/UserCredentails.xlsx";
		String sheetName = "UserAccount";

		Object data[][] = testData(excelPath, sheetName);
		return data;

	}

	public Object[][] testData(String excelPath, String sheetName) {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				String cellData = excel.getCellDataString(i, j);
				// System.out.print(cellData);
				data[i - 1][j] = cellData;

			}

		}
		return data;

	}

}
