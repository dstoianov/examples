package com.revimedia.tests.builder.json;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 12.02.2015.
 */
public class JsonTest {

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();

        HashMap settings = gson.fromJson(readFile("settings.json"), HashMap.class);
        Object fields = gson.fromJson(readFile("fields.json"), Object.class);
        Object steps = gson.fromJson(readFile("steps.json"), Object.class);

//        Object fieldObject = getFieldByName(fields, "Gender");
//        Element element = new Element(fieldObject);

        List<List<String>> fieldsFromSteps = getFieldsFromSteps(steps);
        JsonPage jsonPage = new JsonPage(fields, fieldsFromSteps.get(2));
        jsonPage.build();

        createPage(fieldsFromSteps.get(0));

    }

    private static void createPage(List<String> strings) {

    }

    private static Object getFieldByName(Object fields, String value) {
        for (Object o : (ArrayList<Object>) fields) {
            Object name = ((LinkedTreeMap<String, Object>) o).get("name");
            if (name.toString().equalsIgnoreCase(value)) {
                return o;
            }
        }
        throw new Error(String.format("There is no such field name '%s' in the list", value));
    }

    private static List<List<String>> getFieldsFromSteps(Object steps) throws Exception {
        List<Object> ll = (ArrayList<Object>) steps;
        List<List<String>> pages = new ArrayList<List<String>>();
        for (int i = 0; i < ll.size() - 1; ++i) {
            Map<String, LinkedTreeMap> o = (LinkedTreeMap<String, LinkedTreeMap>) ll.get(i);
            List<Object> fields = (ArrayList<Object>) o.get("content").get("fields");
            pages.add(getFieldsOnPage(fields));
        }
        return pages;
    }


    public static List<String> getFieldsOnPage(List<Object> fields) throws Exception {
        List<String> result = new ArrayList<String>();
        for (Object o : fields) {
            if (o instanceof String) {
                result.add(o.toString());
            } else if (o instanceof Map) {
                String name = BeanUtils.getProperty(o, "name");
                result.add(name);
            } else {
                throw new Exception("Unknown instanceof of Object " + o.getClass());
            }
        }
        System.out.println(String.format("On page '#%s' present '%s' fields %s", 1, result.size(), result.toString()));
        return result;
    }

    private static BufferedReader readFile(String s) throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/test/java/com/revimedia/tests/builder/json/" + s);
        BufferedReader br = new BufferedReader(fileReader);
        return br;
    }


    static Object jsonParser(String jsonStr, String key) throws JSONException {
        int i = 0;
        Object temp = null;
        Object json = new JSONObject(jsonStr);
        String[] keys = key.split("[.]");
        while (i < keys.length) {

            if (json instanceof JSONArray) {
                int index = Integer.parseInt(keys[i]);
                temp = ((JSONArray) json).get(index);
            } else if (json instanceof JSONObject) {
                temp = ((JSONObject) json).get(keys[i]);
            }
            json = temp;
            i++;
        }
        return temp;
    }


}
