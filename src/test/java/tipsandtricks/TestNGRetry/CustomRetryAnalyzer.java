package tipsandtricks.TestNGRetry;

import org.testng.ITestResult;
import org.testng.util.RetryAnalyzerCount;

public class CustomRetryAnalyzer extends RetryAnalyzerCount {

    public CustomRetryAnalyzer() {
        setCount(5);
    }

    @Override
    public boolean retryMethod(ITestResult result) {
        if (result.isSuccess()) {
            return false;
        } else {
            return true;
        }
    }
}

