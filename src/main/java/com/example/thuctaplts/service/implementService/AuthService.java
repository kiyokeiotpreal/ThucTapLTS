package com.example.thuctaplts.service.implementService;

import com.example.thuctaplts.Repository.*;
import com.example.thuctaplts.components.JwtTokenUtils;
import com.example.thuctaplts.exceptions.DataNotFoundException;
import com.example.thuctaplts.model.RefreshToken;
import com.example.thuctaplts.model.Roles;
import com.example.thuctaplts.model.User;
import com.example.thuctaplts.payload.dto.LoginDto;
import com.example.thuctaplts.payload.request.LoginRequest;
import com.example.thuctaplts.payload.request.RegisterRequest;
import com.example.thuctaplts.service.iservice.iAuthService;
import com.example.thuctaplts.utils.MessageKeys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements iAuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RolesRepository roleRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RankCustomerRepository rankCustomerRepo;
    @Autowired
    private UserStatusRepository userStatusRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Override
    public User registerAccount(RegisterRequest registerRequest) throws Exception {
        String email = registerRequest.getEmail();

        if(userRepo.existsByEmail(email)){
            throw new DataIntegrityViolationException(MessageKeys.EMAIL_ALREADY_EXISTS);
        }
        Roles userRole = roleRepo.findById(1).orElseThrow(()
            -> new IllegalStateException("Role not found with ID 2"));

        User user = User.builder()
                .point(0)
                .name(registerRequest.getName())
                .email(email)
                .userName(registerRequest.getName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(userRole)
                .rankCustomer(rankCustomerRepo.findById(1).orElse(null))
                .userStatus(userStatusRepo.findById(2).orElse(null))
                .build();

        return user;
    }

    @Override
    public LoginDto login(LoginRequest loginRequest) throws Exception {
        User existingUser = userRepo.findByEmail(loginRequest.getEmail()).orElse(null);
        if(existingUser == null) {
            throw new DataNotFoundException(MessageKeys.EMAIL_DOES_NOT_EXISTS);
        }
        if(!existingUser.isActive()){
            throw new DisabledException(MessageKeys.USER_ACCOUNT_IS_DISABLED);
        }

        //Chuyền email,password, role vào authenticationToken để xac thực ngươi dùng
        UsernamePasswordAuthenticationToken auToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword(),
                existingUser.getAuthorities()
        );
        //Xác thực người dùng (nếu xác thực không thành công VD: sai pass ) thì sẽ ném ra ngoại lệ
        authenticationManager.authenticate(auToken);

        //Lấy role của user
        User userDetails = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //sinh ngẫu nhiên 1 token từ existingUser
        String token = jwtTokenUtils.generateToken(existingUser);

        RefreshToken refreshTokens = refreshTokenService.createRefreshToken(existingUser.getId());

        LoginDto loginDTO= LoginDto.builder()
                .userName(existingUser.getName())
                .token(token)
                .refreshToken(refreshTokens.getToken())
                .roles(roles)
                .build();
        return loginDTO;
    }

    public User registerRequestToUser(RegisterRequest registerRequest){
        return this.modelMapper.map(registerRequest, User.class);
    }
}
