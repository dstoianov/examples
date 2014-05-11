package com.revimedia.testing.configuration;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Funker on 10.05.14.
 */
public class Config {

    private static final Logger log = Logger.getLogger(Config.class);

    public static final String VAL1;
    public static final String VAL2;
    public static final String VAL3;
    public static final String DEFAULT_PROPERTIES = "localhost.properties";
    public static final String PATH_TO_SCREENS = "target/screens/";


    public static final Properties props = null;


    static {


        // props = loadProperties(readPropertiesName());

        try {
            props.load(loadProperties(readPropertiesName()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        VAL1 = props.getProperty("environment.name");
        VAL2 = props.getProperty("autosysprops");
        VAL3 = props.getProperty("app.url");


    }

    private static InputStream loadProperties(String config) {
        InputStream input = null;
        input = Config.class.getClassLoader().getResourceAsStream(config);
        return input;
    }

    private static String readPropertiesName() {
        URL config = ClassLoader.getSystemResource(DEFAULT_PROPERTIES);
        //String config = System.getProperty(DEFAULT_PROPERTIES);
        if (config.equals("")) {
            throw new RuntimeException("Couldn't read properties: config name is empty");
        }
        return null;
    }

}
