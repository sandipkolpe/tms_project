package com.tms.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tms.framework.actionDriver.Action;
import com.tms.framework.base.BaseClass;

public class UserPage extends BaseClass {

    // WebElements for user management
    @FindBy(xpath = "//*[@id='root']/div[1]/div[1]/ul/li[5]") 
    WebElement userIcon;

    @FindBy(xpath = "//div[contains(text(),'User Management')]")
    WebElement userManagementPageRedirect;

    @FindBy(xpath = "//div[contains(text(),'User Management')]")
    WebElement userManagementHeader;

    @FindBy(xpath = "(//button[@type='button'])[1]") 
    WebElement addUserButton;

    @FindBy(id = "firstName") 
    WebElement firstNameInput;

    @FindBy(id = "lastName") 
    WebElement lastNameInput;

    @FindBy(id = "email") 
    WebElement emailInput;

    @FindBy(id = "mobile") 
    WebElement mobileNumberInput;

    @FindBy(xpath = "(//span[@class='ant-select-selection-search'])[2]") 
    WebElement privilegeDropdown;
 
    @FindBy(xpath = "(//span[@class='ant-select-selection-search'])[3]") 
    WebElement clientDropdown;

    @FindBy(xpath = "(//button[@type='button'])[17]") 
    WebElement nextButton;
    
    @FindBy(id = "new_password") 
    WebElement newPasswordInput;

    @FindBy(id = "reenter_password") 
    WebElement reenterPasswordInput;
    
    
    @FindBy(xpath = "(//button[@type='button'])[17]") 
    WebElement nextButton2;
    
    @FindBy(id = "domain_name") 
    WebElement domainNameInput;
    

    // Constructor to initialize WebElements using PageFactory
    public UserPage() {
        PageFactory.initElements(driver, this);
    }

    // Click the User icon
    public void clickUserIcon() {
        if (Action.isDisplayed(driver, userIcon)) {
            Action.click(driver, userIcon);
        } else {
            System.out.println("User Icon not displayed");
        }
    }

    // Get the title text from the User Management page
    public String getUserManagementPageTitle() {
        return userManagementPageRedirect.getText();
    }

    // Get the header text from the User Management page
    public String getUserManagementHeaderText() {
        return userManagementHeader.getText();
    }

    // Click the "Add User" button
    public void clickAddUser() {
        Action.click(driver, addUserButton);
    }
    
    // Add user by filling out all required fields
    public void addUser(String firstName, String lastName, String email, String mobile, String privilege, String client, String password, String reenterPassword, String domainName) throws InterruptedException {
        Action.type(firstNameInput, firstName);
        Action.type(lastNameInput, lastName);
        Action.type(emailInput, email);
        Action.type(mobileNumberInput, mobile);

        // Select privilege from the dropdown
        if (selectDropdownOption(privilegeDropdown, privilege)) {
            System.out.println("Privilege '" + privilege + "' selected.");
        } else {
            System.out.println("Privilege '" + privilege + "' not found in dropdown.");
        }

        // Select client from the dropdown
        if (selectDropdownOption(clientDropdown, client)) {
            System.out.println("Client '" + client + "' selected.");
        } else {
            System.out.println("Client '" + client + "' not found in dropdown.");
        }

        // Click the "Next" button
        Action.click(driver, nextButton);
        Action.implicitWait(driver, 10);
        
        // Enter password and re-enter password
        Action.type(newPasswordInput, password);
        Action.type(reenterPasswordInput, reenterPassword);
        Action.type(domainNameInput, domainName);
    }
    
    // Click the "Next" button
 

    // Method to get all options from a dropdown and select the specific option
    public boolean getAllDropdownOptionsAndSelect(WebElement dropdownElement, String optionToSelect) throws InterruptedException {
        // Click to show dropdown options
        dropdownElement.click();
        Thread.sleep(2000);  // Wait for options to load

        // Get all available options in the dropdown
        List<WebElement> options = driver.findElements(By.xpath("/html/body/div[3]/div/div[3]/div/div[2]/div[2]/div[5]/div/div/div/span[1]"));

        // Print and select the desired option
        System.out.println("Available Dropdown Options:");
        for (WebElement option : options) {
            String optionText = option.getText().trim();
            System.out.println("Option: " + optionText);

            // Check if the option matches the desired text
            if (optionText.equalsIgnoreCase(optionToSelect)) {
                option.click();
                return true;  // Option found and selected
            }
        }

        return false;  // Option not found
    }

    // Generic method to select a specific option from a dropdown
    public boolean selectDropdownOption(WebElement dropdownElement, String optionText) throws InterruptedException {
        dropdownElement.click();
        Thread.sleep(2000); // Wait for dropdown to load

        List<WebElement> options = driver.findElements(By.xpath("//div[contains(@class, 'ant-select-item-option-content')]"));

        for (WebElement option : options) {
            String optionValue = option.getText().trim();
            if (optionValue.equalsIgnoreCase(optionText)) {
                option.click();
                return true;
            }
        }

        return false;
    }
    
    public void  clickNextButton2() throws InterruptedException {
    	Thread.sleep(3000);
      Action.click(driver, nextButton2);
        
    }
    

}
