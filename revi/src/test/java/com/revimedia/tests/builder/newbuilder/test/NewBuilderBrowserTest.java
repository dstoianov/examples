package com.revimedia.tests.builder.newbuilder.test;


import com.revimedia.testing.cds.auto.staticdata.CommonExtraData;
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

        map.put("islivinghere", "No");
        map.put("propertyzipcode", "56565");
        map.put("address1", "Fake adr 63");



        return new Object[][]{
                {"9A610B50-0E9A-46D5-B810-70A405AC3FEA", "health", map},
                {"2D3D1708-6BFC-431B-8B0D-A3B19EA4E849", "home", map},
                {"79482715-7653-4A5E-9936-CD428B298E2C", "solar", map}, //bad radio button
                {"0B630F68-190C-4508-85F1-50964D153744", "mortgage", map},

//                {"88368545-FB77-40BC-AE55-ABD7978271C3", "GloryToUkraine", map},
//                {"DA907EC3-2F37-47E5-996C-FFF79E5724D0", "auto", map},
//                {"EFD9D7ED-3835-4AFA-97D3-73BEDAEDA4AD", "homesecurity", map},
//                {"4D926807-2EDD-4824-B391-5EBE8210EF62", "life", map},
//                {"FCFE964E-AC1F-418A-B8AE-3A61474CC4D4", "medicalalerts", map},
//                {"320493C8-21EC-440C-B8E8-133D1D7E169E", "awdawd", map},
//                {"85E29948-464B-4F17-9C62-AB2C5DE80701", "testAPI", map},

        };
    }

    @Test(dataProvider = "GuidProvider")
    public void testMainSubmit(String guid, String title, Map<String, String> contact) throws Exception {

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);
        CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);

        campaign.build();
        campaign.open();
        campaign.fillInAllPages(contact);
    }

}
