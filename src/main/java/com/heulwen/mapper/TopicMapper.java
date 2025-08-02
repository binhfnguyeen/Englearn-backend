/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.TopicRequest;
import com.heulwen.dto.response.TopicResponse;
import com.heulwen.pojo.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toTopic(TopicRequest request);
    TopicResponse toTopicResponse(Topic topic);
    void updateTopicFromRequest(TopicRequest request, @MappingTarget Topic topic);
}
