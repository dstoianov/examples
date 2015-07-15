package com.revimedia.tests.builder.newbuilder.dto;

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
        this.hidden = field.getHidden();
        this.value = field.getValue();
//        this.value = setValue(field.getSets());
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

/*    private String setValue(Object sets) {
        if (sets != null) {
            if (sets instanceof ArrayList) {
                Object o = ((ArrayList) sets).get(new Random().nextInt(((ArrayList) sets).size()));
                if (o instanceof String) {
                    return o.toString();
                } else if (o instanceof LinkedTreeMap) {
                    Object label = ((LinkedTreeMap) o).get("label");
                    if (label == null) {
                        return null;
                    }
                    return label.toString();
                } else {
                    System.out.println("Unknown type of sets " + sets.getClass() + " " + sets.toString());
                }
            } else if (sets instanceof LinkedTreeMap) {
                //TODO: need to think about in how to handle it
                return null;
            } else {
                System.out.println("Unknown type of sets " + sets.getClass() + " " + sets.toString());
            }

        }
        return null;
    }*/


}
