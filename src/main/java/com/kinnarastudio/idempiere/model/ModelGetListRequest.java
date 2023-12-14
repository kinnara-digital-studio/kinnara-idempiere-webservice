package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelGetListRequest extends ModelOrientedRequest<ModelGetList> {
    public final static String JSON_KEY = "ModelGetListRequest";
    public ModelGetListRequest(@Nonnull LoginRequest loginRequest, @Nonnull ModelGetList modelRequest) {
        super(loginRequest, modelRequest);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(getLoginRequest().getJsonKey(), getLoginRequest().toJson());
        json.put(getModelRequest().getJsonKey(), getModelRequest().toJson());
        return json;
    }

    @Override
    public String getJsonKey() {
        return JSON_KEY;
    }
}
