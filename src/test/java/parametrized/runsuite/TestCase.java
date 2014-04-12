package parametrized.runsuite;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class TestCase extends BaseTest{

    @Test
    public void testPositiveSubmit() throws Exception {
        driver.get("http://www.google.com");
        getBrowserName();
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Browser Stack");
        element.submit();
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description =  "Fake Test")
    public void testRequiredFields()throws Exception{
        Assert.assertTrue(true);
    }

}
