package com.tipsandtricks.staticContext;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//selenide approach
public class ConcurrentHashMapWebDriverRunner {

    private static final Map<Long, WebDriver> DRIVER_MAP = new ConcurrentHashMap<>();

    @BeforeMethod
    public void setUp() {
        DRIVER_MAP.putIfAbsent(Thread.currentThread().getId(), new ChromeDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            DRIVER_MAP.remove(Thread.currentThread().getId());
        }
    }

    public static WebDriver getDriver() {
        return DRIVER_MAP.get(Thread.currentThread().getId());
    }
}
