package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if(value.getClass().isArray()) {
            final JSONArray values = Arrays.stream((Object[]) value)
                    .collect(JSONCollectors.toJSONArray());

            if(!values.isEmpty()) {
                final JSONObject json = new JSONObject();
                json.put("@column", column);
                json.put("val", values);
                return json;
            }

        } else {

            if(!String.valueOf(value).isEmpty()) {
                final JSONObject json = new JSONObject();
                json.put("@column", column);
                json.put("val", value);
                return json;
            }
        }

        return null;
    }
}
