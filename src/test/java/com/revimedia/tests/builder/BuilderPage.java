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
            Object fieldByName = jsHelper.getFieldByName(e);
            fields.add(new Element(fieldByName));
        }
    }

    public List<Element> getFields() {
        return fields;
    }
}
