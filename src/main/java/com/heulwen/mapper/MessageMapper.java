/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.heulwen.mapper;

import com.heulwen.dto.response.MessageResponse;
import com.heulwen.pojo.Message;
import org.mapstruct.Mapper;

/**
 *
 * @author Dell
 */
@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageResponse toMessageResponse(Message message);
}
