package com.meetnewfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.LikeRepo;

@Service
public class LikeService {
	@Autowired
	private LikeRepo likeRepo;
	
	public LikeEntity checkAlreadyLike(PostEntity postId,UserEntity likeUser,int realUser) {
		return this.likeRepo.findByPostAndUserAndRealuser(postId,likeUser,realUser);
	}
	
	public LikeEntity addLike(LikeEntity like) {
		return this.likeRepo.save(like);
	}
	
	public List<LikeEntity> getPosts(int user){
		return this.likeRepo.findByRealuser(user);
	}
	
	
	@Transactional
	public int delete(UserEntity user,PostEntity post){
		return this.likeRepo.deleteLike(user,post);
	}
}
