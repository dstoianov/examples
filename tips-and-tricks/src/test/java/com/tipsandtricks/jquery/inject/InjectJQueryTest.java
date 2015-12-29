package com.tipsandtricks.jquery.inject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An example of loading jQuery dynamically using WebDriver.
 */
public class InjectJQueryTest {

    // its nice to keep JavaScript snippets in separate files.
//    private static final String JQUERY_LOAD_SCRIPT = "resources\\jQuerify.js";
    private static final String JQUERY_LOAD_SCRIPT = "src/test/resources/jquery-1.7.2.js";

    public WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void simpleTest() throws IOException {
        driver.get("http://www.google.com");
        String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);

        // give jQuery time to load asynchronously
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript(jQueryLoader /*, http://localhost:8080/jquery-1.7.2.js */);

        // ready to rock
        js.executeScript(
                "jQuery(function($) { " +
                        " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); " +
                        " }); "
        );

    }

    // helper method
    private static String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(new File(file));
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }


    @Test
    public void injectjQueryIntoGoogle() throws Exception {
        driver.get("http://www.google.com");
        assertThat(isjQueryLoaded(), is(equalTo(false)));
        injectScript("https://code.jquery.com/jquery-latest.min.js");
        assertThat(isjQueryLoaded(), is(equalTo(true)));
    }

    public Boolean isjQueryLoaded() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return typeof jQuery != 'undefined';");
    }

    public void injectScript(String scriptURL) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function injectScript(url) {\n" +
                " var script = document.createElement ('script');\n" +
                " script.src = url;\n" +
                " var head = document.getElementsByTagName('head')[0];\n" +
                " head.appendChild(script);\n" +
                "}\n" +
                "\n" +
                "var scriptURL = arguments[0];\n" +
                "injectScript(scriptURL);", scriptURL);
    }
}