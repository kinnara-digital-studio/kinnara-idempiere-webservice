package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

public class WebServiceResponse {
    private final JSONObject responsePayload;

    public WebServiceResponse(JSONObject responsePayload) {
        this.responsePayload = responsePayload;
    }

    @Override
    public String toString() {
        return responsePayload.toString();
    }
}
