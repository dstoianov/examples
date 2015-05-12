package com.tipsandtricks.TestNGRetry.fixReport;

import com.tipsandtricks.TestNGRetry.CustomRetryAnalyzer2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by dstoianov on 2014-11-26, 12:39 PM
 */
@Listeners(value = FixRetryListener.class)
public class SimpleTest {

    private int count = 0;

    @DataProvider(name = "dataprovider")
    public Object[][] getData() {
        return new Object[][]{{"Run1"}, {"Run2"}};
    }

    @Test(retryAnalyzer = CustomRetryAnalyzer2.class, dataProvider = "dataprovider")
    public void teste(String testName) {
        count++;
        System.out.println("---------------------------------------");
        System.out.println(testName + " " + count);
        if (count % 3 != 0) {
            Assert.fail();
        }

        count = 0;
    }

    @Test(retryAnalyzer = CustomRetryAnalyzer2.class)
    public void testMethod4() {
        int i = new Random().nextInt(3);
        System.out.println("New Run, test said: Random int is: " + i);
        Assert.assertEquals(i, 1);
    }

}
