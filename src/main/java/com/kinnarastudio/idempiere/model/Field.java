package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public final class Field {
    public final static String JSON_KEY = "field";

    private final FieldEntry[] fieldEntry;

    public Field(FieldEntry[] fieldEntry) {
        this.fieldEntry = fieldEntry;
    }

    public JSONArray toJson() {
        return Optional.ofNullable(this.fieldEntry)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .map(FieldEntry::toJson)
                .collect(JSONCollectors.toJSONArray());
    }
}
