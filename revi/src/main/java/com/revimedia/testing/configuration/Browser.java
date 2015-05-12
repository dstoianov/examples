package com.revimedia.testing.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public enum Browser {
    CHROME("chrome") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            return new ChromeDriver(capabilities);
        }
    },
    FIREFOX("firefox") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            return new FirefoxDriver(capabilities);
        }
    },
    IE("ie") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },

    PHANTOMJS("phantomjs") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            return new PhantomJSDriver(capabilities);
        }
    },

    HTMLUNIT("htmlunit") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            HtmlUnitDriver driver = new HtmlUnitDriver(capabilities);
            driver.setJavascriptEnabled(true);
            return driver;
        }
    },

    SAFARI("safari") {
        @Override
        public WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },;

    private String name;

    private Browser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser getByName(String name) {
        for (Browser browser : Browser.class.getEnumConstants()) {
            if (name.equals(browser.getName())) return browser;
        }
        throw new Error("There is no such browser: " + name);
    }


    public abstract WebDriver getDriverWithCapabilities(DesiredCapabilities capabilities);
}
