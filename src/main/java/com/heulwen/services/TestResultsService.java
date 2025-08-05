/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.TestResultsRequest;
import com.heulwen.dto.response.TestResultsResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.TestResultsMapper;
import com.heulwen.pojo.TestResults;
import com.heulwen.repositories.TestResultsRepository;
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
public class TestResultsService {
    TestResultsRepository testResultsRepository;
    TestResultsMapper testResultsMapper;
    
    public TestResultsResponse createTestResults(TestResultsRequest request){
        TestResults testResults = testResultsMapper.toTestResults(request);
        TestResults saved = testResultsRepository.save(testResults);
        return testResultsMapper.toTestResultsResponse(saved);
    }
    
    public List<TestResultsResponse> getTestResults(){
        return testResultsRepository.findAll().stream().map(testResultsMapper::toTestResultsResponse).toList();
    }
    
    public TestResultsResponse getTestResultsById(int id){
        TestResults testResults = testResultsRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.TEST_RESULTS_NOT_FOUND));
        return testResultsMapper.toTestResultsResponse(testResults);
    }
}
