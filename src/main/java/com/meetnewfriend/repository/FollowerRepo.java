package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.entity.User;

@Repository
public interface FollowerRepo extends CrudRepository<Follower, Integer>{
	@Query(value="select * from followers where accept_user=:id and accept=:accept or (accept_user=:id and follow_back=:follow)",nativeQuery = true)
	public List<Follower> findByUserIdAndAccept(@Param("id") int id,@Param("accept") boolean accept,@Param("follow") boolean follow);
	
	@Query(value="select * from followers where send_user_request=:id and accept_user=:acceptUser ",nativeQuery = true)
	public Follower findByUserIdAndAcceptAndSendUser(@Param("id") int id,@Param("acceptUser") int acceptUser);
	
	@Modifying
	@Query(value="update followers set accept=:a where accept_user=:acceptUser and send_user_request=:userId",nativeQuery = true)
	public int updateAcceptRequest(@Param("a") boolean a,@Param("acceptUser") int acceptUser,@Param("userId") int userId);
	
	@Modifying
	@Query(value = "delete from followers where accept_user=:acceptUser and send_user_request=:userId",nativeQuery = true)
	public int deleteByUserIdAndFollowerId(@Param("acceptUser") int acceptUser,@Param("userId") int userId);
	
	@Query(value="select f from Follower f where f.acceptUser=:acceptUser and f.sendUserRequest=:userEntity")
	public Follower findByUser_idAndfollower(@Param("acceptUser") int acceptUser,@Param("userEntity") User user);
}
