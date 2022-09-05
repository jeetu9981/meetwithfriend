package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entities.RealFollowerEntity;

public interface RealFollowerService {
	//increase follower of one user
	public RealFollowerEntity addFollower(RealFollowerEntity entity);
	
	// this method use for count followers
	public int countFollower(int id);
	
	//already following or not
	public RealFollowerEntity checkExistOrNot(int userId,int acceptUser);
	
	//get user followers
	public List<RealFollowerEntity> getFollower(int userId);
	
	//we reduce follower of user
	public int deleteRealFollower(int userId,int follower);
}
