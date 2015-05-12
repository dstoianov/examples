package ua.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 22.04.14.
 */
public class WindowHandlesTest {

    Long start = null;
    WebDriver driver;
    Long stop = null;


    @Test
    public void testName() throws Exception {

        driver.get("http://rvmd-3130.stagingrevi.com/auto/p/");
        WebElement zipCode = driver.findElement(By.xpath(".//*[@id='bq-form-here']/div/div[4]/div[1]/div/div/div[1]/label/input"));
        zipCode.sendKeys("20002");

        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath(".//button")).click();

        Set<String> handles = driver.getWindowHandles();
        handles.remove(parentWindow);
        String next = handles.iterator().next();
        driver.close();
        driver.switchTo().window(next);
        //String next2 = handles.iterator().next();
        //driver.close();
        //handles.remove(0);
        //((String) handles).last();
        //handles.remove( ((TreeSet) handles).last() );
        // for (String handle : handles) {

        //    driver.switchTo().window(handle);
        //driver.switchTo().window(driver.WindowHandles.Last());

        // }
        driver.findElement(By.xpath(".//button")).click();

    }


    @BeforeClass
    public void setUp() {
        start = System.currentTimeMillis();
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
