package com.tipsandtricks.grid2;

/**
 * Created by User on 1/7/14.
 */

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class Grid2Testing {
    WebDriver driver;


    @Test
    public void runRemoteGrid() throws Exception {

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);

        driver.get("http://www.google.com");

        // RemoteWebDriver does not implement the TakesScreenshot class
        // if the driver does have the Capabilities to take a screenshot
        // then Augmenter will add the TakesScreenshot methods to the instance
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/java/tipsandtricks/grid2/screenshots/screenshot2.png"));


    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}