package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entity.Like;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;

public interface LikeService {
	//to check like or not before
	public Like checkAlreadyLike(Post postId,User likeUser,int realUser);
	
	//to add like on post
	public boolean addLike(int likeUser,int postId,int userId);
	
	//get posts
	public List<Like> getPosts(int user);
	
	//delete like which like before
	public int delete(int userId,int postId,int realUser,int likeUser);
	
	//delete likes of post
	public void deletePost(int postId);
}
