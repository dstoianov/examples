package com.revimedia.tests.builder;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.json2pojo.step.StepsBean;
import com.revimedia.tests.builder.newbuilder.BeanHelper;
import com.revimedia.tests.builder.newbuilder.CampaignBuilder;
import com.revimedia.tests.builder.newbuilder.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.HTTPHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Funker on 09.07.2015.
 */
public class NewBuilderTest {


    @Test
    public void testName() throws Exception {

        HTTPHelper http = new HTTPHelper();
        String offerViewsList = "http://development.stagingrevi.com/api/OfferViews/";
        String offerViews = "http://development.stagingrevi.com/api/OfferViews/70844C03-E2D9-4AB5-8512-20094E4DB3B9";
        String settings = "http://development.stagingrevi.com/api/Settings/70844C03-E2D9-4AB5-8512-20094E4DB3B9";
        String fields = "http://development.stagingrevi.com/api/Fields/70844C03-E2D9-4AB5-8512-20094E4DB3B9";
        String steps = "http://development.stagingrevi.com/api/Steps/70844C03-E2D9-4AB5-8512-20094E4DB3B9";

//        String offerViewsListJson = http.doGet(offerViewsList);
//        String settingsJson = http.doGet(settings);
//        String fieldsJson = http.doGet(fields);
        String stepsJson = http.doGet(steps);

//        OfferBean offerBean = new Gson().fromJson(offerViewsListJson, new TypeToken<OfferBean>() {
//        }.getType());

//        SettingsBean settingsBean = new Gson().fromJson(settingsJson, new TypeToken<SettingsBean>() {
//        }.getType());

//        FieldsBean fieldsBean = new Gson().fromJson(fieldsJson, new TypeToken<FieldsBean>() {
//        }.getType());

        StepsBean stepsBean = new Gson().fromJson(stepsJson, new TypeToken<StepsBean>() {
        }.getType());

    }

    @DataProvider(name = "GuidProvider")
    public static Object[][] primeNumbers() {
        return new Object[][]{
                {"70844C03-E2D9-4AB5-8512-20094E4DB3B9"}
        };
    }

    @Test(dataProvider = "GuidProvider")
    public void testNameLifeMf(String guid) throws Exception {

        CampaignSettings campaignSettings = BeanHelper.getCampaignSettings(guid);

        CampaignBuilder campaignBuilder = new CampaignBuilder(null, campaignSettings);


//        driver.get("http://development.stagingrevi.com/offer/?ovi=" + guid);

//        jsHelper.waitForAjaxComplete();

//        BuilderCampaign campaign = new BuilderCampaign(driver);

//        campaign.buildAllPages();
//        List<Page> campaignWithInitControls = campaign.getCampaign();
//        campaign.fillInAllPages(map);
    }

}
