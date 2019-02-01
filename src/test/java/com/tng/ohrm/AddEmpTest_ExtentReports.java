package com.tng.ohrm;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wd.util.DateUtils;
import com.wd.util.DriverFactory;
import com.wd.util.WebUtils;

public class AddEmpTest_ExtentReports {
  
	public WebDriver driver;

	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void openApplication() {

		try {
			report = new ExtentReports(
					"sample\\src\\..\\ExtentReport\\" + this.getClass().getSimpleName() + DateUtils.getUniqueid() + ".html");
			test = report.startTest("OpenApplication");

			// get driver
			driver = DriverFactory.getDriver("chrome");

			// specify page load timeout
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

			// specify findelement timeout
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			// maximize the browser
			driver.manage().window().maximize();

			// navigate to application
			driver.get("https://opensource-demo.orangehrmlive.com/");

			// asserttrue will expect a true status
			// if true is not returned by the statement then it throws assertionerror

			Assert.assertTrue(WebUtils.isElementExist(driver, By.id("txtUsername")), "VerifyLoginPageDisplayed");
			System.out.println("Application opened");
			test.log(LogStatus.PASS, "VerifyApplicationOpened", "Application opened");

			/*
			 * // verify application is opened if (WebUtils.isElementExist(driver,
			 * By.id("txtUsername"))) { System.out.println("Application opened"); } else {
			 * System.out.println("Login page is not opened"); throw new
			 * AssertionError("Login page is not opened"); }
			 */
		}

		catch (AssertionError ae) {
			String errImgPath = WebUtils.getScreenshot(driver);
			// report error
			System.out.println("Failed in Verification" + ae.getMessage());
			test.log(LogStatus.FAIL, "VerifyApplicationOpened", "Failed in Verification" + ae.getMessage()+test.addScreenCapture(errImgPath)+".jpg");
			throw ae;
		} catch (NullPointerException n) {
			String errImgPath = WebUtils.getScreenshot(driver);
			System.out.println("Failed due to driver not assigned");
			test.log(LogStatus.FAIL, "VerifyApplicationOpened", "Failed due to driver not assigned"+test.addScreenCapture(errImgPath)+".jpg");
			throw n;
		}
		finally {
			report.endTest(test);
			report.flush();
		}
	}

	@Test
	public void login() {

		try {
			
			test = report.startTest("Login");
			
			// enter text on user name
			driver.findElement(By.id("txtUsername")).sendKeys("Admin");
			test.log(LogStatus.PASS,"Text entered on username");

			// enter text on password
			driver.findElement(By.id("txtPassword")).sendKeys("admin123");
			test.log(LogStatus.PASS,"Text entered on password");

			// click on login button
			driver.findElement(By.id("btnLogin")).click();
			test.log(LogStatus.PASS,"Clicked on login button");

			Assert.assertTrue(WebUtils.isElementExist(driver, By.linkText("PIM")), "VerifyWelcomePageOpened");
			test.log(LogStatus.PASS,"VerifyWelcomePageOpened","Login Successful and welcome page is opened");

			/*
			 * // verify welcome page is opened if (WebUtils.isElementExist(driver,
			 * By.linkText("PIM"))) { System.out.println("Welcome Page is displayed"); }
			 * else { System.out.println("Welcome Page is displayed"); new
			 * Exception("Welcome Page is displayed"); }
			 */

		} catch (NoSuchElementException ns) {
			String errImgPath = WebUtils.getScreenshot(driver);
			System.out.println("Login failed due to element not found " + ns.getMessage());
			test.log(LogStatus.FAIL,"VerifyWelcomePageOpened","Login failed due to element not found " + ns.getMessage()+test.addScreenCapture(errImgPath)+".jpg");
			throw ns;
		} catch (AssertionError a) {
			System.out.println("Login failed due to Verification Failed " + a.getMessage());
			test.log(LogStatus.FAIL,"VerifyWelcomePageOpened","Login failed due to Verification Failed " + a.getMessage());
			throw a;
		}
		finally {
			report.endTest(test);
			report.flush();
		}
	}

	@Test(dependsOnMethods = "login")
	public void AddEmployee() {

		try {
			test = report.startTest("AddEmployee");
			
			// click on PIM
			driver.findElement(By.linkText("PIM")).click();
			test.log(LogStatus.PASS,"Clicked on PIM link");

			// click on add employee
			driver.findElement(By.partialLinkText("Add Emp")).click();
			test.log(LogStatus.PASS,"Clicked on add employee link");

			// enter data in first name
			driver.findElement(By.id("firstName")).sendKeys("selenium");
			test.log(LogStatus.PASS,"Data entered on first name");

			// enter data in last name
			driver.findElement(By.id("lastName")).sendKeys("hq");
			test.log(LogStatus.PASS,"Data entered on last name");

			// click on save
			driver.findElement(By.xpath("//input[@value='Save']")).click();
			test.log(LogStatus.PASS,"Clicked on save button");

			assertTrue(WebUtils.isElementExist(driver, By.xpath("//h1[text()='Personal Details']")), "VerifyEmployeeAdded");
			System.out.println("Employee is added");
			test.log(LogStatus.PASS, "VerifyEmployeeAdded", "Employee Added successfully");
			
			/*
			 * // verify welcome page is opened if (WebUtils.isElementExist(driver,
			 * By.xpath("//h1[text()='Personal Details']"))) {
			 * System.out.println("Employee Added"); } else {
			 * System.out.println("Failed to add employee"); new
			 * Exception("Failed to add employee"); }
			 */
		} catch (NoSuchElementException ns) {
			System.out.println("AddEmployee failed due to element not found " + ns.getMessage());
			test.log(LogStatus.FAIL, "VerifyEmployeeAdded","AddEmployee failed due to element not found " + ns.getMessage());
			throw ns;
		} catch (AssertionError a) {
			System.out.println("AddEmployee failed due to Verification Failed " + a.getMessage());
			test.log(LogStatus.FAIL, "VerifyEmployeeAdded","AddEmployee failed due to Verification Failed " + a.getMessage());
			throw a;
		}
		finally {
			report.endTest(test);
			report.flush();
		}

	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
		report.close();
	}

	
}
