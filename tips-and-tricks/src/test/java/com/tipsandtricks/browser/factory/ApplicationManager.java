package com.tipsandtricks.browser.factory;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 29.06.2015.
 */
public class ApplicationManager {

    private WebDriver driver;

    public ApplicationManager() {

        String name = "firefox";

        if ("remote".equals("remote") && !"gridURL".isEmpty()) {
            this.driver = Browser.getByName(name).getRemoteDriver();
        } else {
            this.driver = Browser.getByName(name).getLocalDriver();
        }


        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }
}
