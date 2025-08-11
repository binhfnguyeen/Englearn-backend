/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.repositories;

import com.heulwen.pojo.TestResults;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
public interface TestResultsRepository extends JpaRepository<TestResults, Integer>{
    List<TestResults> findByUserId_IdAndTestId_Id(Integer userId, Integer testId);
}
