package tipsandtricks.TestNGRetry;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TestNGRetryTest {

    @Test(retryAnalyzer = CustomRetryAnalyzer.class)
    public void testMethod1() {
        int i = new Random().nextInt(3);
        System.out.println("Test said: Random int is: " + i);
        Assert.assertEquals(i, 1);
    }


    @Test(retryAnalyzer = CustomRetryAnalyzer2.class)
    public void testMethod2() {
        int i = new Random().nextInt(3);
        System.out.println("Test said: Random int is: " + i);
        Assert.assertEquals(i, 1);
    }


    @Test(retryAnalyzer = CustomRetryAnalyzer3.class)
    public void testMethod3() {
        int i = new Random().nextInt(3);
        System.out.println("New Run, test said: Random int is: " + i);
        Assert.assertEquals(i, 1);
    }
}

