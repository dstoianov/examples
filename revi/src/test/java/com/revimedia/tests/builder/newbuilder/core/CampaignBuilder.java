package com.revimedia.tests.builder.newbuilder.core;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.testing.json2pojo.step.Step;
import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.dto.Element;
import com.revimedia.tests.builder.newbuilder.helper.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class CampaignBuilder {

    private static final Logger log = LoggerFactory.getLogger(CampaignBuilder.class);
    protected WebDriver driver;
    protected CampaignSettings settings;
    protected List<Page> campaign = new ArrayList<Page>();
    protected ElementHelper elementHelper;

    public CampaignBuilder(WebDriver driver, CampaignSettings settings) {
        this.driver = driver;
        this.settings = settings;
        this.elementHelper = new ElementHelper(this);
    }

    public CampaignBuilder open() {
        elementHelper.open(settings.getGuid());
        return this;
    }

    public CampaignBuilder build() {
        System.out.println("");
        log.info("Start building campaign '{}'", settings.getGuid());

        for (Step step : settings.getStepsBean().getSteps()) {
            if (step.getContent() != null && step.getContent().getFields().size() != 0) {
                log.info("Build page number '{}'..", step.getStep());

                List<Object> fields = step.getContent().getFields();
                List<String> fieldsOnPage = getFieldsOnPage(fields);

                Page page = new Page(fieldsOnPage, settings.getFieldsBean(), step.getStep());
                page.build();
                campaign.add(page);
            }
        }
        log.info("Finish building campaign..\n");
        return this;
    }

    private List<String> getFieldsOnPage(List<Object> fields) {
        List<String> fieldsOnPage = new ArrayList<String>();
        for (Object o : fields) {
            if (o instanceof String) {
                fieldsOnPage.add(o.toString());
            } else if (o instanceof LinkedTreeMap) {
                @SuppressWarnings("unchecked")
                Object s = ((LinkedTreeMap<String, Object>) o).get("name");
                if (s.toString().equalsIgnoreCase("Cloning")) {
                    fieldsOnPage.add("Year");
                    fieldsOnPage.add("Make");
                    fieldsOnPage.add("Model");
                } else {
                    fieldsOnPage.add(s.toString());
                }
            } else {
                throw new FrameworkException("Unknown instance of object" + o.getClass() + ", To string  " + o.toString());
            }
        }
        log.info("On page present '{}' fields {}", fieldsOnPage.size(), fieldsOnPage.toString());
        return fieldsOnPage;
    }


    public void fillInAllPages(Map<String, String> map) {
        for (Page p : campaign) {
            log.info("Start filling in page '{}'..", p.getStepNumber());
            List<Element> elements = p.getElements();

            for (Element e : elements) {
                elementHelper.set(e, getValue(e.getName(), map));
            }
            elementHelper.nextPage(p.getStepNumber());
        }
        log.info("Submit campaign..");
    }

    private String getValue(String key, Map<String, String> map) {
        String value = map.get(key.toLowerCase());
        if (value != null) {
            return value;
        } else {
            return null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
