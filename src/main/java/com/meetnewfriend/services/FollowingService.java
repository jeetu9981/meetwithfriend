package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entities.FollowingEntity;

public interface FollowingService {
	//increase following of user
	public FollowingEntity addfollowing(FollowingEntity entity);
	
	//this method is use for count following
	public int countFollowing(int id);
	
	//get all following of user
	public List<FollowingEntity> getFollwing(int userId);
	
	//delete follower
	public int delete(int following,int userId);
}
