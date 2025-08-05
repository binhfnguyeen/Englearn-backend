/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.resolvers;

import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.pojo.Test;
import com.heulwen.repositories.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dell
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestResolver {
    TestRepository testRepository;
    
    public Test fromId(Integer id) {
        return id == null ? null
                : testRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TEST_NOT_FOUND));
    }
}
