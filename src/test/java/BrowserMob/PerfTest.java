package BrowserMob;

/**
 * Created by Funker on 26.04.14.
 */

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

public class PerfTest {

    String strFilePath = "./src/test/resources/har.txt";
    String strFilePath2 = "./src/test/resources/har2.txt";

    @Test
    public void testName1() throws Exception {


        // start the proxy
        ProxyServer server = new ProxyServer(8071);
        server.start();
        //captures the moouse movements and navigations

        //server.setCaptureHeaders(true);
        //server.setCaptureContent(true);


        // get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:8071");
        proxy.setSocksProxy("localhost:8071");
        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // create a new HAR with the label "apple.com"
        server.newHar("assertselenium.com");

        // open yahoo.com
        driver.get("http://assertselenium.com");

        driver.get("http://assertselenium.com/2012/10/30/transformation-from-manual-tester-to-a-selenium-webdriver-automation-specialist/");

        // get the HAR data
        Har har = server.getHar();
        FileOutputStream fos = new FileOutputStream(strFilePath);
        har.writeTo(fos);
        server.stop();
        driver.quit();

    }


    @Test
    public void testName2() throws Exception {

        // start the proxy
        ProxyServer server = new ProxyServer(8071);
        server.start();
        //captures the moouse movements and navigations

        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        //server.set

        // get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:8071");
        proxy.setSocksProxy("localhost:8071");
        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // create a new HAR with the label "apple.com"
        server.newHar("apple.com");
        //server.whitelistRequests();

        driver.get("http://rvmd-denis.stagingrevi.com/auto/mfs/");

        server.getHar().getLog().getEntries().clear();

        //server.cleanup();
        //server.clearDNSCache();
        driver.findElement(By.xpath("//button")).click();

        Thread.sleep(3000);

        Har har = server.getHar();
        FileOutputStream fos = new FileOutputStream(strFilePath2);
        har.writeTo(fos);

        // Header[] headers = request.getAllHeaders();


        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            //System.out.println(request.getUrl() + " : " + response.getStatus() + ", " + entry.getTime() + "ms");

            System.out.println("------------------request----------response----------------");
            System.out.println(request.getCookies() + " : " + response.getContent().getText());
            System.out.println("---");
            System.out.println(request.getPostData() + " : " + response.getCookies());
            System.out.println("---");
            System.out.println(request.getQueryString() + " : " + response.getHeaders());


            System.out.println("------------------request----------response----------------");


            // assertThat(response.getStatus(), is(200));
        }

        System.out.println("------------------toString--------------------------");
        System.out.println(har.getLog().toString());
        System.out.println("------------------toString--------------------------");


        System.out.println(server.getHar().getLog().getEntries().size());
        server.getHar().writeTo(new File("./src/test/resources/har.json"));

        driver.quit();
        server.stop();
    }


    @Test
    public void basicTest() throws Exception {
        ProxyServer server = new ProxyServer(8073);
        server.start();
        // get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:8073");
        proxy.setSslProxy("localhost:8073");
        //proxy.setSocksProxy("localhost:8073");

        server.newHar("my_super_test");

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // start the browser
        //WebDriver driver = new FirefoxDriver(capabilities);
        WebDriver driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // create a new HAR with the label "agame.com"
        //server.newHar("my_super_test");

        //String[] whiteList = "http://www.yandex.com,http://*.google.com/.*".split(",");
        //String[] whiteList = "http://*.yandex.com/".split(",");
        String[] whiteList = ("http(s)?://.*\\.yandex\\.ru/(?!TealeafTarget).*;" +
                "http(s)?://.*\\.google\\.com\\.ua/(?!TealeafTarget).*;" +
                "http(s)?://.*\\.stubhub\\.co.uk/(?!TealeafTarget).*").split(";");

        //server.whitelistRequests(whiteList, 200);

        driver.get("http://www.yandex.ru/");
        Thread.sleep(1000);
        driver.get("http://google.com.ua/");
        Thread.sleep(1000);

        driver.get("http://www.stubhub.co.uk/");
        Thread.sleep(1000);
        //server.waitForNetworkTrafficToStop(20000, 45000);

        // get the HAR data
        System.out.println(server.getHar().getLog().getEntries().size());
        server.getHar().writeTo(new File("./src/test/resources/har.json2.har"));

        driver.quit();
    }

}
