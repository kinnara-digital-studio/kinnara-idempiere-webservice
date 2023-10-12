package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public abstract class ModelOrientedRequest {
    private final LoginRequest loginRequest;

    private final Model modelRequest;

    protected ModelOrientedRequest(@Nonnull LoginRequest loginRequest, @Nonnull Model modelRequest) {
        this.loginRequest = loginRequest;
        this.modelRequest = modelRequest;
    }

    public JSONObject getRequestPayload() {
        final JSONObject jsonPayload = new JSONObject();
        jsonPayload.put(getJsonKey(), toJson());
        return jsonPayload;
    }

    public abstract JSONObject toJson();

    public abstract String getJsonKey();

    @Nonnull
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    @Nonnull
    public Model getModelRequest() {
        return modelRequest;
    }
}
