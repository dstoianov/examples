package com.revimedia.testing.configuration.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Funker on 18.05.14.
 */
public class WebDriverHelper {

    public static String takeScreenShot(WebDriver driver, String pathName) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        TakesScreenshot takesScreenshot = (TakesScreenshot) augmentedDriver;
        File screen = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            File destinationFile = new File(pathName);
            FileUtils.copyFile(screen, destinationFile);
            return destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<screen shot not taken>";
    }

}
