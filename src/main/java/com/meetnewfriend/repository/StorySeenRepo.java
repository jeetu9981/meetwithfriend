package com.meetnewfriend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.StorySeen;
import com.meetnewfriend.entity.User;

@Repository
public interface StorySeenRepo extends CrudRepository<StorySeen, Integer>{
	
	@Query("select st from StorySeen st where st.user=:userId")
	public StorySeen findByUser(@Param("userId") User userId);
}
