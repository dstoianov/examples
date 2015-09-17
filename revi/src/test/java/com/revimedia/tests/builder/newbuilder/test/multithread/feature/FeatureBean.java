package com.revimedia.tests.builder.newbuilder.test.multithread.feature;

/**
 * Created by Funker on 23.08.2015.
 */
public class FeatureBean {

    private String testName;
    private String className;
    private String featureName;
    private String thread;
    private String status;
    private String time;

    public FeatureBean(String testName, String className, String featureName, String thread, String status, String time) {
        this.testName = testName;
        this.className = className;
        this.featureName = featureName;
        this.thread = thread;
        this.status = status;
        this.time = time;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
