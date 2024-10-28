package com.tms.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tms.framework.actionDriver.Action;
import com.tms.framework.base.BaseClass;

public class Dashboard extends BaseClass {

    @FindBy(xpath = "//div[@class='header-title']")
    WebElement dashboardHeader;

    @FindBy(xpath = "//*[@id='root']/div[1]/div[1]/ul/li[1]")
    WebElement dashboardIcon;

    public Dashboard() {
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardHeaderDisplayed() {
        return Action.isDisplayed(driver, dashboardHeader);
    }

    public boolean isDashboardIconDisplayed() {
        return Action.isDisplayed(driver, dashboardIcon);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
