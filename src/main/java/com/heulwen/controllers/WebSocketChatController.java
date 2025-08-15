/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.controllers;

import com.heulwen.dto.request.ChatRequest;
import com.heulwen.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Dell
 */
@Controller
@RequiredArgsConstructor
public class WebSocketChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/chat/{conversationId}")
    public void chat(@DestinationVariable String conversationId, ChatRequest request){
        String reply = chatService.chat(request, conversationId);
        messagingTemplate.convertAndSend("/topic/conversation/" + conversationId, reply);
    }
}
