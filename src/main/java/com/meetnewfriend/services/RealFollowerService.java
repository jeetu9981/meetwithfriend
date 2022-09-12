package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entity.RealFollower;

public interface RealFollowerService {
	//increase follower of one user
	public RealFollower addFollower(RealFollower entity) throws Exception;
	
	// this method use for count followers
	public int countFollower(int id);
	
	//already following or not
	public RealFollower checkExistOrNot(int userId,int acceptUser);
	
	//get user followers
	public List<RealFollower> getFollower(int userId);
	
	//we reduce follower of user
	public int deleteRealFollower(int userId,int follower);
}
