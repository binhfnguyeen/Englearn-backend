/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.ConservationCreationRequest;
import com.heulwen.dto.request.MessageRequest;
import com.heulwen.dto.response.ConservationCreationResponse;
import com.heulwen.dto.response.ConservationResponse;
import com.heulwen.dto.response.MessageResponse;
import com.heulwen.exceptions.AppException;
import com.heulwen.exceptions.ErrorCode;
import com.heulwen.mapper.ConservationMapper;
import com.heulwen.pojo.Conservation;
import com.heulwen.pojo.Message;
import com.heulwen.repositories.ConservationRepository;
import com.heulwen.repositories.MessageRepository;
import java.util.Date;
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
public class ConservationService {

    ConservationRepository conservationRepository;
    MessageRepository messageRepository;
    ConservationMapper conservationMapper;

    public ConservationCreationResponse createConservation(ConservationCreationRequest request) {
        Conservation conservation = conservationMapper.toConservation(request);
        Conservation saved = conservationRepository.save(conservation);
        return conservationMapper.toConservationCreationResponse(saved);
    }

    public List<ConservationResponse> getConservationsByUserId(int userId) {
        List<Conservation> conservations = conservationRepository.getConservationsByUserId(userId);
        return conservations.stream()
                .map(conservation -> {
                    ConservationResponse response = conservationMapper.toConservationResponse(conservation);
                    List<MessageResponse> messages = conservation.getMessageSet().stream()
                            .map(msg -> MessageResponse.builder()
                            .id(msg.getId())
                            .senderRole(msg.getSenderRole())
                            .content(msg.getContent())
                            .sentAt(msg.getSentAt())
                            .build())
                            .toList();

                    response.setMessages(messages);
                    return response;
                }).toList();
    }

    public ConservationResponse getConservationById(int id) {
        Conservation conservation = conservationRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CONSERVATION_NOT_FOUND));
        ConservationResponse response = conservationMapper.toConservationResponse(conservation);
        List<MessageResponse> messages = conservation.getMessageSet().stream()
                .map(msg -> MessageResponse.builder()
                .id(msg.getId())
                .senderRole(msg.getSenderRole())
                .content(msg.getContent())
                .sentAt(msg.getSentAt())
                .build())
                .toList();

        response.setMessages(messages);
        return response;
    }

    public ConservationResponse addMessageToConservation(MessageRequest request, int id) {
        Conservation conservation = conservationRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CONSERVATION_NOT_FOUND));

        Message message = new Message();
        message.setSenderRole(request.getSenderRole());
        message.setContent(request.getContent());
        message.setSentAt(new Date());
        message.setConservationId(conservation);

        messageRepository.save(message);
        
        conservation = conservationRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.CONSERVATION_NOT_FOUND));

        ConservationResponse response = conservationMapper.toConservationResponse(conservation);
        List<MessageResponse> messages = conservation.getMessageSet().stream()
                .map(msg -> MessageResponse.builder()
                .id(msg.getId())
                .senderRole(msg.getSenderRole())
                .content(msg.getContent())
                .sentAt(msg.getSentAt())
                .build())
                .toList();
        response.setMessages(messages);
        return response;
    }
}
