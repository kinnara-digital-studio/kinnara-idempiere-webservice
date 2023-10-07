package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.commons.Try;
import com.kinnarastudio.commons.jsonstream.JSONStream;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.stream.Stream;

public class CompositeResponses extends WebServiceResponse {
    public final static String JSON_KEY = "CompositeResponses";

    private final StandardResponse[] responses;

    private final boolean isError;

    private final String[] errorMessages;

    protected CompositeResponses(JSONObject jsonCompositeResponses) throws WebServiceResponseException {
        super(jsonCompositeResponses);

        try {
            final JSONObject jsonCompositeResponse = jsonCompositeResponses
                    .getJSONObject("CompositeResponse");

            final Object objStandardResponses = jsonCompositeResponse
                    .get(StandardResponse.JSON_KEY);

            final Stream<JSONObject> streamJson;
            if (objStandardResponses instanceof JSONArray) {
                streamJson = JSONStream.of((JSONArray) objStandardResponses, JSONArray::getJSONObject);
            } else if (objStandardResponses instanceof JSONObject) {
                streamJson = Stream.of((JSONObject) objStandardResponses);
            } else throw new WebServiceResponseException("Response payload is not StandardResponse");

            responses = streamJson
                    .map(Try.onFunction(StandardResponse::new))
                    .toArray(StandardResponse[]::new);

            isError = Arrays.stream(responses).anyMatch(StandardResponse::isError);

            errorMessages = Arrays.stream(responses)
                    .map(StandardResponse::getErrorMessage)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

        } catch (JSONException e) {
            throw new WebServiceResponseException(e);
        }
    }

    public StandardResponse[] getResponses() {
        return responses;
    }

    public boolean isError() {
        return isError;
    }

    public String[] getErrorMessages() {
        return errorMessages;
    }
}
