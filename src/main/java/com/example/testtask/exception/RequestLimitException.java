package com.example.testtask.exception;

public class RequestLimitException extends RuntimeException{
    public RequestLimitException(String message) {
        super(message);
    }
}
