package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONObject;

public abstract class WebServiceResponse {
    private final JSONObject responsePayload;

    protected WebServiceResponse(JSONObject responsePayload) {
        this.responsePayload = responsePayload;
    }

    public JSONObject getResponsePayload() {
        return responsePayload;
    }

    @Override
    public String toString() {
        return responsePayload.toString();
    }


    /**
     * Builder class for {@link WebServiceResponse}
     */
    public final static class Builder {
        private JSONObject responsePayload;

        public Builder setResponsePayload(JSONObject responsePayload) {
            this.responsePayload = responsePayload;
            return this;
        }

        public WebServiceResponse build() throws WebServiceResponseException {
            if (responsePayload == null) throw new WebServiceResponseException("Response payload not found");

            if (responsePayload.has(WindowTabData.JSON_KEY)) {
                return new WindowTabData(responsePayload.getJSONObject(WindowTabData.JSON_KEY));
            } else if (responsePayload.has(StandardResponse.JSON_KEY)) {
                return new StandardResponse(responsePayload.getJSONObject(StandardResponse.JSON_KEY));
            } else if (responsePayload.has(CompositeResponses.JSON_KEY)) {
                return new CompositeResponses(responsePayload.getJSONObject(CompositeResponses.JSON_KEY));
            } else {
                throw new WebServiceResponseException("Unrecognized response payload");
            }
        }
    }
}
