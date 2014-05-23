package com.revimedia.testing.configuration.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Funker on 08.05.14.
 */
public class DataHelper {

    public static String phoneTransformation(String phone) {
        return phone.replaceAll("[^\\d]", "");
    }

    public static String generateInvalidAddress() {
        return "Invalid Street Address " + (new Random()).nextInt(999999999);
    }

    public static String getDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        return dateFormat.format(date);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String dateTransformation(String nonFormattedDate) {
        // Jul 3, 1980  to 1980-07-03
        try {
            DateFormat format = DateFormat.getDateInstance();
            Date date = format.parse(nonFormattedDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } catch (ParseException e) {
            System.out.println("Can't transform '" + nonFormattedDate + "' date to valid for compare!!!");
            e.printStackTrace();
        }
        return nonFormattedDate;
    }


    public static String dateTransformInsuredSince(String key) {
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

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("-MM-dd");
        String end = dateFormat.format(date);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int begin = year - map.get(key);

        return Integer.toString(begin) + end;
    }


}
