package com.revimedia.tests.builder;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Funker on 09.02.2015.
 */
public class Element {

    private String type;
    private String name;
    private List<String> sets;
    private String value;
    private String title;
    private boolean hidden;
    private Object raw;

    public Element(Object raw) {
        this.raw = raw;
        ObjectMapper m = new ObjectMapper();
        HashMap mappedObject = m.convertValue(raw, HashMap.class);
        this.type = (String) mappedObject.get("type");
        this.name = (String) mappedObject.get("name");
        this.title = (String) mappedObject.get("title");
        this.value = (String) mappedObject.get("value");
        this.hidden = (boolean) mappedObject.get("hidden");

        Object sets = mappedObject.get("sets");
        if (!name.equalsIgnoreCase("BirthDate")) {
            this.sets = (List<String>) sets;
        }

    }

    public Element(String type, String name, List<String> sets, String value, String title, boolean hidden) {
        this.type = type;
        this.name = name;
        this.sets = sets;
        this.value = value;
        this.title = title;
        this.hidden = hidden;
        this.raw = null;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<String> getSets() {
        return sets;
    }

    public String getValue() {
        return value;
    }

    public Object getRaw() {
        return raw;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getTitle() {
        return title;
    }
}
