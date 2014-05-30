package com.revimedia.testing.configuration.proxy;

import com.revimedia.testing.configuration.Config;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.net.PortProber;

import java.net.UnknownHostException;

/**
 * Created by dstoianov on 4/30/2014, 7:46 PM.
 * http://rdekleijn.nl/functional-test-automation-over-a-proxy/
 * https://groups.google.com/forum/#!topic/browsermob-proxy/ORX5GZyCEt0
 */
public class BrowserMobProxyLocal implements BrowserMobProxy {

    private ProxyServer server;
    //private  int port = Config.SELENIUM_PROXY_PORT;
    private String proxyIp = Config.SELENIUM_PROXY_IP;
    private Proxy proxy;

    @Override
    public synchronized ProxyServer startProxy() throws Exception {
        int port = PortProber.findFreePort();
        //int port = ProxyPorts.getProxyPort();
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.newHar("Revi Media Testing");
        return server;
    }

    @Override
    public synchronized void stopProxy() throws Exception {
        int port = server.getPort();
        ProxyPorts.dismissPort(port);
        server.stop();
    }

    @Override
    public void cleanProxyHar() {
        server.cleanup();
        server.newHar("Revi Media Testing");
    }

    @Override
    public Proxy getProxy() throws UnknownHostException {
        proxy = server.seleniumProxy();
        proxy.setHttpProxy(proxyIp + ":" + server.getPort());
        //proxy.setSslProxy(proxyIp+ ":" + server.getPort());
        //proxy.setSocksProxy("localhost:8073");
        // server.newHar("Revi Media Testing");
        return proxy;
    }

    @Override
    public Har getHar() {
        server.waitForNetworkTrafficToStop(5000, 30000);
        return server.getHar();
    }

}
