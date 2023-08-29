package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public final class Field {
    public final static String JSON_KEY = "field";

    private final FieldEntry[] fieldEntries;

    public Field(FieldEntry[] fieldEntry) {
        this.fieldEntries = fieldEntry;
    }

    public FieldEntry[] getFieldEntries() {
        return fieldEntries;
    }

    public JSONArray toJson() {
        return Optional.ofNullable(this.fieldEntries)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(e -> {
                    final String column = e.getColumn();
                    final String value = e.getValue();
                    return column != null && !column.isEmpty() && value != null && !value.isEmpty();
                })
                .map(FieldEntry::toJson)
                .collect(JSONCollectors.toJSONArray());
    }
}
