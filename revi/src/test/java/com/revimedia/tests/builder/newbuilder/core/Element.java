package com.revimedia.tests.builder.newbuilder.core;

//import org.codehaus.jackson.map.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.revimedia.testing.json2pojo.field.Field;

/**
 * Created by Funker on 09.02.2015.
 */
public class Element {

    private String type;
    private String name;
    private Object sets;
    private String value;
    private String title;
    private boolean hidden;


    public Element(Field field) {
        this.type = field.getType();
        this.name = field.getName();
        this.title = field.getTitle().toString();
        this.value = field.getValue();
        this.hidden = field.getHidden();
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

    public boolean isHidden() {
        return hidden;
    }

    public String getTitle() {
        return title;
    }
}
