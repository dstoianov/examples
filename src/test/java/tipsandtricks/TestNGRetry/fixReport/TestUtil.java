package tipsandtricks.TestNGRetry.fixReport;

import org.testng.ITestResult;

import java.util.Arrays;

/**
 * Created by dstoianov on 2014-11-26, 12:39 PM
 */
public class TestUtil {

    public static int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = 31 * id + result.getMethod().getMethodName().hashCode();
        id = 31 * id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }
}
