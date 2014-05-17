package BrowserMob;

import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Funker on 25.04.14.
 */
public class BrowserMobTest {


    @Test
    public void testName() throws Exception {
        // Start the BrowserMob proxy
        ProxyServer server = new ProxyServer(9978);
        server.start();

//        server.addResponseInterceptor(new HttpResponseInterceptor() {
//
//            @Override
//            public void process(HttpResponse response, HttpContext context)
//                    throws HttpException, IOException {
//                System.out.println(response.getStatusLine());
//            }
//        });

        // Get selenium proxy
        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:9978");

        // Configure desired capability for using proxy server with WebDriver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        // Set up driver
        WebDriver driver = new FirefoxDriver(capabilities);

        driver.get("http://stackoverflow.com/questions/6509628/webdriver-get-http-response-code");

        // Close the browser
        driver.quit();

    }
}
