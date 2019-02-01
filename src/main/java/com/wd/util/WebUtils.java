package com.wd.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebUtils {
	
	
	public static boolean isElementExist(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		}
		catch(NoSuchElementException ns) {
			return false;
		}
				
	}
	
	public static String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("sample\\src\\..\\ExtentReport\\ErrImg\\Demo"+DateUtils.getUniqueid()+".jpg");
		//https://commons.apache.org/proper/commons-io/download_io.cgi
		//download commons-io-2.6-bin.zip
		
		try {
			FileUtils.copyFile(src, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dst.getAbsolutePath();
	}

}
