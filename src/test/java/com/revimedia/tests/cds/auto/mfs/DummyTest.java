package com.revimedia.tests.cds.auto.mfs;


import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 02.05.14.
 */
public class DummyTest {
    public WebDriver driver;

/*        @Test
        public void basicTest() throws Exception {

//            BrowserMobProxy bmp = new BrowserMobProxy("http://localhost", 9090);
//            int port = bmp.getPort();
//            bmp.setPort(port);

            int port = 9090;
            ProxyServer server = new ProxyServer(port);
            //server.setPort(9090);
            server.start();

            // get the Selenium proxy object
            String actProxy = "localhost:" + port;
            Proxy seleniumProxy = new Proxy();
            seleniumProxy.setHttpProxy(actProxy).setFtpProxy(actProxy).setSslProxy(actProxy);

            // configure it as a desired capability
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            // start the browser up
            WebDriver driver = new FirefoxDriver(capabilities);

            // create a new HAR with the label "agame.com"
            bmp.newHar("agame.com", false, false, false);

            bmp.whitelistRequests("http:/*//*.*.spilcdn.com/.*,http:/*//*.*.agame.com/.*,http:/*//*.*.spilgames.com.*", 200);
            driver.get("http://www.agame.com/game/1001-arabian-nights.html");
            bmp.waitForNetworkTrafficToStop(20000, 45000);

            // get the HAR data
            System.out.println(bmp.getHar().getLog().getEntries().size());
            bmp.getHar().writeTo(new File("har.json"));

            driver.quit();
        }*/


    @Test
    public void testName() throws Exception {
        ProxyServer server = new ProxyServer(4675);
        server.start();


// Get the Selenium proxy object
        Proxy proxy = server.seleniumProxy();


        proxy.setHttpProxy("<grid_hub_hostname>:4675");

        proxy.setSslProxy("<grid_hub_hostname");


        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        capabilities.setCapability(CapabilityType.PROXY, proxy);


        WebDriver driver = new RemoteWebDriver(new URL("http://<grid_hub_hostname>:8080/wd/hub"), capabilities);

    }


    @Test
    public void testListToURL() throws Exception {

        List<String> autoMfs = new ArrayList<String>();
        autoMfs.add("firstname");
        autoMfs.add("lastname");


    }
}
