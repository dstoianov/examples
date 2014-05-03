package com.revimedia.tests.cds.auto.mfs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revimedia.testing.configuration.proxy.CDSErrors;
import com.revimedia.testing.configuration.proxy.CDSErrorsDeserializer;
import com.revimedia.testing.configuration.proxy.CDSResponse;
import com.revimedia.testing.configuration.proxy.CDSResponseDeserializer;
import org.testng.annotations.Test;

/**
 * Created by Funker on 02.05.14.
 */
public class DummyTest {

    @Test
    public void testName() throws Exception {
        Gson gson = new Gson();
        //Gson gson = new GsonBuilder().create();
        //String br = response.getContent().getText();
        String success = "{\"_success\":\"BaeOK\",\"TransactionId\":\"99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7\",\"Errors\":[],\"Success\":true,\"IsWarning\":false}";
        String unSuccess = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on Address verification.\",\"Param\":\"\",\"ExtraInfo\":\"The address you provided may be incomplete or invalid, please verify its validity. If the information is correct, press submit to proceed..\"}],\"Success\":false,\"IsWarning\":true}";
        String perError = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on EmailAddress verification.\",\"Param\":\"\",\"ExtraInfo\":\"Email domain invalid.\"}],\"Success\":false,\"IsWarning\":false}";

        CDSResponse response = gson.fromJson(success, CDSResponse.class);
        System.out.println(success);
        System.out.println(response);

        System.out.println("-----------------");

        System.out.println(unSuccess);
        CDSResponse response2 = gson.fromJson(unSuccess, CDSResponse.class);
        System.out.println(response2);

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CDSResponse.class, new CDSResponseDeserializer());
        gsonBuilder.registerTypeAdapter(CDSErrors.class, new CDSErrorsDeserializer());
        final Gson gson2 = gsonBuilder.create();

        final CDSResponse response22 = gson2.fromJson(unSuccess, CDSResponse.class);
        System.out.println(response22);

    }

}
