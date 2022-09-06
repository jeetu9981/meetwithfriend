package com.meetnewfriend.services;

public interface CommentService {
	//add comment on post
	public boolean addComment(int userId,int postId,int commentUser,String comment);
	
	//delete post comments
	public void deletePost(int postId);
}
