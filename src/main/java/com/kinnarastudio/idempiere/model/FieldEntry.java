package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public final class FieldEntry {
    private final String column;
    private final String value;

    public FieldEntry(String column, String value) {
        this.column = column;
        this.value = value;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("@column", column);
        json.put("val", value);
        return json;
    }
}
