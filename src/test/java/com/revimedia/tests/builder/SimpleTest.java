package com.revimedia.tests.builder;


import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 07.02.2015.
 */
public class SimpleTest {

    protected WebDriver driver;
    protected String url = "http://development.stagingrevi.com/auto/mfs/";
//    private JavascriptExecutor js = (JavascriptExecutor) driver;


    @BeforeClass
    public void setUp() {
//        driver = new ChromeDriver();
        driver = new PhantomJSDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
    }


    @Test
    public void testName() throws Exception {
        driver.get(url);


        String surveyPath = getSurveyPath();
        Object settings = getSettings();
//        Object exec = exec("return Bq.App.steps.toJSON();");

        Object zipCode = getFieldByName("ZipCode");
        Object insuranceCompany = getFieldByName("InsuranceCompany");
        Object insuredSince = getFieldByName("InsuredSince");
        Object residenceType = getFieldByName("ResidenceType");
        Object maritalStatus = getFieldByName("MaritalStatus");
        Object creditRating = getFieldByName("CreditRating");

        List<String> fieldsOnPage = getFieldsOnPage(1);


        Element z = new Element(zipCode);
        Element i = new Element(insuranceCompany);
        Element is = new Element(insuredSince);
        Element r = new Element(residenceType);
        Element m = new Element(maritalStatus);
        Element c = new Element(creditRating);

        String value = BeanUtils.getProperty(insuranceCompany, "value");
        String sets = BeanUtils.getProperty(insuranceCompany, "sets");
        String type = BeanUtils.getProperty(insuranceCompany, "type");

//        ObjectMapper m = new ObjectMapper();
//        Map<String,Object> mappedObject = m.convertValue(insuranceCompany, HashMap.class);
//        Object sets2 = mappedObject.get("sets");
//        List<String> sets1 = (List<String>) mappedObject.get("sets");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }


    public String getSteps() {
        String script = "return Bq.App.steps.toJSON();";
        return (String) exec(script);
    }

    private Object exec(String script) {
        System.out.println("Execute Script on page: " + script);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }

    public String getSurveyPath() {
        String script = "return _bqOptions.surveyPath;"; //Bq.App.settings.get('updateSurvey')
        return (String) exec(script);
    }


    public Object getSettings() {
        String script = "return Bq.App.settings.toJSON();"; //Bq.App.settings.get('updateSurvey')
        return exec(script);
    }


    public Object getFieldByName(String name) {
        String script = String.format("return Bq.App.fields.getFields().get('%s').toJSON();", name);
        return exec(script);
    }

    public List<String> getFieldsOnPage(int i) throws Exception {
        List<String> result = new ArrayList<String>();
        String script = String.format("return Bq.App.steps.getStep(%s).get('content').fields;", i);

        for (Object o : (List<Object>) exec(script)) {
            if (o instanceof String) {
                result.add(o.toString());
            } else if (o instanceof Map) {
                String name = BeanUtils.getProperty(o, "name");
                result.add(name);
            } else {
                throw new Exception("Unknown instanceof of Object " + o.getClass());
            }
        }
        return result;
    }


}
