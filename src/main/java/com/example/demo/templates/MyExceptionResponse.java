package com.example.demo.templates;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class MyExceptionResponse {

    public Timestamp timestamp;

    public HttpStatus statusCode;

    public String error;

    public MyExceptionResponse(Timestamp timestamp, HttpStatus statusCode, String error) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
