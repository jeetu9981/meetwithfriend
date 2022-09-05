package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entities.FollowerEntity;

public interface FollowerService {
	//add follow request
	public FollowerEntity addRequest(FollowerEntity entity);
	
	//get all request which is sent by user to the follow
	public List<FollowerEntity> getRequest(int id);
	
	//delete follow request of other user
	public int deleteRequest(int acceptUser,int userId);
	
	//accept request
	public int accept(int acceptUser,int userId);
	
	//check request already sent or not in follow request
	public FollowerEntity checkExixtOrNot(int userId,int acceptUser);
		
		
}
