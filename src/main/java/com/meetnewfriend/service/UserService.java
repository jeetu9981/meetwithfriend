package com.meetnewfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.repository.UserRepo;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public UserEntity addUser(UserEntity userEntity){
		return this.userRepo.save(userEntity);
	}
	
	public UserEntity signin(UserEntity userEntity) {
		return this.userRepo.findByEmailAndPassword(userEntity.getEmail(),userEntity.getPassword());
	}
	
	public boolean updateLoginFirst(int id){
		if(this.userRepo.updateById(id)>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateUserDetail(int id,UserEntity userEntity) {
		if(this.userRepo.updateUserDeatil(userEntity.getFavouritBooks(),userEntity.getFavouritePlaces(),userEntity.getFavouriteSongs(),userEntity.getImage(),id)>0)
			return true;
		return false;
	}
	
	public UserEntity getUser(int id){
		return this.userRepo.findById(id).get();
	}
	
	public List<UserEntity> search(String name){
		return this.userRepo.findByName(name);
	}
	
	public int updateUser(int userId,UserEntity userEntity) {
		return this.userRepo.updateUser(userEntity.getFavouritBooks(),userEntity.getFavouritePlaces(),userEntity.getFavouriteSongs(),userEntity.getImage(),userEntity.getEmail(),userEntity.getName(),userId);
	}
}
