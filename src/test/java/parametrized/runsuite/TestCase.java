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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

/**
 * User: stoianod
 * Date: 4/8/14
 */
public class TestCase {

    private WebDriver driver;


    @BeforeClass
    @org.testng.annotations.Parameters(value={"browser","version","platform"})
    public void setUp(@Optional("firefox") String browser,
                      @Optional("33")String version,
                      @Optional("WIN") String platform) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        //capability.setCapability("platform",platform);
        capability.setCapability("browserName", browser);
        //capability.setCapability("browserVersion", version);
        //capability.setCapability("project", "P1");
        //capability.setCapability("build", "1.0");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    }

    @Test
    public void testSimple() throws Exception {
        driver.get("http://www.google.com");
        //driver.get("http://www.yandex.ru");
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
        WebElement titleElement = driver.findElement(By.className("title"));
        System.out.println("Padding-bottom: " + titleElement.getCssValue("padding-bottom"));
        String titleColor = titleElement.getCssValue("color");
        System.out.println("Title color: " + titleColor);

        Color color = Color.fromString(titleColor);
        System.out.println(color.asHex());
        System.out.println(color.asRgb());
        System.out.println(color.asRgba());
    }

    @AfterClass
    public void tearDown()  {
        driver.quit();
    }

    public void getBrowserName(){
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browsername = cap.getBrowserName();
        String browserversion = cap.getVersion();
        String url = driver.getCurrentUrl();
        System.out.println("Name: " + browsername + ", ver: " + browserversion + ", URL: " + url);
    }

}
