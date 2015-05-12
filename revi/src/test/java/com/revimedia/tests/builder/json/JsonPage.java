package com.revimedia.tests.builder.json;

import com.google.gson.internal.LinkedTreeMap;
import com.revimedia.tests.builder.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 14.02.2015.
 */
public class JsonPage {

    private List<Element> elements = new ArrayList<Element>();
    private String pageNumber;
    private List<String> fieldsOnPage;
    private Object jsonRaw;


    public JsonPage(Object jsonRaw, List<String> fieldsOnPage, int i) {
        this.fieldsOnPage = fieldsOnPage;
        this.jsonRaw = jsonRaw;
        this.pageNumber = String.valueOf(i);
    }

    public JsonPage(Object jsonRaw, List<String> fieldsOnPage) {
        this.fieldsOnPage = fieldsOnPage;
        this.jsonRaw = jsonRaw;
        this.pageNumber = "N/A";
    }

    public void build() {
        for (String e : fieldsOnPage) {
            System.out.println(String.format("Build element '%s'", e));
            if (e.equalsIgnoreCase("BirthDate")) {
                elements.add(initCompositElement(e));
            } else if (e.equalsIgnoreCase("ExtraCar")) {
                List<Object> sets = new ArrayList<Object>(Arrays.asList("Yes", "No"));
                Element element = new Element("radio", "AddExtraCar", sets, null, "Add an extra car?", false);
                elements.add(element);
//            } else if (e.equalsIgnoreCase("Cloning")) {
//                elements.add(null);
//                elements.add(initElementFull("Height_FT"));
//                elements.add(initElementFull("Height_Inch"));
            } else {
                elements.add(initElement(e));
            }
        }
        System.out.println(String.format("All elements for page '#%s' are build", pageNumber));
    }


    private Object getFieldByName(String value) {
        return getFieldByName(jsonRaw, value);
    }

    private Element initElement(String e) {
        Object fieldByName = getFieldByName(e);
        return new Element(fieldByName);
    }

    private Element initCompositElement(String e) {
        Map<String, Object> fieldByName = (LinkedTreeMap<String, Object>) getFieldByName(e);
        String type = (String) fieldByName.get("type");
        String name = (String) fieldByName.get("name");
        String value = (String) fieldByName.get("value");
        String title = (String) fieldByName.get("title");
        boolean hidden = (boolean) fieldByName.get("hidden");
        return new Element(type, name, null, value, title, hidden);
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

    public String getPageNumber() {
        return pageNumber;
    }
}
