package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.Try;
import com.kinnarastudio.commons.jsonstream.JSONStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class DataRow {
    public final static String JSON_KEY = "DataRow";

    private final Field field;

    public DataRow(Field field) {
        this.field = field;
    }

    public DataRow(FieldEntry[] fieldEntries) {
        this(new Field(fieldEntries));
    }

    public DataRow(JSONObject json) {
        final FieldEntry[] fieldEntries = JSONStream.of(json.getJSONArray(Field.JSON_KEY), JSONArray::getJSONObject)
                .filter(Try.toNegate(this::isNilValue))
                .map(j -> {
                    final String column = j.getString("@column");
                    final String value = String.valueOf(j.get("val"));
                    return new FieldEntry(column, value);
                })
                .toArray(FieldEntry[]::new);

        this.field = new Field(fieldEntries);
    }

    public DataRow(JSONArray json) {
        final FieldEntry[] fieldEntries = JSONStream.of(json, JSONArray::getJSONObject)
                .map(j -> j.getJSONArray(Field.JSON_KEY))
                .flatMap(j -> JSONStream.of(j, JSONArray::getJSONObject))
                .filter(Try.toNegate(this::isNilValue))
                .map(j -> {
                    final String column = j.getString("@column");
                    final String value = j.getString("val");
                    return new FieldEntry(column, value);
                })
                .toArray(FieldEntry[]::new);

        this.field = new Field(fieldEntries);
    }

    public Field getField() {
        return field;
    }

    public FieldEntry[] getFieldEntries() {
        return field.getFieldEntries();
    }

    public JSONObject toJson() {
        final JSONObject jsonDataRow = new JSONObject();
        jsonDataRow.put(Field.JSON_KEY, field.toJson());
        return jsonDataRow;
    }

    protected boolean isNilValue(JSONObject jsonField) throws JSONException {
        try {
            return jsonField == null || jsonField.getJSONObject("val").getBoolean("@nil");
        } catch (JSONException e) {

            // consider empty value as NIL
            return jsonField.optString("val").isEmpty();
        }
    }
}
