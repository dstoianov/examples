package com.revimedia.tests.builder;

import com.crunchbase.json2pojo.jsonschema.OfferSchema;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.revimedia.tests.builder.newbuilder.HTTPHelper;
import org.testng.annotations.Test;

/**
 * Created by Funker on 09.07.2015.
 */
public class NewBuilderTest {

    String json = "{\"Result\": \"BaeOK\",\"TotalCount\": \"33\",\"OfferViewList\": [{\"Title\": \"test1212\",\"OfferViewGuid\": \"70844C03-E2D9-4AB5-8512-20094E4DB3B9\"},{\"Title\": \"andrey\",\"OfferViewGuid\": \"60B3F385-10D6-400D-9D04-90E96AAE4C03\"},{\"Title\": \"a2\",\"OfferViewGuid\": \"DB8CA6C9-68E8-4925-A9E5-73AF1D7906FC\"}]}";


    @Test
    public void testName() throws Exception {

        HTTPHelper http = new HTTPHelper();
        String url = "http://development.stagingrevi.com/api/OfferViews/";

        String jsonString = http.doGet(url);

        OfferSchema offer = new Gson().fromJson(jsonString, new TypeToken<OfferSchema>() {
        }.getType());


    }
}
