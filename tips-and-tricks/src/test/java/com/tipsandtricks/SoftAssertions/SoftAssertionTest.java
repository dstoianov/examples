package com.tipsandtricks.SoftAssertions;

import org.testng.annotations.Test;

/**
 * Created by Funker on 22.08.2015.
 */
public class SoftAssertionTest {

    protected SoftAssertions softly = new SoftAssertions();

    @Test
    public void testForSoftAssertion1() {
        softly.assertEquals("tedd", "yrt", "message 3");
        softly.assertEquals(1, 2, "message 2");
        softly.assertTrue(false, "message 1");
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion2() {
        softly.assertEquals("String", "Integer", "message 1");
        softly.assertEquals(100, 200, "message 2");
        softly.assertTrue(false);
        softly.assertEquals("Tank", "Can", "Should be can");
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion3() {
        softly.assertEquals("String", "String", "message 1");
        softly.assertEquals(200, 200, "message 2");
        softly.assertTrue(true, "message 3");
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion4() {
        softly.assertEquals("String", "Stringw");
        softly.assertEquals(100, 200);
        softly.assertTrue(false, "message 3");
        softly.assertAll();
    }
}
