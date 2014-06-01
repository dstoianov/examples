package com.revimedia.testing.configuration;

import com.revimedia.testing.configuration.config.Config;
import com.revimedia.testing.configuration.listeners.LoggingWebDriverEventListener;
import com.revimedia.testing.configuration.proxy.*;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public class WebDriverFactory {
    private WebDriver driver;
    //private BrowserMobProxy proxy;
    //private String driverLocation = "remote";
    private String driverLocation = "local";

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

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriver createDriver(String browserName, String version, Proxy seleniumProxy) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = Browser.getByName(browserName).getDriverWithCapabilities(capability);
        if (Config.WINDOW_MAXIMIZE) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT, TimeUnit.SECONDS);

        LoggingWebDriverEventListener listener = new LoggingWebDriverEventListener();
        return new EventFiringWebDriver(driver).register(listener);
        //return driver;
    }

    public WebDriver createDriver(String browserName, String version) throws Exception {
        // Browser bob
        // start the proxy
/*
        int port = 8071;
        ProxyServer server = new ProxyServer(port);
        server.start();

        // get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:" + port);
 */

        DesiredCapabilities capability = new DesiredCapabilities();
        //Browser browser = Browser.getByName(browserName);
        //capability.setCapability("browserName", browser.getName());


        // BrowserMobProxy proxy = new BrowserMobProxy();
        BrowserMobProxyLocal2.startProxy();
        Proxy proxy = BrowserMobProxyLocal2.getProxy();
        //Proxy proxy = BrowserMobProxyLocal.startBrowserMob();

        capability.setCapability(CapabilityType.PROXY, proxy);
        //driver =  Browser.getByName(browserName, capability);

        driver = Browser.getByName(browserName).getDriverWithCapabilities(capability);

        //driver =  Browser.getDriverWithCapabilities(browserName, capability);


//        if (browser.equals(Browser.IE)) {
//            capability.setCapability("browserVersion", version);
//        }

        //driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        if (Config.WINDOW_MAXIMIZE) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Config.IMPLICITLY_WAIT, TimeUnit.SECONDS);
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
