package com.revimedia.tests.builder.javascript;

import com.revimedia.tests.builder.Element;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 13.02.2015.
 */
public class BuilderCampaign {

    protected WebDriver driver;
    protected JSHelper jsHelper;
    protected List<Page> campaign = new ArrayList<Page>();

    public BuilderCampaign(WebDriver driver) {
        this.driver = driver;
        this.jsHelper = new JSHelper(driver);
    }

    public void buildAllPages() throws Exception {
        int stepsCount = jsHelper.getStepsCount();
        for (int i = 1; i <= stepsCount - 1; ++i) {
            List<String> fieldsOnPage = jsHelper.getFieldsOnPage(i);
            Page page = new Page(driver, fieldsOnPage);
            page.build();
            campaign.add(page);
        }
    }

    public List<Page> getCampaign() {
        return campaign;
    }

    public void fillInAllPages(Map<String, String> map) {
        for (Page p : campaign) {
            List<Element> elements = p.getElements();
            for (Element e : elements) {
                ElementHelper.set(driver, e, "dd");
            }
        }
    }
}
