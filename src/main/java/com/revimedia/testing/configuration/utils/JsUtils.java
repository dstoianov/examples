package com.revimedia.testing.configuration.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Funker on 26.04.14.
 */
public class JsUtils {
    protected static final Logger log = Logger.getLogger(JsUtils.class);
    private JavascriptExecutor js;

    public JsUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public String getXMLSubmit() {
        String xml = (String) js.executeScript("return Bq.App.getXML(Bq.App.fields.getFields());");
        return xml.substring(8); //skip "XMLBody=".length = 8 of message
    }

    public String getPPAndTC() {
        String script = "return $('.page-pp-tc').html();";
        return (String) exec(script);
    }

    public String getTCPADisclaimer() {
        String script = "return $('.bq-tcpa-container').html();";
        return (String) exec(script);
    }

    public String getDisclaimer() {
        String script = "return $('.bq-tcpa-disclaimer').html();";
        return (String) exec(script);
    }

    private Object exec(String script) {
        log.info("Execute Script on page: " + script);
        return js.executeScript(script);
    }

    public boolean isAjaxComplete() {
        final Boolean result = (Boolean) js.executeScript("return $.active == 0");
        return result;
    }

    public static <T> T execute(String js, WebDriver driver) {
        return (T) execute(js, driver, new Object[0]);
    }

    public static <T> T execute(String js, WebDriver driver, Object... arguments) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (T) jsExecutor.executeScript(js, arguments);
    }


}
