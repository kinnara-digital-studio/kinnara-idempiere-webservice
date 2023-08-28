package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelCrudRequest {
    private final LoginRequest loginRequest;
    private final ModelCrud modelCrud;

    public ModelCrudRequest(@Nonnull LoginRequest loginRequest, @Nonnull ModelCrud modelCrud) {
        this.loginRequest = loginRequest;
        this.modelCrud = modelCrud;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(LoginRequest.JSON_KEY, loginRequest.toJson());
        json.put(ModelCrud.JSON_KEY, modelCrud.toJson());
        return json;
    }
}
