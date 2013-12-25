package ua.com.rozetka.speed.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

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
    }

    @Test
    public void firstTest() {
        driver.get("http://rozetka.com.ua/");
        driver.findElement(By.name("signin")).click();
        driver.findElement(By.xpath("(//*[@class='auth-f']//input)[1]")).sendKeys("funker@land.ru");
        driver.findElement(By.xpath("(//*[@class='auth-f']//input)[2]")).sendKeys("222222");
        driver.findElement(By.xpath("//*[@class='auth-f']//button")).click();


        WebElement signOut = findElement(By.xpath("//a[@name='signout']"));

//        WebElement signOut = (new WebDriverWait(driver, 10))
//                .until(new ExpectedCondition<WebElement>() {
//                    @Override
//                    public WebElement apply(WebDriver d) {
//                        return d.findElement(By.xpath("//a[@name='signout']"));
//                    }
//                });

        Assert.assertEquals(signOut.isDisplayed(), is(true));
        signOut.click();
        Assert.assertEquals(findElement(By.name("signin")).isDisplayed(), is(true));
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        stop = System.currentTimeMillis();
        print("That took %s seconds", (double) ((stop - start) / 1000) );
        System.out.println("That took " + (double) ((stop - start) / 1000) + " seconds");
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {return d.findElement(by);
                    }
                });
    }


    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
