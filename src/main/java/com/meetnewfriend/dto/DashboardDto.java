package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.Post;

public class DashboardDto {
	private List<Following> following;
	private List<Post> posts;
	
	
	
	public List<Following> getFollowing() {
		return following;
	}
	public void setFollowing(List<Following> following) {
		this.following = following;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
