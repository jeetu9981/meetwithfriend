package com.meetnewfriend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.LikeRepo;
import com.meetnewfriend.services.LikeService;

@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	private LikeRepo likeRepo;
	
	//to check like or not before
	public LikeEntity checkAlreadyLike(PostEntity postId,UserEntity likeUser,int realUser) {
		return this.likeRepo.findByPostAndUserAndRealuser(postId,likeUser,realUser);
	}
	
	//to add like on post
	public LikeEntity addLike(LikeEntity like) {
		return this.likeRepo.save(like);
	}
	
	//get posts
	public List<LikeEntity> getPosts(int user){
		return this.likeRepo.findByRealuser(user);
	}
	
	
	//delete like which like before
	@Transactional
	public int delete(UserEntity user,PostEntity post,int realUser){
		return this.likeRepo.deleteLike(user,post,realUser);
	}
	
	//delete likes of post
	@Transactional
	public void deletePost(int postId) {
		PostEntity post=new PostEntity();
		post.setId(postId);
		this.likeRepo.deletePost(post);
	}
}
