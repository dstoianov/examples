package com.revimedia.tests.builder.javascript;

import com.google.common.base.Stopwatch;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 10.02.2015.
 */
public class JSHelper {

    protected static final Logger log = Logger.getLogger(JSHelper.class);
    private static final int DEFAULT_TIMEOUT = 10;
    private JavascriptExecutor js;
    private WebDriver driver;
    private WebDriverWait wait;

    private static JSHelper instance = null;

    public static JSHelper getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new JSHelper(driver);
        }
        return instance;
    }

    public JSHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        driver.manage().timeouts().setScriptTimeout(12, TimeUnit.SECONDS);
        this.js = (JavascriptExecutor) driver;
    }

    public Object exec(String script) {
        log.info("Execute js script: '" + script + "'");
        return js.executeScript(script);
    }

    public boolean isAjaxComplete() {
        //return (window.jQuery != null) && (jQuery.active === 0);
//        Boolean result = (Boolean) js.executeScript("return $.active == 0");
        Boolean result = (Boolean) js.executeScript("return (window.jQuery != null) && ($.active === 0)");
//        System.out.println(String.format("Ajax return $.active == 0  is: '%s'", result));
        return result;
    }

    public boolean isPageLoaded() {
        return js.executeScript("return document.readyState").equals("complete");
    }

    public void waitForAjaxComplete() {
//        System.out.println("Waiting for ajax complete...");
        Stopwatch timer = Stopwatch.createStarted();
        final JSHelper js = new JSHelper(driver);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return js.isAjaxComplete();
            }
        });
        System.out.println("All ajax calls are complete, it took: " + timer.stop());
    }

    public int getStepsCount() {
        String script = "return Bq.App.steps.length;";
        long l = (long) exec(script);
        System.out.println(String.format("This company contain '%s' steps", l - 1));
        return (int) l;
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
        System.out.println(String.format("On page '#%s' present '%s' fields %s", i, result.size(), result.toString()));
        return result;
    }

    public Object getFieldByName(String name) {
        String script = String.format("return Bq.App.fields.getElements().get('%s').toJSON();", name);
        return exec(script);
    }

    public Object getFieldByNameLimit(String name) {
        String script = String.format("return Bq.App.fields.getElements().get('%s').toJSON();", name);
//        Object e = exec("return Error.stackTraceLimit=undefined;");
        return exec(script);
    }

}
