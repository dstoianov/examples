package tipsandtricks.ffdefaultdir;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Funker on 20.10.2014.
 */
public class DefaultDownloadDirectoryTest {

    protected WebDriver driver;
    private String FF_DOWNLOAD_DIR = "C:\\tmp\\selenium";

    @BeforeClass
    public void setUp() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();

        creteDirIfNotExist(FF_DOWNLOAD_DIR); // optional
        profile.setPreference("browser.download.dir", FF_DOWNLOAD_DIR);
        profile.setPreference("browser.download.lastDir", FF_DOWNLOAD_DIR);
        profile.setPreference("browser.download.folderList", 2); //the parameter that tells Firefox to use the default download dir all the time
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/xml, text/csv, text/plain, text/log, application/zlib, application/x-gzip, application/x-compressed, application/x-gtar, multipart/x-gzip, application/tgz, application/gnutar, application/x-tar, application/gzip");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, text/pdf, text/csv, application/vnd.ms-excel, text/xml, application/zip"); // pdf, csv, xml, xls
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip"); // zip
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-zip-compressed"); // zip
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-zip"); // zip
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream"); // zip
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword"); // pdf, csv, xml, xls


        //open in browser about:config -> search by 'browser.download'
/*
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.closeWhenDone", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
*/

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

        //capabilities.setCapability("chrome.switches", Arrays.asList("--disable-translate"));
        //capabilities.setCapability("chrome.switches", Arrays.asList("--enable-translate"));
        return new ChromeDriver(capabilities);
    }


}
