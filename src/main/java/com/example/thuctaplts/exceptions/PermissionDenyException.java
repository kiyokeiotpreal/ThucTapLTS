package com.example.thuctaplts.exceptions;
public class PermissionDenyException extends Exception{
    public PermissionDenyException(String message) {
        super(message);
    }
}