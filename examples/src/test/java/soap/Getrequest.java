package soap;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Getrequest {


    public static String getListUsers() throws IOException {

        String url = "https://api.rest-test-app.com/users/list-users";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Authorization", "Basic key");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String list = response.toString();
        System.out.println(list);
        return list;
    }

    @Test
    @Parameters({"url"})
    public void simpleTestGetListOfUsers(@Optional String url) throws InterruptedException, IOException, JSONException {

        // Create json
        String json = "{\"user1\": {\"name\": \"Jamse\",\"email\": \"main_window@mail.com\",\"age\": 50,}"
                + ",\"user2\": {\"name\": \"Bond\",\"email\":\"aston@mail.com\",\"age\": 49,},"
                + "      \"user3\": {\"name\": \"Weider\"\"email\": \"starwars@mail.com\",\"age\": 500,}}";

        // Get json to api
        String response = getListUsers();

        // Compare result
        Assert.assertTrue(response.replace(" ", "").equals(json.replace(" ", "")));
        System.out.println("DEBUG1:" + response);

    }

}
