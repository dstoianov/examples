package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.beans.auto.AffiliateDataType;
import com.revimedia.testing.beans.auto.LeadDataType;
import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.prepop.PrePopParameters;
import com.revimedia.testing.cds.staticdata.VisualWebsiteOptimizerData;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.dto.OfferParameters;
import com.revimedia.testing.configuration.dto.Submit;
import com.revimedia.testing.configuration.helpers.CampaignsHelper;
import com.revimedia.testing.configuration.helpers.DataHelper;
import com.revimedia.testing.configuration.proxy.HarParser;
import com.revimedia.testing.configuration.utils.XmlToObject;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by dstoianov on 5/6/2014, 7:48 PM.
 */
public class PrePopParametersTests extends BaseTest {

    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"prepop"}, dataProvider = "contactAndExtraDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldBePrefilledAndShownOnUIContactAndOtherParameters(Contact contact, ExtraDataAutoMFS extraData) throws Exception {
        // reload page with all pre pop parameters
        driverPage = new DriverPage(driver);
        driverPage.open(PrePopParameters.generateURLForAutoMFSWithContactAndExtra(this.url, contact, extraData));
        driverPage.fillInTheRestFields(extraData);
        // verify city is correct
        assertThat(driverPage.getPageText(), containsString(contact.getCity()));
        // verify InsuranceCompany is correct
        assertThat(driverPage.getInsuranceCompanyValue(), is(extraData.getInsuranceCompany()));

        // verify InsuranceCompany is Displayed
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));

        vehiclePage = driverPage.clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(extraData).clickOnContinue();

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

    @Test(groups = {"prepop"}, dataProvider = "contactAndExtraDataAutoMFSBoundaryTesting", dataProviderClass = AutoDataProvider.class)
    public void shouldBeExpectedErrorsOnPageWhenEnterNonValidData(Contact contact, ExtraDataAutoMFS extraData, List<String> expectedErrorsOnPage) throws Exception {
        // reload page with all pre pop parameters
        driverPage = new DriverPage(driver);
        driverPage.open(PrePopParameters.generateURLForAutoMFSWithContactAndExtra(this.url, contact, extraData));
        driverPage.fillInTheRestFields(extraData);

        assertThat(driverPage.getPageText(), containsString(contact.getCity()));
        assertThat(driverPage.getInsuranceCompanyValue(), is(extraData.getInsuranceCompany()));
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));

        vehiclePage = driverPage.clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(extraData).clickOnContinue();
        compareAndSavePage.clickSubmit();

        assertThat(compareAndSavePage.getErrorsOnBottomPageAsList(), is(equalTo(expectedErrorsOnPage)));
    }

    @Test(groups = {"submit", "vwo"}, dataProvider = "contactAndExtraDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void shouldPresentInURLVisualWebsiteOptimizerData(Contact contact, ExtraDataAutoMFS extraData) throws Exception {
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, extraData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(extraData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, extraData).submitForm();

        List<String> vwoData = HarParser.getVisualWebsiteOptimizerData(proxy.getHar());
        List<String> visualWebsiteOptimizerList = VisualWebsiteOptimizerData.getVWOList(CampaignsHelper.getCampaignName(this.url));

        assertThat(vwoData, is(visualWebsiteOptimizerList));
        assertThat(vwoData.size(), is(visualWebsiteOptimizerList.size()));
    }

    @Test(groups = {"submit", "Offer Parameters"}, dataProvider = "contactExtraDataAndOfferParametersDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void DRAFT_shouldPresentOfferParametersInXMLAndDynamicPixelDataInURL(Contact contact, ExtraDataAutoMFS extraData, OfferParameters offerParameters) throws Exception {
        driverPage = new DriverPage(driver);
        //reload page with necessarily offer parameters in URL
        driverPage.open(this.url + offerParameters.toURLString());
        vehiclePage = driverPage.fillInAllFields(contact, extraData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(extraData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, extraData).submitForm();

        Har har = proxy.getHar();
        Submit submit = HarParser.getSubmit(har);
        LeadDataType leadDataType = XmlToObject.unMarshal(LeadDataType.class, submit.getRequest());
        AffiliateDataType affiliateData = leadDataType.getAffiliateData();

        // Har har = proxy.getHar();
        Map<String, String> dynamicPixelResponse = HarParser.getDynamicPixel(har);
        HarEntry trackingData = HarParser.getTrackingData(har, offerParameters.getOffer_id());

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
        assertThat("Wrong Response of Tracking Data", trackingData.getResponse().getStatus(), is(302));

    }


    @Test(groups = {"prepop", "prepop IP"}, enabled = false, description = "Is not implemented yet on CDS 2.0", dataProvider = "prePopIPParametersData", dataProviderClass = AutoDataProvider.class)
    public void DRAFT_shouldBeShownCityNameAndHidedZipCodeFieldOnPage(String prepop, Map<String, String> response) throws Exception {
        String city = response.get("City");
        String state = response.get("State");
        String zipCode = response.get("Zipcode");

        driverPage = new DriverPage(driver);
        driverPage.open(this.url + prepop);
        assertThat(driverPage.getPageText(), containsString(city));
        assertThat(driverPage.isZipCodeFieldDisplayed(), is(false));
    }
}
