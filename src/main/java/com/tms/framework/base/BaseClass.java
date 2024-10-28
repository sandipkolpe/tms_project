package com.tms.framework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

import com.tms.framework.actionDriver.Action;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	
	@BeforeTest
	public void loadConfig() {
		
        try {
        	prop = new Properties();
            // Load properties from the config file
            FileInputStream fis = new FileInputStream("C:\\Users\\sunday\\eclipse-workspace\\Sandip\\TMS-Project\\Configuration\\config.properties");
            System.getProperty(("user.dir" )+ "\\Configuration\\config.properties");
            prop.load(fis);
            System.out.println("driver");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config properties file");
        }
    }
	public static void initialization() {
		
//		WebDriverManager.chromedriver().setup();
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			driver = new InternetExplorerDriver();
		}
		
		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		
		driver.get(prop.getProperty("url"));
	}
}

