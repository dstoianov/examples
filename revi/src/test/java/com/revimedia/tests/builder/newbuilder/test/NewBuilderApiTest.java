package com.revimedia.tests.builder.newbuilder.test;

import com.google.gson.JsonSyntaxException;
import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
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

    @Test(dataProvider = "allListOfCampaigns", description = "Print statistics for all campaigns")
    public void testCheckBuilderForAllCampaignsTogether(List<OfferViewList> OfferViewList) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<String> errors = new ArrayList<>();
        sb.append("\n");

        for (OfferViewList c : OfferViewList) {
            try {
                System.out.println(String.format("Check campaign with title '%s' and guid '%s'..", c.getTitle(), c.getOfferViewGuid()));
                CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(c.getOfferViewGuid());
                sb.append("{ \"").append(c.getOfferViewGuid()).append("\", \"").append(c.getTitle()).append("\" },\n");
            } catch (JsonSyntaxException e) {
                errors.add(e.getMessage() + " - " + c.getOfferViewGuid() + " - " + c.getTitle());
            }
        }
        int total = OfferViewList.size();
        int err = errors.size();
        String s = String.format("\nTotal campaigns on server '%s'," +
                        "\nFramework can work with '%s' campaigns," +
                        "\nCampaigns with any error(s) '%s'," +
                        "\nThe percentage of covered campaigns is = '%s %%'",
                total, (total - err), err, ((total - err) * 100 / total));
        System.out.println(s);
        System.out.println(sb.toString());

        Collections.sort(errors);
        for (String e : errors) {
            System.out.println(e);
        }
    }


    @Test(dataProvider = "allListOfCampaigns", description = "Just print all campaigns on server")
    public void testPrintAllCampaigns(List<OfferViewList> OfferViewList) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (OfferViewList c : OfferViewList) {
            sb.append(c.getOfferViewGuid()).append(" - ").append(c.getTitle()).append("\n");
        }
        System.out.println(sb.toString());
    }
}