package jsonTests.gsonResponse;

import com.google.gson.*;


import java.lang.reflect.Type;

/**
 * Created by Funker on 03.05.14.
 */
public class ResponseDeserializer implements JsonDeserializer<Response> {

    @Override
    public Response deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final Response response = new Response();

        final String _success = jsonObject.get("_success").getAsString();
        final Boolean Success = jsonObject.get("Success").getAsBoolean();
        final Boolean IsWarning = jsonObject.get("IsWarning").getAsBoolean();

        JsonElement id = jsonObject.get("TransactionId");

        if (jsonObject.get("TransactionId") != null & !jsonObject.get("TransactionId").equals("null")) {
            final String transactionId = jsonObject.get("TransactionId").getAsString();
            response.setTransactionId(transactionId);
        }


        JsonElement errorsJson = jsonObject.get("Errors");

        Errors[] errors = context.deserialize(errorsJson, Errors[].class);


        response.set_success(_success);
        response.setSuccess(Success);
        response.setWarning(IsWarning);
        response.setERRORS(errors);

        return response;
    }
}
