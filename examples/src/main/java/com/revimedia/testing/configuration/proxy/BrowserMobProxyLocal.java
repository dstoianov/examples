package com.revimedia.testing.configuration.proxy;

import com.revimedia.testing.configuration.config.Config;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.net.PortProber;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dstoianov on 4/30/2014, 7:46 PM.
 */
public class BrowserMobProxyLocal implements BrowserMobProxy {

    protected final Logger log = Logger.getLogger(this.getClass());

    private ProxyServer server;
    private String proxyIp = Config.SELENIUM_PROXY_IP;

    @Override
    public ProxyServer startProxy() throws Exception {
        log.info("Starting Proxy Server....");
        int port = PortProber.findFreePort();
        //int port = ProxyPorts.getProxyPort();
        server = new ProxyServer(port);
        server.start();
//
//        server.addRequestInterceptor(new RequestInterceptor() {
//            @Override
//            public void process(BrowserMobHttpRequest request, Har har) {
//                request.getMethod().removeHeaders("User-Agent");
//                request.getMethod().addHeader("User-Agent", "Bananabot/1.0");
//            }
//        });

//        server.addRequestInterceptor( new HttpRequestInterceptor() {
//            @Override
//            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
//                log.info(" " + request.getRequestLine());
//                log.info(" " + context.getAttribute(""));
//                        request.removeHeaders("User-Agent");
//                request.addHeader("User-Agent", "BananaBot/1.0");
//            }
//        });
//
//        server.addResponseInterceptor(new HttpResponseInterceptor() {
//            @Override
//            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
//                log.info(" " + response.getStatusLine());
//            }
//        });

        // server.setCaptureHeaders(true);
        server.setCaptureContent(true);
        server.setLocalHost(InetAddress.getByName(proxyIp));
        server.setRetryCount(2);

        server.blacklistRequests("https://h.online-metrix.net/.*", 404);
        server.blacklistRequests("http://cdn.nextinsure.com/images/.*", 404);
        server.blacklistRequests("http://imageserver.quinstreet.com/.*", 404);
        server.blacklistRequests("https://www.shmktpl.com/retrieve_listings.asp", 404);
        server.blacklistRequests("https://ad.doubleclick.net/ad/.*", 404);
        server.blacklistRequests("https://cdn.bwserver.net/.*", 404);
        server.blacklistRequests("http://apipost2.revimedia.com/.*", 404);

        server.newHar("Revi_Media_Testing");
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
        log.info("New har is created");
    }

    @Override
    public Proxy getProxy() throws UnknownHostException {
        Proxy proxy = new Proxy();
        String PROXY = proxyIp + ":" + server.getPort();
        proxy.setProxyType(Proxy.ProxyType.MANUAL);
        proxy.setHttpProxy(PROXY).setSslProxy(PROXY);
        return proxy;
//        return server.seleniumProxy();
    }

    @Override
    public Har getHar() {
        log.info("Trying to get HAR data, wait For Network Traffic To Stop...");
        Har har;
        try {
            server.waitForNetworkTrafficToStop(200, 15000);
            har = server.getHar();
            printHar(har);
            return har;
        } catch (RuntimeException e) {
            System.err.println("ERROR: Timed out after 15 sec while waiting for network traffic to stop");
        }
        har = server.getHar();
        printHar(har);
        return har;
    }

    @Override
    public void printHar(Har har) {
        System.out.println("=====================Info for debug=======================");
        int i = 1;
        for (HarEntry entry : har.getLog().getEntries()) {
            String url = entry.getRequest().getUrl();
            if (url.contains("create.leadid") || url.contains("/media/")) {
                continue;
            }
            Date startedDateTime = entry.getStartedDateTime();
            String format = DateFormatUtils.format(startedDateTime, "HH:mm:ss.sss", Locale.ENGLISH);
            System.out.println(i + ") Request URL at " + format + " " + url);
            i++;
        }
        System.out.println("=====================Info for debug=======================");
    }

}
