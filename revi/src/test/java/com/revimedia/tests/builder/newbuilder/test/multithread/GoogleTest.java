package com.revimedia.tests.builder.newbuilder.test.multithread;

import org.apache.http.annotation.ThreadSafe;
import org.testng.annotations.Test;

/**
 * Created by Funker on 19.08.2015.
 */
@ThreadSafe
public class GoogleTest extends BaseTest {


    @Test(groups = "forDevTeam")
    public void testName1() throws Exception {
        sleep();
        driver.get("https://www.google.com/");
        sleep();

    }


    @Test(groups = "forDevTeam")
    public void testName2() throws Exception {
        sleep();
        driver.get("https://www.google.co.uk/");
        sleep();
    }
}
