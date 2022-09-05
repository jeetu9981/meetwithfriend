package com.meetnewfriend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.PostRepo;
import com.meetnewfriend.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepo postRepo;
	
	
	//this is for add post
	public PostEntity addPost(PostEntity postEntity){
		return this.postRepo.save(postEntity);
	}
	
	
	//here we get all post of particular users
	public List<PostEntity> get(int id){
		return this.postRepo.findByUserId(id);
	}
	
	
	//get all posts of our all followers
	public List<PostEntity> getAllPost(ArrayList<Integer> usersId){
		ArrayList<PostEntity> user=new ArrayList<PostEntity>();
		for(int id:usersId) {
			List<PostEntity> p=(List<PostEntity>) this.postRepo.findByUserId(id);
			user.addAll(p);
		}
		return user;
	}
	
	//get for single post
	public PostEntity getSinglePost(int postId) {
		return this.postRepo.findById(postId).get();
	}
	
	//delete post
	@Transactional
	public void deletePost(int postId,int userId){
		PostEntity post=new PostEntity();
		post.setId(postId);
		
		UserEntity user=new UserEntity();
		user.setId(userId);
		
		this.postRepo.deletePost(post,user);
	}
}
