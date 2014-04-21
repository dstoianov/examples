package tipsandtricks.DRAFT_ScreenshotOnFail;

/**
 * Created by Funker on 21.04.14.
 */

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

//http://qtp-help.blogspot.com/2010/07/testng-take-screenshot-of-failed-test.html

public class Screenshot extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        File file = new File("");

        Reporter.setCurrentTestResult(result);
        System.out.println(file.getAbsolutePath());
        Reporter.log(file.getAbsolutePath());

        Reporter.log("screenshot saved at " + file.getAbsolutePath() + "\\reports\\" + result.getName() + ".jpg");
        Reporter.log("<a href='../" + result.getName() + ".jpg' <img src='../" + result.getName() + ".jpg' hight='100' width='100'/> </a>");
        //BaseClass.selenium.captureScreenshot(file.getAbsolutePath()+"\\reports\\"+result.getName()+".jpg");
        Reporter.setCurrentTestResult(null);
    }


    @Override
    public void onTestSkipped(ITestResult result) {
// will be called after test will be skipped
    }

    @Override
    public void onTestSuccess(ITestResult result) {
// will be called after test will pass
    }

}
