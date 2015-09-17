package com.revimedia.tests.builder.newbuilder.test.multithread.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.xml.XmlTest;

import java.util.List;

/**
 * Created by Funker on 21.08.2015.
 */
public class TestSuiteListener implements ISuiteListener {

    private static final Logger log = LoggerFactory.getLogger(TestSuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        log.info("Start suite '{}'", suite.getName());
        List<XmlTest> tests = suite.getXmlSuite().getTests();

        for (XmlTest test : tests) {
            test.setParallel("classes");
            int i = Integer.parseInt(System.getProperty("threads", "1"));
            test.setThreadCount(i);
            log.info("Test name '{}', set parallel '{}' and thread count '{}'", test.getName(), test.getParallel(), test.getThreadCount());
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        log.info("Finish suite '{}'", suite.getName());
    }
}
