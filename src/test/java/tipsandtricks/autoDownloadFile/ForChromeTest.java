package tipsandtricks.autoDownloadFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Funker on 09.01.2015.
 */
public class ForChromeTest {

    protected WebDriver driver;
    private String DOWNLOAD_DIR = "C:\\tmp\\selenium";

    //http://peter.sh/experiments/chromium-command-line-switches/
    ///https://sites.google.com/a/chromium.org/chromedriver/capabilities

    //http://stackoverflow.com/questions/25682766/how-to-handle-downloading-popup-alert-while-downloading-a-file-in-chrome
    //http://stackoverflow.com/questions/23530399/chrome-web-driver-download-files

    @BeforeClass
    public void getChrome() {
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", DOWNLOAD_DIR);

        ChromeOptions options = new ChromeOptions();

//        Map<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        options.setExperimentalOption("prefs", chromePrefs);
//        options.addArguments("--test-type");
//        options.addArguments("start-maximized");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
//        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testJpg() throws Exception {
        driver.get("http://the-internet.herokuapp.com/download");
        WebElement jpg = driver.findElement(By.xpath("//*[contains(text(),'jpg')]"));
        WebElement pdf = driver.findElement(By.xpath("//*[contains(text(),'pdf')]"));
        jpg.click();
        pdf.click();
    }

}
