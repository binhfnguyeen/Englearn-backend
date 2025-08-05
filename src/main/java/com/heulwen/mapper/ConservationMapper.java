/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.request.ConservationCreationRequest;
import com.heulwen.dto.response.ConservationCreationResponse;
import com.heulwen.dto.response.ConservationResponse;
import com.heulwen.pojo.Conservation;
import com.heulwen.resolvers.UserResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring", uses = {UserResolver.class, MessageMapper.class})
public interface ConservationMapper {
    @Mapping(source = "userId", target = "userId")
    Conservation toConservation(ConservationCreationRequest request);
    ConservationCreationResponse toConservationCreationResponse(Conservation conservation);
    ConservationResponse toConservationResponse(Conservation conservation);
}
