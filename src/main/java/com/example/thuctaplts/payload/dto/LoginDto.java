package com.example.thuctaplts.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginDto {
    private String userName;
    @JsonProperty("token")
    private String token;
    private List<String> roles;

    private String refreshToken;
}
