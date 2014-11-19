package com.revimedia.testing.configuration.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Funker on 26.04.14.
 */
@SuppressWarnings(value = "unchecked")
public class JsUtils {
    protected static final Logger log = Logger.getLogger(JsUtils.class);
    private JavascriptExecutor js;

    public JsUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public static <T> T execute(String js, WebDriver driver) {
        return (T) execute(js, driver, new Object[0]);
    }

    public static <T> T execute(String js, WebDriver driver, Object... arguments) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (T) jsExecutor.executeScript(js, arguments);
    }

    public String getXMLSubmit() { //it used just for test debug
        String xml = (String) js.executeScript("return Bq.App.getXML(Bq.App.fields.getFields());");
        return xml.substring(8); //skip "XMLBody=".length = 8 of message
    }

    public String getPPAndTU() {
        String script = "return $('.page-pp-tc').html();";
        return (String) exec(script);
    }

    public String getPPAndTC() {
        String script = "return $('.bq-copy').html();";
        return (String) exec(script);
    }

    public String getTCPADisclaimer() {
        String script = "return $('.bq-tcpa-container').html();";
        return (String) exec(script);
    }

    public String getDisclaimer() {
        String script = "return $('.bq-disclaimer-container').html();";
        return (String) exec(script);
    }

    private Object exec(String script) {
        log.info("Execute Script on page: " + script);
        return js.executeScript(script);
    }

    public boolean isAjaxComplete() {
        System.out.println("Ajax return $.active == 0  is: '" + js.executeScript("return $.active") + "'");
        return (Boolean) js.executeScript("return $.active == 0");
    }

    public boolean isPageLoaded() {
        return js.executeScript("return document.readyState").equals("complete");
    }

    public String getSurveyPath() {
        String script = "return _bqOptions.surveyPath;"; //Bq.App.settings.get('updateSurvey')
        return (String) exec(script);
    }
}
