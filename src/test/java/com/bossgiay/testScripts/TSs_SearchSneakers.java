package com.bossgiay.testScripts;

import com.bossgiay.testUtilities.ConfigurationReader;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TSs_SearchSneakers extends TestAbstractClass {

    ConfigurationReader confReader= new ConfigurationReader();

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
        testTearDown();
    }

    @Test(groups = {"001"},
            enabled = true,
                priority = -4)
    public void searchSneakerTest01() throws TimeoutException {



    }
}
