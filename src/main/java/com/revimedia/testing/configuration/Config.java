package com.revimedia.testing.configuration;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Funker on 10.05.14.
 * https://github.com/roydekleijn/HowToUsePropertiesFile/blob/master/src/test/java/properties/Locators.java
 */
public class Config {

    private static final Logger log = Logger.getLogger(Config.class);

    public static final String BROWSER;
    public static final String VERSION;
    public static final String PLATFORM;
    public static final String APP_URL;
    public static final String IMPLICITLY_WAIT;
    public static final Boolean WINDOW_MAXIMIZE;

    public static final String GRID_HUB_IP;
    public static final String SELENIUM_PROXY_IP;
    public static final String SELENIUM_PROXY_PORT;
    public static final String DEFAULT_PROPERTIES = "localhost.properties";
    public static final String PATH_TO_SCREENS = "target/screens/";

    public static Properties props = null;

    static {

        try {
            props = new Properties();
            props.load(readPropertiesName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        BROWSER = props.getProperty("browser");
        VERSION = props.getProperty("version");
        PLATFORM = props.getProperty("platform");
        APP_URL = props.getProperty("app.url");

        IMPLICITLY_WAIT = props.getProperty("implicitly.wait");
        WINDOW_MAXIMIZE = Boolean.valueOf(props.getProperty("window.maximize"));

        GRID_HUB_IP = props.getProperty("grid.hub.ip");
        SELENIUM_PROXY_IP = props.getProperty("selenium.proxy.ip");
        SELENIUM_PROXY_PORT = props.getProperty("selenium.proxy.port");


        log.info("-------------------Settings--------------------");
        log.info("Browser:              " + BROWSER);
        log.info("Browser version:      " + VERSION);
        log.info("Platform:             " + PLATFORM);
        log.info("Application URL:      " + APP_URL);
        log.info("Implicitly Wait:      " + IMPLICITLY_WAIT);
        log.info("Window Maximize:      " + WINDOW_MAXIMIZE);
        log.info("GRID hub IP:          " + GRID_HUB_IP);
        log.info("Selenium Proxy IP:    " + SELENIUM_PROXY_IP);
        log.info("Selenium Proxy Port:  " + SELENIUM_PROXY_PORT);
    }


    private static InputStream readPropertiesName() {
        try {
            URL config = ClassLoader.getSystemResource(DEFAULT_PROPERTIES);
            if (config.equals("")) {
                throw new RuntimeException("Couldn't read properties: config name is empty");
            }
            return config.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
