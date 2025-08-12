/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.AnswerRequest;
import com.heulwen.dto.response.AnswerResponse;
import com.heulwen.mapper.AnswerMapper;
import com.heulwen.pojo.Answer;
import com.heulwen.repositories.AnswerRepository;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AnswerService {
    
    AnswerRepository answerRepository;
    AnswerMapper answerMapper;
    
    public List<AnswerResponse> getAnswer(Integer testResultsId){
        List<Answer> ans = answerRepository.getAnswerByTestResultsId_Id(testResultsId);
        return ans.stream().map(answerMapper::toAnswerResponse).toList();
    }
}
