package com.revimedia.testing.configuration.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.util.Collections;

/**
 * Created by dstoianov on 4/30/2014, 7:46 PM.
 * http://rdekleijn.nl/functional-test-automation-over-a-proxy/
 * https://groups.google.com/forum/#!topic/browsermob-proxy/ORX5GZyCEt0
 */
public class BrowserMobProxyLocal {
    private static ProxyServer server;

    public static Proxy startBrowserMob() throws Exception {
        // start the proxy
        int port = 8073;
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        // get the Selenium proxy object
//        Proxy proxy = server.seleniumProxy();
//        proxy.setHttpProxy("localhost:" + port);

        Proxy proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:8073");
        //proxy.setSslProxy("localhost:8073");
        //proxy.setSocksProxy("localhost:8073");

        server.newHar("ReviMedia testing");
        return proxy;
    }

    public static void stopBrowserMob() throws Exception {
        server.stop();
    }

    public static Har getHar() {
        return server.getHar();
    }

    public void cleanServerHar() {
        server.cleanup();
    }

    public static HarEntry catchHarEntryByTextInURL(String url) {
        Har har = getHar();
        Collections.reverse(har.getLog().getEntries());
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            if (request.getUrl().contains(url)) {
                System.out.println("Has catched string in url " + url);
                return entry;
            }
        }
        return null;
    }
}
