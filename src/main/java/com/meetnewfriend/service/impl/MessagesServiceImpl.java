package com.meetnewfriend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetnewfriend.dto.MessageDto;
import com.meetnewfriend.dto.SenderReciverMessageDto;
import com.meetnewfriend.entity.RecieverMessages;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.MessageRepo;
import com.meetnewfriend.services.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService{
	
	@Autowired
	private MessageRepo messageRepo;
	
	public boolean addMessage(int recieverId,int senderId,String message){
		User user=new User();
		user.setId(senderId);
		
		RecieverMessages mes=new RecieverMessages();
		mes.setMessage(message);
		mes.setSenderId(user);
		mes.setReciverId(recieverId);
		
		if(this.messageRepo.save(mes)!=null)
			return true;
		return false;
	}
	
	public MessageDto getSingleUserMessages(int recieverId,int senderId) {
		MessageDto dto=new MessageDto();
		User user=new User();
		user.setId(senderId);
		ArrayList<RecieverMessages> reciverMessages=(ArrayList<RecieverMessages>) this.messageRepo.getRecieverMessage(recieverId,user);
		
		user.setId(recieverId);
		ArrayList<RecieverMessages> senderMessages=(ArrayList<RecieverMessages>) this.messageRepo.getSenderMessage(senderId,user);
		
		ArrayList<SenderReciverMessageDto> al=new ArrayList<SenderReciverMessageDto>();
		for(int i=0;i<reciverMessages.size();i++) {
			SenderReciverMessageDto d=new SenderReciverMessageDto();
			d.setMessage(reciverMessages.get(i).getMessage());
			d.setName(reciverMessages.get(i).getSenderId().getName());
			al.add(d);
		}
		
		dto.setReciever(al);
		
		al=new ArrayList<SenderReciverMessageDto>();
		for(int i=0;i<senderMessages.size();i++) {
			SenderReciverMessageDto d=new SenderReciverMessageDto();
			d.setMessage(senderMessages.get(i).getMessage());
			d.setName(senderMessages.get(i).getSenderId().getName());
			al.add(d);
		}
		dto.setSender(al);
		return dto;
	}
}
