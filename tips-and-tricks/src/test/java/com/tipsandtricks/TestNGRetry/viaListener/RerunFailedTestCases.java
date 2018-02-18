package com.tipsandtricks.TestNGRetry.viaListener;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RerunFailedTestCases implements IRetryAnalyzer {
    private int count = 0;
    private int maxCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (count < maxCount) {
                count++;
                result.setStatus(ITestResult.SUCCESS);
                log.warn("RETRY: error in test '{}', retrying '{}' more times", result.getName(), (maxCount - count));
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        }
        return false;
    }
}