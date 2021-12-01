package com.bossgiay.testScripts;

import com.beust.jcommander.Parameter;
import com.bossgiay.testUtilities.ConfigurationReader;
import com.google.errorprone.annotations.Var;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;


import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public abstract class TestAbstractClass {

    public ConfigurationReader confReader= new ConfigurationReader();

    @Var
    public final        String              baseURL= confReader.getWebApplicationBaseURL();
    public       static JavascriptExecutor  javascript;
    public       static Actions             action;
    public       static WebDriver           driver;
    public final static Logger              log= org.apache.logging.log4j.LogManager.getLogger();

    public String browserArray[]= {"firefox", "chrome", "opera", "IE", "Edge"};

    @BeforeTest
//    @Parameter(names = {"IE", "firefox", "opera", "Edge"})
    public void testSetUp(@Optional("IE"+"firefox"+"opera"+"Edge") String baseURL, String br) {
        if (br== "chrome"== true
                /*br.equalsIgnoreCase("chrome")==true ||*/
                    /*br.contains("chrome")==true*/) {
            System.setProperty("webdriver.chrome.driver", confReader.getChromeExecutablePath());
            driver= new ChromeDriver();
        }

        driver.get(baseURL);
        driver.manage().window().maximize();
        try {
            Thread.sleep(300);
        } catch (InterruptedException exception) {
                exception.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
    }

    @AfterTest
    public void testTearDown() {
        try {
                if (driver!= null== true) {
                    driver.manage().deleteAllCookies();
                    driver.close();
                    driver.quit();
                }
        } catch (final Exception exception) {
            log.error("//----------##----------- NO DRIVER HAS FOUND -----------##--------------//");
        }
    }
}
