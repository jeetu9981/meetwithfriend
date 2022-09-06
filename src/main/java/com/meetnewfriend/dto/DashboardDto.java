package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.RealFollower;

public class DashboardDto {
	private List<RealFollower> followers;
	private List<Post> posts;
	public List<RealFollower> getFollowers() {
		return followers;
	}
	public void setFollowers(List<RealFollower> followers) {
		this.followers = followers;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
