package com.example.chat_App.service;

import java.util.List;
import java.util.function.Consumer;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.chat_App.model.ChatMessage;

@Service
@SessionScope
public class SessionService {

    private String username;
    private List<ChatMessage> messages = new ArrayList<>();
    private Consumer<ChatMessage> listener;  // Store listener reference

    
    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    public void createSession(String username) {
        this.username = username;
        this.messages = new ArrayList<>();
        
        // Register as a listener to the KafkaConsumerService
        this.listener = this::addMessage;
        kafkaConsumerService.addListener(this.listener);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void clearSession() {
        // Unregister from the KafkaConsumerService using the stored listener reference
        if (this.listener != null) {
            kafkaConsumerService.removeListener(this.listener);
            this.listener = null;  // Clear listener reference after removal
        }
        this.username = null;
        this.messages.clear();
    }
}
