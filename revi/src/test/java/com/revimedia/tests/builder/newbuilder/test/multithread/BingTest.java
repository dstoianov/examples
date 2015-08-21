package com.revimedia.tests.builder.newbuilder.test.multithread;

import org.testng.annotations.Test;

/**
 * Created by Funker on 19.08.2015.
 */
public class BingTest extends BaseTest {


    @Test
    public void testName() throws Exception {
        sleep();
        driver.get("https://www.bing.com/");
        sleep();
    }
}
