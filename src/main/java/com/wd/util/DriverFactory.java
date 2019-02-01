package com.wd.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

	public static WebDriver getDriver(String brname) {
		
		WebDriver driver;
		
		switch (brname.toLowerCase()) {
		case "chrome":
			
			System.setProperty("webdriver.chrome.driver", "E:\\selenes\\BrowserDrivers\\chromedriver.exe");
			driver= new ChromeDriver();
			
			break;
		
		case "firefox":
			
			System.setProperty("webdriver.gecko.driver", "E:\\selenes\\BrowserDrivers\\geckodriver.exe");
			driver= new FirefoxDriver();
			
			break;
			
		  case "edge":
			
			System.setProperty("webdriver.edge.driver", "E:\\selenes\\BrowserDrivers\\MicrosoftWebDriver.exe");
			driver= new EdgeDriver();
			
			break;
			
		case "ie":
			
			System.setProperty("webdriver.ie.driver", "E:\\selenes\\BrowserDrivers\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			
			break;
			
			default:
				System.out.println("no driver found");
				driver= null;
			break;
		}
		
		return driver;
	}
}