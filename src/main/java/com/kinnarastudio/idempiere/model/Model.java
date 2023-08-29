package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public class Model {

    private final String serviceType;

    private final String tableName;

    private final DataRow dataRow;

    public Model(String serviceType, String tableName, DataRow dataRow) {
        this.serviceType = serviceType;
        this.tableName = tableName;
        this.dataRow = dataRow;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", serviceType);
        json.put("TableName", tableName);
        json.put(DataRow.JSON_KEY, dataRow.toJson());
        return json;
    }
}
