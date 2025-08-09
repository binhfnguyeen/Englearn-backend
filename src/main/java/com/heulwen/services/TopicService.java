/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.TopicRequest;
import com.heulwen.dto.response.VocabTopicResponse;
import com.heulwen.dto.response.TopicResponse;
import com.heulwen.dto.response.VocabularyResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.TopicMapper;
import com.heulwen.mapper.VocabularyMapper;
import com.heulwen.pojo.Topic;
import com.heulwen.pojo.Vocabulary;
import com.heulwen.repositories.TopicRepository;
import com.heulwen.repositories.VocabularyRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TopicService {
    
    TopicRepository topicRepository;
    VocabularyRepository vocabularyRepository;
    TopicMapper topicMapper;
    VocabularyMapper vocabularyMapper;
    
    public TopicResponse addOrUpdateTopic(TopicRequest request){
        Topic topic;
        
        if (request.getId() != null){
            topic = topicRepository.findById(request.getId()).orElseThrow(()->new AppException(ErrorCode.TOPIC_NOT_FOUND));
            topicMapper.updateTopicFromRequest(request, topic);
        } else {
            topic = topicMapper.toTopic(request);
        }
        
        Topic saved = topicRepository.save(topic);
        return topicMapper.toTopicResponse(saved);
    }
    
    public Page<TopicResponse> getTopics(String keyword, Pageable pageable){
        Page<Topic> topicResult;
        if (keyword != null && !keyword.isEmpty()){
            topicResult = topicRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            topicResult = topicRepository.findAll(pageable);
        }
        return topicResult.map(topicMapper::toTopicResponse);
    }
    
    public TopicResponse getTopicById(int id){
        Topic topic = topicRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.TOPIC_NOT_FOUND));
        return topicMapper.toTopicResponse(topic);
    }
    
    public void deleteTopic(int id){
        topicRepository.deleteById(id);
    }
    
    public VocabTopicResponse addVocabToTopic(int topicId, int vocabId){
        Vocabulary vocab = vocabularyRepository.findById(vocabId).orElseThrow(()->new AppException(ErrorCode.VOCAB_NOT_FOUND));
        Topic topic = topicRepository.findById(topicId).orElseThrow(()->new AppException(ErrorCode.TOPIC_NOT_FOUND));
        
        if (vocab.getTopicSet() == null){
            vocab.setTopicSet(new HashSet<>());
        }
        
        vocab.getTopicSet().add(topic);
        vocabularyRepository.save(vocab);
        
        return VocabTopicResponse.builder()
                .vocabId(vocab)
                .topicId(topic)
                .build();
    }
    
    public Page<VocabularyResponse> getVocabulariesWithTopic(int topicId, String keyword, Pageable pageable){
        String searchKeyword = (keyword == null) ? "": keyword;
        Page<Vocabulary> vocabPage = vocabularyRepository.findByTopicAndKeyword(topicId, searchKeyword, pageable);
        
        return vocabPage.map(vocabularyMapper::toVocabularyResponse);
    }
    
    public List<VocabularyResponse> getVocabNotInTopic(int topicId, String keyword){
        String searchKeyword = (keyword == null) ? "": keyword;
        List<Vocabulary> vocab = vocabularyRepository.findVocabNotInTopic(topicId, searchKeyword);
        return vocab.stream().map(vocabularyMapper::toVocabularyResponse).toList();
    }
}
