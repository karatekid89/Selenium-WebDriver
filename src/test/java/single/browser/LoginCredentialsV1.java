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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import page.elements.LoginPage;

public class LoginCredentialsV1 {

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

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void navigateToLoginPage() throws IOException, InterruptedException {

		test = extent.createTest("Admin login.",
				"This is to test if the admin can login to the system using their credentials.");

		LoginPage lp = new LoginPage(driver);

		sa = new SoftAssert();

		test.log(Status.INFO, "Starting the test.");

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

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

		try {

			test.pass("Successfully entered the username.");

			lp.EnterUsername("Admin");
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

		try {

			test.pass("Successfully entered the password.");

			lp.EnterPassword("admin123");
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

		if (actualScreenTitle.equals(actualScreenTitle)) {

			test.pass("Successfully login the account.");

			// Take a screenshot if pass.
			screenshotFileName = "05.(Passed)_loginPage";
			file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenshotFileLocation + "/" + className + "/" + dateNow + "_" + timeNow
					+ "/" + screenshotFileName + ".png"));

		} else {

			test.fail("Failed to login the account."
					.concat("<br/> Actual Screen Title : [ " + actualScreenTitle + " ]")
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

}
