package com.meetnewfriend.services;

import java.util.ArrayList;
import java.util.List;

import com.meetnewfriend.entities.PostEntity;

public interface PostService {
	//this is for add post
	public PostEntity addPost(PostEntity postEntity);
	
	//here we get all post of particular users
	public List<PostEntity> get(int id);
	
	//get all posts of our all followers
	public List<PostEntity> getAllPost(ArrayList<Integer> usersId);
	
	//get for single post
	public PostEntity getSinglePost(int postId);
	
	//delete post
	public void deletePost(int postId,int userId);
}
