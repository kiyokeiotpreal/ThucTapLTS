package com.example.thuctaplts.service.iservice;

import com.example.thuctaplts.model.User;

public interface iConfirmEmail {
    void sendConfirmEmail(User user);
    boolean confirmEmail(String confirmCode) throws Exception;
}
