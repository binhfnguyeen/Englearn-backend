/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.ConservationCreationRequest;
import com.heulwen.dto.request.MessageRequest;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.dto.response.ConservationCreationResponse;
import com.heulwen.dto.response.ConservationResponse;
import com.heulwen.services.ConservationService;
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
public class ApiConservationController {
    
    ConservationService conservationService;
    
    @PostMapping("/conservations")
    ApiResponse<ConservationCreationResponse> post(@RequestBody ConservationCreationRequest request){
        return ApiResponse.<ConservationCreationResponse>builder()
                .code(1000)
                .result(this.conservationService.createConservation(request))
                .build();
    }
    
    @GetMapping("/conservations/users/{userId}")
    ApiResponse<List<ConservationResponse>> list(@PathVariable("userId") int userId){
        return ApiResponse.<List<ConservationResponse>>builder()
                .code(1000)
                .result(this.conservationService.getConservationsByUserId(userId))
                .build();
    }
    
    @GetMapping("conservations/{id}")
    ApiResponse<ConservationResponse> retrieve(@PathVariable("id") int id){
        return ApiResponse.<ConservationResponse>builder()
                .code(1000)
                .result(this.conservationService.getConservationById(id))
                .build();
    }
    
    @PostMapping("conservations/{id}")
    ApiResponse<ConservationResponse> retrieve(@RequestBody MessageRequest request,@PathVariable("id") int id){
        return ApiResponse.<ConservationResponse>builder()
                .code(1000)
                .result(this.conservationService.addMessageToConservation(request, id))
                .build();
    }
}
