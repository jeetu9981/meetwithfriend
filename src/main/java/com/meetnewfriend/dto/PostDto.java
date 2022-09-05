package com.meetnewfriend.dto;

import java.util.List;

import com.meetnewfriend.entities.CommentEntity;
import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.UserEntity;

public class PostDto {
	private int id;
	private String description;
	private String image;
	
	
	private UserEntity user;
	
	private List<LikeEntity> likes;
	
	private List<CommentEntity> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<LikeEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeEntity> likes) {
		this.likes = likes;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}
	
	
	
	
}
