package com.meetnewfriend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.dto.DashboardDto;
import com.meetnewfriend.dto.ProfileDto;
import com.meetnewfriend.dto.SerachUserDto;
import com.meetnewfriend.entity.User;

public interface UserService{
	//Add user
	public String addUser(User user);
	
	//user signin
	public User signin(User user);
	
	//this method is use for save first time detail of user when he/she login first time in application
	public String updateUserDetail(int id,User user,MultipartFile image);
	
	//this method is use for get user
	public User getUser(int id);
	
	//update when user Login Fisrt
	public boolean updateLoginFirst(int id);
	
	//get user by name
	public List<SerachUserDto> search(String name,int userId);
	
	
	//Edit user profile
	public User edituserprofile(int userId);
	
	//for user profile
	public ProfileDto getProfile(int userId);
	
	//for user dashboard
	public DashboardDto getDashboard(int userId);
}
