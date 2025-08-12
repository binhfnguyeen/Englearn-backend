/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.response.AnswerResponse;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.services.AnswerService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ApiAnswerController {
    
    AnswerService answerService;
    
    @GetMapping("/test-results/{trId}/answer")
    ApiResponse<List<AnswerResponse>> getAnswerFromResult(@PathVariable("trId") int testResultsId ){
        return ApiResponse.<List<AnswerResponse>>builder()
                .code(1000)
                .result(this.answerService.getAnswer(testResultsId))
                .build();
    }
}
