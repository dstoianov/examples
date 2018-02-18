package com.tipsandtricks.TestNGRetry;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.util.RetryAnalyzerCount;

@Slf4j
public class CustomRetryAnalyzer extends RetryAnalyzerCount {

    public CustomRetryAnalyzer() {
        setCount(5);
    }

    @Override
    public boolean retryMethod(ITestResult result) {
        boolean willRetry = !result.isSuccess();
        if (willRetry) {
            result.setAttribute("retry", true);
            log.warn("Error in test '{}', still have '{}' attempts to rerun", result.getName(), getCount() - 1);
        }
        return willRetry;
    }
}

