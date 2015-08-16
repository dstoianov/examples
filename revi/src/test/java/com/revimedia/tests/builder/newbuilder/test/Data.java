package com.revimedia.tests.builder.newbuilder.test;

import com.revimedia.testing.cds.auto.staticdata.CommonExtraData;
import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 11.07.2015.
 */
public class Data {


    private static Map<String, String> getContact() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("firstname", "Dorian");
        map.put("lastname", "Dummy");
        map.put("gender", "Female");
        map.put("birthdate", "Apr 26, 1982");
        map.put("phonenumber", "6565656565");
        map.put("address", "7500 Dallas Parkway");
        map.put("emailaddress", "sit.amet.massa@consequatenimdiam.ca");
        map.put("zipcode", "75024");
        map.put("city", "PLANO");
        map.put("state", "TX");

        String[] car = CommonExtraData.getRandomCar();
        map.put("year", car[0]);
        map.put("make", car[1]);
        map.put("model", car[2]);

        map.put("hassystem", "Yes");
        map.put("propertyvalue", "Over $2,000,000");

//        map.put("islivinghere", "No");
        map.put("propertyzipcode", "56565");
        map.put("address1", "Fake adr 63");


        map.put("ownrented", "Rented");
//        map.put("expirationdate", "Mar"); //temporary workaround

        return map;
    }

    @DataProvider(name = "Campaigns")
    public static Object[][] badNumbers() {
        Map<String, String> map = getContact();
        return new Object[][]{
                {"9A610B50-0E9A-46D5-B810-70A405AC3FEA", "health", map},
                {"79482715-7653-4A5E-9936-CD428B298E2C", "solar", map}, //bad radio button
                {"2D3D1708-6BFC-431B-8B0D-A3B19EA4E849", "home", map},
                {"0B630F68-190C-4508-85F1-50964D153744", "mortgage", map},
//
//                {"88368545-FB77-40BC-AE55-ABD7978271C3", "GloryToUkraine", map},
//                {"DA907EC3-2F37-47E5-996C-FFF79E5724D0", "auto", map},
//                {"EFD9D7ED-3835-4AFA-97D3-73BEDAEDA4AD", "homesecurity", map},
//                {"4D926807-2EDD-4824-B391-5EBE8210EF62", "life", map},
//                {"FCFE964E-AC1F-418A-B8AE-3A61474CC4D4", "medicalalerts", map},
//                {"320493C8-21EC-440C-B8E8-133D1D7E169E", "awdawd", map},
//                {"85E29948-464B-4F17-9C62-AB2C5DE80701", "testAPI", map},
//                {"A99BB4A4-D817-4A98-B53D-34433972C6B8", "afsdafsd", map},
//                {"7257FE12-EEEB-4CF8-B091-225B8F273140", "asd", map},
//                {"1B6A9957-BB98-4428-950E-978570609697", "asd", map},
//                {"A949BF05-D72C-4EE4-91EE-ECA942AC2504", "asd", map},
//                {"C409FE57-F255-467F-A96D-A0493BD00A80", "test upsellpage", map},

        };
    }

    @DataProvider(name = "allCampaigns")
    public static Object[][] allCampaigns() throws Exception {
        List<OfferViewList> campaigns = BeanHelper.getAvailableCampaignsOnServer();
        System.out.println(String.format("\nServer has '%s' campaigns", campaigns.size()));

        Object[][] result = new Object[campaigns.size()][];
        for (int i = 0; i < campaigns.size(); i++) {
            result[i] = new Object[]{campaigns.get(i)};
        }
        return result;
    }

    @DataProvider(name = "allListOfCampaigns")
    public static Object[][] allListOfCampaigns() throws Exception {
        List<OfferViewList> campaigns = BeanHelper.getAvailableCampaignsOnServer();
        System.out.println(String.format("\nServer has '%s' campaigns", campaigns.size()));
        return new Object[][]{
                {campaigns}
        };
    }
}
