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
                {"70844C03-E2D9-4AB5-8512-20094E4DB3B9"}, //Worked old campaign
                {"42726873-9B6B-4E56-A75B-D91C675AC659"},
                {"BE7F0F54-0BF9-41D3-BA06-E4ED65700703"},
                {"9C9B9B8F-C357-4336-A09A-117D38EC9F76"}, //??
                {"5F54C23D-01A2-4F92-A62E-4603D5C9C39F"},
                {"DD37D5DA-FE78-4815-B99F-06551E3E62D0"}, //??
                {"40C24D19-96B8-44B3-AFCB-7D8A8B1293AD"},
                {"22B49D07-28F9-4946-A9C1-097BDD55D236"}, //??
                {"B18FC762-C029-49C4-BF91-E1E96A47FEA1"},
                {"853B8ED3-F0ED-44FD-ABE5-E05FC2003FF4"},
                {"80081DF2-FE73-40B0-AA27-7BC908B1F804"},
                {"74ECE745-DF89-485D-B267-C719EAC82838"},
                {"4EAF6FAB-4702-402E-B484-051B87D63F76"},
                {"F2DB07BE-7647-4157-AFF8-0F5356EC4DE6"},
                {"EB1BC971-F054-493E-9E62-24F109CF8A29"},
                {"7036F46C-29D2-426B-91C5-82F869535633"},
                {"56242AC4-447D-4898-BA61-53F343243AD7"},
                {"EFED9305-6853-4D71-99AA-4D351E472497"},
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

    @DataProvider(name = "allListOfCampaigns")
    public static Object[][] allListOfCampaigns() throws Exception {
        List<OfferViewList> campaigns = BeanHelper.getAvailableCampaignsOnServer();
        System.out.println(String.format("\nServer has '%s' campaigns", campaigns.size()));
        return new Object[][]{
                {campaigns}
        };
    }
}
