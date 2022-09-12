package com.meetnewfriend.services;

import org.springframework.stereotype.Service;

import com.meetnewfriend.dto.MessageDto;

@Service
public interface MessagesService {
	public boolean addMessage(int senderId,int recieverId,String message);
	
	public MessageDto getSingleUserMessages(int senderId,int recieverId);
}
