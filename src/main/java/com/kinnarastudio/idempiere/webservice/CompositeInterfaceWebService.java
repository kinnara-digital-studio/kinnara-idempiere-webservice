package com.kinnarastudio.idempiere.webservice;

import com.kinnarastudio.idempiere.exception.WebServiceBuilderException;
import com.kinnarastudio.idempiere.exception.WebServiceRequestException;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import com.kinnarastudio.idempiere.model.CompositeRequest;
import com.kinnarastudio.idempiere.model.LoginRequest;
import com.kinnarastudio.idempiere.model.Operation;
import com.kinnarastudio.idempiere.model.WebServiceResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompositeInterfaceWebService {
    private final String baseUrl;

    private final boolean ignoreSslCertificateError;
    private final CompositeRequest compositeRequest;

    private CompositeInterfaceWebService(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.ignoreSslCertificateError = builder.ignoreSslCertificateError;
        this.compositeRequest = builder.compositeRequest;
    }

    public WebServiceResponse execute() throws WebServiceRequestException, WebServiceResponseException {
        final String url = String.join("/", baseUrl, "ADInterface/services/rest/composite_service/composite_operation");

        final JSONObject requestPayload = toJson();

        final HttpPost request = new HttpPost(url);
        request.addHeader("Accept", "application/json");

        final HttpEntity requestEntity = new StringEntity(requestPayload.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);

        final HttpClient client = getHttpClient(ignoreSslCertificateError);

        final HttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            throw new WebServiceRequestException(e);
        }

        final int statusCode = getResponseStatus(response);
        if (statusCode != 200) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
                String responsePayload = br.lines().collect(Collectors.joining());
                throw new WebServiceResponseException("Response code [" + statusCode + "] is not 200 (Success) :" + responsePayload);
            } catch (IOException e) {
                throw new WebServiceResponseException(e);
            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            final JSONObject jsonResponseBody = new JSONObject(br.lines().collect(Collectors.joining()));
            return new WebServiceResponse.Builder().setResponsePayload(jsonResponseBody).build();
        } catch (IOException e) {
            throw new WebServiceResponseException(e);
        }
    }

    protected int getResponseStatus(@Nonnull HttpResponse response) throws WebServiceResponseException {
        return Optional.of(response)
                .map(HttpResponse::getStatusLine)
                .map(StatusLine::getStatusCode)
                .orElseThrow(() -> new WebServiceResponseException("Error getting status code"));
    }

    protected HttpClient getHttpClient(boolean ignoreCertificate) throws WebServiceRequestException {
        try {
            if (ignoreCertificate) {
                SSLContext sslContext = new SSLContextBuilder()
                        .loadTrustMaterial(null, (certificate, authType) -> true).build();
                return HttpClients.custom().setSSLContext(sslContext)
                        .setSSLHostnameVerifier(new NoopHostnameVerifier())
                        .build();
            } else {
                return HttpClientBuilder.create().build();
            }
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            throw new WebServiceRequestException(e);
        }
    }

    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        json.put(CompositeRequest.JSON_KEY, compositeRequest.toJson());
        return json;
    }

    public static class Builder {
        private CompositeRequest compositeRequest;
        private LoginRequest loginRequest;

        private String baseUrl;

        private boolean ignoreSslCertificateError = false;

        private String serviceType;

        private final List<Operation> operations = new ArrayList<>();

        public Builder setLoginRequest(LoginRequest loginRequest) {
            this.loginRequest = loginRequest;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setHost(String host) {
            this.setBaseUrl("https://" + host + ":8443");
            return this;
        }

        public Builder ignoreSslCertificateError() {
            this.ignoreSslCertificateError = true;
            return this;
        }

        public Builder setServiceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder addWebServiceOperation(Operation webServiceOperation) {
            operations.add(webServiceOperation);
            return this;
        }

        public CompositeInterfaceWebService build() throws WebServiceBuilderException {
            if (baseUrl == null || baseUrl.isEmpty()) throw new WebServiceBuilderException("Base Url is not provided");

            if (loginRequest == null) throw new WebServiceBuilderException("Login information is not provided");

            if (serviceType == null) throw new WebServiceBuilderException("No service type is defined");

            if(operations.isEmpty()) throw new WebServiceBuilderException("No web service is defined");

            compositeRequest = new CompositeRequest(loginRequest, serviceType, operations.toArray(new Operation[0]));

            return new CompositeInterfaceWebService(this);
        }
    }
}
