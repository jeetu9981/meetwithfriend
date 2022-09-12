package com.meetnewfriend.dto;

import com.meetnewfriend.entity.User;

public class SerachUserDto {
	private User user;
	private boolean followStatus;
	private boolean followBackStatus;
	private boolean declineRequest;
	private int mutualFriends;
	
	public boolean isDeclineRequest() {
		return declineRequest;
	}
	public void setDeclineRequest(boolean declineRequest) {
		this.declineRequest = declineRequest;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(boolean followStatus) {
		this.followStatus = followStatus;
	}
	public SerachUserDto(User user, boolean followStatus) {
		super();
		this.user = user;
		this.followStatus = followStatus;
	}
	public SerachUserDto() {
		super();
	}
	public boolean isFollowBackStatus() {
		return followBackStatus;
	}
	public void setFollowBackStatus(boolean followBackStatus) {
		this.followBackStatus = followBackStatus;
	}
	public int getMutualFriends() {
		return mutualFriends;
	}
	public void setMutualFriends(int mutualFriends) {
		this.mutualFriends = mutualFriends;
	}
	
	
	
}
