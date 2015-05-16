package confetqa2013;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import net.lightbody.bmp.proxy.http.BrowserMobHttpRequest;
import net.lightbody.bmp.proxy.http.RequestInterceptor;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Sample6_StrippingReferer {

    @Test
    public void strippingReferer() throws Exception {
        ProxyServer bmp = new ProxyServer(8071);
        bmp.start();

        HttpRequestInterceptor stripper = new RefererStripper();
        RequestInterceptor stripperNew = new RefererStripperNew();

        bmp.addRequestInterceptor(stripper);
        // for avoiding deprecate method
        bmp.addRequestInterceptor(stripperNew);

//        ((LegacyProxyServer)bmp).addRequestInterceptor(stripper);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());

        WebDriver driver = new FirefoxDriver(caps);

        driver.get("http://localhost/test_referer.html");
        driver.findElement(By.tagName("a")).click();

        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("No referer"));

        Thread.sleep(10000);

        driver.quit();

        bmp.stop();
    }

    public static class RefererStripper implements HttpRequestInterceptor {
        @Override
        public void process(HttpRequest request, HttpContext context) {
            request.removeHeaders("Referer");
        }
    }

    public class RefererStripperNew implements RequestInterceptor {
        @Override
        public void process(BrowserMobHttpRequest request, Har har) {
            request.getMethod().removeHeaders("Referer");
        }
    }
}
