package com.example.chat_App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.chat_App.model.ChatMessage;

@Service
@SessionScope
public class SessionService {
	
	private String username;
	private List<ChatMessage> messages = new ArrayList<>();
	
	public void createSession(String username) {
		this.username = username;
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

}
