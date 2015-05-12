package com.revimedia.testing.configuration.response;

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
        response.set_success(jsonObject.get("_success").getAsString());
        if (!jsonObject.get("TransactionId").isJsonNull()) { // if (!jsonObject.get("TransactionId").toString().equals("null")) {
            response.setTransactionId(jsonObject.get("TransactionId").getAsString());
        }
        Errors[] errors = context.deserialize(jsonObject.get("Errors"), Errors[].class);
        response.setERRORS(errors);
        response.setSuccess(jsonObject.get("Success").getAsBoolean());
        response.setWarning(jsonObject.get("IsWarning").getAsBoolean());
        return response;
    }
}
