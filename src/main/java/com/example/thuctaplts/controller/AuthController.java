package com.example.thuctaplts.controller;

import com.example.thuctaplts.Repository.ConfirmEmailRepository;
import com.example.thuctaplts.Repository.UserRepository;
import com.example.thuctaplts.Repository.UserStatusRepository;
import com.example.thuctaplts.exceptions.ConfirmEmailExpired;
import com.example.thuctaplts.exceptions.DataNotFoundException;
import com.example.thuctaplts.model.ConfirmEmail;
import com.example.thuctaplts.model.User;
import com.example.thuctaplts.payload.dto.LoginDto;
import com.example.thuctaplts.payload.request.LoginRequest;
import com.example.thuctaplts.payload.request.RegisterRequest;
import com.example.thuctaplts.payload.response.ResponseObject;
import com.example.thuctaplts.service.implementService.AuthService;
import com.example.thuctaplts.service.implementService.ConfirmEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ConfirmEmailService confirmEmailService;

    @Autowired
    private ConfirmEmailRepository confirmEmailRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserStatusRepository userStatusRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = authService.registerAccount(registerRequest);
            confirmEmailService.sendConfirmEmail(user);
            return ResponseEntity.ok().body("Kiểm tra email để xác nhận tài khoản");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/confirm-register")
    public ResponseEntity<?> confirmEmail(@RequestParam String confirmCode) {
        try {
            ConfirmEmail confirmEmail = confirmEmailRepo.findConfirmEmailByConfirmCode(confirmCode);
            User user = userRepo.findByConfirmEmails(confirmEmail);

            var isConfirm = confirmEmailService.confirmEmail(confirmCode);
            if (isConfirm) {
                user.setActive(true);
                user.setUserStatus(userStatusRepo.findById(1).orElse(null));
                userRepo.save(user);
            }
            return ResponseEntity.ok().body("Đăng kí thành công");
        } catch (DataNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ConfirmEmailExpired ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
//        try{
//            var result = authService.login(loginRequest);
//            return ResponseEntity.ok().body(result);
//        }catch (DataNotFoundException | DisabledException | AuthenticationException ex){
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        } catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Error");
//        }
//    }
//}

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            var loginDTO = authService.login(loginRequest);
            return ResponseEntity.ok().body(loginDTO);
        } catch (DataNotFoundException e) {
            // Email không tồn tại
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AuthenticationException e) {
            // Sai mật khẩu hoặc thông tin đăng nhập không hợp lệ
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            //lỗi khác do serve
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
