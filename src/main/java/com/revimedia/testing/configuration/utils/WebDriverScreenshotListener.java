package com.revimedia.testing.configuration.utils;

import com.revimedia.testing.configuration.helpers.DataHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class WebDriverScreenshotListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.setEscapeHtml(false);
        Reporter.setCurrentTestResult(result);
        File outputDirectory = new File(System.getProperty("user.dir"), "target/surefire-reports/screenshots");
        try {
            outputDirectory.mkdirs();
            File outFile = new File(outputDirectory, DataHelper.getDate() + "-" + result.getName() + ".png");
            captureScreenshot(outFile);
            String html = "<br><a href=\"file:///" + (outFile.getAbsolutePath()).replaceAll("\\/", "/") + "\" >Screenshot on failed page</a>";
            Reporter.log(html);

            // String s = outFile.getAbsolutePath();
            //String s1 = s.replace("\\/", "/");
            //Reporter.setEscapeHtml(false);
            // Reporter.log("<br>V3-1 Saved <a href=\"file:///" + s + "\">Screenshot</a>");
            //Reporter.log("<br>V3-2 Saved <a href=\"../screenshots/" + outFile.getName() + "\">Screenshot</a>");
            //Reporter.log("<br>V4 Saved <a href=\"screenshots/" + outFile.getName() + "\">Screenshot</a>");
            Reporter.log("<br>V5 Saved <a href=\"~/screenshots/" + outFile.getName() + "\">Screenshot</a>");


            System.out.println("Screen shot taken: " + outFile.getAbsolutePath() + "\n");
        } catch (Exception e) {
            Reporter.log("Couldn't create screenshot");
            Reporter.log(e.getMessage());
        }
        Reporter.setCurrentTestResult(null);
    }

    private static void captureScreenshot(File outFile) throws Exception {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", outFile);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

}
