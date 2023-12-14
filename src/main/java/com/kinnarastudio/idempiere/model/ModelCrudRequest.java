package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelCrudRequest extends ModelOrientedRequest<ModelCrud> {
    public final static String JSON_KEY = "ModelCRUDRequest";

    public ModelCrudRequest(@Nonnull LoginRequest loginRequest, @Nonnull ModelCrud modelCrud) {
        super(loginRequest, modelCrud);
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();

        final LoginRequest loginRequest = getLoginRequest();
        json.put(loginRequest.getJsonKey(), loginRequest.toJson());

        final Model model = getModelRequest();
        json.put(model.getJsonKey(), model.toJson());

        return json;
    }

    @Override
    public String getJsonKey() {
        return JSON_KEY;
    }
}
