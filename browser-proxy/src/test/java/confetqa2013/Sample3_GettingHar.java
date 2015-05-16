package confetqa2013;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Sample3_GettingHar {

    WebDriver driver;
    ProxyServer bmp;

    @Test
    public void gettingHar() throws Exception {
        bmp = new ProxyServer(8072);
        bmp.start();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());

        driver = new FirefoxDriver(caps);

        bmp.newHar("ya.ru");

        driver.get("http://ya.ru/");

        bmp.waitForNetworkTrafficToStop(300, 10000);
        Har har = bmp.getHar();

        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println(request.getUrl() + " : " + response.getStatus() + ", " + entry.getTime() + "ms");

            assertThat(response.getStatus(), is(200));
        }

        driver.quit();

        bmp.stop();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
            bmp.stop();
        }
    }

}
