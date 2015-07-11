package com.revimedia.tests.builder.newbuilder.test;

import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.testng.annotations.DataProvider;

import java.util.List;

/**
 * Created by Funker on 11.07.2015.
 */
public class Data {

    @DataProvider(name = "goodCampaigns")
    public static Object[][] goodNumbers() {
        return new Object[][]{
//                {"70844C03-E2D9-4AB5-8512-20094E4DB3B9"},
//                {"42726873-9B6B-4E56-A75B-D91C675AC659"},
                {"F8CFF49B-7CC3-4EB6-83CD-7AECDDE7150F"}
        };
    }

    @DataProvider(name = "badCampaigns")
    public static Object[][] badNumbers() {
        return new Object[][]{
                {"F8CFF49B-7CC3-4EB6-83CD-7AECDDE7150F"},
                {"60B3F385-10D6-400D-9D04-90E96AAE4C03"},
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
}
