package com.meetnewfriend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.entity.Post;

public interface PostService {
	//this is for add post
	public Post addPost(Post post,MultipartFile image,int userId);
	
	//here we get all post of particular users
	public List<Post> get(int id);
	
	//get all posts of our all followers
	public List<Post> getAllPost(ArrayList<Integer> usersId);
	
	//get for single post
	public Post getSinglePost(int postId);
	
	//delete post
	public void deletePost(int postId,int userId);
}
