package com.revimedia.tests.cds.DraftTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Funker on 25.05.14.
 */
public class LoadWithExtentionFF {


    public WebDriver driver;

    @BeforeMethod
    public void startBrowser() throws IOException {

        //URL resource = DummyTest.class.getResource("/searchresults.xml");
        File ext = new File("lib/ga-debug-chrome/2.6_0");
        File prof = new File("lib/chrome-profile/Default2");
        System.out.println(ext.getAbsolutePath());

        //http://peter.sh/experiments/chromium-command-line-switches/
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--console");
        //options.addArguments("--start-maximized");
        options.addArguments("--disable-translate");
        options.addArguments("--enable-extensions");
        options.addArguments("--disable-extensions-file-access-check");
        options.addArguments("--log-level=3");
        options.addArguments("user-data-dir=" + prof.getAbsolutePath());
        options.addArguments("load-extension=./lib/ga-debug-chrome/2.6_0");
        options.addArguments("load-extension=" + ext.getAbsolutePath());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        File file = new File("lib/gadebugger-1.0.2b.xpi");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        //firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.8.1"); // Avoid startup screen

        driver = new FirefoxDriver(firefoxProfile);


        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        logPrefs.enable(LogType.CLIENT, Level.ALL);
        // logPrefs.enable(LogType.DRIVER, Level.ALL);
        //logPrefs.enable(LogType.PROFILER, Level.ALL);
        //logPrefs.enable(LogType.SERVER, Level.ALL);

        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        //driver = new ChromeDriver(capabilities);

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
}
