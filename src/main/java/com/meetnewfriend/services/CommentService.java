package com.meetnewfriend.services;

import com.meetnewfriend.entities.CommentEntity;

public interface CommentService {
	//add comment on post
	public CommentEntity addComment(CommentEntity comment);
	
	//delete post comments
	public void deletePost(int postId);
}
