package com.revimedia.tests.builder.newbuilder.test;

import com.google.gson.JsonSyntaxException;
import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.tests.builder.newbuilder.core.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Funker on 11.07.2015.
 */
public class NewBuilderApiTest extends Data {

    @Test(dataProvider = "Campaigns", invocationCount = 1)
    public void testCheckBuilderForCampaignBad(String guid, String title, Map<String, String> contact) throws Exception {
        System.out.println(String.format("Check campaign with guid '%s'..", guid));
        WebDriver driver = mockDriver();

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);
        CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);
        campaign.build();
        campaign.setDataForAllElements(contact);
        campaign.verifyCampaignData();
        campaign.checkDependencyElements();
    }

    @Test(dataProvider = "allListOfCampaigns", description = "Print statistics for all campaigns")
    public void testCheckBuilderForAllCampaignsTogether(List<OfferViewList> OfferViewList) throws Exception {
        WebDriver driver = mockDriver();
        StringBuilder sb = new StringBuilder();
        List<String> errors = new ArrayList<>();
        sb.append("\n");

        for (OfferViewList c : OfferViewList) {
            try {
                System.out.println(String.format("Check campaign with title '%s' and guid '%s'..", c.getTitle(), c.getOfferViewGuid()));
                CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(c.getOfferViewGuid());
                CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);
                campaign.build();
                sb.append("{ \"").append(c.getOfferViewGuid()).append("\", \"").append(c.getTitle()).append("\", map },\n");
            } catch (JsonSyntaxException e) {
                errors.add(e.getMessage() + " - " + c.getOfferViewGuid() + " - " + c.getTitle());
            }
        }
        int total = OfferViewList.size();
        int err = errors.size();
        String s = String.format("\nTotal campaigns on server '%s'," +
                        "\nFramework can work with '%s' campaigns, or '%s%%'" +
                        "\nCampaigns with any error(s) '%s'",
                total, (total - err), ((total - err) * 100 / total), err);
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

    public static WebDriver mockDriver() {
        WebDriver driver = mock(ChromeDriver.class);
        WebDriver.Options webDriverOptionsMock = mock(WebDriver.Options.class);
        WebDriver.Timeouts timeoutsMock = mock(WebDriver.Timeouts.class);
        when(driver.manage()).thenReturn(webDriverOptionsMock);
        when(driver.manage().timeouts()).thenReturn(timeoutsMock);
        return driver;
    }
}