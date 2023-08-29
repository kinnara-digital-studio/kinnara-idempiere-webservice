package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONException;
import org.json.JSONObject;

public class StandardResponse extends WebServiceResponse {
    private final Integer recordId;
    private final boolean isError;

    private final String errorMessage;

    public StandardResponse(JSONObject responsePayload) throws WebServiceResponseException {
        super(responsePayload);

        try {
            final JSONObject jsonStandardResponse = responsePayload.getJSONObject("StandardResponse");
            isError = jsonStandardResponse.optBoolean("@IsError", false);

            if(isError) {
                errorMessage = jsonStandardResponse.getString("Error");
            } else {
                errorMessage = "";
            }

            recordId = jsonStandardResponse.optInt("@RecordID");
        } catch (JSONException e) {
            throw new WebServiceResponseException(e);
        }
    }

    public Integer getRecordId() {
        return recordId;
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
