package com.example.chat_App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.chat_App.model.ChatMessage;

@Service
@SessionScope
public class SessionService {

    private String username;
    private List<ChatMessage> messages = new ArrayList<>();

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    public void createSession(String username) {
        this.username = username;
        this.messages = new ArrayList<>();

        // Register as a listener to the KafkaConsumerService
        kafkaConsumerService.addListener(this::addMessage);
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
        this.username = null;
        this.messages.clear();

        // Unregister from the KafkaConsumerService
        kafkaConsumerService.removeListener(this::addMessage);
    }
}
