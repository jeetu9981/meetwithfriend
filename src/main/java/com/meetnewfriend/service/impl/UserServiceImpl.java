package com.meetnewfriend.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.UserRepo;
import com.meetnewfriend.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	
	
	//Add user
	public UserEntity addUser(UserEntity userEntity){
		String userName = userEntity.getName().trim();
		if (userEntity.getName().length() < 5) {
			userEntity.setName("User " + userName);
		}
		userName = userName + new Random().nextInt(1000);
		userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
		userEntity.setUserName(userName);

		userEntity.setLoginFirst(false);
		return this.userRepo.save(userEntity);
	}
	
	//user signin
	public UserEntity signin(UserEntity userEntity) {
		return this.userRepo.findByEmailAndPassword(userEntity.getEmail(),userEntity.getPassword());
	}
	
	//update when user Login Fisrt
	public boolean updateLoginFirst(int id){
		if(this.userRepo.updateById(id)>0) {
			return true;
		}
		return false;
	}
	
	
	//this method is use for save first time detail of user when he/she login first time in application
	public boolean updateUserDetail(int id,UserEntity userEntity) {
		if(this.userRepo.updateUserDeatil(userEntity.getFavouritBooks(),userEntity.getFavouritePlaces(),userEntity.getFavouriteSongs(),userEntity.getImage(),id)>0)
			return true;
		return false;
	}
	
	
	//this method is use for get user
	public UserEntity getUser(int id){
		return this.userRepo.findById(id).get();
	}
	
	//get user by name
	public List<UserEntity> search(String name){
		return this.userRepo.findByName(name);
	}
	
	//update user
	public int updateUser(int userId,UserEntity userEntity) {
		return this.userRepo.updateUser(userEntity.getFavouritBooks(),userEntity.getFavouritePlaces(),userEntity.getFavouriteSongs(),userEntity.getImage(),userEntity.getEmail(),userEntity.getName(),userId);
	}

	
	//Edit user profile
	public UserEntity edituserprofile(int userId) {
		return this.userRepo.findById(userId).get();
	}
}
