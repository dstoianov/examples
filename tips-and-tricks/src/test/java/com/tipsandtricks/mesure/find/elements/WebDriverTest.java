package com.tipsandtricks.mesure.find.elements;

/**
 * Created by Funker on 26.04.2016.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

//http://jperala.fi/2016/04/11/webdriver-locator-performance/
@RunWith(Parameterized.class)
public class WebDriverTest {

    private static final String[] BROWSERS = {"firefox", "chrome", "ie11"};
    //    private static final String[] BROWSERS = {"firefox", "chrome", "edge"};
    private static final int TEST_COUNT = 100;

    private WebDriver driver;
    private By by;
    private String locator;
    private String browser;
    private static List<String> results = new ArrayList<>();

    @Parameters(name = "{index}: browser:{0}, locator:{1}")
    public static Collection locators() {
        List<Object[]> params = new ArrayList<>();
        for (String browser : BROWSERS) {
            params.add(new Object[]{browser, By.name("session[username_or_email]"), "name"});
            params.add(new Object[]{browser, By.className("email-input"), "className"});
            params.add(new Object[]{browser, By.id("signin-email"), "id"});
            params.add(new Object[]{browser, By.linkText("Forgot password?"), "linkText"});
            params.add(new Object[]{browser, By.xpath("//*[@id=\"signin-email\"]"), "xpath"});
            params.add(new Object[]{browser, By.cssSelector("input.text-input.email-input"), "cssSelector"});
        }
        return params;
    }

    public WebDriverTest(String browser, By by, String locator) {
        this.browser = browser;
        this.by = by;
        this.locator = locator;
    }

    @Before
    public void before() {
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "ie11":
                driver = new InternetExplorerDriver();
                break;
            default:
                break;
        }
    }

    @After
    public void afterClass() {
        driver.close();
        driver.quit();
    }

    @AfterClass
    public static void printResult() {
        System.out.println("=====================REPORT======================");
        for (String message : results) {
            System.out.println(message);
        }
        System.out.println("=================================================");
    }

    @Test
    public void locatorPerformance() throws InterruptedException {
        // load page
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://twitter.com/?lang=en");

        // dry-run and let browser settle a bit
        driver.findElement(by);
        TimeUnit.SECONDS.sleep(5);

        // do measurements
        long start = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            driver.findElement(by);
        }
        long stop = System.currentTimeMillis();

        String message = browser + ": Locator '" + locator + "' execution time: " + (stop - start);
        results.add(message);
        System.out.println(message);
    }
}
