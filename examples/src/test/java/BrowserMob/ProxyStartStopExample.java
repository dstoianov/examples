package BrowserMob;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Denis Stoianov on 24/06/2014, 5:33 PM
 * E-mail: denis@revimedia.com
 */
public class ProxyStartStopExample {
    ProxyServer server;
    WebDriver driver;

    @Test
    public void setUp() throws Exception {
        int port = PortProber.findFreePort();
        server = new ProxyServer(port);
        server.start();

        Map<String, String> options = new HashMap<String, String>();
        options.put("httpProxy", "172.31.29.21:" + port); //   would prefer
        server.setOptions(options);

        //server.setLocalHost(InetAddress.getLocalHost());

        InetAddress localHost = InetAddress.getLocalHost();
        InetAddress[] allByName = InetAddress.getAllByName("172.31.29.21");

        InetAddress byName = InetAddress.getByName("172.31.29.21");

        //  server.setLocalHost(byName);

        Proxy proxy = server.seleniumProxy();
        Proxy proxy2 = server.seleniumProxy();

// Traffic will be routed to a geoEdge proxy
        String PROXY = "172.31.29.21:" + port;
        proxy.setHttpProxy(PROXY).setSslProxy(PROXY);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        driver = new FirefoxDriver(capabilities);

        server.setLocalHost(byName);

        server.setCaptureContent(true);
        server.setCaptureHeaders(true);
        server.newHar("test");

        driver.get("http://google.com");
        Thread.sleep(3000);
        Har har = server.getHar();

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        server.stop();
    }


}
