package BrowserMob;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Funker on 25.04.14.
 */
public class BrowserMobTest {

    private static List<Integer> portsList = Collections.synchronizedList(new ArrayList<Integer>());

    private static ProxyServer server;

    private Integer i1;
    private Integer i2;

    @Test
    public void testName() throws Exception {
        // Start the BrowserMob proxy
        server = new ProxyServer(9978);
        server.start();


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

    @Test
    public void testPort() throws Exception {

        i1 = getProxyPort();
        i2 = getProxyPort();
        //getProxyPort();

        dismissPort(i1);
        dismissPort(i2);
        getProxyPort();

    }

    private Integer getProxyPort() {
        int newPort;
        int i = 0;
        synchronized (portsList) {
            do {
                newPort = generateNewPort();
                i++;
            } while (!(portsList.indexOf(newPort) == -1 || i > 5));
            if (i >= 5) {
                throw new Error("The is no any free port for Proxy server");
            }
            portsList.add(newPort);
        }
        return newPort;
    }

    public static void dismissPort(Integer port) {
        //int port = server.getPort();
        synchronized (portsList) {
            boolean hasPortInList = portsList.indexOf(port) == -1;
            if (!hasPortInList) {
                int i = portsList.indexOf(port);
                portsList.remove(i);
            }
        }
    }

    private int generateNewPort() {
        Random rand = new Random();
        int min = 8085;
        int max = 8086;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
