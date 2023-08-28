package com.kinnarastudio.idempiere.webservice;

import com.kinnarastudio.idempiere.exception.WebServiceBuilderException;

import java.util.ArrayList;
import java.util.List;

public class CompositeInterfaceWebService {
    private CompositeInterfaceWebService(Builder builder) {

    }

    public static class Builder {
        private final List<ModelOrientedWebService> webServices = new ArrayList<>();

        public Builder addWebService(ModelOrientedWebService webService) {
            webServices.add(webService);
            return this;
        }
        public CompositeInterfaceWebService build() throws WebServiceBuilderException {
            if(webServices.isEmpty()) throw new WebServiceBuilderException("No web service is defined");
            return new CompositeInterfaceWebService(this);
        }
    }
}
