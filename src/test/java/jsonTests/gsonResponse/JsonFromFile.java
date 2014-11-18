package jsonTests.gsonResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Denis Stoianov on 04/07/2014, 1:17 PM
 * E-mail: denis@revimedia.com
 */
public class JsonFromFile {

    String file = ".\\src\\test\\java\\jsonTests\\gsonResponse\\Bq.Config.copy.json";
    //String file = ".\\src\\test\\java\\jsonTests\\gsonResponse\\Bq.Config.copy.json";

    @Test
    public void testName2() throws Exception {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(file));
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Map<String, Object>> jsonMap = gson.fromJson(br, type);

        Map<String, Object> disclaimerText = jsonMap.get("disclaimerText");
        Map<String, Object> homesecurity = (Map<String, Object>) disclaimerText.get("homesecurity");

        String tcpa = (String) homesecurity.get("tcpa");
        String bestq = (String) homesecurity.get("bestq");

    }

    @Test
    public void testName() throws Exception {
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            Map<String, Map<String, Object>> hashMap = gson.fromJson(br, HashMap.class);
            Map<String, Object> disclaimerText = hashMap.get("disclaimerText");
            Map<String, Object> homesecurity = (Map<String, Object>) disclaimerText.get("homesecurity");

            String tcpa = (String) homesecurity.get("tcpa");
            String bestq = (String) homesecurity.get("bestq");

            System.out.println(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
