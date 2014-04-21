package tipsandtricks.DRAFT_ScreenshotOnFail;

/**
 * Created by Funker on 21.04.14.
 */

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScreenshotUsingEventFiringWebDriver {

    private EventFiringWebDriver efd = null;

    @Test
    public void foo() throws WebDriverException, IOException {
        efd.get("http://www.google.com");
        // Now lets trigger an exception by looking for a Non Existing element.
        efd.findElement(By.name("foo")).click();
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        efd = new EventFiringWebDriver(rwd);
        efd.register(new MyEventListener());
    }

    @AfterClass
    public void afterClass() {
        efd.quit();
    }

    public static class MyEventListener extends AbstractWebDriverEventListener {


        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println("Exception was triggered");
            super.onException(throwable, driver);
            WebDriver wd = new Augmenter().augment(driver);
            File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(f, new File("src/test/resources/krishnan.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
