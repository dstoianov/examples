package com.revimedia.testing.configuration.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Proxy;

/**
 * Created by Funker on 07.05.14.
 */
public interface IBrowserMobProxy {
    public void startProxy();

    public void stopProxy();

    public void cleanProxyHar();

    public Proxy getProxy();

    public Har getHar();

    public HarEntry catchRequestByURL();
}