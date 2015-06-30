package google.code;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: stoianod
 * Date: 11/6/13
 */
public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "./examples/src/test/resources/drivers/chromedriver.exe");
        System.setProperty("phantomjs.binary.path", "./examples/src/test/resources/drivers/phantomjs.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


}
