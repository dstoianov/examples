package browser.logs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * Created by Denis Stoianov on 25/06/2014, 1:33 PM
 * E-mail: denis@revimedia.com
 */
public class Ie {

    WebDriver driver;

    @Test
    public void testName() throws Exception {

        WebDriver driver = new InternetExplorerDriver();
        ((RemoteWebDriver) driver).setLogLevel(Level.CONFIG);
        try {
            FileHandler fh = new FileHandler("c:\\log.txt");
            fh.setFormatter(new SimpleFormatter());
            java.util.logging.Logger.getLogger(RemoteWebDriver.class.getName()).addHandler(fh);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get("http://google.ru");
        driver.findElement(By.xpath("//input[1]"));
        driver.quit();

    }


    @Test
    public void testName2() throws Exception {
        InternetExplorerDriverService.Builder service = new InternetExplorerDriverService.Builder();
        service = service.withLogLevel(InternetExplorerDriverLogLevel.TRACE);
        service = service.withLogFile(new File("lib/logs/ie_log.log"));

        driver = new InternetExplorerDriver(service.build());


        driver.get("http://google.ru");
        driver.findElement(By.xpath("//input[1]"));
        driver.quit();


    }


    @Test
    public void testName3() throws Exception {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.ALL);
        logs.enable(LogType.CLIENT, Level.ALL);
        logs.enable(LogType.BROWSER, Level.ALL);

        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
//        desiredCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
//        desiredCapabilities.setCapability("ie.ensureCleanSession", true);
        desiredCapabilities.setCapability("ie.usePerProcessProxy", true);
//        desiredCapabilities.setCapability("enablePersistentHover", false);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
//        desiredCapabilities.setCapability("webdriver.remote.quietExceptions", false);
//        desiredCapabilities.setCapability("webdriver.server.session.timeout", 20);

        //RemoteWebDriver driver;
        driver = new RemoteWebDriver(new URL("http://172.31.29.21:4444/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

//        driver = new InternetExplorerDriver(desiredCapabilities);

        driver.get("http://www.facebook.com");

        Map<String, LogEntries> logTypeToEntriesMap = new HashMap<String, LogEntries>();
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for (String logType : logTypes) {
            logTypeToEntriesMap.put(logType, driver.manage().logs().get(logType));
        }

        driver.quit();

    }
}
