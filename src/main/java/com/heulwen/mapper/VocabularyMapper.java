/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.VocabularyCreationRequest;
import com.heulwen.dto.response.VocabularyResponse;
import com.heulwen.pojo.Vocabulary;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring")
public interface VocabularyMapper {
    Vocabulary toVocabulary(VocabularyCreationRequest request);
    VocabularyResponse toVocabularyResponse(Vocabulary vocabulary);
    void updateVocabularyFromRequest(VocabularyCreationRequest request, @MappingTarget Vocabulary vocab);
}
