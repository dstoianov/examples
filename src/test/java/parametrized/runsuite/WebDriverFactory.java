package parametrized.runsuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public class WebDriverFactory {

    private WebDriver driver;
    private String driverLocation = "remote";
    // private String driverLocation = "local";

/*    public WebDriver getDriver(String browserName, String version) throws Exception {
        if (driver == null) {
            return createDriver(browserName, version);
        }
        return driver;
    }*/

    public WebDriver getDriver(String browserName, String version) throws Exception {
        if (driver == null) {
            return DriverLocation.getByName(driverLocation).getDriver(Browser2.getBrowser(browserName.toLowerCase(), version));
        }
        return driver;
    }


    private WebDriver createDriver(String browserName, String version) throws Exception {

        DesiredCapabilities capability = new DesiredCapabilities();
        Browser browser = Browser.getByName(browserName);
        capability.setCapability("browserName", browser.getName());

//        if (browser.equals(Browser.IE)) {
//            capability.setCapability("browserVersion", version);
//        }

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public WebDriver getLocalDriver(String _browser, String version) {
        return Browser2.getBrowser(_browser, version).getLocalDriver();
    }

    enum DriverLocation {
        LOCAL("local") {
            @Override
            public WebDriver getDriver(Browser2 browser2) {
                return browser2.getLocalDriver();
            }
        },
        REMOTE("remote") {
            @Override
            public WebDriver getDriver(Browser2 browser2) throws Exception {
                return browser2.getRemoteDriver();
            }
        },;

        String name;

        DriverLocation(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static DriverLocation getByName(String name) {
            for (DriverLocation l : DriverLocation.class.getEnumConstants()) {
                if (l.getName().equals(name)) return l;
            }
            throw new Error("There is no such driver location: " + name);
        }

        public abstract WebDriver getDriver(Browser2 browser2) throws Exception;

    }
}
