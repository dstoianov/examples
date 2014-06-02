package com.revimedia.tests.cds.DraftTests;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by Funker on 08.05.14.
 */
public class DraftTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;


    @Test
    public void testCheckRequiredFields() throws Exception {
        driverPage = new DriverPage(driver);
        driverPage.clickOnContinue();

        assertThat(driverPage.getPageText(), containsString("Please enter zip code."));
        assertThat(driverPage.getAllErrors().size(), is(7));

        driverPage.fillInZipCode("20002").clickOnContinue();
        assertThat(driverPage.getAllErrors().size(), is(6));

        assertThat(driverPage.getPageText(), containsString("Please correct the errors above."));
        //assertThat();
    }


    @Test(groups = {"submit"}, enabled = true, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testCheckZipCode(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        driverPage = new DriverPage(driver);
        driverPage.fillInAllFields(contact, staticData);
        //assertThat(driverPage.getPageText(), containsString(contact.getCity()));

        vehiclePage = driverPage.clickOnContinue();

        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);


        assertThat(compareAndSavePage.getZipStateAndCity(), containsString(contact.getCity() + ", " + contact.getState()));


    }
}
