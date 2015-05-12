package com.revimedia.testing.configuration.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;

/**
 * Created by dstoianov on 07.05.14.
 */
public interface IBrowserMobProxy {
    ProxyServer startProxy() throws Exception;

    void stopProxy() throws Exception;

    void cleanProxyHar();

    Proxy getProxy() throws UnknownHostException;

    Har getHar();

    void printHar(Har har);
}