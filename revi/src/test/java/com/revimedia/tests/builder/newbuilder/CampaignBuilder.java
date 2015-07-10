package com.revimedia.tests.builder.newbuilder;

import com.revimedia.tests.builder.javascript.JSHelper;
import com.revimedia.tests.builder.javascript.Page;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class CampaignBuilder {

    protected WebDriver driver;
    protected JSHelper jsHelper;
    protected List<Page> campaign = new ArrayList<Page>();

    public CampaignBuilder(WebDriver driver, CampaignSettings campaignSettings) {
        this.driver = driver;
        this.jsHelper = new JSHelper(driver);
    }


}
