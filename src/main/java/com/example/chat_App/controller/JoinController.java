package com.example.chat_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat_App.model.ChatMessage;
import com.example.chat_App.service.KafkaProducerService;
import com.example.chat_App.service.SessionService;

@RestController
@RequestMapping("/join")
public class JoinController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Autowired
	private SessionService sessionService;
	
	@PostMapping
	public String joinChat(@RequestParam("username") String username) {
		sessionService.createSession(username);
		String joinMessage = username + " has joined the chat.";
		kafkaProducerService.sendMessage("System: " + joinMessage);
		sessionService.addMessage(new ChatMessage("System", joinMessage));
		return "Welcome "+ username;
	}
	
}
