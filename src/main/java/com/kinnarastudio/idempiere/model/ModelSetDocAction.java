package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelSetDocAction extends Model {
    public final static String JSON_KEY = "ModelSetDocAction";

    private final String docAction;

    public ModelSetDocAction(@Nonnull String serviceType, String tableName, Integer recordId, String recordIdVariable, @Nonnull String docAction) {
        super(serviceType, tableName, recordId, recordIdVariable);
        this.docAction = docAction;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", getServiceType());
        json.put("recordID", getRecordId());
        json.put("docAction", docAction);
        return json;
    }

    @Override
    protected String getJsonKey() {
        return JSON_KEY;
    }
}
