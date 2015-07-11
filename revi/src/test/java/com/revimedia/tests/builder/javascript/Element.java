package com.revimedia.tests.builder.javascript;

//import org.codehaus.jackson.map.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 09.02.2015.
 */
public class Element {

    private String type;
    private String name;
    private List<Object> sets;
    private String value;
    private String title;
    private boolean hidden;
    private Object raw;

    public Element(Object raw) {
        this.raw = raw;
        Gson gson = new Gson();
        Map<String, Object> mappedObject = gson.fromJson(raw.toString(), HashMap.class);
//        ObjectMapper m = new ObjectMapper();
//        HashMap mappedObject = m.convertValue(raw, HashMap.class);
        this.type = (String) mappedObject.get("type");
        this.name = (String) mappedObject.get("name");
        this.title = (String) mappedObject.get("title");
        this.value = (String) mappedObject.get("value");
        this.hidden = (boolean) mappedObject.get("hidden");
        this.sets = (List<Object>) mappedObject.get("sets");
    }

    public Element(String type, String name, List<Object> sets, String value, String title, boolean hidden) {
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

    public List<Object> getSets() {
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
