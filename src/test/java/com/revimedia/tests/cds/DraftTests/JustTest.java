package com.revimedia.tests.cds.DraftTests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.configuration.helpers.OfferParameters;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dstoianov on 5/23/2014, 4:24 PM.
 */
public class JustTest {


    @Test
    public void testName() throws Exception {
        //Expected: is "2013-05-23"
        //but: was "1"
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("5+", 10);

        String key = "1";

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("-MM-dd");
        String end = dateFormat.format(date);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int begin = year - map.get(key);

        String result = Integer.toString(begin) + end;


    }


    @Test
    public void testIPAddress() throws Exception {

        InetAddress localHost = InetAddress.getLocalHost();

    }

    public boolean equals(Object that, String[] fields) {

        try {
            for (String fieldName : fields) {
                if (!this.getClass().getField(fieldName).equals(that.getClass().getField(fieldName)))
                    return false;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Test
    public void testGson() throws Exception {

        String xml = "jQuery18204237265042029321_1401116273271({\"_success\":\"BaeOK\",\"Mode\":\"Dynamic\",\"Errors\":[],\"Success\":true})";
        String xml2 = "{\"_success\":\"BaeOK\",\"Mode\":\"Dynamic\",\"Errors\":[],\"Success\":true}";
        //Type listType = new TypeToken<HashMap<String, String>>() {}.getType();
        //final GsonBuilder gsonBuilder = new GsonBuilder();
        //final Gson gson = gsonBuilder.create();
        //final Gson gson = new Gson();

        String xmlValid = xml.substring(xml.indexOf("(") + 1, xml.lastIndexOf(")"));
        Map<String, String> result = new ObjectMapper().readValue(xmlValid, HashMap.class);
        String success = result.get("_success");


        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, String> jsonMap = gson.fromJson(xmlValid, type);
        String success1 = jsonMap.get("_success");


    }

    @Test
    public void testRandom() throws Exception {

        OfferParameters offerParameters = new OfferParameters();
        System.out.println(offerParameters.toURLString());
    }
}
