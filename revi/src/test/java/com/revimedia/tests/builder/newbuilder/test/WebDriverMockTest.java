package com.revimedia.tests.builder.newbuilder.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Funker on 08.08.2015.
 */
public class WebDriverMockTest {

    @Test
    public void testName() {
        WebDriver driver = mockDriver();

        driver.manage().timeouts().setScriptTimeout(12, TimeUnit.SECONDS);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        driver.quit();
    }

    public static WebDriver mockDriver() {
        WebDriver driver = mock(ChromeDriver.class);
        WebDriver.Options webDriverOptionsMock = mock(WebDriver.Options.class);
        Timeouts timeoutsMock = mock(Timeouts.class);
        when(driver.manage()).thenReturn(webDriverOptionsMock);
        when(driver.manage().timeouts()).thenReturn(timeoutsMock);
        return driver;
    }
}
