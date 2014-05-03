package jsonTests.gsonResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revimedia.testing.configuration.proxy.CDSErrors;
import com.revimedia.testing.configuration.proxy.CDSErrorsDeserializer;
import com.revimedia.testing.configuration.proxy.CDSResponse;
import com.revimedia.testing.configuration.proxy.CDSResponseDeserializer;
import org.testng.annotations.Test;

/**
 * Created by Funker on 03.05.14.
 */
public class ResponseTest {


    @Test
    public void testName() throws Exception {
        String success = "{\"_success\":\"BaeOK\",\"TransactionId\":\"99EAF189-5D2D-4D1F-A2CD-7A993EA8A0F7\",\"Errors\":[],\"Success\":true,\"IsWarning\":false}";
        String unSuccess = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on Address verification.\",\"Param\":\"\",\"ExtraInfo\":\"The address you provided may be incomplete or invalid, please verify its validity. If the information is correct, press submit to proceed..\"}],\"Success\":false,\"IsWarning\":true}";
        String perError = "{\"_success\":\"BaeNOK\",\"TransactionId\":null,\"Errors\":[{\"Reason\":\"Errors on EmailAddress verification.\",\"Param\":\"\",\"ExtraInfo\":\"Email domain invalid.\"}],\"Success\":false,\"IsWarning\":false}";


        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Response.class, new ResponseDeserializer());
        gsonBuilder.registerTypeAdapter(Errors.class, new ErrorsDeserializer());
        final Gson gson = gsonBuilder.create();

        String w = unSuccess;
        final Response response = gson.fromJson(w, Response.class);
        System.out.println(w);
        System.out.println(response);

        System.out.println("----------Back to JSON------------------");

        final String json = gson.toJson(response);
        System.out.println(json);

    }

}
