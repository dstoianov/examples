package tipsandtricks.chromelogging;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by dstoianov on 2014-10-21.
 */
public class ChromeGetPerformanceLogTest {

    RemoteWebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        driver = new ChromeDriver(capabilities);
    }


    @Test
    public void testName() throws Exception {
//        driver.get("https://www.google.com.ua/");
        driver.get("http://development.stagingrevi.com/auto/a/");
        printLogs();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    private void printLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);

        System.out.println(logEntries.getAll().size() + " performance log entries found");

        for (LogEntry entry : logEntries) {
            System.out.println(entry.toString());
        }
    }


}
