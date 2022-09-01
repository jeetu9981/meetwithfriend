package com.meetnewfriend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="followers")
public class FollowerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="follower_id")
	private int id;
	
	private int acceptUser;
	
	@OneToOne
	@JoinColumn(name="sendUserRequest")
	private UserEntity sendUserRequest;
	
	private boolean accept;
	private boolean followBack;

	

	@Override
	public String toString() {
		return "FollowerEntity [id=" + id + ", acceptUser=" + acceptUser + ", sendUserRequest=" + sendUserRequest
				+ ", accept=" + accept + ", followBack=" + followBack + "]";
	}

	public boolean getFollowBack() {
		return followBack;
	}

	public void setFollowBack(boolean followBack) {
		this.followBack = followBack;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcceptUser() {
		return acceptUser;
	}

	public void setAcceptUser(int acceptUser) {
		this.acceptUser = acceptUser;
	}

	public UserEntity getSendUserRequest() {
		return sendUserRequest;
	}

	public void setSendUserRequest(UserEntity sendUserRequest) {
		this.sendUserRequest = sendUserRequest;
	}

	public boolean getAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	
	
	
}
