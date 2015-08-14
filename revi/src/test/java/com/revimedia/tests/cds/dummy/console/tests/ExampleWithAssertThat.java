package com.revimedia.tests.cds.dummy.console.tests;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Demonstrates how Hamcrest matchers can be used with assertThat()
 * using TestNG.
 *
 * @author Joe Walnes
 */

public class ExampleWithAssertThat {

    @Test
    public void usingAssertThatPass() {
        assertThat("xx", is("xx"));
        assertThat("yy", is(not("xx")));
        assertThat("i like cheese", containsString("cheese"));
    }

    @Test
    public void usingAssertThatFail() {
        assertThat("xx", is("xx"));
        assertThat("yy", is(not("xx")));
        assertThat("i like che_ese", containsString("cheese"));
    }

    @Test(enabled = false)
    public void usingAssertThatSkip() {
        assertThat("xx", is("xx"));
        assertThat("yy", is(not("xx")));
        assertThat("i like che_ese", containsString("cheese"));
    }

    @Test
    public void checkParametrizedBuildTest() {
        System.out.println("webdriver.chrome.driver path " + System.getProperty("webdriver.chrome.driver"));
        System.out.println("browser " + System.getProperty("browser"));
        System.out.println("Guid " + System.getProperty("guid"));
        System.out.println("Threads count " + System.getProperty("threads"));
        System.out.println("Note message " + System.getProperty("note"));
    }
}
