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
//                List<String> sets = jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('sets');",e));
                String type = (String) jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('type');", e));
                String value = (String) jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('value');", e));
                String name = (String) jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('name');", e));
                String title = (String) jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('title');", e));
                boolean hidden = (boolean) jsHelper.exec(String.format("return Bq.App.fields.getFields().get('%s').get('hidden');", e));
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
