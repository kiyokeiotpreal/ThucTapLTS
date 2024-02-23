package com.example.thuctaplts.service.iservice;

import com.example.thuctaplts.model.RefreshToken;

public interface IRefreshTokenService {
    RefreshToken createRefreshToken (int userID) throws Exception;
}
