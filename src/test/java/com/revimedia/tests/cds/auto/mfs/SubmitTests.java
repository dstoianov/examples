package com.revimedia.tests.cds.auto.mfs;

import com.revimedia.testing.cds.auto.mfs.pages.CompareAndSavePage;
import com.revimedia.testing.cds.auto.mfs.pages.DriverPage;
import com.revimedia.testing.cds.auto.mfs.pages.VehiclePage;
import com.revimedia.testing.cds.auto.staticdata.StaticDataAutoMFS;
import com.revimedia.testing.configuration.dto.Contact;
import com.revimedia.testing.configuration.helpers.Formatter;
import com.revimedia.testing.configuration.proxy.HarParser;
import com.revimedia.testing.configuration.proxy.Submit;
import com.revimedia.tests.configuration.BaseTest;
import com.revimedia.tests.configuration.dataproviders.AutoDataProvider;
import com.revimedia.tests.configuration.helpers.SubmitVerifier;
import net.lightbody.bmp.core.har.HarEntry;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;


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

    @Test(groups = {"submit", "polk"}, dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
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


    @Test(groups = {"submit", "survey path"}, dataProvider = "contactAndStaticData", dataProviderClass = AutoDataProvider.class)
    public void testSurveyPath(Contact contact, StaticDataAutoMFS staticData) throws Exception {

        driverPage = new DriverPage(driver);
        String surveyPathValue = driverPage.getSurveyPathValue();

        vehiclePage = driverPage.fillInAllFields(contact, staticData).clickOnContinue();
        compareAndSavePage = vehiclePage.fillInAllFields(staticData).clickOnContinue();
        compareAndSavePage.fillInAllFields(contact, staticData).submitForm();

        Submit submit = HarParser.getSubmit();
        String xml2 = submit.getRequest();

        //assertThat(xml, hasXPath("//something[@id='b']/cheese", equalTo(surveyPathValue)));
        //assertThat(hasXPath("//honky"), xml);
        // assertThat(xml, Matchers.hasXPath("dddd", equalToIgnoringCase("ddd")));

        //  assertThat(xml, hasXPath("/mountains/mountain"));

    }



}
