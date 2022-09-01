package com.meetnewfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetnewfriend.entities.CommentEntity;
import com.meetnewfriend.repository.CommentREPO;

@Service
public class CommentService {
	@Autowired
	private CommentREPO commentRepo;
	
	public CommentEntity addComment(CommentEntity comment) {
		return this.commentRepo.save(comment);
	}
}
