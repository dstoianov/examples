package com.revimedia.tests.configuration;

import com.revimedia.testing.configuration.Config;
import com.revimedia.testing.configuration.WebDriverFactory;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.testing.configuration.proxy.BrowserMobProxyLocal2;
import com.revimedia.testing.configuration.utils.WebDriverHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;

/**
 * Created by Funker on 12.04.14.
 */

//@Listeners({WebDriverScreenshotListener.class})
public class BaseTest {
    protected WebDriver driver;
    private String url;
    protected static final Logger log = Logger.getLogger(BaseTest.class);

    public static WebDriverFactory instanceDriver;


    @BeforeClass
    @Parameters(value = {"browser", "version", "url"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("9") String version,
//                      @Optional("WIN") String platform,
                      @Optional("http://development.stagingrevi.com/auto/mfs/") String url) throws Exception {
        //@Optional("http://development.stagingrevi.com/auto/s/") String url) throws Exception {

        log.info("Start the browser...");
        this.url = url;
        log.info("Setting up parameters...");
        WebDriverFactory instanceDriver = new WebDriverFactory();

        //driver = instanceDriver.getDriver(browser, version);
        //with browserMob
        //new WebDriverFactory().createDriver(browser, version);
        driver = instanceDriver.createDriver(browser, version);

        //driver = instanceDriver.getDriver(browser, version);
        //driver = instanceDriver.getLocalDriver(browser, version);
//        driver = Browser2.getBrowser(browser, version).getDriver();

        //driver = WebDriverFactory.getDriver(browser, version, platform);
        printBrowserParameters();
        log.info("Browser is started!");
    }


    @BeforeMethod(alwaysRun = true)
    public void openMainPage(Method method, Object[] parameters) {
        log.info("------------------------------------------------------------------------------------------------------");
        log.info(getMethodFullName(method) + " Test Started.");
        log.info("------------------------------------------------------------------------------------------------------");

        BrowserMobProxyLocal2.cleanProxyHar();
        driver.get(url);

        log.info("************************ TEST DATA BEGIN ***********************************");
        for (Object parameter : parameters) {
            System.out.println(parameter.toString());
        }
        log.info("************************ TEST DATA END *************************************\n");
    }


    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(Method method, ITestResult result) {

        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.warn(getMethodFullName(method) + " - Test Finished.");
        log.warn("Test result: " + getTestResult(result));
        log.warn("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (!result.isSuccess()) {
            Reporter.setCurrentTestResult(result);
            Reporter.log("Failed test name: " + method.getName());
            takeScreenShot(getMethodFullName(method) + "_teardown_" + DataHelper.getDate());
            Reporter.setCurrentTestResult(null);
        }

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        BrowserMobProxyLocal2.stopProxy();
        driver.quit();
        log.info("Tear Down the Browser.....");
    }

    private void printBrowserParameters() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        log.info("----------- Browser Parameters -----------------------------------------------");
        log.info("Tested URL:        " + this.url);
        log.info("Browser Name:      " + cap.getBrowserName());
        log.info("Browser version:   " + cap.getVersion());
        log.info("Platform:          " + cap.getPlatform());
        log.info("----------- Browser Parameters ------------------------------------------------");
    }

    protected String getMethodFullName(Method method) {
        return method.getDeclaringClass().getCanonicalName() + "_" + method.getName();
    }

    private String getTestResult(ITestResult result) {
        int status = result.getStatus();
        String resultString;
        switch (status) {
            case 1:
                resultString = "Success";
                break;
            case 2:
                resultString = "Failure";
                break;
            case 3:
                resultString = "Skip";
                break;
            default:
                resultString = "Unknown";
        }
        return resultString;
    }


    public WebDriver getDriver() {
        return this.driver;
    }

    public void takeScreenShot(String name) {
        String path = WebDriverHelper.takeScreenShot(driver, Config.PATH_TO_SCREENS + name + ".png");
        log.info("\nScreen shot taken: " + path);
        Reporter.setEscapeHtml(false);
        String fileName = path.substring(path.lastIndexOf("\\") + 1);
        String html = "<br><b>TestNG:</b><a href=\"screens/" + fileName + "\" > failed screenshot<img align=\"center\" height=\"120\" width=\"120\" src=\"screens/" + fileName + "\"></a>";
        //String html2 = "<br><b>ReportNG:</b><a href=\"../screens/" + fileName + "\" > failed screenshot<img align=\"center\" height=\"120\" width=\"120\" src=\"../screens/" + fileName + "\"></a>";
        Reporter.log(html, true);
        //Reporter.log(html2);
    }


}
