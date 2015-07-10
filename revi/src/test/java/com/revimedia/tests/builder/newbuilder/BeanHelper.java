package com.revimedia.tests.builder.newbuilder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.testing.json2pojo.field.FieldsBean;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.testing.json2pojo.step.StepsBean;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class BeanHelper {

    //    private static Map<String, Object> map = new HashMap<String, Object>();
    private static String url = "http://development.stagingrevi.com/api/";

    public static CampaignSettings getCampaignSettings(String guid) throws Exception {

        HTTPHelper http = new HTTPHelper();
        CampaignSettings settings = new CampaignSettings();

        String settingsJson = http.doGet(url + "Settings/" + guid);
        String fieldsJson = http.doGet(url + "Fields/" + guid);
        String stepsJson = http.doGet(url + "Steps/" + guid);

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
}
