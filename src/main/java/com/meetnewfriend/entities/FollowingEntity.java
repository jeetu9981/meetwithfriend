package com.meetnewfriend.entities;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="following")
public class FollowingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="following_id")
	private int id;
	
	private int user_id;
	
	@OneToOne
	@JoinColumn(name="following")
	private UserEntity following;

	@Override
	public String toString() {
		return "FollowingEntity [id=" + id + ", user_id=" + user_id + ", following=" + following + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public UserEntity getFollowing() {
		return following;
	}

	public void setFollowing(UserEntity following) {
		this.following = following;
	}
	
	
}
