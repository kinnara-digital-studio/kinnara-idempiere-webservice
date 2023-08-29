package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.Try;
import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import com.kinnarastudio.commons.jsonstream.JSONStream;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public final class DataSet {
    public final static String JSON_KEY = "DataSet";
    private final DataRow[] dataRows;

    public DataSet() {
        this.dataRows = new DataRow[0];
    }

    public DataSet(DataRow[] dataRows) {
        this.dataRows = dataRows;
    }

    public DataSet(JSONObject json) {
        dataRows = Optional.of(json)
                .map(j -> {
                    final Object objDataRow = j.get(DataRow.JSON_KEY);
                    if(objDataRow instanceof JSONObject) {
                        final JSONArray wrapper = new JSONArray();
                        wrapper.put(objDataRow);
                        return wrapper;
                    } else if(objDataRow instanceof JSONArray) {
                        return (JSONArray) objDataRow;
                    } else return null;
                })
                .map(j -> JSONStream.of(j, JSONArray::getJSONObject))
                .orElseGet(Stream::empty)
                .map(DataRow::new)
                .toArray(DataRow[]::new);
    }

    public DataRow[] getDataRows() {
        return dataRows;
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
