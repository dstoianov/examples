package BrowserMob.confetqa2013;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class Sample1_StartWithProxy {

    @Test
    public void startWithProxy() throws Exception {
        ProxyServer bmp = new ProxyServer(8071);
        bmp.start();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());

        WebDriver driver = new FirefoxDriver(caps);

        driver.get("http://localhost/");
        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("WampServer"));

        driver.quit();

        bmp.stop();
    }
}
