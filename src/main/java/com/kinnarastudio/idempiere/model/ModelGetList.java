package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelGetList extends Model {
    public final static String JSON_KEY = "ModelGetList";

    public ModelGetList(@Nonnull String serviceType, String tableName) {
        super(serviceType, tableName, null, null);
    }

    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", getServiceType());
        json.put("TableName", getTableName());
        return json;
    }

    @Override
    protected String getJsonKey() {
        return JSON_KEY;
    }
}
