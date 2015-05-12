package com.tipsandtricks.TestNGRetry.fixReport;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.*;

/**
 * Created by dstoianov on 2014-11-26, 12:37 PM
 */
public class FixRetryListener extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);

        // List of test results which we will delete later
        List<ITestResult> testsToBeRemoved = new ArrayList<>();

        // collect all id's from passed test
        Set<Integer> passedTestIds = new HashSet<>();
        for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
            passedTestIds.add(getId(passedTest));
        }

        Set<Integer> failedTestIds = new HashSet<>();
        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {

            // id = class + method + dataprovider
            int failedTestId = getId(failedTest);

            // if we saw this test as a failed test before we mark as to be deleted
            // or delete this failed test if there is at least one passed version
            if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                testsToBeRemoved.add(failedTest);
            } else {
                failedTestIds.add(failedTestId);
            }
        }

        // finally delete all tests that are marked
        for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext(); ) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {
                iterator.remove();
            }
        }

    }

    private int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = 31 * id + result.getMethod().getMethodName().hashCode();
        id = 31 * id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }


}

