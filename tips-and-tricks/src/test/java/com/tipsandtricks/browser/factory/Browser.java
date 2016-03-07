package com.tipsandtricks.browser.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public enum Browser implements BrowserSetup {

    CHROME("chrome") {
        @Override
        public WebDriver getLocalDriver() {
            return new ChromeDriver();
        }

        @Override
        public RemoteWebDriver getRemoteDriver() {
            return getRemote(DesiredCapabilities.chrome());
        }
    },

    FIREFOX("firefox") {
        @Override
        public WebDriver getLocalDriver() {
            return new FirefoxDriver();
        }

        @Override
        public RemoteWebDriver getRemoteDriver() {
            return getRemote(DesiredCapabilities.firefox());
        }
    },

    IE("ie") {
        @Override
        public WebDriver getLocalDriver() {
            return new InternetExplorerDriver();
        }

        @Override
        public RemoteWebDriver getRemoteDriver() {
            return getRemote(DesiredCapabilities.internetExplorer());
        }
    },

    SAFARI("safari") {
        @Override
        public WebDriver getLocalDriver() {
            return new SafariDriver();
        }

        @Override
        public RemoteWebDriver getRemoteDriver() {
            return getRemote(DesiredCapabilities.safari());
        }
    };

    private String name;

    Browser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser getByName(String name) {
        for (Browser browser : values()) {
            if (name.equals(browser.getName())) return browser;
        }
        throw new RuntimeException("There is no such browser: " + name);
    }

    protected RemoteWebDriver getRemote(DesiredCapabilities dc) {
        URL remoteAddress = null;
        try {
            remoteAddress = new URL("http", "localhost", 4444, "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(remoteAddress, dc);
    }

}
