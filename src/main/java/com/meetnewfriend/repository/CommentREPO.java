package com.meetnewfriend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Comment;
import com.meetnewfriend.entity.Post;

@Repository
public interface CommentREPO extends CrudRepository<Comment,Integer>{
	
	@Modifying
	@Query(value="delete from comments where post_post_id=:post",nativeQuery=true)
	public void deleteByPostPostId(@Param("post") Post postId);
}
