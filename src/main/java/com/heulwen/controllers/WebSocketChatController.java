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
    
    @MessageMapping("/speak/{conversationId}")
    public void speak(@DestinationVariable String conversationId, ChatRequest request){
        String reply = chatService.speak(request, conversationId);
        messagingTemplate.convertAndSend("/topic/conversation/speak/" + conversationId, reply);
    }
    
    @MessageMapping("/assisstant/{conversationId}")
    public void assisstant(@DestinationVariable String conversationId, ChatRequest request){
        String reply = chatService.assisstant(request, conversationId);
        messagingTemplate.convertAndSend("/topic/conversation/assisstant/" + conversationId, reply);
    }
}
