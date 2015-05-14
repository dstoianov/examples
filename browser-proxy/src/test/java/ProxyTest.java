import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

/**
 * Created by dstoianov on 2015-05-13.
 */
public class ProxyTest {


    @Test
    public void testName() throws Exception {

/*
        // start the proxy
        BrowserMobProxy server = new ProxyServer();
        server.start(0);

        // get the Selenium proxy object
        Proxy proxy = ClientUtil.createSeleniumProxy(server);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);

        // create a new HAR with the label "yahoo.com"
        server.newHar("yahoo.com");

        // open yahoo.com
        driver.get("http://yahoo.com");

        // get the HAR data
        Har har = server.getHar();
*/

    }

    @Test
    public void testName1() throws Exception {
        // start the proxy
        ProxyServer server = new ProxyServer(8081);
        server.start();

// get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();

// configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

// start the browser up
        WebDriver driver = new FirefoxDriver(capabilities);

// create a new HAR with the label "yahoo.com"
        server.newHar("yahoo.com");

// open yahoo.com
        driver.get("http://yahoo.com");

// get the HAR data
        Har har = server.getHar();
        driver.quit();
        server.stop();

    }
}
