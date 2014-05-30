package com.revimedia.testing.configuration.proxy;

import com.revimedia.testing.configuration.Config;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dstoianov on 5/7/2014, 2:43 PM.
 */
public class BrowserMobProxyLocal2 {//implements IBrowserMobProxy {

    private static ProxyServer server;
    private static int port = Config.SELENIUM_PROXY_PORT;
    private static String proxyIp = Config.SELENIUM_PROXY_IP;
    private static Proxy proxy;


    public static void startProxy() throws Exception {
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.newHar("Revi Media Testing");
    }


    public static void stopProxy() throws Exception {
        server.stop();
    }


    public static void cleanProxyHar() {
        server.cleanup();
        server.newHar("Revi Media Testing");
    }


    public static Proxy getProxy() throws UnknownHostException {
        proxy = server.seleniumProxy();
        proxy.setHttpProxy(proxyIp + ":" + server.getPort());
        //proxy.setSslProxy(proxyIp+ ":" + server.getPort());
        //proxy.setSocksProxy("localhost:8073");
        //server.newHar("ReviMedia testing");
        return proxy;
    }


    public static Har getHar() {
        server.waitForNetworkTrafficToStop(5000, 30000);
        return server.getHar();
    }

    public static HarEntry getSubmitHarEntry() {
        HarEntry entry = catchHarEntryByTextInURL("submit");
        return entry;
    }

    public static List<HarEntry> getPolkData() {
        return collectHarEntryByTextInURL("polk?");
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
        System.out.println("Error, has no any " + url + " in log!!!");
        throw new Error("There is no any submits in logs!!!");
    }

    public static List<HarEntry> collectHarEntryByTextInURL(String url) {
        List<HarEntry> entryList = new ArrayList<HarEntry>();
        for (HarEntry entry : getHar().getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            if (request.getUrl().contains(url)) {
                System.out.println("Has catched string in url " + url);
                System.out.println("URL:  " + request.getUrl());
                entryList.add(entry);
            }
        }
        return entryList;
    }

}
