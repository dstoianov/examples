package ua.com.rozetka.speed.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Arrays;


/**
 * Created by Funker on 15.07.2015.
 */

public class CustomListener extends TestListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);


    private static final String TEST_NAME_TEMPLATE = "<< %s >>";

    private void log(String msg, Object... args) {
        logger.info(String.format(msg, args));
    }

    @Override
    public void onStart(ITestContext arg0) {
        log("Test suite: " + arg0.getName());
        log("Parameters " + arg0.getCurrentXmlTest().getAllParameters().toString());
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        String[] methodsDependedUpon = arg0.getMethod().getMethodsDependedUpon();

        logger.info(arg0.getMethod().getTestClass().toString());

        log(TEST_NAME_TEMPLATE, arg0.getName());
        logger.info("Test parameters {}", Arrays.toString(arg0.getParameters()));
        if (methodsDependedUpon != null && methodsDependedUpon.length > 0) {
            log("Depends on %s", Arrays.toString(methodsDependedUpon));
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log("%s --- SUCCESS ---\n", tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        logger.error(tr.getName() + " --- FAILED --- ");
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            logger.error(cause + "\n");
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("%s --- SKIPPED ---\n", tr.getName());
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            logger.error(cause + "\n");
        }
    }

    public long timer(ITestResult tr) {
        return tr.getEndMillis() - tr.getStartMillis();
    }
}
