package com.revimedia.testing.configuration.proxy;

import com.google.gson.Gson;
import net.lightbody.bmp.core.har.*;

import java.io.BufferedReader;

/**
 * Created by Funker on 02.05.14.
 */
public class HarParser {

    public static void answer() {
        Har har = BrowserMobProxy.getHar();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            if (request.getUrl().contains("submit")) {
                System.out.println("Has submit");

                System.out.println("------------------request----------response----------------");
                System.out.println(request.getUrl());

                for (HarPostDataParam list : request.getPostData().getParams()) {
                    System.out.println(list.getName());
                    System.out.println(list.getValue());
                }
                System.out.println("----------");
                System.out.println(response.getContent().getText());
                System.out.println("------------------request----------response----------------");

//                Gson gson = new Gson();
//                String br = response.getContent().getText();
//                Response response1 = gson.fromJson(br, Response.class);
            }

        }
    }
}
