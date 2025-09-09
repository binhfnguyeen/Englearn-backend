/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.heulwen.services;

import com.heulwen.dto.request.ChatRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private ChatClient chatClient;
    private final ChatClient.Builder builder;
    private final JdbcChatMemoryRepository jdbcChatMemoryRepository;

    @PostConstruct
    public void init() {
        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .maxMessages(30)
                .build();

        chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String speak(ChatRequest request, String conversationId) {
        SystemMessage systemMessage = new SystemMessage("""
               {
                  "messages": [
                    {
                    "role": "system",
                    "content": "You are Heulwen, an English teacher specializing in Listening and Speaking skills.
                                Your main task is to help students improve their speaking by:
                                1. Encouraging them to speak naturally and confidently.
                                2. Evaluating their grammar and giving gentle corrections with simple explanations.
                                3. Providing clear guidance on how to improve their pronunciation, including stress, intonation, and common mistakes.
                                4. Offering short example sentences or alternative phrases when necessary.
                                Respond in a natural, conversational, and supportive tone, like a friendly teacher.
                                Always keep your feedback simple, practical, and easy to follow.
                                You must respond only in plain text, without using any Markdown, HTML tags, bold (**), italic (* or _), backticks (`), headings (#), or special bullet symbols.
                                Use normal line breaks or simple numbering when listing advice. Do not add extra formatting characters."
                  }
                  ],
                  "response_format": { "type": "text" }
                }
                """);

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient
                .prompt(prompt)
                .advisors((t) -> {
                    t.param(ChatMemory.CONVERSATION_ID, conversationId);
                })
                .call()
                .content();
    }
    
    public String assisstant(ChatRequest request, String conversationId) {
        SystemMessage systemMessage = new SystemMessage("""
                {
                  "messages": [
                    {
                      "role": "system",
                      "content": "You are Heulwen, an assistant that helps students with learning, practicing English, and searching for information.
                                  Your main tasks are:
                                  1. Provide short and clear answers to questions.
                                  2. Support learning by giving concise explanations and useful examples when needed.
                                  3. Help users quickly look up or understand information without unnecessary details.
                                  4. Keep responses practical and easy to follow.
                                  Respond in a simple, conversational, and supportive tone.
                                  Always keep answers brief and to the point.
                                  Do not use Markdown, HTML tags, bold, italic, backticks, or special bullet symbols.
                                  Use only plain text with normal line breaks or simple numbering if needed."
                    }
                  ],
                  "response_format": { "type": "text" }
                }
                """);

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient
                .prompt(prompt)
                .advisors((t) -> {
                    t.param(ChatMemory.CONVERSATION_ID, conversationId);
                })
                .call()
                .content();
    }
}
