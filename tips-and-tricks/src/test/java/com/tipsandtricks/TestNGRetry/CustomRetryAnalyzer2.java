package com.tipsandtricks.TestNGRetry;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

//import java.util.logging.Logger;

/**
 * Created by dstoianov on 5/28/2014, 6:49 PM.
 */
public class CustomRetryAnalyzer2 implements IRetryAnalyzer {

    protected final Logger log = Logger.getLogger("testbase.testng");
    private int count = 1;
    private int maxCount = 3;

    public CustomRetryAnalyzer2() {
//        log.info("ModeledRetryAnalyzer constructor " + this.getClass().getName());
        System.out.println("ModeledRetryAnalyzer constructor " + this.getClass().getName());
    }

    @Override
    public boolean retry(ITestResult result) {
        String messsage = String.format("Running retry logic for '%s' test \non class '%s'\nwith status '%s' \nRetrying '%s' times ",
                result.getName(), this.getClass().getName(), getTestResult(result), count);

//        log.trace(messsage);
        System.out.println(messsage);
        Reporter.log(messsage);
        if (count < maxCount) {
            count++;
            return true;
        }
        return false;
    }


    private String getTestResult(ITestResult result) {
        int status = result.getStatus();
        String resultString;
        switch (status) {
            case 1:
                resultString = "Success";
                break;
            case 2:
                resultString = "Failure";
                break;
            case 3:
                resultString = "Skip";
                break;
            default:
                resultString = "Unknown";
        }
        return resultString;
    }


}
