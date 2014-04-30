package com.revimedia.testing.configuration;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by dstoianov on 4/30/2014, 6:54 PM.
 */
public enum Browser_old {

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

    private Browser_old(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Browser_old getByName(String name) {
        for (Browser_old browser : Browser_old.class.getEnumConstants()) {
            if (name.equals(browser.getName())) return browser;
        }
        throw new Error("There is no such browser: " + name);
    }


    public abstract DesiredCapabilities getDesiredCapabilities();
}


