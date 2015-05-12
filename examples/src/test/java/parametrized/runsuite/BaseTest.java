package parametrized.runsuite;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

/**
 * Created by Funker on 12.04.14.
 */
public class BaseTest {
    protected WebDriver driver;
    private String url;


    @BeforeClass
    @Parameters(value = {"browser", "version", "url"})
    public void setUp(@Optional("FireFox") String browser,
                      @Optional("9") String version,
//                      @Optional("WIN") String platform,
                      @Optional("http://rvmd-denis.stagingrevi.com/auto/mfs/") String url) throws Exception {

        this.url = url;

        WebDriverFactory instanceDriver = new WebDriverFactory();

        driver = instanceDriver.getDriver(browser, version);

        //driver = instanceDriver.getDriver(browser, version);
        //driver = instanceDriver.getLocalDriver(browser, version);
//        driver = Browser2.getBrowser(browser, version).getDriver();

        //driver = WebDriverFactory.getDriver(browser, version, platform);
        //driver = WebDriverFactory.getDriver(browser, version, platform);


    }


    @BeforeMethod
    public void openMainPage() {
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browsername = cap.getBrowserName();
        String browserversion = cap.getVersion();
        String url = driver.getCurrentUrl();
        System.out.println("Name: " + browsername + ", ver: " + browserversion + ", URL: " + url);
    }

}
