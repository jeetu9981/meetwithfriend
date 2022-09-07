package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.FollowerRepo;
import com.meetnewfriend.repository.FollowingRepo;
import com.meetnewfriend.services.FollowingService;

@Service
public class FollowingServiceImpl implements FollowingService{
	@Autowired
	private FollowingRepo followingRepo;
	
	@Autowired
	private FollowerServiceImpl followerServiceImpl;
	
	
	@Autowired
	private RealFollowerServiceImpl realFollowerService;
	
	@Autowired 
	private FollowerRepo followerRepo;
	
	//increase following of user
	@Transactional
	public Following addfollowing(Following entity){
		return this.followingRepo.save(entity);
	}
	
	
	//this method is use for count following
	public int countFollowing(int id) {
		return this.followingRepo.countFollowing(id);
	}
	
	
	//get all following of user
	public List<Following> getFollwing(int userId){
		return (List<Following>) this.followingRepo.findFollowings(userId);
	}
	
	//delete follower
	@Transactional
	public int delete(int following,int userId) {
		User follow=new User();
		follow.setId(following);
		
		if(this.followingRepo.deleteFollowing(follow,userId)>0) {
			//if both are not following each other then if execute and inside if from follow request we delete request from other user
			if(this.followerServiceImpl.deleteRequest(following,userId)>0) {
				
				//delete follower from onother user
				this.realFollowerService.deleteRealFollower(following,userId);
				
			}else {
				//delete follower from other person and add this follower in follow back request
				this.realFollowerService.deleteRealFollower(following,userId);
				System.out.println("Follow Request : "+following);
				System.out.println("Real User : "+userId);
				Follower follower = new Follower();
				User user = new User();
				user.setId(following);
				follower.setAccept(true);
				follower.setFollowBack(false);
				follower.setSendUserRequest(user);
				follower.setAcceptUser(userId);
				
				//we add this request in follow back request only
				this.followerRepo.save(follower);
			}
			return 1;
		}
		return 0;
	}


	@Transactional
	public int deleteFollowing(int blockUser, int realUser) {
		return this.followingRepo.deleteByBlockUser(blockUser,realUser);
	}
}
