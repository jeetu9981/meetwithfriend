package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.RealFollower;

@Repository
public interface RealFollowerRepo extends CrudRepository<RealFollower, Integer>{
	@Query(value="select count(*) from realfollowers where user_id=:id",nativeQuery = true)
	public int countFollower(@Param("id") int id);
	
	@Query("select rf from RealFollower rf where rf.user_id=:userId")
	public List<RealFollower> findAllById(@Param("userId") int userId);
	
	@Query(value="select * from realfollowers where user_id=:acceptUser and follower=:userId",nativeQuery = true)
	public RealFollower findByUser_idAndfollower(@Param("acceptUser") int acceptUser,@Param("userId") int userId);
	
	
	@Modifying
	@Query(value="delete from realfollowers where user_id=:userId and follower=:follower",nativeQuery = true)
	public int deleteRealFollower(@Param("userId") int userId,@Param("follower") int follower);
}
