package com.revimedia.tests.builder;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 09.02.2015.
 */
public class BuilderPage {

    private List<Element> fields = new ArrayList<Element>();
    private int pageNumber;
    private WebDriver driver;
    private List<String> fieldsOnPage;
    private JSHelper jsHelper;


    public BuilderPage(WebDriver driver, List<String> fieldsOnPage) {
        this.driver = driver;
        this.fieldsOnPage = fieldsOnPage;
        this.jsHelper = new JSHelper(driver);
    }

    public void build() {
        for (String e : fieldsOnPage) {
            System.out.println(String.format("Build element '%s'", e));
            if (e.equalsIgnoreCase("BirthDate")) {
//                List<String> sets = jsHelper.exec("return Bq.App.fields.getFields().get('BirthDate').get('sets');");
                String type = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('type');");
                String value = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('value');");
                String name = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('name');");
                String title = (String) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('title');");
                boolean hidden = (boolean) jsHelper.exec("return Bq.App.fields.getFields().get('Education').get('hidden');");
                fields.add(new Element(type, name, null, value, title, hidden));
            } else {
                Object fieldByName = jsHelper.getFieldByName(e);
                fields.add(new Element(fieldByName));
            }
        }
    }

    public List<Element> getFields() {
        return fields;
    }
}
