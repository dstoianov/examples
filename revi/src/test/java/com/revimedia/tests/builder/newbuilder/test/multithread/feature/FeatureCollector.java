package com.revimedia.tests.builder.newbuilder.test.multithread.feature;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dstoianov on 2015-05-27.
 */
public class FeatureCollector {

    private List<FeatureBean> featureBeanList = new ArrayList<>();

    //    Double Checked Locking & volatile
    private static volatile FeatureCollector instance;

    public static FeatureCollector getInstance() {
        FeatureCollector localInstance = instance;
        if (localInstance == null) {
            synchronized (FeatureCollector.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FeatureCollector();
                }
            }
        }
        return localInstance;
    }


    public void printData() {

    }

    public void putData(FeatureBean feature) {
        featureBeanList.add(feature);
    }

}
