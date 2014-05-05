package com.revimedia.testing.configuration.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revimedia.testing.configuration.response.Errors;
import com.revimedia.testing.configuration.response.ErrorsDeserializer;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.testing.configuration.response.ResponseDeserializer;
import net.lightbody.bmp.core.har.*;

/**
 * Created by Funker on 02.05.14.
 */
public class HarParser {

    public static Submit getSubmit() {
        Har har = BrowserMobProxyLocal.getHar();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            String resRAW = response.getContent().getText();

            if (request.getUrl().contains("submit")) {
                System.out.println("Has submit");
                Submit submit = new Submit();


                System.out.println("------------------request----------response----------------");
                System.out.println(request.getUrl());

                for (HarPostDataParam list : request.getPostData().getParams()) {
                    System.out.println(list.getName());
                    System.out.println(list.getValue());
                    submit.setRequest(list.getValue());
                }
                System.out.println("----------");
                System.out.println(resRAW);
                System.out.println("------------------request----------response----------------");

                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
                gsonBuilder.registerTypeAdapter(Errors.class, new ErrorsDeserializer());
                final Gson gson = gsonBuilder.create();

                Response responseObj = gson.fromJson(resRAW, Response.class);
                submit.setResponse(responseObj);

                return submit;
            }
        }
        return null;
    }
}
