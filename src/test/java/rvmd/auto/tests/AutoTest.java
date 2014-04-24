package rvmd.auto.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import rvmd.auto.pages.CompareAndSavePage;
import rvmd.auto.pages.DriverPage;
import rvmd.auto.pages.VehiclePage;
import rvmd.webBrowser.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
        Assert.assertTrue(isTextPresent("Please enter zip code."));
        Assert.assertTrue(isTextPresent("Please correct the errors above."));
        assertThat(driver.findElement(By.xpath(".//*[@id='bq-form-here']/div")).getText(), containsString("Please correct the errors above."));
        assertThat(driver.findElement(By.xpath(".//*[@id='bq-form-here']/div")).getText(), containsString("Please enter zip code."));
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
