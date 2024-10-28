package com.framework.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tms.framework.base.BaseClass;
import com.tms.framework.pages.LoginPage;

public class LoginPageUITestCases extends BaseClass {

    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        initialization();
        loginPage = new LoginPage();
    }

    @Test
    public void verifyLogoDisplayed() {
        boolean isLogoVisible = loginPage.isLogoDisplayed();
        Assert.assertTrue(isLogoVisible, "Logo is not displayed as expected");
    }

    @Test
    public void verifyPageTitle() {
        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualTitle, "TMS Admin Portal", "Page title does not match");
    }

    @Test
    public void verifyWelcomeHeaderText() {
        String actualHeaderText = loginPage.getWelcomeHeaderText();
        Assert.assertEquals(actualHeaderText, "Hello, Welcome Back", "Welcome header text does not match");
    }

    @Test
    public void verifyLoginTitleText() {
        String actualLoginTitle = loginPage.getLoginTitleText();
        Assert.assertEquals(actualLoginTitle, "Login", "Login title does not match");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
