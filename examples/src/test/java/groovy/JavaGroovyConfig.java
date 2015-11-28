package groovy;

import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Funker on 28.11.2015.
 */
public class JavaGroovyConfig {

    private static final String CONFIG_PATH = "examples/src/test/java/groovy/property.groovy";
    private static final String ENV = System.getProperty("env", "uat");
    private static final JavaGroovyConfig JAVA_GROOVY_CONFIG = new JavaGroovyConfig();
    private static ConfigObject config;

    private JavaGroovyConfig() {
    }

    public static JavaGroovyConfig newInstance() {
        // Read in 'config.groovy' for the development environment.
        try {
            File file = new File(CONFIG_PATH);
            config = new ConfigSlurper(ENV).parse(file.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return JAVA_GROOVY_CONFIG;
    }

    public String getProperty(String key) {
        return config.toProperties().getProperty(key);
    }
}

