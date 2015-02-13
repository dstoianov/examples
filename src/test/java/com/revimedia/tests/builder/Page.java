package com.revimedia.tests.builder;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 09.02.2015.
 */
public class Page {

    private List<Element> fields = new ArrayList<Element>();
    private int pageNumber;
    private WebDriver driver;
    private List<String> fieldsOnPage;
    private JSHelper jsHelper;


    public Page(WebDriver driver, List<String> fieldsOnPage) {
        this.driver = driver;
        this.fieldsOnPage = fieldsOnPage;
        this.jsHelper = new JSHelper(driver);
    }

    public void build() {
        for (String e : fieldsOnPage) {
            System.out.println(String.format("Build element '%s'", e));
            if (e.equalsIgnoreCase("BirthDate")) {
                fields.add(initElement(e));
            } else if (e.equalsIgnoreCase("Height")) {
                fields.add(initElement("Height_FT"));
            } else {
                Object fieldByName = jsHelper.getFieldByName(e);
                fields.add(new Element(fieldByName));
            }
        }
    }

    private Element initElement(String e) {
        //                List<String> sets = jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('sets');",e));
        String type = (String) jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('type');", e));
        String value = (String) jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('value');", e));
        String name = (String) jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('name');", e));
        String title = (String) jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('title');", e));
        boolean hidden = (boolean) jsHelper.exec(String.format("return Bq.App.fields.getElements().get('%s').get('hidden');", e));

        return new Element(type, name, null, value, title, hidden);
    }


    public List<Element> getElements() {
        return fields;
    }
}
