package com.tipsandtricks.browser.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by Funker on 24.02.2016.
 */
public interface BrowserSetup {

    WebDriver getLocalDriver();

    RemoteWebDriver getRemoteDriver();


}
