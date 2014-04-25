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

    public String getXMLBody() {
        String xml = (String) js.executeScript("alert(getSG());");
        return xml;
    }

    private void exec(String script) {
        log.info(script);
        js.executeScript(script);
    }

    public boolean isAjaxComplete() {
        final Boolean result = (Boolean) js.executeScript("return $.active == 0");
        return result;
    }


}
