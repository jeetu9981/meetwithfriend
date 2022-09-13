package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entity.Story;
import com.meetnewfriend.entity.User;

@Repository
public interface StoryRepo extends CrudRepository<Story,Integer>{
	 @Query(value="select st from Story st where st.user=:userId")
	public List<Story> findByUserId(@Param("userId") User userId);
}
