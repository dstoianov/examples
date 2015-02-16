package com.revimedia.tests.builder.json;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Funker on 12.02.2015.
 */
public class JsonTest {

    public static void main(String[] args) throws Exception {
        Stopwatch timer = Stopwatch.createStarted();

        Gson gson = new Gson();
        WebDriver driver = null;

        HashMap settings = gson.fromJson(readFile("settings.json"), HashMap.class);
        Object fields = gson.fromJson(readFile("fields.json"), Object.class);
        Object steps = gson.fromJson(readFile("steps.json"), Object.class);

        JsonCampaign campaign = new JsonCampaign(driver, steps, fields);
        campaign.buildAllPages();

        List<JsonPage> campaign1 = campaign.getCampaign();
        campaign.fillInAllPages(getContactData());
        System.out.println("Campaign build took: " + timer.stop());

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

    private static Map<String, String> getContactData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Dorian");
        map.put("LastName", "Dummy");
        map.put("Gender", "Female");
        map.put("BirthDate", "Apr 26, 1982");
        map.put("PhoneNumber", "5608039491");
        map.put("Address", "7500 Dallas Parkway");
        map.put("EmailAddress", "sit.amet.massa@consequatenimdiam.ca");
        map.put("ZipCode", "75024");
        map.put("City", "PLANO");
        map.put("State", "TX");
        return map;
    }

}
