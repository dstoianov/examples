package com.revimedia.tests.cds.DraftTests;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 11.05.14.
 */
public class ProxyGridTest {
    WebDriver driver;
    static ProxyServer server;
    int port = 8085;

    @AfterMethod
    public void tearDown() throws Exception {
        server.stop();
        driver.quit();
    }

    @BeforeMethod
    public void setUp() throws Exception {

        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.newHar("Revi Media Testing");

        String proxyAddress = "192.168.0.103";
        String hubAddress = "192.168.0.103";

        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy(proxyAddress + ":" + port);
        proxy.setSslProxy(proxyAddress + ":" + port);

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        driver = new RemoteWebDriver(new URL("http://" + hubAddress + ":4444/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    @Test
    public void testName() throws Exception {
        driver.get("http://development.stagingrevi.com/auto/mfs");
        driver.findElement(By.xpath(".//*[@id='bq-form-here']/div/div[2]/div/div/div[1]/label/input")).sendKeys("99999");

        Har har = server.getHar();
    }

    @Test
    public void testCheckPort() throws Exception {

        List<Integer> portsList = new ArrayList<Integer>();
        portsList.add(getPort());
        portsList.add(getPort());
        portsList.add(getPort());

        int newPort = getPort();

        if (portsList.indexOf(newPort) == -1) {
            portsList.add(newPort);
        }


    }

    private int getPort() {
        Random rand = new Random();
        int max = 8085;
        int min = 8090;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }


}
