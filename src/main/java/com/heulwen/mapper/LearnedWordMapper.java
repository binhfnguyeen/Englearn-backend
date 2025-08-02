/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.LearnedWordRequest;
import com.heulwen.dto.response.LearnedWordResponse;
import com.heulwen.pojo.LearnedWord;
import com.heulwen.resolvers.UserResolver;
import com.heulwen.resolvers.VocabularyResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring", uses = {UserResolver.class, VocabularyResolver.class})
public interface LearnedWordMapper {
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "vocabularyId", target = "vocabularyId")
    LearnedWord toLearnedWord(LearnedWordRequest request);
    
    LearnedWordResponse toLearnedWordResponse(LearnedWord learnedWord);
}
