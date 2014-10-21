package tipsandtricks.chromelogging;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by dstoianov on 2014-10-21.
 */
public class ChromeLoggingTest {

    private static final String WEBDRIVER_SERVER_URL = "http://localhost:4444/wd/hub";
    private static String androidPackage = null; // Assigned in main()
    //    private static String androidPackage = "com.google.android.apps.chrome";
    private WebDriver driver;

    @Test
    public void testGoogleSearch() throws Exception {
        driver.get("http://www.google.co.uk/news");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium Conference 2014");
        element.submit();
        driver.findElement(By.linkText("Web")).click();
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        if (null != androidPackage) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("androidPackage", androidPackage);
            caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        }
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

//        RemoteWebDriver chromeDriver = new RemoteWebDriver(new URL(WEBDRIVER_SERVER_URL), caps);
        ChromeDriver chromeDriver = new ChromeDriver(caps);
        driver = new Augmenter().augment(chromeDriver);
        Capabilities actualCaps = ((HasCapabilities) driver).getCapabilities();
        System.out.println("Actual caps: " + actualCaps);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        try {
            Logs logs = driver.manage().logs();
            System.out.println("Log types: " + logs.getAvailableLogTypes());
            printLog(LogType.BROWSER);
            submitPerformanceResult("Test.testGoogleSearch", logs.get(LogType.PERFORMANCE).getAll());
        } finally {
            driver.quit();
        }
    }

    void printLog(String type) {
        List<LogEntry> entries = driver.manage().logs().get(type).getAll();
        System.out.println(entries.size() + " " + type + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    void submitPerformanceResult(String name, List<LogEntry> perfLogEntries) throws IOException, JSONException {
        JSONArray devToolsLog = new JSONArray();
        System.out.println(perfLogEntries.size() + " performance log entries found");
        for (LogEntry entry : perfLogEntries) {
            JSONObject message = new JSONObject(entry.getMessage());
            JSONObject devToolsMessage = message.getJSONObject("message");

//            System.out.println(devToolsMessage.getString("method") + " " + message.getString("webview"));

            devToolsLog.put(devToolsMessage);
        }
        byte[] screenshot = null;
        if (null == androidPackage) { // Chrome on Android does not yet support screenshots
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
//        String resultUrl = new WebPageTest(new URL("http://localhost:8888/"), "Test", name).submitResult(devToolsLog, screenshot);
        String resultUrl = new WebPageTest(new URL("http://webpagetest.org/"), "Europe", name).submitResult(devToolsLog, screenshot);
        System.out.println("Result page: " + resultUrl);
    }

    public static void main(String[] argv) throws Exception {
        if (argv.length > 0 && "--android".equals(argv[0])) {
// androidPackage = "com.google.android.apps.chrome_dev";
            androidPackage = "com.google.android.apps.chrome";
        }
//        Test test = new Test();
//        test.setUp();
//        try {
//            test.testGoogleSearch();
//        } finally {
//            test.tearDown();
//        }
//    }
    }

}
