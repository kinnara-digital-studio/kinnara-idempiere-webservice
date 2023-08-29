package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class CompositeRequest extends ModelRequest {
    public final static String JSON_KEY = "CompositeRequest";

    private final String serviceType;

    private final Operation[] operations;

    public CompositeRequest(LoginRequest loginRequest, String serviceType, Operation[] operations) {
        super(loginRequest);
        this.serviceType = serviceType;
        this.operations = operations;
    }

    public JSONObject toJson() {
        final JSONObject json = super.toJson();
        json.put("serviceType", serviceType);

        if (operations != null && operations.length > 0) {
            final JSONObject jsonOperations = new JSONObject();

            final JSONArray jsonOperation = Optional.of(operations)
                    .map(Arrays::stream)
                    .orElseGet(Stream::empty)
                    .map(Operation::toJson)
                    .collect(JSONCollectors.toJSONArray());
            jsonOperations.put("operation", jsonOperation);

            json.put("operations", jsonOperations);
        }

        return json;
    }

    @Override
    public String getJsonKey() {
        return JSON_KEY;
    }
}
