package com.revimedia.tests.builder.newbuilder.test.multithread;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 14.08.2015.
 */
public abstract class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        System.setProperty("phantomjs.binary.path", "./revi/src/test/resources/drivers/phantomjs.exe");

//        String browser = AppProperties.getInstance().getBrowser();

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        log.info(String.format("Start the browser '%s'", browser));

        if (browser.equalsIgnoreCase(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase(BrowserType.PHANTOMJS)) {
            driver = new PhantomJSDriver();
        } else {
            driver = new ChromeDriver();
        }

//        if (AppProperties.getInstance().isBrowserMaximize()) {
        driver.manage().window().maximize();
//        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        OnFailure.driver = driver;
    }

    @BeforeMethod
    public void beforeMethod(Method m, ITestContext ctx) {
        int i = this.getClass().getName().lastIndexOf(".");
        System.out.println(String.format("Test name '%s.%s' - %s", this.getClass().getName().substring(i + 1), m.getName(), printThreadId()));
//        String property = System.getProperty("env.text");
//        System.out.println("Profile Property is " + property);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void sleep() {
        sleep(1000);
    }

    public void sleep(long i) {
//        log.info("Waiting for '{}' sec...", i / 1000);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void randomWait() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String printThreadId() {
        long threadId = Thread.currentThread().getId();
        return String.format("Thread #%s", threadId);
    }


}
