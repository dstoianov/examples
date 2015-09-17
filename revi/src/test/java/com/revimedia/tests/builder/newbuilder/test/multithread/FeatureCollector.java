package com.revimedia.tests.builder.newbuilder.test.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dstoianov on 2015-05-27.
 */
public class FeatureCollector {

    private static FeatureCollector instance = null;
    private List<String> data = new ArrayList<>();

    protected FeatureCollector() {
        // Exists only to defeat instantiation.
    }

    public static synchronized FeatureCollector getInstance() {
        if (instance == null) {
            instance = new FeatureCollector();
        }
        return instance;
    }

    public void putData(String value) {
        data.add(value);
    }

    public void printData() {
        for (String s : data) {
            System.out.println(s);
        }
    }

}
