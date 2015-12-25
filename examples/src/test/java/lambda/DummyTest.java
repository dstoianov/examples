package lambda;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ch.lambdaj.Lambda.filter;
import static ru.yandex.qatools.matchers.webdriver.DisplayedMatcher.displayed;


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

        //driver = new ChromeDriver(service, capabilities);
        //driver = new ChromeDriver();

        driver = new PhantomJSDriver();
//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testName() throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs");
        driver.findElement(By.xpath("//button")).click();

        List<WebElement> visibleError = filter(displayed(), driver.findElements(By.xpath(".//*[contains(@class, 'error')]")));
        List<String> errorText = new ArrayList<String>();
        for (WebElement error : visibleError) {
            if (!error.getText().equals("")) {
                errorText.add(error.getText());
            }
        }
        System.out.print(errorText.toString());
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

}
