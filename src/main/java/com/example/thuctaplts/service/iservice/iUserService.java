package com.example.thuctaplts.service.iservice;

import com.example.thuctaplts.model.User;

public interface iUserService {

//    User createUser(UserDTO userDTO) throws Exception;
//
//    UserResponse getAllUser(int numberPage, int limit);
//    LoginResponse login(UserLoginDTO userLoginDTO) throws Exception;
    long getTotalUserCount();

    long getTotalUserLocked();
}
