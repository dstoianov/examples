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


        return new Object[][]{
//                {"88368545-FB77-40BC-AE55-ABD7978271C3", map},
//                {"9A610B50-0E9A-46D5-B810-70A405AC3FEA", map}
//                {"79482715-7653-4A5E-9936-CD428B298E2C", map}
                {"4D926807-2EDD-4824-B391-5EBE8210EF62", map}
        };
    }

    @Test(dataProvider = "GuidProvider")
    public void testMainSubmit(String guid, Map<String, String> contact) throws Exception {

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);
        CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);

        campaign.build();
        campaign.open();
        campaign.fillInAllPages(contact);
    }

}
