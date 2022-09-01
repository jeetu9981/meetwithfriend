package com.meetnewfriend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meetnewfriend.entities.CommentEntity;

@Repository
public interface CommentREPO extends CrudRepository<CommentEntity,Integer>{

}
