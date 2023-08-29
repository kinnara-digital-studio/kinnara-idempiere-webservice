package com.kinnarastudio.idempiere.type;

public enum ServiceMethod {
    CREATE_DATA("model_adservice", "create_data", "createData", "ModelCRUD", "ModelCRUDRequest"),
    DELETE_DATA("model_adservice", "delete_data", "deleteData", "ModelCRUD", "ModelCRUDRequest"),
    GET_LIST("model_adservice", "get_list", "", "ModelCRUD", "ModelGetListRequest"),
    QUERY_DATA("model_adservice", "query_data", "", "ModelCRUD", "ModelCRUDRequest"),
    READ_DATA("model_adservice", "read_data", "readData", "ModelCRUD", "ModelCRUDRequest"),
    RUN_PROCESS("model_adservice", "run_process", "runProcess", "ModelCRUD", "ModelRunProcessRequest"),
    SET_DOCUMENT_ACTION("model_adservice", "set_docaction", "setDocAction", "ModelCRUD", "ModelSetDocActionRequest"),
    UPDATE_DATA("model_adservice", "update_data", "updateData", "ModelCRUD", "ModelCRUDRequest"),
    CREATE_OR_UPDATE_DATA("model_adservice", "create_update_data", "createUpdateData", "ModelCRUD", "ModelCRUDRequest");

    private final String port;
    private final String method;
    private final String targetPort;

    private final String keyModel;
    private final String keyRequest;

    ServiceMethod(String port, String method, String targetPort, String keyModel, String keyRequest) {
        this.port = port;
        this.method = method;
        this.targetPort = targetPort;
        this.keyModel = keyModel;
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

    public String getTargetPort() {
        return targetPort;
    }

    public String getKeyModel() {
        return keyModel;
    }
}
