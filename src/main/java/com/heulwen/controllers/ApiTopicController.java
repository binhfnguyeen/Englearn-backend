/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.TopicRequest;
import com.heulwen.dto.response.VocabTopicResponse;
import com.heulwen.dto.response.ApiResponse;
import com.heulwen.dto.response.TopicResponse;
import com.heulwen.dto.response.VocabularyResponse;
import com.heulwen.services.TopicService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ApiTopicController {

    TopicService topicService;

    @GetMapping("/topics")
    ApiResponse<List<TopicResponse>> list() {
        return ApiResponse.<List<TopicResponse>>builder()
                .code(1000)
                .result(topicService.getTopics())
                .build();
    }

    @PostMapping("/topics")
    ApiResponse<TopicResponse> addOrUpdate(@RequestBody TopicRequest request) {
        TopicResponse result = topicService.addOrUpdateTopic(request);
        return ApiResponse.<TopicResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }
    
    @GetMapping("/topics/{id}")
    ApiResponse<TopicResponse> retrieve(@PathVariable("id") Integer id){
        return ApiResponse.<TopicResponse>builder()
                .code(1000)
                .result(topicService.getTopicById(id))
                .build();
    }
    
    @DeleteMapping("/topics/{id}")
    ResponseEntity<?> delete(@PathVariable("id") int id){
        this.topicService.deleteTopic(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully!"));
    }
    
    @GetMapping("/topics/{topicId}/vocabularies")
    ApiResponse<Set<VocabularyResponse>> list(@PathVariable("topicId") int topicId){
        return ApiResponse.<Set<VocabularyResponse>>builder()
                .code(1000)
                .result(topicService.getVocabulariesWithTopic(topicId))
                .build();
    }
    
    @PostMapping("/topics/{topicId}/vocabularies")
    ApiResponse<VocabTopicResponse> addVocabToTopic(@PathVariable("topicId") int topicId, @RequestBody int vocabId){
        return ApiResponse.<VocabTopicResponse>builder()
                .code(1000)
                .result(topicService.addVocabToTopic(topicId, vocabId))
                .build();
    }
    
}
