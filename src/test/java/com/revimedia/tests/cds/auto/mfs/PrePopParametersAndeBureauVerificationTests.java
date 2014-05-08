package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.constants.Messages;
import com.revimedia.testing.cds.prepop.PrePopExitPage;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by dstoianov on 5/6/2014, 7:48 PM.
 */
public class PrePopParametersAndeBureauVerificationTests extends BaseTest {

    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"prepop", "not ready yet"}, enabled = false, description = "Is not ready yet")
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

    @Test(groups = {"prepop"}, dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
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

        // Assertions
        assertThat(compareAndSavePage.getFirstNameValue(), equalTo(contact.getFirstName()));
        assertThat(compareAndSavePage.getLastNameValue(), equalTo(contact.getLastName()));
        assertThat(compareAndSavePage.getGenderValue(), equalTo(contact.getGender()));
        assertThat(compareAndSavePage.getDateOfBirthValue(), equalTo(contact.getBirthDate()));
        assertThat(compareAndSavePage.getStreetAddressValue(), equalTo(contact.getAddress()));
        assertThat(compareAndSavePage.getZipCodeValue(), equalTo(contact.getZipCode()));
        assertThat(DataHelper.phoneTransformation(compareAndSavePage.getPhoneNumberValue()),
                equalTo(DataHelper.phoneTransformation(contact.getPhoneNumber())));
        assertThat(compareAndSavePage.getEmailValue(), equalToIgnoringCase(contact.getEmailAddress()));
    }

    @Test(groups = {"eBureau Verification"}, dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
    public void testeBureauVerification(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        //ACT
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);
        compareAndSavePage.fillInInvalidStreetAddressField(DataHelper.generateInvalidAddress()).clickSubmit();

        //Assert
        assertThat(compareAndSavePage.getPageText(), containsString(Messages.EBUREAU_VERIFICATION));
        assertThat(compareAndSavePage.getAllErrors().get(0).getText(), equalTo(Messages.EBUREAU_VERIFICATION));
    }
}
