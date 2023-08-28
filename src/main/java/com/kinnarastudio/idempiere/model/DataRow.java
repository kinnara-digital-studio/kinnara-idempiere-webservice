package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public final class DataRow {
    public final static String JSON_KEY = "DataRow";

    private final Field field;

    public DataRow(Field field) {
        this.field = field;
    }

    public JSONObject toJson() {
        final JSONObject jsonDataRow = new JSONObject();
        jsonDataRow.put(Field.JSON_KEY, field.toJson());
        return jsonDataRow;
    }
}
