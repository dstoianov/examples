package com.tipsandtricks.annotations;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by dstoianov on 2014-10-22.
 */
public class AnnotationExampleTest {

    @BeforeMethod
    public void beforeTest(Method testMethod) throws Exception {
        System.out.println("Run test name --> '" + testMethod.getName() + "'");
    }


    @Bug(id = {"9999", "8888"})
    @Test
    public void testName1() throws Exception {
        Assert.assertTrue(true);
    }


    @Bug(id = "7777")
    @Test(description = "test description")
    public void testName2() throws Exception {
        Assert.assertTrue(true);
    }


    @Test
    public void testName3() throws Exception {
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void after(Method testMethod) {
        Bug bug = testMethod.getAnnotation(Bug.class);
        if (bug != null) {
            for (String id : bug.id()) {
                System.out.println("This is a bug, url: '" + bug.url() + id + "'");
            }
        } else {
            System.out.println("This is no any bug annotation for this test --> '" + testMethod.getName() + "'");
        }
        Test test = testMethod.getAnnotation(Test.class);
        if (!(test.description().equals(""))) {
            System.out.println("Test description is: '" + test.description() + "'");
        }

    }
}
