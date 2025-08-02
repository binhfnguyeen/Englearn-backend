/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.AuthenticationRequest;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.dto.response.AuthenticationResponse;
import com.heulwen.dto.response.UserResponse;
import com.heulwen.mapper.UserMapper;
import com.heulwen.pojo.User;
import com.heulwen.services.AuthenticationService;
import com.heulwen.services.UserService;
import java.security.Principal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApiAuthenticationController {
    AuthenticationService authenticationService;
    UserService userDetailsService;
    UserMapper userMapper;
    
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }
    
    @PostMapping("/secure/profile")
    @ResponseBody
    @CrossOrigin
    public ApiResponse<UserResponse> getProfile(Principal principal){
        User user = this.userDetailsService.getUserByUsername(principal.getName());
        UserResponse response = userMapper.toUserResponse(user);
        
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }
}
