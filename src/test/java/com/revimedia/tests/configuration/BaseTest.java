package com.revimedia.tests.configuration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import com.revimedia.testing.configuration.WebDriverFactory;

/**
 * Created by Funker on 12.04.14.
 */
public class BaseTest {
    protected WebDriver driver;
    private String url;
    protected static final Logger log = Logger.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters(value = {"browser", "version", "url"})
    public void setUp(@Optional("firefox") String browser,
                      @Optional("9") String version,
//                      @Optional("WIN") String platform,
                      @Optional("http://development.stagingrevi.com/auto/mfs/") String url) throws Exception {

        this.url = url;
        log.info("some info");
        WebDriverFactory instanceDriver = new WebDriverFactory();

        driver = instanceDriver.getDriver(browser, version);

        //driver = instanceDriver.getDriver(browser, version);
        //driver = instanceDriver.getLocalDriver(browser, version);
//        driver = Browser2.getBrowser(browser, version).getDriver();

        //driver = WebDriverFactory.getDriver(browser, version, platform);
        //driver = WebDriverFactory.getDriver(browser, version, platform);


    }


    @BeforeMethod
    public void openMainPage() {
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browsername = cap.getBrowserName();
        String browserversion = cap.getVersion();
        String url = driver.getCurrentUrl();
        System.out.println("Name: " + browsername + ", ver: " + browserversion + ", URL: " + url);
    }

}
