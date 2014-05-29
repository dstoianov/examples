package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.beans.auto.AffiliateDataType;
import com.revimedia.testing.beans.auto.LeadDataType;
import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.staticdata.VWOData;
import com.revimedia.testing.cds.prepop.PrePopExitPage;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.testing.configuration.dto.OfferParameters;
import com.revimedia.testing.configuration.proxy.HarParser;
import com.revimedia.testing.configuration.proxy.Submit;
import com.revimedia.testing.configuration.utils.XmlToObject;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

/**
 * Created by dstoianov on 5/6/2014, 7:48 PM.
 */
public class PrePopParametersTests extends BaseTest {

    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"prepop", "not ready yet"}, enabled = false, description = "Is not implemented yet on CDS 2.0")
    public void shouldBeShownExitPopUpWindow() throws Exception {
        PrePopExitPage exitTruePage = new PrePopExitPage(driver);
        exitTruePage.reloadPageWithPrePopTrue();
        exitTruePage.prePopShowUp();

        assertThat(exitTruePage.getHeader(), is("Wait, don't leave! We are here to help you!"));
        assertThat(exitTruePage.getPhoneTextLink(), is("(888) 759-1914"));

        exitTruePage.closePopUp();

        assertThat(exitTruePage.getHeader(), not(is("Wait, don't leave! We are here to help you!")));
        assertThat(exitTruePage.getPhoneTextLink(), not(is("(888) 759-1914")));
    }

    @Test(groups = {"prepop"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldBePrefilledAndShownOnUIContactAndOtherParameters(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        // reload page with all pre pop parameters
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

    @Test(groups = {"prepop"}, dataProvider = "contactAndStaticDataAutoMFSBoundaryTesting", dataProviderClass = AutoDataProvider.class)
    public void shouldBeExpectedErrorsOnPageWhenEnterNonValidData(Contact contact, StaticDataAutoMFS staticData, List<String> expectedErrorsOnPage) throws Exception {
        // reload page with all pre pop parameters
        driver.get(PrePopParameters.generateURLForAutoMFSWithContactAndStatic(this.url, contact, staticData));
        driverPage = new DriverPage(driver);

        assertThat(driverPage.getPageText(), containsString(contact.getCity()));
        assertThat(driverPage.getInsuranceCompanyValue(), is(staticData.getInsuranceCompany()));
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));

        vehiclePage = driverPage.fillInTheRestFields(staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.clickSubmit();

        List<WebElement> gotErrorsOnPage = compareAndSavePage.getErrorsOnBottomPage();

        for (int i = 0; i < expectedErrorsOnPage.size(); i++) {
            assertThat(expectedErrorsOnPage.get(i), is(gotErrorsOnPage.get(i).getText()));
        }
        assertThat(gotErrorsOnPage.size(), is(expectedErrorsOnPage.size()));
    }

    @Test(groups = {"submit", "vwo"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldPresentInURLVisualWebsiteOptimizerData(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData).submitForm();

        List<String> vwoData = HarParser.getVWOData();

        assertThat(vwoData, is(VWOData.AUTO_MFS_VWO));
        assertThat(vwoData.size(), is(VWOData.AUTO_MFS_VWO.size()));
    }

    @Test(groups = {"submit", "Offer Parameters"}, dataProvider = "contactAndStaticAndOfferParametersDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void DRAFT_shouldPresentOfferParametersInXMLAndDynamicPixelDataInURL(Contact contact, StaticDataAutoMFS staticData, OfferParameters offerParameters) throws Exception {
        //reload page with necessarily offer parameters in URL
        driver.get(this.url + offerParameters.toURLString());
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData).submitForm();

        Submit submit = HarParser.getSubmit();
        LeadDataType leadDataType = XmlToObject.unMarshal(LeadDataType.class, submit.getRequest());
        AffiliateDataType affiliateData = leadDataType.getAffiliateData();

        Map<String, String> dynamicPixelResponse = HarParser.getDynamicPixel();
        HarEntry trackingData = HarParser.getTrackingData(offerParameters.getOffer_id());

        // Asserts
        assertThat(affiliateData.getId(), is(offerParameters.getAff_id()));
        assertThat(affiliateData.getOfferId(), is(offerParameters.getOffer_id()));
        assertThat(affiliateData.getSubId(), is(offerParameters.getAff_sub()));
        assertThat(affiliateData.getSub2Id(), is(offerParameters.getAff_sub2()));
        assertThat(affiliateData.getSource(), is(offerParameters.getSource()));
        assertThat(affiliateData.getTransactionId(), is(offerParameters.getTransaction_id()));

        assertThat(dynamicPixelResponse.get("_success"), is("BaeOK"));

        //TODO: ???
        assertThat(trackingData, is(notNullValue()));
        assertThat(trackingData.getResponse().getStatus(), is(302));

    }


    @Test(groups = {"prepop", "prepop IP"}, enabled = false, description = "Is not implemented yet on CDS 2.0", dataProvider = "prePopIPParametersData", dataProviderClass = AutoDataProvider.class)
    public void DRAFT_shouldBeShownCityNameAndHidedZipCodeFieldOnPage(String prepop, Map<String, String> response) throws Exception {
        driver.get(this.url + prepop);
        String city = response.get("City");
        String state = response.get("State");
        String zipCode = response.get("Zipcode");

        driverPage = new DriverPage(driver);
        assertThat(driverPage.getPageText(), containsString(city));
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));
    }
}
