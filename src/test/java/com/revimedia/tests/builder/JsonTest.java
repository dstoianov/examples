package com.revimedia.tests.builder;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by Funker on 12.02.2015.
 */
public class JsonTest {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();

        HashMap settings = gson.fromJson(readFile("settings.json"), HashMap.class);

//        JSONParser parser = new JSONParser();

        Object fields = gson.fromJson(readFile("fields.json"), Object.class);
        Object steps = gson.fromJson(readFile("steps.json"), Object.class);

        System.out.println(settings);

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
