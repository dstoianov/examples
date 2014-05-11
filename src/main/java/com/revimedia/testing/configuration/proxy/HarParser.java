package com.revimedia.testing.configuration.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revimedia.testing.configuration.helpers.Formatter;
import com.revimedia.testing.configuration.response.Errors;
import com.revimedia.testing.configuration.response.ErrorsDeserializer;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.testing.configuration.response.ResponseDeserializer;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;

import java.util.List;

import static com.revimedia.testing.configuration.proxy.BrowserMobProxyLocal2.*;

/**
 * Created by Funker on 02.05.14.
 */
public class HarParser {
    public static HarEntry entry;

    public static Submit getSubmit() {
        entry = BrowserMobProxyLocal2.getSubmitHarEntry();
        HarRequest request = entry.getRequest();
        HarResponse response = entry.getResponse();
        String resRAW = response.getContent().getText();

        Submit submit = new Submit();
        System.out.println("------------------REQUEST---URL-------------------------------");
        System.out.println("URL: " + request.getUrl());

        for (HarPostDataParam list : request.getPostData().getParams()) {
            System.out.println("\n------------------REQUEST---XML-------------------------------");
            System.out.println(Formatter.prettyXMLFormat(list.getValue()));
            submit.setRequest(list.getValue());
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


    public static List<HarEntry> getPolkData() {
        return BrowserMobProxyLocal2.getPolkData();
    }
}
