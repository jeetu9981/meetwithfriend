package com.meetnewfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.RealFollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.RealFollowerRepo;

@Service
public class RealFollowerSErvice {
	@Autowired
	private RealFollowerRepo realFollowerRepo;
	
	public RealFollowerEntity addFollower(RealFollowerEntity entity) {
		return this.realFollowerRepo.save(entity);
	}
	
	public int countFollower(int id){
		return this.realFollowerRepo.countFollower(id);
	}
	
	public List<RealFollowerEntity> getMyFollowerPosts(int userId){
		return this.realFollowerRepo.findAllById(userId);
	}
	
	public RealFollowerEntity checkExistOrNot(int userId,int acceptUser){
		return this.realFollowerRepo.findByUser_idAndfollower(acceptUser,userId);
	}
	
	public List<RealFollowerEntity> getFollower(int userId){
		return this.realFollowerRepo.findAllById(userId);
	}
	
	@Transactional
	public int deleteRealFollower(int userId,int follower) {
		return this.realFollowerRepo.deleteRealFollower(userId,follower);
	}
}
