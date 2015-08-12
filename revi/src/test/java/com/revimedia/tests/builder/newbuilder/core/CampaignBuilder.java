package com.revimedia.tests.builder.newbuilder.core;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.testing.json2pojo.step.Step;
import com.revimedia.tests.builder.exception.FrameworkException;
import com.revimedia.tests.builder.newbuilder.dto.CampaignSettings;
import com.revimedia.tests.builder.newbuilder.dto.Element;
import com.revimedia.tests.builder.newbuilder.dto.WebField;
import com.revimedia.tests.builder.newbuilder.helper.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by dstoianov on 2015-07-10.
 */
public class CampaignBuilder {

    private static final Logger log = LoggerFactory.getLogger(CampaignBuilder.class);

    protected WebDriver driver;
    protected CampaignSettings settings;
    protected List<Page> campaign = new ArrayList<Page>();
    protected Map<String, String> fieldValue = new HashMap<String, String>();
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
        log.info("Start building campaign '{}', '{}'", settings.getGuid(), settings.getTitle());

        for (Step step : settings.getStepsBean().getSteps()) {
            if (step.getContent() == null || (step.getContent().getFields() instanceof Boolean)) {
                continue; //skip incorrect data or end (empty) of campaign
            }

            if (step.getContent().getFields() != null) {

                @SuppressWarnings("unchecked")
                List<Object> fields = (ArrayList<Object>) step.getContent().getFields();
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
        List<String> fieldsOnPage = new ArrayList<>();
        for (Object o : fields) {
            if (o instanceof String) {
                fieldsOnPage.add(o.toString());
            } else if (o instanceof LinkedTreeMap) {
                @SuppressWarnings("unchecked")
                Object s = ((LinkedTreeMap<String, Object>) o).get("name");
                if (s.toString().equalsIgnoreCase("Cloning")) {
                    fieldsOnPage.add(WebField.YAEAR.getName());
                    fieldsOnPage.add(WebField.MAKE.getName());
                    fieldsOnPage.add(WebField.MODEL.getName());
                } else if (s.toString().equalsIgnoreCase("Height")) {
                    fieldsOnPage.add(WebField.HEIGHT_FT.getName());
                    fieldsOnPage.add(WebField.HEIGHT_INCH.getName());
                } else {
                    fieldsOnPage.add(s.toString());
                }
            } else {
                String message = String.format("Unknown instance of object '%s', to string '%s'", o.getClass(), o.toString());
                log.error(message);
                throw new FrameworkException(message);
            }
        }
        fieldsOnPage = checkForLogicWithDubFields(fieldsOnPage);
        log.info("On page present '{}' fields {}", fieldsOnPage.size(), fieldsOnPage.toString());
        return fieldsOnPage;
    }

    private List<String> checkForLogicWithDubFields(List<String> fieldsOnPage) {
        for (String f : fieldsOnPage) {
            if (f.equalsIgnoreCase(WebField.ADDRESS1.getName())) {
                fieldsOnPage.remove(WebField.YEARS_AT_RESIDENCE.getName());
                log.info("Remove '{}' field", WebField.YEARS_AT_RESIDENCE);
                break;
            }
        }
        return fieldsOnPage;
    }


    public void fillInAllPages(Map<String, String> contactData) {
        for (Page p : campaign) {
            log.info("Start filling in page '{}'..", p.getStepNumber());
            List<Element> elements = p.getElements();

            for (Element e : elements) {
                String value = getValue(e, contactData);
                elementHelper.set(e, value);
            }
            elementHelper.nextPage(p);
        }
        log.info("Submit campaign..");
        elementHelper.sleep(5000);
    }

    private String getValue(Element e, Map<String, String> contactData) {

        String value = contactData.get(e.getName().toLowerCase());
        String valueFromSet = getValueFromSet(e.getSets());

        if (value != null) {
            return value;
        } else if (valueFromSet != null) {
            return valueFromSet;
        } else {
            throw new FrameworkException(String.format("No data(value, text, sets) for element '%s'", e.getName()));
        }
    }

    public WebDriver getDriver() {
        return driver;
    }


    private String getValueFromSet(Object sets) {
        if (sets != null) {
            if (sets instanceof ArrayList) {
                Object o = ((ArrayList) sets).get(new Random().nextInt(((ArrayList) sets).size()));
                if (o instanceof String) {
                    return o.toString();
                } else if (o instanceof LinkedTreeMap) {
                    Object label = ((LinkedTreeMap) o).get("label");
                    if (label == null) {
                        return null;
                    }
                    return label.toString().replace("вЂ“", "–"); //home campaign, correct value for Approx. Sq. Footage
                } else if (o instanceof Double) {
                    Integer s = ((Double) o).intValue();
                    return s.toString();
                } else {
                    System.out.println("Unknown type of sets " + sets.getClass() + " " + sets.toString());
                }
            } else if (sets instanceof LinkedTreeMap) {
                //TODO: need to think about it how to handle this
                return null;
            } else {
                System.out.println("Unknown type of sets " + sets.getClass() + " " + sets.toString());
            }
        }
        return null;
    }

    public void verifyDataForAllElements(Map<String, String> contact) {
        for (Page p : campaign) {
            for (Element e : p.getElements()) {

            }
        }
    }
}
