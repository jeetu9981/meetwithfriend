package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;

@Repository
public interface LikeRepo extends CrudRepository<LikeEntity, Integer>{
	public LikeEntity findByPostAndUserAndRealuser(@Param("postId") PostEntity postId,@Param("likeUser") UserEntity likeUser,@Param("realUser") int realUser);
	
	public List<LikeEntity> findByRealuser(@Param("user") int user);
	
	
	@Modifying
	@Query(value="delete from likes where user_user_id=:user and post_post_id=:post and realuser=:realUser",nativeQuery = true)
	public int deleteLike(@Param("user") UserEntity user,@Param("post") PostEntity post,@Param("realUser") int realUser);
	
	
	@Modifying
	@Query(value="delete from likes where post_post_id=:post",nativeQuery = true)
	public void deletePost(@Param("post") PostEntity post);
}
