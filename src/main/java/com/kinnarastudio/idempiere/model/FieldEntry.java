package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public final class FieldEntry {
    private final String column;
    private final Object value;

    public FieldEntry(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("@column", column);
        json.put("val", value);
        return json;
    }
}
