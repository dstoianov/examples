package com.revimedia.tests.builder.newbuilder.test;


import com.revimedia.tests.builder.newbuilder.core.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 09.07.2015.
 */
public class NewBuilderBrowserTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new PhantomJSDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "GuidProvider")
    public static Object[][] primeNumbers() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("firstname", "Dorian");
        map.put("lastname", "Dummy");
        map.put("gender", "Female");
        map.put("birthdate", "Apr 26, 1982");
        map.put("phonenumber", "5608039491");
        map.put("address", "7500 Dallas Parkway");
        map.put("emailaddress", "sit.amet.massa@consequatenimdiam.ca");
        map.put("zipcode", "75024");
        map.put("city", "PLANO");
        map.put("state", "TX");

        return new Object[][]{
//                {"70844C03-E2D9-4AB5-8512-20094E4DB3B9", map}, // work
//                {"42726873-9B6B-4E56-A75B-D91C675AC659", map},
                {"F8CFF49B-7CC3-4EB6-83CD-7AECDDE7150F", map}
        };
    }

    @Test(dataProvider = "GuidProvider")
    public void testMainSubmit(String guid, Map<String, String> map) throws Exception {

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);
        CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);

        campaign.build();
        campaign.open();
        campaign.fillInAllPages(map);
    }

}
