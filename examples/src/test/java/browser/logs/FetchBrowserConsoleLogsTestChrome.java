package browser.logs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Denis Stoianov on 25/06/2014, 1:12 PM
 * E-mail: denis@revimedia.com
 */
public class FetchBrowserConsoleLogsTestChrome {
    private WebDriver driver = null;

    @Test
    public void f() {
        driver.get("http://www.facebook.com");

        Map<String, LogEntries> logTypeToEntriesMap = new HashMap<String, LogEntries>();
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for (String logType : logTypes) {
            logTypeToEntriesMap.put(logType, driver.manage().logs().get(logType));
        }


        analyzeLog();
        printLog(LogType.BROWSER);
        printLog(LogType.CLIENT);
        printLog(LogType.DRIVER);
    }

    public void printLog(String type) {
        List<LogEntry> entries = driver.manage().logs().get(type).getAll();
//        List<LogEntry> entries = driver.manage().logs().get(type).filter(Level.WARNING);
        System.out.println(entries.size() + " " + type + " log entries found");
        for (LogEntry entry : entries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    public void analyzeLog() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }
    }


    @BeforeMethod
    public void startBrowser() throws IOException {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/drivers/chromedriver.exe"))
                .usingAnyFreePort()
                .withLogFile(new File("lib/logs/chrome_log.log"))
                .build();

        //http://peter.sh/experiments/chromium-command-line-switches/

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-logging");
        options.addArguments("--v=2");

        options.addArguments("--log-level=3");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(service, capabilities);
//        driver = new ChromeDriver(capabilities);
//        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }


    @AfterMethod
    public void afterMethod() {
        driver.quit();

    }
}
