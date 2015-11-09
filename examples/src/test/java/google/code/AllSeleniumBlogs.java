package google.code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by dstoianov on 2015-11-09.
 */
public class AllSeleniumBlogs extends BaseTest {


    @Test
    public void testName() throws Exception {
        driver.get("http://it-kosmopolit.de/Selenium/blog/selenium-blogs/selenium_blogs.php");
//        List<WebElement> elements = driver.findElements(By.cssSelector("[align='center'] a")); //for FF
        List<WebElement> elements = driver.findElements(By.cssSelector("[align='middle'] a"));  // for Chrome

        for (WebElement e : elements) {
            String href = e.getAttribute("href");
            if (href.length() > 0) {
                System.out.println(href);
            }
        }
    }
}
