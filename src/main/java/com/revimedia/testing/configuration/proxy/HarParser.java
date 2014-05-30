package com.revimedia.testing.configuration.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.configuration.dto.Submit;
import com.revimedia.testing.configuration.helpers.Formatter;
import com.revimedia.testing.configuration.response.Errors;
import com.revimedia.testing.configuration.response.ErrorsDeserializer;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.testing.configuration.response.ResponseDeserializer;
import net.lightbody.bmp.core.har.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 02.05.14.
 */
public class HarParser {

    private static HarEntry getSubmitHarEntry(Har har) {
        return catchHarEntryByTextInURL(har, "submit");
    }

    public static Submit getSubmit() {
        HarEntry entry = BrowserMobProxyLocal2.getSubmitHarEntry();
        HarRequest request = entry.getRequest();
        HarResponse response = entry.getResponse();
        String resRAW = response.getContent().getText();

        Submit submit = new Submit();
        System.out.println("------------------REQUEST---URL-------------------------------");
        System.out.println("URL: " + request.getUrl());

        for (HarPostDataParam list : request.getPostData().getParams()) {
            if (list.getName().equalsIgnoreCase("XMLBody")) {
                System.out.println("\n------------------REQUEST---XML-------------------------------");
                System.out.println(Formatter.prettyXMLFormat(list.getValue()));
                submit.setRequest(list.getValue());
            }
        }
        System.out.println("\n------------------RESPONSE---JSON--------------------------");
        System.out.println(Formatter.prettyJSONFormat(resRAW));
        System.out.println("-----------------------------------------------------------\n");

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
        gsonBuilder.registerTypeAdapter(Errors.class, new ErrorsDeserializer());
        final Gson gson = gsonBuilder.create();

        Response responseObj = gson.fromJson(resRAW, Response.class);
        submit.setResponse(responseObj);
        return submit;
    }

    public static Submit getSubmit(Har har) {
        HarEntry entryLocal = getSubmitHarEntry(har);
        HarRequest request = entryLocal.getRequest();
        HarResponse response = entryLocal.getResponse();
        String resRAW = response.getContent().getText();

        Submit submit = new Submit();
        System.out.println("------------------REQUEST---URL-------------------------------");
        System.out.println("URL: " + request.getUrl());

        for (HarPostDataParam list : request.getPostData().getParams()) {
            if (list.getName().equalsIgnoreCase("XMLBody")) {
                System.out.println("\n------------------REQUEST---XML-------------------------------");
                System.out.println(Formatter.prettyXMLFormat(list.getValue()));
                submit.setRequest(list.getValue());
            }
        }
        System.out.println("\n------------------RESPONSE---JSON--------------------------");
        System.out.println(Formatter.prettyJSONFormat(resRAW));
        System.out.println("-----------------------------------------------------------\n");

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
        gsonBuilder.registerTypeAdapter(Errors.class, new ErrorsDeserializer());
        final Gson gson = gsonBuilder.create();

        Response responseObj = gson.fromJson(resRAW, Response.class);
        submit.setResponse(responseObj);
        return submit;
    }

    public static HarEntry catchHarEntryByTextInURL(Har har, String url) {
        // Har har = getHar();
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

    public static List<HarEntry> collectHarEntryByTextInURL(Har har, String url) {
        List<HarEntry> entryList = new ArrayList<>();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            if (request.getUrl().contains(url)) {
                System.out.println("Has catched string in url " + url);
                System.out.println("URL:  " + request.getUrl());
                entryList.add(entry);
            }
        }
        return entryList;
    }


    public static List<String> getVisualWebsiteOptimizerData() {
        List<HarEntry> harEntries = BrowserMobProxyLocal2.collectHarEntryByTextInURL("dev.visualwebsiteoptimizer.com");
        List<String> urls = new ArrayList<>();
        for (HarEntry entry : harEntries) {
            String value = entry.getRequest().getQueryString().get(0).getValue();
            String substring = value.substring(value.lastIndexOf("/") + 1);
            urls.add(substring);
        }
        return urls;
    }

    public static List<String> getVisualWebsiteOptimizerData(Har har) {
        List<HarEntry> harEntries = collectHarEntryByTextInURL(har, "dev.visualwebsiteoptimizer.com");
        List<String> urls = new ArrayList<>();
        for (HarEntry entry : harEntries) {
            String value = entry.getRequest().getQueryString().get(0).getValue();
            String substring = value.substring(value.lastIndexOf("/") + 1);
            urls.add(substring);
        }
        return urls;
    }

    public static List<HarEntry> getPolkData() {
        return BrowserMobProxyLocal2.collectHarEntryByTextInURL("polk?");
    }

    public static List<HarEntry> getPolkData(Har har) {
        return collectHarEntryByTextInURL(har, "polk?");
    }

    public static HarEntry getTrackingData(String id) {
        return BrowserMobProxyLocal2.catchHarEntryByTextInURL("/aff_l?offer_id=" + id);
    }

    public static HarEntry getTrackingData(Har har, String id) {
        return catchHarEntryByTextInURL(har, "/aff_l?offer_id=" + id);
    }

    public static Map<String, String> getDynamicPixel() throws IOException {
        HarEntry pixelCheck = BrowserMobProxyLocal2.catchHarEntryByTextInURL("pixelcheck");
        String xmlResponse = pixelCheck.getResponse().getContent().getText();
        String xmlValid = xmlResponse.substring(xmlResponse.indexOf("(") + 1, xmlResponse.lastIndexOf(")"));
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(xmlValid, type);
        return jsonMap;
    }

    public static Map<String, String> getDynamicPixel(Har har) throws IOException {
        HarEntry pixelCheck = catchHarEntryByTextInURL(har, "pixelcheck");
        String xmlResponse = pixelCheck.getResponse().getContent().getText();
        String xmlValid = xmlResponse.substring(xmlResponse.indexOf("(") + 1, xmlResponse.lastIndexOf(")"));
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(xmlValid, type);
        return jsonMap;
    }


}
