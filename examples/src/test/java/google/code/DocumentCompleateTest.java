package google.code;

import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by dstoianov on 2015-11-03.
 */
public class DocumentCompleateTest extends BaseTest {

    @Test
    public void testName() throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs/");
        waitForPageComplete();
//        isAjaxComplete();
        driver.findElement(By.cssSelector(".bq-name-ZipCode input")).sendKeys("20202");
//        driver.findElement(By.cssSelector("button")).click();

    }


    public void waitForAjaxComplete() {
        System.out.println("Waiting for ajax complete...");
        Stopwatch timer = Stopwatch.createStarted();

        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isAjaxComplete();
            }
        });
        System.out.println("All ajax calls are complete, it took: " + timer.stop());
    }


    public void waitForPageComplete() {
        System.out.println("Waiting for page complete...");
        Stopwatch timer = Stopwatch.createStarted();

        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isPageLoaded();
            }
        });
        System.out.println("Page complete, it took: " + timer.stop());
    }

    public boolean isAjaxComplete() {
        //return (window.jQuery != null) && (jQuery.active === 0);
//        Boolean result = (Boolean) js.executeScript("return $.active == 0");
        Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && ($.active === 0)");
//        System.out.println(String.format("Ajax return $.active == 0  is: '%s'", result));
        return result;
    }

    public boolean isPageLoaded() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

}
