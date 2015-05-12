package com.tipsandtricks.TestNGRetry.fixReport;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by dstoianov on 2014-11-26, 12:30 PM
 */
public class Retry implements IRetryAnalyzer {

    private final int m_maxRetries = 2;
    private final int m_sleepBetweenRetries = 1000;
    private int currentTry;
    private String previousTest = null;
    private String currentTest = null;

    public Retry() {
        currentTry = 0;
    }

    @Override
    public boolean retry(final ITestResult result) {
        // If a testcase has succeeded, this function is not called.
        boolean retValue = false;

        // Getting the max retries from suite.
        String maxRetriesStr = result.getTestContext().getCurrentXmlTest().getParameter("maxRetries");
        int maxRetries = m_maxRetries;
        if (maxRetriesStr != null) {
            try {
                maxRetries = Integer.parseInt(maxRetriesStr);
            } catch (final NumberFormatException e) {
                System.out.println("NumberFormatException while parsing maxRetries from suite file." + e);
            }
        }

        // Getting the sleep between retries from suite.
        String sleepBetweenRetriesStr = result.getTestContext().getCurrentXmlTest().getParameter("sleepBetweenRetries");
        int sleepBetweenRetries = m_sleepBetweenRetries;
        if (sleepBetweenRetriesStr != null) {
            try {
                sleepBetweenRetries = Integer.parseInt(sleepBetweenRetriesStr);
            } catch (final NumberFormatException e) {
                System.out.println("NumberFormatException while parsing sleepBetweenRetries from suite file." + e);
            }
        }

        currentTest = result.getTestContext().getCurrentXmlTest().getName();

        if (previousTest == null) {
            previousTest = currentTest;
        }
        if (!(previousTest.equals(currentTest))) {
            currentTry = 0;
        }

        if (currentTry < maxRetries) {
            try {
                Thread.sleep(sleepBetweenRetries);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            retValue = true;
            currentTry++;
        } else {
            currentTry = 0;
        }
        previousTest = currentTest;
        // if this method returns true, it will rerun the test once again.
        return retValue;
    }
}
