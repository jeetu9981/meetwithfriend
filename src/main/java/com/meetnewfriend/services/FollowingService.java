package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entity.Following;

public interface FollowingService {
	//increase following of user
	public Following addfollowing(Following entity);
	
	//this method is use for count following
	public int countFollowing(int id);
	
	//get all following of user
	public List<Following> getFollwing(int userId);
	
	//delete follower
	public int delete(int following,int userId);
	
	public int deleteFollowing(int blockUser,int realUser);
	public List<Following> getFollowing(int userId);
}
