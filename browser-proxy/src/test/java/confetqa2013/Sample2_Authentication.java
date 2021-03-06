package confetqa2013;

import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class Sample2_Authentication {

    @Test
    public void autoBasicAuthorization() throws Exception {
        ProxyServer bmp = new ProxyServer(8071);
        bmp.start();

        bmp.autoBasicAuthorization("", "admin", "password");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());

        WebDriver driver = new FirefoxDriver(caps);

        driver.get("http://localhost/restricted");
        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("Access granted"));

        driver.quit();

        bmp.stop();
    }
}
