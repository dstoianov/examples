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
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PerfTest {

    String strFilePath = "./src/test/resources/har.txt";
    String strFilePath2 = "./src/test/resources/har2.txt";
    ProxyServer server;
    WebDriver driver;

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
        int port = PortProber.findFreePort();
        server = new ProxyServer(port);
        server.start();
        //captures the moouse movements and navigations

        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        //server.set
        InetAddress byName = InetAddress.getByName("172.31.29.21");
        server.setLocalHost(byName);

        // get the Selenium proxy object


        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        //String proxyStr = String.format("%s:%d", getLocalHost().getCanonicalHostName(), server.getPort());
        String proxyStr = String.format("%s:%d", server.getLocalHost(), server.getPort());
        proxy.setHttpProxy(proxyStr);
        proxy.setSslProxy(proxyStr);

        String PROXY = "172.31.29.21:" + port;

        // Proxy proxy = new Proxy();
        //proxy.setHttpProxy(PROXY); //.setFtpProxy(PROXY).setSslProxy(PROXY);


        //Proxy proxy = server.seleniumProxy();

        //proxy.setHttpProxy("127.0.0.1:" + port);
        //proxy.setHttpProxy("172.31.29.21:" + port);
        //proxy.setSslProxy("172.31.29.21:" + port);
        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
//------CHROME------
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--proxy-server=http://" + PROXY);
        // capabilities.setCapability(ChromeOptions.CAPABILITY, options);

//----FIREFOX---------------
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", "172.31.29.21");
        profile.setPreference("network.proxy.http_port", port);
        // capabilities.setCapability(FirefoxDriver.PROFILE, profile);

//---------IE-----------------
        capabilities.setCapability("ie.usePerProcessProxy", true);
        capabilities.setCapability("ie.setProxyByServer", true);

//        capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());

        // start the browser up
        capabilities = DesiredCapabilities.internetExplorer();
        //capabilities.setCapability(CapabilityType.PROXY, proxy);
        capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());

        driver = new RemoteWebDriver(new URL("http://172.31.29.21:4444/wd/hub"), capabilities);
//        WebDriver driver = new ChromeDriver(capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.manage().window().maximize();
//        InetAddress byName = InetAddress.getByName("127.0.0.1");


        // create a new HAR with the label "apple.com"
        server.newHar("apple.com");
        //server.whitelistRequests();

        driver.get("http://development.stagingrevi.com/auto/mfs/");

        //server.getHar().getLog().getEntries().clear();

        //server.cleanup();
        //server.clearDNSCache();
        driver.findElement(By.xpath("//button")).click();

        Thread.sleep(3000);
        InetAddress localHost = server.getLocalHost();

        Har har = server.getHar();
        //FileOutputStream fos = new FileOutputStream(strFilePath2);
        //har.writeTo(fos);

        int size1 = har.getLog().getEntries().size();
        System.out.println("Size 1: " + size1);

        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println("------------------request----------response----------------");
            System.out.println(request.getCookies() + " : " + response.getContent().getText());
            System.out.println("---");
            System.out.println(request.getPostData() + " : " + response.getCookies());
            System.out.println("---");
            System.out.println(request.getQueryString() + " : " + response.getHeaders());
            System.out.println("------------------request----------response----------------");
        }
        System.out.println("------------------toString--------------------------");
        System.out.println(har.getLog().toString());
        System.out.println("------------------toString--------------------------");

        int size2 = server.getHar().getLog().getEntries().size();
        System.out.println("Size 2: " + size2);

        //server.getHar().writeTo(new File("./src/test/resources/har.json"));
        server.stop();
        driver.quit();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
            server.stop();
        }
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


        server.blacklistRequests("http://.*\\.fbcdn.net/.*", 404);
        server.blacklistRequests("http://ecx.images-amazon.com/.*", 404);
        server.blacklistRequests("http://z-ecx.images-amazon.com/images/G/01/browser-scripts/.*", 404);


        server.whitelistRequests(whiteList, 200);

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


    @Test
    public void testName() throws Exception {
        // start the proxy
        int port = PortProber.findFreePort();
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.setLocalHost(InetAddress.getByName("172.31.29.21"));
        server.newHar("ie_proxy_test");

        // get the Selenium proxy object

//        String base_url = "http://172.31.29.21:8084";
        Proxy proxy = new Proxy();
        String PROXY = "172.31.29.21:" + port;
        proxy.setHttpProxy(PROXY).setSslProxy(PROXY);

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setVersion("10");
        capabilities.setPlatform(Platform.ANY);
//        capabilities.setCapability("ie.usePerProcessProxy", true);
        capabilities.setCapability(CapabilityType.PROXY, proxy);

//        capabilities.setCapability(CapabilityType.PROXY, server.seleniumProxy());
        WebDriver driver = new RemoteWebDriver(new URL("http://172.31.29.21:4444/wd/hub"), capabilities);
        // RemoteWebDriver driver = new RemoteWebDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://development.stagingrevi.com/auto/mfs/");
        driver.findElement(By.xpath("//button")).click();

        Har har = server.getHar();
        int size1 = har.getLog().getEntries().size();
        System.out.println("Size 1: " + size1);


        driver.quit();
        server.stop();

    }
}
