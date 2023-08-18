package com.serenecandles.server.util;

import com.serenecandles.server.model.User;
import com.serenecandles.server.repo.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@Service
@Transactional
public class OtpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void generateOneTimePassword(User user) throws UnsupportedEncodingException, MessagingException {

        //Generate OTP
        String numbers = "0123456789";
        Random rand = new Random();
        char[] OTP = new char[6];
        for(int i=0;i<6;i++){
            OTP[i] = numbers.charAt(rand.nextInt(numbers.length()));
        }
        String otp = new String(OTP);

        String encodedOtp = passwordEncoder.encode(otp);

        user.setOneTimeEmailPassword(encodedOtp);
        user.setEmailOTPRequestedTime(new Date());

        userRepository.save(user);
        sendOTPEmail(user, otp);
    }

    public void sendOTPEmail(User user, String otp) throws UnsupportedEncodingException, MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("bhargavnikumbh99@gmail.com", "Serene Candles");
        helper.setTo(user.getEmail());

        String subject = "Here is your One Time Password (OTP) - Expires in 5 minutes!";

        String content = "<p> Hello "+user.getFirstName()+"</p>"
                +"<p>For Security reasons you are required the given OTP to register "
                +"One Time Password (OTP) to Login:</p>"
                +"<p><b>" + otp +"</b></p>"
                +"<br>"
                +"<p>Note: This OTP expires in 5 minutes.</p>";

        helper.setSubject(subject);
        helper.setText(content,true);
        javaMailSender.send(message);
    }

    public void clearOTP(User user){
        user.setOneTimeEmailPassword(null);
        user.setEmailOTPRequestedTime(null);
        userRepository.save(user);
    }
}
