package com.kinnarastudio.idempiere.type;

public enum ModelOrientedWebServiceMethod {
    CREATE_DATA("model_adservice", "create_data", "ModelCRUDRequest", "ModelCRUD"),
    DELETE_DATA("model_adservice", "delete_data", "ModelCRUDRequest", "ModelCRUD"),
    GET_LIST("model_adservice", "get_list", "ModelGetListRequest", "ModelGetList"),
    QUERY_DATA("model_adservice", "query_data", "ModelCRUDRequest", "ModelCRUD"),
    READ_DATA("model_adservice", "read_data", "ModelCRUDRequest", "ModelCRUD"),
    RUN_PROCESS("model_adservice", "run_process", "ModelRunProcessRequest", "ModelCRUD"),
    SET_DOCUMENT_ACTION("model_adservice", "set_docaction", "ModelSetDocActionRequest", "ModelSetDocAction"),
    UPDATE_DATA("model_adservice", "update_data", "ModelCRUDRequest", "ModelCRUD"),
    CREATE_OR_UPDATE_DATA("model_adservice", "create_update_data", "ModelCRUDRequest", "ModelCRUD");

    private final String port;
    private final String method;
    private final String keyRequest;

    private final String keyModel;

    ModelOrientedWebServiceMethod(String port, String method, String keyRequest, String keyModel) {
        this.port = port;
        this.method = method;
        this.keyRequest = keyRequest;
        this.keyModel = keyModel;
    }

    public String getKeyRequest() {
        return keyRequest;
    }

    public String getKeyModel() {
        return keyModel;
    }

    public String getPort() {
        return port;
    }

    public String getMethod() {
        return method;
    }
}
