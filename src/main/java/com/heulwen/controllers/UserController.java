/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.UserCreationRequest;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.dto.response.UserResponse;
import com.heulwen.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    
    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request){
        ApiResponse<UserResponse> response = new ApiResponse<>();
        
        response.setResult(userService.createUser(request));
        return response;
    }
    
    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userService.getUsers())
                .build();
    }
}
