package com.example.thuctaplts.payload.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String Username;

    private String name;

    private String email;

    private String password;

    private String confirmPassword;

    private int phoneNumber;

}
