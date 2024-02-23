package com.example.thuctaplts.service.iservice;

import com.example.thuctaplts.model.User;
import com.example.thuctaplts.payload.dto.LoginDto;
import com.example.thuctaplts.payload.request.LoginRequest;
import com.example.thuctaplts.payload.request.RegisterRequest;
public interface iAuthService {
    User registerAccount(RegisterRequest registerRequest) throws Exception;

    LoginDto login(LoginRequest loginRequest) throws Exception;
}
