package com.kinnarastudio.idempiere.webservice;

import com.kinnarastudio.idempiere.exception.WebServiceBuilderException;
import com.kinnarastudio.idempiere.exception.WebServiceRequestException;
import com.kinnarastudio.idempiere.exception.WebServiceResponseException;
import com.kinnarastudio.idempiere.model.*;
import com.kinnarastudio.idempiere.type.ModelOrientedWebServiceMethod;
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
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelOrientedWebService {
    private final String baseUrl;
    private final ModelOrientedWebServiceMethod method;
    private final ModelCrudRequest request;
    private final boolean ignoreSslCertificateError;

    private ModelOrientedWebService(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.method = builder.method;
        this.request = builder.request;
        this.ignoreSslCertificateError = builder.ignoreSslCertificateError;
    }

    public WebServiceResponse execute() throws WebServiceRequestException, WebServiceResponseException {
        final String url = String.join("/", baseUrl, "ADInterface/services/rest", method.getPort(), method.getMethod());

        final JSONObject requestPayload = new JSONObject();
        requestPayload.put(method.getKeyRequest(), request.toJson());

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
            return new WebServiceResponse(jsonResponseBody);
        } catch (IOException e) {
            throw new WebServiceResponseException(e);
        }
    }

    private int getResponseStatus(@Nonnull HttpResponse response) throws WebServiceResponseException {
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

    /**
     *
     */
    public static class Builder {
        private ModelCrudRequest request;

        private String baseUrl;

        private LoginRequest loginRequest;
        private String serviceType;

        private ModelOrientedWebServiceMethod method;

        private String table;

        private Integer recordId;

        private DataRow dataRow;

        private JSONObject requestPayload;

        private boolean ignoreSslCertificateError = false;

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setHost(String host) {
            this.setBaseUrl("https://" + host + ":8443");
            return this;
        }

        public Builder setLoginRequest(LoginRequest loginRequest) {
            this.loginRequest = loginRequest;
            return this;
        }

        public Builder setServiceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder setMethod(ModelOrientedWebServiceMethod method) {
            this.method = method;
            return this;
        }

        public Builder setTable(String table) {
            this.table = table;
            return this;
        }

        public Builder setRecordId(Integer recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder ignoreSslCertificateError() {
            this.ignoreSslCertificateError = true;
            return this;
        }

        public ModelOrientedWebService build() throws WebServiceBuilderException {
            if(baseUrl == null || baseUrl.isEmpty()) throw new WebServiceBuilderException("Base Url is not provided");

            if (serviceType == null) throw new WebServiceBuilderException("No serviceType is defined");

            if (method == null) throw new WebServiceBuilderException("No method is defined");

            if (loginRequest == null) throw new WebServiceBuilderException("Login information is not provided");

            final ModelCrud modelCrud = new ModelCrud(serviceType);

            request = new ModelCrudRequest(loginRequest, modelCrud);

            return new ModelOrientedWebService(this);
        }

    }
}
