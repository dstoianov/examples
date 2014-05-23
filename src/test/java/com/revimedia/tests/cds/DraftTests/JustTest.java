package com.revimedia.tests.cds.DraftTests;

import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
}
