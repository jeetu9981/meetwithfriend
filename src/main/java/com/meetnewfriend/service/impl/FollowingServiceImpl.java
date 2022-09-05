package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.FollowingRepo;
import com.meetnewfriend.services.FollowingService;

@Service
public class FollowingServiceImpl implements FollowingService{
	@Autowired
	private FollowingRepo followingRepo;
	
	//increase following of user
	@Transactional
	public FollowingEntity addfollowing(FollowingEntity entity){
		return this.followingRepo.save(entity);
	}
	
	
	//this method is use for count following
	public int countFollowing(int id) {
		return this.followingRepo.countFollowing(id);
	}
	
	
	//get all following of user
	public List<FollowingEntity> getFollwing(int userId){
		return (List<FollowingEntity>) this.followingRepo.findFollowings(userId);
	}
	
	//delete follower
	@Transactional
	public int delete(int following,int userId) {
		UserEntity follow=new UserEntity();
		follow.setId(following);
		
		return this.followingRepo.deleteFollowing(follow,userId);
	}
}
