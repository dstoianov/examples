package tipsandtricks.takeScreenshotOfElement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.UUID;

//https://github.com/roydekleijn/testshop/blob/master/src/test/java/book/chapter10/AttachScreenshotToReports.java

public class TakesScreenshotAndAddItToReporter {

    WebDriver driver = new FirefoxDriver();

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String fileName = result.getName() + UUID.randomUUID();
                File targetFile = new File("target/screenshots/" + fileName + ".png");
                FileUtils.copyFile(scrFile, targetFile);
                result.setAttribute("screenshot", "<a target='blank' href='../screenshots/" + fileName + ".png'>Screenshot</a>");
                Reporter.setCurrentTestResult(result);
                Reporter.log("<a target='blank' href='" + targetFile.getAbsolutePath() + "'> Screenshot</a>\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        /* end */

    @Test
    public void dummyTest() {
        Assert.fail();
    }
}
