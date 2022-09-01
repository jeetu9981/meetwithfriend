package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.FollowerEntity;
import com.meetnewfriend.entities.RealFollowerEntity;
import com.meetnewfriend.entities.UserEntity;

@Repository
public interface FollowerRepo extends CrudRepository<FollowerEntity, Integer>{
	@Query(value="select * from followers where accept_user=:id and accept=:accept or (accept_user=:id and follow_back=:follow)",nativeQuery = true)
	public List<FollowerEntity> findByUserIdAndAccept(@Param("id") int id,@Param("accept") boolean accept,@Param("follow") boolean follow);
	
	
	@Modifying
	@Query(value="update followers set accept=:a where accept_user=:acceptUser and send_user_request=:userId",nativeQuery = true)
	public int updateAcceptRequest(@Param("a") boolean a,@Param("acceptUser") int acceptUser,@Param("userId") int userId);
	
	@Modifying
	@Query(value = "delete from followers where accept_user=:acceptUser and send_user_request=:userId",nativeQuery = true)
	public int deleteByUserIdAndFollowerId(@Param("acceptUser") int acceptUser,@Param("userId") int userId);
	
	@Query(value="select f from FollowerEntity f where f.acceptUser=:acceptUser and f.sendUserRequest=:userEntity")
	public FollowerEntity findByUser_idAndfollower(@Param("acceptUser") int acceptUser,@Param("userEntity") UserEntity userEntity);
}
