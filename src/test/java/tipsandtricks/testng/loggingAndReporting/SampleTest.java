package tipsandtricks.testng.loggingAndReporting;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Funker on 04.01.2015.
 */
public class SampleTest {

    @Test
    public void testMethodOne0() {
        Assert.assertTrue(true);
    }

    @Test
    public void testMethodOne1() {
        Assert.assertTrue(true);
    }

    @Test
    public void testMethodOne2() {
        Assert.assertTrue(true);
    }

    @Test
    public void testMethodTwo0() {
        Assert.assertTrue(false);
    }

    @Test
    public void testMethodTwo1() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = {"testMethodTwo0"})
    public void testMethodThree0() {
        Assert.assertTrue(true);
    }

}
