package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.repository.RealFollowerRepo;
import com.meetnewfriend.services.RealFollowerService;

@Service
public class RealFollowerServiceImpl implements RealFollowerService{
	@Autowired
	private RealFollowerRepo realFollowerRepo;
	
	
	//increase follower of one user
	public RealFollower addFollower(RealFollower entity) throws Exception{
		return this.realFollowerRepo.save(entity);
	}
	
	
	// this method use for count followers
	public int countFollower(int id){
		return this.realFollowerRepo.countFollower(id);
	}
	
	
	//already following or not
	public RealFollower checkExistOrNot(int userId,int acceptUser){
		return this.realFollowerRepo.findByUser_idAndfollower(acceptUser,userId);
	}
	
	
	//get user followers
	public List<RealFollower> getFollower(int userId){
		return this.realFollowerRepo.findAllById(userId);
	}
	
	//we reduce follower of user
	@Transactional
	public int deleteRealFollower(int userId,int follower) {
		return this.realFollowerRepo.deleteRealFollower(userId,follower);
	}
}
