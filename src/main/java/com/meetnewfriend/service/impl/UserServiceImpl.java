package com.meetnewfriend.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.controller.UserController;
import com.meetnewfriend.dto.DashboardDto;
import com.meetnewfriend.dto.ProfileDto;
import com.meetnewfriend.dto.SerachUserDto;
import com.meetnewfriend.entity.Block;
import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.entity.Story;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.BlockRepo;
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

	@Autowired
	private FollowerServiceImpl followerServiceImpl;

	@Autowired
	private BlockRepo blockRepo;
	
	@Autowired
	private StoryServiceImpl storyServiceImpl;

	// Add user
	public String addUser(User user) {
		String userName = user.getName().trim();
		String email = user.getEmail().trim();
		String password = user.getPassword().trim();

		System.out.println(Pattern.matches("/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/", email));

		if (email.length() == 0 || password.length() == 0)
			return "invaliddata";

		if (user.getName().length() < 5) {
			user.setName("User " + userName);
		}
		userName = userName + new Random().nextInt(1000);
		userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
		user.setUserName(userName);
		user.setLoginFirst(false);

		if (user.getCaptcha().equals(UserController.hidden)) {
			if (this.userRepo.save(user) != null) {
				return "success";
			}
		} else {
			return "captchafail";
		}
		return "fail";
	}

	// user signin
	public User signin(User user) {
		String email = user.getEmail().trim();
		String password = user.getPassword();

		return this.userRepo.findByEmailAndPassword(email, password);
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
	public String updateUserDetail(int id, User user, MultipartFile image) {

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
				return "fail";
			}
		} else {
			User user1 = this.getUser(id);
			user1.setImage(user.getImage());
		}
		if (this.userRepo.updateUserDeatil(user.getFavouritBooks(), user.getFavouritePlaces(), user.getFavouriteSongs(),
				user.getImage(), id) > 0)
			return "success";
		return "fail";
	}

	// this method is use for get user
	public User getUser(int id) {
		return this.userRepo.findById(id).get();
	}

	// get user by name
	public List<SerachUserDto> search(String name, int userId) {
		ArrayList<User> users = (ArrayList<User>) this.userRepo.findByName(name);
		ArrayList<Block> blocks = (ArrayList<Block>) this.blockRepo.findAll();

		ArrayList<User> newUsers = new ArrayList<User>();

		// here we select only unblock users
		boolean status;
		for (int i = 0; i < users.size(); i++) {
			status = true;
			for (int j = 0; j < blocks.size(); j++) {
				if (blocks.get(j).getBlockUser().getId() == userId
						&& blocks.get(j).getRealUser().getId() == users.get(i).getId()
						|| blocks.get(j).getBlockUser().getId() == users.get(i).getId()
								&& blocks.get(j).getRealUser().getId() == userId) {
					status = false;
					break;
				}
			}
			if(users.get(i).getId()==userId)
				continue;
			if (status)
				newUsers.add(users.get(i));
		}

		// after selecting ublock user we can elect follow or unfollow user
		ArrayList<SerachUserDto> searchUsers = new ArrayList<SerachUserDto>();

		List<Following> following = this.followingServiceImpl.getFollowing(userId);

		SerachUserDto userDto;
		for (int i = 0; i < newUsers.size(); i++) {
			status = true;
			userDto = new SerachUserDto();
			// check can we follow before or not if follow then set followstatus true
			for (int j = 0; j < following.size(); j++) {
				if (following.get(j).getFollowing().getId() == newUsers.get(i).getId()) {
					status = false;
					break;
				}
			}
			if (status) {
				Follower follower = this.followerServiceImpl.getFollowerRequest(userId, newUsers.get(i).getId());
				if (follower != null) {
					if (follower.getAccept()) {
						userDto.setFollowBackStatus(true);
						userDto.setUser(newUsers.get(i));
					} else if (!follower.getFollowBack() && !follower.getAccept()
							&& userId != follower.getAcceptUser()) {
						userDto.setDeclineRequest(true);
						userDto.setUser(newUsers.get(i));
					}
				} else {
					follower = this.followerServiceImpl.getFollowerRequest(newUsers.get(i).getId(), userId);
					if (follower != null) {
						userDto.setFollowBackStatus(true);
						userDto.setUser(newUsers.get(i));
					} else {
						userDto.setFollowStatus(false);
						userDto.setUser(newUsers.get(i));
					}
				}
			} else {
				userDto.setFollowStatus(true);
				userDto.setUser(newUsers.get(i));
			}
			searchUsers.add(userDto);
		}

		for (int i = 0; i < searchUsers.size(); i++) {
			int count = 0;
			List<Following> userFollowing = this.followingServiceImpl.getFollowing(searchUsers.get(i).getUser().getId());
			for (int j = 0; j < userFollowing.size(); j++) {
				for (int k = 0; k < following.size(); k++) {
					if (userFollowing.get(j).getFollowing().getId() == following.get(k).getFollowing().getId()) {
						count++;
					}
				}
			}
			if(count>0)
					searchUsers.get(i).setMutualFriends(count);
		}
		return searchUsers;
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
//		List<RealFollower> followers = this.realFollowerServiceImpl.getFollower(userId);
		List<Following> following = this.followingServiceImpl.getFollowing(userId);
		ArrayList<Integer> allUsersId = new ArrayList<Integer>();

		// get all followers id with the help of them id we can find them all posts
		for (Following p : following) {
			allUsersId.add(p.getFollowing().getId());
		}

		// here we will get our follower all posts
		List<Post> posts = this.postServiceImpl.getAllPost(allUsersId);
		
		List<Story> myStory=this.storyServiceImpl.getMystory(userId);
		List<Story> myFollowingStory=this.storyServiceImpl.getAllStory();
		
		ArrayList<Story> followingStory=new ArrayList<Story>();
		
		for(int i=0;i<myFollowingStory.size();i++) {
			for(int j=0;j<following.size();j++)
			{
				if(following.get(j).getFollowing().getId()==myFollowingStory.get(i).getUser().getId())
					followingStory.add(myFollowingStory.get(i));
			}
		}

		dashboard.setMyStory(myStory);
		dashboard.setFollowing(following);
		dashboard.setMyFollowingStory(followingStory);
		dashboard.setPosts(posts);
		return dashboard;
	}
}
