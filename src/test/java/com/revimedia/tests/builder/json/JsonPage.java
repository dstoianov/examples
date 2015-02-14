package com.revimedia.tests.builder.json;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.tests.builder.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Funker on 14.02.2015.
 */
public class JsonPage {

    private List<Element> elements = new ArrayList<Element>();
    private int pageNumber;
    private List<String> fieldsOnPage;
    private Object jsonRaw;


    public JsonPage(Object jsonRaw, List<String> fieldsOnPage) {
        this.fieldsOnPage = fieldsOnPage;
        this.jsonRaw = jsonRaw;
    }

    public void build() {
        for (String e : fieldsOnPage) {
            System.out.println(String.format("Build element '%s'", e));
//            if (e.equalsIgnoreCase("BirthDate")) {
//                elements.add(new Element(e));
//            } else if (e.equalsIgnoreCase("Height")) {
//                elements.add(initElementFull("Height_FT"));
//                elements.add(initElementFull("Height_Inch"));
//            } else {
            Object fieldByName = getFieldByName(e);
            elements.add(new Element(fieldByName));
//            }
        }
    }


    private Object getFieldByName(String value) {
        return getFieldByName(jsonRaw, value);
    }

    private Object getFieldByName(Object fields, String value) {
        for (Object o : (ArrayList<Object>) fields) {
            Object name = ((LinkedTreeMap<String, Object>) o).get("name");
            if (name.toString().equalsIgnoreCase(value)) {
                return o;
            }
        }
        throw new Error(String.format("There is no such field name '%s' in the list", value));
    }

    public List<Element> getElements() {
        return elements;
    }

}
