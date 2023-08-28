package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public class ModelSetDocActionRequest {
    private final LoginRequest loginRequest;

    private final ModelSetDocAction modelSetDocAction;

    public ModelSetDocActionRequest(LoginRequest loginRequest, ModelSetDocAction modelSetDocAction) {
        this.loginRequest = loginRequest;
        this.modelSetDocAction = modelSetDocAction;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(LoginRequest.JSON_KEY, loginRequest.toJson());
        json.put(ModelSetDocAction.JSON_KEY, modelSetDocAction.toJson());
        return json;
    }
}
