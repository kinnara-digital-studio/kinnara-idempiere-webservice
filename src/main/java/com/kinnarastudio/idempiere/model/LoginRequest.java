package com.kinnarastudio.idempiere.model;

import org.json.JSONObject;

import java.util.Optional;

public final class LoginRequest {
    public final static String JSON_KEY = "ADLoginRequest";

    private final String username;
    private final String password;
    private final String language;
    private final Integer clientId;
    private final Integer roleId;
    private final Integer orgId;
    private final Integer warehouseId;

    public LoginRequest(String username, String password, String language, Integer clientId, Integer roleId) {
        this(username, password, language, clientId, roleId, null, null);
    }

    public LoginRequest(String username, String password, String language, Integer clientId, Integer roleId, Integer orgId, Integer warehouseId) {
        this.username = username;
        this.password = password;
        this.language = language;
        this.clientId = clientId;
        this.roleId = roleId;
        this.orgId = orgId;
        this.warehouseId = warehouseId;
    }

    public String getJsonKey() {
        return JSON_KEY;
    }

    public JSONObject toJson() {
        final JSONObject jsonLoginRequest = new JSONObject();
        jsonLoginRequest.put("user", username);
        jsonLoginRequest.put("pass", password);
        jsonLoginRequest.put("lang", language);

        jsonLoginRequest.put("ClientID", clientId);
        jsonLoginRequest.put("RoleID", roleId);

        Optional.ofNullable(orgId).ifPresent(i -> jsonLoginRequest.put("OrgID", i));
        Optional.ofNullable(warehouseId).ifPresent(i -> jsonLoginRequest.put("WarehouseID", i));

        return jsonLoginRequest;
    }
}
