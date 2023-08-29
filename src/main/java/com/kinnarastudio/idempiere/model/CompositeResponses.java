package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.Try;
import com.kinnarastudio.commons.jsonstream.JSONStream;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompositeResponses extends WebServiceResponse {
    public final static String JSON_KEY = "CompositeResponses";

    private final StandardResponse[] responses;

    protected CompositeResponses(JSONObject responsePayload) throws WebServiceResponseException {
        super(responsePayload);

        try {
            final JSONArray jsonStandardResponses = responsePayload
                    .getJSONObject(JSON_KEY)
                    .getJSONObject("CompositeResponse")
                    .getJSONArray(StandardResponse.JSON_KEY);

            responses = JSONStream.of(jsonStandardResponses, JSONArray::getJSONObject)
                    .map(Try.onFunction(StandardResponse::new))
                    .toArray(StandardResponse[]::new);

        } catch (JSONException e) {
            throw new WebServiceResponseException(e);
        }
    }

    public WebServiceResponse[] getResponses() {
        return responses;
    }
}
