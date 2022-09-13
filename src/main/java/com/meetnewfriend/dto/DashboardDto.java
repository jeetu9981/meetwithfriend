package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entity.Following;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.Story;

public class DashboardDto {
	private List<Following> following;
	private List<Post> posts;
	private List<Story> myStory;
	private List<Story> myFollowingStory;
	
	
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
	public List<Story> getMyStory() {
		return myStory;
	}
	public void setMyStory(List<Story> myStory) {
		this.myStory = myStory;
	}
	public List<Story> getMyFollowingStory() {
		return myFollowingStory;
	}
	public void setMyFollowingStory(List<Story> myFollowingStory) {
		this.myFollowingStory = myFollowingStory;
	}
	
	
}
