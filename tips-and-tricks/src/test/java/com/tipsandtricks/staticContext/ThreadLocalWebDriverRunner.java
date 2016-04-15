package com.tipsandtricks.staticContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


//barancev approach
public class ThreadLocalWebDriverRunner {

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(ITestContext context, Method method) {
//        WEB_DRIVER_THREAD_LOCAL.set(WebDriverFactory.getDriver(DesiredCapabilities.chrome()));
        WEB_DRIVER_THREAD_LOCAL.set(new FirefoxDriver());
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            WEB_DRIVER_THREAD_LOCAL.remove();
        }
    }

    public static WebDriver getDriver() {
        return WEB_DRIVER_THREAD_LOCAL.get();
    }
}
