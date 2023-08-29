package com.kinnarastudio.idempiere.model;

import com.kinnarastudio.idempiere.type.ServiceMethod;
import org.json.JSONObject;

public class Operation {
    private final boolean preCommit;
    private final boolean postCommit;

    private final ServiceMethod method;

    private final Model model;

    public Operation(ServiceMethod method, Model model) {
        this(method, model, false, false);
    }

    public Operation(ServiceMethod method, Model model, boolean preCommit, boolean postCommit) {
        this.preCommit = preCommit;
        this.postCommit = postCommit;
        this.method = method;
        this.model = model;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("@preCommit", preCommit);
        json.put("@postCommit", postCommit);
        json.put("TargetPort", method.getTargetPort());
        json.put(method.getKeyModel(), model.toJson());
        return json;
    }
}
