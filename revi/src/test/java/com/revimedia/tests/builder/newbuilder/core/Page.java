package com.revimedia.tests.builder.newbuilder.core;

import com.revimedia.testing.json2pojo.field.Field;
import com.revimedia.testing.json2pojo.field.FieldsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 09.02.2015.
 */
public class Page {

    private List<String> fieldsOnPage = new ArrayList<>();
    private List<Element> fields = new ArrayList<>();
    private int stepNumber;
    private FieldsBean fieldsBean;

    public Page(List<String> fieldsOnPage, FieldsBean fieldsBean, Integer step) {
        this.fieldsOnPage = fieldsOnPage;
        this.fieldsBean = fieldsBean;
        this.stepNumber = step;
    }

    public void build() {
        for (String e : fieldsOnPage) {
            System.out.println(String.format("Build element '%s'", e));
            Field field = null;
            for (Field f : fieldsBean.getFields()) {
                if (f.getName().equalsIgnoreCase(e)) {
                    field = f;
                    break;
                }
            }
            Element element = new Element(field);
            fields.add(element);
        }
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public List<Element> getElements() {
        return fields;
    }
}
