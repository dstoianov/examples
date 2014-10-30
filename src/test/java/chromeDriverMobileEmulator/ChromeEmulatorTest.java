package chromeDriverMobileEmulator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**

 */
public class ChromeEmulatorTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        Map<String, String> mobileEmulation = new HashMap<String, String>();

//        https://sites.google.com/a/chromium.org/chromedriver/mobile-emulation

//        mobileEmulation.put("deviceName", "Google Nexus 5");
        mobileEmulation.put("deviceName", "Apple iPhone 5");
//        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(capabilities);
    }


    @Test
    public void testName() throws Exception {
        driver.get("http://bbc.com");


    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
