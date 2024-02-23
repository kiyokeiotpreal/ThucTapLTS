package com.example.thuctaplts.service.implementService;

import com.example.thuctaplts.Repository.ConfirmEmailRepository;
import com.example.thuctaplts.exceptions.ConfirmEmailExpired;
import com.example.thuctaplts.exceptions.DataNotFoundException;
import com.example.thuctaplts.model.ConfirmEmail;
import com.example.thuctaplts.model.User;
import com.example.thuctaplts.service.iservice.iConfirmEmail;
import com.example.thuctaplts.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ConfirmEmailService implements iConfirmEmail {
    @Autowired
    private ConfirmEmailRepository confirmEmailRepo;

    @Autowired
    private JavaMailSender javaMailSender;
    private String generateConfirmCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }
    private void senEmail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("doan77309@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);

    }

    public boolean isExpired(ConfirmEmail confirmEmail) {
        return LocalDateTime.now().isAfter(confirmEmail.getExpiredTime().toLocalDateTime());
    }

    @Override
    public void sendConfirmEmail(User user) {
        ConfirmEmail confirmEmail = ConfirmEmail.builder()
                .user(user)
                .confirmCode(generateConfirmCode())
                .expiredTime(Timestamp.valueOf(LocalDateTime.now().plusSeconds(60)))
                .isConfirm(false)
                .requiredTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        confirmEmailRepo.save(confirmEmail);

        //todo Gửi email với mã code và thông tin
        String subject ="Xác nhận email của bạn";
        String content = "Mã xác thực của bạn là: " + confirmEmail.getConfirmCode();
        senEmail(user.getEmail(),subject,content);
    }

    @Override
    public boolean confirmEmail(String confirmCode) throws Exception {
        ConfirmEmail confirmEmail = confirmEmailRepo.findConfirmEmailByConfirmCode(confirmCode);

        if(confirmEmail == null ){
            throw  new DataNotFoundException(MessageKeys.INCORRECT_VERIFICATION_CODE);
        }

        if (isExpired(confirmEmail)){
            throw new ConfirmEmailExpired(MessageKeys.VERIFICATION_CODE_HAS_EXPIRED);
        }
        confirmEmail.setConfirm(true);

        confirmEmailRepo.save(confirmEmail);

        return true;
    }
}
