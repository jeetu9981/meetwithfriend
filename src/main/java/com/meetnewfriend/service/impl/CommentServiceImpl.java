package com.meetnewfriend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entity.Comment;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.CommentREPO;
import com.meetnewfriend.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentREPO commentRepo;
	
	//add comment on post
	public boolean addComment(int userId,int postId,int commentUser,String userComment) {
		if(userComment.trim().length()==0)
			return false;
		User user=new User();
		user.setId(userId);
		
		Post post=new Post();
		post.setId(postId);
		
		Comment comment=new Comment();
		comment.setPost(post);
		comment.setUser(user);
		comment.setRealuser(commentUser);
		comment.setComment(userComment);
		if(this.commentRepo.save(comment)!=null)
			return true;
		return false;
	}
	
	@Transactional(rollbackFor = Exception.class)
	//delete post comments
	public void deletePost(int postId) {
		Post post=new Post();
		post.setId(postId);
		this.commentRepo.deleteByPostPostId(post);
	}
}
