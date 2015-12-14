package com.tipsandtricks.windowshandle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by Funker on 10.10.2015.
 */
public class WindowHandlesTest {


    public WebDriver driver;


    @Test
    public void testName() throws Exception {

        waitForWindowsCount(2);
    }

    private void waitForWindowsCount(final int count) {
        WebDriverWait waiter = (WebDriverWait) new WebDriverWait(driver, 20).withMessage("driver.getWindowHandles().size() is "
                + driver.getWindowHandles().size()
                + ". Expected size is " + count);

        waiter.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                if (input.getWindowHandles().size() == count) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

}
