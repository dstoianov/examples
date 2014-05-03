package com.revimedia.testing.configuration.response;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by Funker on 03.05.14.
 */
public class ErrorsDeserializer implements JsonDeserializer {

    @Override
    public Errors deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final Errors errors = new Errors();
        errors.setReason(jsonObject.get("Reason").getAsString());
        errors.setParam(jsonObject.get("Param").getAsString());
        errors.setExtraInfo(jsonObject.get("ExtraInfo").getAsString());
        return errors;
    }
}