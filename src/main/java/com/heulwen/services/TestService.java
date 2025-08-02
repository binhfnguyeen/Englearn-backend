/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.TestRequest;
import com.heulwen.dto.response.TestResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.TestMapper;
import com.heulwen.pojo.Test;
import com.heulwen.repositories.TestRepository;
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
public class TestService {

    TestRepository testRepository;
    TestMapper testMapper;

    public TestResponse addOrUpdateTest(TestRequest request) {
        Test test;

        if (request.getId() != null) {
            test = testRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));
            testMapper.updateTestFromRequest(request, test);
        } else {
            test = testMapper.toTest(request);
        }

        Test saved = testRepository.save(test);
        return testMapper.toTestResponse(saved);
    }

    public List<TestResponse> getTests() {
        return testRepository.findAll().stream().map(testMapper::toTestResponse).toList();
    }

    public TestResponse getTestById(int id) {
        Test test = testRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));
        return testMapper.toTestResponse(test);
    }

    public void deleteTest(int id) {
        testRepository.deleteById(id);
    }
}
