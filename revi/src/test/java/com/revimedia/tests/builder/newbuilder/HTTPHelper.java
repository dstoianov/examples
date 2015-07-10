package com.revimedia.tests.builder.newbuilder;

/**
 * Created by dstoianov on 5/26/2014, 6:38 PM.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HTTPHelper {

    private final String USER_AGENT = "Mozilla/5.0";

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
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String result = response.toString().replace("\\", "");
        String res1 = result.substring(1, result.length() - 1);
        String res = res1.replace("<span class=\"bq-atsk\">", "<span class='bq-atsk'>");

        //print result
        System.out.println(res);

        return res;
    }

}
