package com.revimedia.testing.configuration.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Funker on 08.05.14.
 */
public class DataHelper {

    public static String phoneTransformation(String phone) {
        return phone.replaceAll("[^\\d]", "");
    }

    public static String generateInvalidAddress() {
        return "Invalid Street Address " + (new Random()).nextInt(9999);
    }

    public static String getDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        return dateFormat.format(date);
    }
}
