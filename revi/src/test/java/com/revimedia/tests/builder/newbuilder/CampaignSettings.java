package com.revimedia.tests.builder.newbuilder;

import com.revimedia.testing.json2pojo.field.FieldsBean;
import com.revimedia.testing.json2pojo.settings.SettingsBean;
import com.revimedia.testing.json2pojo.step.StepsBean;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class CampaignSettings {

    private String guid;
    private SettingsBean settingsBean;
    private FieldsBean fieldsBean;
    private StepsBean stepsBean;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public SettingsBean getSettingsBean() {
        return settingsBean;
    }

    public void setSettingsBean(SettingsBean settingsBean) {
        this.settingsBean = settingsBean;
    }

    public FieldsBean getFieldsBean() {
        return fieldsBean;
    }

    public void setFieldsBean(FieldsBean fieldsBean) {
        this.fieldsBean = fieldsBean;
    }

    public StepsBean getStepsBean() {
        return stepsBean;
    }

    public void setStepsBean(StepsBean stepsBean) {
        this.stepsBean = stepsBean;
    }
}
