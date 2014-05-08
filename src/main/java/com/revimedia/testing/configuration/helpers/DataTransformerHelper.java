package com.revimedia.testing.configuration.helpers;

/**
 * Created by Funker on 08.05.14.
 */
public class DataTransformerHelper {

    public static String phoneTransformation(String phone) {
        return phone.replaceAll("[^\\d]", "");
    }
}
