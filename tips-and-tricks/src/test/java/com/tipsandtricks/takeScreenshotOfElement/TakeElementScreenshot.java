package com.tipsandtricks.takeScreenshotOfElement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TakeElementScreenshot {

    private final String screenshotFolder = "target/screenshots/";
    protected WebDriver driver;

    public TakeElementScreenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void shoot(WebElement element) {
        try {
            File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Point p = element.getLocation();

            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            Rectangle rect = new Rectangle(width, height);

            BufferedImage img = ImageIO.read(screen);
            BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

            ImageIO.write(dest, "png", screen);
            FileUtils.copyFile(screen, new File(screenshotFolder + System.nanoTime() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}