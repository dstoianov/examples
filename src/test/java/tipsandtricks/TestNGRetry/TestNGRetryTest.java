package tipsandtricks.TestNGRetry;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TestNGRetryTest {

    @Test(retryAnalyzer = CustomRetryAnalyzer.class)
    public void testForRetry() {
        int i = new Random().nextInt(3);
        System.out.println("Random int is: " + i);
        Assert.assertEquals(i, 1);
    }
}

