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
import com.heulwen.pojo.Answer;
import com.heulwen.pojo.QuestionChoice;
import com.heulwen.pojo.TestResults;
import com.heulwen.repositories.QuestionChoiceRepository;
import com.heulwen.repositories.TestResultsRepository;
import java.util.HashSet;
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
    QuestionChoiceRepository questionChoiceRepository;
    TestResultsMapper testResultsMapper;

    public TestResultsResponse createTestResults(TestResultsRequest request) {
        TestResults testResults = testResultsMapper.toTestResults(request);

        if (request.getAnswers() != null && !request.getAnswers().isEmpty()) {
            List<Answer> answerList = request.getAnswers().stream()
                    .map(ansReq -> {
                        Answer answer = new Answer();
                        answer.setTestResultsId(testResults);
                        QuestionChoice qc = questionChoiceRepository.getReferenceById(ansReq.getQuestionChoiceId());
                        answer.setQuestionChoiceId(qc);
                        return answer;
                    })
                    .toList();

            testResults.setAnswerSet(new HashSet<>(answerList));
        }
        TestResults saved = testResultsRepository.save(testResults);
        return testResultsMapper.toTestResultsResponse(saved);
    }

    public List<TestResultsResponse> getTestResults() {
        return testResultsRepository.findAll().stream().map(testResultsMapper::toTestResultsResponse).toList();
    }

    public TestResultsResponse getTestResultsById(int id) {
        TestResults testResults = testResultsRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEST_RESULTS_NOT_FOUND));
        return testResultsMapper.toTestResultsResponse(testResults);
    }

    public List<TestResultsResponse> getResultTestByUser(int userId, int testId) {
        List<TestResults> testResults = testResultsRepository.findByUserId_IdAndTestId_Id(userId, testId);
        return testResults.stream().map(testResultsMapper::toTestResultsResponse).toList();
    }
}
