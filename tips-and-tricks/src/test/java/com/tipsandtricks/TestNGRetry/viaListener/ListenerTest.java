package com.tipsandtricks.TestNGRetry.viaListener;

import com.tipsandtricks.TestNGRetry.CustomRetryAnalyzer;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Random;

@Listeners({RetryListener.class})
public class ListenerTest {


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(method -> method.setRetryAnalyzer(new CustomRetryAnalyzer()));
    }


    @Test
    public void testName() {
        int i = new Random().nextInt(3);
        System.out.println("Test said: Random int is: " + i);
        Assert.assertEquals(i, 1);
    }
}
