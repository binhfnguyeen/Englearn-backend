/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.repositories;

import com.heulwen.pojo.Answer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    List<Answer> getAnswerByTestResultsId_Id(Integer testResultsId);
}
