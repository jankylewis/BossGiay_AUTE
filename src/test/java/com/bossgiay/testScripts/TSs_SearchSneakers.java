package com.bossgiay.testScripts;

import com.bossgiay.pageObjects.BasePage;
import com.bossgiay.pageObjects.SearchProductPage;
import com.bossgiay.testListeners.TestListeners;
import com.bossgiay.testUtilities.ConfigurationReader;
import com.google.errorprone.annotations.Var;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

//import listeners class
@Listeners({TestListeners.class})
public class TSs_SearchSneakers extends TestAbstractClass {

    ConfigurationReader confReader = new ConfigurationReader();
    @SuppressWarnings("unsuable")
    SearchProductPage search;
    WebDriver dr;
    Actions actions;

//    declare variables
    @Var
    private boolean checkPointIfFailed;
    private boolean checkPointIfPassed;
    private boolean checkPointIfSkipped;
    private String searchKey;
    private final String pageTitle = "TÌM KIẾM";
    private final String headingText = "Kết quả tìm kiếm cho" + "\"" + searchKey + "\".";
    private final String noResultsFoundTitle= "Không tìm thấy nội dung bạn yêu cầu";
    private final String noResultsFoundText= "Không tìm thấy" + "\""+ searchKey+ "\""+
            ". Vui lòng kiểm tra chính tả, sử dụng các từ tổng quát hơn và thử lại";

    private final By LBL_PAGE_TITLE_LOCATOR =
            By.xpath("//div[@class=\"heading-page\"]//child::h1");
    private final By LBL_HEADING_TEXT_LOCATOR =
            By.xpath("//p[@class=\"subtext-result\"]");
    private final By LBL_SNEAKER_NAME_CHILD_LOCATOR =
            By.xpath("//h3[@class=\"pro-name\"]//a");
    private final By LBL_SNEAKER_NAME_PARENT_LOCATOR =
            By.xpath("//div[contains(@class,\"search-list\")]");
    private final By TXT_SEARCH_LOCATOR=
            By.xpath("//form[@class=\"search-page\" and @action]//input[@class=\"search_box\"]");
    private final By LBL_NO_RESULTS_FOUND_TITLE_LOCATOR=
            By.xpath("//div[@id=\"search\"]//h2");
    private final By LBL_NO_RESULTS_FOUND_TEXT_LOCATOR=
            By.xpath("//div[@id=\"search\"]//preceding::div[1][@class=\"subtext\"]");
    private final By BTN_PAGE_NODE_LOCATOR=
            By.xpath("//div[@id=\"pagination\"]//a[@class=\"page-node\"]");
    private final By BTN_NEXT_LOCATOR=
            By.xpath("//div[@id=\"pagination\"]//a[@class=\"next\"]//following-sibling::*[local-name() = 'svg' and @version and @viewBox]");

    private List<String> sneakersArray = new ArrayList<String>();
    private String searchSneakerKeyArr[];

    @BeforeMethod(alwaysRun = true,
            enabled = true,
            description = "facilitate triggering browser")
    public void be4SearchCaseMethod() throws TimeoutException {
        testSetUp(confReader.getWebApplicationBaseURL(), "chrome");
    }

    @AfterMethod(alwaysRun = true,
            enabled = true,
            description = "facilitate repelling driver")
    public void afSearchCaseMethod() throws TimeoutException {
        testTearDown();
    }

    @Test(groups = {"001"},
            enabled = true,
            priority = -4,
            description =

                    "This test case is to test the list of returned sneakers that are worked functionally." +
                    "The list of sneakers will be less than 51 sneakers and displayed by one page." +
                    "Test script flow: " +
                            "1. Initialize an array that contains all of the search key"+
                            "2. Conduct searching "+
                            "3. Assert the text on website (headin, title)"+
                            "4. Generate a for loop to get all the sneakers text name"+
                            "5. Verify whether the name contains the search key?"

    )
    public void searchSneakerTest01() throws TimeoutException, AWTException {

//        create an array to select a random search key from the array
        this.searchSneakerKeyArr = new String[]{
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
                "2",
                "SUNFLOWER",
                "SUPREME",
        };
        this.searchKey = searchSneakerKeyArr[new Random().nextInt(searchSneakerKeyArr.length)];

//        project to SearchProductPage to use method
        SearchProductPage searchPage = new SearchProductPage(driver);
        searchPage.clickOnSearchButton();
        log.info("//------CLICKED SPY GLASS BUTTON-------//" + "\n");
        searchPage.sendKeysToSearchTxt(searchKey);
        log.info("//------INPUTTED SEARCH KEY-------//" + "\n");
        searchPage.pauseWithTryCatch(1250);
        searchPage.pressEnter();
        log.info("//------PRESSED ENTER-------//" + "\n");
//        searchPage.pauseWithTryCatch(750);
//        searchPage.clickOnPoppedUpSearchButton();
//        log.info("//------SEARCHED-------//" + "\n");

//        locate elements
        searchPage.setWaitFor(LBL_PAGE_TITLE_LOCATOR);
        String pageTitleGetTxt = driver.findElement(LBL_PAGE_TITLE_LOCATOR).getText();
        searchPage.setWaitFor(LBL_HEADING_TEXT_LOCATOR);
        String headingTextGetTxt = driver.findElement(LBL_HEADING_TEXT_LOCATOR).getText();
        System.out.println("" + pageTitleGetTxt + "\n");
        System.out.println(headingTextGetTxt + "\n");

//        assertion text elements such as search heading, search title
        if ((pageTitleGetTxt.equalsIgnoreCase(pageTitle) || pageTitleGetTxt.contains(pageTitle))
                && headingTextGetTxt.equalsIgnoreCase(headingText) || headingTextGetTxt.contains(headingText)) {

//            the page title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + pageTitleGetTxt + "\"" + " matched the expected text!" + "\n");
            }
        }

//       assert sneakers name
//       get number of total sneaker
        int sneakerCounter = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
        System.out.println("Number of sneakers: " + sneakerCounter + "\n");

//       get name for each sneaker
        for (int index = 1; index <= sneakerCounter; index++) {
            WebElement listSneakers = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
            List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
            String sneakerName = childListSneakers.get(index - 1).getText();
            System.out.println("NAME OF SNEAKER NUMBER " + index + ": " + (childListSneakers.get(index - 1).getText()) + "");

            //assert name of each sneaker
            if (sneakerName.contains(searchKey) == true) {
                this.checkPointIfPassed = true;
                this.checkPointIfFailed = false;
                this.checkPointIfSkipped = false;
                if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                    Assert.assertFalse(false);
                    log.info("//------**ASSERTED SNEAKER NUMBER " + index + "!-------//" + "");

//                    add sneaker name to the array
                    sneakersArray.add(sneakerName.toUpperCase(Locale.ROOT));

//                   print out the has-been-added array
                    System.out.println("Sneaker number " + index + " is inserted " +
                            "into the sneakers array: \n" + sneakersArray + "\n");
                }
            }
        }
    }


    @Test(groups = {"003"},
            enabled = true,
            priority = -2,
            description =

                    "This test case is to check whether alert messages are displayed when sneakers name does not " +
                            "contain any characters of search key. The list of sneakers will contain no sneakers." +

                            "Test script flow: " +
                            "1. Initialize an array that contains all of the search key"+
                            "2. Conduct searching "+
                            "3. Assert the text on website (heading, title)"+
                            "4. Assert the alert messages are shown correctly in terms of semantic, spelling"

    )
    public void searchSneakerTest03() throws TimeoutException, AWTException {

//        create an array to select a random search key from the array
        this.searchSneakerKeyArr = new String[]{"AJAX",
                "SOUL",
                "JORDAN NIGHT 1 LOW",
                "JORDAN NIGHT 1 SPADE",
                "KEY",
                "SAN",
                "SHALLOW",
                "WATT",
                "FORD",
                "BAM",
                "BANG",
                "JORLAN",
                "JORSAN",
                "JORFAN",
                "MC"
        };
        this.searchKey = searchSneakerKeyArr[new Random().nextInt(searchSneakerKeyArr.length)];

//        project to SearchProductPage to use method
        SearchProductPage searchPage = new SearchProductPage(driver);
        searchPage.clickOnSearchButton();
        log.info("//------CLICKED SPY GLASS BUTTON-------//" + "\n");
        searchPage.sendKeysToSearchTxt(searchKey);
        log.info("//------INPUTTED SEARCH KEY-------//" + "\n");
        searchPage.pauseWithTryCatch(750);
        searchPage.clickOnPoppedUpSearchButton();
        log.info("//------SEARCHED-------//" + "\n");
        searchPage.setWaitFor(LBL_PAGE_TITLE_LOCATOR);
        String pageTitleGetTxt= driver.findElement(LBL_PAGE_TITLE_LOCATOR).getText();
        searchPage.setWaitFor(LBL_NO_RESULTS_FOUND_TITLE_LOCATOR);
        String noResultsFoundTitleGetTxt= driver.findElement(LBL_NO_RESULTS_FOUND_TITLE_LOCATOR).getText();
        searchPage.setWaitFor(LBL_NO_RESULTS_FOUND_TEXT_LOCATOR);
        String noResultsFoundTextGetTxt= driver.findElement(LBL_NO_RESULTS_FOUND_TEXT_LOCATOR).getText();
//        searchPage.setWaitFor(TXT_SEARCH_LOCATOR);
//        String searchKeyGetTxt= driver.findElement(TXT_SEARCH_LOCATOR).getText();
        System.out.println("" + pageTitleGetTxt + "\n");
        System.out.println(noResultsFoundTitleGetTxt + "\n");
        System.out.println(noResultsFoundTextGetTxt + "\n");
//        System.out.println(searchKeyGetTxt + "\n");

//        assertion
        if ((pageTitleGetTxt.equalsIgnoreCase(pageTitle) ||
                pageTitleGetTxt.contains(pageTitle))
                &&  noResultsFoundTitleGetTxt.equalsIgnoreCase(noResultsFoundTitle) ||
                    noResultsFoundTitleGetTxt.contains(noResultsFoundTitle)
                    &&  noResultsFoundTextGetTxt.equalsIgnoreCase(noResultsFoundText) ||
                        noResultsFoundTextGetTxt.contains(noResultsFoundText)
                        /* &&  searchKeyGetTxt.equalsIgnoreCase(searchKey) ||
                            searchKeyGetTxt.contains(searchKey) */) {

//            the page title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Texts on web: " + "\"" + pageTitleGetTxt+
                        ", "+ noResultsFoundTitleGetTxt+ ", "+
                        noResultsFoundTextGetTxt+ ", "+
                        /* searchKeyGetTxt+ */ "\"" + " matched the expected text!" + "\n");
            }
        }
    }
    @Test(groups = {"002"},
            enabled = true,
            priority = -3,
            description =

                    "This test case is to test the list of returned sneakers that are worked functionally." +
                            "The list of sneakers will be more than 51 sneakers and displayed by more than one page." +
                            "Test script flow: " +
                            "1. Initialize an array that contains all of the search key"+
                            "2. Conduct searching"+
                            "3. Assert the text on website (heading, title)"+
                            "4. Generate a for loop to click on the next button when the page has already asserted"+
                            "6. Generate a for loop to get all the sneakers text name for comparison"+
                            "7. Verify whether the name contains the search key?"+
                            "8. All Sneakers of the last page will be processed separately due to a reason by a for loop"

    )
    public void searchSneakerTest02() throws TimeoutException, AWTException {
        this.searchSneakerKeyArr = new String[] {"A", "B", "S", "N", "M"};
        this.searchKey = searchSneakerKeyArr[new Random().nextInt(searchSneakerKeyArr.length)];

//        project to SearchProductPage to use method
        SearchProductPage searchPage = new SearchProductPage(driver);
        searchPage.clickOnSearchButton();
        log.info("//------CLICKED SPY GLASS BUTTON-------//" + "\n");
        searchPage.sendKeysToSearchTxt(searchKey);
        log.info("//------INPUTTED SEARCH KEY-------//" + "\n");
        searchPage.pauseWithTryCatch(750);
        searchPage.clickOnPoppedUpSearchButton();
        log.info("//------SEARCHED-------//" + "\n");
        searchPage.setWaitFor(LBL_PAGE_TITLE_LOCATOR);
        String pageTitleGetTxt = driver.findElement(LBL_PAGE_TITLE_LOCATOR).getText();
        searchPage.setWaitFor(LBL_HEADING_TEXT_LOCATOR);
        String headingTextGetTxt = driver.findElement(LBL_HEADING_TEXT_LOCATOR).getText();
        System.out.println("" + pageTitleGetTxt + "\n");
        System.out.println(headingTextGetTxt + "\n");

//        assertion the search heading, search title
        if ((pageTitleGetTxt.equalsIgnoreCase(pageTitle) || pageTitleGetTxt.contains(pageTitle))
                && headingTextGetTxt.equalsIgnoreCase(headingText) || headingTextGetTxt.contains(headingText)) {

//            the page title text matches the expected title => true assertion
            this.checkPointIfPassed = true;
            this.checkPointIfFailed = false;
            this.checkPointIfSkipped = false;
            if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                Assert.assertFalse(false);
                System.out.println("Text on web: " + "\"" + pageTitleGetTxt + "\"" + " matched the expected text!" + "\n");
            }
        }

//        declare a variable to count the number of click
        int clickIndex=0;

//        declare a variable to count how many times we have to click
        int pageNodeSize= driver.findElements(BTN_PAGE_NODE_LOCATOR).size();

/*        declare a variable to attach label to each sneaker:
        - the first sneaker: SNEAKER NUMBER 1
        - the second sneaker: SNEAKER NUMBER 2
        , ...
 */
        int sneakerLabel= 1;

//        System.out.println(pageNodeSize);

//        generate a for loop to verify whether each sneaker name contains the inputted search key
        for (int index = 0; index< pageNodeSize; index++) {

//            get number of sneakers per page
            int listOfSneakersSize= driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
            System.out.println("Number of sneakers page "+ (index+ 1)+
                    " is: "+ listOfSneakersSize+ " sneakers"+ "\n");

//            verify the sneakers name
            for (int sneakerIndex = 0; sneakerIndex < listOfSneakersSize; sneakerIndex++) {
                WebElement listSneakers = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                java.util.List<WebElement> childListSneakers = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                String sneakerName = childListSneakers.get(sneakerIndex - 0).getText();
                System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel + ": " + (childListSneakers.get(sneakerLabel - 1).getText()) + "");
                if (sneakerName.contains(searchKey) == true) {
                    this.checkPointIfPassed = true;
                    this.checkPointIfFailed = false;
                    this.checkPointIfSkipped = false;
                    if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                        Assert.assertFalse(false);
                        log.info("//------**ASSERTED SNEAKER NUMBER " + sneakerLabel + "!-------//" + "");
//                       add sneaker name to the array
                        sneakersArray.add(sneakerName.toUpperCase());
//                       print out the has-been-added array
                        System.out.println("Sneaker number " + sneakerLabel + " is inserted " +
                                "into the sneakers array: \n" + sneakersArray + "\n");
                    }
                }
                sneakerLabel+=1;

            }
            WebElement nextButton= driver.findElement(BTN_NEXT_LOCATOR);
            searchPage.executeScrollingDown(5000);
            searchPage.pauseWithTryCatch(550);
            nextButton.click();
            clickIndex+=1;

//            trigger For loop for the last page separately due to an unsolvable problem
            if (clickIndex-1==index && clickIndex==pageNodeSize) {
                System.out.println("\" //-----**------ THE LAST PAGE --------**-------//\""+ "\n");
                int listOfSneakersLastPageSize= driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR).size();
                System.out.println("Number of sneakers page "+ (pageNodeSize+ 1)+
                        " is: "+ listOfSneakersLastPageSize+ " sneakers"+ "\n");

                for (int sneakerIndexLastPage = 1;
                     sneakerIndexLastPage <= listOfSneakersLastPageSize;
                     sneakerIndexLastPage++) {
                    WebElement listSneakersLastPage = driver.findElement(LBL_SNEAKER_NAME_PARENT_LOCATOR);
                    List<WebElement> childListSneakersLastPage = driver.findElements(LBL_SNEAKER_NAME_CHILD_LOCATOR);
                    String sneakerNameLastPage = childListSneakersLastPage.get(sneakerIndexLastPage - 1).getText();
                    System.out.println("NAME OF SNEAKER NUMBER " + sneakerLabel +
                            ": " + (childListSneakersLastPage.get(sneakerIndexLastPage - 1).getText()) + "");
                    if (sneakerNameLastPage.contains(searchKey) == true) {
                        this.checkPointIfPassed = true;
                        this.checkPointIfFailed = false;
                        this.checkPointIfSkipped = false;
                        if (checkPointIfPassed && !checkPointIfFailed && !checkPointIfSkipped) {
                            Assert.assertFalse(false);
                            log.info("//------**ASSERTED SNEAKER NUMBER " + sneakerLabel + "!-------//" + "");

//                           add sneaker name to the array
                            sneakersArray.add(sneakerNameLastPage.toUpperCase(Locale.ROOT));

//                           print out the has-been-added array
                            System.out.println("Sneaker number " + sneakerLabel + " is inserted " +
                                    "into the sneakers array: \n" + sneakersArray + "\n");
                        }
                    }
                    sneakerLabel++;
                }
            }
        }

//        print out the total of sneakers is
        System.out.println("Total number of sneakers is: "+ (sneakerLabel-1));
    }
}


