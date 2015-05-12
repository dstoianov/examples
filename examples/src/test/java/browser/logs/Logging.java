package browser.logs;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * Created by Denis Stoianov on 25/06/2014, 2:12 PM
 * E-mail: denis@revimedia.com
 */
public class Logging {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }


    @Test
    public void differentLogsShouldNotContainTheSameLogEntries() {

        driver.get("http://facebook.com");
        Map<String, LogEntries> logTypeToEntriesMap = new HashMap<String, LogEntries>();
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for (String logType : logTypes) {
            logTypeToEntriesMap.put(logType, driver.manage().logs().get(logType));
        }
        for (String firstLogType : logTypeToEntriesMap.keySet()) {
            for (String secondLogType : logTypeToEntriesMap.keySet()) {
                if (!firstLogType.equals(secondLogType)) {
                    assertFalse(String.format("Two different log types (%s, %s) should not contain the same log entries", firstLogType, secondLogType),
                            hasOverlappingLogEntries(logTypeToEntriesMap.get(firstLogType), logTypeToEntriesMap.get(secondLogType)));
                }
            }
        }

    }


    public static boolean hasOverlappingLogEntries(LogEntries firstLog, LogEntries secondLog) {
        for (LogEntry firstEntry : firstLog) {
            for (LogEntry secondEntry : secondLog) {
                if (firstEntry.getLevel().getName().equals(secondEntry.getLevel().getName()) &&
                        firstEntry.getMessage().equals(secondEntry.getMessage()) &&
                        firstEntry.getTimestamp() == secondEntry.getTimestamp()) {
                    return true;
                }
            }
        }
        return false;
    }
}
