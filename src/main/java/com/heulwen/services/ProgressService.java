/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.response.ProgressOverviewResponse;
import com.heulwen.dto.response.ProgressResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.ProgressMapper;
import com.heulwen.pojo.Progress;
import com.heulwen.pojo.User;
import com.heulwen.repositories.LearnedWordRepository;
import com.heulwen.repositories.ProgressRepository;
import com.heulwen.repositories.UserRepository;
import java.time.LocalDate;
import java.sql.Date;
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
public class ProgressService {
    ProgressRepository progressRepository;
    LearnedWordRepository learnedWordRepository;
    UserRepository userRepository;
    ProgressMapper progressMapper;
    
    public ProgressOverviewResponse getProgressOverview(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        long daysStudied = progressRepository.countDistinctDaysByUserId(userId);
        long totalWordsLearned = learnedWordRepository.sumWordsLearnedByUserId(userId);
        
        String level;
        
        if (daysStudied >= 10 && totalWordsLearned >= 50)
            level = "intermediate";
        else if (daysStudied >= 5 && totalWordsLearned >= 20)
            level = "beginner";
        else
            level = "newbie";
        
        return ProgressOverviewResponse.builder()
                .userId(user)
                .daysStudied((int) daysStudied)
                .wordsLearned((int) totalWordsLearned)
                .level(level)
                .build();
    }
    
    public ProgressResponse updateUserLearnedDay(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        Progress progress = new Progress();
        progress.setUserId(user);
        progress.setLearned_date(Date.valueOf(LocalDate.now()));
        
        return progressMapper.toProgressResponse(progressRepository.save(progress));
    }
}
