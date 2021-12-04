package com.bossgiay.testScripts;

import com.bossgiay.pageObjects.CollectionPage;
import com.bossgiay.testListeners.TestListeners;
import com.bossgiay.testUtilities.ConfigurationReader;
import com.google.errorprone.annotations.Var;
import lombok.EqualsAndHashCode;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
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
import java.util.NoSuchElementException;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({TestListeners.class})
public class TSs_SortSneakers extends TestAbstractClass {

    @Var
    private ConfigurationReader confReader = new ConfigurationReader();
    private final String ascdPriceCriteria = "Giá: Tăng dần";
    private final String dsdPriceCriteria = "Giá: Giảm dần";
    private final String alphaOrderCriteria= "Tên: A-Z";
    private final String nonAlphaOrderCriteria= "Tên: Z-A";
    private final String sneakerCategory = "Sneaker";
    private final String slideSandalCategory = "Slide/Sandal";
    private final String bagCategory = "Bag";
    private final String clothingCategory = "Clothing";
    private final String sneakerTitle = sneakerCategory.toUpperCase(Locale.ROOT);
    private final String slideSandalTitle= slideSandalCategory.toUpperCase(Locale.ROOT);
    private final String bagTitle= bagCategory.toUpperCase(Locale.ROOT);
    private final String clothingTitle= clothingCategory.toUpperCase(Locale.ROOT);

    private final By LBL_CATEGORY_TITLE_LOCATOR =
            By.xpath("//h1[@class=\"title\"]");
    private final By LBL_SNEAKER_PRICE_CHILD_LOCATOR =
            By.xpath("//p[contains((@class),\"pro-price\") and contains(text(),\"000\")]");
    private final By LBL_SNEAKER_PRICE_PARENT_LOCATOR =
            By.xpath("//div[contains((@class),\"product-list\")]");
    private final By LBL_SNEAKER_NAME_CHILD_LOCATOR=
            By.xpath("//h3[@class=\"pro-name\"]//a");
    private final By LBL_SNEAKER_NAME_PARENT_LOCATOR=
            LBL_SNEAKER_PRICE_PARENT_LOCATOR;

    private final By LBL_SLIDE_SANDAL_CHILD_LOCATOR=
            LBL_SNEAKER_PRICE_CHILD_LOCATOR;
    private final By LBL_SLIDE_SANDAL_PARENT_LOCATOR=
            LBL_SNEAKER_PRICE_PARENT_LOCATOR;
    private final By LBL_BAG_PRICE_CHILD_LOCATOR=
            LBL_SNEAKER_PRICE_CHILD_LOCATOR;
    private final By LBL_BAG_PRICE_PARENT_LOCATOR=
            LBL_SNEAKER_PRICE_PARENT_LOCATOR;
    private final By LBL_CLOTHING_PRICE_PARENT_LOCATOR=
            LBL_SNEAKER_PRICE_PARENT_LOCATOR;
    private final By LBL_CLOTHING_PRICE_CHILD_LOCATOR=
            LBL_BAG_PRICE_CHILD_LOCATOR;
    private final By LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR=
            LBL_SNEAKER_NAME_CHILD_LOCATOR;
    private final By LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR=
            LBL_SNEAKER_NAME_PARENT_LOCATOR;
    private final By LBL_BAG_NAME_CHILD_LOCATOR=
            LBL_SNEAKER_NAME_CHILD_LOCATOR;
    private final By LBL_BAG_NAME_PARENT_LOCATOR=
            LBL_SNEAKER_NAME_PARENT_LOCATOR;
    private final By LBL_CLOTHING_NAME_CHILD_LOCATOR=
            LBL_SNEAKER_NAME_CHILD_LOCATOR;
    private final By LBL_CLOTHING_NAME_PARENT_LOCATOR=
            LBL_SNEAKER_NAME_PARENT_LOCATOR;

    private final By BTN_PAGE_NODE_LOCATOR =
            By.xpath("//div[@id=\"pagination\"]//a[@class=\"page-node\"]");
    private final By BTN_NEXT_LOCATOR =
            By.xpath("//div[@id=\"pagination\"]//a[@class=\"next\"]" +
                    "//following-sibling::*[local-name() = 'svg' and @version and @viewBox]");

    transient boolean checkPointIfPassed;
    transient boolean checkPointIfFailed;
    transient boolean checkPointIfSkipped;
    public int listValue;

    @EqualsAndHashCode.Include
    private final List<String> sneakersPriceList = new ArrayList<String>();
    private final List<String> ssPriceList= new ArrayList<String>();
    private final List<String> bagPriceList= new ArrayList<String>();
    private final List<String> clothingPriceList= new ArrayList<String>();
    private final List<String> ssNameList= new ArrayList<String>();
    private final List<String> sneakersNameList = new ArrayList<String>();
    private final List<String> bagNameList= new ArrayList<String>();
    private final List<String> clothingNameList= new ArrayList<String>();

    private final List<Integer> snkPriceListInt= new ArrayList<Integer>();
    private final List<Integer> ssPriceListInt= new ArrayList<Integer>();
    private final List<Integer> bagPriceListInt= new ArrayList<Integer>();
    private final List<Integer> clothingPriceListInt= new ArrayList<Integer>();

    private final List<Character> frstCharSnkNameList= new ArrayList<Character>();
    private final List<Character> frstCharSSNameList= new ArrayList<Character>();
    private final List<Character> frstCharBagNameList= new ArrayList<Character>();
    private final List<Character> frstCharClothingNameList= new ArrayList<Character>();

    @BeforeMethod(alwaysRun = true,
            enabled = true,
            description = "facilitate triggering browser")
    public void be4SearchCaseMethod() throws TimeoutException {
        testSetUp(confReader.getWebApplicationBaseURL() + "collections/", "chrome");
    }

    @AfterMethod(alwaysRun = true,
            enabled = true,
            description = "facilitate repelling driver")
    public void afSearchCaseMethod() throws TimeoutException {
        testTearDown();
    }

    @Test(groups = {"001", "sort", "sneaker"},
            enabled = true,
            priority = -4,
            description =
            "this test script is facilitate verifying the sort function" +
            "whether it works funtionally:" +
                    "1. Navigate to collection site" +
                    "2. Select sort category (sneaker) dynamically" +
                    "3. Select sort criteria (ascending price)" +
                    "4. Create an array of sneaker price" +
                    "5. Assert array of sneaker price whether it is sorted by ascending order"
    )
    public void sortSneakersTest01() throws TimeoutException {
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by sneaker sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", sneakerCategory, driver);
        log.info("//--**------------ SELECTED SNEAKER CATEGORY -------------**--//" + "\n");

//        select sort sneakers by ascending price sending a text
        collect.selectDynamicSortCriteria(ascdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY ASCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String sneakerCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + sneakerCategoryGetTxt + "\n");

//        assert sneaker title
        if (sneakerCategoryGetTxt.equalsIgnoreCase(sneakerTitle)) {

//            the sneaker title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
//        declare a variable to count the number of click
        int clickIndex = 0;

//        declare a variable to count how many times we have to click
        int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each sneaker:
        - the first sneaker: SNEAKER NUMBER 1
        - the second sneaker: SNEAKER NUMBER 2
        , ...
 */
        int sneakerLabel = 1;
        int indexSneakerPriceArray = 0;
        System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each sneaker name contains the inputted search key
        for (int index = 0; index < pageNodeSize; index++) {

//            get number of sneakers per page
            int listOfSneakersSize = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR).size();
            System.out.println("\n" + "Number of sneakers page " + (index + 1) +
                    " is: " + listOfSneakersSize + " sneakers" + "\n");
            for (int sneakerIndex = 0; sneakerIndex < listOfSneakersSize; sneakerIndex++) {
                WebElement listSneakers = driver.findElement(LBL_SNEAKER_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR);
                String sneakerPrice = childListSneakers.get(sneakerIndex - 0).getText();
                System.out.println("PRICE OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerPrice + "");
                sneakersPriceList.add(sneakerPrice);
                System.out.println(sneakersPriceList);
                sneakerLabel++;
            }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfSneakersLastPageSize = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR).size();
                    System.out.println("Number of sneakers page " + (pageNodeSize + 1) +
                            " is: " + listOfSneakersLastPageSize + " sneakers" + "\n");
                    for (int sneakerIndexLastPage = 0; sneakerIndexLastPage < listOfSneakersLastPageSize; sneakerIndexLastPage++) {
                        WebElement listSneakersLastPage = driver.findElement(LBL_SNEAKER_PRICE_PARENT_LOCATOR);
                        java.util.List<WebElement> childListSneakerslastPage = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR);
                        String sneakerPriceLastPage = childListSneakerslastPage.get(sneakerIndexLastPage - 0).getText();
                        System.out.println("PRICE OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerPriceLastPage + "");
                        sneakersPriceList.add(sneakerPriceLastPage);
                        System.out.println(sneakersPriceList);
                        sneakerLabel++;
                    }
                }
            }

        System.out.println("\n"+ "//-------------//"+ "\n");
        System.out.println("\n"+ "LIST OF SNEAKERS PRICE BEFORE PROCESSING: "+ sneakersPriceList+ "\n");
        System.out.println("\n"+ "//-------------//"+ "\n");

        for (int indexInSnkPrList= 0;
             indexInSnkPrList< sneakersPriceList.size();
             indexInSnkPrList++) {
            String priceInList= sneakersPriceList.get(indexInSnkPrList);
//            System.out.println(priceInList+ "\n");
            if (priceInList.contains(" ")==false) {
                if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                     for (int i=0; i<2; i++) {
                         combinePartitions+= splitCommaChar[i];
                     }
//                     System.out.println(combinePartitions);
                     snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
                else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<3; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
            }
            else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                String[] splitSpacerChar= priceInList.split(" ");
                priceInList =splitSpacerChar[0];
                if (priceInList.length()< 9) {
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<2; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
                else { // encompass more than 9 chars
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<3; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
            }
        } // exit For loop
        System.out.println("\n"+ "//-------------//"+ "\n");
        System.out.println("\n"+ "LIST OF SNEAKERS PRICE AFTER REFINING: "+ snkPriceListInt+ "\n");
        System.out.println("\n"+ "//-------------//"+ "\n");
        for (int indexAssertPriceListInt= 0;
             indexAssertPriceListInt< snkPriceListInt.size();
             indexAssertPriceListInt++) {
            if (snkPriceListInt.get(indexAssertPriceListInt)<= snkPriceListInt.get(indexAssertPriceListInt+1)) {
                log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                        "SNEAKER PRICE NUMBER "+ (indexAssertPriceListInt+ 1)+ " --------**-------//"+ "\n\n");
                this.checkPointIfPassed=true;
                this.checkPointIfSkipped=false;
                if (this.checkPointIfPassed==true && this.checkPointIfSkipped==false) {
                    Assert.assertFalse(false);
                }
            }
            else { //the next sneaker price is less than the previous sneaker price => wrong functionally
                log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                        "SNEAKER PRICE NUMBER "+ (indexAssertPriceListInt+ 1)+ " --------**-------//"+ "\n\n");
                this.checkPointIfFailed=true;
                this.checkPointIfSkipped=false;
                if (this.checkPointIfFailed==true && this.checkPointIfSkipped==false) {
                    Assert.assertFalse(true);
                }
            }
        }
    }


    @Test(groups = {"001", "sort", "slide/sandal"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (slide/sandal) dynamically" +
                            "3. Select sort criteria (ascending price)" +
                            "4. Create an array of slide/sandal price" +
                            "5. Assert array of slide/sandal price whether it is sorted by ascending order"
    )
    public void sortSlideSandalTest01() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of slides/sandals is less than one page - absence of pagination
//        assign list value= 2 when list of slides/sandals is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by slide/sandal sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", slideSandalCategory, driver);
        log.info("//--**------------ SELECTED SLIDE/SANDAL CATEGORY -------------**--//" + "\n");

//        select sort bags by ascending price sending a text
        collect.selectDynamicSortCriteria(ascdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY ASCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String slideSandalCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + slideSandalCategoryGetTxt + "\n");

        if (slideSandalCategoryGetTxt.equalsIgnoreCase(slideSandalTitle)) {

//            the slide/sandal title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + slideSandalCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + slideSandalCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
        int ssLabel = 1;
        int indexSSPriceArray = 0;
            int listOfSSsSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
            for (int ssIndex = 0; ssIndex < listOfSSsSize; ssIndex++) {
                org.openqa.selenium.WebElement listSSs =
                        driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                java.util.List<WebElement> childListSSs = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                String ssPrice = childListSSs.get(ssIndex).getText();
                System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPrice + "");
                ssPriceList.add(ssPrice);
                System.out.println(ssPriceList);
                ssLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS PRICE BEFORE PROCESSING: " + ssPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInSSPrList = 0;
                 indexInSSPrList < ssPriceList.size(); indexInSSPrList++) {
                String priceInList = ssPriceList.get(indexInSSPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS PRICE AFTER REFINING: " + ssPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < ssPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (ssPriceListInt.get(indexAssertPriceListInt) <= ssPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next slide/sandal price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each slide/sandal name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of slides/sandals per page
                int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of slides/sandals page " + (index + 1) +
                        " is: " + listOfSSSize + " slides/sandals" + "\n");
                for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                    WebElement listSS = driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                    String ssPrice = childListSS.get(ssIndex - 0).getText();
                    System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPrice + "");
                    ssPriceList.add(ssPrice);
                    System.out.println(ssPriceList);
                    ssLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfSSLastPageSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
                    System.out.println("Number of slides/sandals page " + (pageNodeSize + 1) +
                            " is: " + listOfSSLastPageSize + " slides/sandals" + "\n");
                    for (int ssIndexLastPage = 0; ssIndexLastPage < listOfSSLastPageSize; ssIndexLastPage++) {
                        WebElement listSSLastPage = driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                        java.util.List<WebElement> childListSSLastPage = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                        String ssPriceLastPage = childListSSLastPage.get(ssIndexLastPage).getText();
                        System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPriceLastPage + "");
                        ssPriceList.add(ssPriceLastPage);
                        System.out.println(ssPriceList);
                        ssLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF SLIDES/SANDALS PRICE BEFORE PROCESSING: "+ ssPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInSSPrList= 0;
                 indexInSSPrList< ssPriceList.size();
                 indexInSSPrList++) {
                String priceInList= ssPriceList.get(indexInSSPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF SLIDES/SANDALS PRICE AFTER REFINING: "+ ssPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< ssPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (ssPriceListInt.get(indexAssertPriceListInt) <= ssPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next sneaker price is less than the previous sneaker price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"001", "sort", "bag"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (bag) dynamically" +
                            "3. Select sort criteria (ascending price)" +
                            "4. Create an array of bag price" +
                            "5. Assert array of bag price whether it is sorted by ascending order"
    )
    public void sortBagTest01() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of bags is less than one page - absence of pagination
//        assign list value= 2 when list of bags is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by bag sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", bagCategory, driver);
        log.info("//--**------------ SELECTED BAG CATEGORY -------------**--//" + "\n");

//        select sort bags by ascending price sending a text
        collect.selectDynamicSortCriteria(ascdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY ASCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String bagCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + bagCategoryGetTxt + "\n");

        if (bagCategoryGetTxt.equalsIgnoreCase(bagTitle)) {

//            the bag title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagPriceArray = 0;
            int listOfBagSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
            for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                org.openqa.selenium.WebElement listBag =
                        driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                String bagPrice = childListBag.get(bagIndex).getText();
                System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPrice + "");
                bagPriceList.add(bagPrice);
                System.out.println(bagPriceList);
                bagLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS PRICE BEFORE PROCESSING: " + bagPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInBagPrList = 0;
                 indexInBagPrList< bagPriceList.size(); indexInBagPrList++) {
                String priceInList = bagPriceList.get(indexInBagPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS PRICE AFTER REFINING: " + bagPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < bagPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (bagPriceListInt.get(indexAssertPriceListInt) <= bagPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each bag name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfBagSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of bags page " + (index + 1) +
                        " is: " + listOfBagSize + " bags" + "\n");
                for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                    WebElement listBag = driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                    java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                    String bagPrice = childListBag.get(bagIndex - 0).getText();
                    System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPrice + "");
                    bagPriceList.add(bagPrice);
                    System.out.println(bagPriceList);
                    bagLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfBagLastPageSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfBagLastPageSize + " bags" + "\n");
                    for (int bagIndexLastPage = 0;
                         bagIndexLastPage < listOfBagLastPageSize; bagIndexLastPage++) {
                        WebElement listBagLastPage = driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                        java.util.List<WebElement> childListBagLastPage = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                        String bagPriceLastPage = childListBagLastPage.get(bagIndexLastPage).getText();
                        System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPriceLastPage + "");
                        bagPriceList.add(bagPriceLastPage);
                        System.out.println(bagPriceList);
                        bagLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF BAGS PRICE BEFORE PROCESSING: "+ bagPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInBagPrList= 0;
                 indexInBagPrList< bagPriceList.size();
                 indexInBagPrList++) {
                String priceInList= bagPriceList.get(indexInBagPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF BAGS PRICE AFTER REFINING: "+ bagPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< bagPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (bagPriceListInt.get(indexAssertPriceListInt) <= bagPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous bag price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"001", "sort", "clothing"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (clothing) dynamically" +
                            "3. Select sort criteria (ascending price)" +
                            "4. Create an array of clothing price" +
                            "5. Assert array of clothing price whether it is sorted by ascending order"
    )
    public void sortClothingTest01() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of clothing is less than one page - absence of pagination
//        assign list value= 2 when list of clothing is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by clothing sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", clothingCategory, driver);
        log.info("//--**------------ SELECTED CLOTHING CATEGORY -------------**--//" + "\n");

//        select sort clothing by ascending price sending a text
        collect.selectDynamicSortCriteria(ascdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY ASCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String clothingCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + clothingCategoryGetTxt + "\n");

        if (clothingCategoryGetTxt.equalsIgnoreCase(clothingTitle)) {

//            the clothing title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingPriceArray = 0;
            int listOfClothingSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
            for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                org.openqa.selenium.WebElement listClothing =
                        driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                String clothingPrice = childListClothing.get(clothingIndex).getText();
                System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPrice + "");
                clothingPriceList.add(clothingPrice);
                System.out.println(clothingPriceList);
                clothingLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING PRICE BEFORE PROCESSING: " + clothingPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInClothingPrList = 0;
                 indexInClothingPrList< clothingPriceList.size(); indexInClothingPrList++) {
                String priceInList = clothingPriceList.get(indexInClothingPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING PRICE AFTER REFINING: " + clothingPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < clothingPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (clothingPriceListInt.get(indexAssertPriceListInt) <= clothingPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second bag: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each clothing name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of clothing per page
                int listOfClothingSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of clothing page " + (index + 1) +
                        " is: " + listOfClothingSize + " clothing" + "\n");
                for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                    WebElement listClothing = driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                    java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                    String clothingPrice = childListClothing.get(clothingIndex - 0).getText();
                    System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPrice + "");
                    clothingPriceList.add(clothingPrice);
                    System.out.println(clothingPriceList);
                    clothingLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfClothingLastPageSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfClothingLastPageSize + " clothing" + "\n");
                    for (int clothingIndexLastPage = 0;
                         clothingIndexLastPage < listOfClothingLastPageSize; clothingIndexLastPage++) {
                        WebElement listClothingLastPage = driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                        java.util.List<WebElement> childListClothingLastPage = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                        String clothingPriceLastPage = childListClothingLastPage.get(clothingIndexLastPage).getText();
                        System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPriceLastPage + "");
                        clothingPriceList.add(clothingPriceLastPage);
                        System.out.println(clothingPriceList);
                        clothingLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF CLOTHING PRICE BEFORE PROCESSING: "+ clothingPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInClothingPrList= 0;
                 indexInClothingPrList< clothingPriceList.size();
                 indexInClothingPrList++) {
                String priceInList= clothingPriceList.get(indexInClothingPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF CLOTHING PRICE AFTER REFINING: "+ clothingPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< clothingPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (clothingPriceListInt.get(indexAssertPriceListInt) <= clothingPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next clothing price is less than the previous bag price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }

    @Test(groups = {"002", "descending price sort", "sneaker dsd"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (sneaker) dynamically" +
                            "3. Select sort criteria (descending price)" +
                            "4. Create an array of sneaker price" +
                            "5. Assert array of sneaker price whether it is sorted by descending order"
    )
    public void sortSneakersTest02() throws TimeoutException {
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by sneaker sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", sneakerCategory, driver);
        log.info("//--**------------ SELECTED SNEAKER CATEGORY -------------**--//" + "\n");

//        select sort sneakers by descending price sending a text
        collect.selectDynamicSortCriteria(dsdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY DESCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String sneakerCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + sneakerCategoryGetTxt + "\n");

//        assert sneaker title
        if (sneakerCategoryGetTxt.equalsIgnoreCase(sneakerTitle)) {

//            the sneaker title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
//        declare a variable to count the number of click
        int clickIndex = 0;

//        declare a variable to count how many times we have to click
        int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each sneaker:
        - the first sneaker: SNEAKER NUMBER 1
        - the second sneaker: SNEAKER NUMBER 2
        , ...
 */
        int sneakerLabel = 1;
        int indexSneakerPriceArray = 0;
        System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each sneaker name contains the inputted search key
        for (int index = 0; index < pageNodeSize; index++) {

//            get number of sneakers per page
            int listOfSneakersSize = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR).size();
            System.out.println("\n" + "Number of sneakers page " + (index + 1) +
                    " is: " + listOfSneakersSize + " sneakers" + "\n");
            for (int sneakerIndex = 0; sneakerIndex < listOfSneakersSize; sneakerIndex++) {
                WebElement listSneakers = driver.findElement(LBL_SNEAKER_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR);
                String sneakerPrice = childListSneakers.get(sneakerIndex - 0).getText();
                System.out.println("PRICE OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerPrice + "");
                sneakersPriceList.add(sneakerPrice);
                System.out.println(sneakersPriceList);
                sneakerLabel++;
            }
            WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
            ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
            collect.pauseWithTryCatch(1550);
            nextButton.click();
            clickIndex += 1;
            if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                int listOfSneakersLastPageSize = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR).size();
                System.out.println("Number of sneakers page " + (pageNodeSize + 1) +
                        " is: " + listOfSneakersLastPageSize + " sneakers" + "\n");
                for (int sneakerIndexLastPage = 0; sneakerIndexLastPage < listOfSneakersLastPageSize; sneakerIndexLastPage++) {
                    WebElement listSneakersLastPage = driver.findElement(LBL_SNEAKER_PRICE_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSneakerslastPage = driver.findElements(LBL_SNEAKER_PRICE_CHILD_LOCATOR);
                    String sneakerPriceLastPage = childListSneakerslastPage.get(sneakerIndexLastPage - 0).getText();
                    System.out.println("PRICE OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerPriceLastPage + "");
                    sneakersPriceList.add(sneakerPriceLastPage);
                    System.out.println(sneakersPriceList);
                    sneakerLabel++;
                }
            }
        }

        System.out.println("\n"+ "//-------------//"+ "\n");
        System.out.println("\n"+ "LIST OF SNEAKERS PRICE BEFORE PROCESSING: "+ sneakersPriceList+ "\n");
        System.out.println("\n"+ "//-------------//"+ "\n");

        for (int indexInSnkPrList= 0;
             indexInSnkPrList< sneakersPriceList.size();
             indexInSnkPrList++) {
            String priceInList= sneakersPriceList.get(indexInSnkPrList);
//            System.out.println(priceInList+ "\n");
            if (priceInList.contains(" ")==false) {
                if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<2; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
                else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<3; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
            }
            else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                String[] splitSpacerChar= priceInList.split(" ");
                priceInList =splitSpacerChar[0];
                if (priceInList.length()< 9) {
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<2; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
                else { // encompass more than 9 chars
                    String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                    String[] splitCommaChar= trimLastChar.split(",");
                    String combinePartitions="";
                    for (int i=0; i<3; i++) {
                        combinePartitions+= splitCommaChar[i];
                    }
//                     System.out.println(combinePartitions);
                    snkPriceListInt.add(Integer.parseInt(combinePartitions));
                }
            }
        } // exit For loop
        System.out.println("\n"+ "//-------------//"+ "\n");
        System.out.println("\n"+ "LIST OF SNEAKERS PRICE AFTER REFINING: "+ snkPriceListInt+ "\n");
        System.out.println("\n"+ "//-------------//"+ "\n");
        for (int indexAssertPriceListInt= 0;
             indexAssertPriceListInt< snkPriceListInt.size();
             indexAssertPriceListInt++) {
            if (snkPriceListInt.get(indexAssertPriceListInt)>= snkPriceListInt.get(indexAssertPriceListInt+1)) {
                log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                        "SNEAKER PRICE NUMBER "+ (indexAssertPriceListInt+ 1)+ " --------**-------//"+ "\n\n");
                this.checkPointIfPassed=true;
                this.checkPointIfSkipped=false;
                if (this.checkPointIfPassed==true && this.checkPointIfSkipped==false) {
                    Assert.assertFalse(false);
                }
            }
            else { //the next sneaker price is less than the previous sneaker price => wrong functionally
                log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                        "SNEAKER PRICE NUMBER "+ (indexAssertPriceListInt+ 1)+ " --------**-------//"+ "\n\n");
                this.checkPointIfFailed=true;
                this.checkPointIfSkipped=false;
                if (this.checkPointIfFailed==true && this.checkPointIfSkipped==false) {
                    Assert.assertFalse(true);
                }
            }
        }
    }
    @Test(groups = {"002", "descending price sort", "slide/sandal dsd"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (slide/sandal) dynamically" +
                            "3. Select sort criteria (descending price)" +
                            "4. Create an array of slide/sandal price" +
                            "5. Assert array of slide/sandal price whether it is sorted by descending order"
    )
    public void sortSlideSandalTest02() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of slides/sandals is less than one page - absence of pagination
//        assign list value= 2 when list of slides/sandals is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by slide/sandal sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", slideSandalCategory, driver);
        log.info("//--**------------ SELECTED SLIDE/SANDAL CATEGORY -------------**--//" + "\n");

//        select sort bags by descending price sending a text
        collect.selectDynamicSortCriteria(dsdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY DESCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String slideSandalCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + slideSandalCategoryGetTxt + "\n");

        if (slideSandalCategoryGetTxt.equalsIgnoreCase(slideSandalTitle)) {

//            the slide/sandal title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + slideSandalCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + slideSandalCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSPriceArray = 0;
            int listOfSSsSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
            for (int ssIndex = 0; ssIndex < listOfSSsSize; ssIndex++) {
                org.openqa.selenium.WebElement listSSs =
                        driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                java.util.List<WebElement> childListSSs = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                String ssPrice = childListSSs.get(ssIndex).getText();
                System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPrice + "");
                ssPriceList.add(ssPrice);
                System.out.println(ssPriceList);
                ssLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS PRICE BEFORE PROCESSING: " + ssPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInSSPrList = 0;
                 indexInSSPrList < ssPriceList.size(); indexInSSPrList++) {
                String priceInList = ssPriceList.get(indexInSSPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS PRICE AFTER REFINING: " + ssPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < ssPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (ssPriceListInt.get(indexAssertPriceListInt) >= ssPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next slide/sandal price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each slide/sandal name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of slides/sandals per page
                int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of slides/sandals page " + (index + 1) +
                        " is: " + listOfSSSize + " slides/sandals" + "\n");
                for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                    WebElement listSS = driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                    String ssPrice = childListSS.get(ssIndex - 0).getText();
                    System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPrice + "");
                    ssPriceList.add(ssPrice);
                    System.out.println(ssPriceList);
                    ssLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfSSLastPageSize = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR).size();
                    System.out.println("Number of slides/sandals page " + (pageNodeSize + 1) +
                            " is: " + listOfSSLastPageSize + " slides/sandals" + "\n");
                    for (int ssIndexLastPage = 0; ssIndexLastPage < listOfSSLastPageSize; ssIndexLastPage++) {
                        WebElement listSSLastPage = driver.findElement(LBL_SLIDE_SANDAL_PARENT_LOCATOR);
                        java.util.List<WebElement> childListSSLastPage = driver.findElements(LBL_SLIDE_SANDAL_CHILD_LOCATOR);
                        String ssPriceLastPage = childListSSLastPage.get(ssIndexLastPage).getText();
                        System.out.println("PRICE OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssPriceLastPage + "");
                        ssPriceList.add(ssPriceLastPage);
                        System.out.println(ssPriceList);
                        ssLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF SLIDES/SANDALS PRICE BEFORE PROCESSING: "+ ssPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInSSPrList= 0;
                 indexInSSPrList< ssPriceList.size();
                 indexInSSPrList++) {
                String priceInList= ssPriceList.get(indexInSSPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        ssPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF SLIDES/SANDALS PRICE AFTER REFINING: "+ ssPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< ssPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (ssPriceListInt.get(indexAssertPriceListInt) >= ssPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next sneaker price is less than the previous sneaker price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"002", "descending price sort", "bag dsd"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (bag) dynamically" +
                            "3. Select sort criteria (descending price)" +
                            "4. Create an array of bag price" +
                            "5. Assert array of bag price whether it is sorted by descending order"
    )
    public void sortBagTest02() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of bags is less than one page - absence of pagination
//        assign list value= 2 when list of bags is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by bag sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", bagCategory, driver);
        log.info("//--**------------ SELECTED BAG CATEGORY -------------**--//" + "\n");

//        select sort bags by descending price sending a text
        collect.selectDynamicSortCriteria(dsdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY DESCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String bagCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + bagCategoryGetTxt + "\n");

        if (bagCategoryGetTxt.equalsIgnoreCase(bagTitle)) {

//            the bag title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagPriceArray = 0;
            int listOfBagSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
            for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                org.openqa.selenium.WebElement listBag =
                        driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                String bagPrice = childListBag.get(bagIndex).getText();
                System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPrice + "");
                bagPriceList.add(bagPrice);
                System.out.println(bagPriceList);
                bagLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS PRICE BEFORE PROCESSING: " + bagPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInBagPrList = 0;
                 indexInBagPrList< bagPriceList.size(); indexInBagPrList++) {
                String priceInList = bagPriceList.get(indexInBagPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS PRICE AFTER REFINING: " + bagPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < bagPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (bagPriceListInt.get(indexAssertPriceListInt) >= bagPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each bag name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfBagSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of bags page " + (index + 1) +
                        " is: " + listOfBagSize + " bags" + "\n");
                for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                    WebElement listBag = driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                    java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                    String bagPrice = childListBag.get(bagIndex - 0).getText();
                    System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPrice + "");
                    bagPriceList.add(bagPrice);
                    System.out.println(bagPriceList);
                    bagLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfBagLastPageSize = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfBagLastPageSize + " bags" + "\n");
                    for (int bagIndexLastPage = 0;
                         bagIndexLastPage < listOfBagLastPageSize; bagIndexLastPage++) {
                        WebElement listBagLastPage = driver.findElement(LBL_BAG_PRICE_PARENT_LOCATOR);
                        java.util.List<WebElement> childListBagLastPage = driver.findElements(LBL_BAG_PRICE_CHILD_LOCATOR);
                        String bagPriceLastPage = childListBagLastPage.get(bagIndexLastPage).getText();
                        System.out.println("PRICE OF BAG NUMBER " + bagLabel + ": " + bagPriceLastPage + "");
                        bagPriceList.add(bagPriceLastPage);
                        System.out.println(bagPriceList);
                        bagLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF BAGS PRICE BEFORE PROCESSING: "+ bagPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInBagPrList= 0;
                 indexInBagPrList< bagPriceList.size();
                 indexInBagPrList++) {
                String priceInList= bagPriceList.get(indexInBagPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        bagPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF BAGS PRICE AFTER REFINING: "+ bagPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< bagPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (bagPriceListInt.get(indexAssertPriceListInt) >= bagPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous bag price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"002", "descending price sort", "clothing dsd"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (clothing) dynamically" +
                            "3. Select sort criteria (descending price)" +
                            "4. Create an array of clothing price" +
                            "5. Assert array of clothing price whether it is sorted by descending order"
    )
    public void sortClothingTest02() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {

//        assign list value= 1 when list of clothing is less than one page - absence of pagination
//        assign list value= 2 when list of clothing is more than one page - presence of pagination
        this.listValue= 1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by clothing sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", clothingCategory, driver);
        log.info("//--**------------ SELECTED CLOTHING CATEGORY -------------**--//" + "\n");

//        select sort clothing by descending price sending a text
        collect.selectDynamicSortCriteria(dsdPriceCriteria, driver);
        log.info("//--**------------ SORTED BY DESCENDING PRICE -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String clothingCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + clothingCategoryGetTxt + "\n");

        if (clothingCategoryGetTxt.equalsIgnoreCase(clothingTitle)) {

//            the clothing title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }
        if (listValue==1) {

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingPriceArray = 0;
            int listOfClothingSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
            for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                org.openqa.selenium.WebElement listClothing =
                        driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                String clothingPrice = childListClothing.get(clothingIndex).getText();
                System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPrice + "");
                clothingPriceList.add(clothingPrice);
                System.out.println(clothingPriceList);
                clothingLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING PRICE BEFORE PROCESSING: " + clothingPriceList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

            for (int indexInClothingPrList = 0;
                 indexInClothingPrList< clothingPriceList.size(); indexInClothingPrList++) {
                String priceInList = clothingPriceList.get(indexInClothingPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ") == false) {
                    if (priceInList.length() < 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                } else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar = priceInList.split(" ");
                    priceInList = splitSpacerChar[0];
                    if (priceInList.length() < 9) {
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 2; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    } else { // encompass more than 9 chars
                        String trimLastChar = priceInList.substring(0, (priceInList.length() - 1));
                        String[] splitCommaChar = trimLastChar.split(",");
                        String combinePartitions = "";
                        for (int i = 0; i < 3; i++) {
                            combinePartitions += splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            }// exit For loop
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING PRICE AFTER REFINING: " + clothingPriceListInt + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexAssertPriceListInt = 0;
                 indexAssertPriceListInt < clothingPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (clothingPriceListInt.get(indexAssertPriceListInt) >= clothingPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next bag price is less than the previous slide/sandal price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1){

//        declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second bag: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingPriceArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");

//        generate a for loop to verify whether each clothing name contains the inputted search key
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of clothing per page
                int listOfClothingSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of clothing page " + (index + 1) +
                        " is: " + listOfClothingSize + " clothing" + "\n");
                for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                    WebElement listClothing = driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                    java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                    String clothingPrice = childListClothing.get(clothingIndex - 0).getText();
                    System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPrice + "");
                    clothingPriceList.add(clothingPrice);
                    System.out.println(clothingPriceList);
                    clothingLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfClothingLastPageSize = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfClothingLastPageSize + " clothing" + "\n");
                    for (int clothingIndexLastPage = 0;
                         clothingIndexLastPage < listOfClothingLastPageSize; clothingIndexLastPage++) {
                        WebElement listClothingLastPage = driver.findElement(LBL_CLOTHING_PRICE_PARENT_LOCATOR);
                        java.util.List<WebElement> childListClothingLastPage = driver.findElements(LBL_CLOTHING_PRICE_CHILD_LOCATOR);
                        String clothingPriceLastPage = childListClothingLastPage.get(clothingIndexLastPage).getText();
                        System.out.println("PRICE OF CLOTHING NUMBER " + clothingLabel + ": " + clothingPriceLastPage + "");
                        clothingPriceList.add(clothingPriceLastPage);
                        System.out.println(clothingPriceList);
                        clothingLabel++;
                    }
                }
            }

            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF CLOTHING PRICE BEFORE PROCESSING: "+ clothingPriceList+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");

            for (int indexInClothingPrList= 0;
                 indexInClothingPrList< clothingPriceList.size();
                 indexInClothingPrList++) {
                String priceInList= clothingPriceList.get(indexInClothingPrList);
//            System.out.println(priceInList+ "\n");
                if (priceInList.contains(" ")==false) {
                    if (priceInList.length()< 9) {
//                    System.out.println("less than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else {
//                    System.out.println("more than 9 chars: "+ priceInList);
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
                else {  //price in list contains spacer character
//                System.out.println("contain spacer char: "+ priceInList);
                    String[] splitSpacerChar= priceInList.split(" ");
                    priceInList =splitSpacerChar[0];
                    if (priceInList.length()< 9) {
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<2; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                    else { // encompass more than 9 chars
                        String trimLastChar= priceInList.substring(0, (priceInList.length()-1));
                        String[] splitCommaChar= trimLastChar.split(",");
                        String combinePartitions="";
                        for (int i=0; i<3; i++) {
                            combinePartitions+= splitCommaChar[i];
                        }
//                     System.out.println(combinePartitions);
                        clothingPriceListInt.add(Integer.parseInt(combinePartitions));
                    }
                }
            } // exit For loop
            System.out.println("\n"+ "//-------------//"+ "\n");
            System.out.println("\n"+ "LIST OF CLOTHING PRICE AFTER REFINING: "+ clothingPriceListInt+ "\n");
            System.out.println("\n"+ "//-------------//"+ "\n");
            for (int indexAssertPriceListInt= 0;
                 indexAssertPriceListInt< clothingPriceListInt.size();
                 indexAssertPriceListInt++) {
                if (clothingPriceListInt.get(indexAssertPriceListInt) >= clothingPriceListInt.get(indexAssertPriceListInt + 1)) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the next clothing price is less than the previous bag price => wrong functionally
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING PRICE NUMBER " + (indexAssertPriceListInt + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }

    @Test(groups = {"003", "alphabetical sort", "sneaker ABC"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (sneaker) dynamically" +
                            "3. Select sort criteria (alphabetical order)" +
                            "4. Create an array of sneaker name with the first character" +
                            "5. Assert array of sneaker name whether it is sorted by alphabetical order"
    )
    public void sortSneakerTest03() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by sneaker sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", sneakerCategory, driver);
        log.info("//--**------------ SELECTED SNEAKER CATEGORY -------------**--//" + "\n");

//        select sort sneakers by alphabetical order sending a text
        collect.selectDynamicSortCriteria(alphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String sneakerCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + sneakerCategoryGetTxt + "\n");

        //        assert sneaker title
        if (sneakerCategoryGetTxt.equalsIgnoreCase(sneakerTitle)) {

//            the sneaker title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

//        declare a variable to count the number of click
        int clickIndex = 0;

//        declare a variable to count how many times we have to click
        int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each sneaker:
        - the first sneaker: SNEAKER NUMBER 1
        - the second sneaker: SNEAKER NUMBER 2
        , ...
 */
        int sneakerLabel = 1;
        int indexSneakerNameArray = 0;
        System.out.println("Number of page: " + (pageNodeSize + 1) + "");
        for (int index = 0; index < pageNodeSize; index++) {

//            get number of sneakers per page
            int listOfSneakersSize = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
            System.out.println("\n" + "Number of sneakers page " + (index + 1) +
                    " is: " + listOfSneakersSize + " sneakers" + "\n");
            for (int sneakerIndex = 0; sneakerIndex < listOfSneakersSize; sneakerIndex++) {
                WebElement listSneakers = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                String sneakerName = childListSneakers.get(sneakerIndex - 0).getText();
                System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerName + "");
                sneakersNameList.add(sneakerName);
                System.out.println(sneakersNameList);
                sneakerLabel++;
            }
            WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
            ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
            collect.pauseWithTryCatch(1550);
            nextButton.click();
            clickIndex += 1;
            if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                int listOfSneakersLastPageSize = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
                System.out.println("Number of sneakers page " + (pageNodeSize + 1) +
                        " is: " + listOfSneakersLastPageSize + " sneakers" + "\n");
                for (int sneakerIndexLastPage = 0; sneakerIndexLastPage < listOfSneakersLastPageSize; sneakerIndexLastPage++) {
                    WebElement listSneakersLastPage = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSneakerslastPage = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                    String sneakerNameLastPage = childListSneakerslastPage.get(sneakerIndexLastPage - 0).getText();
                    System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerNameLastPage + "");
                    sneakersNameList.add(sneakerNameLastPage);
                    System.out.println(sneakersNameList);
                    sneakerLabel++;
                }
            }
        }
        System.out.println("\n" + "//-------------//" + "\n");
        System.out.println("\n" + "LIST OF SNEAKERS NAME BEFORE PROCESSING: " + sneakersNameList + "\n");
        System.out.println("\n" + "//-------------//" + "\n");
        for (int indexInSnkNameList = 0;
             indexInSnkNameList < sneakersNameList.size();
             indexInSnkNameList++) {

//            get the first letter of sneaker name then insert it into a new list
            char frstLetterOfSnkName = (sneakersNameList.get(indexInSnkNameList)).charAt(0);
            frstCharSnkNameList.add(frstLetterOfSnkName);
        }
        System.out.println("\n" + "//-------------//" + "\n");
        System.out.println("\n" + "LIST OF SNEAKERS NAME AFTER PROCESSING: " + frstCharSnkNameList + "\n");
        System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of sneakers name whether list of first
        letter of sneakers name is following alphabetical order */
        for (int indexInSnkNameList = 0;
             indexInSnkNameList < frstCharSnkNameList.size();
             indexInSnkNameList++) {
            if (frstCharSnkNameList.get(indexInSnkNameList)
                    .compareTo(frstCharSnkNameList.get(indexInSnkNameList + 1))<= 0) {
                log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                        "SNEAKER NAME NUMBER " + (indexInSnkNameList + 1) + " --------**-------//" + "\n\n");
                this.checkPointIfPassed = true;
                this.checkPointIfSkipped = false;
                if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                    Assert.assertFalse(false);
                }
            } else { //the previous sneaker name comes before the next sneaker name in the ASCII order
                log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                        "SNEAKER NAME NUMBER " + (indexInSnkNameList + 1) + " --------**-------//" + "\n\n");
                this.checkPointIfFailed = true;
                this.checkPointIfSkipped = false;
                if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                    Assert.assertFalse(true);
                }
            }
        }
    }
    @Test(groups = {"003", "alphabetical sort", "slide/sandal ABC"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (slide/sandal) dynamically" +
                            "3. Select sort criteria (alphabetical order)" +
                            "4. Create an array of slide/sandal name with the first character" +
                            "5. Assert array of slide/sandal name whether it is sorted by alphabetical order"
    )
    public void sortSSTest03() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by slide/sandal sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", slideSandalCategory, driver);
        log.info("//--**------------ SELECTED SLIDE/SANDAL CATEGORY -------------**--//" + "\n");

//        select sort slides/sandals by alphabetical order sending a text
        collect.selectDynamicSortCriteria(alphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String ssCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + ssCategoryGetTxt + "\n");

        //        assert slide/sandal title
        if (ssCategoryGetTxt.equalsIgnoreCase(slideSandalTitle)) {

//            the slide/sandal title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + ssCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + ssCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSNameArray = 0;
            int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
            for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                org.openqa.selenium.WebElement listSS =
                        driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                String ssName = childListSS.get(ssIndex).getText();
                System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssName + "");
                ssNameList.add(ssName);
                System.out.println(ssNameList);
                ssLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInSSNameList = 0;
                 indexInSSNameList < ssNameList.size();
                 indexInSSNameList++) {

//            get the first letter of slide/sandal name then insert it into a new list
                char frstLetterOfSSName = (ssNameList.get(indexInSSNameList)).charAt(0);
                frstCharSSNameList.add(frstLetterOfSSName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME AFTER PROCESSING: " + frstCharSSNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of slides/sandals name whether list of first
                letter of slides/sandals name is following alphabetical order         */
            for (int indexInSSNameList = 0;
                 indexInSSNameList < frstCharSSNameList.size();
                 indexInSSNameList++) {
                if (frstCharSSNameList.get(indexInSSNameList)
                        .compareTo(frstCharSSNameList.get(indexInSSNameList + 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous slide/sandal name comes before the next slide/sandal name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of slides/sandals per page
                int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of slides/sandals page " + (index + 1) +
                        " is: " + listOfSSSize + " slides/sandals" + "\n");
                for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                    WebElement listSS = driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                    String ssName = childListSS.get(ssIndex - 0).getText();
                    System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssName + "");
                    ssNameList.add(ssName);
                    System.out.println(ssNameList);
                    ssLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfSSLastPageSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of slides/sandals page " + (pageNodeSize + 1) +
                            " is: " + listOfSSLastPageSize + " slides/dandals" + "\n");
                    for (int ssIndexLastPage = 0; ssIndexLastPage < listOfSSLastPageSize; ssIndexLastPage++) {
                        WebElement listSSLastPage = driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListSSlastPage = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                        String ssNameLastPage = childListSSlastPage.get(ssIndexLastPage - 0).getText();
                        System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssNameLastPage + "");
                        ssNameList.add(ssNameLastPage);
                        System.out.println(ssNameList);
                        ssLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInSSNameList = 0;
                 indexInSSNameList < ssNameList.size();
                 indexInSSNameList++) {

//            get the first letter of slide/sandal name then insert it into a new list
                char frstLetterOfSSName = (ssNameList.get(indexInSSNameList)).charAt(0);
                frstCharSSNameList.add(frstLetterOfSSName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME AFTER PROCESSING: " + frstCharSSNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of slides/sandals name whether list of first
        letter of slides/sandals name is following alphabetical order */
            for (int indexInSSNameList = 0;
                 indexInSSNameList < frstCharSSNameList.size();
                 indexInSSNameList++) {
                if (frstCharSSNameList.get(indexInSSNameList)
                        .compareTo(frstCharSSNameList.get(indexInSSNameList + 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous slide/sandal name comes before the next slide/sandal name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"003", "alphabetical sort", "bag ABC"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (bag) dynamically" +
                            "3. Select sort criteria (alphabetical order)" +
                            "4. Create an array of bag name with the first character" +
                            "5. Assert array of bag name whether it is sorted by alphabetical order"
    )
    public void sortBagTest03() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by bag sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", bagCategory, driver);
        log.info("//--**------------ SELECTED BAG CATEGORY -------------**--//" + "\n");

//        select sort bags by alphabetical order sending a text
        collect.selectDynamicSortCriteria(alphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String bagCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + bagCategoryGetTxt + "\n");

        //        assert bag title
        if (bagCategoryGetTxt.equalsIgnoreCase(bagTitle)) {

//            the bag title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagNameArray = 0;
            int listOfBagSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
            for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                org.openqa.selenium.WebElement listBag =
                        driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                String bagName = childListBag.get(bagIndex).getText();
                System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagName + "");
                bagNameList.add(bagName);
                System.out.println(bagNameList);
                bagLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME BEFORE PROCESSING: " + bagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInBagNameList = 0;
                 indexInBagNameList < bagNameList.size();
                 indexInBagNameList++) {

//            get the first letter of bag name then insert it into a new list
                char frstLetterOfBagName = (bagNameList.get(indexInBagNameList)).charAt(0);
                frstCharBagNameList.add(frstLetterOfBagName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME AFTER PROCESSING: " + frstCharBagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of bags name whether list of first
                letter of bags name is following alphabetical order         */
            for (int indexInBagNameList = 0;
                 indexInBagNameList < frstCharBagNameList.size();
                 indexInBagNameList++) {
                if (frstCharBagNameList.get(indexInBagNameList)
                        .compareTo(frstCharBagNameList.get(indexInBagNameList+ 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList+ 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes before the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfBagSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of bags page " + (index + 1) +
                        " is: " + listOfBagSize + " bags" + "\n");
                for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                    WebElement listBag = driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                    String bagName = childListBag.get(bagIndex - 0).getText();
                    System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagName + "");
                    bagNameList.add(bagName);
                    System.out.println(bagNameList);
                    bagLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfBagLastPageSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfBagLastPageSize + " bags" + "\n");
                    for (int bagIndexLastPage = 0;
                         bagIndexLastPage < listOfBagLastPageSize; bagIndexLastPage++) {
                        WebElement listBagLastPage = driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListBaglastPage = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                        String bagNameLastPage = childListBaglastPage.get(bagIndexLastPage - 0).getText();
                        System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagNameLastPage + "");
                        bagNameList.add(bagNameLastPage);
                        System.out.println(bagNameList);
                        bagLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInBagNameList = 0;
                 indexInBagNameList < bagNameList.size();
                 indexInBagNameList++) {

//            get the first letter of bag name then insert it into a new list
                char frstLetterOfBagName = (bagNameList.get(indexInBagNameList)).charAt(0);
                frstCharBagNameList.add(frstLetterOfBagName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME AFTER PROCESSING: " + frstCharBagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of bags name whether list of first
        letter of bags name is following alphabetical order */
            for (int indexInBagNameList = 0;
                 indexInBagNameList < frstCharBagNameList.size();
                 indexInBagNameList++) {
                if (frstCharBagNameList.get(indexInBagNameList)
                        .compareTo(frstCharBagNameList.get(indexInBagNameList + 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes before the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"003", "alphabetical sort", "clothing ABC"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (clothing) dynamically" +
                            "3. Select sort criteria (alphabetical order)" +
                            "4. Create an array of clothing name with the first character" +
                            "5. Assert array of clothing name whether it is sorted by alphabetical order"
    )
    public void sortClothingTest03() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by clothing sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", clothingCategory, driver);
        log.info("//--**------------ SELECTED CLOTHING CATEGORY -------------**--//" + "\n");

//        select sort clothing by alphabetical order sending a text
        collect.selectDynamicSortCriteria(alphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String clothingCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + clothingCategoryGetTxt + "\n");

//       assert clothing title
        if (clothingCategoryGetTxt.equalsIgnoreCase(clothingTitle)) {

//            the clothing title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingNameArray = 0;
            int listOfClothingSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
            for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                org.openqa.selenium.WebElement listClothing =
                        driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                String clothingName = childListClothing.get(clothingIndex).getText();
                System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingName + "");
                clothingNameList.add(clothingName);
                System.out.println(clothingNameList);
                clothingLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME BEFORE PROCESSING: " + clothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < clothingNameList.size();
                 indexInClothingNameList++) {

//            get the first letter of clothing name then insert it into a new list
                char frstLetterOfClothingName = (clothingNameList.get(indexInClothingNameList)).charAt(0);
                frstCharClothingNameList.add(frstLetterOfClothingName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME AFTER PROCESSING: " + frstCharClothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of clothing name whether list of first
                letter of clothing name is following alphabetical order         */
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < frstCharClothingNameList.size();
                 indexInClothingNameList++) {
                if (frstCharClothingNameList.get(indexInClothingNameList)
                        .compareTo(frstCharClothingNameList.get(indexInClothingNameList+ 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList+ 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes before the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfClothingSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of clothing page " + (index + 1) +
                        " is: " + listOfClothingSize + " clothing" + "\n");
                for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                    WebElement listClothing = driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                    String clothingName = childListClothing.get(clothingIndex - 0).getText();
                    System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingName + "");
                    clothingNameList.add(clothingName);
                    System.out.println(clothingNameList);
                    clothingLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfClothingLastPageSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of clothing page " + (pageNodeSize + 1) +
                            " is: " + listOfClothingLastPageSize + " bags" + "\n");
                    for (int clothingIndexLastPage = 0;
                         clothingIndexLastPage < listOfClothingLastPageSize; clothingIndexLastPage++) {
                        WebElement listClothingLastPage = driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListClothingLastPage = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                        String clothingNameLastPage =
                                childListClothingLastPage.get(clothingIndexLastPage - 0).getText();
                        System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingNameLastPage + "");
                        clothingNameList.add(clothingNameLastPage);
                        System.out.println(clothingNameList);
                        clothingLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME BEFORE PROCESSING: " + clothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < clothingNameList.size();
                 indexInClothingNameList++) {

//            get the first letter of clothing name then insert it into a new list
                char frstLetterOfClothingName = (clothingNameList.get(indexInClothingNameList)).charAt(0);
                frstCharClothingNameList.add(frstLetterOfClothingName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME AFTER PROCESSING: " + frstCharClothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of clothing name whether list of first
        letter of clothing name is following alphabetical order */
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < frstCharClothingNameList.size();
                 indexInClothingNameList++) {
                if (frstCharClothingNameList.get(indexInClothingNameList)
                        .compareTo(frstCharClothingNameList.get(indexInClothingNameList + 1))<= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous clothing name comes before the next clothing name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }

    @Test(groups = {"004", "non-alphabetical sort", "sneaker CBA"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (sneaker) dynamically" +
                            "3. Select sort criteria (non-alphabetical order)" +
                            "4. Create an array of sneaker name with the first character" +
                            "5. Assert array of sneaker name whether it is sorted by non-alphabetical order"
    )
    public void sortSneakerTest04() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by sneaker sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", sneakerCategory, driver);
        log.info("//--**------------ SELECTED SNEAKER CATEGORY -------------**--//" + "\n");

//        select sort sneakers by alphabetical order sending a text
        collect.selectDynamicSortCriteria(nonAlphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY NON-ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String sneakerCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + sneakerCategoryGetTxt + "\n");

        //        assert sneaker title
        if (sneakerCategoryGetTxt.equalsIgnoreCase(sneakerTitle)) {

//            the sneaker title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + sneakerCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

//        declare a variable to count the number of click
        int clickIndex = 0;

//        declare a variable to count how many times we have to click
        int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each sneaker:
        - the first sneaker: SNEAKER NUMBER 1
        - the second sneaker: SNEAKER NUMBER 2
        , ...
 */
        int sneakerLabel = 1;
        int indexSneakerNameArray = 0;
        System.out.println("Number of page: " + (pageNodeSize + 1) + "");
        for (int index = 0; index < pageNodeSize; index++) {

//            get number of sneakers per page
            int listOfSneakersSize = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
            System.out.println("\n" + "Number of sneakers page " + (index + 1) +
                    " is: " + listOfSneakersSize + " sneakers" + "\n");
            for (int sneakerIndex = 0; sneakerIndex < listOfSneakersSize; sneakerIndex++) {
                WebElement listSneakers = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                String sneakerName = childListSneakers.get(sneakerIndex - 0).getText();
                System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerName + "");
                sneakersNameList.add(sneakerName);
                System.out.println(sneakersNameList);
                sneakerLabel++;
            }
            WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
            ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
            collect.pauseWithTryCatch(1550);
            nextButton.click();
            clickIndex += 1;
            if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                int listOfSneakersLastPageSize = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
                System.out.println("Number of sneakers page " + (pageNodeSize + 1) +
                        " is: " + listOfSneakersLastPageSize + " sneakers" + "\n");
                for (int sneakerIndexLastPage = 0; sneakerIndexLastPage < listOfSneakersLastPageSize; sneakerIndexLastPage++) {
                    WebElement listSneakersLastPage = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSneakerslastPage = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                    String sneakerNameLastPage = childListSneakerslastPage.get(sneakerIndexLastPage - 0).getText();
                    System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel + ": " + sneakerNameLastPage + "");
                    sneakersNameList.add(sneakerNameLastPage);
                    System.out.println(sneakersNameList);
                    sneakerLabel++;
                }
            }
        }
        System.out.println("\n" + "//-------------//" + "\n");
        System.out.println("\n" + "LIST OF SNEAKERS NAME BEFORE PROCESSING: " + sneakersNameList + "\n");
        System.out.println("\n" + "//-------------//" + "\n");
        for (int indexInSnkNameList = 0;
             indexInSnkNameList < sneakersNameList.size();
             indexInSnkNameList++) {

//            get the first letter of sneaker name then insert it into a new list
            char frstLetterOfSnkName = (sneakersNameList.get(indexInSnkNameList)).charAt(0);
            frstCharSnkNameList.add(frstLetterOfSnkName);
        }
        System.out.println("\n" + "//-------------//" + "\n");
        System.out.println("\n" + "LIST OF SNEAKERS NAME AFTER PROCESSING: " + frstCharSnkNameList + "\n");
        System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of sneakers name whether list of first
        letter of sneakers name is following non-alphabetical order */
        for (int indexInSnkNameList = 0;
             indexInSnkNameList < frstCharSnkNameList.size();
             indexInSnkNameList++) {
            if (frstCharSnkNameList.get(indexInSnkNameList)
                    .compareTo(frstCharSnkNameList.get(indexInSnkNameList + 1))>= 0) {
                log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                        "SNEAKER NAME NUMBER " + (indexInSnkNameList + 1) + " --------**-------//" + "\n\n");
                this.checkPointIfPassed = true;
                this.checkPointIfSkipped = false;
                if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                    Assert.assertFalse(false);
                }
            } else { //the previous sneaker name comes after the next sneaker name in the ASCII order
                log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                        "SNEAKER NAME NUMBER " + (indexInSnkNameList + 1) + " --------**-------//" + "\n\n");
                this.checkPointIfFailed = true;
                this.checkPointIfSkipped = false;
                if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                    Assert.assertFalse(true);
                }
            }
        }
    }
    @Test(groups = {"004", "non-alphabetical sort", "slide/sandal CBA"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (slide/sandal) dynamically" +
                            "3. Select sort criteria (non-alphabetical order)" +
                            "4. Create an array of slide/sandal name with the first character" +
                            "5. Assert array of slide/sandal name whether it is sorted by non-alphabetical order"
    )
    public void sortSSTest04() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by slide/sandal sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", slideSandalCategory, driver);
        log.info("//--**------------ SELECTED SLIDE/SANDAL CATEGORY -------------**--//" + "\n");

//        select sort slides/sandals by alphabetical order sending a text
        collect.selectDynamicSortCriteria(nonAlphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY NON-ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String ssCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + ssCategoryGetTxt + "\n");

        //        assert slide/sandal title
        if (ssCategoryGetTxt.equalsIgnoreCase(slideSandalTitle)) {

//            the slide/sandal title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + ssCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + ssCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSNameArray = 0;
            int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
            for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                org.openqa.selenium.WebElement listSS =
                        driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                String ssName = childListSS.get(ssIndex).getText();
                System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssName + "");
                ssNameList.add(ssName);
                System.out.println(ssNameList);
                ssLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInSSNameList = 0;
                 indexInSSNameList < ssNameList.size();
                 indexInSSNameList++) {

//            get the first letter of slide/sandal name then insert it into a new list
                char frstLetterOfSSName = (ssNameList.get(indexInSSNameList)).charAt(0);
                frstCharSSNameList.add(frstLetterOfSSName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME AFTER PROCESSING: " + frstCharSSNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of slides/sandals name whether list of first
                letter of slides/sandals name is following non-alphabetical order         */
            for (int indexInSSNameList = 0;
                 indexInSSNameList < frstCharSSNameList.size();
                 indexInSSNameList++) {
                if (frstCharSSNameList.get(indexInSSNameList)
                        .compareTo(frstCharSSNameList.get(indexInSSNameList + 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous slide/sandal name comes before the next slide/sandal name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each slide/sandal:
        - the first slide/sandal: SLIDE/SANDAL NUMBER 1
        - the second slide/sandal: SLIDE/SANDAL NUMBER 2
        , ...
 */
            int ssLabel = 1;
            int indexSSNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of slides/sandals per page
                int listOfSSSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of slides/sandals page " + (index + 1) +
                        " is: " + listOfSSSize + " slides/sandals" + "\n");
                for (int ssIndex = 0; ssIndex < listOfSSSize; ssIndex++) {
                    WebElement listSS = driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListSS = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                    String ssName = childListSS.get(ssIndex - 0).getText();
                    System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssName + "");
                    ssNameList.add(ssName);
                    System.out.println(ssNameList);
                    ssLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfSSLastPageSize = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of slides/sandals page " + (pageNodeSize + 1) +
                            " is: " + listOfSSLastPageSize + " slides/dandals" + "\n");
                    for (int ssIndexLastPage = 0; ssIndexLastPage < listOfSSLastPageSize; ssIndexLastPage++) {
                        WebElement listSSLastPage = driver.findElement(LBL_SLIDE_SANDAL_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListSSlastPage = driver.findElements(LBL_SLIDE_SANDAL_NAME_CHILD_LOCATOR);
                        String ssNameLastPage = childListSSlastPage.get(ssIndexLastPage - 0).getText();
                        System.out.println("NAME OF SLIDE/SANDAL NUMBER " + ssLabel + ": " + ssNameLastPage + "");
                        ssNameList.add(ssNameLastPage);
                        System.out.println(ssNameList);
                        ssLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInSSNameList = 0;
                 indexInSSNameList < ssNameList.size();
                 indexInSSNameList++) {

//            get the first letter of slide/sandal name then insert it into a new list
                char frstLetterOfSSName = (ssNameList.get(indexInSSNameList)).charAt(0);
                frstCharSSNameList.add(frstLetterOfSSName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF SLIDES/SANDALS NAME AFTER PROCESSING: " + frstCharSSNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of slides/sandals name whether list of first
        letter of slides/sandals name is following non-alphabetical order */
            for (int indexInSSNameList = 0;
                 indexInSSNameList < frstCharSSNameList.size();
                 indexInSSNameList++) {
                if (frstCharSSNameList.get(indexInSSNameList)
                        .compareTo(frstCharSSNameList.get(indexInSSNameList + 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous slide/sandal name comes after the next slide/sandal name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "SLIDE/SANDAL NAME NUMBER " + (indexInSSNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"004", "non-alphabetical sort", "bag CBA"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (bag) dynamically" +
                            "3. Select sort criteria (non-alphabetical order)" +
                            "4. Create an array of bag name with the first character" +
                            "5. Assert array of bag name whether it is sorted by non-alphabetical order"
    )
    public void sortBagTest04() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by bag sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", bagCategory, driver);
        log.info("//--**------------ SELECTED BAG CATEGORY -------------**--//" + "\n");

//        select sort bags by non-alphabetical order sending a text
        collect.selectDynamicSortCriteria(nonAlphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY NON-ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String bagCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + bagCategoryGetTxt + "\n");

        //        assert bag title
        if (bagCategoryGetTxt.equalsIgnoreCase(bagTitle)) {

//            the bag title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + bagCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagNameArray = 0;
            int listOfBagSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
            for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                org.openqa.selenium.WebElement listBag =
                        driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                String bagName = childListBag.get(bagIndex).getText();
                System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagName + "");
                bagNameList.add(bagName);
                System.out.println(bagNameList);
                bagLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME BEFORE PROCESSING: " + bagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInBagNameList = 0;
                 indexInBagNameList < bagNameList.size();
                 indexInBagNameList++) {

//            get the first letter of bag name then insert it into a new list
                char frstLetterOfBagName = (bagNameList.get(indexInBagNameList)).charAt(0);
                frstCharBagNameList.add(frstLetterOfBagName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME AFTER PROCESSING: " + frstCharBagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of bags name whether list of first
                letter of bags name is following reversely alphabetical order         */
            for (int indexInBagNameList = 0;
                 indexInBagNameList < frstCharBagNameList.size();
                 indexInBagNameList++) {
                if (frstCharBagNameList.get(indexInBagNameList)
                        .compareTo(frstCharBagNameList.get(indexInBagNameList+ 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList+ 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes after the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each bag:
        - the first bag: BAG NUMBER 1
        - the second bag: BAG NUMBER 2
        , ...
 */
            int bagLabel = 1;
            int indexBagNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfBagSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of bags page " + (index + 1) +
                        " is: " + listOfBagSize + " bags" + "\n");
                for (int bagIndex = 0; bagIndex < listOfBagSize; bagIndex++) {
                    WebElement listBag = driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListBag = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                    String bagName = childListBag.get(bagIndex - 0).getText();
                    System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagName + "");
                    bagNameList.add(bagName);
                    System.out.println(bagNameList);
                    bagLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfBagLastPageSize = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of bags page " + (pageNodeSize + 1) +
                            " is: " + listOfBagLastPageSize + " bags" + "\n");
                    for (int bagIndexLastPage = 0;
                         bagIndexLastPage < listOfBagLastPageSize; bagIndexLastPage++) {
                        WebElement listBagLastPage = driver.findElement(LBL_BAG_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListBaglastPage = driver.findElements(LBL_BAG_NAME_CHILD_LOCATOR);
                        String bagNameLastPage = childListBaglastPage.get(bagIndexLastPage - 0).getText();
                        System.out.println("NAME OF BAG NUMBER " + bagLabel + ": " + bagNameLastPage + "");
                        bagNameList.add(bagNameLastPage);
                        System.out.println(bagNameList);
                        bagLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME BEFORE PROCESSING: " + ssNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInBagNameList = 0;
                 indexInBagNameList < bagNameList.size();
                 indexInBagNameList++) {

//            get the first letter of bag name then insert it into a new list
                char frstLetterOfBagName = (bagNameList.get(indexInBagNameList)).charAt(0);
                frstCharBagNameList.add(frstLetterOfBagName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF BAGS NAME AFTER PROCESSING: " + frstCharBagNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of bags name whether list of first
        letter of bags name is following non-alphabetical order */
            for (int indexInBagNameList = 0;
                 indexInBagNameList < frstCharBagNameList.size();
                 indexInBagNameList++) {
                if (frstCharBagNameList.get(indexInBagNameList)
                        .compareTo(frstCharBagNameList.get(indexInBagNameList + 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes after the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "BAG NAME NUMBER " + (indexInBagNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }
    @Test(groups = {"004", "non-alphabetical sort", "clothing CBA"},
            enabled = true,
            priority = -4,
            description =
                    "this test script is facilitate verifying the sort function" +
                            "whether it works funtionally:" +
                            "1. Navigate to collection site" +
                            "2. Select sort category (clothing) dynamically" +
                            "3. Select sort criteria (non-alphabetical order)" +
                            "4. Create an array of clothing name with the first character" +
                            "5. Assert array of clothing name whether it is sorted by non-alphabetical order"
    )
    public void sortClothingTest04() throws TimeoutException,
            InterruptedException,
            NoSuchElementException,
            CommandLine.ExecutionException {
        this.listValue=1;
        CollectionPage collect = new CollectionPage(driver);

//        select sort category by clothing sending a text of Title Attribute (getAttribute)
        collect.selectDynamicSortCategories("title", clothingCategory, driver);
        log.info("//--**------------ SELECTED CLOTHING CATEGORY -------------**--//" + "\n");

//        select sort clothing by non-alphabetical order sending a text
        collect.selectDynamicSortCriteria(nonAlphaOrderCriteria, driver);
        log.info("//--**------------ SORTED BY NON-ALPHABETICAL ORDER -------------**--//" + "\n");
//        collect.setWaitFor(LBL_CATEGORY_TITLE_LOCATOR);
        String clothingCategoryGetTxt = driver.findElement(LBL_CATEGORY_TITLE_LOCATOR).getText();
        System.out.println("" + clothingCategoryGetTxt + "\n");

//       assert clothing title
        if (clothingCategoryGetTxt.equalsIgnoreCase(clothingTitle)) {

//            the clothing title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt +
                        "\"" + " matched the expected text!" + "\n");
            }
        } else {
            this.checkPointIfPassed = false;
            this.checkPointIfFailed = true;
            this.checkPointIfSkipped = false;
            if (checkPointIfFailed) {
                Assert.assertFalse(true);
                System.out.println("Text on web: " + "\"" + clothingCategoryGetTxt + "\"" +
                        " did not match the expected text!" + "\n");
            }
        }

        if (listValue==1) {

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingNameArray = 0;
            int listOfClothingSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
            for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                org.openqa.selenium.WebElement listClothing =
                        driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                String clothingName = childListClothing.get(clothingIndex).getText();
                System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingName + "");
                clothingNameList.add(clothingName);
                System.out.println(clothingNameList);
                clothingLabel++;
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME BEFORE PROCESSING: " + clothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < clothingNameList.size();
                 indexInClothingNameList++) {

//            get the first letter of clothing name then insert it into a new list
                char frstLetterOfClothingName = (clothingNameList.get(indexInClothingNameList)).charAt(0);
                frstCharClothingNameList.add(frstLetterOfClothingName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME AFTER PROCESSING: " + frstCharClothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*              assertion of clothing name whether list of first
                letter of clothing name is following non-alphabetical order         */
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < frstCharClothingNameList.size();
                 indexInClothingNameList++) {
                if (frstCharClothingNameList.get(indexInClothingNameList)
                        .compareTo(frstCharClothingNameList.get(indexInClothingNameList+ 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList+ 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous bag name comes after the next bag name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
        else if (listValue> 1) {
//            declare a variable to count the number of click
            int clickIndex = 0;

//        declare a variable to count how many times we have to click
            int pageNodeSize = driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each clothing:
        - the first clothing: CLOTHING NUMBER 1
        - the second clothing: CLOTHING NUMBER 2
        , ...
 */
            int clothingLabel = 1;
            int indexClothingNameArray = 0;
            System.out.println("Number of page: " + (pageNodeSize + 1) + "");
            for (int index = 0; index < pageNodeSize; index++) {

//            get number of bags per page
                int listOfClothingSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
                System.out.println("\n" + "Number of clothing page " + (index + 1) +
                        " is: " + listOfClothingSize + " clothing" + "\n");
                for (int clothingIndex = 0; clothingIndex < listOfClothingSize; clothingIndex++) {
                    WebElement listClothing = driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                    java.util.List<WebElement> childListClothing = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                    String clothingName = childListClothing.get(clothingIndex - 0).getText();
                    System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingName + "");
                    clothingNameList.add(clothingName);
                    System.out.println(clothingNameList);
                    clothingLabel++;
                }
                WebElement nextButton = driver.findElement(BTN_NEXT_LOCATOR);
                ((JavascriptExecutor) driver).executeScript("scroll(0,6000)");
                collect.pauseWithTryCatch(1550);
                nextButton.click();
                clickIndex += 1;
                if (clickIndex - 1 == index && clickIndex == pageNodeSize) {
                    System.out.println("\n" + "\" //-----**------ THE LAST PAGE --------**-------//\"" + "\n");
                    int listOfClothingLastPageSize = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR).size();
                    System.out.println("Number of clothing page " + (pageNodeSize + 1) +
                            " is: " + listOfClothingLastPageSize + " bags" + "\n");
                    for (int clothingIndexLastPage = 0;
                         clothingIndexLastPage < listOfClothingLastPageSize; clothingIndexLastPage++) {
                        WebElement listClothingLastPage = driver.findElement(LBL_CLOTHING_NAME_PARENT_LOCATOR);
                        java.util.List<WebElement> childListClothingLastPage = driver.findElements(LBL_CLOTHING_NAME_CHILD_LOCATOR);
                        String clothingNameLastPage =
                                childListClothingLastPage.get(clothingIndexLastPage - 0).getText();
                        System.out.println("NAME OF CLOTHING NUMBER " + clothingLabel + ": " + clothingNameLastPage + "");
                        clothingNameList.add(clothingNameLastPage);
                        System.out.println(clothingNameList);
                        clothingLabel++;
                    }
                }
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME BEFORE PROCESSING: " + clothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < clothingNameList.size();
                 indexInClothingNameList++) {

//            get the first letter of clothing name then insert it into a new list
                char frstLetterOfClothingName = (clothingNameList.get(indexInClothingNameList)).charAt(0);
                frstCharClothingNameList.add(frstLetterOfClothingName);
            }
            System.out.println("\n" + "//-------------//" + "\n");
            System.out.println("\n" + "LIST OF CLOTHING NAME AFTER PROCESSING: " + frstCharClothingNameList + "\n");
            System.out.println("\n" + "//-------------//" + "\n");

/*        assertion of clothing name whether list of first
        letter of clothing name is following non-alphabetical order */
            for (int indexInClothingNameList = 0;
                 indexInClothingNameList < frstCharClothingNameList.size();
                 indexInClothingNameList++) {
                if (frstCharClothingNameList.get(indexInClothingNameList)
                        .compareTo(frstCharClothingNameList.get(indexInClothingNameList + 1))>= 0) {
                    log.info("\n\n//-----**------ ASSERTED SUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfPassed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfPassed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(false);
                    }
                } else { //the previous clothing name comes after the next clothing name in the ASCII order
                    log.info("\n\n//-----**------ ASSERTED UNSUCCESSFULLY " +
                            "CLOTHING NAME NUMBER " + (indexInClothingNameList + 1) + " --------**-------//" + "\n\n");
                    this.checkPointIfFailed = true;
                    this.checkPointIfSkipped = false;
                    if (this.checkPointIfFailed == true && this.checkPointIfSkipped == false) {
                        Assert.assertFalse(true);
                    }
                }
            }
        }
    }





}


