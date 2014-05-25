package com.revimedia.tests.cds.DraftTests;


import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.prepop.PrePop;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

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

        driver = new ChromeDriver(capabilities);

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

    @Test
    public void basicTest() throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs");
        //analyzeLog();
        //driver.navigate().refresh();
        //driver.navigate().refresh();
        driver.findElement(By.xpath(".//*[@id='bq-form-here']/div/div[2]/div/div/div[1]/label/input")).sendKeys("99999");

        Robot robot = new Robot();
        tryingTornOn();
        robot.keyPress(KeyEvent.VK_F12);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_F5);
        //driver.navigate().refresh();
        Thread.sleep(2000);
        tryingTornOn();
        Thread.sleep(2000);
        tryingTornOn();
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_F5);

        // Set<String> availableLogTypes = driver.manage().logs().getAvailableLogTypes();

        //  List<LogEntry> browserLogs = driver.manage().logs().get("browser").getAll();

        //printLog(LogType.BROWSER);
        //printLog(LogType.CLIENT);
        //printLog(LogType.DRIVER);
        //printLog(LogType.PERFORMANCE);
        //printLog(LogType.PROFILER);
//        printLog(LogType.SERVER);
        driver.findElement(By.xpath(".//*[@id='bq-form-here']/div/div[2]/div/div/div[1]/label/input")).sendKeys("99999xxxx");

        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        Object capability = cap.getCapability("userDataDir");
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


    @Test
    public void testListToURL() throws Exception {

        List<String> autoMfs = new ArrayList<String>();
        autoMfs.add("firstname");
        autoMfs.add("lastname");

        String s1 = "D:\\git_tutorial\\github.com\\examples\\target\\surefire-reports\\screenshots\\2014-05-21_12-44-13-testPositiveSubmit.png";
        // String s2 = "(560) 405-8013";

        String s11 = s1.replaceAll("\\/", "/");
        //String s22 = s2.replaceAll("[^\\d]", "");
        System.out.println(s1);
        System.out.println(s11);

    }

    @Test(dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testExitTrue(Contact contact, StaticDataAutoMFS staticData) throws Exception {


        //PrePop prePop = new PrePop(contact);
        //Class aClass = classLoader.loadClass("reflection.PrePop");
        Class aClass = PrePop.class;

        Field[] methods = aClass.getFields();

        Class<?> firstname = aClass.getDeclaredField("firstname").getType();

        driver.get("http://development.stagingrevi.com/auto/mfs");

        String url2 = PrePopParameters.getAutoMFS(driver.getCurrentUrl(), contact, staticData);

        driver.get(url2);

    }




}
