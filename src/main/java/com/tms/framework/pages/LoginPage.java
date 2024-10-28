package com.tms.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tms.framework.actionDriver.Action;
import com.tms.framework.base.BaseClass;

public class LoginPage extends BaseClass {
    
    // Web elements on the page
    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[1]/img")
    WebElement logoElement;
    
    @FindBy(xpath = "//div[contains(text(), 'Hello, Welcome Back')]")
    WebElement welcomeHeaderText;
    
    @FindBy(xpath = "//div[contains(text(),'Login')]")
    WebElement loginTitleElement;
    
    @FindBy(id = "username")
    WebElement usernameInput;
    
    @FindBy(id = "password")
    WebElement passwordInput;
    
    @FindBy(xpath = "//button[@type='button']")
    WebElement loginButton;
    
    // Constructor to initialize the page elements
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    
    // Perform login with username and password
    public Dashboard login(String username, String password) throws Throwable {
        Action.type(usernameInput, username);
        Action.type(passwordInput, password);
        Action.click(driver, loginButton);
        return new Dashboard();
       
    }
    
    // Validate if the TMS logo is displayed
    public boolean isLogoDisplayed() {
        return Action.isDisplayed(driver, logoElement);
    }
    
    // Get the title of the current page
    public String getPageTitle() {
        String title = driver.getTitle();
        System.out.println(title);
        return title;
    }
    
    // Validate the header text (Welcome Message)
    public String getWelcomeHeaderText() {
        String headerText = welcomeHeaderText.getText();
        System.out.println(headerText);
        return headerText;
    }
    
    // Validate the login title text
    public String getLoginTitleText() {
        String loginTitle = loginTitleElement.getText();
        System.out.println(loginTitle);
        return loginTitle;
    }
}
