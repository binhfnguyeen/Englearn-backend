/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.TestResultsRequest;
import com.heulwen.dto.response.TestResultsResponse;
import com.heulwen.pojo.TestResults;
import com.heulwen.resolvers.TestResolver;
import com.heulwen.resolvers.UserResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring", uses = {TestResolver.class, UserResolver.class})
public interface TestResultsMapper {
    
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "testId", target = "testId")
    TestResults toTestResults(TestResultsRequest request);
    
    TestResultsResponse toTestResultsResponse(TestResults testResults);
}
