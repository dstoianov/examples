package com.revimedia.tests.builder;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        List<String> fieldsOnPage1 = jsHelper.getFieldsOnPage(1);
//        List<String> fieldsOnPage2 = jsHelper.getFieldsOnPage(2); org.openqa.selenium.WebDriverException: unknown error: Maximum call stack size exceeded
        List<String> fieldsOnPage3 = jsHelper.getFieldsOnPage(3);

//   js.executeScript("document.getElementsByTagName('head')[0].innerHTML += '<script src=\"<PATH_TO_FILE>\" type=\"text/javascript\"></script>';");
//        js.ExecuteAsyncScript("setInterval(function () {" +
//                "    alert('Hello');" +
//                "}, 3000);" +
//                "callback();");


        Object sets = jsHelper.exec("return Bq.App.fields.getFields().get('BirthDate').get('sets');");
        String type = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('type');");
        String value = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('value');");
        String name = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('name');");
        String title = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('title');");
        boolean hidden = (boolean) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('hidden');");

//        new Element(type, name, sets, )

        Object birthDate = jsHelper.getFieldByNameLimit("BirthDate");
        Element element = new Element(birthDate);

        BuilderPage page1 = new BuilderPage(driver, fieldsOnPage1);
        page1.build();
        List<Element> fields = page1.getFields();

// for page 1
        ElementHelper.set(driver, fields.get(0), "20202");
        ElementHelper.set(driver, fields.get(1), "AAA");
        ElementHelper.set(driver, fields.get(2), "3");
        ElementHelper.set(driver, fields.get(3), "Other");
        ElementHelper.set(driver, fields.get(4), "Married");
        ElementHelper.set(driver, fields.get(5), "Excellent");


        WebElement nextButton = getNextButton();
        nextButton.click();
        jsHelper.waitForAjaxComplete();


// for page 3
        driver.get("http://development.stagingrevi.com/auto/mfs/#page/3");
        jsHelper.waitForAjaxComplete();


        BuilderPage page3 = new BuilderPage(driver, fieldsOnPage3);
        page3.build();
        List<Element> fields3 = page3.getFields();

        // for page 3
        ElementHelper.set(driver, fields.get(0), "Aderr");
        ElementHelper.set(driver, fields.get(1), "Deayetr");
        ElementHelper.set(driver, fields.get(2), "None");
        ElementHelper.set(driver, fields.get(3), "Male");
        ElementHelper.set(driver, fields.get(4), "1985-02-19");

    }

    public void jsClick(String cssSelector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var x = $(\'" + cssSelector + "\');");
        stringBuilder.append("x.click();");
        js.executeScript(stringBuilder.toString());
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

    public WebElement getNextButton() {
        return driver.findElement(By.cssSelector(".bq-step1 .bq-control.bq-type-simple"));
    }


}
