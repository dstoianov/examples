package com.revimedia.tests.builder.newbuilder.core;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.testing.json2pojo.step.Step;
import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.helper.ElementHelper;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class CampaignBuilder {

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
        System.out.println("Start building campaign " + settings.getGuid());

        for (Step step : settings.getStepsBean().getSteps()) {
            if (step.getContent() != null && step.getContent().getFields().size() != 0) {
                System.out.println(String.format("\nBuild page number '%s'..", step.getStep()));

                List<Object> fields = step.getContent().getFields();
                List<String> fieldsOnPage = getFieldsOnPage(fields);

                Page page = new Page(fieldsOnPage, settings.getFieldsBean(), step.getStep());
                page.build();
                campaign.add(page);
            }
        }
        System.out.println("\nFinish building campaign..");
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
        System.out.println(String.format("On page present '%s' fields %s", fieldsOnPage.size(), fieldsOnPage.toString()));
        return fieldsOnPage;
    }


    public void fillInAllPages(Map<String, String> map) {
        for (Page p : campaign) {
            System.out.println(String.format("\nStart filling in page '%s'..", p.getStepNumber()));
            List<Element> elements = p.getElements();

            for (Element e : elements) {
                elementHelper.set(e, getValue(e.getName(), map));
            }
            elementHelper.nextPage(p.getStepNumber());
        }
        System.out.println("\nSubmit campaign..");
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
