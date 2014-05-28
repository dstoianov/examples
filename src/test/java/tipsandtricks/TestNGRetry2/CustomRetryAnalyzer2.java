package tipsandtricks.TestNGRetry2;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//import java.util.logging.Logger;

/**
 * Created by dstoianov on 5/28/2014, 6:49 PM.
 */
public class CustomRetryAnalyzer2 implements IRetryAnalyzer {

    private int count = 0;

    private int maxCount = 2;
    //protected Logger log;
    //private static Logger testbaseLog;
    protected final Logger log = Logger.getLogger("testbase.testng");

//    static {
//
//        //testbaseLog = Logger.getLogger("testbase.testng");
//        log = Logger.getLogger("testbase.testng");
//    }

    public CustomRetryAnalyzer2() {

        log.info(" ModeledRetryAnalyzer constructor " + this.getClass().getName());
        System.out.println(" ModeledRetryAnalyzer constructor " + this.getClass().getName());

        //testbaseLog.trace(" ModeledRetryAnalyzer constructor " + this.getClass().getName());

    }

    @Override
    public boolean retry(ITestResult result) {
        log.trace("running retry logic for  '"
                + result.getName()
                + "' \non class " + this.getClass().getName() + " \nwith status " + getTestResult(result) + "Retrying " + count + " times");

        System.out.println("running retry logic for  '"
                + result.getName()
                + "'\non class " + this.getClass().getName() + "\nwith status " + getTestResult(result) + "\nRetrying " + count + " times");

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
