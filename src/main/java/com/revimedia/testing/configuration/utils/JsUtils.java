package com.revimedia.testing.configuration.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by Funker on 26.04.14.
 */
public class JsUtils {
    private JavascriptExecutor js;

    public JsUtils(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    private void exec(String script) {
        //log.info(script);
        js.executeScript(script);
    }

    public boolean isAjaxComplete() {
        final Boolean result = (Boolean) js.executeScript("return $.active == 0");
        return result;
    }
}
