package com.revimedia.tests.builder.newbuilder.dto;

import com.revimedia.testing.json2pojo.field.Composite;
import com.revimedia.testing.json2pojo.field.Field;

import java.util.List;

/**
 * Created by Funker on 09.02.2015.
 */
public class Element {

    private String type;
    private String name;
    private Object sets;
    private List<Composite> composite;
    private String displayedText;
    private String value;
    private String title;
    private boolean hidden;


    public Element(Field field) {
        this.type = field.getType();
        this.name = field.getName();
        this.title = field.getTitle().toString();
        this.hidden = field.getHidden();
        this.value = field.getValue();
        this.composite = field.getCollection();
        this.sets = field.getSets();
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getSets() {
        return sets;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayedText() {
        return displayedText;
    }

    public void setDisplayedText(String displayedText) {
        this.displayedText = displayedText;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getTitle() {
        return title;
    }

    public List<Composite> getComposite() {
        return composite;
    }
}
