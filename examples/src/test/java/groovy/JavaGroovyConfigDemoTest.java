package groovy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Funker on 28.11.2015.
 */
public class JavaGroovyConfigDemoTest {


    @BeforeMethod
    public void setUp() throws Exception {
//        System.setProperty("evn", "dev");

    }

    @Test
    public void testName() {

        String env = System.getProperty("env");

        JavaGroovyConfig config = JavaGroovyConfig.newInstance();

        System.out.println(String.format("print data for '%s' environment", System.getProperty("env")));

        String description = config.getProperty("app.description");
        System.out.println("App description: " + description);

        String url = config.getProperty("server.URL");
        System.out.println("Server URL: " + url);

        String version = config.getProperty("app.version");
        System.out.println("app version: " + version);

        String dataset = config.getProperty("app.dataset");
        System.out.println("app dataset: " + dataset);

        Integer value = Integer.valueOf(config.getProperty("app.value"));
        System.out.println("app value: " + value);

        System.out.println("app random: " + config.getProperty("app.random"));
        System.out.println("app date: " + config.getProperty("app.date"));


    }

}
