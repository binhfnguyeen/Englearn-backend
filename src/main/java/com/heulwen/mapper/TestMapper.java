/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.TestRequest;
import com.heulwen.dto.response.TestResponse;
import com.heulwen.pojo.Test;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toTest(TestRequest  request);
    TestResponse toTestResponse(Test test);
    void updateTestFromRequest(TestRequest request, @MappingTarget Test test);
}
