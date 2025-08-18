/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.pojo.OtpToken;
import com.heulwen.pojo.User;
import com.heulwen.repositories.OtpTokenRepository;
import com.heulwen.repositories.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OtpService {
    OtpTokenRepository otpTokenRepository;
    UserRepository userRepository;
    EmailService emailService;
    
    public void generateAndSendOtp(String email){
        User user = userRepository.findByEmail(email);
        
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        
        OtpToken otpToken = new OtpToken();
        otpToken.setOtpCode(otp);
        otpToken.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        otpToken.setUserId(user);
        
        otpTokenRepository.save(otpToken);
        
        emailService.sendOtpEmail(user.getEmail(), otp);
    }
    
    public void verifyOtpAndResetPassword(String email, String otp, String newPassword){
        User user = userRepository.findByEmail(email);
        OtpToken otpToken = otpTokenRepository.findByUserIdAndOtpCode(user, otp).orElseThrow(() -> new AppException(ErrorCode.INVALID_OTP));
        
        if (otpToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        
        user.setPassword(new BCryptPasswordEncoder(10).encode(newPassword));
        
        otpTokenRepository.delete(otpToken);
    }
}
