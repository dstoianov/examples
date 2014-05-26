package com.revimedia.tests.cds.DraftTests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.helpers.OfferParameters;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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

        StaticDataAutoMFS staticDataAutoMFS = new StaticDataAutoMFS();

        StaticDataAutoMFS copy = staticDataAutoMFS.clone();
        copy.setMake("qqqqq");
        //assertThat(staticDataAutoMFS, equalTo(copy));
        Assert.assertEquals(staticDataAutoMFS, copy);

    }

    @Test
    public void testRandom() throws Exception {

        OfferParameters offerParameters = new OfferParameters();
        System.out.println(offerParameters.toURLString());
    }
}
