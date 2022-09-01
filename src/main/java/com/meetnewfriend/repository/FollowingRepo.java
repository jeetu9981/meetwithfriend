package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.UserEntity;

@Repository
public interface FollowingRepo extends CrudRepository<FollowingEntity,Integer>{
	@Query(value="select count(*) from following where user_id=:id",nativeQuery = true)
	public int countFollowing(@Param("id") int id);
	
	@Query(value="select f from FollowingEntity f where f.user_id=:userId")
	public List<FollowingEntity> findFollowings(@Param("userId") int userId);
	
	@Modifying
	@Query(value="delete from following where following=:follow and user_id=:userId",nativeQuery = true)
	public int deleteFollowing(@Param("follow") UserEntity follow,@Param("userId") int userId);
}
