	package com.example.chat_App.service;
	
	import java.util.ArrayList;
	import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.springframework.kafka.annotation.KafkaListener;
	import org.springframework.stereotype.Service;
	
	import com.example.chat_App.model.ChatMessage;
	
	@Service
	public class KafkaConsumerService {
		
		private final List<ChatMessage> messageHistory = new ArrayList<>();
	    private final List<Consumer<ChatMessage>> listeners = new CopyOnWriteArrayList<>();

		
		@KafkaListener(topics = "chat-app", groupId = "group_id")
		public void consume(String message) {
			//Since our kafka producer has "username: " message format
			String[] parts = message.split(": ",2);
			//if parts has length 1 it means we dont have sender or message info
			String sender = parts.length > 1  ? parts[0] : "Unknown";
			//here if parts has length 1 we are lacking message it just assign username
			String content = parts.length > 1 ? parts[1] : parts[0];
			
			ChatMessage chatMessage = new ChatMessage(sender, content);
			messageHistory.add(chatMessage);
			
			// Notify all subscribed sessions of the new message
			for (Consumer<ChatMessage> listener : listeners) {
	            listener.accept(chatMessage);
	        }
			
			
			System.out.println("Consumed message: " + message);
		}
		
		public void addListener(Consumer<ChatMessage> listener) {
			listeners.add(listener);
		    System.out.println("Listener added: " + listener);

		}
		
		public void removeListener(Consumer<ChatMessage> listener) {
		    boolean removed = listeners.remove(listener);
		    if (removed) {
		        System.out.println("Listener removed successfully.");
		    } else {
		        System.out.println("Listener not found.");
		    }
		}

		
		public List<ChatMessage> getMessageHistory(){
			return messageHistory;
		}
		
	}
