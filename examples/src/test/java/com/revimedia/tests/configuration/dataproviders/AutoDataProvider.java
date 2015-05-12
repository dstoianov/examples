package com.revimedia.tests.configuration.dataproviders;

import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoMFS;
import com.revimedia.testing.cds.auto.staticdata.ExtraDataAutoP;
import com.revimedia.testing.configuration.dto.Contacts;
import com.revimedia.testing.configuration.dto.OfferParameters;
import com.revimedia.testing.configuration.helpers.CampaignsHelper;
import com.revimedia.testing.configuration.utils.PrePopIPHelper;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Denis Stoianov on 4/28/2014.
 */
public class AutoDataProvider extends DataProviderHelper {

    @DataProvider
    public static Object[][] contactAndExtraDataAutoMFS() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        return new Object[][]{
                {contact, new ExtraDataAutoMFS()}
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoMFSForeBureauChecking() {
        return new Object[][]{
                {getContactForEBureauVerification(), new ExtraDataAutoMFS()},
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoPForeBureauChecking() {
        return new Object[][]{
                {getContactForEBureauVerification(), new ExtraDataAutoP()},
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoPMobForeBureauChecking() {
        return new Object[][]{
                {getContactForEBureauVerification(), getExtraDataAutoPMob()},
        };
    }


    @DataProvider
    public static Object[][] contactExtraDataAndOfferParametersDataAutoMFS() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        Object[][] result = {
                {contact, new ExtraDataAutoMFS(), new OfferParameters()},
        };
        return result;
    }


    @DataProvider
    public static Object[][] prePopIPParametersData() throws Exception {
        Map<String, String> response = PrePopIPHelper.getResponse();
        Object[][] result = {
                {"?prepopip=true", response},
        };
        return result;
    }


    @DataProvider
    public static Object[][] contactData() {
        return new Object[][]{
                {unMarshalToObject(xmlContactData, Contacts.class)},
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoMFSBoundaryTesting(ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("url");
        String campaignName = "";
        if (url != null) {
            campaignName = CampaignsHelper.getCampaignName(CampaignsHelper.getFullUrl(url));
        }

        ExtraDataAutoMFS staticData = (new ExtraDataAutoMFS());
        staticData.setInsuranceCompany("Currently not insured");
        List<String> expectedErrors1;
        List<String> expectedErrors2;

        if (campaignName.equalsIgnoreCase("auto/s")) {
            expectedErrors1 = Arrays.asList("Phone number is invalid.", "Select education level.");
            expectedErrors2 = Arrays.asList("Email address is invalid.", "Select education level.", "Street address is invalid.");
        } else {
            expectedErrors1 = Arrays.asList("Please enter valid phone number.", "Select education level.");
            expectedErrors2 = Arrays.asList("Please enter valid street address.", "Please enter valid email address.", "Select education level.");
        }
        Collections.sort(expectedErrors1);
        Collections.sort(expectedErrors2);
        Object[][] result = {
                {contact1, staticData, expectedErrors1},
                {contact2, staticData, expectedErrors2},
        };
        return result;
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoPBoundaryTesting() {
        ExtraDataAutoP extraData = new ExtraDataAutoP();
        extraData.setInsuranceCompany("Currently not insured");
        //List<String> expectedErrors = Arrays.asList("Please enter street address.", "Please enter a valid email address.", "Please enter a valid phone number.");
        Collections.sort(expectedErrorsSorted);
        return new Object[][]{
                {contact, extraData, expectedErrorsSorted},
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoABoundaryTesting() {
        Collections.sort(expectedErrorsSorted);
        return new Object[][]{
                {contact, getExtraDataAutoA(), expectedErrorsSorted},
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoPMobBoundaryTesting() {
        // List<String> expectedErrors = Arrays.asList("Please enter street address.", "Please enter a valid email address.", "Please enter a valid phone number.");
        Collections.sort(expectedErrorsSorted);
        return new Object[][]{
                {contact, getExtraDataAutoPMob(), expectedErrorsSorted},
        };
    }


    @DataProvider
    public static Object[][] contactAndExtraDataAutoP() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        return new Object[][]{
                {contact, new ExtraDataAutoP()}
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoA() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        return new Object[][]{
                {contact, getExtraDataAutoA()}
        };
    }

    @DataProvider
    public static Object[][] contactAndExtraDataAutoPMob() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        return new Object[][]{
                {contact, getExtraDataAutoPMob()}
        };
    }


    @DataProvider
    public static Object[][] contactExtraDataAndOfferParametersDataAutoP() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        Object[][] result = {
                {contact, new ExtraDataAutoP(), new OfferParameters()},
        };
        return result;
    }

    @DataProvider
    public static Object[][] contactExtraDataAndOfferParametersDataAutoPMob() {
        Object contact = unMarshalToObject(xmlContactData, Contacts.class);
        return new Object[][]{
                {contact, getExtraDataAutoPMob(), new OfferParameters()},
        };
    }


}
