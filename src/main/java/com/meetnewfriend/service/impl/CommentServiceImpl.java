package com.meetnewfriend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.CommentEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.repository.CommentREPO;
import com.meetnewfriend.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentREPO commentRepo;
	
	//add comment on post
	public CommentEntity addComment(CommentEntity comment) {
		return this.commentRepo.save(comment);
	}
	
	@Transactional
	//delete post comments
	public void deletePost(int postId) {
		PostEntity post=new PostEntity();
		post.setId(postId);
		this.commentRepo.deleteByPostPostId(post);
	}
}
