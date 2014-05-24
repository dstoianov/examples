package com.revimedia.testing.configuration.utils;

import com.revimedia.testing.configuration.helpers.DataHelper;
import org.testng.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class WebDriverScreenshotListener implements ITestListener, IInvokedMethodListener {

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
            //Reporter.log("<br>Open from ReportNG report:  <a href=\"../screenshots/" + outFile.getName() + "\">Screenshot</a>");
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

    private static void captureScreenshot2(File outFile) throws AWTException, IOException {
        // capture the whole screen
        BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        // Save as PNG
        ImageIO.write(screencapture, "png", outFile);
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


    private String getTestResult(ITestResult result) {
        int status = result.getStatus();
        String resultString;
        switch (status) {
            case 1:
                resultString = "Success";
                break;
            case 2:
                resultString = "Failure";
                break;
            case 3:
                resultString = "Skip";
                break;
            default:
                resultString = "Unknown";
        }
        return resultString;
    }

    protected String getMethodFullName(Method method) {
        return method.getDeclaringClass().getCanonicalName() + "_" + method.getName();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        Reporter.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Reporter.log(method.getTestMethod().getDescription() + " - Test Finished.");
        Reporter.log(method.getTestMethod().getMethodName() + " - Test Finished.");
        //Reporter.log(getMethodFullName(method) + " - Test Finished.");
        Reporter.log("Test result: " + getTestResult(result));
        Reporter.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        Reporter.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Reporter.log(method.getTestMethod().getMethodName() + " - Test Finished.");
        Reporter.log("Test result: " + getTestResult(result));
        Reporter.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    }
}
