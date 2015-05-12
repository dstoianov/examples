package com.tipsandtricks.TestNGRetry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Created by dstoianov on 6/2/2014, 12:00 PM.
 */
public class CustomRetryAnalyzer3 implements IRetryAnalyzer {

    private int count = 1;
    private int maxCount = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxCount) {
                count++;
                result.setStatus(ITestResult.SUCCESS);
                String message = String.format("'%s': Error in '%s' Retrying '%s' more times", Thread.currentThread().getName(), result.getName(), (maxCount - count));
//                String message = Thread.currentThread().getName() + ": Error in " + result.getName() + " Retrying " + (maxCount - count) + " more times";
                System.out.println(message);
                Reporter.log(message);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        }
        return false;
    }
}
