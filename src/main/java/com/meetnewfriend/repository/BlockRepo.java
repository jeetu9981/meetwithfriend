package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Block;
import com.meetnewfriend.entity.User;

@Repository
public interface BlockRepo extends CrudRepository<Block,Integer>{
	public List<Block> findByRealUser(User userId);
	
	@Modifying
	@Query(value="delete from block where block_user_user_id=:blockUser and real_user_user_id=:realUser",nativeQuery = true)
	public int unblockUser(@Param("blockUser") int blockUser,@Param("realUser") int realUser);
}
