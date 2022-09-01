package com.meetnewfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.FollowingRepo;

@Service
public class FollowingService {
	@Autowired
	private FollowingRepo followingRepo;
	
	@Transactional
	public FollowingEntity addfollowing(FollowingEntity entity){
		return this.followingRepo.save(entity);
	}
	
	public int countFollowing(int id) {
		return this.followingRepo.countFollowing(id);
	}
	
	public List<FollowingEntity> getFollwing(int userId){
		return (List<FollowingEntity>) this.followingRepo.findFollowings(userId);
	}
	
	@Transactional
	public int delete(int following,int userId) {
		UserEntity follow=new UserEntity();
		follow.setId(following);
		
		return this.followingRepo.deleteFollowing(follow,userId);
	}
}
