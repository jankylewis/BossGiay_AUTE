package com.bossgiay.pageObjects;

import com.bossgiay.testScripts.TestAbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class BasePage extends TestAbstractClass {

    public Actions actions;
    public WebDriver driver;

    public static void clickOnElementWE(WebElement element, WebDriver dr) {
        WebDriverWait wait= new WebDriverWait(dr, 30);
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    public static void clickOnElementStr(String elementXpath, WebDriver dr) {
        WebDriverWait wait= new WebDriverWait(dr, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))).click();
    }

    public static void sendKeysToElementWE(WebElement element, String key, WebDriver dr) {
        WebDriverWait wait= new WebDriverWait(dr, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(key);
    }

    public static void sendKeysToElementStr(String elementXpath, String key, WebDriver dr) {
        WebDriverWait wait= new WebDriverWait(dr, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
        dr.findElement(By.xpath(elementXpath)).clear();
        dr.findElement(By.xpath(elementXpath)).sendKeys(key);
    }

    public static void pressKey(String keycap, int times) throws AWTException {
        if (Objects.equals(keycap, "ENTER")) {
            Robot robot= new Robot();
            if (times==1) {
                robot.keyPress(KeyEvent.VK_ENTER);
            }
            else if (times>1) {
                for (int index= 0; index< times; index++) {
                    robot.keyPress(KeyEvent.VK_ENTER);
                }
            }
        }
        if (Objects.equals(keycap, "TAB")) {
            Robot robot= new Robot();
            if (times==1) {
                robot.keyPress(KeyEvent.VK_TAB);
            }
            else if (times>1) {
                for (int index= 0; index< times; index++) {
                    robot.keyPress(KeyEvent.VK_TAB);
                }
            }
        }
    }

    public static void waitUntilElementIsVisible(By element, WebDriver dr) {
        WebDriverWait wait= new WebDriverWait(dr, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void pauseWithTryCatch(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public static void executeScrollingDown(double scrollCoordinates, JavascriptExecutor js, WebDriver dr) {
        ((JavascriptExecutor)dr).executeScript("scroll(0,"+scrollCoordinates+")");
    }

}
