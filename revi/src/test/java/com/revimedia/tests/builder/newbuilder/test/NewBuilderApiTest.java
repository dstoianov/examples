package com.revimedia.tests.builder.newbuilder.test;

import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.testng.annotations.Test;

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

}
