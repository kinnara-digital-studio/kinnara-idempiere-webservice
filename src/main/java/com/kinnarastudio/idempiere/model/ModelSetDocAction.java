package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.Map;

public class ModelSetDocAction extends Model {
    public final static String JSON_KEY = "ModelSetDocAction";

    public final static String PARAMETER_RECORD_ID = "recordID";

    private final String docAction;

    public ModelSetDocAction(@Nonnull String serviceType, String tableName, Integer recordId, String recordIdVariable, String docAction) {
        super(serviceType, tableName, recordId, recordIdVariable);
        this.docAction = docAction;
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put("serviceType", getServiceType());
        json.put(PARAMETER_RECORD_ID, getRecordId());
        json.put("recordIDVariable", getRecordIdVariable());
        json.put("docAction", docAction);

        return json;
    }

    @Override
    protected String getJsonKey() {
        return JSON_KEY;
    }
}
