package tipsandtricks.TestNGRetry2;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dstoianov on 5/28/2014, 7:02 PM.
 */
public class Test2 {

    @Test(retryAnalyzer = CustomRetryAnalyzer2.class)
    public void testMethod1() {
        System.out.println("in method1 ");
        Assert.fail();
    }
}
