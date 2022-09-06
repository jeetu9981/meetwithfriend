package com.meetnewfriend.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.controller.UserController;
import com.meetnewfriend.dto.DashboardDto;
import com.meetnewfriend.dto.ProfileDto;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.UserRepo;
import com.meetnewfriend.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private FollowingServiceImpl followingServiceImpl;

	@Autowired
	private RealFollowerServiceImpl realFollowerServiceImpl;

	@Autowired
	private PostServiceImpl postServiceImpl;

	// Add user
	public String addUser(User user) {
		String userName = user.getName().trim();
		String email=user.getEmail().trim();
		String password=user.getPassword().trim();
		
		if(email.length()==0 || password.length()==0)
			return "invaliddata";
		
		if (user.getName().length() < 5) {
			user.setName("User " + userName);
		}
		userName = userName + new Random().nextInt(1000);
		userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
		user.setUserName(userName);
		user.setLoginFirst(false);

		if (user.getCaptcha().equals(UserController.hidden)) {
			if (this.userRepo.save(user)!= null) {
				return "success";
			} 
		} else {
			return "captchafail";
		}
		return "fail";
	}

	// user signin
	public User signin(User user) {
		return this.userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	// update when user Login Fisrt
	public boolean updateLoginFirst(int id) {
		if (this.userRepo.updateById(id) > 0) {
			return true;
		}
		return false;
	}

	// this method is use for save first time detail of user when he/she login first
	// time in application
	public boolean updateUserDetail(int id, User user, MultipartFile image) {
		if (!image.isEmpty()) {
			// this peace of code use for upload image
			String fileName = image.getOriginalFilename().trim();
			try {
				InputStream is = image.getInputStream();
				String path = "C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"
						+ fileName;
				int bytes = 0;
				FileOutputStream fs = new FileOutputStream(path);
				while ((bytes = is.read()) != -1)
					fs.write(bytes);
				fs.close();

				user.setImage(fileName);
			} catch (Exception e) {
				return false;
			}
		} else {
			User user1 = this.getUser(id);
			user1.setImage(user.getImage());
		}
		if (this.userRepo.updateUserDeatil(user.getFavouritBooks(), user.getFavouritePlaces(),
				user.getFavouriteSongs(), user.getImage(), id) > 0)
			return true;
		return false;
	}

	// this method is use for get user
	public User getUser(int id) {
		return this.userRepo.findById(id).get();
	}

	// get user by name
	public List<User> search(String name) {
		return this.userRepo.findByName(name);
	}

	// Edit user profile
	public User edituserprofile(int userId) {
		return this.userRepo.findById(userId).get();
	}

	// for get user profile
	public ProfileDto getProfile(int userId) {
		ProfileDto profile = new ProfileDto();

		// here we get all posts of user
		List<Post> allposts = this.postServiceImpl.get(userId);

		// here we get user particular user detail.
		User user = this.userServiceImpl.getUser(userId);

		// here we count user followers
		int countFollower = this.realFollowerServiceImpl.countFollower(userId);

		// here we count user following
		int countFollowing = this.followingServiceImpl.countFollowing(userId);

		// here we will get user all followers
		List<RealFollower> followers = this.realFollowerServiceImpl.getFollower(userId);

		// get user following
		List<Following> following = this.followingServiceImpl.getFollwing(userId);

		profile.setCountFollowers(countFollower);
		profile.setCountFollowing(countFollowing);
		profile.setFollowers(followers);
		profile.setFollowings(following);
		profile.setPosts(allposts);
		profile.setUser(user);
		return profile;
	}

	public DashboardDto getDashboard(int userId) {
		DashboardDto dashboard = new DashboardDto();
		// here we will get all followers
		List<RealFollower> followers = this.realFollowerServiceImpl.getFollower(userId);
		ArrayList<Integer> allUsersId = new ArrayList<Integer>();

		// get all followers id with the help of them id we can find them all posts
		for (RealFollower p : followers) {
			allUsersId.add(p.getFollower().getId());
		}

		// here we will get our follower all posts
		List<Post> posts = this.postServiceImpl.getAllPost(allUsersId);

		dashboard.setFollowers(followers);
		dashboard.setPosts(posts);
		return dashboard;
	}
}
