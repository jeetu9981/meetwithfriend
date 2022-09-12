package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entity.RecieverMessages;

public class MessageDto {
	List<SenderReciverMessageDto> sender;
	List<SenderReciverMessageDto> reciever;
	public List<SenderReciverMessageDto> getSender() {
		return sender;
	}
	public void setSender(List<SenderReciverMessageDto> sender) {
		this.sender = sender;
	}
	public List<SenderReciverMessageDto> getReciever() {
		return reciever;
	}
	public void setReciever(List<SenderReciverMessageDto> reciever) {
		this.reciever = reciever;
	}
	
	
	
}
