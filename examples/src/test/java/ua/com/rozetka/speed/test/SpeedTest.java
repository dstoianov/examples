package ua.com.rozetka.speed.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
public class SpeedTest {

    Long start = null;
    WebDriver driver;
    Long stop = null;


    @BeforeClass
    public void setUp() {
        start = System.currentTimeMillis();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void firstTest() {
        driver.get("http://rozetka.com.ua/");
        driver.findElement(By.name("signin")).click();

        WebElement login = driver.findElement(By.xpath("(//*[@class='auth-f']//input)[1]"));
        login.clear();
        login.sendKeys("funker@land.ru");

        WebElement pass = driver.findElement(By.xpath("(//*[@class='auth-f']//input)[2]"));
        pass.clear();
        pass.sendKeys("222222");
        driver.findElement(By.xpath("//*[@class='auth-f']//button")).click();

        WebElement signOut = findElement(By.xpath("//a[@name='signout']"));

        assertThat(signOut.isDisplayed(), is(true));
        signOut.click();
        assertThat(findElement(By.name("signin")).isDisplayed(), is(true));
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
        stop = System.currentTimeMillis();
        print("That took %s seconds", (double) ((stop - start) / 1000));
        //System.out.println("That took " + (double) ((stop - start) / 1000) + " seconds");
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }


    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
