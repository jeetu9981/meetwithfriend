package com.meetnewfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.FollowerEntity;
import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.FollowerRepo;
import com.meetnewfriend.repository.FollowingRepo;

@Service
public class FollowerService {
	@Autowired
	private FollowerRepo followerRepo;
	
	public FollowerEntity addRequest(FollowerEntity entity){
		return this.followerRepo.save(entity);
	}
	
	public List<FollowerEntity> getRequest(int id) {
		boolean accept=false;
		boolean follow=false;
		return this.followerRepo.findByUserIdAndAccept(id,accept,follow);
	}
	
	@Transactional
	public int deleteRequest(int acceptUser,int userId){
		return this.followerRepo.deleteByUserIdAndFollowerId(acceptUser,userId);
	}
	
	@Transactional
	public int accept(int acceptUser,int userId){
		boolean a=true;
		return this.followerRepo.updateAcceptRequest(a,acceptUser,userId);
	}
	
	public FollowerEntity checkExixtOrNot(int userId,int acceptUser){
		UserEntity userEntity=new UserEntity();
		userEntity.setId(userId);
		return this.followerRepo.findByUser_idAndfollower(acceptUser,userEntity);
	}
}
