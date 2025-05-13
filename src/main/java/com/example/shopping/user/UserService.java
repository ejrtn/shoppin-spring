package com.example.shopping.user;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String naverId;

    public int userSave(UserDto userDto){
        userDto.setPartnerUserId(UUID.randomUUID()+" "+ LocalDateTime.now().toString());
        return userMapper.userSave(userDto);
    }

    public int userDelete(String userId){
        return userMapper.userDelete(userId);
    }

    public int login(String userId, String password){
        return userMapper.login(userId,password);
    }

    private int passwordUpdate(String userId, String newPassword){
        return userMapper.passwordUpdate2(userId,newPassword);
    }

    public int passwordUpdate(String userId, String password, String newPassword){
        return userMapper.passwordUpdate(userId,password,newPassword);
    }

    public String findId(String name, String email){
        return userMapper.findId(name,email);
    }

    public int findPassword(String name, String email, String userId){
        String result = userMapper.findPassword(name,email,userId);

        if(!result.equals("0")){
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                messageHelper.setFrom(naverId);		// 보내는 이메일
                messageHelper.setTo(email);	// 받는 이메일
                messageHelper.setSubject("DEOKSU 비밀번호 새로 발급");		// 메일 제목
                messageHelper.setText("DEOKSU\n" +
                        "새로 발급 비밀번호 : "+result+"\n"+
                        "로그인 후 비밀번호 변경해주시기 바랍니다.");		// 메일 내용
                messageHelper.setSentDate(new Date());	// 송신 날짜
                mailSender.send(message);	// 실제 보내기4
                passwordUpdate(userId,result);
                return 1;
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int idDoubleCheck(String userId){
        return userMapper.idDoubleCheck(userId);
    }
}
