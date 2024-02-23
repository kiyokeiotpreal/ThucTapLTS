package com.example.thuctaplts.service.implementService;

import com.example.thuctaplts.Repository.RefreshTokenRepository;
import com.example.thuctaplts.Repository.UserRepository;
import com.example.thuctaplts.exceptions.DataNotFoundException;
import com.example.thuctaplts.model.RefreshToken;
import com.example.thuctaplts.model.User;
import com.example.thuctaplts.service.iservice.IRefreshTokenService;
import com.example.thuctaplts.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RefreshTokenRepository refreshTokenRepo;
    @Value("${jwt.expirationRefreshToken}")
    private int expirationRefreshToken;

    @Override
    public RefreshToken createRefreshToken(int userID) throws Exception {
        User user = userRepo.findById(userID).orElse(null);
        if(user == null){
            throw  new DataNotFoundException(MessageKeys.EMAIL_DOES_NOT_EXISTS);
        }
        if (!user.isActive()){
            throw new Exception(MessageKeys.USER_ACCOUNT_IS_DISABLED);
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiredTime(LocalDate.from(LocalDateTime.now().plusSeconds(expirationRefreshToken)))
                .user(user)
                .build();
        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }
}
