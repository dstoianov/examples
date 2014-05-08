package com.revimedia.testing.configuration.helpers;

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

}
