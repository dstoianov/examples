package com.tipsandtricks.SoftAssertions;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


/**
 * Created by dstoianov on 2015-09-01.
 */
public class SoftAssertionHamcrestTest {

    protected SoftAssertionHamcrest softly = new SoftAssertionHamcrest();

    @Test
    public void testForSoftAssertion1() {
        softly.assertThat("Some message 1", "bbb", is("tesgh"));
        softly.assertThat("Some message 2", false, is(true));
        softly.assertThat("Some message 3", "ccc sdfsdf sdf sdf ", is(containsString("ssss")));
        softly.assertThat("Some message 4", false, is(true));
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion2() {
        softly.assertThat("Some message  jkhj1", "bbb", is("tesgh"));
        softly.assertThat("Some message gbghj 2", "aaa", is("aaa"));
        softly.assertThat("Some message  ghkjgjk3", "ccc", is("ssss"));
        softly.assertThat("Some message  ghkjgjdfgdf fghfk4", "dfdsgfd", is("ssss"));
        softly.assertThat("Some message  ghkjgjk5", false);
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion3() {
        softly.assertThat("bbb", is("bbb"));
        softly.assertThat("Some message 2", true, is(true));
        softly.assertThat("ssss", is("ssss"));
        softly.assertThat("Some message  ghkjgjk5", true);
        softly.assertAll();
    }

    @Test
    public void testForSoftAssertion4() {
        softly.assertThat("Some message 3", "ccc sdfsdf sdf sdf ", is(containsString("ssss")));
        softly.assertAll();
    }

}
