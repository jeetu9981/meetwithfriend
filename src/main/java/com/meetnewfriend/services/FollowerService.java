package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entity.Follower;

public interface FollowerService {
	//add follow request
	public String addRequest(int userId,int sesssionUserId);
	
	//get all request which is sent by user to the follow
	public List<Follower> getRequest(int id);
	
	//delete follow request of other user
	public int deleteRequest(int acceptUser,int userId) throws Exception;
	
	//accept request
	public int accept(int acceptUser,int userId) throws Exception;
	
	//check request already sent or not in follow request
	public Follower checkExixtOrNot(int userId,int acceptUser);
		
	//follow back
	public boolean saveFollower(int acceptUser,int userId) throws Exception;
	
	public Follower getFollowerRequest(int userId,int acceptUser);
}
