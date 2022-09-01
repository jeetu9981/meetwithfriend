package com.meetnewfriend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.repository.PostRepo;

@Service
public class PostService {
	@Autowired
	private PostRepo postRepo;
	
	public PostEntity addPost(PostEntity postEntity){
		return this.postRepo.save(postEntity);
	}
	
	public List<PostEntity> get(int id){
		return this.postRepo.findByUserId(id);
	}
	
	public List<PostEntity> getAllPost(ArrayList<Integer> usersId){
		ArrayList<PostEntity> user=new ArrayList<PostEntity>();
		for(int id:usersId) {
			List<PostEntity> p=(List<PostEntity>) this.postRepo.findByUserId(id);
			user.addAll(p);
		}
		return user;
	}
}
