package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Like;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;

@Repository
public interface LikeRepo extends CrudRepository<Like, Integer>{
	public Like findByPostAndUserAndRealuser(@Param("postId") Post postId,@Param("likeUser") User likeUser,@Param("realUser") int realUser);
	
	public List<Like> findByRealuser(@Param("user") int user);
	
	
	@Modifying
	@Query(value="delete from likes where user_user_id=:user and post_post_id=:post and realuser=:realUser",nativeQuery = true)
	public int deleteLike(@Param("user") User user,@Param("post") Post post,@Param("realUser") int realUser);
	
	
	@Modifying
	@Query(value="delete from likes where post_post_id=:post",nativeQuery = true)
	public void deletePost(@Param("post") Post post);
}
