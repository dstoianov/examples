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
            Reporter.log("<br>Open from ReportNG report:  <a href=\"../screenshots/" + outFile.getName() + "\">Screenshot</a>");
            Reporter.log("<br>Open from TestNG report: <a href=\"screenshots/" + outFile.getName() + "\">Screenshot</a>");
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
