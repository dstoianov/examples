package modal.windows;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: stoianod
 * Date: 11/6/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Base {

    //  public static Logger logger = Logger.getLogger(Base.class);

    public WebDriver driver;


    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver.manage().window().maximize();
        //logger.info("Start WebDriver");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getBrowserName();
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
        // logger.info("Stop WebDriver");
    }


    public void writeFile(File fleName, List<String> li) {
        try {
            FileUtils.writeLines(fleName, li);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public List<String> readFile(File fileName) {
        try {
            File file = fileName;
            List<String> lines = FileUtils.readLines(file);
            Collections.sort(lines);
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }


    public String getBrowserName() {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getVersion();
        System.out.println("Browser Name is: " + browserName + " and version is: " + browserVersion);
        return browserName;
    }
}
