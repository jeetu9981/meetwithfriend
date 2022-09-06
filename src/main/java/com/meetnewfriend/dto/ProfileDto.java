package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.RealFollower;
import com.meetnewfriend.entity.User;

public class ProfileDto {
	private List<Post> posts;
	User user;
	private int countFollowers;
	private int countFollowing;
	private List<RealFollower> followers;
	private List<Following> followings;
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCountFollowers() {
		return countFollowers;
	}
	public void setCountFollowers(int countFollowers) {
		this.countFollowers = countFollowers;
	}
	public int getCountFollowing() {
		return countFollowing;
	}
	public void setCountFollowing(int countFollowing) {
		this.countFollowing = countFollowing;
	}
	
	public List<RealFollower> getFollowers() {
		return followers;
	}
	public void setFollowers(List<RealFollower> followers) {
		this.followers = followers;
	}
	public List<Following> getFollowings() {
		return followings;
	}
	public void setFollowings(List<Following> followings) {
		this.followings = followings;
	}
	
	
	
	
	
	
}
