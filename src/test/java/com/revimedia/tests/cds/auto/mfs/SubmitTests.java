package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.proxy.HarParser;
import com.revimedia.testing.configuration.proxy.Submit;
import com.revimedia.testing.configuration.response.Response;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import com.revimedia.tests.configuration.helpers.SubmitVerifier;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Funker on 23.04.14.
 */
public class SubmitTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"submit"}, dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
    public void testPositiveSubmit(Contact contact, StaticDataAutoMFS staticData) throws Exception {

        driverPage = new DriverPage(driver);
        driverPage.fillInAllFields(contact, staticData);
        assertThat(driverPage.getPageText(), containsString(contact.getCity()));

        vehiclePage = driverPage.clickOnContinue();

        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);

        assertThat(compareAndSavePage.getZipStateAndCity(), containsString(contact.getCity() + ", " + contact.getState()));

        compareAndSavePage.submitForm();

        SubmitVerifier.verifyResponse(HarParser.getSubmit().getResponse());
        //SubmitVerifier.verifyRequest(HarParser.getSubmit().getRequest());
    }



}
