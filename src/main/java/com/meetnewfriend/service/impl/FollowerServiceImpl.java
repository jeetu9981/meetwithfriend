package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.FollowerRepo;
import com.meetnewfriend.services.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService{
	@Autowired
	private FollowerRepo followerRepo;
	
	@Autowired
	private FollowingServiceImpl followingServiceImpl;

	@Autowired
	RealFollowerServiceImpl realFollowerServiceImpl;
	
	
	//add follow request
	public String addRequest(int userId,int sesssionUserId){
		Follower follower =null;
		
		User user = new User();
		user.setId(sesssionUserId);
		
		follower=new Follower();
		follower.setAccept(false);
		follower.setFollowBack(false);
		follower.setSendUserRequest(user);
		follower.setAcceptUser(userId);
		if(this.followerRepo.save(follower)!=null)
			return "success";
		else
			return "fail";
		
	}
	
	//get all request which is sent by user to the follow
	public List<Follower> getRequest(int id) {
		boolean accept=false;
		boolean follow=false;
		return this.followerRepo.findByUserIdAndAccept(id,accept,follow);
	}
	
	public Follower getFollowerRequest(int userId,int acceptUser){
		return this.followerRepo.findByUserIdAndAcceptAndSendUser(userId,acceptUser);
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
		int i=this.followerRepo.updateAcceptRequest(a,acceptUser,userId);
		if(i>0) {
			Follower follower=this.followerRepo.findByUserIdAndAcceptAndSendUser(userId, acceptUser);
			if(follower.getAccept() && follower.getFollowBack()) {
				this.followerRepo.deleteByUserIdAndFollowerId(acceptUser, userId);
			}
			RealFollower realFollower = new RealFollower();
			User user = new User();
			user.setId(userId);
			realFollower.setFollower(user);
			realFollower.setUser_id(acceptUser);

			Following following = new Following();
			User user1 = new User();
			user1.setId(acceptUser);
			
			following.setFollowing(user1);
			following.setUser_id(userId);
			
			//here we add following of one user and add increase follower of one user
			if (this.followingServiceImpl.addfollowing(following) != null && this.realFollowerServiceImpl.addFollower(realFollower) != null) 
				return 1;
		}
		return 0;
	}
	
	//check request already sent or not in follow request
	public Follower checkExixtOrNot(int userId,int acceptUser){
		User user=new User();
		user.setId(userId);
		return this.followerRepo.findByUser_idAndfollower(acceptUser,user);
	}
	
	//followback
	@Transactional
	public boolean saveFollower(int acceptUser,int userId) {
//		RealFollower realFollower = new RealFollower();
//		User user = new User();
//		user.setId(acceptUser);
//		realFollower.setFollower(user);
//		realFollower.setUser_id(userId);

//		Following following = new Following();
//		User user1 = new User();
//		user1.setId(userId);
//		following.setFollowing(user1);
//		following.setUser_id(acceptUser);

		//here we add following of one user and add increase follower of one user and delete follow request
//		if (this.followingServiceImpl.addfollowing(following) != null&& this.realFollowerServiceImpl.addFollower(realFollower) != null) {
//			this.deleteRequest(acceptUser, userId);
//			return true;
//		}
		
		if(this.followerRepo.deleteByUserIdAndFollowerId(acceptUser, userId)>0)
		{
			System.out.println("Deleted.............");
			Follower follower =new Follower();
			User user = new User();
			user.setId(acceptUser);
			
			follower.setAccept(false);
			follower.setFollowBack(true);
			follower.setSendUserRequest(user);
			follower.setAcceptUser(userId);
			if(this.followerRepo.save(follower)!=null)
				return true;
		}
		return false;
	}
}
























