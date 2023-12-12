package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public abstract class Model {
    private final String serviceType;

    private final String tableName;

    private final Integer recordId;

    private final String recordIdVariable;

    public Model(@Nonnull String serviceType, String tableName, Integer recordId, String recordIdVariable) {
        this.serviceType = serviceType;
        this.tableName = tableName;
        this.recordId = recordId;
        this.recordIdVariable = recordIdVariable;
    }

    public abstract JSONObject toJson();

    protected abstract String getJsonKey();

    protected String getServiceType() {
        return serviceType;
    }

    protected String getTableName() {
        return tableName;
    }

    protected Integer getRecordId() {
        return recordId;
    }

    protected String getRecordIdVariable() {
        return recordIdVariable;
    }
}
