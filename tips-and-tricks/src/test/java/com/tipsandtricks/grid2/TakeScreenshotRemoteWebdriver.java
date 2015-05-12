package com.tipsandtricks.grid2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 1/10/14.
 */
public class TakeScreenshotRemoteWebdriver {

    public WebDriver driver;

    @BeforeTest
    public void initDriver() {
        try {
            //DesiredCapabilities capability = DesiredCapabilities.chrome();
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            //DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
            //capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

            //Default method
            //WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

            //Custom method
            WebDriver driver = new CustomRemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runRemoteGrid() throws Exception {

        driver.get("http://www.google.com");

        // RemoteWebDriver does not implement the TakesScreenshot class
        // if the driver does have the Capabilities to take a screenshot
        // then Augmenter will add the TakesScreenshot methods to the instance
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        String uid = UUID.randomUUID().toString();
        FileUtils.copyFile(screenshot, new File("src/test/java/tipsandtricks/grid2/screenshots/screenshot2.png"));


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
