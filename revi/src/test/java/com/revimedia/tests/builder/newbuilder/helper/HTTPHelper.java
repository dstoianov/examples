package com.revimedia.tests.builder.newbuilder.helper;

/**
 * Created by dstoianov on 5/26/2015, 6:38 PM.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HTTPHelper {

    private final String USER_AGENT = "Mozilla/5.0";
    private static final Logger log = LoggerFactory.getLogger(HTTPHelper.class);

    public void doConnection(String url) throws Exception {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        System.out.println(response.toString());
    }


    // HTTP GET request
    public String doGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        log.info("Sending 'GET' request to URL : " + url);
        log.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String result = response.toString().replace("\\", "");
        String res1 = result.substring(1, result.length() - 1);
        String res = res1.replace("<span class=\"bq-atsk\">", "<span class='bq-atsk'>").replace("<span class=\"bq-span\">", "<span class='bq-span'>");

        //print result
//        log.info(res);
        System.out.println(res);

        return res;
    }

}
