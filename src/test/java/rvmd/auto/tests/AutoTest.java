package rvmd.auto.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import rvmd.auto.pages.CompareAndSavePage;
import rvmd.auto.pages.DriverPage;
import rvmd.auto.pages.VehiclePage;
import rvmd.webBrowser.BaseTest;

/**
 * Created by Funker on 23.04.14.
 */
public class AutoTest extends BaseTest {

    @Test
    public void testPositiveSubmit() throws Exception {

        DriverPage driverPage = new DriverPage(driver);
        VehiclePage vehiclePage = driverPage.fillInAllFields("20002").clickOnContinue();
        CompareAndSavePage compareAndSavePage = vehiclePage.fillInAllFields().clickOnContinue();
        compareAndSavePage.fillInAllFields();
        //compareAndSavePage.clickOnGetMyQuotes();
        compareAndSavePage.submitForm();

        //compareAndSavePage.
    }

    @Test
    public void testCheckRequiredFields() throws Exception {
        DriverPage driverPage = new DriverPage(driver);
        driverPage.clickOnContinue();
        Assert.assertTrue(isTextPresent("Please correct the errors above."));
        Assert.assertTrue(isTextPresent("Please enter at least 4 characters."));
        //assertThat();
    }


    public boolean isTextPresent(String text) {
        try {

            // return driver.getPageSource().contains(text);
            String text1 = driver.findElement(By.xpath(".//*[@id='bq-form-here']/div")).getText();
            return text1.contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}
