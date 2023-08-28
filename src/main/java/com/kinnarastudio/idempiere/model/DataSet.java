package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class DataSet {
    final DataRow[] dataRows;

    public DataSet(DataRow[] dataRows) {
        this.dataRows = dataRows;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();

        final JSONArray jsonDataRow = Optional.ofNullable(dataRows)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .map(DataRow::toJson)
                .collect(JSONCollectors.toJSONArray());
        json.put(DataRow.JSON_KEY, jsonDataRow);
        return json;
    }
}
