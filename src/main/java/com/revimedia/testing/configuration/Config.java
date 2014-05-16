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

    public static final int IMPLICITLY_WAIT;
    public static final Boolean WINDOW_MAXIMIZE;

    public static final Boolean IS_GRID_USE;
    public static final String GRID_HUB_IP;
    public static final String SELENIUM_PROXY_IP;
    public static final int SELENIUM_PROXY_PORT;
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

        IMPLICITLY_WAIT = Integer.valueOf(props.getProperty("implicitly.wait"));
        WINDOW_MAXIMIZE = Boolean.valueOf(props.getProperty("window.maximize"));

        IS_GRID_USE = Boolean.valueOf(props.getProperty("is.grid.use"));
        GRID_HUB_IP = props.getProperty("grid.hub.ip");
        SELENIUM_PROXY_IP = props.getProperty("selenium.proxy.ip");
        SELENIUM_PROXY_PORT = Integer.valueOf(props.getProperty("selenium.proxy.port"));


        log.info("-------------------Settings--------------------");
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
