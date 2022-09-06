package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;

@Repository
public interface PostRepo extends CrudRepository<Post,Integer>{
	public List<Post> findByUserId(@Param("id") int id);
	
	@Modifying
	@Query(value="delete from posts where post_id=:post and user_id=:user",nativeQuery = true)
	public void deletePost(@Param("post") Post post,@Param("user") User user);
}
