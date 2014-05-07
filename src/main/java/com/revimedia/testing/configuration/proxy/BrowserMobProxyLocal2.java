package com.revimedia.testing.configuration.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;

/**
 * Created by dstoianov on 5/7/2014, 2:43 PM.
 */
public class BrowserMobProxyLocal2 implements IBrowserMobProxy {
    private ProxyServer server;
    private int port = 8073;
    private Proxy proxy;

    @Override
    public void startProxy() throws Exception {
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.newHar("Revi Media testing");
    }

    @Override
    public void stopProxy() throws Exception {
        server.stop();
    }

    @Override
    public void cleanProxyHar() {
        server.cleanup();
    }

    @Override
    public Proxy getProxy() throws UnknownHostException {
        proxy = server.seleniumProxy();
        proxy.setHttpProxy("localhost:8073");
        //proxy.setSslProxy("localhost:8073");
        //proxy.setSocksProxy("localhost:8073");
        //server.newHar("ReviMedia testing");
        return proxy;
    }

    @Override
    public Har getHar() {
        return server.getHar();
    }

    public HarEntry getSubmitHarEntry() {
        HarEntry entry = catchHarEntryByTextInURL("submit");

        return entry;
    }

    @Override
    public HarEntry catchHarEntryByTextInURL(String url) {
        Har har = getHar();
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
