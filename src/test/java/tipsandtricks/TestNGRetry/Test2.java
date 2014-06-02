package tipsandtricks.TestNGRetry;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by dstoianov on 5/28/2014, 7:02 PM.
 */
public class Test2 {

    @Test(retryAnalyzer = CustomRetryAnalyzer2.class)
    public void testMethod1() {
        System.out.println("in method1 ");
        Assert.fail();
    }


    @Test(retryAnalyzer = CustomRetryAnalyzer2.class)
    public void testForRetry2() {
        int i = new Random().nextInt(3);
        System.out.println("Random int is: " + i);
        Assert.assertEquals(i, 1);
    }


    @Test(retryAnalyzer = CustomRetryAnalyzer3.class)
    public void testForRetry3() {
        int i = new Random().nextInt(3);
        System.out.println("Random int is: " + i);
        Assert.assertEquals(i, 1);
    }
}
