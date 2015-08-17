package com.revimedia.tests.builder.newbuilder.test;


import com.revimedia.testing.configuration.listeners.WebDriverScreenshotListener;
import com.revimedia.tests.builder.newbuilder.core.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.BeanHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Funker on 09.07.2015.
 */
@Listeners({WebDriverScreenshotListener.class})
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


    @Test(dataProvider = "Campaigns", invocationCount = 5, dataProviderClass = Data.class)
    public void testMainSubmit(String guid, String title, Map<String, String> contact) throws Exception {

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);
        CampaignBuilder campaign = new CampaignBuilder(driver, campaignSettings);

        campaign.build();
        campaign.setDataForAllElements(contact);
        campaign.verifyCampaignData();
        campaign.checkDependencyElements();
        campaign.open();
        campaign.fillInAllPages();

    }

}
