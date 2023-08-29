package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import javax.annotation.Nonnull;

public class ModelCrudRequest extends ModelRequest {
    private final ModelCrud modelCrud;

    public ModelCrudRequest(@Nonnull LoginRequest loginRequest, @Nonnull ModelCrud modelCrud) {
        super(loginRequest);
        this.modelCrud = modelCrud;
    }

    public JSONObject toJson() {
        final JSONObject json = super.toJson();
        json.put(ModelCrud.JSON_KEY, modelCrud.toJson());
        return json;
    }

    @Override
    public String getJsonKey() {
        return ModelCrud.JSON_KEY;
    }
}
