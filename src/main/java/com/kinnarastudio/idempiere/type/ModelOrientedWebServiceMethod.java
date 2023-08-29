package com.kinnarastudio.idempiere.type;

public enum ModelOrientedWebServiceMethod {
    CREATE_DATA("model_adservice", "create_data", "ModelCRUDRequest"),
    DELETE_DATA("model_adservice", "delete_data", "ModelCRUDRequest"),
    GET_LIST("model_adservice", "get_list", "ModelGetListRequest"),
    QUERY_DATA("model_adservice", "query_data", "ModelCRUDRequest"),
    READ_DATA("model_adservice", "read_data", "ModelCRUDRequest"),
    RUN_PROCESS("model_adservice", "run_process", "ModelRunProcessRequest"),
    SET_DOCUMENT_ACTION("model_adservice", "set_docaction", "ModelSetDocActionRequest"),
    UPDATE_DATA("model_adservice", "update_data", "ModelCRUDRequest"),
    CREATE_OR_UPDATE_DATA("model_adservice", "create_update_data", "ModelCRUDRequest");

    private final String port;
    private final String method;
    private final String keyRequest;

    ModelOrientedWebServiceMethod(String port, String method, String keyRequest) {
        this.port = port;
        this.method = method;
        this.keyRequest = keyRequest;
    }

    public String getKeyRequest() {
        return keyRequest;
    }

    public String getPort() {
        return port;
    }

    public String getMethod() {
        return method;
    }
}
