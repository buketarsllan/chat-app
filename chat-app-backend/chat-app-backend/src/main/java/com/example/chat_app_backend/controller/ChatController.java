package com.example.chat_app_backend.controller;

import com.example.chat_app_backend.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class ChatController {
    // Sends messages to the specific chat room
    @MessageMapping("/chat/{roomId}/sendMessage")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage(@DestinationVariable String roomId, ChatMessage chatMessage) {
        chatMessage.setRoomId(roomId);
        return chatMessage;
    }

    // Adds a user to the specified room
    @MessageMapping("/chat/{roomId}/addUser")
    @SendTo("/topic/{roomId}")
    public ChatMessage addUser(@DestinationVariable String roomId, ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() == null) {
            headerAccessor.setSessionAttributes(new HashMap<>());
        }
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomId", roomId);

        chatMessage.setType(ChatMessage.MessageType.JOIN);
        chatMessage.setRoomId(roomId);
        chatMessage.setContent(chatMessage.getSender() + " JOINED room " + roomId);
        return chatMessage;
    }
}