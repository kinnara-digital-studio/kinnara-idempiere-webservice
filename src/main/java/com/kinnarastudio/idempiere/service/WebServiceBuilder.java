package com.kinnarastudio.idempiere.service;

import com.kinnarastudio.idempiere.model.LoginRequest;
import com.kinnarastudio.idempiere.webservice.CompositeInterfaceWebService;

/**
 *
 * @param <Q>
 * @param <P>
 */
public abstract class WebServiceBuilder<Q, P> {
    private LoginRequest loginRequest;

    private String baseUrl;

    private boolean ignoreSslCertificateError = false;

    private String serviceType;

    public WebServiceBuilder<Q, P> setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
        return this;
    }

    public WebServiceBuilder<Q, P> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public WebServiceBuilder<Q, P> setHost(String host) {
        this.setBaseUrl("https://" + host + ":8443");
        return this;
    }

    public WebServiceBuilder<Q, P> ignoreSslCertificateError() {
        this.ignoreSslCertificateError = true;
        return this;
    }

    public WebServiceBuilder<Q, P> setServiceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    protected LoginRequest getLoginRequest() {
        return loginRequest;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }

    protected boolean isIgnoreSslCertificateError() {
        return ignoreSslCertificateError;
    }

    protected String getServiceType() {
        return serviceType;
    }
}
