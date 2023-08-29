package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONException;
import org.json.JSONObject;

public class StandardResponse extends WebServiceResponse {
    public final static String JSON_KEY = "StandardResponse";
    private final Integer recordId;
    private final boolean isError;
    private final String errorMessage;
    private final WindowTabData windowTabData;

    public StandardResponse(JSONObject jsonStandardResponse) throws WebServiceResponseException {
        super(jsonStandardResponse);

        try {
            isError = jsonStandardResponse.optBoolean("@IsError", false);

            if(isError) {
                errorMessage = jsonStandardResponse.getString("Error");
            } else {
                errorMessage = "";
            }

            recordId = jsonStandardResponse.optInt("@RecordID");

            if(jsonStandardResponse.has(WindowTabData.JSON_KEY)) {
                final JSONObject jsonWindowTabData = jsonStandardResponse.getJSONObject(WindowTabData.JSON_KEY);
                windowTabData = new WindowTabData(jsonWindowTabData);
            } else {
                windowTabData = null;
            }
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
