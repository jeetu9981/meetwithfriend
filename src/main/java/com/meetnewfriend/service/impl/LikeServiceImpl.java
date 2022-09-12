package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.Like;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.LikeRepo;
import com.meetnewfriend.services.LikeService;

@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	private LikeRepo likeRepo;
	
	//to check like or not before
	public Like checkAlreadyLike(Post postId,User likeUser,int realUser) {
		return this.likeRepo.findByPostAndUserAndRealuser(postId,likeUser,realUser);
	}
	
	//to add like on post
	public boolean addLike(int likeUser,int postId,int userId) {
		Post post=new Post();
		post.setId(postId);
		User user=new User();
		user.setId(userId);
		
		//this api ceck if it already like or not
		Like like=this.checkAlreadyLike(post,user,userId);
		
		if(like==null) {
			like =new Like();
			like.setUser(user);
			like.setPost(post);
			like.setStatus(true);
			like.setRealuser(likeUser);
			if(this.likeRepo.save(like)!=null)
				return true;
		}
		return false;
	}
	
	//get posts
	public List<Like> getPosts(int user){
		return this.likeRepo.findByRealuser(user);
	}
	
	
	//delete like which like before
	@Transactional(rollbackFor = Exception.class)
	public int delete(int userId,int postId,int realUser,int likeUser){
		Post post=new Post();
		post.setId(postId);
		User user=new User();
		user.setId(likeUser);
		if(this.likeRepo.deleteLike(user,post,realUser)>0) {
			return 1;
		}
		return 0;
	}
	
	//delete likes of post
	@Transactional
	public void deletePost(int postId) {
		Post post=new Post();
		post.setId(postId);
		this.likeRepo.deletePost(post);
	}
}
