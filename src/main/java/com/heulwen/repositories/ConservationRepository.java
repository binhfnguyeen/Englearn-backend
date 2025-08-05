/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.repositories;

import com.heulwen.pojo.Conservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
public interface ConservationRepository extends JpaRepository<Conservation, Integer>{
    @Query("SELECT c FROM Conservation c WHERE c.userId.id = :userId")
    List<Conservation> getConservationsByUserId(@Param("userId") int userId);
}
