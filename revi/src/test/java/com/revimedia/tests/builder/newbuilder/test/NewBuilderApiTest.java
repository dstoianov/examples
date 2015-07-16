package com.revimedia.tests.builder.newbuilder.test;

import com.google.gson.JsonSyntaxException;
import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Funker on 11.07.2015.
 */
public class NewBuilderApiTest extends Data {

    @Test(dataProvider = "badCampaigns")
    public void testCheckBuilderForCampaignBad(String guid) throws Exception {
        System.out.println(String.format("Check campaign with guid '%s'..", guid));
        BeanHelper.getCampaignSettings(guid);
    }


    @Test(dataProvider = "goodCampaigns")
    public void testCheckBuilderForCampaign(String guid) throws Exception {
        System.out.println(String.format("Check campaign with guid '%s'..", guid));
        BeanHelper.getCampaignSettings(guid);
    }


    @Test(dataProvider = "allCampaigns")
    public void testCheckBuilderForAllCampaigns(OfferViewList c) throws Exception {
        System.out.println(String.format("Check campaign with title '%s' and guid '%s'..", c.getTitle(), c.getOfferViewGuid()));
        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(c.getOfferViewGuid());
    }


    @Test(dataProvider = "allCampaigns")
    public void testCheckSettingsJSON(OfferViewList c) throws Exception {
        System.out.println(String.format("Check Settings for campaign with title '%s' and guid '%s'..", c.getTitle(), c.getOfferViewGuid()));
        SettingsBean settingsFor = BeanHelper.getSettingsFor(c.getOfferViewGuid());
    }

    @Test(dataProvider = "allListOfCampaigns")
    public void testCheckBuilderForAllCampaignsTogether(List<OfferViewList> OfferViewList) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        for (OfferViewList c : OfferViewList) {
            try {
                System.out.println(String.format("Check campaign with title '%s' and guid '%s'..", c.getTitle(), c.getOfferViewGuid()));
                CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(c.getOfferViewGuid());
                sb.append("{\"").append(c.getOfferViewGuid()).append("\"},\n");
            } catch (JsonSyntaxException e) {
            }
        }
        System.out.println(sb.toString());
    }

}
