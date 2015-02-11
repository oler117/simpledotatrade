package com.simpledotatrade.domain.exceptions;

/**
 * Created by oler on 11.02.2015.
 */
public class ErrorResponse {

    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
