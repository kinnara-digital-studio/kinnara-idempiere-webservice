package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import java.util.Optional;

public final class ModelCrud {
    public final static String JSON_KEY = "ModelCRUD";

    private final String serviceType;

    private String tableName;

    private Integer recordId;

    private String recordIdVariable;

    private Integer offset;

    private Integer limit;
    private DataRow dataRow;

    public ModelCrud(String serviceType) {
        this.serviceType = serviceType;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", serviceType);

        Optional.ofNullable(tableName).ifPresent(s -> json.put("TableName", s));
        Optional.ofNullable(recordId).ifPresent(s -> json.put("RecordID", s));
        Optional.ofNullable(recordIdVariable).ifPresent(s -> json.put("recordIDVariable", s));
        Optional.ofNullable(offset).ifPresent(i -> json.put("Offset", i));
        Optional.ofNullable(limit).ifPresent(i -> json.put("Limit", i));
        Optional.ofNullable(dataRow).ifPresent(r -> json.put(DataRow.JSON_KEY, r.toJson()));

        return json;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public void setRecordIdVariable(String recordIdVariable) {
        this.recordIdVariable = recordIdVariable;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setDataRow(DataRow dataRow) {
        this.dataRow = dataRow;
    }
}
