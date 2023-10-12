package com.kinnarastudio.idempiere.type;

import com.kinnarastudio.idempiere.model.ModelCrud;
import com.kinnarastudio.idempiere.model.ModelSetDocAction;

public enum ServiceMethod {
    CREATE_DATA("model_adservice", "create_data", "createData", ModelCrud.JSON_KEY),
    DELETE_DATA("model_adservice", "delete_data", "deleteData", ModelCrud.JSON_KEY),
    GET_LIST("model_adservice", "get_list", "", ModelCrud.JSON_KEY),
    QUERY_DATA("model_adservice", "query_data", "", ModelCrud.JSON_KEY),
    READ_DATA("model_adservice", "read_data", "readData", ModelCrud.JSON_KEY),
    RUN_PROCESS("model_adservice", "run_process", "runProcess", ModelCrud.JSON_KEY),
    SET_DOCUMENT_ACTION("model_adservice", "set_docaction", "setDocAction", ModelSetDocAction.JSON_KEY),
    UPDATE_DATA("model_adservice", "update_data", "updateData", ModelCrud.JSON_KEY),
    CREATE_OR_UPDATE_DATA("model_adservice", "create_update_data", "createUpdateData", ModelCrud.JSON_KEY);

    private final String port;
    private final String method;
    private final String targetPort;

    private final String keyModel;

    ServiceMethod(String port, String method, String targetPort, String keyModel) {
        this.port = port;
        this.method = method;
        this.targetPort = targetPort;
        this.keyModel = keyModel;
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
