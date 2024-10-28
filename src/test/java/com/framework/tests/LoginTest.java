package com.framework.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tms.framework.base.BaseClass;
import com.tms.framework.pages.Dashboard;
import com.tms.framework.pages.LoginPage;

public class LoginTest extends BaseClass {

    LoginPage loginPage;
    Dashboard dashboard;

    @BeforeMethod
    public void setup() throws Throwable {
        initialization();
        loginPage = new LoginPage();
    }

    @Test
    public void validateLogin() throws Throwable {
        dashboard = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

        Thread.sleep(3000);  // Wait for the page to load
        
        String actualURL = dashboard.getCurrentURL();
        String expectedURL = "https://admin.dev.tms.sundayapi.com/dashboard";
        Assert.assertEquals(actualURL, expectedURL, "Current URL does not match expected URL");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
