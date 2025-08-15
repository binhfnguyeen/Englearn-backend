/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.LearnedWordRequest;
import com.heulwen.dto.response.LearnedWordResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.LearnedWordMapper;
import com.heulwen.pojo.LearnedWord;
import com.heulwen.repositories.LearnedWordRepository;
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
public class LearnedWordService {

    LearnedWordRepository learnedWordRepository;

    LearnedWordMapper learnedWordMapper;

    public LearnedWordResponse addLearnedWord(LearnedWordRequest request) {
        if (learnedWordRepository.existsByUserId_IdAndVocabularyId_Id(request.getUserId(), request.getVocabularyId())){
            throw new AppException(ErrorCode.WORD_ALREADY_LEARNED);
        }
        LearnedWord learnedWord = learnedWordMapper.toLearnedWord(request);
        LearnedWord saved = learnedWordRepository.save(learnedWord);
        return learnedWordMapper.toLearnedWordResponse(saved);
    }
    
    public List<LearnedWordResponse> getLearnedWordsByUser(Integer userId){
        List<LearnedWord> learnedWords = learnedWordRepository.getLearnedWordByUserId_Id(userId);
        return learnedWords.stream().map(learnedWordMapper::toLearnedWordResponse).toList();
    }
}
