package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public abstract class ModelRequest {

    private final LoginRequest loginRequest;

    protected ModelRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(LoginRequest.JSON_KEY, loginRequest.toJson());
        return json;
    }

    public abstract String getJsonKey();
}
