package com.tipsandtricks.hamcrestCustomeMatcher;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import static com.tipsandtricks.hamcrestCustomeMatcher.AllFieldsMatcher.samePropertyValuesAs;
import static com.tipsandtricks.hamcrestCustomeMatcher.DocumentLoadedMatcher.contentLoaded;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ozapolska on 09.11.2014.
 */
public class HamcrestTest {

    @Test
    public void testName() throws Exception {
        SomeObject someObject1 = new SomeObject("one", null, "three", "four", "rrr");
        SomeObject someObject2 = new SomeObject("zero", "two", "three", "four", null);

        assertThat(someObject1, samePropertyValuesAs(someObject2));
    }

    @Test
    public void testName2() throws Exception {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://development.stagingrevi.com/auto/mfs/");

        Thread.sleep(3000);

        assertThat(driver, is(contentLoaded()));

        driver.quit();

    }
}
