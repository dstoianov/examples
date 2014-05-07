package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.prepop.PrePopExitPage;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by dstoianov on 5/6/2014, 7:48 PM.
 */
public class PrePopParametersTests extends BaseTest {

    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test
    public void testExitTrue() throws Exception {
        PrePopExitPage exitTruePage = new PrePopExitPage(driver);
        exitTruePage.reloadPageWithPrePopTrue();
        exitTruePage.prePopShowUp();

        assertThat(exitTruePage.getHeader(), is("Wait, don't leave! We are here to help you!"));
        assertThat(exitTruePage.getPhoneTextLink(), is("(888) 759-1914"));

        exitTruePage.closePopUp();

        assertThat(exitTruePage.getHeader(), not(is("Wait, don't leave! We are here to help you!")));
        assertThat(exitTruePage.getPhoneTextLink(), not(is("(888) 759-1914")));
    }


    @Test(dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
    public void testPrePopAllRestParameters(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        // reload page with all pre pop parameters
        driver.get(PrePopParameters.getAutoMFS(driver.getCurrentUrl(), contact, staticData));

        driverPage = new DriverPage(driver);
        // verify city is correct
        assertThat(driverPage.getPageText(), containsString(contact.getCity()));
        // verify InsuranceCompany is correct
        assertThat(driverPage.getInsuranceCompanyValue(), is(staticData.getInsuranceCompany()));

        // verify InsuranceCompany is Displayed
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));

        vehiclePage = driverPage.fillInTheRestFields(staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();

        //compareAndSavePage.get


    }
}
