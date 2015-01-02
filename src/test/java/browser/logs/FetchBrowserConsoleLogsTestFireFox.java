package browser.logs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;


public class FetchBrowserConsoleLogsTestFireFox {
    private WebDriver driver = null;

    @Test
    public void f() {
        //driver.get("http://www.facebook.com");

        driver.get("http://development.stagingrevi.com/auto/mfs/");

        Map<String, LogEntries> logTypeToEntriesMap = new HashMap<String, LogEntries>();
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for (String logType : logTypes) {
            logTypeToEntriesMap.put(logType, driver.manage().logs().get(logType));
        }
    }


    @BeforeMethod
    public void beforeMethod() {
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, Level.ALL);
        dc.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new FirefoxDriver(dc);
    }


    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            for (LogEntry eachEntry : logEntries.getAll()) {
                System.out.println(eachEntry.toString());
            }
            driver.quit();
        }
    }

}
