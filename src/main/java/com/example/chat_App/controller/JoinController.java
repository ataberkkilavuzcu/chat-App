package com.example.chat_App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chat_App.model.ChatMessage;
import com.example.chat_App.service.KafkaConsumerService;
import com.example.chat_App.service.KafkaProducerService;
import com.example.chat_App.service.SessionService;

@Controller
public class JoinController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@Autowired
	private SessionService sessionService;
	
	
	@GetMapping("/join")
	public String showJoinPage() {
		return "join";
	}
	
	@PostMapping("/join")
	public String joinChat(@RequestParam("username") String username, Model model) {
		sessionService.createSession(username);
		
		String joinMessage = username + " has joined the chat.";
		kafkaProducerService.sendMessage("System: " + joinMessage);
		
		model.addAttribute("username",username);
		model.addAttribute("messages", sessionService.getMessages());
		
		System.out.println("Welcome "+username);
		return "redirect:/message"; 
	}
	
	@PostMapping("/leave")
	public String leaveChat() {
		String username = sessionService.getUsername();
		if(username != null) {
			kafkaProducerService.sendMessage("System: "+username+" has left the chat.");
			sessionService.clearSession();
		}
		return username + " has left the chat.";
	}
	
}
