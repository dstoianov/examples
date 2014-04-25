package com.revimedia.testing.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public enum Browser {
    CHROME("chrome") {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.chrome();
        }
    },
    FIREFOX("firefox") {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.firefox();
        }
    },
    IE("ie") {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.internetExplorer();
        }
    },


    SAFARI("safari") {
        @Override
        public DesiredCapabilities getDesiredCapabilities() {
            return DesiredCapabilities.safari();
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


    public abstract DesiredCapabilities getDesiredCapabilities();
}
