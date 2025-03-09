package com.example.myapp.exception;

public class BiasNotFoundException extends RuntimeException {
    public BiasNotFoundException(String message) {
        super(message);
    }
}