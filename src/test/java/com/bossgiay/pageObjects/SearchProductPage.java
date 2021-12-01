package com.bossgiay.pageObjects;

import com.bossgiay.testScripts.TestAbstractClass;
import com.google.errorprone.annotations.Var;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class SearchProductPage extends BasePage {

    @Var
    public boolean checkPoint;
    public Actions actions;
    public WebDriverWait waitFor;
    public WebDriver driver;


//    @Default
//    initialize the driver of page factory initiation
    public SearchProductPage (WebDriver dr) {
        driver=dr;
        PageFactory.initElements(dr, this);
        actions= new Actions(dr);
//        time =>> second
        waitFor= new WebDriverWait(dr, 100);
    }

    @FindBy(xpath = "//*[@class=\"svg-search\"]")
    @CacheLookup
    public WebElement SVG_SEARCH_BUTTON;

    @FindBy(xpath = "//form[@action=\"/search\"]//input[@id=\"inputSearchAuto\"]")
    @CacheLookup
    public WebElement TXT_SEARCH;

    public void clickOnSearchButton() {
        clickOnElementWE(SVG_SEARCH_BUTTON, driver);
    }

    public void sendKeysToSearchTxt(String key) {
        sendKeysToElementWE(TXT_SEARCH, key, driver);
    }

    public void pressEnter() throws AWTException {
        pressKey("ENTER", 2);
    }

    public void setWaitFor(By element) {
        waitUntilElementIsVisible(element, driver);
    }

    public void pauseWithTryCatch(float seconds) {
        pauseWithTryCatch(seconds);
    }

}
