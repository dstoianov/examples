package com.revimedia.testing.configuration.proxy;

import com.revimedia.testing.configuration.config.Config;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.net.PortProber;

import java.net.UnknownHostException;

/**
 * Created by dstoianov on 4/30/2014, 7:46 PM.
 * http://rdekleijn.nl/functional-test-automation-over-a-proxy/
 * https://groups.google.com/forum/#!topic/browsermob-proxy/ORX5GZyCEt0
 */
public class BrowserMobProxyLocal implements BrowserMobProxy {

    protected final Logger log = Logger.getLogger(this.getClass());

    private ProxyServer server;
    //private  int port = Config.SELENIUM_PROXY_PORT;
    private String proxyIp = Config.SELENIUM_PROXY_IP;
    private Proxy proxy;

    @Override
    public ProxyServer startProxy() throws Exception {
        log.info("Starting Proxy Server....");
        int port = PortProber.findFreePort();
        //int port = ProxyPorts.getProxyPort();
        server = new ProxyServer(port);
        server.start();
        server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.newHar("Revi Media Testing");
        log.info("Proxy Server was started on port: '" + port + "'");
        return server;
    }

    @Override
    public void stopProxy() throws Exception {
        int port = server.getPort();
        ProxyPorts.dismissPort(port);
        server.stop();
        log.info("Proxy Server is shut down");
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
        server.waitForNetworkTrafficToStop(5000, 45000);
        return server.getHar();
    }

}
