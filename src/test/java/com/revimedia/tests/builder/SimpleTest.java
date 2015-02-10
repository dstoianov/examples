package com.revimedia.tests.builder;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 07.02.2015.
 */
public class SimpleTest {

    protected WebDriver driver;
    protected String url = "http://development.stagingrevi.com/auto/mfs/";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new PhantomJSDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
    }

    @Test
    public void testName() throws Exception {
        driver.get(url);
        JSHelper jsHelper = new JSHelper(driver);
        jsHelper.waitForAjaxComplete();

        int stepsCount = jsHelper.getStepsCount();
        List<String> fieldsOnPage = jsHelper.getFieldsOnPage(1);

        BuilderPage builderPage = new BuilderPage(driver, fieldsOnPage);
        builderPage.build();
        List<Element> fields = builderPage.getFields();

        ElementHelper.set(driver, fields.get(0), "20202");
        ElementHelper.set(driver, fields.get(1), "AAA");
        ElementHelper.set(driver, fields.get(2), "3");
        ElementHelper.set(driver, fields.get(3), "Other");
        ElementHelper.set(driver, fields.get(4), "Married");
        ElementHelper.set(driver, fields.get(5), "Excellent");


        WebElement nextButton = getNextButton();
        nextButton.click();
        jsHelper.waitForAjaxComplete();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    public WebElement getNextButton() {
        return driver.findElement(By.cssSelector(".bq-step1 .bq-control.bq-type-simple"));
    }


}
