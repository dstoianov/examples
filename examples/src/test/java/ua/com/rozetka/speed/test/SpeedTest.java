package ua.com.rozetka.speed.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 11/16/13
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Listeners(value = {CustomListener.class, CustomReport.class})
public class SpeedTest {

    private static final Logger log = LoggerFactory.getLogger(SpeedTest.class);

    RemoteWebDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void setUp() {
        log.info("Start browser");
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
//        driver.setLogLevel(Level.INFO);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {
        driver.get("https://my.rozetka.com.ua/");
//        $(By.name("signin")).click();

        WebElement login = $(By.cssSelector("input[name='login']"));
        login.clear();
        login.sendKeys("funker@land.ru");

        WebElement pass = $(By.cssSelector("input[name='password']"));
        pass.clear();
        pass.sendKeys("222222");
        $(By.cssSelector("#signin_form button")).click();

        WebElement signOut = $(By.cssSelector(".profile-m-edit-signout"));
        wait.until(ExpectedConditions.elementToBeClickable(signOut));
        wait.until(ExpectedConditions.visibilityOf(signOut));

        assertThat(signOut.isDisplayed(), is(true));
        signOut.click();
        WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("signin")));

        assertThat(signIn.isDisplayed(), is(true));
    }

    @Test
    public void usingAssertThat() {
        assertThat("xx", is("xx"));
        assertThat("yy", is(not("xx")));
        assertThat("i like cheese", containsString("cheese"));

/*        print("os is %s ", System.getProperty("os.arch"));

        // Will say "x86" even on a 64-bit machine
        // using a 32-bit Java runtime
        SystemEnvironment env = SystemEnvironment.getSystemEnvironment();
        final String envArch = env.getOsArchitecture();

        // The os.arch property will also say "x86" on a
        // 64-bit machine using a 32-bit runtime
        final String propArch = System.getProperty("os.arch");

        System.out.println( "getOsArchitecture() says => " + envArch );
        System.out.println( "getProperty() says => " + propArch );*/


    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        log.info("Stop browser");
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }

    private WebElement $(By by) {
        return driver.findElement(by);
    }


    //  print("That took %s seconds", 25);
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
