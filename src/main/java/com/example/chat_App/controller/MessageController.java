package com.example.chat_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat_App.model.ChatMessage;
import com.example.chat_App.service.KafkaConsumerService;
import com.example.chat_App.service.KafkaProducerService;
import com.example.chat_App.service.SessionService;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping
	public String sendMessage(@RequestParam("message") String message) {
		String username = sessionService.getUsername();
		String userMessage = username + ": " + message;
		kafkaProducerService.sendMessage(userMessage);
		sessionService.addMessage(new ChatMessage(username, message));
		return "Message sent: " + message;
	}
	
	 @GetMapping("/history")
	 public List<ChatMessage> getMessageHistory() {
	    return kafkaConsumerService.getMessageHistory();
	}
	
}
