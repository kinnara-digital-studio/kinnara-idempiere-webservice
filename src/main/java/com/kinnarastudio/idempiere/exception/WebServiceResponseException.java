package com.kinnarastudio.idempiere.exception;

public class WebServiceResponseException extends Exception {
    public WebServiceResponseException(String message) {
        super(message);
    }

    public WebServiceResponseException(Throwable cause) {
        super(cause);
    }
}
