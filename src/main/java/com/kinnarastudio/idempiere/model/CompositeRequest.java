package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.jsonstream.JSONCollectors;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class CompositeRequest {
    public final static String JSON_KEY = "CompositeRequest";

    private final LoginRequest loginRequest;

    private final String serviceType;

    private final Operation[] operations;
    public CompositeRequest(@Nonnull LoginRequest loginRequest, String serviceType, Operation[] operations) {
        this.loginRequest = loginRequest;
        this.serviceType = serviceType;
        this.operations = operations;
    }

    @Nonnull
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public Operation[] getOperations() {
        return operations;
    }

    public String getServiceType() {
        return serviceType;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();

        json.put("serviceType", getServiceType());

        final LoginRequest loginRequest = getLoginRequest();
        json.put(loginRequest.getJsonKey(), loginRequest.toJson());

        final Operation[] operations = getOperations();
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

    public JSONObject getRequestPayload() {
        final JSONObject jsonPayload = new JSONObject();
        jsonPayload.put(getJsonKey(), toJson());
        return jsonPayload;
    }

    public String getJsonKey() {
        return JSON_KEY;
    }

}
