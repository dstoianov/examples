package ua.com.antenka.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 1/20/14.
 */
public class BuyTest {

    Long start = null;
    WebDriver driver;
    Long stop = null;

    @Test
    public void firstTest() {
        do {
            driver.get("http://antenka.com.ua/");
            List<WebElement> goods = driver.findElements(By.xpath(".//div[@class='topItems']"));
            goods.get(getRnd(goods.size())).click();

            //driver.findElement(By.xpath(".//input[@class='addtocart_button'][1]")).click();

        } while (!isElementExist("//input[12]"));

        driver.findElement(By.xpath("//input[12]")).click();

        driver.findElement(By.xpath(".//*[@id='enterCart']")).click();
        driver.findElement(By.xpath(".//*[@id='applyCart']")).click();
        ////*[@id='enterCart']
    }

    private boolean isElementExist(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private int getRnd(int i) {
        Random rand = new Random();
        return rand.nextInt(i);
    }

    @BeforeClass
    public void setUp() {
        start = System.currentTimeMillis();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        stop = System.currentTimeMillis();
        print("That took %s seconds", (double) ((stop - start) / 1000));
        //System.out.println("That took " + (double) ((stop - start) / 1000) + " seconds");
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
