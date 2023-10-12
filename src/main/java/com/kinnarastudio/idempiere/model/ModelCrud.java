package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import java.util.Optional;

public final class ModelCrud extends Model {
    public final static String JSON_KEY = "ModelCRUD";

    private final Integer offset;

    private final Integer limit;
    private final DataRow dataRow;

    public ModelCrud(String serviceType, String tableName, Integer recordId, String recordIdVariable, Integer offset, Integer limit, DataRow dataRow) {
        super(serviceType, tableName, recordId, recordIdVariable);
        this.offset = offset;
        this.limit = limit;
        this.dataRow = dataRow;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", getServiceType());

        Optional.ofNullable(getTableName()).ifPresent(s -> json.put("TableName", s));
        Optional.ofNullable(getRecordId()).filter(i -> i > 0).ifPresent(s -> json.put("RecordID", s));
        Optional.ofNullable(getRecordIdVariable()).ifPresent(s -> json.put("recordIDVariable", s));
        Optional.ofNullable(offset).ifPresent(i -> json.put("Offset", i));
        Optional.ofNullable(limit).ifPresent(i -> json.put("Limit", i));
        Optional.ofNullable(dataRow).ifPresent(r -> json.put(DataRow.JSON_KEY, r.toJson()));

        return json;
    }

    @Override
    protected String getJsonKey() {
        return JSON_KEY;
    }
}
