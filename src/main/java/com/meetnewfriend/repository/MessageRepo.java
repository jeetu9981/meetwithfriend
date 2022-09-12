package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.RecieverMessages;
import com.meetnewfriend.entity.User;

@Repository
public interface MessageRepo extends CrudRepository<RecieverMessages, Integer>{
	@Query("select rc from RecieverMessages rc where rc.reciverId=:rId and rc.senderId=:sId")
	public List<RecieverMessages> getRecieverMessage(@Param("rId")int rId,@Param("sId") User sId);
	
	@Query("select rc from RecieverMessages rc where rc.reciverId=:rId and rc.senderId=:sId")
	public List<RecieverMessages> getSenderMessage(@Param("rId")int rId,@Param("sId") User sId);
}
