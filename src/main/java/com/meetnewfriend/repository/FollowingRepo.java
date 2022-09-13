package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.User;

@Repository
public interface FollowingRepo extends CrudRepository<Following,Integer>{
	@Query(value="select count(*) from following where user_id=:id",nativeQuery = true)
	public int countFollowing(@Param("id") int id);
	
	@Query(value="select f from Following f where f.user_id=:userId")
	public List<Following> findFollowings(@Param("userId") int userId);
	
	@Modifying
	@Query(value="delete from following where following=:follow and user_id=:userId",nativeQuery = true)
	public int deleteFollowing(@Param("follow") User follow,@Param("userId") int userId);
	
	@Modifying
	@Query(value="delete from following where following=:blockUser and user_id=:real",nativeQuery = true)
	public int deleteByBlockUser(@Param("blockUser")int blockUser,@Param("real")int realUser);
	
	
	@Query("select rf from Following rf where rf.user_id=:userId ")
	public List<Following> findAllById(@Param("userId") int userId);
}
