package com.meetnewfriend.services;

import java.util.List;

import com.meetnewfriend.entities.UserEntity;

public interface UserService{
	//Add user
	public UserEntity addUser(UserEntity userEntity);
	
	//user signin
	public UserEntity signin(UserEntity userEntity);
	
	//this method is use for save first time detail of user when he/she login first time in application
	public boolean updateUserDetail(int id,UserEntity userEntity);
	
	//this method is use for get user
	public UserEntity getUser(int id);
	
	//update when user Login Fisrt
	public boolean updateLoginFirst(int id);
	
	//get user by name
	public List<UserEntity> search(String name);
	
	//update user
	public int updateUser(int userId,UserEntity userEntity);
	
	//Edit user profile
	public UserEntity edituserprofile(int userId);
}
