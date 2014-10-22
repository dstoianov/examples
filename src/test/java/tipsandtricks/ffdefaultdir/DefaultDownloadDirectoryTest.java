package tipsandtricks.ffdefaultdir;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Funker on 20.10.2014.
 */
public class DefaultDownloadDirectoryTest {

    protected WebDriver driver;
    private String DOWNLOAD_DIR = "C:\\tmp\\selenium";

    //    @BeforeClass
    public void setUp() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();

        creteDirIfNotExist(DOWNLOAD_DIR); // optional
//        open new tab and print about:config check that all settings are correct
        profile.setPreference("browser.download.dir", DOWNLOAD_DIR);
        profile.setPreference("browser.download.lastDir", DOWNLOAD_DIR);
        profile.setPreference("browser.download.folderList", 2); //the parameter that tells Firefox to use the default download dir all the time
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);

//        Set to false so popup not displayed when download finished.
//        profile.setPreference("browser.download.manager.closeWhenDone", true);
//        profile.setPreference("browser.download.manager.showAlertOnComplete",false);
//        profile.setPreference("browser.download.manager.showWhenStartinge",false);
//        profile.setPreference("browser.download.panel.shown",false);

        StringBuffer mimeTypes = new StringBuffer();
        mimeTypes.append("application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream"); //zip files
        mimeTypes.append(", ");
        mimeTypes.append("application/vnd.ms-excel, application/msexcel, application/x-msexcel, application/x-ms-excel, application/x-excel, application/x-dos_ms_excel, application/xls, application/x-xls, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //excel files
        mimeTypes.append(", ");
        mimeTypes.append("text/html, text/plain, image/jpeg, text/xml, text/csv, text/log"); //others files

//        profile.setPreference("browser.helperApps.neverAsk.openFile", mimeTypes.toString());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", mimeTypes.toString());

//        Set this to true to disable the pdf opening
        profile.setPreference("pdfjs.disabled", true);

        driver = new FirefoxDriver(profile);
        driver.manage().window().maximize();
    }

    @Test
    public void testName() throws Exception {
        driver.get("http://www.computerhope.com/issues/ch001113.htm");
        WebElement element = driver.findElement(By.xpath("//img[@alt= 'PDF']"));
        element.click();
    }


    @Test
    public void testZip() throws Exception {
        driver.get("http://getbootstrap.com/getting-started/");
        WebElement element = driver.findElement(By.xpath("//*[text()='Download Bootstrap']"));
        element.click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    private static void creteDirIfNotExist(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            System.out.println("Dir is already exist in the path: " + path);
        } else {
            dir.mkdirs();
            System.out.println("Dir created: " + path);
        }
    }


    public WebDriver createChrome() {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        //http://peter.sh/experiments/chromium-command-line-switches/
        ///https://sites.google.com/a/chromium.org/chromedriver/capabilities

        //http://stackoverflow.com/questions/25682766/how-to-handle-downloading-popup-alert-while-downloading-a-file-in-chrome
        //http://stackoverflow.com/questions/23530399/chrome-web-driver-download-files

        return new ChromeDriver(capabilities);
    }


    @BeforeClass
    public void getChrome2() {

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", DOWNLOAD_DIR);

        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();

        options.setExperimentalOption("prefs", chromePrefs);
//        options.addArguments("--test-type");
//        options.addArguments("start-maximized");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
    }
}
