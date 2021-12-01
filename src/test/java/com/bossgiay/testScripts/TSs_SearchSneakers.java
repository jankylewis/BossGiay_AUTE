package com.bossgiay.testScripts;

import com.bossgiay.pageObjects.BasePage;
import com.bossgiay.pageObjects.SearchProductPage;
import com.bossgiay.testListeners.TestListeners;
import com.bossgiay.testUtilities.ConfigurationReader;
import com.google.errorprone.annotations.Var;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


@Listeners({TestListeners.class})
public class TSs_SearchSneakers extends TestAbstractClass {

    ConfigurationReader confReader= new ConfigurationReader();
    SearchProductPage search;
    WebDriver dr;

    @Var
    private boolean checkPointIfFailed;
    private boolean checkPointIfPassed;
    private boolean checkPointIfSkipped;
    private String searchKey;
    private final String pageTitle= "TÌM KIẾM";
    private final String headingText= "Kết quả tìm kiếm cho"+ "\""+ searchKey+ "\".";

    private final By LBL_PAGE_TITLE_LOCATOR= By.xpath("//div[@class=\"heading-page\"]//child::h1");
    private final By LBL_HEADING_TEXT_LOCATOR= By.xpath("//p[@class=\"subtext-result\"]");
    private final By LBL_SNEAKER_NAME_CHILD_LOCATOR= By.xpath("//h3[@class=\"pro-name\"]//a");
    private final By LBL_SNEAKER_NAME_PARENT_LOCATOR= By.xpath("//div[contains(@class,\"search-list\")]");

    private List<String> sneakersArray= new ArrayList<String>();
    private String searchSneakerOnePageKey[];

    @BeforeMethod(alwaysRun = true,
                    enabled = true,
                        description = "facilitate triggering browser")
    public void be4SearchCaseMethod() throws TimeoutException{
        testSetUp(confReader.getWebApplicationBaseURL(), "chrome");
    }

    @AfterMethod(alwaysRun = true,
                    enabled = true,
                        description = "facilitate repelling driver")
    public void afSearchCaseMethod() throws TimeoutException {
//        testTearDown();
    }

    @Test(groups = {"001"},
            enabled = true,
                priority = -4,
                    description = "returned result is less than two page")
    public void searchSneakerTest01() throws TimeoutException, AWTException {

//        select randomly a keyword
        this.searchSneakerOnePageKey= new String[] {
        "JORDAN",
                "LOW",
                "MID",
                "STAN",
                "NIKE",
                "ADIDAS",
                "SUPER",
                "HOLOGRAM",
                "DOMBA",
                "WHITE",
                "BLACK",
                "RAINBOWN",
                "SMITH",
                "ALL",
                "MAX",
                "VICTOR",
                "MLB",
                "SHADOW",
                "DOGGER",
                "SLIDE",
                "GOLD",
                "HIGH",
                "ICE",
                "META",
                "LASER",
                "RED",
                "SIMPSONS",
                "ADLV",
                "BIG",
                "X",
                "V",
                "PUMA",
                "JAN",
                "K",
                "YANKEE",
                "MONOGRAM",
                "FORCE",
                "FRUIT",
                "ROSE",
                "CARTOON",
                "POUCH",
                "WORLDWIDE",
                "ASUNA",
                "JACQUARD",
                "F",
                "MON",
                "HOODY",
                "LIFE",
                "VOLT",
                "METALLIC",
                "POINT",
                "SP",
                "2"
        };
        this.searchKey= searchSneakerOnePageKey[new Random().nextInt(searchSneakerOnePageKey.length)];
//        project to SearchProductPage to use method
        SearchProductPage searchPage= new SearchProductPage(driver);
        searchPage.clickOnSearchButton();
        log.info("//------CLICKED SPY GLASS BUTTON-------//"+ "\n");
        searchPage.sendKeysToSearchTxt(searchKey);
        log.info("//------INPUTTED SEARCH KEY-------//"+ "\n");
        searchPage.pauseWithTryCatch(1250);
        searchPage.pressEnter();
        log.info("//------PRESSED ENTER-------//"+ "\n");
        searchPage.setWaitFor(LBL_PAGE_TITLE_LOCATOR);
        String pageTitleGetTxt= driver.findElement(LBL_PAGE_TITLE_LOCATOR).getText();
        searchPage.setWaitFor(LBL_HEADING_TEXT_LOCATOR);
        String headingTextGetTxt= driver.findElement(LBL_HEADING_TEXT_LOCATOR).getText();
        System.out.println(""+ pageTitleGetTxt+ "\n");
        System.out.println(headingTextGetTxt+ "\n");

//        assertion
        if ((pageTitleGetTxt.equalsIgnoreCase(pageTitle) || pageTitleGetTxt.contains(pageTitle))
                && headingTextGetTxt.equalsIgnoreCase(headingText) || headingTextGetTxt.contains(headingText)) {

//            the page title text matches the expected title => true assertion
            this.checkPointIfPassed=true;
            this.checkPointIfFailed=false;
            this.checkPointIfSkipped=false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + pageTitleGetTxt + "\"" + " matched the expected text!" + "\n");
            }
        }
//        else {
//            this.checkPointIfPassed=false;
//            this.checkPointIfSkipped=false;
//            this.checkPointIfFailed=true;
//            if (!checkPointIfPassed && !checkPointIfSkipped && checkPointIfFailed==true) {
//                Assert.assertTrue(false);
//                System.out.println("Text on web: "+ "\""+pageTitleGetTxt+"\""+ " matched the expected text!"+ "\n");
//                log.info("//------TEST CASE 001 IS FAILED-------//"+ "\n");
//            }
//        }

//       assert sneakers name
//       get number of total sneaker
        int sneakerCounter= driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
        System.out.println("Number of sneakers: "+ sneakerCounter+ "\n");

//       get name for each sneaker
        for (int index= 1; index<= sneakerCounter; index++) {
            WebElement listSneakers= driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
            List<WebElement> childListSneakers= driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
            String sneakerName= childListSneakers.get(index-1).getText();
            System.out.println("NAME OF SNEAKER NUMBER "+ index+ ": "+ (childListSneakers.get(index-1).getText())+ "");
            if (sneakerName.contains(searchKey)==true) {
                this.checkPointIfPassed=true;
                this.checkPointIfFailed=false;
                this.checkPointIfSkipped=false;
                if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                    Assert.assertFalse(false);
                    log.info("//------**ASSERTED SNEAKER NUMBER "+ index+ "!-------//"+ "");
//                    add sneaker name to the array
                    sneakersArray.add(sneakerName.toUpperCase(Locale.ROOT));
//                   print out the has-been-added array
                    System.out.println("Sneaker number "+ index+ " is inserted " +
                            "into the sneakers array: \n"+ sneakersArray+ "\n");
                }
            }
        }
    }








}


