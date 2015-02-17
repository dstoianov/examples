package com.revimedia.tests.builder.json;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.tests.builder.Element;
import com.revimedia.tests.builder.javascript.ElementHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 13.02.2015.
 */
public class JsonCampaign {

    protected WebDriver driver;
    protected Object steps;
    protected Object fields;
    protected List<JsonPage> campaign = new ArrayList<JsonPage>();

    public JsonCampaign(WebDriver driver, Object steps, Object fields) {
        this.driver = driver;
        this.steps = steps;
        this.fields = fields;
    }

    public void buildAllPages() throws Exception {
        List<List<String>> fieldsFromSteps = getFieldsFromSteps(steps);
        for (int i = 0; i <= fieldsFromSteps.size() - 1; ++i) {
            List<String> fieldsOnPage = fieldsFromSteps.get(i);
            JsonPage jsonPage = new JsonPage(fields, fieldsOnPage, i + 1);
            jsonPage.build();
            campaign.add(jsonPage);
        }
    }

    public List<JsonPage> getCampaign() {
        return campaign;
    }


    private List<List<String>> getFieldsFromSteps(Object stepsObject) throws Exception {
        List<Object> objectList = (ArrayList<Object>) stepsObject;
        List<List<String>> pages = new ArrayList<List<String>>();
        for (int i = 0; i < objectList.size() - 1; ++i) {
            Map<String, LinkedTreeMap> o = (LinkedTreeMap<String, LinkedTreeMap>) objectList.get(i);
            List<Object> fields = (ArrayList<Object>) o.get("content").get("fields");
            List<String> fieldsOnPage = getFieldsOnPage(fields);
            System.out.println(String.format("On page '#%s' present '%s' fields %s", i + 1, fieldsOnPage.size(), fieldsOnPage.toString()));
            pages.add(fieldsOnPage);
        }
        return pages;
    }


    public List<String> getFieldsOnPage(List<Object> fields) throws Exception {
        List<String> result = new ArrayList<String>();
        for (Object o : fields) {
            if (o instanceof String) {
                result.add(o.toString());
            } else if (o instanceof Map) {
                String name = BeanUtils.getProperty(o, "name");
                String group = BeanUtils.getProperty(o, "group");
                if (group != null && group.equalsIgnoreCase("Vehicle")) {
                    result.add("Year");
                    result.add("Make");
                    result.add("Model");
                } else {
                    result.add(name);
                }
            } else {
                throw new Exception("Unknown instanceof of Object " + o.getClass());
            }
        }
        return result;
    }

    public void fillInAllPages(Map<String, String> contactData) throws Exception {
        for (JsonPage page : campaign) {
            System.out.println(">>>>>>>>>>>>>>>>>> Start filling in page " + page.getPageNumber() + " ---------------------");

            List<Element> elements = page.getElements();

            for (Element element : elements) {
                if (element.getType().matches("select|polk".toLowerCase())) {
                    ElementHelper.setSelectRandom(driver, element);
                } else {

                    String value = contactData.get(element.getName());
                    if (value == null) {
                        throw new Error(String.format("Unknown name '%s' of element, element title '%s'", element.getName(), element.getTitle()));
                    }
                    String type = element.getType().toLowerCase();
                    boolean isInput = type.matches("input|zipUS|name|address|phoneUS|email".toLowerCase());
                    if (isInput) {
                        ElementHelper.setInput(driver, element, value);
                    } else if (type.equalsIgnoreCase("radio")) {
                        ElementHelper.setRadio(driver, element, value);
                    } else if (type.equalsIgnoreCase("composite") && element.getName().equalsIgnoreCase("BirthDate")) {
                        ElementHelper.setBirthDay(driver, element, value);
                    } else {
                        throw new Exception(String.format("Unknown type of element '%s', element name '%s'", type, element.getName()));
                    }
                }
            }
            System.out.println("<<<<<<<<<<<<<<<<<< End filling in page " + page.getPageNumber() + " ---------------------");

            clickNextButton(page.getPageNumber());
        }
    }


    public void clickNextButton(String pageNumber) {
        System.out.println(String.format("Click Next Button on page '%s'", pageNumber));
        String locator = String.format(".bq-step%s .bq-control.bq-type-simple", pageNumber);
        WebElement nextButton = driver.findElement(By.cssSelector(locator));
        nextButton.click();

    }

}
