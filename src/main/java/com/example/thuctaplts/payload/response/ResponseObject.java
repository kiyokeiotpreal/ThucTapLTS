package com.example.thuctaplts.payload.response;

import java.net.HttpURLConnection;

public class ResponseObject <T> {
    private int statusCode;
    private String message;
    private T data;

    public ResponseObject() {
    }

    public ResponseObject(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseObject<T> responseSuccess(String message, T data){
        return new ResponseObject<T>(HttpURLConnection.HTTP_OK, message, data);
    }
    public ResponseObject<T> responseError(int statusCode , String message, T data){
        return new ResponseObject<T>(statusCode, message, data);
    }
}
