package com.tipsandtricks.highlight.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

// http://selenium.polteq.com/en/highlight-elements-with-selenium-webdriver/
//


public class HighlightElementTest {

    protected WebDriver driver;

//    public void highlightElement(WebDriver driver, WebElement element) {
//        for (int i = 0; i < 2; i++) {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
//                    element, "color: yellow; border: 2px solid yellow;");
//            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
//        }
//    }

    @Test
    public void highlightTest() {
        driver.get("https://www.google.com/webhp?hl=en&noj=1");

        WebElement searchField = driver.findElement(By.name("q"));
        highlightElement(searchField);
        searchField.sendKeys("ipod nano");

        WebElement searchButton = driver.findElement(By.name("btnK"));
        highlightElement(searchButton);
        searchButton.click();

        String searchHeader = driver.findElement(By.cssSelector("H3")).getText().toLowerCase();
        Assert.assertTrue(searchHeader.contains("ipod nano"));
    }

    public void highlightElement(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 3px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }
    }

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
