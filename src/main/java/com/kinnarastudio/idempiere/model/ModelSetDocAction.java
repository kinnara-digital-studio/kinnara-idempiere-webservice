package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public class ModelSetDocAction {
    public final static String JSON_KEY = "ModelSetDocAction";

    private final String serviceType;
    private final Integer recordId;

    public ModelSetDocAction(String serviceType, Integer recordId) {
        this.serviceType = serviceType;
        this.recordId = recordId;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", serviceType);
        json.put("recordID", recordId);
        return json;
    }
}
