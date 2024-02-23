package com.example.thuctaplts.payload.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;

    private long point;

    private String userName;

    private String email;

    private String name;

    private int phoneNumber;

    private String password;
}
