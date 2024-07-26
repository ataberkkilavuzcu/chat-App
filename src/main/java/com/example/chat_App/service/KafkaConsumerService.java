package com.example.chat_App.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.chat_App.model.ChatMessage;

@Service
public class KafkaConsumerService {
	
	private final List<ChatMessage> messageHistory = new ArrayList<>();
	
	@KafkaListener(topics = "chat-app", groupId = "group_id")
	public void consume(String message) {
		//Since our kafka producer has "username: " message format
		String[] parts = message.split(": ",2);
		//if parts has length 1 it means we dont have sender or message info
		String sender = parts.length > 1  ? parts[0] : "Unknown";
		//here if parts has length 1 we are lacking message it just assign username
		String content = parts.length > 1 ? parts[1] : parts[0];
		
		messageHistory.add(new ChatMessage(sender, content));
		System.out.println("Consumed message: " + message);
	}
	
	public List<ChatMessage> getMessageHistory(){
		return messageHistory;
	}
}
