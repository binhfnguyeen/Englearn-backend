/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.AnswerRequest;
import com.heulwen.dto.response.AnswerResponse;
import com.heulwen.pojo.Answer;
import com.heulwen.resolvers.QuestionChoiceResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring", uses = {QuestionChoiceResolver.class})
public interface AnswerMapper {
    
    @Mapping(source = "questionChoiceId", target = "questionChoiceId")
    Answer toAnswer(AnswerRequest request);
    
    AnswerResponse toAnswerResponse(Answer answer);
}
