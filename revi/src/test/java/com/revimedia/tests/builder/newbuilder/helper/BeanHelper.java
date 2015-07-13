package com.revimedia.tests.builder.newbuilder.helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.json2pojo.field.FieldsBean;
import com.revimedia.testing.json2pojo.offer.OfferBean;
import com.revimedia.testing.json2pojo.offer.OfferViewList;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.testing.json2pojo.step.StepsBean;
import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class BeanHelper {

    private static String url = "http://development.stagingrevi.com/api/";
    private static final Logger log = LoggerFactory.getLogger(BeanHelper.class);

    public static CampaignSettings getCampaignSettings(String guid) throws Exception {

        HTTPHelper http = new HTTPHelper();
        CampaignSettings settings = new CampaignSettings();

        String settingsJson = http.doGet(url + "Settings/" + guid);
        String fieldsJson = http.doGet(url + "Fields/" + guid);
        String stepsJson = http.doGet(url + "Steps/" + guid);

/*        String offerViewsListJson = http.doGet(url + "OfferViews/");
        OfferBean offerBean = new Gson().fromJson(offerViewsListJson, new TypeToken<OfferBean>() {
        }.getType());

        for (OfferViewList o : offerBean.getOfferViewList()) {
            if (o.getOfferViewGuid().equalsIgnoreCase(guid)) {
                settings.setTitle(o.getTitle());
                break;
            }
        }*/

        SettingsBean settingsBean = new Gson().fromJson(settingsJson, new TypeToken<SettingsBean>() {
        }.getType());

        FieldsBean fieldsBean = new Gson().fromJson(fieldsJson, new TypeToken<FieldsBean>() {
        }.getType());

        StepsBean stepsBean = new Gson().fromJson(stepsJson, new TypeToken<StepsBean>() {
        }.getType());

        settings.setGuid(guid);
        settings.setFieldsBean(fieldsBean);
        settings.setSettingsBean(settingsBean);
        settings.setStepsBean(stepsBean);

        return settings;
    }

    public static List<OfferViewList> getAvailableCampaignsOnServer() throws Exception {
        HTTPHelper http = new HTTPHelper();
        String offerViewsListJson = http.doGet(url + "OfferViews/");
        OfferBean offerBean = new Gson().fromJson(offerViewsListJson, new TypeToken<OfferBean>() {
        }.getType());
        return offerBean.getOfferViewList();
    }

    public static SettingsBean getSettingsFor(String guid) throws Exception {
        HTTPHelper http = new HTTPHelper();
        String settingsJson = http.doGet(url + "Settings/" + guid);

        if (settingsJson.length() < 35) {
            throw new FrameworkException(String.format("Campaign '%s' is broken or not full,\n\t\t\t\tdue to settings is absent '%s'", guid, settingsJson));
        }

        SettingsBean settingsBean = new Gson().fromJson(settingsJson, new TypeToken<SettingsBean>() {
        }.getType());
        return settingsBean;
    }
}
