package com.revimedia.testing.configuration.proxy;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.Proxy;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Funker on 07.05.14.
 */
public interface BrowserMobProxy {
    public void startProxy() throws Exception;

    public void stopProxy() throws Exception;

    public void cleanProxyHar();

    public Proxy getProxy() throws UnknownHostException;

    public Har getHar();

    public HarEntry catchHarEntryByTextInURL(String url);

    public List<HarEntry> collectHarEntryByTextInURL(String url);

}