package com.framework.tests;

import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tms.framework.actionDriver.Action;
import com.tms.framework.base.BaseClass;
import com.tms.framework.pages.LoginPage;
import com.tms.framework.pages.UserPage;
import com.tms.framework.utility.ExcelUtils;

public class UserPageTestCases extends BaseClass {

    LoginPage loginPage;
    UserPage userPage;

    @BeforeMethod
    public void setup() throws Throwable {
        initialization();
        loginPage = new LoginPage();
        userPage = new UserPage();
    }

    @Test
    public void addMultipleUsersFromExcel() throws Throwable {
        loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        userPage.clickUserIcon();
        userPage.clickAddUser();

        // Read user data from Excel
        List<String[]> userList = ExcelUtils.getUserData();

        // Iterate over user data and add users
        for (String[] userData : userList) {
            String firstName = userData[0];
            String lastName = userData[1];
            String email = userData[2];
            String mobile = userData[3];
            String privilege = userData[4];
            String client = userData[5];
            String password = userData[6];
            String reEnterPassword = userData[7];
            String domainName = userData[8];

            // Add user with data from Excel
            userPage.addUser(firstName, lastName, email, mobile, privilege, client, password, reEnterPassword, domainName);
        }
        
    }
    

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
}
