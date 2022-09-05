package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.FollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.FollowerRepo;
import com.meetnewfriend.services.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService{
	@Autowired
	private FollowerRepo followerRepo;
	
	
	//add follow request
	public FollowerEntity addRequest(FollowerEntity entity){
		return this.followerRepo.save(entity);
	}
	
	//get all request which is sent by user to the follow
	public List<FollowerEntity> getRequest(int id) {
		boolean accept=false;
		boolean follow=false;
		return this.followerRepo.findByUserIdAndAccept(id,accept,follow);
	}
	
	//delete follow request of other user
	@Transactional
	public int deleteRequest(int acceptUser,int userId){
		return this.followerRepo.deleteByUserIdAndFollowerId(acceptUser,userId);
	}
	
	//accept request
	@Transactional
	public int accept(int acceptUser,int userId){
		boolean a=true;
		return this.followerRepo.updateAcceptRequest(a,acceptUser,userId);
	}
	
	//check request already sent or not in follow request
	public FollowerEntity checkExixtOrNot(int userId,int acceptUser){
		UserEntity userEntity=new UserEntity();
		userEntity.setId(userId);
		return this.followerRepo.findByUser_idAndfollower(acceptUser,userEntity);
	}
}
