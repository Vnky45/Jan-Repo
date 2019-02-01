package com.tng.ohrm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wd.util.DriverFactory;



public class OhrmAddEmpTest1 {
	public WebDriver driver;

	@BeforeClass
	public void openApplication() {
		
		driver=DriverFactory.getDriver("chrome");
		
		System.out.println("this is my first change");

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@Test
	public void login() {
		
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");

		driver.findElement(By.id("txtPassword")).sendKeys("admin123");

		driver.findElement(By.id("btnLogin")).click();
	}

	@Test(dependsOnMethods = "login")
	public void AddEmployee() {
		
		driver.findElement(By.linkText("PIM")).click();

		driver.findElement(By.partialLinkText("Add Emp")).click();

		driver.findElement(By.id("firstName")).sendKeys("selenium");

		driver.findElement(By.id("lastName")).sendKeys("QA");

		driver.findElement(By.xpath("//input[@value='Save']")).click();

	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
	}

}
