package com.example.chat_App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chat_App.service.KafkaConsumerService;

@Controller
public class UserController {

	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@GetMapping("/onlineUsers")
	@ResponseBody
	public List<String> getonlineUsers(){
		return kafkaConsumerService.getonlineUsers();
	}
}
