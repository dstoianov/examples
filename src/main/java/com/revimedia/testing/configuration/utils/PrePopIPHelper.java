package com.revimedia.testing.configuration.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dstoianov on 5/27/2014, 2:43 PM.
 */
public class PrePopIPHelper {

    public static Map<String, String> getResponse() {
        String url = "http://development.stagingrevi.com/ajax/location/ip";
        try {
            String body = Jsoup.connect(url).method(Connection.Method.GET).execute().body();

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            Map<String, String> responseMap = gson.fromJson(body, type);
            return responseMap;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("There is no any JSON response from " + url);
        }
    }
}
