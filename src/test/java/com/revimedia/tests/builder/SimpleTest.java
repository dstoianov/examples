package com.revimedia.tests.builder;


import com.revimedia.tests.builder.javascript.BuilderCampaign;
import com.revimedia.tests.builder.javascript.ElementHelper;
import com.revimedia.tests.builder.javascript.JSHelper;
import com.revimedia.tests.builder.javascript.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 07.02.2015.
 */
public class SimpleTest {

    protected WebDriver driver;
    protected JSHelper jsHelper;
    protected String url = "http://development.stagingrevi.com/auto/mfs/";
    protected Map<String, String> map = new HashMap<String, String>();

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new PhantomJSDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        jsHelper = new JSHelper(driver);
/*
        <FirstName>Dorian</FirstName>
        <LastName>Dummy</LastName>
        <gender>Female</gender>
        <birthDate>Apr 26, 1982</birthDate>
        <phoneNumber>560-803-9491</phoneNumber>
        <address>7500 Dallas Parkway</address>
        <emailAddress>sit.amet.massa@consequatenimdiam.ca</emailAddress>
        <zipCode>75024</zipCode>
        <city>PLANO</city>
        <state>TX</state>
 */

        map.put("firstName", "Dorian");
        map.put("lastName", "Dummy");
        map.put("gender", "Female");
        map.put("birthDate", "Apr 26, 1982");
        map.put("phoneNumber", "5608039491");
        map.put("address", "7500 Dallas Parkway");
        map.put("emailAddress", "sit.amet.massa@consequatenimdiam.ca");
        map.put("zipCode", "75024");
        map.put("city", "PLANO");
        map.put("state", "TX");

    }

    @Test
    public void testName() throws Exception {
        driver.get(url);
        JSHelper jsHelper = new JSHelper(driver);
        jsHelper.waitForAjaxComplete();

        int stepsCount = jsHelper.getStepsCount();

        List<String> fieldsOnPage1 = jsHelper.getFieldsOnPage(1);
//        List<String> fieldsOnPage2 = jsHelper.getFieldsOnPage(2);// org.openqa.selenium.WebDriverException: unknown error: Maximum call stack size exceeded
        List<String> fieldsOnPage3 = jsHelper.getFieldsOnPage(3);

//        Object birthDate = jsHelper.getFieldByNameLimit("BirthDate");
//        Element element = new Element(birthDate);

        Page page1 = new Page(driver, fieldsOnPage1);
        page1.build();
        List<Element> fields = page1.getElements();

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


        Page page3 = new Page(driver, fieldsOnPage3);
        page3.build();
        List<Element> fields3 = page3.getElements();

        // for page 3
        ElementHelper.set(driver, fields3.get(0), "Aderr");
        ElementHelper.set(driver, fields3.get(1), "Deayetr");
        ElementHelper.set(driver, fields3.get(2), "None");
        ElementHelper.set(driver, fields3.get(3), "Male");
        ElementHelper.set(driver, fields3.get(4), "Mar 12, 1987");
        ElementHelper.set(driver, fields3.get(5), "Ahsjdshjdf 222");
        ElementHelper.set(driver, fields3.get(6), "20202");
        ElementHelper.set(driver, fields3.get(7), "5655656565");
        ElementHelper.set(driver, fields3.get(8), "c@c.com");

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

/*    @Test
    public void expectedTest() throws Exception {
        driver.get(url);
        JSHelper jsHelper = new JSHelper(driver);
        jsHelper.waitForAjaxComplete();

        int stepsCount = jsHelper.getStepsCount();

        PagesBuilder pagesBuilder = new PagesBuilder(stepsCount);
        pagesBuilder.buildAllPages();
        pagesBuilder.populateAllPages(new UserData());
        pagesBuilder.submiteCampany();


    }*/


    @Test
    public void testNameLifeMf() throws Exception {
        driver.get("http://development.stagingrevi.com/life/mf/");
        jsHelper.waitForAjaxComplete();

        BuilderCampaign campaign = new BuilderCampaign(driver);

        campaign.buildAllPages();
        List<Page> campaignWithInitControls = campaign.getCampaign();
        campaign.fillInAllPages(map);
    }
}
