/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.LearnedWordRequest;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.dto.response.LearnedWordResponse;
import com.heulwen.services.LearnedWordService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ApiLearnedWordController {
    LearnedWordService learnedWordService;
    
    @PostMapping("/learnedWords")
    ApiResponse<LearnedWordResponse> post(@RequestBody LearnedWordRequest request){
        return ApiResponse.<LearnedWordResponse>builder()
                .code(1000)
                .result(learnedWordService.addLearnedWord(request))
                .build();
    }
    
}
