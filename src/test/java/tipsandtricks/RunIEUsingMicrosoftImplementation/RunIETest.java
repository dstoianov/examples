package tipsandtricks.RunIEUsingMicrosoftImplementation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverEngine;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Funker on 03.01.2015.
 */
public class RunIETest {

    public InternetExplorerDriverService service;
    public WebDriver driver;

    @Test
    public void testName() throws Exception {
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!\n");
        element.submit();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        service = new InternetExplorerDriverService.Builder()
//                .usingDriverExecutable(new File("C:\\Selenium\\IEDriverServer.exe"))
                .withEngineImplementation(InternetExplorerDriverEngine.LEGACY)
                .withLogFile(new File("IEDriver.log"))
                .withLogLevel(InternetExplorerDriverLogLevel.DEBUG)
                .usingAnyFreePort()
                .build();

        service.start();

        driver = new InternetExplorerDriver(service);
//        driver = new FirefoxDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("Quiting the browser application");
        driver.quit();
        if (service != null) {
            System.out.println("Stopping the InternetExplorerDriverService");
            service.stop();
        }
    }
}
