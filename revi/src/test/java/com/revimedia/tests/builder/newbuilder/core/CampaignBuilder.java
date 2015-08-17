package com.revimedia.tests.builder.newbuilder.core;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.testing.json2pojo.field.Composite;
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

    private WebDriver driver;
    private CampaignSettings settings;
    private List<Page> campaign = new ArrayList<>();
    private ElementHelper elementHelper;


    public CampaignBuilder(WebDriver driver, CampaignSettings settings) {
        this.driver = driver;
        this.settings = settings;
        String title = settings.getSettingsBean().getSettings().getVertical() + "/" + settings.getSettingsBean().getSettings().getCampaign();
        this.settings.setTitle(title);
        this.elementHelper = new ElementHelper(this);
    }

    public CampaignBuilder open() {
        elementHelper.open(settings.getGuid());
        return this;
    }

    public CampaignBuilder build() {
        System.out.println("");
//        campaign = new ArrayList<Page>();
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
        log.info("On page present '{}' fields {}", fieldsOnPage.size(), fieldsOnPage.toString());
        return fieldsOnPage;
    }

    public void checkDependencyElements() {
        log.info("Check dependency between elements..");
        List<String> elementForRemove = new ArrayList<>();
        for (Page p : campaign) {
            for (Element e : p.getElements()) {

                /**
                 * Logic for Element Is Living Here (Yes/No)
                 */
                if (e.getName().equals(WebField.IS_LIVING_HERE.getName())) {

                    if (e.getDisplayedText().equals("No")) {
                        elementForRemove.add(WebField.YEARS_AT_RESIDENCE.getName());
                    } else {
                        elementForRemove.add(WebField.PROPERTY_ZIP_CODE.getName());
                        elementForRemove.add(WebField.ADDRESS1.getName());
                    }
                }

                if (e.getName().equals(WebField.EXPIRATION_DATE.getName())) {
                    e.getComposite().get(1).setHidden(true);
                }

                /**
                 * Logic for Element Own Rented (Own/Rented) (e.g. solar/mf campaign)
                 */
                if (e.getName().equals(WebField.OWN_RENTED.getName())) {

                    if (e.getDisplayedText().equals("Own")) {
                        elementForRemove.add(WebField.AUTHORIZED_FOR_PROPERTY_CHANGES.getName());
                    }
                }

                /**
                 * Logic for Element InsuranceCompany, if InsuranceCompany = Currently not insured
                 * it has to be removed element InsuredSince
                 */
                if (e.getName().equals(WebField.INSURANCE_COMPANY.getName())) {

                    if (e.getDisplayedText().equals("Currently not insured")) {
                        elementForRemove.add(WebField.INSURED_SINCE.getName());
                    }
                }

                /**
                 * Logic for Element Has System, if HasSystem = No
                 * it has to be removed element CurrentSecuritySystemCompany
                 */
                if (e.getName().equals(WebField.HAS_SYSTEM.getName())) {

                    if (e.getDisplayedText().equals("No")) {
                        elementForRemove.add(WebField.CURRENT_SECURITY_SYSTEM_COMPANY.getName());
                    }
                }


            }
        }
        if (elementForRemove.size() != 0) {
            removeElementsFromCampaign(elementForRemove);
        }
        log.info("Dependency between elements have been checked..");
    }

    private void removeElementsFromCampaign(List<String> elements) {
        log.info("Removing '{}' element(s) '{}'..", elements.size(), elements);
        for (String elementName : elements) {
            for (Page p : campaign) {
                for (Element e : p.getElements()) {
                    if (e.getName().equalsIgnoreCase(elementName)) {
                        log.info("Remove element '{}'", elementName);
                        p.getElements().remove(e);
                        break; // prevent java.util.ConcurrentModificationException
                    }
                }
            }
        }
    }

    public void fillInAllPages() {
        for (Page p : campaign) {
            log.info("Start filling in page '{}'..", p.getStepNumber());
            for (Element e : p.getElements()) {
                elementHelper.set(e);
            }
            elementHelper.nextPage(p);
        }
        log.info("Submit campaign..");
        elementHelper.sleep(5000);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDataForAllElements(Map<String, String> contact) {
        Map<String, String> dataSet = null;
        log.info("Start collect and set data for campaign '{}'", settings.getTitle());

        setContactData(contact);

        for (Page p : campaign) {
            log.info("Start collect data for page '{}'", p.getStepNumber());
            for (Element e : p.getElements()) {
//                if (e.getName().equalsIgnoreCase("ExpirationDate")) {
//                    String s = "";
//                }
                if (e.getDisplayedText() != null) {
                    continue;
                }

                if (e.getType().equals("composite")) {
                    for (Composite c : e.getComposite()) {
                        dataSet = getKeyAndValueFromSet(c.getSets());

                        log.info("\tset text '{}' and value '{}' for element '{} - {}'", dataSet.get("text"), dataSet.get("value"), e.getName(), c.getName());

                        c.setDisplayedText(dataSet.get("text"));
                        c.setValue(dataSet.get("value"));
                    }
                } else if (e.getSets() != null) {
                    dataSet = getKeyAndValueFromSet(e.getSets());
                    String msg = String.format("set text '%s' and value '%s' to element '%s'", dataSet.get("text"), dataSet.get("value"), e.getName());
                    log.info(msg);
                    e.setValue(dataSet.get("value"));
                    e.setDisplayedText(dataSet.get("text"));
                } else {
                    throw new FrameworkException(String.format("No data (value or text from set) for element '%s'", e.getName()));
                }
            }
        }
    }

    private void setContactData(Map<String, String> contact) {
        log.info("Set 'Contact' data for campaign..");
        int i = 0;
        for (Page p : campaign) {
            for (Element e : p.getElements()) {
                String valueContact = contact.get(e.getName().toLowerCase());
                if (valueContact != null) {
                    e.setDisplayedText(valueContact);
                    i++;
                }
            }
        }
        log.info("Data 'Contact' for campaign has been set... was set '{}' elements", i);
    }


    private Map<String, String> getKeyAndValueFromSet(Object sets) {
        Map<String, String> result = new HashMap<>();

        if (sets == null) {
            throw new FrameworkException("Set is equal to null, no data in set");
        }

        if (sets instanceof ArrayList) {
            Object o = ((ArrayList) sets).get(new Random().nextInt(((ArrayList) sets).size()));
            if (o instanceof String) {
                result.put("text", o.toString());
            } else if (o instanceof Double) {
                Integer s = ((Double) o).intValue();
                result.put("text", s.toString());
            } else if (o instanceof LinkedTreeMap) {

                Object text = ((Map) o).get("label");
                Object value = ((Map) o).get("value");

                if (value != null && value instanceof Double) {
                    value = ((Double) value).intValue();
                }

                if (text == null) {
                    return null;
                }

                String textResult = text.toString().replace("вЂ“", "–");//home campaign, correct value for Approx. Sq. Footage
                result.put("text", textResult);
                result.put("value", value.toString());

            } else {
                throw new FrameworkException(String.format("Unknown type of set '%s', '%s'", sets.getClass(), sets.toString()));
            }
        } else {
            throw new FrameworkException(String.format("Unknown instance of set '%s', '%s'", sets.getClass(), sets.toString()));
        }

        return result;
    }

    public void verifyCampaignData() {
        StringBuilder sb = new StringBuilder();
        for (Page p : campaign) {
            log.info("Verify data for page '{}'", p.getStepNumber());
            for (Element e : p.getElements()) {

                if (e.getType().equals("composite") && e.getDisplayedText() == null) {
                    for (Composite c : e.getComposite()) {
                        if (c.getDisplayedText() == null) {
                            sb.append("Element '")
                                    .append(e.getName()).append(" - ").append(c.getName())
                                    .append("' does not have data").append("\n");
                        }
                    }
                }

                if (e.getDisplayedText() == null && !e.getType().equals("composite")) {
                    sb.append("Element '").append(e.getName()).append("' does not have data").append("\n");
                }
            }
        }
        if (sb.toString().length() != 0) {
            String info = String.format("Campaign '%s', '%s'\n", settings.getTitle(), settings.getGuid());
            throw new FrameworkException(info + sb.toString());
        }
    }
}
