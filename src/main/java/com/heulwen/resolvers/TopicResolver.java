/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.resolvers;

import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.pojo.Topic;
import com.heulwen.repositories.TopicRepository;
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
public class TopicResolver {
    TopicRepository topicRepository;
    
    public Topic fromId(Integer id) {
        return id == null ? null
                : topicRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.VOCAB_NOT_FOUND));
    }
}
