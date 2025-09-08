/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.repositories;

import com.heulwen.pojo.Exercise;
import com.heulwen.pojo.ExerciseType;
import com.heulwen.pojo.Vocabulary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{
    List<Exercise> findByVocabularyId(Vocabulary vocab);
    boolean existsByVocabularyId_IdAndExerciseType(Integer vocabularyId, ExerciseType exerciseType);
}
