package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.beans.auto.LeadDataType;
import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.cds.auto.staticdata.SurveyPath;
import com.revimedia.testing.cds.auto.staticdata.VWOData;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.Formatter;
import com.revimedia.testing.configuration.proxy.HarParser;
import com.revimedia.testing.configuration.proxy.Submit;
import com.revimedia.testing.configuration.utils.XmlToObject;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import com.revimedia.tests.configuration.helpers.SubmitVerifier;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by Funker on 23.04.14.
 */
public class SubmitTests extends BaseTest {
    public DriverPage driverPage;
    public VehiclePage vehiclePage;
    public CompareAndSavePage compareAndSavePage;

    @Test(groups = {"submit"}, enabled = true, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testPositiveSubmit(Contact contact, StaticDataAutoMFS staticData) throws Exception {

        driverPage = new DriverPage(driver);
        driverPage.fillInAllFields(contact, staticData);
        assertThat(driverPage.getPageText(), containsString(contact.getCity() + "fgfgfg"));

        vehiclePage = driverPage.clickOnContinue();

        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData);

        assertThat(compareAndSavePage.getZipStateAndCity(), containsString(contact.getCity() + ", " + contact.getState()));

        compareAndSavePage.submitForm();

        Submit submit = HarParser.getSubmit();
        // Assert response, step 1
        SubmitVerifier.verifyResponse(submit.getResponse());

        LeadDataType leadDataType = XmlToObject.unMarshal(LeadDataType.class, submit.getRequest());
        String leadId = leadDataType.getAffiliateData().getLeadId();

        // Assert request, step 2
        assertThat(leadId, is(notNullValue()));
        assertThat(leadId.length(), is(36));

        // TODO: need add "XML check for submit" and "Radio Buttons" checking

    }

    @Test(groups = {"submit", "polk"}, enabled = true, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testPolk(Contact contact, StaticDataAutoMFS staticData) throws Exception {
        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        vehiclePage.fillInAllFields(staticData);

        List<HarEntry> polkData = HarParser.getPolkData();
        String make = polkData.get(0).getResponse().getContent().getText();
        String model = polkData.get(1).getResponse().getContent().getText();

        // Asserts URL
        assertThat(polkData.get(0).getRequest().getUrl(), containsString("polk?year=" + staticData.getYear()));
        assertThat(polkData.get(1).getRequest().getUrl(), containsString("polk?year=" + staticData.getYear() + "&make=" + staticData.getMake()));

        //Asserts values that displayed in drop downs
        assertThat(Formatter.itemsJSONToList(make), containsInAnyOrder((vehiclePage.getAllMakedCarsList()).toArray()));
        assertThat(Formatter.itemsJSONToList(model), equalTo(vehiclePage.getAllModelsList()));
    }


    @Test(groups = {"submit", "survey path", "default alias answer"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testSurveyPathAndDefaultAliasAnswer(Contact contact, StaticDataAutoMFS staticData) throws Exception {

        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData).submitForm();

        LeadDataType leadDataType = XmlToObject.unMarshal(LeadDataType.class, HarParser.getSubmit().getRequest());
        String surveyPath = leadDataType.getAffiliateData().getSurveyPath();
        String annualMiles = leadDataType.getQuoteRequest().getVehicles().getVehicle().getAnnualMiles();

        // Assert survey Path
        assertThat(SurveyPath.AUTO_MFS, is(surveyPath));

        // Assert all  Default Alias Answers for this campaign
        assertThat(annualMiles, is("12500"));
    }

    @Test(groups = {"submit", "vwo"}, dataProvider = "contactAndStaticDataAutoMFS", dataProviderClass = AutoDataProvider.class)
    public void testVWO(Contact contact, StaticDataAutoMFS staticData) throws Exception {

        driverPage = new DriverPage(driver);
        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData).submitForm();

        List<String> vwoData = HarParser.getVWOData();

        assertThat(vwoData.size(), is(VWOData.AUTO_MFS_VWO.size()));
        assertThat(vwoData, is(VWOData.AUTO_MFS_VWO));
    }
}
