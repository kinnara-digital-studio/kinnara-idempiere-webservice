package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelSetDocActionRequest extends ModelOrientedRequest<ModelSetDocAction> {
    public final static String JSON_KEY = "ModelSetDocActionRequest";

    public ModelSetDocActionRequest(@Nonnull LoginRequest loginRequest, @Nonnull ModelSetDocAction modelSetDocAction) {
        super(loginRequest, modelSetDocAction);
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(LoginRequest.JSON_KEY, getLoginRequest().toJson());

        final Model modelRequest = getModelRequest();
        json.put(modelRequest.getJsonKey(), modelRequest.toJson());

        return json;
    }

    @Override
    public String getJsonKey() {
        return JSON_KEY;
    }
}
