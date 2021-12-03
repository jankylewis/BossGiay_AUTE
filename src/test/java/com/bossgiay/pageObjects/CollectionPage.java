package com.bossgiay.pageObjects;

import com.google.errorprone.annotations.Var;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CollectionPage extends BasePage{

    @Var
    public static Actions actions;
    public static WebDriver driver;

    public CollectionPage (WebDriver driver) {
        driver=driver;
        PageFactory.initElements(driver, this);
        actions= new Actions(driver);
    }

    @CacheLookup
    private By DDL_SELECT_PARENT_LOCATOR=
            By.xpath("//option//ancestor::select[contains((@class),\"sort\")]");
    @CacheLookup
    private By DDI_OPTION_CHILD_LOCATOR=
            By.xpath("//select//child::option[@value]");
    @CacheLookup
    private By LBL_SORT_CATEGORIES_CHILD_LOCATOR=
            By.xpath("//div[@class=\"group-menu\"]//a[contains((@href),\"collections\") and @title]");
    @CacheLookup
    private By LBL_SORT_CATEGORIES_PARENT_LOCATOR=
            By.xpath("//div[@class=\"group-menu\"]");



    public void selectDynamicSortCriteria(String sortCriteria, WebDriver dr) {

//       select sort criteria dynamically by sending a text
        selectDynamicByText(DDL_SELECT_PARENT_LOCATOR,
                DDI_OPTION_CHILD_LOCATOR, sortCriteria, dr);
    }

    public void selectDynamicSortCategories(String attribute, String text, WebDriver dr) {

//        select sort categories dynamically by sending a title (getAttribute)
        selectDynamicByValue(LBL_SORT_CATEGORIES_PARENT_LOCATOR,
                LBL_SORT_CATEGORIES_CHILD_LOCATOR, attribute, text, dr);
    }

    public void setWaitFor(By element) {
        waitUntilElementIsVisible(element, driver);
    }

    public void pauseWithTryCatch(double seconds) {
        pauseWithTryCatch(seconds);
    }

    public void executeScrollingDown(double scrollCoordinates) {
        executeScrollingDown(scrollCoordinates, javascript, driver);
    }

}
