package com.revimedia.tests.cds.DraftTests;


import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.prepop.PrePop;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;


/**
 * Created by Funker on 02.05.14.
 */
public class DummyTest {
    public WebDriver driver;

    @BeforeMethod
    public void startBrowser() throws IOException {

        File ga_debug = new File("lib/ga-debug-chrome/2.6_0");
        File chromeProfile = new File("lib/chrome-profile/Default2");

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/drivers/chromedriver.exe"))
                .usingAnyFreePort()
                .withLogFile(new File("lib/chrome-profile/logs/log.log"))
                .build();

        //http://peter.sh/experiments/chromium-command-line-switches/
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-logging");
        //options.addArguments("--v=2");

        options.addArguments("--start-maximized");
        options.addArguments("--disable-translate");
        options.addArguments("--enable-extensions");
        options.addArguments("--disable-extensions-file-access-check");
        //options.addArguments("--log-level=3");
        options.addArguments("user-data-dir=" + chromeProfile.getAbsolutePath());
        //options.addArguments("load-extension=./lib/ga-debug-chrome/2.6_0");
        options.addArguments("load-extension=" + ga_debug.getAbsolutePath());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

/*        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
         //logPrefs.enable(LogType.CLIENT, Level.ALL);
         logPrefs.enable(LogType.DRIVER, Level.ALL);
       // logPrefs.enable(LogType.PROFILER, Level.ALL);
       // logPrefs.enable(LogType.SERVER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

 */

        //--------------------
        File file = new File("lib/gadebugger-1.0.2b.xpi");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        //firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.8.1"); // Avoid startup screen
        //driver = new FirefoxDriver(firefoxProfile);
        //--------------------

        driver = new ChromeDriver(service, capabilities);
        //driver = new ChromeDriver();

        //driver = new PhantomJSDriver();
        //driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        // driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void basicTest(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs");

        Robot robot = new Robot();
        tryingTornOn();
        robot.keyPress(KeyEvent.VK_F12);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_F5);
        Thread.sleep(2000);
        tryingTornOn();
        Thread.sleep(2000);
        tryingTornOn();
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_F5);

        driver.findElement(By.xpath(".//*[@id='bq-form-here']/div/div[2]/div/div/div[1]/label/input")).sendKeys("99999");

        DriverPage driverPage = new DriverPage(driver);
        VehiclePage vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        CompareAndSavePage compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);
    }

    public void tryingTornOn() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ALT);
    }


    void printLog(String type) {
        List<LogEntry> entries = driver.manage().logs().get(type).getAll();
        //Set<String> entries = driver.manage().logs().getAvailableLogTypes();
        // List<LogEntry> entries = driver.manage().logs().get(type).filter(Level.WARNING);
        System.out.println(entries.size() + " " + type + " log entries found");

        for (LogEntry entry : entries) {
            //for (String entry : entries) {
            System.out.println(
                    //new Date( entry.getTimestamp()) + " " +
                    entry.getLevel() + " " + entry.getMessage());
            //entry);


        }
    }

    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }

    @FindBys({@FindBy(tagName = "a"), @FindBy(tagName = "img")})
    List<WebElement> images;

    @Test
    public void testListToURL() throws Exception {

        ByChained byChained = new ByChained(
                By.cssSelector("djfhdj"),
                By.tagName("div"),
                By.xpath("dfdh")
        );

        driver.findElement(byChained);


    }

    @Test(dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testExitTrue(Contact contact, StaticDataAutoMFS staticData) throws Exception {


        //PrePop prePop = new PrePop(contact);
        //Class aClass = classLoader.loadClass("reflection.PrePop");
        Class aClass = PrePop.class;

        Field[] methods = aClass.getFields();

        Class<?> firstname = aClass.getDeclaredField("firstname").getType();

        driver.get("http://development.stagingrevi.com/auto/mfs");

        String url2 = PrePopParameters.generateURLForAutoMFSWithContactAndStatic(driver.getCurrentUrl(), contact, staticData);

        driver.get(url2);

    }


}
