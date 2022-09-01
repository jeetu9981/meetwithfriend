package com.meetnewfriend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.PostEntity;

@Repository
public interface PostRepo extends CrudRepository<PostEntity,Integer>{
	public List<PostEntity> findByUserId(@Param("id") int id);
}
