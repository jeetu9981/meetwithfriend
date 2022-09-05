package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;

public interface LikeService {
	//to check like or not before
	public LikeEntity checkAlreadyLike(PostEntity postId,UserEntity likeUser,int realUser);
	
	//to add like on post
	public LikeEntity addLike(LikeEntity like);
	
	//get posts
	public List<LikeEntity> getPosts(int user);
	
	//delete like which like before
	public int delete(UserEntity user,PostEntity post,int realUser);
	
	//delete likes of post
	public void deletePost(int postId);
}
