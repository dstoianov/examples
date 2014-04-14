package parametrized.runsuite;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.collect.ImmutableList;

/**
 * User: stoianod
 * Date: 4/11/14
 */
public enum Browser2 {
    CHROME("chrome") {
        @Override
        public WebDriver getLocalDriver() {
            return setConfigDriver(new ChromeDriver());
        }

        @Override
        public WebDriver getRemoteDriver() throws Exception {
            return setConfigDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome()));
        }
    },
    FIREFOX("firefox") {
        @Override
        public WebDriver getLocalDriver() {
            return setConfigDriver(new FirefoxDriver());
        }

        @Override
        public WebDriver getRemoteDriver() throws Exception {
            return setConfigDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox()));
        }
    },
    SAFARI("safari") {
        @Override
        public WebDriver getLocalDriver() {
            return setConfigDriver(new SafariDriver());
        }

        @Override
        public WebDriver getRemoteDriver() throws Exception {
            return setConfigDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.safari()));
        }
    },
    IE7("ie", "7"),
    IE8("ie", "8"),
    IE9("ie", "9"),
    IE10("ie", "10"),
    IE11("ie", "11"),;

    private static WebDriver setConfigDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    Browser2(String name, String version) {
        this.name = name;
        this.version = version;
    }

    Browser2(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    private String name = "ie";
    private String version;

    private static List<Browser2> NOT_IES = ImmutableList.of(CHROME, FIREFOX, SAFARI);
    private static List<Browser2> IES = ImmutableList.of(IE7, IE8, IE9, IE10, IE11);

    public static Browser2 getBrowser(String name, String version) {
        for (Browser2 b : NOT_IES) {
            if (b.getName().equals(name)) return b;
        }

        if (!"ie".equals(name)) throw new Error("There is no such browser: " + name + ", version: " + version);

        for (Browser2 b : IES) {
            if (b.getVersion().equals(version)) return b;
        }

        throw new Error("There is no such browser: " + name + ", version: " + version);
    }

    public WebDriver getLocalDriver() {
        return setConfigDriver(new InternetExplorerDriver());
    }

    public WebDriver getRemoteDriver() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        //capabilities.setCapability("browserVersion", version);
        capabilities.setVersion(version);
        capabilities.setPlatform(Platform.ANY);
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    ;
}

