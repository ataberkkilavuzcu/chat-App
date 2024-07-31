package com.example.chat_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chat_App.model.ChatMessage;
import com.example.chat_App.service.KafkaConsumerService;
import com.example.chat_App.service.KafkaProducerService;
import com.example.chat_App.service.SessionService;

@Controller
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/message")
    public String showChatPage(Model model) {
        String username = sessionService.getUsername();
        List<ChatMessage> messages = sessionService.getMessages();

        model.addAttribute("username", username);
        model.addAttribute("messages", messages);

        return "chat";
    }

    @PostMapping("/message")
    public String sendMessage(@RequestParam("message") String message, Model model) {
        String username = sessionService.getUsername();
        String userMessage = username + ": " + message;
        kafkaProducerService.sendMessage(userMessage);

        List<ChatMessage> messages = sessionService.getMessages();

        model.addAttribute("username", username);
        model.addAttribute("messages", messages);

        System.out.println("Message sent: " + message);
        return "chat";
    }

    @GetMapping("/history")
    @ResponseBody
    public List<ChatMessage> getMessageHistory() {
       	return sessionService.getMessages();
    }
    
	@PostMapping("/leave")
	public String leaveChat() {
		String username = sessionService.getUsername();
		if(username != null) {
			kafkaProducerService.sendMessage("System: "+username+" has left the chat.");
			kafkaConsumerService.removeonlineUser(username);
			sessionService.clearSession();
		}
		return "join";
	}
   
}
