package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public class ModelSetDocActionRequest extends WebServiceRequest {

    private final ModelSetDocAction modelSetDocAction;

    public ModelSetDocActionRequest(LoginRequest loginRequest, ModelSetDocAction modelSetDocAction) {
        super(loginRequest);
        this.modelSetDocAction = modelSetDocAction;
    }

    public JSONObject toJson() {
        final JSONObject json = super.toJson();
        json.put(ModelSetDocAction.JSON_KEY, modelSetDocAction.toJson());
        return json;
    }

    @Override
    public String getJsonKey() {
        return null;
    }
}
