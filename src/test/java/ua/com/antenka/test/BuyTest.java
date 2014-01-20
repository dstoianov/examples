package ua.com.antenka.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        driver.findElement(By.id("last_nameClient")).sendKeys("wwrwr");
        driver.findElement(By.id("nameClient")).sendKeys("wwrwr");
        driver.findElement(By.id("patronymic")).sendKeys("wwrwr");

        WebElement phone = driver.findElement(By.id("telClient"));
        phone.clear();
        phone.sendKeys("+38(303)734-63-74");



        Select mailService = new Select(driver.findElement(By.id("courier")));
        List<WebElement> options = mailService.getOptions();
        mailService.selectByIndex(getRnd(options.size()));

        Select paymentMethods = new Select(driver.findElement(By.id("money")));
        //List<WebElement> options = mailService.getOptions();
        paymentMethods.selectByIndex(getRnd(2));



        Select city = new Select(driver.findElement(By.id("sity")));
        List<WebElement> cityCount = city.getOptions();
        city.selectByIndex(getRnd(cityCount.size()));


        //print("All count of options is %s ", options.size());



    }

    private boolean isElementExist(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebElement findElement(final By by) {
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
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
