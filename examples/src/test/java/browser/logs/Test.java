package browser.logs;

/**
 * Created by Funker on 21.08.2015.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test {

    private WebDriver driver;

    public void testGoogleSearch() throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs/");
        WebElement zip = $(".bq-name-ZipCode input");
        zip.sendKeys("20202");

        selectByText($(".bq-name-InsuranceCompany select"), "AAA");
        selectByText($(".bq-name-InsuredSince select"), "4");
        selectByText($(".bq-name-ResidenceType select"), "Other");
        selectByText($(".bq-name-MaritalStatus select"), "Single");
        selectByText($(".bq-name-CreditRating select"), "Good");

        $(".bq-type-simple").click();

        Thread.sleep(2000);

    }

    public void setUp() throws Exception {
        DesiredCapabilities caps = DesiredCapabilities.chrome();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        System.setProperty("webdriver.chrome.driver", "./examples/src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver(caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Capabilities actualCaps = ((HasCapabilities) driver).getCapabilities();
        System.out.println("Actual caps: " + actualCaps);
    }

    public void tearDown() throws Exception {
        try {
            Logs logs = driver.manage().logs();
            System.out.println("Log types: " + logs.getAvailableLogTypes());
            printLog(LogType.BROWSER);

            List<LogEntry> all = logs.get(LogType.PERFORMANCE).getAll();
            int size = all.size();
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

    private void selectByText(WebElement webElement, String text) {
        new Select(webElement).selectByVisibleText(text);
    }

    private WebElement $(String css) {
        return driver.findElement(By.cssSelector(css));
    }

    public static void main(String[] argv) throws Exception {

        Test test = new Test();
        test.setUp();
        try {
            test.testGoogleSearch();
        } finally {
            test.tearDown();
        }
    }
}

