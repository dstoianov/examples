package com.revimedia.tests.builder.newbuilder.test.multithread;

import org.apache.http.annotation.NotThreadSafe;
import org.testng.annotations.Test;

/**
 * Created by Funker on 19.08.2015.
 */
@NotThreadSafe
public class GoogleTest extends BaseTest {


    @Test
    public void testName1() throws Exception {
        sleep();
        driver.get("https://www.google.com/");
        sleep();

    }


    @Test
    public void testName2() throws Exception {
        sleep();
        driver.get("https://www.google.co.uk/");
        sleep();
    }
}
